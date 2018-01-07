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
					<h3 class="panel-title">Skill Master</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form action="" class="col-md-7" method="post" id="skillform">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							
							<input type="hidden" id="skillid" name="skillid" value=""/>
							<tr>
									<td><label>Skill Name</label>
									<input type="text" class="form-control" style="width: 500px;"  id="skillname"	name="skillname"></td>
									</tr>
							      <tr>
							      <td><input type="button" class="btn btn-primary btn-sm"
										value="Save" onclick="saveskills()">
								<input type="button" class="btn btn-primary btn-sm" value="Update"onclick="editskills()">
								<button type="button" class="btn btn-primary btn-sm" onclick="deleteskills()">Delete</button></td>
								</tr>
						</table>
						</form>
					</div>
					
					
					 <form class="form-inline" action="searchSkill">
					<div class="panel-body">
					<div class="row">
					<div class="col-md-11">
                 
                   <table id="EditTable" class="table">
				
					<tbody>
					<tr>
					<td> <label for="email">Search Skill</label>
                   <input type="text" class="form-control" name="skill123" id="skills" value="${skill123}"/>
                  <input type="submit" id="skill123" name="search" class="btn btn-primary btn-sm" value="Search" ></td>
					</tr>
					</tbody>
                    </table>
                  
                </div>
                </div>
                </div>
                <div class="row"> -
               <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Skill Id</th>
								<th>Skill name</th>
								<th>Valid</th>
								 <th>UpdatedBy</th>
                                <th>UpdatedDate</th>
                                <th>UpdatedIp</th>
                                 <th>Edit</th>
							
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						
							
						 <c:forEach var="li" items="${skillslist}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${li.skillid}</td>
									<td>${li.skillname}</td>
									<td>${li.valid}</td>
									<td>${li.updatedby}</td>
									<td>${li.updateddate}</td>
									<td>${li.updatedip}</td>
									<td><a href="#" class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
								</tr>
							</c:forEach>
							
					
						</tbody>
					  </table> 
					</div>
				</div>
				
				
				<div class="row">
          <div class="col-md-12">
           <div align="left">  
            <b>Pages:</b>
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
			&nbsp;View Of:<c:out value="${paging1.currentPageNum}"></c:out>/<b><c:out value="${paging1.noOfPages}"></c:out></b>
	     	</div> 						  	
           </div> 
          </div>
				
				
			</form>	
				
				
				
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var skillid = $row[0].children[0].innerHTML;
	    var skillname = $row[0].children[1].innerHTML;
	  
	    
	    $('#skillid').val(skillid);
	    $('#skillname').val(skillname);
	 

	    
	 
	});
});

function saveskills(){
	var name=document.getElementById("skillname").value; 
	if(name==""||name==null)
		{
		alert("Please Enter  skill Name");
		document.getElementById("skillname").focus();
		return false;
		}
	$('#skillform').attr('action', 'addskills');
	$('#skillform').submit();
}
function editskills(){
	var name1=document.getElementById("skillname").value; 
	if(name1==""||name1==null)
		{
		alert("Please Enter  Edit");
		document.getElementById("skillname").focus();
		return false;
		}
	$('#skillform').attr('action', 'editskills');
	$('#skillform').submit();
}
function deleteskills(){
	var name2=document.getElementById("skillname").value; 
	if(name2==""||name2==null)
		{
		alert("Please Enter  Edit");
		document.getElementById("skillname").focus();
		return false;
		}
	$('#skillform').attr('action', 'deleteskills');
	$('#skillform').submit();
}
setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);

</script> 