<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-header">
				<small>Ticket Closing</small>
			</h1>
		</div>
	</div>
	<div class="form-group">
		<form id="ticketform" action="updateTicketStatus" method="get">
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" id="ticketno" name="ticketNo"
						value="${ticketdetails.getTicketno()}">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-8">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="table bg-info">
									
									<tr>
										<td><label>Ticket No</label></td>
										<td id="ticketNo"><span>${ticketdetails.getTicketno()}</span></td>
									</tr>
									<tr>
										<td><label>Complaint</label></td>
										<td ><span>${ticketdetails.getComplaint()}</span></td>
									</tr>
									<tr>
										<td><label>Response</label></td>
										<td ><span>${ticketdetails.getResponsemaster().getResponsedescription()}</span></td>
									</tr>
									<tr>
										<td><label>Status</label></td>
										<td><span>${ticketdetails.getStatusmaster().getStatusdescription()}</span></td>
									</tr>
									<tr>
										<td><label>Priority</label></td>
										<td><span>${ticketdetails.getPrioritymaster().getPrioritydescription()}</span></td>
									</tr>
									<tr>
										<td><label>Department</label></td>
										<td><span>${ticketdetails.getDepartmentmaster().getDepartmentname()}</span></td>
									</tr>
									<tr>
										<td><label>Create Date</label></td>
										<td><span> ${ticketdetails.getTicketdate()}</span></td>
									</tr>
								</table>
							</div>
							<div class="col-md-8">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="table bg-info">
									<%-- <tr>
										<td><label>Assigned To</label></td>
										<td><span>${ticketdetails.getStaffmaster().getStaffname()}</span></td>
									</tr> --%>

									<tr>
										<td><label>E-mail</label></td>
										<td><span>${ticketdetails.getStaffmaster().getEmail()}</span></td>
									</tr>
									<tr>
										<td><label>Source</label></td>
										<td><span>${ticketdetails.getTickettypemaster().getTickettypedescription()}</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table width="100%" class="table">
							<tr>
								<td><div>
										<label>Remarks</label>
									</div></td>
								<td>
									<div class="col-md-12">
										<textarea rows="4" cols="" id="remarks" name="remarks"
											class="form-control" required></textarea>
									</div>
								</td>
								<td>
									<div>
										<label>Ticket Status</label>
									</div>
								</td>
								<td>
									<div class="col-md-12">
										<select class="form-control input-sm" name="ticketStatus"
											id="ticketStatus">
											<option value="">Select</option>

											<c:forEach var="ticketstatus" items="${statusname}">
												<option value="${ticketstatus.getStatusid()}">${ticketstatus.getStatusdescription()}</option>
											</c:forEach>
										</select>
									</div>
								</td>
							</tr>
                             <tr><td>&nbsp;</td><td class="pull-right">
                              <button type="submit"  class="btn btn-primary btn-sm" id="submit" >submit</button>
                              </td>
                              </tr>
						</table>
						
						 
					</div>
					      
				</div>
			</div>
		</form>
	</div>
</div>



<script>

	function msg() {
		var ticketno = $('#ticketno').val();
		var status = $('#ticketStatus').val();
		var remarks = $('#remarks').val();
		alert("submiting");
		var url = 'updateTicketStatus?ticketNo='+ticketno+'&&remarks='+remarks+'&&status='+status;
		$('#ticketform').attr('action', url).submit();
	}
</script>
