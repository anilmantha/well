<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <div class="container-fluid"> 
     
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Doctor Advice</small> </h1>
          <!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
        </div>
      </div>
      
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
             <h3 class="panel-title">Stock Details</h3>
            </div>
            
            <form action=""></form>
            <div class="panel-body">
          <c:set var="appId" value="${param.appId}"></c:set>
    	  <input type="hidden" id="appid" name="appid" value="${appId}" >
    	  
    	  <c:set var="srvId" value="${param.srvId}"></c:set>
    	  <input type="hidden" id="srvid" name="srvid" value="${srvId}" >
    	  
    	   <c:set var="pkgId" value="${param.pkgId}"></c:set>
    	  <input type="hidden" id="pkgid" name="pkgid" value="${pkgId}" >
            
            <c:forEach var="val" items="${mainList[0]}">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                    <tr>
                        <td class="col-md-3"><label>Service Name</label></td>
                        <td class="col-md-3"><input type="search" class="form-control" id="serviceName" readonly="readonly"></td>
                   </tr>
                     <tr>
                        <td class="col-md-3"><label>Client Name</label></td>
                        <td class="col-md-3"><input type="search" class="form-control" id="email" value="${val}" readonly="readonly"></td>
                    </tr> 
                    <tr>
                     <td class="col-md-3"><label>Search Product By Name</label></td>
                     <td><select class="form-control" id="stockNameSelection" name="stockNameSelection" onchange="get()">
						 <option>Select</option>
						 </select>
					 </td>
					    <td id="redorder" hidden="true"></td>
						<td id="warning"  hidden="true"></td>
						<td id="availability"></td>
						<td id="currentStockAvailability"  hidden="true"></td>
						<td id="professionalprice" hidden="true"></td>
						<td id="stockId" hidden="true"></td>
					
					
					<td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Quantity Required</label></td>
                        <td class="col-md-3"><input type="search" class="form-control" id="stockreq" value=""></td>
                    </tr>
                                        
                    <tr>
                    	<td class="col-md-3"></td>
                        <td class="col-md-3"><button type="button" id="addStock"  class="btn btn-primary btn-sm" >Add Stock</button></td>
                        <td class="col-md-6"></td>
                    </tr>
                  
                    </table>
               </c:forEach>
               
                    <div class="row" >
              	<div class="table-responsive padding-10" >
              	<div id="table">
              	           
            <table id="tblStockDetails" class="table table-bordered table-hover" >
              <thead>
                <tr>
                 <th>Stock Name</th>
		         <th>Available Stock</th>
		         <th>Re-Order Level</th>
		         <th>Warning Level</th>
				 <th>Quantity Required</th>
		         <th>Price</th>
		         <th>Delete</th>
				
                </tr>
              </thead>
                <c:set var="i" value="1" scope="page"></c:set>
              <tbody>
          
            <c:forEach var="val1" items="${mainList[1]}">
            <tr>
            <td id="stockName">${val1[0]}</td>
			<td id="availStock">${val1[1]}</td>
			<td id="redorder">${val1[2]}</td>
			<td id="warning">${val1[3]}</td>
			<td class="col-md-3"><input type="search" class="form-control" id="QuantityRequired${i}" value="${val1[4]}"></td>
			<td style="display: none;"><input type="hidden" id="QuantityRequiredd${i}" value="aaa"></td>
			<td id="price">${val1[5]}</td>
			<td id="stkid" hidden="">${val1[7]}</td>
			<td id="sno" hidden="">${val1[8]}</td>
			<td id="appointmentstockid" hidden="">${val1[9]}</td>
			<!-- <td id="delete"><button type="button" id="click" class="btn btn-primary btn-sm" style="color: red" onclick="deleteRow(this)">DELETE</button></td> -->
			<td id="delete"><button type="button" id="click" class="btn btn-primary btn-sm" style="color: red" onclick="deleteStock()">DELETE</button></td>
			</tr>
			 	<c:set var="i" value="${i+1}" scope="page"></c:set>
            </c:forEach>
          
            
              </tbody>
            </table>
            <table  border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                    <tr>
                        <td><label>Remarks</label></td>
                        <td><textarea class="form-control" rows="5" cols="35"></textarea></td>
                    </tr>
             </table>
             <table class="table table-no-bordered">
            		<tr align="right">
            			<td id="delete" align="right"><button type="button" id="saveRecords" class="btn btn-primary btn-sm" >Save</button></td>
            			<td id="delete" align="right"><button type="button" id="click" class="btn btn-primary btn-sm" >Cancel</button></td>
            		</tr>
             </table>
          </div>
              </div>
              </div>
 			</div>
            </div>
          </div>
        </div>
    </div>

<script type="text/javascript">
	$(document).ready(function() {
		var srvid = document.getElementById("srvid").value;
		$.ajax({
			type : "GET",
			async : false,
			url : "getServiceName?srvid="+srvid,
			success : function(response) {
				document.getElementById("serviceName").value = response;
			}
		});
	});
</script>
  
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		type : "GET",
		async : false,
		url : "fetchStockNames",
		success : function(response) {
			 $('#stockNameSelection').empty();
			$('#stockNameSelection').append($('<option/>').attr("value","").text("Select"));
            for (var i = 0, len = response.length; i < len; ++i) 
            {
                var prj = response[i];
            	
                $('#stockNameSelection').append($('<option/>').attr("value", prj).text(prj));
            } 
		}
		
	});

});
</script>

<!-- <script type="text/javascript">

function deleteRow(row)
{
    var i=row.parentNode.parentNode.rowIndex;
    document.getElementById('tblStockDetails').deleteRow(i);
}

</script> -->

<script>
$("#addStock").click(function (){
	  var stockName = document.getElementById("stockNameSelection").value;
	  var availStock = document.getElementById("currentStockAvailability").innerHTML;
	  var redorder = document.getElementById("redorder").innerHTML;
	  var warning = document.getElementById("warning").innerHTML;
	  var professionalprice = document.getElementById("professionalprice").innerHTML;
	  var stockId =  document.getElementById("stockId").innerHTML;
	  
	  alert("professionalprice"+professionalprice);
	  
	  var stockreq = document.getElementById("stockreq").value;
	  
	  var stockvalue = (professionalprice*stockreq);
	  
	  alert("stockvalue"+stockvalue);
	  
	  var j = document.getElementById("tblStockDetails").rows.length;
	  
	  
	  var i=2;
       var data="<tr><td id='stockName'>"+stockName+"</td><td id='availStock'>"+availStock+"</td>";
        data +="<td id='redorder'>"+redorder+"</td><td id='warning'>"+warning+"</td><td class='col-md-3'><input type='search' class='form-control' id='QuantityRequired"+j+"' value="+stockreq+"><td style='display: none;'><input type='hidden' id='QuantityRequiredd"+j+"' value='bbb'></td><td id='reatailPrice'>"+professionalprice+"</td><td id='stkid' hidden=''>"+stockId+"</td><td id='sno' hidden=''>0</td><td id='appointmentstockid' hidden=''>0</td><td id='delete'><button type='button' id='click' class='btn btn-primary btn-sm' style='color: red' onclick='deleteRow(this)'>DELETE</button></td></tr>";
        $('#tblStockDetails').append(data);
        i++; 
}); 
</script>



<!-- <script type="text/javascript">
$(document).ready(function() {
	var amount=null;
	var rowcount = document.getElementById("tblStockDetails").rows.length;
	 for(var i=1;i<rowcount;i++)
   	{
	 serName=document.getElementById("tblStockDetails").rows[i].cells.item(7).innerHTML;
   	}
	document.getElementById("serviceName").value=serName;
});
</script> -->


<script type="text/javascript">
		function get() {
			var name = document.getElementById("stockNameSelection").value;
			$.ajax({
				type : "GET",
				async : false,
				url : "stockDetailInfo?stockName="+name,
				success : function(response) {

					document.getElementById("redorder").innerHTML = response[1];
					document.getElementById("warning").innerHTML = response[2];
					document.getElementById("availability").innerHTML = "Current Availability : "+ response[3];
					document.getElementById("currentStockAvailability").innerHTML = response[3];
					document.getElementById("professionalprice").innerHTML = response[4];
					document.getElementById("stockId").innerHTML = response[5];
					
					
				}
			});
		}
			
</script>
<script type="text/javascript">
function deleteStock(){
$('#tblStockDetails tbody td').click(function (){
	var row_num = parseInt($(this).parent().index() + 1);
	var id = document.getElementById("tblStockDetails").rows[row_num].cells.item(9).innerHTML;
	var appid = document.getElementById("appid").value;
	var srvid = document.getElementById("srvid").value;
	
	alert(id);
	$.ajax({
		type : "GET",
		async : false,
		url : "deleteStock?id="+id,
		success : function(response) {
			
			if (response == 1){
				
				alert("Stock Deleted Sucessfully")
				location.href = "doctoradvice?appId="+appid+"&srvId="+srvid;
			}
			
			else{
				
				alert("Delete Operation Failed")
				location.href = "doctoradvice?appId="+appid+"&srvId="+srvid;
			}
			
						
		}
	});
	
	
});
	

}
</script>

<script type="text/javascript">
$('#saveRecords').click(function (){
	var rowcount = document.getElementById("tblStockDetails").rows.length;
	//alert(rowcount);
	var stockName = document.getElementById("stockNameSelection").value;
	var appid = document.getElementById("appid").value;
	var srvid = document.getElementById("srvid").value;
	var pkgid = document.getElementById("pkgid").value;
	
	
	var ObjArry=[];
	for(var k=1;k<rowcount;k++){
	var stkId=document.getElementById("tblStockDetails").rows[k].cells.item(7).innerHTML;
	var sno=document.getElementById("tblStockDetails").rows[k].cells.item(8).innerHTML;
	var ascid=document.getElementById("tblStockDetails").rows[k].cells.item(9).innerHTML;
	var MYqty=	document.getElementById("QuantityRequiredd"+k).value;
	var qty=	document.getElementById("QuantityRequired"+k).value;
	var reorderLevel = document.getElementById("tblStockDetails").rows[k].cells.item(2).innerHTML;
	var availstock = document.getElementById("tblStockDetails").rows[k].cells.item(1).innerHTML;
	var price = document.getElementById("tblStockDetails").rows[k].cells.item(6).innerHTML;
	
	var ssetObj  = {};
	var apparry	 = {};
	var servarry = {};
	var pkgarry = {};
	var stockarry = {};
	
	apparray={
		
		appointmentid	:appid,
	}
	
	servarry = {
			
			serviceid		:srvid,
			
	}
	
	pkgarray = {
			
			packageid : pkgid,
	}
	stockarry = {
			
			stockid			:stkId,
	}
	
	ssetObj={
			sno 				:	sno,
			stockusage	    	:	qty,
			appointment     	:	apparray,
			servicemaster   	:	servarry,
			stockmaster			:	stockarry,
			appointmentstockid	:	ascid,
			stockvalue			:	price,
			packagemaster		:	pkgarray
	}
	
	ObjArry.push(ssetObj);
	
	}
	
	
	  $.ajax({
			type : "POST", 
			url : "addStock",
			contentType : 'application/json',
			data : JSON.stringify(ObjArry),
			success : function(response) {
				alert("Stock Added Sucessfully");
				location.href = "doctoradvice?appId="+appid+"&srvId="+srvid+"&pkgId="+pkgid;
			}
		});   

	  
	  
	  
	  
	  
	  
	  /* var appid = document.getElementById("appid").value;
	var srvid = document.getElementById("srvid").value;
	var stkid = document.getElementById("stockId").innerHTML;
	var stockreq = document.getElementById("stockreq").value;
	
	alert(stkid); */
	/* 
	 $.ajax({
		type : "GET",
		async : false,
		url : "addStock?appid="+appid+"&srvid="+srvid+"&stkid="+stkid+"&stockreq="+stockreq,
		success : function(response) {
			
            if (response == 1){
				
				alert("Stock Added Sucessfully")
				location.href = "doctoradvice?appId="+appid+"&srvId="+srvid;
			}
			
			else{
				
				alert("Stock Addition Failed")
				location.href = "doctoradvice?appId="+appid+"&srvId="+srvid;
			}
			
		}
	}); */
	
	
	
});


</script>

