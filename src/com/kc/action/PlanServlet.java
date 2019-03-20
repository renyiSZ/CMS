package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class PlanServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String type=(String)request.getParameter("type");
		if(type.equals("writeorigin")){
			request.setAttribute("currentclass", classlist.get(0));
			String sql="select * from kcdesc where kcid='"+ classlist.get(0)+"'";
			try {
				List planList=DAOFactory.getClassInfoDAOInstance().getClass(sql);
				request.setAttribute("planList", planList);
				request.getRequestDispatcher("teacher/plan.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("write")){
			String kc=(String)request.getParameter("kc");
			request.setAttribute("currentclass", kc);
			String sql="select * from kcdesc where kcid='"+kc+"'";
			try {
				List planList=DAOFactory.getClassInfoDAOInstance().getClass(sql);
				request.setAttribute("planList", planList);
				request.getRequestDispatcher("teacher/plan.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type.equals("edit")){
			String kc=(String)request.getParameter("kc");
			String plan=(String)request.getParameter("plan");
			try {
				DAOFactory.getClassInfoDAOInstance().editClass(kc, plan);
				request.getRequestDispatcher("PlanServlet?type=writeorigin").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("readorigin")){
			request.setAttribute("currentclass", classlist.get(0));
			String sql="select * from kcdesc where kcid='"+ classlist.get(0)+"'";
			try {
				List planList=DAOFactory.getClassInfoDAOInstance().getClass(sql);
				request.setAttribute("planList", planList);
				request.getRequestDispatcher("student/plan.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("read")){
			String kc=(String)request.getParameter("kc");
			request.setAttribute("currentclass", kc);
			String sql="select * from kcdesc where kcid='"+kc+"'";
			try {
				List planList=DAOFactory.getClassInfoDAOInstance().getClass(sql);
				request.setAttribute("planList", planList);
				request.getRequestDispatcher("student/plan.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
