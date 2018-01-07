%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<!-- Main Content Area -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Tax Structure</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="get">
								<tbody>
									<tr>
										<td><label>Tax Structure Id</label></td>
										<td><input type="text" class="form-control" id="taxstructureid"
											name="taxstructureid"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Tax Name</label></td>
										<td><select class="form-control" name="taxId"
											id="taxId">
												<option>Select Tax</option>
												<c:forEach var="taxlist" items="${taxMasterList}">
													<option value="${taxlist.taxmasterid}">${taxlist.taxname}</option>
												</c:forEach>
											</select>
										</td>

										<td>&nbsp;</td>
									</tr>						
									<tr>
										<td><label>Tax Structure Description</label></td>
										<td><input type="text" class="form-control" id="taxstructuredescription"
											name="taxstructuredescription" placeholder="example: servicetax@10%,vat@5%"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Tax Structure Percent</label></td>
										<td><input type="text" class="form-control" id="taxstructurepercent"
											name="taxstructurepercent" placeholder="example:16.24,5.36"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Tax Type</label></td>
										<td><input type="text" class="form-control" id="taxtype"
											name="taxtype"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Tax On</label></td>
										<td><input type="text" class="form-control" id="taxon"
											name="taxon"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="col-md-3"><label>Applicable Date</label></td>
										<td class="col-md-4"><input type="date"
											class="form-control" id="applicabledate" name="applicabledate" /></td>
										<td class="col-md-6">&nbsp;</td>
									</tr>
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="SaveorUpdate" onclick="savetaxstructure()">
												<td>
													<button type="submit" class="btn btn-primary btn-sm">Clear</button>
												</td>
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="col-md-3"></div>
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Tax Structure Id</th>
								<th>Tax Name</th>
								<th>Tax Structure Description</th>
								<th>Tax Percent</th>
								<th>Tax Type</th>
								<th>Tax On</th>
								<th>Applicable Date</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="addrowstextfields">
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
	loadtaxdata();
}); 
	
function savetaxstructure() {
	var name=document.getElementById("taxstructureid").value; 
	if(name==""||name==null)
		{
		alert("Please Enter TaxstructureId");
		document.getElementById("taxstructureid").focus();
		return false;
		} 
	
	var taxdetails={};
	taxdetails = {
			taxStructureId : document.getElementById("taxstructureid").value,
			taxId : document.getElementById("taxId").value,
			taxStructureDescription : document.getElementById("taxstructuredescription").value,
			taxStructurePercent : document.getElementById("taxstructurepercent").value,
			taxType : document.getElementById("taxtype").value,
			taxOn : document.getElementById("taxon").value,
			applicableDate : document.getElementById("applicabledate").value,
	}
	$.ajax({
		type : "POST",
		async : false,
		url : "savetaxstructure",
		contentType : 'application/json',
		data :JSON.stringify(taxdetails),
		success : function(response) {
			alert(response);
			location.reload();
		}
	});
}

function loadtaxdata() {
	$("#addrowstextfields").html('<tr></tr>');
	$.ajax({
		type : "POST",
		async : false,
		url : "getTaxStructureList",
		success : function(response) {
			for(var i=0; i<response.length;i=i+7)
			{
				 var tr = '<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button"><td>'+response[i]+'</td>'+
				'<td>'+response[i+1]+'</td>'+
				'<td>'+response[i+2]+'</td>'+
				'<td>'+response[i+3]+'</td>'+
				'<td>'+response[i+4]+'</td>'+
				'<td>'+response[i+5]+'</td><td>'+response[i+6]+'</td><td><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button></td></tr>';
				$("#addrowstextfields tr:last").after(tr);
			} 
		}
	});
}

$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var id = $row[0].children[0].innerHTML;
	    var taxname = $row[0].children[1].innerHTML;
	    var description = $row[0].children[2].innerHTML;
	    var percent = $row[0].children[3].innerHTML;
	    var type = $row[0].children[4].innerHTML;
	    var on = $row[0].children[5].innerHTML;
	    var applicabledate = $row[0].children[6].innerHTML;
	    
	    $('#taxstructureid').val(id);
	    $("option").filter(function() {
			return $(this).text() == taxname;
		}).prop('selected', true);
	    $('#taxstructuredescription').val(description);
	    $('#taxstructurepercent').val(percent);
	    $('#taxtype').val(type);
	    $('#taxon').val(on);
	    $('#applicabledate').val(applicabledate);
	    document.getElementsByName("applicabledate")[0].setAttribute('min', applicabledate);
	    
	    $('#taxstructureid').attr('disabled','disabled');
		 $('#taxstructuredescription').attr('disabled','disabled');
		 $('#taxstructurepercent').attr('disabled','disabled');
		 $('#taxtype').attr('disabled','disabled');
		 $('#taxon').attr('disabled','disabled');
		 $('#taxId').attr('disabled','disabled');
	});
});
</script>