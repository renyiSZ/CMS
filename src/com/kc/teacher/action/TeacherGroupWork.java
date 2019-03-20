package com.kc.teacher.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class TeacherGroupWork extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String userid=(String)request.getSession().getAttribute("uid");
		String type=(String)request.getParameter("type");
		
		if(type.equals("group")){
			String sql="select * from  homework where teacherid='"+userid+"' and (answerlink='group' or answerlink='divided')";
			try {
				List gwlist = DAOFactory.getHomeworkDAOInstance().getHomework(sql);
				request.setAttribute("gwlist", gwlist);
				System.out.println("GroupWork: "+gwlist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/GroupWork.jsp").forward(request, response);
		}
		
		if(type.equals("divide")){
			String kcid=(String)request.getParameter("kcid");
			String hwid=(String)request.getParameter("hwid");
			request.setAttribute("kcid", kcid);
			request.setAttribute("hwid", hwid);
			System.out.println("Servlet kcid + hwid"+kcid+" "+hwid);
			request.getRequestDispatcher("teacher/groupUpload.jsp").forward(request, response);
			
		}
		if(type.equals("check")){
			String kcid=(String)request.getParameter("kcid");
			String hwid=(String)request.getParameter("hwid");
			request.setAttribute("kcid", kcid);
			request.setAttribute("hwid", hwid);
			
			try {
				String sql="select groupname from studentgroup where hwid='"+hwid+"'";
				List groupNameList=DAOFactory.getHomeworkDAOInstance().getGroupName(sql);
				request.setAttribute("groupNameList", groupNameList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/GroupShareCheck.jsp").forward(request, response);
		}
		
	}

}
