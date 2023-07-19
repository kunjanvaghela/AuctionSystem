<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Send Email</title>
	</head>
	<body>
		 <div align="center">
		 <h1>Check</h1>
		 <h2>SignUp/Login Form</h2>
		 <form action="<%= request.getContextPath() %>/EmailSendingServlet" method="post" name='registration'>
		 	<input type="submit" value="Send Email" />
		 </form>
		 </div>
	</body>
	
	<script src="loginFormValidation.js"></script>
</html>