<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
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
					<h3 class="panel-title">Packages</h3>
					</br>
				</div>
				<div class="panel-body">
				<form action="" class="col-md-7" method="post" id="packageform">
							<input type="hidden" id="packageid" name="packageid" value=""/>
					<div class="col-md-11">
						<table class="table table-no-bordered">
							<tbody>
								<tr>
										<td><label>Package Name</label></td>
										<td><input type="text" class="form-control" id="packagename"
											name="packagename"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Service Name</label></td>
										<td><select multiple class="form-control" name="serviceid"
											id="serviceid">
												<option>Select multiple services</option>
												<c:forEach var="packageservices" items="${servicesList}">
													<option value="${packageservices.serviceid}">${packageservices.servicename}</option>
												</c:forEach>
											</select>
										</td>

										<td>&nbsp;</td>
									</tr>						
									
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="add" onclick="addpackagerows()">
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							
						</table>

					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<table class="table table-bordered table-hover" id="hidediv" style="visibility:hidden;">
                    <thead>
                      <tr>
                         <th>Service Name</th>
                         <th></th>
                        </tr>
                    </thead>
                    	<tbody id="addrowstextfields">				
							
						</tbody>

                  </table>
                  <div class="col-md-12" id="hidebuttons" style="visibility: hidden;">
	                  <div class="pull-right">
	                  <input type="button" value="Save" class="btn btn-primary btn-sm" id="save" onclick="savepackage()"/>
		              <input type="button" value="Edit" class="btn btn-primary btn-sm" id="edit" disabled="disabled" onclick="editpackage()"/>
		                  
		                  <a href="serviceStockList"><input type="button" value="Cancel" id="cancel" name="cancel" class="btn btn-primary btn-sm"/></a>
	                  </div>
               		 </div>
                   <div class="row">
                <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Package Id</th>
								<th>Package Name</th>
								<th>Services</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						<c:choose>
							<c:when test="${listmessage!=null}">
								<tr><c:out value="${listmessage}"></c:out>
								</tr>
							</c:when>
							<c:otherwise>
							<c:forEach var="packages" items="${packagesList}">
								<tr>
									<td>${packages.getPackageid()}</td>
									<td>${packages.getPackagename()}</td>
									<td>
								<c:forEach var="packageservice" items="${packageserviceList}">
									<c:choose>
										<c:when test="${packages.getPackageid()==packageservice.getPackagemaster().getPackageid()}">
											<c:out value=" ${packageservice.getServicemaster().getServicename()},"></c:out>
										</c:when>
									</c:choose>
								</c:forEach>
								</td>
									<td class="text-center"><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button>
									<button class="deletepackage"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td>
								</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
					</div>
				</div> 
                  </div>
               		 </div>
</div>
<script type="text/javascript">
function savepackage(){
	$('#packageform').attr('action', 'savePackage');
	$('#packageform').submit();
}
function editpackage(){
	$('#packageform').attr('action', 'editPackage');
	$('#packageform').submit();
}
function deletepackage(){
	$('#packageform').attr('action', 'deletePackage');
	$('#packageform').submit();
}

function addpackagerows()
{
	$("#addrowstextfields").html('<tr></tr>');
	document.getElementById("hidediv").style.visibility="visible";
	document.getElementById("hidebuttons").style.visibility="visible";
	var servicesnames=document.getElementById("serviceid");
  	for (var i = 0; i < servicesnames.options.length; i++) {
     if(servicesnames.options[i].selected ==true){
          	var tr = '<tr>'+
      			'<td><input type="text" disabled class="form-control" value="'+servicesnames.options[i].text+'" name="'+servicesnames.options[i].text+'"></td>'+
      			'<td><button id="'+servicesnames.options[i].text+'"onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
          	$("#addrowstextfields tr:last").after(tr);	
      }
  }
}

$(function(){
	$(".use-address").click(function() {
		$("#serviceid option:selected").removeAttr("selected");
	    var $row = $(this).closest("tr");
	    var id = $row[0].children[0].innerHTML;
	    var packagename = $row[0].children[1].innerHTML;
	    var services = $row[0].children[2].innerHTML;
	    $.ajax({
			type : "POST",
			async : false,
			url : "getServicesByPackageId?packageId="+id,
			success : function(response) {
				for(var i = 0; i<response.length; i++){
					$("option").filter(function(){
						return $(this).text()==response[i];
					}).prop('selected',true);
				}
			}
	    });
	    
	    $('#packageid').val(id);
	    $('#packagename').val(packagename); 
	    $('#edit').prop("disabled", false);
	    $('#save').prop("disabled", true);
	});
}); 

$(function(){
	$(".deletepackage").click(function() {
	    var $row = $(this).closest("tr");
	    var id = $row[0].children[0].innerHTML;
	    if (confirm("Are you sure you want to delete this "+id+"!") == true) {
	    	$('#packageform').attr('action', 'deletePackage?deletePackageId='+id);
			$('#packageform').submit();
	    } else {
	        return false;
	    }
	});
});

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>