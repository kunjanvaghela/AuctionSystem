<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Bootstrap 5 Forgot Password</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<div class="container d-flex flex-column">
		<div
			class="row align-items-center justify-content-center
          min-vh-100">
			<div class="col-12 col-md-8 col-lg-4">
				<div class="card shadow-sm">
					<div class="card-body">
						<div class="mb-4">
							<h5>Forgot Password?</h5>
							<p class="mb-2">Enter your registered email ID and contact number to reset the
								password</p>
						</div>
						<form id="forgotpasswordform" method="post" action="<%=request.getContextPath()%>/forgotPassword">
							<div class="mb-3">
								<label for="emailId" class="form-label">Email:</label> <input
									type="email" class="form-control" id="emailId" name="emailId"
									name="email" placeholder="Enter Your Email" required>
							</div>
							<div class="mb-3">
								<label for="phoneNr" class="form-label">Contact Number :</label> <input
									type="text" class="form-control text-left" id="phoneNr"
									name="phoneNr" onchange="phoneNumberCheck()" required>
							</div>
							<div class="mb-3 d-grid">
								<button type="submit" class="btn btn-primary">Reset
									Password</button>
							</div>
							<span>Don't have an account? <a href="registration.jsp">Sign Up</a></span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

<% String alertMsg = (String)request.getAttribute("alertMsg");%>
<% String newPassword = (String)request.getAttribute("newPassword");%>
<script type="text/javascript">

    var alertMesg = "<%=alertMsg%>";
	if (alertMesg != "null" && alertMesg =="InvalidEmail") {
		if(window.confirm("No such user found,Please proceed to Registration Page?")){
            window.location.replace("registration.jsp");
         }
	}
	
	var newPassword  = "<%=newPassword%>";
	if (newPassword  != "null") {
		alert("New password is:"+newPassword )
	}
	
</script>

</body>
<%-- 
<% String message = (String)request.getAttribute("alertMsg");%>
function onload()
{	    var alertMsg=null;
		if (alertMsg != "null" && alertMsg =="InvalidEmail") {
			if(window.confirm("No such user found, proceed to Registration Page?")){
	            window.open("registration.jsp");
	         }
		}

	}
 --%>
 <script> function phoneNumberCheck() {
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