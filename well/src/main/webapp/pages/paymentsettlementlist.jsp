
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- changes -->


    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Bill Settlement List</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">List of Bills</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline" action="searchbybillno">
                    <div class="form-group">
                      <label for="email">Search Bill Settlement By Bill No :</label>
                      <input type="text" class="form-control" name="billno" id="billno">
                    </div>
                    <button type="submit" id="billsearch" class="btn btn-primary btn-sm">Search</button>
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
                        <th>Bill No</th>
                        <th>Bill Generated Date</th>
                        <th>Guest Name</th>
                        <th>Bill amount</th>
                        <th>Due Amount</th>
                        <th>Staff Name</th>
                       </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="billmasterlist" items="${billmasterlist}">
                      <tr id="trow">
                        <td ><a href="billsettle?billNo=${billmasterlist.billno }">${billmasterlist.billno }</a></td>
                        <td><fmt:formatDate value="${billmasterlist.updateddate }" pattern="yyyy-MM-dd" /></td>
                        <td>${billmasterlist.guestmaster.name }</td>
                        <td>${billmasterlist.amount }</td>
                        <td>${billmasterlist.outstandingamount }</td>
                        <td>${billmasterlist.updatedby }</td>
                     </tr>
                     </c:forEach>
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
  
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 



