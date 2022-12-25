<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>

<div class="mt-3">

	<h2>Add New Product</h2>
	<form action="" method="post">
	  <div class="form-group">
	    <label for="name">Product Name</label>
	    <input type="text" class="form-control" id="name" name="name" placeholder="Enter a name" required>
	  </div>
	  <div class="form-group">
	    <label for="name">Quantity</label>
	    <input type="number" class="form-control" id="quantity" name="quantity" 
	    placeholder="Quantity of the product" value="0" min="0" required>
	  </div>
	  <div class="form-group">
	    <label for="name">Supplier:</label>
	    <select class="form-control" id="supplier" name="supplier" placeholder="Select a supplier">
	    	<c:forEach items="${suppliers}" var="supplier" varStatus="status">
	    		<option value="${supplier.ID}">${supplier.name}</option>
	    	</c:forEach>
	    </select>
	  </div>
	  
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>

<%@include file='footer.jsp'%>