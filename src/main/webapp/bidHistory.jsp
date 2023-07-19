<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.users.database.pkg.ItemListingDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"> -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Common/stylesheet.css">

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
		<script defer src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
		<script defer src="Common/script.js"></script>
		
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
<title>Bidding History</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="Common/stylesheet.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
	integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
	integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
	crossorigin="anonymous"></script>
<script defer src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script defer
	src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
<script defer src="Common/script.js"></script>
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

	<h1 class="display-1">Bidding Transaction History:</h1>
	<nav class="navbar sticky-top navbar-light bg-light">
		<div class="container-fluid">

			<!-- Left links -->
			<form id="bidHistoryReportFilters"
				action="<%=request.getContextPath()%>/biddingHistory" method="post">
				<ul class="nav"><h4>Filters:</h4>
					<li class="nav-item">
						<div class="form-check form-switch">
							<!-- <input class="form-check-input" type="checkbox"
								name="itemNameCheck" id="itemNameCheck" value="true">  --><input
								type="text" class="form-control" placeholder="Item Name"
								id="itemName" name="itemName">
						</div> 
					</li>
					<li class="nav-item">
						<div class="form-check form-switch">
							<!-- <input class="form-check-input" type="checkbox"
								name="sellerNameCheck" id="sellerNameCheck" value="true"> -->
							<input type="text" class="form-control" placeholder="Seller Name"
								id="sellerName" name="sellerName">
						</div>
					</li>

					<li class="nav-item">
						<div class="form-check form-switch">
							<!-- input class="form-check-input" type="checkbox"
								name="buyerNameCheck" id="buyerNameCheck" value="true"> -->
							<input type="text" class="form-control" placeholder="Bidder Name"
								id="buyerName" name="buyerName">
						</div>
					</li>
				<li class="nav-item">
						<div class="form-check form-switch">
							<!-- <input class="form-check-input" type="checkbox"
								name="categoryCheck" id="categoryCheck" value="true">  --><select
								class="form-select" id="category" name="category">
								<option selected value=''>All Categories</option>
								<option value="Laptop">Laptop</option>
								<option value="Smartphone">Smartphone</option>
								<option value="Television">Television</option>
							</select>

						</div>
					</li>
					<li class="nav-item">
						<div class="form-check form-switch">
							<!-- <input class="form-check-input" type="checkbox"
								name="categoryCheck" id="categoryCheck" value="true">  --><select
								class="form-select" id="pastMonths" name="pastMonths">
								<option selected value=''>All Time</option>
								<option value="1">Last Month</option>
								<option value="3">Last 3 Months</option>
								<option value="6">Last 6 Months</option>
							</select>

						</div>
					</li>
					<li class="nav-item">
						<div class="form-check form-switch">
							<!-- <input class="form-check-input" type="checkbox"
								name="categoryCheck" id="categoryCheck" value="true"> --> <select
								class="form-select" id="bidStatus" name="bidStatus">
								<option selected value=''>All Bids</option>
								<option value="W">Winning Bids</option>
								<option value="A">Active Bids</option>
								<option value="X">Inactive Bids</option>
							</select>

						</div>
					</li>
	

				</ul>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
<input style="align:center" class="btn btn-outline-success" name="generateBidReport"
					id="generateBidReport" type="submit" value="Generate Bids Report">
			</form>
		</div>

	</nav>

	<div class="card">
		<div class="card-body">

			<div class="container-fluid" style="width: 100%">
				<table class="table table-striped table-hover" id="dashboardBidList">

					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>Bid Id (Reference)</th>
							<th>Item Name</th>
							<th>Item Description</th>
							<th>Item Image</th>
							<th>Seller Name</th>
							<th>Bidder Name (PII)</th>
							<th>Your Bid (USD)</th>
							<th>Secret Upper Limit (USD)</th>
							<th>Current Highest Bid (USD)</th>
							<th>Time Left</th>
							<th>Bid Status</th>
							<th>Auction Closing Date</th>
							<th>Auction Status</th>

						</tr>
					</thead>
					<tbody>
						<!-- Iterate over your bid list and populate the table rows -->


						<c:if test="${not empty bidList}">
							<c:forEach var="bidList" items="${bidList}" varStatus="loop">
								<tr>
									<td>${loop.index+1}</td>
									<td>${bidList.getBidId()}</td>
									<td>${bidList.getItemName()}</td>
									<td>${bidList.getItemDescription()}</td>
									<td><img
										src="data:image/jpeg;base64,${bidList.getAddInfo1()}"
										width="150" height="150" /></td>
									<td>${bidList.getSellerName()}</td>
									<td>${bidList.getAdd_info3()}</td>
									<td>${bidList.getCurrentBid()}$</td>
									<td>${bidList.getHighestBid()}$</td>
									<td>${bidList.getCurrentBestBidAmount()}$</td>

									<td><span id="countdownBid${bidList.getBidId()}"
										class="display-6"></span></td>
										
									<td>${bidList.getBidStatus()}</td>
									
									<td>${bidList.getClosingDateTime()}</td>
									<td><span id="statusBid${bidList.getBidId()}"></span></td>
									<!--CLOSING DATE FORMAT: "2023-03-29 16:45:00.0"-->
									<%-- <td>${bidList.getCurrentStatus()}</td> --%>

								</tr>
								<script>
					// Set the countdown date for this item
					var countdownDateBid${bidList.getBidId()} = new Date("${bidList.getClosingDateTime()}").getTime();

					// Update the countdown every second
					var countdownBid${bidList.getBidId()} = setInterval(function() {
						// Get the current date and time
						var now = new Date().getTime();

						// Calculate the time remaining
						var distance = countdownDateBid${bidList.getBidId()} - now;

						// Calculate days, hours, minutes and seconds remaining
						var days = Math.floor(distance / (1000 * 60 * 60 * 24));
						var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
						var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
						var seconds = Math.floor((distance % (1000 * 60)) / 1000);

						// Display the countdown
						document.getElementById("countdownBid${bidList.getBidId()}").innerHTML = days + "d " + hours + "h "
							+ minutes + "m " + seconds + "s ";

						// If the countdown is over, clear the interval and update the status
						if (distance < 0) {
							clearInterval(countdownBid${bidList.getBidId()});
							document.getElementById("countdownBid${bidList.getBidId()}").innerHTML = "AUCTION CLOSED";
							document.getElementById("statusBid${bidList.getBidId()}").innerHTML = "CLOSED";
						}
						else{
							document.getElementById("statusBid${bidList.getBidId()}").innerHTML = "ONGOING";
						}
					}, 1000);
				</script>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<jsp:include page="Common/footer.jsp"></jsp:include>
</body>
<script src="formValidation.js"></script>
<script type="text/javascript">

	
	$(document).ready(function() {
		$('#dashboardBidList').DataTable();

	});
	

</script>
</html>