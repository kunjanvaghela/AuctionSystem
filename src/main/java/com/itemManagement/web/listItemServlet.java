package com.itemManagement.web;

import java.util.ArrayList;
import java.util.List;

import com.itembean.pkg.catalogMaster;
import com.items.database.pkg.itemDAO;


public class listItemServlet {
//    private itemDAO itemDAO;

    public void init() {
//    	userDAO = new UserDAO();
    }
    
    public List<catalogMaster> get_values() throws ClassNotFoundException {
    	itemDAO obj_itemDAO = new itemDAO();
    	List<catalogMaster> list = new ArrayList<catalogMaster>();
    	list = obj_itemDAO.getItemsAvailable();
    	return list;
    }
}