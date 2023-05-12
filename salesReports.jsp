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
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1);
%>

<title>Admin Page</title>
<!-- Add your stylesheet and scripts here -->
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
	<br>
	<br>
<!-- 	<div class="container-fluid" style="width:100%"> -->
	<div class="container" style="width:150%">
		<h1 class="display-1">Sales Dashboard</h1>
		<nav class="navbar sticky-top navbar-light bg-light">
			<div class="container-fluid">


				<!-- Left links -->
				<form id="adminReport"
					action="<%=request.getContextPath()%>/salesReport" method="post">
					<ul class="nav">
						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="totalEarnings" id="totalEarnings" value="true"> <label
									class="form-check-label" for="totalEarnings">Total
									Earnings	&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>

						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="earningsByItem" id="earningsByItem" value="true">
								<label class="form-check-label" for="flexSwitchCheckDefault">Earnings
									by Item	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>

						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="earningsByType" id="earningsByType" value="true">
								<label class="form-check-label" for="flexSwitchCheckDefault">Earnings
									by Category (Item Type)	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>
						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="earningsByUser" id="earningsByUser" value="true">
								<label class="form-check-label" for="flexSwitchCheckDefault">Earnings
									by End-User	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>
						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="bestSellingItems" id="bestSellingItems" value="true">
								<label class="form-check-label" for="flexSwitchCheckDefault">Best-Selling
									Items	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>
						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="bestBuyers" id="bestBuyers" value="true"> <label
									class="form-check-label" for="flexSwitchCheckDefault">Best
									Buyers	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</li>
						<li class="nav-item">
							<div class="form-check form-switch">
								<input class="form-check-input" type="checkbox"
									name="generateAll" id="generateAll" value="true"> <label
									class="form-check-label" for="flexSwitchCheckDefault">All Reports&nbsp;</label>
							</div>
						</li>
					</ul>

					<input class="btn btn-outline-success" name="generateReport"
						id="generateReport" type="submit" value="Generate Report">
				</form>
			</div>

		</nav>
		<hr>

		<c:if test="${not empty totalEarningsResult}">
	
			<h1 class="display-6">
				Total Earnings:
				</h6>
				<table class="table table-dark table-hover">
					<tr>
						<th>Total Earnings:</th>
						<th>${totalEarningsResult}$</th>
					</tr>

				</table>
				<hr>
		</c:if>

		<c:if test="${not empty earningsByItem}">
		<br>
						<h1 class="display-6">
Earnings by Item:</h1>
			<table class="table table-striped table-hover"
				id="earningsByItemTable">
				<thead>
					<tr>
						<th>Item Name</th>
						<th>Earnings (USD)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="earningsByItem" items="${earningsByItem}">
						<tr>
							<td>${earningsByItem.getItemName()}</td>
							<td>${earningsByItem.getAddInfo1()}$</td>
					</c:forEach>
				</tbody>
			</table>
<hr>
		</c:if>
		<c:if test="${not empty earningsByCategory}">
		<br>
						<h1 class="display-6">
Earnings by Category:</h1>
			<table class="table table-striped table-hover"
				id="earningsByCategoryTable">
				<thead>
					<tr>
						<th>Item Name</th>
						<th>Earnings (USD)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="earningsByCategory" items="${earningsByCategory}">
						<tr>
							<td>${earningsByCategory.getCategory()}</td>
							<td>${earningsByCategory.getAddInfo1()}$</td>
					</c:forEach>
				</tbody>
			</table>
<hr>
		</c:if>

		<c:if test="${not empty earningsByUser}">
		<br>
						<h1 class="display-6">
Earnings by User:</h1>
			<table class="table table-striped table-hover"
				id="earningsByUserTable">
				<thead>
					<tr>
						<th>UserID</th>
						<th>Name</th>
						<th>Earnings (USD)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="earningsByUser" items="${earningsByUser}">
						<tr>
							<td>${earningsByUser.getAddInfo3()}</td>
							<td>${earningsByUser.getAddInfo2()}</td>
							<td>${earningsByUser.getAddInfo1()}$</td>
					</c:forEach>
				</tbody>
			</table>
<hr>
		</c:if>
		
		<c:if test="${not empty bestSellingItems}">
		<br>
						<h1 class="display-6">
Best Selling Items:</h1>
			<table class="table table-striped table-hover"
				id="bestSellingItemsTable">
				<thead>
					<tr>
						<th>Item Name</th>
						<th>Frequency</th>
						<th>Earnings (USD)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bestSellingItems" items="${bestSellingItems}">
						<tr>
							<td>${bestSellingItems.getItemName()}</td>
							<td>${bestSellingItems.getAddInfo2()}</td>
							<td>${bestSellingItems.getAddInfo1()}$</td>
					</c:forEach>
				</tbody>
			</table>
<hr>
		</c:if>
		<%-- 
		<c:if test="${not empty param.bestBuyers}">
			<h2>Best Buyers</h2>
			<!-- Code to display best buyers here -->
		</c:if> --%>
		
				<c:if test="${not empty bestBuyers}">
		<br>
						<h1 class="display-6">
Best Buyers:</h1>
			<table class="table table-striped table-hover"
				id="bestBuyersTable">
				<thead>
					<tr>
						<th>UserID</th>
						<th>Name</th>
						<th>Total Spent(USD)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bestBuyers" items="${bestBuyers}">
						<tr>
							<td>${bestBuyers.getAddInfo3()}</td>
							<td>${bestBuyers.getAddInfo2()}</td>
							<td>${bestBuyers.getAddInfo1()}$</td>
					</c:forEach>
				</tbody>
			</table>
<hr>
		</c:if>
	</div>

	<jsp:include page="Common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

	
	$(document).ready(function() {
		$('#earningsByItemTable').DataTable();
		$('#earningsByCategoryTable').DataTable();
		$('#earningsByUserTable').DataTable();
		$('#bestSellingItemsTable').DataTable();
		$('#bestBuyersTable').DataTable();
		
	});
	$(document).ready(function() {
		  $('#generateAll').change(function() {
		    if($(this).is(':checked')) {
		      $('input[type="checkbox"]').prop('checked', true);
		    }
		    else {
		      $('input[type="checkbox"]').prop('checked', false);
		    }
		  });
		});

</script>

</html>
