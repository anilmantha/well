<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.leonia.wellness.entity.Guestmaster" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
var i="${msg}";
var length=i.length;
if(length==0)
	{
	}
else
	{
	alert(i);
	location.href="registration";
	}
</script>
    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Registration</small> </h1>
          <!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
        </div>
      </div>
      
      <!-- Main Content Area -->
      <form action="customersave" method="post">


       ${msg}
	      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">New Client Details</h3>
            </div>
            <div class="panel-body">
              <div class="col-md-10">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                  <tbody>
                    <tr>
                      <td width="12%"><label>Title</label></td>
                      <td width="35%"><select required class="form-control" name="title" id="title">
                          <option value="">Select</option>
                          <c:forEach var="title" items="${title}">
                               <option value="${title.key}">${title.value}</option>
                          </c:forEach>
                        </select></td>
                      <td width="5%">&nbsp;</td>
                      <td width="12%"><label>Name</label></td></td>
                      <td width="36%"><input type="search" class="form-control" id="name" name="name" required></td>
                    </tr>
                   <!--  <tr>
                      <td><label>Middle Name</label></td>
                      <td><input type="search" class="form-control" id="middlename" name="middleName"></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>Last Name</label></td>
                      <td><input type="search" class="form-control" id="lastName" name="lastName"></td>
                    </tr> -->
                    <tr>
                      <td><label>Date of Birth</label></td>
                      <td><input type="date" class="form-control" id="dob" name="dob"  onchange="getAge()"></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>Gender</label></td>
                      <td><select class="form-control" name="gender" id="gender" required>
                          <option value="">Select</option>
                          <c:forEach var="gender" items="${gender}">
                               <option value="${gender.key}" >${gender.value}</option>
                          </c:forEach> 
                        </select></td>
                    </tr>
                    <tr>
                      <td><label>Mobile No.</label></td>
                      <td><input type="text" class="form-control" id="mobile" name="mobile" maxlength="10" onkeypress="return isNumber(event)" ></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>E-mail ID</label></td>
                      <td><input type="email" class="form-control" id="email" name="email"></td>
                    </tr>
                    <tr>
                      <td><label>Address</label></td>
                      <td><textarea class="form-control" rows="5" id="address" name="address" ></textarea></td>
                      <td width="5%">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td><label>City</label></td>
                      <td><select class="form-control" name="city" id="city" >
                      <option value="">Select</option>
                          <c:forEach var="city" items="${city}">
                          <option value="${city.key}">${city.value}</option>
                            </c:forEach>
                        </select></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>State</label></td>
                      <td><select class="form-control" id="state1" name="state" >
                          <option value="">Select</option>
                          <c:forEach var="state" items="${state}">
                          <option value="${state.key}">${state.value}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                      <td><label>Country</label></td>
                      <td><select class="form-control" id="country" name="country" >
                          <option value="">Select</option>
                          <c:forEach var="country" items="${country}">
                          <option value="${country.key}">${country.value}</option>
                            </c:forEach>
                        </select></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>Nationality</label></td>
                      <td><input type="search" class="form-control" id="nationality" name="nationality" ></td>
                    </tr>
                    <tr>
                      <td><label>Pin Code</label></td>
                      <td><input type="search" class="form-control" id="pincode" name="pincode" maxlength ="6" onkeypress="return isNumber(event)" ></td>
                      <td width="5%">&nbsp;</td>
                      <td><label>Age</label></td>
                      <td><input type="search" class="form-control" id="age" readonly></td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="col-md-2"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Other Details</h3>
            </div>
            <div class="panel-body">
              <div class="col-md-11">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                  <tbody>
                    <tr>
                      <td width="11%">&nbsp;</td>
                      <td width="31%">&nbsp;</td>
                      <td width="6%">&nbsp;</td>
                      <td width="9%"></td>
                      <td colspan="4"></td>
                    </tr>
                    <tr>
                      <td><label>Occupation</label></td>
                      <td><input type="search" class="form-control" id="occupation" name="occupation"></td>
                      <td width="6%">&nbsp;</td>
                      <td><label>Category</label></td>
                      <td colspan="4"><input type="search" class="form-control" id="catogery" name="catogery"></td>
                    </tr>
                    <tr>
                      <td><label>Segment</label></td>
                      
                      <td><select class="form-control" name="segmentmaster" id="segmentmaster" value="${segment.key}">
                         <option value="">Select</option>
                         <c:forEach var="segment" items="${segment}">
                          <option value="${segment.key}">${segment.value}</option>
                            </c:forEach> 
                        </select></td>
                      
                      <td width="6%">&nbsp;</td>
                      <td><label>Corporate</label></td>
                      <td colspan="4"><select class="form-control" name="corporate" id="corporate">
                         <option value="">Select</option>
                          <c:forEach var="corporate" items="${corporate}">
                          <option value="${corporate.key}">${corporate.value}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                       <td><label>Business Source</label></td>
                      <td><select class="form-control" name="businessSource" id="businessSource">
                        <option value="">Select</option>
                         <c:forEach var="businessSource" items="${businessSource}">
                          <option value="${businessSource.key}">${businessSource.value}</option>
                            </c:forEach>
                        </select></td>
                      <td width="6%">&nbsp;</td>
                      <td><label>Billing Instruction</label></td>
                      <td colspan="4"><select class="form-control" name="billingInstruction" id="billingInstruction">
                          <option value="">Select</option>
                          <c:forEach var="billingInformation" items="${billingInformation}">
                          <option value="${billingInformation.key}">${billingInformation.value}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                     <tr>
                      <td rowspan="2"><!-- <label>Pref.Contact</label> --></td>
                      <td rowspan="2"><!-- <label class="radio-inline">
                          <input type="radio" name="prefContact" id="prefContact" value="phone" checked="">
                          Phone </label>
                        <label class="radio-inline">
                          <input type="radio" name="prefContact" id="prefContact" value="mail" checked="">
                          E-mail </label> --></td>
                      <td width="6%" rowspan="2">&nbsp;</td>
                      <td rowspan="2"><label>Do Not Disturb</label></td>
                      <td width="4%" height="49"><label>From</label></td>
                      <td width="18%"><input type="search" class="form-control" id="dndfrom" name="dndfrom"></td>
                      <td width="3%"><label>To</label></td>
                      <td width="18%"><input type="search" class="form-control" id="dndto" name="dndto"></td>
                    </tr>
                   
               
                    <tr>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td><input type="submit" value="Registration"class="btn btn-primary btn-sm" onclick="form.action='onlyregistration';"></td>
                      <td colspan="4"><div class="pull-right">
                         <!--  <input type="submit" class="btn btn-primary btn-sm" id="appointment" value="Book appointment" onclick="appointment()">
                           <input type="submit" class="btn btn-primary btn-sm" id=register value="Register" onclick="register()"> -->
                          <button type="submit" class="btn btn-primary btn-sm">Book appointment</button>
                          
                        </div></td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="col-md-1"></div>
            </div>
          </div>
        </div>
      </div>
	</form>

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
    function getAge() {
    	var dateString =document.getElementById("dob").value;
    	var today = new Date();
        var birthDate = new Date(dateString);
        var age = today.getFullYear() - birthDate.getFullYear();
        var m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        document.getElementById("age").value=age+" years";
    }
    function getstatecountry(){
		var cityid = document.getElementById("city").value;
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
    
    
 
