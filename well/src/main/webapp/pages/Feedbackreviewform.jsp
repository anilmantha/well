<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Feedback Review Reports</small>
			</h1>
		</div>
	</div>
	<hr>
	
			
				
						<form action="searchbydate" method="post">
						<div class="row">
						<div class="panel-body">
							<div class="form-group">
								<div class="col-md-1">
									<label>From Date</label>
								</div>
								<div class="col-md-3">
									<input type="date" class="form-control" id="fromdate"name="fromdate" required="required" value="${fromdate}" />
								</div>
								<div class="col-md-1">
									<label>To Date</label>
								</div>
								<div class="col-md-3">
									<input type="date" class="form-control" id="todate"
										name="todate" value="${todate}" required="required" />
								</div>
								<div class="col-md-1">
									<label>Form Type</label>
								</div>
								<div class="col-md-3">
									<select class="form-control input-sm" name="formMode"
										id="formMode" required="required">
										<option value="">Select</option>
										<c:forEach var="formMode" items="${FormModeList}">
											<c:choose>
												<c:when test="${formid==formMode.getFormid()}">
													<option value="${formMode.getFormid()}" selected>

														${formMode.getFormdescription()}</option>
												</c:when>
												<c:otherwise>
													<option value="${formMode.getFormid()}">

														${formMode.getFormdescription()}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
								</div>
								</div>
								</div>
								<div class="row">

								<div class="col-md-2">
									<button type="submit" class="btn btn-primary btn-sm" id="searchevent" class="">Search</button>
								</div>
							</div>
						</form>
			
	<div class="row">
		<div class="col-lg-12">
			<h4>
				<b>Review Report</b>
			</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<td><b>FormId: </b></td>
							<td>${formid}</td>
						</tr>
						<tr>
							<td><b> Form Description</b></td>
							<td id="fdescription">${fdescription}</td>
						</tr>
						<tr>
							<td><b>Date Range : </b></td>
							<td>From &nbsp ${fromdate} &nbsp To &nbsp ${todate}</td>
						</tr>
						<tr>
							<td><b>Number of survey Forms Filled: </b></td>
							<td id="formcount">${formcount}</td>
						</tr>
						<tr>
							<td><b>Number of Attribute per Form:</b></td>
							<td id="qescount">${qescount}</td>
						</tr>
						<tr>
							<td><b>Total Attributes:</b></td>
							<td id="attribute">${attribute}</td>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>FormId</th>
									<th>Question</th>
									<th>Excellent(4)</th>
									<th>Very Good(3)</th>
									<th>Good(2)</th>
									<th>Fair(1)</th>
									<th>Poor(0)</th>
									<th>Very Poor(-1)</th>
									<th>Total Report</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="rr" varStatus="status" items="${qlist}">
									<tr class="responsecount">
										<td><c:out value="${rr.getFormmaster().getFormid()}" /></td>
										<td><c:out value="${rr.getQuestiondescription()}" /></td>
										<c:forEach var="response" items="${rcount}">
											<c:if
												test="${rr.getQuestionid()==response.getQuestionid()}">
												<td><c:out value="${response.ECount}" /></td>
												<td><c:out value="${response.VCount}" /></td>
												<td><c:out value="${response.GCount}" /></td>
												<td><c:out value="${response.FCount}" /></td>
												<td><c:out value="${response.PCount}" /></td>
												<td><c:out value="${response.VPCount}" /></td>
												<td><c:out value="${response.total}" /></td>
											</c:if>
										</c:forEach>
									</tr>
								</c:forEach>
								<tr>
									<td><b>Total Values: </b></td>
									<c:forEach var="sum" items="${sumlist}">
										<td></td>
										<td><c:out value="${sum.sumECount}" /></td>
										<td><c:out value="${sum.sumVCount}" /></td>
										<td><c:out value="${sum.sumGCount}" /></td>
										<td><c:out value="${sum.sumFCount}" /></td>
										<td><c:out value="${sum.sumPCount}" /></td>
										<td><c:out value="${sum.sumVPCount}" /></td>
									</c:forEach>
								</tr>

								<tr>
									<td><b>Scored Values: </b></td>
									<c:forEach var="pro" items="${prolist}">
										<td></td>
										<td><c:out value="${pro.proECount}" /></td>
										<td><c:out value="${pro.proVCount}" /></td>
										<td><c:out value="${pro.proGCount}" /></td>
										<td><c:out value="${pro.proFCount}" /></td>
										<td><c:out value="${pro.proPCount}" /></td>
										<td><c:out value="${pro.proVPCount}" /></td>
										<td><c:out value="${pro.sumtotal}" /></td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="alert alert-danger">
				<strong>Maximum Score:</strong> ${maxscore}
			</div>
			<div class="alert alert-info">
				<strong>Performance Index in(%):</strong> ${performindex}
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
				<input type="button" value="Export"
					class="btn btn-primary btn-sm" id="Export" onclick="msg()" />
			</div>
			<div class="col-md-3"></div>
		</form>
	</div>
	<p>&nbsp;</p>
</div>
<!--  End Container  -->

<script>
	var fromday = new Date().toISOString().split('T')[0];
	document.getElementsByName("fromdate")[0].setAttribute('max', fromday);
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("todate")[0].setAttribute('max', today);

	function msg() {
		var formid = document.getElementById("formMode").value;
		var fromdate = document.getElementById("fromdate").value;
		var todate = document.getElementById("todate").value;
		var generatetype = document.getElementById("generatetype").value;
		document.getElementById

		("exportform").action = 'generateReviewReports?formid=' + formid

		+ '&fromdate=' + fromdate + '&todate=' + todate + '&generate='
				+ generatetype;
		document.getElementById("exportform").submit();
	}
</script>