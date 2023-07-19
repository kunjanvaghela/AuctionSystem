<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.users.database.pkg.ItemListingDAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"> -->
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
		<title>FAQs</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Common/stylesheet.css">

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
		<script defer src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
		<script defer src="Common/script.js"></script>
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
		
		<div align="center" class="container">
			<p></p>
			<br><br>
			<div class="mb-4"></div>
			<c:choose>
			    <c:when test="${not empty username}">
			    	<form action="<%=request.getContextPath()%>/contactCustomerRepresentative" method="post">
				    	<div class="mb-4">
						  <h4><label for="contactUs" class="form-label">Do you have a question? Reach out to our customer representative.</label></h4>
						  <textarea class="form-control" id="contactUs" rows="3" name="question"></textarea>
						</div>
						<br>
						<input type="submit" value="Ask a Question!" />
					</form>
				</c:when>    
			    <c:otherwise>
			        <h4><a href="loginPage.jsp">Log In</a> to contact our customer representative!</h4>
			    </c:otherwise>
			</c:choose>
			<div class="mb-4"></div>
			<br><br>
			<p></p>
			<br><br>
		</div>
		
		<div class="container">
			<!--Section: FAQ-->
			<section>
			  <h2 class="text-center mb-4 pb-2 text-primary fw-bold">FAQ</h2>
			  <p class="text-center mb-5">
			    Find the answers for the most frequently asked questions below
			  </p>
			
			  <div class="row">
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="far fa-paper-plane text-primary pe-2"></i> How do I register?</h6>
			      <p>
			      	To register for an account on the auction website, click on the "Register" button on the homepage and follow the prompts to create an account.
			        Alternatively, you can <a href="registration.jsp"><strong>Register</strong></a> from here.
			      </p>
			    </div>
			
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-pen-alt text-primary pe-2"></i> How do I place a bid on an item I am interested in?</h6>
			      <p>To place a bid on an item, navigate to the Sell page and enter your bid amount in the designated field for the items available for auction. If your bid is the highest at the end of the auction, you will win the item.</p>
			    </div>
			
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-user text-primary pe-2"></i>Can I retract a bid I have placed on an item?</h6>
			      <p>No, once a bid is placed, it cannot be retracted. Please make sure to carefully consider your bids before placing them.</p>
			    </div>
			
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-rocket text-primary pe-2"></i>How can I see the current bids on an item?</h6>
			      <p>To see the current bids on an item, navigate to the Dashboard page and view the bidding history section. This will show you all of the current and previous bids on the item.</p>
			    </div>
			
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-home text-primary pe-2"></i>What happens if I win an auction but then change my mind and no longer want the item?</h6>
			      <p>If you win an auction and then decide you no longer want the item, you will still be responsible for paying for it. Failure to pay for an item you have won can result in account suspension or other penalties.</p>
			    </div>
			
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-book-open text-primary pe-2"></i>Can I set a maximum bid on an item?</h6>
			      <p><strong>Yes</strong>, you can set a maximum bid on an item. This allows the system to automatically place bids on your behalf up to your maximum bid amount.</p>
			    </div>
			    
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-book-open text-primary pe-2"></i>How do I know if I have been outbid on an item?</h6>
			      <p>You will receive an email notification if you have been outbid on an item. You can also check the bidding history section on the item's page to see if you are still the highest bidder.</p>
			    </div>
			    
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-book-open text-primary pe-2"></i>How long do I have to pay for an item I have won?</h6>
			      <p>Payment deadlines vary by auction, but typically range from 24 to 72 hours after the auction ends.</p>
			    </div>
			    
			    <div class="col-md-6 col-lg-4 mb-4">
			      <h6 class="mb-3 text-primary"><i class="fas fa-book-open text-primary pe-2"></i>What happens if I am the highest bidder but the reserve price has not been met?</h6>
			      <p>If the reserve price has not been met, the item will not be sold and you will not be charged for your bid. The seller may choose to relist the item with a lower reserve price or accept offers from bidders below the reserve price.</p>
			    </div>
			  </div>
			</section>
			<!--Section: FAQ-->
		
		</div>
		<br><br>
		<p></p>
		
		<jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
</html>