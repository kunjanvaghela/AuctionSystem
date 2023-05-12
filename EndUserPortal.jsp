<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.usersManagement.web.RequestsRead"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator"%>
<%@page import="com.usersbean.pkg.RequestsForCR" %>

 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>End User Portal</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	</head>

	<body>
	<%-- <jsp:include page="Common/header.jsp"></jsp:include> --%>
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
		<br><br>
		<div align="center">
			<form method="post" name='endUserOptions'>
				<h1>End User Portal</h1>
				<h2>Welcome to Auction.com</h2>
				<h4>Do you want to sell today?</h4><!-- 
				<a href="SellerPosting.jsp">Post a Sell!</a> -->
				<a href="<%=request.getContextPath()%>/sellerPostingPageCall">Post a Sell!</a>
				<%-- <button type="submit" formaction="<%=request.getContextPath()%>/SellerPosting">Post a Sell!</button> --%>
				<h4>Do you want to bid today?</h4>
				<a href="<%=request.getContextPath()%>/listingSellItems">I want to bid!</a>
				
			</form>
			<%-- <form action="<%=request.getContextPath()%>/listingSellItems" method="post" name='placeBid'>
				<button type="submit">I want to bid!</button>
			</form> --%>
		</div>
		
		<!--Remaining section-->
		<jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
</html>