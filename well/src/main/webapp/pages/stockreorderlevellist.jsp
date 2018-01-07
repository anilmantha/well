<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Stock ReOrderLevel Report</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of ReOrderLevels</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchstockreorderlevel" method="post">
                   <table class="table table-no-bordered">
						<tr>
                        <td><label for="email">stockName</label></td>
                        <td><input type="text" class="form-control" id="stockname" name="stockname"  value="${stockName}" placeholder="example:Anu Thilam"/></td>
                        <td><label for="email">stockType</label></td>
                        <td><select class="form-control" id="stocktype" name="stocktype">
								<option value="">Select</option>
								<c:forEach var="stocktype" items="${stocktype}">
									<c:choose>
										<c:when test="${stocktype.dropdowndetailsid==stocktypes}">
											<option selected value="${stocktype.dropdowndetailsid}">${stocktype.description}</option>
										</c:when>
										<c:otherwise>
											<option value="${stocktype.dropdowndetailsid}">${stocktype.description}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select></td></tr>
                        <tr><td><label for="email">stockGroup</label></td>
                        <td><select class="form-control" id="stockgroup" name="stockgroup">
								<option value="">Select</option>
								<c:forEach var="stockgroup" items="${stockgroupmaster}">
									<c:choose>
										<c:when test="${stockgroup.stockgroupid==stockGroup}">
											<option selected value="${stockgroup.stockgroupid}">${stockgroup.stockgroupname}</option>
										</c:when>
										<c:otherwise>
											<option value="${stockgroup.stockgroupid}">${stockgroup.stockgroupname}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select></td>
                        <td><label for="email">stockSubGroup</label></td>
                        <td><select class="form-control" id="stocksubgroup" name="stocksubgroup">
								<option value="">Select</option>
								<c:forEach var="Stocksubgroup" items="${Stocksubgroupmaster}">
									<c:choose>
										<c:when test="${Stocksubgroup.stocksubgroupid==stockSubGroup}">
											<option selected value="${Stocksubgroup.stocksubgroupid}">${Stocksubgroup.stocksubgroupname}</option>
										</c:when>
										<c:otherwise>
											<option value="${Stocksubgroup.stocksubgroupid}">${Stocksubgroup.stocksubgroupname}</option>
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
                        <th>S.No</th>
                        <th>Stock Name</th>
                        <th>Stock Type</th>
                         <th>Current Stock</th>
                          <th>Reorder Level</th>
                          <th>Stock Group</th>
                          <th>Stock Sub Group</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${stockReorderList!=null}">
                    <c:forEach var="reorderList" items="${stockReorderList}">
                      <tr id="trow">
                        <td>${reorderList.getSno()}</td>
                        <td>${reorderList.getStockname()}</td>
                        <td>${reorderList.dropdowndetails.getDescription()}</td>
                          <td>${reorderList.getAvailable()}</td>
                         <td>${reorderList.getReorderlevel()}</td>
                      <td>${reorderList.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname()}</td>
                        <td>${reorderList.getStocksubgroupmaster().getStocksubgroupname()}</td>
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
    	var stockname = $('#stockname').val();
    	var stocktype1 = $('#stocktype option:selected').text();
    	var stockgroup1 = $('#stockgroup option:selected').text();
    	var stocksubgroup1= $('#stocksubgroup option:selected').text();
    	var stocktype = $('#stocktype').val();
    	var stockgroup = $('#stockgroup').val();
    	var stocksubgroup = $('#stocksubgroup').val();
    	var generatetype = $('#generatetype').val();
    	$('#reportform').attr('action','generateStockReorderLevelReport?stockname='+stockname+'&&stocktype='+stocktype+'&&stockgroup='+stockgroup+'&&stocksubgroup='+stocksubgroup+'&&type='+stocktype1+'&&group='+stockgroup1+'&&subgroup='+stocksubgroup1);
    	$('#reportform').submit();
    });
    </script>