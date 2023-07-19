package com.usersManagement.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.database.pkg.FAQDAO;
import com.usersbean.pkg.FAQ;

@WebServlet("/contactCustomerRepresentative")
public class ContactCustomerRepresentative  extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String question = request.getParameter("question");
        
        FAQ obj_faq = new FAQ();
        FAQDAO obj_faqDAO = new FAQDAO();
        
        obj_faq.setUserId(userId);
        obj_faq.setQuestion(question);
        
        try {
        	obj_faqDAO.registerFAQ(obj_faq);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.sendRedirect("FAQ.jsp");
    }
    
}