
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="viewname" value="${param.name}" />
<c:out value="${pagina}" /> 
-->

<!-- To active the current menu item according to the active web page -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="pagina"
	value="${requestScope['javax.servlet.forward.request_uri']}" />

<style>
	#sidebar li {
		padding-left:2px;
	}
	#sidebar .active {
		background-color:#ffffff22;
	}
</style>

<div class="wrapper d-flex align-items-stretch">
	<nav id="sidebar">
		<div class="custom-menu">
			<button type="button" id="sidebarCollapse" class="btn btn-primary">
				<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
			</button>
		</div>
		<div class="p-4 pt-5">
			<h1>
				<a href="/" class="logo">Inventory Master</a>
			</h1>
			<ul class="list-unstyled components mb-5">
				<li class="${pagina.equals('/') ? 'active' : ''}"><a href="/">Home</a>
				</li>
				<li class="${pagina.startsWith('/about') ? 'active' : ''}"><a
					href="/about">About</a></li>
				<li class="${pagina.startsWith('/supplier') ? 'active' : ''}"><a
					href="#supSubmenu" data-toggle="collapse" aria-expanded="false"
					class="dropdown-toggle">Suppliers</a>
					<ul class="collapse list-unstyled" id="supSubmenu">
						<li><a href="/supplier/list">List</a></li>
						<li><a href="/supplier/add">Add New</a></li>
						<li><a href="/supplier/search">Search</a></li>
					</ul></li>

				<li class="${pagina.startsWith('/product') ? 'active' : ''}"><a
					href="#productSubmenu" data-toggle="collapse" aria-expanded="false"
					class="dropdown-toggle">Products</a>
					<ul class="collapse list-unstyled" id="productSubmenu">
						<li><a href="/product/list">List</a></li>
						<li><a href="/product/add">Add New</a></li>
						<li><a href="/product/search">Search</a></li>
					</ul></li>

				<li class="${pagina.startsWith('/log') ? 'active' : ''}">
				<a href="#logSubmenu" data-toggle="collapse" aria-expanded="false"
					class="dropdown-toggle">Logs</a>
					<ul class="collapse list-unstyled" id="logSubmenu">
						<li><a href="/log/list">List</a></li>
					</ul></li>


				<li class="${pagina.startsWith('/contact') ? 'active' : ''}"><a href="/contact">Contact</a></li>
			</ul>

			<div class="mb-5">
				<h3 class="h6">Search</h3>
				<form action="search" class="colorlib-subscribe-form" method="get">
					<div class="form-group d-flex">
						<div class="icon">
							<span class="icon-paper-plane"></span>
						</div>
						<input type="text" name="query" class="form-control"
							placeholder="search query">
					</div>
				</form>
			</div>

			<div class="footer">
				<p>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | This template is made with <i
						class="icon-heart" aria-hidden="true"></i> by <a
						href="https://colorlib.com" target="_blank">Colorlib.com</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>
			</div>

		</div>
	</nav>

	<!-- Page Content  -->
	<div id="content" class="p-4 p-md-5 pt-5">