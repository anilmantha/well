<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



   <div class="container-fluid">
      <div class="row">
		 <div class="col-lg-12">
		   <h1 class="page-header"></h1>
		     </div>
	           </div>

	<!-- Main Content Area -->
	
	       <div class="row">
		     <div class="col-lg-12">
			   <div class="panel panel-default">
				 <div class="panel-heading">
				   <h3 class="panel-title">Edit Department</h3>
			         </div>
				       <div class="panel-body">
					     <div class="col-md-11">
					       <form action="updatedepartment" method="post">
						     <table width="100%" border="0" cellspacing="0" cellpadding="0"
							       class="table table-no-bordered">
							    <tbody>
							     <tr>
									<td width="12%"><label>DepartmentId</label></td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="departmentid" name="departmentid" value="${departmentmaster.departmentid }" required="required"/></td>
								    <td width="5%">&nbsp;</td>
									<td width="14%">&nbsp;</td>
									<td class="col-md-3">&nbsp;</td>
							      </tr>
								  <tr>
									 <td width="12%"><label>Department Name</label></td>
									 <td class="col-md-3"><input type="text"
										 class="form-control" id="departmentname" name="departmentname" value="${departmentmaster.departmentname }" required="required"/></td>
								     <td width="5%">&nbsp;</td>
									 <td width="14%">&nbsp;</td>
									 <td class="col-md-3">&nbsp;</td>
				                </tr>
							    <tr>
									<td width="12%"><label>Description</td>
									<td class="col-md-3"><input type="text"
										class="form-control" id="description" name="description" value="${departmentmaster.description}" required="required"/></td>
								    <td width="5%">&nbsp;</td>
									<td width="14%">&nbsp;</td>
									<td class="col-md-3">&nbsp;</td>
							    </tr>
							    <tr><td width="12%"><label>Valid</label></td>
								    <td>
                                    <select  class="form-control" id="valid" name="valid" required="required">
                            	    <option value="true">True</option>
                                    <option value="false">False</option>
                                    </select>
                                    </td>
                                    </tr> 
								    <tr>
									<td></td>
									<td>
									<button type="submit" class="btn btn-primary btn-sm">Update</button>
								   </td>
								   <td></td>
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
                 <!-- Main Content Area -->
                 <!-- /.container-fluid -->
                 
                 
                 
    <script type="text/javascript">
       function isNumber(evt)
       {
          evt = (evt) ? evt : window.event;
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode > 31 && (charCode<48 || charCode>57)) {
          return false;
        }
       return true;
     }
   </script>
   <script type="text/javascript">
	   function isNumberKey(evt, element)
	   {
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
