<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<small>Manual Feedback</small>
						</h1>
						<!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
					</div>
				</div>

				<!-- Main Content Area -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Enter Comments Here</h3>
							</div>
							<div class="panel-body">
								<div class="col-md-9">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="table table-no-bordered">
										<form action="" method="post"  id="serviceform">
											<tbody>
												<tr>
													<td><label>Department Name</label></td>
													<td><select class="form-control" name="departmentid"
														id="departmentid" >
															<option>Select</option>
															<c:forEach var="departmentName" items="${departmentnames}">
															<option value="${departmentName.departmentid}">${departmentName.departmentname}</option>

															</c:forEach>
													</select></td>
                                              <tr>
													<td class="col-md-3"><label>Comments</label></td>
													<td class="col-md-6"><input type="text" class="form-control"  maxlength="150" name="comments"></td>
													<td class="col-md-6">&nbsp;</td>
												</tr>
													<td>&nbsp;</td>
												<tr>
												<td><div class="pull-right">
															<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="savemanualFeedback()">
														<a href="ManualFeedback"><input type="button" class="btn btn-primary btn-sm" value="Close"></a>
												
									             </div>	</td>
									</tr>
									</tbody>
								</form>
							</table>
                         	</div>
						<div class="col-md-3"></div>
					</div>
				</div>
			</div>
		</div>

				<!-- Main Content Area -->
				<!-- /.container-fluid -->
<script type="text/javascript">
function savemanualFeedback(){
	$('#serviceform').attr('action', 'savemanualFeedback');
	$('#serviceform').submit();
}


</script>
			