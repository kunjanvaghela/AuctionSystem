package com.admin.Reports;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import com.admin.Database.pkg.SalesReportDAO;
import com.itembean.pkg.catalogMaster;

@WebServlet("/salesReport")
public class SalesReport extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN doPostdoPostdoPostdoPostdoPost");
		HttpSession session = request.getSession();
		// boolean totalEarnings = request.getParameter("totalEarnings") != null;

		boolean totalEarnings = "true".equals(request.getParameter("totalEarnings"));
		boolean earningsByItem = "true".equals(request.getParameter("earningsByItem"));
		boolean earningsByType = "true".equals(request.getParameter("earningsByType"));
		boolean earningsByUser = "true".equals(request.getParameter("earningsByUser"));
		boolean bestSellingItems = "true".equals(request.getParameter("bestSellingItems"));
		boolean bestBuyers = "true".equals(request.getParameter("bestBuyers"));

		Long totalEarningsResult = null;
		List<catalogMaster> getearningsByItemList = new ArrayList<catalogMaster>();
		List<catalogMaster> getearningsByCategoryList = new ArrayList<catalogMaster>();
		List<catalogMaster> getearningsByUserList = new ArrayList<catalogMaster>();
		List<catalogMaster> bestSellingItemsList = new ArrayList<catalogMaster>();
		List<catalogMaster> bestBuyersList = new ArrayList<catalogMaster>();
		try { // Create a SalesReportDAO object
			SalesReportDAO salesReportDAO = new SalesReportDAO();

			// List<SalesReport> salesReports = new ArrayList<SalesReport>();

			if (totalEarnings) {
				totalEarningsResult = salesReportDAO.getTotalEarnings();
				if (totalEarningsResult != null) {
					request.setAttribute("totalEarningsResult", (totalEarningsResult));
				} else {
					throw new NullPointerException(" USER DEFINED EXCEPTION ::: Total earnings is null!");
				}
			}
			if (earningsByItem) {
				getearningsByItemList.addAll(salesReportDAO.getEarningsByItem());
				if (!getearningsByItemList.isEmpty()) {
					request.setAttribute("earningsByItem", (getearningsByItemList));
				} else {
					throw new NullPointerException("USER DEFINED EXCEPTION ::: getearningsByItemList is null!");
				}
			}

			if (earningsByType) {
				getearningsByCategoryList.addAll(salesReportDAO.getEarningsByCategory());
				if (!getearningsByCategoryList.isEmpty()) {
					request.setAttribute("earningsByCategory", (getearningsByCategoryList));
				} else {
					throw new NullPointerException("USER DEFINED EXCEPTION ::: getearningsByCategoryList is null!");
				}
			}

			if (earningsByUser) {
				getearningsByUserList.addAll(salesReportDAO.getEarningsByEndUser());
				if (!getearningsByUserList.isEmpty()) {
					request.setAttribute("earningsByUser", (getearningsByUserList));
				} else {
					throw new NullPointerException("USER DEFINED EXCEPTION ::: getearningsByUserList is null!");
				}
			}

			if (bestSellingItems) {
				bestSellingItemsList.addAll(salesReportDAO.getBestSellingItems());
				if (!bestSellingItemsList.isEmpty()) {
					request.setAttribute("bestSellingItems", (bestSellingItemsList));
				} else {
					throw new NullPointerException("USER DEFINED EXCEPTION ::: bestSellingItemsList is null!");
				}
			}

			if (bestBuyers) {
				bestBuyersList.addAll(salesReportDAO.getBestBuyers());
				if (!bestBuyersList.isEmpty()) {
					request.setAttribute("bestBuyers", (bestBuyersList));
				} else {
					throw new NullPointerException("USER DEFINED EXCEPTION ::: bestBuyersList is null!");
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("salesReports.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			session.setAttribute("msg2", "Their is an exception. Please try again! ");
			response.getWriter().write("false");

		}
	}
}
