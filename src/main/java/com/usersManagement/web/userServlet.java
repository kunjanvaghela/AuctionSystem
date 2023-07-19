package com.usersManagement.web;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.database.pkg.UserDAO;
import com.usersbean.pkg.User;
import com.usersbean.pkg.EndUsers;
import java.util.regex.Pattern;

@WebServlet("/register")
public class userServlet  extends HttpServlet {
	public static String myKey="1079DBDS";
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey() {
    	MessageDigest sha = null;
      try {
        key = myKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
      } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    public static String encrypt(final String strToEncrypt) {
      try {
        setKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder()
          .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
      } catch (Exception e) {
        System.out.println("Error while encrypting: " + e.toString());
      }
      return null;
    }

    public static String decrypt(final String strToDecrypt, final String secret) {
      try {
        setKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder()
          .decode(strToDecrypt)));
      } catch (Exception e) {
        System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
    }
    
    public void init() {
    	userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	System.out.print("userservletinDoPost");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String emailId = request.getParameter("emailId");
        System.out.println("name:::"+name);
        System.out.println("password:::"+password);
        System.out.println("emailId:::"+emailId);
        String address = request.getParameter("address");
        String phoneNr = request.getParameter("phoneNr");
        String pseudoName = request.getParameter("pseudoName");
        
        String encryptedName=name;
        String encryptedEmailId=emailId;
        String encryptedPassword=password;
        
        EndUsers user = new EndUsers();
        //user.setUserId());
        user.setName(name);
        user.setEncryptedName(encrypt(encryptedName)); //PII PENDING
        user.setEmailId(emailId);
        user.setEncryptedEmailId(encrypt(encryptedEmailId)); //PII PENDING
        user.setPassword(password);
        user.setEncryptedPassword(encrypt(encryptedPassword)); //PII PENDING
        user.setAddress(address);
        user.setPhoneNr(phoneNr);
        user.setAdd_info3(pseudoName);
        HttpSession session = request.getSession();

        try {
        	int rowsUpdated = userDAO.registerEndUser(user);
            System.out.print(rowsUpdated);
            if (rowsUpdated != 0) {
//      	      session.setAttribute("msg2", "false");
    	      response.getWriter().write("true");
    	      session.setAttribute("msg2", "true");
//              response.sendRedirect("loginPage.jsp");
        	} else {
    	      session.setAttribute("msg2", "Their is an exception. Please try again! ");
      	      response.getWriter().write("false");        		
        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
  	      	session.setAttribute("msg2", "Their is an exception. Please try again! ");
  	      	response.getWriter().write("false");

        }

        //response.sendRedirect("loginPage.jsp");
    }
    
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
    
    protected boolean validatePhoneNr(String phoneNr)
    {
    	String phoneNrFormat = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$\r\n";
    	return patternMatches(phoneNr, phoneNrFormat);    	
    }
}