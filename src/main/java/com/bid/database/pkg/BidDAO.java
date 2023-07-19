package com.bid.database.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

//import com.bidbean.pkg.Bid;
import com.superPOJO.pkg.SuperPojo;
import com.utility.pkg.dbGetConnection;

public class BidDAO {
	private Connection connection;

	public BidDAO() {
		connection = dbGetConnection.dbGetConn();
	}

//	----------------
//	public String getPseudoNamefromUserId(Long userId)
//			{
//		
//			}
	public String getPseudoNamefromUserId(Long userId) throws ClassNotFoundException {
		System.out.println("getPseudoNamefromUserId");
		String pseudoName = null;
		String getPseudoNamefromUserId = "SELECT add_info3 as pseudoName from users__ WHERE `userId` = ?;";
//		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();
				PreparedStatement preparedStatement = connection.prepareStatement(getPseudoNamefromUserId)) {
			preparedStatement.setLong(1, userId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if (rs != null) {
				rs.last(); // moves cursor to the last row
				pseudoName = rs.getString("pseudoName");
				return pseudoName;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pseudoName;
	}
	
	public String getBidStatusFromBidId(String bidId) throws ClassNotFoundException {
		System.out.println("getBidStatusFromBidId");
		String bidStatus = null;
		String bidStatusPhrase=null;
		String getBidStatusFromBidId = "select current_status from bids where bidId=?;";
//		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(getBidStatusFromBidId)) {
			preparedStatement.setString(1, bidId);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if (rs != null) {
				rs.last(); // moves cursor to the last row
				bidStatus = rs.getString("current_status");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		if ("W".equalsIgnoreCase(bidStatus)) {
			bidStatusPhrase="Winner";
		} else if ("A".equalsIgnoreCase(bidStatus))

		{
			bidStatusPhrase="Active";
		} else if ("X".equalsIgnoreCase(bidStatus))

		{
			bidStatusPhrase="Expired";
		} else if ("D".equalsIgnoreCase(bidStatus))

		{
			bidStatusPhrase="Deleted by User";
		} else if ("DI".equalsIgnoreCase(bidStatus))

		{
			bidStatusPhrase="Deleted Initiated by User";
		}else {
			bidStatusPhrase="Not Available";
		}
		return bidStatusPhrase;
	}

	public String getUserIdfromPseudoName(String pseudoName) throws ClassNotFoundException {
		System.out.println("getUserIdfromPseudoName");

		String userIdList = null;
		List<String> userId = new ArrayList<>();
		String getPseudoNamefromUserId = "SELECT userId as userId from users__ WHERE `add_info3` LIKE '%" + pseudoName
				+ "%'";

		// '%" + bidPageFilter.getSellerName() + "%'"
//		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = dbGetConnection.dbGetConn();

				PreparedStatement preparedStatement = connection.prepareStatement(getPseudoNamefromUserId)) {
			// preparedStatement.setString(1,pseudoName);

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userId.add(resultSet.getString("userId"));
			}
			if (!userId.isEmpty()) {
				String userIdsList = "("
						+ String.join(",", Arrays.toString(userId.toArray()).split("[\\[\\]]")[1].split(", ")) + ")";
				System.out.println(userIdsList);
				return userIdsList;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return userIdList;
	}

//	-------------------
	public List<SuperPojo> getAllBids(SuperPojo bidPageFilter) throws SQLException, Exception, ClassNotFoundException {
		System.out.println("getAllBids");

		List<SuperPojo> superPojoList = new ArrayList<>();

		StringBuilder getAllBidsbyUserId = new StringBuilder();
		getAllBidsbyUserId.append(
				"SELECT l.listingId as listingId, b.buyerId as buyerId, b.bidId as bidId, l.sellerId as sellerId,  b.buyerId as buyerId, c.itemId as itemId,  u.name as SellerName, c.name AS item_name, c.description AS item_description, c.itemImage as itemImage, b.current_bid  AS current_bid,\r\n"
						+ "       b.highest_bid AS userUpperLimit,l.current_best_bid_amount as highestBidOnItem, l.closing_timestamp AS auction_end_date,\r\n"
						+ "l.current_status AS auction_status , b.current_status as bidStatus \r\n" + "FROM bids b\r\n"
						+ "JOIN endusers e ON b.buyerId = e.userId\r\n"
						+ "JOIN item_listing l ON b.listingId = l.listingId\r\n"
						+ "JOIN catalog_master c ON l.itemId = c.itemId\r\n"
						+ "JOIN users__ u ON  u.userId=l.sellerId\r\n WHERE 1=1");

		if (bidPageFilter.getItemName() != null && bidPageFilter.getItemName() != "") {
			getAllBidsbyUserId.append(" AND c.name LIKE '%" + bidPageFilter.getItemName() + "%'");
		}

		if (bidPageFilter.getSellerName() != null && bidPageFilter.getSellerName() != "") {
			getAllBidsbyUserId.append(" AND u.name LIKE '%" + bidPageFilter.getSellerName() + "%'");
		}

		if (bidPageFilter.getBuyerName() != null && bidPageFilter.getBuyerName() != "") {

			String userIdList = getUserIdfromPseudoName(bidPageFilter.getBuyerName());
			if (!userIdList.isEmpty())
				getAllBidsbyUserId.append(" AND  b.buyerId IN " + userIdList);
		}

		if (bidPageFilter.getCategory() != null && bidPageFilter.getCategory() != "") {

			getAllBidsbyUserId.append(" AND c.category = '" + bidPageFilter.getCategory() + "'");
		}

		/* created_at >= DATE_SUB(NOW(), INTERVAL 3 MONTH */
		if (bidPageFilter.getPastMonths() != null && bidPageFilter.getPastMonths() != "") {
			getAllBidsbyUserId.append(
					" AND b.createdOn >= DATE_SUB(NOW(), INTERVAL " + bidPageFilter.getPastMonths() + " MONTH)");
		}

		if (bidPageFilter.getBidStatus() != null && bidPageFilter.getBidStatus() != "") {
			getAllBidsbyUserId.append(" AND b.current_status ='" + bidPageFilter.getBidStatus() + "'");
		}
		
		getAllBidsbyUserId.append(" ORDER BY b.createdOn desc;");

		PreparedStatement statement = connection.prepareStatement(getAllBidsbyUserId.toString());
		System.out.println(statement);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			SuperPojo superPojo = new SuperPojo();
			superPojo.setListingId(Integer.parseInt(resultSet.getString("listingId")));
			superPojo.setAdd_info3(getPseudoNamefromUserId(Long.parseLong(resultSet.getString("buyerId"))));
			System.out.println(superPojo.getAdd_info3());
			superPojo.setBidId(Integer.parseInt(resultSet.getString("bidId")));
			superPojo.setBuyerId(Integer.parseInt(resultSet.getString("buyerId")));
			superPojo.setSellerId(Long.parseLong(resultSet.getString("sellerId")));
			superPojo.setItemId(Long.parseLong(resultSet.getString("itemId")));
			superPojo.setSellerName((resultSet.getString("SellerName")));
			superPojo.setItemName(resultSet.getString("item_name"));
			superPojo.setItemDescription(resultSet.getString("item_description"));
			System.out.println(":BID STATUS:" + resultSet.getString("bidStatus"));
			if ("W".equalsIgnoreCase(resultSet.getString("bidStatus"))) {
				superPojo.setBidStatus("Winner");
			} else if ("A".equalsIgnoreCase(resultSet.getString("bidStatus")))

			{
				superPojo.setBidStatus("Active");
			} else if ("X".equalsIgnoreCase(resultSet.getString("bidStatus")))

			{
				superPojo.setBidStatus("Expired");
			} else if ("D".equalsIgnoreCase(resultSet.getString("bidStatus")))

			{
				superPojo.setBidStatus("Deleted by User");
			} else if ("DI".equalsIgnoreCase(resultSet.getString("bidStatus")))

			{
				superPojo.setBidStatus("Deleted Initiated by User");
			}else {
				superPojo.setBidStatus("Not Available");
			}

			try {
				if (resultSet.getBinaryStream("itemImage") != null) {
					byte[] bytes = resultSet.getBinaryStream("itemImage").readAllBytes();
					String base64 = Base64.getEncoder().encodeToString(bytes);
					superPojo.setAddInfo1(base64);
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

			superPojo.setCurrentBid(resultSet.getFloat("current_bid")); // Current BID by USERID
			superPojo.setHighestBid(resultSet.getFloat("userUpperLimit")); // UPPER LIMIT OF USER ID
			superPojo.setCurrentBestBidAmount(resultSet.getFloat("highestBidOnItem")); // CURRENT HIGHEST BID ON ITEM
			superPojo.setClosingDateTime(resultSet.getTimestamp("auction_end_date"));
			
			superPojo.setCurrentStatus(resultSet.getString("auction_status"));

			superPojoList.add(superPojo);

		}

		return superPojoList;
	}

	public List<SuperPojo> getAllBidsbyUserId(Long userId) throws SQLException {
		System.out.println("getAllBidsbyUserId");

		// List<Bid> bidList = new ArrayList<>();
		List<SuperPojo> superPojoList = new ArrayList<>();

		String getAllBidsbyUserId = "SELECT l.listingId as listingId, MAX(b.bidId) as bidId, l.sellerId as sellerId,  b.buyerId as buyerId, c.itemId as itemId,  u.name as SellerName, c.name AS item_name, c.description AS item_description, c.itemImage as itemImage, MAX(b.current_bid)  AS current_bid,\r\n"
				+ "       MAX(b.highest_bid ) AS userUpperLimit,MAX(l.current_best_bid_amount) as highestBidOnItem, l.closing_timestamp AS auction_end_date,\r\n"
				+ "l.current_status AS auction_status\r\n " + "FROM bids b\r\n"
				+ "JOIN endusers e ON b.buyerId = e.userId\r\n" + "JOIN item_listing l ON b.listingId = l.listingId\r\n"
				+ "JOIN catalog_master c ON l.itemId = c.itemId\r\n" + "JOIN users__ u ON  u.userId=l.sellerId\r\n"
				+ "WHERE e.userId = ? GROUP BY listingId;";
		PreparedStatement statement = connection.prepareStatement(getAllBidsbyUserId);
		statement.setLong(1, userId);
		System.out.println(statement);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			SuperPojo superPojo = new SuperPojo();
			superPojo.setListingId(Integer.parseInt(resultSet.getString("listingId")));
			superPojo.setBidId(Integer.parseInt(resultSet.getString("bidId")));
			superPojo.setBuyerId(Integer.parseInt(resultSet.getString("buyerId")));
			superPojo.setSellerId(Long.parseLong(resultSet.getString("sellerId")));
			superPojo.setItemId(Long.parseLong(resultSet.getString("itemId")));
			superPojo.setSellerName((resultSet.getString("SellerName")));
			superPojo.setItemName(resultSet.getString("item_name"));
			superPojo.setItemDescription(resultSet.getString("item_description"));

			String bidIdtemp=resultSet.getString("bidId");
			try {
				superPojo.setBidStatus(getBidStatusFromBidId(bidIdtemp));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (resultSet.getBinaryStream("itemImage") != null) {
					byte[] bytes = resultSet.getBinaryStream("itemImage").readAllBytes();
					String base64 = Base64.getEncoder().encodeToString(bytes);
					superPojo.setAddInfo1(base64);
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

			superPojo.setCurrentBid(resultSet.getFloat("current_bid")); // Current BID by USERID
			superPojo.setHighestBid(resultSet.getFloat("userUpperLimit")); // UPPER LIMIT OF USER ID
			superPojo.setCurrentBestBidAmount(resultSet.getFloat("highestBidOnItem")); // CURRENT HIGHEST BID ON ITEM
			superPojo.setClosingDateTime(resultSet.getTimestamp("auction_end_date"));
			superPojo.setCurrentStatus(resultSet.getString("auction_status"));
			superPojoList.add(superPojo);

		}

		return superPojoList;
	}

	public List<SuperPojo> getAllListingbyUserId(Long userId) throws SQLException {
		System.out.println("getAllListingbyUserId");

		// List<Bid> bidList = new ArrayList<>();
		List<SuperPojo> superPojoList = new ArrayList<>();

		String getAllListingbyUserId = "SELECT i.listingId as listingId, i.sellerId as sellerId, c.name AS item_name, c.description AS item_description, c.itemImage as itemImage, \r\n"
				+ "i.initial_price as listing_price, \r\n" + "  i.min_bid_price AS secret_bid_price, \r\n"
				+ "  i.current_best_bid_amount AS highest_bid, \r\n"
				+ "  i.closing_timestamp AS auction_end_date, i.current_status AS auction_status \r\n"
				+ "FROM item_listing i\r\n" + "JOIN catalog_master c ON c.itemId = i.itemId\r\n"
				+ "LEFT JOIN bids b ON b.listingId = i.listingId\r\n" + " WHERE i.sellerId = ?\r\n"
				+ "GROUP BY i.listingId;";

		PreparedStatement statement = connection.prepareStatement(getAllListingbyUserId);
		statement.setLong(1, userId);
		System.out.println(statement);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			SuperPojo superPojo = new SuperPojo();
			superPojo.setListingId(Integer.parseInt(resultSet.getString("listingId")));
			superPojo.setSellerId(Long.parseLong(resultSet.getString("sellerId")));
			superPojo.setItemName(resultSet.getString("item_name"));
			superPojo.setItemDescription(resultSet.getString("item_description"));

			try {
				if (resultSet.getBinaryStream("itemImage") != null) {
					byte[] bytes = resultSet.getBinaryStream("itemImage").readAllBytes();
					String base64 = Base64.getEncoder().encodeToString(bytes);
					superPojo.setAddInfo1(base64);
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

			superPojo.setInitialPrice(resultSet.getFloat("listing_price"));
			superPojo.setMinBidPrice(resultSet.getFloat("secret_bid_price"));
			superPojo.setCurrentBestBidAmount(resultSet.getFloat("highest_bid"));
			superPojo.setClosingDateTime(resultSet.getTimestamp("auction_end_date"));
			
			
//			superPojo.setCurrentStatus(resultSet.getString("auction_status"));
			
			if ("A".equalsIgnoreCase(resultSet.getString("auction_status")))

			{
				superPojo.setCurrentStatus("Active");
			} else if ("X".equalsIgnoreCase(resultSet.getString("auction_status")))

			{
				superPojo.setCurrentStatus("Expired");
			} else if ("D".equalsIgnoreCase(resultSet.getString("auction_status")))

			{
				superPojo.setCurrentStatus("Deleted by User");
			} else if ("DI".equalsIgnoreCase(resultSet.getString("auction_status")))

			{
				superPojo.setCurrentStatus("Deleted Initiated by User");
			}else {
				superPojo.setCurrentStatus("Not Available");
			}
			
			
			superPojoList.add(superPojo);

		}

		return superPojoList;
	}

//MANAN
	public List<SuperPojo> getAllInterestsUserId(Long userId) throws SQLException {
		System.out.println("getAllInterestsUserId");

		List<SuperPojo> superPojoList = new ArrayList<>();

		String getAllInterestsUserId = "SELECT u.userId as sellerId,interests.interestId as interestId,u.name as SellerName, catalog_master.itemId as itemId, catalog_master.name as item_name, catalog_master.description as item_description, catalog_master.itemImage as itemImage, item_listing.createdOn AS auction_start_date, item_listing.closing_timestamp as auction_end_date,item_listing.current_status as auction_status\r\n"
				+ " FROM interests\r\n" + "JOIN catalog_master ON interests.ItemId = catalog_master.itemId\r\n"
				+ "LEFT JOIN item_listing ON interests.ItemId = item_listing.itemId\r\n"
				+ " LEFT JOIN users__ u ON  u.userId=item_listing.sellerId\r\n" + "WHERE interests.userId = ?;";

		PreparedStatement statement = connection.prepareStatement(getAllInterestsUserId);
		statement.setLong(1, userId);
		System.out.println(statement);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			SuperPojo superPojo = new SuperPojo();
			superPojo.setInterestId(Integer.parseInt(resultSet.getString("interestId")));
			
			if(resultSet.getString("sellerId")!=null)
			{superPojo.setSellerId(Long.parseLong(resultSet.getString("sellerId")));}
			
			if(resultSet.getString("sellerName")!=null)
			{superPojo.setSellerName((resultSet.getString("sellerName")));}
			
			superPojo.setItemId(Long.parseLong(resultSet.getString("itemId")));
			superPojo.setItemName(resultSet.getString("item_name"));
			superPojo.setItemDescription(resultSet.getString("item_description"));
			
			if(resultSet.getString("sellerId")!=null)
			{superPojo.setAddInfo4(resultSet.getString("interestId") + resultSet.getString("sellerId"));}
			try {
				if (resultSet.getBinaryStream("itemImage") != null) {
					byte[] bytes = resultSet.getBinaryStream("itemImage").readAllBytes();
					String base64 = Base64.getEncoder().encodeToString(bytes);
					superPojo.setAddInfo1(base64);
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

			superPojo.setCreatedOn(resultSet.getTimestamp("auction_start_date"));
			superPojo.setClosingDateTime(resultSet.getTimestamp("auction_end_date"));
			superPojo.setCurrentStatus(resultSet.getString("auction_status"));
			superPojoList.add(superPojo);

		}

		return superPojoList;
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
