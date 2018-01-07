<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
	<c:set var="string" value="${message}" />
	<c:choose>
		<c:when test="${fn:contains(string, 'not')}">
			<div class="row">
				<div class="col-lg-12 col-md-offset-5">
					<h4>
						<span class="label label-danger" id="message"><c:out
								value="${message}" /></span>
					</h4>
				</div>
			</div>
		</c:when>
		<c:when test="${message==null}">
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-lg-12 col-md-offset-5">
					<h4>
						<span class="label label-success" id="message"><c:out
								value="${message}" /></span>
					</h4>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<br>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Staff</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form action="saveStaff" method="post" enctype="multipart/form-data">
						<table class="table table-no-bordered">
							<tbody>
									<tr>
                 						<td width="12%"><label>Staff Name</label></td>
                 						 <td width="35%">
					                    <input type="text" class="form-control" id="staffname" name="staffname" required="required"></td>
					                    <td width="5%">&nbsp;</td>
					                    <td width="12%"><label>Email</label></td>
					                    <td width="36%"><input type="email" class="form-control" id="email" name="email" required="required"></td>
					                </tr>
                 					<tr>
										<td><label>Gender</label></td>
										<td>
											<select class="form-control" name="genderid" id="genderid">
												<option>Select Gender</option>
												<c:forEach var="genderlist" items="${gendersList}">
													<option value="${genderlist.dropdowndetailsid}">${genderlist.description}</option>
												</c:forEach>
											</select>
										</td>
										 <td width="5%">&nbsp;</td>
										<td><label>Date of Birth</label></td>
                      					<td><input type="date" class="form-control" id="dateofbirth" name="dateofbirth" required="required"></td>
                      					</tr>
                 						<tr>
										<td width="14%"><label>Mobile</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="mobile" name="mobile"
											maxlength="10" onkeypress="return isNumber(event)" /></td>

										<td width="5%">&nbsp;</td>
										<td width="12%"><label>Address</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="address" name="address" /></td>
										
									</tr>
									
                
                    				<tr>
										<td><label>City</label></td>
										<td><select class="form-control forChosen" onchange="getstatecountry()" name="cityid"
											id="cityid">
												<option>Select City</option>
												<c:forEach var="citylist" items="${cityList}">
													<option value="${citylist.cityid}">${citylist.cityname}</option>
												</c:forEach>
											</select>
										</td>
										  <td width="5%">&nbsp;</td>
										  <td><label>State</label></td>
										<td><select class="form-control" name="stateid"
											id="stateid">
												<option>Select State</option>
												<c:forEach var="statelist" items="${stateList}">
													<option value="${statelist.stateid}">${statelist.statename}</option>
												</c:forEach>
											</select>
										</td>
										</tr>
										 <tr>
										<td><label>Country</label></td>
										<td><select class="form-control" name="countryid"
											id="countryid">
												<option>Select Country</option>
												<c:forEach var="countrylist" items="${countryList}">
													<option value="${countrylist.countryid}">${countrylist.countryname}</option>
												</c:forEach>
											</select>
										</td>
										  <td width="5%">&nbsp;</td>
										  <td><label>Pin Code</label></td>
                      <td><input type="text" maxlength="6" onkeypress="return isNumber(event)" class="form-control" id="pincode" name="pincode"></td>
                      </tr>
                      <tr>
                      <td><label>Nationality</label></td>
                      <td><input type="text" class="form-control" id="nationality" name="nationality"></td>
                       <td width="5%">&nbsp;</td>
                        <td><label>Designation</label></td>
                      <td><input type="text" class="form-control" id="desgination" name="desgination"></td>
                      </tr>
                       <tr>
										<td><label>Department</label></td>
										<td><select class="form-control" name="departmentid"
											id="departmentid">
												<option>Select Department</option>
												<c:forEach var="departmentlist" items="${departmentList}">
													<option value="${departmentlist.departmentid}">${departmentlist.departmentname}</option>
												</c:forEach>
											</select>
										</td>
										 <td width="5%">&nbsp;</td>
										 <td><label>WeekOff</label></td>
                      					 <td><select class="form-control" name="weekoff"
											id="weekoff">
												<option>Select Weekoff</option>
												<option value="1">Sunday</option>
												<option value="2">Monday</option>
												<option value="3">Tuesday</option>
												<option value="4">Wednesday</option>
												<option value="5">Thursday</option>
												<option value="6">Friday</option>
												<option value="7">Satuarday</option>
											</select>
										 </td>
										
			                      </tr>
			                      <tr>
			                      	<td><label>File input</label></td>
								    <td><input type="file" id="staffphotos" name="staffphotos">
								    </td>
			                      </tr> 
			                      <tr>
										<td>
											<div class="col-md-8"></div>
												<div class="pull-right"></div>
													<td>
														<button type="submit" class="btn btn-primary btn-sm">Save</button>
													</td>
													</tr>
												</tbody>
												</table>
						</form>
						</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function isNumber(evt) {

		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode<48 || charCode>57)) {
			return false;
		}
		return true;
	}

	setTimeout(function() {
		$('#message').fadeOut('fast');
	}, 2000);
	
	function getstatecountry(){
		var cityid = document.getElementById("cityid").value;
		$.ajax({
		type : "POST",
		async : false,
		url : "getStateCountryByCity?cityid="+cityid,
		success : function(response) {
				var i = 0;
			 	 $("option").filter(function() {
						return $(this).text() == response[i];
					}).prop('selected', true);
					
				$("option").filter(function() {
					return $(this).text() == response[i+1];
				}).prop('selected', true);
     }
	}); 
	}
</script>




<!-- Main Content Area -->
<!-- /.container-fluid -->

