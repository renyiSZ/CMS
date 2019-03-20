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

public class StudentHomeworkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	String sqlstatement;
	String uid=(String) request.getSession().getAttribute("uid");
	String action=(String) request.getParameter("action");
	String require=(String) request.getParameter("require");
	//未交作业
	if(require.equals("handin")){
		if(action.equals("singleClassHW")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.kcid='"+classid+"' and studentid='"+uid+"' and correct=9 order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/downloadhw.jsp").forward(request, response);
		}
		if(action.equals("allClassHW")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where studentid='"+uid+"' and correct=9";
			
			sqlstatement+=" order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/downloadhw.jsp").forward(request, response);
		}
		
	}
	//已交作业为批改
	if(require.equals("submitted")){
		if(action.equals("singleClassHW")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.kcid='"+classid+"' and studentid='"+uid+"' and correct=0 order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/submittedHW.jsp").forward(request, response);
		}
		if(action.equals("allClassHW")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where studentid='"+uid+"' and correct=0";
			
			sqlstatement+=" order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/submittedHW.jsp").forward(request, response);
		}
	}
	//已批改
	if(require.equals("graded")){
		if(action.equals("singleClassHW")){
			String classid=(String) request.getParameter("classid");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.kcid='"+classid+"' and studentid='"+uid+"' and correct=1 order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/gradedHW.jsp").forward(request, response);
		}
		if(action.equals("allClassHW")){
			ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
			sqlstatement="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where studentid='"+uid+"' and correct=1";
			
			sqlstatement+=" order by hwtime desc";
			try {
				List l=  DAOFactory.getHomeworkDAOInstance().getJoinInfo(sqlstatement); 
				request.setAttribute("hwlist", l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getRequestDispatcher("student/gradedHW.jsp").forward(request, response);
		}
	}
  }

	
}
