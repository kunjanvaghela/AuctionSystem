<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	type="text/javascript"></script>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1);
%>

<title>Dashboard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.0/css/bootstrap.min.css">
<style>
.carousel-control-prev, .carousel-control-next {
	font-size: 0.8rem;
}
</style>

</head>
<body>
	<style>/* Style buttons */
.btn {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 10px 12px;
	font-size: 18px;
	cursor: pointer;
}

/* Darker background on mouse-over */
.btn:hover {
	background-color: RoyalBlue;
}
</style>

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

	<br>
	<br>

	<h1 class="display-1" id="title_summary">Dashboard : Your Bids</h1>
	<h1 class="display-6">
		<c:choose>
			<c:when test="${empty userId}">
					        User Not Logged in. Please <a href="loginPage.jsp">Sign
					In!</a> and try again.
					    </c:when>
		</c:choose>
	</h1>

	<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">

		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"></li>
			<li data-bs-target="#myCarousel" data-bs-slide-to="1"></li>
			<li data-bs-target="#myCarousel" data-bs-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">

			<!-- First List: Items You Have Bid For -->
			<div class="carousel-item active">
				<div class="card">
					<!--  <h5 class="card-title">Your Bids:</h5>  -->
					<div class="card-body">

						<div class="container" style="width: 92%">
							<table class="table table-striped table-hover"
								id="dashboardBidList">

								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Bid Id (Reference)</th>
										<th>Item Name</th>
										<th>Item Description</th>
										<th>Item Image</th>
										<th>Seller Name</th>
										<th>Your Bid (USD)</th>
										<th>Secret Upper Limit (USD)</th>
										<th>Current Highest Bid (USD)</th>
										<th></th>
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
												<%-- <td>${bidList.getCurrentBid()}$</td> --%>

												<td>${bidList.getCurrentBid()}$</td>
												<td>${bidList.getHighestBid()}$</td>
												<td>${bidList.getCurrentBestBidAmount()}$</td>
												<td>
													<form
														action="<%=request.getContextPath()%>/removeAuctionBid"
														method="get">
														<input type="hidden" name="bidId"
															value="${bidList.getBidId()}" /> <input type="hidden"
															name="requestType" value="Remove Bid" />

														<button class="btn" type="submit">
															<svg xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" class="bi bi-trash"
																viewBox="0 0 16 16">
														  <path
																	d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z" />
														  <path
																	d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z" />
														</svg>
														</button>
													</form>
												</td>

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
			</div>

			<!-- Second List: Items You Have Put Out for Auction -->
			<div class="carousel-item">
				<div class="card">
					<div class="card-body">
						<div class="container" style="width: 70%">
							<!-- <h5 class="card-title">Submitted Auctions:</h5> -->
							<table class="table table-striped table-hover"
								id="dashboardsellList">

								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Listing Id (Reference)</th>
										<th>Item Name</th>
										<th>Item Description</th>
										<th>Item Image</th>
										<th>Initial Listing Price (USD)</th>
										<th>Secret Reserve Price (USD)</th>
										<th>Current Highest Bid (USD)</th>
										<th></th>
										<th>Time Left</th>
										<th>Listing Status</th>
										<th>Auction Closing Date</th>
										<th>Auction Status</th>

									</tr>
								</thead>
								<tbody>
									<!-- Iterate over your bid list and populate the table rows -->
									<c:if test="${not empty sellList}">
										<c:forEach var="sellList" items="${sellList}" varStatus="loop">
											<tr>
												<td>${loop.index+1}</td>

												<td>${sellList.getListingId()}</td>
												<td>${sellList.getItemName()}</td>
												<td>${sellList.getItemDescription()}</td>
												<td><img
													src="data:image/jpeg;base64,${sellList.getAddInfo1()}"
													width="150" height="150" /></td>
												<td>${sellList.getInitialPrice()}$</td>
												<td>${sellList.getMinBidPrice()}$</td>
												<td>${sellList.getCurrentBestBidAmount()}$</td>
												<td>
													<form
														action="<%=request.getContextPath()%>/removeAuctionBid"
														method="get">
														<input type="hidden" name="auctionId"
															value="${sellList.getListingId()}" /> <input
															type="hidden" name="requestType" value="Remove Auction" />

														<button class="btn" type="submit">
															<svg xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor" class="bi bi-trash"
																viewBox="0 0 16 16">
														  <path
																	d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z" />
														  <path
																	d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z" />
														</svg>
														</button>
													</form>
												</td>

												<td><span id="countdownSell${sellList.getListingId()}"
													class="display-6"></span></td>
												<td>${sellList.getCurrentStatus()}</td>

												<td>${sellList.getClosingDateTime()}</td>
												<td><span id="statusSell${sellList.getListingId()}"></span></td>
												<!--CLOSING DATE FORMAT: "2023-03-29 16:45:00.0"-->
												<%-- <td>${sellList.getCurrentStatus()}</td> --%>

											</tr>
											<script>
					// Set the countdown date for this item
					var countdownDateSell${sellList.getListingId()} = new Date("${sellList.getClosingDateTime()}").getTime();

					// Update the countdown every second
					var countdownSell${sellList.getListingId()} = setInterval(function() {
						// Get the current date and time
						var now = new Date().getTime();

						// Calculate the time remaining
						var distance = countdownDateSell${sellList.getListingId()} - now;

						// Calculate days, hours, minutes and seconds remaining
						var days = Math.floor(distance / (1000 * 60 * 60 * 24));
						var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
						var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
						var seconds = Math.floor((distance % (1000 * 60)) / 1000);

						// Display the countdown
						document.getElementById("countdownSell${sellList.getListingId()}").innerHTML = days + "d " + hours + "h "
							+ minutes + "m " + seconds + "s ";

						// If the countdown is over, clear the interval and update the status
						if (distance < 0) {
							clearInterval(countdownSell${sellList.getListingId()});
							document.getElementById("countdownSell${sellList.getListingId()}").innerHTML = "AUCTION CLOSED";
							document.getElementById("statusSell${sellList.getListingId()}").innerHTML = "CLOSED";
						}
						else{
							document.getElementById("statusSell${sellList.getListingId()}").innerHTML = "ONGOING";
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
			</div>

			<!-- Third List: Items You Are Interested In -->
			<div class="carousel-item">

				<div class="card">
					<div class="card-body">
						<!--  <h5 class="card-title">Items You Are Interested In:</h5>  -->
						<div class="container" style="width: 70%">
							<table class="table table-striped table-hover"
								id="dashboardInterestList">

								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Item Id (Reference)</th>
										<th>Item Name</th>
										<th>Item Description</th>
										<th>Item Image</th>
										<th>Seller Name</th>
										<th>Time Left</th>
										<th>Auction Start Date</th>
										<th>Auction Closing Date</th>
										<th>Auction Status</th>
									</tr>
								</thead>

								<tbody>
									<!-- Iterate over your bid list and populate the table rows -->
									<c:if test="${not empty interestList}">
										<c:forEach var="interestList" items="${interestList}"
											varStatus="loop">
											<tr>
												<td>${loop.index+1}</td>
												<td>${interestList.getItemId()}</td>
												<td>${interestList.getItemName()}</td>
												<td>${interestList.getItemDescription()}</td>
												<td><img
													src="data:image/jpeg;base64,${interestList.getAddInfo1()}"
													width="150" height="150" /></td>
												<c:choose>
													<c:when test="${interestList.getSellerName()!=null}">
													<td>${interestList.getSellerName()}</td>
													<td><span
														id="countdownInterest${interestList.getAddInfo4()}"
														class="display-6"></span></td>
													<td>${interestList.getCreatedOn()}</td>
													<td>${interestList.getClosingDateTime()}</td>
													<td><span
														id="statusInterest${interestList.getAddInfo4()}"></span></td>
													</c:when>
													<c:otherwise>
														<td>Not Available</td>
														<td>Not Available</td>
														<td>Not Available</td>
														<td>Not Available</td>
														<td>Not Available</td>
													</c:otherwise>
												</c:choose>


												<!--CLOSING DATE FORMAT: "2023-03-29 16:45:00.0"-->
												<%-- <td>${interestList.getCurrentStatus()}</td> --%>

											</tr>
											<script>
					// Set the countdown date for this item
					var countdownDateInterest${interestList.getAddInfo4()} = new Date("${interestList.getClosingDateTime()}").getTime();

					// Update the countdown every second
					var countdownInterest${interestList.getAddInfo4()} = setInterval(function() {
						// Get the current date and time
						var now = new Date().getTime();

						// Calculate the time remaining
						var distance = countdownDateInterest${interestList.getAddInfo4()} - now;

						// Calculate days, hours, minutes and seconds remaining
						var days = Math.floor(distance / (1000 * 60 * 60 * 24));
						var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
						var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
						var seconds = Math.floor((distance % (1000 * 60)) / 1000);

						// Display the countdown
						document.getElementById("countdownInterest${interestList.getAddInfo4()}").innerHTML = days + "d " + hours + "h "
							+ minutes + "m " + seconds + "s ";

						// If the countdown is over, clear the interval and update the status
						if (distance < 0) {
							clearInterval(countdownInterest${interestList.getAddInfo4()});
							document.getElementById("countdownInterest${interestList.getAddInfo4()}").innerHTML = "AUCTION CLOSED";
							document.getElementById("statusInterest${interestList.getAddInfo4()}").innerHTML = "CLOSED";
						}
						else{
							document.getElementById("statusInterest${interestList.getAddInfo4()}").innerHTML = "ONGOING";
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
			</div>

		</div>
		<!-- Left and right controls -->
		<a class="carousel-control-prev" href="#myCarousel" role="button"
			data-bs-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
		</a> <a class="carousel-control-next" href="#myCarousel" role="button"
			data-bs-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="visually-hidden">Next</span>
		</a>
	</div>

	<br>
	<br>
	<jsp:include page="Common/footer.jsp"></jsp:include>
	<%
	String message = (String) request.getAttribute("alertMsg");
	%>
	<script type="text/javascript">
	    var msg = "<%=message%>";
	    if (msg!="null"){
	    	alert(msg);
	    }
	</script>
</body>
<script type="text/javascript">
var myCarousel = new bootstrap.Carousel(document.getElementById('dashboardBidList'), {
    interval: 5000, // set interval between slides to 5 seconds
    //pause: 'hover', // pause the carousel when hovering over it
    keyboard: true // enable keyboard navigation between slides
  });
	
	$(document).ready(function() {
/* 		if("${session.userId}"=="")
			{document.getElementById("mainDIV").style.display = "none";} */
		$('#dashboardBidList').DataTable();
		$('#dashboardsellList').DataTable();
		$('#dashboardInterestList').DataTable();
	});

</script>
<script>
    // Get a reference to the 'title_summary' element
    var titleSummary = document.getElementById('title_summary');

    // Add an event listener to the carousel that listens for a slide change event
    var carousel = document.getElementById('myCarousel');
    carousel.addEventListener('slide.bs.carousel', function (event) {
        // Get the index of the new active slide
        var slideIndex = event.to;

        // Depending on the slide index, update the content of the 'title_summary' element
        switch (slideIndex) {
            case 0:
                titleSummary.innerHTML = 'Dashboard : Your Bids';
                break;
            case 1:
                titleSummary.innerHTML = 'Dashboard : Your Auctions';
                break;
            case 2:
                titleSummary.innerHTML = 'Dashboard : Items You Are Interested In';
                break;
        }
    })
</script>
</html>