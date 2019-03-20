package com.kc.student.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Grade;
import com.kc.factory.DAOFactory;

public class StudentGrade extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String uid = (String)request.getSession().getAttribute("uid");
		String type=(String)request.getParameter("type");
		
		if(type.equals("gradeorigin")){
			request.setAttribute("currentclass", classlist.get(0));
			
			try {
				String sql="select * from grade where studentid='"+uid+"' and kcid='"+classlist.get(0)+"'";
				List<Grade> gradelist=DAOFactory.getGradeDAOInstance().getAllGrade(sql);
				request.setAttribute("gradelist", gradelist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/grade.jsp").forward(request, response);
		}
		if(type.equals("grade")){
			String currentclass= (String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass);
			try {
				String sql="select * from grade where studentid='"+uid+"' and kcid='"+currentclass+"'";
				List<Grade> gradelist=DAOFactory.getGradeDAOInstance().getAllGrade(sql);
				request.setAttribute("gradelist", gradelist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/grade.jsp").forward(request, response);
		}
		if(type.equals("showclasschart")){
			String kcid= (String)request.getParameter("kcid");
			String gname= (String)request.getParameter("gname");
			request.setAttribute("kcid", kcid);
			request.setAttribute("gname", gname);
			request.getRequestDispatcher("student/classGrade.jsp").forward(request, response);
		}
		
		
	}

}
