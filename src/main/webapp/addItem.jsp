<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register to Auction System</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/dataTables.bootstrap5.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	type="text/javascript"></script>

<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1);
%>

<script type="text/javascript">

function onLoad()
{
	document.getElementById("screenSizes").style.display = "none";
	document.getElementById("operatingSystems").style.display = "none";
	document.getElementById("screenTypes").style.display = "none";
	document.getElementById("screenResolutions").style.display = "none";
	document.getElementById("frontCamera").style.display="none";
	document.getElementById("frontCamera2").style.display="none";
	document.getElementById("webCam").style.display="none";
	document.getElementById("rearCameraDiv").style.display="none";
	
	}
//Function to Check and populate appropriate OS dropdown according to category
function checkCategory()
{
	 $("#item").closest('form').find("input[type=text], textarea").val("");
	document.getElementById("screenSizes").style.display = "inline";
	document.getElementById("operatingSystems").style.display ="inline";
	document.getElementById("screenTypes").style.display = "inline";
	document.getElementById("screenResolutions").style.display = "inline";
	document.getElementById("operatingSystem").value = '';
	document.getElementById("screenType").value = '';
	document.getElementById("screenResolution").value='';
	
	if (document.getElementById("category").value=='Smartphone') 
		{	
		
			document.getElementById("screenSizeSmartphone").style.display = "inline";
			document.getElementById("screenSizeTV").style.display = "none";
			document.getElementById("screenSizeLaptop").style.display = "none";
			document.getElementById("frontCamera").style.display="inline";
			document.getElementById("frontCamera2").style.display="inline";
			document.getElementById("webCam").style.display="none";
			document.getElementById("rearCameraDiv").style.display="inline";

			var divsToHideA = document.getElementsByClassName("operatingSystemMobile");
			var divsToHideB = document.getElementsByClassName("screenTypeMobile");
			var divsToHide1 = document.getElementsByClassName("screenResolutionMobile");
			var allDivsToHide1 = [...divsToHideA, ...divsToHideB, ...divsToHide1];
		    for(var i = 0; i < allDivsToHide1.length; i++){
		    	allDivsToHide1[i].style.display = "inline"; // depending on what you're doing
		    }
		    var divsToHideC = document.getElementsByClassName("operatingSystemTV"); //divsToHide is an array
		    var divsToHideD =document.getElementsByClassName("screenTypeTV");
		    var divsToHide2 =document.getElementsByClassName("screenResolutionTV");
		    var allDivsToHide2 = [...divsToHideC, ...divsToHideD, ...divsToHide2];
		    for(var j = 0; j < allDivsToHide2.length; j++){
		    	allDivsToHide2[j].style.display = "none"; // depending on what you're doing
		    }
		    var divsToHideE = document.getElementsByClassName("operatingSystemLaptop"); //divsToHide is an array
		    var divsToHideF = document.getElementsByClassName("screenTypeLaptop"); //divsToHide is an array
		    var divsToHide3 = document.getElementsByClassName("screenResolutionLaptop"); //divsToHide is an array
		    var allDivsToHide3 = [...divsToHideE, ...divsToHideF, ...divsToHide3];
		    for(var k = 0; k < allDivsToHide3.length; k++){
		    	allDivsToHide3[k].style.display = "none"; // depending on what you're doing
		    }
			divsToHideA='';
		    divsToHideB='';
		    divsToHideC='';
		    divsToHideD='';
		    divsToHideE='';
		    divsToHideF='';
		    divsToHide1='';
		    divsToHide2='';
		    divsToHide3='';
		    allDivsToHide3=''
		    allDivsToHide2=''
		    allDivsToHide1=''
		}
	if (document.getElementById("category").value=='Laptop')
	{
		document.getElementById("screenSizeSmartphone").style.display = "none";
		document.getElementById("screenSizeTV").style.display = "none";
		document.getElementById("screenSizeLaptop").style.display = "inline";
		document.getElementById("frontCamera").style.display="inline";
		document.getElementById("frontCamera2").style.display="none";
		document.getElementById("webCam").style.display="inline";
		document.getElementById("rearCameraDiv").style.display="none";

		
		var divsToHideA = document.getElementsByClassName("operatingSystemMobile");
		var divsToHideB = document.getElementsByClassName("screenTypeMobile");
		var divsToHide1 = document.getElementsByClassName("screenResolutionMobile");
		var allDivsToHide1 = [...divsToHideA, ...divsToHideB, ...divsToHide1];
	    for(var i = 0; i < allDivsToHide1.length; i++){
	    	allDivsToHide1[i].style.display = "none"; // depending on what you're doing
	    }
	    var divsToHideC = document.getElementsByClassName("operatingSystemTV"); //divsToHide is an array
	    var divsToHideD =document.getElementsByClassName("screenTypeTV");
	    var divsToHide2 =document.getElementsByClassName("screenResolutionTV");
	    var allDivsToHide2 = [...divsToHideC, ...divsToHideD, ...divsToHide2];
	    for(var j = 0; j < allDivsToHide2.length; j++){
	    	allDivsToHide2[j].style.display = "none"; // depending on what you're doing
	    }
	    var divsToHideE = document.getElementsByClassName("operatingSystemLaptop"); //divsToHide is an array
	    var divsToHideF = document.getElementsByClassName("screenTypeLaptop"); //divsToHide is an array
	    var divsToHide3 = document.getElementsByClassName("screenResolutionLaptop"); //divsToHide is an array
	    var allDivsToHide3 = [...divsToHideE, ...divsToHideF, ...divsToHide3];
	    for(var k = 0; k < allDivsToHide3.length; k++){
	    	allDivsToHide3[k].style.display = "inline"; // depending on what you're doing
	    }     
		divsToHideA='';
	    divsToHideB='';
	    divsToHideC='';
	    divsToHideD='';
	    divsToHideE='';
	    divsToHideF='';
	    divsToHide1='';
	    divsToHide2='';
	    divsToHide3='';
	    allDivsToHide3=''
	    allDivsToHide2=''
	    allDivsToHide1=''
	    }
	if (document.getElementById("category").value=='Television')
	{	
		document.getElementById("screenSizeSmartphone").style.display = "none";
		document.getElementById("screenSizeTV").style.display = "inline";
		document.getElementById("screenSizeLaptop").style.display = "none";
		document.getElementById("frontCamera2").style.display="none";
		document.getElementById("frontCamera").style.display="inline";
		document.getElementById("webCam").style.display="inline";		
		document.getElementById("rearCameraDiv").style.display="none";
		document.getElementById("rearCamera").value="";
		var divsToHideA = document.getElementsByClassName("operatingSystemMobile");
		var divsToHideB = document.getElementsByClassName("screenTypeMobile");
		var divsToHide1 = document.getElementsByClassName("screenResolutionMobile");
		var allDivsToHide1 = [...divsToHideA, ...divsToHideB, ...divsToHide1];		
	    for(var i = 0; i < allDivsToHide1.length; i++){
	    	allDivsToHide1[i].style.display = "none"; // depending on what you're doing
	    }
	    var divsToHideC = document.getElementsByClassName("operatingSystemTV"); //divsToHide is an array
	    var divsToHideD =document.getElementsByClassName("screenTypeTV");
	    var divsToHide2 =document.getElementsByClassName("screenResolutionTV");
	    var allDivsToHide2 = [...divsToHideC, ...divsToHideD, ...divsToHide2];
	    for(var j = 0; j < allDivsToHide2.length; j++){
	    	allDivsToHide2[j].style.display = "inline"; // depending on what you're doing
	    }
	    var divsToHideE = document.getElementsByClassName("operatingSystemLaptop"); //divsToHide is an array
	    var divsToHideF = document.getElementsByClassName("screenTypeLaptop"); //divsToHide is an array
	    var divsToHide3 = document.getElementsByClassName("screenResolutionLaptop"); //divsToHide is an array
	    var allDivsToHide3 = [...divsToHideE, ...divsToHideF, ...divsToHide3];
	    for(var k = 0; k < allDivsToHide3.length; k++){
	    	allDivsToHide3[k].style.display = "none"; // depending on what you're doing
	    }
		divsToHideA='';
	    divsToHideB='';
	    divsToHideC='';
	    divsToHideD='';
	    divsToHideE='';
	    divsToHideF='';
	    divsToHide1='';
	    divsToHide2='';
	    divsToHide3='';
	    allDivsToHide3=''
	    allDivsToHide2=''
	    allDivsToHide1=''	
	}

}

function previewImage() {
    var preview = document.getElementById('preview');
    var file = document.getElementById('itemImage').files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
        preview.src = reader.result;
        preview.style.display = "block";
    }
    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
        preview.style.display = "none";
    }
}
</script>
</head>

<body onLoad="onLoad();">
	
	
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
	<h1 class="text-primary text-center mx-auto">Catalog Master Screen
	</h1>
	<br>
	<br>
	<br>
	<h2 class="text-center">Add Item Form</h2>

	<h3 id="ifsuccess" align="center"></h3>

	<form id="item" name="catalogMaster" method="post" enctype="multipart/form-data">
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="category" class="form-label text-left">Category:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<select id="category" name="category" onchange="checkCategory()" class="form-control text-left" required>
					<option value='' selected disabled hidden>Please select an Item Category</option>
					<option value='Laptop'>Laptop</option>
					<option value='Smartphone'>Smartphone</option>
					<option value='Television'>Television</option>
				</select>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="itemName" class="form-label text-left">Item Name:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="itemName" name="itemName" onchange="" class="form-control text-left" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>

		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="description" class="form-label text-left">Description:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="description" name="description" onchange="" class="form-control text-left" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
<!-- 			Photo Upload: <input type="file" name="itemImage"><br> -->
<!-- 			Upload button to confirm:<input type="button" name="itemImageUpload"
				id="itemImageUpload" value="Upload"> -->
		
				<label for="itemImage" class="form-label text-left">Photo Upload:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
       			<input type="file" name="itemImage" id="itemImage" accept="image/*" onchange="previewImage()" class="form-control text-left">
        	</div>
        	<div class="col col-lg-3"></div>
        <!-- <input type="submit" value="Upload"> -->
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"></div>
			<div class="col col-lg-4 align-self-center mx-auto">
        		<img id="preview" style="display:none;">
        	</div>
        	<div class="col col-lg-4"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="cpu" class="form-label text-left">CPU:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="cpu" name="cpu" onchange="" class="form-control text-left" required> <br>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="gpu" class="form-label text-left">GPU:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="gpu" name="gpu" onchange="" class="form-control text-left" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="ram" class="form-label text-left">RAM:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="ram" name="ram" class="form-control text-left" onchange="" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="storage" class="form-label text-left">Storage/HDD:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="storage" name="storage" class="form-control text-left" onchange="" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center" id="operatingSystems">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
			 	<label for="operatingSysten" class="form-label text-left">Operating Systems:</label>
			 </div>
			<!-- SmartPHONE OS -->
			<div class="col col-lg-4 align-self-center mx-auto">
				<select class="form-control text-left" id="operatingSystem" name="operatingSystem" required>
					<option value='' selected disabled hidden>Please select the from the following:</option>
					<option value='iOS' class='operatingSystemMobile'>iOS</option>
					<option value='Android' class='operatingSystemMobile'>Android</option>
					<option value='Symbian' class='operatingSystemMobile'>Symbian</option>
					<option value='Windows' class='operatingSystemMobile'>Windows</option>
					<!-- Television TV OS -->
					<option value='GoogleTV' class='operatingSystemTV'>GoogleTV / Android TV</option>
					<option value='webOS' class='operatingSystemTV'>webOS</option>
					<option value='tvOS' class='operatingSystemTV'>tvOS</option>
					<option value='roku' class='operatingSystemTV'>Roku</option>
					<!-- Laptop OS -->
					<option value='Mac' class='operatingSystemLaptop'>Mac</option>
					<option value='ChromeOS' class='operatingSystemLaptop'>ChromeOS</option>
					<option value='Windows' class='operatingSystemLaptop'>Windows</option>
					<option value='Linux' class='operatingSystemLaptop'>Linux</option>
					<option value='DOS' class='operatingSystemLaptop'>DOS</option>
				</select>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div id="screenSizes" class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="screenSize" class="form-label text-left">Screen Size (in inches):</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input
					type="number" id="screenSizeSmartphone" name="screenSizeSmartphone"
					step="0.1" min="2.5" max="8.0" class="form-control text-left">
				<input type="number"
					id="screenSizeTV" name="screenSizeTV" step="0.1" min="24"
					max="293.0" class="form-control text-left">
				<input type="number" id="screenSizeLaptop"
					name="screenSizeLaptop" step="0.1" min="11.0" max="17.3" class="form-control text-left">
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div id="screenTypes" class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="screenType" class="form-label text-left">Screen Type:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<select class="screenType" id="screenType" name="screenType" class="form-control text-left" required>
					<!-- SmartPHONE screenType -->
					<option value='' selected disabled hidden>Please select the from the following:</option>
					<option value='LCD' class='screenTypeMobile'>LCD</option>
					<option value='IPS-LCD' class='screenTypeMobile'>IPS-LCD</option>
					<option value='OLED' class='screenTypeMobile'>OLED</option>
					<option value='AMOLED' class='screenTypeMobile'>AMOLED</option>
					<!-- Television TV screenType -->
					<option value='LCD' class='screenTypeTV'>LCD</option>
					<option value='OLED' class='screenTypeTV'>OLED</option>
					<option value='Plasma' class='screenTypeTV'>Plasma</option>
					<!-- Laptop screenType -->
					<option value='LCD' class='screenTypeLaptop'>LCD</option>
					<option value='LED' class='screenTypeLaptop'>LED</option>
					<option value='OLED' class='screenTypeLaptop'>OLED</option>
					<option value='Plasma' class='screenTypeLaptop'>Plasma</option>
				</select>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div id="screenResolutions" class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="screenResolution" class="form-label text-left">Screen Resolution:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<select class="screenResolution" id="screenResolution" name="screenResolution" class="form-control text-left" required>
					<!-- SmartPHONE screenType -->
					<option value='' selected disabled hidden>Please select the
						from the following:</option>
					<option value="320x240" class='screenResolutionMobile'>320x240</option>
					<option value="360x480" class='screenResolutionMobile'>360x480</option>
					<option value="480x320" class='screenResolutionMobile'>480x320</option>
					<option value="640x480" class='screenResolutionMobile'>640x480</option>
					<option value="720x480" class='screenResolutionMobile'>720x480</option>
					<option value="800x480" class='screenResolutionMobile'>800x480</option>
					<option value="854x480" class='screenResolutionMobile'>854x480</option>
					<option value="1024x768" class='screenResolutionMobile'>1024x768</option>
					<option value="1280x720" class='screenResolutionMobile'>1280x720</option>
					<option value="1366x768" class='screenResolutionMobile'>1366x768</option>
					<option value="1920x1080" class='screenResolutionMobile'>1920x1080</option>
					<option value="2560x1440" class='screenResolutionMobile'>2560x1440</option>
					<option value="3840x2160" class='screenResolutionMobile'>3840x2160</option>
	
	
					<!-- Television TV screenType -->
					<option value="480i" class='screenResolutionTV'>480i</option>
					<option value="480p" class='screenResolutionTV'>480p</option>
					<option value="720p" class='screenResolutionTV'>720p</option>
					<option value="1080i" class='screenResolutionTV'>1080i</option>
					<option value="1080p" class='screenResolutionTV'>1080p</option>
					<option value="2160p" class='screenResolutionTV'>2160p</option>
					<!-- Laptop screenType -->
					<option value="1366x768" class='screenResolutionLaptop'>1366x768</option>
					<option value="1440x900" class='screenResolutionLaptop'>1440x900</option>
					<option value="1536x864" class='screenResolutionLaptop'>1536x864</option>
					<option value="1920x1080" class='screenResolutionLaptop'>1920x1080</option>
					<option value="2560x1440" class='screenResolutionLaptop'>2560x1440</option>
					<option value="3840x2160" class='screenResolutionLaptop'>3840x2160</option>
				</select>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="frontCamera" class="form-label text-left" id="frontCamera2">Front Camera:</label>
				<label
					for="frontCamera" id="webCam" class="form-label text-left">Web Cam:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="frontCamera" name="frontCamera" onchange="" class="form-control text-left" required>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div id="rearCameraDiv" class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="rearCamera" class="form-label text-left">Rear Camera:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<input type="text" id="rearCamera" name="rearCamera" onchange="" class="form-control text-left">
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="listingStatus" class="form-label text-left">Listing Status:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<select class="listingStatus" id="listingStatus" name="listingStatus" class="form-control text-left" required>
					<option value='' selected disabled hidden>Please select the from the following:</option>
					<option value='A'>Approve</option>
					<option value='X'>Reject</option>
				</select>
			</div>
			<div class="col col-lg-3"></div>
		</div>
		<br>
		<div class="row justify-content-md-center">
			<div class="col col-lg-3"></div>
			<div class="col col-lg-2 align-self-center mx-auto">
				<label for="approvalStatus" class="form-label text-left">Approval Status:</label>
			</div>
			<div class="col col-lg-4 align-self-center mx-auto">
				<select class="approvalStatus" id="approvalStatus" name="approvalStatus" class="form-control text-left" required>
					<option value='' selected disabled hidden>Please select the from the following:</option>
					<option value='A'>Approve</option>
					<option value='X'>Reject</option>
				</select>
			</div>
			<!-- <input
				type="text" id="approvalStatus" name="approvalStatus"
				onchange="" required> -->
			<div class="col col-lg-3"></div>
		</div>
		<br><br>
		<div class="col-md-3 text-center mx-auto">
			<input type="submit" name="submit" value="Submit" />
		</div>
	</form>
	<br>
</body>

<script src="formValidation.js" type="text/javascript"></script>

<script type="text/javascript"> 
$("#item").on('submit', function(event) {
    event.preventDefault();
    //var data = $("#item").serialize();
    var data = new FormData($("#item")[0]);
    //console.log("qwe"+data)
    $.ajax({
      url: "<%=request.getContextPath()%>/addItem",
      data: data,
      type: 'POST',
      processData: false, // Don't process the data (files)
      contentType: false, //
      success: function(response){
						console.log(response);
						console.log("API call done..");
			console.log('<%if (session.getAttribute("msg2") != null) {
	session.getAttribute("msg2").toString();
}%>');
						
						if (response == "true") { 
	 						$("#ifsuccess").html("Item Added Successfully and will by available for users");
	 						<%-- $("#item").hide();
							window.setTimeout(goToNewPage, 1500);
							function goToNewPage() {
								window.location.href = "<%=request.getContextPath()%>/loginPage.jsp"							
							} 
							
							    $(this).closest('form').find("input[type=text], textarea").val("");); --%>
							    $("#item").closest('form').find("input[type=text], textarea").val("");
	 					} else {
	 						$("#ifsuccess").html("Their is an exception, please try again!");
	 					}
					},
		            error: function (jqXHR, exception) {
		                console.log('Error occured!!');
		            }
				})				
			})
</script>

</html>