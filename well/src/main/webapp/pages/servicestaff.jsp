<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<!-- Main Content Area -->
	<c:set var="string" value="${message}" />
	<c:choose>
		<c:when test="${fn:contains(string, 'not')}">
			<div class="row">
				<div class="col-lg-12 col-md-offset-5">
					<h4>
						<span class="label label-danger" id="message"><c:out
								value="${message}" /></span>
					</h4>
				</div>
			</div>
		</c:when>
		<c:when test="${message==null}">
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-lg-12 col-md-offset-5">
					<h4>
						<span class="label label-success" id="message"><c:out
								value="${message}" /></span>
					</h4>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<br>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">ServiceStaff</h3>
				
				</div>
				
				<div class="panel-body">
					<div class="col-md-11">
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="servicestaffid" name="servicestaffid" value=""/>
								<tbody>
									<tr>
									<td><label>Service Name</label></td>
									<td><select class="form-control" id="serviceId"
										name="serviceId">
											<option value="">Select Service</option>
											<c:forEach var="services" items="${servicesList}">
												<option value="${services.serviceid}">${services.servicename}</option>
											</c:forEach>
									</select></td>

								</tr>		
									<tr>
									<td><label>Staff</label></td>
									<td><select class="form-control" id="staffid"
										name="staffid">
											<option value="">Select staff</option>
											<c:forEach var="stf" items="${staffList}">
												<option value="${stf.staffid}">${stf.staffname}</option>
											</c:forEach>
									</select></td>

								</tr>		
									<tr>
										<td><label>Description</label></td>
										<td><input type="text" class="form-control" id="description"
											name="description"></td>
										<td>&nbsp;</td>
									</tr>
								
									
									<td></td>
									<td>
									 <div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save" onclick="saveservicestaff()">
												<button type="button" class="btn btn-primary btn-sm" onclick="editservicestaff()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" onclick="deleteservicestaff()">Delete</button>
								</div>
									</td>
								
								</tbody>
							</form>
						</table>
</div>
					</div>
					
					
					 <form class="form-inline" action="searchServiceStaff">
					<div class="row">
					<div class="col-md-12">
                  
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                   <div class="form-group">
                      <label for="email">Search Service By Name :</label>
                      <input type="text" class="form-control" name="serviceName" id="serviceNames" value="${serviceName}"/>
                    </div>
                    <input type="submit" id="serviceName" name="search" class="btn btn-primary btn-sm" value="Search">
                    </td>
                    </tr>
                    </tbody>
                    </table>
                
                </div>
                </div>
                <div class="row">
                <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>ServiceStaffId</th>
								<th>Service Name</th>
								<th>Staff Name</th>
								<th>Description</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						<c:choose>
							<c:when test="${listmessage!=null}">
								<tr><c:out value="${listmessage}"></c:out>
								</tr>
						    	</c:when>
							   <c:otherwise>
							   <c:forEach var="servicestaff" items="${servicestaff}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${servicestaff.servicestaffid}</td>
									<td>${servicestaff.getServicemaster().getServicename()}</td>
									<td>${servicestaff.getStaffmaster().getStaffname()}</td>
									<td>${servicestaff.description}</td>
									<td><a href="#" class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
								</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
					</div>
				</div>
				
				<div class="row">
          <div class="btn-group">
           <div align="center"> 
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
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var id = $row[0].children[0].innerHTML;
	    var servicename = $row[0].children[1].innerHTML;
	    var staffname = $row[0].children[2].innerHTML;
	    var description = $row[0].children[3].innerHTML;
	    
	    $('#servicestaffid').val(id);
	    $("option").filter(function() {
			return $(this).text() == servicename;
		}).prop('selected', true);
	    $("option").filter(function() {
			return $(this).text() == staffname;
		}).prop('selected', true);
	    $('#description').val(description);
	  
	    
	   /*  $('#servicename').attr('disabled','disabled');
		 $('#genderid').attr('disabled','disabled');
	    $('#preparetime').attr('disabled','disabled');
		 $('#servicetime').attr('disabled','disabled');
		 $('#waitingtime').attr('disabled','disabled');
		 $('#cleaningtime').attr('disabled','disabled');
		 $('#totaltime').attr('disabled','disabled');
		 $('#servicegroup').attr('disabled','disabled'); */
	});
});

function saveservicestaff(){
	var name=document.getElementById("description").value; 
	if(name==""||name==null)
		{
		alert("Please Enter  Description");
		document.getElementById("description").focus();
		return false;
		}
	var name1=document.getElementById("staffid").value; 
	if(name1==""||name1==null)
		{
		alert("Please Select Staff");
		document.getElementById("staffid").focus();
		return false;
		}
	var name2=document.getElementById("serviceId").value; 
	if(name2==""||name2==null)
		{
		alert("Please select  Service");
		document.getElementById("serviceId").focus();
		return false;
		}
	$('#serviceform').attr('action', 'saveServiceStaff');
	$('#serviceform').submit();
}
function editservicestaff(){
	
	var name3=document.getElementById("servicestaffid").value; 
	if(name3==""||name3==null)
		{
		alert("Please Click On Edit");
		document.getElementById("servicestaffid").focus();
		return false;
		}
	
	$('#serviceform').attr('action', 'editServiceStaff');
	$('#serviceform').submit();
}
function deleteservicestaff(){
	
	var name4=document.getElementById("servicestaffid").value; 
	if(name4==""||name4==null)
		{
		alert("Please Click On Edit");
		document.getElementById("servicestaffid").focus();
		return false;
		}
	
	$('#serviceform').attr('action', 'deleteServiceStaff');
	$('#serviceform').submit();
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>