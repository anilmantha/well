<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form action="">
	
	<!-- Page Heading -->
	
	<div class="row">

		<div class="col-lg-12">
		
			<h1 class="page-header">CreateRoster</h1>
			
		</div>
		
	</div>
	
	<div class="col-md-3"></div>
	
	<input type="hidden" name="staffId" value="${staffname1.staffid}"/>
	
							<table id="EditTable" class="table table-bordered table-hover">
							
								<thead>
								
										<tr>
										<th>Staff Id</th>
										<th>StaffName</th>
										<th>Department Name</th>
										<th>WeekOff</th>
										</tr>
										
									</thead>
								
								 <tbody id="addrowdata">
								 
                 			 <tr>
                 			 
                          <td >${staffname1.staffid}</td>
                          
                         <td>${staffname1.staffname}</td>
                         
                    	  <td>${staffname1.getDepartmentmaster().getDepartmentname()}</td>
                    	  
                    	<c:if test="${staffname1.weekoff == 1}">
                    	
                    	  <td>Sunday</td>
                    	  
						</c:if>
						
						<c:if test="${staffname1.weekoff == 2}">
						
                    	  <td>Monday</td>
                    	  
						</c:if>
						
						<c:if test="${staffname1.weekoff == 3}">
						
                    	  <td>Tuesday</td>
                    	  
						</c:if>
						
						<c:if test="${staffname1.weekoff == 4}">
						
                    	  <td>Wednesday</td>
                    	  
						</c:if>
						<c:if test="${staffname1.weekoff == 5}">
                    	  <td>Thursday</td>
						</c:if>
						<c:if test="${staffname1.weekoff == 6}">
                    	  <td>Friday</td>
						</c:if>
						<c:if test="${staffname1.weekoff == 7}">
                    	  <td>Saturday</td>
						</c:if>
						</tr>
						</tbody>
							</table>
							
					 <div class="row">
					 
        	<div class="col-md-4">
          	<div class="panel panel-default">
            <div class="panel-heading">
            <h3 class="panel-title">Roster for the Month of:</h3>
            </div>
           	<tbody>
                    <tr>
                    
                     <td><select id="month" name="month">
                     
                          <option value="">Month</option>
                          
                         <c:set var="count" value="1" scope="page"/>
                         
                          <c:forEach var="month" items="${month}">
                          
                          <option value="${count}">${month.value}</option>
                          
                         <c:set var="count" value="${count + 1}" scope="page"/>
                         
                          </c:forEach>
                          
                        	</select></td>
                        	
                        	<td>
                        	
                        	<select id="year" name="year">
                        	
                         	 	<option value="">year</option>
                         	 	
                       			 <option>2012</option>
                               	<option>2013</option>
                               	<option>2014</option>
                               	<option>2015</option>
                               	<option>2016</option>
                        		<option>2017</option>
                               <option>2018</option>
                               <option>2019</option>
                               <option>2020</option>
                               
            					</select>
                        </td>
                      </tr>
				</div>
				
			</div>
			
			</div>
			
		<input type="button"  class="btn btn-primary btn-sm" id="search" value="Generate Roster" />
		
		<table id="calendarTable" class="table table-bordered table-hover">
		
		</table>
		
		  <input type="button" class="btn btn-primary btn-sm" value="save" id="save">
		 </form>
		 
		</div>
	
	<script type="text/javascript">

	$('#save').click(function(){
		
	var staffId = document.getElementById("EditTable").rows[1].cells.item(0).innerHTML;
	
	var slemonth=document.getElementById("month").value;
	
	var year=document.getElementById("year").value;
	
	var weekDay = document.getElementById("EditTable").rows[1].cells.item(3).innerHTML;
	
	var updateArr=[];
	
	var inputElements = document.getElementsByClassName('case1');
	
	   
	
	 for(var i=0; inputElements[i]; ++i){
		 
		 if($('.case1')[i].checked)
	       updateArr.push(1);
		 
		 else{
			 
			 updateArr.push(0);
		 }
		 
		}
	 
	 alert(updateArr);
	
	$.ajax({
		
		type : "POST",
		url : 'UpdateRoster?staffId='+staffId+'&slemonth='+slemonth+'&year='+year+'&weekDay='+weekDay,
		contentType : 'application/json',
		data : JSON.stringify(updateArr),
		success : function(response) { 
			alert(response);
		}
	}); 
});


 $('#search').click(function()
		 
		 {
	 
	var staffId = document.getElementById("EditTable").rows[1].cells.item(0).innerHTML;
	var weekDay = document.getElementById("EditTable").rows[1].cells.item(3).innerHTML;
	var slemonth=	document.getElementById("month").value;
	var year=document.getElementById("year").value;
	
	var arryval=[];
	
	$.ajax({
		
	type : "GET",
	
	async : false,
	
	url : 'checkmonthyear?staffId='+staffId+'&slemonth='+slemonth+'&year='+year,
			
	success : function(response) { 
		
		if(response == null || response == ""){
			
			$('#calendarTable').html("");
			$.ajax({
				type : "GET",
				async : false,
				url : 'addRooster?staffId='+staffId+'&weekDay='+weekDay+'&slemonth='+slemonth+'&year='+year+"&response="+response,
				success : function(response) { 
					
					arryval=response;
					}
		});
	}
		else{
			$('#calendarTable').html("");
			alert("Roaster Already Done For Employee")
			$.ajax({
				type : "GET",
				async : false,
				url : 'addRooster?staffId='+staffId+'&weekDay='+weekDay+'&slemonth='+slemonth+'&year='+year+"&response="+response,
				success : function(response){ 
					
					arryval=response;
					
					}
				});
			
			}
		}
	
	
});

var year = year; 

var month = slemonth;

var days = [ 'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT' ];
var date = new Date(year,month-1, 1);
var resultDate = [];
var resultDay = [];

	while (date.getMonth() == month-1) {
	
	resultDate.push(date.getDate());
	resultDay.push(days[date.getDay()]);
	date.setDate(date.getDate()+1);

}
var xs = "<thead><tr>";
for(var x = 0; x < days.length; x++)
{
	xs += "<th>"+days[x]+"</th>";
}
xs += "</tr></thead><tbody>";
for(var i = 0; i < resultDate.length; )
{
	xs += "<tr>";
	var index = days.indexOf(resultDay[i]);
	for(var j = 0; j < days.length; j++)
	{
		if(j < index)
		{
			xs += "<td></td>";
			continue;
		}
		else
		{
			if(i < resultDate.length){
				xs += "<td>"+resultDate[i];
				
				if(arryval[i] == 2 || arryval[i] == 0){
					
				xs+="<input type=\"checkbox\" name=\"case1\" class=\"case1\"></td>";
				
				}
				
				else{
					
					xs+="<input type=\"checkbox\" name=\"case1\" class=\"case1\" checked=\"checked\"></td>";
				}
				
			}
			else
				
			{
				xs += "<td></td>";
			}
			
			i++;
		}
		
		}
	
	xs += "</tr>";
	
		}
	xs +="</tbody>";
	$('#calendarTable').append(xs);
	});

</script>



