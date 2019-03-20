package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.ForumPost;
import com.kc.factory.DAOFactory;

public class AddPostServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userid= (String) request.getSession().getAttribute("uid");
		String username=(String) request.getSession().getAttribute("uname");
		String posthead=(String) request.getSession().getAttribute("uhead");
		String title = request.getParameter("title");
		String postcontent = request.getParameter("postcontent"); 
		String posttype =request.getParameter("posttype");
		
		PrintWriter out=response.getWriter();
		
		ForumPost post=new ForumPost();
		post.setPosttitle(title);
		post.setPostcontent(postcontent);
		post.setPosttype(posttype);
		post.setUserid(userid);
		post.setUsername(username);
		//头像还没做
		post.setPosthead(posthead);
		try {
			if (DAOFactory.getForumPostDAOInstance().addPost(post) == 1) {
				System.out.println("db store post successfully");
				out.println("<script type='text/javascript' language='javascript'>");
				out.println("alert('Sent successfully !');");
				//////////////////out.println("window.history.back(-1);");
				out.println("window.location.href('addpost.jsp');");
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
