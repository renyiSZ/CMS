package com.kc.teacher.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Ask;
import com.kc.factory.DAOFactory;

public class TeacherAnswer extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=(String)request.getParameter("type");
		String uid = (String)request.getSession().getAttribute("uid");
		String uname = (String)request.getSession().getAttribute("uname");
		
		if(type.equals("mark")){
			String from=(String)request.getParameter("from");
			int askid=Integer.parseInt(request.getParameter("askid"));
			System.out.println("askid:"+askid);
			try {
				Ask ask =new Ask();
				ask.setAnswercontent("");
				ask.setAskid(askid);
				ask.setTeacherid(uid);
				ask.setTeachername(uname);
				DAOFactory.getAskDAOInstance().AddAnswer(ask);
				if(from.equals("R")){
					request.getRequestDispatcher("StudentAsk?type=TCollectOrigin&from=R").forward(request, response);
				}
				if(from.equals("TA")){
					request.getRequestDispatcher("StudentAsk?type=TCollectOrigin&from=TA").forward(request, response);
				}
				if(from.equals("student")){
					request.getRequestDispatcher("StudentAsk?type=TCollectOrigin&from=student").forward(request, response);
				}
				if(from.equals("Rmark")){///////////////////////////
					request.getRequestDispatcher("StudentAsk?type=RCollect").forward(request, response);
				}
			  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("answer")){
			String from=(String)request.getParameter("from");
			String answercontent=(String)request.getParameter("answercontent");
			int askid=Integer.parseInt(request.getParameter("askid"));
			
			
			Ask ask =new Ask();
			ask.setAnswercontent(answercontent);
			ask.setAskid(askid);
			ask.setTeacherid(uid);
			ask.setTeachername(uname);
			try {
				DAOFactory.getAskDAOInstance().AddAnswer(ask);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(from.equals("R")){
				request.getRequestDispatcher("StudentAsk?type=TCollectOrigin&from=R").forward(request, response);
			}
			
			if(from.equals("student")){
				request.getRequestDispatcher("StudentAsk?type=TCollectOrigin&from=student").forward(request, response);
			}
			
		}
		
	}

}
