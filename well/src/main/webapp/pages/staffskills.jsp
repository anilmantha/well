<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h2 class="page-header">
							Staff Skills
						</h2>
						
					</div>
				</div>

				<!-- Main Content Area -->
				<div class="row">
					<div class="col-lg-12">
					<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"> Staff-Skills </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Staff Related Skill Addition</th>
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                    	<td><div class="form-group">
						  <label>Select Staff</label>
						  <select class="form-control" id="staffname" scrolling="yes">
							<option>Select</option>
							<c:forEach var="stf" items="${staffs}">
								<option value="${stf.staffid}">${stf.staffname}</option>
							</c:forEach>
						  </select>
						</div></td>
                       	<td><div class="form-group">
						  <label>Select Skills</label>
						  <select multiple class="form-control" id="skillname">
							<option>Select</option>
							<c:forEach var="skl" items="${skills}">
								<option value="${skl.skillid}">${skl.skillname}</option>
							</c:forEach>
						  </select>
						</div></td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            <div class="row">
                
                <div class="col-md-12">
                  <div class="pull-right">
	                  <input type="button" value="Add" id="add" name="add" class="btn btn-primary btn-sm" onclick="addskillrows()"/>
	                   <a href="staffskills"><input type="button" value="Cancel" id="cancel" name="cancel" class="btn btn-primary btn-sm"/></a>
                  </div>
                </div>
                <div class="col-md-12"> <h1></h1></div>
                <div class="col-md-12" id="divhidden" style="visibility: hidden">
                <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"> Selected Skills  </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="table-responsive padding-10">
                <form action="" class="col-md-7" id="staffskillsave">
                  <table class="table table-bordered table-hover">
                    <tbody id="stafftext">
                    </tbody>
                  </table> 
                  <div class="col-md-12">
                  <div class="pull-right">
	                 <input type="button" value="Save" class="btn btn-primary btn-sm" onclick="savestaffskill()"/>
	                  <a href="staffskills"><input type="button" value="Clear" id="clear" name="clear" class="btn btn-primary btn-sm"/></a>
                   </div>
                </div>
                  </form>
                </div>
                
            <table class="table table-bordered table-hover">
                    <tbody id="addrowstextfields">				
							
						</tbody>

                  </table>
     			</div>
              </div>
            </div>
          </div>
                </div>
              </div>
            </div>
          </div>
					</div>
				</div>
			
			<!-- Main Content Area -->
			<!-- /.container-fluid -->
<script type="text/javascript">

function addskillrows()
{
	$("#stafftext").html('<tr></tr>');
	document.getElementById("divhidden").style.visibility="visible";
   	for (var i = 0; i < skillname.options.length; i++) {
     if(skillname.options[i].selected ==true){
    	 var tr = '<tr class="se">'+
			'<td>SkillName<input type="hidden" class="form-control" value="'+skillname.options[i].value+'" name="'+skillname.options[i].text+'"></td><td>'+skillname.options[i].text+'</td></tr>';
			$("#stafftext tr:last").after(tr);
      }
  }
}
	
function savestaffskill()
{

	var x = document.getElementById("staffskillsave");
	var i;
	var skillid;
    var staffid;

  	var sfs=[];
    for (i = 0; i <$('.se').length; i++) {
        
      staffid =  document.getElementById("staffname").value;
       skillid = $('.se')[i].children[0].children[0].value;
        var ssetObj={};
    	
       
       ssetObj={
    		   staffid  : staffid,
    		   skillid: skillid,
    
    	}
       sfs.push(ssetObj);
    
    }
  
    $.ajax({
		type : "POST", 
		url : "savestaffskills",
		contentType : 'application/json',
		data :JSON.stringify(sfs),
		success : function(response) {
		
		get();
		}
	});
  
  }
function get() {
	var id = document.getElementById("staffname").value;
	$("#addrowstextfields").html('<tr></tr>');
	$.ajax({
		type : "GET",
		async : false,
		url : "getSkill?staffid="+id,
		success : function(response) {
		
			var length = response.length;
			for(var i=0; i<length;i=i+3)
				{
				var tr = '<tr class="se">'+
      			'<td><input type="text" disabled class="form-control" value="'+response[i+1]+'" name="'+response[i+1]+'"></td>';
      			$("#addrowstextfields tr:last").after(tr);
				}
		} 
	});
}
</script>


