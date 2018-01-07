<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Product Sales Report</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Product Sales</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchproductsales" method="post">
                   <table class="table table-no-bordered">
                    <%-- <td><label for="email">FromDate</label></td>
                        <td><input type="date" class="form-control" id="fromdate" name="fromdate"  value="${fromdate}"/></td>
                        <td><label for="email">ToDate</label></td>
                        <td><input type="date" class="form-control" id="todate" name="todate"  value="${todate}"/></td> --%>
                        <tr><td><label for="email">stockName:</label>&nbsp;<select class="form-control" id="stockid" name="stockid">
								<option value="Select">Select</option>
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
							</select>&nbsp;<button type="submit" class="btn btn-primary btn-sm" >Search</button></td></tr>
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
                        <th>ProductSales Id</th>
                        <th>Stock Name</th>
                         <th>Quantity</th>
                          <th>Total Amount</th>
                          <th>Bill No</th>
                          <th>Process Date</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${productSalesList!=null}">
                    <c:forEach var="productsales" items="${productSalesList}">
                      <tr id="trow">
                        <td>${productsales.getProductsalesid()}</td>
                        <td>${productsales.stockmaster.getStockname()}</td>
                        <td>${productsales.getQuantity()}</td>
                        <td>${productsales.getTotalamount()}</td>
                        <td>${productsales.billmaster.getBillno()}</td>
                        <td>${productsales.getUpdateddate()}</td>
                       <%--  <c:forEach var="billdetails" items="${appointmentstock.getAppointment().getBilldetailses()}">
                        <c:if test="${billdetails.getServicemaster()!=null && billdetails.getTaxstructureid()==null && billdetails.getServicemaster().getServiceid()==servicestockList.getServicemaster().getServiceid()}"> --%>
                        	<%-- <td>${billdetails.getBillmaster().getBillno()}</td> --%>
                        <%-- </c:if> 
                        </c:forEach>--%>
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
    	var stockname = $('#stockid').val();
    	var stockname1 = $('#stockid option:selected').text();
    	var generatetype = $('#generatetype').val();
    	$('#reportform').attr('action','generateproductsalesreport?fromDate='+fromdate+'&&toDate='+todate+'&&stockname='+stockname+'&&stockname1='+stockname1+'&&generateType='+generatetype);
    	$('#reportform').submit();
    });
	</script>
   