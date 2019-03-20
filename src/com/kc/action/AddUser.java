package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Users;
import com.kc.factory.DAOFactory;

public class AddUser extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String userid=request.getParameter("userid");
		String username=request.getParameter("username");
		int sex=Integer.parseInt(request.getParameter("sex"));
		String usertype=request.getParameter("usertype");
		String date=request.getParameter("date");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		
		PrintWriter out=response.getWriter();
		
		Users user = new Users();
		user.setUid(userid);
		user.setUname(username);
		user.setUsex(sex);
		user.setJob(usertype);
		user.setUbirthday(date);
		user.setEmail(email);
		user.setUphone(phone);
		user.setPasswd(password);
		user.setUhead("images/head/man-default.jpg");
		
		try {
			if(DAOFactory.getUserDAOInstance().addUser(user)==1){
				out.println("<script type='text/javascript' language='javascript'>");
				out.println("alert('Add user successfully!');");
				out.println("window.location.href('admin/addUser.jsp');");
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
