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
					<h3 class="panel-title">Package Rate</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form>
						<table class="table table-no-bordered">
							<tbody>
							<tr>
									<td><label>Package Name</label></td>
									<td><select class="form-control" id="packageid"
										name="packageid">
											<option value="">Select</option>
											<c:forEach var="packages" items="${packageList}">
												<option value="${packages.packageid}">${packages.packagename}</option>
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
									<td><label>Package Cost</label></td>
									<td><input type="text" class="form-control"
										id="packagecost" name="packagecost" onkeypress="return isNumberKey(event,this)"></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="col-md-3"><label>Applicable Date</label></td>
									<td class="col-md-4"><input type="date"
										class="form-control" id="applicableDate" name="applicableDate" /></td>
									<td class="col-md-6">&nbsp;</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								<td>
									<div class="pull-right">
										<input type="button" class="btn btn-primary btn-sm" value="SaveorUpdate" onclick="savepackagerate()">
										<button type="submit" class="btn btn-primary btn-sm">Clear</button>
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
										<th>Package Name</th>
										<th>Tax Structure Description</th>
										<th>Package Cost</th>
										<th>Applicable date</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="addrowstextfields">
									<c:forEach var="packages" items="${packageRateList}">
										<tr data-toggle="tooltip" data-placement="top" title="T0 Edit click on the button">
											<td>${packages.getPackagemaster().getPackagename()}</td>
											<td>${packages.getTaxstructuremaster().getTaxstructuredescription()}</td>
											<td>${packages.getPackagecost()}</td>
											<td>${packages.getApplicabledate()}</td>
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
		document.getElementsByName("applicableDate")[0].setAttribute('min', today);
		$('#applicableDate').val(today);
		}); 
	function savepackagerate() {
		var name=document.getElementById("packageid").value; 
		if(name==""||name==null)
			{
			alert("Please select Package");
			document.getElementById("serviceid").focus();
			return false;
			}
		var name=document.getElementById("taxstructureid").value; 
		if(name==""||name==null)
			{
			alert("Please select TaxStructure");
			document.getElementById("taxstructureid").focus();
			return false;
			}
		var name=document.getElementById("packagecost").value; 
		if(name==""||name==null)
			{
			alert("Please Enter PackageCost");
			document.getElementById("packagecost").focus();
			return false;
			}
		var name=document.getElementById("applicableDate").value; 
		if(name==""||name==null)
			{
			alert("Please Enter ApplicableDate");
			document.getElementById("applicableDate").focus();
			return false;
			}

		var id = document.getElementById("packageid").value;
		var taxStructureId = document.getElementById("taxstructureid").value;
		var packageCost = document.getElementById("packagecost").value;
		var serviceDate = document.getElementById("applicableDate").value;
		
		$.ajax({
			type : "GET",
			async : false,
			url : "savepackagerate?packageid=" + id +"&&taxstructureid="+taxStructureId+ "&&packagecost="
					+ packageCost + "&&applicableDate=" + serviceDate,
			success : function(response) {
				alert(response);
				location.reload();
			}
		});
		}
	
	$(function(){
		$(".use-address").click(function() {
		    var $row = $(this).closest("tr");
		    var packagename = $row[0].children[0].innerHTML;
		    var taxstructure = $row[0].children[1].innerHTML;
		    var packagecost = $row[0].children[2].innerHTML;
		    var date = $row[0].children[3].innerHTML;
		    $("option").filter(function() {
				return $(this).text() == packagename;
			}).prop('selected', true);
		    $("option").filter(function() {
				return $(this).text() == taxstructure;
			}).prop('selected', true);
		    $('#packagecost').val(packagecost);
		    $('#applicableDate').val(date);
		    $('#packageid').attr('disabled','disabled');
			$('#taxstructureid').attr('disabled','disabled');
			$('#packageCost').attr('disabled','disabled');
		});
	});

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