<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- changes -->


<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>SuppliersList</small>
			</h1>
		</div>
	</div>
	<!-- Main Content Area -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">List of Suppliers</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12"></div>
						<hr>
						<div class="row">
							<div class="table-responsive padding-10">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th></th>
											<th>Supplier Name</th>
											<th>Contact Person1</th>
											<th>Address1</th>
											<th>Phone1</th>
											<th>Fax1</th>
											<th>EmailId1</th>
											<th>Contact Person2</th>
											<th>Address2</th>
											<th>Phone2</th>
											<th>Fax2</th>
											<th>EmailId2</th>
											<th>Contact person3</th>
											<th>Address3</th>
											<th>Phone3</th>
											<th>Fax3</th>
											<th>EmailId3</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="supplier" items="${supplierList}">
											<tr id="trow">
												<td>
												<a href="opensuppliereditpage?supplierid=${supplier.supplierid}">${supplier.supplierid}
												</td>
												</a>
												<td>${supplier.getSuppliername()}</td>
												<td>${supplier.getContactperson1()}</td>
												<td>${supplier.getAddress1()}</td>
												<td>${supplier.getPhone1()}</td>
												<td>${supplier.getFax1()}</td>
												<td>${supplier.getEmail1()}</td>
												<td>${supplier.getContactperson2()}</td>
												<td>${supplier.getAddress2()}</td>
												<td>${supplier.getPhone2()}</td>
												<td>${supplier.getFax2()}</td>
												<td>${supplier.getEmail2()}</td>
												<td>${supplier.getContactperson3()}</td>
												<td>${supplier.getAddress3()}</td>
												<td>${supplier.getPhone3()}</td>
												<td>${supplier.getFax3()}</td>
												<td>${supplier.getEmail3()}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="pull-left">
									<ul class="pagination pagination-sm">
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
									</ul>
								</div>
							</div>
							<!-- <div class="col-md-8">
                  <div class="pull-right">
                    <button type="submit" class="btn btn-primary btn-sm">Close</button>
                  </div>
                </div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Main Content Area -->
<!-- /.container-fluid -->



