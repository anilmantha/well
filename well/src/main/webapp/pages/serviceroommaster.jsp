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
					<h3 class="panel-title">ServiceRoom</h3>
					</br>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<form action="" class="col-md-7" method="post" id="serviceform">
							<input type="hidden" id="serviceroomid" name="serviceroomid" value=""/>
								<tbody>
									<tr>
									<td><label>Service Name</label></td>
									<td><select class="form-control" id="serviceid"
										name="serviceid">
											<option value="">Select Service</option>
											<c:forEach var="services" items="${serviceslist}">
												<option value="${services.serviceid}">${services.servicename}</option>
											</c:forEach>
									</select></td>

								</tr>		
									<tr>
									<td><label>Room No</label></td>
									<td><select class="form-control" id="roomid"
										name="roomid">
											<option value="">Select Room</option>
											<c:forEach var="rooms" items="${roomList}">
												<option value="${rooms.roomid}">${rooms.roomno}</option>
											</c:forEach>
									</select></td>

								</tr>		
									<tr>
										<td><label>Description</label></td>
										<td><input type="text" class="form-control" id="description"
											name="description"></td>
										<td>&nbsp;</td>
									</tr>
									<td>&nbsp;</td>
									<td><div class="pull-right">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save" id="save" onclick="saveserviceroom()">
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="edit" onclick="editserviceroom()">Edit</button>
												<button type="button" class="btn btn-primary btn-sm" disabled="disabled" id="delete" onclick="deleteserviceroom()">Delete</button>
									</div></td>
									<td>&nbsp;</td>
									</tr>
								</tbody>
							</form>
						</table>

					</div>
					<div class="row">
					<div class="col-md-12">
                  <form class="form-inline" action="searchServiceRoom">
                   <table id="EditTable" class="table">
					<tbody>
						<tr>
							<td>
                   <div class="form-group">
                      <label for="email">Search Service By Name :</label>
                      <input type="text" class="form-control" name="serviceName"  value="${searchserviceroom}" id="serviceName"/>
                    </div>
                    <input type="submit" id="serviceName" class="btn btn-primary btn-sm" value="search"></button>
                    </td>
                    </tr>
                    </tbody>
                    </table>
                     </form>
                </div>
                </div>
                
                <div class="row">
                <div class="col-md-12">
					<table id="EditTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>ServiceRoomId</th>
								<th>Service Name</th>
								<th>Room No</th>
								<th>Description</th>
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
							<c:forEach var="serviceroom" items="${serviceRoomMaster}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
									<td>${serviceroom.serviceroomid}</td>
									<td>${serviceroom.getServicemaster().getServicename()}</td>
									<td>${serviceroom.getRoommaster().getRoomno()}</td>
									<td>${serviceroom.description}</td>
									
									<td><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>
								</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
					</div>
				</div>
				<%-- <div class="row">
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
		</div> --%>
		 
				</div>
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
	    var roomno = $row[0].children[2].innerHTML;
	    var description = $row[0].children[3].innerHTML;
	    
	    $('#serviceroomid').val(id);
	    $("option").filter(function() {
			return $(this).text() == servicename;
		}).prop('selected', true);
	    $("option").filter(function() {
			return $(this).text() == roomno;
		}).prop('selected', true);
	    $('#description').val(description);
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

function saveserviceroom(){
	var name=document.getElementById("serviceid").value; 
	if(name==""||name==null)
		{
		alert("Please select service");
		document.getElementById("serviceid").focus();
		return false;
		}
	var name=document.getElementById("roomid").value; 
	if(name==""||name==null)
		{
		alert("Please select RoomNo");
		document.getElementById("roomid").focus();
		return false;
		}
	var name=document.getElementById("description").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Description");
		document.getElementById("description").focus();
		return false;
		}

	$('#serviceform').attr('action', 'saveServiceRoom');
	$('#serviceform').submit();
}
function editserviceroom(){
	$('#serviceform').attr('action', 'editServiceRoom');
	$('#serviceform').submit();
}
function deleteserviceroom(){
	$('#serviceform').attr('action', 'deleteServiceRoom');
	$('#serviceform').submit();
}

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);
</script>