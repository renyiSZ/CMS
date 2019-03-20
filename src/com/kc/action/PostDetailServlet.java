package com.kc.action;

import java.util.List;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;
import com.kc.service.ForumPostService;

public class PostDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int postid= Integer.parseInt(request.getParameter("postid"));
		//int postclick= Integer.parseInt(request.getParameter("postclick"));
		String detailWhat=request.getParameter("detailWhat");
		String time=request.getParameter("time");
		//System.out.println( postid);
		try {
			//获取帖子
			
			ForumPostService service= new ForumPostService();
			Map map=service.queryByPid(postid);
			request.setAttribute("map", map);
			//获取帖子对应的回复
			ForumPostService s= new ForumPostService();
			List list=s.getAllReply(postid);
			request.setAttribute("list", list);
			System.out.println(detailWhat);
			if(!time.equals("two")){
			DAOFactory.getForumPostDAOInstance().addRead(postid, 1);}
			if(detailWhat.equals("me")){
				request.getRequestDispatcher("student/mypostdetail.jsp").forward(request, response);
			}
			if(detailWhat.equals("notme")){   
				request.getRequestDispatcher("postDetail.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	

}
