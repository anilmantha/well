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
					<h3 class="panel-title">Stock Group</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="stockgroupid" name="stockgroupid" value=""/>
								<tbody>
									<tr>
										<td><label>Stock Group Name</label></td>
										<td><input type="text" class="form-control" id="stockgroupname"
											name="stockgroupname"></td>
										<td>&nbsp;</td>
									</tr>
										<tr>
										<td><label>Description</label></td>
										<td><input type="text" class="form-control" id="description"
											name="description"></td>
										<td>&nbsp;</td>
									</tr>
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save" id="save" onclick="savestockgroup()">
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="edit" onclick="editstockgroup()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="delete" onclick="deletestockgroup()">Delete</button>
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="stockGroupSearchByName">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                    <div class="form-group">
                      <label for="email">Search StockGroup By Name :</label>
                      <input type="text" class="form-control" name="stockgroupname" value="${searchstockgroup}" id="stockgroupname"/>
                    </div>
                    <button type="submit" id="stockgroupname" class="btn btn-primary btn-sm">Search</button>
                    </td>
                    </tr>
                    </tbody>
                    </table>
                  </form>
                </div>
                </div>
                <div class="row"> -
               <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Stock Group Id</th>
								<th>Stock Group Name</th>
								<th>Description</th>
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
							<c:forEach var="stockgroup" items="${stockGroupList}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${stockgroup.stockgroupid}</td>
									<td>${stockgroup.stockgroupname}</td>
									<td>${stockgroup.description}</td>
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
	</div>
</div>
<script type="text/javascript">
$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var id = $row[0].children[0].innerHTML;
	    var stockgroupname = $row[0].children[1].innerHTML;
	    var description = $row[0].children[2].innerHTML;
	    
	    $('#stockgroupid').val(id);
	    $('#stockgroupname').val(stockgroupname);
	    $('#description').val(description);
	    $('#edit').prop("disabled", false);
	    $('#delete').prop("disabled", false);
	    $('#save').prop("disabled", true);
	    
	   /*  $('#servicename').attr('disabled','disabled');
		 $('#genderid').attr('disabled','disabled');
	    $('#preparetime').attr('disabled','disabled');
		 $('#servicetime').attr('disabled','disabled');
		 $('#waitingtime').attr('disabled','disabled');
		 $('#cleaningtime').attr('disabled','disabled');
		 $('#totaltime').attr('disabled','disabled');
		 $('#servicegroup').attr('disabled','disabled'); */
	});
});

function savestockgroup(){
	var name=document.getElementById("stockgroupname").value; 
	if(name==""||name==null)
		{
		alert("Please Enter stockgroup");
		document.getElementById("stockgroupname").focus();
		return false;
		}
	$('#serviceform').attr('action', 'saveStockGroup');
	$('#serviceform').submit();
}
function editstockgroup(){
	$('#serviceform').attr('action', 'editStockGroup');
	$('#serviceform').submit();
}
function deletestockgroup(){
	$('#serviceform').attr('action', 'deleteStockGroup');
	$('#serviceform').submit();
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script> 