<%@page import="com.usersManagement.web.RequestsRead"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.usersbean.pkg.RequestsForCR"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative Portal</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
<!-- <script>
		$(document).ready(function() {
			// Submit form on button click
			$('#submitBtn').click(function() {
				$('#itemRequestsForm').submit();
			});
			
			// Handle form submission response
			$('#itemRequestsForm').submit(function(event) {
				event.preventDefault();
				$.ajax({
					type: "POST",
					url: "CRRequestApproval",
					data: $(this).serialize(),
					success: function(response) {
						$('#formResponse').text(response);
					}
				});
			});
		});
	</script> -->
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
	<div align="center">
		<br> <br>
		<h1 class="text-primary text-center mx-auto">Customer
			Representative Portal</h1>
		<br> <br>
		<br>
		<h2 class="text-center">Requests from End Users</h2>


		<%
				RequestsRead obj_requestsread=new RequestsRead();
				List<RequestsForCR> list=obj_requestsread.get_values();
				Iterator<RequestsForCR> it_list=list.iterator();
			%>
		<div>
			<form id="itemRequestsForm"
				action="<%=request.getContextPath()%>/CRRequestApproval"
				method="get">
				<table class="table table-striped table-hover"
					id="itemRequestsFromEndUsers">
					<thead>
						<tr>
							<th>Request ID</th>
							<th>User ID</th>
							<th>Request Type</th>
							<th>Bid ID</th>
							<th>Auction ID</th>
							<th>Update Description</th>
							<th>Approve</th>
							<th>Deny</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="listRequestsEndUser"
							items="${listRequestsEndUser}">
							<tr>
								<td>${listRequestsEndUser.getRequestId()}</td>
								<td>${listRequestsEndUser.getUserId()}</td>
								<td>${listRequestsEndUser.getRequestType()}</td>


								<!-- //BID ID -->

								<c:if
									test="${listRequestsEndUser.getRequestType()=='Remove Bid'}">
									<td>${listRequestsEndUser.getBidId()}</td>

								</c:if>
								<c:if
									test="${listRequestsEndUser.getRequestType()=='Remove Auction'}">
									<td>Listing Removal Request</td>
								</c:if>
								<c:if
									test="${listRequestsEndUser.getRequestType()=='Password Change'}">
									<td>Password Change Request</td>
								</c:if>
								<!-- //BID ID -->
								<!-- //LIISTING ID -->

								<c:if
									test="${listRequestsEndUser.getRequestType()=='Remove Auction'}">
									<td>${listRequestsEndUser.getListingId()}</td>

								</c:if>
								<c:if test="${listRequestsEndUser.getRequestType()=='Remove Bid'}">
									<td>Bid Removal Request</td>
								</c:if>

								<c:if
									test="${listRequestsEndUser.getRequestType()=='Password Change'}">
									<td>Password Change Request</td>
								</c:if>


								<td>${listRequestsEndUser.getUpdateDescription()}</td>
								<td><input type="checkbox" name="approve[]"
									value="${listRequestsEndUser.getRequestId()}" /></td>
								<td><input type="checkbox" name="deny[]"
									value="${listRequestsEndUser.getRequestId()}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- <input type="hidden" name="action" value="" />
					<input type="submit" name="approve" value="Approve" onclick="document.forms[0].action.value='approve'" />
					<input type="submit" name="deny" value="Deny" onclick="document.forms[0].action.value='deny'" /> -->
				<input type="submit" class="btn btn-primary" value="Submit"
					id="submitBtn" />
			</form>

		</div>


		<div id="filteredItems">
			<table class="table table-striped table-hover" id="itemFilterTable">
				<caption>Reply to User Queries</caption>
				<thead>
					<tr>
						<th>User ID</th>
						<th>Question</th>
						<th>Answer</th>
						<!-- <th>Status</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listFAQ" items="${listFAQ}">
						<tr>
							<td>${listFAQ.getUserId()}</td>
							<td>${listFAQ.getQuestion()}</td>
							<td><c:choose>
									<c:when test="${not empty listFAQ.getAnswer()}">
									        ${listFAQ.getAnswer()}
									    </c:when>
									<c:otherwise>
										<form id="crReplyForm"
											action="<%=request.getContextPath()%>/cRepReply" method="get">
											<input type="hidden" name="faqId"
												value="${listFAQ.getFaqId()}" /> <input type="text"
												name="answer" placeholder="Answer user query" size="80"
												required>
											<!-- <input type="email" class="form-control" id="answer" name="answer" aria-describedby="Answer User Query" placeholder="Answer User Query"> -->
											<input type="submit" value="Reply">
										</form>
									</c:otherwise>
								</c:choose></td>
							<%-- <td>${listFAQ.getStatusFAQ()}</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<p></p>
	<jsp:include page="Common/footer.jsp"></jsp:include>
</body>
<script src="formValidation.js"></script>
<script>
		// To select either Approve or Deny for a given request
		const approveCheckboxes = document.getElementsByName('approve[]');
		approveCheckboxes.forEach(function(checkbox) {
		  checkbox.addEventListener('click', function() {
		    const row = this.parentNode.parentNode;
		    const denyCheckbox = row.querySelector('input[name="deny[]"]');
		    denyCheckbox.checked = false;
		  });
		});
	
		const denyCheckboxes = document.getElementsByName('deny[]');
		denyCheckboxes.forEach(function(checkbox) {
		  checkbox.addEventListener('click', function() {
		    const row = this.parentNode.parentNode;
		    const approveCheckbox = row.querySelector('input[name="approve[]"]');
		    approveCheckbox.checked = false;
		  });
		});
	</script>
</html>