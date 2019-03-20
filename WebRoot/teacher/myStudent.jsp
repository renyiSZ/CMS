<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My Student</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<style>
.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:160px; height:260px; word-wrap:break-word;}
		.imgdiv{margin:5px; width:150px; height:150px; background:black; }
</style>
  </head>
  
  <body>
  <div class="admin-main">
 
    <blockquote class="layui-elem-quote">
  <% ArrayList classlist = (ArrayList)session.getAttribute("classlist"); 
  List studentlist=(List)request.getAttribute("studentlist"); 
  String currentclass=(String)request.getAttribute("currentclass"); 
  System.out.println(studentlist);  
  List Rlist= (List)request.getAttribute("Rlist");
   System.out.println("page Rlist:"+Rlist); 
  String Rid="";
  if(Rlist!=null){
  if(Rlist.size()>0){
    HashMap p=(HashMap) Rlist.get(0);
    Rid=""+p.get("uid");
    }
  }
  %>
  <div style="height:50px;"><div style="float:left;">
 <form name="classform" action="<%=path%>/CheckStudentServlet" method="post">
 <select name="show" style="width:200px; height:40px;">
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
</select>
<button onclick="javascript:document.classform.submit();">&nbsp;Search&nbsp;</button> </form>
</div>
<div style="float:right;">
<a href="<%=path %>/DBToExcel?type=studentinfo&kcid=<%=currentclass%>">
<button> &nbsp;Export student info as excel file&nbsp;</button></a>
<%
if(Rid.equals("")){
 %>
 &nbsp; &nbsp;
 <div style="float:right;" id="forgot">
 <a href="javascript:;">
 <button>&nbsp;assign a representative&nbsp;</button>
 </a></div>
 <%
}
 %>
</div></div>
  </blockquote></br>
    <fieldset class="layui-elem-field">
    <%
     	if(studentlist.size()>0){
         for(int j=0;j<studentlist.size();j++){
         
    		HashMap m=(HashMap) studentlist.get(j);
     %> 
    <div class="videodiv">
	 	<div class="imgdiv">
	 	<a href="<%=path%>/TeacherChartServlet?chart=singlestudentHWChart&uid=<%= m.get("uid")%>&kcid=<%=currentclass %>">
			 <img src="<%=m.get("uhead") %>" width="150px" height="150px"></img>
		</a>
   	 	</div>
   	 	&nbsp;<%= m.get("uid")%></br>
   	 <%
   	 String userid=""+m.get("uid");
   	 if (userid.equals(Rid)){
   	  %>
   	  	<font color="red"><i class="fa fa-user" aria-hidden="true"></i></font>
   	  <%
   	   }else{
   	  %>	
   	  <i class="fa fa-user" aria-hidden="true"></i>
   	  	<%} %>
   	  	&nbsp;<%= m.get("uname")%></br>
	 	<i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;<%=m.get("uemail")%></br>
	 	<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;<%=m.get("uphone")%></br>
   	</div>
    <%
   }}else{
     %>
     No Student
     <%} %>
     </fieldset>
     </div>
     <script type="text/javascript" src="plugins/layui/layui.js"></script>
	 
			<script src="js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;
					
					$('#forgot').on('click', function() {
						layer.open({
							title: 'Assign',
							maxmin: true,
							type: 2,
							content: 'AssignRTA?type=R&kcid=<%=currentclass%>',
							area: ['800px', '500px']
						});
					});
					
				});
			</script>
  </body>
</html>
