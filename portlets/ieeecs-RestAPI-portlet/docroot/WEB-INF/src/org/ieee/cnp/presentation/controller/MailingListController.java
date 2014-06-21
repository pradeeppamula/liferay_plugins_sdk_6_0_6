package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;

import org.apache.commons.lang.StringUtils;
import org.ieee.cnp.util.JoinEmailAuthentication;
import org.ieee.cnp.util.RestAPIUtil;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;


public class MailingListController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";

	
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {

			String groupUID = ParamUtil.getStringParameter(renderRequest, "groupUID");
			String cmd      = ParamUtil.getStringParameter(renderRequest, "cmd");
			
			if ( null != groupUID && !"".equals(groupUID) ) {
	
				if ( null == RestAPIUtil.mailingListMap || "CLEARCONFIG".equalsIgnoreCase(cmd) ) {						
					readProperties("MailingListConfigs.txt");
				}
										
				// Get the Team mailing list email/password for authentication purposes, per Team
				JSONArray stcMailingListEmailInformation = getMailingListAccountInfo(groupUID);
				
				// Get the list of User Emails, per Team
				Map<String,List<Address>> teamUserEmailsMap = getTeamUserEmails(groupUID);
				
				if ( null == stcMailingListEmailInformation && stcMailingListEmailInformation.length() > 0 ) {
					output = "{'status':'error', 'message':'This community mailing list account information has not been configured in the \"MailingListConfigs.txt\" file yet.'}";
				} else {
					
					if ( null == teamUserEmailsMap ) {
						output = "{'status':'error', 'message':'This community has not been configured in the \"MailingListConfigs.txt\" file yet.'}";
					} else {
						
						JSONArray outputArray = new JSONArray();
	
						for ( int mIndex = 0; mIndex < stcMailingListEmailInformation.length(); mIndex++ ) {

							JSONObject currentJSONTeam = stcMailingListEmailInformation.getJSONObject(mIndex);
							String teamName = currentJSONTeam.getString("name");
							String teamEmail = currentJSONTeam.getString("email");
							String teamPassword = currentJSONTeam.getString("password");
							List<Address> teamUserEmailsList = teamUserEmailsMap.get(teamName);
							Address[] teamUserEmails = teamUserEmailsList.toArray(new Address[teamUserEmailsList.size()]);
							
							JSONObject listResultsObject = new JSONObject();
							JSONArray results = forwardEmail(groupUID, teamEmail, teamPassword, teamUserEmails);
							listResultsObject.put(teamName, results);
							outputArray.put(listResultsObject);

						} // for ( int mIndex = 0; mIndex < stcMailingList.length(); mIndex++ ) {
						
						output = outputArray.toString();
						
					} // if ( null == teamEmailMap ) {
				} // if ( null == stcMailingList && stcMailingList.length() > 0 ) {
			} // if ( null != groupUID && !"".equals(groupUID) ) {
		
		} catch (Exception e) {
			output = "{'status':'error', 'message':'There is a general error with the ajax call for the content.  Please look at the log files.'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}
	
	private static void readProperties(String propertyFile) {

		try {

			DynamicQuery contentItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
																			.add(PropertyFactoryUtil.forName("title").eq(propertyFile));

			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(contentItemQuery);

			if ( null != fileEntryList && fileEntryList.size() > 0 ) {
				
				RestAPIUtil.mailingListMap = new HashMap<String,String>();

				DLFileEntry fe = fileEntryList.get(0);

				InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null) {	

					if ( !StringUtils.startsWith(line, "#") && !"".equals(line.trim()) ) {	
						String[] keyValuePair = line.split("=",-1);
						String key = keyValuePair[0];
						String value = keyValuePair[1];
						RestAPIUtil.mailingListMap.put(key, value);
					}
				}	
				is.close();				
			}		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getComputerName() {
		
		String computerName = "";
		
		try {			
			computerName = PortalUtil.getPortal().getComputerName().toUpperCase();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return computerName;
	}
	
	private static long getGroupId(String groupUID) {
		
		long groupId = 0;
				
		try {
			
			String computerName = getComputerName();
			String groupIdFromUID = RestAPIUtil.mailingListMap.get(groupUID + ". " + computerName + ".groupId");

			if ( null != groupIdFromUID && !"".equals(groupIdFromUID) ) {
				groupId = new Long(groupIdFromUID);
			} else {
				groupIdFromUID = RestAPIUtil.mailingListMap.get(groupUID);
				if ( null != groupIdFromUID && !"".equals(groupIdFromUID) ) {
					groupId = new Long(groupIdFromUID);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groupId;
	}
	
	private static JSONArray getMailingListAccountInfo(String groupUID) {
		
		JSONArray accountInfo = null;
		
		try {
			
			String jsonInfo = RestAPIUtil.mailingListMap.get(groupUID + ".teams.email");
			
			if ( null != jsonInfo && !"".equals(jsonInfo) ) {
				accountInfo = new JSONArray(jsonInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accountInfo;
	}
	
	private static Map<String,List<Address>> getTeamUserEmails(String groupUID) {
		
		Map<String,List<Address>> teamUserEmailMap = null;
		
		try {
			
			long groupId = getGroupId(groupUID);
			if ( groupId > 0 ) {
				
				List<User> groupUserList = UserLocalServiceUtil.getGroupUsers(groupId);
				
				if ( null != groupUserList && groupUserList.size() > 0 ) {
					
					teamUserEmailMap = new HashMap<String,List<Address>>();
					
					for ( int currentUserIndex = 0; currentUserIndex < groupUserList.size(); currentUserIndex++ ) {
						
						User currentUser = groupUserList.get(currentUserIndex);
						String currentUserEmail = currentUser.getEmailAddress();
						List<Team> currentTeamList = TeamLocalServiceUtil.getUserTeams(currentUser.getUserId());
						
						if ( null != currentTeamList && currentTeamList.size() > 0 ) {
							
							for ( int currentTeamIndex = 0; currentTeamIndex < currentTeamList.size(); currentTeamIndex++ ) {
								
								Team currentTeam = currentTeamList.get(currentTeamIndex);
								String currentTeamName = currentTeam.getName();
								
								List<Address> teamAddressList = teamUserEmailMap.get(currentTeamName);
								
								if ( null == teamAddressList ) {
									teamAddressList = new ArrayList<Address>();
								}
								Address newAddress = new InternetAddress(currentUserEmail);
								teamAddressList.add(newAddress);	
								teamUserEmailMap.put(currentTeamName, teamAddressList);
								
							} // for ( int currentTeamIndex = 0; currentTeamIndex < currentTeamList.size(); currentTeamList++ ) {	
						} // if ( null != currentTeamList && currentTeamList.size() > 0 ) {
					} // for ( int currentUserIndex = 0; currentUserIndex < groupUserList.size(); currentUserIndex++ ) {
				} // if ( null != groupUserList && groupUserList.size() > 0 ) {
			} // if ( groupId > 0 ) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teamUserEmailMap;
	}

	private static JSONArray forwardEmail(String groupUID, String userName, String password, Address[] toAddresses) {

		// Array used for the output message
		JSONArray outputArray = new JSONArray();
		
		try {

			// Get the email server values for this transaction
			String protocol = RestAPIUtil.mailingListMap.get("server.protocol");
			String storeName = RestAPIUtil.mailingListMap.get("server.store.name");
			String imapHost = RestAPIUtil.mailingListMap.get("server.imap.host");
			String mailbox = RestAPIUtil.mailingListMap.get(groupUID + ".mailbox");
			
			// Set the properties
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", protocol); 

			// Create a session and connect to the server using the email/password passed into the method
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore(storeName);
			store.connect(imapHost, userName, password);

			// Open the mailbox and start reading. 
			// Mark the emails as "seen", (if they were "unseen"), after processing/forwarding.
			Folder inbox = store.getFolder(mailbox);			
			inbox.open(Folder.READ_WRITE);
			
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message[] messages = inbox.search(unseenFlagTerm);			

			if ( null != messages && messages.length > 0 ) {				
				for ( int index = 0; index < messages.length; index++ ) {
					JSONObject currentJSONObject = sendEmail(userName, password, imapHost, messages[index], false, "false", toAddresses);
					messages[index].setFlag(Flags.Flag.SEEN, true);
					outputArray.put(currentJSONObject);
				}
			} else {
				JSONObject currentJSONObject = new JSONObject();
				currentJSONObject.put("status","No New Messages");
				currentJSONObject.put("from",userName);
				outputArray.put(currentJSONObject);
			}
			
		} catch (Exception e) {			
			try {			
				JSONObject currentJSONObject = new JSONObject();
				currentJSONObject.put("status","error");
				currentJSONObject.put("message",e.getMessage());
				currentJSONObject.put("from",userName);
				outputArray.put(currentJSONObject);
			} catch (Exception exc) {
				exc.printStackTrace();
			}			
			e.printStackTrace();
		}
		
		return outputArray;
	}
	
	private static JSONObject sendEmail(String from, String password, String host, Message originalMessage, boolean debugMailSession, String debugMode, Address[] toAddresses) {

		JSONObject statusObject = new JSONObject();		
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.debug", debugMode);		
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", password);		

		JoinEmailAuthentication jea = new JoinEmailAuthentication(from, password);

		Session session = Session.getInstance(properties, jea);
		session.setDebug(debugMailSession);

		try {
						
			String emailSubject = originalMessage.getSubject();
			statusObject.put("status","Not Forwarded");
			statusObject.put("subject",emailSubject);
			statusObject.put("from",from);
	
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, toAddresses);
			message.setSubject( emailSubject );

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(originalMessage.getDataHandler());

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			statusObject.put("status","Forwarded Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return statusObject;
	}
	
}