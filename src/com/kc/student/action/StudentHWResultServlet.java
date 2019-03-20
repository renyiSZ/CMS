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

public class StudentHWResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sqlstatement;
		String uid=(String) request.getSession().getAttribute("uid");
		String action=(String) request.getParameter("action");
		
		
		if(action.equals("singleClassResult")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.kcid='"+classid+"' and studentid='"+uid+"' and correct=1 order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwResultlist", l);
				request.setAttribute("AnalysisFlag", "yes");
				request.setAttribute("currentclass", classid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/hwresult.jsp").forward(request, response);
		}
		if(action.equals("allClassResult")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where studentid='"+uid+"' and correct=1";
			
			sqlstatement+=" order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwResultlist", l);
				request.setAttribute("AnalysisFlag", "no");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/hwresult.jsp").forward(request, response);
		}
		if(action.equals("showsinglechart")){
			String hwid=(String) request.getParameter("hwid");
			 String hwname= (String)request.getParameter("hwname");
		     String kcid= (String)request.getParameter("kcid");
			request.setAttribute("hwid", hwid);
			request.setAttribute("hwname", hwname);
			request.setAttribute("kcid", kcid);
			request.getRequestDispatcher("student/singleHWChart.jsp").forward(request, response);
		}
		if(action.equals("showclasschart")){
			String kcid= (String)request.getParameter("kcid");
			request.setAttribute("kcid", kcid);
			request.setAttribute("uid", uid);
			request.getRequestDispatcher("student/classHWChart.jsp").forward(request, response);
		}
	}

}
