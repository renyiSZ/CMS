package com.kc.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import com.kc.factory.DAOFactory;

public class DBToExcel extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		String type=(String)request.getParameter("type");
		if(type.equals("studentinfo")){
				String kcid=(String)request.getParameter("kcid");
				String sql="select * from users join class on users.uid=class.userid where kc1='"+kcid+"' or kc2='"+kcid+"' or kc3='"+kcid+"' or kc4='"+kcid+"' or kc5='"+kcid+"' or kc6='"+kcid+"' or kc7='"+kcid+"' or kc8='"+kcid+"' or kc9='"+kcid+"' or kc10='"+kcid+"'";
				try {
					List studentlist=DAOFactory.getUserDAOInstance().searchTableClassUsers(sql);
					
					WritableWorkbook wwb = null;
					 
					File file1 =new File("E:/kc");    
					//如果文件夹不存在则创建    
					if  (!file1 .exists()  && !file1 .isDirectory())      
					{       
					   System.out.println("//不存在");  
					    file1 .mkdir();    
					} 

					   // 创建可写入的Excel工作簿
					   String fileName = "E:/kc/"+kcid+".xls";
					   File file=new File(fileName);
					   if (!file.exists()) {
						   file.createNewFile();
					   }
					   //以fileName为文件名来创建一个Workbook
					   wwb = Workbook.createWorkbook(file);

					   // 创建工作表
					   WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
					   
					   //查询数据库中所有的数据
					  
					   //要插入到的Excel表格的行号，默认从0开始
					   Label labelNo= new Label(0, 0, "No.");//表示第
					   Label labelId= new Label(1, 0, "id");//表示第
					   Label labelName= new Label(2, 0, "name");
					   Label labelSex= new Label(3, 0, "sex");
					   Label labelMail= new Label(4, 0, "email");
					   Label labelPhone= new Label(5, 0, "phone");
					   ws.addCell(labelNo);
					   ws.addCell(labelId);
					   ws.addCell(labelName);
					   ws.addCell(labelSex);
					   ws.addCell(labelMail);
					   ws.addCell(labelPhone);
					   
					   for (int i = 0; i < studentlist.size(); i++) {
						   HashMap m= (HashMap) studentlist.get(i);
						   Label labelNo_i= new Label(0, i+1, (i+1)+"");
						   Label labelId_i= new Label(1, i+1, m.get("uid")+"");
						   Label labelName_i= new Label(2, i+1, ""+m.get("uname"));
						   Label labelSex_i= new Label(3, i+1, ""+m.get("usex"));
						   Label labelMail_i= new Label(4, i+1,m.get("uemail")+"");
						   Label labelPhone_i= new Label(5, i+1, m.get("uphone")+"");
						   ws.addCell(labelNo_i);
						   ws.addCell(labelId_i);
						   ws.addCell(labelName_i);
						   ws.addCell(labelSex_i);
						   ws.addCell(labelMail_i);
						   ws.addCell(labelPhone_i);
					   }
					 
					  //写进文档
					   wwb.write();
					  // 关闭Excel工作簿对象
					   wwb.close();

						PrintWriter out=response.getWriter();
					   out.println("<script type='text/javascript' language='javascript'>");
						out.println("alert('Export Ok Check your kc folder in disk E!');");
						out.println("window.history.back(-1);");
						out.println("</script>");
						out.flush();
						out.close();
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	}

	
	
}
