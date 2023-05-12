<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Edit/Delete EndUser Information</title>
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

	</head>
	
	<body>
        <%-- 		<div id="header"></div>
		<%@ include file="header.jsp" %> 
		<br> <br> --%>
		<%-- <jsp:include page="Common/crheader.jsp"></jsp:include> --%>
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
			<h1 class="text-primary text-center mx-auto">Edit/Delete EndUser Information</h1>
			<br> <br><br>
			 <h2 class="text-center" id="t1">Fetch User Details</h2>
			<!-- KUNJAN <form action="<%-- <%= request.getContextPath() %> --%>/register" method="post" onSubmit="return formValidation();" name='registration'> -->
            <form id="submitUserId" name="submitUserId">
                <div class="col-md-3 align-self-center mx-auto">
                    <label for="exampleName" class="form-label text-left">UserId</label>
                    <input type="number" class="form-control text-left" id="userId" name="userId" required>
                </div>
                <br>
                <div class="col-md-3 text-center mx-auto">
                    <button type="submit" class="btn btn-primary align-self-center">Submit</button>
                </div>
            </form>
			<br> <br><br>
            <h4 class="text-center" id="ifsuccess2"> </h4>

			 <form id="reg" name="registration">
                <h2 class="text-center">Edit below user details</h2>

            	  <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleName" class="form-label text-left">Name</label>
				       <input type="text" class="form-control text-left" id="name" name="name" required>
				   </div>
				   <br>
				   <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleInputEmail1" class="form-label text-left">Email address</label>
				       <input type="email" class="form-control text-left" id="emailId" aria-describedby="emailHelp" name="emailId" required readonly>
				       <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
				   </div>
				   <br>
				   <!-- <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleInputPassword1" class="form-label text-left">Password</label>
				       <input type="password" class="form-control text-left" id="password" name="password" onchange="passid_validation('a',7,12)" required>
				   </div>
				   <br>
				   <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleInputPassword1" class="form-label text-left">Confirm Password</label>
				       <input type="password" class="form-control text-left" id="cpassword" name="cpassword" onchange="passid_validation('a',7,12)" required>
				   </div>
				   <br> -->
				   <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleInputPassword1" class="form-label text-left">Address</label>
				       <input type="text" class="form-control text-left" id="address" name="address" required>
				   </div>
				   <br>
				   <div class="col-md-3 align-self-center mx-auto">
				       <label for="exampleInputPassword1" class="form-label text-left">Phone Number</label>
				       <input type="text" class="form-control text-left" id="phoneNr" name="phoneNr" required>
				   </div>
				   <br> <br>
				   <div class="col-md-3 text-center mx-auto">
				    <button type="submit" class="btn btn-primary align-self-center">Submit</button>
                    <button type="button" onclick="deleteData()" class="btn btn-danger align-self-center">Delete</button>
				</div>
			</form>
		</div>
        <h4 class="text-center" id="ifsuccess"> </h4>
		<br><br><br><br>
		<p></p>
		<jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
	<!-- <script src="formValidation.js"></script> -->

<script type="text/javascript"> 
        window.onload = function() {
          document.getElementById('reg').style.display = 'none';
        };

        $("#reg").on('submit', function(event) {
  			event.preventDefault();
			var f = $("#reg").serialize();
            console.log(f);
            console.log();
			//console.log(f);
			// status = formValidation();
			//console.log(status);
			
            console.log("Ajax to be attempted");

            $("#reg").show()
            
            document.getElementById("name").value = document.getElementById("name").value;
            document.getElementById("emailId").value = document.getElementById("emailId").value;
            document.getElementById("address").value = document.getElementById("address").value;
            document.getElementById("phoneNr").value = document.getElementById("phoneNr").value;

            $("#ifsuccess2").html("User Edited successfully.");
            $("#reg").hide();
            document.getElementById("userId").value = "";
            // window.setTimeout( $("#ifsuccess2").hide(), 50000);
        })

		$("#submitUserId").on('submit', function(event) {
  			event.preventDefault();
			var f = $("#submitUserId").serialize();
			//console.log(f);
			// status = formValidation();
			//console.log(status);
			
            console.log("Ajax to be attempted");

            $("#reg").show()
            // $("#name").html("yu");
            
            document.getElementById("name").value = "Mayank";
            document.getElementById("emailId").value = "mayank@gmail.com";
            document.getElementById("address").value = "100 Adam Street";
            document.getElementById("phoneNr").value = "7329884610";
            $("#ifsuccess2").html("");

            // $.ajax({
            //     url: "<%= request.getContextPath() %>/editUser",
            //     data: f,
            //     type: 'POST',
            //     //contentType: "application/json; charset=utf-8",
            //     success: function(response) {
            //         console.log(response);
            //         if (response == "true") { 
            //             $("#ifsuccess").html("You have successfully edited details of user, you will be redirected to home page");
            //             $("#submitUserId").hide();
            //             $("#reg").show();
            //             window.setTimeout(goToNewPage, 1500);
            //             function goToNewPage() {
            //                 window.location.href = "<%= request.getContextPath() %>/loginPage.jsp"							
            //             }
            //         } else {
            //             $("#ifsuccess").html("Their is an exception, please try again!");
            //         }
            //     },
            //     error: function (jqXHR, exception) {
            //         console.log('Error occured!!');
            //     }
            // })				
		})


        function deleteData(){
            $("#submitUserId").hide();
            $("#reg").hide();
            $("#ifsuccess2").html("");
            $("#ifsuccess").html("User Deleted successfully.");
        }

</script>
</html>