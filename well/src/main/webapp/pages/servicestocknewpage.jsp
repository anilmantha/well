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
              <h3 class="panel-title"> Service-Stock </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Service Related Stock Addition</th>
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                    	<td><div class="form-group">
						  <label>Select Service</label>
						  <select class="form-control" id="servicename">
							<option>Select</option>
							<c:forEach var="serviceName" items="${listofservices}">
								<option value="${serviceName.serviceid}">${serviceName.servicename}</option>
							</c:forEach>
						  </select>
						</div></td>
                       	<td><div class="form-group">
						  <label>Select Stock</label>
						  <select multiple class="form-control" id="stocknames">
							<option>Select</option>
							<c:forEach var="stockname" items="${stockList}">
								<option value="${stockname.stockname}">${stockname.stockname}</option>
							</c:forEach>
						  </select>
						</div></td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                
                <div class="col-md-12">
                  <div class="pull-right">
	                  <input type="button" value="Add" id="add" name="add" class="btn btn-primary btn-sm" onclick="addstockrows()"/>
	                   <a href="serviceStockNewPage"><input type="button" value="Cancel" id="cancel" name="cancel" class="btn btn-primary btn-sm"/></a>
                  </div>
                </div>
                <div class="col-md-12"> <h1></h1></div>
                <div class="col-md-12" id="divhidden" style="display: none">
                <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"> Add Stock Quantity </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                <form action="" class="col-md-7" id="servicestocksave">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="table table-no-bordered" id="textfields">
                    <tbody>
                    	<div id="servicetext"></div>
                    </tbody>
                  </table>
                  <div class="col-md-12">
                  <div class="pull-right">
	                  <input type="button" value="Save" class="btn btn-primary btn-sm" onclick="savestockservice()"/>
	                  <a href="serviceStockNewPage"><input type="button" value="Cancel" id="cancel" name="cancel" class="btn btn-primary btn-sm"/></a>
                  </div>
                </div>
                  </form>
                </div>
     			</div>
              </div>
            </div>
          </div>
          <div class="col-md-12" id="tablehidden" style="display: none">
                <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"> Added stock to the service </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                </div>
                
                <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Stock Name</th>
                        <th>Quantity Usage</th>
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
              </div>
            </div>
          </div>
					</div>
				</div>
			</div>
			<!-- Main Content Area -->
			<!-- /.container-fluid -->
<script type="text/javascript">

function addstockrows()
{
	//document.getElementById("divhidden").style.visibility="visible";
	$("#divhidden").css("display", "inline");
	$("#tablehidden").css("display", "none");
  var servicename = document.getElementById("servicename");
  var sname = $('#servicename :selected').text();
  var stocknames=document.getElementById("stocknames");
  document.getElementById("servicetext").innerHTML += '<label>Selected Service is '+sname+'</label>\r\n';
  	for (var i = 0; i < stocknames.options.length; i++) {
     if(stocknames.options[i].selected ==true){
           $.ajax({
      		type : "GET",
      		async : false,
      		url : "getStockId?stockname="+stocknames.options[i].value,
      		success : function(response) { 
      			document.getElementById("textfields").innerHTML += '<tr><td><input type="hidden" class="form-control" value="'+response+'" name="'+stocknames.options[i].value+'"></td>'+
      			'<td><input type="text" disabled class="form-control" value="'+stocknames.options[i].value+'" name="'+stocknames.options[i].value+'"></td>'+
      			'<td><input type="text" class="form-control" value="" name="'+stocknames.options[i].value+'_quantity"></td></tr>\r\n';
      		   }
      	}); 
          
      }
  }
}
	
function savestockservice()
{
	var x = document.getElementById("servicestocksave");
	var i;
	var stockName;
    var stockId;
    var serviceId;
    var stockQuantity;
	var ObjectArray=[];
    for (i = 0; i < x.length-2; i=i+3) {
        
       serviceId =  document.getElementById("servicename").value;
       stockId = x.elements[i].value;
       stockName = x.elements[i+1].value;
       stockQuantity = x.elements[i+2].value;
      
        var ssetObj={};
    	
       
       ssetObj={
    			serviceId: serviceId,
    			stockId : stockId,
    			stockName : stockName,
    			stockQuantity : stockQuantity,
    	}
    	ObjectArray.push(ssetObj);
    
    }
    $.ajax({
		type : "POST", 
		url : "saveservicestock",
		contentType : 'application/json',
		data :JSON.stringify(ObjectArray),
		success : function(response) {
			alert(response);
			get();
		}
	});
  }
function get() {
	var id = document.getElementById("servicename").value;
	$("#divhidden").removeAttr("style");
	//$("#divhidden").addAttr("style");
	$("#divhidden").css("display", "none");
	//document.getElementById("tablehidden").style.visibility="visible";
	$("#tablehidden").css("display", "inline");
	 
	$("#addrowstextfields").html('<tr></tr>');
	$.ajax({
		type : "GET",
		async : false,
		url : "getStockUsage?serviceId="+id,
		success : function(response) {
			var length = response.length;
			for(var i=0; i<length;i=i+3)
				{
				var tr = '<tr class="se">'+
      			'<td><input type="text" disabled class="form-control" value="'+response[i+1]+'" name="'+response[i+1]+'"></td>'+
      			'<td><input type="text"  disabled class="form-control" value="'+response[i]+'" name="stockusage"></td></tr>';
      			$("#addrowstextfields tr:last").after(tr);
				}
		} 
	});
}
</script>


