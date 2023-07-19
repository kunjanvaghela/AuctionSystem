package com.usersManagement.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.users.database.pkg.FAQDAO;
import com.usersbean.pkg.FAQ;
import com.usersbean.pkg.RequestsForCR;

@WebServlet("/cRPortalServlet")
public class CRPortalServlet  extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	

		// To populate the requests by the end users
    	List<RequestsForCR> listRequestsEndUser= new ArrayList<RequestsForCR>();
    	RequestsRead obj_requestsread=new RequestsRead();
		try {
			listRequestsEndUser=obj_requestsread.get_values();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listRequestsEndUser", listRequestsEndUser);
		
//		Iterator<RequestsForCR> it_list=list.iterator();
		
		// To populate questions asked by endusers
        List<FAQ> listFAQ = new ArrayList<FAQ>();
        FAQDAO obj_faqDAO = new FAQDAO();
        
        try {
			listFAQ = obj_faqDAO.getAllQuestions();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        request.setAttribute("listFAQ", listFAQ);
        System.out.println("listFAQ : "+listFAQ);
		System.out.println("listRequestsEndUser : " + listRequestsEndUser);
		request.getRequestDispatcher("CustomerRepresentativePortal.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doGet(request,response);
    }
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//
//        List<FAQ> listFAQ = new ArrayList<FAQ>();
//        FAQDAO obj_faqDAO = new FAQDAO();
//        
//        try {
//			listFAQ = obj_faqDAO.getAllQuestions();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        
//        request.setAttribute("listFAQ", listFAQ);
//		System.out.println(listFAQ);
//		request.getRequestDispatcher("CustomerRepresentativePortal.jsp").forward(request, response);
//    }
    
}