package com.utility.pkg;

import java.util.regex.Pattern;

public class utilityJava {

    protected boolean patternMatches(String text, String regexPattern) {
        return Pattern.compile(regexPattern)
          .matcher(text)
          .matches();
    }    
    protected boolean validateRegistration(String name, String password, String emailId){
    	int minPasswordLength = 7;
    	int maxPasswordLength = 12;
    	if (validateAllLetter(name) && validateEmail(password) && validateLength(password, minPasswordLength, maxPasswordLength)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }    
    protected boolean validateAllLetter(String uname){ 
    	String pattern = "/^[A-Za-z]+$/";
    	return patternMatches(uname, pattern);
    }
    protected boolean validateEmail(String uemail)
    {
    	String mailformat = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";
    	return patternMatches(uemail, mailformat);    	
    }
    protected boolean validateLength(String text, int minLen, int maxLen){
    	if (text.length() >= minLen && text.length() <= maxLen) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
