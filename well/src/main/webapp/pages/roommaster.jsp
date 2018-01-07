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
					<h3 class="panel-title">Room Master</h3>
					
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<tbody>
							
							<form action="" class="col-md-7" method="post" id="roomform">
							
							<input type="hidden" id="roomid" name="roomid" value=""/>
								
							 <tr>
							 <td width="12%"><label> Room Number</label></td>
							 <td class="col-md-3"><input type="text"
								  class="form-control" id="roomnum" name="roomnum" /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							 	 <tr>
							 <td width="12%"><label> Room Description</label></td>
							 <td class="col-md-3"><input type="text"
								  class="form-control" id="roomname" name="roomname" /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							 <tr>
							<td></td> 
						
							 <td><input type="button" class="btn btn-primary btn-sm" value="Save" onclick="saveRoom()">
							 <button type="button" class="btn btn-primary btn-sm" onclick="editRoom()">Update</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="deleteRoom()">Delete</button></td>
								</tr>
									
							</form>
							</tbody>
						</table>
					</div>
					<form class="form-inline" action="searchRoom">
					<div class="panel-body">
					<div class="row">
					<div class="col-md-10">
                  
                   <table id="EditTable" class="table">
				
					<tbody>
					<tr>
					<td> <label for="email">Search RoomNumber</label>
                   <input type="text" class="form-control" name="room123" id="rooms" value="${roomnum}"/>
                  <input type="submit" id="room123" name="search1" class="btn btn-primary btn-sm" value="Search"></td>
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
								<th>Room Id</th>
								<th>Room Number</th>
								<th>Room Description</th>
								<th>UpdatedBy</th>
								<th>UpdatedDate</th>
								<th>UpdatedIp</th>
								<th>Edit</th>	
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						 <c:forEach var="room" items="${roomlist}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
								<td>${room.roomid}</td>
								<td>${room.roomno}</td>
								<td>${room.description}</td>
								<td>${room.updatedby}</td>
								<td>${room.updateddate}</td>
								<td>${room.updatedip}</td>
									<td><a href="#" class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
								</tr>
							</c:forEach>
							</tbody>
					   </table> 
					</div>
				</div>
				
					
				
				<div class="row">
          <div class="btn-group">
           <div align="center"> 
        &nbsp;&nbsp;<b>Pages:&nbsp;&nbsp;</b>
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
				         <button type="submit" name="search1" id="search1" value="${pageBegin - 3}"> <<< </button>
				         </c:if>
				         <c:forEach begin="${pageBegin}" end="${pageEnd}" varStatus="i">
					     <c:choose>
					     <c:when test="${i.index == paging1.currentPageNum}">
					     <span class="page selectedPage"><c:out value="${i.index}" /></span>
					     </c:when>
					     <c:otherwise>
 						 <span class="page">
 						 <input type="submit" name="search1" id="search1" value="${i.index}"/>
 						 </span>
					     </c:otherwise>
				        </c:choose>
				       </c:forEach>
				      <c:if test="${pageEnd < paging1.noOfPages}">
				     <span>   &nbsp;&nbsp;
			        <button type="submit" name="search1" id="search1" value="${pageBegin + 3}"> >>> </button>
			       </span>
				  <span class="page"></span>
				 </c:if>  
			    </c:if>
			&nbsp;&nbsp;<c:out value="${paging1.currentPageNum}"></c:out>/<b><c:out value="${paging1.noOfPages}"></c:out></b>
	     	</div> 						  	
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
	    var roomid = $row[0].children[0].innerHTML;
	    var roomno = $row[0].children[1].innerHTML;
	    var description = $row[0].children[2].innerHTML;
	    
	    $('#roomid').val(roomid);
	    $('#roomnum').val(roomno);
	    $('#roomname').val(description);	  

	  
	});
});

function saveRoom(){
	var name=document.getElementById("roomnum").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Room Number");
		document.getElementById("roomnum").focus();
		return false;
		}
	var name=document.getElementById("roomname").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Room description");
		document.getElementById("roomname").focus();
		return false;
		}
	
	$('#roomform').attr('action', 'addRooms');
	$('#roomform').submit();
	
}
function editRoom(){
	
	var name1=document.getElementById("roomid").value; 
	if(name1==""||name==null)
		{
		alert("Please Click On Edit");
		document.getElementById("roomid").focus();
		return false;
		}
	$('#roomform').attr('action', 'editRoom');
	$('#roomform').submit();
}
function deleteRoom(){
	var name2=document.getElementById("roomid").value; 
	if(name2==""||name2==null)
		{
		alert("Please Click On Edit");
		document.getElementById("roomid").focus();
		return false;
		}
	
	$('#roomform').attr('action', 'deleteRoom');
	$('#roomform').submit();
}
 

setTimeout(function() {
	$('#message').fadeOut('fast');
}, 2000);

</script> 









 
               