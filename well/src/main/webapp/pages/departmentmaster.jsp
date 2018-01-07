<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--All Departments  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="container-fluid"> 
        <div class="row">
		  <div class="col-lg-12">
		   <h1 class="page-header"></h1>
		     </div>
	           </div> 
                       	<!-- add new department -->
              	 <form action="savedepartment" name= "myform" method="post">
	              <div class="row">
		            <div class="col-lg-12">
			          <div class="panel panel-default">
				        <div class="panel-heading">
					     <h3 class="panel-title"><b>Add New Department</b></h3>
				          </div>
				           <div class="panel-body">
				            <div class="col-md-11">
					          <input type="hidden" name="pageName" id="pageName" value="${pageName}">
						     <table width="100%" border="0" cellspacing="0" cellpadding="0"
							        class="table table-no-bordered">
				             <tbody>
							 <tr>
							 <td width="12%"><label>Department Name</label></td>
							 <td class="col-md-3"><input type="text"
								  class="form-control" id="departmentname" name="departmentname" /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							 <tr>
							 <td width="14%"><label>Description</label></td>
							 <td class="col-md-3"><input type="text"
								 class="form-control" id="description" name="description"  /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
						     <tr>
							 <td></td>
							 <td><button type="submit"  value="Save" id="search" name="search" onclick="return validations()" class="btn btn-primary btn-sm">Save</button>  <button type="reset" class="btn btn-primary btn-sm">Clear</button></td></td>
             				 <td></td>
                             </tr>
					        </tbody>
						   </table>
						
		                 </div>
		                </div>
			           </div>
		              </div>
	                </div>
<!-- Main Content Area -->
<!-- /.container-fluid -->


 
                     <!-- Searching Department -->


              <div class="container-fluid">
                <div class="row">
		         <div class="col-lg-12">
		          </div>
                   </div>
	                 <!-- Main Content Area -->        
	                  <div class="row">
                       <div class="col-lg-12">
                        <div class="panel-body">
                         <div class="row">
                          <div class="col-md-12">
                      <%--   <form action="listdepartments" method="post"> --%>
                          <div class="form-group"><tr><td></td>
                         <td><div class="col-md-2"><label>Department Name :</label></div></td>
                        <div class="col-md-2"><input type="text" class="form-control" id="deptname" name="deptname" /></div>
                       <div class="col-md-2"><input type="submit"  id="search" name="search" value="search" class="btn btn-primary btn-sm" class="pull-right"></div>
			          <div class="col-md-4"></div>
                     </div>
                   </div>
                  <div class="col-md-2"></div>
                 </div>
                </div>
               </div>
              </div>
              </div>
            
              
     
                      <!-- Departmentlist -->


    
           <div class="container-fluid"> 
             <div class="row">
		       <div class="col-lg-12">
		        <h1 class="page-header"></h1>
		         </div>
	              </div> 
                   <div class="row">
                    <div class="col-lg-12">
                     <div class="panel panel-default">
                     <div class="panel-heading">
                     <h3 class="panel-title"><b>DepartmentList</b></h3>
                     </div>
                     <div class="panel-body">
                     <div class="row">         
                     <div class="col-md-3"></div>
                     </div>
                     <div class="row">
                     <div class="col-md-12">
                     <div style="height:250px; overflow-y:scroll;">
                     <table class="table table-bordered table-hover" >
                     <thead>
                   <tr> 
                     <th>DepartmentId</th>
                     <th>DepartmentName</th>
                     <th>Description</th>
                     <th>Valid</th>
                     <th>UpdatedBy</th>
                     <th>UpdatedDate</th>
                     <th>UpdatedIp</th>
                     <th>Edit</th>
                   </tr>
                     <tbody>
                     <c:forEach var="ob" varStatus="status" items="${deptlists}">
                    <tr>
                     <td><c:out value="${ob[0]}"/></td>
                     <td><c:out value="${ob[1]}"/></td>
                     <td><c:out value="${ob[2]}"/></td>
                     <td><c:out value="${ob[3]}"/></td>
                     <td><c:out value="${ob[4]}"/></td>
                     <td><c:out value="${ob[5]}"/></td>
                     <td><c:out value="${ob[6]}"/></td> 
                     <td><a href="editdepartment?depId=${ob[0]}">Edit</a></td>
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
          
         
       
       
          
          <div class="col-md-11">
           <div align="left"> 
        &nbsp;<b>Pages:&nbsp;&nbsp;</b>
            <c:if test="${paging1.totalRecs != 0}">
             </c:if>			
			  <c:if test="${paging1.noOfPages > 1}">
			   <c:set var="currentPageIndex" value="${paging1.currentPageNum}" scope="page" />
			   	<c:set var="isLastPage" value="${(currentPageIndex % 3)}" scope="page" />
				  <c:choose>
				    <c:when test="${isLastPage == 0}">
					 <c:set var="pageBegin" value="${currentPageIndex - 2}" scope="page" />
					 </c:when>
					   <c:otherwise>
					     <c:set var="pageBegin" value="${currentPageIndex - (currentPageIndex % 3) + 1}" scope="page" />
					     </c:otherwise>
				         </c:choose>
				         <c:set var="pageEnd" value="${pageBegin + 2}" scope="page" />
				         <c:if test="${pageEnd > paging1.noOfPages}">
					     <c:set var="pageEnd" value="${paging1.noOfPages}" scope="page" />
				         </c:if>
				         <c:if test="${pageBegin > 3}">
				         <button type="submit" name="search" id="search" value="${pageBegin - 3}"> <<< </button>
				         </c:if>
				         <c:forEach begin="${pageBegin}" end="${pageEnd}" varStatus="i">
					     <c:choose>
					     <c:when test="${i.index == paging1.currentPageNum}">
					     <span class="page selectedPage"><c:out value="${i.index}" /></span>
					     </c:when>
					     <c:otherwise>
 						 <span class="page">
 						 <input type="submit" name="search" id="search" value="${i.index}"/>
 						 </span>
					     </c:otherwise>
				        </c:choose>
				       </c:forEach>
				      <c:if test="${pageEnd < paging1.noOfPages}">
				     <span>   &nbsp;&nbsp;
			        <button type="submit" name="search" id="search" value="${pageBegin + 3}"> >>> </button>
			       </span>
				  <span class="page"></span>
				 </c:if>  
			    </c:if>
			&nbsp;View Of:&nbsp;<c:out value="${paging1.currentPageNum}"></c:out>/<b><c:out value="${paging1.noOfPages}"></c:out></b>
	     	</div> 						  	
           </div> 
          </div>
		 </form>
        </div>
  
								

       

  <script>
 function validations()
 {
	 
	var name=document.getElementById("departmentname").value; 
	if(name==""||name==null)
		{
		alert("Please Enter  Department Name");
		document.getElementById("departmentname").focus();
		return false;
		}
	 var name=document.getElementById("description").value; 
	 if(name==""||name==null)
		{
		alert("Please Enter  Description");
		document.getElementById("description").focus();
		return false;
		}
	 
	 
 }
  </script>



        
