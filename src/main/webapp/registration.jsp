<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register to Auction System</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
		<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	type="text/javascript"></script>
		<%
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Expires","0");
		response.setDateHeader("Expires",-1);
		%>
	<!-- 	<script> 
			$(function(){
			  $("#header").load("header.jsp"); 
			  $("#footer").load("footer.html"); 
			});
		</script>  -->
	</head>
	
	<body>
	<%-- 		<div id="header"></div>
		<%@ include file="header.jsp" %> 
		<br> <br> --%>
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
		<h1 class="text-primary text-center mx-auto">Welcome to the
			Auction Portal</h1>
		<br> <br> <br>
		<h2 class="text-center">User Register Form</h2>
		<form id="reg" name="registration">
			<div class="col-md-3 align-self-center mx-auto">
				<label for="exampleName" class="form-label text-left">Name</label> <input
					type="text" class="form-control text-left" id="name" name="name"
					onchange="allLetter('a')" required>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<label for="pseudoName" class="form-label text-left">Pseudo
					Name:</label> <input type="text" class="form-control text-left"
					id="pseudoName" name="pseudoName" required>
				<div id="emailHelp" class="form-text">This Name will be used
					in Public Reports (Bidding History)</div>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<!-- <label for="emailId" class="form-label text-left">Email
					address</label> <input type="email" class="form-control text-left"
					id="emailId" aria-describedby="emailHelp" name="emailId"
					onchange="validateEmail('a')" required> -->
					
					<label for="emailId" class="form-label">Email:</label> <input
									type="email" class="form-control" id="emailId" name="emailId"
									name="email" placeholder="Enter Your Email" required>
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<label for="password" class="form-label text-left">Password</label>
				<input type="password" class="form-control text-left" id="password"
					name="password" onchange="passid_validation('a',7,12)" required>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<label for="cpassword" class="form-label text-left">Confirm
					Password</label> <input type="password" class="form-control text-left"
					id="cpassword" name="cpassword"
					onchange="passid_validation('a',7,12)" required>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<label for="address" class="form-label text-left">Address</label> <input
					type="text" class="form-control text-left" id="address"
					name="address" required>
			</div>
			<br>
			<div class="col-md-3 align-self-center mx-auto">
				<label for="phoneNr" class="form-label">Contact Number :</label> <input
					type="text" class="form-control text-left" id="phoneNr"
					name="phoneNr" onchange="phoneNumberCheck()" required>
			</div>
			<br> <br>
			<div class="col-md-3 text-center mx-auto">
				<button type="submit" class="btn btn-primary align-self-center">Submit</button>
			</div>
		</form>
	</div>
<br> <br><br> <br><br> <br><br> <p></p>
 <jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
	<script src="formValidation.js"></script>
<script type="text/javascript"> 
		$("#reg").on('submit', function(event) {
  			event.preventDefault();
			var f = $("#reg").serialize();
			//console.log(f);
			status = formValidation();
			//console.log(status);
			if (status == 'true') {
				console.log("Ajax to be attempted");
	 			$.ajax({
					url: "<%= request.getContextPath() %>/register",
					data: f,
					type: 'POST',
		            //contentType: "application/json; charset=utf-8",
					success: function(response) {
						console.log(response);
						if (response == "true") { 
	 						$("#ifsuccess").html("You are successfully registered, you will be redirected to login page");
							$("#reg").hide();
							window.setTimeout(goToNewPage, 1500);
							function goToNewPage() {
								window.location.href = "<%= request.getContextPath() %>/loginPage.jsp"							
							}
	 					} else {
	 						$("#ifsuccess").html("Their is an exception, please try again!");
	 					}
					},
		            error: function (jqXHR, exception) {
		                console.log('Error occured!!');
		            }
				})				
			}
		})
function phoneNumberCheck() {
	  // Regular expression to match valid phone numbers
	  const phoneRegex = /^[+]?[(]?[0-9]{3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4}$/im;
	  
	  value=document.getElementById('phoneNr').value;
	  // Get the input field
	  const phoneInput = document.getElementById('phoneNr');
	  
	  // Check if the input value matches the phone number regex
	  if (phoneRegex.test(value)) {
	    // If the input is valid, remove any existing error messages and set the input field as valid
	    phoneInput.setCustomValidity('');
	    phoneInput.classList.remove('is-invalid');
	    phoneInput.classList.add('is-valid');
	    return true;
	  } else {
	    // If the input is invalid, set a custom error message and set the input field as invalid
	    phoneInput.setCustomValidity('Please enter a valid phone number.');
	    phoneInput.classList.remove('is-valid');
	    phoneInput.classList.add('is-invalid');
	  }
	}
</script>
</html>