package com.kc.action;

import java.util.List;
import java.io.IOException;




import com.kc.dao.MaterialDao;
import com.kc.entity.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;
import com.kc.service.ForumPostService;
import com.kc.service.MaterialService;
import com.kc.service.VideoService;

public class PageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int thispage=Integer.parseInt(request.getParameter("thispage"));
		String spitPageWhat=(String)request.getParameter("spitPageWhat");
		//System.out.println(thispage);
		//System.out.println(spitPageWhat);
		if(spitPageWhat.equals("material")){
			try {
				MaterialService service=new MaterialService();
				String sql1="select count(*) from material";
				String sql2="select * from material order by mtime desc";
				Page page=service.pageListMaterial(thispage,sql1,sql2);
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("material.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(spitPageWhat.equals("materialselect")){
		String classid=(String)request.getParameter("classid");
			try {
				String sql1="";String sql2="";
				
				MaterialService service=new MaterialService();
				if(classid.equals("")||classid.equals(null)){
					sql1="select count(*) from material";
					sql2="select * from material order by mtime desc";
				}else{
					sql1="select count(*) from material where kcid='"+classid+"'";
					sql2="select * from material where kcid='"+classid+"' order by mtime desc";
				}
				Page page=service.pageListMaterial(thispage,sql1,sql2);
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("material.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(spitPageWhat.equals("post")){
			try {
				ForumPostService service=new ForumPostService();
				String sql1="select count(*) from post";
				String sql2="select * from post order by posttime desc";
				Page page=service.pageListPost(thispage,sql1,sql2);
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("forum.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(spitPageWhat.equals("postselect")){
			String posttype=(String)request.getParameter("posttype");
			try {
				
				String sql1="";String sql2="";
				ForumPostService service=new ForumPostService();
				if(posttype.equals("")||posttype.equals(null)){
					sql1="select count(*) from post";
					sql2="select * from post order by posttime desc";
				}else{
					
				 sql1="select count(*) from post where posttype='"+posttype+"'";
				 sql2="select * from post where posttype='"+posttype+"' order by posttime desc";
				}
				Page page=service.pageListPost(thispage,sql1,sql2);
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("forum.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(spitPageWhat.equals("video")){
			try {
				VideoService service=new VideoService();
				String sql1="select count(*) from video";
				String sql2="select * from video order by vtime desc";
				Page page=service.pageListVideo(thispage,sql1, sql2);
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("MyJsp.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(spitPageWhat.equals("videoselect")){
			String classid=request.getParameter("classid");
			String classtype=request.getParameter("classtype");
			String teachername=request.getParameter("teachername");
			String sql1="";String sql2="";
			try {
				VideoService service=new VideoService();
				if(classid.equals("")&&classtype.equals("")&&teachername.equals("")){
					sql1="select * from video";
					sql2="select count(*) from video";
				}
				else{
					if(!classid.equals("")){
						sql1="select * from video where kcid='"+classid+"'";
						sql2="select count(*) from video where kcid='"+classid+"'";
						if(!classtype.equals("")){
							sql1=sql1+" and vtype='"+classtype+"'";
							sql2=sql2+" and vtype='"+classtype+"'";
						}
						if(!teachername.equals("")){
							sql1=sql1+" and username='"+teachername+"'";
							sql2=sql2+" and username='"+teachername+"'";
						}
					}
					else{
						
						if(!classtype.equals("")){
							sql1="select * from video where vtype='"+classtype+"'";
							sql2="select count(*) from video where vtype='"+classtype+"'";
							if(!teachername.equals("")){
								sql1=sql1+" and username='"+teachername+"'";
								sql2=sql2+" and username='"+teachername+"'";
							}
						}
						else{
							if(!teachername.equals("")){
								sql1="select * from video where username='"+teachername+"'";
								sql2="select count(*) from video where username='"+teachername+"'";
							}
						}
					}
				}
				
				Page page=service.pageListVideo(thispage,sql2, sql1);//特意调过来
				request.setAttribute("page", page);
				
				request.getRequestDispatcher("MyJsp.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	
}
