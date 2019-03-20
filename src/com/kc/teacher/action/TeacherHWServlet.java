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

import com.kc.factory.DAOFactory;

public class TeacherHWServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
	    String type= (String)request.getParameter("type");
	    String userid=(String)request.getSession().getAttribute("uid");
	    
	    if(type.equals("correctorigin")){
	    	request.setAttribute("currentclass", classlist.get(0));  
	    	try{
	    		String sql="select * from hwforcorrect where kcid='"+classlist.get(0)+"' and correct=0 order by time desc";
	    		List l=  DAOFactory.getHomeworkDAOInstance().getStudentHWs(sql); 
	    		request.setAttribute("correctlist", l);  
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}
	    	request.getRequestDispatcher("teacher/correctHW.jsp").forward(request, response);
	    }
	    if(type.equals("correct")){
	    	 String kcid= (String)request.getParameter("kcid");
	    	 request.setAttribute("currentclass", kcid);
	    	try{
	    		String sql="select * from hwforcorrect where kcid='"+kcid+"' and correct=0 order by time desc";
	    		List l=  DAOFactory.getHomeworkDAOInstance().getStudentHWs(sql); 
	    		request.setAttribute("correctlist", l);  
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}
	    	request.getRequestDispatcher("teacher/correctHW.jsp").forward(request, response);
	    }
	    //
	    if(type.equals("TAcorrect")){
	    	String assistant = (String)request.getSession().getAttribute("assistant");
	    	try{
	    		String sql="select * from hwforcorrect where kcid='"+assistant+"' and correct=0 order by time desc";
	    		List l=  DAOFactory.getHomeworkDAOInstance().getStudentHWs(sql); 
	    		request.setAttribute("correctlist", l);  
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}
	    	request.getRequestDispatcher("student/TACorrect.jsp").forward(request, response);
	    }
	}
}
