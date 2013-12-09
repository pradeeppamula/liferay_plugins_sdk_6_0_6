<%@ include file="/WEB-INF/jsp/inc/includes.jsp" %>
<portlet:actionURL var="configureAction" portletMode="view" windowState="normal"/>
<portlet:actionURL var="viewAction" windowState="normal" portletMode="view"/>

<%-- *****************************************************
	Hide the Portlet when it has been DEACTIVATED.  This 
	will allow the admin of the page/site to make changes
	to the portlet, without having the users/visitors
	see those changes.
***************************************************** --%>
<c:if test="${portletMode == 'ACTIVATED' || portletMode == 'PREVIEW'}">

<style type="text/css">

	#communityPollInnerContainer${id} {
		margin: ${innerMargins};
	}

	#communityPollContainer${id} {
		border-top: ${portletBorderPixelTop}px solid #${portletBorderColorTop};
		border-right: ${portletBorderPixelRight}px solid #${portletBorderColorRight};
		border-bottom: ${portletBorderPixelBottom}px solid #${portletBorderColorBottom};
		border-left: ${portletBorderPixelLeft}px solid #${portletBorderColorLeft};		
		border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		-moz-border-top-left-radius: ${portletTopLeftRadius}px ${portletTopLeftRadius}px;
		border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		-moz-border-top-right-radius: ${portletTopRightRadius}px ${portletTopRightRadius}px;		
		border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;		
		-moz-border-bottom-left-radius: ${portletBottomLeftRadius}px ${portletBottomLeftRadius}px;
		border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;
		-moz-border-bottom-right-radius: ${portletBottomRightRadius}px ${portletBottomRightRadius}px;
	    color: #${pollFontColor};
	}	
	
	#communityPollContainer${id} {	
		background-color: #${portletBackgroundColor};	
	}
	
	#error-message-${id} { display:none; font-size: 1.2em; }
	
	.progress-info .bar {
	  background-color: #${pollBarColor};
	  background-image: -moz-linear-gradient(top, #${pollBarColor}, #${pollBarColor});
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#${pollBarColor}), to(#${pollBarColor}));
	  background-image: -webkit-linear-gradient(top, #${pollBarColor}, #${pollBarColor});
	  background-image: -o-linear-gradient(top, #${pollBarColor}, #${pollBarColor});
	  background-image: linear-gradient(to bottom, #${pollBarColor}, #${pollBarColor});
	  background-repeat: repeat-x;
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff${pollBarColor}', endColorstr='#ff${pollBarColor}', GradientType=0);
	}
	
	#vote-form-${id}{
		font-size: ${pollFontSize}px;
	}
	
	.poll-question-${id} {
		font-weight: bold;
		font-size: ${pollQuestionSize}px;
	}
	
<c:if test="${canInlineEdit}">
	#editorModal${id} {
		background-color: #FFFFFF;	
		padding-left: 5px;
	} 	
</c:if>
</style>

<div id="communityPollContainer${id}">
<c:if test="${canInlineEdit}">
	<div id="editContent${id}" class="editContent"></div>
</c:if>	
	<div id="communityPollInnerContainer${id}">
		<div>
			<p class="poll-question-${id}">${currentPoll.question}</p>
			<form id="vote-form-${id}" name="vote-form-${id}" method="POST" action="${viewAction}">
				<input type="hidden" id="source-${id}" name="source-${id}" value="VOTE"/>
				<input type="hidden" id="question-${id}" name="question-${id}" value="${currentPoll.questionId}" />
				<c:choose>
				  <c:when test="${canVote}">
				    <div>  
				        <c:forEach items = "${currentPoll.choices}" var = "choice" varStatus="idx">
							<label class="radio">
						  	<input type="radio" name="option-radios-${id}" id="option-radios-${choice.choiceId}" value="${choice.choiceId}"/>
						  		${choice.description}
							</label>
						</c:forEach>
					</div>
					<div><button id="btn-vote-submit-${id}" class="btn btn-primary">Submit</button></div>
				  </c:when>
				  <c:otherwise>
				  	<c:forEach items = "${currentPoll.choices}" var = "choice" varStatus="idx">
				  		<div>${choice.description}</div>
						<div class="progress progress-info"> 
							<c:choose>
								<c:when test="${choice.numberOfVotes > 0}">
							  		<div id="progress-choice-${choice.choiceId}" class="bar" style="width: 0%" data-percentage="${choice.numberOfVotes/currentPoll.numberOfVotes*100}">
							  		</div>
								</c:when>
								<c:otherwise>
									<div id="progress-choice-${choice.choiceId}" class="bar" style="width: 5%" data-percentage="0">0%</div>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
					<c:if test="${showVoteTotal}">
						<div><strong>Total Votes: ${currentPoll.numberOfVotes}</strong></div>
					</c:if>
				  </c:otherwise>
				</c:choose>
			</form>
		</div>
	</div>
</div>

<c:if test="${canInlineEdit}">
	<div id="editorModal${id}" class="editorModal">
		<div>
			<p id="error-message-${id}" class="text-error"></p>
			<button id="btn-add-choice-${id}" class="btn">Add Choice</button>
		    <form class="form-horizontal" id="configure-form-${id}" name="configure-form-${id}" method="POST" action="${configureAction}">
				<input type="hidden" id="source-${id}" name="source-${id}" value="CONFIG"/>
				<input type="hidden" id="question-${id}" name="question-${id}" value="${currentPoll.questionId}" />
				 <div class="control-group">
					<label class="control-label" for="input-question-${currentPoll.questionId}">Question:</label>
					<div class="controls">
					  	<textarea id="input-question-${currentPoll.questionId}-${id}" name="input-question-${currentPoll.questionId}-${id}" rows="3"> ${currentPoll.question}</textarea>
					 </div>
				</div>
				<c:forEach items = "${currentPoll.choices}" var = "choice" varStatus="idx">
					<div id="choice-container-${choice.choiceId}-${id}" class="control-group existing-choice-${id}">
					    <label class="control-label" for="input-choice-${choice.choiceId}-${id}">Choice ${idx.count}:</label>
					    <div class="controls">
					      <input type="text" id="input-choice-${choice.choiceId}-${id}" name="input-choice-${choice.choiceId}-${id}" value="${choice.description}"/>
					      <c:if test="${fn:length(currentPoll.choices) > 2}">
								<button id="btn-remove-choice-${id}-${choice.choiceId}" class="btn btn-danger">Remove</button>
					      </c:if>						     
					    </div>
				  	</div>
				</c:forEach>
				<c:forEach begin="1" end="10" var="val">
				    <div id="choice-container-${val}-${id}" class="control-group new-choice-${id}" style="display:none;">
					    <label class="control-label" for="input-choice-${val}-${id}">New Choice ${val}:</label>
					    <div class="controls">
					      <input type="text" id="input-choice-${val}-${id}" name="input-choice-${val}-${id}" value="" placeholder="Description" />
							<button id="btn-remove-choice-${id}-${val}-new" class="btn btn-danger">Remove</button>
					    </div>
					</div>
				</c:forEach>
				<div class="editor-modal-submit-${id}">
					<button class="btn btn-primary" id="editorModalSave${id}">Save</button>	
					<button class="btn" id="editorModalCancel${id}">Cancel</button>	
				</div>
			</form>
		</div>
	</div>		
</c:if>

<script type="text/javascript">


$(document).ready(function() {
	function animateProgressBars() {
		setTimeout(function(){
		    $('.progress .bar').each(function() {
		        var me = $(this);
		        var perc = me.attr("data-percentage");
		        var current_perc = 0;
		        var progress = setInterval(function() {
		            if (current_perc>=perc) {
		                clearInterval(progress);
		            } else {
		                current_perc +=1;
		                me.css('width', (current_perc)+'%');
		            }
		
		            me.text((current_perc)+'%');
		
		        }, 10);
		    });
		},100);
	}
	
	animateProgressBars();

	<c:if test="${canInlineEdit}">
			var nextChoiceId = 0;
			function resetForm(id) {
				$('#'+id).each(function(){
					this.reset();
				});
				
				// remove any errors 
				$('#error-message-${id}').html("");
				$('#error-message-${id}').hide();
				
				// remove the error class from the question text area
			    var questionElement = $('#input-question-${currentPoll.questionId}-${id}');
			   	questionElement.parent().parent().removeClass('error');
	
				// iterate over all the new choice fields and hide them
				$(".new-choice-${id}").each(function( index ) {
					$(this).removeClass('error');
					// clear out the new choice fields
					$(this).find('input').val('');
					$(this).hide();
				});
				
				// iterate over all the existing choices and show them
				$(".existing-choice-${id}").each(function( index ) {
					$(this).removeClass('error');
					$(this).show();
				});
				
				// re-enable all remove buttons
				// get the number of choices for the current poll 
				var $choicesCount = $("button[id^='btn-remove-choice-${id}']").size();
	            /* 
	             * count the number or remove buttons for the portal
	             * if there are > 2, enable them all.
	             */
	            if($choicesCount > 2) {
		 			$("button[id^='btn-remove-choice-${id}']").each(function( index ) {
						 $(this).removeAttr("disabled");
					});
	            }
				
				// reset the nextChoiceId 
				nextChoiceId = 0;	
			}
			
			$("#editContent${id}").click(function() {	
				$(this).hide();	
				$("#editorModal${id}").fadeIn(500);
			});
			
			$("#editorModalCancel${id}").click(function(event) {
				// prevent the form submission
				event.preventDefault();	
				
				// reset the form
				resetForm("configure-form-${id}");	
					
				$("#editContent${id}").show();
				$("#editorModal${id}").fadeOut(500);
			});		
		
			$("#editorModalSave${id}").click(function() {
				var invalidData = false;
			    // validate for the mininum number of choices
			    if($("div[id^='choice-container-']:visible").size() < 2) {
			    	// show error alert 
			    	$('#error-message-${id}').text('There needs to be at least 2 choices.');
			    	$('#error-message-${id}').show();
			    	invalidData = true;
			    	return false;
			    }
			    
			    // validate that the question has data
			    var questionElement = $('#input-question-${currentPoll.questionId}-${id}');
			    if(questionElement.val() == '') {
			        questionElement.parent().parent().addClass('error');
			    	invalidData = true;
			    } else {
			    	questionElement.parent().parent().removeClass('error');
			    }
			    // validate that all the choices have data entered
			    $("div[id^='choice-container-']:visible").each(function( index ) {
					// validate that each item has data
					if($(this).find('input').val() == '') {
						// add error to the 
						$(this).addClass('error');
						invalidData = true;
					} else {
						$(this).removeClass('error');
					}
				});
				
				// if there are validation errors, prevent the form from submitting
				if(invalidData) {
				  	$('#error-message-${id}').text('Please verify that all fields have data entered.');
			    	$('#error-message-${id}').show();
					return false;
				}
				
				// iterate over all the existing choices and remove the ones that are hidden 
				// so that they are not submitted
				$(".existing-choice-${id}").each(function( index ) {
					if(!$(this).is(':visible')) {
					   $(this).remove();
					}				
				});
				
				$("#configure-form-${id}").attr("action", "${configureAction}");
				$("#configure-form-${id}").submit();
			});	
			
			// add another choice row when the user clicks the "Add Choice" button
			$("#btn-add-choice-${id}").click(function(event) {
				// prevent the form submission
				event.preventDefault();	
				// increment the next new choice id
				nextChoiceId++;
				// show the next new choice  
				$('#choice-container-'+nextChoiceId+'-${id}').show();
				// get the number of choices for the current poll 
				var $choicesCount = $("button[id^='btn-remove-choice-${id}']:visible").size();
	            /* 
	             * count the number or remove buttons for the portal
	             * if there are > 2, enable them all.
	             */
	            if($choicesCount > 2) {
		 			$("button[id^='btn-remove-choice-${id}']").each(function( index ) {
						 $(this).removeAttr("disabled");
					});
	            }
	            
	            // check the number of choices, if we hit the max, disable the add choices button
	            if($choicesCount >= ${maxNumberOfChoices}) {
	            	$("#btn-add-choice-${id}").attr("disabled", "disabled");
	            } 
			});		
			
			$("button[id^='btn-remove-choice-']").live('click',function(event) {
				// prevent the form submission
				event.preventDefault();
				// we are using the default browser confirm for now, we can change to something fancy later.
				var result = confirm("Are you sure you would like to remove this choice?");
				if (result == true)
				{
				  	// parse out the choice id and instance id
					var id = event.target.id;
					var idTokens = id.split('-');
					var choiceId = idTokens[4];
					var instanceId = idTokens[3];
					var newChoice = idTokens[5];
					
					// if we are removing a new choice, just hide it
					if(choiceId != undefined && choiceId == 'new') {
						$('#choice-container-'+choiceId+'-${id}').hide();	
					} else {
						// now remove the choice container for matching this id
						$('#choice-container-'+choiceId+'-${id}').hide();	
					}
		            $(this).dialog("close");
	
		            /* 
		             * count the number or remove buttons for the portal
		             * if there are < 3, disable them all.
		             */
		            var $choicesCount = $("button[id^='btn-remove-choice-${id}']:visible").size();
		            if($choicesCount < 3) {
			 			$("button[id^='btn-remove-choice-${id}']").each(function( index ) {
							 $(this).attr("disabled", "disabled");
						});
		            }
		            // check the number of choices, if we are under the max, re-enable the add choices button
		            if($choicesCount < ${maxNumberOfChoices}) {
		            	$("#btn-add-choice-${id}").removeAttr("disabled");
		            } 
				}
			});
		</c:if>
	});	
</script>
</c:if>
