package com.kc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kc.factory.DAOFactory;
import com.kc.entity.Material;



public class Upload extends HttpServlet{
	
	public Upload(){
		super();
	}

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
			Map map=DAOFactory.getMaterialDAOInstance().queryByMid(downloadID);
			String path=""+map.get("mlink")+"\\"+map.get("mtitle");
			/////////////////
			
			os=response.getOutputStream();//初始化输出流
			File file=new File(path);
			is=new FileInputStream(new File(path));
			response.addHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(""+map.get("mtitle"),"UTF-8"));
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
		String materialFlag =(String) request.getSession().getAttribute("materialFlag");
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//String username=(String) request.getSession().getAttribute("uname");
		String userid=(String) request.getSession().getAttribute("uid");
		
		String fileName="";//文件名
		String filePath="";//文件路径
		
		String fileDesc="";//文件介绍  老师上传时使用
		String classID="";// 作业，资料对应的课程号
		String materialtype="";
		String jumpPath="";
		
		long fileSize=0;
		
		//创建上传文件硬盘对象
		DiskFileItemFactory disk= new DiskFileItemFactory();
		long maxSize=1024*1024*10;//最大10M
		disk.setSizeThreshold(10240);//上传缓冲区大小10k
		//String tmpFile=this.getServletContext().getRealPath("/");
		String tmpFile="F:/";
		
		File f=new File(tmpFile+"tmpFile");
		
		if(!f.exists()){
			f.mkdirs();//没有保存目录，则创建一个目录
		}
		disk.setRepository(f);
		
		//创建能够解析上传文件的二进制流的类
		ServletFileUpload sfu= new ServletFileUpload(disk);
		sfu.setSizeMax(maxSize);//设置上传最大容量
		
		List list=null;
		try{
			list=sfu.parseRequest(request);//完成对上传文件的解析，解析后内容放入list
			
		}catch(Exception e){
			out.println("<script language='javascript'>");
			out.println("alert('文件上传失败')");
			out.println("</script>");	
		}
		
		//遍历list对象中的内容，完成将文件保存到服务器
		Iterator iterator= list.iterator();
		int n=0;
		while(iterator.hasNext()){
			FileItem fileItem=(FileItem) iterator.next();//fileItem是上传文件的内容
			n++;
			
			if(fileItem.isFormField()){ //判断上传内容是普通文本域还是文件域
				String formName=fileItem.getFieldName();
				
				String formContent=fileItem.getString("UTF-8");
				
				
				//必要时修改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
				////////////////////////获取表单中的课程号
				if(formName.equals("classID"))
					classID=formContent;
				////////////////////获取表单中的文件简介
				else if(formName.equals("fileDesc"))
					fileDesc=formContent;
				else if(formName.equals("materialtype"))
					materialtype=formContent;
				
			}
			if(!fileItem.isFormField()){
				fileName=fileItem.getName();//获取上传文件的名称
				fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
				filePath=tmpFile+"tmpFile";//设置相对路径
				fileSize=fileItem.getSize();
				
				if(!"".equals(fileName)){
					//定义输出流
					FileOutputStream fos=new FileOutputStream(filePath+"\\"+fileName);
					
					if(fileItem.isInMemory()){//上传文件在缓冲区，则直接上传
						try{
							fos.write(fileItem.get());
							out.println("<script type='text/javascript' language='javascript' charset='UTF-8'>");
							out.println("alert('upload success!');");
							out.println("window.location.href='Upload.jsp';");
							out.println("</script>");
						}catch(Exception e){
							out.println("<script type='text/javascript' language='javascript' charset='UTF-8'>");
							out.println("alert('upload failed');");
							out.println("window.location.href='Upload.jsp';");
							out.println("</script>");	
						}finally{
							fos.close();
							fileItem.delete();
						}
						
					}else{//上传文件不在缓冲区，则进行标准io流操作
						InputStream is= fileItem.getInputStream();
					    try{
					    	
					    	int i=-1;
					    	byte[] b= new byte[1024*2];
					    	i=is.read(b);
					    	while(i!=-1){
					    		fos.write(b,0,i);//写入到服务器
					    		i=is.read(b);	
					    	}
					    	out.println("<script type='text/javascript' language='javascript' charset='UTF-8'>");
					    	out.println("alert('upload success!');");
					    	out.println("window.location.href='Upload.jsp';");
					    	out.println("</script>");
					    }catch(IOException e){
					    	out.println("<script type='text/javascript' language='javascript' charset='UTF-8'>");
					    	out.println("alert('upload error!');");
					    	out.println("window.location.href='Upload.jsp';");
					    	out.println("</script>");	
					    }finally{
					    	is.close();
					    	fos.flush();
					    	fos.close();
					    	fileItem.delete();
					    }
					
					}
				}
				
			}
			
			if(!"".equals(fileName)){//将文件信息存数据库
				Material m=new Material();
				m.setMtitle(fileName);
				m.setMlink(filePath);
				m.setMdesc(fileDesc);
				m.setUserid(userid);
				m.setKcid(classID);
				m.setMtype(materialtype);
				try {
					if (DAOFactory.getMaterialDAOInstance().addMaterial(m) == 1) {
						out.println("db store data successfully");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
		
		out.flush();
		out.close();
		
		
	}

}
