<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ page import="com.kc.entity.Users"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacher.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
<style>
.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:160px; height:260px; word-wrap:break-word;}
		.imgdiv{margin:5px; width:150px; height:150px; background:black; }
</style>
  </head>
  
  <body>
   <%
    String id =(String)request.getSession().getAttribute("uid");
  	 Map map = DAOFactory.getUserDAOInstance().searchClass(id);
  	 String sql= "select * from kcdesc join users on kcdesc.teacherid=users.uid where ";
  	 
  	 		if(map.get("kc1")!=null){
  	 			sql= sql+"kcid='"+map.get("kc1")+"' ";
  	 		
  	 		if(map.get("kc2")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc2")+"' ";
  	 		if(map.get("kc3")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc3")+"' ";  
  	 		if(map.get("kc4")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc4")+"' ";  
  	 		if(map.get("kc5")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc5")+"' "; 
  	 		if(map.get("kc6")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc6")+"' ";
  	 		if(map.get("kc7")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc7")+"' ";  
  	 		if(map.get("kc8")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc8")+"' ";   
  	 		if(map.get("kc9")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc9")+"' ";  
  	 		if(map.get("kc10")!=null)
  	 		   sql= sql+"or kcid='"+map.get("kc10")+"' ";  
  	 		 
  	 		    
  	 		List l=  DAOFactory.getClassInfoDAOInstance().getJoinKCandUsers(sql); 
  	 		if(l.size()>0){
				for(int i=0;i<l.size();i++){
					HashMap m=(HashMap) l.get(i);
					
	%>
	<div class="videodiv">
	 	<div class="imgdiv">
			 <img src="<%=m.get("head") %>" width="150px" height="150px"></img>
   	 	</div>
   	 	&nbsp;<%= m.get("kcid")%></br>
   	  	<i class="fa fa-user" aria-hidden="true"></i>&nbsp;<%=m.get("teachername")%></br>
	 	<i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;<%=m.get("email")%></br>
	 	<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;<%=m.get("phone")%></br>
   	</div>
   <%} }}else{out.println("no teacher");} 
   %>
  </body>
</html>
