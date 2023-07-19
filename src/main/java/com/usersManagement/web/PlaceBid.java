package com.usersManagement.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codejava.email.EmailUtility;
import com.users.database.pkg.BidsDAO;
import com.users.database.pkg.ItemListingDAO;
import com.usersbean.pkg.Bids;
import com.usersbean.pkg.ItemListing;

@WebServlet("/placeBid")
public class PlaceBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bids bid;

	public void init() {
		bid = new Bids();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long listingId = Long.valueOf(request.getParameter("itemListingSelectionClass")); // Check how to get
		Long buyerId = (Long) session.getAttribute("userId"); // Check how to get from Session
//        Float lowestBid = Float.valueOf(request.getParameter("initialPrice"));
//        Float highestBid = Float.valueOf(request.getParameter("highestBidPossible"));
		Float lowestBid;
		Float highestBid = (float) -1.0;

		if (request.getParameter("initialPrice") != null) {
			try {
				lowestBid = Float.parseFloat(request.getParameter("initialPrice"));
			} catch (NumberFormatException e) {
				// handle the exception if the parameter is not a valid float
				lowestBid = (float) -1.00;
			}
		} else {
			lowestBid = (float) -1.00;
		}

		if ((lowestBid >= 0) && (request.getParameter("initialPrice") != null)) {
			try {
				if (request.getParameter("highestBidPossible") != null) {
					highestBid = Float.parseFloat(request.getParameter("highestBidPossible"));
				}
				else {highestBid = lowestBid;}
			} catch (NumberFormatException e) {
				// handle the exception if the parameter is not a valid float
				highestBid = lowestBid;
			}
		}

		BidsDAO bidsDAO = new BidsDAO();

		bid.setListingId(listingId);
		bid.setBuyerId(buyerId);
		bid.setLowest_bid(lowestBid);
		bid.setHighest_bid(highestBid);

		try {
			bidsDAO.registerPlacedBid(bid); // This will insert the new Bid, and then will also call the procedure to
											// update Bids and Item_Listing tables with best Bid.

			// Now, to send alerts to the bidders who are losing.

			ItemListingDAO itemListingDAO = new ItemListingDAO();
			ItemListing itemListing = itemListingDAO.getItemListingAttributes(listingId);

			List<Bids> losingBidders = new ArrayList<Bids>();
			System.out.println("Going to call bidsDAO.getListOfLosingBidders(itemListing)");
			losingBidders = bidsDAO.getListOfLosingBidders(itemListing);
			System.out.println("Call completed: bidsDAO.getListOfLosingBidders(itemListing)");

			// Sending Email
			ServletContext context = getServletContext();
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");
			String subject = "You might lose the bid for " + itemListing.getItemName();

			String resultMessage = "";

			try {
				for (Bids loserBidder : losingBidders) {
//                    String recipient = "kunjanvaghela@gmail.com";
					System.out.println("Email sending module in effect");
					String recipient = loserBidder.getBidderEmail();

					String content = "Hi " + loserBidder.getBidderName()
							+ ", \n\n\nA competitor recently bid more than your bid and may win the "
							+ itemListing.getItemName() + ". Bid with higher amount to not lose the bid!";
					EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
				}

				resultMessage = "The e-mail was sent successfully";
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
			} finally {
				request.setAttribute("Message", resultMessage);
//                getServletContext().getRequestDispatcher("/Result.jsp").forward(
//                        request, response);
			}
			request.getRequestDispatcher("/dashboard").forward(request, response);
//			response.sendRedirect("/dashboard");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("loginPage.jsp");
	}

}