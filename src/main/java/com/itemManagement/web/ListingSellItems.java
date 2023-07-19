package com.itemManagement.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.users.database.pkg.ItemListingDAO;
import com.usersbean.pkg.ItemListing;
import com.usersbean.pkg.ItemListingReport;


@WebServlet("/listingSellItems")
public class ListingSellItems extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ListingSellItems() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// Auction/ItemListing Filter details
		String sellerNameF = request.getParameter("sellerName");
//		Float initialPriceStartRangeF = Float.valueOf(request.getParameter("initialPriceStart"));
//		Float initialPriceEndRangeF = Float.valueOf(request.getParameter("initialPriceEnd"));
//		Float minBidIncrementStartRangeF  = Float.valueOf(request.getParameter("minBidIncrementStartRange"));
//		Float minBidIncrementEndRangeF  = Float.valueOf(request.getParameter("minBidIncrementEndRange"));
//		String bidStartDateTimeFString  = (String)(request.getParameter("bidStartDateTime")).replace('T', ' ');
//		bidStartDateTimeFString = bidStartDateTimeFString + ":00";
//		Timestamp bidStartDateTimeF = Timestamp.valueOf(bidStartDateTimeFString);
//		String bidClosingDateTimeFString  = (String)(request.getParameter("bidClosingDateTime")).replace('T', ' ');
//		bidClosingDateTimeFString = bidClosingDateTimeFString + ":00";
//		Timestamp bidClosingDateTimeF = Timestamp.valueOf(bidClosingDateTimeFString);
		
//		String storageF  = request.getParameter("storage");
//		String osF  = request.getParameter("os");
//		String screenSizeF  = request.getParameter("screenSize");
//		String screenTypeF  = request.getParameter("screenType");
//		String screenResF  = request.getParameter("screenRes");
//		String frontCamF  = request.getParameter("frontCam");
//		String rearCamF  = request.getParameter("rearCam");
		// Item filters
		String itemNameF = request.getParameter("itemName");
		String categoryF = request.getParameter("category");
		String cpuF = request.getParameter("cpu");
		String gpuF  = request.getParameter("gpu");
		String ramF  = request.getParameter("ram");
		String storageF  = request.getParameter("storage");
		String osF  = request.getParameter("os");
		String screenSizeF  = request.getParameter("screenSize");
		String screenTypeF  = request.getParameter("screenType");
		String screenResF  = request.getParameter("screenRes");
		String frontCamF  = request.getParameter("frontCam");
		String rearCamF  = request.getParameter("rearCam");
		
		
		List<ItemListingReport> list_itemListing = new ArrayList<ItemListingReport>();
		ItemListingDAO itemListingDAO = new ItemListingDAO();
		try {
			list_itemListing = itemListingDAO.getItemListingAttributesOnFilter(categoryF, cpuF, gpuF, ramF, storageF, osF, screenSizeF, screenTypeF, screenResF, frontCamF, rearCamF, sellerNameF, itemNameF);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		List<Integer> listRangeInitialPrice = new ArrayList<Integer>();
//		try {
//			listRangeInitialPrice=itemListingDAO.rangeInitialPrice();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      System.out.println(listRangeInitialPrice);
//		request.setAttribute("listRangeInitialPrice", listRangeInitialPrice);
		
		List<String> listUniqueCategory = new ArrayList<String>();
		List<String> listUniqueCPU = new ArrayList<String>();
		List<String> listUniqueGPU = new ArrayList<String>();
		List<String> listUniqueRAM = new ArrayList<String>();
		List<String> listUniqueStorage = new ArrayList<String>();
		List<String> listUniqueOS = new ArrayList<String>();
		List<String> listUniqueScreenSize = new ArrayList<String>();
		List<String> listUniqueScreenType = new ArrayList<String>();
		List<String> listUniqueScreenRes = new ArrayList<String>();
		List<String> listUniqueFrontCam = new ArrayList<String>();
		List<String> listUniqueRearCam = new ArrayList<String>();
		
		SellerPostingPageCall obj_selleerPostingPageCall = new SellerPostingPageCall();
		listUniqueCategory = obj_selleerPostingPageCall.getUniqueItemAttribute(1);
		listUniqueCPU = obj_selleerPostingPageCall.getUniqueItemAttribute(2);
		listUniqueGPU = obj_selleerPostingPageCall.getUniqueItemAttribute(3);
		listUniqueRAM = obj_selleerPostingPageCall.getUniqueItemAttribute(4);
		listUniqueStorage = obj_selleerPostingPageCall.getUniqueItemAttribute(5);;
		listUniqueOS = obj_selleerPostingPageCall.getUniqueItemAttribute(6);;
		listUniqueScreenSize = obj_selleerPostingPageCall.getUniqueItemAttribute(7);
		listUniqueScreenType = obj_selleerPostingPageCall.getUniqueItemAttribute(8);
		listUniqueScreenRes = obj_selleerPostingPageCall.getUniqueItemAttribute(9);
		listUniqueFrontCam = obj_selleerPostingPageCall.getUniqueItemAttribute(10);
		listUniqueRearCam = obj_selleerPostingPageCall.getUniqueItemAttribute(11);
		
		request.setAttribute("listUniqueCategory", listUniqueCategory);
		request.setAttribute("listUniqueCPU", listUniqueCPU);
		request.setAttribute("listUniqueGPU", listUniqueGPU);
		request.setAttribute("listUniqueRAM", listUniqueRAM);
		request.setAttribute("listUniqueStorage", listUniqueStorage);
		request.setAttribute("listUniqueOS", listUniqueOS);
		request.setAttribute("listUniqueScreenSize", listUniqueScreenSize);
		request.setAttribute("listUniqueScreenType", listUniqueScreenType);
		request.setAttribute("listUniqueScreenRes", listUniqueScreenRes);
		request.setAttribute("listUniqueFrontCam", listUniqueFrontCam);
		request.setAttribute("listUniqueRearCam", listUniqueRearCam);

	    System.out.println("Value in obj_ItemListing of ListingSellItems Servlet: " + list_itemListing);
		request.setAttribute("itemListing", list_itemListing);
		request.getRequestDispatcher("BuyerBidding.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}