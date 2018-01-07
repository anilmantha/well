<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Therapist wise Transaction Report</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Transactions</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchtherapistreport" method="post">
                   <table class="table table-no-bordered">
                   <tr><td><label for="email">FromDate</label></td>
                        <td><input type="date" class="form-control" id="fromdate" name="fromdate"  value="${fromdate}"/></td>
                        <td><label for="email">ToDate</label></td>
                        <td><input type="date" class="form-control" id="todate" name="todate"  value="${todate}"/></td>
						<td><label for="email">serviceName</label></td>
                        <td><select class="form-control" id="servicename" name="servicename">
								<option value="">Select</option>
								<c:forEach var="service" items="${servicemaster}">
									<c:choose>
										<c:when test="${service.serviceid==servicename}">
											<option selected value="${service.serviceid}">${service.servicename}</option>
										</c:when>
										<c:otherwise>
											<option value="${service.serviceid}">${service.servicename}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select></td>
                  		<td><button type="submit" class="btn btn-primary btn-sm" >Search</button></td></tr>
                    </table>
                  </form>
                </div>
                </div>
                <div class="col-md-3"></div>
              </div>
              <hr>
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Bill No</th>
                        <th>Appointment Date</th>
                        <th>Staff Name</th>
                        <th>Service Name</th>
                        <th>Amount Paid</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${therapistList!=null}">
                    <c:forEach var="therapist" items="${therapistList}">
                    <c:forEach var="billdetails" items="${billdetailslist}">
                      <c:if test="${therapist.appointment.getAppointmentdate()==billdetails.appointment.getAppointmentdate() && therapist.servicemaster.getServiceid()==billdetails.servicemaster.getServiceid() && billdetails.getTaxstructureid()==null}">
	                  <tr id="trow">
                        <td>${billdetails.billmaster.getBillno()}</td>
                        <td>${therapist.appointment.getAppointmentdate()}</td>
                        <td>${therapist.staffmaster.getStaffname()}</td>
                      	<td>${therapist.servicemaster.getServicename()}</td>
	                    <td>${billdetails.getAmount()}</td>
					  </tr>
                    	</c:if>
                        </c:forEach>
                      </c:forEach>
                    </c:if>
                    <%--  <c:if test="${msg!=null}">
                     ${msg}
                     </c:if> --%>
                     </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4">
                  <div class="pull-left">
                    <!-- <ul class="pagination pagination-sm">
                      <li><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li>
                    </ul> -->
                  </div>
                </div>
                <div class="col-md-8">
                <form id="reportform" action="">
                <table class="">
                  <tr><td>
                    <select class="form-control" id="generatetype" name="generatetype">
		            	<option>Select Generate To</option>
		              	<option value="pdf">PDF</option>
		              	<option value="excel">Excel Sheet</option>
		            </select>
		            </td>
		          	<td>
		              <input type="button" id="generateform" class="btn btn-primary btn-sm" value="Submit">
		            </td></tr>
		            </table>
                    <!-- <a href="billsettle">billsettle</a> -->
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 
    <script type="text/javascript">
    var fromday = new Date().toISOString().split('T')[0];
	document.getElementsByName("fromdate")[0].setAttribute('max', fromday);
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("todate")[0].setAttribute('max', today);
    function isNumber(evt) {
    	
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode<48 || charCode>57)) {
            return false;
        }
        return true;
    }
    
    $('#generateform').click(function(){
    	var fromdate = $('#fromdate').val();
    	var todate = $('#todate').val();
    	var serviceid = $('#servicename').val();
    	var generatetype = $('#generatetype').val();
    	$('#reportform').attr('action','generateTherapistTransactionReport?fromdate='+fromdate+'&&todate='+todate+'&&servicename='+serviceid+'&&generatetype='+generatetype);
    	$('#reportform').submit();
    });
    </script>