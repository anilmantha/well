
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
          <h1 class="page-header"><small>Appointment Booked</small> </h1>
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
              
            </div>
            <div class="panel-body">
              <div class="row">
              	
                    
                    
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
                   <th>Service Name</th>
                 <th>Room name</th>
                  <th>Staff Name</th>
                   <th>Date</th>
                   <th>Time</th>
                  
                </tr>
              </thead>
              <tbody>
             
              <%-- <c:forEach var="record" items="${bookedappointment}"> --%>
             <%--  <c:forEach var="record" items="${records}"></c:forEach> --%>
                <tr>
               
                 <%--  <td><a href="appointment?name=${record.registerUser.firstName}">${record.registerUser.userId}</a></td> --%>
                  <td>${bookedappointment.registerUser.userId}</td>
                   <td>${bookedappointment.registerUser.firstName}</td>
                  <td>${bookedappointment.servicename}</td>
                  <td>${bookedappointment.roomname}</td>
               <td>${bookedappointment.staffname}</td> 
               <td>${bookedappointment.arrivaldate}</td> 
                  <td>${bookedappointment.arrivaltime}</td>
                  <%-- <td>${record.numberOfVisits}</td>  --%>
               
               <%--  </c:forEach> --%>
                
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
