package com.kc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.Video;

public interface VideoDao {
	//分页显示视频列表
     public List listVideoByPage(int page, String sql) throws Exception;
    
	 public int getCountrow(String sql) throws Exception;
		
	 
	 
	 public Map queryByVid(int vid) throws Exception;
			
     //根据课程ID显示列表
     //列出用户相关video
     public List listVideo(String sql) throws Exception;
     
     public int saveVideo(Video video) throws Exception;
     public int deleteVideo(int vid) throws Exception;
}
