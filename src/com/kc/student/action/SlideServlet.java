package com.kc.student.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class SlideServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sqlstatement;
		String action=(String) request.getParameter("action");
		
		if(action.equals("singleClassSlide")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from material where kcid='"+classid+"' order by mtime desc";
			try {
				List l=  DAOFactory.getMaterialDAOInstance().queryByClassID(sqlstatement);
				request.setAttribute("slidelist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/mySlide.jsp").forward(request, response);
		}
		if(action.equals("allClassSlide")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from material where ";
			for (int i=0;i<classlist.size();i++){
				if(i==0){
					sqlstatement=sqlstatement+" kcid='"+classlist.get(i)+"'";
				}
				else{
					sqlstatement+=" or kcid='"+classlist.get(i)+"'";
				}
			}
			sqlstatement+=" order by mtime desc";
			try {
				List l=  DAOFactory.getMaterialDAOInstance().queryByClassID(sqlstatement);
				request.setAttribute("slidelist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/mySlide.jsp").forward(request, response);
		}
		
		//Video
		
		if(action.equals("singleClassVideo")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from video where kcid='"+classid+"' order by vtime desc";
			try {
				List l=  DAOFactory.getVideoDAOInstance().listVideo(sqlstatement);
				request.setAttribute("videolist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/onlineCourse.jsp").forward(request, response);
		}
		if(action.equals("allClassVideo")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from video where ";
			for (int i=0;i<classlist.size();i++){
				if(i==0){
					sqlstatement=sqlstatement+" kcid='"+classlist.get(i)+"'";
				}
				else{
					sqlstatement+=" or kcid='"+classlist.get(i)+"'";
				}
			}
			sqlstatement+=" order by vtime desc";
			try {
				List l=  DAOFactory.getVideoDAOInstance().listVideo(sqlstatement);
				request.setAttribute("videolist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/onlineCourse.jsp").forward(request, response);
		}
	}
}


