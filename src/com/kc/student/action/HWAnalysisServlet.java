package com.kc.student.action;

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

import com.kc.entity.ForChart;
import com.kc.entity.Homework;
import com.kc.factory.DAOFactory;

import net.sf.json.JSONArray;

public class HWAnalysisServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//String uid=(String) request.getSession().getAttribute("uid");
		String action=(String) request.getParameter("action");
		
		
		if(action.equals("singlehw")){
			int hwid=Integer.parseInt(request.getParameter("hwid"));
			ArrayList<Homework> array;
			try {
				array = (ArrayList<Homework>)DAOFactory.getHomeworkDAOInstance().ListForSingleHWChart(hwid);
				System.out.println("list for chart:"+array);
				JSONArray json=JSONArray.fromObject(array);
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
		if(action.equals("classhw")){
			String uid= (String)request.getParameter("uid");
		    String kcid= (String)request.getParameter("kcid");
		    ArrayList<Homework> array;
			try {
				array = (ArrayList<Homework>)DAOFactory.getHomeworkDAOInstance().ListForClassHWChart(kcid, uid);
				//System.out.println("list for chart:"+array);
				JSONArray json=JSONArray.fromObject(array);
				//System.out.println("json:"+json.toString());
				PrintWriter out = response.getWriter();  
		        out.println(json);  
		        out.flush();  
		        out.close(); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(action.equals("singlehwpie")){
			int hwid=Integer.parseInt(request.getParameter("hwid"));
			ArrayList<Homework> array;
			try {
				array = (ArrayList<Homework>)DAOFactory.getHomeworkDAOInstance().ListForSingleHWChart(hwid);
			//System.out.println("list for chart:"+array);
			List<ForChart> marklist=new ArrayList<ForChart>();
			int sec1=0,sec2=0,sec3=0,sec4=0,sec5=0,sec6=0,sec7=0,sec8=0;
			for(int i=0;i<array.size();i++){
					Homework m=new Homework(); 
					m=array.get(i);
					if(m.getResult()>=96){
						sec1++;
					}
					if(m.getResult()>=90&&m.getResult()<96){
						sec2++;
					}
					if(m.getResult()>=85&&m.getResult()<90){
						sec3++;
					}
					if(m.getResult()>=80&&m.getResult()<85){
						sec4++;
					}
					if(m.getResult()>=75&&m.getResult()<80){
						sec5++;
					}
					if(m.getResult()>=70&&m.getResult()<75){
						sec6++;
					}
					if(m.getResult()>=60&&m.getResult()<70){
						sec7++;
					}
					if(m.getResult()<60){
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
