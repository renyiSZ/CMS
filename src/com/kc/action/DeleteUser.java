package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class DeleteUser extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String deleteNumber=request.getParameter("deleteNumber");
		String userid=request.getParameter("uid");
		String [] uidArr= userid.split(",");
		if(deleteNumber.equals("many")){
			System.out.println("userid:"+userid);
			try {
				DAOFactory.getUserDAOInstance().deleteUsers(uidArr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(deleteNumber.equals("single")){
			System.out.println("userid:"+userid);
			try {
				DAOFactory.getUserDAOInstance().deleteUser(userid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println("<script type='text/javascript' language='javascript'>");
	
		out.println("window.location.href('admin/editUser.jsp');");
		out.println("</script>");
		out.flush();
		out.close();
	}

}
