package com.kc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

public class HWforCorrect extends HttpServlet {

	
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
			Map map=DAOFactory.getHomeworkDAOInstance().queryByCorrectid(downloadID);
			String path=""+map.get("correctlink");
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
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");  
		String uid=(String) request.getSession().getAttribute("uid");
		String uname=(String) request.getSession().getAttribute("uname");
		 int homeworkid=0;
		 String teacherid="";
		 String kcid="";
		 String filePath="";
		 int status=9;
		      PrintWriter out=response.getWriter();
		        //为解析类提供配置信息  
		       DiskFileItemFactory factory = new DiskFileItemFactory();  
		        //创建解析类的实例  
		       ServletFileUpload sfu = new ServletFileUpload(factory);  
	        //开始解析  
		        sfu.setFileSizeMax(1024*400*1024);  
	       //每个表单域中数据会封装到一个对应的FileItem对象上  
		        try {  
		            List<FileItem> items = sfu.parseRequest(request);  
		            //区分表单域  
		           for (int i = 0; i < items.size(); i++) {  
		                FileItem item = items.get(i);  
		                //isFormField为true，表示这不是文件上传表单域 
		             if(item.isFormField()){ //判断上传内容是普通文本域还是文件域
		    				String formName=item.getFieldName();
		    				
		    				String formContent=item.getString("UTF-8");
		    				//必要时修改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
		    				////////////////////////获取表单中的课程号
		    				if(formName.equals("homeworkid"))
		    					homeworkid=Integer.parseInt(formContent);
		    				////////////////////获取表单中的文件简介
		    				else if(formName.equals("teacherid"))
		    					teacherid=formContent;
		    				else if(formName.equals("kcid"))
		    					kcid=formContent;
		    			}    
		                
	                 if(!item.isFormField()){  
		                    ServletContext sctx = getServletContext();  
		                    //获得存放文件的物理路径  
		                    //upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹  
	                      
		                    String path = sctx.getRealPath("homework/forcorrect");  
		                    System.out.println("path:"+path);  
		                    //获得文件名  
		                    String fileName = item.getName();  
		                   
		                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		                    System.out.println("filename:"+fileName); 
		                    
		                    filePath=path+"\\"+fileName;
		                    File file = new File(path+"\\"+fileName);  
		                        item.write(file);  
		                        //将上传图片的名字记录到数据库中         
	                }  
		          } 
		          Homework correct=new Homework(); 
		          
		          correct.setHomeworkid(homeworkid);
		          correct.setStatus(0);
		          correct.setStudentid(uid);
		          correct.setStudentname(uname);
		          correct.setCorrectlink(filePath);
		          correct.setUserid(teacherid);
		          correct.setKcid(kcid);
		          
		          if(DAOFactory.getHomeworkDAOInstance().handinUpdate(correct)==1){
		        	 
		        	  out.println("<script type='text/javascript' language='javascript'>");
						out.println("alert('Hand in homework Successfully!');");
						out.println("window.location.href='StudentHomeworkServlet?require=handin&action=allClassHW';");
						out.println("</script>");
						out.flush();
						out.close();
		          }
		        }catch (Exception e) {  
		            e.printStackTrace();  
		        }   

	}

}
