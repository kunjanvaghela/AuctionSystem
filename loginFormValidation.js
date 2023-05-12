/**
 * 
 */

 
function formValidation(){
	var uemail = document.login.emailId;
	var passid = document.login.password;
	
	if(uemail.value==''||passid.value==''){	
		alert("You have missed mandatory field(s)");
		return false;
	}
	
	if(validateEmail(uemail) && passid_validation(passid,7,12)){
		return true;
	}
	return false;
}


function passid_validation(passid,mx,my){
	var passid_len = passid.value.length;
	if (passid_len == 0 ){
		alert("Password should not be empty");
		passid.focus();
		return false;
	}
	else if(passid_len >= my || passid_len < mx){
		alert("Password Length should be between "+mx+" to "+my);
		return false;
	}
	return true;
}

function allLetter(uname){ 
	var letters = /^[a-zA-Z ]*$/;
	if(uname.value.match(letters)){
		return true;
	}
	alert('Username must have alphabet characters only');
	uname.focus();
	return false;
}


function validateEmail(uemail){
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(uemail.value.match(mailformat)){
		return true;
	}
	alert("You have entered an invalid email address!");
	uemail.focus();
	return false;
}


function cpassid_validation(cpassid, passid){
	if (passid.value == cpassid.value){
		return true
	}
	alert("Password and Confirm Password do not match!");
	return false;
}
