package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class EditReply extends HttpServlet {

	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				//PrintWriter out=response.getWriter();
				int replyid =Integer.parseInt(request.getParameter("replyid"));
				int postid =Integer.parseInt(request.getParameter("postid"));
				 try {
						DAOFactory.getForumPostDAOInstance().deleteReply(replyid);
						System.out.println("db delete my reply successfully");
						DAOFactory.getForumPostDAOInstance().addReplycount(postid, -1);
						request.getRequestDispatcher("student/myreply.jsp").forward(request, response); 	 
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
