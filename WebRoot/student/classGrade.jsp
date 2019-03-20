<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'classGrade.jsp' starting page</title>
    
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
    
     String gname= (String)request.getAttribute("gname");
     String kcid= (String)request.getAttribute("kcid");
     System.out.println("kcid:"+kcid);
    
     System.out.println("gname:"+gname);
  %>
<div class="admin-main">
    &nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" onClick="javascript:history.back(-1);">
    	<i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i>
    </button>
</br></br>
    <fieldset class="layui-elem-field">
  <div id="main" style="float:left;margin:5px; height: 400px;width:50%;"> </div>
   <div id="piemain" style="float:left;margin:5px; height: 400px;width:45%;"> </div>
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
	require([ 'echarts','echarts/theme/macarons','echarts/chart/bar','echarts/chart/pie'// 使用柱状图就加载bar模块，按需加载
	], 
	drewEcharts
    );
    function drewEcharts(ec){
       drawBar(ec);
       drawPie(ec);
    }
    function drawBar(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));
        myChart.showLoading({
           text: 'Loading...',    //loading话术
        });
        var option = {
        	 title : {
        		text: '<%=kcid %>: <%=gname %>',
        		
        		
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
                                         url : "GradeAnalysisServlet?action=singletestbar&gname=<%=gname%>&kcid=<%=kcid%>",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].studentid);
                                                  arr.push(i+1);
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
				"name" : "grade",
				"type" : "bar",
				"data" : (function(){
                                        var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "GradeAnalysisServlet?action=singletestbar&gname=<%=gname%>&kcid=<%=kcid%>",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].grade);
                                                  arr.push(result[i].grade);
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
        
	function drawPie(ec){
		var myPieChart = ec.init(document.getElementById('piemain'));

		myPieChart.showLoading({
           text: 'Loading...',    //loading话术
        });
		 
		var option2 = {
    		title : {
       		 text: 'Grade Distribution',
        	subtext: '<%=kcid %>: <%=gname %>',
        	x:'center'
    	},
    	tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
     toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true,
                type: ['pie', 'funnel']
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'result',
            type:'pie',
            radius : [30, 120],
            
            roseType : 'area',
            x: '50%',               // for funnel
            max: 40,                // for funnel
            sort : 'ascending',     // for funnel
            data :(function(){
                                    var res=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "GradeAnalysisServlet?action=singletestpie&gname=<%=gname%>&kcid=<%=kcid%>",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].marksection);
                                                  res.push({
                                					name: result[i].marksection,
                                					value: result[i].number
                                				  });

                                                }    
                                        }
                                        
                                    },
                                    error : function(errorMsg) {
                                        alert("sorry, data failed!");
                                        myPieChart.hideLoading();
                                    }
                                   })
                                   return res;
                                })() 
        }]
};
	myPieChart.setOption(option2);
	myPieChart.hideLoading();
} 
</script>
  </body>
</html>
