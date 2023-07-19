<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" session="true" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator"%>
<%@page import="com.itemManagement.web.listItemServlet"%>
<%@page import="com.itembean.pkg.catalogMaster" %>
<%@page import="com.users.database.pkg.ItemListingDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Items Available!</title>
		<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">
		<script type="text/javascript" src="Scripts/bootstrap.min.js"></script>
		<script type="text/javascript" src="Scripts/jquery-2.1.1.min.js"></script> -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Common/stylesheet.css">

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
		<script defer src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
		<script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
		<script defer src="Common/script.js"></script>
		
		<style>
			html, body { height:100%; }

			.flexbox {
				display: flex;
				height: 100%;
				justify-content: center;
				align-items: center;
			}
			
			.fav-btn {
				display:flex;
				height: 100%;
				justify-content: center;
				align-items: center;
			}
			
			
			@keyframes favme-anime {
			  0%   { 
					opacity: 1;
					font-size: ms(0);
					-webkit-text-stroke-color: transparent;
				}
				25%	 { 
					opacity:.6;
					color: #FFF;
					font-size: ms(-2);
					-webkit-text-stroke-width: 1px;
			   	-webkit-text-stroke-color: #DC3232;
				}
				75%	 { 
					opacity:.6;
					color: #FFF;
					font-size: ms(3);
					-webkit-text-stroke-width: 1px;
			   	-webkit-text-stroke-color: #DC3232;
				}
			  100% { 
					opacity: 1;
					font-size: ms(2);
					-webkit-text-stroke-color: transparent;
				}
			}
			
			@keyframes favme-hover {
				from {
					font-size: ms(3);
				}
				80% {
					font-size: ms(2);
				}
			}
			
			.favme {
				display:block;
				font-size: ms(2);
				width: auto;
				height: auto;
				cursor: pointer;
				box-shadow: none;
				transition: all .2s ease;
				color: #CBCDCE;
				margin: 0;
				
				&.active {
					color: #DC3232;
				}
				&:hover {
					animation: favme-hover .3s infinite alternate;
				}
				&.is_animating {
					animation: favme-anime .3s;
				}
			}
		</style>
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
			<h1 class="text-primary text-center mx-auto">Add Items to Watchlist:</h1>
			<section class="">
	
				<div class="container-fluid px-0">
					<div class="row g-0">
					
					    <!-- First column -->
						<div class="col-md-2 vh-100">
							<form id="itemFilter1" name="itemFilter" method="get" action="<%=request.getContextPath()%>/itemsPage">
								<div align="center">
																		
									<br><br>
									<h4>Item Filter</h4>
									<br>
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
						   	<form id="clearFilter" name="clearFilter" method="get" action="<%=request.getContextPath()%>/itemsPage">
						   		<button type="submit">Clear Filter</button>
						   	</form>
						   	<br><br><br><br><br><br>
						<p> </p>
						</div>
						<!-- First column -->
						
						<!-- Second column -->
						<div class="col-md-10 vh-100">
							
							<!-- <br> <br>
							<h2 class="text-center"></h2> -->
							
							<%-- <p>iitemlists</p>:: ${itemLists} --%>
							<table class="table table-striped table-hover" id ="itemFilterTable">
				     		<caption>Add Item for Auction</caption>
				     		<thead>
								<tr>
									<th>Add Favorite</th>
									<!-- <th>ItemId ID</th> -->
									<th>Item Name</th>
									<th>Item Image</th>
									<th>Description</th>
									<th>Category</th>
									<th>CPU</th>
									<th>GPU</th>
									<th>RAM</th>
									<th>Storage</th>
									<th>Operating System</th>
									<th>Screen Size</th>
									<th>Screen Type</th>
									<th>Screen Resolution</th>
									<th>Front Camera</th>
									<th>Rear Camera</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<%-- <c:if test="${not empty itemLists}"> --%>
								<c:forEach var="obj_catalogMaster" items="${itemLists}">
									<tr>
										<td>
											<button type="button" onclick="showpopup()" class="btn btn-primary align-self-center">Add to Watchlist</button>	
										</td>
										<td>${obj_catalogMaster.getItemName()}</td>
										<td><img src="data:image/jpeg;base64,${obj_catalogMaster.getAddInfo1()}" width="150" height="150" />
										<td>${obj_catalogMaster.getItemDescription()}</td>
										<td>${obj_catalogMaster.getCategory()}</td>
										<td>${obj_catalogMaster.getCpu()}</td>
										<td>${obj_catalogMaster.getGpu()}</td>
										<td>${obj_catalogMaster.getRam()}</td>
										<td>${obj_catalogMaster.getStorage()}</td>
										<td>${obj_catalogMaster.getOperatingSystem()}</td>
										<td>${obj_catalogMaster.getScreenSize()}</td>
										<td>${obj_catalogMaster.getScreenType()}</td>
										<td>${obj_catalogMaster.getScreenResolution()}</td>
										<td>${obj_catalogMaster.getFrontCamera()}</td>
										<td>${obj_catalogMaster.getRearCamera()}</td>
										<td><input type="hidden" id="itemID" name="selectedItemId" value="${obj_catalogMaster.getItemid()}"></td>
										<%-- <td>${obj_catalogMaster.getItemImage()}</td> --%>
									
										<%-- <td><img src="data:image/jpeg;base64,${fn:encodeBase64(${obj_catalogMaster.getItemImage()})}" /></td> --%>
									</tr>
								</c:forEach>
							<%-- </c:if> --%>
							</tbody>
						</table>
							<!-- <a href="EndUserPortal.jsp">Go back to Enduser Portal</a> -->
						
						<br><br><br><br><br><br>
						<p> </p>
						
						</div>
						<!-- Second column -->
				
					</div>	
				</div>
			</section>
		</div>
		<jsp:include page="Common/footer.jsp"></jsp:include>
		
	</body>
	<!-- <script src="formValidation.js"></script> -->
	<script type="text/javascript"> 
		$("#itemFilter").on("submit", function(event) {
			event.preventDefault();
		    var data = $("#itemFilter").serialize();
		    console.log("Filter data retreived : "+data)
		    /* alert("In FilterCall "); */
		    $.ajax({
				<%-- url: "<%=request.getContextPath()%>/filterCall", --%>
				url: "<%=request.getContextPath()%>/filterCall",
				data: data,
				type: 'POST',
				success: function(response, data){
					alert("Call successful, #filteredItems must be updated before success message");
					console.log("Response = ");
					console.log(response);
					console.log("API call done..");
					alert("Call successful, #filteredItems must be updated before success message");
					
					console.log('<%if (session.getAttribute("itemLists") != null) {
						session.getAttribute("itemLists").toString();
					} %>');
					console.log('data.itemLists : '+data.itemLists);
					console.log('data.someString : '+data.someString);
					console.log('data.someString : '+data.someString);
					
					<%-- if (response == "true") { 
 						$("#filteredItems").html("Item Added Successfully and will by available for users");
 						alert("Call successful, #filteredItems must be updated ");
 						$("#item").hide();
						window.setTimeout(goToNewPage, 1500);
						function goToNewPage() {
							window.location.href = "<%=request.getContextPath()%>/loginPage.jsp"							
						}
						
						    $(this).closest('form').find("input[type=text], textarea").val(""););
						    /* $(this).closest('form').find("input[type=text], textarea").val(""); */
 					} else {
 						$("#ifsuccess").html("Their is an exception, please try again!");
 					} --%>
				},
	            error: function (jqXHR, exception) {
	                console.log('Error occured!!');
	            }
			})
		})
		/* <input class="submit_button" type="submit" id="btnPay" name="btnPay" value="Payment"> */
	</script>
	
	<script>
		function validateForm() {
		    const initialPriceInput = document.getElementById('initialPrice');
		    const bidIncrementInput = document.getElementById('bidIncrement');
		    const minBidPriceInput = document.getElementById('minBidPrice');
		    const closingtimestampInput = document.getElementById('closingtimestamp');
		    
		    
		    var radioButtons = document.getElementsByName("selectedItemId");
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
		    
		    if (initialPriceInput.value === '') {
		        alert('Please fill in Initial Price');
		        return false;
		    }
		    else if (bidIncrementInput.value === '') {
		        alert('Please fill in Bid Increment');
		        return false;
		    }
		    else if (minBidPriceInput.value === '') {
		        alert('Please fill in Minimum Bid Price');
		        return false;
		    }
		    else if (closingtimestamp.value === '') {
		        alert('Please fill in Closing Time Stamp');
		        return false;
		    }
		    else {
		    	
		        return true;
		    }
		}
	</script>
	<script>
		$(document).ready(function() {
			  $('input[id="itemID"]').click(function() {
			    $('html, body').animate({scrollTop: $(document).height()}, 'slow');
			  });
			});
	</script>
	
	<script>
	

	// Favorite Button - Heart
	$('.favme').click(function() {
		$(this).toggleClass('active');
	});

	/* when a user clicks, toggle the 'is-animating' class */
	$(".favme").on('click touchstart', function(){
	  $(this).toggleClass('is_animating');
	});

	/*when the animation is over, remove the class*/
	$(".favme").on('animationend', function(){
	  $(this).toggleClass('is_animating');
	});
	</script>
	
	<script>
        
	function showpopup() {
		alert("Item added to your watchlist!");
	}
	</script>
</html>