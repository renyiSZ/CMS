package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.kc.entity.Video;
import com.kc.service.VideoService;

public class VideoDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int vid= Integer.parseInt(request.getParameter("vid"));
		String kcid=request.getParameter("kcid");
		String videoAction=request.getParameter("videoAction");
		
		System.out.println( vid+" :"+videoAction);
		
		try {
			//获取帖子
			
				
				String sql="select * from video where vid='"+vid+"'";
				VideoService service= new VideoService();
				List list=service.listVideo(sql);
				
				String sql2="select * from video where kcid='"+kcid+"' and vid<>'"+vid+"'";
				VideoService service2= new VideoService();
				List list2=service2.listVideo(sql2);
				
				request.setAttribute("list",list);
				request.setAttribute("list2",list2);
				
			if(videoAction.equals("listvideodetail")){
			   request.getRequestDispatcher("video.jsp").forward(request, response);
			}
			if(videoAction.equals("listmyvideo")){
			   request.getRequestDispatcher("student/myvideoDetail.jsp").forward(request, response);
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
