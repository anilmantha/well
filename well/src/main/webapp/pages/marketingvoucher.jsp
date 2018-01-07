<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
					     <h3 class="panel-title"><b>Marketing Voucher </b></h3>
				          </div>
				           <div class="panel-body">
				            <div class="col-md-11">
					          <input type="hidden" name="pageName" id="pageName" value="${pageName}">
						     <table width="100%" border="0" cellspacing="0" cellpadding="0"
							  class="table table-no-bordered"><form action="" class="col-md-7"  id="mvoucherform">
							<input type="hidden" id="marketingvoucherid" name="marketingvoucherid" value=""/>
				             <tbody>
							 <tr>
							 <td width="12%"><label> Voucher Amount</label></td>
							 <td class="col-md-3"><input type="text"
								  class="form-control" id="voucheramount" name="voucheramount" required/></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							  <tr>
							 <td width="12%"><label> Marketing Company</label></td>
							 <td class="col-md-3"><input type="text"
								  class="form-control" id="mcompany" name="mcompany"  required/></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							 <tr>
							 <td width="14%"><label>Valid from</label></td>
							 <td class="col-md-3"><input type="date" 
								 class="form-control" id="validfrom" name="validfrom" required  /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							  <tr>
							 <td width="14%"><label>ValidTo</label></td>
							 <td class="col-md-3"><input type="date"
								 class="form-control" id="validto" name="validto" required /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
							  <tr>
							 <td width="14%"><label>Remarks</label></td>
							 <td class="col-md-3"><input type="text"
								 class="form-control" id="remark" name="remark" required /></td>
							 <td width="5%">&nbsp;</td>
							 <td width="14%">&nbsp;</td>
							 <td class="col-md-3">&nbsp;</td>
							 </tr>
						     <tr>
							 <td></td>
							 <td><button type="submit" id="savevoucher" name="savevoucher" onclick="savevoucher123()" class="btn btn-primary btn-sm">SaveorUpdate</button> 
							 <button type="reset" class="btn btn-primary btn-sm">Clear</button></td>
             				 <td></td>
                             </tr>
                              </tbody>
                              </form>
						   </table>
		                 </div>
		                </div>
			         </div>
		              
	             
	             <form class="form-inline" action="searchMarketVoucher">    
	             <div class="row">
			      <div class="col-md-10">
			      
                    <table id="EditTable" class="table">
				    <tbody>
					<tr>
					<td> <label for="email">MarketingVoucher By Company</label>
                   <input type="text" class="form-control" name="market123" id="markets"/>
                  <input type="submit" id="market12" name="search" class="btn btn-primary btn-sm" value="Search" ></td>
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
								<th>MarketVoucherId</th>
								<th>Voucher Amount</th>
								<th>Marketing Company</th>
								<th>ValidFrom</th>
								<th>ValidTo</th>
								<th>Remarks</th>
								<th>UpdatedBy</th>
								<th>UpdatedDate</th>
								<th>UpdatedIp</th>
								<th>Edit</th>	
							</tr>
						</thead>
						<tbody id="addrowstextfields">
						 <c:forEach var="voucher" items="${mlist}">
								<tr data-toggle="tooltip" data-placement="top" title="TO Edit double click on the button">
								<td>${voucher.marketingvoucherid}</td>
								<td>${voucher.voucheramount}</td>
                                 <td>${voucher.marketingcompany}</td>
                               <td><fmt:formatDate pattern="dd-MM-yyyy" 
                                 value="${voucher.validfrom}" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" 
                                 value="${voucher.validto}" /></td>
								<td>${voucher.remarks}</td>
								<td>${voucher.updatedby}</td>
								<td>${voucher.updateddate}</td>
								<td>${voucher.updatedip}</td>
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
	     </div>
	     </div>
	
		
	

<script type="text/javascript">
$(function(){
	$(".use-address").click(function() {
	    var $row = $(this).closest("tr");
	    var marketingvoucherid123 = $row[0].children[0].innerHTML;
	    var voucheramount = $row[0].children[1].innerHTML;
	    var marketingcompany =  $row[0].children[2].innerHTML;
	    var validfrom =  $row[0].children[3].innerHTML;   
	     var validto =  $row[0].children[4].innerHTML;
	     var  remarks=  $row[0].children[5].innerHTML;
	   
	    $('#marketingvoucherid').val(marketingvoucherid123);
	    $('#voucheramount').val(voucheramount);
	    $('#mcompany').val(marketingcompany);
	    $('#validfrom').val(validfrom);
	    $('#validto').val(validto);
	    $('#remark').val(remarks);
	    $('#voucheramount').attr('readonly','readonly');
	    $('#remark').attr('readonly','readonly');
	    $('#mcompany').attr('readonly','readonly');
	    
	  
	});
});

function savevoucher123(){
	var name=document.getElementById("voucheramount").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Voucher Amount");
		document.getElementById("voucheramount").focus();
		return false;
		}
	var name=document.getElementById("mcompany").value; 
	if(name==""||name==null)
		{
		alert("Please Enter Company Name");
		document.getElementById("mcompany").focus();
		return false;
		}
	 $('#mvoucherform').attr('action', 'saveMarketVoucher');
	$('#mvoucherform').submit(); 
	
}

  setTimeout(function() {
	$('#message').fadeOut('fast');
  }, 2000);

</script> 
