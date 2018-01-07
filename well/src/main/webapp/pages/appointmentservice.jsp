<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      <div id="alert">
      </div>
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Appointment</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
      <div id="alert">
      </div>
      
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Services in Appointment</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Appointment Id</th>
                        <th>Guest Id</th>
                        <th>Guest Name</th>
                        <th>Date of appointment</th>
                   		<th>Appointment Status</th>
                       </tr>
                    </thead>
                    <tbody>
                     <c:forEach var="servicelist" items="${appointmentserviceslist}">
                     <c:if test="${servicelist.appointmentId!=null}">
                      <tr id="appointmentdetails">
                       <td>${servicelist.appointmentId}</td>
                        <td> ${servicelist.guestId}</td>
                        <td> ${servicelist.guestName}</td>
                        <td> ${servicelist.dateOfAppontment}</td>
                         <td> ${servicelist.appointmentStatus}</td>
                      </tr>
                      </c:if>
                      </c:forEach>
                     </tbody>
                  </table>
                </div>
              </div>
       		<div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Service Id</th>
                        <th>Service Name</th>
                        <th>Staff Id</th>
                        <th>Staff Name</th>
                   		<th>Start Time</th>
                   		<th>End Time</th>
                   		<th>Status</th>
                   		<th>Doctor's Advice(Y/N)</th>
                   		<th>Start/Stop</th>
                       </tr>
                    </thead>
                    <tbody id="servicedetails">
                     <c:forEach var="servicelist" items="${appointmentserviceslist}">
                     <c:if test="${servicelist.serviceId!=null}">
                      <tr id="trow">
                       <td>${servicelist.serviceId}</td>
                        <td> ${servicelist.serviceName}</td>
                        <td>${servicelist.staffId}</td>
                        <td>${servicelist.staffName}</td>
                        <td>${servicelist.startTime}</td>
                        <td>${servicelist.endTime}</td>
                        <td>${servicelist.serviceStatus}</td>
                        <c:if test="${servicelist.doctoradvice==true}">
                        <td>Yes</td>
                        </c:if>
                        <c:if test="${servicelist.doctoradvice!=true}">
                        <td>No</td>
                        </c:if>
                        <c:choose>
	                        <c:when test="${servicelist.serviceStatus=='Not started'}">
	                         	<td id="buttonevent"><input type="button" class="btn start btn-primary btn-sm" id="${servicelist.serviceId}" value="Start" onclick="start(${servicelist.serviceId},this.id)">
	                         	<input type="button" class="disabled stop btn btn-primary btn-sm" id="${servicelist.staffId}" value="Finished" onclick="stop(${servicelist.serviceId},this.id)">
	                         </c:when>
	                         <c:when test="${servicelist.serviceStatus=='Completed' || servicelist.serviceStatus=='Billed'}">
	                         	<td id="buttonevent"><input type="button" class="disabled btn start btn-primary btn-sm" id="${servicelist.serviceId}" value="Start" onclick="start(${servicelist.serviceId},this.id)">
	                         	<input type="button" class="disabled stop btn btn-primary btn-sm" id="${servicelist.staffId}" value="Finished" onclick="stop(${servicelist.serviceId},this.id)">
	                         </c:when>
	                         <c:otherwise>
	                         	<td id="buttonevent"><input type="button" class="btn disabled start btn-primary btn-sm" id="${servicelist.serviceId}" value="Start" onclick="start(${servicelist.serviceId},this.id)">
	                         	<input type="button" class="stop btn btn-primary btn-sm" id="${servicelist.staffId}" value="Finished" onclick="stop(${servicelist.serviceId},${servicelist.staffId})">
	                         </c:otherwise>
                         </c:choose>
                          </td>
                      </tr>
                      </c:if>
                      </c:forEach>
                     </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 
<script type="text/javascript">
function start(serviceid,startbuttonid)
{
	var appointmentid = $('#appointmentdetails')[0].children[0].innerText;
	$.ajax({
			type : "POST", 
			url : "startService?appointmentId="+appointmentid+"&&serviceId="+serviceid,
			async : false,
			success : function(response) {
				alert(response);
				location.reload();
				}
		});
	}

function stop(serviceid,stopbuttonid)
{
	var appointmentid = $('#appointmentdetails')[0].children[0].innerText;
	$.ajax({
			type : "POST", 
			url : "stopService?appointmentId="+appointmentid+"&&serviceId="+serviceid,
			async : false,
			success : function(response) {
				alert(response);
				location.reload();
				}
		});
}
</script>