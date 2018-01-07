

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form   action="customerlist" method="post">

    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Appointment Booking</small> </h1>
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
                  <th>Customer ID</th>
                  <th>Name</th>
                  <th>Gender</th>
                  <th>DOB</th>
                  <th>Mobile</th>
                  <th>Email</th>
                  <th>Last Visit</th>
                  <th>No.of Visits</th>
                </tr>
              </thead>
              <tbody>
             <c:choose>
				<c:when test="${msg!=null}">
					<tr class="text-success"><c:out value="${msg}"></c:out></tr>
				</c:when>
				<c:otherwise>
	              <c:forEach var="record" items="${records}">
	                <tr>
	                  <td><a href="appointment?name=${record.name}&id=${record.guestid}">${record.guestid}</a></td>
	                  <td>${record.name}</td>
	                  <td>${record.dropdowndetailsByGenderid.description}</td>
	                  <td>${record.dob}</td>
	                  <td>${record.mobile}</td> 
	                  <td>${record.email}</td> 
	                  <td>${record.lastvisitdate}</td>
	                  <td>${record.noofvisits}</td> 
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
   
</form>