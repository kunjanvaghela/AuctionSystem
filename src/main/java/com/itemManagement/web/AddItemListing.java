package com.itemManagement.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.database.pkg.ItemListingDAO;
import com.usersbean.pkg.ItemListing;

@WebServlet("/addItemListing")
public class AddItemListing  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemListing itemListing;

    
    public void init() {
    	itemListing = new ItemListing();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	System.out.println(session.getAttribute("userId"));
    	System.out.println(request.getParameter("selectedItemId"));
//        Integer itemId = 1;	// Check how to get
        Integer itemId = Integer.parseInt(request.getParameter("selectedItemId"));
        System.out.println("Item ID from AddItemListing.java: "+itemId);
        Long sellerId = (Long) session.getAttribute("userId");	// Check how to get from Session
        Float initialPrice = Float.valueOf(request.getParameter("initialPrice"));
        Float bidIncrement = Float.valueOf(request.getParameter("bidIncrement"));
        Float minBidPrice = Float.valueOf(request.getParameter("minBidPrice"));
        String datevalue = (String)(request.getParameter("closingtimestamp")).replace('T', ' ');
        datevalue = datevalue + ":00";
        System.out.println((request.getParameter("closingtimestamp")).replace('T', ' '));
        System.out.println(datevalue);
//        Timestamp closingtimestamp = Timestamp.valueOf((request.getParameter("closingtimestamp")).replace('T', ' '));
        Timestamp closingtimestamp = Timestamp.valueOf(datevalue);
        ItemListingDAO itemListingDAO = new ItemListingDAO();
        
        itemListing.setItemId(itemId);
        itemListing.setSellerId(sellerId);
        itemListing.setInitialPrice(initialPrice);
        itemListing.setBidIncrement(bidIncrement);
        itemListing.setMinBidPrice(minBidPrice);
        itemListing.setClosingDateTime(closingtimestamp);
        
        try {
        	PrintWriter out = response.getWriter();
        	itemListingDAO.registerItemListing(itemListing);
//        	out.println("<meta http-equiv='refresh' content='3;URL=index.jsp'>");//redirects after 3 seconds
//    	    out.println("<p style='color:red;'>Listing added successfully!</p>");
    	    out.println("<script type=\"text/javascript\">");
    	    out.println("alert('Item Added successfully for bidding!');");
    	    out.println("location='EndUserPortal.jsp';");
    	    out.println("</script>");
//        	response.sendRedirect("SellerPosting.jsp");
//    	    RequestDispatcher rd = request.getRequestDispatcher("sellerPostingPageCall");
//    	    rd.forward(request,response);
//    	    response.sendRedirect("/dashboard");
    	    request.getRequestDispatcher("/dashboard").forward(request, response);
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
}