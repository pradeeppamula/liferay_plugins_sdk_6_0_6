/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created October 1, 2013
 * @description This class will function as the action handler for the Purchase portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.HomepagePurchaseUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.*;
import java.io.FileNotFoundException;
import java.util.*;


public class HomepagePurchaseController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepagePurchaseController.class);

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
     * Atomic helper method that will read in the a file from the
     * giving full path
     * @param path
     * @return String
     */
    private String readFile(String path) {
        String retVal = "";
        try {
            FileSystemResource resource = new FileSystemResource(path);
            // read in the entire file
            retVal = new Scanner(resource.getFile()).useDelimiter("\\Z").next();
        } catch(FileNotFoundException fe) {
            LOGGER.error("There was a problem reading in the file with path ( " + path + "):"  + ExceptionUtils.getRootCauseMessage(fe));
        }
        return retVal;
    }

    /**
     * Atomic helper method that will read in the email template for
     * the purchasing of a bundle.  It will then replace the tokens
     * in the email with the user data from the request.
     *
     * @param request
     * @param instanceId
     * @return  String
     */
    private String buildPurchaseEmail(ResourceRequest request, String instanceId) {
        String retVal = null;
        try {
            String emailFullFilePath = getPortletContext().getRealPath(HomepagePurchaseUtil.EMAIL_FILE_PATH);
            // first read in the email template
            String html = this.readFile(emailFullFilePath + "/" + HomepagePurchaseUtil.EMAIL_FILE_NAME);

            // grab the data JSON off of the request
            String name = com.liferay.portal.kernel.util.ParamUtil.getString(request, "name_" + instanceId, "");
            String email = com.liferay.portal.kernel.util.ParamUtil.getString(request, "email_" + instanceId, "");
            String phone = com.liferay.portal.kernel.util.ParamUtil.getString(request, "phone_" + instanceId, "");
            String company = com.liferay.portal.kernel.util.ParamUtil.getString(request, "company_" + instanceId, "");
            String title = com.liferay.portal.kernel.util.ParamUtil.getString(request, "title_" + instanceId, "");
            String bundleType = com.liferay.portal.kernel.util.ParamUtil.getString(request, "bundleType_" + instanceId, "");

            // if the template was read in ok, replace the tokens with the data from the user
            if(html != null && !"".equalsIgnoreCase(html)) {
                html = html.replace(HomepagePurchaseUtil.EMAIL_NAME_TOKEN, name);
                html = html.replace(HomepagePurchaseUtil.EMAIL_TITLE_TOKEN, title);
                html = html.replace(HomepagePurchaseUtil.EMAIL_PHONE_TOKEN, phone);
                html = html.replace(HomepagePurchaseUtil.EMAIL_COMPANY_TOKEN, company);
                html = html.replace(HomepagePurchaseUtil.EMAIL_BUNDLE_TYPE_TOKEN, bundleType);
                html = html.replace(HomepagePurchaseUtil.EMAIL_EMAIL_TOKEN, email);
                retVal = html;
            } else {
                // if the email template was not read in ok, create a basic email with the user's data
                StringBuilder body = new StringBuilder();
                body.append("<html><body><h2>Below are the details of the bundle request:</h2><table><tbody><tr><td width=\"100\">Type:</td><td>");
                body.append(bundleType);
                body.append("</td><tr/><tr><td width=\"100\">Name:</td><td>");
                body.append(name);
                body.append("</td><tr/><tr><td width=\"100\">Email:</td><td>");
                body.append(email);

                body.append("</td><tr/><tr><td width=\"100\">Company:</td><td>");
                body.append(company);
                body.append("</td><tr/><tr><td width=\"100\">Phone:</td><td>");
                body.append(phone);
                body.append("</td><tr/><tr>");

                // only append the title if there is one
                if(title != null && !"".equalsIgnoreCase(title)) {
                    body.append("<td width=\"100\">Title:</td><td>");
                    body.append(title);
                    body.append("</td><tr/>");
                }

                body.append("/tbody></body></html>");
                retVal = body.toString();
            }
        } catch (Exception e) {
            LOGGER.error("There was a problem building the purchase email: " + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This method will construct and send the email notification to
     * sales of the user's bundle purchase request.
     *
     * @param request
     * @param instanceId
     * @return boolean
     */
    private boolean sendEmailNotification(ResourceRequest request, String instanceId) {
        boolean retVal = false;

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", HomepagePurchaseUtil.EMAIL_HOST);
        properties.put("mail.debug", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", HomepagePurchaseUtil.EMAIL_USER);
        properties.put("mail.smtp.password", HomepagePurchaseUtil.EMAIL_PASSWORD);

        // create the auth object for the email session
        SmtpAuthenticator authentication = new SmtpAuthenticator(HomepagePurchaseUtil.EMAIL_USER,HomepagePurchaseUtil.EMAIL_PASSWORD);

        // Get the default Session object.
        Session session = Session.getInstance(properties, authentication);

        // first grab the theme display for the portlet
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            Message message = new MimeMessage(session);
            // first build the email body
            String body = this.buildPurchaseEmail(request, instanceId);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(HomepagePurchaseUtil.EMAIL_FROM, HomepagePurchaseUtil.EMAIL_FROM_NAME));

            // Set Subject: header field
            message.setSubject("Bundle Request");

            // Set To: the recipient
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(HomepagePurchaseUtil.EMAIL_TO, false));

            // if the user is authenticated, grab their organization's POC Email to add to the CC line
            if (themeDisplay.isSignedIn()) {
               message = this.addCCOrganizationPOCEmail(themeDisplay, message);
            }

            // Set Body: the html/text
            message.setContent(body, "text/html; charset=utf-8");
            message.setSentDate(new Date());
            // send the email now
            Transport.send(message);
            retVal = true;
//        } catch(MessagingException me){
//            LOGGER.error("There was a problem sending an email notification", me);
        } catch(Exception e) {
        	e.printStackTrace();
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
            String requestType = com.liferay.portal.kernel.util.ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (requestType.equalsIgnoreCase(HomepagePurchaseUtil.REQUEST_TYPE_PURCHASE_BUNDLE)) {
                //  email Sales
                if(this.sendEmailNotification(request, instanceId)) {
                    // based in email success, return a result back to the client
                    model.put("response", 200);
                } else {
                    model.put("response", 422);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when handling the resource request: "  + ExceptionUtils.getRootCauseMessage(e));
            e.printStackTrace();
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
        String bundleType = "";
		
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
			HomepagePurchaseUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepagePurchaseUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepagePurchaseUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepagePurchaseUtil.MODE) ) {
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

            // get the bundle type so we can know how the UI will render
            String type = ParamUtil.getStringParameter(renderRequest, "t");
            type = (type != null && !("".equalsIgnoreCase(type))) ? type.toLowerCase() : null;
            if("a".equalsIgnoreCase(type)) {
                type = "article";
            }else if ("w".equalsIgnoreCase(type)) {
                type = "webinar";
            }
            model.put("type", type);
		} catch (Exception e) {
			model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when rendering the portlet: "  + ExceptionUtils.getRootCauseMessage(e));
		}

		// create the model for the View and add the model attributes to it
		model.put("id", instanceId);
		model.put("isAuthenticated", isAuthenticated);
        model.put("canInlineEdit", canInlineEdit);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
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
            LOGGER.error("A problem occurred when retrieving the organizations: "   + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * Atomic helper method that will add the user's organization's POC Email to the
     * CC list of the bundle request email.
     * @param themeDisplay
     * @param message
     * @return Message message
     * @throws Exception
     */
    private Message addCCOrganizationPOCEmail(ThemeDisplay themeDisplay, Message message) throws Exception {
        // retrieve the user's purchase data so get can get their org id
        String json = this.getPurchaseData(themeDisplay.getUserId(), themeDisplay.getUser().getEmailAddress());

        // convert the json string to a MongoDBObject
        BasicDBObject userPurchaseData = MongoHandler.getInstance().jsonToDBObject(json);

        // retrieve the organization of the user
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(userPurchaseData.get("organization_id").toString()));
        String organization = this.getOrganizations(query);

        // convert the json to a DBobject so we can get the id
        BasicDBList orgData = MongoHandler.getInstance().jsonToDBList(organization);
        BasicDBObject email = (BasicDBObject)orgData.get(0);
        if(email != null && email.get("pocEmail") != null) {
            // Set CC: the user organization's POC
            message.setRecipient(Message.RecipientType.CC, new InternetAddress((String)email.get("pocEmail"), false));
        }
        return message;
    }

    /**
     * This atomic helper method will retrieve the purchase data based on
     * the user's credentials.
     * TODO: Move this to common CSDL Handler
     *
     * @param userId
     * @param email
     * @return String
     * @throws Exception
     */
    private String getPurchaseData(long userId, String email) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("user_id", userId);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);

            /**
             * If the user does not have any bundle purchase information based on their id, this
             * means that they are a new user.  Since a new organization's users are added to
             * the system by the unique email addresses, we need to search against their Liferay
             * email for their purchase data.
             *
             * If the user is then found, we will then update their purchase data with their userid
             * to be used in the future.
             *
             * If the user is NOT found.  At this point they will NOT have any purchase data.  Is
             * this what we want?
             */
            // if there is a result, grab the first one
            if(results != null && results.size() > 0) {
                retVal = results.get(0).toString();
            } else {
                // now grab the purchase data by the email address
                query = new BasicDBObject("email", email);

                // execute the query against the collection
                results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
                if(results != null && results.size() > 0) {

                    // put the user's user id on the purchase object for future logins
                    DBObject purchaseData = results.get(0);
                    purchaseData.put("user_id", userId);
                    // update the purchase data in mongo
                    this.updatePurchaseData(purchaseData.toString(), userId, email);
                    retVal = purchaseData.toString();
                }
            }
        } catch (org.ieeecs.communities.mongo.MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the purchase data: "   + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This method will update the user's purchase data in the datasource based
     * on the passed in user id
     *
     * TODO: Move this to common CSDL Handler
     *
     * @param purchaseJSON
     * @param userId
     * @param email
     * @return  boolean
     * @throws Exception
     */
    private boolean updatePurchaseData(String purchaseJSON, long userId, String email) throws Exception {
        boolean retVal = false;
        try {
            // build up the query document
            BasicDBObject query = null;
            if(email != null) {
                query = new BasicDBObject("email", email);
            } else {
                query = new BasicDBObject("user_id", userId);
            }
            // convert the json to a DBobject so we can save in mongo
            BasicDBObject purchaseData = MongoHandler.getInstance().jsonToDBObject(purchaseJSON);

            // execute the query against the collection
            MongoHandler.getInstance().update(MongoConfigUtil.Collection.PURCHASE, query, purchaseData);
            retVal = true;
        } catch (org.ieeecs.communities.mongo.MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when updating the purchase data for user with id  " + userId + ": "  + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }
}