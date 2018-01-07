<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Ticket Review Report</small>
			</h1>
		</div>
	</div>

	<form action="ticketsGenerateList" method="post">
		<div class="row">

			<div class="col-md-12">

				<div class="form-group">
					<div class="col-md-2">
						<label>From Date</label>
					</div>
					<div class="col-md-2">
						<input type="date" class="form-control" id="fromdate"
							name="fromdate" value="${fromdate}" />
					</div>
					<div class="col-md-2">
						<label>To Date</label>
					</div>
					<div class="col-md-2">
						<input type="date" class="form-control" id="todate" name="todate"
							value="${todate}" />
					</div>
					<div class="col-md-2">
						<label>Department</label>
					</div>
					<div class="col-md-2">
						<select class="form-control input-sm" name="deptMode"
							id="department">
							<option>Select</option>
							<c:forEach var="deptMode" items="${departmentnames}">
								<c:choose>
									<c:when test="${departmentid==deptMode.getDepartmentid()}">
										<option value="${deptMode.getDepartmentid()}" selected>${deptMode.getDepartmentname()}</option>
									</c:when>
									<c:otherwise>
										<option value="${deptMode.getDepartmentid()}">${deptMode.getDepartmentname()}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">

				<div class="col-md-2">
					<label>Status Type</label>
				</div>

				<div class="col-md-2">
					<select class="form-control input-sm" name="statusMode"
						id="statusMode">
						<option>Select</option>
						<c:forEach var="statusMode" items="${statusname}">
							<c:choose>
								<c:when test="${statusid==statusMode.getStatusid()}">
									<option value="${statusMode.getStatusid()}" selected>${statusMode.getStatusdescription()}</option>
								</c:when>
								<c:otherwise>
									<option value="${statusMode.getStatusid()}">${statusMode.getStatusdescription()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="col-md-2">
					<label>Complaint Source Type</label>
				</div>

				<div class="col-md-2">
					<select class="form-control input-sm" name="ticketMode"
						id="ticketMode">
						<option>Select</option>
						<c:forEach var="ticketMode" items="${tickettype}">
							<c:choose>
								<c:when test="${tickettypeid==ticketMode.getTickettypeid()}">
									<option value="${ticketMode.getTickettypeid()}" selected>${ticketMode.getTickettypedescription()}</option>
								</c:when>
								<c:otherwise>
									<option value="${ticketMode.getTickettypeid()}">${ticketMode.getTickettypedescription()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<label>Ticket count:</label>${totaltickets} &nbsp
				</div>
				<div class="col-md-2">
					<label>Open:</label>${opentickets}</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<div align="left">
					<button type="submit" class="btn btn-primary btn-sm"
						id="searchevent">Search</button>
				</div>
			</div>
		</div>

	</form>


	<hr>
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Ticketno</th>
							<th>Date</th>
							<th>Complaints</th>
							<th>Department</th>
							<!-- <th>Assigned To</th> -->
							<th>Status</th>
							<th>Ticket Type</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tickets" items="${ticketslist}">
							<tr id="trow">
								<td>${tickets.getTicketno()}</td>
								<td>${tickets.getTicketdate()}</td>
								<td>${tickets.getComplaint()},(${tickets.responsemaster.responsedescription})</td>
								<td>${tickets.getDepartmentmaster().getDepartmentname()}</td>
								<%-- <td>${tickets.getAssignedto()}</td> --%>
								<td>${tickets.getStatusmaster().getStatusdescription()}</td>
								<td>${tickets.getTickettypemaster().getTickettypedescription()}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>



<div class="row">

	<form id="exportform" method="post">
		<div class="col-md-3">
			<select class="form-control" id="generatetype" name="generatetype">
				<option>Select Generate To</option>
				<option value="pdf">PDF</option>
				<option value="excel">Excel Sheet</option>
			</select>
		</div>
		<div class="col-md-3">
			<input type="button" value="Export" class="btn btn-primary btn-sm"
				id="Export" onclick="msg()" />
		</div>
		<div class="col-md-3"></div>
	</form>
</div>
</div>


<script>
	var fromday = new Date().toISOString().split('T')[0];
	document.getElementsByName("fromdate")[0].setAttribute('max', fromday);
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("todate")[0].setAttribute('max', today);

	function msg() {
		var fromdate = document.getElementById("fromdate").value;
		var todate = document.getElementById("todate").value;
		var dept = document.getElementById("department").value;
		var status = document.getElementById("statusMode").value;
		var srctype = document.getElementById("ticketMode").value;
		var generatetype = document.getElementById("generatetype").value;
		document.getElementById("exportform").action = 'generateTicketReports?fromdate='
				+ fromdate
				+ '&todate='
				+ todate
				+ '&dept='
				+ dept
				+ '&status='
				+ status + '&srctype=' + srctype + '&generate=' + generatetype;
		document.getElementById("exportform").submit();
	}
</script>