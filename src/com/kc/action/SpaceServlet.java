package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class SpaceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userjob= (String) request.getSession().getAttribute("job");
		ArrayList classlist=new ArrayList();
		String id =(String)request.getSession().getAttribute("uid");
		String representative =(String)request.getSession().getAttribute("representative");
		System.out.println("representative:"+ representative);
		String assistant =(String)request.getSession().getAttribute("assistant");
		//纯学生
		if (userjob.equals("student")&& representative==null && assistant==null){
		  	try {
				Map map = DAOFactory.getUserDAOInstance().searchClass(id);
				for(int i=1;i<11;i++){
					if(map.get("kc"+i)!=null){
						classlist.add(""+map.get("kc"+i));
					}
				}
				System.out.println("student class:"+classlist.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	request.getSession().setAttribute("classlist", classlist);
			request.getRequestDispatcher("userSpace.jsp").forward(request, response);
		}
		//纯TA
		if (userjob.equals("student")&& representative==null && assistant!=null){
		  	try {
				Map map = DAOFactory.getUserDAOInstance().searchClass(id);
				for(int i=1;i<11;i++){
					if(map.get("kc"+i)!=null){
						classlist.add(""+map.get("kc"+i));
					}
				}
				System.out.println("student class:"+classlist.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	request.getSession().setAttribute("classlist", classlist);
		  	System.out.println("去了TA页面");
			request.getRequestDispatcher("TASpace.jsp").forward(request, response);
		}
		//纯课代表
		if (userjob.equals("student")&& representative!=null && assistant==null){
		  	try {
				Map map = DAOFactory.getUserDAOInstance().searchClass(id);
				for(int i=1;i<11;i++){
					if(map.get("kc"+i)!=null){
						classlist.add(""+map.get("kc"+i));
					}
				}
				System.out.println("student class:"+classlist.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	request.getSession().setAttribute("classlist", classlist);
		  	System.out.println("去了课代表页面");
			request.getRequestDispatcher("representativeSpace.jsp").forward(request, response);
		}
		
		//课代表+zhujiao
				if (userjob.equals("student")&& representative!=null && assistant!=null){
				  	try {
						Map map = DAOFactory.getUserDAOInstance().searchClass(id);
						for(int i=1;i<11;i++){
							if(map.get("kc"+i)!=null){
								classlist.add(""+map.get("kc"+i));
							}
						}
						System.out.println("student class:"+classlist.toString());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  	request.getSession().setAttribute("classlist", classlist);
				  	System.out.println("去了TAR页面");
					request.getRequestDispatcher("TARSpace.jsp").forward(request, response);
				}
				
		if (userjob.equals("teacher")){
			String sql="select * from kcdesc where teacherid='"+id+"'";
			List l;
			try {
				//teacher's class list
				l = DAOFactory.getClassInfoDAOInstance().getClass(sql);
				if(l.size()>0){
					for(int i=0;i<l.size();i++){
						HashMap m=(HashMap) l.get(i);
	  					classlist.add(""+m.get("kcid"));
	  				}
	  	 		}
				
				System.out.println("teacher class:"+classlist.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.getSession().setAttribute("classlist", classlist);  	 		
			request.getRequestDispatcher("teacherSpace.jsp").forward(request, response);
		}
		if (userjob.equals("admin")){
			request.getRequestDispatcher("adminSpace.jsp").forward(request, response);
		}
	}

	
}
