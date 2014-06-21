package org.ieee.cnp.presentation.controller;

import java.io.ByteArrayOutputStream;
import java.net.*;
import java.util.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

import org.ieee.common.constants.GlobalConstants;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;

import org.ieee.common.presentation.controller.BaseController;

import org.ieee.common.util.DateUtils;
import org.ieee.common.util.ParamUtil;


public class CommentController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";
	private static final String INSERT      = "insert";
	private static final String QUERY       = "query";
	
	private static String api = "";
	
	
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";
		api = "https://api.mongolab.com/api/1/databases/computingnow/collections/comments?apiKey=50760f52e4b088be4c29f312";

		try {
			
			String process = ParamUtil.getStringParameter(renderRequest, "process");
			
			if ( QUERY.equalsIgnoreCase(process) ) {
				
				HttpClient client = new HttpClient();
				
				String commentParentId = ParamUtil.getStringParameter(renderRequest, "commentParentId");
				String commentParentType = ParamUtil.getStringParameter(renderRequest, "commentParentType");
				String queryUrl = "&q=" + URLEncoder.encode("{'commentParentId':'" + commentParentId + "','commentParentType':'" + commentParentType + "'}");
				String finalUrl = api + queryUrl;
	
				GetMethod get = new GetMethod(finalUrl);
				client.executeMethod(get);
				output = getResponseFromGet(get);

			} else if ( INSERT.equalsIgnoreCase(process) ) {
				
				Map<String,Object> smap = getSessionMap(renderRequest);	

				if ( null != smap ) {

					boolean signedInLiferay = (Boolean) smap.get(GlobalConstants.SIGNEDINLIFERAY);
					boolean signedInSocial  = (Boolean) smap.get(GlobalConstants.SIGNEDINSOCIAL);

					if ( !signedInLiferay && !signedInSocial ) {
						output = "{'status':'error', 'message':'Session not found or you are not signed in.'}";	
					} else {

						// Retrieve all values we'll need for the MongoDB JSON payload
						String newComment        = ParamUtil.getStringParameter(renderRequest, "comment");
						String commentParentId   = ParamUtil.getStringParameter(renderRequest, "commentParentId");
						String commentParentType = ParamUtil.getStringParameter(renderRequest, "commentParentType");						
						String emailAddress      = (String) smap.get(GlobalConstants.EMAILADDRESS);
						String socialSource      = (String) smap.get(GlobalConstants.AUTHFROM);
						
						String fullName = ParamUtil.getStringParameter(renderRequest, "fullName");						
						if ( null == fullName || "".equals(fullName.trim()) ) {
							fullName = (String) smap.get(GlobalConstants.FULLNAME);	
						}						
						
						if ( null != newComment ) {
							newComment = URLDecoder.decode(newComment,"UTF-8");
						}

						// Start to create the MongoDB JSON payload
						// and then get the "id" value for this content "parent"
						JSONObject jsonCommentObject = new JSONObject();
						jsonCommentObject.put("fullName", fullName);
						jsonCommentObject.put("emailAddress", emailAddress);
						jsonCommentObject.put("socialSource", socialSource);						
						jsonCommentObject.put("comment", newComment);
						jsonCommentObject.put("createDate", DateUtils.dateToString( new Date(), "EEEE, MMM d, yyyy HH:mm a"));
			
						// Do comments already exist for this content entry?
						// If the "id" returns as an empty string, then none yet. INSERT
						// If the "id" returns a value, then there are existing comments. UPDATE
						String id = getId(commentParentId, commentParentType);
						String url = api;						
						
						if ( "".equals(id.trim()) ) {							
							output = insertComment(commentParentId, commentParentType, url, jsonCommentObject);							
						} else {							
							String idQuery = URLEncoder.encode("{'_id':{'$oid':'" + id + "'}}");
							url = url + "&u=true&q=" + idQuery;							
							output = updateComment(commentParentId, commentParentType, url, jsonCommentObject);					
						}			
						
					} // if ( !signedInLiferay && !signedInSocial ) {
				} else {
					output = "{'status':'error', 'message':'Session timed-out. Please copy your comment, refresh the page, and login again.'}";					
				} // if ( null != smap ) {
				
			} // if ( QUERY.equalsIgnoreCase(process) ) {
	
		} catch (Exception e) {
			output = "{'status':'error', 'message':'There has been an error in saving the comment.'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}

	private static String insertComment(String commentParentId, String commentParentType, String url, JSONObject jsonCommentObject) {
		
		String output = "";
		HttpClient client = new HttpClient();
		
		try {

			// Finish payload
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("commentParentId", commentParentId);
			jsonObject.put("commentParentType", commentParentType);
			JSONArray commentArray = new JSONArray("[]");
			commentArray.put(jsonCommentObject);
			jsonObject.put("comments", commentArray);
			
			// Hit the comment server
			PostMethod post = new PostMethod(url);
			post.setRequestBody(jsonObject.toString());
	        post.setRequestHeader("Content-type", "application/json;charset=utf-8");
			client.executeMethod(post);
			
			// Verfiy the response
			JSONObject response = new JSONObject(getResponseFromPost(post));
			
			if ( null != response.get("_id") ) {
				jsonCommentObject.put("status", "success");
				jsonCommentObject.put("message", "");
			} else {
				jsonCommentObject.put("status", "error");
				jsonCommentObject.put("message", "There was an error in saving your comment.");
			}
			
			output = jsonCommentObject.toString();
			
		} catch (Exception e) {
			output = "{'status':'error', 'message':'There has been an error in saving the comment.'}";
			e.printStackTrace();
		}
		
		return output;		
	}
	
	private static String updateComment(String commentParentId, String commentParentType, String url, JSONObject jsonCommentObject) {
		
		String output = "";
		HttpClient client = new HttpClient();
		
		try {
			
			// Finish payload
			JSONObject newCommentJSON = new JSONObject();							
			newCommentJSON.put("comments", jsonCommentObject);							
			JSONObject pushNewCommentJSON = new JSONObject();
			pushNewCommentJSON.put("$push", newCommentJSON);	
			
			// Hit the comment server
			PutMethod put = new PutMethod(url);
			put.setRequestBody(pushNewCommentJSON.toString());
			put.setRequestHeader("Content-type", "application/json;charset=utf-8");
			client.executeMethod(put);
			
			// Verfiy the response
			JSONObject response = new JSONObject(getResponseFromPut(put));
			
			if ( null != response.get("error") ) {
				if ( response.get("error").toString().equalsIgnoreCase("null") ) {
					jsonCommentObject.put("status", "success");
					jsonCommentObject.put("message", "");
				} else {
					jsonCommentObject.put("status", "error");
					jsonCommentObject.put("message", "There was an error in adding your comment.");
				}
			} else {
				jsonCommentObject.put("status", "error");
				jsonCommentObject.put("message", "There was an error in adding your comment.");
			}
		
			output = jsonCommentObject.toString();	
			
		} catch (Exception e) {
			output = "{'status':'error', 'message':'There has been an error in saving the comment.'}";
			e.printStackTrace();
		}
		
		return output;
	}
	
	private static String getResponseFromPost(PostMethod post) {
		
		String response = "";
		
		try {
			
			byte[] byteArray = new byte[1024];				        
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int count = 0 ;						
			while((count = post.getResponseBodyAsStream().read(byteArray, 0, byteArray.length)) > 0) {					
				outputStream.write(byteArray, 0, count) ;					
			}						
			response = new String(outputStream.toByteArray(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return response;
	}
	
	private static String getResponseFromGet(GetMethod get) {
		
		String response = "";
		
		try {
			
			byte[] byteArray = new byte[1024];				        
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int count = 0 ;						
			while((count = get.getResponseBodyAsStream().read(byteArray, 0, byteArray.length)) > 0) {					
				outputStream.write(byteArray, 0, count) ;					
			}						
			response = new String(outputStream.toByteArray(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return response;
	}
	
	private static String getResponseFromPut(PutMethod put) {
		
		String response = "";
		
		try {
			
			byte[] byteArray = new byte[1024];				        
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int count = 0 ;						
			while((count = put.getResponseBodyAsStream().read(byteArray, 0, byteArray.length)) > 0) {					
				outputStream.write(byteArray, 0, count) ;					
			}						
			response = new String(outputStream.toByteArray(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return response;
	}
	
	private static String getId(String commentParentId, String commentParentType) {
		
		String oid = "";
		
		try {
			
			HttpClient client = new HttpClient();
			
			String queryString = "&q=" + URLEncoder.encode("{'commentParentId':'" + commentParentId + "','commentParentType':'" + commentParentType + "'}");
			String finalUrl = api + queryString;
	
			GetMethod get = new GetMethod(finalUrl);
			client.executeMethod(get);
			JSONArray jsonResponseArray = new JSONArray( getResponseFromGet(get) );
			
			if ( null != jsonResponseArray && jsonResponseArray.length() > 0 ) {
				
				JSONObject jsonResponseObject = jsonResponseArray.getJSONObject(0);
				
				if ( jsonResponseObject.has("_id") ) {
					JSONObject jsonResponseIdObject = jsonResponseObject.getJSONObject("_id");					
					if ( jsonResponseIdObject.has("$oid") ) {						
						oid = (String) jsonResponseIdObject.get("$oid");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return oid;
	}
}