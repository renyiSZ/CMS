package com.kc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class AdminSlide extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=(String) request.getParameter("type");
		if(type.equals("origin")){
			String sql= "select * from material order by mtime desc"; 
  	 		try {
				List l=  DAOFactory.getMaterialDAOInstance().queryByClassID(sql);
				request.setAttribute("slidelist", l);
				request.getRequestDispatcher("admin/editSlides.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if(type.equals("select")){
			String classid=(String) request.getParameter("classid");
			String sql="";
			if (classid.equals(null)||classid.equals("")){
				sql= "select * from material order by mtime desc"; 
			}
			else{
				sql= "select * from material where kcid='"+classid+"' order by mtime desc"; 
			}
  	 		try {
				List l=  DAOFactory.getMaterialDAOInstance().queryByClassID(sql);
				request.setAttribute("slidelist", l);
				request.getRequestDispatcher("admin/editSlides.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
	}
}
