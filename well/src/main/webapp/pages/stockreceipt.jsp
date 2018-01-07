<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-header">
							
						</h1>
						<!--<ol class="bread crumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
					</div>
				</div>

				<!-- Main Content Area -->
				<c:set var="string" value="${message}"/>
				<c:choose>
					<c:when test="${fn:contains(string, 'not')}">
						<div class="row">
							<div class="col-md-offset-5">
									<h4><span class="label label-danger"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:when>
					<c:when test="${message==null}">
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="col-md-offset-5">
									<h4><span class="label label-success"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Add Stock Receipt</h3>
							</div>
							<div class="panel-body">
								<div class="col-md-11">
								<form action="stockrefill" class="col-md-12" method="get">
									<table class="table table-no-bordered">
											<tbody>
												<tr>
													<td class="col-md-3"><label>Date of Receipt</label></td>
													<td class="col-md-4"><input type="date"
														class="form-control" id="dateOfReceipt"
														name="receiveddate" required/></td>
													<td class="col-md-5">&nbsp;</td>
												</tr>
												<tr>
													<td><label>Stock Name</label></td>
													<td><select class="form-control forChosen"
														id="stockId" name="stockId" onchange="get()" required>
															<option>Select</option>
															<c:forEach var="stockname" items="${receiptstockdetails}">
																<option value="${stockname.stockid}">${stockname.stockname}</option>
															</c:forEach>
													</select></td>
													<td id="availability"></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Quantity Received</label></td>
													<td><input type="number" class="form-control"
														id="quantityReceived" name="receivedstock" required></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Supplier</label></td>
													<td><select class="form-control forChosen1"
														id="supplier" name="suppliermaster" tabindex="0" required>
															<option>Select</option>
													</select></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Manufacturer</label></td>
													<td><select class="form-control forChosen2"
														id="manufacturer" name="manufacturermaster" tabindex="0" required>
															<option>Select</option>
													</select></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><label>Stock Unit Rate</label></td>
													<td><input type="number" class="form-control"
														id="unitRate" required
														onkeypress="return isNumberKey(event,this)"
														name="stockunitprice"></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="col-md-3"><label>Expiry Date</label></td>
													<td class="col-md-3"><input type="date"
														class="form-control" id=expiryDate name="expirydate" required/></td>
													<td class="col-md-3">&nbsp;</td>
												</tr>
												<tr>
													<td class="col-md-3"><label>Manufacture Date</label></td>
													<td class="col-md-3"><input type="date"
														class="form-control" id=manufacturedate name="manufacturedate" required/></td>
													<td class="col-md-3">&nbsp;</td>
												</tr>
												<tr>
													<td><label>Remarks</label></td>
													<td><textarea class="form-control" rows="5"
															id="remarks" maxlength="255" name="remarks" required></textarea></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><div class="pull-right">
															<input type="submit" class="btn btn-primary btn-sm"
																value="Save"/> <a href="stocklist"><input
																type="button" class="btn btn-primary btn-sm"
																value="Close"></a>
														</div></td>
													<td>&nbsp;</td>
												</tr>
											</tbody>
										
									</table>
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
		var today = new Date().toISOString().split('T')[0];
		document.getElementsByName("receiveddate")[0].setAttribute('max', today);
		document.getElementsByName("expirydate")[0].setAttribute('min', today);
		document.getElementsByName("manufacturedate")[0].setAttribute('max', today);
		$('#dateOfReceipt').val(today);
		$('#expiryDate').val(today);
		$('#manufacturedate').val(today);
	</script>
	
	<script type="text/javascript">
		function get() {
			var name = document.getElementById("stockId").value;
			$.ajax({
				type : "GET",
				async : false,
				url : "stockavailability?stockId="+name,
				success : function(response) {
					document.getElementById("availability").innerHTML = "Current Avaliability : "+ response;
					
				}
			});
			
			$.ajax({
				type : "GET",
				async : false,
				url : "getsupplierlist",
				success : function(response) {
					for (var i = 0, len = response.length; i < len; i=i+2) {
						$('#supplier').append(
								$('<option/>').attr("value", response[i]).text(response[i+1]));
					}
					supplier();

				}
			});
			
			$.ajax({
				type : "GET",
				async : false,
				url : "getmanufacturerlist",
				success : function(response) {
					for (var i = 0, len = response.length; i < len; i=i+2) {
						$('#manufacturer').append(
								$('<option/>').attr("value", response[i]).text(response[i+1]));
					}
					manufacturer();

				}
			});

		}
	</script>
	<script type="text/javascript">
	function isNumberKey(evt, element) {
  var charCode = (evt.which) ? evt.which : event.keyCode
  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charcode == 8))
    return false;
  else {
    var len = $(element).val().length;
    var index = $(element).val().indexOf('.');
    if (index > 0 && charCode == 46) {
      return false;
    }
    if (index > 0) {
      var CharAfterdot = (len + 1) - index;
      if (CharAfterdot > 3) {
        return false;
      }
    }

  }
  return true;
}
</script>

	<script type="text/javascript">
function supplier(){
	 $(function() {
	     $('.forChosen1').chosen();
	  });
}
function manufacturer(){
	 $(function() {
	     $('.forChosen2').chosen();
	  });
}
setTimeout(function() {
    $('#message').fadeOut('fast');
}, 2000);
</script>

