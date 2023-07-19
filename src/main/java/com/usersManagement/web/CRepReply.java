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

@WebServlet("/cRepReply")
public class CRepReply  extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String answer = request.getParameter("answer");
        Long faqId = (long) Integer.parseInt(request.getParameter("faqId"));
        
//        FAQ obj_faq = new FAQ();
        FAQDAO obj_faqDAO = new FAQDAO();
        
//        obj_faq.setFaqId(userId);
//        obj_faq.setAnswer(question);
        
        try {
        	obj_faqDAO.postAnswer(faqId, answer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.getRequestDispatcher("cRPortalServlet").forward(request, response);
    }
    
}