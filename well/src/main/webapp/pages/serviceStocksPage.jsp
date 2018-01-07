<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri= "http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Stock Id</th>
			<th>Stock Name</th>

			<th>Warning Level</th>
			<th>Re-Order Level</th>

			<th>Usage</th>
			<th>Cost</th>
			<th>Available</th>
			
			
			<th>Availability After Usage</th>
		</tr>
	</thead>
	<tbody>
	<c:set var="i" value="1" scope="page"></c:set>
		<c:forEach var="user" items="${serviceStockList}">
			<tr>
				<td id="stockId${i}">${user[0]}</td>
				<td id="stockName${i}">${user[1]}</td>

				<td id="warning${i}">${user[5]}</td>
				<td id="reorder${i}">${user[6]}</td>

				<td id="usage${i}">${user[2]}</td>
				<td id="cost${i}">${user[3]}</td>
				<td id="available${i}">${user[4]}</td>
				<td id="availableAfter${i}">${user[7]}</td>
			</tr>
			<c:set var="i" value="${i+1}" scope="page"></c:set>
		</c:forEach>
	</tbody>
</table>

<input type="hidden" id="count" name="count" value="${i-1}"/>

</body>
</html>
