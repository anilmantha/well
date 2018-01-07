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
					<h3 class="panel-title">Billing Instruction</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="billinginstructionid" name="billinginstructionid" value=""/>
								<tbody>
									<tr>
										<td><label>Instruction Description</label></td>
										<td><input type="text" class="form-control" id="instructiondescription"
											name="instructiondescription"></td>
										<td>&nbsp;</td>
									</tr>
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save" id="save" onclick="savebillinginstruction()">
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="edit" onclick="editbillinginstruction()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="delete" onclick="deletebillinginstruction()">Delete</button>
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="getsearchbillinginstruction">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                    <div class="form-group">
                      <label for="email">Search BillingInstruction :</label>
                      <input type="text" class="form-control" name="instructiondescription" value="${searchbillinginstruction}" id="instructiondescription"/>
                    </div>
                    <button type="submit" id="instructiondescription" class="btn btn-primary btn-sm">Search</button>
                    </td>
                    </tr>
                    </tbody>
                    </table>
                  </form>
                </div>
                </div>
                <div class="row">
                <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>BillingInstruction Id</th>
								<th>Instruction Description</th>
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
							<c:forEach var="billinginstruction" items="${billingInstructionList}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${billinginstruction.billinginstructionid}</td>
									<td>${billinginstruction.instructiondescription}</td>
									
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
	    var billinginstruction = $row[0].children[1].innerHTML;
		$('#billinginstructionid').val(id);
	    $('#instructiondescription').val(billinginstruction);
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

function savebillinginstruction(){
	var name=document.getElementById("instructiondescription").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Billing Instruction");
		document.getElementById("instructiondescription").focus();
		return false;
		}
	$('#serviceform').attr('action', 'saveBillingInstruction');
	$('#serviceform').submit();
}
function editbillinginstruction(){
	$('#serviceform').attr('action', 'editBillingInstruction');
	$('#serviceform').submit();
}
function deletebillinginstruction(){
	$('#serviceform').attr('action', 'deleteBillingInstruction');
	$('#serviceform').submit();
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>