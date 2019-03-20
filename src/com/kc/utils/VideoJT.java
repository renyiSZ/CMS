package com.kc.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoJT {
	 public static boolean processVideo(String video_path,String ffmpeg_path,String pic_path,String codcFilePath) {  
		    File file = new File(video_path);
		    //String fileName = file.getName(); 
		    //String name=fileName.substring(0,fileName.lastIndexOf("."));
		   //System.out.println(name);
		   //
		    if (!file.exists()) {  
		    System.err.println("路径[" + video_path + "]对应的视频文件不存在!");  
		    return false;  
		    }  
		    
		    List<String> convert = new ArrayList<String>();
	        convert.add(ffmpeg_path); // 添加转换工具路径
	        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
	        convert.add(video_path); // 添加要转换格式的视频文件的路径
	        convert.add("-qscale");     //指定转换的质量
	        convert.add("6");
	        convert.add("-ab");        //设置音频码率
	        convert.add("64");
	        convert.add("-ac");        //设置声道数
	        convert.add("2");
	        convert.add("-ar");        //设置声音的采样频率
	        convert.add("22050");
	        convert.add("-r");        //设置帧频
	        convert.add("24");
	        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
	        convert.add(codcFilePath);
		    
		    
		    List<String> commands = new java.util.ArrayList<String>();  
		    commands.add(ffmpeg_path);  
		    commands.add("-i");  
		    commands.add(video_path);  
		    commands.add("-y");  
		    commands.add("-f");  
		    commands.add("image2");  
		    commands.add("-ss");  
		    commands.add("8");//这个参数是设置截取视频多少秒时的画面  
		    //commands.add("-t");  
		    //commands.add("0.001");  
		    commands.add("-s");  
		    commands.add("700x525");  
		    commands.add(pic_path);  
		    try {  
		   	
		    	
		    ProcessBuilder builder = new ProcessBuilder(); 
		    builder.command(convert);
		    builder.redirectErrorStream(true);
		    builder.start();
		    System.out.println("转码成功"); 
		    builder.command(commands);  
		    builder.start();  
		    System.out.println("截取成功");  
		    return true;  
		    } catch (Exception e) {  
		    e.printStackTrace();  
		    return false;  
		    }  
	 }   
}  
