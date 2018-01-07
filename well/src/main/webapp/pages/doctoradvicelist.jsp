
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form   action="searchdoctrAdviceList" method="post">

    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Doctor Advice List</small> </h1>
          <!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
        </div>
      </div>
      
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Client Selection</h3>
            </div>
            <div class="panel-body">
               <div class="row">
                <div class="col-md-12">
                    <table>
                    <tr>
	                    <td>
	                    	<label for="email">Search Name</label>
	                    </td>
	                    <td>&nbsp;&nbsp;</td>
	                    <td>
	                    	<input type="text" class="form-control" id="firstname" name="firstname" value="${searchedName}">
	                    </td>
	                    <td>&nbsp;&nbsp;</td>
                		<td>
                			<input type="submit" class="btn btn-primary btn-sm" value="search">
                		</td>
                	</tr>
                	</table>
                </div>
              </div>
              <hr>
            
              <div class="row">
              	<div class="table-responsive padding-10">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>Appointment ID</th>
                  <th>Name</th>
                  <th>Package</th>
                  <th>Service</th>
                  <th>Staff</th>
                  <th>Appointment date</th>
                 <!--  <th>(Price + Tax)Total</th> -->
                </tr>
              </thead>
              <tbody>
               <c:choose>
				<c:when test="${msg!=null}">
					<tr class="text-success"><c:out value="${msg}"></c:out></tr>
				</c:when>
				<c:otherwise>
              <c:forEach var="doctoradvicelist" items="${customerList}">
                <tr>
                  <td><a href="doctoradvice?appId=${doctoradvicelist.appointment.appointmentid}&srvId=${doctoradvicelist.servicemaster.serviceid}&pkgId=${doctoradvicelist.packagemaster.packageid}">${doctoradvicelist.appointment.appointmentid}</a></td>
                  <td>${doctoradvicelist.appointment.guestmaster.name}</td>
                  <td>${doctoradvicelist.packagemaster.packageid}</td>
                  <td>${doctoradvicelist.servicemaster.serviceid}</td>
               	  <td>${doctoradvicelist.staffmaster.staffid}</td>
                  <td>${doctoradvicelist.appointment.bookingdate}</td>
                 <!--  <td>5820</td> -->
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
 </form>

 <!-- <script>
 $("#search").click(function (){
	 var name = document.getElementById('guestname').value;
	 alert("!!!!!!!!!!!!!"+name);
	 $.ajax({
			type : "POST",
			async : false,
			url : "searchdoctrAdviceList?guestname="+name,
			success : function(response) {
				
			}
		});
	 
	 
	 
 });
 
 </script> -->
 