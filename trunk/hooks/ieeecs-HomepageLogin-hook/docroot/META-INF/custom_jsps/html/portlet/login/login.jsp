<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 * NOTE: We are overriding this file to fit our custom needs.  The original
 * Liferay login page was terrible anyways.
 *
 */
--%>

<%@ include file="/html/portlet/login/init.jsp" %>
<style type="text/css">
	.login-form { color: #ffffff;}
</style>
<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon()) {
			signedInAs = "<a href=\"" + HtmlUtil.escape(themeDisplay.getURLMyAccount().toString()) + "\">" + signedInAs + "</a>";
		}
		%>

		<%= LanguageUtil.format(pageContext, "you-are-signed-in-as-x", signedInAs) %>
	</c:when>
	<c:otherwise>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		String login = LoginUtil.getLogin(request, "login", company);
		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}

		%>
		<portlet:actionURL secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
			<portlet:param name="saveLastPath" value="0" />
			<portlet:param name="struts_action" value="/login/login" />
		</portlet:actionURL>

		<liferay-ui:error exception="<%= AuthException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed" />
            	</h4>
        	</liferay-ui:error>	
	
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed-please-enable-browser-cookies" />
            	</h4>
        	</liferay-ui:error>	
		
        	<liferay-ui:error exception="<%= NoSuchUserException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed-please-enable-browser-cookies" />
            	</h4>
        	</liferay-ui:error>	

			<liferay-ui:error exception="<%= PasswordExpiredException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="your-password-has-expired" />
            	</h4>
        	</liferay-ui:error>	

			<liferay-ui:error exception="<%= UserEmailAddressException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed" />
            	</h4>
        	</liferay-ui:error>	
   
			<liferay-ui:error exception="<%= UserLockoutException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="this-account-has-been-locked" />
            	</h4>
        	</liferay-ui:error>	

			<liferay-ui:error exception="<%= UserPasswordException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed" />
            	</h4>
        	</liferay-ui:error>	

			<liferay-ui:error exception="<%= UserScreenNameException.class %>">
                <h4>
                	<i class="icon-exclamation-sign icon-2x icon-fixed-width"></i>
                	 <liferay-ui:message key="authentication-failed" />
            	</h4>
        	</liferay-ui:error>	
         <div class="col-md-6 col-sm-6 login-form">   
			<aui:form action="<%= loginURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<c:if test='<%= SessionMessages.contains(request, "user_added") %>'>

				<%
				String userEmailAddress = (String)SessionMessages.get(request, "user_added");
				String userPassword = (String)SessionMessages.get(request, "user_added_password");
				%>

				<div class="portlet-msg-success">
					<c:choose>
						<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
							<%= LanguageUtil.get(pageContext, "thank-you-for-creating-an-account") %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "thank-you-for-creating-an-account.-your-password-is-x", userPassword, false) %>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
						<%= LanguageUtil.format(pageContext, "your-password-has-been-sent-to-x", userEmailAddress) %>
					</c:if>
				</div>
			</c:if>
			
			<!--<aui:fieldset>-->

				<%
				String loginLabel = null;

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					loginLabel = "Username";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					loginLabel = "Screen Name";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					loginLabel = "Id";
				}
				%>


			  <!--	<span>Login is::
				<aui:input label="<%= loginLabel %>" name="login" type="text" value="<%= login %>" />
			</span>-->
	 		<div class="form-group">
			    <label for="_58_login" class="control-label"><%= loginLabel %></label>
			    <input type="text" class="form-control input-lg " name="_58_login" id="_58_login" placeholder="Username" value="<%= login %>">
		    </div>

		 	<div class="form-group">
			    <label for="inputPassword" class="control-label">Password</label>
			    <input type="password" class="form-control input-lg" name="_58_password" id="_58_password" placeholder="Password" value="<%= password %>">
		  	</div>

				<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

				<!--<c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
					<aui:input checked="<%= rememberMe %>" inlineLabel="left" name="rememberMe" type="checkbox" />
				</c:if>-->
			<!--</aui:fieldset>-->

			<div class="col-md-5 col-sm-5">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</div>
		</aui:form>
	</div>
		<!-- <liferay-util:include page="/html/portlet/login/navigation.jsp" /> -->

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />login);
			</aui:script>
		</c:if>

		<aui:script use="aui-base">
			var password = A.one('#<portlet:namespace />password');

			if (password) {
				password.on(
					'keypress',
					function(event) {
						Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
					}
				);
			}
		</aui:script>
	</c:otherwise>
</c:choose>
<script>
	<!-- Check to see if bootstrap is available, else use default styling -->
	if (jQuery("link[href='/ieeecs-HomePage-theme/css/bootstrap.min.css']").length) {
	    jQuery('.portlet-topper').hide();
	    jQuery('.portlet-msg-error').addClass('alert alert-block alert-danger fade in');
	    jQuery('.alert').removeClass('portlet-msg-error');
	} else {
		jQuery('.login-form ').css('color', '#000');
	}
</script>