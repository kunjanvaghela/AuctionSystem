package com.users.database.pkg;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.usersbean.pkg.User;
import com.utility.pkg.dbGetConnection;
import com.usersbean.pkg.EmployeeProfileMaster;
import com.usersbean.pkg.EndUsers;

public class UserDAO {

	public int registerUser(User user) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO `users__`(`name`,`encrypted_name`,`emailId`,"
				+ "`encrypted_emailId`,`password`,`encrypted_password`)VALUES(?,?,?,?,?,?);";

		int result = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEncryptedName()); // PII PENDING
			preparedStatement.setString(3, user.getEmailId());
			preparedStatement.setString(4, user.getEncryptedEmailId()); // PII PENDING
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getEncryptedPassword()); // PII PENDING

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println("RESULT OF REGISTER USER::::->" + result);
		return result;
	}


	public boolean validate(User user, HttpServletRequest request) throws ClassNotFoundException {
		boolean status = false;
		//Long userID = null;
		Class.forName("com.mysql.jdbc.Driver");
		HttpSession session = request.getSession();
//		try (Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost:3306/AuctionSystem2?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
		try {
			Connection connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement("select userId, name, emailId from users__ where encrypted_emailId = ? and encrypted_password = ? ");
			preparedStatement.setString(1, user.getEncryptedEmailId());
			preparedStatement.setString(2, user.getEncryptedPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			int size = 0;
			if (rs != null) {
				rs.last();
				Long userId = rs.getLong("userid");
                String emailId = rs.getString("emailId");
                String username = rs.getString("name");
				session.setAttribute("userId", userId);
                session.setAttribute("emailId", emailId);
                session.setAttribute("username", username);
                user.setName(rs.getString("name"));
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
    
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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
    
    public int registerEmployee(EmployeeProfileMaster epm) throws ClassNotFoundException {
    	String INSERT_USERS_SQL = "INSERT INTO `users__`(`name`,`encrypted_name`,`emailId`,"
        		+ "`encrypted_emailId`,`password`,`encrypted_password`)VALUES(?,?,?,?,?,?);";
    	String INSERT_EMPLOYEEPROFILEMASTER_SQL = "INSERT INTO `employeeprofilemaster`(`userId`,`ssn`,`Dateofbirth`,"
        		+ "`createdOn`)VALUES(?,?,?,?);";
    	String DELETE_USERS_SQL = "DELETE FROM `users__` WHERE userId = ?;";

        int result = 0;
        int userid = 0;
//        boolean status = false;	
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, epm.getName());
            preparedStatement.setString(2, epm.getEncryptedName()); //PII PENDING
            preparedStatement.setString(3, epm.getEmailId());
            preparedStatement.setString(4, epm.getEncryptedEmailId()); //PII PENDING
            preparedStatement.setString(5, epm.getPassword());
            preparedStatement.setString(6, epm.getEncryptedPassword()); //PII PENDING

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            
            
            try (

                    PreparedStatement preparedStatement1 = connection
                    .prepareStatement("select userid from users__ where encrypted_emailId = ? and encrypted_password = ? ")) {
                    preparedStatement1.setString(1, epm.getEncryptedEmailId());
                    preparedStatement1.setString(2, epm.getEncryptedPassword());

                    System.out.println(preparedStatement1);
                    ResultSet rs = preparedStatement1.executeQuery();
                   	//status=true;

                    	while (rs.next()){
                    		userid = rs.getInt("userid");
                    	}
                    	try (
                            	PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_EMPLOYEEPROFILEMASTER_SQL)) {
                                preparedStatement2.setInt(1, userid);
                                preparedStatement2.setInt(2, epm.getssn()); //PII PENDING
                                preparedStatement2.setDate(3, Date.valueOf(epm.getdob()));
                                preparedStatement2.setDate(4, Date.valueOf(LocalDate.now())); //PII PENDING

                                System.out.println(preparedStatement2);
                                result = preparedStatement2.executeUpdate();
                            }
                            catch (SQLException e) {
        	                    // process sql exception
        	                    printSQLException(e);
        	                    try(PreparedStatement preparedStatement3 = connection.prepareStatement(DELETE_USERS_SQL)) {
        	                    		preparedStatement3.setInt(1, userid);
        	                    		System.out.println(preparedStatement3);
                                        result = preparedStatement3.executeUpdate();
        	                         } catch (SQLException e1) {
        	                            e1.printStackTrace();
        	                         } 
                            }
//                    }
                    
                } catch (SQLException e) {
                    // process sql exception
                    printSQLException(e);
                }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    
    public int registerEndUser(EndUsers enduser) throws ClassNotFoundException {
    	String INSERT_USERS_SQL = "INSERT INTO `users__`(`name`,`encrypted_name`,`emailId`,"
        		+ "`encrypted_emailId`,`password`,`encrypted_password`,`add_info3`)VALUES(?,?,?,?,?,?,?);";
    	String INSERT_ENDUSERS_SQL = "INSERT INTO `Endusers`(`userId`,`address`,`phoneNr`)VALUES(?,?,?);";
    	String DELETE_USERS_SQL = "DELETE FROM `users__` WHERE userId = ?;";

        int result = 0;
        int userid = 0;
//        boolean status = false;	
//        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, enduser.getName());
            preparedStatement.setString(2, enduser.getEncryptedName()); //PII PENDING
            preparedStatement.setString(3, enduser.getEmailId());
            preparedStatement.setString(4, enduser.getEncryptedEmailId()); //PII PENDING
            preparedStatement.setString(5, enduser.getPassword());
            preparedStatement.setString(6, enduser.getEncryptedPassword()); //PII PENDING
            preparedStatement.setString(7, enduser.getAdd_info3()); //PII PENDING

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            
            
            try (
                    // Step 2:Create a statement using connection object
                    PreparedStatement preparedStatement1 = connection
                    .prepareStatement("select userid from users__ where encrypted_emailId = ? and encrypted_password = ? ")) {
                    preparedStatement1.setString(1, enduser.getEncryptedEmailId());
                    preparedStatement1.setString(2, enduser.getEncryptedPassword());

                    System.out.println(preparedStatement1);
                    ResultSet rs = preparedStatement1.executeQuery();
                    	while (rs.next()){
                    		userid = rs.getInt("userid");
                    	}
                    	try (
                            	PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_ENDUSERS_SQL)) {
                                preparedStatement2.setInt(1, userid);
                                preparedStatement2.setString(2, enduser.getAddress()); //PII PENDING
                                preparedStatement2.setString(3, enduser.getPhoneNr());

                                System.out.println(preparedStatement2);
                                result = preparedStatement2.executeUpdate();
                            }
                            catch (SQLException e) {
        	                    // process sql exception
        	                    printSQLException(e);
        	                    try(PreparedStatement preparedStatement3 = connection.prepareStatement(DELETE_USERS_SQL)) {
        	                    		preparedStatement3.setInt(1, userid);
        	                    		System.out.println(preparedStatement3);
                                        result = preparedStatement3.executeUpdate();
        	                         } catch (SQLException e1) {
        	                            e1.printStackTrace();
        	                         } 
                            }
//                    }
                    
                } catch (SQLException e) {
                    // process sql exception
                    printSQLException(e);
                }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    
    
    public Integer changePasswordApproved(Long userID) throws ClassNotFoundException {
    	String MODIFY_PASSWORD_SQL = "UPDATE `users__` SET `password` = `Add_info3`,`encrypted_password` = `Add_info3` WHERE `userId` = ?;";
    	
    	int result = 0;
//      boolean status = false;	
      Class.forName("com.mysql.jdbc.Driver");

      try (Connection connection = dbGetConnection.dbGetConn();
	
	          PreparedStatement preparedStatement = connection.prepareStatement(MODIFY_PASSWORD_SQL)) {
	          preparedStatement.setLong(1, userID);
	          
	          System.out.println(preparedStatement);
	          result = preparedStatement.executeUpdate();
      } catch (SQLException e) {
          printSQLException(e);
      }
      return result;
          
    }

    
    public Integer getUserType(Long userId) throws ClassNotFoundException {
    	Integer userType = 0;
    	String CHECK_EMPLOYEEPROFILEMASTER = "SELECT userId from `Employeeprofilemaster` WHERE `userId` = ?;";
//    	Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();
  	
	  	          PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMPLOYEEPROFILEMASTER)) {
	  	          preparedStatement.setLong(1, userId);
	  	          
	  	          System.out.println(preparedStatement);
	  	          ResultSet rs = preparedStatement.executeQuery();
	  	          rs.next();

	  	        int size=0;
	  	          if (rs!=null) {
	  	        	  rs.last();    // moves cursor to the last row
	  	        	  size = rs.getRow();
	  	        	  userType = 1;
	  	          }
	  	          if (size == 0) {
	  	        	userType = 2;
	  	          }
	        }
        catch (SQLException e) {
            printSQLException(e);
        }
    	
    	return userType;
    }
}