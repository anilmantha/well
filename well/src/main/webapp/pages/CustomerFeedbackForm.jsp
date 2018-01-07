<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
				</h1>
			</div>
		</div>

				
      <!-- Main Content Area -->
      
      <div class="row">
      <div class="col-lg-12">
      <div class="panel panel-default">
      <div class="panel-heading">
              <h3 class="panel-title">Customer Feedback</h3>
            </div>
        </div>
        </div>
        </div>
          <form class="form-inline" action="" method="post" id="saveresponseform">
         <div class="row">
         <div class="col-md-12">
        
          <c:forEach var="forms" items="{formvalue}"></c:forEach>
          <input type="hidden" name="formid" value="${formvalue.getFormid()}"/>
                      <input type="hidden" name="feedbackformid" value="${feedbackformid}"/>
                      <input type="hidden" name="Sbuid" value="${formvalue.getSbumaster().getSbuid()}"/> 
                      <input type="hidden" name="billno" value="${feedbackform.getBillmaster().getBillno()}"/> 
                      
                       <input type="hidden" name="guestid" value="${guestdetails.getGuestid()}"/>
                      <table cellpadding="0" cellspacing="0" class="table" style="width:800px !important;">
                                                         <tr> <label>Dear &nbsp  <c:out value="${ guestdetails.getName()},"></c:out></label>
                                                          <p>Thank you for visiting Juventa. Our aim is to offer you great customer service. Your comments and suggestion would help us measure 
                    and improve the quality of our service products and ambience. Please take a moment to let us know how we are doing. </p>
                    </tr>         
                   </table>
                  </div>
                  </div>
                   <div class="row">
                   <div class="col-md-12">
                    <table cellpadding="0" cellspacing="0" class="table" style="width:800px !important;">
                      <tr> 
                        <td><strong>Please check the appropriate box below :</strong></td>  
                         <c:forEach var="responseDetails" items="${responsedetails}">
                        <input type="hidden" name="${responseDetails.getResponseid()}" value="${responseDetails.getResponsescore()}"/>
                        <td><strong><c:out value="${responseDetails.getResponsedescription()}"></c:out></strong></td>
                        </c:forEach>
                        </tr>
                                       
                     <c:forEach var="questionDetails" items="${questiondetails}">
                      <c:if test="${formvalue.getFormid()==questionDetails.getFormmaster().getFormid()}">
                      <input type="hidden" id="${questionDetails.getQuestionid() }" name="dept${questionDetails.getQuestionid()}" value="${questionDetails.getDepartmentmaster().getDepartmentid()}"/>
                     
                      <tr class="questions">
                      <td><label><c:out  value="${questionDetails.getQuestiondescription()}"></c:out><i class="fa"></i></label></td>
                      
                          <c:forEach var="responseDetails" items="${responsedetails}">
                          	<td><label class="cr-styled" for="example-radio2">
                          <input type="radio" id="${questionDetails.getQuestionid() }" name="ques${questionDetails.getQuestionid()}"  value="${responseDetails.getResponseid()}"><i class="fa"></i></label>
                        </td>
                          </c:forEach>
                      </tr></c:if>
                     </c:forEach>
                     </table>
                   </div>
                   </div>
                   
                   <button class="btn btn-primary btn-sm" id="savefeedback">Send feedback</button> 
                    <!-- Modal content-->
                    <div id="summaryModal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Summary</h4>
      </div>
      <div class="modal-body">
      		<div class="table-responsive">
            <table class="success table table-bordered">
              <tbody id="addsummaryfields">
              	
              </tbody>
              </table>
              </div>
          </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" id="proceed">Proceed</button>
         <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">Cancel</button>
      </div>
    </div>
</div>
  </div>
    </form>
</div>
              
                
        
       
  <script type="text/javascript">

       $('#proceed').click(function(){
    	  var count = 0;
    	  var questions="";
    	    $("#addsummaryfields").html('<tr></tr>');
     	 
    	  for(var i = 1; i<=$('.questions').length;i++){
    		  
    	  
    	  var responsevalue=$('input[name=ques'+i+']:checked').val();
    	  if(responsevalue==4)
    		 
    		  {
    		 
    	    	var ques = $('.questions')[i-1].children[0].children[0].innerText;
    	    	var tr = '<tr class="se"><td>'+ques+':VeryPoor</td>'+
      			'</tr>';
      			$("#addsummaryfields tr:last").after(tr); 
    	    	++count;
    		  }
    	  else if(responsevalue==5){
    		  var ques = $('.questions')[i-1].children[0].children[0].innerText;
    		    var tr = '<tr class="se"><td>'+ques+':Poor</td>'+
    			'</tr>';
    			$("#addsummaryfields tr:last").after(tr); 
  		  	++count;
    	  }
           
          
    	}
   
    	  if(count!=0)
    		  {
    		  $('#addsummaryfields')[0].innerHtml = ""+questions;
    		  	 $('#summaryModal').modal(); 
    		  	
    		  }
    	  else{
    		  document.getElementById("saveresponseform").action='saveResponse';
    		  document.getElementById("saveresponseform").submit();
    	  }
      
      });
         
      $('#savefeedback').click(function(){
    	 document.getElementById("saveresponseform").action='saveResponse';
		  document.getElementById("saveresponseform").submit(); 
     });
</script> 	
