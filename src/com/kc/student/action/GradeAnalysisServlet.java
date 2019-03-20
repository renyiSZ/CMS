package com.kc.student.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.kc.entity.ForChart;
import com.kc.entity.Grade;
import com.kc.entity.Homework;
import com.kc.factory.DAOFactory;

public class GradeAnalysisServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action=(String) request.getParameter("action");
		
		if(action.equals("singletestbar")){
			String kcid=request.getParameter("kcid");
			String gname=request.getParameter("gname");
			
			List<Grade> gradelist;
			try {
				String sql="select * from grade where kcid='"+kcid+"' and gname='"+gname+"' order by grade desc";
				gradelist= (List<Grade>)DAOFactory.getGradeDAOInstance().getAllGrade(sql);
			
				JSONArray json=JSONArray.fromObject(gradelist);
				System.out.println("json:"+json.toString());
				PrintWriter out = response.getWriter();  
		        out.println(json);  
		        out.flush();  
		        out.close(); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(action.equals("singletestpie")){
			String kcid=request.getParameter("kcid");
			String gname=request.getParameter("gname");
			List<Grade> gradelist;
			try {
				String sql="select * from grade where kcid='"+kcid+"' and gname='"+gname+"'";
				gradelist= (List<Grade>)DAOFactory.getGradeDAOInstance().getAllGrade(sql);
			
			List<ForChart> marklist=new ArrayList<ForChart>();
			int sec1=0,sec2=0,sec3=0,sec4=0,sec5=0,sec6=0,sec7=0,sec8=0;
			for(int i=0;i<gradelist.size();i++){
					Grade g=new Grade(); 
					g=gradelist.get(i);
					if(g.getGrade()>=96){
						sec1++;
					}
					if(g.getGrade()>=90&&g.getGrade()<96){
						sec2++;
					}
					if(g.getGrade()>=85&&g.getGrade()<90){
						sec3++;
					}
					if(g.getGrade()>=80&&g.getGrade()<85){
						sec4++;
					}
					if(g.getGrade()>=75&&g.getGrade()<80){
						sec5++;
					}
					if(g.getGrade()>=70&&g.getGrade()<75){
						sec6++;
					}
					if(g.getGrade()>=60&&g.getGrade()<70){
						sec7++;
					}
					if(g.getGrade()<60){
						sec8++;
					}
			}
			if(sec1!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("95-100");
				forchart.setNumber(sec1);
				marklist.add(forchart);
			}
			if(sec2!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("90-94");
				forchart.setNumber(sec2);
				marklist.add(forchart);
			}	
			if(sec3!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("85-89");
				forchart.setNumber(sec3);
				marklist.add(forchart);
			}	
			if(sec4!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("80-84");
				forchart.setNumber(sec4);
				marklist.add(forchart);
			}
			if(sec5!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("75-79");
				forchart.setNumber(sec5);
				marklist.add(forchart);
			}	
			if(sec6!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("70-74");
				forchart.setNumber(sec6);
				marklist.add(forchart);
			}	
			if(sec7!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("60-69");
				forchart.setNumber(sec7);
				marklist.add(forchart);
			}	
			if(sec8!=0){
				ForChart forchart=new ForChart();
				forchart.setMarksection("below 60");
				forchart.setNumber(sec8);
				marklist.add(forchart);
			}	
			JSONArray json=JSONArray.fromObject(marklist);
			System.out.println("json:"+json.toString());
			PrintWriter out = response.getWriter();  
	        out.println(json);  
	        out.flush();  
	        out.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  }

}
