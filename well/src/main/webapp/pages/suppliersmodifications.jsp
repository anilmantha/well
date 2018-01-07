<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
			<!--<ol class="bread crumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
		</div>
	</div>

	<!-- Main Content Area -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Update Supplier Details</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">

							<form action="updatesupplier" method="post">
								<input type="hidden" class="form-control" required
									id="supplierid" name="supplierid"
									value="${supplier.supplierid}" />
								<tbody>
									<tr>
										<td width="12%"><label>Supplier Name</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="suppliername"
											name="suppliername" value="${supplier.suppliername}" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Contact Person1</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="contactperson1"
											name="contactperson1" value="${supplier.contactperson1}" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Address1</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="address1" name="address1"
											/ value="${supplier.address1}"></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Phone1</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="phone1" name="phone1"
											value="${supplier.phone1}" maxlength="10"
											onkeypress="return isNumber(event)" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Fax1</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="fax1" name="fax1"
											value="${supplier.fax1}" onkeypress="return isNumber(event)" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Email Id1</label></td>
										<td class="col-md-3"><input type="email"
											class="form-control" required id="email1" name="email1"
											value="${supplier.email1}" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Contact Person2</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="contactperson2"
											name="contactperson2" value="${supplier.contactperson2}" /></td>
										<td width="5%">&nbsp;</td>

										<td width="14%"><label>Address2</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" required id="address2" name="address2"
											value="${supplier.address2}" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Phone2</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="phone2" name="phone2"
											value="${supplier.phone2}" maxlength="10"
											onkeypress="return isNumber(event)" /></td>
										<td width="5%">&nbsp;</td>

										<td width="14%"><label>Fax2</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="fax2" name="fax2"
											value="${supplier.fax2}" onkeypress="return isNumber(event)" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Email Id2</label></td>
										<td class="col-md-3"><input type="email"
											class="form-control" id="email2" name="email2"
											value="${supplier.email2}" /></td>
										<td width="5%">&nbsp;</td>

										<td width="14%"><label>Contact Person3</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="contactperson3"
											name="contactperson3" value="${supplier.contactperson3}" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Address3</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="address3" name="address3"
											value="${supplier.address3}" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Phone3</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="phone3" name="phone3"
											value="${supplier.phone3}" maxlength="10"
											onkeypress="return isNumber(event)" /></td>
									</tr>
									<tr>
										<td width="12%"><label>Fax3</label></td>
										<td class="col-md-3"><input type="text"
											class="form-control" id="fax3" name="fax3"
											value="${supplier.fax3}" onkeypress="return isNumber(event)" /></td>

										<td width="5%">&nbsp;</td>
										<td width="14%"><label>Email Id3</label></td>
										<td class="col-md-3"><input type="email"
											class="form-control" id="email3" name="email3"
											value="${supplier.email3}" /></td>
									</tr>
									<%-- 	 <c:if test="${supplier.wholesale==true}"> --%>

									<%-- <tr>
									
									
										<div class="form-group">
										<td width="12%"><label>Whole sale</label></td>
											<c:choose>
							        			<c:when test="${supplier.wholesale==true}">
							        				<td class="col-md-3"><input type="checkbox"
																		class="checkbox " value="${supplier.wholesale}" checked name="wholesale"></td>
							        			</c:when>
							        			<c:otherwise>
							        				<td class="col-md-3"><input type="checkbox"
																		class="checkbox " value="false" name="wholesale"></td>
							        			</c:otherwise>
						        			</c:choose>
										</div> --%>


									<!-- 	<td width="5%"></td> -->
									<td width="14%"><label>Note</label></td>
									<td class="col-md-3"><textarea class="form-control"
											rows="5" id="remarks" maxlength="255" name="remarks"
											value="${supplier.remarks}">${supplier.remarks}</textarea></td>
									<td>&nbsp;</td>
									<!-- </tr> -->

									<tr>
										<td>
											<div class="col-md-8">
												<div class="pull-right">
													<td>
														<button type="submit" class="btn btn-primary btn-sm">Update</button>
													</td>
												</div>
						</table>

						</form>
						</tbody>
						</table>


					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
	</div>
</div>
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




<!-- Main Content Area -->
<!-- /.container-fluid -->

