<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<small>Credit List</small>
			</h1>
		</div>
	</div>
	<!-- Main Content Area -->
	<br>
	<div class="row">
		<form method="get" id="submitcreditlist">
			
			<div class= "obutton feature2" data-id="<?php echo $bookID;?>">
    <button class="reserve-button">Reserve Book</button>
</div>
		</form>
	</div>
	<br>
	
</div>
<script type="text/javascript">
var url = "<spring:message code="wellnessservices_url" />";
$('.reserve-button').click(function(){
	
	alert("hi@@@@@@@@@@@@@@@@@11111111111111");
	
	
	$.ajax({
		type : "POST", 
		url : url+'master/supplier/',
		contentType :   'application/json',
		data : JSON.stringify(supplierobj),
		ststusCode :{
			201: function(response,status) {
				alert("hi@@@@@@@@@@@@@@@@@22222222222"+response);
			
			
		},
		500:function(){
				
			},
		400:function(){
				
			},
		0: function() {
				
			}
		}
	});

  
/* 
    $.ajax
    ({ 
        url: 'getMyPackages',
        headers: {
            Accept: 'application/json'
        },
        dataType: 'json',
        success: function(response)
        {
        	for(var i=0;i<response.length;i++){
        		alert(response[i].id);
        		alert(response[i].name);
        		
        		
        	}
        	
        	
        		
        		
        	
        }
    }); */
});
	
</script>