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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

	public static String myKey="1079DBDS";
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
    	
    	System.out.println("In /login servlet");

        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
        
        
        User user = new User();
        user.setEmailId(emailId);
        user.setPassword(password);
        user.setEncryptedEmailId(encrypt(emailId)); //PII PENDING
        user.setPassword(password);
        user.setEncryptedPassword(encrypt(password)); //PII PENDING

        
        try {
            if (userDAO.validate(user,request)) {
                HttpSession session = request.getSession();
                 //response.sendRedirect("loginSuccess.jsp");
                 int userType = userDAO.getUserType(Long.parseLong(session.getAttribute("userId").toString())); // 1: Employee, 2: Enduser
            	System.out.println(userType);
            	
            	if (emailId.equals("admin@auction.com")) {
            		session.setAttribute("UserType", "Admin");
            		//response.sendRedirect("/salesReport");
            		request.getRequestDispatcher("/salesReport").forward(request, response);

            	}
            	else if (userType==1) {
            		session.setAttribute("UserType", "CR");
            		//response.sendRedirect("CustomerRepresentativePortal.jsp");
            		request.getRequestDispatcher("/cRPortalServlet").forward(request, response);
            		//request.getRequestDispatcher("/cRPortalServlet").forward(request, response);
            		//response.sendRedirect("/cRPortalServlet");

            	}
            	else if (userType==2) {
            		session.setAttribute("UserType", "EndUser");
//            		response.sendRedirect("/dashboard");
            		request.getRequestDispatcher("/dashboard").forward(request, response);

            	}
            	else {
            		response.sendRedirect("loginSuccess.jsp");
            	}
                 
            } else {

                response.sendRedirect("loginFailed.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}