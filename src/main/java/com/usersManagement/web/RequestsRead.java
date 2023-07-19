package com.usersManagement.web;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import com.users.database.pkg.RequestsForCRDAO;
import com.usersbean.pkg.RequestsForCR;

@WebServlet("/requestsread")
public class RequestsRead extends HttpServlet {
    private static final long serialVersionUID = 1L;
//    Class.forName("com.mysql.jdbc.Driver");
//    private UserDAO userDAO;

    public void init() {
//    	userDAO = new UserDAO();
    }
    
    public List<RequestsForCR> get_values() throws ClassNotFoundException {
    	RequestsForCRDAO obj_requestsForCRDAO = new RequestsForCRDAO();
    	List<RequestsForCR> list = new ArrayList<RequestsForCR>();
    	list = obj_requestsForCRDAO.getRequestsForCR();
    	return list;
    }

}