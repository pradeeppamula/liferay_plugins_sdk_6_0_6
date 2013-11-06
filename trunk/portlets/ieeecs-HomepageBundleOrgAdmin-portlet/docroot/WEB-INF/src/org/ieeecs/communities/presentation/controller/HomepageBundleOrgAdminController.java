/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created October 1, 2013
 * @description This class will function as the action handler for the BundleOrgAdmin portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.DateUtil;
import org.ieeecs.communities.util.HomepageBundleOrgAdminUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomepageBundleOrgAdminController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageBundleOrgAdminController.class);
    protected StringBuilder addOrganizationErrors;

    /**
     * Helper method that will clean up all necessary resources
     * when the bean is destroyed.
     * @throws Exception
     */
    public void cleanUp() throws Exception {
        // close all mongo connections
        MongoHandler.getInstance().close();
    }

    /**
     * This atomic helper method will retrieve the portlet meta data based on
     * the passed in portlet name.
     * TODO: Move this to common CSDL Handler
     *
     * @param portletName
     * @return DBObject
     * @throws Exception
     */
    public DBObject getPortletMetaData(String portletName) throws Exception {
        DBObject retVal = null;
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("portletName", portletName);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PORTLET_META_DATA, query);
            // if there is a result, grab the first one
            if(results != null && results.size() > 0) {
                retVal = results.get(0);
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the portlet data.",  e);
        }
        return retVal;
    }

    /**
     * The function will update the bundle data of all the users of the organization based on
     * the passed in organization id and various bundle json.
     *
     * @param orgId
     * @param articleBundleJSON
     * @param webinarBundleJSON
     * @return boolean
     * @throws Exception
     */
    private boolean updateOrganizationBundleData(String orgId, String articleBundleJSON, String webinarBundleJSON) throws Exception {
        boolean retVal = false;
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject();
            query.put("organization_id", new ObjectId(orgId));

            // convert all of the json to a DBobject so we can save in mongo
            BasicDBList articleBundleData = MongoHandler.getInstance().jsonToDBList(articleBundleJSON);
            BasicDBList webinarBundleData = MongoHandler.getInstance().jsonToDBList(webinarBundleJSON);

            // build up the data for the update
            BasicDBObject bundleData = new BasicDBObject();
            bundleData.append("bundle.csdl_article", articleBundleData);
            bundleData.append("bundle.webinar",webinarBundleData);


            // grab all the skus for the webinars and save them to the organization user's units for webinars
            BasicDBObject webinar = (BasicDBObject)webinarBundleData.get(0);
            BasicDBList webinarSkus = new BasicDBList();
            if(webinar != null) {
                // get the selected items map
                BasicDBList selectedItems = (BasicDBList)webinar.get("selected_items");
                // add the skus to the list of webinar skus
                for(Object item : selectedItems) {
                    String sku = (String)((BasicDBObject)item).get("sku");
                    webinarSkus.add(sku);
                }
            }
            bundleData.append("units.webinars", webinarSkus);

            // finally add all the bundle data to the update data item for mongo
            BasicDBObject updateData = new BasicDBObject("$set", bundleData);

            // execute the query against the purchase collection for all the organization's users
            MongoHandler.getInstance().updateMultiple(MongoConfigUtil.Collection.PURCHASE, query, updateData);

            /*
             * update the organization's webinar and article bundle data.
             *
             * NOTE: In the future all user's will just pull from the organization's bundle
             * data not their own purchase data.  We are saving it to the purchase data and
             * organization right now which is a bit redundant.
             */
            query = new BasicDBObject();
            query.put("_id", new ObjectId(orgId));
            // build up the data for the organization update
            bundleData = new BasicDBObject();
            bundleData.append("csdl_article", articleBundleData);
            bundleData.append("webinar", webinarBundleData);
            updateData = new BasicDBObject("$set", bundleData);
            // execute the query against the organization collection
            MongoHandler.getInstance().update(MongoConfigUtil.Collection.ORGANIZATION, query, updateData);
            retVal = true;
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when updating the organization bundle data: ",  e);
        }
        return retVal;
    }

    /**
     * This method will update the organization in the datasource based on
     * the passed in organization JSON
     * @param orgId
     * @param orgJSON
     * @return  boolean
     * @throws Exception
     */
    private boolean updateOrganizationData(String orgId, String orgJSON) throws Exception {
        boolean retVal = false;
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(orgId));

            // convert the json to a DBobject so we can save in mongo
            BasicDBObject orgData = MongoHandler.getInstance().jsonToDBObject(orgJSON);

            // execute the query against the collection
            MongoHandler.getInstance().update(MongoConfigUtil.Collection.ORGANIZATION, query, orgData);
            retVal = true;
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when updating the organization: ",  e);
        }
        return retVal;
    }

    /**
     * Atomic helper method that will query the mongo db for
     * organizations based on the query parameter.  If no
     * parameter is passed in it will just retrieve all organizations.
     * @return String
     * @throws Exception
     */
    private String getOrganizations(BasicDBObject query) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            if(query == null) {
                query = new BasicDBObject();
            }

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.ORGANIZATION, query);
            // if there are results convert them to jSON
            if(results != null && results.size() > 0) {
                retVal = results.toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the organizations.",  e);
        }
        return retVal;
    }


    /**
     * Atomic helper method that will remove the user based on the passed
     * in orgId and userId or email.
     *
     * @param orgId
     * @param userId
     * @param email
     * @return String
     * @throws Exception
     */
    private boolean removeUserPurchaseData(String orgId, String userId, String email) throws Exception {
        boolean retVal = false;
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject();
            query.put("organization_id", new ObjectId(orgId));
            /* if the user if is present use that for deletion query,
             * otherwise we know it is a new user that doesn't have a
             * liferay account yet so we need to delete by their email.
             */
            if(userId != null && !"".equalsIgnoreCase(userId)) {
                query.put("user_id", Long.parseLong(userId));
            } else {
                query.put("email", email);
            }

            // execute the query against the collection
            retVal = MongoHandler.getInstance().remove(MongoConfigUtil.Collection.PURCHASE, query);
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when attempting to remove the user purchase data.",  e);
        }
        return retVal;
    }

    /**
     * Atomic helper method that will retrieve all of the users (purchase data)
     * based on the passed in organization id
     * @param orgId
     * @return String
     * @throws Exception
     */
    private String getOrganizationUsers(String orgId) throws Exception {
        String retVal = "[]";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject();
            query.put("organization_id", new ObjectId(orgId));

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);

            // if there are results convert them to jSON
            if(results != null && results.size() > 0) {
                /*
                 * Load the user's info here from Liferay and put on the results.
                 * If they don't have a Liferay account yet use their purchase data
                 * name.
                 */
                for(DBObject result : results) {
                    Object id = result.get("user_id");
                    Integer userId = null;
                    if(id != null && id instanceof Double) {
                      userId = ((Double)id).intValue();
                    } else {
                      userId = (Integer)id;
                    }
                    if(userId != null) {
                        User currUser = UserLocalServiceUtil.getUser(userId.longValue());
                        if(currUser != null) {
                            result.put("name", currUser.getFirstName() + " " + currUser.getLastName());
                        }
                    } else {  // just use their purchase data first and last name
                        String firstName = (String)result.get("firstName");
                        String lastName = (String)result.get("lastName");
                        String email = (String)result.get("email");
                        String name = (firstName != null && lastName != null) ? firstName + " " + lastName : (String)result.get("email");
                        result.put("name",name);
                        // also put the email on there as the email field
                        result.put("email", email);
                    }
                }
                retVal = results.toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the organization users purchase data.",  e);
        }
        return retVal;
    }

    /**
     * Atomic helper method that will build the organization JSON
     * @param organizationName
     * @return String
     * @throws Exception
     */
    private String buildOrganizationJSON(String organizationName) throws Exception {
        StringBuilder json = new StringBuilder();
        json.append("{\"name\":\"");
        json.append(organizationName);
        json.append("\",\"description\": \"\",\"modifiedDate\": \"\",\"createdDate\": \"");
        json.append(DateUtil.now());
        json.append("\",\"csdl_article\":[],\"webinar\": [{\"selected_items\": []}]}");
        return json.toString();
    }

    /**
     * Atomic helper method that will build the user purchase JSON
     * @param email
     * @return String
     * @throws Exception
     */
    private String buildUserPurchaseJSON(String email) throws Exception {
        StringBuilder json = new StringBuilder();
        json.append("{\"bundle\":{\"csdl_article\":[],\"webinar\":[{\"selected_items\":[]}]},");
        json.append("\"created_date\": \"");
        json.append(DateUtil.now());
        json.append("\",\"modified_date\": \"");
        json.append(DateUtil.now());
        json.append("\",\"units\": {\"csdl_article\":[],\"webinars\":[],\"essentialset\":[],\"readynote\":[]},\"email\": \"");
        json.append(email);
        json.append("\"}");
        return json.toString();
    }

    /**
     * This method will create the new organization in the datasource.  It will
     * first check to see if an organization with the same name already exists,
     * if so it will simply return that organization's id.
     * @param organizationName
     * @return String the organization id
     * @throws Exception
     */
    private String getOrganizationId(String organizationName) throws Exception {
        String retVal = null;
        // first check to see if the organization already exists
        BasicDBObject query = new BasicDBObject();
        query.put("name", organizationName);
        String organization = this.getOrganizations(query);
        // if so, get the id from the returned organization
        if(!"{}".equalsIgnoreCase(organization) && organization != null) {
            // convert the json to a DBobject so we can get the id
            BasicDBList orgObjectList = MongoHandler.getInstance().jsonToDBList(organization);
            // get the first organization (will only be one, function just returns a list)
            BasicDBObject orgObject = (BasicDBObject)orgObjectList.get(0);
            ObjectId id = (ObjectId)orgObject.get("_id");
            retVal = id.toString();
        } else {
            // if not, build the new organization json
            String orgJSON = this.buildOrganizationJSON(organizationName);
            // create the new organization in the mongo datasource

            // convert the json to a DBobject so we can save in mongo
            BasicDBObject orgData = MongoHandler.getInstance().jsonToDBObject(orgJSON);

            // execute the query against the collection
            MongoHandler.getInstance().insert(MongoConfigUtil.Collection.ORGANIZATION, orgData);

            // get the new organization
            organization = this.getOrganizations(query);

            // convert the json to a DBobject so we can get the id
            BasicDBList orgObjectList = MongoHandler.getInstance().jsonToDBList(organization);
            // get the first organization (will only be one, function just returns a list)
            BasicDBObject orgObject = (BasicDBObject)orgObjectList.get(0);
            ObjectId id = (ObjectId)orgObject.get("_id");
            retVal = id.toString();
        }
        return retVal;
    }

    /**
     * This method will create a new user purchase data for the organization by associating
     * it with the organization id passed in as a parameter.
     * @param organizationId
     * @param firstName
     * @param lastName
     * @param email
     * @return boolean
     */
    private boolean createUser(String organizationId, String firstName, String lastName, String email) {
        boolean retVal = false;
        try {
            // build up the new user json
            String userPurchaseJSON = this.buildUserPurchaseJSON(email);

            // convert the json to a DBobject so we can save in mongo
            BasicDBObject userPurchaseData = MongoHandler.getInstance().jsonToDBObject(userPurchaseJSON);

            // add in the organization id, firstname and lastname to the new user purchase object
            userPurchaseData.put("organization_id", new ObjectId(organizationId));
            userPurchaseData.put("firstName", firstName);
            userPurchaseData.put("lastName", lastName);

            /*
             * first validate that the user does not already exists for this
             * organization, if so add to the errors and return false
             */
            BasicDBObject userExistsQuery = new BasicDBObject("email", email);
            userExistsQuery.put("organization_id", new ObjectId(organizationId));
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, userExistsQuery);
            // if there are results convert them to jSON
            if(results != null && results.size() > 0) {
                // add the user exists error
                addOrganizationErrors.append("Error: user already exists for organization with email: ");
                addOrganizationErrors.append(email);
                addOrganizationErrors.append("<br />");
            } else {
                // execute the query against the collection
                MongoHandler.getInstance().insert(MongoConfigUtil.Collection.PURCHASE, userPurchaseData);
                retVal = true;
            }
        } catch (Exception e) {
            LOGGER.error("There was a problem creating the new user ("+ email+") for new org id: " + organizationId, e);
        }
        return retVal;
    }

    /**
     * Atomic helper method that will read in the a file from the
     * giving full path
     * @param neworgFile
     * @param sourceFileName
     * @return boolean
     */
    private boolean processNewOrganization(File neworgFile, String sourceFileName) {
        boolean retVal = false;
        boolean hasErrors = false;
        StringBuilder errors = new StringBuilder();
        BufferedReader in = null;
        // lineNumber for error reporting to the client
        int lineNumber = 1;
        int numberOfUsersCreated = 0;

        try {
            // read in the file
            in = new BufferedReader(new FileReader(neworgFile));
            String str;

            // now iterate over each of the lines
            while ((str = in.readLine()) != null) {
                boolean isMalformedLine = false;
                // split up the current line by the delimiter
                String[] ar=str.split(",");

                // Validation 1. Current Line Hard Fail - Tokens != 4
                if(ar.length != 4) {
                    errors.append("Line ");
                    errors.append(lineNumber);
                    errors.append(": Could not process, line was malformed. <br />");
                    hasErrors = true;
                    lineNumber++;
                    continue;
                }

                // get the values for the current line
                String organizationName = ar[0];
                String firstName = ar[1];
                String lastName = ar[2];
                String email = ar[3];

                // Validation 2. Current Line Hard Fail - Any of the tokens cannot equal the empty string after trim()
                if(organizationName == null || "".equalsIgnoreCase(organizationName)) {
                    isMalformedLine = true;
                }
                if(firstName == null || "".equalsIgnoreCase(firstName)) {
                    isMalformedLine = true;
                }
                if(lastName == null || "".equalsIgnoreCase(lastName)) {
                    isMalformedLine = true;
                }
                if(email == null || "".equalsIgnoreCase(email)) {
                    isMalformedLine = true;
                }

                // if there is a malformed line break this loop
                if(isMalformedLine) {
                    // add malformed line error
                    errors.append("Line ");
                    errors.append(lineNumber);
                    errors.append(": Could not process, line was malformed. <br />");
                    hasErrors = true;
                    lineNumber++;
                    continue;
                }

                // trim any whitespace from the data
                organizationName = organizationName.trim();
                firstName = firstName.trim();
                lastName = lastName.trim();
                email = email.trim();

                // if all initial validations pass, get the organization id
                String orgId = this.getOrganizationId(organizationName);

                // Validation 3. Current Line Hard Fail - Organization id equals null or empty string
                if(orgId == null || "".equalsIgnoreCase(orgId)) {
                    // add malformed line error
                    errors.append("Line ");
                    errors.append(lineNumber);
                    errors.append(": Could not process, unable to get id for organization ");
                    errors.append(organizationName);
                    errors.append(". <br />");
                    hasErrors = true;
                    lineNumber++;
                    continue;
                }

                // create the new user purchase data
                boolean result = this.createUser(orgId, firstName, lastName, email);

                //  Current Line Soft Fail - User creation failed
                if(!result) {
                    // add error for line number to response
                    errors.append("Line ");
                    errors.append(lineNumber);
                    errors.append(": Could not process, unable to create user. <br />");
                    hasErrors = true;
                } else {
                    numberOfUsersCreated++;
                }
                lineNumber++;
            }
            // check for errors if none, set retVal to true
            if(hasErrors) {
                addOrganizationErrors.append(errors.toString());
            }
            retVal = numberOfUsersCreated > 0;
        } catch(Exception e) {
            LOGGER.error("There was a problem processing the new organization file with name: " + sourceFileName, e);
            retVal = numberOfUsersCreated > 0;
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (Exception e) {} // digest exception
        }
        return retVal;
    }

    /**
     * This method will handle all ajax requests from the portlet.  As of now it will
     * simply read the data passed in based on the request type, and send an
     * email to the sales team.
     *
     * @param request
     * @param response
     */
    @Override
    public ModelAndView handleResourceRequest(ResourceRequest request,
                                              ResourceResponse response) throws Exception {
        ModelAndView modelAndView = null;
        ThemeDisplay themeDisplay = null;
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            // grab the ThemeDisplay that contains all needed information on the user
            themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
            int cropHere = portletId.indexOf("_INSTANCE_");
            String instanceId = "_" + portletId.substring(cropHere + 10);
            String currentUserId = new Long(themeDisplay.getUserId()).toString();

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");
            if(HomepageBundleOrgAdminUtil.REQUEST_TYPE_LOAD_ORGANIZATIONS.equalsIgnoreCase(requestType)) {
                // load the organizations from the datasource
                model.put("response", this.getOrganizations(null));
            } else if (HomepageBundleOrgAdminUtil.REQUEST_TYPE_LOAD_USERS.equalsIgnoreCase(requestType)) {
                // grab the organization id  from the request
                String orgId = ParamUtil.getString(request, "orgId_" + instanceId, "");
                // load the organization user's purchase data from the datasource
                model.put("response", this.getOrganizationUsers(orgId));
            } else if(HomepageBundleOrgAdminUtil.REQUEST_TYPE_REMOVE_USER.equalsIgnoreCase(requestType)) {
                // grab the user id, email, and organization id  from the request
                String userId = ParamUtil.getString(request, "userId_" + instanceId, "");
                String email =   ParamUtil.getString(request, "userEmail_" + instanceId, "");
                String orgId =   ParamUtil.getString(request, "userOrgId_" + instanceId, "");
                if(this.removeUserPurchaseData(orgId, userId, email)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            } else if(HomepageBundleOrgAdminUtil.REQUEST_TYPE_UPDATE_ORGANIZATION.equalsIgnoreCase(requestType)) {
                // grab the organization data json off the request
                String orgJSON  = ParamUtil.getString(request, "orgData_" + instanceId, "");
                String orgId  = ParamUtil.getString(request, "orgId_" + instanceId, "");
                // update the user's purchase data
                if(this.updateOrganizationData(orgId,orgJSON)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            } else if (HomepageBundleOrgAdminUtil.REQUEST_TYPE_SAVE_ORGANIZATION_BUNDLES.equalsIgnoreCase(requestType)) {
                // grab the organization article bundle data json off the request
                String articleBundleJSON  = ParamUtil.getString(request, "articleBundleData_" + instanceId, "");
                // grab the organization webinar bundle data json off the request
                String webinarBundleJSON  = ParamUtil.getString(request, "webinarBundleData_" + instanceId, "");
                // grab the organization's id
                String orgId  = ParamUtil.getString(request, "orgId_" + instanceId, "");
                if(this.updateOrganizationBundleData(orgId, articleBundleJSON, webinarBundleJSON)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when handling the resource request.", e);
            model.put("response", 422);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }

	/**
	 * This method is called before the view of the portlet is rendered.  It will 
	 * perform any necessary setup processes.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		ModelAndView modelAndView = null;
		ThemeDisplay themeDisplay = null;
		PortletPreferences prefs = null;
		boolean canInlineEdit = false;
		String groupId    = "";
        boolean isAuthenticated = false;

		try {
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// set the group id
			groupId = Long.toString(themeDisplay.getScopeGroupId());
			// grab the preferences from the request
			prefs = renderRequest.getPreferences();
			// set if the user is signed in or not
            isAuthenticated      = themeDisplay.isSignedIn();
			// add the default preferences to the model used in the view
			HomepageBundleOrgAdminUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageBundleOrgAdminUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepageBundleOrgAdminUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepageBundleOrgAdminUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}
			// -----------------------------------------------------------------------------------------------------------------
			// This will be moved in the next few versions to the "base" controller class.
			// -----------------------------------------------------------------------------------------------------------------
			User currentUser        = themeDisplay.getUser();
			
			boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());			
			boolean isLiferayAdmin  = renderRequest.isUserInRole("Administrator") ? true : false;
			
			if ( isAuthenticated && isMemberOfGroup && !isLiferayAdmin ) {
				List<Role> userGroupRoles = RoleLocalServiceUtil.getUserGroupRoles(currentUser.getUserId(), new Long(groupId));
				if ( null != userGroupRoles && userGroupRoles.size() > 0 ) {
					for ( int index = 0; index < userGroupRoles.size(); index++ ) {
						Role currentRole = userGroupRoles.get(index);
						String currentRoleName = currentRole.getName();								
						if ( "Community Owner".equals(currentRoleName) || 
							 "Community Administrator".equals(currentRoleName) || 
							 "Community ControlPanel".equals(currentRoleName) || 
							 "Community Editor".equals(currentRoleName) ) {
							canInlineEdit = true;									
						}	
					}	
				}
			}
			if ( isLiferayAdmin ) {
				canInlineEdit = true;
			}
            // load the portlet meta data if user is authenticated and is a liferay admin
            if(isAuthenticated && isLiferayAdmin) {
                DBObject results = getPortletMetaData(HomepageBundleOrgAdminUtil.META_PORTLET_NAME);
                if(results != null) {
                    model.put("webinars", results.get("webinars").toString());
                }

                 /*
                 * Check to see if there are any messages on the session from
                 * the add organization process.  If so add it to the view model.
                 */
                String errors = (String)SessionMessages.get(renderRequest, "addOrganzationErrors");
                if(errors != null && !"".equalsIgnoreCase(errors)) {
                    model.put("hasAddOrgErrors", true);
                    model.put("addOrganzationErrors", errors);
                }
                String success = (String)SessionMessages.get(renderRequest, "addOrganzationSuccess");
                if(success != null && !"".equalsIgnoreCase(success)) {
                    model.put("hasAddOrgSuccess", true);
                }
            }

		} catch (Exception e) {
			model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when rendering the portlet",  e);
		}

		// create the model for the View and add the model attributes to it
		model.put("id", instanceId);
		model.put("isAuthenticated", isAuthenticated);
        model.put("canInlineEdit", canInlineEdit);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}

    /**
     * This method will handle adding the new organization to the myHome application.
     * @param actionRequest
     * @param actionResponse
     */
    protected void handleActionRequestInternal(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String portletId = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
            int cropHere = portletId.indexOf("_INSTANCE_");
            String instanceId = "_" + portletId.substring(cropHere+10);
            // initialize the error string builder for processing this new organization
            addOrganizationErrors = new StringBuilder();

            // String fileData  = ParamUtil.getString(request, "orgFileId_" + instanceId, "");
            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

            // grab the original file name from the request
            String sourceFileName = uploadRequest.getFileName("orgFile_" + instanceId);
            // get the file that contains the new organization data
            File file = uploadRequest.getFile("orgFile_" + instanceId);
            if(sourceFileName != null && !"".equalsIgnoreCase(sourceFileName) && file != null) {
                // Validate the file extension [.csv, .txt]
                String[] tokens = sourceFileName.split("\\.(?=[^\\.]+$)");
                String fileExtension = tokens[tokens.length-1];
                boolean isValidExtension = false;
                for(String validExtension : HomepageBundleOrgAdminUtil.VALID_FILE_EXTENSIONS) {
                    if(validExtension.equalsIgnoreCase(fileExtension))
                    {
                        isValidExtension = true;
                    }
                }

                if(isValidExtension) {
                    // process the new organization
                    if(this.processNewOrganization(file, sourceFileName)) {
                       /* if successful add the success attribute to the session to be picked up by the view
                        * NOTE: if the process was not successful, we will add the errors to the session within
                        * the atomic methods that process the new organization.
                        */
                        SessionMessages.add(actionRequest, "addOrganzationSuccess", "true");
                    }
                } else {
                    addOrganizationErrors.append("Error: ");
                    addOrganizationErrors.append(fileExtension);
                    addOrganizationErrors.append(" is not a valid file extension.");
                }
            } else {
                addOrganizationErrors.append("Error: Could not read file.");
            }

            // add any errors from the processing of the organization if there are any
            if(!"".equalsIgnoreCase(addOrganizationErrors.toString())) {
                SessionMessages.add(actionRequest, "addOrganzationErrors", addOrganizationErrors.toString());
            }

            // redirect back to the new organization view
            actionResponse.sendRedirect("/portal/web/guest/admin#/new");
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling the action request.",  e);
            try {
                SessionMessages.add(actionRequest, "addOrganzationErrors", "Error: There was a problem processing your request.");
            }catch (Exception ee) {}
        }
    }
}