<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
<form action="searchticketsList" method="post">
	 <input type="hidden" name="pageName" id="pageName" value="${pageName}">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Ticket </small>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 bg-info">
			<div class="panel-body">
				
					<div class="row">

						<div class="col-md-12">

							<div class="form-group">
								<div class="col-md-2">
									<label>From Date</label>
								</div>
								<div class="col-md-2">
									<input type="date" class="form-control" id="fromdate"
										name="fromdate" value="${fromdate}" />
								</div>
								<div class="col-md-2">
									<label>To Date</label>
								</div>
								<div class="col-md-2">
									<input type="date" class="form-control" id="todate"
										name="todate" value="${todate}"/>
								</div>
								<div class="col-md-2">
									<label>Department</label>
								</div>
								<div class="col-md-2">
									<select class="form-control input-sm" name="deptMode"
										id="department">
										<option>Select</option>
										<c:forEach var="deptMode" items="${departmentnames}">
											<c:choose>
												<c:when test="${departmentid==deptMode.getDepartmentid()}">
													<option value="${deptMode.getDepartmentid()}" selected>${deptMode.getDepartmentname()}</option>
												</c:when>
												<c:otherwise>
													<option value="${deptMode.getDepartmentid()}">${deptMode.getDepartmentname()}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-12">

							<div class="col-md-2">
								<label>Status Type</label>
							</div>

							<div class="col-md-2">
								<select class="form-control input-sm" name="statusMode"
									id="statusMode">
									<option>Select</option>
									<c:forEach var="statusMode" items="${statusname}">
										<c:choose>
											<c:when test="${statusid==statusMode.getStatusid()}">
												<option value="${statusMode.getStatusid()}" selected>${statusMode.getStatusdescription()}</option>
											</c:when>
											<c:otherwise>
												<option value="${statusMode.getStatusid()}">${statusMode.getStatusdescription()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

							<div class="col-md-2">
								<label>Complaint Source Type</label>
							</div>

							<div class="col-md-2">
								<select class="form-control input-sm" name="ticketMode"
									id="ticketMode">
									<option>Select</option>
									<c:forEach var="ticketMode" items="${tickettype}">
										<c:choose>
											<c:when test="${tickettypeid==ticketMode.getTickettypeid()}">
												<option value="${ticketMode.getTickettypeid()}" selected>${ticketMode.getTickettypedescription()}</option>
											</c:when>
											<c:otherwise>
												<option value="${ticketMode.getTickettypeid()}">${ticketMode.getTickettypedescription()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								
							</div>
							<div class="col-md-2"><label>Ticket count:</label>${ticketListCount} &nbsp</div>
                			 <div class="col-md-2"><label>Open:</label>${openTickets}</div>
                			 </div></div>
                			 
                			 <div class="row">

							<div class="col-md-2">
								<div align="">
									<button type="submit" class="btn btn-primary btn-sm"
										id="searchevent" name="search" value="search">Search</button>
								</div>
							</div>
						</div>
			</div>
		</div>
	</div>


	<hr>
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
						    <th>Sno</th>
							<th>Ticketno</th>
							<th>Date</th>
							<th>Complaints</th>
							<th>Department</th>
							<!-- <th>Assigned To</th> -->
							<th>Status</th>
							<th>Ticket Type</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tickets" items="${ticketslist}">
							<tr id="trow">
								<td> ${tickets.getSno()}</td>
								<td><a href="openticketnopage?ticketno=${tickets.getTicketno()}">${tickets.getTicketno()}</a></td>
								<td>${tickets.getTicketdate()}</td>
								<td>${tickets.getComplaint()},(${tickets.responsemaster.responsedescription})</td>
								<td>${tickets.getDepartmentmaster().getDepartmentname()}</td>
								<%-- <td>${tickets.getAssignedto()}</td> --%>
								<td>${tickets.getStatusmaster().getStatusdescription()}</td>
								<td>${tickets.getTickettypemaster().getTickettypedescription()}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
          <div class="col-md-12">
           <div align="left"> 
       &nbsp;<b>Pages:&nbsp;</b>
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
	var fromday = new Date().toISOString().split('T')[0];
	document.getElementsByName("fromdate")[0].setAttribute('max', fromday);
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("todate")[0].setAttribute('max', today);
	
</script>