package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class EditPost extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//PrintWriter out=response.getWriter();
		int postid =Integer.parseInt( request.getParameter("postid"));
		String editWhat = request.getParameter("editWhat"); 
		
		 try {
			 if(editWhat.equals("delete"))
			 {	
				DAOFactory.getForumPostDAOInstance().delPost(postid);
				DAOFactory.getForumPostDAOInstance().delReply(postid);
				System.out.println("db delete post and reply successfully");
				request.getRequestDispatcher("student/mypost.jsp").forward(request, response);
					 
			 }
			if(editWhat.equals("add")){
				request.getRequestDispatcher("/PostDetailServlet?detailWhat=me&&time=one&postid="+postid).forward(request, response);
				
			}
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	
}
