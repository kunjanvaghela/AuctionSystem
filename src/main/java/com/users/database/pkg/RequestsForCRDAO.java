package com.users.database.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usersbean.pkg.RequestsForCR;
import com.usersbean.pkg.User;
import com.utility.pkg.dbGetConnection;

public class RequestsForCRDAO {
    
    public List<RequestsForCR> getRequestsForCR() throws ClassNotFoundException{
	    List<RequestsForCR> list=new ArrayList<RequestsForCR>();
	    Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
	    	String querry="select * from enduserrequests where current_status = 'I'; ";
	    	PreparedStatement ps = connection.prepareStatement(querry);	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
		    	  RequestsForCR requestsForCR=new RequestsForCR();
		    	  requestsForCR.setUserId(rs.getLong("userId"));
		    	  requestsForCR.setRequestId(rs.getLong("requestid"));
		    	  Long temp=rs.getLong("bidId") ;
		    	  if(temp!=null)
		    	  {requestsForCR.setBidId(rs.getLong("bidId"));}
		    	  requestsForCR.setRequestType(rs.getString("requesttype"));
		    	  requestsForCR.setListingId(rs.getLong("listingId"));
		    	  requestsForCR.setUpdateDescription(rs.getString("update_description"));
		    	  requestsForCR.setCurrent_status(rs.getString("current_status"));
		    	  list.add(requestsForCR);
	    	}
		}
		catch (SQLException e) {
			printSQLException(e);
	    }
		return list;
    }
    
    public RequestsForCR getRequestIDData(Integer requestId) throws ClassNotFoundException{
    		Class.forName("com.mysql.jdbc.Driver");
    		
			RequestsForCR requestsForCR=new RequestsForCR();
			try(Connection connection = dbGetConnection.dbGetConn()) {
				String querry="select * from enduserrequests where requestid = "+ Integer.toString(requestId);
				PreparedStatement ps = connection.prepareStatement(querry);	
				ResultSet rs = ps.executeQuery();
				rs.next();
				requestsForCR.setUserId(rs.getLong("userId"));
				requestsForCR.setRequestId(rs.getLong("requestid"));
				requestsForCR.setBidId(rs.getLong("bidId"));
				requestsForCR.setRequestType(rs.getString("requesttype"));
				requestsForCR.setUpdateDescription(rs.getString("update_description"));
				requestsForCR.setListingId(rs.getLong("listingId"));
				
	    	}
			catch (Exception e) {
		    	System.out.println(e);
		    }

			return requestsForCR;
    }
    
    public Integer deleteRequestIDData(Integer requestId) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try(Connection connection = dbGetConnection.dbGetConn()) {
			String querry="delete from enduserrequests where requestid = "+ Integer.toString(requestId);
			PreparedStatement ps = connection.prepareStatement(querry);	
			result = ps.executeUpdate();	
			
    	}
		catch (Exception e) {
	    	System.out.println(e);
	    }

		return result;
    }

    
    public Integer updateRequestIdStatus(Integer requestId, String updateToStatus) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try(Connection connection = dbGetConnection.dbGetConn()) {
			String querry="UPDATE enduserrequests SET current_status = ? where requestid = ?";
			PreparedStatement ps = connection.prepareStatement(querry);
			ps.setLong(2, requestId);
			ps.setString(1, updateToStatus);
			result = ps.executeUpdate();	
			
    	}
		catch (Exception e) {
	    	System.out.println(e);
	    }

		return result;
    }
    
    public Integer removeBidFromBids(RequestsForCR requestsForCR) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try(Connection connection = dbGetConnection.dbGetConn()) {
			String querry="UPDATE bids SET current_status = ? where bidId = ?";
			PreparedStatement ps = connection.prepareStatement(querry);
			ps.setString(1, "D");
			ps.setLong(2, requestsForCR.getBidId());
			System.out.println(ps);
			result = ps.executeUpdate();	
			
    	}
		catch (Exception e) {
	    	System.out.println(e);
	    }

		return result;
    }
    
    public Integer removeAuctionFromItem_Listing(RequestsForCR requestsForCR) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try(Connection connection = dbGetConnection.dbGetConn()) {
			String querry="UPDATE item_listing SET current_status = ? where listingId = ?";
			PreparedStatement ps = connection.prepareStatement(querry);
			ps.setString(1, "D");
			ps.setLong(2, requestsForCR.getListingId());
			System.out.println(ps);
			result = ps.executeUpdate();	
			
    	}
		catch (Exception e) {
	    	System.out.println(e);
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