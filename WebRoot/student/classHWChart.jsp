<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'singleHWChart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body>
  <%
     String uid= (String)request.getAttribute("uid");
     String kcid= (String)request.getAttribute("kcid");
     System.out.println("kcid:"+kcid);
      System.out.println("uid:"+uid);
     
  %>
<div class="admin-main">
    &nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" onClick="javascript:history.back(-1);">
    	<i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i>
    </button>
</br></br>
    <fieldset class="layui-elem-field">
  <div id="main" style="float:left;margin:5px; height: 400px;width:70%;"> </div>
   
  </fieldset>
</div>  

 <script type="text/javascript" src="<%=path %>/dist/echarts.js"></script>
 <script type="text/javascript" src="jquery.min.js"></script>
 <script type="text/javascript">
	// 路径配置
	require.config({
		paths : {
			echarts : '<%=path %>/dist'
		}
	});
	// 使用
	require([ 'echarts','echarts/theme/macarons','echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	], 
	drewEcharts
    );
    function drewEcharts(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));
        myChart.showLoading({
           text: 'Loading...',    //loading话术
        });
        var option = {
        	 title : {
        		text: '<%=kcid %> All Homework',
        		subtext:'<%=uid %>'
    	},
			tooltip : {
				show : true
			},
			legend : {
				data : [ 'result' ]
			},
			xAxis : [ {
				type : 'category',
				data :(function(){
                                    var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "HWAnalysisServlet?action=classhw&kcid=<%=kcid%>&uid=<%=uid%>",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].homeworktitle);
                                                  arr.push(result[i].homeworktitle);
                                                }    
                                        }
                                        
                                    },
                                    error : function(errorMsg) {
                                        alert("sorry, data failed!");
                                        myChart.hideLoading();
                                    }
                                   })
                                   return arr;
                                })() 
			
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				"name" : "homework result",
				"type" : "bar",
				"data" : (function(){
                                        var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "HWAnalysisServlet?action=classhw&kcid=<%=kcid%>&uid=<%=uid%>",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].result);
                                                  arr.push(result[i].result);
                                                }  
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，大爷，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                  return arr;
                            })(),
				 
			markPoint : {
                data : [
                    {type : 'max', name: 'Max'},
                    {type : 'min', name: 'Min'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: 'average'}
                ]
            }
                    
            
                       
			} ]
			
		};
		
		
		// 为echarts对象加载数据 
		myChart.setOption(option);
		myChart.hideLoading();
	}
        
	
</script>
  </body>
</html>
