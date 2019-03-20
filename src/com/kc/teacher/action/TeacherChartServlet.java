package com.kc.teacher.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.factory.DAOFactory;

public class TeacherChartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
	    String chart= (String)request.getParameter("chart");
	    String userid=(String)request.getSession().getAttribute("uid");
	   //////////////////chart
		if(chart.equals("singlestudentHWChart")){
			String kcid= (String)request.getParameter("kcid");
			String uid= (String)request.getParameter("uid");
			request.setAttribute("kcid", kcid);
			request.setAttribute("uid", uid);
			request.getRequestDispatcher("student/classHWChart.jsp").forward(request, response);
		}
		
		
		///////////////////////
		if(chart.equals("assignedhworigin")){
			//String hw= (String)request.getParameter("hw");
			request.setAttribute("currentclass", classlist.get(0));  
			try{
				String sql="select * from homework where kcid='"+classlist.get(0)+"'";
				List hwlist = DAOFactory.getHomeworkDAOInstance().getHomework(sql);
				request.setAttribute("hwlist", hwlist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/assignedhw.jsp").forward(request, response);
		}
		
		if(chart.equals("assignedhw")){
			String hw= (String)request.getParameter("hw");
			request.setAttribute("currentclass", hw);  
			try{
				String sql="select * from homework where kcid='"+hw+"'";
				List hwlist = DAOFactory.getHomeworkDAOInstance().getHomework(sql);
				request.setAttribute("hwlist", hwlist);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/assignedhw.jsp").forward(request, response);
		}
		
		if(chart.equals("resultorigin")){
			try{
				String str="select * from homework where teacherid='"+userid+"'";
				List l=DAOFactory.getHomeworkDAOInstance().getHomework(str);
				String originhwID="";
				String originhwtitle="";
				String originclass="";
				String oringintime="";
				if( l.size()>0){
				HashMap m=(HashMap)l.get(0);
				 originhwID=""+m.get("hwid");
				 originhwtitle=""+m.get("hwtitle");
				 originclass=""+m.get("kcid");
				 oringintime=""+m.get("hwtime");
				}
				System.out.println("currentclass:"+originclass);
				System.out.println("originhwID:"+originhwID);
				System.out.println("originhwtitle:"+originhwtitle);
				
				request.setAttribute("currentclass", originclass); 
				request.setAttribute("currenthw",originhwID);
				request.setAttribute("currenthwtitle",originhwtitle);
				request.setAttribute("currenthwtime",oringintime);
				
				String sql1="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.hwid='"+originhwID+"'";
				List homeworklist=DAOFactory.getHomeworkDAOInstance().getJoinInfo(sql1);
				String sql2="select count(*) from hwforcorrect where hwid='"+originhwID+"' and correct=1";
				int submitcount=DAOFactory.getHomeworkDAOInstance().getSubmitHWCount(sql2);
				System.out.println("submitcount:"+submitcount);
				String sql3="select count(*) from hwforcorrect where hwid='"+originhwID+"' and correct=0";
				int handinNotCorrect=DAOFactory.getHomeworkDAOInstance().getSubmitHWCount(sql3);
				System.out.println("handinNotCorrect:"+handinNotCorrect);
				
				request.setAttribute("homeworklist", homeworklist);
				request.setAttribute("submitcount", submitcount);
				request.setAttribute("handinNotCorrect", handinNotCorrect);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/hwresult.jsp").forward(request, response);
		}
		
		if(chart.equals("result")){
			String hw= (String)request.getParameter("hw");
			System.out.println("current :"+hw);
			String currentclass=hw.substring(0,hw.indexOf("."));
			System.out.println("current class:"+currentclass);
			String hwID=hw.substring(hw.indexOf(".")+1,hw.indexOf(":"));
			System.out.println("current homework id:"+hwID);
			String hwTitle=hw.substring(hw.lastIndexOf(":")+1);
			System.out.println("current homework title:"+hwTitle);
			
			request.setAttribute("currentclass",currentclass);
			request.setAttribute("currenthw",hwID);
			request.setAttribute("currenthwtitle",hwTitle);
			try{
				String sql1="select * from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where homework.hwid='"+hwID+"'";
				List homeworklist=DAOFactory.getHomeworkDAOInstance().getJoinInfo(sql1);
				HashMap map=(HashMap)homeworklist.get(0);
				String hwtime=""+map.get("hwtime");
				String sql2="select count(*) from hwforcorrect where hwid='"+hwID+"' and correct=1";
				int submitcount=DAOFactory.getHomeworkDAOInstance().getSubmitHWCount(sql2);
				String sql3="select count(*) from hwforcorrect where hwid='"+hwID+"' and correct=0";
				int handinNotCorrect=DAOFactory.getHomeworkDAOInstance().getSubmitHWCount(sql3);
				
				request.setAttribute("currenthwtime",hwtime);
				request.setAttribute("homeworklist", homeworklist);
				request.setAttribute("submitcount", submitcount);
				request.setAttribute("handinNotCorrect", handinNotCorrect);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("teacher/hwresult.jsp").forward(request, response);
		}
		
	}

	private int lastIndexOf(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

}
