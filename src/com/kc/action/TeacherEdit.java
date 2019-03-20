package com.kc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.kc.entity.Material;
import com.kc.factory.DAOFactory;
import com.kc.factory.DAOFactory;
public class TeacherEdit extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Edit=request.getParameter("Edit");
		if(Edit.equals("material")){
			int mid=Integer.parseInt(request.getParameter("mid"));
			try {
				Map map=DAOFactory.getMaterialDAOInstance().queryByMid(mid);
				request.setAttribute("map", map);
				request.getRequestDispatcher("teacher/editSlides.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Edit.equals("materialdelete")){
			int mid=Integer.parseInt(request.getParameter("mid"));
			try {
				if(DAOFactory.getMaterialDAOInstance().deleteMaterial(mid)==1)
				
				   request.getRequestDispatcher("teacher/mySlide.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Edit.equals("videodelete")){
			int vid=Integer.parseInt(request.getParameter("vid"));
			try {
				if(DAOFactory.getVideoDAOInstance().deleteVideo(vid)==1)
				
				   request.getRequestDispatcher("teacher/myVideo.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Edit.equals("adminvideodelete")){
			int vid=Integer.parseInt(request.getParameter("vid"));
			try {
				if(DAOFactory.getVideoDAOInstance().deleteVideo(vid)==1)
				
				   request.getRequestDispatcher("admin/editVideo.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String uid =(String) request.getSession().getAttribute("uid");
			int mid=0;
			String status="";
			String description="";
			String materialtype="";
			String classid="";
			String mlink="";
			String mtitle="";
			 Material material=new Material();
			 PrintWriter out=response.getWriter();
			 String fileName="";//文件名
			String filePath="";//文件路径
			DiskFileItemFactory factory = new DiskFileItemFactory();
	         //创建一个解析器,分析InputStream,该解析器会将分析的结果封装成一个FileItem对象的集合
	         //一个FileItem对象对应一个表单域
	        ServletFileUpload sfu = new ServletFileUpload(factory);
			
	         
			try {
				List<FileItem> items = sfu.parseRequest(request);
	             boolean flag = false;    //转码成功与否的标记
	             for(int i=0; i<items.size(); i++){
	                 FileItem item = items.get(i);
	                 //要区分是上传文件还是普通的表单域
	                 if(item.isFormField()){//isFormField()为true,表示这不是文件上传表单域
	                     //普通表单域
	                     String paramName = item.getFieldName();
	                     if(paramName.equals("mid")){
	                         mid= Integer.parseInt(new String(item.getString("UTF-8")));
	                         System.out.println("mid: "+mid);
	                     }
	                     if(paramName.equals("status")){
	                         status= new String(item.getString("UTF-8"));
	                         System.out.println("status: "+status);
	                     }
	                     if(paramName.equals("description")){
	                         description= new String(item.getString("UTF-8"));
	                         System.out.println("description: "+description);
	                     }
	                     if(paramName.equals("materialtype")){
	                         materialtype= new String(item.getString("UTF-8"));
	                         System.out.println("materialtype: "+materialtype);
	                     }
	                     if(paramName.equals("classid")){
	                         classid= new String(item.getString("UTF-8"));
	                         System.out.println("classid: "+classid);
	                     }
	                     if(paramName.equals("mlink")){
	                         mlink= new String(item.getString("UTF-8"));
	                         System.out.println("classid: "+classid);
	                     }
	                     if(paramName.equals("mtitle")){
	                         mtitle= new String(item.getString("UTF-8"));
	                         System.out.println("mtitle: "+mtitle);
	                     }
	                 } 
	                 if(status.equals("nofile")&&mid!=0&&!mtitle.equals("")&&!classid.equals("")&&!description.equals("")&&!materialtype.equals("")){ 
	                	 DAOFactory.getMaterialDAOInstance().deleteMaterial(mid);
	                	
	                	 material.setKcid(classid);
	                	 material.setMdesc(description);
	                	
	                	 material.setMlink(mlink);
	                	 material.setMtitle(mtitle);
	                	 material.setUserid(uid);
	                	 material.setMtype(materialtype);
	                	 DAOFactory.getMaterialDAOInstance().addMaterial(material);
		            	 System.out.println("edit material OK");
	                	 break;
	                 }
	                 //if(status.equals("file")&&mid!=0&&!mtitle.equals("")&&!classid.equals("")&&!description.equals("")&&!materialtype.equals(""))
	                 if(!item.isFormField())
	                 { 
	                	 fileName=item.getName();//获取上传文件的名称
	                	 System.out.println(fileName);
	                	 if(!"".equals(fileName)){

		            	    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		            	    ServletContext sctx = this.getServletContext();
		            	   
		            	    filePath="F:/tmpFile";
		            	    File uploadFile = new File(filePath+"/"+fileName);
		            	    item.write(uploadFile);
		     					
		            	 
	                	}	
		     		}
		            	 
	             }
	             if(status.equals("file")) {
	            	 File file=new File(mlink+"\\"+mtitle);
            		 if (!file.exists()) {
            			 System.out.println("文件不存在");
            		 }
            		 else{
            			 file.delete();
            			 System.out.println("delete previous material OK");
            			 DAOFactory.getMaterialDAOInstance().deleteMaterial(mid);
	            	      System.out.println("delete previous material table OK");  
            		 }
            		 material.setKcid(classid);
	            	  material.setMdesc(description);
	            	    material.setMlink(filePath);
	            	    material.setMtitle(fileName);
	            	    material.setUserid(uid);
	            	    material.setMtype(materialtype);
	            	    DAOFactory.getMaterialDAOInstance().addMaterial(material);
	            	    System.out.println("edit material OK");
            		 
	             }
	             
	             out.println("<script type='text/javascript' language='javascript'>");
	 			 out.println("alert('Edit Successfully!');");
	 			 out.println("window.location.href('teacher/mySlide.jsp');");
	 			 out.println("</script>");
	 			 out.flush();
	 			 out.close();
	           
	             
			//	   request.getRequestDispatcher("teacher/editSlides.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
