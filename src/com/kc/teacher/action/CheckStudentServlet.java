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

public class CheckStudentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String show=(String)request.getParameter("show");
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		if(show.equals("first")){
		try{	
				String sql="select * from users join class on users.uid=class.userid where kc1='"+classlist.get(0)+"' or kc2='"+classlist.get(0)+"' or kc3='"+classlist.get(0)+"' or kc4='"+classlist.get(0)+"' or kc5='"+classlist.get(0)+"' or kc6='"+classlist.get(0)+"' or kc7='"+classlist.get(0)+"' or kc8='"+classlist.get(0)+"' or kc9='"+classlist.get(0)+"' or kc10='"+classlist.get(0)+"'";
				List studentlist=DAOFactory.getUserDAOInstance().searchTableClassUsers(sql);
				request.setAttribute("studentlist", studentlist); 
				request.setAttribute("currentclass", classlist.get(0));  
				
			
				//find if any representative
				String sql2="select * from users where representative='"+classlist.get(0)+"'";
				List Rlist=DAOFactory.getUserDAOInstance().getUsers(sql2);
				request.setAttribute("Rlist", Rlist);
				System.out.println("Rlist:"+Rlist);  
				
				request.getRequestDispatcher("teacher/myStudent.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
		try{	
				String sql="select * from users join class on users.uid=class.userid where kc1='"+show+"' or kc2='"+show+"' or kc3='"+show+"' or kc4='"+show+"' or kc5='"+show+"' or kc6='"+show+"' or kc7='"+show+"' or kc8='"+show+"' or kc9='"+show+"' or kc10='"+show+"'";
				List studentlist=DAOFactory.getUserDAOInstance().searchTableClassUsers(sql);
				request.setAttribute("studentlist", studentlist);  	 
				request.setAttribute("currentclass", show);  
				
			
				//find if any representative
				String sql2="select * from users where representative='"+show+"'";
				List Rlist=DAOFactory.getUserDAOInstance().getUsers(sql2);
				request.setAttribute("Rlist", Rlist); 
				System.out.println("Rlist:"+Rlist);  
				
				request.getRequestDispatcher("teacher/myStudent.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
