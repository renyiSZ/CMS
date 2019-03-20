package com.kc.teacher.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kc.entity.Grade;

import com.kc.factory.DAOFactory;
import com.kc.service.GradeService;

public class GradeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uname =(String) request.getSession().getAttribute("uname");
		String uid =(String) request.getSession().getAttribute("uid");
		
		PrintWriter out=response.getWriter();

		String fileName="";//文件名
		String filePath="";//文件路径
		String classID="";// 作业，资料对应的课程号
		String gname="";
		
		DiskFileItemFactory factory = new DiskFileItemFactory();  
        //创建解析类的实例  
        ServletFileUpload sfu = new ServletFileUpload(factory);  
    //开始解析  
        sfu.setFileSizeMax(1024*50*1024);  
        try {  
            List<FileItem> items = sfu.parseRequest(request);  
            //区分表单域  
           for (int i = 0; i < items.size(); i++) {  
                FileItem fileItem = items.get(i);  
			
			if(fileItem.isFormField()){ //判断上传内容是普通文本域还是文件域
				String formName=fileItem.getFieldName();
				
				String formContent=fileItem.getString("UTF-8");
				//必要时修改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
				////////////////////////获取表单中的课程号
				if(formName.equals("classid"))
					classID=formContent;
				////////////////////获取表单中的文件简介
				//else if(formName.equals("gname"))
					//gname=formContent;	
			}
			if(!fileItem.isFormField()){
				ServletContext sctx = getServletContext();  
                //获得存放文件的物理路径  
                //upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹  
              
                String path = sctx.getRealPath("grade");  
                System.out.println("path:"+path);  
                //获得文件名  
                fileName = fileItem.getName();  
                fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                System.out.println("filename:"+fileName);  
                //该方法在某些平台(操作系统),会返回路径+文件名  
                filePath=path+"\\"+fileName;
                System.out.println("filepath:"+filePath);
                if(!fileName.equals("")){
                File file = new File(filePath);  
                    fileItem.write(file);  
                }   //将上传图片的名字记录到数据库中    
			}
		
	     }
           System.out.println("wenjian addr:"+filePath);
           GradeService service=new GradeService();
           List<Grade> gradelist = service.getAllByExcel(filePath, classID, uid, uname);
           
           for (Grade grade : gradelist) {
   			String studentid =grade.getStudentid();
   			String kcid= grade.getKcid();
   			String gradename=grade.getGname();
   			GradeService s=new GradeService();
   			if (!s.isExist(studentid,kcid,gradename)) {
   				DAOFactory.getGradeDAOInstance().addGrade(grade);
   			}else {
   				String sql="update grade set grade='"+grade.getGrade()+"' where studentid='"+studentid+"' and kcid='"+kcid+"' and gname='"+gradename+"'";
   				DAOFactory.getGradeDAOInstance().updateGrade(sql);
   			}
   		  }
	        	   	out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Release grade OK!');");
					out.println("window.location.href='GradeAction?type=gradeorigin';");
					out.println("</script>");
					out.flush();
					out.close();
	                   
     } catch (Exception e) {  
            e.printStackTrace();  
     }
  	
		
	}
}
