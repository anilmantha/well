
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

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">corporateList</h3>
				</div>
				<div class="panel-body">

					<div class="col-md-12">
						<form action="savecorporatemaster" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="table table-no-bordered">

								<div style="display: none;">
									<input type="text" id="idname" name="corpid" value="">
								</div>

								<tr>
									<td><label>CorporateType</label></td>
									<td><select required class="form-control"
										name="corporateType" id="corporateType" required="required"
										onselect="alert()">
											<option value="">Select</option>
											<c:forEach var="corporateTypeList"
												items="${corporateTypeList}">
												<option id="${corporateTypeList.corporatetype}"
													value="${corporateTypeList.corporatetypeid}">${corporateTypeList.corporatetype}</option>
											</c:forEach>
									</select></td>

									<td><label>Corporatename</label></td>
									<td><input type="text" class="form-control" required
										id="corporatename" name="corporatename" /></td>
									<td><label>Description</label></td>
									<td><input type="text" class="form-control" required
										id="description" name="description" /></td>
									<td><label>Contactperson1</label></td>
									<td><input type="text" class="form-control" required
										id="contactperson1" name="contactperson1" /></td>


								</tr>
								<tr>
									<td width=""><label>Address1</label></td>
									<td class=""><input type="text" class="form-control"
										required id="address1" name="address1" /></td>
									<td width=""><label>Phone1</label></td>
									<td class=""><input type="text" class="form-control"
										required id="phone1" name="phone1" maxlength="10"
										onkeypress="return isNumber(event)" /></td>
									<td width=""><label>Fax1</label></td>
									<td class=""><input type="text" class="form-control"
										required id="fax1" name="fax1"
										onkeypress="return isNumber(event)" /></td>
									<td width=""><label>Email Id1</label></td>
									<td class=""><input type="email" class="form-control"
										required id="email1" name="email1" /></td>

								</tr>
								<tr>
									<td width=""><label>Contact Person2</label></td>
									<td class=""><input type="text" class="form-control"
										id="contactperson2" name="contactperson2" /></td>
									<td width=""><label>Address2</label></td>
									<td class=""><input type="text" class="form-control"
										required id="address2" name="address2" /></td>
									<td width=""><label>Phone2</label></td>
									<td class=""><input type="text" class="form-control"
										id="phone2" name="phone2" maxlength="10"
										onkeypress="return isNumber(event)" /></td>
									<td width=""><label>Fax2</label></td>
									<td class=""><input type="text" class="form-control"
										id="fax2" name="fax2" onkeypress="return isNumber(event)" /></td>

								</tr>
								<tr>
									<td width=""><label>Email Id2</label></td>
									<td class=""><input type="email" class="form-control"
										id="email2" name="email2" /></td>
									<td width=""><label>Contact Person3</label></td>
									<td class=""><input type="text" class="form-control"
										id="contactperson3" name="contactperson3" /></td>
									<td width=""><label>Address3</label></td>
									<td class=""><input type="text" class="form-control"
										required id="address3" name="address3" /></td>
									<td width=""><label>Phone3</label></td>
									<td class=""><input type="text" class="form-control"
										id="phone3" name="phone3" maxlength="10"
										onkeypress="return isNumber(event)" /></td>


								</tr>
								<tr>
									<td width=""><label>Fax3</label></td>
									<td class=""><input type="text" class="form-control"
										id="fax3" name="fax3" onkeypress="return isNumber(event)" /></td>
									<td width=""><label>Email Id3</label></td>
									<td class=""><input type="email" class="form-control"
										id="email3" name="email3" /></td>
									<td width=""><label>Remarks</label></td>
									<td class=""><input type="text" class="form-control"
										id="remarks" name="remarks" /></td>
									<td width=""><label>BlackListreason</label></td>
									<td class=""><input type="text" class="form-control"
										id="blacklistreason" name="blacklistreason" /></td>

								</tr>
								<tr>
									<td>

										<button type="submit" class="btn btn-primary btn-sm">SaveorUpdate</button>
									</td>

									<td>
										<button type="submit" class="btn btn-primary btn-sm">Clear</button>
									</td>

								</tr>

							</table>

						</form>



						<div class="row">
							<div class="col-md-6">

								<div class="form-group">
									<label for="email">Search Corporate Name :</label> <input
										type="text" class="form-control" id="searchName"
										name="searchName" />

								</div>
								<button class="btn btn-primary btn-sm" id="search">Search</button>
							</div>
							<div class="col-md-3"></div>
						</div>
						<div style="display: none;" class="row">
							<div class="col-md-12">
								<div style="height: 90px; overflow-y: scroll;"></div>
							</div>
						</div>
						<div class="col-md-3"></div>
						<div class="row">
							<div class="col-md-12">
								<div style="height: 250px; overflow-y: scroll;">
									<table class="table table-bordered table-hover" id="tblMain">
										<thead>
											<tr>
												<th>Corporate Type</th>
												<th>Corporatename</th>
												<th>Description</th>
												<th>Contactperson1</th>
												<th>Address1</th>
												<th>Phone1</th>
												<th>Fax1</th>
												<th>Email Id1</th>
												<th>Contact Person2</th>
												<th>Address 2</th>
												<th>Phone2</th>
												<th>Fax 2</th>
												<th>Email Id2</th>
												<th>Contact Person 3</th>
												<th>Address 3</th>
												<th>Phone3</th>
												<th>Fax 3</th>
												<th>Email Id3</th>
												<th>remarks</th>
												<th>blacklist</th>
												<th>blacklistreason</th>

											</tr>
										</thead>
										<tbody id="addrowdata">
											<c:forEach var="list" items="${corporateList}">
												<tr id="${list.corporateid}"
													ondblclick="searchmembers(this.id)">
													<td>${list.getCorporatetypemaster().getCorporatetype()}</td>
													<td>${list.corporatename}</td>
													<td>${list.description}</td>
													<td>${list.contactperson1}</td>
													<td>${list.address1}</td>
													<td>${list.phone1}</td>
													<td>${list.fax1}</td>
													<td>${list.email1}</td>
													<td>${list.contactperson2}</td>
													<td>${list.address2}</td>
													<td>${list.phone2}</td>
													<td>${list.fax2}</td>
													<td>${list.email2}</td>
													<td>${list.contactperson3}</td>
													<td>${list.address3}</td>
													<td>${list.phone3}</td>
													<td>${list.fax3}</td>
													<td>${list.email3}</td>
													<td>${list.remarks}</td>
													<td>${list.blacklist}</td>
													<td>${list.blacklistreason}</td>
													<td style="display: none;">${list.corporateid}</td>
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
		var  res='${editcorporateList}';
		alert(res);
	 if(res==""||res==null){
		 
	 }
	 else
	 {
		
	 }
		
	});
	 
</script>
<script>

	
$("#search").click(function(){
	var name =	document.getElementById("searchName").value;
	$("#addrowdata").html('<tr></tr>');
	alert("name::::::::::"+name);
	$.ajax({
		type : "POST",
		async : false,
		url : "searchcorporatemaster?corporatename="+name,
		success : function(response) { 
			alert(response);
			 for(var i=0; i<response.length;i=i+22){
				var tr='<tr id="'+response[i+21]+'" ondblclick="searchmembers(this.id)" ><td>'+response[i]+'</td>'+
				'<td>'+response[i+1]+'</td>'+
				'<td>'+response[i+2]+'</td>'+
				'<td>'+response[i+3]+'</td>'+
				'<td>'+response[i+4]+'</td>'+
				'<td>'+response[i+5]+'</td>'+
				'<td>'+response[i+6]+'</td>'+
				'<td>'+response[i+7]+'</td>'+
				'<td>'+response[i+8]+'</td>'+
				'<td>'+response[i+9]+'</td>'+
				'<td>'+response[i+10]+'</td>'+
				'<td>'+response[i+11]+'</td>'+
				'<td>'+response[i+12]+'</td>'+
				'<td>'+response[i+13]+'</td>'+
				'<td>'+response[i+14]+'</td>'+
				'<td>'+response[i+15]+'</td>'+
				'<td>'+response[i+16]+'</td>'+
				'<td>'+response[i+17]+'</td>'+
				'<td>'+response[i+18]+'</td>'+
				'<td>'+response[i+19]+'</td>'+
				'<td>'+response[i+20]+'</td>'+
				'<td style="display:none;">'+response[i+21]+'</td></tr>';
				$('#addrowdata tr:last').after(tr);
			}
		}
	});
	});
	function searchmembers(id){
		var cid = $('#'+id)[0].children[0].innerHTML;
		$("#"+cid).prop('selected',true);
		$('#corporatename').val($('#'+id)[0].children[1].innerHTML);
		$('#description').val($('#'+id)[0].children[2].innerHTML);
		$('#contactperson1').val($('#'+id)[0].children[3].innerHTML);
		$('#address1').val($('#'+id)[0].children[4].innerHTML);
		$('#phone1').val($('#'+id)[0].children[5].innerHTML);
		$('#fax1').val($('#'+id)[0].children[6].innerHTML);
		$('#email1').val($('#'+id)[0].children[7].innerHTML);
		$('#contactperson2').val($('#'+id)[0].children[8].innerHTML);
		$('#address2').val($('#'+id)[0].children[9].innerHTML);
		$('#phone2').val($('#'+id)[0].children[10].innerHTML);
		$('#fax2').val($('#'+id)[0].children[11].innerHTML);
		$('#email2').val($('#'+id)[0].children[12].innerHTML);
		$('#contactperson3').val($('#'+id)[0].children[13].innerHTML);
		$('#address3').val($('#'+id)[0].children[14].innerHTML);
		$('#phone3').val($('#'+id)[0].children[15].innerHTML);
		$('#fax3').val($('#'+id)[0].children[16].innerHTML);
		$('#email3').val($('#'+id)[0].children[17].innerHTML);
		$('#remarks').val($('#'+id)[0].children[18].innerHTML);	
		$('#blacklisted').val($('#'+id)[0].children[19].innerHTML);
		$('#blacklistreason').val($('#'+id)[0].children[20].innerHTML);
		$('#idname').val($('#'+id)[0].children[21].innerHTML);
		}	
		</script>