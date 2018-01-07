<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Product Rate</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form>
						<table class="table table-no-bordered">
						
							<tbody>

								<tr>
									<td><label>Product Name</label></td>
									<td><select class="form-control" id="stockid"
										name="stockid">
											<option value="">Select</option>
											<c:forEach var="products" items="${productlist}">
												<option value="${products.stockid}">${products.stockname}</option>
											</c:forEach>
									</select></td>

								</tr>
								<tr>
									<td><label>Tax structure</label></td>
									<td><select class="form-control" id="taxstructureid"
										name="taxstructureid">
											<option value="">Select</option>
											<c:forEach var="taxstructure" items="${taxstructurelist}">
												<option value="${taxstructure.getTaxstructureid()}">${taxstructure.getTaxstructuredescription()}</option>
											</c:forEach>
									</select></td>

								</tr>
								<tr>
									<td><label>Product Cost</label></td>
									<td><input type="text" class="form-control"
										id="productcost" name="productcost" onkeypress="return isNumberKey(event,this)"></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="col-md-3"><label>Applicable Date</label></td>
									<td class="col-md-4"><input type="date"
										class="form-control" id="applicabledate" name="applicabledate" /></td>
									<td class="col-md-6">&nbsp;</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								<td>
									<div class="pull-right">
										<input type="button" class="btn btn-primary btn-sm" value="SaveorUpdate" onclick="saveproductrate()">
										<button type="reset" class="btn btn-primary btn-sm">Clear</button>
									</div>
								</td>
								<td>&nbsp;</td>
								</tr>
							</tbody>
							
						</table>
						</form>

							</div>
							<div class="col-md-3"></div>
							<table  id="EditTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Product Name</th>
										<th>Tax Structure Description</th>
										<th>Product Cost</th>
										<th>Applicable date</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="addrowstextfields">
									<c:forEach var="products" items="${productRateList}">
										<tr data-toggle="tooltip" data-placement="top" title="T0 Edit click on the button">
											<td>${products.getStockmaster().getStockname()}</td>
											<td>${products.getTaxstructuremaster().getTaxstructuredescription()}</td>
											<td>${products.getProductcost()}</td>
											<td>${products.getApplicabledate()}</td>
											<td><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>
										</tr>
									</c:forEach>
								</tbody>

							</table> 
							</div>
							</div>
							
				</div>
			</div>
		</div>
<script type="text/javascript">
 $(document).ready(function(){
	var today = new Date().toISOString().split('T')[0];
		document.getElementsByName("applicabledate")[0].setAttribute('min', today);
		$('#applicabledate').val(today);
		});  
	function saveproductrate() {
		var id = document.getElementById("stockid").value;
		var taxStructureId = document.getElementById("taxstructureid").value;
		var productCost = document.getElementById("productcost").value;
		var serviceDate = document.getElementById("applicabledate").value;
		
		$.ajax({
			type : "GET",
			async : false,
			url : "saveproductrate?stockid=" + id +"&&taxstructureid="+taxStructureId+ "&&productcost="
					+ productCost + "&&applicableDate=" + serviceDate,
			success : function(response) {
				alert(response);
				location.reload();

			}
		});
		var today = new Date().toISOString().split('T')[0];
		$('#stockid').prop("disabled", false);
		 $('#taxstructureid').prop("disabled", false);
		 $('#productcost').prop("disabled", false);
		 $("option").filter(function() {
				return $(this).text() == "Select";
			}).prop('selected', true);
			 $("option").filter(function() {
					return $(this).text() == "Select";
				}).prop('selected', true);
			 $('#productcost').val("");
				$('#applicabledate').val(today);
		
		} 
	 
	 $(function(){
			$(".use-address").click(function() {
			    var $row = $(this).closest("tr");
			    var productname = $row[0].children[0].innerHTML;
			    var taxstructure = $row[0].children[1].innerHTML;
			    var productcost = $row[0].children[2].innerHTML;
			    var date = $row[0].children[3].innerHTML;
			    $("option").filter(function() {
					return $(this).text() == productname;
				}).prop('selected', true);
			    $("option").filter(function() {
					return $(this).text() == taxstructure;
				}).prop('selected', true);
			    $('#productcost').val(productcost);
			    $('#applicabledate').val(date);
			    $('#stockid').attr('disabled','disabled');
				 $('#taxstructureid').attr('disabled','disabled');
				 $('#productcost').attr('disabled','disabled');
			});
		});
	
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