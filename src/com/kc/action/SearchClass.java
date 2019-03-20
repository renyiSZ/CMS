package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class SearchClass extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search=request.getParameter("search");
		
		if(search.equals("origin")){
			String sql= "select * from kcdesc";
		   	
			request.setAttribute("sqlstatement",sql);
			request.getRequestDispatcher("curriculum.jsp").forward(request, response);	
		}
		if(search.equals("homesearch")){
			String sql1="";
			String classid=request.getParameter("classid");
			String classtype=request.getParameter("classtype");
			String teachername=request.getParameter("teachername");
			
			if(classid.equals("")&&classtype.equals("")&&teachername.equals("")){
				sql1="select * from kcdesc";
			}
			else{
				if(!classid.equals("")){
					sql1="select * from kcdesc where kcid='"+classid+"'";
					if(!classtype.equals("")){
						sql1=sql1+" and kctype='"+classtype+"'";
					}
					if(!teachername.equals("")){
						sql1=sql1+" and teachername='"+teachername+"'";
					}
				}
				else{
					
					if(!classtype.equals("")){
						sql1="select * from kcdesc where kctype='"+classtype+"'";
						if(!teachername.equals("")){
							sql1=sql1+" and teachername='"+teachername+"'";
						}
					}
					else{
						if(!teachername.equals("")){
							sql1="select * from kcdesc where teachername='"+teachername+"'";
						}
					}
				}
			}
			System.out.println(sql1);
			request.setAttribute("sqlstatement",sql1);
			request.getRequestDispatcher("curriculum.jsp").forward(request, response);	
		}
	}

	

}
