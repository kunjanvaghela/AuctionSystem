package com.itemManagement.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itembean.pkg.catalogMaster;
//import com.users.database.pkg.ItemListingDAO;
//import com.usersbean.pkg.ItemListing;
import com.items.database.pkg.itemDAO;


@WebServlet("/filterCall")
public class FilterCall extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FilterCall() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		itemDAO obj_itemDAO=new itemDAO();
		List<catalogMaster> list = new ArrayList<catalogMaster>();
		
		
		String categoryF = request.getParameter("category");
//		String cpuF = request.getParameter("cpu");
//		String gpuF  = request.getParameter("gpu");
//		String ramF  = request.getParameter("ram");
//		String storageF  = request.getParameter("storage");
//		String osF  = request.getParameter("os");
//		String screenSizeF  = request.getParameter("screenSize");
//		String screenTypeF  = request.getParameter("screenType");
//		String screenResF  = request.getParameter("screenRes");
//		String frontCamF  = request.getParameter("frontCam");
//		String rearCamF  = request.getParameter("rearCam");
//        System.out.println("categoryFilter = " + categoryFilter);
		if (categoryF ==null || categoryF == "") {
			System.out.println("category filter null. Value : "+categoryF);
		}
		else {
			System.out.println("category filter not null. Value : "+categoryF);
		}
//		try {
//			list = obj_itemDAO.getFilterResult(categoryF, cpuF, gpuF, ramF, storageF, osF, screenSizeF, screenTypeF, screenResF, frontCamF, rearCamF);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		listItemServlet obj_listItemServlet=new listItemServlet();
		try {
			list = obj_listItemServlet.get_values();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println(list);
		request.setAttribute("itemLists", list);
		request.setAttribute("someString", "SomeStrinnggg");
		request.getRequestDispatcher("SellerPosting.jsp").forward(request, response);
	}
	
	
	// 1: Category, 2: CPU, 3: GPU, 4: RAM, 5: Storage, 6: Operating System, 7: Screen Size, 8: Screen Type, 9: Screen Resolution, 10: Front Camera, 11: Rear Camera
	protected List<String> getUniqueItemAttribute(Integer val) {
		List<String> listItemAttribute = new ArrayList<String>();
		itemDAO obj_itemDAO = new itemDAO();
		
		if (val == 1) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("category");
		}
		else if (val == 2) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("cpu");
		}
		else if (val == 3) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("gpu");
		}
		else if (val == 4) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("ram");
		}
		else if (val == 5) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("storage");
		}
		else if (val == 6) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("operating_system");
		}
		else if (val == 7) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("screen_size");
		}
		else if (val == 8) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("screen_type");
		}
		else if (val == 9) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("screen_resolution");
		}
		else if (val == 10) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("front_camera");
		}
		else if (val == 11) {
			listItemAttribute = obj_itemDAO.getUniqueAttribute("rear_camera");
		}
		return listItemAttribute;
	}

}