package com.biddingHistory.pkg;

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
import com.superPOJO.pkg.SuperPojo;

@WebServlet("/biddingHistory")
public class BiddingHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private itemDAO itemDao;
	private BidDAO bidDao;

	public void init() {
		// itemDao = new itemDAO();
		bidDao = new BidDAO();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("bidHistory.jsp").include(request, response);
		HttpSession session = request.getSession();
		SuperPojo bidPageFilter=new SuperPojo();
		bidPageFilter.setSellerName(request.getParameter("sellerName"));
		bidPageFilter.setBuyerName(request.getParameter("buyerName"));
		bidPageFilter.setItemName(request.getParameter("itemName"));
		bidPageFilter.setPastMonths(request.getParameter("pastMonths"));
		bidPageFilter.setBidStatus(request.getParameter("bidStatus"));
		bidPageFilter.setCategory(request.getParameter("category"));
		
		List<SuperPojo> superPojoListBidDashboard;
		
		try {
			superPojoListBidDashboard = bidDao.getAllBids(bidPageFilter);

			request.setAttribute("bidList", superPojoListBidDashboard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("bidHistory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
