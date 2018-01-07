<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Manufacturer List</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Manufacturers</h3>
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
                        <th>Manufacturer</th>
                        <th>Contact Person1</th>
                        <th>Address1</th>
                        <th>Phone1</th>
                        <th>Fax1</th>
                        <th>EmailId1</th>
                        <th>Contact Person2</th>
                        <th>Address2</th>
                        <th>Phone2</th>
                        <th>Fax2</th>
                        <th>EmailId2</th>
						 <th>Contact person3</th>
                         <th>Address3</th>
                        <th>Phone3</th>
                        <th>Fax3</th>
                        <th>EmailId3</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="manufacturer" items="${manufacturerList}">
                      <tr id="trow">
                      <td><a href="openmanufacturereditpage?manufacturerid=${manufacturer.manufacturerid}">${manufacturer.manufacturerid}</td></a>
                        <td>${manufacturer.getManufacturername()}</td>
                        <td>${manufacturer.getContactperson1()}</td>
                        <td>${manufacturer.getAddress1()}</td> 
                        <td>${manufacturer.getPhone1()}</td> 
                         <td>${manufacturer.getFax1()}</td> 
                        <td>${manufacturer.getEmail1()}</td> 
                        <td>${manufacturer.getContactperson2()}</td> 
                        <td>${manufacturer.getAddress2()}</td> 
                        <td>${manufacturer.getPhone2()}</td> 
                        <td>${manufacturer.getFax2()}</td> 
                        <td>${manufacturer.getEmail2()}</td> 
                        <td>${manufacturer.getContactperson3()}</td> 
                        <td>${manufacturer.getAddress3()}</td> 
                        <td>${manufacturer.getPhone3()}</td> 
                        <td>${manufacturer.getFax3()}</td> 
                        <td>${manufacturer.getEmail3()}</td> 
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






