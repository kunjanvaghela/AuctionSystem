package com.users.database.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.itembean.pkg.catalogMaster;
import com.usersbean.pkg.ItemListing;
import com.usersbean.pkg.ItemListingReport;
import com.usersbean.pkg.RequestsForCR;
import com.utility.pkg.dbGetConnection;

public class ItemListingDAO {

	public int registerItemListing(ItemListing itemListing) throws ClassNotFoundException {
		String INSERT_ITEMLISTING_SQL = "INSERT INTO `item_listing`(`itemId`,`sellerId`,`initial_price`,"
				+ "`bid_increment`,`min_bid_price`,`closing_timestamp`,current_status)VALUES(?,?,?,?,?,?,'I');";

		int result = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMLISTING_SQL)) {
			System.out.println("iTem ID from itemListingDAO: " + itemListing.getItemId());
			preparedStatement.setInt(1, itemListing.getItemId());
			preparedStatement.setFloat(2, itemListing.getSellerId()); // PII PENDING
			preparedStatement.setFloat(3, itemListing.getInitialPrice());
			preparedStatement.setFloat(4, itemListing.getBidIncrement()); // PII PENDING
			preparedStatement.setFloat(5, itemListing.getMinBidPrice());
			preparedStatement.setTimestamp(6, itemListing.getClosingDateTime()); // PII PENDING

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
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

	public List<ItemListing> getItemListingOnBid() throws ClassNotFoundException {
		List<ItemListing> list = new ArrayList<ItemListing>();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn()) {
			String querry = "select a.*, b.name as \"itemName\", c.name as \"SellerName\" from item_listing a, catalog_master b, users__ c where a.itemId = b.itemId and a.sellerId=c.userid;";
			PreparedStatement ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemListing obj_ItemListing = new ItemListing();
				obj_ItemListing.setListingId(rs.getLong("listingId"));
				obj_ItemListing.setItemId(rs.getInt("itemId"));
				obj_ItemListing.setSellerId(rs.getLong("sellerId"));
				obj_ItemListing.setInitialPrice(rs.getFloat("initial_price"));
				obj_ItemListing.setBidIncrement(rs.getFloat("bid_increment"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setCreatedOn(rs.getTimestamp("createdOn"));
				obj_ItemListing.setClosingDateTime(rs.getTimestamp("closing_timestamp"));
				obj_ItemListing.setCurrentStatus(rs.getString("current_status"));
				obj_ItemListing.setBestBidId(rs.getInt("best_bidId"));
				obj_ItemListing.setCurrentBestBidAmount(rs.getFloat("current_best_bid_amount"));
				obj_ItemListing.setCreatedBy(rs.getString("Created_by"));
				obj_ItemListing.setRemarks(rs.getString("remarks"));
				obj_ItemListing.setUpdatedBy(rs.getString("updated_by"));
				obj_ItemListing.setUpdatedOn(rs.getTimestamp("updated_on"));
				obj_ItemListing.setItemName(rs.getString("itemName"));
				obj_ItemListing.setSellerName(rs.getString("SellerName"));

				list.add(obj_ItemListing);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public List<ItemListing> getAuctionOnFilter() throws ClassNotFoundException {
		List<ItemListing> list = new ArrayList<ItemListing>();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn()) {
			String querry = "select a.*, b.name as \"itemName\", c.name as \"SellerName\" from item_listing a, catalog_master b, users__ c where a.itemId = b.itemId and a.sellerId=c.userid;";
			PreparedStatement ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemListing obj_ItemListing = new ItemListing();
				obj_ItemListing.setListingId(rs.getLong("listingId"));
				obj_ItemListing.setItemId(rs.getInt("itemId"));
				obj_ItemListing.setSellerId(rs.getLong("sellerId"));
				obj_ItemListing.setInitialPrice(rs.getFloat("initial_price"));
				obj_ItemListing.setBidIncrement(rs.getFloat("bid_increment"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setCreatedOn(rs.getTimestamp("createdOn"));
				obj_ItemListing.setClosingDateTime(rs.getTimestamp("closing_timestamp"));
				obj_ItemListing.setCurrentStatus(rs.getString("current_status"));
				obj_ItemListing.setBestBidId(rs.getInt("best_bidId"));
				obj_ItemListing.setCurrentBestBidAmount(rs.getFloat("current_best_bid_amount"));
				obj_ItemListing.setCreatedBy(rs.getString("Created_by"));
				obj_ItemListing.setRemarks(rs.getString("remarks"));
				obj_ItemListing.setUpdatedBy(rs.getString("updated_by"));
				obj_ItemListing.setUpdatedOn(rs.getTimestamp("updated_on"));
				obj_ItemListing.setItemName(rs.getString("itemName"));
				obj_ItemListing.setSellerName(rs.getString("SellerName"));

				list.add(obj_ItemListing);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public ItemListing getItemListingAttributes(Long listingId) throws ClassNotFoundException {

//    	String SELECT_ALL_ITEMLISTING = "SELECT * FROM ITEMLISTING WHERE ListingId = ?";
		String SELECT_ALL_ITEMLISTING = "select a.*, b.name as itemName, c.name as SellerName from item_listing a, catalog_master b, users__ c where a.itemId = b.itemId and a.sellerId=c.userid and ListingId = ?;";

		ItemListing obj_ItemListing = new ItemListing();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMLISTING)) {
			preparedStatement.setLong(1, listingId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Executed successfully!");
			rs.next();
			System.out.println(rs.getLong("listingId"));

			obj_ItemListing.setListingId(rs.getLong("listingId"));
			obj_ItemListing.setItemId(rs.getInt("itemId"));
			obj_ItemListing.setSellerId(rs.getLong("sellerId"));
			obj_ItemListing.setInitialPrice(rs.getFloat("initial_price"));
			obj_ItemListing.setBidIncrement(rs.getFloat("bid_increment"));
			obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
			obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
			obj_ItemListing.setCreatedOn(rs.getTimestamp("createdOn"));
			obj_ItemListing.setClosingDateTime(rs.getTimestamp("closing_timestamp"));
			obj_ItemListing.setCurrentStatus(rs.getString("current_status"));
			obj_ItemListing.setBestBidId(rs.getInt("best_bidId"));
			obj_ItemListing.setCurrentBestBidAmount(rs.getFloat("current_best_bid_amount"));
			obj_ItemListing.setCreatedBy(rs.getString("Created_by"));
			obj_ItemListing.setRemarks(rs.getString("remarks"));
			obj_ItemListing.setUpdatedBy(rs.getString("updated_by"));
			obj_ItemListing.setUpdatedOn(rs.getTimestamp("updated_on"));
			obj_ItemListing.setItemName(rs.getString("itemName"));
			obj_ItemListing.setSellerName(rs.getString("SellerName"));

		} catch (SQLException e) {
			printSQLException(e);
		}
		return obj_ItemListing;
	}

	public List<Integer> rangeInitialPrice() throws ClassNotFoundException {

		String SELECT_MIN_INITIALPRICE = "select min(initial_price) from item_listing;";
		String SELECT_MAX_INITIALPRICE = "select max(initial_price) from item_listing;";
		List<Integer> results = new ArrayList<Integer>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MIN_INITIALPRICE)) {
			System.out.println("Find Min Initial_price from item_Listing : " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Executed successfully!");
			rs.next();
			results.add(rs.getInt(1));
			rs.close();

		} catch (SQLException e) {
			printSQLException(e);
		}

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_INITIALPRICE)) {
			System.out.println("Find Max Initial_price from item_Listing : " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Executed successfully!");
			rs.next();
			results.add(rs.getInt(1));
			rs.close();

		} catch (SQLException e) {
			printSQLException(e);
		}

		return results;
	}

//To get distinct categories present in the item_listing table
	public List<String> getUniqueAttribute(String attr) {
		List<String> listOfUniqueAttr = new ArrayList<String>();
		String SELECT_UNIQUE_ATTR_AUCTION = "SELECT DISTINCT(" + attr + ") from item_listing;";

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNIQUE_ATTR_AUCTION)) {
			System.out.println("Unique catalog_master: " + SELECT_UNIQUE_ATTR_AUCTION);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				if (rs.getString(1) != null) {
					listOfUniqueAttr.add(rs.getString(1));
				}
			}
			rs.close();

		} catch (SQLException e) {
			printSQLException(e);
		}
		return listOfUniqueAttr;
	}

	public List<ItemListingReport> getItemListingAttributesOnFilter(String category, String cpu, String gpu, String ram,
			String storage, String os, String screenSize, String screenType, String screenRes, String frontCam,
			String rearCam, String sellerName, String itemNameF) throws ClassNotFoundException {

		List<ItemListingReport> listOfFilteredOutput = new ArrayList<ItemListingReport>();
//	String SELECT_ALL_ITEMLISTING = "SELECT * FROM ITEMLISTING WHERE ListingId = ?";
//	String SELECT_ALL_ITEMLISTING ="select a.*, b.name as itemName, c.name as SellerName from item_listing a, catalog_master b, users__ c where a.itemId = b.itemId and a.sellerId=c.userid and ListingId = ?;";
		String querry = "select a.*, b.*, c.name as SellerName from item_listing a, catalog_master b, users__ c where a.itemId = b.itemId and a.sellerId=c.userid and  a.current_status!='X' ";
		if (category != null && !category.isEmpty()) {
			querry += "AND category LIKE '%" + category + "%' ";
		}

		if (cpu != null && !cpu.isEmpty()) {
			querry += "AND cpu LIKE '%" + cpu + "%' ";
		}

		if (gpu != null && !gpu.isEmpty()) {
			querry += "AND gpu LIKE '%" + gpu + "%' ";
		}

		if (ram != null && !ram.isEmpty()) {
			querry += "AND ram LIKE '%" + ram + "%' ";
		}

		if (storage != null && !storage.isEmpty()) {
			querry += "AND storage LIKE '%" + storage + "%' ";
		}

		if (os != null && !os.isEmpty()) {
			querry += "AND operating_system LIKE '%" + os + "%' ";
		}

		if (screenSize != null && !screenSize.isEmpty()) {
			querry += "AND screen_size LIKE '%" + screenSize + "%' ";
		}

		if (screenType != null && !screenType.isEmpty()) {
			querry += "AND screen_type LIKE '%" + screenType + "%' ";
		}

		if (screenRes != null && !screenRes.isEmpty()) {
			querry += "AND screen_resolution LIKE '%" + screenRes + "%' ";
		}

		if (frontCam != null && !frontCam.isEmpty()) {
			querry += "AND front_camera LIKE '%" + frontCam + "%' ";
		}

		if (rearCam != null && !rearCam.isEmpty()) {
			querry += "AND rear_camera LIKE '%" + rearCam + "%' ";
		}

		if (sellerName != null && !sellerName.isEmpty()) {
			querry += "AND c.name  LIKE '%" + sellerName + "%' ";
		}

		if (itemNameF != null && !itemNameF.isEmpty()) {
			querry += "AND b.name LIKE '%" + itemNameF + "%' ";
		}
		querry += " ORDER BY a.createdOn DESC ";
		
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(querry)) {

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Executed successfully!");
			while (rs.next()) {
				ItemListingReport obj_ItemListing = new ItemListingReport();
				obj_ItemListing.setListingId(rs.getLong("listingId"));
				obj_ItemListing.setItemId(rs.getInt("itemId"));
				obj_ItemListing.setSellerId(rs.getLong("sellerId"));
				obj_ItemListing.setInitialPrice(rs.getFloat("initial_price"));
				obj_ItemListing.setBidIncrement(rs.getFloat("bid_increment"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setMinBidPrice(rs.getFloat("min_bid_price"));
				obj_ItemListing.setCreatedOn(rs.getTimestamp("createdOn"));
				obj_ItemListing.setClosingDateTime(rs.getTimestamp("closing_timestamp"));
				obj_ItemListing.setCurrentStatus(rs.getString("current_status"));
				obj_ItemListing.setBestBidId(rs.getInt("best_bidId"));
				obj_ItemListing.setCurrentBestBidAmount(rs.getFloat("current_best_bid_amount"));
				obj_ItemListing.setCreatedBy(rs.getString("Created_by"));
				obj_ItemListing.setRemarks(rs.getString("remarks"));
				obj_ItemListing.setUpdatedBy(rs.getString("updated_by"));
				obj_ItemListing.setUpdatedOn(rs.getTimestamp("updated_on"));
				obj_ItemListing.setItemName(rs.getString("name"));
				obj_ItemListing.setSellerName(rs.getString("SellerName"));

				obj_ItemListing.setItemName(rs.getString("name"));
				obj_ItemListing.setItemDescription(rs.getString("description"));
				obj_ItemListing.setCategory(rs.getString("category"));
				obj_ItemListing.setCpu(rs.getString("cpu"));
				obj_ItemListing.setGpu(rs.getString("gpu"));
				obj_ItemListing.setRam(rs.getString("ram"));
				obj_ItemListing.setStorage(rs.getString("storage"));
				obj_ItemListing.setOperatingSystem(rs.getString("operating_system"));
				obj_ItemListing.setScreenSize(rs.getString("screen_size"));
				obj_ItemListing.setScreenType(rs.getString("screen_type"));
				obj_ItemListing.setScreenResolution(rs.getString("screen_resolution"));
				obj_ItemListing.setFrontCamera(rs.getString("front_camera"));
				obj_ItemListing.setRearCamera(rs.getString("rear_camera"));
				obj_ItemListing.setListingStatus(rs.getString("listing_Status"));
				obj_ItemListing.setApprovalStatus(rs.getString("approval_Status"));

				String allValues = rs.getString("description") + ", ";
				allValues += rs.getString("category") + ", ";
				allValues += rs.getString("cpu") + ", ";
				allValues += rs.getString("gpu") + ", ";
				allValues += rs.getString("ram") + ", ";
				allValues += rs.getString("storage") + ", ";
				allValues += rs.getString("operating_system") + ", ";
				allValues += rs.getString("screen_size") + ", ";
				allValues += rs.getString("screen_type") + ", ";
				allValues += rs.getString("screen_resolution") + ", ";
				allValues += rs.getString("front_camera") + ", ";
				allValues += rs.getString("rear_camera");
				obj_ItemListing.setItemSummary(allValues);

				try {
					if (rs.getBinaryStream("itemImage") != null) {
						byte[] bytes = rs.getBinaryStream("itemImage").readAllBytes();
						String base64 = Base64.getEncoder().encodeToString(bytes);
						obj_ItemListing.setAddInfo1(base64);
					}
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}

				listOfFilteredOutput.add(obj_ItemListing);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println("Value in obj_ItemListing of getItemListingAttributesOnFilter: " + listOfFilteredOutput);
		return listOfFilteredOutput;
	}
	
	
	public Integer deleteRequestToItem_Listing(Long listingId) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try(Connection connection = dbGetConnection.dbGetConn()) {
			String querry="UPDATE item_listing SET current_status = ? where listingId = ?";
			PreparedStatement ps = connection.prepareStatement(querry);
			ps.setString(1, "DI");
			ps.setLong(2, listingId);
			System.out.println(querry);
			result = ps.executeUpdate();	
			
    	}
		catch (Exception e) {
	    	System.out.println(e);
	    }

		return result;
    }

}