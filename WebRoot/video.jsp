<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>video</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<style>
	.videoleft{ float:left; width:520px; height:600px;  }
	
	  #divdesc{ margin:5px auto ;overflow:auto; height:245px; } 
      #divdesc img{ border-radius:50%; width:45px; height:45px;} 
      
    .videoright{border:1px solid #E5E5E5; float:left; margin-left:50px; width:300px; height:550px;padding:10px; overflow:auto}
    .singlevideo{border:1px solid #E5E5E5; float:left;margin:5px 0px;width:300px; height:110px; }
    .imgdiv{float:left; margin:5px; width:170px; height:100px;  }
    .fontdiv{float:left; margin:5px; width:110px; height:100px;}
      </style>
  </head>
  
  <body>
  <jsp:include page="header.jsp"></jsp:include>
  <!-- ########################################################################################## -->
<div class="wrapper row3">
 <div class="rnd">
  <div id="container" class="clear">
  <!-- ####################-->
   
    <blockquote class="layui-elem-quote">
    <button class="layui-btn layui-btn-small" onclick="javascript:history.back(-1);"> <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
    </blockquote>
   <!-- ########################################################################################## -->
   <div class="videoleft">
    
   <c:forEach items="${requestScope.list}" var="list">
  	<h2>Title:&nbsp;${list.vtitle}</h2>
   <object type="application/x-shockwave-flash" id="VideoPlayer" data="tools/JarisFLVPlayer.swf" width="504px" height="288px">
   <param name="menu" value="false">
   <param name="scale" value="noScale">
   <param name="allowFullscreen" value="true">
   <param name="allowScriptAccess" value="always">
   <param name="bgcolor" value="#000000">
   <param name="quality" value="high">
   <param name="wmode" value="opaque">
   <param name="flashvars" value="source=<%=path %>/${list.vlink}&amp;type=video&amp;streamtype=file&amp;controltype=0&amp;duration=52&amp;poster=${list.vpic}&amp;aspectratio=&amp;autostart=false&amp;logo=http://jarisflvplayer.org//images/logo.png&amp;logoposition=top left&amp;logoalpha=30&amp;logowidth=130&amp;logolink=http://jarisflvplayer.org&amp;hardwarescaling=false&amp;controls=true&amp;darkcolor=000000&amp;brightcolor=4c4c4c&amp;controlcolor=FFFFFF&amp;hovercolor=67A8C1&amp;seekcolor=D3D3D3&amp;jsapi=flase">
   </object>
      <div id="divdesc">
        <div style="float:left;"> Title:&nbsp;${list.vtitle}&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div style="float:right;"> <a href="<%=path %>/VideoServlet?vid=${list.vid}">
				<i class="fa fa-download " aria-hidden="true"></i>&nbsp;download</a></div>
         </br>
   			 Type:[${list.vtype}]&nbsp;&nbsp;&nbsp;Class:&nbsp;${list.kcid}</br>
  			 Uploader: &nbsp;<img src="${list.userhead }" />&nbsp;
  			 &nbsp;${list.username}</br>
  			 Time:&nbsp;${list.vtime}</br><hr/>
  			 Description: &nbsp;${list.vcontent}</br>
   
      </div></c:forEach></br></br></br>
   </div>
   <!-- ############################################ -->
   <div class="videoright">
 	<h3>Related Videos</h3>
 	<c:if test="${requestScope.list2.size()==0}"><center></br></br><font size="5" color=" #DEDEDE ">No Related Videos</font> </center></c:if>
 	<c:forEach items="${requestScope.list2}" var="list2">
 	
 	 	<div class="singlevideo">
 			<div class="imgdiv">
 				<a href="<%=path %>/VideoDetailServlet?videoAction=listvideodetail&vid=${list2.vid}&kcid=${list2.kcid}">
	 			<img src="<c:out value="${list2.vpic}"/>" width="170px" height="100px"></img>
	 			</a>
	 		</div>
	 		<div class="fontdiv">&nbsp;Title:${list2.vtitle}</br>Class:${list2.kcid}</br>Uploader:${list2.username}
	 		</div>
	    </div>
 	</br></br>
 	
 	</c:forEach>
   </div>
   <!-- #####################-->
  </div>
 </div>
 </div>
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
