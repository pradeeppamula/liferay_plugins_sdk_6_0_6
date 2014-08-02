<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>
<portlet:resourceURL var='csfeaturedSubContentAjaxHandler' id='csfeaturedSubContentAjaxHandler' />
<style type="text/css">
    #cs-featured-subcontent-container-${id} { position: relative; }
    .cs-featured-subcontent { padding-top: 2%; }

    div[id^="subcontent-item"] {
        height: 350px;
        position: relative;
    }

    .subcontent-container {
        padding: 0px;
        height: 340px;
        overflow: hidden;
    }

    .subcontent-container:hover {
        box-shadow: 0px 0px 15px #a6a6a6;
    }

    .subcontent-container h5 {
       padding:20px 0;
    }

    div[id^="subcontent-item"]:hover .bottom-section {
        visibility:visible;
        opacity:1;
        transition-delay:0s;
    }

    div[id^="subcontent-item"] .bottom-section {
        visibility:hidden;
        opacity:0;
        height: 250px;
        transition:visibility 0s linear 0.2s,opacity 0.2s linear;
    }

    div[id^="subcontent-item"] .bar {
        height: 4px;
        position: relative;
        z-index: 3;
    }

    div[id^="subcontent-item"] .bar-square {
       width: 20px;
       height: 20px;
       position: relative;
       top: -8px;
       margin: 0 auto;
    }

    div[id^="subcontent-item"] .description-container {
        background: -moz-linear-gradient(top,  rgba(255,255,255,1) 48%, rgba(255,255,255,0.35) 82%, rgba(255,255,255,0) 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(48%,rgba(255,255,255,1)), color-stop(82%,rgba(255,255,255,0.35)), color-stop(100%,rgba(255,255,255,0))); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top,  rgba(255,255,255,1) 48%,rgba(255,255,255,0.35) 82%,rgba(255,255,255,0) 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top,  rgba(255,255,255,1) 48%,rgba(255,255,255,0.35) 82%,rgba(255,255,255,0) 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top,  rgba(255,255,255,1) 48%,rgba(255,255,255,0.35) 82%,rgba(255,255,255,0) 100%); /* IE10+ */
        background: linear-gradient(to bottom,  rgba(255,255,255,1) 48%,rgba(255,255,255,0.35) 82%,rgba(255,255,255,0) 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#00ffffff',GradientType=0 ); /* IE6-9 */
        min-height: 100px;
        padding-top: 20px;
        position: relative;
        z-index: 2;
    }

    div[id^="subcontent-item"] a {
        margin-top: 100px;
    }

    <c:if test="${canInlineEdit}">
        #edit-featured-subcontent-${id} {
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

<div id="cs-featured-subcontent-container-${id}">
    <c:if test="${canInlineEdit}">
        <span id="edit-featured-subcontent-${id}" class="label label-danger"><a class="inline-edit" data-toggle="modal" data-target="#featured-subcontent-edit-modal-${id}">Edit</a></span>
        <div id="featured-subcontent-edit-modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="featured-subcontent-edit-modal-${id}" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Featured Subcontent Administration</h4>
                </div>
                <div class="modal-body">
                   <form id="edit-featured-subcontent-form-${id}" role="form">
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
                         <input type="text" class="form-control" id="item-1-header-${id}" placeholder="Header" maxlength="140">
                        </div>
                        <div class="form-group">
                         <label for="item-1-description-${id}">Content Description</label>
                         <textarea class="form-control" id="item-1-description-${id}" rows="3" maxlength="140"></textarea>
                         <p class="help-block">140 characters or less.</p>
                        </div>
                        <div class="form-group">
                            <label for="item-1-accent-color-${id}">Accent Color</label>
                            <div class="accent-color-picker input-group col-sm-2">
                                <input class="form-control" id="item-1-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                                <span class="input-group-addon"><i></i></span>
                            </div>
                         </div>
                        <div class="form-group">
                         <label for="item-1-bg-image-${id}">Background Image URL</label>
                         <input type="text" class="form-control" id="item-1-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                         <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                        </div>
                         <div class="form-group">
                         <label for="item-1-dest-url-${id}">Destination URL</label>
                         <input type="text" class="form-control" id="item-1-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                         <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                       </div>
                       </div> <!-- /#item-1-${id} -->

                       <div class="tab-pane" id="item-2-${id}">
                           <div class="form-group">
                           <label for="item-2-header-${id}">Header</label>
                           <input type="text" class="form-control" id="item-2-header-${id}" placeholder="Header" maxlength="140">
                          </div>
                          <div class="form-group">
                           <label for="item-2-description-${id}">Content Description</label>
                           <textarea class="form-control" id="item-2-description-${id}" rows="3" maxlength="140"></textarea>
                           <p class="help-block">140 characters or less.</p>
                          </div>
                          <div class="form-group">
                              <label for="item-2-accent-color-${id}">Accent Color</label>
                              <div class="accent-color-picker input-group col-sm-2">
                                  <input class="form-control" id="item-2-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                                  <span class="input-group-addon"><i></i></span>
                              </div>
                           </div>
                          <div class="form-group">
                           <label for="item-2-bg-image-${id}">Background Image URL</label>
                           <input type="text" class="form-control" id="item-2-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                           <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                          </div>
                           <div class="form-group">
                             <label for="item-2-dest-url-${id}">Destination URL</label>
                             <input type="text" class="form-control" id="item-2-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                             <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                           </div>
                       </div> <!-- /#item-2-${id} -->

                       <div class="tab-pane" id="item-3-${id}">
                         <div class="form-group">
                         <label for="item-3-header-${id}">Header</label>
                         <input type="text" class="form-control" id="item-3-header-${id}" placeholder="Header" maxlength="140">
                        </div>
                        <div class="form-group">
                         <label for="item-3-description-${id}">Content Description</label>
                         <textarea class="form-control" id="item-3-description-${id}" rows="3" maxlength="140"></textarea>
                         <p class="help-block">140 characters or less.</p>
                        </div>
                        <div class="form-group">
                            <label for="item-3-accent-color-${id}">Accent Color</label>
                            <div class="accent-color-picker input-group col-sm-2">
                                <input class="form-control" id="item-3-accent-color-${id}" type="text" placeholder="CCCCCC" maxlength="6" value="" />
                                <span class="input-group-addon"><i></i></span>
                            </div>
                         </div>
                        <div class="form-group">
                         <label for="item-3-bg-image-${id}">Background Image URL</label>
                         <input type="text" class="form-control" id="item-3-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                         <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                        </div>
                        <div class="form-group">
                        <label for="item-3-dest-url-${id}">Destination URL</label>
                        <input type="text" class="form-control" id="item-3-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                        <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                      </div>
                       </div> <!-- /#item-3-${id} -->

                       <div class="tab-pane" id="item-4-${id}">
                        <div class="form-group">
                            <label for="item-4-header-${id}">Header</label>
                            <input type="text" class="form-control" id="item-4-header-${id}" placeholder="Header" maxlength="140">
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
                            <label for="item-4-bg-image-${id}">Background Image URL</label>
                            <input type="text" class="form-control" id="item-4-bg-image-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                            <p class="help-block">Please use the full url, i.e http://www.art.com/345.jpg</p>
                           </div>
                            <div class="form-group">
                                <label for="item-4-dest-url-${id}">Destination URL</label>
                                <input type="text" class="form-control" id="item-4-dest-url-${id}" placeholder="http://www.computer.org/images/background.jpg" maxlength="140">
                                <p class="help-block">Please use the full url, i.e http://www.computer.org/science</p>
                              </div>
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
        </div> <!-- /#featured-subcontent-edit-modal-${id} -->

        <script>
            $(document).ready(function() {

                var buildItemsData = function(postData) {
                    postData.item1_${id} = getItemData(1);
                    switch(parseInt(postData.numberOfItems_${id})) {
                        case 2: postData.item2_${id} = getItemData(2);
                        break;
                        case 3:
                            postData.item2_${id} = getItemData(2);
                            postData.item3_${id} = getItemData(3);
                        break;
                        case 4:
                            postData.item2_${id} = getItemData(2);
                            postData.item3_${id} = getItemData(3);
                            postData.item4_${id} = getItemData(4);
                        break;
                    }
                    return postData;
                }

                var getItemData = function(itemNumber) {
                    var item = {};
                    item.header = $('#item-'+itemNumber+'-header-${id}').val();
                    item.description = $('#item-'+itemNumber+'-description-${id}').val();
                    item.accentColor = $('#item-'+itemNumber+'-accent-color-${id}').val();
                    item.bgImage = $('#item-'+itemNumber+'-bg-image-${id}').val();
                    item.destURL = $('#item-'+itemNumber+'-dest-url-${id}').val();
                    return JSON.stringify(item);
                }

                $("#btn-save-${id}").click(function() {
                    var postData = {};
                    postData.requestType_${id} = 'SAVE_CONFIG';
                    postData.numberOfItems_${id} = $('#number-items-select-${id}').val();
                    postData = buildItemsData(postData);
                    $.ajax({
                      type: "POST",
                      data: postData,
                      url: "${csfeaturedSubContentAjaxHandler}",
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
                        console.error(data);
                        $("#alert-main-danger-${id}").fadeIn(100);
                        $("#error-message-${id}").html(data.responseJSON.error);
                        $("#btn-save-${id}").prop('disabled', false);
                      }
                  });
               });
            });
       </script>
    </c:if>

    <div id="cs-featured-subcontent-${id}" class="cs-featured-subcontent">
    </div> <!-- /#cs-featured-subcontent-${id} -->
</div>

<script>
   var showItemsAsNeeded = function() {
     var numberOfItems =  parseInt($('#number-items-select-${id}').val());
     switch(numberOfItems) {
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

   var items = [$.parseJSON('${item1}'),$.parseJSON('${item2}'),$.parseJSON('${item3}'),$.parseJSON('${item4}')];

   var setItemsOnForm = function() {
       var numberOfItems =  parseInt($('#number-items-select-${id}').val());
       setItemDataOnForm(1, items[0]);
       switch(numberOfItems) {
           case 2:
           setItemDataOnForm(2, items[1]);
           break;
           case 3:
           setItemDataOnForm(2, items[1]);
           setItemDataOnForm(3, items[2]);
           break;
           case 4:
           setItemDataOnForm(2, items[1]);
           setItemDataOnForm(3, items[2]);
           setItemDataOnForm(4, items[3]);
           break;
       }
   }

   var setItemDataOnForm = function(itemNumber, item) {
        $('#item-'+itemNumber+'-header-${id}').val(item.header);
        $('#item-'+itemNumber+'-description-${id}').val(item.description);
        $('#item-'+itemNumber+'-accent-color-${id}').val(item.accentColor);
        $('#item-'+itemNumber+'-bg-image-${id}').val(item.bgImage);
        $('#item-'+itemNumber+'-dest-url-${id}').val(item.destURL);
   }

   $(document).ready(function() {
        var numberOfItems = '${numberOfItems}';
        numberOfItems = numberOfItems == '' ? '1' : numberOfItems;
        $('#number-items-select-${id} option[value="'+numberOfItems+'"]').attr("selected", "selected");
        showItemsAsNeeded();
        setItemsOnForm();

        $('#number-items-select-${id}').change(function() {
             showItemsAsNeeded();
        });

        var colCSSClass = "col-sm-12";
        switch(parseInt(numberOfItems)) {
            case 2: colCSSClass = "col-sm-6";
            break;
            case 3: colCSSClass = "col-sm-4";
            break;
            case 4: colCSSClass = "col-sm-3";
            break;
        }

        for(var idx=0;idx<numberOfItems;idx++) {
            var html = '<div id="subcontent-item-'+idx+'-${id}" class="'+colCSSClass+'">';
            html += '<div class="subcontent-container"><h5 class="text-center">'+items[idx].header+'</h5>';
            html += '<div class="bar" style="background-color: #'+items[idx].accentColor+'"><div class="bar-square" style="background-color: #'+items[idx].accentColor+'"></div></div>';
            html += '<div class="col-sm-12 description-container"><div class="description text-center">';
            html += '<em>'+items[idx].description+'</em></div></div>';
            html += '<div class="bottom-section" style="background: url('+items[idx].bgImage+') 100%; background-size: cover;">';
            html += '<div class="col-sm-12 text-center bg-image">';
            html += '<a href="'+items[idx].destURL+'" class="btn btn-primary" style="background-color: #'+items[idx].accentColor+'; border-color: #'+items[idx].accentColor+';">More Information</a></div></div></div></div>';
            $('#cs-featured-subcontent-${id}').append(html);
        }
   });
</script>