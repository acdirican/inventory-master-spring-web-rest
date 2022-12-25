<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>

<div class="mt-3">
	<h2>Search</h2>
	<form action="search" class="colorlib-subscribe-form" method="get">
       <div class="form-group d-flex">
       	<div class="icon"><span class="icon-paper-plane"></span></div>
         <input type="text" name="query" class="form-control" placeholder="search query">
       </div>
     </form>
</div>

<%@include file='footer.jsp'%>