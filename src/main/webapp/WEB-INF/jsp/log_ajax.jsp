<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<table class="table">
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>#</td>
				<td>ID</td>
				<td>Quantity</td>
				<td>Type</td>
				<td>Time</td>
			</tr>
		</thead>
		<c:forEach items="${logs}" var="log" varStatus="status">
			<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
				<td>${status.index + 1}</td>
				<td>${log.ID}</td>
				<td>${log.quantity}</td>
				<td>${log.type}</td>
				<td>${log.time}</td>
			</tr>
		</c:forEach>
	</table>
