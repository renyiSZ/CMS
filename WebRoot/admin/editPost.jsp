<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Post</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/admincss/table.css" />
  </head>
  
  <body>
   <div class="admin-main">
    <blockquote class="layui-elem-quote">
    
    <!-- ########## -->
    
    </blockquote>
    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-comment" aria-hidden="true"></i>&nbsp;
		Post
	</legend>
		<div class="layui-field-box">
		<table class="site-table table-hover">
		  <thead>
			<tr>
    		 <th>POST TOPIC</th>
    		 <th>POST TITLE</th>
    		 <th>POST CONTENT</th>
    		 <th>TIME</th>
    		 <th>VIEWS/REPLIES</th>
    		 <th>SETTINGS</th>
    	    </tr>
    	  </thead>   
   <%    
   String sql="select * from post";
  	 List l=  DAOFactory.getForumPostDAOInstance().getPost(sql); 
  	 	if(l.size()>0){
		    for(int i=0;i<l.size();i++){
			   HashMap m=(HashMap) l.get(i);
   %>
  	 
    	<tbody>
			<tr>
				<td><font color="#737373"><strong><%=m.get("posttype") %></strong></font></td>
				<td><%=m.get("posttitle") %></td>
				<td><font color="#737373"><%=m.get("postcontent") %></font></td>
				<td><%=m.get("posttime") %></td>
				<td><%=m.get("postclick") %> / <%=m.get("postreply") %></td>
				
				<td>
				<button class="layui-btn layui-btn-small" onclick="javascript:location.href='<%=path%>/EditPost?postid=<%=m.get("postid")%>&editWhat=add'"><i class="layui-icon"></i></button>
				</br></br><button  class="layui-btn layui-btn-normal layui-btn-small" onclick="javascript:location.href='<%=path%>/EditPost?postid=<%=m.get("postid")%>&editWhat=delete'"><i class="layui-icon"></i></button>
				</td>
			</tr>
  	 <%
  	          }
  	     } 	 
  	
  	 %>
  	  </tbody>
	  </table></br></br>
    </div>
	</fieldset>

</div>
  </body>
</html>
