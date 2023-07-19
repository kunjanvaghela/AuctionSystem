package com.users.database.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usersbean.pkg.FAQ;
import com.utility.pkg.dbGetConnection;

public class FAQDAO {

    public int registerFAQ(FAQ obj_faq) throws ClassNotFoundException {
        String INSERT_BIDS_SQL = "INSERT INTO `frequentlyaskedquestions`(`question`,`userId`,statusFAQ)VALUES(?,?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BIDS_SQL)) {
            preparedStatement.setString(1, obj_faq.getQuestion());
            preparedStatement.setLong(2, obj_faq.getUserId());
            preparedStatement.setString(3, "P");

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }


    public List<FAQ> getAllQuestions() throws ClassNotFoundException{
	    List<FAQ> list=new ArrayList<FAQ>();
	    
	    String querry="select * from frequentlyaskedquestions order by statusFAQ desc;";
	    Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
	    	
	    	PreparedStatement ps = connection.prepareStatement(querry);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
		    	  FAQ obj_faq=new FAQ();
		    	  obj_faq.setFaqId(rs.getLong("faqId"));
		    	  obj_faq.setUserId(rs.getLong("userId"));
		    	  obj_faq.setQuestion(rs.getString("question"));
		    	  obj_faq.setAnswer(rs.getString("answer"));
		    	  obj_faq.setStatusFAQ(rs.getString("statusFAQ"));
		    	  list.add(obj_faq);
	    	}
		}
		catch (Exception e) {
	    	System.out.println(e);
	    }
		return list;
    }
    
    public Integer postAnswer(Long faqId, String answer) throws ClassNotFoundException {
    	String UPDATE_QUERY = "UPDATE `frequentlyaskedquestions` SET answer= ?, statusFAQ=? where faqid = ?;";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, answer);
            preparedStatement.setString(2, "C");
            preparedStatement.setLong(3, faqId);

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            printSQLException(e);
        }
    	
    	
    	
		return result;
    	
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
    
    
    
}