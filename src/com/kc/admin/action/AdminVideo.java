package com.kc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class AdminVideo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		}

		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String type=(String) request.getParameter("type");
			if(type.equals("origin")){
				 String sql= "select * from video order by vtime desc";
		  	 		
	  	 		try {
					List l=   DAOFactory.getVideoDAOInstance().listVideo(sql); 
					request.setAttribute("videolist", l);
					request.getRequestDispatcher("admin/editVideo.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			if(type.equals("select")){
				String classid=(String) request.getParameter("classid");
				String sql="";
				if (classid.equals(null)||classid.equals("")){
					sql="select * from video order by vtime desc";
				}
				else{
					sql= "select * from video where kcid='"+classid+"' order by vtime desc"; 
				}
	  	 		try {
					List l=  DAOFactory.getVideoDAOInstance().listVideo(sql);
					request.setAttribute("videolist", l);
					request.getRequestDispatcher("admin/editVideo.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		
		}
	}
