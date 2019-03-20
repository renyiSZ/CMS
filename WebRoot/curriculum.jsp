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
    
    <title>My JSP 'curriculum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	 <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	 
		<link rel="stylesheet" href="css/admincss/global.css" media="all"/>
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body  id="top">
  <jsp:include page="header.jsp"></jsp:include>
  <!-- ########################################################################################## -->
 <div class="wrapper row3">
  <div class="rnd">
    <div id="container" class="clear">
 <div class="admin-main">   
    <blockquote class="layui-elem-quote">
    <form name="searchform" action="<%=path %>/SearchClass?search=homesearch" method="post">
   		<input type="text" name="classid" style="width:80px; height:25px;" placeholder="Class ID" >&nbsp;
   		<input type="text" name="teachername" style="width:100px; height:25px;" placeholder="teacher name" >&nbsp;
   	    <select name="classtype" style="width:120px; height:25px;">
								<option value="" > --type--</option> 
								<option value="Agronomy">Agronomy</option> 
								<option value="Economics">Economics</option> 
								<option value="Engineering">Engineering</option> 
								<option value="History">History</option> 
								<option value="Law">Law</option> 
								<option value="Literature">Literature</option> 
								<option value="Management">Management</option> 
								<option value="Medical Science">Medical Science</option> 
								<option value="Military Science">Military Science</option> 
								<option value="Pedagogy">Pedagogy</option> 
								<option value="Philosophy">Philosophy</option> 
								<option value="Science">Science</option> 
								<option value="other">Other</option> 
		</select> 
    	<button class="layui-btn layui-btn-small" onclick="javascript:document.searchform.submit();"> 
    		<i class="layui-icon">&#xe615;</i>&nbsp;search</i>
    	</button>
    	</form>
    </blockquote>
    <!-- ########-->
  <div style=" height:650px; overflow:auto;">
   <%
     String sql= (String)request.getAttribute("sqlstatement");
   	 List list=  DAOFactory.getClassInfoDAOInstance().getClass(sql); 
  	 if(list.size()>0){
   %>
    
    
   <%
		for(int i=0;i<list.size();i++){
			HashMap m=(HashMap)list.get(i);
			Users teacher= DAOFactory.getUserDAOInstance().queryByID(""+m.get("teacherid"));
   %>
   <div style="width:80%;  height:420px; ">
    
   &nbsp;&nbsp;&nbsp;<h3><i class="fa fa-hand-o-right" aria-hidden="true"></i>&nbsp;
   <%=m.get("kcid") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <%=m.get("kcname") %></h3>
   &nbsp;&nbsp;&nbsp;Type :&nbsp;&nbsp;<%=m.get("kctype") %></br>
   &nbsp;&nbsp;&nbsp;Semester :&nbsp;&nbsp;<%=m.get("semester") %></br>
   &nbsp;&nbsp;&nbsp;Credit :&nbsp;&nbsp;<%=m.get("credit") %></br>
   &nbsp;&nbsp;&nbsp;Teacher:</br>
    <div style="  height:130px; float:left;margin-left:8px; ">
    <img src="<%= teacher.getUhead()%>" width="120px" height="120px"></img>
    </div>
    
    <div  style=" height:130px; float:left;padding-left:10px; ">
     <%= teacher.getUname()%></br></br>
     E-mail :&nbsp;&nbsp;<%=teacher.getEmail()%></br>
     Phone :&nbsp;&nbsp;<%=teacher.getUphone()%></br>
    </div>
    <div style="border-radius:5px 5px 5px 5px;overflow:auto; width:80%;height:130px; padding:10px;float:left;padding-left:10px; background: #E0EEE0 ;">
   	Class description:&nbsp;&nbsp;<%=m.get("kcdesc") %><br>
   	Teaching Plan:&nbsp;&nbsp;<%=m.get("teachingplan") %><br>
    </div>
    
    </div>
    <%  
  	        } 	 
  	 }
  	 else{
  	   out.print("No class");
  	 }
   %>	
   </div>
   </div>
   </div>
   </div>
  </div>
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
