<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Appointments List</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Appointments</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="appointmentsearch" method="post">
                    <div class="form-group">
                    <table>
                    <tr>
                     <td> <label for="email">AppointmentId :</label></td><td>&nbsp;</td>
                     <td> <input type="text" class="form-control" name="appointmentsid" id="appointmentsid" onkeypress="return isNumber(event)"></td>
                      <td>&nbsp;&nbsp;&nbsp;</td>
                      <td><label for="email">Guest Name :</label></td><td>&nbsp;</td>
                     <td> <input type="text" class="form-control" name="name" id="name"><br></td>
                      <td>&nbsp;&nbsp;&nbsp;</td>
                      <td><label for="email">Appointment Date :</label></td><td>&nbsp;</td>
                     <td> <input type="date" class="form-control" name="appointmentdate" id="appointmentdate"></td>
                       <td>&nbsp;&nbsp;&nbsp;</td>
                     <!--  <td><label for="email">Search  By Doctor Advice :</label>
                      <input type="text" class="form-control" name="doctoradvice" id="doctoradvice"></td> -->
                      
                      <td><label></label>
                      <button type="submit" id="searchappointment" class="btn btn-primary btn-sm">Search</button></td>
                      </tr>
                      
                      </table>
                      
                    </div>
                   
                  </form>
                </div>
                <div class="col-md-3"></div>
              </div>
              <hr>
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Appointment Id</th>
                        <th>Guest Name</th>
                        <th>Appointment Date</th>
                         <th>Appointment Time</th>
                        
                        <!--  <th>Booking Date</th>
                          <th>Doctor Advice(Y/N)</th> -->
                          <th>Status</th>
                     </tr>
                    </thead>
                    <tbody>
                    <c:if test="${appointmentList!=null}">
                    <c:forEach var="appointmentList" items="${appointmentList}">
                      <tr id="trow">
                        <td id="appointmentid"><a href="appointmentservice?appointmentId=${appointmentList[0]}">${appointmentList[0]}</a></td>
                        <td id="guestname">${appointmentList[1]}</td>
                        <td id="appointmentdate">${appointmentList[2]}</td>
                        <td id ="appointmenttime">${appointmentList[3]}</td>
                         
                         <%-- <td id="bookingdate">${appointmentList.bookingdate}</td> --%>
                         <%-- <c:if test="${appointmentList.doctoradvice==true}">
                         <td id="doctoradvice">Yes</td>
                         </c:if>
                         <c:if test="${appointmentList.doctoradvice==false}">
                         <td id="doctoradvice">No</td>
                         </c:if> --%>
                         <td id="status">${appointmentList[4]}</td>
                      </tr>
                      </c:forEach>
                      </c:if>
                     <c:if test="${msg!=null}">
                     ${msg}
                     </c:if>
                     </tbody>
                  </table>
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
    <script type="text/javascript">
    function isNumber(evt) {
    	
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode<48 || charCode>57)) {
            return false;
        }
        return true;
    }
    
    </script>