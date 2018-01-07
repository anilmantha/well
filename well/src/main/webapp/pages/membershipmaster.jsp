<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
			<!--<ol class="bread crumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
		</div>
	</div>

	<!-- Main Content Area -->
	<c:if test="${msg!=null }">
					${msg}
					</c:if>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">MemberShip</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">


						<form action="membership" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="table table-no-bordered">
								<div style="display: none;">
									<input type="text" id="idname" name="idname">
								</div>

								<tbody>
									<tr>
										<td width="12%"><label>Member Name</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="membername" name="membername"
											required="required" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Address</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="address" name="address" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Phone No</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="phoneno" name="phoneno"
											maxlength="10" onkeypress="return isNumber(event)"
											required="required" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Email</label></td>
										<td class="col-md-3"><input type="email"
											class="form-control" id="email" name="email"
											required="required" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Black Listed</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="blacklisted" name="blacklisted" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Blacklist Reason</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="blacklistreason"
											name="blacklistreason" /></td>
									</tr>
									<tr>
										<td width="12%"><label>amount paid</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="amountpaid" name="amountpaid"
											onkeypress="return isNumber(event)" required="required" /></td>
										<td width="5%">&nbsp;</td>

										<td width="14%"><label>Valid From</label></td>
										<td class="col-md-3"><input type="date"
											class="form-control" id="validfrom" name="validfrom123"
											required="required" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Valid To</label></td>
										<td class="col-md-3"><input type="date"
											class="form-control" id="validto" name="validto123"
											required="required" /></td>
										<td width="5%">&nbsp;</td>


										<td width="14%"><label>Remarks</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="remarks" name="remarks" /></td>
									</tr>

									<tr>
										<td>
											<div class="col-md-8">
												<div class="pull-right">
													<td>
														<button type="submit" class="btn btn-primary btn-sm">SaveorUpdate</button>
													</td>
													<td>
														<button type="reset" class="btn btn-primary btn-sm">Clear</button>
													</td>

												</div>
											</div>
										</td>
									</tr>


								</tbody>
							</table>
						</form>

						<div class="row">
							<div class="col-md-6">

								<div class="form-group">
									<label for="email">Search MemberShip Name :</label> <input
										type="text" class="form-control" id="searchName"
										name="searchName" />

								</div>
								<button class="btn btn-primary btn-sm" id="search">Search</button>

							</div>
							<div class="col-md-3"></div>
						</div>

						<div style="display: none;" class="row">
							<div class="col-md-12">
								<div style="height: 90px; overflow-y: scroll;">
									<table class="table table-bordered table-hover" id="tblmember">
										<thead>
											<tr>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="list" items="${membervalue}">
												<td>${list.membershipid}</td>
												<td>${list.membername}</td>
												<td>${list.address}</td>
												<td>${list.phoneno}</td>
												<td>${list.email}</td>
												<td>${list.amountpaid}</td>
												<td>${list.validfrom}</td>
												<td>${list.validto}</td>
												<td>${list.remarks}</td>
												<td>${list.blacklisted}</td>
												<td>${list.blacklistreason}</td>


											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-3"></div>
						<div class="row">
							<div class="col-md-12">
								<div style="height: 250px; overflow-y: scroll;">
									<table class="table table-bordered table-hover" id="tblMain">
										<thead>
											<tr>
												<th>Member Name</th>
												<th>Address</th>
												<th>Phone No</th>
												<th>Email</th>
												<th>Amount Paid</th>
												<th>Valid From</th>
												<th>Valid To</th>
												<th>Remarks</th>
												<th>Black Listed</th>
												<th>Blacklist Reason</th>
											</tr>
										</thead>
										<tbody id="addrowdata">
											<c:forEach var="list" items="${membershipList}">
												<%-- window.location.replace('editmember?val=${list.membershipid}'); --%>
												<tr id="${list.membershipid}"
													ondblclick="searchmembers(this.id)">
													<td>${list.membername}</td>
													<td>${list.address}</td>
													<td>${list.phoneno}</td>
													<td>${list.email}</td>
													<td>${list.amountpaid}</td>
													<td>${list.validfrom}</td>
													<td>${list.validto}</td>
													<td>${list.remarks}</td>
													<td>${list.blacklisted}</td>
													<td>${list.blacklistreason}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>


					</div>
					<div class="col-md-3"></div>
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
	$(document).ready(function(){	
		var  res='${membervalue}';
		alert(res);
	 if(res==""||res==null){
		 
	 }
	 else
	 {
		var rowcount = document.getElementById("tblmember").rows.length;
		for (var i = 1; i < rowcount; i++) {
		var id=document.getElementById("tblmember").rows[i].cells.item(0).innerHTML;
		var membername=document.getElementById("tblmember").rows[i].cells.item(1).innerHTML;
		var address=document.getElementById("tblmember").rows[i].cells.item(2).innerHTML;
		var phoneno=document.getElementById("tblmember").rows[i].cells.item(3).innerHTML;
		var email=document.getElementById("tblmember").rows[i].cells.item(4).innerHTML;
		var amountpaid=document.getElementById("tblmember").rows[i].cells.item(5).innerHTML;
		var validfrom=document.getElementById("tblmember").rows[i].cells.item(6).innerHTML;
		var validto=document.getElementById("tblmember").rows[i].cells.item(7).innerHTML;
		var remarks=document.getElementById("tblmember").rows[i].cells.item(8).innerHTML;
		var blacklisted=document.getElementById("tblmember").rows[i].cells.item(9).innerHTML;
		var blacklistreason=document.getElementById("tblmember").rows[i].cells.item(10).innerHTML;
		
		}
		document.getElementById("idname").value=id;
		document.getElementById("membername").value=membername;
		document.getElementById("address").value=address;
		document.getElementById("phoneno").value=phoneno;
		document.getElementById("email").value=email;
		document.getElementById("amountpaid").value=amountpaid;
		document.getElementById("validfrom").value=validfrom;
		document.getElementById("validto").value=validto;
		document.getElementById("remarks").value=remarks;
		document.getElementById("blacklisted").value=blacklisted;
		document.getElementById("blacklistreason").value=blacklistreason;
	 }
		
	});
	 
</script>
<script>

	
$("#search").click(function(){
	var name =	document.getElementById("searchName").value;
	$("#addrowdata").html('<tr></tr>');
	$.ajax({
		type : "POST",
		async : false,
		
		url : "Search?membername="+name,
		success : function(response) { 
			 for(var i=0; i<response.length;i=i+11){
				var tr='<tr id="'+response[i+10]+'" ondblclick="searchmembers(this.id)" ><td>'+response[i]+'</td>'+
				'<td>'+response[i+1]+'</td>'+
				'<td>'+response[i+2]+'</td>'+
				'<td>'+response[i+3]+'</td>'+
				'<td>'+response[i+4]+'</td>'+
				'<td>'+response[i+5]+'</td>'+
				'<td>'+response[i+6]+'</td>'+
				'<td>'+response[i+7]+'</td>'+
				'<td>'+response[i+8]+'</td>'+
				'<td>'+response[i+9]+'</td></tr>';
				$('#addrowdata tr:last').after(tr);
			}
		}
	});
	});


	function searchmembers(id){
		window.location.replace('editmember?val='+id+'');
	}
</script>