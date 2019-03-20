<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShareNote.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

	 <style>
		button{ background: #D0EEFF;}
		.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:100px; height:170px;word-wrap:break-word; font-size:11; color:grey; }
		.imgdiv{margin:0px; width:100px; height:100px; background:black; }
			
	</style>
  </head>
  
  <body>
  <% 
  ArrayList classlist = (ArrayList)session.getAttribute("classlist");  
   //String uid = (String)session.getAttribute("uid"); 
  String currentclass=(String)request.getAttribute("currentclass"); 
  List notelist =(List)request.getAttribute("notelist");
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/ShareServlet" method="post" >
 <select name="kc" style="width:200px; height:40px;">
 <option value="<%=currentclass %>"  ><%=currentclass %></option> 
			<%
			 	for(int i=0;i<classlist.size();i++){
			 	String c=""+classlist.get(i);
			 	if(!c.equals(currentclass)){
			 
			 %>
				<option value="<%=classlist.get(i) %>"  > <%=classlist.get(i) %></option> 
			<%
				}}
			 %>	
</select><input type="hidden" name="type" value="teachernote">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
  
  <fieldset class="layui-elem-field">
  
     
    <%
    if(notelist.size()>0){
    for(int i=0;i<notelist.size();i++){
         HashMap m=(HashMap)notelist.get(i);
         String link=""+m.get("sharelink");
		 String filename=link.substring(link.lastIndexOf("\\")+1);
		 String filetype=link.substring(link.lastIndexOf(".")+1);
		 System.out.println("filename:"+filename);
		 System.out.println("filetype:"+filetype);
		 String filepic="images/file.jpg";
		 if(filetype.equals("doc")||filetype.equals("docx")) filepic="images/doc.jpg";
    	 if(filetype.equals("jpg")) filepic="images/jpg.jpg";
    	 if(filetype.equals("pdf")) filepic="images/pdf.jpg";
    	 if(filetype.equals("ppt")) filepic="images/ppt.jpg";
    	 if(filetype.equals("rar")) filepic="images/rar.jpg";
    	 if(filetype.equals("zip")) filepic="images/zip.jpg";
    	 if(filetype.equals("xls")) filepic="images/xls.jpg";
    	 if(filetype.equals("txt")) filepic="images/txt.jpg";
    	  if(filetype.equals("mp3")) filepic="images/mp3.jpg";
    	 String time=""+m.get("sharetime");
    	 time=time.substring(0,10);
    	 String uploaderid=""+m.get("uploaderid");
     %>
    
    <div class="videodiv">
	 <div class="imgdiv">
	 	<a href="<%=path%>/UploadShare?downloadID=<%=m.get("shareid")%>">
	 	<img src="<%=filepic%>" width="100px" height="100px"></img>
		</a>
	 </div>
	 &nbsp;<%=filename%></br>
	 &nbsp;<%=m.get("uploadername")%></br>
	  &nbsp;<%=time%>&nbsp;
	  
	  <a href="<%=path%>/ShareServlet?type=deletenote&noteid=<%=m.get("shareid")%>&kc=<%=currentclass%>&person=teacher"><i class="fa fa-trash-o " aria-hidden="true"></i> </a>
	 
	</div>
      
      
    <%
    }}
     %></br></br>
  </fieldset>
  </div>

  </body>
</html>
