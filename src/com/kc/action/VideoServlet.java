package com.kc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kc.entity.Video;
import com.kc.factory.DAOFactory;
import com.kc.utils.VideoJT;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class VideoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.reset();//清除缓存中的内容
		response.setContentType("application/x-download");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		InputStream is=null;
		OutputStream os=null;//文件下载输入输出流
		int vid=Integer.parseInt(request.getParameter("vid"));
		try {
			Map map=DAOFactory.getVideoDAOInstance().queryByVid(vid);
			String vlink=(String) map.get("vlink");
			ServletContext sctx = this.getServletContext();
	        String basePath = sctx.getRealPath("video");
			String path=basePath+"\\"+"transcode"+"\\";
			String fileName=vlink.substring(vlink.lastIndexOf("/")+1,vlink.length());
			path=path+fileName;
			//System.out.println(path);
			/////////////////
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
		String uhead =(String) request.getSession().getAttribute("uhead");
		PrintWriter out=response.getWriter();
		
		String fileName="";//文件名
		String filePath="";//文件路径
		String message = "";
		 
		DiskFileItemFactory factory = new DiskFileItemFactory();
         
         //创建一个解析器,分析InputStream,该解析器会将分析的结果封装成一个FileItem对象的集合
         //一个FileItem对象对应一个表单域
         ServletFileUpload sfu = new ServletFileUpload(factory);
     
         try {
             Video video = new Video();
             video.setUserhead(uhead);
             video.setUserid(uid);
             video.setUsername(uname);
             List<FileItem> items = sfu.parseRequest(request);
             boolean flag = false;    //转码成功与否的标记
             for(int i=0; i<items.size(); i++){
                 FileItem item = items.get(i);
                 //要区分是上传文件还是普通的表单域
                 if(item.isFormField()){//isFormField()为true,表示这不是文件上传表单域
                     //普通表单域
                     String paramName = item.getFieldName();
                     /*
                         String paramValue = item.getString();
                         System.out.println("参数名称为:" + paramName + ", 对应的参数值为: " + paramValue);
                     */
                     if(paramName.equals("videotitle")){
                         video.setVtitle(new String(item.getString("UTF-8")));
                     }
                     if(paramName.equals("description")){
                     	video.setVcontent(new String(item.getString("UTF-8")));
                     }
                     if(paramName.equals("classid")){
                      	video.setKcid(new String(item.getString("UTF-8")));
                      }
                     if(paramName.equals("videotype")){
                      	//video.setVtype(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
                    	 video.setVtype(new String(item. getString("UTF-8")));
                      }
                     
                 }else{
                	fileName=item.getName();//获取上传文件的名称
     				fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
     				String name=fileName.substring(0,fileName.lastIndexOf("."));
                     //上传文件
                     //System.out.println("上传文件" + item.getName());
                     ServletContext sctx = this.getServletContext();
                     //获得保存文件的路径
                     String basePath = sctx.getRealPath("video");
                     filePath=basePath;
          
                     //待转码的文件
                     File uploadFile = new File(basePath+"/"+fileName);
                     item.write(uploadFile);
                     
                     if(item.getSize()>500*1024*1024){
                         message = "<li>上传失败！您上传的文件太大,系统允许最大文件500M</li>";
                     }
                    
                     String codcFilePath=basePath + "/transcode/"+name + ".flv";
                     String picpath = basePath + "/temp" +File.separator+name+".jpg";
                     // 获取配置的转换工具（ffmpeg.exe）的存放路径
                     String ffmpegPath = getServletContext().getRealPath("/tools/ffmpeg.exe");
                     
                     video.setVlink("video/transcode/" + name + ".flv");
                     video.setVpic("video/temp/" +name + ".jpg");
                    
                     //转码
                     
                     flag =VideoJT.processVideo(uploadFile.getAbsolutePath(),ffmpegPath,picpath, codcFilePath );
                 }
             }
             if(flag){
                 //转码成功,向数据表中添加该视频信息
            	 try {
 					if (DAOFactory.getVideoDAOInstance().saveVideo(video) == 1) {
 						System.out.println("db store video successfully");
 					}
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
                 message = "<li>上传成功!</li>";
             }
             out.println("<script type='text/javascript' language='javascript'>");
 			 out.println("alert('Upload Successfully!');");
 			out.println("window.location.href('teacher/uploadVideo.jsp');");
 			 out.println("</script>");
 			 out.flush();
 			 out.close();
            // request.setAttribute("message", message);
             //////////////////////////////////////////////////////////////
             //request.getRequestDispatcher("teacher/uploadVideo.jsp").forward(request,response);
         
         }catch (Exception e) {
                 e.printStackTrace();
                 throw new ServletException(e);
         }
	}
}
	


