<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Inventory Report</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Stocks</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchInventory" method="post">
                   <table class="table table-no-bordered">
                   
						<tr>
                        <td><label for="email">stockName</label></td>
                        <td><select class="form-control" id="stockname" name="stockname">
								<option value="">Select</option>
								<c:forEach var="stock" items="${stockmaster}">
									<c:choose>
										<c:when test="${stock.stockid==stockname}">
											<option selected value="${stock.stockid}">${stock.stockname}</option>
										</c:when>
										<c:otherwise>
											<option value="${stock.stockid}">${stock.stockname}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select></td>
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
                        <th>Stock Id</th>
                        <th>Stock Name</th>
                        <th>Service Name</th>
                         <th>Quantity</th>
                          <th>Process Date</th>
                          <th>Bill No</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${stockList!=null}">
                    <c:forEach var="servicestockList" items="${stockList}">
                      <tr id="trow">
                        <td>${servicestockList.stockmaster.getStockid()}</td>
                        <td>${servicestockList.stockmaster.getStockname()}</td>
                        <td>${servicestockList.servicemaster.getServicename()}</td>
                        <td>${servicestockList.getStockusage()}</td>
                        <td>${servicestockList.getUpdateddate()}</td>
                        <c:forEach var="billdetails" items="${billdetailslist}">
                        <c:if test="${billdetails.getServicemaster()!=null && billdetails.getTaxstructureid()==null && billdetails.getServicemaster().getServiceid()==servicestockList.getServicemaster().getServiceid()}">
                        	<td>${billdetails.getBillmaster().getBillno()}</td>
                        </c:if>
                        </c:forEach>
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