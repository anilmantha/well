<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<c:set var="string" value="${message}"/>
				<c:choose>
					<c:when test="${fn:contains(string, 'not')}">
						<div class="row">
							<div class="col-lg-12 col-md-offset-5">
									<h4><span class="label label-danger"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:when>
					<c:when test="${message==null}">
					</c:when>
					<c:otherwise>
						<div class="row">
							<div class="col-lg-12 col-md-offset-5">
									<h4><span class="label label-success"  id="message"><c:out value="${message}"/></span></h4>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Add Manufacturer Details</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<form action="saveManufacturer" method="post">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="table table-no-bordered">
							<tbody>
								<tr>
									<td width="12%"><label>Manufacturer</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" required id="manufacturername" name="manufacturername" /></td>
								
								 <td width="5%">&nbsp;</td>
									<td width="14%"><label>Contact Person1</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" required id="contactperson1" name="contactperson1" /></td>
								</tr>
								<tr>
									<td width="12%"><label>Address1</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" required id="address1" name="address1" /></td>
								
								<td width="5%">&nbsp;</td>
									<td width="14%"><label>Phone1</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="phone1" name="phone1" maxlength="10" onkeypress="return isNumber(event)"/></td>
								</tr>
								<tr>
									<td width="12%"><label>Fax1</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="fax1" name="fax1" onkeypress="return isNumber(event)"/></td>
								
							<td width="5%">&nbsp;</td>
									<td width="14%"><label>Email Id1</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="email1" name="email1" /></td>
								</tr>
								<tr>
									<td width="12%"><label>Contact Person2</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="contactperson2" name="contactperson2" /></td>
								<td width="5%">&nbsp;</td>
								
									<td width="14%"><label>Address2</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" required id="address2" name="address2" /></td>
								</tr>
								<tr>
									<td width="12%"><label>Phone2</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="phone2" name="phone2" maxlength="10" onkeypress="return isNumber(event)"/></td>
								<td width="5%">&nbsp;</td>
								
									<td width="14%"><label>Fax2</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="fax2" name="fax2" onkeypress="return isNumber(event)"/></td>
								</tr>
							<tr>
									<td width="12%"><label>Email Id2</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="email2" name="email2" /></td>
								<td width="5%">&nbsp;</td>
								
									<td width="14%"><label>Contact Person3</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="contactperson3" name="contactperson3" /></td>
								</tr>
								<tr>
									<td width="12%"><label>Address3</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" required id="address3" name="address3" /></td>
								
								<td width="5%">&nbsp;</td>
									<td width="14%"><label>Phone3</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="phone3" name="phone3" maxlength="10" onkeypress="return isNumber(event)"/></td>
								</tr>
								<tr>
									<td width="12%"><label>Fax3</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="fax3" name="fax3" onkeypress="return isNumber(event)"/></td>
								
							<td width="5%">&nbsp;</td>
									<td width="14%"><label>Email Id3</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="email3" name="email3" /></td>
								</tr>
								<td width="12%"><label>Note</label></td>
									<td class="col-md-3"><textarea class="form-control" rows="5" id="remarks"
											maxlength="255" name="remarks"></textarea></td>
									<td width="5%">&nbsp;</td>
								<tr>
									<td>
										<div class="col-md-8"></div>
									
											<div class="pull-right"></div>
												<td>
													<button type="submit" class="btn btn-primary btn-sm">Save</button>
												</td>
												<td>
													<button type="submit" class="btn btn-primary btn-sm">Clear</button>
												</td>
												</tr>
											
								</tbody>
								
						</table>
						
						</form>
						
						

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
    
    setTimeout(function() {
        $('#message').fadeOut('fast');
    }, 2000);
    
    </script>

                 

<!-- Main Content Area -->
<!-- /.container-fluid -->

