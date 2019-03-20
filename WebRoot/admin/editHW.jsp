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
    
    <title>homework</title>
    
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
    <blockquote class="layui-elem-quote" style="height:30px">
  
     
<div style="float:left"> 
 <form name="formname" action="<%=path %>/AdminHW" method="post">
      <input type="text" name="classid" style="width:80px; height:25px;" placeholder="Class ID" >
      <input type="hidden" name="type" value="select">
    <a href="javascript:document.formname.submit();" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> search
	</a>
 </form>
</div>
    </blockquote>
    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		homework
	</legend>
		<div class="layui-field-box">
		
    <%

  	 
  	
  	   
  	 		List l= (List)request.getAttribute("hwlist"); 
  	 		if(l.size()>0){
  	 		%>
  	 		<table class="site-table table-hover">
		  <thead>
			<tr>
			 <th>CLASS</th>
    		 <th>Title</th>
    		
    		 <th>DESCRIPTION</th>
    		  <th>ATTACHMENT</th>
    		 <th>TIME</th>
    		 <th>OPERATION</th>
    	    </tr>
    	  </thead>
  	 		<% 
				for(int i=0;i<l.size();i++){
					HashMap m=(HashMap) l.get(i);
					String fileName=""+m.get("hwlink");
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		%>
  	 
    	<tbody>
			<tr>
				
				<td><%=m.get("kcid") %></td>
				<td><%=m.get("hwtitle") %></td>
				
				<td><%=m.get("hwdesc") %></td>
				<td><%=fileName %></td>
				<td><%=m.get("hwtime") %></td>
				
			<%
			if(fileName.equals("")){
			 %>	
			   <td align="center">
				
				<a href="<%=path %>/TeacherEdit?Edit=material&mid=<%=m.get("mid")%>">
				<i class="fa fa-edit " aria-hidden="true"></i>
				</a>&nbsp;
				
				<a href="<%=path %>/TeacherEdit?Edit=materialdelete&mid=<%=m.get("mid")%>">
				<i class="fa fa-trash-o " aria-hidden="true"></i>
				</a>&nbsp;
			</td>
			   
			 <%
			 }else{
			 %>	
				<td align="center"><a href="<%=path %>/HomeworkServlet?downloadID=<%=m.get("hwid") %>">
				<i class="fa fa-download " aria-hidden="true"></i>&nbsp;</a>
				&nbsp;
				
				<a href="<%=path %>/TeacherEdit?Edit=material&mid=<%=m.get("mid")%>">
				<i class="fa fa-edit " aria-hidden="true"></i>
				</a>&nbsp;
				
				<a href="<%=path %>/TeacherEdit?Edit=materialdelete&mid=<%=m.get("mid")%>">
				<i class="fa fa-trash-o " aria-hidden="true"></i>
				</a>&nbsp;
				</td>
			 <%
			 }
			 %>
				
				
				
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
