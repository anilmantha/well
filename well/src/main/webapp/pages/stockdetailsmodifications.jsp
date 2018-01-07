<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var i="${msg}";
var length=i.length;
if(length==0)
	{
	}
else
	{
	alert(i);
	location.href="fetchselectedstock123";
	}
</script>
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Stock Update</small>
			</h1>
			<!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
		</div>
	</div>

	<!-- Main Content Area -->
	<form action="updateStock">
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Stock Update</h3>
            </div>
            <div class="panel-body">
              <div class="col-md-10">
		<table border="0" cellspacing="0" cellpadding="0"
			class="table table-no-bordered">
			<tr>
				<td class="col-md-3"><label>Stock Type</label></td>
				<td class="col-md-3">
				
				<select class="form-control" name="dropdownDetailsId">
                          <option selected value="${stockDetails.dropdownDetailsId}">${stockDetails.dropDownDescription}</option>
                          <c:forEach var="stockType"  items="${stockType}">
                              <c:if test="${stockType.value!=stockDetails.dropDownDescription}">
                               <option value="${stockType.key}">${stockType.value}</option>
                              </c:if>
                          </c:forEach>
                        </select></td>
				<td class="col-md-6">&nbsp;</td>
			</tr>
			<tr>
				<td><label>Stock Name</label></td>
				<td><input type="text" class="form-control" id="stockName"
					name="stockName" value="${stockDetails.stockName}"></td>
				<td><input type="hidden" class="form-control" id="stockId"
					name="stockId" value="${stockDetails.stockId}"></td>
			</tr>
			
			<tr>
				<td><label>Stock Price</label></td>
				<td><input type="text" class="form-control" id="retailprice"
					name="retailprice" value="${stockDetails.retailprice}"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><label>Warning Level</label></td>
				<td><input type="text" class="form-control" id="warninglevel"
					name="warninglevel" value="${stockDetails.warninglevel}"
					readonly="readonly"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><label>Reorder Level</label></td>
				<td><input type="text" class="form-control" id="reorderlevel"
					name="reorderlevel" value="${stockDetails.reorderlevel}"
					readonly="readonly"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><label>Stock Avail.</label></td>
				<td><input type="text" class="form-control"
					id="available" name="available"
					value="${stockDetails.available}" readonly="readonly"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><label>Supplier</label></td>
				<td><%-- <input type="text" class="form-control" id="supplierName"
					name="supplierName" value="${stockDetails.supplierName}"> --%>
					<select class="form-control" name="supplierId">
                          <option selected value="${stockDetails.supplierId}">${stockDetails.supplierName}</option>
                          <c:forEach var="supplier" items="${supplier}">
                            <c:if test="${supplier.value!=stockDetails.supplierName}">
                          
                               <option value="${supplier.key}">${supplier.value}</option>
                            </c:if>
                          </c:forEach>
                        </select></td>
				<td><input type="hidden" class="form-control" id="supplierId"
					name="supplierId" value="${stockDetails.supplierId}"></td>
			</tr>
			<tr>
				<td><label>Maker</label></td>
				<td><%-- <input type="text" class="form-control" id="manufacturerName"
					name="manufacturerName" value="${stockDetails.manufacturerName}"> --%>
					<select class="form-control" name="manufacturerId">
                          <option selected value="${stockDetails.manufacturerId}">${stockDetails.manufacturerName}</option>
                          <c:forEach var="manufacturer" items="${manufacturer}">
                            <c:if test="${manufacturer.value!=stockDetails.manufacturerName}">
                               <option value="${manufacturer.key}">${manufacturer.value}</option>
                             </c:if>
                          </c:forEach>
                        </select></td>
				<td><input type="hidden" class="form-control" id="manufacturerId"
					name="manufacturerId" value="${stockDetails.manufacturerId}"></td>
			</tr>
			<!-- <input type="hidden" value="" id="StockGroupMasterId" name="StockGroupMasterId"/> -->
			<tr>
				<td><label> Stock Group</label></td>
				<td><select class="form-control" name="stockGroupName"
					id="stockgroup" onchange="getting()">
						<option>${stockDetails.stockGroupMasterName}</option>
						<c:forEach var="stock" items="${stockgroupnames}">
						     <c:if test="${stock != stockDetails.stockGroupMasterName}">
							        <option>${stock}</option>
							 </c:if>
						</c:forEach>
				</select></td>

				<td>&nbsp;</td>
			</tr>
           <!--  <input type="text" value="" id="StockSubGroupId" name="StockSubGroupId"/> -->
			<tr>
				<td><label>Sub Group</label></td>
				<td><select class="form-control" id="stocksubgroup"
					name="stockSubGroup" onchange="getSubGroupId()">
					    <option>${stockDetails.stockSubGroupName}</option>
						<option >Select</option>
				</select></td>
				<td><input type="hidden" class="form-control" id="stockSubGroupId"
					name="stockSubGroupId" value="${stockDetails.stockSubGroupId}"></td>
			</tr>

			<tr>
				<td><label>Manufacture Date</label></td>
				<td><input type="Date" class="form-control"
					id="manufacturedate" name="manufacturedate"
					value="${stockDetails.manufacturedate}" readonly="readonly"></td>
				<td>&nbsp;</td>

			</tr>
			
		
			<tr>
				<td><label>Expire Date</label></td>
				<td><input type="Date" class="form-control" id="expirydate"
					name="expirydate" value="${stockDetails.expirydate}"
					readonly="readonly"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><div class="pull-right">
						<input type="submit" class="btn btn-primary btn-sm" value="update">
						<input type="reset" class="btn btn-primary btn-sm" value="clear">
					</div></td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</div>
		</div>
		</div>
		</div>
		</div>

	</form>


	<!-- Main Content Area -->
	<!-- /.container-fluid -->
	<script type="text/javascript">
	function getting(){
		var name =	document.getElementById("stockgroup").value;
		alert(name);
	 $.ajax({
		type : "GET",
		async : false,
		url : "stocksubgroupname?stockGroupMasterName="+name,
		success : function(response) { 
			 $('#stocksubgroup').empty();
			$('#stocksubgroup').append($('<option/>').attr("value","").text("Select"));
			for (var i = 0, len = response.length; i < len; ++i) 
			{
				var prj = response[i];
				
				$('#stocksubgroup').append($('<option/>').attr("value", prj).text(prj));
			} 
			

		        }
	}); 
	}
function getSubGroupId(){
	var name =	document.getElementById("stocksubgroup").value;
	alert(name);
	$.ajax({
		type : "GET",
		async : false,
		url : "getStockSubGroupId?stockSubGroupName="+name,
		success : function(response) { 
			alert(response);
			document.getElementById("stockSubGroupId").value=response;

		        }
	}); 
}
</script>
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