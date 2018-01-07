<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Services
						</h1>
						
					</div>
				</div>

				<!-- Main Content Area -->
				<div class="row">
					<div class="col-lg-12">
					<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"> Service Stock List</h3>
            </div>
            <div class="panel-body">
              <div class="col-md-11">
              <form action="" id="servicestocksave">
              	<div class="col-md-12">
              		<label>Service Name:</label>
              		<select class="form-control forChosen"
									id="serviceId" name="serviceId" onchange="get()">
										<option value="">Select Service to view related stock</option>
										<c:forEach var="servicename" items="${listofexistedstockservices}">
											<option value="${servicename.serviceid}">${servicename.servicename}</option>
										</c:forEach>
								</select>
              	</div>
              	<div>&nbsp;<div>
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Stock Name</th>
                        <th>Quantity Usage</th>
                        <th>&nbsp;</th>
                      </tr>
                    </thead>
                    	<tbody id="addrowstextfields">				
							
						</tbody>

                  </table>
     				<div class="col-md-12" id="hidebuttons" style="visibility: hidden;">
	                  <div class="pull-right">
	                  <input type="button" value="New" class="btn btn-primary btn-sm" id="new" onclick="addfields()"/>
		                  <input type="button" value="Save" class="btn btn-primary btn-sm" id="save" onclick="savenew()"/>
		                  <input type="button" value="Edit" class="btn btn-primary btn-sm" id="edit" onclick="saveedited()"/>
		                  <input type="button" value="Delete" class="btn btn-primary btn-sm" id="delete" onclick="deletestockrelation()"/>
		                  <a href="serviceStockList"><input type="button" value="Cancel" id="cancel" name="cancel" class="btn btn-primary btn-sm"/></a>
	                  </div>
               		 </div>
               		 </form>
               </div>
            </div>
          </div>
					</div>
				</div>
			</div>
			<!-- Main Content Area -->
			<!-- /.container-fluid -->
<script type="text/javascript">
	function get() {
		var id = document.getElementById("serviceId").value;
		$("#addrowstextfields").html('<tr></tr>');
		$.ajax({
			type : "GET",
			async : false,
			url : "getStockUsage?serviceId="+id,
			success : function(response) {
				var length = response.length;
				for(var i=0; i<length;i=i+3)
					{
					var tr = '<tr class="se"><td><input type="checkbox" class="case" name="case" value="'+response[i+2]+','+response[i+1]+','+response[i]+'"/></td>'+
	      			'<td><input type="text" disabled class="form-control" value="'+response[i+1]+'" name="'+response[i+1]+'"></td>'+
	      			'<td><input type="text" class="form-control" value="'+response[i]+'" name="stockusage"></td><td><input type="hidden" class="form-control" value="'+response[i+2]+'" name="'+response[i+2]+'"></td></tr>';
	      			$("#addrowstextfields tr:last").after(tr);
					}
			} 
		});
			
			document.getElementById("hidebuttons").style.visibility="visible";
       
	}
	function addfields(){
		var tr ='<tr><td><input type="checkbox" class="case" name="case" value=""/></td>'+
      		'<td><select id="select" class="form-control forChosen1" name="stockId">'+
					'<option value="">Select stock</option>'+'</select></td>'+
		'<td><input type="text" class="form-control" value="" name="stockusage"></td><td><input type="hidden" class="form-control" value="" name="stockusage"></td></tr>';
		$("#addrowstextfields tr:last").after(tr);
		$.ajax({
			type : "GET",
			async : false,
			url : "getStocknames",
			success : function(response) {
				for (var i = 0, len = response.length; i < len; i++) {
					$("#addrowstextfields select:last").append(
							$('<option/>').attr("value", response[i]).text(response[i]));
				}
			} 
		});
		//select();
		}
	function saveedited()
	{
		
		var arr = [];
		   var length =  $('.case:checked').length;
	       var l = length*3;
	       var j=0;
	       var ObjectArray=[];
	       $('.case:checked').each(function () {
	    	   var i=0;
		   	   var stockName;
		   	   var serviceId;
		   	   var stockQuantity;
		   	   var stockId;
	   		   serviceId =  document.getElementById("serviceId").value;
	   	       stockName = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+1].children[0].value;
	   	       stockQuantity = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+2].children[0].value;
	   	       stockId = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+3].children[0].value
		   	        
		   	      
	   	      var ssetObj={};
	   	    	    ssetObj={
	   	    			serviceId: serviceId,
	   	    			stockName : stockName,
	   	    			stockQuantity : stockQuantity,
	   	    			stockId : stockId,
	   	       }
	   	    	ObjectArray.push(ssetObj);
				j++;
			});
		   	$.ajax({
		   			type : "POST", 
		   			url : "updateservicestock",
		   			contentType : 'application/json',
		   			data :JSON.stringify(ObjectArray),
		   			success : function(response) {
		   				alert(response);
  						location.reload();
		   			}
		   });
	}
	function savenew()
	{
		
	       var arr = [];
	       var length =  $('.case:checked').length;
	       var l = length*3;
	       var j=0;
	       var ObjectArray=[];
      	   
	       $('.case:checked').each(function () {
	    	   var i=0;
	    	   
	    	var stockName;
      	    var serviceId;
      	    var stockQuantity;
      	    var stockId;
      		    serviceId =  document.getElementById("serviceId").value;
      	       stockName = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+1].children[0].value;
      	       stockQuantity = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+2].children[0].value;
      	       stockId = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+3].children[0].value
      	      
      	      var ssetObj={};
      	    	
      	       
      	       ssetObj={
      	    			serviceId: serviceId,
      	    			stockName : stockName,
      	    			stockQuantity : stockQuantity,
      	    			stockId : stockId,
      	       }
      	    	ObjectArray.push(ssetObj);

     	  		j++;
     		});
	       
      	   
      	$.ajax({
      			type : "POST", 
      			url : "saveservicestock",
      			contentType : 'application/json',
      			data :JSON.stringify(ObjectArray),
      			success : function(response) {
      				alert(response);
					location.reload();
      			}
      		});       
	}
	function deletestockrelation()
	{
			var arr = [];
	       var length =  $('.case:checked').length;
	       var l = length*3;
	       var j=0;
	       var ObjectArray=[];
     	    
	       $('.case:checked').each(function () {
	    	   var i=0;
	    	  var stockName;
      	    var serviceId;
      	    var stockQuantity;
      	    var stockId;
      		   serviceId =  document.getElementById("serviceId").value;
      	       stockName = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+1].children[0].value;
      	       stockQuantity = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+2].children[0].value;
      	       stockId = $('#addrowstextfields input:checkbox:checked').closest('tr')[j].children[i+3].children[0].value
      	          var ssetObj={};
      	    	
      	       
      	       ssetObj={
      	    			serviceId: serviceId,
      	    			stockName : stockName,
      	    			stockQuantity : stockQuantity,
      	    			stockId : stockId,
      	       }
      	    	ObjectArray.push(ssetObj);
      	     j++;
     		});
      	   
      	$.ajax({
      			type : "POST", 
      			url : "deleteservicestock",
      			contentType : 'application/json',
      			data :JSON.stringify(ObjectArray),
      			success : function(response) {
      						alert(response);
      						location.reload();
      					}
      		});
	}
	/* function select(){
		 $(function() {
		     $('#addrowstextfields select:last').chosen();
		  });
	}
	 */
</script>