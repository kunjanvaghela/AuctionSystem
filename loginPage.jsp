<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Auction System Sign Up</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
	</head>
	<body>
	
		<c:choose>
		<c:when test="${sessionScope.UserType eq 'Admin'}">
			<jsp:include page="Common/adminheader.jsp"></jsp:include>
		</c:when>
		<c:when test="${sessionScope.UserType eq 'CR'}">
			<jsp:include page="Common/crheader.jsp"></jsp:include>
		</c:when>
		<c:when test="${sessionScope.UserType eq 'EndUser'}">
			<jsp:include page="Common/header.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="Common/header.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
		<div align="center">
			<br> <br>
			<h1 class="text-primary text-center mx-auto">Welcome to the Auction Portal</h1>
			<br> <br><br>
			<h2 class="text-center">SignUp/Login Form</h2>
			 
			<form action="<%=request.getContextPath()%>/login" method="post" name="login" onSubmit="return formValidation();">
			<!-- <table style="with: 100%">
			   <tr>
			    <td>Email ID:</td>
			    <td><input type="text" name="emailId" /></td>
			   </tr>
			   <tr>
			    <td>Password:</td>
			    <td><input type="password" name="password" /></td>
			   </tr>
			    </table>
			  <input type="submit" value="Submit" /> -->
			  
				<div class="col-md-3 align-self-center mx-auto">
					<label for="exampleInputEmail1" class="form-label text-left">Email address</label>
					<input type="email" class="form-control text-left" id="emailId" name="emailId" onchange="validateEmail('a')" required>
				</div>
			    <br>
			    <div class="col-md-3 align-self-center mx-auto">
			        <label for="exampleInputPassword1" class="form-label text-left">Password</label>
			        <input type="password" class="form-control text-left" id="password" name="password" onchange="passid_validation('a',7,12)" required>
			    </div> 
			                  <p class="small"><a class="text-primary" href="forgotpassword.jsp">Forgot password?</a></p>
			    
			    
			    <div class="col-md-3 text-center mx-auto">
				    <button type="submit" class="btn btn-primary align-self-center">Log In</button>
				     <!-- <button type="" class="btn btn-primary align-self-center"></button> -->
				     <a class="btn btn-outline-success align-self-center" href="registration.jsp" role="button">Sign Up</a>
				    <!-- <button type="submit" class="btn btn-primary align-self-center">Forgot Password</button> -->
				    
				</div>
			</form>

			
			<br> <br>
<!-- 			
			<form action="CustomerRepresentative.jsp" method="post">
				<h3 class="text-center">Are you a Customer Representative? Register here!</h3>
				<input type="submit" value="Customer Representative Sign Up!" />
				<div class="col-md-3 text-center mx-auto">
				    <button type="submit" class="btn btn-primary align-self-center">Customer Representative Sign Up!</button>
				</div>
			</form> -->
		</div>
		<jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
	
	<script src="loginFormValidation.js"></script>
</html>