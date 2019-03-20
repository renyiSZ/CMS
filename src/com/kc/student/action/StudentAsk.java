package com.kc.student.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Ask;
import com.kc.entity.Grade;
import com.kc.factory.DAOFactory;

public class StudentAsk extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList classlist = (ArrayList)request.getSession().getAttribute("classlist");
		String uid = (String)request.getSession().getAttribute("uid");
		String uname = (String)request.getSession().getAttribute("uname");
		String type=(String)request.getParameter("type");
		String representative = (String)request.getSession().getAttribute("representative");
		String assistant = (String)request.getSession().getAttribute("assistant");
		
		if(type.equals("toRepresentative")){
			
			try {
				if(classlist.size()>0){
					String sql="select * from users where";
					for(int i=0;i<classlist.size();i++){
						if(i==0){
							sql+=" representative='"+classlist.get(0)+"'";
						}
						sql+=" or representative='"+classlist.get(i)+"'";
					}
					List representativelist=DAOFactory.getUserDAOInstance().getUsers(sql);
					request.setAttribute("representativelist", representativelist);
				}
				else{
					request.setAttribute("representativelist", null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("people", type);
			request.getRequestDispatcher("student/ReportToRepresentative.jsp").forward(request, response);
		}
		if(type.equals("toTeacher")){
			
			try {
				if(classlist.size()>0){
					String sql="select * from users join kcdesc on users.uid=kcdesc.teacherid where";
					for(int i=0;i<classlist.size();i++){
						if(i==0){
							sql+=" kcid='"+classlist.get(0)+"'";
						}
						sql+=" or kcid='"+classlist.get(i)+"'";
					}
					List representativelist=DAOFactory.getUserDAOInstance().getUsers(sql);
					request.setAttribute("representativelist", representativelist);
				}
				else{
					request.setAttribute("representativelist", null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("people", type);
			request.getRequestDispatcher("student/ReportToRepresentative.jsp").forward(request, response);
		}
		
		if(type.equals("addask")){
			String asktoid=(String)request.getParameter("asktoid");
			String advice=(String)request.getParameter("advice");
			try {
				Ask ask=new Ask();
				ask.setAsktoid(asktoid);
				ask.setStudentid(uid);
				ask.setStudentname(uname);
				ask.setAskcontent(advice);
				if(DAOFactory.getAskDAOInstance().AddAsk(ask)==1){
					 PrintWriter out=response.getWriter();
					out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Report your advice or questions Successfully!');");
					out.println("window.history.back(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//TA report
		if(type.equals("TAReport")){
			try {
				//teacher id
				String sql2="select * from users join kcdesc on users.uid=kcdesc.teacherid where kcid='"+assistant+"'";
				List teacher=DAOFactory.getUserDAOInstance().getUsers(sql2);
				HashMap m=(HashMap)teacher.get(0);
				System.out.println("teacher:"+m.get("uid")+"  representative:"+representative);
				String t=""+m.get("uid");
				request.setAttribute("teacherid", t);
				
				request.getRequestDispatcher("student/TAReport.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//课代表收集问题
		if(type.equals("RCollect")){
			try {
				//student feedback
				String sql="select * from ask where  NOT EXISTS (select * from answer where ask.askid=answer.askid) and toid='"+uid+"'";
				List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql);
				request.setAttribute("asklist", asklist);
				
				//teacher id
				String sql2="select * from users join kcdesc on users.uid=kcdesc.teacherid where kcid='"+representative+"'";
				List teacher=DAOFactory.getUserDAOInstance().getUsers(sql2);
				HashMap m=(HashMap)teacher.get(0);
				System.out.println("teacher:"+m.get("uid")+"  representative:"+representative);
				String t=""+m.get("uid");
				request.setAttribute("teacherid", t);
				
				request.getRequestDispatcher("student/RcollectAsk.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//老师收集问题
		if(type.equals("TCollectOrigin")){
			String from=(String)request.getParameter("from");
			request.setAttribute("currentclass", classlist.get(0));
			try {
				
				if(from.equals("R")){
					String sql="select * from users where representative='"+classlist.get(0)+"'";
					List r=DAOFactory.getUserDAOInstance().getUsers(sql);//查课代表是谁
					
					if(r.size()>0){
						HashMap m=(HashMap)r.get(0);
						String sql2="select * from ask where NOT EXISTS (select * from answer where ask.askid=answer.askid) and askerid='"+m.get("uid")+"'";
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql2);
						request.setAttribute("asklist", asklist);
						
					}else{
					request.setAttribute("asklist", null);
					}
					request.getRequestDispatcher("teacher/fromRAsk.jsp").forward(request, response);
				}
				
				if(from.equals("TA")){
					String sql="select * from users where assistant='"+classlist.get(0)+"'";
					List ta=DAOFactory.getUserDAOInstance().getUsers(sql);//查ta是谁
					
					if(ta.size()>0){
						HashMap m=(HashMap)ta.get(0);
						String sql2="select * from ask  where NOT EXISTS (select * from answer where ask.askid=answer.askid) and ask.askerid='"+m.get("uid")+"'";
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql2);
						request.setAttribute("asklist", asklist);
						
					}else{
					request.setAttribute("asklist", null);
					}
					request.getRequestDispatcher("teacher/FromTAAsk.jsp").forward(request, response);
				}
				if(from.equals("student")){
					//第一门课的所有学生
					String sql="select * from users join class on users.uid=class.userid where kc1='"+classlist.get(0)+"' or kc2='"+classlist.get(0)+"' or kc3='"+classlist.get(0)+"' or kc4='"+classlist.get(0)+"' or kc5='"+classlist.get(0)+"' or kc6='"+classlist.get(0)+"' or kc7='"+classlist.get(0)+"' or kc8='"+classlist.get(0)+"' or kc9='"+classlist.get(0)+"' or kc10='"+classlist.get(0)+"'";
					
					List studentlist=DAOFactory.getUserDAOInstance().searchTableClassUsers(sql);
					if(studentlist.size()>0){
						String sql3="select * from ask where NOT EXISTS (select * from answer where ask.askid=answer.askid) and";
							for(int i=0;i<studentlist.size();i++){
								HashMap m=(HashMap)studentlist.get(i);
						         if(i==0){
						        	 sql3=sql3+" (askerid='"+m.get("uid")+"'";
						         }else{
						         sql3=sql3+" or askerid='"+m.get("uid")+"'";}
							}
							sql3=sql3+" )";
							
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql3);
						System.out.println(asklist);
						System.out.println(studentlist);
						request.setAttribute("asklist", asklist);
					}else{
						request.setAttribute("asklist", null);//没学生
					}
					request.getRequestDispatcher("teacher/FromStudentAsk.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type.equals("TCollect")){
			String from=(String)request.getParameter("from");
			String currentclass=(String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass);
			
			
			try {
				
				if(from.equals("R")){
					String sql="select * from users where representative='"+currentclass+"'";
					List r=DAOFactory.getUserDAOInstance().getUsers(sql);//查课代表是谁
					
					if(r.size()>0){
						HashMap m=(HashMap)r.get(0);
						//System.out.println("R id:"+m.get("uid"));
						String sql2="select * from ask  where NOT EXISTS (select * from answer where ask.askid=answer.askid) and ask.askerid='"+m.get("uid")+"'";
						
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql2);
						System.out.println("teacher collect R:"+asklist);
						request.setAttribute("asklist", asklist);
						
					}else{
					request.setAttribute("asklist", null);
					}
					request.getRequestDispatcher("teacher/fromRAsk.jsp").forward(request, response);
				}
				if(from.equals("TA")){
					String sql="select * from users where assistant='"+currentclass+"'";
					List ta=DAOFactory.getUserDAOInstance().getUsers(sql);//查ta是谁
					
					if(ta.size()>0){
						HashMap m=(HashMap)ta.get(0);
						String sql2="select * from ask  where NOT EXISTS (select * from answer where ask.askid=answer.askid) and askerid='"+m.get("uid")+"'";
						
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql2);
						request.setAttribute("asklist", asklist);
						
					}else{
					request.setAttribute("asklist", null);
					}
					request.getRequestDispatcher("teacher/FromTAAsk.jsp").forward(request, response);
				}
				if(from.equals("student")){
					//第一门课的所有学生
					String sql="select * from users join class on users.uid=class.userid where kc1='"+currentclass+"' or kc2='"+currentclass+"' or kc3='"+currentclass+"' or kc4='"+currentclass+"' or kc5='"+currentclass+"' or kc6='"+currentclass+"' or kc7='"+currentclass+"' or kc8='"+currentclass+"' or kc9='"+currentclass+"' or kc10='"+currentclass+"'";
					List studentlist=DAOFactory.getUserDAOInstance().searchTableClassUsers(sql);
					if(studentlist.size()>0){
						String sql3="select * from ask where NOT EXISTS (select * from answer where ask.askid=answer.askid) and";
							for(int i=0;i<studentlist.size();i++){
								HashMap m=(HashMap)studentlist.get(i);
						         if(i==0){
						        	 sql3=sql3+" (askerid='"+m.get("uid")+"'";
						         }else{
						             sql3=sql3+" or askerid='"+m.get("uid")+"'";
						         }
							}
							sql3+=")";
							System.out.println(sql3);
						List asklist=DAOFactory.getAskDAOInstance().ListAsk(sql3);
						System.out.println(asklist);
						request.setAttribute("asklist", asklist);
					}else{
						request.setAttribute("asklist", null);//没学生
					}
					request.getRequestDispatcher("teacher/FromStudentAsk.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("feedbackOrigin")){
			request.setAttribute("currentclass", classlist.get(0));
			
			try {
				//teacher id
				String sql2="select * from users join kcdesc on users.uid=kcdesc.teacherid where kcid='"+classlist.get(0)+"'";
				List teacher=DAOFactory.getUserDAOInstance().getUsers(sql2);
				HashMap m=(HashMap)teacher.get(0);
				String teacherid=""+m.get("uid");
				
				String sql="select * from ask join answer on ask.askid=answer.askid where answercontent<>'' and teacherid='"+teacherid+"' order by answertime desc";
				List asklist=DAOFactory.getAskDAOInstance().ListJoinQA(sql);
				request.setAttribute("asklist", asklist);
				request.getRequestDispatcher("student/feedback.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type.equals("feedback")){
			String currentclass=(String)request.getParameter("kc");
			request.setAttribute("currentclass", currentclass);
			try {
				//teacher id
				String sql2="select * from users join kcdesc on users.uid=kcdesc.teacherid where kcid='"+currentclass+"'";
				List teacher=DAOFactory.getUserDAOInstance().getUsers(sql2);
				HashMap m=(HashMap)teacher.get(0);
				String teacherid=""+m.get("uid");
				
				String sql="select * from ask join answer on ask.askid=answer.askid where answercontent<>'' and teacherid='"+teacherid+"' order by answertime desc";
				List asklist=DAOFactory.getAskDAOInstance().ListJoinQA(sql);
				request.setAttribute("asklist", asklist);
				request.getRequestDispatcher("student/feedback.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	
}
