package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class CorrectServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int correctid=Integer.parseInt(request.getParameter("correctid"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		String comment=request.getParameter("comment");
		//String userid=(String) request.getSession().getAttribute("uid");
		System.out.println("comment:"+comment);
		System.out.println("correctid:"+correctid);
		System.out.println("mark:"+mark);
		String assistant = (String)request.getSession().getAttribute("assistant");
		if(assistant!=null){
		try {
			
			if(DAOFactory.getHomeworkDAOInstance().correct(correctid,mark,comment)==1){
					request.getRequestDispatcher("TeacherHWServlet?type=TAcorrect").forward(request, response);
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		else{
			try {
				
				if(DAOFactory.getHomeworkDAOInstance().correct(correctid,mark,comment)==1){
						request.getRequestDispatcher("TeacherHWServlet?type=correctorigin").forward(request, response);
					
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

}
