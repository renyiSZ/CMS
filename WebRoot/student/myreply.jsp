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
    
    <title>My Reply</title>
    
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
		My Reply
	</legend>
		<div class="layui-field-box">
		<table class="site-table table-hover">
		  <thead>
			<tr>
    		 <th>NUMBER</th>
    		 <th>REPLY TO</th>
    		 <th>REPLY CONTENT</th>
    		 <th>REPLY TIME</th>
    		 <th>SETTINGS</th>
    	    </tr>
    	  </thead>
   <%    
   	String id =(String)request.getSession().getAttribute("uid"); 
  	 List l=  DAOFactory.getForumPostDAOInstance().getMyReply(id); 
  	 
  	 	if(l.size()>0){
		    for(int i=0;i<l.size();i++){
			   HashMap m=(HashMap) l.get(i);
			   
   %>
  	 
    	<tbody>
			<tr>
				<td><font color="#737373"><strong><%=i+1 %></strong></font></td>
				<td><%=m.get("userid") %></td>
				<td><font color="#737373"><%=m.get("replycontent") %></font></td>
				<td><%=m.get("replytime") %></td>
				
				<td>
				<button class="layui-btn layui-btn-small" onclick="javascript:location.href='<%=path%>/EditPost?postid=<%=m.get("postid")%>&editWhat=add'"><i class="layui-icon"></i></button>
				</br></br>
				<button  class="layui-btn layui-btn-normal layui-btn-small" onclick="javascript:location.href='<%=path%>/EditReply?replyid=<%=m.get("postreplyid")%>&postid=<%=m.get("postid")%>'"> 
					<i class="layui-icon"></i>
				</button>
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
