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
				
					<h3 class="panel-title">StaffList</h3>
					
				</div>
				
				<div class="panel-body">
				
					<div class="col-md-12">
						<table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                      	<th>Staff ID</th>
                        <th>Staff Name</th>
                        <th>DepartmentName</th>
                       </tr>
                    </thead>
				    <tbody id="addrowdata">
                    
                    <c:forEach var="list" items="${staffmasterlist}">
                      			
                      	<tr id="trow">
                      	 <td ><a href="fetchselectedstaff?staffid=${list.staffid}">${list.staffid}</a></td>
                         <td>${list.getStaffname()}</td>
                    	<td>${list.getDepartmentmaster().getDepartmentname()}</td>
                     	 </tr>
					 </c:forEach>
					
                     </tbody>
			<div class="row">
                <div class="col-md-6">
                 
                    <div class="form-group">
                     <label for="email">Search StaffName:</label>
                      <input type="text" class="form-control" id="searchName" name="searchName"/>
                	 </div>
                    <button  class="btn btn-primary btn-sm" id="search" >Search</button>
 				 </div>
                <div class="col-md-3"></div>
              </div>
		<div  style="display: none;"  class="row">
          <div class="col-md-12">
            <div style="height:90px; overflow-y:scroll;">
            
            </div>
          </div>
       </div>
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
	 
</script>
	<script>
	$("#search").click(function(){
	var name =	document.getElementById("searchName").value;
	
	$("#addrowdata").html('<tr></tr>');
	$.ajax({
		type : "GET",
		async : false,
		url : 'searchstaffname123?staffname='+name,
		success : function(response) { 
			var status=response.length;
			if(status==0){
				alert("No employee Found");				
			}
			else{
				alert("employee found");
			}
			
			 for(var i=0; i<response.length;i=i+3){
				
				var tr='<tr id="'+response[i]+
				'><td><input type="checkbox"></td><td><a href="fetchselectedstaff?staffid='+response[i]+'">'+response[i]+'</a></td>'+
				'<td>'+response[i+1]+'</td>'+
				'<td>'+response[i+2]+'</td></tr>';
				$('#addrowdata tr:last').after(tr);
			}
		}
	});
	});
	function searchmembers(id){
		var cid = $('#'+id)[0].children[0].innerHTML;
		$("#"+cid).prop('selected',true);
		$('#staffid').val($('#'+id)[0].children[1].innerHTML);
		$('#staffname').val($('#'+id)[0].children[1].innerHTML);
		$('#departmentname').val($('#'+id)[0].children[2].innerHTML);
		}
	
	$("#roster").click(function(){
		var name =	document.getElementById("createroster").value;
		$.ajax({
			type : "GET",
			async : false,
			url : 'saveroster?employeeroster='+name,
			success : function(response) { 
				alert(response);
			}
		});
		});
		</script>
		