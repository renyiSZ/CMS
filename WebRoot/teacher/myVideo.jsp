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
    
    <title>My Online Courses</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
    <style>
    body{
	margin:0;
	padding:0;
	font-size:13px;
	
	color: #575757 ;
	
	}
		.allvideo{ border:1px solid #E5E5E5; margin:0px; width:100%; height:850px;}
		.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:200px; height:200px; }
		.imgdiv{margin:5px; width:190px; height:110px; background:black; }
			
	</style>
  </head>
  
  <body>
	<div class="admin-main">
<div class="allvideo">
   <blockquote class="layui-elem-quote">
    
     <button class="layui-btn layui-btn-small" onclick="javascript:location.href='<%=path%>/teacher/uploadVideo.jsp'">
      <i class="fa fa-file" aria-hidden="true">&nbsp;Upload New Video</i>
      </button>
    
    </blockquote>
    
    <%
    String id =(String)request.getSession().getAttribute("uid");
  	 String sql= "select * from video where userid='"+id+"'";
  	   
  	 		   
  	 		    
  	 		List l=  DAOFactory.getVideoDAOInstance().listVideo(sql); 
  	 		if(l.size()>0){
				for(int i=0;i<l.size();i++){
					HashMap m=(HashMap) l.get(i);
		%>
	<div class="videodiv">
	 <div class="imgdiv">
	 	<a href="<%=path %>/VideoDetailServlet?videoAction=listmyvideo&vid=<%=m.get("vid")%>&kcid=<%=m.get("kcid")%>">
	 	<img src="<%=m.get("vpic")%>" width="190px" height="110px"></img>
		</a>
	 </div>
	 &nbsp;Title:&nbsp;<%=m.get("vtitle")%></br>
	 &nbsp;<%=m.get("vtime")%></br>
	 &nbsp;<a href="<%=path %>/TeacherEdit?Edit=videodelete&vid=<%=m.get("vid")%>">
	  <i class="fa fa-trash-o " aria-hidden="true"></i>
	</a>
	</div>
	<%
  	           }
  	        }
  	        else{
  	        out.println("You don't have any videos currently!");
  	        } 	 
  	 %>
	</div>
	</div>
  </body>
</html>
