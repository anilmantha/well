
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Bills List</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Bills</h3>
            </div>
            <c:if test="${msg!=null}">
              ${msg}
            </c:if>
            <div class="panel-body">
            <div class="row" >
                <div class="col-md-3">
                <div class="form-inline">
                <div class="form-group">
                  <label  for="email">Complaint By :</label>
                        <select  class="form-control" id="ga" name="ga">
                                                      <option value="">Select</option>
                                                      <option value="guest">Guest</option>
                                                       <option value="adminstration">Administration</option>
                                                       <option value="inhouseguest">In-House Guest</option>
                   </select>
                  </div>  
                  </div>
               </div>
             </div><br>
              <div class="row" id="guest">
                <div class="col-md-12">
                 <form class="form-inline" action="#">
                    <div class="form-group">
                     <table>
                      <tr>
                     <td> <label for="email">From Date :</label>
                      <input type="date" class="form-control" name="fromdate" id="fromdate" required="required"></td>
                       <td>&nbsp;&nbsp;</td>
                      <td><label for="email">To Date :</label>
                      <input type="date" class="form-control" name="todate" id="todate" required="required"><br></td>
                      <td>&nbsp;&nbsp;</td>
                      <td><label for="email">Guest Name :</label>
                      <input type="text" class="form-control" name="guestname" id="guestname"></td>
                       <td>&nbsp;&nbsp;</td>
                       <td><label></label>
                      <button type="button" id="billsearch" class="btn btn-primary btn-sm">Search</button></td>
                      </tr>
                      
                     </table> 
                      <!-- <label for="email">Search Bills  By Bill Date :</label>
                      <input type="date" class="form-control" name="billdate" id="billdate"> -->
                    </div>
                    <!-- <button type="button" id="billsearch" class="btn btn-primary btn-sm">Search</button> -->
                </form>
               
                <div class="col-md-3"></div>
             
              <hr>
            <!--  <div class="row"> -->
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Bill No</th>
                        <th>Bill Generated Date</th>
                        <th>Guest Id</th>
                        <th>Guest Name</th>
                        <th>Bill amount</th>
                      </tr>
                    </thead>
                    <tbody id="billslist123">
                     </tbody>
                  </table>
                </div>
              <!-- //</div> -->
               </div>
              </div>
              <div class="row" id="administartion">
              
              <form action="savemanualcomplaintbyadmin" method="post">
                <div class="col-md-12">
                <div class="table-responsive padding-10">
                  <table class="table table-no-bordered">
                    <tbody>
                    <tr>
                     <td class="col-md-2"><label for="email">Employee Name</label></td>
                        <td class="col-md-3"><select class="form-control" name="employeeId" id="employeeId" >
				               <option value="">Select</option>
				               <c:forEach var="employee" items="${employeeList}">
					                 <option value="${employee.key}">${employee.value}</option>
					           </c:forEach>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Employee Department</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="empdepartmentId" id="empdepartmentId"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Designation</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="designation" id="designation"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                      <tr >
                        <td class="col-md-2"><label for="email">Department Name</label></td>
                        <td class="col-md-3"><select class="form-control" name="department" id="department" >
				               <option value="">Select</option>
				               <c:forEach var="departmentmasterList" items="${departmentmasterList}">
					                 <option value="${departmentmasterList.departmentid}">${departmentmasterList.departmentname}</option>
					           </c:forEach>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Complaint</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="complaint" id="complaint"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Remarks</label></td>
                        <td class="col-md-3"><textarea class="form-control" rows="5" id="remarks" name="remarks"></textarea></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"></td>
                        <td class="col-md-3"><div class="pull-right"><button type="submit" class="btn btn-primary btn-sm"
											id="add">Submit</button> <input type="reset"
										class="btn btn-primary btn-sm" value="Clear" /></div></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     </tbody>
                  </table>
                </div>
                
                
                </div>
                </form>
              </div><br>
              <div class="row" id="inhouseguest">
              
              <form action="savemanualcomplaintbyinhouseguest" method="post">
                <div class="col-md-12">
                <div class="table-responsive padding-10">
                  <table class="table table-no-bordered">
                    <tbody>
                    <tr >
                        <td class="col-md-2"><label for="email">Guest Name</label></td>
                        <td class="col-md-3"><select class="form-control forChosen" name="inhouseguest1" id="inhouseguest1" >
				               <option value="">Select</option>
				               <c:forEach var="guest" items="${guestList}">
					                 <option value="${guest.key}">${guest.value}</option>
					           </c:forEach>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                     <tr >
                        <td class="col-md-2"><label for="email">Service Names</label></td>
                        <td class="col-md-3"><select class="form-control" name="serviceId" id="serviceId">
				               <option>Select</option>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                      <tr >
                        <td class="col-md-2"><label for="email">Department Name</label></td>
                        <td class="col-md-3"><select class="form-control" name="indepartment" id="indepartment" >
				               <option value="">Select</option>
				               <c:forEach var="departmentmasterList" items="${departmentmasterList}">
					                 <option value="${departmentmasterList.departmentid}">${departmentmasterList.departmentname}</option>
					           </c:forEach>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Complaint</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="incomplaint" id="incomplaint"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Remarks</label></td>
                        <td class="col-md-3"><textarea class="form-control" rows="5" id="inremarks" name="inremarks"></textarea></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"></td>
                        <td class="col-md-3"><div class="pull-right"><button type="submit" class="btn btn-primary btn-sm"
											id="add">Submit</button> <input type="reset"
										class="btn btn-primary btn-sm" value="Clear" /></div></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     </tbody>
                  </table>
                </div>
                
                
                </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
  
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 

<script type="text/javascript">
$(document).ready(function(){
    $("#administartion").hide();
    $("#guest").hide();
    $("#inhouseguest").hide();
 });
 </script>
<script type="text/javascript">
$("#ga").change(function(){
	var x =document.getElementById("ga").value;
	if(x=="guest"){
		$("#administartion").hide();
		$("#inhouseguest").hide();
		$("#guest").show();
	}else if(x=="adminstration"){
		 $("#guest").hide();
		 $("#inhouseguest").hide();
		$("#administartion").show();
	}
	else if(x=="inhouseguest"){
		$("#inhouseguest").show();
		$("#guest").hide();
		$("#administartion").hide();
	}else{
		$("#inhouseguest").hide();
		$("#guest").hide();
		$("#administartion").hide();
	}
 });
 </script>
<script type="text/javascript">
$("#billsearch").click(function(){
	
	var fromdate=document.getElementById("fromdate").value;
	var todate=document.getElementById("todate").value;
	var guestname=document.getElementById("guestname").value;
	
	if(fromdate == "" || fromdate == null)
		{
		alert("Select From date");
		document.getElementById('fromdate').focus();
		return false;
		}
	if(todate == "" || todate == null)
	{
	alert("Select To date");
	document.getElementById('todate').focus();
	return false;
	}
	
	$('#billslist123').html('<tr></tr>');
	$.ajax({
		type : "GET",
		asyn : false,
		url  : "billdates?fromdate="+fromdate+"&todate="+todate+"&guestname="+guestname,
		success : function(response){
			alert(response.length);
		for(var i=0;i<response.length;i=i+5){
		     var tr ='<tr><td id="billno"><a href="manualcomplaint?billno='+response[i]+'">'+response[i]+'</a></td>'
						   +'<td>'+response[i+1]+'</td>'
						   +'<td>'+response[i+4]+'</td>'
						   +'<td>'+response[i+3]+'</td>'
						   +'<td>'+response[i+2]+'</td></tr>';
		        $("#billslist123 tr:last").after(tr);
				
			}
		}
	});
});
</script>
<script type="text/javascript">
$("#employeeId").change(function(){
	var empId =document.getElementById("employeeId").value;
	
	$.ajax({
		type : "POST",
		asyn : false,
		url  : "empdep?empId="+empId,
		success  : function(response){
			alert(response);
			$("#empdepartmentId").val(response[0]);
			$("#designation").val(response[1]);
		}
		
	});
	
});
</script>
<script type="text/javascript">
$("#inhouseguest1").change(function(){
	var guestId =document.getElementById("inhouseguest1").value;
	alert(guestId);
	$.ajax({
		type : "GET",
		asyn : false,
		url  : "guestservices?guestId="+guestId,
		success  : function(response){
			$('#serviceId').empty();
			$('#serviceId').append($('<option/>').attr("value","").text("Select"));
			for (var i = 0, len = response.length; i < len; i=i+2) 
			{
				$('#serviceId').append($('<option/>').attr("value", response[i]).text(response[i+1]));
			}  
		}
		
	});
	
});
</script>