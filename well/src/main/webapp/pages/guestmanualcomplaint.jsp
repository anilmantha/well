<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- changes -->

<script>
var i="${msg}";
var length=i.length;
if(length==0)
	{
	}
else
	{
	alert(i);
	location.href="savemanualcomplaint123";
	}
</script>
    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Complaint Page</small> </h1>
          </div>
      </div>
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Complaint Information</h3>
            </div>
           <%--  <c:if test="${msg!=null}">
              ${msg}
            </c:if> --%>
            <div class="panel-body">
            <form action="savemanualcomplaint" method="post">
             <div class="row">
                <div class="col-md-12">
                <div class="table-responsive padding-10">
                  <table class="table table-no-bordered">
                    <tbody>
                      <tr>
                        <td class="col-md-2"><label for="email">Guest Name</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="guestName" id="guestName" value="${billMaster.guestmaster.name}" readonly="readonly"></td>
                        <td class="col-md-4"><input type="hidden" class="form-control" name="guestId" id="guestId" value="${billMaster.guestmaster.guestid}" ></td>
                        <td class="col-md-3">&nbsp;</td>
                       </tr>
                        <tr >
                        <td class="col-md-2"><label for="email">BillNo :</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="billNo" id="billNo" value="${billMaster.billno}" readonly="readonly"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                     <tr >
                        <td class="col-md-2"><label for="email">Bill Amount</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="billAmount" id="billAmount" value="${billMaster.amount}" readonly="readonly"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Bill Date</label></td>
                        <td class="col-md-3" ><input type="text" class="form-control" name="billdate" id="billdate" value="<fmt:formatDate value="${billMaster.updateddate }" pattern="yyyy-MM-dd" />" readonly="readonly"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr >
                        <td class="col-md-2"><label for="email">Department Name</label></td>
                        <td class="col-md-3"><select class="form-control" name="departmentId" id="departmentId" >
				               <option value="">Select</option>
				               <c:forEach var="departmentlist" items="${departmentmasterList}">
					                 <option value="${departmentlist.departmentid}">${departmentlist.departmentname}</option>
					           </c:forEach>
				           </select></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                        
                     </tr>
                    <tr>
                     <td class="col-md-2"><label for="email">Complaint</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="complaint" id="complaint"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"><label for="email">Remarks</label></td>
                        <td class="col-md-3"><input type="text" class="form-control" name="remarks" id="remarks"></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     <tr>
                     <td class="col-md-2"></td>
                        <td class="col-md-3"><div class="pull-right"><button type="submit" class="btn btn-primary btn-sm"
											id="add">Submit</button> <input type="reset"
										class="btn btn-primary btn-sm" value="Clear" /></div></td>
                        <td class="col-md-4">&nbsp;</td>
                        <td class="col-md-3">&nbsp;</td>
                     </tr>
                     </tbody>
                  </table>
                </div>
                
                
                </div>
              </div>
              </form>
              
            </div>
          </div>
        </div>
      </div>
  
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 

