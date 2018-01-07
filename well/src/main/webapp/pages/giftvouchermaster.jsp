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
					<h3 class="panel-title">Gift Voucher</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="giftvoucherid" name="giftvoucherid" value=""/>
								<tbody>
									<tr>
										<td><label>Gift Voucher Amount</label></td>
										<td><input type="text" class="form-control" id="giftvoucheramount"
											name="giftvoucheramount"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
									<td class="col-md-3"><label>Valid From</label></td>
									<td class="col-md-4"><input type="date"
										class="form-control" id="validfrom" onchange="changeValidToDate()" name="validfrom" /></td>
									<td class="col-md-6">&nbsp;</td>
								</tr>
								<tr>
									<td class="col-md-3"><label>Valid To</label></td>
									<td class="col-md-4"><input type="date"
										class="form-control" id="validto" name="validto" /></td>
									<td class="col-md-6">&nbsp;</td>
								</tr>
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="SaveorUpdate" onclick="savegiftvoucher()">
												<!-- <button type="button" class="btn btn-primary btn-sm" onclick="editgiftvoucher()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" onclick="deletegiftvoucher()">Delete</button> -->
									</div></td>
									<td>
													<button type="submit" class="btn btn-primary btn-sm">Clear</button>
												</td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="searchGiftVoucher">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                    <%-- <div class="form-group">
                      <label for="email">Search GiftVoucher Amount :</label>
                      <input type="text" class="form-control" name="giftvoucheramount" value="${searchgiftvoucher}" id="giftvoucheramount"/>
                    </div>
                    <button type="submit" id="giftvoucheramount" class="btn btn-primary btn-sm">Search</button> --%>
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
								<th>GiftVoucher Id</th>
								<th>GiftVoucher Amount</th>
								<th>Valid From</th>
								<th>Valid To</th>
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
							<c:forEach var="giftvoucher" items="${giftVoucherList}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${giftvoucher.giftvoucherid}</td>
									<td>${giftvoucher.giftvoucheramount}</td>
									<td>${giftvoucher.validfrom}</td>
									<td>${giftvoucher.validto}</td>
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
var today = new Date().toISOString().split('T')[0];
document.getElementsByName("validfrom")[0].setAttribute('min', today);
document.getElementById("validfrom").value=today;
document.getElementsByName("validto")[0].setAttribute('min', today);
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
	    var giftvoucheramount = $row[0].children[1].innerHTML;
	    var validfrom = $row[0].children[2].innerHTML;
	    var validto = $row[0].children[3].innerHTML;
		$('#giftvoucherid').val(id);
	    $('#giftvoucheramount').val(giftvoucheramount);
	    $('#validfrom').val(validfrom);
	    $('#validto').val(validto);
	    
	  $('#giftvoucheramount').attr('readonly','readonly');
		 /* $('#genderid').attr('disabled','disabled');
	    $('#preparetime').attr('disabled','disabled');
		 $('#servicetime').attr('disabled','disabled');
		 $('#waitingtime').attr('disabled','disabled');
		 $('#cleaningtime').attr('disabled','disabled');
		 $('#totaltime').attr('disabled','disabled');
		 $('#servicegroup').attr('disabled','disabled');  */
	});
});

function savegiftvoucher(){
	var name=document.getElementById("giftvoucheramount").value; 
	if(name==""||name==null)
		{
		alert("Please select GiftVoucherAmount");
		document.getElementById("giftvoucheramount").focus();
		return false;
		}
	var name=document.getElementById("validfrom").value; 
	if(name==""||name==null)
		{
		alert("Please select Valid From Date");
		document.getElementById("validfrom").focus();
		return false;
		}
	var name=document.getElementById("validto").value; 
	if(name==""||name==null)
		{
		alert("Please select Valid To Date");
		document.getElementById("validto").focus();
		return false;
		}
	$('#serviceform').attr('action', 'saveGiftVoucher');
	$('#serviceform').submit();
}

function changeValidToDate(){
	var today = document.getElementById("validfrom").value;
	document.getElementById("validto").value=today;
	document.getElementsByName("validto")[0].setAttribute('min', today);
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>