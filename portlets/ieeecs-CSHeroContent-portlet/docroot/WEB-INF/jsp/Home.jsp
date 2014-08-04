<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<portlet:resourceURL var='csheroContentAjaxHandler' id='${id}csheroContentAjaxHandler' />
<style type="text/css">
    #cs-hero-content-container-${id} { position: relative; }
    .cs-hero-content { padding-top: 2%; }

    div[id^="hero-content-item-${id}"] {
        height: 250px;
        position: relative;
        padding-left: 4px;
        padding-right: 4px;
    }

    div[id^="hero-content-item-${id}"] .hero-content-container:hover {
        cursor: pointer;
    }

    div[id^="hero-content-item-${id}"] .hero-content-container {
        padding: 0px;
        height: 250px;
        overflow: hidden;
        position:relative;
        z-index:2;
    }

    div[id^="hero-content-item-${id}"] .hero-content-container .label-header {
       border-radius: 0;
       text-transform:uppercase;
       position: absolute;
       z-index: 2;
       top: 8px;
       left: 8px;
       min-width: 100px;
    }

    div[id^="hero-content-item-${id}"] .bg-section {
        position: absolute;
        height: inherit;
        width: 100%;
        z-index: 0;
    }

    .bg-section iframe,
    .bg-section object,
    .bg-section embed {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }

    div[id^="hero-content-item-${id}"] .description-container {
        /* IE9 SVG, needs conditional override of 'filter' to 'none' */
        background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzAwMDAwMCIgc3RvcC1vcGFjaXR5PSIwIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjM2JSIgc3RvcC1jb2xvcj0iIzAwMDAwMCIgc3RvcC1vcGFjaXR5PSIwLjQzIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjgzJSIgc3RvcC1jb2xvcj0iIzAwMDAwMCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgPC9saW5lYXJHcmFkaWVudD4KICA8cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMSIgaGVpZ2h0PSIxIiBmaWxsPSJ1cmwoI2dyYWQtdWNnZy1nZW5lcmF0ZWQpIiAvPgo8L3N2Zz4=);
        background: -moz-linear-gradient(top,  rgba(0,0,0,0) 0%, rgba(0,0,0,0.53) 26%, rgba(0,0,0,1) 83%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,0,0,0)), color-stop(26%,rgba(0,0,0,0.53)), color-stop(83%,rgba(0,0,0,1))); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top,  rgba(0,0,0,0) 0%,rgba(0,0,0,0.53) 26%,rgba(0,0,0,1) 83%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top,  rgba(0,0,0,0) 0%,rgba(0,0,0,0.53) 26%,rgba(0,0,0,1) 83%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top,  rgba(0,0,0,0) 0%,rgba(0,0,0,0.53) 26%,rgba(0,0,0,1) 83%); /* IE10+ */
        background: linear-gradient(to bottom,  rgba(0,0,0,0) 0%,rgba(0,0,0,0.53) 26%,rgba(0,0,0,1) 83%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#00000000', endColorstr='#000000',GradientType=0 ); /* IE6-8 */
        min-height: 80px;
        position: absolute;
        bottom: 0;
        color: #ffffff;
        padding-top: 10px;
    }

    div[class$='-bg-media-${id}'] { display: none; }

    <c:if test="${canInlineEdit}">
        #edit-hero-content-${id} {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 99;
        }
        a.inline-edit {
            opacity: 0.6;
            filter: alpha(opacity=60);
            transition: 0.1s linear;
            -moz-transition: 0.1s linear;
            -webkit-transition: 0.1s linear;
            color: #ffffff;
            font-size: 14px;
            line-height: 14px;
        }
        a.inline-edit:hover {
            text-decoration: none;
            opacity: 1.0;
            filter: alpha(opacity=100);
            cursor: pointer;
        }
        #alert-main-success-${id}, #alert-main-danger-${id} {
            display: none;
        }
    </c:if>
</style>

   <!-- Move to theme! -->
    <!--[if gte IE 9]>
      <style type="text/css">
        .gradient {
           filter: none;
        }
      </style>
    <![endif]-->

<div id="cs-hero-content-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-hero-content-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#hero-content-edit-modal-${id}">Edit</a></span>
        <div id="hero-content-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="hero-content-edit-modal-${id}" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Hero Content Administration</h4>
                </div>
                <div class="modal-body">
                   <form id="edit-hero-content-form-${id}" role="form">
                     <div class="form-group row">
                        <div class="col-sm-2">
                             <label for="number-items-select-${id}">Number of Items</label>
                             <select id="number-items-select-${id}" class="form-control">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                             </select>
                         </div>
                     </div>
                     <hr />
                     <!-- Nav tabs -->
                     <ul class="nav nav-pills">
                       <li id="list-item-1-${id}" class="active"><a href="#item-1-${id}" role="tab" data-toggle="tab">Item 1</a></li>
                       <li id="list-item-2-${id}" class="hide"><a href="#item-2-${id}" role="tab" data-toggle="tab">Item 2</a></li>
                       <li id="list-item-3-${id}" class="hide"><a href="#item-3-${id}" role="tab" data-toggle="tab">Item 3</a></li>
                       <li id="list-item-4-${id}" class="hide"><a href="#item-4-${id}" role="tab" data-toggle="tab">Item 4</a></li>
                     </ul> <!-- /.nav-tabs -->

                     <!-- Tab panes -->
                     <div class="tab-content">
                       <div class="tab-pane active" id="item-1-${id}">
                        <div class="form-group">
                         <label for="item-1-header-${id}">Header</label>
                         <input type="text" class="form-control" id="item-1-header-${id}" placeholder="Header" maxlength="17">
                        </div>
                        <div class="form-group">
                         <label for="item-1-description-${id}">Content Description</label>
                         <textarea class="form-control" id="item-1-description-${id}" rows="3" maxlength="140"></textarea>
                         <p class="help-block">140 characters or less.</p>
                        </div>
                        <div class="form-group">
                            <label for="item-1-accent-color-${id}">Accent Color</label>
                            <div class="input-group col-sm-2">
                                <span class="input-group-addon">#</span>
                                <input class="form-control" id="item-1-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                            </div>
                         </div>
                        <div class="form-group">
                         <label for="item-1-dest-url-${id}">Destination URL</label>
                         <input type="text" class="form-control" id="item-1-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                         <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                       </div>

                       <div class="well">
                        <div class="form-group">
                          <label for="item-1-bg-mode-${id}">Background Mode</label>
                          <select id="item-1-bg-mode-${id}" class="form-control">
                             <option value="image">Image</option>
                             <option value="media">Media</option>
                          </select>
                        </div>
                        <div class="form-group item-1-bg-image-${id}">
                         <label for="item-1-bg-image-${id}">Background Image URL</label>
                         <input type="text" class="form-control" id="item-1-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                         <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                        </div>
                        <div class="form-group item-1-bg-media-${id}">
                         <label for="item-1-bg-media-${id}">Background Media</label>
                         <textarea class="form-control" id="item-1-bg-media-${id}" rows="3" maxlength="500"></textarea>
                         <p class="help-block">You can use HTML, Javascript for your audio/videos</p>
                        </div>
                       </div> <!-- /.well -->

                       </div> <!-- /#item-1-${id} -->

                       <div class="tab-pane" id="item-2-${id}">
                           <div class="form-group">
                           <label for="item-2-header-${id}">Header</label>
                           <input type="text" class="form-control" id="item-2-header-${id}" placeholder="Header" maxlength="17">
                          </div>
                          <div class="form-group">
                           <label for="item-2-description-${id}">Content Description</label>
                           <textarea class="form-control" id="item-2-description-${id}" rows="3" maxlength="140"></textarea>
                           <p class="help-block">140 characters or less.</p>
                          </div>
                          <div class="form-group">
                              <label for="item-2-accent-color-${id}">Accent Color</label>
                              <div class="input-group col-sm-2">
                                <span class="input-group-addon">#</span>
                                  <input class="form-control" id="item-2-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                              </div>
                           </div>

                           <div class="form-group">
                             <label for="item-2-dest-url-${id}">Destination URL</label>
                             <input type="text" class="form-control" id="item-2-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                             <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                           </div>

                          <div class="well">
                               <div class="form-group">
                                 <label for="item-2-bg-mode-${id}">Background Mode</label>
                                 <select id="item-2-bg-mode-${id}" class="form-control">
                                    <option value="image">Image</option>
                                    <option value="media">Media</option>
                                 </select>
                               </div>
                                 <div class="form-group item-2-bg-image-${id}">
                                  <label for="item-2-bg-image-${id}">Background Image URL</label>
                                  <input type="text" class="form-control" id="item-2-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                                  <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                                 </div>
                               <div class="form-group item-2-bg-media-${id}">
                                <label for="item-2-bg-media-${id}">Background Media</label>
                                <textarea class="form-control" id="item-2-bg-media-${id}" rows="3" maxlength="500"></textarea>
                                <p class="help-block">You can use HTML, Javascript for your audio/videos</p>
                               </div>
                          </div> <!-- /.well -->

                       </div> <!-- /#item-2-${id} -->

                       <div class="tab-pane" id="item-3-${id}">
                         <div class="form-group">
                         <label for="item-3-header-${id}">Header</label>
                         <input type="text" class="form-control" id="item-3-header-${id}" placeholder="Header" maxlength="17">
                        </div>
                        <div class="form-group">
                         <label for="item-3-description-${id}">Content Description</label>
                         <textarea class="form-control" id="item-3-description-${id}" rows="3" maxlength="140"></textarea>
                         <p class="help-block">140 characters or less.</p>
                        </div>
                        <div class="form-group">
                            <label for="item-3-accent-color-${id}">Accent Color</label>
                            <div class="input-group col-sm-2">
                                <span class="input-group-addon">#</span>
                                <input class="form-control" id="item-3-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                            </div>
                         </div>

                        <div class="form-group">
                            <label for="item-3-dest-url-${id}">Destination URL</label>
                            <input type="text" class="form-control" id="item-3-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                            <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                        </div>
                         <div class="well">
                           <div class="form-group">
                             <label for="item-3-bg-mode-${id}">Background Mode</label>
                             <select id="item-3-bg-mode-${id}" class="form-control">
                                <option value="image">Image</option>
                                <option value="media">Media</option>
                             </select>
                           </div>
                             <div class="form-group item-3-bg-image-${id}">
                              <label for="item-3-bg-image-${id}">Background Image URL</label>
                              <input type="text" class="form-control" id="item-3-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                              <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                             </div>
                           <div class="form-group item-3-bg-media-${id}">
                            <label for="item-3-bg-media-${id}">Background Media</label>
                            <textarea class="form-control" id="item-3-bg-media-${id}" rows="3" maxlength="500"></textarea>
                            <p class="help-block">You can use HTML, Javascript for your audio/videos</p>
                           </div>
                          </div> <!-- /.well -->
                       </div> <!-- /#item-3-${id} -->

                       <div class="tab-pane" id="item-4-${id}">
                        <div class="form-group">
                            <label for="item-4-header-${id}">Header</label>
                            <input type="text" class="form-control" id="item-4-header-${id}" placeholder="Header" maxlength="17">
                           </div>
                           <div class="form-group">
                            <label for="item-4-description-${id}">Content Description</label>
                            <textarea class="form-control" id="item-4-description-${id}" rows="3" maxlength="140"></textarea>
                            <p class="help-block">140 characters or less.</p>
                           </div>
                           <div class="form-group">
                               <label for="item-4-accent-color-${id}">Accent Color</label>
                               <div class="input-group col-sm-2">
                                   <span class="input-group-addon">#</span>
                                   <input class="form-control" id="item-4-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                               </div>
                            </div>

                          <div class="form-group">
                            <label for="item-4-dest-url-${id}">Destination URL</label>
                            <input type="text" class="form-control" id="item-4-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                            <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                          </div>

                           <div class="well">
                             <div class="form-group">
                               <label for="item-4-bg-mode-${id}">Background Mode</label>
                               <select id="item-4-bg-mode-${id}" class="form-control">
                                  <option value="image">Image</option>
                                  <option value="media">Media</option>
                               </select>
                             </div>
                               <div class="form-group item-4-bg-image-${id}">
                                <label for="item-4-bg-image-${id}">Background Image URL</label>
                                <input type="text" class="form-control" id="item-4-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                                <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                               </div>
                                 <div class="form-group item-4-bg-media-${id}">
                                  <label for="item-4-bg-media-${id}">Background Media</label>
                                  <textarea class="form-control" id="item-4-bg-media-${id}" rows="3" maxlength="500"></textarea>
                                  <p class="help-block">You can use HTML, Javascript for your audio/videos</p>
                                 </div>
                            </div> <!-- /.well -->
                       </div> <!-- /#item-4-${id} -->
                     </div>  <!-- /.tab-content -->

                   </form>
                </div> <!-- /.modal-body -->
                 <div class="modal-footer">
                     <!-- Alerts -->
                        <div id="alert-main-success-${id}" class="alert alert-success text-left">
                            <strong>&#10004;</strong> Your portlet was updated.
                        </div>
                      <div id="alert-main-danger-${id}" class="alert alert-danger text-left">
                        <a href="#" class="close">&times;</a>
                        <strong>&#9888;</strong> There seems to be a problem:
                        <span id="error-message-${id}"></span>
                      </div>
                      <!-- End Alerts -->
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" id="btn-save-${id}">Save</button>
                 </div>
            </div>
          </div>
        </div> <!-- /#hero-content-edit-modal-${id} -->

        <script>
            $(document).ready(function() {

                var buildItemsData${id} = function(postData) {
                    postData.item1 = getItemData${id}(1);
                    switch(parseInt(postData.numberOfItems)) {
                        case 2: postData.item2 = getItemData${id}(2);
                        break;
                        case 3:
                            postData.item2 = getItemData${id}(2);
                            postData.item3 = getItemData${id}(3);
                        break;
                        case 4:
                            postData.item2 = getItemData${id}(2);
                            postData.item3 = getItemData${id}(3);
                            postData.item4 = getItemData${id}(4);
                        break;
                    }
                    return postData;
                }

                var getItemData${id} = function(itemNumber) {
                    var item = {};
                    item.header = $('#item-'+itemNumber+'-header-${id}').val().replace(/'/g, "&#39;");
                    item.description = $('#item-'+itemNumber+'-description-${id}').val().replace(/'/g, "&#39;");
                    item.accentColor = $('#item-'+itemNumber+'-accent-color-${id}').val();
                    item.destURL = $('#item-'+itemNumber+'-dest-url-${id}').val();
                    item.bgMode = $('#item-'+itemNumber+'-bg-mode-${id}').val();
                    item.bgImage = encodeURIComponent($('#item-'+itemNumber+'-bg-image-${id}').val());
                    item.bgMedia = encodeURIComponent($('#item-'+itemNumber+'-bg-media-${id}').val().replace(/\n/g, ""));
                    return JSON.stringify(item);
                }

                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.instanceId = '${id}';
                    postData.requestType = 'SAVE_CONFIG';
                    postData.numberOfItems = $('#number-items-select-${id}').val();
                    postData = buildItemsData${id}(postData);

                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${csheroContentAjaxHandler}",
                      beforeSend: function() {
                        $("#btn-save-${id}").prop('disabled', true);
                      },
                      success: function (response) {
                        if(response == 200) {
                            $("#alert-main-danger-${id}").fadeOut(0);
                             $("#alert-main-success-${id}").fadeIn(100);
                             $("#btn-save-${id}").prop('disabled', false);
                             setTimeout(function() {$("#alert-main-success-${id}").fadeOut(300);},3000);
                         } else {
                           console.error(data);
                           $("#alert-main-danger-${id}").fadeIn(100);
                           $("#error-message-${id}").html("There seems to be a problem with your request.  Please contact help@computer.org.");
                           $("#btn-save-${id}").prop('disabled', false);
                         }
                      },
                      error: function (data) {
                        console.error(data.error);
                        $("#alert-main-danger-${id}").fadeIn(100);
                        $("#error-message-${id}").html("There seems to be a problem with your request.  Please contact help@computer.org.");
                        $("#btn-save-${id}").prop('disabled', false);
                      }
                  });
               });
            });
       </script>
    </c:if>

    <div id="cs-hero-content-${id}" class="cs-hero-content row">
    </div> <!-- /#cs-hero-content-${id} -->
</div>

<script>
   var showItemsAsNeeded${id} = function() {
     var count${id} =  parseInt($('#number-items-select-${id}').val());
     switch(count${id}) {
         case 1:
             $('#list-item-2-${id},#list-item-3-${id},#list-item-4-${id}').addClass('hide');
             $('#item-2-${id},#item-3-${id},#item-4-${id}').addClass('hide');
             $('a[href="#item-1-${id}"]').tab('show');
         break;
         case 2:
             $('#list-item-2-${id},#item-2-${id}').removeClass('hide');
             $('#list-item-3-${id},#list-item-4-${id},#item-3-${id},#item-4-${id}').addClass('hide');
             $('a[href="#item-2-${id}"]').tab('show');
         break;
         case 3:
             $('#list-item-2-${id},#item-2-${id},#list-item-3-${id},#item-3-${id}').removeClass('hide');
             $('#list-item-4-${id},#item-4-${id}').addClass('hide');
             $('a[href="#item-3-${id}"]').tab('show');
         break;
         case 4:
             $('#list-item-2-${id},#item-2-${id},#list-item-3-${id},#item-3-${id},#list-item-4-${id},#item-4-${id}').removeClass('hide');
             $('a[href="#item-4-${id}"]').tab('show');
         break;
     }
   }

   var items${id} = [$.parseJSON('${item1}'),$.parseJSON('${item2}'),$.parseJSON('${item3}'),$.parseJSON('${item4}')];

   var setItemsOnForm${id} = function() {
       var numberOfItems =  parseInt($('#number-items-select-${id}').val());
       setItemDataOnForm${id}(1,  items${id}[0]);
       switch(numberOfItems) {
           case 2:
           setItemDataOnForm${id}(2,  items${id}[1]);
           break;
           case 3:
           setItemDataOnForm${id}(2,  items${id}[1]);
           setItemDataOnForm${id}(3,  items${id}[2]);
           break;
           case 4:
           setItemDataOnForm${id}(2,  items${id}[1]);
           setItemDataOnForm${id}(3,  items${id}[2]);
           setItemDataOnForm${id}(4,  items${id}[3]);
           break;
       }
   }

   var setItemDataOnForm${id} = function(itemNumber, item) {
        $('#item-'+itemNumber+'-header-${id}').val(item.header);
        $('#item-'+itemNumber+'-description-${id}').val(item.description);
        $('#item-'+itemNumber+'-accent-color-${id}').val(item.accentColor);
        $('#item-'+itemNumber+'-dest-url-${id}').val(item.destURL);
        $('#item-'+itemNumber+'-bg-mode-${id} option[value="'+item.bgMode+'"]').attr("selected", "selected");
        $('#item-'+itemNumber+'-bg-image-${id}').val(decodeURIComponent(item.bgImage));
        $('#item-'+itemNumber+'-bg-media-${id}').val(decodeURIComponent(item.bgMedia));
        setItemFormForBgMode${id}(item.bgMode, itemNumber);
   }

   var setItemFormForBgMode${id} = function(bgMode, itemNumber) {
        if(bgMode == 'image' || bgMode == undefined) {
            $('.item-'+itemNumber+'-bg-image-${id}').show();
            $('.item-'+itemNumber+'-bg-media-${id}').hide();
        } else {
            $('.item-'+itemNumber+'-bg-image-${id}').hide();
            $('.item-'+itemNumber+'-bg-media-${id}').show();
        }
   }

   $(document).ready(function() {
        var numberOfItems${id} = '${numberOfItems}';
        numberOfItems${id} = numberOfItems${id} == '' ? '1' : numberOfItems${id};
        $('#number-items-select-${id} option[value="'+numberOfItems${id}+'"]').attr("selected", "selected");
        showItemsAsNeeded${id}();
        setItemsOnForm${id}();

        $('#number-items-select-${id}').change(function() {
             showItemsAsNeeded();
        });

        var colCSSClass = "col-sm-12";
        switch(parseInt(numberOfItems${id})) {
            case 2: colCSSClass = "col-sm-6";
            break;
            case 3: colCSSClass = "col-sm-4";
            break;
            case 4: colCSSClass = "col-sm-3";
            break;
        }

        for(var idx=0;idx<numberOfItems${id};idx++) {
            var html = '<div id="hero-content-item-${id}-'+idx+'" class="'+colCSSClass+'">';
            html += '<div class="hero-content-container">';
            if(items${id}[idx].bgMode == 'image') {
                html += '<div class="bg-section" style="background: url('+ decodeURIComponent(items${id}[idx].bgImage)+') 100%; background-size: cover;"></div>';
            } else {
                html += '<div class="bg-section">'+ decodeURIComponent(items${id}[idx].bgMedia) + '</div>';
            }
            html += '<div class="label label-header" style="background-color: #'+ items${id}[idx].accentColor+'">'+ items${id}[idx].header+'</div>';
            html += '<div class="col-sm-12 description-container gradient"><div class="description text-left">';
            html +=  items${id}[idx].description+'</div></div>';
            $('#cs-hero-content-${id}').append(html);
            setItemFormForBgMode${id}(items${id}[idx].bgMode, (idx+1));
        }

        $('div[id^="hero-content-item-${id}"] .hero-content-container').click(function() {
            var tokens = $(this).parent().attr('id').split('-');
            window.location = items${id}[tokens[4]].destURL;
        });

        $('select[id$="bg-mode-${id}"]').change(function() {
            var tokens = $(this).attr('id').split('-');
            setItemFormForBgMode${id}($(this).val(), tokens[1]);
        });
   });
</script>