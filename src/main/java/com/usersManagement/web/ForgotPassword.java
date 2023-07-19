package com.usersManagement.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.database.pkg.UserDAO;
import com.usersbean.pkg.User;
import com.utility.pkg.dbGetConnection;
import com.codejava.email.*;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private static final String DB_URL = "jdbc:mysql://localhost:3306/AuctionSystem2?allowPublicKeyRetrieval=true&useSSL=false";
//	private static final String DB_USER = "root";
//	private static final String DB_PASS = "root";
	private String host;
    private String port;
    private String user;
    private String pass;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("emailId");
		String phoneNr = request.getParameter("phoneNr");

	    ServletContext context = getServletContext();
	    host = context.getInitParameter("host");
	    port = context.getInitParameter("port");
	    user = context.getInitParameter("user");
	    pass = context.getInitParameter("pass");
	    try {
			if (isValidEmail(email, phoneNr)) 
			{
				
				String newPassword= generateNewPassword(getNamefromEmail(email).replaceAll("\\s", ""),phoneNr);;
				System.out.println(newPassword);
				
				if(setNewPassword(email,newPassword))
				{
					String content="Dear "+getNamefromEmail(email)+",\nWe are from Auction.com, \n You are receving this mail as Forgot Password functionality was used for the account linked to this email address. \n Kindly contact us if not raised by you. "
							+ " \n Your New Password is :"+newPassword +" \n Regards, \n Auction.com \n Happy Bidding!";
					try {
						EmailUtility.sendEmail(host, port, user, pass, email, "Reset Password : Auction Management", content);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//request.setAttribute("newPassword", newPassword);
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
				
				}
				else {
					request.setAttribute("alertMsg", "Error in Setting a new Password, contact administrator!");
					request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
				}
			}
			else {
				request.setAttribute("alertMsg", "InvalidEmail");
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
	}

	public boolean isValidEmail(String email, String phoneNr) throws ClassNotFoundException {
		boolean status = false;
		// Long userID = null;
//		Class.forName("com.mysql.jdbc.Driver");
		try (Connection conn = dbGetConnection.dbGetConn(); //= DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement preparedStatement = conn.prepareStatement(
						"select u.userId from users__ u inner join endusers e on u.userId=e.userId where u.emailId=? and e.phoneNr=?;")) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, phoneNr);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			int size = 0;
			if (rs != null) {
				rs.last();
				size = rs.getRow();
			}
			if (size > 0) {
				status = true;
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	public String getNamefromEmail(String email) throws ClassNotFoundException {
		// Long userID = null;
		String name=null;
//		Class.forName("com.mysql.jdbc.Driver");
		try (Connection conn = dbGetConnection.dbGetConn(); //= DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement preparedStatement = conn.prepareStatement(
						"select u.name from users__ u where u.emailId=?")) {
			preparedStatement.setString(1, email);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if (rs != null) {
				rs.last();
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return name;
	}
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
    
	public boolean setNewPassword(String email, String password) throws ClassNotFoundException {

		boolean status = false;
//		Class.forName("com.mysql.jdbc.Driver");
		try (Connection conn = dbGetConnection.dbGetConn();  //= DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement preparedStatement = conn.prepareStatement(
						"update users__ set password=?,encrypted_password=? where emailId=?;")) {
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, encrypt(password));
			preparedStatement.setString(3, email);

			System.out.println(preparedStatement);
			Integer updatedRows = preparedStatement.executeUpdate();
			
			
			if (updatedRows!=null || updatedRows!=0) {
				status=true;
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}


	private String generateNewPassword(String name, String contactNumber) {
		String newpassword= name +"@"+ contactNumber;
		return newpassword.substring(0, 10);
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
