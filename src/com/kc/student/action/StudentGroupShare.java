package com.kc.student.action;

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

public class StudentGroupShare extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String uid = (String)request.getSession().getAttribute("uid");
		String type=(String)request.getParameter("type");
		
		if(type.equals("grouporigin")){
			request.setAttribute("currentclass", classlist.get(0));
			System.out.println("group member:"+classlist.get(0));
			try {
				String sql="select groupname from studentgroup where studentid='"+uid+"' and kcid='"+classlist.get(0)+"'";
				List l=DAOFactory.getHomeworkDAOInstance().getGroupName(sql);
				System.out.println("group name:"+l);
				//request.setAttribute("groupnamelist", l);
				if(l.size()>0){
					HashMap m=(HashMap)l.get(0);
					String sql2="select * from studentgroup where kcid='"+classlist.get(0)+"' and groupname='"+m.get("groupname")+"'";
					List groupMemberList=DAOFactory.getHomeworkDAOInstance().getGroupInfo(sql2);
					request.setAttribute("groupMemberList", groupMemberList);
					System.out.println("group member:"+groupMemberList);
					
					String sql3="select * from share where classid='"+classlist.get(0)+"' and groupname='"+m.get("groupname")+"'";
					List notelist=DAOFactory.getMaterialDAOInstance().getShareFile(sql3);
					request.setAttribute("notelist", notelist);
				}else if (l.size()==0){
					request.setAttribute("groupMemberList", null);
					request.setAttribute("notelist", null);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/GroupShare.jsp").forward(request, response);
		}
		
		if(type.equals("group")){
			String currentclass= (String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass);
			try {
				String sql="select groupname from studentgroup where studentid='"+uid+"' and kcid='"+currentclass+"'";
				List l=DAOFactory.getHomeworkDAOInstance().getGroupName(sql);
				System.out.println("group name:"+l+" and size:"+l.size());
				System.out.println("execute find group name");
				//request.setAttribute("groupnamelist", l);
				if(l.size()>0){
					HashMap m=(HashMap)l.get(0);
					String sql2="select * from studentgroup where kcid='"+currentclass+"' and groupname='"+m.get("groupname")+"'";
					List groupMemberList=DAOFactory.getHomeworkDAOInstance().getGroupInfo(sql2);
					request.setAttribute("groupMemberList", groupMemberList);
					System.out.println("group member:"+groupMemberList);
					System.out.println("execute if size>0");
					
					String sql3="select * from share where classid='"+currentclass+"' and groupname='"+m.get("groupname")+"'";
					List notelist=DAOFactory.getMaterialDAOInstance().getShareFile(sql3);
					request.setAttribute("notelist", notelist);
				}else if (l.size()==0){
					request.setAttribute("groupMemberList", null);
					request.setAttribute("notelist", null);
					System.out.println("execute else if size=0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/GroupShare.jsp").forward(request, response);
		}
		
	}

}
