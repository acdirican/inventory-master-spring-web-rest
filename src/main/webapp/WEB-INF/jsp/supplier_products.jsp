<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invetory Master - Products</title>
</head>
<body>
	<h3>ID:${supplier.ID}</h3>
	<h3>Name:${supplier.name}</h3>
	<table>
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>ID</td>
				<td>Name</td>
				<td>Quantity</td>
			</tr>
		</thead>
		<c:forEach items="${products}" var="product" varStatus="status">
			<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
			
				<td>${product.ID}</td>
				<td>${product.name}</td>
				<td>${product.quantity}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>