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

	<!-- Main Content Area -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Tax</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form action="" method="post" id="serviceform">
							<input type="hidden" id="taxmasterid" name="taxmasterid" value=""/>
						<table class="table table-no-bordered">
								<tbody>
								<tr>
									<td><label>Tax name</label></td>
										<td><input type="text" class="form-control" id="taxname"
											name="taxname"></td>
										<td>&nbsp;</td>
									</tr>
									<tr><td>&nbsp;</td><td>&nbsp;</td><td class="pull-right">
											<input type="button" class="btn btn-primary btn-sm" id="save"
												value="Save" onclick="savetax()">
									<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="edit" onclick="edittax()">Edit</button>
									<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="delete" onclick="deletetax()">Delete</button>
									</td></tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="row">
								<div class="col-md-12">
				                  <form class="form-inline" action="searchTax">
				                   <table id="EditTable" class="table">
									<tbody>
										<tr>
											<td>
				                   <div class="form-group">
				                      <label for="email">Search By TaxName :</label>
				                      <input type="text" class="form-control" name="searchtaxname" value="${searchtax}" id="searchtaxname"/>
				                    </div>
				                    <input type="submit" id="searchtax" class="btn btn-primary btn-sm" value="search">
				                    </td>
				                    </tr>
				                    </tbody>
				                    </table>
				                    </form>
				                </div>
			                </div>
					<%-- <div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="searchTax">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                    <div class="form-group">
                      <label for="email">Search TaxName:</label>
                      <input type="text" class="form-control" name="taxname" value="${searchtax}" id="taxname"/>
                    </div>
                    <button type="submit" id="taxname" class="btn btn-primary btn-sm">Search</button>
                    </td>
                    </tr>
                    </tbody>
                    </table>
                  </form>
                </div>
                </div> --%>
					<div class="col-md-3"></div>
					<table id="EditTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<!-- <th></th> -->
										<th>Tax Id</th>
										<th>Tax Name</th>
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
									<c:forEach var="tax" items="${taxList}">
										<tr data-toggle="tooltip" data-placement="top" title="T0 Edit click on the button">
											<td>${tax.getTaxmasterid()}</td>
											<td>${tax.getTaxname()}</td>
											<td><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>
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
function savetax() {
	var name=document.getElementById("taxname").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Tax Name");
		document.getElementById("taxname").focus();
		return false;
		}
	var id = document.getElementById("taxmasterid").value; 
	var taxName= document.getElementById("taxname").value;
	$.ajax({
		type : "GET",
		async : false,
		url : "savetax??taxmasterid=" + id + "&&taxname="+ taxName,
		success : function(response) {
			alert(response);
			location.reload();

		}
	});
	 $('#taxname').val("");
	
	
	}

$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var taxid = $row[0].children[0].innerHTML;
	    var taxname = $row[0].children[1].innerHTML;
		$('#taxname').val(taxname);
		$('#taxmasterid').val(taxid);
		$('#edit').prop("disabled", false);
	    $('#delete').prop("disabled", false);
	    $('#save').prop("disabled", true);
	});
});

function edittax(){
	$('#serviceform').attr('action', 'editTax');
	$('#serviceform').submit();
}
function deletetax(){
	$('#serviceform').attr('action', 'deleteTax');
	$('#serviceform').submit();
	
	setTimeout(function() {
		$('#message').fadeOut('fast');
	}, 2000);
}
</script>

