package com.itemManagement.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.itembean.pkg.catalogMaster;
import com.items.database.pkg.itemDAO;
import com.users.database.pkg.UserDAO;
import com.usersbean.pkg.User;

@WebServlet("/addItem")
@MultipartConfig(maxFileSize = 16177215)
public class itemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private itemDAO itemDAO;

	public void init() {
		itemDAO = new itemDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print("in /addItem");
		System.out.print("itemSErvlet");

//    	Item Name: 
//    	Description: 
//    	Category: 
//    	CPU: 
//    	GPU: 
//    	RAM: 
//    	Storage/HDD: 
//    	Operating Systems: 
//    	Screen Size (in inches): 
//    	Screen Type: 
//    	Screen Resolution: 
//    	Front Camera: 
//    	Rear Camera: 
//    	Listing Status: 
//    	Approval Status:

		catalogMaster item = new catalogMaster();

		String itemName = request.getParameter("itemName");
		item.setItemName(itemName);
		String description = request.getParameter("description");
		item.setItemDescription(description);
		String category = request.getParameter("category");
		item.setCategory(category);
		String screenSize = null;
		if(category.equals("Laptop"))
		{screenSize = request.getParameter("screenSizeLaptop");}
		else if(category.equals("Smartphone"))
		{screenSize = request.getParameter("screenSizeSmartphone");}
		else
		{screenSize = request.getParameter("screenSizeTV");}
		String cpu = request.getParameter("cpu");
		item.setCpu(cpu);
		String gpu = request.getParameter("gpu");
		item.setGpu(gpu);
		String ram = request.getParameter("ram");
		item.setRam(ram);
		String storage = request.getParameter("storage");
		item.setStorage(storage);
		String operatingSystem = request.getParameter("operatingSystem");
		item.setOperatingSystem(operatingSystem);

		System.out.println("request.getParameter(\"screenSizeTV\")::::" + request.getParameter("screenSizeTV")
				+ ":::::request.getParameter(\"screenSizeLaptop\")::::" + request.getParameter("screenSizeLaptop")
				+ "::::request.getParameter(\"screenSizeSmartphone\"):::"
				+ request.getParameter("screenSizeSmartphone"));


		/*
		 * if (request.getParameter("screenSizeTV") != null &&
		 * request.getParameter("screenSizeLaptop") == null &&
		 * request.getParameter("screenSizeSmartphone") == null) { screenSize =
		 * request.getParameter("screenSizeTV"); }
		 * 
		 * if (request.getParameter("screenSizeTV") == null &&
		 * request.getParameter("screenSizeLaptop") != null &&
		 * request.getParameter("screenSizeSmartphone") == null) { screenSize =
		 * request.getParameter("screenSizeLaptop"); }
		 * 
		 * if (request.getParameter("screenSizeTV") == null &&
		 * request.getParameter("screenSizeLaptop") == null &&
		 * request.getParameter("screenSizeSmartphone") != null) { screenSize =
		 * request.getParameter("screenSizeSmartphone"); }
		 */ item.setScreenSize(screenSize);

		String screenType = request.getParameter("screenType");
		item.setScreenType(screenType);
		String screenResolution = request.getParameter("screenResolution");
		item.setScreenResolution(screenResolution);
		String frontCamera = request.getParameter("frontCamera");
		if (frontCamera.equals("")) {
			frontCamera = "NA";
		}
		item.setFrontCamera(frontCamera);
		String rearCamera = request.getParameter("rearCamera");
		if (rearCamera.equals("")) {
			rearCamera = "NA";
		}
		item.setRearCamera(rearCamera);
		String listingStatus = request.getParameter("listingStatus");
		item.setListingStatus(listingStatus);
		String approvalStatus = request.getParameter("approvalStatus");
		item.setApprovalStatus(approvalStatus);

		InputStream itemImage = null;
		Part filePart = request.getPart("itemImage");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			itemImage = filePart.getInputStream();
		}

		if (itemImage != null) {
			// fetches input stream of the upload file for the blob column
			item.setItemImage(itemImage);
		}

		HttpSession session = request.getSession();
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		try {
			int rowsUpdated = itemDAO.addItem(item, userId);
			System.out.print("NUMBER OF ROWS INSERTED ADDING ITEM::::" + rowsUpdated);
		    if (filePart != null) {
		        itemImage.close();
		        filePart.delete();
		    }
			if (rowsUpdated != 0) {
				item = null;
				response.getWriter().write("true");
				session.setAttribute("msg2", "true");

			} else {
				session.setAttribute("msg2", "Their is an exception. Please try again! ");
				response.getWriter().write("false");
			}
		} catch (Exception e) {

			e.printStackTrace();
			session.setAttribute("msg2", "Their is an exception. Please try again! ");
			response.getWriter().write("false");

		}finally {
		    if (itemImage != null) {
		        itemImage.close();
		    }
		    if (filePart != null) {
		        filePart.delete();
		    }
	}}
}