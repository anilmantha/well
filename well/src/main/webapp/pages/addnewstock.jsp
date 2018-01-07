<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<small>Stock Lists</small>
						</h1>
						<!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
					</div>
				</div>

				<!-- Main Content Area -->
				<c:set var="string" value="${message}"/>
				<c:choose>
					<c:when test="${fn:contains(string, 'not')}">
						<div class="row">
							<div class="col-lg-12 col-md-offset-5">
									<h4><span class="label label-danger"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:when>
					<c:when test="${message==null}">
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="col-lg-12 col-md-offset-5">
									<h4><span class="label label-success"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Add Stock Details</h3>
							</div>
							<div class="panel-body">
								<div class="col-md-9">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="table table-no-bordered">
										<form action="savenewstock" method="post">
											<tbody>
												<tr>
													<td class="col-md-3"><label>Stock Name</label></td>
													<td class="col-md-6"><input type="text" class="form-control" name="stockName" required></td>
													<td class="col-md-6">&nbsp;</td>
												</tr>
												<input type="hidden" value="" id="StockGroupMasterId" name="StockGroupMasterId"/>
												<tr>
													<td><label>Group</label></td>
													<td><select class="form-control" name="stockGroupName"
														id="stockgroup" onchange="getting()" required>
															<option>Select</option>
															<c:forEach var="stockgroupname" items="${stockgroupnames}">
																<option>${stockgroupname}</option>
															</c:forEach>
													</select></td>

													<td>&nbsp;</td>
												</tr>
												<input type="hidden" value="" id="StockSubGroupId" name="StockSubGroupId"/>
												<tr>
													<td><label>Sub Group</label></td>
													<td><select class="form-control" id="stocksubgroup" name="stockSubgroupName" onchange="getSubGroupId()" required>
															<option>Select</option>
													</select></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Stock Type</label></td>
													<td><select class="form-control" id="stocktype" name="stocktype" required>
															<option>Select Stock Type</option>
															<c:forEach var="stocktype" items="${stocktype}">
																<option value="${stocktype.getDropdowndetailsid()}">${stocktype.getDescription()}</option>
															</c:forEach>
													</select></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Reorder Level</label></td>
													<td><input type="text" class="form-control" id="" name="reOrderlevel" required></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Warning Level</label></td>
													<td><input type="text" class="form-control" id="" name="warningLevel" required></td>
													<td>&nbsp;</td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td><div class="pull-right">
															<input type="submit" class="btn btn-primary btn-sm" value="Save">
															<a href="stocklist"><input type="button" class="btn btn-primary btn-sm" value="Close"></a>
														</div></td>
													<td>&nbsp;</td>
												</tr>
											</tbody>
										</form>
									</table>

								</div>
								<div class="col-md-3"></div>
							</div>
						</div>
					</div>
				</div>

				<!-- Main Content Area -->
				<!-- /.container-fluid -->

			

		<script type="text/javascript">
function getting(){
	var name =	document.getElementById("stockgroup").value;
	$.ajax({
		type : "GET",
		async : false,
		url : "stockgroupmasterid?stockGroupMasterName="+name,
		success : function(response) { 
			document.getElementById("StockGroupMasterId").value=response;

		        }
	}); 
$.ajax({
	type : "GET",
	async : false,
	url : "stocksubgroupname?stockGroupMasterName="+name,
	success : function(response) { 
		 $('#stocksubgroup').empty();
		$('#stocksubgroup').append($('<option/>').attr("value","").text("Select"));
		for (var i = 0, len = response.length; i < len; ++i) 
		{
			var prj = response[i];
			$('#stocksubgroup').append($('<option/>').attr("value", prj).text(prj));
		} 
		

	        }
}); 
}
function getSubGroupId(){
	var name =	document.getElementById("stocksubgroup").value;
	$.ajax({
		type : "GET",
		async : false,
		url : "getStockSubGroupId?stockSubGroupName="+name,
		success : function(response) { 
			document.getElementById("StockSubGroupId").value=response;

		        }
	}); 
}

setTimeout(function() {
    $('#message').fadeOut('fast');
}, 2000);
</script>


