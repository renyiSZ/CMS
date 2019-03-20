package com.kc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;
import com.kc.entity.ClassInfo;
import com.kc.entity.Users;
public class EditClass extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String classwhat=request.getParameter("classwhat");
		PrintWriter out = response.getWriter();
		
		if(classwhat.equals("add")){
			String classid=request.getParameter("classid");
			String classname=request.getParameter("classname");
			String classtype=request.getParameter("classtype");
			String teacherid=request.getParameter("teacherid");
			int credit=Integer.parseInt(request.getParameter("credit"));
			String semester_y=request.getParameter("semester_y");
			String semester_s=request.getParameter("semester_s");
			String semester=semester_y+"-"+semester_s;
			
			try {
				Users teacher= DAOFactory.getUserDAOInstance().queryByID(teacherid);
				ClassInfo classinfo=new ClassInfo();
				classinfo.setKcid(classid);
				classinfo.setKcname(classname);
				classinfo.setKctype(classtype);
				classinfo.setTeacherid(teacherid);
				classinfo.setCredit(credit);
				classinfo.setSemester(semester);
				classinfo.setTeachername(""+teacher.getUname());
				if(DAOFactory.getClassInfoDAOInstance().addClass(classinfo)==1){
					out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Add successfully !');");
					out.println("window.location.href('admin/addClass.jsp');");
					out.println("</script>");
					out.flush();
					out.close();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		
		
		if(classwhat.equals("edit")){
			
		}
		
	}

	

}
