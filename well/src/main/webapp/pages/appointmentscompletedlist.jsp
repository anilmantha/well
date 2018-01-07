<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Bills Page</small>
			</h1>
		</div>
	</div>
	<!-- Main Content Area -->
	<div class="row">
		<form action="" method="post" id="submitservices">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">List of Services Completed</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th></th>
										<th>Appointment ID</th>
										<th>Guest Name</th>
										<th>Package ID</th>
										<th>Package Name</th>
										<th>Service ID</th>
										<th>Service Name</th>
										<th>Service Status</th>
										<th>Appointment Date</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="appointmentlist"
										items="${appointmentscompletedlist}">
										<c:forEach var="appointment" items="${serviceList}">
											<c:if
												test="${appointment.getAppointment().getAppointmentid()==appointmentlist.appointmentid && appointment.getPackagemaster()==null}">
												<tr>
													<td><input type="checkbox" class="case" name="case"
														value="" /></td>
													<td>${appointment.getAppointment().getAppointmentid()}</td>
													<td>${appointment.getAppointment().getGuestmaster().getName()}</td>
													<td></td>
													<td></td>
													<td>${appointment.getServicemaster().getServiceid()}</td>
													<td>${appointment.getServicemaster().getServicename()}</td>
													<td>${appointment.getStatusmaster().getStatusdescription()}</td>
													<td>${appointmentlist.appointmentdate}</td>
													<input type="hidden"
														value="${appointmentlist.getGuestmaster().getGuestid()}" />
												</tr>
											</c:if>
										</c:forEach>
										<c:forEach var="appointment" items="${packageList}">
											<c:if
												test="${appointment.getAppointment().getAppointmentid()==appointmentlist.appointmentid && appointment.getPackagemaster()!=null}">
												<tr>
													<td><input type="checkbox" class="case" name="case"
														value="" /></td>
													<td>${appointment.getAppointment().getAppointmentid()}</td>
													<td>${appointment.getAppointment().getGuestmaster().getName()}</td>
													<td>${appointment.getPackagemaster().getPackageid()}</td>
													<td>${appointment.getPackagemaster().getPackagename()}</td>
													<td></td>
													<td></td>
													<td>${appointment.getStatusmaster().getStatusdescription()}</td>
													<td>${appointmentlist.appointmentdate}</td>
													<input type="hidden"
														value="${appointmentlist.getGuestmaster().getGuestid()}" />
												</tr>
											</c:if>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group pull-left">
				<select class="form-control" id="payeenames">
					<option>Select Payee Name</option>
				</select>
			</div>
		</div>
		<div class="col-md-6">
			<div class="pull-right">
				<input type="button" class="btn start btn-primary btn-sm"
					value="Add to Bill" onclick="gotoBillPage()"> <a
					href="appointmentslist"><input type="button"
					class="stop btn btn-primary btn-sm" value="Cancel"></a>
			</div>
		</div>
	</div>
	<div class="row" style="visibility: hidden" id="hiddenpanel">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Service Amounts</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Appointment ID</th>
									<th>Guest Name</th>
									<th>Package ID</th>
									<th>Package Name</th>
									<th>Service ID</th>
									<th>Service Name</th>
									<th>Service Amount</th>
									<th>Product ID</th>
									<th>Product Name</th>
									<th>Product Quantity</th>
									<th>Product Amount</th>
									<th>Tax</th>
									<th>Service + Tax</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="addrowstextfields">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-12" id="hidebuttons">
			<div class="pull-left">
				<input type="button" value="Add Product" id="addproduct"
					class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal" />
				<input type="button" value="Add Discount" id="adddiscount"
					class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#adddiscountModal" />
			</div>
			<div class="pull-right">
				<input type="button" value="Preview" class="btn btn-primary btn-sm"
					data-toggle="modal" data-target="#summaryModal" id="summarytable" />
				<input type="button" value="Generate Bill"
					class="btn btn-primary btn-sm" id="generateBill" /> <a
					href="appointmentscompletedlistpage"><input type="button"
					class="stop btn btn-primary btn-sm" value="Cancel"></a>
			</div>
		</div>


		<!-- Modal content for Adding product-->

		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Product List</h4>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<tbody>
									<tr>
										<td><label>Products</label></td>
										<td><select class="form-control" id="stockId"
											name="stockId">
												<option value="">Select Product</option>
										</select></td>
										<td id="availability"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Quantity</label></td>
										<td><input type="text" class="form-control"
											id="productquantity" name="quantity" maxlength="4"
											onkeypress="return isNumber(event)" required="required"></td>
										<td>&nbsp;</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary btn-sm"
							id="addproducttotable" data-dismiss="modal">Ok</button>
						<button type="button" class="btn btn-primary btn-sm"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>

			</div>
		</div>

<!-- Modal content for Adding Discounts-->

		<div id="adddiscountModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
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
										<td><label>Discount Type</label></td>
										<td> <select  class="form-control" id="discounttype" name="discounttype">
                                                      <option value="">Select</option>
                                                      <option value="Percentage">Percent</option>
                                                       <option value="DiscountAmount">Amount</option>
										<td>&nbsp;</td>
									</tr>
									<tr id="disamount">
										<td><label>Discount by Amount</label></td>
										<td><input type="text" class="form-control"
											id="discountbyamount" name="discountbyamount"
											onkeypress="return isNumberKey(event,this)" required="required" placeholder="0.00"></td>
										<td>&nbsp;</td>
									</tr>
									<tr id="dispercent">
										<td><label>Discount by percent</label></td>
										<td><input type="text" class="form-control"
											id="discountbypercent" name="discountbypercent"
											onkeypress="return isNumber(event)" required="required" maxlength="2" placeholder="00%"></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><label>Discount Reason</label></td>
										<td><select class="form-control" name="discountreason"
											id="discountreason" required>
												<option>Select</option>
												<c:forEach var="reason" items="${reasonList}">
													<option value="${reason.reasondescription}">${reason.reasondescription}</option>
												</c:forEach>
										</select></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary btn-sm"
							id="adddiscounttotable" data-dismiss="modal">Ok</button>
						<button type="button" class="btn btn-primary btn-sm"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>

			</div>
		</div>

		<!-- Modal content for Preview Data-->

		<div id="summaryModal" class="modal fade bs-example-modal-lg"
			role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Summary</h4>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<table class="success table table-bordered">
								<thead>
									<tr>
										<th>Appointment ID</th>
										<th>Guest Name</th>
										<th>Package ID</th>
										<th>Package Name</th>
										<th>Service ID</th>
										<th>Service Name</th>
										<th>Service Amount</th>
										<th>Product ID</th>
										<th>Product Name</th>
										<th>Product Quantity</th>
										<th>Product Amount</th>
										<th>Tax</th>
										<th>Service + Tax</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="addsummaryfields">
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary btn-sm"
							data-dismiss="modal">Ok</button>
					</div>
				</div>

			</div>
		</div>

		<!-- Modal content for Preview Data Ended-->
	</div>
</div>
<input type="hidden" id="reason" />
<input type="hidden" id="percent" />
<input type="hidden" id="amount" />									
<!-- Main Content Area -->
<!-- /.container-fluid -->

<script type="text/javascript">
$("#disamount").hide();
 $("#dispercent").hide();
function gotoBillPage()
{
	if($('#payeenames :selected').text()=="Select Payee Name")
		{
		alert("Please select payee");
		}
	
	else{
		var i = 0;
		var servicesArray=[];
		if ($("#addrowstextfields").find('tr:empty').length==0)
			{
				$("#addrowstextfields").html('<tr></tr>');
			}
		else {
			$("#addrowstextfields tr:last").remove();
		}
		  $('.case:checked').each(function () {
		 var appointmentid = $('.case:checked').closest('tr')[i].children[1].innerHTML;
		 var serviceid = $('.case:checked').closest('tr')[i].children[5].innerHTML;
		 var packageid = $('.case:checked').closest('tr')[i].children[3].innerHTML;
		 var guestid = $('.case:checked').closest('tr')[i].children[9].value;
		 var discountType = $('#discountid').val();
		 if (!packageid){
			    packageid = 0;
			}
		 if (!serviceid){
			    serviceid = 0;
			}
		 var servicecost;
		 var tax;
		 var total;
		 $.ajax({
				type : "POST",
				async : false,
				url : "getServicerate?serviceId="+serviceid+"&&appointmentId="+appointmentid+"&&packageId="+packageid,
				success : function(response) { 
					var i = 0;
					servicecost = response[i];
					tax = response[i+1];
					total = response[i+2];
					}
			});
		  var tr = '<tr class="se"><td>'+$('.case:checked').closest('tr')[i].children[1].innerHTML+'</td>'+
				'<td>'+$('.case:checked').closest('tr')[i].children[2].innerHTML+'</td>'+
				'<td>'+$('.case:checked').closest('tr')[i].children[3].innerHTML+'</td>'+
				'<td>'+$('.case:checked').closest('tr')[i].children[4].innerHTML+'</td>'+
				'<td>'+$('.case:checked').closest('tr')[i].children[5].innerHTML+'</td>'+
				'<td>'+$('.case:checked').closest('tr')[i].children[6].innerHTML+'</td>'+
				'<td>'+servicecost+'</td><td></td><td></td><td></td><td></td><td>'+tax+'</td><td>'+total+'</td><td><button id="'+$('.case:checked').closest('tr')[i].children[1].innerHTML+'"onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
				$("#addrowstextfields tr:last").after(tr);
			i++;
		  document.getElementById("hiddenpanel").style.visibility="visible";
		  });
	  }
	$('.case:checked').prop('checked',false);
	var MyIndexValue = 0;
	 var total=0;
	  for (var i = 0; i < $('.se').length; i++) {
	  	MyIndexValue = $('.se')[i].children[12].innerHTML;
	  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
	  	total = total+number;
	  }
	  total = total.toFixed(2);
	  var tr = '<tr class="se text-danger text-bold"><td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td><strong>Grand Total</strong></td>'+
		'<td><strong>'+total+'</strong></td><td></td></tr>';
		$("#addrowstextfields tr:last").after(tr);
	  }

$(".case").change(function() {
    if(this.checked) {
    	$('#payeenames option[value!="Select Payee Name"]').remove();
    	var i=0;
    	 $('.case:checked').each(function () {
    		 var guestid = $('.case:checked').closest('tr')[i].children[9].value;
    		var guestname = $('.case:checked').closest('tr')[i].children[2].innerHTML;
    		var id = Number(guestid.replace(/[^0-9\.]+/g,""));
    		$('#payeenames').append($('<option/>').attr("value", guestid).text(guestname));
    		i++;
   	 	});
   	}
    else{
    	$('#payeenames option[value!="Select Payee Name"]').remove();
    	var i=0;
    	 $('.case:checked').each(function () {
    		 var guestid = $('.case:checked').closest('tr')[i].children[9].value;
    		var guestname = $('.case:checked').closest('tr')[i].children[2].innerHTML;
    		$('#payeenames').append($('<option/>').attr("value", guestid).text(guestname));
    		i++;
   	 	});
    }
});

function deleterow(element) {
    var tr = element.parentNode.parentNode;
    var appointmentid = $(tr)[0].children[1].innerHTML;
    if (!appointmentid){
    	$('#adddiscount').removeAttr('disabled');
	}
    tr.parentNode.removeChild(tr);
    $("#addrowstextfields tr:last").remove();
    var MyIndexValue = 0;
	 var total=0;
	  for (var i = 0; i < $('.se').length; i++) {
	  	MyIndexValue = $('.se')[i].children[12].innerHTML;
	  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
	  	total = total+number;
	  	}
	  total = total.toFixed(2);
	  var tr = '<tr class="se text-danger text-bold"><td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td></td>'+
		'<td><strong>Grand Total</strong></td>'+
		'<td><strong>'+total+'</strong></td><td></td></tr>';
		$("#addrowstextfields tr:last").after(tr);
}

$("#addproduct").click(function() {
	$.ajax({
		type : "POST",
		async : false,
		url : "getStockofRetail",
		success : function(response) { 
				$('#stockId').empty();
				$('#stockId').append($('<option/>').attr("value","").text("Select Product"));
				for (var i = 0, len = response.length; i < len; i=i+2) 
				{
					$('#stockId').append($('<option/>').attr("value", response[i]).text(response[i+1]));
				} 
			}
	});
	
});

$("#addproducttotable").click(function() {
	var productid = document.getElementById("stockId").value;
	var entered = document.getElementById("productquantity").value;
    var quantity = document.getElementById("availability").innerHTML;
    var  stockquantity = Number(quantity.replace(/[^0-9\.]+/g,""));
    var  enteredquantity = Number(entered.replace(/[^0-9\.]+/g,""));
    if(enteredquantity>stockquantity)
    	{
    		alert("Stock in-sufficient!\n Please enter quantity Less than available");
    		document.getElementById('productquantity').focus();
    	}
    else{
			$.ajax({
			type : "POST",
			async : false,
			url : "getproductrate?productid="+productid+"&&quantity="+entered,
			success : function(response) { 
				j=0;
				productcost=response[j];
				tax=response[j+1];
				var total=response[j+2]+"";
				var totalamount = Number(total.replace(/[^0-9\.]+/g,""));
				totalamount = totalamount.toFixed(2);
				productname=response[j+3];
				$("#addrowstextfields tr:last").remove();
				var tr = '<tr class="se"><td>'+$('.se')[0].children[0].innerHTML+'</td>'+
					'<td>'+$('.se')[0].children[1].innerHTML+'</td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td>'+productid+'</td>'+
					'<td>'+productname+'</td>'+
					'<td>'+enteredquantity+'</td>'+
					'<td>'+productcost+'</td><td>'+tax+'</td><td>'+totalamount+'</td><td><button id="'+productid+'"onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
					$("#addrowstextfields tr:last").after(tr);
					var MyIndexValue = 0;
					 var total=0;
					  for (var i = 0; i < $('.se').length; i++) {
					  	MyIndexValue = $('.se')[i].children[12].innerHTML;
					  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
					  	total = total+number;
					  	}
					  total = total.toFixed(2);
					  var tr = '<tr class="se text-danger text-bold"><td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td><strong>Grand Total</strong></td>'+
						'<td><strong>'+total+'</strong></td><td></td></tr>';
						$("#addrowstextfields tr:last").after(tr);
			}
		});
    }
		$("#stockId").prop("selected", false);
		$('#productquantity').val("");
		$('#availability').empty();
});

$("#summarytable").click(function() {
	$("#addsummaryfields").html('<tr></tr>');
	for (var i = 0; i < $('.se').length; i++) {
	  	$('.se')[i].children[8].innerHTML;
	  	var tr = '<tr class="total"><td>'+$('.se')[i].children[0].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[1].innerHTML+'</td>'+
		'<td></td>'+
		'<td></td>'+
		'<td>'+$('.se')[i].children[4].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[5].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[6].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[7].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[8].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[9].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[10].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[11].innerHTML+'</td>'+
		'<td>'+$('.se')[i].children[12].innerHTML+'</td><td></td></tr>';
		$("#addsummaryfields tr:last").after(tr);
		
	  }
});

$("#stockId").change(function(){
    var productid = document.getElementById("stockId").value;
    $.ajax({
		type : "POST",
		async : false,
		url : "getRetailStockAvailability?stockid="+productid,
		success : function(response) { 
				document.getElementById("availability").innerHTML = "Current Avaliability : "+ response;
			}
	});
});

$("#productquantity").change(function(){
    
    
});

$("#generateBill").click(function() {
	var acceptance = confirm("Do you really want to generate bill?");
	if (acceptance == true) {
		$('#generateBill').attr('disabled','disabled');
		var discountreason = $('#reason').val();
		var discountpercent = $('#percent').val();
		var discountamount = $('#amount').val();
		if ((!discountpercent || !discountamount)){
		$.ajax({
			type : "POST",
			async : false,
			url : "savePreBillDiscount?discountreason="+discountreason+"&&discountpercent="+discountpercent+"&&discountamount="+discountamount,
			success : function(response) { 
					
				}
		});
		}
		var DataArray=[];
		var ssetObj={};
		var guestid = $('#payeenames').val();
		for (var i = 0; i < $('.se').length-1; i++) {
			var temp = $('.se')[i].children[1].innerHTML;
			if(!temp){
				ssetObj={
				  		guestId:guestid,
			    		appointmentId: $('.se')[i].children[0].innerHTML,
			    		packageId:$('.se')[i].children[2].innerHTML,
			    		serviceId : $('.se')[i].children[4].innerHTML,
			    		servicerate : $('.se')[i].children[6].innerHTML,
			    		productId	:	$('.se')[i].children[7].innerHTML,
			    		productName : $('.se')[i].children[8].innerHTML,
			    		productrate : $('.se')[i].children[10].innerHTML,
			    		quantity :  $('.se')[i].children[9].innerHTML,
			    		tax : '',
			    		total : $('.se')[i].children[12].innerHTML
		   				}	
    		}
			else
				{
				ssetObj={
				  		guestId:guestid,
			    		appointmentId: $('.se')[i].children[0].innerHTML,
			    		packageId:$('.se')[i].children[2].innerHTML,
			    		serviceId : $('.se')[i].children[4].innerHTML,
			    		servicerate : $('.se')[i].children[6].innerHTML,
			    		productId	:	$('.se')[i].children[7].innerHTML,
			    		productName : $('.se')[i].children[8].innerHTML,
			    		productrate : $('.se')[i].children[10].innerHTML,
			    		quantity :  $('.se')[i].children[9].innerHTML,
			    		tax : ($('.se')[i].children[11].innerHTML),
			    		total : $('.se')[i].children[12].innerHTML
		   				}	
				}
			 DataArray.push(ssetObj);
		}
		$.ajax({
				type : "POST", 
				url : "generateBillDetails",
				async : false,
				contentType : 'application/json',
				data :JSON.stringify(DataArray),
				success : function(response) {
					alert(response);
						window.open("printbillreceipt?billno="+response+"&guestid="+guestid);
						location.replace("appointmentscompletedlistpage");
					}
		});
	} else {
	   
	}
});

function isNumber(evt) {
	
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode<48 || charCode>57)) {
        return false;
    }
    return true;
}

$("#adddiscounttotable").click(function() {
	var discountbypercent = document.getElementById("discountbypercent").value;
	var discountbyamount = document.getElementById("discountbyamount").value;
	var discountreason = document.getElementById("discountreason").value;
    var quantity = document.getElementById("availability").innerHTML;
    var  discountpercent = Number(discountbypercent.replace(/[^0-9\.]+/g,""));
    var  discountamount = Number(discountbyamount.replace(/[^0-9\.]+/g,""));
    var discount = 0;
    $('#reason').val(discountreason);
    $('#percent').val(discountbypercent);
    $('#amount').val(discountbyamount);
    if(discountpercent==0 && discountamount==0)
    	{
    		return;
    	}
    else if(discountamount!=0){
    	discount = discountamount;
    	var totalamount = $('.se')[($('.se').length)-1].children[12].innerHTML+"";
    	totalamount=Number(totalamount.replace(/[^0-9\.]+/g,""));
    	totalamount = totalamount.toFixed(2);
    	if(totalamount<discount)
    		{
    			alert("Discount amount is greater than total amount\n Enter valid amount!");
    			return;
    		}
				$("#addrowstextfields tr:last").remove();
				var tr = '<tr class="se"><td>'+$('.se')[0].children[0].innerHTML+'</td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td>'+
					'<td></td><td><strong>Discount</strong></td><td>'+discount+'</td><td><button id="'+discount+'"onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
					$("#addrowstextfields tr:last").after(tr);
					var MyIndexValue = 0;
					 var total=0;
					  for (var i = 0; i < ($('.se').length)-1; i++) {
					  	MyIndexValue = $('.se')[i].children[12].innerHTML;
					  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
					  	total = number+total;
					  	}
					  MyIndexValue = $('.se')[($('.se').length)-1].children[12].innerHTML;
					  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
					  	total = total-number;
					  	total = total.toFixed(2);
					  var tr = '<tr class="se text-danger text-bold"><td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td></td>'+
						'<td><strong>Grand Total</strong></td>'+
						'<td><strong>'+total+'</strong></td><td></td></tr>';
						$("#addrowstextfields tr:last").after(tr);
						$('#adddiscount').attr('disabled','disabled');
    }
    else if(discountpercent!=0){
    	discount = discountpercent;
    	$("#addrowstextfields tr:last").remove();
		var tr = '<tr class="se"><td>'+$('.se')[0].children[0].innerHTML+'</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td>'+
			'<td></td><td><strong>Discount(%)</strong></td><td>'+discount+'</td><td><button id="'+discount+'"onclick="deleterow(this)"><i class="fa fa-times text-danger" aria-hidden="true"></i></button></td></tr>';
			$("#addrowstextfields tr:last").after(tr);
			var MyIndexValue = 0;
			 var total=0;
			  for (var i = 0; i < ($('.se').length)-1; i++) {
			  	MyIndexValue = $('.se')[i].children[12].innerHTML;
			  	var number = Number(MyIndexValue.replace(/[^0-9\.]+/g,""));
			  	total = number+total;
			  	}
			  	var pertotal = (discount*total)/100;
			  	total = total - pertotal;
			  	total = total.toFixed(2);
			  var tr = '<tr class="se text-danger text-bold"><td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td></td>'+
				'<td><strong>Grand Total</strong></td>'+
				'<td><strong>'+total+'</strong></td><td></td></tr>';
				$("#addrowstextfields tr:last").after(tr);
				$('#adddiscount').attr('disabled','disabled');
    }
		$('#discountbypercent').val("");
		$('#discountbyamount').val("");
		$("#disamount").hide();
		$("#dispercent").hide();
});

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
