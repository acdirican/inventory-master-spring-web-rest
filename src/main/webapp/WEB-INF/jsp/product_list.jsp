<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>

<div class="mt-3">
	<h2>Product List</h2>
	<table class="table">
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>#</td>
				<td>ID</td>
				<td>Name</td>
				<td>Quantity</td>
			</tr>
		</thead>
		<c:forEach items="${products}" var="product" varStatus="status">
			<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
				<td>${status.index + 1}</td>
				<td>${product.ID}</td>
				<td><a href="${product.ID}">${product.name}</a></a></td>
				<td>${product.quantity}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<%@include file='footer.jsp'%>