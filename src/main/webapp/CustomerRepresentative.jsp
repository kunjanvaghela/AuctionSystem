<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative Portal</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
</head>


<body>
<%-- <jsp:include page="Common/adminheader.jsp"></jsp:include> --%>
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
 <h1 class="text-primary text-center mx-auto">Customer Representative Registration Portal</h1>
 <br> <br>  <br>
  <h2 class="text-center">Customer Representative Registration Form</h2>
  <form action="<%=request.getContextPath()%>/crregister" method="post" onSubmit="return crFormValidation();" name='crregistration'>
   <!-- <table style="with: 80%">
    <tr>
     <td>Name:</td>
     <td><input type="text" name="name" /></td>
    </tr>
    <tr>
     <td>Email Id:</td>
     <td><input type="text" name="emailId" /></td>
    </tr>
    <tr>
     <td>Password:</td>
     <td><input type="password" name="password" /></td>
    </tr>
    <tr>
     <td>Confirm Password:</td>
     <td><input type="password" name="cpassword" /></td>
    </tr>
    <tr>
     <td>SSN:</td>
     <td><input type="text" name="ssn" /></td>
    </tr>
    <tr>
     <td>Date of Birth:</td>
     <td><input type="date" name="dateofbirth" min="1920-01-01"/></td>
    </tr>
   </table>
   <input type="submit" value="Submit" /> -->
   
   
   <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleName" class="form-label text-left">Name</label>
        <input type="text" class="form-control text-left" id="name" name="name" onchange="allLetter('a')" required>
    </div>
    <br>
    <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleInputEmail1" class="form-label text-left">Email address</label>
        <input type="email" class="form-control text-left" id="emailId" aria-describedby="emailHelp" name="emailId" onchange="validateEmail('a')" required>
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
    </div>
    <br>
    <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleInputPassword1" class="form-label text-left">Password</label>
        <input type="password" class="form-control text-left" id="password" name="password" onchange="passid_validation('a',7,12)" required>
    </div>
    <br>
    <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleInputPassword1" class="form-label text-left">Confirm Password</label>
        <input type="password" class="form-control text-left" id="cpassword" name="cpassword" onchange="passid_validation('a',7,12)" required>
    </div>
    <br>
    <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleInputPassword1" class="form-label text-left">SSN</label>
        <input type="text" class="form-control text-left" id="ssn" name="ssn" required>
    </div>
    <br>
    <div class="col-md-3 align-self-center mx-auto">
        <label for="exampleInputPassword1" class="form-label text-left">Date of Birth</label>
        <input type="date" class="form-control text-left" id="dateofbirth" name="dateofbirth" onchange="passid_validation('a',7,12)" required>
    </div>
    <br> <br>
    <div class="col-md-3 text-center mx-auto">
	    <button type="submit" class="btn btn-primary align-self-center">Submit</button>
	</div>
   
  </form>
 </div>
 <br><br><br>
 <p></p>
 <jsp:include page="Common/footer.jsp"></jsp:include>
</body>
<script src="formValidation.js"></script>
</html>