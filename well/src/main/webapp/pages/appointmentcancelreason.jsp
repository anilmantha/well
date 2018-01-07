<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Reasons</h1>

		</div>
	</div>

	<!-- Main Content Area -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Reasons</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<form action="">
							<div class="col-md-12">
								<label>Reason Name:</label> <select
									class="form-control forChosen" id="reasondetailsid"
									name="reasondetailsid" onchange="get()">
									<option value="">Select</option>
									<c:forEach var="reasons" items="${reasonlist}">
										<option value="${reasons.reasonid}">${reasons.description}</option>
									</c:forEach>
								</select>
							</div>
							<div>
								&nbsp;
								<div>
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>&nbsp;</th>
												<th>reason</th>
												<th>&nbsp;</th>
											</tr>
										</thead>
										<tbody id="addrowstextfields">
										</tbody>
									</table>
									<div class="col-md-12" id="hidebuttons"
										style="visibility: hidden;">
										<div class="pull-right">
											<input type="button" value="New"
												class="btn btn-primary btn-sm" onclick="addfields()" /> <input
												type="button" value="Save" class="btn btn-primary btn-sm"
												onclick="savenew()" /> <input type="button" value="Edit"
												class="btn btn-primary btn-sm" onclick="update()" /> <input
												type="button" value="Delete" class="btn btn-primary btn-sm"
												onclick="deleteReason()" />
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Main Content Area -->
<!-- /.container-fluid -->
<script type="text/javascript">
	function get() {

		var id = document.getElementById("reasondetailsid").value;
		$("#addrowstextfields").html('<tr></tr>');
		$
				.ajax({
					type : "GET",
					async : false,
					url : "listofreasons?reasondetailsid=" + id,
					success : function(response) {
						var length = response.length;
						for (var i = 0; i < length; i = i + 2) {
							var tr = '<tr class="se"><td><input type="checkbox" class="case" name="case" value=""/></td>'
									+ '<td><input type="text" class="form-control" value="'+response[i+1]+'" name="'+response[i+1]+'"></td><td><input type="hidden" class="form-control" name="'+response[i]+'" value="'+response[i]+'"/></td></tr>';

							$("#addrowstextfields tr:last").after(tr);
						}
					}
				});
		var tr = '<tr class="se"><td><input type="checkbox" class="case" name="case" value=""/></td><td><input type="text" class="form-control" value="" name="description"></td><td><input type="hidden" class="form-control" value="" name=""></td>';

		$("#addrowstextfields tr:last").after(tr);
		document.getElementById("hidebuttons").style.visibility = "visible";

	}
	function addfields() {
		var tr = '<tr><td><input type="checkbox" class="case" name="case" value=""/></td>'
				+ '<td><input type="text" class="form-control" value="" name="cancelreason"></td></tr><td><input type="hidden" class="form-control" value="" name=""></td>';
		$("#addrowstextfields tr:last").after(tr);
	}
	function savenew() {

		var id = document.getElementById("reasondetailsid").value;
		var description = $('#addrowstextfields input:checkbox:checked')
				.closest('tr')[0].children[1].children[0].value;

		$.ajax({
			type : "GET",
			async : false,
			url : "savereason?reasondetailsid=" + id + "&&description="
					+ description,
			success : function(response) {
				alert(response);

			}
		});
		get();
	}
	function update() {
		var id = document.getElementById("reasondetailsid").value;
		var description = $('#addrowstextfields input:checkbox:checked')
				.closest('tr')[0].children[1].children[0].value;
		var reasonid = $('#addrowstextfields input:checkbox:checked').closest(
				'tr')[0].children[2].children[0].value;
		$.ajax({
			type : "GET",
			async : false,
			url : "updatereason?reasondetailsid=" + id + "&&description="
					+ description + "&&reasonid=" + reasonid,
			success : function(response) {
				alert(response);
			}
		});
		get();
	}
	function deleteReason() {
		var id = document.getElementById("reasondetailsid").value;
		var description = $('#addrowstextfields input:checkbox:checked')
				.closest('tr')[0].children[1].children[0].value;
		var reasonid = $('#addrowstextfields input:checkbox:checked').closest(
				'tr')[0].children[2].children[0].value;
		$.ajax({
			type : "GET",
			async : false,
			url : "deletereason?reasondetailsid=" + id + "&&description="
					+ description + "&&reasonid=" + reasonid,
			success : function(response) {
			}
		});
		get();
}
</script>