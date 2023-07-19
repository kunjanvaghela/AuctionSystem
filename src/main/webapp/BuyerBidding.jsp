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
		<title>Bidding Page</title>
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
			<%-- <select>
				<option value="0">Select range</option>
				<c:forEach items="${listRangeInitialPrice}" var="listRangeInitialPrice">
			        <option value="listRangeInitialPrice">${listRangeInitialPrice}</option>
			    </c:forEach>
			</select> --%>
		
		<div align="center">
			 <br>
			<h1 class="text-primary text-center mx-auto">Bidding Page</h1>
			<br>
			<h2 class="text-center">Open Auctions:</h2>
			
			<div class="container-fluid px-0">
				<div class="row g-0">
						
				    <!-- First column -->
					<div class="col-md-2 vh-100">
						<form id="itemFilter1" name="itemFilter" method="get" action="<%=request.getContextPath()%>/listingSellItems">
							<div align="center">
								<br><br>
								<h4>Item Filter</h4>
								<br>
								<label for="itemName" class="filterOptionStyling">Item name:</label>
 								<input type="text" id="itemName" name="itemName" class="filterOptionStyling">
								<br><br>
								<select name="category" class="filterOptionStyling">
									<option value="">Select Category</option>
									<c:forEach items="${listUniqueCategory}" var="listUniqueCategory">
										<option>${listUniqueCategory}</option>
									</c:forEach>
								</select>
								<br><br>
								<%-- <select name="itemName" class="filterOptionStyling">
									<option value="">Select Product Name</option>
									<c:forEach items="${listUniqueRearCam}" var="listUniqueRearCam">
										<option>${listUniqueRearCam}</option>
									</c:forEach>
								</select> --%>
								<label for="sellerName" class="filterOptionStyling">Seller name:</label>
 								<input type="text" id="sellerName" name="sellerName" class="filterOptionStyling">
								<br><br>
								<select name="rearCam" class="filterOptionStyling">
									<option value="">Select Rear Camera</option>
									<c:forEach items="${listUniqueRearCam}" var="listUniqueRearCam">
										<option>${listUniqueRearCam}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="cpu" class="filterOptionStyling">
									<option value="">Select CPU</option>
									<c:forEach items="${listUniqueCPU}" var="listUniqueCPU">
										<option>${listUniqueCPU}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="gpu" class="filterOptionStyling">
									<option value="">Select GPU</option>
									<c:forEach items="${listUniqueGPU}" var="listUniqueGPU">
										<option>${listUniqueGPU}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="ram" class="filterOptionStyling">
									<option value="">Select RAM</option>
									<c:forEach items="${listUniqueRAM}" var="listUniqueRAM">
										<option>${listUniqueRAM}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="storage" class="filterOptionStyling">
									<option value="">Select Storage</option>
									<c:forEach items="${listUniqueStorage}" var="listUniqueStorage">
										<option>${listUniqueStorage}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="os" class="filterOptionStyling">
									<option value="">Select Operating System</option>
									<c:forEach items="${listUniqueOS}" var="listUniqueOS">
										<option>${listUniqueOS}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="screenSize" class="filterOptionStyling">
									<option value="">Select Screen Size</option>
									<c:forEach items="${listUniqueScreenSize}" var="listUniqueScreenSize">
										<option>${listUniqueScreenSize}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="screenType" class="filterOptionStyling">
									<option value="">Select Screen Type</option>
									<c:forEach items="${listUniqueScreenType}" var="listUniqueScreenType">
										<option>${listUniqueScreenType}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="screenRes" class="filterOptionStyling">
									<option value="">Select Screen Resolution</option>
									<c:forEach items="${listUniqueScreenRes}" var="listUniqueScreenRes">
										<option>${listUniqueScreenRes}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="frontCam" class="filterOptionStyling">
									<option value="">Select Front Camera</option>
									<c:forEach items="${listUniqueFrontCam}" var="listUniqueFrontCam">
										<option>${listUniqueFrontCam}</option>
									</c:forEach>
								</select>
								<br><br>
								<select name="rearCam" class="filterOptionStyling">
									<option value="">Select Rear Camera</option>
									<c:forEach items="${listUniqueRearCam}" var="listUniqueRearCam">
										<option>${listUniqueRearCam}</option>
									</c:forEach>
								</select>
								<br>
								<br><br>
								
						 		<!-- <input type="submit" value="Search" /> -->
								<!-- <input type="submit" value="Search" name="Search" id="Search" /> -->
						  		<button type="submit">Apply Filter</button>
							</div>
					 	</form>
					 	<br>
					 	<form id="clearFilter" name="clearFilter" method="post" action="<%=request.getContextPath()%>/listingSellItems">
					   		<button type="submit">Clear Filter</button>
					   	</form>
					</div>
					<!-- First column -->
					<!-- Second column -->
					<div class="col-md-10 vh-100">
						
						<br> <br>
						<form action="<%=request.getContextPath()%>/placeBid" method="post" name='placeBid'>
							<table class="table" style="with: 80%">
								<tr>
									<td></td>
									<td><!-- <input type="text" name="item" /> -->
								  		<!-- <table border="1" cellpadding="2" cellspacing="2"> -->
								  		
								  		<table class="table table-striped table-hover" id ="itemFilterTable">
								  			<thead>
												<tr>
													<th>Select</th>
													<th>Product</th>
													<th>Image</th>
													<th>Description</th>
													<th>Seller Name</th>
													<th>Initial Price</th>
													<th>Minimum Bid Increment</th>
													<th>Winning Bid</th>
													<th>Bid Start Time</th>
													<th>Bid Closing Time</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="itemListing" items="${itemListing }">
													<tr>
														<td><input type="radio" id="itemListingSelection" name="itemListingSelectionClass" value="${itemListing.listingId}"></td>
														<td>${itemListing.itemName }</td>
														<td><img src="data:image/jpeg;base64,${itemListing.getAddInfo1()}" width="150" height="150" />
														<td>${itemListing.itemSummary }</td>
														<td>${itemListing.sellerName }</td>
														<td>${itemListing.initialPrice }</td>
														<td>${itemListing.bidIncrement }</td>
														<td>${itemListing.currentBestBidAmount }</td>
														<td>${itemListing.createdOn }</td>
														<td>${itemListing.closingDateTime }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>Bid Amount:</td>
									<td id="lowestBidPossible_td"><input id="lowestBidPossible" type="number" step="0.01" name="initialPrice" required/></td>
								</tr>
								<tr>
									<td><label for="enableBidInput">Auto-Bid:</label></td>
									<td><input type="checkbox" id="bidCheckbox"/></td>
								</tr>
								<tr>
									<td><label for="highestBidPossible">Highest Bid:</label></td>
									<td><input type="number" step="0.01" name="highestBidPossible" disabled/></td>
								</tr>
							</table>
							
							<c:choose>
								<c:when test="${not empty username}">
										<input type="submit" value="Submit" class="btn btn-primary" onclick="return validateForm()"/>
								</c:when>
								<c:otherwise>
										<input type="submit" value="Submit" class="btn btn-primary" onclick="return validateForm()" disabled/>
								</c:otherwise>
							</c:choose>
							
							
						</form>
		<br><br><br><br><br><br>
		<p> </p>
					</div>
					<!-- Second column -->
					</div>
			</div>
		</div>
		<jsp:include page="Common/footer.jsp"></jsp:include>
	</body>
	<script src="formValidation.js"></script>
	<script>
		const itemListingSelection = document.getElementsByName("itemListingSelectionClass");
		const lowestBidPossibleInput = document.getElementById("lowestBidPossible");
		
		itemListingSelection.forEach(function(radio) {
		    radio.addEventListener("click", function() {
		        const selectedListing = document.querySelector('input[name="itemListingSelectionClass"]:checked').parentNode.parentNode;
		        const initialPrice = parseFloat(selectedListing.cells[5].innerHTML);
		        lowestBidPossibleInput.min = initialPrice;
		        lowestBidPossibleInput.step = parseFloat(selectedListing.cells[6].innerHTML);;
		        lowestBidPossibleInput.value = initialPrice;
		    });
		});
	</script>
	<script>
		function validateForm() {
		    const lowestBidPossibleInput = document.getElementById('lowestBidPossible');
		    /* const highestBidPossibleInput = document.getElementsByName('highestBidPossible')[0]; */
		    
		    
		    /* if (lowestBidPossibleInput.value === '' || highestBidPossibleInput.value === '') */
		    if (lowestBidPossibleInput.value === '') {
		        alert('Please fill in all fields.');
		        return false;
		    } else {
		    	var radioButtons = document.getElementsByName("itemListingSelectionClass");
		        var selected = false;
		        for (var i = 0; i < radioButtons.length; i++) {
		            if (radioButtons[i].checked) {
		                selected = true;
		                break;
		            }
		        }
		        if (!selected) {
		            alert("Please select an item.");
		            return false;
		        }
		        return true;
		    }
		}
	</script>
	<script>
		$(document).ready(function() {
			  $('input[id="itemListingSelection"]').click(function() {
			    $('html, body').animate({scrollTop: $(document).height()}, 'slow');
			  });
			});
		
		$(document).ready(function() {
			  $('#bidCheckbox').change(function() {
			    var isChecked = $(this).is(':checked');
			    $('input[name=highestBidPossible]').prop('disabled', !isChecked);
			  });
			});
	</script>
</html>