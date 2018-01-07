

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form   action="customerlist" method="post" >

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
              	<div class="col-md-3">
                <select class="form-control">
                	<option>Full Name</option>
                    <option>xyz</option>
                    <option>abc</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select></div>
                <div class="col-md-6">
                    
                    <div class="form-group">
                    <label for="email">Search Name</label>
                    <input type="text" class="form-control" id="email" name="search">
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Search</button>
                 
                </div>
                <div class="col-md-3"></div>
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
             
              <c:forEach var="record" items="${list}">
             <%--  <c:forEach var="record" items="${records}"></c:forEach> --%>
                <tr>
               
                <td><a href="appointment?name=${record.registerUser.firstName}">${record.registerUser.userId}</a></td>
                  <!-- <td>records.registerUser.userId</td> -->
                   <td>${record.registerUser.firstName}</td>
                  <td>${record.registerUser.gender}</td>
                  <td>${record.registerUser.dateOfBirth}</td>
               <td>${record.registerUser.mobileNumber}</td> 
               <td>${record.registerUser.email}</td> 
                  <td>${record.lasvisiteddate}</td>
                  <td>${record.numberOfVisits}</td> 
               
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
    
  
</form>

