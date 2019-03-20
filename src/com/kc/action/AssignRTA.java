package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class AssignRTA extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type= (String)request.getParameter("type");
		PrintWriter out=response.getWriter();
		
		if(type.equals("R")){
			String kcid= (String)request.getParameter("kcid");
			
			request.setAttribute("kcid",kcid);
			
			request.getRequestDispatcher("teacher/teacherAssignR.jsp").forward(request, response);
		}
		
		if(type.equals("TA")){
			
		}
		
		if(type.equals("AssignR")){
			String kcid= (String)request.getParameter("kcid");
			String studentid= (String)request.getParameter("studentid");
			String sql="update users set representative='"+kcid+"' where uid='"+studentid+"'";
			try {
				if(DAOFactory.getUserDAOInstance().editInfo(sql)==1){
					out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Assign OK');");
					out.println("window.history.back(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				}
				else{
					out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Wrong Student ID!');");
					out.println("window.history.back(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
