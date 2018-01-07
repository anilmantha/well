<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Staff List</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Staffs</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
              </div>
              <hr>
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                      <th></th>
                        <th>Staff Name</th>
                        <th>Email</th>
                        <th>Gender</th>
                        <th>DOB</th>
                        <th>Mobile</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Country</th>
                        <th>Pin code</th>
                        <th>Nationality</th>
						 <th>Designation</th>
                         <th>Department</th>
                        <th>Week Off</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="staff" items="${staffList}">
                      <tr id="trow">
                      <td><a href="openstaffeditpage?staffid=${staff.staffid}">${staff.staffid}</td></a>
                        <td>${staff.getStaffname()}</td>
                        <td>${staff.getEmail()}</td>
                        <td>${staff.getDropdowndetails().getDescription()}</td> 
                        <td>${staff.getDob()}</td> 
                         <td>${staff.getMobile()}</td> 
                        <td>${staff.getAddress()}</td> 
                        <td>${staff.getCitymaster().getCityname()}</td> 
                        <td>${staff.getStatemaster().getStatename()}</td> 
                        <td>${staff.getCountrymaster().getCountryname()}</td> 
                        <td>${staff.getPincode()}</td> 
                        <td>${staff.getNationality()}</td> 
                        <td>${staff.getDesgination()}</td> 
                        <td>${staff.getDepartmentmaster().getDepartmentname()}</td> 
                        <td>${staff.getWeekoff()}</td> 
                      </tr>
					 </c:forEach>
                     </tbody>
                  </table>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4">
                  <div class="pull-left">
                    <ul class="pagination pagination-sm">
                      <li><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 






