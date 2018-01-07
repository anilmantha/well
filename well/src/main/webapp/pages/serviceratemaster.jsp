<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					<h3 class="panel-title">Service Rate</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
					<form>
						<table class="table table-no-bordered">
							<tbody>
							<tr>
									<td><label>Service Name</label></td>
									<td><select class="form-control" id="serviceid"
										name="serviceid">
											<option value="">Select</option>
											<c:forEach var="services" items="${serviceslist}">
												<option value="${services.serviceid}">${services.servicename}</option>
											</c:forEach>
									</select></td>

								</tr>
								<tr>
									<td><label>Tax structure</label></td>
									<td><select class="form-control" id="taxstructureid"
										name="taxstructureid">
											<option value="">Select</option>
											<c:forEach var="taxstructure" items="${taxstructurelist}">
												<option value="${taxstructure.getTaxstructureid()}">${taxstructure.getTaxstructuredescription()}</option>
											</c:forEach>
									</select></td>

								</tr>
								<tr>
									<td><label>Service Cost</label></td>
									<td><input type="text" class="form-control"
										id="servicecost" name="servicecost" onkeypress="return isNumberKey(event,this)"></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="col-md-3"><label>Applicable Date</label></td>
									<td class="col-md-4"><input type="date"
										class="form-control" id="serviceDate" name="applicableDate" /></td>
									<td class="col-md-6">&nbsp;</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								<td><div class="pull-right">
									<input type="button" class="btn btn-primary btn-sm"	value="SaveorUpdate" onclick="saveservicerate()">
									<button type="reset" class="btn btn-primary btn-sm">Clear</button>
								</div></td>
								<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
						</form>
							</div>
							<div class="row">
								<div class="col-md-12">
				                  <form class="form-inline" action="searchServiceRate">
				                   <table id="EditTable" class="table">
									<tbody>
										<tr>
											<td>
				                   <div class="form-group">
				                      <label for="email">Search Service By Name :</label>
				                      <input type="text" class="form-control" name="serviceName" value="${searchservicerate}" id="serviceName"/>
				                    </div>
				                    <input type="submit" id="serviceName" class="btn btn-primary btn-sm" value="search">
				                    </td>
				                    </tr>
				                    </tbody>
				                    </table>
				                   
				                </div>
			                </div>
							<table  id="EditTable" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Service Name</th>
										<th>Tax Structure Description</th>
										<th>Service Cost</th>
										<th>Applicable date</th>
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
									<c:forEach var="services" items="${serviceRateList}">
										<tr data-toggle="tooltip" data-placement="top" title="T0 Edit click on the button">
											<td>${services.getServicemaster().getServicename()}</td>
											<td>${services.getTaxstructuremaster().getTaxstructuredescription()}</td>
											<td>${services.getServicecost()}</td>
											<td>${services.getApplicabledate()}</td>
											<td><button class="use-address"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>
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
<script type="text/javascript">
	$(document).ready(function(){
		var today = new Date().toISOString().split('T')[0];
		document.getElementsByName("applicableDate")[0].setAttribute('min', today);
		$('#serviceDate').val(today);
		}); 
	function saveservicerate() {
		var name=document.getElementById("serviceid").value; 
		if(name==""||name==null)
			{
			alert("Please select Service");
			document.getElementById("serviceid").focus();
			return false;
			}
		var name=document.getElementById("taxstructureid").value; 
		if(name==""||name==null)
			{
			alert("Please select TaxStructure");
			document.getElementById("taxstructureid").focus();
			return false;
			}
		var name=$("#servicecost").val(); 
		if(name==""||name==null)
			{
				alert("Please Enter serviceCost");
				document.getElementById("servicecost").focus();
				return false;
			}
		
		var id = document.getElementById("serviceid").value;
		var taxStructureId = document.getElementById("taxstructureid").value;
		var serviceCost = document.getElementById("servicecost").value;
		var serviceDate = document.getElementById("serviceDate").value;
		
		$.ajax({
			type : "GET",
			async : false,
			url : "saveservicerate?serviceid=" + id +"&&taxstructureid="+taxStructureId+ "&&servicecost="
					+ serviceCost + "&&applicableDate=" + serviceDate,
			success : function(response) {
				alert(response);
				location.reload();
			}
		});
		
	}

	$(function(){
		$(".use-address").click(function() {
		    var $row = $(this).closest("tr");
		    var servicename = $row[0].children[0].innerHTML;
		    var taxstructure = $row[0].children[1].innerHTML;
		    var servicecost = $row[0].children[2].innerHTML;
		    var date = $row[0].children[3].innerHTML;
		    $("option").filter(function() {
				return $(this).text() == servicename;
			}).prop('selected', true);
		    $("option").filter(function() {
				return $(this).text() == taxstructure;
			}).prop('selected', true);
		    $('#servicecost').val(servicecost);
		    $('#serviceDate').val(date);
		    $('#serviceid').attr('disabled','disabled');
			$('#taxstructureid').attr('disabled','disabled');
			$('#servicecost').attr('disabled','disabled');
		});
	});
</script>
<script type="text/javascript">
function isNumberKey(evt, element) {
	  var charCode = (evt.which) ? evt.which : event.keyCode
	  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charcode == 8))
	    return false;
	  else {
	    var len = $(element).val().length;
	    var index = $(element).val().indexOf('.');
	    if (index > 0 && charCode == 46) {
	      return false;
	    }
	    if (index > 0) {
	    var CharAfterdot = (len + 1) - index;
	    if (CharAfterdot > 3) {
	    return false;
	      }
	    
	    }

	  }
	  return true;
	}
</script>