<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>
<div class="mt-3">
	
	<h2>Supplier Management</h2>
	<div class="row">
		<div class="col-md-6">
			<form class="mt-3" id="form" method="post">
			  <div class="form-group mb-3">
			    <label for="id">ID:</label>
			    <input type="text" class="form-control" id="ID" name="ID" value="${supplier.ID}" readonly>
			  </div>
			  <div class="form-group mb-3">
			    <label for="name">Name</label>
			    <input type="text" class="form-control" id="name" name="name" value="${supplier.name}">
			  </div>	
			  <div class="form-group mb-3">
			  	<button class="btn btn-primary" onclick="submitForm('update');">Update</button>
			  	<button class="btn btn-danger" onclick="submitForm('remove');">Delete</button>
			  </div>	  
			</form>
		</div>
		<div class="col-md-6">
			<h4>Supplier's Products </h4>
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
						<td>${product.name}</td>
						<td>${product.quantity}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</div>

<script>
	function submitForm(action)
	{
		if(confirm("Do you want to proceed delete or update!")){
			 document.getElementById('form').action = action;
			 document.getElementById('form').submit();
		}
	   
	}
</script>

<%@include file='footer.jsp'%>