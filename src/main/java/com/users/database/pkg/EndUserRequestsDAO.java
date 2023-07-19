package com.users.database.pkg;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usersbean.pkg.Bids;
import com.usersbean.pkg.EndUserRequests;
import com.usersbean.pkg.ItemListing;
import com.utility.pkg.dbGetConnection;

public class EndUserRequestsDAO {

    public int insertUserRequest(EndUserRequests endUserRequests) throws ClassNotFoundException {
        String INSERT_SQL = "INSERT INTO `enduserrequests`(`userId`,`requestType`,`bidId`, listingId, "
        		+ "`update_description`,current_status)VALUES(?,?,?,?,?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = dbGetConnection.dbGetConn();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setLong(1, endUserRequests.getUserId());
            preparedStatement.setString(2, endUserRequests.getRequestType());
//            preparedStatement.setFloat(3, endUserRequests.getBidId());
//            preparedStatement.setFloat(3, endUserRequests.getAuctionId());
            if (endUserRequests.getBidId() != null) {
                preparedStatement.setFloat(3, endUserRequests.getBidId());
            } else {
                preparedStatement.setNull(3, java.sql.Types.FLOAT);
            }

            if (endUserRequests.getAuctionId() != null) {
                preparedStatement.setFloat(4, endUserRequests.getAuctionId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.FLOAT);
            }
            preparedStatement.setString(5, endUserRequests.getUpdate_description());
            preparedStatement.setString(6, endUserRequests.getCurrent_status());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }


    public List<Bids> getBidsBasedOnItemListing(ItemListing itemListing) throws ClassNotFoundException{
	    List<Bids> list=new ArrayList<Bids>();
	    String querry="select * from bids where listingId = ?;";
	    Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
	    	
	    	PreparedStatement ps = connection.prepareStatement(querry);
	    	ps.setLong(1, itemListing.getListingId());
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
		    	  Bids bid=new Bids();
		    	  bid.setBidId(rs.getLong("bidId"));
		    	  bid.setBuyerId(rs.getLong("buyerId"));
		    	  bid.setListingId(rs.getLong("listingId"));
		    	  bid.setLowest_bid(rs.getFloat("lowest_bid"));
		    	  bid.setHighest_bid(rs.getFloat("highest_bid"));
		    	  list.add(bid);
	    	}
		}
		catch (Exception e) {
	    	System.out.println(e);
	    }
		return list;
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
    
    public List<Bids> getListOfLosingBidders(ItemListing itemListing) throws ClassNotFoundException{
	    List<Bids> list=new ArrayList<Bids>();
	    String querry="select a.*, b.name, b.emailId from bids a, users__ b where listingId = ? and bidId != ? and a.buyerId=b.userId;";
	    Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
	    	
	    	PreparedStatement ps = connection.prepareStatement(querry);
	    	ps.setLong(1, itemListing.getListingId());
	    	ps.setLong(2, itemListing.getBestBidId());
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
		    	  Bids bid=new Bids();
		    	  bid.setBidId(rs.getLong("bidId"));
		    	  bid.setBuyerId(rs.getLong("buyerId"));
		    	  bid.setListingId(rs.getLong("listingId"));
		    	  bid.setLowest_bid(rs.getFloat("lowest_bid"));
		    	  bid.setHighest_bid(rs.getFloat("highest_bid"));
		    	  bid.setBidderName(rs.getString("name"));
		    	  bid.setBidderEmail(rs.getString("emailId"));
		    	  list.add(bid);
	    	}
		}
		catch (Exception e) {
	    	System.out.println(e);
	    }
		return list;
    }
    
}