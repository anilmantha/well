<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="container-fluid"> 
    <form action="billsSearchByDate" method="post">
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Bill Setteled List</small> </h1>
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
                <div class="table-responsive padding-10">
                  <table class="table">
                    <tbody>
                     	<tr><td class="pull-right"><label>Date</label></td>
                     	<td class="col-md-3"><input type="date" class="form-control" id="fromdate" name="fromdate" value="${fromdate}" /></td>
                     	<td><button type="submit" class="btn btn-primary btn-sm"
										id="searchevent" name="search" value="search">Search</button></td>
                     	</tr>
                    </tbody>
                  </table>
                </div>
              </div>
       		<div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Bill No</th>
                        <th>Guest name</th>
                        <th>Bill amount</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="billsettledlist" items="${billsettledlist}">
							<tr id="trow">
							<td><a href="customerfeedback?billno=${billsettledlist.getBillno()}&&guestid=${billsettledlist.getGuestmaster().guestid}&&formid=${formid}&&sbuid=1&&feedbackformid=">${billsettledlist.getBillno()}</a></td>
							<td>${billsettledlist.getGuestmaster().getName()}</td>
							<td>${billsettledlist.getAmount()}</td>
							</tr>
							</c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </form>
    </div>
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 