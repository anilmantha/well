<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="resources/css/charts09.css">
<script src="resources/js/charts/jquery.js"></script>

<script src="resources/js/charts/material-charts.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<style>
.pos-60 {
	position: relative;
	left: -40px;
}
</style>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-header">DashBoard</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-5 border-chart" style="margin-left: -19px;">
			<input type="hidden" id="open" value="${ticketopen}"> 
			<input type="hidden" id="close" value="${ticketclose}"> 
			<c:forEach var="depcount" items="${Deptcount}">
				<input type="hidden" class="opendeptcount" value="${depcount}">
			</c:forEach>
			<c:forEach var="dept" items="${Deptmaster}">
				<input type="hidden" class="deptnames"
					value="${dept.getDepartmentname()}">
			</c:forEach>
			<div id="PieChart" class=""></div>
		</div>
		<div class="col-md-5 border-chart">
			<div id="BarChart" class="pos-60"></div>
		</div>
	</div>
</div>

<script type="text/javascript" src="resources/js/DashBoard.js"></script>
<script type="text/javascript">
	var open = parseInt($('#open').val(), 10);
	var close = parseInt($('#close').val(), 10);
	parseInt("1000", 10);

	// Load the Visualization API and the piechart package.
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(drawChart);

	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.

	function drawChart() {
		var arr = [];
		//open ticket count is added to array and iterator loop 
		$(".opendeptcount").each(function() {
			var value = $(this).val();
			var number = Number(value.replace(/[^0-9\.]+/g, ""));
			arr.push(number);
		});
		//dept count is added to array and iterator in loop
		var dept = [];
		$(".deptnames").each(function() {
			dept.push($(this).val());
		});
		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Tickets Status');
		data.addColumn('number', 'Number of Tickets');
		data.addRows([ [ 'Open Tickets', open ], [ 'Closed Tickets', close ]]);

		var data3 = new google.visualization.DataTable();
		data3.addColumn('string', 'Departments');
		data3.addColumn('number', 'Complaints Raised');
		var i = 0;
		//static adding of graph
		data3.addRows([ [ dept[i], arr[i] ], [ dept[i + 1], arr[i + 1] ],
				[ dept[i + 2], arr[i + 2] ], [ dept[i + 3], arr[i + 3] ] ]);

		var options = {
			'title' : 'Overview of Tickets',
			'width' : 550,
			'height' : 550
		};

		var options3 = {
			'title' : 'Department Wise Pending Tickets',
			'width' : 700,
			'height' : 500
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('PieChart'));
		chart.draw(data, options);

		var chart3 = new google.visualization.ColumnChart(document
				.getElementById('BarChart'));
		chart3.draw(data3, options3);

	}
</script>
