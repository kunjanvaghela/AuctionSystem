package com.userDashboard.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.database.pkg.BidsDAO;
import com.users.database.pkg.EndUserRequestsDAO;
import com.users.database.pkg.ItemListingDAO;
import com.usersbean.pkg.EndUserRequests;

@WebServlet("/removeAuctionBid")
public class RemoveAuctionBid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String requestType = request.getParameter("requestType");
		
		EndUserRequests endUserRequests = new EndUserRequests();
		EndUserRequestsDAO endUserRequestsDAO = new EndUserRequestsDAO();
		
		endUserRequests.setUserId((Long) session.getAttribute("userId"));
		endUserRequests.setRequestType(requestType);
		endUserRequests.setUpdate_description("By User");
		endUserRequests.setCurrent_status("I");
		
		if (requestType.equals("Remove Bid")) {
			System.out.println("Remove Bid triggered.");
			Long bidId = Long.parseLong(request.getParameter("bidId"));
			endUserRequests.setBidId(bidId);
			endUserRequests.setAuctionId(null);
			request.setAttribute("alertMsg", "A request to delete the Bid has been placed. A Customer Representative will soon assist you!");
		}
		if (requestType.equals("Remove Auction")) {
			System.out.println("Remove Auction triggered.");
			Long auctionId = Long.parseLong(request.getParameter("auctionId"));
			endUserRequests.setBidId(null);
			endUserRequests.setAuctionId(auctionId);
			request.setAttribute("alertMsg", "A request to delete the Auction has been placed. A Customer Representative will soon assist you!");
		}
		if (requestType.equals("Password Change")) {
			endUserRequests.setBidId(null);
			endUserRequests.setAuctionId(null);
		}
		try {
			endUserRequestsDAO.insertUserRequest(endUserRequests);
			if (requestType.equals("Remove Bid")) {
				System.out.println("Will change the status of bids table for bidId = "+endUserRequests.getBidId());
				BidsDAO bidsDAO = new BidsDAO();
				bidsDAO.deleteRequestToBids(endUserRequests.getBidId());
			}
			if (requestType.equals("Remove Auction")) {
				System.out.println("Will change the status of item_listing table for listingId = "+endUserRequests.getAuctionId());
				ItemListingDAO itemListing = new ItemListingDAO();
				itemListing.deleteRequestToItem_Listing(endUserRequests.getAuctionId());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/dashboard").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
