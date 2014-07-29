<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:resourceURL var='configureSearchViewAjaxHandler' id='configureSearchViewAjaxHandler' />

<portlet:actionURL var="configureAction" portletMode="edit">
	<portlet:param name="action" value="cssearchViewConfigure"/>
</portlet:actionURL>

<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<style type="text/css"></style>

<div class="container">
    <h4>Search View Portlet Configuration</h2>
    <h6>Instance ID: <em>${id}</em></h4>

    <div class="jumbotron">
      <h1>No Configuration Needed</h1>
      <p><a href="#" class="btn btn-default btn-cancel-${id} pull-right">Back</a></p>
    </div>
</div>

<script>
    $(document).ready(function() {
        $(".btn-cancel-${id}").click(function() {
            // go back to previous page
            window.location = "${viewAction}";
        });
    });
</script>