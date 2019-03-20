package com.kc.action;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.ForumPost;
import com.kc.entity.ForumPostReply;
import com.kc.factory.DAOFactory;

public class AddReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String replyhead=(String) request.getSession().getAttribute("uhead");
		String userid= (String) request.getSession().getAttribute("uid");
		String username=(String) request.getSession().getAttribute("uname");
		String replycontent = request.getParameter("replycontent"); 
		String postuserid = request.getParameter("postuserid"); 
		String flag=request.getParameter("flag"); //判断是homepage还是account添加的reply
		//int postreply = Integer.parseInt(request.getParameter("postreply")); 
		int postid = Integer.parseInt(request.getParameter("postid")); 
		//System.out.println(replycontent);
		//System.out.println(userid+" "+username+"      "+postuserid);
		
		ForumPostReply reply=new ForumPostReply();
		reply.setReplycontent(replycontent);
		reply.setUserid(postuserid);
		reply.setReplyuserid(userid);
		reply.setReplyusername(username);
		reply.setPostid(postid);
		//头像还没做
		reply.setReplyhead(replyhead);
		
		try {
			if (DAOFactory.getForumPostDAOInstance().addReply(reply) == 1) {
				System.out.println("db store reply successfully");
				DAOFactory.getForumPostDAOInstance().addReplycount(postid, 1);
				request.setAttribute("postid", postid);
				if(flag.equals("detail")){
				   request.getRequestDispatcher("/PostDetailServlet?detailWhat=notme&time=two&postid="+postid).forward(request, response);}
				else if(flag.equals("mydetail"))
				{request.getRequestDispatcher("/PostDetailServlet?detailWhat=me&time=two&postid="+postid).forward(request, response);}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
