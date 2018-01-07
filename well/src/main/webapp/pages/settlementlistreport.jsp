<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Bill Settlement</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Settlements</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchsettlement" method="post">
                   <table class="table table-no-bordered">
						<tr><td><label for="email">FromDate</label></td>
                        <td><input type="date" class="form-control" id="fromdate" name="fromdate"  value="${fromdate}"/></td>
                        <td><label for="email">ToDate</label></td>
                        <td><input type="date" class="form-control" id="todate" name="todate"  value="${todate}"/></td>
                        <td><label>Pay Mode</label></td>
									<td><select class="form-control" id="paymodeid"
										name="paymodeid">
											<option value="">Select</option>
											<c:forEach var="paymode" items="${paymodeList}">
												<c:choose>
													<c:when test="${paymode.getPaymodeid()==payMode}">
														<option selected value="${paymode.getPaymodeid()}">${paymode.getPaymode()}</option>
													</c:when>
													<c:otherwise>
														<option value="${paymode.getPaymodeid()}">${paymode.getPaymode()}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select></td>
                        <!-- <td><label for="email">Paymode</label></td>
                        <td><input type="text" class="form-control" id="billmode" name="billmode" value="" placeholder="example:cash"/></td> -->
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
                        <th>Bill Amount</th>
                        <th>Bill Date</th>
                         <th>Bill Mode</th>
                          <th>Settled Amount</th>
                          <th>Remarks</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${billSettlementList!=null}">
                    <c:forEach var="settlementList" items="${billSettlementList}">
                      <tr id="trow">
                        <td>${settlementList.billmaster.getBillno()}</td>
                        <td>${settlementList.getAmount()}</td>
                        <td>${settlementList.billmaster.getBilldate()}</td>
                        <td>${settlementList.getPaymodemaster().getPaymode()}</td>
                        <td>${settlementList.getAmount()}</td>
                        <td>${settlementList.getRemarks()}</td>
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
                <form id="reportform" action="" method="post">
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
    	var paymode = $('#paymodeid').val();
    	var paymode1 = $('#paymodeid option:selected').text();
    	var generatetype = $('#generatetype').val();
    	$('#reportform').attr('action','generateSettlementReport?fromDate='+fromdate+'&&toDate='+todate+'&&paymode='+paymode+'&&paymode1='+paymode1+'&&generateType='+generatetype);
    	$('#reportform').submit();
    });
    </script>