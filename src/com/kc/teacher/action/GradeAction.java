package com.kc.teacher.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Grade;
import com.kc.factory.DAOFactory;

public class GradeAction extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String type=(String)request.getParameter("type");
		if(type.equals("gradeorigin")){
			request.setAttribute("currentclass", classlist.get(0));
			request.getRequestDispatcher("teacher/releaseGrade.jsp").forward(request, response);
		}
		
		if(type.equals("grade")){
			String currentclass= (String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass);
			request.getRequestDispatcher("teacher/releaseGrade.jsp").forward(request, response);
		}
		
		if(type.equals("listorigin")){
			
			
			try {
				String sql1="select kcid,gname from grade";
				List test=DAOFactory.getGradeDAOInstance().searchTestName(sql1);
				 System.out.println("GradeAction中 test ： "+test);
				 if(test.size()>0){
				HashMap m=(HashMap)test.get(0);
				String currentclass=""+m.get("kcid");
				
				String sql="select * from grade where gname='"+m.get("gname")+"' and kcid='"+m.get("kcid")+"'";
				List<Grade> gradelist=DAOFactory.getGradeDAOInstance().getAllGrade(sql);
				 System.out.println("GradeAction中gradelist： "+gradelist);
				request.setAttribute("gradelist", gradelist);
				String gname=""+m.get("gname");
				System.out.println("GradeAction中gradename： "+gname);
				
				request.setAttribute("currentgname", gname);
				request.setAttribute("currentclass", currentclass);
				}else{
					request.setAttribute("currentclass", classlist.get(0));
					request.setAttribute("currentgname", "");
					request.setAttribute("gradelist",null);
				}
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/gradeAnalysis.jsp").forward(request, response);
		}
		
		if(type.equals("list")){
			String kc= (String)request.getParameter("kc");
			String currentclass=kc.substring(0,kc.indexOf("."));
			String gname=kc.substring(kc.lastIndexOf(".")+1);
			
			request.setAttribute("currentclass", currentclass);
			request.setAttribute("currentgname", gname);	
			try {
				String sql="select * from grade where kcid='"+currentclass+"' and gname='"+gname+"'";
				List<Grade> gradelist=DAOFactory.getGradeDAOInstance().getAllGrade(sql);
				request.setAttribute("gradelist", gradelist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/gradeAnalysis.jsp").forward(request, response);
		}
		
	}

	
}
