<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Bill Settlement</small>
			</h1>
		</div>
	</div>

	<!-- Main Contenet Area -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Payment</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-12">
						<form name="biisettlement" id="biisettlement">
							<table border="0" cellspacing="0" cellpadding="0"
								class="table table-no-bordered">
								<tr>
								
									<td class="col-md-2"><label>Bill Id:</label></td>
									<td class="col-md-2"><input type="text"
										class="form-control" id="billid" name="billid"
										value="${billMaster.billno}" readonly="readonly"></td>
									<td class="col-md-2"><label>Bill Amount :</label></td>
									<td class="col-md-2"><input type="text"
										class="form-control" id="amountCharged" name="amountCharged"
										value="${billMaster.amount}" onchange="disablediscount()" readonly="readonly"></td>
									<td class="col-md-2"><label>Guest Name:</label></td>
									<td class="col-md-2"><input type="text"
										class="form-control" id="guestname" name="guestname"
										value="${billMaster.guestmaster.name }" readonly="readonly"><input
										type="hidden" id="guestid" class="form-control" name="guestid"
										value="${billMaster.guestmaster.guestid }"></td>
								</tr>
								<input type="hidden" class="form-control"
									name="paymentmodetypename" id="paymentmodetypename" value="">
								<tr>
									<td><label>Payment Mode</label></td>
									<td><select class="form-control" name="paymentmodetype" id="paymentmodetype" >
											<option value="">Select</option>
											<c:forEach var="paymodeMasterList" items="${paymodeDetails}">
												<option value="${paymodeMasterList.paymodeid}">${paymodeMasterList.paymode}</option>
											</c:forEach>
									</select></td>
									<td><label>Amount</label></td>
									<td class="col-md-2"><input type="text"
										class="form-control" name="amount" id="amount"
										onkeypress="return isNumber(event)"></td>
									<td><label>Tip Amount</label></td>
									<td class="col-md-2"><input type="text"
										class="form-control" name="tipamount" id="tipamount"
										onkeypress="return isNumber(event)"></td>
								</tr>
								<input type="hidden" name="tipamt" id="tipamt" value="">
								<tr id="1" class="block">
									<td colspan="12">
										<table id="corporatetable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td><label for="email">Corporate</label></td>
												<td class="col-md-2" ><select class="form-control"
													name="corporateType" id="corporateType" onchange="getCT()">
														<option>Select</option>
														<c:forEach var="paymodeMasterList"
															items="${creditMasterList}">
															<option value="${paymodeMasterList.creditlistid}">${paymodeMasterList.corporatemaster.corporatename}</option>
														</c:forEach>
												</select></td>
												<td ><label for="email">CreditList Amount</label></td>
												<td class="col-md-2"><input type="text" class="form-control"
													id="creditlistoutsatndingmoney"
													name="creditlistoutsatndingmoney" value="" readonly="readonly"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="2" class="block">
									<td colspan="12">
										<table id="membershiptable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td><label for="email">Member Name</label></td>
												<td class="col-md-2"><select class="form-control"
													name="membership" id="membership" onchange="getMS()">
														<option value="">Select</option>
														<c:forEach var="membershipList" items="${membershipList}">
															<option value="${membershipList.membershipid}">${membershipList.membername}</option>
														</c:forEach>
												</select></td>
												<td ><label for="email">Membership Amount</label></td>
												<td class="col-md-2"><input type="text" class="form-control"
													id="membershipoutsatndingmoney"
													name="membershipoutsatndingmoney" value="" readonly="readonly"></td>
												
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="3" class="block">
									<td colspan="12">
										<table id="creditcardtable" width="100%" class="table-no-border-no-border">
											<tr>
												<td><label for="email">Name on
														Credit card</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="creditcardname"
													id="creditcardname"></td>
												<td ><label for="email">Credit
														card Number</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="creditcardnumber"
													id="creditcardnumber" onkeypress="return isNumber(event)" maxlength="4"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
												<td><label for="email">Credit
														card Type</label></td>
												<td class="col-md-2"><select class="form-control"
													name="creditcardtype" id="creditcardtype" >
														<option value="0">Select</option>
														<c:forEach var="cardtypelist" items="${cardtypelist}">
															<option value="${cardtypelist.cardtypeid}">${cardtypelist.cardtypedescription}</option>
														</c:forEach>
												</select></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												
											</tr>
										</table>
									</td>
								</tr>
								<tr id="4" class="block">
									<td colspan="12">
										<table id="debitcardtable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td ><label for="email">Name on
														Debit card</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="debitcardname"
													id="debitcardname"></td>
												<td ><label for="email">Debit
														card Number</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="debitcardnumber"
													id="debitcardnumber" onkeypress="return isNumber(event)" maxlength="4"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
												<td ><label for="email">Debit
														card Type</label></td>
												<td class="col-md-2"><select class="form-control"
													name="debitcardtype" id="debitcardtype" >
														<option value="0">Select</option>
														<c:forEach var="cardtypelist" items="${cardtypelist}">
															<option value="${cardtypelist.cardtypeid}">${cardtypelist.cardtypedescription}</option>
														</c:forEach>
												</select></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="5" class="block">
									<td colspan="12">
										<table id="chequetable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td ><label for="email">Bank
														Name</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="chequebank" id="chequebank"></td>
												<td ><label for="email">Cheque
														Number</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="chequenumber" id="chequenumber" onkeypress="return isNumber(event)"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
												<td ><label for="email">Cheque
														Payee</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="chequepayee" id="chequepayee"></td>
												<td ><label for="email">cheque
														Date</label></td>
												<td class="col-md-2"><input type="date"
													class="form-control" name="chequedate" id="chequedate"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="7" class="block">
									<td colspan="12">
										<table id="giftvouchertable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td ><label for="email">Gift
														Vocher Id</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="giftvoucherid"
													id="giftvoucherid" onchange="giftamount()" onkeypress="return isNumber(event)"></td>
												<td class="col-md-2"><label for="email">Gift Voucher Amount</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="giftvoucheramount"
													id="giftvoucheramount" readonly="readonly"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>

										</table>
									</td>
								</tr>
								<tr id="8" class="block">
									<td colspan="12">
										<table id="marketingvochertable" width="100%"
											class="table-no-border-no-border">
											<tr>
												<td ><label for="email">Marketing
														VocherId</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="marketingvoucherid"
													id="marketingvoucherid" onchange="marketingvoucheramount()" onkeypress="return isNumber(event)"></td>
												<td class="col-md-2"><label for="email">Voucher
														Amount</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="voucheramount"
													id="voucheramount" readonly="readonly"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
											    <td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
											<tr>
												<td><label for="email">Marketing
														Company</label></td>
												<td class="col-md-2"><input type="text"
													class="form-control" name="marketingcompany"
													id="marketingcompany" readonly="readonly"></td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
												<td class="col-md-2">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><label>Remarks</label></td>
									<td ><input type="text"
										class="form-control" name="remarks" id="remarks"></td>
									<td>&nbsp;</td>
									<td colspan="6"><div class="pull-right"><button type="button" class="btn btn-primary btn-sm"
											id="add">Add</button> <input type="reset"
										class="btn btn-primary btn-sm" value="Clear" />
										<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalbillsettlediscount" id="discount" >Discount</button>
										<button type="button" class="btn btn-primary btn-sm"
											id="addtip" onclick="tipfun()">Add Tip</button></div></td>
								</tr>
								<tr>
								   <td colspan="6"><hr></td>
								</tr>
								<tr>
									
									
								</tr>
							</table>
						</form>
					</div>
					
					
					
		
					
					
					
					
					
					
					
					
					
					<div id="guest_payment" align="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table-no-border-no-border">
							<tr>
								<td class="col-md-2" width="5%"><label for="email">Paid
										Amount :</label></td>
								<td class="col-md-2"><input type="text"
									name="totalOutstanding" id="totalOutstanding" value=""
									placeholder="0.00" readonly="readonly"
									class="form-control input-sm"></td>
								
								<td class="col-md-2"><label for="email"><h5><b>Net
										Amount :</b></h5></label></td>
								<td class="col-md-2"><input type="text" style="background-color:#66ffff; color:blue;" 
									name="balanceOutstanding" id="balanceOutstanding" value="${billMaster.outstandingamount}"
									placeholder="0.00" disabled="disabled"
									class="form-control input-sm"></td>
									<td class="col-md-2" id="dislabel"></td>
									<td style="display:none;" class="col-md-2" id ="dismoney"><input type='text'
						name='discountamountt' id='discountamountt' value=""
						placeholder='0.00' readonly='readonly'
						class='form-control input-sm'></td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div>
				<table class="table table-bordered" id="addtable">
					<thead>
						<tr>
							<th hidden="true"></th>
							<th hidden="true"></th>
							<th hidden="true"></th>
							<th hidden="true"></th>
							<th hidden="true"></th>
							<th>Bill No</th>
							<th>Guest Name</th>
							<th>Type of Payment</th>
							<th>Cash Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="walkinguestlist">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="pull-right">
		<button type="button" class="btn btn-primary btn-sm"
			disabled="disabled" id="payment" onclick="save123()">Save</button>
	</div>
	<div id="modalbillsettlediscount" class="modal fade" role="dialog">
			<div class="modal-dialog">
			<form action="billsettlementdiscount"> 
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Discounts</h4>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<tbody>
								<tr>
										<td><label>Discount By Percent</label></td>
										<td> <select  class="form-control" id="discounttype" name="discounttype">
                                                      <option value="">Select</option>
                                                      <option value="Percentage">Percent</option>
                                                       <option value="DiscountAmount">Amount</option>
                                              </select><!-- <input type="text" class="form-control"
											id="billdiscountbypercent" name="discountbypercent" maxlength="2"
											onkeypress="return isNumber(event)" required="required" placeholder="0"> --></td>
										<td>&nbsp;</td>
									</tr>
									<tr id="disamount">
										<td><label>Discount by Amount</label></td>
										<td><input type="text" class="form-control"
											id="billdiscountbyamount" name="billdiscountbyamount"
											onkeypress="return isNumber(event)" required="required" placeholder="0.00"></td>
										<td>&nbsp;</td>
									</tr>
									<tr id="dispercent">
										<td><label>Discount by percent</label></td>
										<td><input type="text" class="form-control"
											id="billdiscountbypercent" name="billdiscountbypercent"
											onkeypress="return isNumber(event)" required="required" maxlength="2" placeholder="00%"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Discount Reason</label></td>
										<td><select class="form-control" name="discountreason"
											id="billdiscountreason" required>
												<option>Select</option>
												<c:forEach var="reason" items="${reasonList}">
													<option value="${reason.reasondescription}">${reason.reasondescription}</option>
												</c:forEach>
										</select></td>
										<td>&nbsp;</td>
									</tr>
									
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" onclick="fuctionra()"  class="btn btn-primary btn-sm"
							id="adddiscounttotable" data-dismiss="modal" >Submit</button>
						<button type="button" class="btn btn-primary btn-sm"
							data-dismiss="modal">Cancel</button>
					</div>
					</form>
				</div>

			</div>	
			<script>
		
			function fuctionra()
			{
				
				var discountreason = document.getElementById("billdiscountreason").value;
				
				var discounttype = document.getElementById("discounttype").value;
				
				var discountamount;
				if(discounttype=="Percentage"){
					var per =parseInt(document.getElementById("billdiscountbypercent").value);
					var  billamount= parseFloat(document.getElementById('amountCharged').value);
					discountamount=(billamount/100)*per;
				}else if(discounttype=="DiscountAmount"){
					discountamount = document.getElementById("billdiscountbyamount").value;
				}
				var  amountCharged= document.getElementById('amountCharged').value;
				if(parseFloat(discountamount)>parseFloat(amountCharged)){
					alert("You are Entering More amount than Bill Amount");
					return false;
				}
				var  billId= document.getElementById('billid').value;
				 $("#dislabel").html("<label>Discoun Amount :</label>");
				 alert(discountamount);
				 $("#dismoney").attr("style","display");
				 $("#discountamountt").val(discountamount);
				 var balanceOutstanding = document.getElementById("balanceOutstanding").value;
				 balanceOutstanding =parseFloat(balanceOutstanding)-parseFloat(discountamount);
				 document.getElementById("balanceOutstanding").value= balanceOutstanding;
				 var x =document.getElementById('discount');
	 		    	x.disabled = true;
				 
				  /* $.ajax({

						type : "GET",
						async : false,
						url : "billsettlementdiscount?discountreason="+discountreason+"&discounttype="+discounttype+"&discountamount="+discountamount+"&billid="+billId,
						success : function(response) {
							
							location.reload("billsettle?billNo="+billId);
							
						}
					}); */
			}
			</script>
 		<script type="text/javascript">
 		function tipfun(){
 			
 			 var tips =document.getElementById("tipamt").value;
 		
 			var tipamount =document.getElementById("tipamount").value;
 			
 			if(tipamount!=""){
 			var balanceOutstanding = document.getElementById("balanceOutstanding").value;
 			  balanceOutstanding =parseFloat(balanceOutstanding)+parseFloat(tipamount);
			 document.getElementById("balanceOutstanding").value= balanceOutstanding;
			 if(tips == "" || tips == null)
				 tips=parseFloat(0,10)
			 tips=parseFloat(tips)+parseFloat(tipamount);
			
			 document.getElementById("tipamt").value=tips;
			 document.getElementById("tipamount").value=tips;
			 if(parseFloat(balanceOutstanding)!=0)
			 {
				document.getElementById('payment').disabled=true;
				document.getElementById('add').disabled=false;
			 }
 			}
 		}
 		</script>
 <script type="text/javascript">
$("#discounttype").change(function(){
	var x =document.getElementById("discounttype").value;
	if(x=="Percentage"){
		$("#disamount").hide();
		$("#dispercent").show();
	}else if(x=="DiscountAmount"){
		 $("#disamount").show();
		 $("#dispercent").hide();
	}
	
 });
</script>

<script type="text/javascript">
function getCT() {
	var name = document.getElementById("corporateType").value;
	$.ajax({

		type : "GET",
		async : false,
		url : "creditlistmoneyavailability?creditListId="+name,
		success : function(response) {
			alert("Available CreditList Amount:"+response);
			$('#creditlistoutsatndingmoney').val(response);
			
			
		}
	});
	
}


</script>

<script type="text/javascript">
function giftamount() {
	var giftVoucherId = document.getElementById("giftvoucherid").value;
	
	$.ajax({
        type : "GET",
		async : false,
		url : "giftvouchermoneyavailability?giftVoucherId="+giftVoucherId,
		success : function(response) {
			
			
			$('#giftvoucheramount').val(response);
			
		}
	});
	
}


</script>
<script type="text/javascript">
function marketingvoucheramount() {
	var marketingvoucherid = document.getElementById("marketingvoucherid").value;
	
	$.ajax({
        type : "GET",
		async : false,
		url : "marketingvouchermoneyavailability?marketingvoucherid="+marketingvoucherid,
		success : function(response) {
			
			
			$('#voucheramount').val(response[0]);
			$('#marketingcompany').val(response[1]);
		}
	});
	
}


</script>
	<script type="text/javascript">
function getMS() {
	var name = document.getElementById("membership").value;
	
	$.ajax({

		type : "GET",
		async : false,
		url : "memebershipmoneyavailability?memebershipId="+name,
		success : function(response) {
			alert("Available Memebership Amount:"+response);
			$('#membershipoutsatndingmoney').val(response);
			
			
		}
	});
	
}


</script>
<script type="text/javascript">
$('.block').hide();
$('#paymentmodetype').change(function () {
      $(this).find("option").each(function (){
          $('#' + this.value).hide();
      });
      $('#' + this.value).show();
      document.getElementById("amount").value= "";
      document.getElementById('corporateType').value="";
      document.getElementById('membership').value="";
      document.getElementById('creditcardname').value="";
      document.getElementById('creditcardnumber').value="";
      document.getElementById('creditcardtype').value="";
      document.getElementById('debitcardname').value="";
      document.getElementById('debitcardnumber').value="";
      document.getElementById('debitcardtype').value="";
      document.getElementById('chequebank').value="";
      document.getElementById('chequenumber').value="";
      document.getElementById('chequepayee').value="";
      document.getElementById('chequedate').value="";
      document.getElementById('giftvoucherid').value="";
      document.getElementById('giftvoucheramount').value="";
      document.getElementById('marketingvoucherid').value="";
      document.getElementById('voucheramount').value="";
      document.getElementById('marketingcompany').value="";
      document.getElementById('remarks').value="";
      document.getElementById('membershipoutsatndingmoney').value="";
      document.getElementById('creditlistoutsatndingmoney').value="";
      var paymode = $("#paymentmodetype option:selected").text();
     
      document.getElementById("paymentmodetypename").value=paymode;

});
</script>
<script type="text/javascript">
$(document).ready(
			function() {
			    window. billsettlementdtolist=[];
			    window.totalOutstanding = parseFloat(0);
				window.balanceOutstanding=parseFloat(0); 
				$("#disamount").hide();
	 		    $("#dispercent").hide();
	 		    
				$('#walkinguestlist').html('<tr></tr>');
           }
);
 </script>
<script type="text/javascript">
function save123(){
	
	var  tipamount= document.getElementById('tipamt').value;
	var  discountamount= document.getElementById('discountamountt').value;
	var rowcount = document.getElementById("addtable").rows.length;
	for(var k=2;k<rowcount;k++){
	    var guestId = document.getElementById("addtable").rows[k].cells.item(0).innerHTML;
	    var paymode = document.getElementById("addtable").rows[k].cells.item(1).innerHTML;
	    var balanceOutStanding = document.getElementById("addtable").rows[k].cells.item(2).innerHTML;
	    var remarks = document.getElementById("addtable").rows[k].cells.item(3).innerHTML;
	    var corporateType = document.getElementById("addtable").rows[k].cells.item(4).innerHTML;
	    var membershipType = document.getElementById("addtable").rows[k].cells.item(5).innerHTML;
	    var billId=document.getElementById("addtable").rows[k].cells.item(6).innerHTML;
	    var amount=document.getElementById("addtable").rows[k].cells.item(9).innerHTML;
	    var creditcardname = document.getElementById("addtable").rows[k].cells.item(10).innerHTML;
	    var creditcardnumber=document.getElementById("addtable").rows[k].cells.item(11).innerHTML;
	    var CCtype=document.getElementById("addtable").rows[k].cells.item(12).innerHTML;
	    var debitcardname = document.getElementById("addtable").rows[k].cells.item(13).innerHTML;
	    var debitcardnumber=document.getElementById("addtable").rows[k].cells.item(14).innerHTML;
	    var Dctype=document.getElementById("addtable").rows[k].cells.item(15).innerHTML;
	    var chequebank=document.getElementById("addtable").rows[k].cells.item(16).innerHTML;
	    var chequenumber = document.getElementById("addtable").rows[k].cells.item(17).innerHTML;
	    var chequepayee=document.getElementById("addtable").rows[k].cells.item(18).innerHTML;
	    var chequedate=document.getElementById("addtable").rows[k].cells.item(19).innerHTML;
	    var giftvoucherid=document.getElementById("addtable").rows[k].cells.item(20).innerHTML;
	    var marketingvoucherid=document.getElementById("addtable").rows[k].cells.item(21).innerHTML;
	    var guestObj  = {};
 	    var paymodeTypeObj	 = {};
 	    var corporateObj = {};
 	    var membershipObj = {};
 	    var billsettlement = {};
 	    var billmaster = {};
 	    var creditcardtype={};
 	    var debitcardtype={};
 	    guestObj={
 			guestid:guestId,
 		}
 	    paymodeTypeObj = {
 			paymodeid:paymode,
 		}
 	    corporateObj = {
 			creditlistid:corporateType,
 	    }
        membershipObj = {
 			membershipid:membershipType,
 		}
 	    billmaster={
 			billno : billId,
 	    }
 	   giftvouchermaster={
 	    		giftvoucherid:giftvoucherid,
 	    }
 	   marketingvouchermaster={
 	    		marketingvoucherid:marketingvoucherid,
 	    }
 	   
         creditcardtypeobj={
 	 	    		cardtypeid   :  CCtype,
 	 	    }
 	
	    	 debitcardtypeobj={
	 	    		cardtypeid   :  Dctype,
	 	    } 
	      billsettlement = {
 			       billmaster 			:	billmaster,
 			       remarks	    	    :	remarks,
 			       amount     	        :	amount,
 			       outstandingamount    :	balanceOutStanding,
 			       guestmaster	        :	guestObj,
 			       membershipmaster	    :	membershipObj,
 			       paymodemaster        :   paymodeTypeObj,
 			       creditlistmaster	    :	corporateObj,
 			       creditcardnumber     :   creditcardnumber,
 			       creditcardname       :   creditcardname,
 			       //cardtypemaster       :   creditcardtypeobj,
 			      cardtypemasterByCreditcardtype :   creditcardtypeobj,
 			       debitcardnumber      :   debitcardnumber,
	 			   debitcardname        :   debitcardname,
	 			  cardtypemasterByDebitcardtype :debitcardtypeobj,
 			       chequebank           :   chequebank,
 			       chequenumber         :   chequenumber,
 			       chequepayee          :   chequepayee,
 			       chequedate           :   chequedate,
 			       giftvouchermaster    :   giftvouchermaster,
 			       marketingvouchermaster:  marketingvouchermaster
 	         };
 	   
 	    billsettlementdtolist.push(billsettlement);
 	} //for end
	 $.ajax({
			type : "POST", 
			url : "savebillsettlement?tipamount="+tipamount+"&discountamount="+discountamount,
			contentType : 'application/json',
			data : JSON.stringify(billsettlementdtolist),
			success : function(response) {
				alert(response);
				window.open("generatepdfreceipt?billno="+billId+"&guestid="+guestId);
				location.replace("paymentsettlement");
			}

	}); 
}
</script>
	<script type="text/javascript">
$('#add').click(function(){
	 var  guestId= document.getElementById('guestid').value;
	 var  guestName= document.getElementById('guestname').value;
 	 var  billId= document.getElementById('billid').value;
 	 var paymode =document.getElementById('paymentmodetype').value;
 	 var paymodename= document.getElementById('paymentmodetypename').value;
 	 var amount =document.getElementById('amount').value;
 	 var  remarks= document.getElementById('remarks').value;
     var  amountCharged= document.getElementById('amountCharged').value;
	 var corporateType=document.getElementById('corporateType').value;
     var membership=document.getElementById('membership').value;
     var creditcardname=document.getElementById('creditcardname').value;
     var creditcardnumber=document.getElementById('creditcardnumber').value;
     var creditcardtype=document.getElementById('creditcardtype').value;
     var debitcardname=document.getElementById('debitcardname').value;
     var debitcardnumber=document.getElementById('debitcardnumber').value;
     var debitcardtype=document.getElementById('debitcardtype').value;
     var chequebank=document.getElementById('chequebank').value;
     var chequenumber=document.getElementById('chequenumber').value;
     var chequepayee=document.getElementById('chequepayee').value;
     var chequedate=document.getElementById('chequedate').value;
     var giftvoucherid=document.getElementById('giftvoucherid').value;
     var marketingvoucherid=document.getElementById('marketingvoucherid').value;
	 var totalOutstanding =document.getElementById('totalOutstanding').value;
	 var balanceOutstanding =document.getElementById('balanceOutstanding').value;
     if(paymode == '' )
	 {
		alert("Please Select Payment Mode ");
		document.getElementById('paymentmodetype').focus();
	 }else if(amount == 0 )
     {
	    alert("Please Enter valid amount ");
		document.getElementById('amount').focus();
	 }/* else  if(parseFloat(amount) > amountCharged)
	 {
		 alert("Please Enter  valid amount");
	     document.getElementById('amount').focus();
	} */else  if( parseFloat(amount) > balanceOutstanding && balanceOutstanding !=0 )
	{
		 alert("Settlement amount not matching with Net Amount");
		 document.getElementById('amount').focus();
	}else if(  paymode!='' && amount!='' )
    { 
		
	     if(corporateType!=''||membership!=''||(creditcardname!=''&&creditcardnumber!=''&&creditcardtype!='')||(debitcardname!=''&&debitcardnumber!=''&&debitcardtype!='')||(chequenumber!=''&&chequebank!=''&&chequepayee!=''&&chequedate!='')||paymode==6||giftvoucherid!=''||marketingvoucherid!=''){
	    	
	    	 var creditlistoutsatndingmoney =document.getElementById("creditlistoutsatndingmoney").value;
	    	 
	    	 var membershipoutsatndingmoney =document.getElementById("membershipoutsatndingmoney").value;
	    	 var giftvoucheramount =document.getElementById("giftvoucheramount").value;
	    	 var voucheramount =document.getElementById("voucheramount").value;
	    	   if(amount <=parseFloat(creditlistoutsatndingmoney)||amount<=parseFloat(membershipoutsatndingmoney)||creditcardname!=''||debitcardname!=''||chequenumber!=''||paymode==6||amount <=parseFloat(giftvoucheramount)||amount <=parseFloat(voucheramount)){
	           if(amount == "" || amount == null)
			        amount=parseFloat(0,10)
		       amount=parseFloat(amount);
		       if(totalOutstanding == "" || totalOutstanding == null)
			        totalOutstanding=parseFloat(0,10)
		       totalOutstanding=parseFloat(totalOutstanding,10)+amount;
		       var balanceOutstanding=balanceOutstanding-amount; 
		       document.getElementById("totalOutstanding").value= totalOutstanding;
               document.getElementById("balanceOutstanding").value= balanceOutstanding;
              /*  if(amountCharged==totalOutstanding||amountCharged<=totalOutstanding) */
              if(parseFloat(balanceOutstanding)==0)
		       {
			        var x = document.getElementById('add');
			        x.disabled = true;
			        document.getElementById('payment').disabled=false;
		       }
	           var tr='<tr><td id="hidguestid" hidden="true">'+guestId
 	           +'</td><td id="hidpaymode" hidden="true">'+paymode
 	           +'</td><td id="hidbalanceOutstanding" hidden="true">'+balanceOutstanding
 	           +'</td><td id="hidremarks" hidden="true">'+remarks
 	           +'</td><td id="hidcorporateType" hidden="true">'+corporateType
 	           +'</td><td id="hidmembership" hidden="true">'+membership
 	           +'</td><td id="billId">'+billId
 	           +'</td><td>'+guestName
 	           +'</td><td>'+paymodename
 	           +'</td><td id="amount">'+amount
 	           +'</td><td id="hidcreditcardname" hidden="true">'+creditcardname
	           +'</td><td id="hidcreditcardnumber" hidden="true">'+creditcardnumber
	           +'</td><td id="hidcreditcardtype" hidden="true">'+creditcardtype
	           +'</td><td id="hiddebitcardname" hidden="true">'+debitcardname
	           +'</td><td id="hiddebitcardnumber" hidden="true">'+debitcardnumber
	           +'</td><td id="hiddebitcardtype" hidden="true">'+debitcardtype
	           +'</td><td id="hidchequebank" hidden="true">'+chequebank
	           +'</td><td id="hidchequenumber" hidden="true">'+chequenumber
	           +'</td><td id="hidchequepayee" hidden="true">'+chequepayee
	           +'</td><td id="hidchequedate" hidden="true">'+chequedate
	           +'</td><td id="hidgiftvoucherid" hidden="true">'+giftvoucherid
	           +'</td><td id="hidmarketingvoucherid" hidden="true">'+marketingvoucherid
 	           +'</td><td><button id="close123" onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
 	           $("#walkinguestlist tr:last").after(tr);
	           document.getElementById("amount").value= "";
               document.getElementById("paymentmodetype").value="";
               document.getElementById('corporateType').value="";
               document.getElementById('membership').value="";
               document.getElementById('creditcardname').value="";
               document.getElementById('creditcardnumber').value="";
               document.getElementById('creditcardtype').value="";
               document.getElementById('debitcardname').value="";
               document.getElementById('debitcardnumber').value="";
               document.getElementById('debitcardtype').value="";
               document.getElementById('chequebank').value="";
               document.getElementById('chequenumber').value="";
               document.getElementById('chequepayee').value="";
               document.getElementById('chequedate').value="";
               document.getElementById('giftvoucherid').value="";
               document.getElementById('giftvoucheramount').value="";
               document.getElementById('marketingvoucherid').value="";
               document.getElementById('voucheramount').value="";
               document.getElementById('marketingcompany').value="";
               document.getElementById('remarks').value="";
               document.getElementById('membershipoutsatndingmoney').value="";
               document.getElementById('creditlistoutsatndingmoney').value="";
	    	   }else if(paymode==1){
	    		   alert("Your CreditList Amount is : "+parseFloat(creditlistoutsatndingmoney));
	    		   alert("your are entering more amount than your creditlist amount ");
	    		   document.getElementById('amount').focus();
	    	   }else if(paymode==2){
	    		   alert("Your Memebership Amount is : "+parseFloat(membershipoutsatndingmoney));
	    		   alert("your are entering more amount than your membership amount ");
	    		   document.getElementById('amount').focus();
	    	   }
	    	   else if(paymode==7){
	    		   alert("Your Gift Voucher Amount is : "+parseFloat(giftvoucheramount));
	    		   alert("your are entering more amount than your Gift Vocher amount ");
	    		   document.getElementById('amount').focus();
	    	   }else if(paymode==8){
	    		   alert("Your Marketing Voucher Amount is : "+parseFloat(voucheramount));
	    		   alert("your are entering more amount than your Voucher amount ");
	    		   document.getElementById('amount').focus();
	    	   }
	    }else if(paymode==1){
	    	     
		         alert("please Select corporate name");
	    }else if(paymode==2){
		        alert("please Select member name");
	    }else if(paymode==3){
	    	   if(creditcardname==''){
		    	   alert("please Enter name on the Credit Card");
		       }else if(creditcardnumber==''){
		    	   alert("please Enter Credit Card Number");
		       }else if(creditcardtype==''){
		    	   alert("please Enter Credit Card Type");
		       }
	    }else if(paymode==4){
	    	   if(debitcardname==''){
		    	   alert("please Enter name on the debit Card");
		       }else if(debitcardnumber==''){
		    	   alert("please Enter Debit Card Number");
		       }else if(debitcardtype==''){
		    	   alert("please Enter Debit Card Type");
		       }
	    }else if(paymode==5){
	    	   if(chequebank==''){
		    	   alert("please Enter name of the Bank");
		       }else if(chequenumber==''){
		    	   alert("please Enter Cheque Number");
		       }else if(chequepayee==''){
		    	   alert("please Enter Cheque Payee");
		       }else if(chequedate==''){
		    	   alert("please Enter Cheque Date");
		       }
	    }else if(paymode==7){
   	     
	         alert("please Enter Gift Vocher");
        }else if(paymode==8){
	        alert("please Enter Marketing Vocher");
        }
   }
 });
function deleterow(element){
	 var i = element.parentNode.parentNode.rowIndex;
	  
	 var removeMoney = document.getElementById("addtable").rows[i].cells.item(9).innerHTML;
	 
	 var totalOutstanding = document.getElementById("totalOutstanding").value - parseFloat(removeMoney);
	 var balanceOutstanding = parseFloat(document.getElementById("balanceOutstanding").value) + parseFloat(removeMoney);
	 var  amountCharged= document.getElementById('amountCharged').value;
	 document.getElementById("balanceOutstanding").value= balanceOutstanding;
	 document.getElementById("totalOutstanding").value= totalOutstanding;
	 document.getElementById("addtable").deleteRow(i);
	 /* if(amountCharged > totalOutstanding) */
	  if(parseFloat(balanceOutstanding)!=0)
	 {
		document.getElementById('payment').disabled=true;
		document.getElementById('add').disabled=false;
	 }
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
<script type="text/javascript">
$
</script>