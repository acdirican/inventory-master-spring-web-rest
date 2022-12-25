<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file='head.jsp'%>
<%@include file='header.jsp'%>


<div class="mt-3">
	<div class="row">
		<div class="col-md-6">
			<h2>Product Management</h2>
				<form id="productForm" action="" method="post">
				<div class="form-group">
				    <label for="name">Product ID</label>
				    <input type="text" class="form-control" id="id" name="ID" value="${product.ID }" required readonly>
				  </div>
				  <div class="form-group">
				    <label for="name">Product Name</label>
				    <input type="text" class="form-control" id="name" name="name" placeholder="Enter a name"
				     value="${product.name }" required>
				  </div>
				  <div class="form-group">
				    <label for="name">Quantity</label>
				    <input type="number" class="form-control" id="quantity" name="quantity" 
				    placeholder="Quantity of the product" min="0" 
				     value="${product.quantity}" required>
				  </div>
				  <div class="form-group">
				    <label for="name">Supplier:</label>
				    <select class="form-control" id="supplier" name="supplier" placeholder="Select a supplier">
				    	<c:forEach items="${suppliers}" var="supplier" varStatus="status">
				    		<option ${supplier.ID == product.supplier.ID ? "selected" : "" } value="${supplier.ID}">${supplier.name}</option>
			  	    	</c:forEach>
				    </select>
				  </div>
				  
				    <div class="form-group mb-3">
					  	<button class="btn btn-primary" onclick="submitForm('update');">Update</button>
					 
					  	<button class="btn btn-warning" onclick="submitForm('increase');">Increase</button>
					  	<button class="btn btn-warning" onclick="submitForm('decrease');">Decrease</button>
					  	 - 
					  	 <button class="btn btn-danger" onclick="submitForm('remove');">Delete</button>
					  </div>
				</form>
		</div>
		<div  class="col-md-6 mt-5">
			<h4>Logs</h4>
			<div id="logs" onclick="getLogs(${product.ID});">Click me to get logs...</div>
		</div>
	</div>
</div>

<script>

	function submitForm(action)
	{
		if(confirm("Do you want to proceed to " +action)){
			 document.getElementById('productForm').action = action;
			 if (action == "increase" || action == "decrease"){
				 val = prompt("How much do you want to " + action + "?"); 
				 current = $("#quantity").val();
				 if (action=="increase"){
					 newVal = parseFloat(val) + parseFloat(current);
				 }
				 else {
					 newVal = parseFloat(current) - parseFloat(val);
				 }
				 $("#quantity").val(newVal);
				 document.getElementById('productForm').action = "update";
				 alert(1);
			 }
			
			 
			 document.getElementById('productForm').submit();
		}
	   
	}
	
	
	function getLogs(id){
		$.ajax({
	          type: "get",
	          url: '/log/get/'+id,
	          data : '',
	          contentType:"application/json",
	          success: function(data){
	              $("#logs").html(data);
	          },
	          error: function(jqxhr) {
	            $("#register_area").text(jqxhr.responseText); 
	          }
	    });
	}			
			
</script>

<%@include file='footer.jsp'%>