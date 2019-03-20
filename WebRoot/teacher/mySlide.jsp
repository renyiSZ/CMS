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
    
    <title>My Slide</title>
    
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
    
     <button class="layui-btn layui-btn-small" onclick="javascript:location.href='<%=path%>/Upload.jsp'">
      <i class="fa fa-file" aria-hidden="true">&nbsp;Add New Slides</i>
      </button>
    
    </blockquote>
    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		My Slides
	</legend>
		<div class="layui-field-box">
		
    <%
    String id =(String)request.getSession().getAttribute("uid");
  	 
  	 String sql= "select * from material where userid='"+id+"'";
  	   
  	 		List l=  DAOFactory.getMaterialDAOInstance().queryByClassID(sql); 
  	 		if(l.size()>0){
  	 		%>
  	 		<table class="site-table table-hover">
		  <thead>
			<tr>
    		 <th>SLIDES</th>
    		 <th>CLASS</th>
    		 <th>TYPE</th>
    		 <th>DESCRIPTION</th>
    		 <th>TIME</th>
    		 <th>DOWNLOAD</th>
    	    </tr>
    	  </thead>
  	 		<% 
				for(int i=0;i<l.size();i++){
					HashMap m=(HashMap) l.get(i);
		%>
  	 
    	<tbody>
			<tr>
				
				<td><%=m.get("mtitle") %></td>
				<td><%=m.get("kcid") %></td>
				<td><%=m.get("mtype") %></td>
				<td><%=m.get("mdesc") %></td>
				<td><%=m.get("mtime") %></td>
				<td align="center">
				
				<a href="<%=path %>/upload?downloadID=<%=m.get("mid")%>">
				<i class="fa fa-download " aria-hidden="true"></i>&nbsp;
				</a>&nbsp;
				
				<a href="<%=path %>/TeacherEdit?Edit=material&mid=<%=m.get("mid")%>">
				<i class="fa fa-edit " aria-hidden="true"></i>
				</a>&nbsp;
				
				<a href="<%=path %>/TeacherEdit?Edit=materialdelete&mid=<%=m.get("mid")%>">
				<i class="fa fa-trash-o " aria-hidden="true"></i>
				</a>&nbsp;
				
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
