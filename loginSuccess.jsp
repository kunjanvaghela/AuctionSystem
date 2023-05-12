<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">

<script type = "text/javascript" >
    function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>
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
<h2 align="center">Sucessully Logged In!</h2>
 <div align="center">
  <form action="<%=request.getContextPath()%>/logout">
   <input type="submit" value="Logout" />
  </form>
  <a href='addItem.jsp' >Click here if you are a admin </a>
 </div>
 <br><br><br><br>
 <p></p>
 <jsp:include page="Common/footer.jsp"></jsp:include>
</body>
</html>