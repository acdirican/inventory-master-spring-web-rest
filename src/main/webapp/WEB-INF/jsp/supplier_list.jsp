<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>

<div class="mt-3">
	<h2>Supplier List</h2>
	<table class="table">
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<th>#</th>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
	
		<c:forEach items="${suppliers}" var="supplier" varStatus="status">
			
				<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
					<td>${status.index + 1}</td>
					<td>${supplier.ID}</td>
					<td><a href="${supplier.ID}">${supplier.name}</a></td>
				</tr>
			
		</c:forEach>
	</table>
</div>

<%@include file='footer.jsp'%>