package com.admin.Database.pkg;

import java.sql.*;
import java.util.*;
import com.admin.Reports.SalesReport;
import com.itembean.pkg.catalogMaster;
import com.utility.pkg.dbGetConnection;

public class SalesReportDAO {
	private Connection connection;
	// Your code to connect to the database goes here
	public void init() {
		connection = dbGetConnection.dbGetConn();
	}
	public Long getTotalEarnings() {
    	String getTotalEarnings ="SELECT SUM(current_best_bid_amount) AS total_earnings\r\n"
    	+ "FROM item_listing\r\n"
    	+ "WHERE current_best_bid_amount IS NOT NULL";
    	Long totalEarnings=null;
    	try {
    		connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getTotalEarnings);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				rs.last();
				totalEarnings = rs.getLong("total_earnings");
				return totalEarnings;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

	public List<catalogMaster> getEarningsByItem() {
    	String getEarningsByItemQuery ="SELECT catalog_master.name as name, SUM(item_listing.current_best_bid_amount) AS earnings\r\n"
    			+ "FROM item_listing\r\n"
    			+ "INNER JOIN catalog_master ON item_listing.itemId = catalog_master.itemId\r\n"
    			+ "WHERE item_listing.current_best_bid_amount IS NOT NULL\r\n"
    			+ "GROUP BY catalog_master.itemId ORDER BY earnings DESC";
    	
	    List<catalogMaster> getEarningsByItem=new ArrayList<catalogMaster>();

    	try {
    		connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getEarningsByItemQuery);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setItemName(rs.getString("name"));
	    		obj_catalogMaster.setAddInfo1(rs.getString("earnings"));
	    		getEarningsByItem.add(obj_catalogMaster);
	    	}
			return getEarningsByItem;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

	public List<catalogMaster> getEarningsByCategory() {
    	String getEarningsByCategoryQuery ="SELECT catalog_master.category as category, SUM(item_listing.current_best_bid_amount) AS earnings\r\n"
    			+ "FROM item_listing\r\n"
    			+ "INNER JOIN catalog_master ON item_listing.itemId = catalog_master.itemId\r\n"
    			+ "WHERE item_listing.current_best_bid_amount IS NOT NULL\r\n"
    			+ "GROUP BY catalog_master.category ORDER BY earnings DESC;";
    	
	    List<catalogMaster> getEarningsByCategory=new ArrayList<catalogMaster>();

    	try {
    		connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getEarningsByCategoryQuery);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setCategory(rs.getString("category"));
	    		obj_catalogMaster.setAddInfo1(rs.getString("earnings"));
	    		getEarningsByCategory.add(obj_catalogMaster);
	    	}
			return getEarningsByCategory;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

//	public List<SalesReport> getEarningsByEndUser() {
//		// Your code to generate the earnings by end-user report goes here
//		return new ArrayList<SalesReport>();
//	}

	public List<catalogMaster> getEarningsByEndUser() {
    	String getEarningsByEndUserQuery ="SELECT endusers.userId as userId, users__.name as name,SUM(bids.current_bid) AS earnings\r\n"
    			+ "FROM bids\r\n"
    			+ "INNER JOIN endusers ON bids.buyerId = endusers.userId\r\n"
    			+ "INNER JOIN users__ ON users__.userId = endusers.userId AND users__.userId = bids.buyerId\r\n"
    			+ "WHERE bids.current_status = 'W'\r\n"
    			+ "GROUP BY endusers.userId ORDER BY earnings DESC;";
    	
	    List<catalogMaster> getEarningsByEndUser=new ArrayList<catalogMaster>();

    	try {
			connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getEarningsByEndUserQuery);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setAddInfo3(rs.getString("userId"));
	    		obj_catalogMaster.setAddInfo2(rs.getString("name"));
	    		obj_catalogMaster.setAddInfo1(rs.getString("earnings"));
	    		getEarningsByEndUser.add(obj_catalogMaster);
	    	}
			return getEarningsByEndUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	

	public List<catalogMaster> getBestSellingItems() {
    	String getBestSellingItemsQuery ="SELECT catalog_master.name as itemName, COUNT(item_listing.listingId) AS frequency, SUM(item_listing.current_best_bid_amount) AS earnings\r\n"
    			+ "FROM item_listing\r\n"
    			+ "INNER JOIN catalog_master ON item_listing.itemId = catalog_master.itemId\r\n"
    			+ "WHERE item_listing.current_best_bid_amount IS NOT NULL\r\n"
    			+ "GROUP BY catalog_master.itemId\r\n"
    			+ "ORDER BY frequency,earnings DESC;";
    	
	    List<catalogMaster> getBestSellingItems=new ArrayList<catalogMaster>();

    	try {
			connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getBestSellingItemsQuery);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setItemName(rs.getString("itemName"));
	    		obj_catalogMaster.setAddInfo2(rs.getString("frequency"));
	    		obj_catalogMaster.setAddInfo1(rs.getString("earnings"));
	    		getBestSellingItems.add(obj_catalogMaster);
	    	}
			return getBestSellingItems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
//	public List<SalesReport> getBestBuyers() {
//		// Your code to generate the best buyers report goes here
//		return new ArrayList<SalesReport>();
//	}
	
	
	public List<catalogMaster> getBestBuyers() {
    	String getBestBuyersQuery ="SELECT endusers.userId,users__.name as name, SUM(bids.current_bid) AS total_spent\r\n"
    			+ "FROM bids\r\n"
    			+ "INNER JOIN endusers ON bids.buyerId = endusers.userId\r\n"
    			+ "INNER JOIN users__ ON users__.userId = endusers.userId AND users__.userId = bids.buyerId\r\n"
    			+ "WHERE bids.current_status = 'W'\r\n"
    			+ "GROUP BY endusers.userId\r\n"
    			+ "ORDER BY total_spent DESC;";
    	
	    List<catalogMaster> getBestBuyers=new ArrayList<catalogMaster>();

    	try {
			connection = dbGetConnection.dbGetConn();
			PreparedStatement preparedStatement = connection.prepareStatement(getBestBuyersQuery);
			System.out.println("preparedStatement"+preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setAddInfo3(rs.getString("userId"));
	    		obj_catalogMaster.setAddInfo2(rs.getString("name"));
	    		obj_catalogMaster.setAddInfo1(rs.getString("total_spent"));
	    		getBestBuyers.add(obj_catalogMaster);
	    	}
			return getBestBuyers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
