<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
var i="${msg}";
var length=i.length;
if(length==0)
	{
	}
else
	{
	alert(i);
	location.href="stockadjustmentpage";
	}
</script>
    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Stock Adjustments</small> </h1>
          <h4>
          <%-- <c:if test="${msg!=null }">
           ${msg}
          </c:if> --%>
          </h4>
           
        </div>
      </div>
      
      <!-- Main Content Area -->
      
        
   
      <form action="stockadjustment" method="post" name="stockadj" >
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Stock Adjustments</h3>
            </div>
            <div class="panel-body">
              <div class="col-md-9">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                  <tbody>
                    <tr>
                      <td class="col-md-3"><label>Stock Name</label></td>
                      <td class="col-md-6"><select required class="form-control forChosen" name="stockName" id="stockName"  onchange="get()" >
                          <option value="" >Select </option>
                          <c:forEach var="stock" items="${stockList}">
                          <option value="${stock.stockId}">${stock.stockName}</option>
                          
                           </c:forEach> 
                        </select></td>
                      <td class="col-md-6"><input type="hidden" id="stockId" name="stockId" value="${stock.stockId}"></td>
                    </tr>
                    <tr>
                      <td><label>Quantity Adjusted</label></td>
                      
                      <td><input type="search" class="form-control" id="quantityAdjusted" name="quantityAdjusted" required="required" onkeypress="return isNumber(event)"></td>
                     
                      <td id="availability"></td>
                    </tr>
                   
                    <tr>
                      <td><label>Expiry Date</label></td>
                      <td><input type="Date" class="form-control" id="expireDate" name="expireDate" required="required"></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td><label>Remarks</label></td>
                      <td><textarea class="form-control" rows="5" id="comment" maxlength="255" name="remarks" required="required"></textarea></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                   
                      <td>&nbsp;</td>
                      <td><div class="pull-right">
                          <input type="submit" class="btn btn-primary btn-sm" value="Adjust" onclick="return validateForm()">
                        </div></td>
                      <td>&nbsp;</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="col-md-3"></div>
            </div>
          </div>
        </div>
      </div>
      </form>
       
    </div>
    <!-- Main Content Area --> 
    <!-- /.container-fluid --> 

<script type="text/javascript">
function get() {
	var name = document.getElementById("stockName").value;
	$.ajax({

		type : "GET",
		async : false,
		url : "stockavailability?stockId="+name,
		success : function(response) {
			document.getElementById("availability").innerHTML = "Current Avaliability : "+response;
		}
	});
	
}


</script>
<script type="text/javascript">
    function isNumber(evt) {
    	
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode<48 || charCode>57)) {
            return false;
        }
        return true;
    }
    
    </script>
    <script>
function validateForm() {
    var x = document.getElementById("stockName").value;
   if (x == null || x == "") {
        alert("Stock must be select");
        document.getElementById("stockName").focus();
        return false;
    }else{
    	return true;
    }
}
</script>
    

