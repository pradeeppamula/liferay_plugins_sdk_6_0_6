/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will ...
 */
package org.ieeecs.communities.presentation.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.EncryptionMaterials;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.json.XML;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;
import org.ieeecs.communities.amazon.S3Stub_KeyPairConsumption;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.HomepageContentUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageContentController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageContentController.class);
    // TODO: move to CSDL Constants
    public static final String CSDL_ROOT_PATH = "http://www.computer.org/digitallibrary/";
    public static final String CSDL_URL_ENDPOINT_CONTENT_DOI = "content/description/doi/";

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
     * This atomic helper method will retrieve the purchase data based on
     * the user's credentials.
     * TODO: Move this to common CSDL Handler
     *
     * @param userId
     * @return String
     * @throws Exception
     */
    private String getPurchaseData(long userId) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("user_id", userId);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
            // if there is a result, grab the first one
            if(results != null && results.size() > 0) {
                retVal = results.get(0).toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the purchase data: "  + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This function will load up the article data from the REST Endpoint
     * based on the passed in cid
     * @param cid
     * @return String
     */
    private String loadArticleContent(String cid) {
        // build the URL for the article count REST endpoint
        String url = CSDL_ROOT_PATH + CSDL_URL_ENDPOINT_CONTENT_DOI;
        String fullURL = url + cid;
        fullURL = fullURL.replace(" ", "%20");
        return this.retrieveContentData(fullURL);
    }

    /**
     * Atomic helper method that will retrieve the content data from the passed in
     * url and return it in JSON form to the callee.
     *
     * @param url
     * @return   String
     */
    private String retrieveContentData(String url) {
        HttpClient client = new HttpClient();
        String retVal = "";
        // create the GET method for the URL
        GetMethod method = new GetMethod(url);
        try {
            // execute the get method for the content endpoint
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("There was a problem accessing the content with url endpoint: " + url);
            } else {
                // grab the response body
                byte[] responseBody = method.getResponseBody();

                // since the CDSL returns as XML, we need to convert it to json
                org.ieee.common.json.JSONObject responseJSON = XML.toJSONObject(new String(responseBody));
                // build article JSON in atomic method
                retVal = responseJSON.toString();
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred when retrieving the content data: "  + ExceptionUtils.getRootCauseMessage(e));
        } finally {
            method.releaseConnection();
        }
        return retVal;
    }


    /**
     * This function will retrieve the full text content from
     * Amazon S3
     * @param contentPath
     * @return HashMap<String,Object>
     */
    public HashMap<String,Object> retrieveArticleContent(String contentPath) {
        HashMap<String, Object> retVal = new HashMap<String, Object>();
        boolean isPeriodical = false;
        if(contentPath == null || contentPath.equalsIgnoreCase("")) return retVal;
        try {
            // AWS S3 Bucket Name, Public Key, Private Key
            // Public and Private keys should ideally be in a properties file.
            String bucketName = "ieeecs.cdn.test";
            String base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDL+eGns3OujdAI4IiJc9Csj8puiLPc+X7w5veoK1YrplTapJc9lY9/l/HCbyF4iPz5HrKQeOngGQ8v6Dq8ruYLfEkCMNfI7w/zPNAjfqnp4pi4XztXOBqWoTjv6YTzozzD9qqoquphLuhJXo07P5ORNG+0fuGMj5zwr0ppWpovTwIDAQAB";
            String base64PrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMv54aezc66N0AjgiIlz0KyPym6Is9z5fvDm96grViumVNqklz2Vj3+X8cJvIXiI/PkespB46eAZDy/oOryu5gt8SQIw18jvD/M80CN+qenimLhfO1c4GpahOO/phPOjPMP2qqiq6mEu6ElejTs/k5E0b7R+4YyPnPCvSmlami9PAgMBAAECgYEAxMfAG+Jy0s47CaVb84cIpMFfoL0/EAqenVelStbsHdYsfHZW9fGoa4axlEtT9g3qR4eG6vvLXQev8B7RM6Bjiq02AZoy/ocgNpy4FCopClZisAsaRbVkgKRXXJyRJmaJiGq/ulW+sFpwOR2ktrvnaLOxJdFbX6+5vjqYiOA7JlkCQQDz7u3gchtfLDvw3RmpRhuvbyRiupvNO5nIUxHaFdqE+VGZ46tlnd0AtcwZP2hRN9gsIPCYtEFmCfjQoZ6s4LP9AkEA1hDzV4qS6CSr73RdbMINgTYg2l08KWz6netyelI+rq43P9zSRanIBHUdKM6/ZFrF2e03y+sOKdC3Vb4H8P/EOwJBAJn1r8X9vzDuplZ79npUeZzctUZrKXL7rzA03gubx3QuOEa8360fWiHcnJlC4ACDErmngLOg8bYJjTWBDLloibECQQCroUGJoagxih10SocRSPDM9VoX2gL/b8nsTbcmTcTGBxZzFJhelEazFDcB3enkC0dwd9pxm49qhVZI36i8WES7AkAubxmoeI8syomnifIp/oV/K3UaUg4DDj5b9FwaHUsTzFN/EtUI/tkruE9+Nl3CzAalGUEQvSSgYrKCE8KOh1xk";

            // Our AWS S3 file -- its "key" value -- that we want to download.
            String s3FileKey = contentPath;

            // split the content path by "/"
            String[] tokens = contentPath.split("/");

            // determine if the content is a periodical or proceeding
            isPeriodical = (tokens.length > 0 && tokens.length == 5);

            // Since we're dealing in this case with an XML file, and we want the end-result to be HTML, we'll apply this XSLT.
            String xsltPath =  getPortletContext().getRealPath("/WEB-INF/xsls/fulltext_article.xsl");

            // These variables are strictly for the XSLT in this case.
            String pubType  = tokens[0];
            String idPrefix = tokens[1];
            String year     = tokens[2];
            String issueNumber = "", catalogNumber, volumeNumber;
            if(isPeriodical) {
                issueNumber = tokens[3];
            } else {
                catalogNumber = tokens[3];
                volumeNumber = tokens[4];
            }

            // split the filename so we know what the file extension is,
            // which will determine how we deal with the retrieved content
            String[] fileNameTokens = tokens[tokens.length-1].split("\\.");
            String fno = fileNameTokens[0];
            String fileExt = fileNameTokens[1];

            // Create the PublicKey and PrivateKey objects, allowing our KeyPair object to exist.
            PublicKey publicKey = S3Stub_KeyPairConsumption.getPublicKey(base64PublicKey);
            PrivateKey privateKey = S3Stub_KeyPairConsumption.getPrivateKey(base64PrivateKey);
            KeyPair keyPair = new KeyPair(publicKey, privateKey);

            // At this point we're talking AWS SDK, so create one of their "EncryptionMaterials" with our new KeyPair object
            // We'll use the "AmazonS3EncryptionClient" as we'll need to decrypt the file(s) within the AWS S3 bucket.
            EncryptionMaterials encryptionMaterials = new EncryptionMaterials( keyPair );
            AWSCredentialsProvider awsCredentialProvider = new ClasspathPropertiesFileCredentialsProvider();
            AmazonS3EncryptionClient s3 = new AmazonS3EncryptionClient(awsCredentialProvider.getCredentials(), encryptionMaterials);

            // Note that the "s3" object is an instance of "AmazonS3EncryptionClient", so the decryption is done for us automatically.
            S3Object s3FileObject = s3.getObject(new GetObjectRequest(bucketName, s3FileKey));

            // Now it's a matter of applying the XSLT to the stream -- s3FileObject.getObjectContent() -- to get our HTML.
            StreamSource in = new StreamSource(s3FileObject.getObjectContent());
            StreamResult out = new StreamResult(new StringWriter());

            if(isPeriodical) {
                // Pure Java XSLT code.
                // The "setParameters" are necessary only for the "fulltext_article.xsl" file.
                // You will most likely not need them for other XSLTs.
                System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
                transformer.setParameter("PubType", pubType.substring(0,1));
                transformer.setParameter("PubAcro", idPrefix.toLowerCase());
                transformer.setParameter("PubYear", year);
                transformer.setParameter("PubIss", issueNumber);
                transformer.transform(in, out);
                fileExt = "html";
            } else { // massage the proceeding
                // TODO: handle the proceeding!@!!
            }

            StringWriter sw = (StringWriter) out.getWriter();
            StringBuffer sb = sw.getBuffer();
            retVal.put("article", sb.toString());
            retVal.put("fileType", fileExt);
        } catch (AmazonServiceException ase) {
            LOGGER.error("An AmazonServiceException occurred when retrieving the content data, which means your request made it to Amazon S3, but was rejected with an error response for some reason: " + ExceptionUtils.getRootCauseMessage(ase));
        } catch (AmazonClientException ace) {
            LOGGER.error("An AmazonClientException occurred when retrieving the content data, which means the client encountered a serious internal problem while trying to communicate with S3, such as not being able to access the network: "  + ExceptionUtils.getRootCauseMessage(ace));
        } catch (Exception e) {
            LOGGER.error("An exception occurred when retrieving the article content: " + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This method will load the number of articles in the CSDL based on what
     * the user has in their bundle.
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
        String contentType = "text/json";
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
            if (requestType.equalsIgnoreCase(HomepageContentUtil.REQUEST_TYPE_LOAD_ARTICLE_CONTENT)) {
                // grab the article Path  off of the request
                String contentPath = com.liferay.portal.kernel.util.ParamUtil.getString(request, "contentPath_" + instanceId, "");
                HashMap<String,Object> articleContent = this.retrieveArticleContent(contentPath);
                if(articleContent.containsKey("article")) {
                    // now go retrieve actual subscription content metadata
                    model.put("response", articleContent.get("article"));
                    contentType = "text/".concat((String)articleContent.get("fileType"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when handling the resource request: "  + ExceptionUtils.getRootCauseMessage(e));
        }

        // TODO: the response type is determined by the content extension
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }

    /**
     * This method is called before the view of the portlet is rendered.  It will
     * perform any necessary setup processes.
     *
     * @param renderRequest
     * @param renderResponse
     */
    protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();
        String instanceId = "";
        ModelAndView modelAndView = null;
        ThemeDisplay themeDisplay = null;
        PortletPreferences prefs = null;
        boolean isAuthenticated = false;

        try {
            // first grab the theme display for the portlet
            themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            // set if the user is signed in or not
            isAuthenticated = themeDisplay.isSignedIn();
            // grab the preferences from the request
            prefs = renderRequest.getPreferences();
            // add the default preferences to the model used in the view
            HomepageContentUtil.putPortletPreferencesIntoModel(prefs, model);

            // grab the instance id of this portlet
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // -----------------------------------------------------------------------------------------------------------------
            // If the current user has Deactivated this Portlet, we should still let them
            // see what they are doing.  Allow only the user who is controlling this portlet
            // configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
            // until this current "owner" user sets it to "Activated".
            // -----------------------------------------------------------------------------------------------------------------
            String currentUserId = new Long(themeDisplay.getUserId()).toString();
            String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageContentUtil.USERID);
            String currentMode = prefs.getValue("portletMode", HomepageContentUtil.MODE);

            // if the current user is the modifying user, then put the model in "preview" mode
            if (currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) &&
                    currentMode.trim().equalsIgnoreCase(HomepageContentUtil.MODE)) {
                model.put("portletMode", "PREVIEW");
            }

            if(isAuthenticated) {
                // add teh user's purchase data to the request since they are authenticated
                model.put("userPurchaseData", this.getPurchaseData(themeDisplay.getUserId()));
            }

            // get the content type so we can know how the UI will render
            String type = ParamUtil.getStringParameter(renderRequest, "type");
            type = (type != null && !("".equalsIgnoreCase(type))) ? type.toLowerCase() : "generic";
            model.put("type", type);

            // get the unique identifier for the content
            String id = ParamUtil.getStringParameter(renderRequest, "cid");
            model.put("cid", id);

            // add the url for Elastic Search
            String esURL = PropsUtil.get(HomepageContentUtil.PROPERTY_ES_URL);
            model.put("elasticSearchURL", esURL);
        } catch (Exception e) {
            //  gracefully handle exception and put on model
            model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("An error occurred when handling the render request: "  + ExceptionUtils.getRootCauseMessage(e));
        }

        // create the model for the View and add the model attributes to it
        model.put("isAuthenticated", isAuthenticated);
        model.put("id", instanceId);
        modelAndView = new ModelAndView("Home", model);
        return modelAndView;
    }
}