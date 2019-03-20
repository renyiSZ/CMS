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

public class ShareServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String type= (String)request.getParameter("type");
		String userid=(String)request.getSession().getAttribute("uid");
		String username=(String)request.getSession().getAttribute("uname");
		
		if(type.equals("noteorigin")){
			request.setAttribute("currentclass", classlist.get(0)); 
			try{
				String sql="select * from share where groupname='' and classid='"+classlist.get(0)+"'";
				List notelist = DAOFactory.getMaterialDAOInstance().getShareFile(sql);
				System.out.println("notelist:"+notelist);
				
				request.setAttribute("notelist", notelist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/ShareNote.jsp").forward(request, response);	
		}
		if(type.equals("note")){
			String currentclass= (String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass); 
			try{
				String sql="select * from share where  groupname='' and classid='"+currentclass+"'";
				List notelist = DAOFactory.getMaterialDAOInstance().getShareFile(sql);
				request.setAttribute("notelist", notelist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("student/ShareNote.jsp").forward(request, response);	
		}
		
		if(type.equals("deletenote")){
			int id= Integer.parseInt(request.getParameter("noteid"));
			System.out.println("delete note first step share id:"+id);
			String currentclass= (String)request.getParameter("kc");
			String person= (String)request.getParameter("person");
			request.setAttribute("currentclass", currentclass); 
			PrintWriter out=response.getWriter();
			
			try{
				DAOFactory.getMaterialDAOInstance().deleteShareFile(id);
				String sql="select * from share where  groupname='' and classid='"+currentclass+"'";
				List notelist = DAOFactory.getMaterialDAOInstance().getShareFile(sql);
				request.setAttribute("notelist", notelist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(person.equals("student"))
				request.getRequestDispatcher("student/ShareNote.jsp").forward(request, response);
			else if(person.equals("teacher"))
				request.getRequestDispatcher("teacher/StudentNote.jsp").forward(request, response);
			else if(person.equals("studentgroup")){
				out.println("<script type='text/javascript' language='javascript'>");
				out.println("window.location.href='StudentGroupShare?type=group&kc="+currentclass+"';");
				out.println("</script>");
				out.flush();
				out.close();
			}
		}
		if(type.equals("teacherorigin")){
			request.setAttribute("currentclass", classlist.get(0)); 
			try{
				String sql="select * from share where groupname='' and classid='"+classlist.get(0)+"'";
				List notelist = DAOFactory.getMaterialDAOInstance().getShareFile(sql);
				System.out.println("notelist:"+notelist);
				
				request.setAttribute("notelist", notelist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/StudentNote.jsp").forward(request, response);	
			
		}
		if(type.equals("teachernote")){
			String currentclass= (String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass); 
			try{
				String sql="select * from share where  groupname='' and classid='"+currentclass+"'";
				List notelist = DAOFactory.getMaterialDAOInstance().getShareFile(sql);
				request.setAttribute("notelist", notelist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/StudentNote.jsp").forward(request, response);	
		}
	}

	

}
