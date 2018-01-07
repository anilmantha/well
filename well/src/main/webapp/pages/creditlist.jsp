<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Credit List</small>
			</h1>
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
		<form method="post" id="submitcreditlist">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Credit List Form</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12">
                			<table class="table table-no-bordered">
                 				<tbody>
                 					<tr>
                 						<td width="12%"><label>Corporate</label></td>
					                    <td width="35%">
					                    	<select class="form-control" name="corporateid" id="corporateid">
					                        	<option>Select Corporate</option>
					                        	<c:forEach var="corporate" items="${corporateList}">
					                        		<option value="${corporate.getCorporateid()}">${corporate.getCorporatename()}</option>
					                        	</c:forEach>
					                        </select></td>
					                    <td width="5%">&nbsp;</td>
					                    <td width="12%"><label>Credit Amount</label></td>
					                    <td width="36%"><input type="number" onkeypress="return isNumberKey(event,this)" class="form-control" id="creditamount" name="creditamount" required></td>
                 					</tr>
                 					<tr>
                 						<td><label>Valid From</label></td>
					                    <td><input type="date" onchange="changeValidToDate()" class="form-control" id="validfrom" name="validfrom" required="required"></td>
					                    <td width="5%">&nbsp;</td>
					                    <td><label>Valid to</label></td>
					                    <td><input type="date" class="form-control" id="validto" name="validto" required="required"></td>
					                </tr>
					                <tr>
                 						
										<div class="form-group">
											<td width="12%"><label>Black List</label></td>
											<td class="col-md-3"><input type="checkbox"
												class="checkbox " id="blacklisted" name="blacklisted"></td>
										</div>
										  <td width="5%">&nbsp;</td>
										<td><label>Black List Reason</label></td>
					                    <td><input type="text" class="form-control" id="blacklistreason" name="blacklistreason" required="required"></td>
					                </tr>
                 					<tr>
					                    <td><label>Remarks</label></td>
					                    <td><textarea class="form-control" rows="5" id="remarks" name="remarks"></textarea></td>
					                </tr>
					        	</tbody>
                 			</table>
                 		</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="pull-right">
					<input type="button" class="btn btn-primary btn-sm" id="savecreditlist" value="Save" onclick="savecredit()">
                </div>
			</div>
		</form>
	</div>
	<br>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Credit List Table</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-12">
               			<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Credit List Id</th>
								<th>Corporate Name</th>
								<th>Credit List Amount</th>
								<th>Valid From</th>
								<th>Valid To</th>
								<th>Blacklist(True/False)</th>
								<th>Blacklist Reason</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="addrowstextfields">
							<c:forEach var="creditlist" items="${creditListDetails}">
								<tr>
									<td>${creditlist.creditlistid}</td>
									<td>${creditlist.corporatemaster.corporatename}</td>
									<td>${creditlist.creditamount}</td>
									<td>${creditlist.validfrom}</td>
									<td>${creditlist.validto}</td>
									<td>${creditlist.blacklisted}</td>
									<td>${creditlist.blacklistreason}</td>
									<td class="text-center"><button class="use-address"><i class="fa fa-pencil text-success" aria-hidden="true"></i></button>
									<button class="deletecredit"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td>
					        	</tr>
					        </c:forEach>
						</tbody>
						</table>
                	</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("validfrom")[0].setAttribute('min', today);
	document.getElementById("validfrom").value=today;
	document.getElementsByName("validto")[0].setAttribute('min', today);

	setTimeout(function() {
	    $('#message').fadeOut('fast');
	}, 2000);
	
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
		
		function changeValidToDate(){
			var today = document.getElementById("validfrom").value;
			document.getElementById("validto").value=today;
			document.getElementsByName("validto")[0].setAttribute('min', today);
		}
		
		$(function(){
			$(".use-address").click(function() {
			    var $row = $(this).closest("tr");
			    var id = $row[0].children[0].innerHTML;
			    var corporatename = $row[0].children[1].innerHTML;
			    var amount = $row[0].children[2].innerHTML;
			    var validfrom = $row[0].children[3].innerHTML;
			    var validto = $row[0].children[4].innerHTML;
			    var blacklisted = $row[0].children[5].innerHTML;
			    var blacklistreason = $row[0].children[6].innerHTML;
			    
				$("option").filter(function() {
					return $(this).text() == corporatename;
				}).prop('selected', true);
			    $('#validfrom').val(validfrom);
			    $('#validto').val(validto);
			    $('#creditamount').val(amount);
			    if(blacklisted!=null)
			    	{
			    		$('#blacklisted').prop('checked', true);
			    	}
			    $('#blacklistreason').val(blacklistreason);
			     document.getElementsByName("validfrom")[0].setAttribute('min', validfrom);
			    
			    $('#corporateid').attr('readonly','readonly');
			});
		});
		
		function savecredit(){
			var name=document.getElementById("corporateid").value; 
			if(name==""||name==null)
				{
				alert("Please select Corporate");
				document.getElementById("corporateid").focus();
				return false;
				}
			var name=document.getElementById("creditamount").value; 
			if(name==""||name==null)
				{
				alert("Please Enter Credit Amount");
				document.getElementById("creditamount").focus();
				return false;
				}
			var name=document.getElementById("validfrom").value; 
			if(name==""||name==null)
				{
				alert("Please Enter ValidFrom Date");
				document.getElementById("validfrom").focus();
				return false;
				}
			var name=document.getElementById("validto").value; 
			if(name==""||name==null)
				{
				alert("Please Enter ValidTo Date");
				document.getElementById("validto").focus();
				return false;
				}
			var name=document.getElementById("blacklistreason").value; 
			if(name==""||name==null)
				{
				alert("Please Enter blacklistreason");
				document.getElementById("blacklistreason").focus();
				return false;
				}
			var id = $('#corporateid').val();
			$('#submitcreditlist').attr('action', 'saveCreditList');
			$('#submitcreditlist').submit();
		}
		
		$(function(){
			$(".deletecredit").click(function() {
			    var $row = $(this).closest("tr");
			    var id = $row[0].children[0].innerHTML;
			    if (confirm("Are you sure you want to delete this "+id+"!") == true) {
			    	$('#submitcreditlist').attr('action', 'deleteCreditList?deletecorpId='+id);
					$('#submitcreditlist').submit();
			    } else {
			        return false;
			    }
			});
		});
</script>