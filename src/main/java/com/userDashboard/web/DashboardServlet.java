package com.userDashboard.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bid.database.pkg.BidDAO;
import com.items.database.pkg.itemDAO;
import com.bidbean.pkg.Bid;
import com.itembean.pkg.catalogMaster;
import com.superPOJO.pkg.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private itemDAO itemDao;
	private BidDAO bidDao;

	public void init() {
		// itemDao = new itemDAO();
		bidDao = new BidDAO();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
		request.getRequestDispatcher("dashboard.jsp").include(request, response);
		HttpSession session = request.getSession();
		List<SuperPojo> superPojoListBidDashboard;
		List<SuperPojo> superPojoListSellDashboard;
		List<SuperPojo> superPojoListInterestDashboard;
		// Fetch the required data from the database
		/*
		 * List<catalogMaster> itemList; try { itemList = itemDao.getItemsAvailable();
		 * request.setAttribute("itemList", itemList); } catch (ClassNotFoundException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// List<Bid> bidList;
		try {
			if ((session.getAttribute("userId")) != null) {
				superPojoListBidDashboard = bidDao
						.getAllBidsbyUserId(Long.parseLong(session.getAttribute("userId").toString()));
				superPojoListSellDashboard = bidDao
						.getAllListingbyUserId(Long.parseLong(session.getAttribute("userId").toString()));
				superPojoListInterestDashboard = bidDao
						.getAllInterestsUserId(Long.parseLong(session.getAttribute("userId").toString()));

				request.setAttribute("bidList", superPojoListBidDashboard);
				request.setAttribute("sellList", superPojoListSellDashboard);
				request.setAttribute("interestList", superPojoListInterestDashboard);
			} else {
				throw new NullPointerException("USER DEFINED EXCEPTION ::: (session.getAttribute(\"userId\") is null!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// request.setAttribute("winningBidList", bidDao.getWinningBids());

		// Dispatch to JSP
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
