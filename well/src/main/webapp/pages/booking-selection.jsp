<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <div class="container-fluid"> 
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Appointment Booking</small> </h1>
        </div>
      </div>
      
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Booking Selection</h3>
            </div>
            <div class="panel-body">
           <%--  <form action="makeAppointment" method="post" onsubmit="return validations();"> --%>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                <c:set var="status" value="${param.id}"></c:set>
                   <input type="hidden" value="${status}" name="id" id="custid" />
                    <tr>
                    
                        <td class="col-md-3"><label>Name</label></td>
                        <td class="col-md-3"><input type="search" class="form-control" id="email" value="${name}" readonly="readonly" ></td>
                        <td class="col-md-2"><label>No:of Visits</label></td>
                        <td class="col-md-2"><label><span style="color: #e47676;" id="visits"></span></label></td>
                        <td class="col-md-2"></td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Select Appointment Time</label></td>
                        <td class="col-md-3">
                        	<select class="form-control" id="appointTime" name="arrivaltime">
			                	<option value="">Select Time</option>
			                	<option value="08:00:00">08:00:00</option>
			                    <option value="09:00:00">09:00:00</option>
			                    <option value="10:30:00">10:30:00</option>
			                    <option value="11:00:00">11:00:00</option>
			                    <option value="12:00:00">12:00:00</option>
		                	</select>
		                </td>
                        <td class="col-md-2"><label>Average Revenue</label></td>
                        <td class="col-md-2"><label><span style="color: #e47676;" id="revenue"></span></label></td>
                       <td class="col-md-2"><label><span style="color: #E51C1C;" id="textContent"></span></label></td>
                       <!--  <td class="col-md-2"><img src="/wellness/src/main/webapp/resources/images/HighSpender.png" /></td> -->
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Select Date</label></td>
                        <td class="col-md-3"><input type="date"  class="form-control" id="date" name="arrivaldate"></td>
                        <td class="col-md-2"><label>Last Visit Date</label></td>
                        <td class="col-md-2"><label><span style="color: #e47676;" id="lastvisitdate"></span></label></td>
                        <td class="col-md-2"></td>
                    </tr>
                    
                    
                    <tr>
                        <td class="col-md-3"><label>Select services/packages</label></td>
                        <td class="col-md-3"><select class="form-control" id="service">
                	<option value="">Select</option>
                    <option value="service">Service</option>
                    <option value="package">Package</option>
                  </select></td>
                        <td class="col-md-2"><label>Last Visit Complaints</label></td>
                        <td class="col-md-2" rowspan="3" colspan="2"><label><span style="color: #e47676;" id="complaints"></span></label></td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Available Services Packages</label></td>
                        <td class="col-md-3">
                        	<select class="form-control" id="package"  name="servicename" multiple>
                            <option value="">Select</option>
                          
                  </select>
                        </td>
                        
                        <td id="guestid" hidden=""></td>
						<td id="name" hidden=""></td>
						<td id="corporateid" hidden=""></td>
						<td id="segmentid" hidden=""></td>
						<td id="businesssourceid" hidden=""></td>
						<td id="billinginstructionid" hidden=""></td>
                        
                        <td class="col-md-2"></td>
                    </tr>
                    <tr>
                    	<td class="col-md-3"><label>Queries form Guest (If Any)</label></td>
                        <td class="col-md-3" colspan="2"><textarea class="form-control" id="queries" rows="5"></textarea></td>
                        <td class="col-md-2"></td>
                        <td class="col-md-2"></td>
                    </tr>
                    <tr>
                    	<td class="col-md-3"></td>
                        <td class="col-md-3"><button type="button" id="addButton" class="btn btn-primary btn-sm" >Add</button></td>
                        <td class="col-md-2"></td>
                        <td class="col-md-2"></td>
                        <td class="col-md-2"></td>
                    </tr>
                </table>
				<div class="row">
		          <div class="table-responsive padding-10">
		           <table class="table table-bordered table-hover delClass" id="ttttable"></table>
		           <div id="serviceStockList"></div>
		           <div class="pull-right">
		           <!-- <button type="submit" class="btn btn-primary btn-sm" value="dad" id = "doctorAdvice" name="doctorapoint">Doctor's Advice</button>
		           <button type="submit" class="btn btn-primary btn-sm" value="apb" id = "saveAppointment" name="newappoint">New Appointment</button> -->
		           <button type="button" class="btn btn-primary btn-sm" onclick="bookingAppointment()">Book Appointment</button>
		           </div>
		          </div>
              </div>
		    </div>
         </div>
       </div>
     </div>
   </div>


<script>
$(document).ready(function() {
	var custid = $('#custid').val();
	$.ajax({
		type : "GET",
		async : false,
		url : "getGuestInfo?custid="+custid,
		success : function(response) {
		
			document.getElementById("guestid").innerHTML = response[0];
			document.getElementById("name").innerHTML = response[1];
			document.getElementById("corporateid").innerHTML = response[2];
			document.getElementById("segmentid").innerHTML = response[3];
			document.getElementById("businesssourceid").innerHTML = response[4];
			document.getElementById("billinginstructionid").innerHTML = response[5];
			
		
		}
	});
});
/* 
$(".savebutton").click(function() {
	
	var result = validations();
	
	if(result > 0)
	{

		var btnid = $(this).attr('id'); 
		var ObjArry=[];
		var servarry = {};
		var guestarry = {};
		var staffarry = {};
		var roomarry = {};
		var corparry = {};
		var segmentarry = {};
		var bussourcearry = {};
		var billinstarry = {};
		
		
		
		var arrivaldate = $('#date').val();
		var arrivaltime = $('#appointTime').val();
		
		var d = document.getElementById('package');
		var serviceid= d.options[d.selectedIndex].id;
		
		
		var guestid = $('#custid').val();
		var staffid = $('#staff').val();
		var roomid = $('#rooms').val();
		var name = document.getElementById("name").innerHTML;
		var corporateid = document.getElementById("corporateid").innerHTML;
		var segmentid = document.getElementById("segmentid").innerHTML;
		var businesssourceid = document.getElementById("businesssourceid").innerHTML;
		var billinginstructionid = document.getElementById("billinginstructionid").innerHTML;
		var starttime =  $('#startTime').html();
		
		servarry = {
				serviceid		:serviceid
				}
		
		guestarry = {
				guestid		:guestid
				}
		
		staffarry = {
				staffid		:staffid
				}
		
		roomarry = {
				roomid			:roomid
				}
		
		corparry = {
				corporateid		:corporateid
				}
		
		segmentarry = {
				segmentid	:segmentid
				}
		
		bussourcearry = {
				businesssourceid	:businesssourceid
				}
		
		billinstarry = {
				billinginstructionid	:billinginstructionid
				}
		
		
		var ssetObj={};
		ssetObj={
				arrivaldate     :	arrivaldate,
				arrivaltime		:	arrivaltime,
				guestmaster		:	guestarry,
				staffmaster		:	staffarry,
				roommaster		:	roomarry,
				corporatemaster	:	corparry,
				segmentmaster 	:	segmentarry,
				businesssourcemaster	:	bussourcearry,
				billinginstructionmaster	:	billinstarry,
				servicemaster   :	servarry
		}
		
		ObjArry.push(ssetObj);
		
		$.ajax({
			type : "POST", 
			url : "makeAppointment?custid="+guestid+'&btnid='+btnid,
			contentType : 'application/json',
			data : JSON.stringify(ObjArry),
			success : function(response) {
				if(btnid == "doctorAdvice"){
					 alert("Appointment Sent For Doctor Advice Sucessfully");
				}
				else{
					alert("Appointment Saved Sucessfully");
				}
			   
			}
		});

	}
	else
	{
		alert("Please Check the Stock, Staff and Room Availability before Booking.!");
	}
});
 */
$("#service").change(function(){
	
	var val = $('#service').val();
	if(val == "service")
	{
		$.ajax({
			type : "GET",
			async : false,
			url : 'getServices',
			success : function(response) {
				$('#package').empty();
		        for (var i = 0, len = response.length; i < len; ++i) 
		        {
		            var prj = response[i];
		            $('#package').append($('<option/>').attr("id", prj[0]).attr("value", prj[1]).text(prj[1]));
		        } 
			}
		});
	}
	else if(val == "package")
	{
		$.ajax({
			type : "GET",
			async : false,
			url : 'getPackages',
			success : function(response) {
				$('#package').empty();
		        for (var i = 0, len = response.length; i < len; ++i) 
		        {
		            var prj = response[i];
		            $('#package').append($('<option/>').attr("id", prj[0]).attr("value", prj[1]).text(prj[1]));
		        } 
			}
		});
	}
	else
	{
		$('#package').empty();
	}
});

$("#addButton").click(function(){
	var strTime = $('#appointTime').val();
	var arvDate = $('#date').val();
	var srvOrPkg = $('#service').val();
	var service = $("#package").val();
	var srvNameArr = [];
	var srvIdArr = [];
	var s1 = document.getElementById('package');
	for (var i = 0; i < s1.options.length; i++) {
		if(s1.options[i].selected){
			srvIdArr.push(s1.options[i].id);
			srvNameArr.push(s1.options[i].value);
		}
	}
	if(strTime == null || strTime == "")
	{
		alert("Please Select Start Time");
		$('#appointTime').focus();
	}
	else if(arvDate == null || arvDate == "")
	{
		alert("Please Select Date of Appointment");
		$('#date').focus();
	}
	else if(srvOrPkg == null || srvOrPkg == "")
	{
		alert("Please Select Service Or Package");
		$('#service').focus();
	}
	else if(service == null || service == "")
	{
		alert("Please Select Atleast One Service");
		$('#package').focus();
	}
	else
	{
		var res = parseInt(1, 10);
		var s = document.getElementById('package');
		var servname = s.options[s.selectedIndex].text;
		var servid = s.options[s.selectedIndex].id; 
		if(rowsArr.length > 0)
		{
			var a = $('#srvStart'+rowsArr[rowsArr.length-1]).html();
			var b = $('#srvEnd'+rowsArr[rowsArr.length-1]).html();
			strTime = b;
		}
		 $.ajax({
			type : "POST",
			async : false,
			url : 'allServiceDetails?idArr='+srvIdArr+"&nameArr="+srvNameArr+"&srvOrPkg="+srvOrPkg+"&time="+strTime,
			success : function (response){
				var i = 0;
				var xf = 0;
				var oldABC = abcVal;
				for(i = 0; i < response.length; i++)
				{
					var service = response[i];
					var xs = "<tr id=\"heading"+abcVal+"\" style=\"border: 1px solid #ddd; background-color: transparent; background: #ddd;\"><th style=\"display: none;\"></th><th></th><th style=\"display: none;\">Service ID</th><th>Name</th><th>Rooms</th><th>Staff</th><th>Start Time</th><th>Preparation Time</th><th>Service Time</th><th>Wait Time</th><th>Cleaning Time</th><th>End Time</th><th>Price</th><th>Tax</th><th>DoctorAdvice</th><th></th></tr><tr id=\"services"+abcVal+"\">";
						for(var a = 0; a < service.length; a++)
						{
							if(xf == 0)
							{
								if(a == 0)
								{
									xs += "<td id=\"pkgId"+abcVal+"\" style=\"display: none;\">"+service[a]+"</td>";
								}
								else if(a == 1)
								{
									if(srvOrPkg == "package")
										xs += "<td id=\"pkgName"+abcVal+"\" style=\"color: red;\">"+service[a]+"</td>";
									else
										xs += "<td id=\"pkgName"+abcVal+"\">"+service[a]+"</td>";
								}
								else if(a == 2)
								{
									xs += "<td id=\"srvId"+abcVal+"\" style=\"display: none;\">"+service[a]+"</td>";
									var index = serviceIdArr.indexOf(parseInt(service[a], 10));
								    if(index < 0)
								    {
										packageIdArr.push(service[0]);
								    	serviceIdArr.push(service[a]);
								    }
								    else
								    {
								    	xf = 1;
								    	break;
								    }
								}
								else if(a == 3)
									xs += "<td id=\"srvName"+abcVal+"\">"+service[a]+"</td>";
								else if(a == 4)
								{
									var serviceRooms = service[a];
									xs += "<td><select class=\"form-control\" id=\"room"+abcVal+"\">";
									for(var s = 0; s < serviceRooms.length; s++)
									{
										xs += "<option value=\""+serviceRooms[s].roomno+"\" id=\""+serviceRooms[s].roomid+"\">"+serviceRooms[s].roomno+"</option>"
									}
									xs += "</select></td>";
								}
								else if(a == 5)
								{
									var serviceStaff = service[a];
									xs += "<td><select class=\"form-control\" id=\"staff"+abcVal+"\">";
									for(var s = 0; s < serviceStaff.length; s++)
									{
										xs += "<option value=\""+serviceStaff[s].staffname+"\" id=\""+serviceStaff[s].staffid+"\">"+serviceStaff[s].staffname+"</option>"
									}
									xs += "</select></td>";
								}
								else if(a == 6)
									xs += "<td id=\"srvStart"+abcVal+"\">"+service[a]+"</td>";
								else if(a == 11)
									xs += "<td id=\"srvEnd"+abcVal+"\">"+service[a]+"</td>";
								else if(a == service.length-1)
								{
									var serviceStocks = service[a];
									xs += "</tr><tr id=\"stock"+abcVal+"\"><td colspan=\"13\"><table class=\"table table-bordered table-hover\"><tr><th>Stock Id</th><th>Stock Name</th>";
									xs += "<th>Warning Level</th><th>Re-Order Level</th><th>Usage</th><th>Cost</th><th>Available</th><th>Availability After Usage</th></tr>";
									for(var s = 0; s < serviceStocks.length; s++)
									{
										var stk = serviceStocks[s];
										xs += "<tr>";
										for(var t = 0; t < stk.length; t++)
										{
											xs += "<td>"+stk[t]+"</td>"
										}
										xs += "</tr>";
									}
									xs += "</table></td></tr>";
								}
								else
								{
									xs += "<td>"+service[a]+"</td>";
									if(a == service.length-2)
									{
										xs += "<td><select class=\"form-control\" id=\"doctorAdvice"+abcVal+"\">";
										xs += "<option value=\"false\">false</option>";
										xs += "<option value=\"true\">true</option>";
										xs += "</select></td>";
										xs += "<td rowspan=\"2\" id=\"delRow\"><button type=\"button\" value="+abcVal+" >X</button></td>";
									}
								}
							}
						}
						if(xf == 0)
						{
							$('#ttttable').append(xs);
							rowsArr.push(abcVal);
							abcVal = abcVal+1;
						}
				}
				if(xf == 0)
				{}
				else
				{
					alert("Selected Service cannot be Selected Again.!");
					var dval = abcVal-oldABC;
					for(var i = 1; i <= dval; i++)
					{
						$('#heading'+(abcVal-1)).remove();
					    $('#services'+(abcVal-1)).remove();
					    $('#stock'+(abcVal-1)).remove();
						rowsArr.splice(rowsArr.length-1, 1);
						serviceIdArr.splice(serviceIdArr.length-1, 1);
						abcVal = abcVal - 1;
					}
				}
			 }
		 });
	}
}); 


function validations()
{
	var res = parseInt(val, 10);
	var arvdate = $('#date').val();
	var startTime = $('#startTime').html();
	var endTime = $('#endTime').html();
	var staff = $('#staff').val();
	var room = $('#rooms').val();
	
	if(room == null || room == "")
	{
		alert("Please Select Room");
		$('#rooms').focus();
	}
	else if(staff == null || staff == "")
	{
		alert("Please Select Staff");
		$('#staff').focus();
	}
	
	else
	{
		$.ajax({
	 		type : "POST",
	 		async : false,
	 		url : 'appointmentConditions?r='+room+'&st='+startTime+'&arv='+arvdate+'&staff='+staff,
	 		success : function(response) { 
	 			if(response == 1)
	 			{
	 				alert("Selected Room is Currently in Use. So Please Select Other Room");
	 				res = -1;
	 			}
	 			else if(response == 2)
	 			{
	 				alert("Selected Staff is Currently in Use. So Please Select Other Staff Member");
	 				res = -1;
	 			}
	 			else
	 			{
	 				//alert("Success.!");
	 				res = 1;
	 			}
	 		}
	 	});
	}	
	
	if(val > 0 && res > 0)
		return 1;
	else
		return -1;
	
}


$(document).ready(function(){
	window.val = parseInt(1, 10);
	window.abcVal = parseInt(0, 10);
	window.rowsArr = [];
	window.serviceIdArr = [];
	window.packageIdArr = [];
	window.appointment = 0;
	$("#table").hide();
	var guestId = '${param.id}';
	$.ajax({
 		type : "POST",
 		async : false,
 		url : 'appointmentGuestDetails?gid='+guestId,
 		success : function(response) { 
 			var visits = response[0];
 			var revenue = response[1];
 			var lastvisitdate = response[2];
 			var complaints = response[3];
 			
 			
 			
 			if(visits == null)
 				visits = 0;
 			if(revenue == null)
 				revenue = "0.00";
 			$('#visits').html(visits);
 			$('#revenue').html(revenue);
 			$('#lastvisitdate').html(lastvisitdate);
 			
 			$('#textContent').html("REGULAR GUEST");
 			
 			if(revenue >= 5000){
 				
 				$('#textContent').html("HIGH SPENDER");
 			}
 			
            if(visits == 0){
 				
 				$('#textContent').html("NEW GUEST");
 			}
 			
            if(visits == 1){
 				
 				$('#textContent').html("SECOND VISIT");
 			} 
            
           if(complaints != 0){
 				
 				$('#textContent').html("LOW FEEDBACK");
 			}
 			
 			$('#complaints').html(complaints);
 		}
 	});
	
}); 

$('.delClass').on('click','button', function() {
	
    var r= $(this).attr('value');
    r = parseInt(r, 10);
    //alert("!#!#!#!#!#!#!#!#!#!#!#!#!#"+r);
    var pkgId = $('#pkgId'+r).html();
    var pkgName = $('#pkgName'+r).html();
    var srvName = $('#srvName'+r).html();
        
    if(pkgId == null || pkgId == "")
    {
    	if(confirm("Confirm Deleting \""+srvName+"\" Service.?"))
    	{
        	var srvId = $('#srvId'+r).html();
        	var prev = rowsArr[rowsArr.indexOf(r)-1];
        	var now = rowsArr[rowsArr.indexOf(r)];
        	prevSrvStart = $('#srvStart'+prev).html();
        	prevSrvEnd = $('#srvEnd'+prev).html();
        	nowSrvStart = $('#srvStart'+now).html();
        	nowSrvEnd = $('#srvEnd'+now).html();

        	if(rowsArr.indexOf(r) == 0)
        	{
        		prevSrvEnd = $('#srvStart'+now).html();
        	}
        	
            for(var xsd = rowsArr.indexOf(r); xsd < rowsArr.length; xsd++)
            {
            	if(xsd == rowsArr.length-1)
            	{
            		break;
            	}
            	else
            	{
	            	//alert("!#!#!#"+xsd+"!#!#!#!#!#!#!#!#"+rowsArr[xsd]);
	            	var next = rowsArr[xsd+1];
	            	//alert("#######"+next);
	            	nextSrvStart = $('#srvStart'+next).html();
	            	nextSrvEnd = $('#srvEnd'+next).html();
	            	
	            	var diffTime = 0;
	                var newEndTime = 0;
	                
	                $.ajax({
	             		type : "POST",
	             		async : false,
	             		url : 'subtractTimes?startTime='+nextSrvStart+'&endTime='+nextSrvEnd,
	             		success : function(response) { 
	             			diffTime = response;
	             		}
	             	});
	                
	                $.ajax({
	             		type : "POST",
	             		async : false,
	             		url : 'addTimes?startTime='+prevSrvEnd+'&endTime='+diffTime,
	             		success : function(response) { 
	             			newEndTime = response;
	             		}
	             	});
	            	
	                $('#srvStart'+next).html(prevSrvEnd);
	                $('#srvEnd'+next).html(newEndTime);
	                prevSrvEnd = newEndTime;
            	}
            }
            
            $('#heading'+r).remove();
            $('#services'+r).remove();
            $('#stock'+r).remove();
            var index = rowsArr.indexOf(parseInt(r, 10));
            if (index > -1) {
                rowsArr.splice(index, 1);
            }
            var index1 = serviceIdArr.indexOf(parseInt(srvId, 10));
            if (index1 > -1) {
            	serviceIdArr.splice(index1, 1);
		    	packageIdArr.splice(index1, 1);
            }
    	}
    }
    else
    {
    	if(confirm("Confirm Deleting \""+pkgName+"\" Package.?"))
    	{
	    		
	        	var pkgRowsArr = [];
	        	var pkgidid = $('#pkgId'+r).html();
	        	
	    		for (var xi = 0; xi < packageIdArr.length; xi++) {
	    			if(packageIdArr[xi] == pkgidid)
	    				pkgRowsArr.push(xi);
	    		}
	        	
	        	var rp = rowsArr[pkgRowsArr[0]];
	        	var rn = rowsArr[pkgRowsArr[pkgRowsArr.length-1]];
	    	
	    		var prev = rowsArr[rowsArr.indexOf(rp)-1];
	        	var now = rowsArr[rowsArr.indexOf(rn)];
	        	prevSrvStart = $('#srvStart'+prev).html();
	        	prevSrvEnd = $('#srvEnd'+prev).html();
	        	nowSrvStart = $('#srvStart'+now).html();
	        	nowSrvEnd = $('#srvEnd'+now).html();
	        	if(rowsArr.indexOf(rp) == 0)
	        	{
	        		prevSrvEnd = $('#srvStart'+rowsArr[rowsArr.indexOf(rp)]).html();
	        	}
	            for(var xsd = rowsArr.indexOf(rn); xsd < rowsArr.length; xsd++)
	            {
	            	if(xsd == rowsArr.length-1)
	            	{
	            		break;
	            	}
	            	else
	            	{
	                	var next = rowsArr[xsd+1];
	                	nextSrvStart = $('#srvStart'+next).html();
	                	nextSrvEnd = $('#srvEnd'+next).html();
	                	var diffTime = 0;
	                    var newEndTime = 0;
	                    $.ajax({
	                 		type : "POST",
	                 		async : false,
	                 		url : 'subtractTimes?startTime='+nextSrvStart+'&endTime='+nextSrvEnd,
	                 		success : function(response) { 
	                 			diffTime = response;
	                 		}
	                 	});
	                    $.ajax({
	                 		type : "POST",
	                 		async : false,
	                 		url : 'addTimes?startTime='+prevSrvEnd+'&endTime='+diffTime,
	                 		success : function(response) { 
	                 			newEndTime = response;
	                 		}
	                 	});
	                    $('#srvStart'+next).html(prevSrvEnd);
	                    $('#srvEnd'+next).html(newEndTime);
	                    prevSrvEnd = newEndTime;
	            	}
	            }
	        for(var i = 0; i < abcVal; i++)
	    	{
	    		var spk = $('#pkgId'+i).html();
	    		if(spk == pkgId)
	    		{
	    			var srvId = $('#srvId'+i).html();
	    		    $('#heading'+i).remove();
	    		    $('#services'+i).remove();
	    		    $('#stock'+i).remove();
	    		    var index = rowsArr.indexOf(parseInt(i, 10));
	    		    if (index > -1) {
	    		        rowsArr.splice(index, 1);
	    		    }
	    		    var index1 = serviceIdArr.indexOf(parseInt(srvId, 10));
	    		    if (index1 > -1) {
	    		    	serviceIdArr.splice(index1, 1);
	    		    }
	    		    var indexi = packageIdArr.indexOf(parseInt(pkgidid, 10));
	    		    if (indexi > -1) {
	    		    	packageIdArr.splice(indexi, 1);
	    		    }
	    		}
	    	}
    	}
    }
});

function bookingAppointment()
{
	var guestid = $('#custid').val();
	var appointDate = $('#date').val();
	var appointTime = $('#appointTime').val();
	var queries = $('#queries').val();
	var servicesObjList = [];
	var result = parseInt(0, 10);
	
	var guestMaster = {
		guestid	:	guestid
	}

/* 	
	corparry = {
			corporateid		:corporateid
			}
	
	segmentarry = {
			segmentid	:segmentid
			}
	
	bussourcearry = {
			businesssourceid	:businesssourceid
			}
	
	billinstarry = {
			billinginstructionid	:billinginstructionid
			}
	 */
	
    for(var i = 0; i < rowsArr.length; i++)
    {
    	var res = parseInt(0, 10);
    	var servicesObj;
    	var pkgId = $('#pkgId'+rowsArr[i]).html();
    	var srvId = $('#srvId'+rowsArr[i]).html();
    	var r = document.getElementById('room'+rowsArr[i]);
    	var srvRoomId = r.options[r.selectedIndex].id;
    	var srvRoom = r.options[r.selectedIndex].value;
    	var s = document.getElementById('staff'+rowsArr[i]);
    	var srvStaffId = s.options[s.selectedIndex].id;
    	var srvStaff = s.options[s.selectedIndex].value;
    	var srvName = $('#srvName'+rowsArr[i]).html();
    	var doctorAdvice = $('#doctorAdvice'+rowsArr[i]).val();
    	var srvStartTime = $('#srvStart'+rowsArr[i]).html();
    	var srvEndTime = $('#srvEnd'+rowsArr[i]).html();
    	var arvdate = $('#date').val();
    	
			    	var staffMaster = {
			   			staffid	:	srvStaffId
			   		}
			    		
			   		var roomMaster = {
			   			roomid	:	srvRoomId
			   		}
			    	
			    	var packageMaster = {
			  			packageid	:	pkgId
			  		}
			   		
			  		var serviceMaster = {
			  			serviceid	:	srvId
			  		}
			
			    	servicesObj = {
			    		packagemaster	:	packageMaster,
			    		servicemaster	:	serviceMaster,
			    		roommaster	:	roomMaster,
			    		staffmaster	: staffMaster,
			    		doctoradvice	:	doctorAdvice
			    	};
			    	servicesObjList.push(servicesObj);
			    	
    	$.ajax({
	 		type : "POST",
	 		async : false,
	 		url : 'appointmentConditions?r='+srvRoomId+'&st='+srvStartTime+'&arv='+arvdate+'&staff='+srvStaffId+'&end='+srvEndTime,
	 		success : function(response) { 
	 			if(response == 1)
	 			{
	 				alert("\""+srvRoom+"\" Room is Not Available for \""+srvName+"\" Service. Kindly Select Other Room.!");
	 				res = -1;
	 			}
	 			else if(response == 2)
	 			{
	 				alert("\""+srvStaff+"\" is Not Available for \""+srvName+"\" Service. Kindly Select Other Staff Member.!");
	 				res = -1;
	 			}
	 			else
	 			{
	 				//alert("Success.!");
	 				res = 1;
	 			}
	 			result += res;
	 		}
	 	});
    	
    	
    }
    
	appointment = {
		guestmaster	:	guestMaster,
		appointmentdate	:	appointDate,
		queries	:	queries,
		appointmentservicedetailses	:	servicesObjList
	};
	
	if(result > 0)
	{
		$.ajax({
			type : "POST", 
			url : 'bookAppointment?time='+appointTime,
			contentType :   'application/json',
			data : JSON.stringify(appointment),
			success : function(response) {
				if(response > 0)
				{
					alert("Your Appointment for \""+arvdate+"\" is Successfully Booked and Appointment ID is : "+response);
					location.replace('appointmentslist');
				}
				else
					alert("Error Occured in Booking Appointment.!");
			}
		});
	}	
}

</script>


