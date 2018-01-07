<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid"> 
<form   action="guestemaillist" method="get">  
  
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Client Email List</small> </h1>
        </div>
      </div>
      
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Client Email List</h3>
            </div>
            </div>
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
                 <th>Mobile</th>
                  <th>Email</th>
                
                </tr>
              </thead>
              <tbody>
             
              <c:forEach var="gmlist1" items="${guemali}">
                <tr>
                  <td>${gmlist1.guestid}</td>
                  <td>${gmlist1.name}</td>
            		<td>${gmlist1.mobile}</td> 
                  <td>${gmlist1.email}</td> 
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
              </div>
         
 
  </form>    
   </div> 

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
						id="Export" onclick="msg()"/>
				</div>
				<div class="col-md-3"></div>
			</form>
		</div>
	</div> 
</div>


<script>
	 function msg() {
		 
		 
		 var generatetype = document.getElementById("generatetype").value;
		document.getElementById("exportform").action = 'guestemailreport?generate='+ generatetype;
		document.getElementById("exportform").submit();
	} 
</script>







