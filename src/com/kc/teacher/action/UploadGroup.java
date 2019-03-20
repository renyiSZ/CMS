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
import com.kc.entity.Group;
import com.kc.factory.DAOFactory;
import com.kc.service.GradeService;
import com.kc.service.HomeworkService;

public class UploadGroup extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//String uname =(String) request.getSession().getAttribute("uname");
		//String uid =(String) request.getSession().getAttribute("uid");
		
		PrintWriter out=response.getWriter();

		String fileName="";//文件名
		String filePath="";//文件路径
		String classID="";// 作业，资料对应的课程号
		String hwid="";
		//String gname="";
		
		DiskFileItemFactory factory = new DiskFileItemFactory();  
        //创建解析类的实例  
        ServletFileUpload sfu = new ServletFileUpload(factory);  
    //开始解析  
        sfu.setFileSizeMax(1024*10*1024);  
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
				else if(formName.equals("hwid"))
					hwid=formContent;
				System.out.println("uploadServlet kcid + hwid"+classID+" "+hwid);
			}
			if(!fileItem.isFormField()){
				ServletContext sctx = getServletContext();  
                //获得存放文件的物理路径  
                //upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹  
              
                String path = sctx.getRealPath("group");  
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
                }      
			}
		
	     }
          if(!classID.equals("")&&!hwid.equals("")){
           System.out.println("wenjian addr:"+filePath);
           HomeworkService service=new HomeworkService();
           List<Group> grouplist = service.getAllByExcel(filePath,classID, hwid);
           
           for (Group group : grouplist) {
   			
   			//System.out.println("Group: "+group.getStudentid()+" "+group.getStudentname()+" "+group.getKcid()+" "+group.getGroupname()+" "+group.getHwid()+" ");
   		    DAOFactory.getHomeworkDAOInstance().addGroup(group);
   			
   		  }
           DAOFactory.getHomeworkDAOInstance().updateGroupStatus(hwid);
	        	   	out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Divide group OK!');");
					out.println("window.history.back(-1);");
					out.println("</script>");
					out.flush();
					out.close();
           }           
     } catch (Exception e) {  
            e.printStackTrace();  
     }
  	
	}

}
