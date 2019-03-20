package com.kc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kc.entity.Homework;
import com.kc.factory.DAOFactory;

public class HomeworkServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.reset();//清除缓存中的内容
		
		response.setContentType("application/x-download");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		InputStream is=null;
		OutputStream os=null;//文件下载输入输出流
		
		int downloadID=Integer.parseInt(request.getParameter("downloadID"));
		
		
		try {
			Map map=DAOFactory.getHomeworkDAOInstance().queryByhwid(downloadID);
			String path=""+map.get("hwlink");
			/////////////////
			String fileName=path.substring(path.lastIndexOf("\\")+1);
			os=response.getOutputStream();//初始化输出流
			File file=new File(path);
			is=new FileInputStream(new File(path));
			response.addHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(""+fileName,"UTF-8"));
			response.addHeader("Content-Length",new Long(file.length()).toString());
			
			int i=-1;//文件是否读取结束的标志
			byte[] b=new byte[1024];
			i=is.read(b);
			while(i!=-1){
				os.write(b,0,i);
				i=is.read(b);
			}
			is.close();
			os.flush();
	    	os.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		String title="";
		String fileDesc="";//文件介绍  老师上传时使用
		String classID="";// 作业，资料对应的课程号
		String open="";
		
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
				else if(formName.equals("description"))
					fileDesc=formContent;
				else if(formName.equals("homeworktitle"))
					title=formContent;
				else if(formName.equals("open")){
					open=formContent;
					System.out.println("open:"+open);
				}
				
			}
			if(!fileItem.isFormField()){
				ServletContext sctx = getServletContext();  
                //获得存放文件的物理路径  
                //upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹  
              
                String path = sctx.getRealPath("homework");  
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
           if(!classID.equals("")){
             Homework hw= new Homework();
             hw.setKcid(classID);
             hw.setUserid(uid);
             hw.setUsername(uname);
             hw.setHomeworktitle(title);
             hw.setHomeworklink(filePath);
             hw.setHomeworkdesc(fileDesc);
             if(open.equals("on")){
            	 hw.setAnswerlink("group");
             }
             
	          if(DAOFactory.getHomeworkDAOInstance().addHomework(hw)==1){
	        	  int hwid=DAOFactory.getHomeworkDAOInstance().queryHWID(title, classID, uid, filePath);
		          //xuesheng list
		          String s = "select class.userid,users.uname from class join users on class.userid=users.uid where kc1='"+classID+"' or kc2='"+classID+"' or kc3='"+classID+"' or kc4='"+classID+"' or kc5='"+classID+"' or kc6='"+classID+"' or kc7='"+classID+"' or kc8='"+classID+"' or kc9='"+classID+"' or kc10='"+classID+"'";
		          List studentlist=DAOFactory.getUserDAOInstance().searchStudent(s);
		          for(int k=0; k<studentlist.size();k++){
		        	  HashMap m=(HashMap) studentlist.get(k);
		        	  DAOFactory.getHomeworkDAOInstance().addHWforCorrect(hwid,classID,""+m.get("studentid"),""+m.get("studentname"),uid);
		          }
		          
	        	   	out.println("<script type='text/javascript' language='javascript'>");
					out.println("alert('Asign homework OK!');");
					out.println("window.location.href='teacher/addHW.jsp';");
					out.println("</script>");
					out.flush();
					out.close();
	          }
	          
           }  
	        
	          
     } catch (Exception e) {  
            e.printStackTrace();  
     }
       
	}
}
	


