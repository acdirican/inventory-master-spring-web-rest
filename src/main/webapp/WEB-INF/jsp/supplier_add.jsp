<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>

<div class="mt-3">

	<h2>Add New Supplier</h2>
	<form action="" method="post">
	  <div class="form-group">
	    <label for="name">Supplier Name</label>
	    <input type="text" class="form-control" id="name" name="name" placeholder="Enter a name">
	  </div>
	 
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>

<%@include file='footer.jsp'%>