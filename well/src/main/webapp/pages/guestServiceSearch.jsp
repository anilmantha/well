<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form action="guestServicesSearch" method="Post">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Client Service Name List</small>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel-body">
					<div class="row">

						<div class="col-md-12">

							<div class="form-group">
								<div class="col-md-4">
									<label>From Date</label>
									<input type="date" class="form-control" id="fromdate"
										name="fromdate" required="required" value="${fromdate}" />
								</div>
								<div class="col-md-4">
									<label>To Date</label>
									<input type="date" class="form-control" id="todate"
										name="todate" value="${todate}" required="required" />
								</div>
								<div class="col-md-4">
									<label>Service Name</label>
									<select multiple class="form-control input-md" name="serviceNames" id="serviceNames">
										<option>Select</option>
										<c:forEach var="serviceList1" items="${serviceList}">
										<c:choose>
										<c:when test="${servicenames!=null}">
											<c:forEach var="servicename" items="${servicenames}">
												<c:choose>
													<c:when test="${serviceList1.serviceid==servicename}">
														<option value="${serviceList1.serviceid}" selected="selected">${serviceList1.servicename}</option>
													</c:when>
													<c:otherwise>
														<option value="${serviceList1.serviceid}">${serviceList1.servicename}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
												<c:otherwise>
													<option value="${serviceList1.serviceid}">${serviceList1.servicename}</option>
												</c:otherwise>
												</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				
							<div class = "col-md-2" >
								<div>
									<button type="submit" class="btn btn-primary btn-sm"
										id="searchevent">Search</button>
								</div>
							</div>
						</div>
					</div>
			
			
		
			</div>





<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Customer No</th>
						<th>Customer Name</th>
						<th>Service Name</th>
						<th>scheduled date</th>
						<th>Email</th>
						
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${message}!=null">
							<tr><c:out value="${message}"></c:out></tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="servicelist" items="${appointmentservicelist}">
								<tr id="trow">
									<td>${servicelist.getAppointment().getGuestmaster().guestid}</td>
									<td>${servicelist.getAppointment().getGuestmaster().name}</td>
									<td>${servicelist.getServicemaster().servicename})</td>
									<td>${servicelist.getSchappointdate()}</td>
									<td>${servicelist.getAppointment().getGuestmaster().getEmail()}</td>
		
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div> 

	<div class="row">
		<div class="panel-body">
				<div class="col-md-3">
					<select class="form-control" id="generatetype" name="generatetype">
						<option value="select">Select</option>
						<option value="pdf">PDF</option>
						<option value="excel">Excel Sheet</option>
					</select>
				</div>
				<div class="col-md-3">
					<input type="submit" value="Export" class="btn btn-primary btn-sm"
						id="Export"/>
				</div>
				<div class="col-md-3"></div>
			
		</div>
	</div> 
	</form>
</div>


<script type="text/javascript">
var found = [];
$("select option:selected").each(function() {
  found.push(this.value);
}); 

var founditem = [];
$("select option").each(function() {
	  if($.inArray(this.value, founditem) != -1) $(this).remove();
	  founditem.push(this.value);
});
$("select option").each(function() {
	for(var i=0;i<found.length;i++)
		{
			$("option").filter(function() {
				return $(this).val() == found[i];
			}).prop('selected', true);
		}
});

/* function msg() {
	
		var fromDate = document.getElementById("fromdate").value;
		
		var toDate = document.getElementById("todate").value;
		
		
		
		
		var serviceNames=[];
		for(var i = 0;i<$('#serviceNames option:selected').length;i++)
			
			{
			var k = $('#serviceNames option:selected')[1].value;
				serviceNames.push(k);
			}
		
		alert(serviceNames);
		var generatetype = document.getElementById("generatetype").value;
	
		document.getElementById("exportform").action = 'guestservicesreport?serviceNames='+serviceNames+'&fromDate='+fromDate+'&toDate='+toDate+'&generate='+ generatetype;
		document.getElementById("exportform").submit();
	}  */
	 
</script>
