package com.usersManagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.users.database.pkg.RequestsForCRDAO;
import com.users.database.pkg.UserDAO;
import com.usersbean.pkg.RequestsForCR;

@WebServlet("/CRRequestApproval")
public class CRRequestApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CRRequestApproval() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the values of the checked checkboxes
        String[] approveIds = request.getParameterValues("approve[]");
        String[] denyIds = request.getParameterValues("deny[]");
        RequestsForCRDAO requestsForCRDAO = new RequestsForCRDAO();
        int result;
//        String[] approveIds = request.getParameter("approve[]");
//        String[] denyIds = request.getParameter("deny[]");

        // Check if the approveIds array is not null and has at least one value
        if (approveIds != null && approveIds.length > 0) {
            for (String id : approveIds) {
                // Perform the approve action for the checkbox with the given id
                // ...
            	System.out.println("approveIds : "+id);
            	RequestsForCR requestsForCR=new RequestsForCR();
				try {
					requestsForCR = requestsForCRDAO.getRequestIDData(Integer.parseInt(id));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("getRequestType : "+requestsForCR.getRequestType());
				if (requestsForCR.getRequestType().equals("Password Change")) {
				  // Code for changing the password
					  UserDAO userDAO = new UserDAO();
					  try {
						result = userDAO.changePasswordApproved(requestsForCR.getUserId());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
				  else if (requestsForCR.getRequestType().equals("Remove Bid")) {
					  // Code for removing the bid based on the bid id.
					  try {
						requestsForCRDAO.removeBidFromBids(requestsForCR);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				  }
				  else if (requestsForCR.getRequestType().equals("Remove Auction")) {
					  // Code for removing the bid based on the bid id.
					  try {
						  System.out.println("Removing Auction "+requestsForCR.getListingId());
						requestsForCRDAO.removeAuctionFromItem_Listing(requestsForCR);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				  }
				  try {
					requestsForCRDAO.updateRequestIdStatus(Integer.parseInt(id), "A");
				} catch (NumberFormatException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				
            }
        }

        // Check if the denyIds array is not null and has at least one value
        if (denyIds != null && denyIds.length > 0) {
            for (String id : denyIds) {
                // Perform the deny action for the checkbox with the given id
                // ...
            	System.out.println("Deny ID : "+id);
            	try {
  				  
//  				  result = requestsForCRDAO.deleteRequestIDData(requestId);
  				  result = requestsForCRDAO.updateRequestIdStatus(Integer.parseInt(id), "D");
  				  
  				  
  				} catch (ClassNotFoundException e) {
  						// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
            }
        }
        
			  
		request.getRequestDispatcher("cRPortalServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		  Integer requestId = Integer.getInteger(request.getParameter("name"));
//		  List<> requestIds = Integer.getInteger(request.getParameter("name"));
//		  String action = request.getParameter("action");
//		  RequestsForCRDAO requestsForCRDAO = new RequestsForCRDAO();
//		  Integer result = 0;
//		  System.out.println(requestIds + " has action : "+action);
////		  response.getWriter().print("Hello "+ String.valueOf(requestIds) + "!!" + action);
//		  if (action=="Approve") {
//			  try {
//				  RequestsForCR requestsForCR=new RequestsForCR();
//				  requestsForCR = requestsForCRDAO.getRequestIDData(requestIds);
//				  
//				  if (requestsForCR.getRequestType() == "Password Change") {
//					  // Code for changing the password
//					  UserDAO userDAO = new UserDAO();
//					  result = userDAO.changePasswordApproved(requestsForCR.getUserId());
//				  }
//				  else if (requestsForCR.getRequestType() == "Remove Bid") {
//					  // Code for removing the bid based on the bid id.
//				  }
//				  requestsForCRDAO.updateRequestIdStatus(requestIds, "A");
//				  
//				} catch (ClassNotFoundException e) {
//						// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		  }
//		  else {
//			  try {
//				  
////				  result = requestsForCRDAO.deleteRequestIDData(requestId);
//				  result = requestsForCRDAO.updateRequestIdStatus(requestIds, "D");
//				  
//				  
//				} catch (ClassNotFoundException e) {
//						// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		  }
		
		
		  
	}
}