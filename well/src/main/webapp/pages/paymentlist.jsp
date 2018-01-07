<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Payment Report</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Payments</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchInventory" method="post">
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
                         <th>Bill Date</th>
                          <th>Guest Name</th>
                          <th>Staff Name</th>
                           <th>Service Name</th>
                            <th>Package Name</th>
                             <th>Room No</th>
                             <th>Payment Mode</th>
                              <th>Gift Voucher Id</th>
                                <th>Discount</th>
                                 <th>Total Amount</th>
                                 <th>Amount Owing</th>
                                 <th>Paid Amount</th>
                                 <th>Payment Date</th>
                                <th>Card No</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${paymentList!=null}">
                    <c:forEach var="payment" items="${paymentreportlist}">
                      <tr id="trow">
                        <td>${payment.billno}</td>
                        <td>${payment.billDate}</td>
                        <td>${payment.guestName}</td>
                        <td>${payment.staffName}</td>
                        <td>${payment.serviceName}</td>
                        <td>${payment.packageName}</td>
                        <td>${payment.roomno}</td>
                       <td>${payment.paymentmode}</td>
                        <td>${payment.giftVoucherId}</td> 
                        <td>${payment.discount}</td>
                        <td>${payment.totalAmount}</td>
                        <td>${payment.paidAmount}</td>
                        <td>${payment.paymentDate}</td>
                        <td>${payment.cardno}</td>
					 </tr>
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
    	var paymode = $('#billmode').val();
    	var generatetype = $('#generatetype').val();
    	$('#reportform').attr('action','generateInventoryReport?fromdate='+fromdate+'&&todate='+todate+'&&paymode='+paymode+'&&generatetype='+generatetype);
    	$('#reportform').submit();
    });
    </script>