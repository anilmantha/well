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
					<h3 class="panel-title">Services</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="serviceid" name="serviceid" value=""/>
								<tbody>
									<tr>
										<td><label>Service Name</label></td>
										<td><input type="text" class="form-control" id="servicename"
											name="servicename"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Gender</label></td>
										<td><select class="form-control" name="genderid"
											id="genderid">
												<option>Select Gender</option>
												<c:forEach var="genderlist" items="${genderslist}">
													<option value="${genderlist.dropdowndetailsid}">${genderlist.description}</option>
												</c:forEach>
											</select>
										</td>

										<td>&nbsp;</td>
									</tr>						
									<tr>
										<td><label>Prepare Time</label></td>
										<td><input type="text" class="form-control" id="preparetime"
											name="preparetime"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Service Time</label></td>
										<td><input type="text" class="form-control" id="servicetime"
											name="servicetime"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Waiting Time</label></td>
										<td><input type="text" class="form-control" id="waitingtime"
											name="waitingtime"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Cleaning Time</label></td>
										<td><input type="text" class="form-control" id="cleaningtime"
											name="cleaningtime"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>TotalService Time</label></td>
										<td><input type="text" class="form-control" id="totaltime"
											name="totaltime"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Service Group</label></td>
										<td><select class="form-control" name="servicegroupid"
											id="servicegroupid">
												<option>Select ServiceGroup</option>
												<c:forEach var="servicegroup" items="${serviceGroupslist}">
													<option value="${servicegroup.servicegroupid}">${servicegroup.servicegroupname}</option>
												</c:forEach>
											</select>
										</td>

										<td>&nbsp;</td>
									</tr>				
									
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save" id="save" onclick="saveservice()">
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="edit" onclick="editservice()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="delete" onclick="deleteservice()">Delete</button>
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="serviceSearchByName">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                    <div class="form-group">
                      <label for="email">Search Service By Name :</label>
                      <input type="text" class="form-control" name="servicename" value="${searchname}" id="servicename"/>
                    </div>
                    <button type="submit" id="servicename" class="btn btn-primary btn-sm">Search</button>
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
								<th>Service Id</th>
								<th>Service Name</th>
								<th>Gender</th>
								<th>Prepare Time</th>
								<th>Service Time</th>
								<th>Waiting Time</th>
								<th>Cleaning Time</th>
								<th>TotalService Time</th>
								<th>Service Group</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						<c:choose>
							<c:when test="${listmessage!=null}">
								<tr><c:out value="${listmessage}"></c:out>
								</tr>
							</c:when>
							<c:otherwise>
							<c:forEach var="service" items="${servicesList}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${service.serviceid}</td>
									<td>${service.servicename}</td>
									<td>${service.getDropdowndetails().getDescription()}</td>
									<td>${service.preparetime}</td>
									<td>${service.servicetime}</td>
									<td>${service.waitingtime}</td>
									<td>${service.cleaningtime}</td>
									<td>${service.totalservicetime}</td>
									<td>${service.getServicegroupmaster().getServicegroupname()}</td>
									<td><a href="#" class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
								</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
					</div>
				</div>
				</div>
				<div class="row">
          <div class="btn-group">
           <div align="center"> 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Pages:&nbsp;&nbsp;</b>
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;View Of:&nbsp;&nbsp;<c:out value="${paging1.currentPageNum}"></c:out>/<b><c:out value="${paging1.noOfPages}"></c:out></b>
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
	    var gender = $row[0].children[2].innerHTML;
	    var preparetime = $row[0].children[3].innerHTML;
	    var servicetime = $row[0].children[4].innerHTML;
	    var waitingtime = $row[0].children[5].innerHTML;
	    var cleaningtime = $row[0].children[6].innerHTML;
	    var totaltime = $row[0].children[7].innerHTML;
	    var subgroup = $row[0].children[8].innerHTML;
	    
	    $('#serviceid').val(id);
	    $('#servicename').val(servicename);
	    $("option").filter(function() {
			return $(this).text() == gender;
		}).prop('selected', true);
	    $('#preparetime').val(preparetime);
	    $('#servicetime').val(servicetime);
	    $('#waitingtime').val(waitingtime);
	    $('#cleaningtime').val(cleaningtime);
	    $('#totaltime').val(totaltime);
	    $("option").filter(function() {
			return $(this).text() == subgroup;
		}).prop('selected', true);
	    $('#edit').prop("disabled", false);
	    $('#delete').prop("disabled", false);
	    $('#save').prop("disabled", true);
	    
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

function saveservice(){
	var name=document.getElementById("servicename").value; 
	if(name==""||name==null)
		{
		alert("Please Enter ServiceName");
		document.getElementById("servicename").focus();
		return false;
		}
	var name=document.getElementById("genderid").value; 
	if(name==""||name==null)
		{
		alert("Please Select Gender");
		document.getElementById("genderid").focus();
		return false;
		}
	$('#serviceform').attr('action', 'saveService');
	$('#serviceform').submit();
}
function editservice(){
	$('#serviceform').attr('action', 'editService');
	$('#serviceform').submit();
}
function deleteservice(){
	$('#serviceform').attr('action', 'deleteService');
	$('#serviceform').submit();
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>