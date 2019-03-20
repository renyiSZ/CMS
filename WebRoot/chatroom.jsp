<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="UTF-8">
  <title>Chat Room</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="assets/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/umeditor/lang/zh-cn/zh-cn.js"></script>

<style>
body{width:100%;}

</style>
</head>
<body>
	
		<header class="am-topbar am-topbar-fixed-top">
	  		<div class="am-container">
	    		<h1 class="am-topbar-brand">
	     		Chat Room
	    		</h1>
	    		 <div class="am-collapse am-topbar-collapse" id="collapse-head">
				 	
	     		 <div class="am-topbar-right">
	        		<a href="homepage.jsp"><button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm"><span class="am-icon-home"></span>&nbsp;Exit</button>
	      		    </a>
	      		 </div>
	
	     		 
	    		</div>
	  		</div>
	  
		</header>
	
<div id="left" style="height: 600px; width: 700px; float:left;margin-left:120px; ">

		<!-- 聊天内容展示区域 -->
	<div id="ChatBox" class="am-g am-g-fixed" >
	
	  <div class="am-u-lg-12" style="height:400px;width:700px;border:1px solid #999;overflow-y:scroll;">
	    
	    <div id="smallword" style="height: 20px;text-align:center; color:grey;"></div>
	  
		<ul id="chatContent" class="am-comments-list am-comments-list-flip">
			<li id="msgtmp" class="am-comment" style="display:none;">
			    <a href="">
			     <img id="headpic" class="am-comment-avatar" src="${uhead}" alt=""/>
			    </a>
			    <div class="am-comment-main" >
			        <header class="am-comment-hd">
			            <div class="am-comment-meta">
			              <a ff="nickname" href="#link-to-user" class="am-comment-author">System</a>
			              <time ff="msgdate" datetime="" title="">
			              
			              </time>
			            </div>
			        </header>
			     <div ff="content" class="am-comment-bd"  style="word-wrap:break-word">Welcome to Chat Room</div>
			    </div>
			</li>
		</ul>
	  </div>
	</div>
	
	<!-- 聊天内容发送区域 -->
	<div id="EditBox" class="am-g am-g-fixed">
	<!--style给定宽度可以影响编辑器的最终宽度-->
	<script type="text/plain" id="myEditor" style="width:700px;height:120px;"></script>
	<button id="send" type="button" class="am-btn am-btn-primary am-btn-block" style="width:700px">send</button>
	</div>
</div>
<div class="right" id="users" style="height: 600px; width:20%; float:left;margin-left:5px;margin-right:50px; padding:20px;border:1px solid grey;overflow-y:scroll;">

</div>
<script type="text/javascript">

$(function(){


	//实例化编辑器
    var um = UM.getEditor('myEditor',{
    	initialContent:"please type in...",
    	autoClearinitialContent :true, 
    	autoHeightEnabled:false,
    	toolbar:[
            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | emotion'
        ]
    });
    
    
   var nickname="<%=session.getAttribute("uname")%>";
    var userhead="<%=session.getAttribute("uhead")%>";
    
	var socket = new WebSocket("ws://localhost:8080/finalProject2/web/"+nickname);
	//10.213.14.103
    //接收服务器的消息
    //var inc = document.getElementById("smallword");
    //var user=session.getAttribute("uname");
    
    
    socket.onopen = function(){
		//inc.innerHTML += '-Welcome to Chat Room!-<br/>';	
		//alert(""+name);
		//var userdiv = document.getElementById("users");
  		//userdiv.innerHTML="<font id='"+nickname+"'>"+nickname+"</font>";	
    };
    socket.onclose = function() {
		//$("#"+nickname).hide();
			
    };
    socket.onmessage=function(ev){
    	var obj = eval(   '('+ev.data+')'   );
    	addMessage(obj);
    }
    window.onbeforeunload = function() {
        socket.close();
    };
    
    $("#send").click(function(){
    	if (!um.hasContents()) {  // 判断消息输入框是否为空
            // 消息输入框获取焦点
            um.focus();
            // 添加抖动效果
            $('.edui-container').addClass('am-animation-shake');
            setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
        } else {
        	//获取输入框的内容
        	var txt = um.getContent();
        	var count=1;
        	//var date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	//构建一个标准格式的JSON对象
        	//var date=new date();
        	var time = getNowFormatDate();
        	var obj = JSON.stringify({
	    		nickname:nickname,
	    		date:time,
	    		content:txt,
	    		uhead:userhead
	    	});
            // 发送消息
            socket.send(obj);
            // 清空消息输入框
            um.setContent('');
            // 消息输入框获取焦点
            um.focus();
        }
    
    });
    document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];       
             if(e.ctrlKey && e.keyCode==13){ // enter 键
                 //要做的事情
                  $("#send").click();
            }
        }; 
    
    
    
    
});

//人名nickname，时间date，是否自己isSelf，内容content
function addMessage(msg){
	var head=document.getElementById("headpic");
	head.src= ""+msg.uhead;
	var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
	box.show();							//设置box状态为显示
	box.appendTo("#chatContent");		//把box追加到聊天面板中
	
	box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
	box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
	box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
	box.addClass(msg.isSelf? 'am-comment-flip':'');	//右侧显示
	box.addClass(msg.isSelf? 'am-comment-warning':'am-comment-success');//颜色
	box.css((msg.isSelf? 'margin-left':'margin-right'),"20%");//外边距
	
	
	$("#users").html('Online Users:'+msg.count+'</br><hr>'+msg.onlineusers+'</br>');
	
	$("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
	
}


function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
       strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();

    return currentdate;
} 
</script>

</body>
</html>
