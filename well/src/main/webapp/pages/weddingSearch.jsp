<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1></h1>
		</div>
	</div>

	<br>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Client Wedding Date Search</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel-body">
			<div class="col-md-1">
				<label>Search By Month :</label>
			</div>
			<div class="col-md-2">
				<select id="month" name="month">

					<option value="">Month</option>

					<c:set var="count" value="1" scope="page" />

					<c:forEach var="month" items="${month}">

						<option value="${count}">${month.value}</option>

						<c:set var="count" value="${count + 1}" scope="page" />

					</c:forEach>

				</select>
			</div>
			<div class="col-md-1">
				<label>From Date:</label>
			</div>
			<div class="col-md-2">
				<select id="fromDate" name="fromDate">
					<c:forEach var="i" begin="1" end="31">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-1">
				<label>To Date :</label>
			</div>
			<div class="col-md-2">
				<select id="toDate" name="toDate">
					<c:forEach var="i" begin="1" end="31">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel-body">
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary btn-sm"
					onclick="getData()">Search</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive padding-10">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Customer ID</th>
							<th>Name</th>
							<th>wedding date</th>
							<th>Mobile</th>
							<th>Email</th>
							<th>Last Visit</th>
							<th>No.of Visits</th>
						</tr>
					</thead>
					<tbody id="addrowdata"></tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		function getData() {

			var month = document.getElementById("month").value;
			
			var fromdate = document.getElementById("fromDate").value;
			
			var todate = document.getElementById("toDate").value;

			/* alert("HIIII"+month);
			alert("HIIII"+fromdate);
			alert("HIIII"+todate); 
			 */
			$("#addrowdata").html('<tr></tr>');
			$.ajax({
				type : "GET",
				async : false,
				url : "WeddingDateSearch?month=" + month + "&fromDate=" + fromdate
						+ "&toDate=" + todate,
				success : function(response) {

					alert("HIIIIIII" + response);
					for (var j = 0; j < response.length; j = j + 7) {
						var tr = '<tr><td>' + response[j] + '</td>' + '<td>'
								+ response[j + 1] + '</td>' + '<td>'
								+ response[j + 2] + '</td>' + '<td>'
								+ response[j + 3] + '</td>' + '<td>'
								+ response[j + 4] + '</td>' + '<td>'
								+ response[j + 5] + '</td>' + '<td>'
								+ response[j + 6] + '</td></tr>';
						$("#addrowdata tr:last").after(tr);

					}
				}
			});

		}
	</script>
	<div class="row">
		<div class="panel-body">
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
</div>


<script>
	 function msg() {
		var month = document.getElementById("month").value;
		var fromDate = document.getElementById("fromDate").value;
		var toDate = document.getElementById("toDate").value;
		var generatetype = document.getElementById("generatetype").value;
		document.getElementById("exportform").action = 'generateClientWDReports?month='+month+'&fromDate='+fromDate+'&toDate='+toDate+'&generate='+ generatetype;
		document.getElementById("exportform").submit();
	} 
</script>







