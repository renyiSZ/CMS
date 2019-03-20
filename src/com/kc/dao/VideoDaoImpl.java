package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.Video;

public class VideoDaoImpl implements VideoDao {
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	// 通过构造方法取得数据库连接
		public VideoDaoImpl(Connection conn) { 
			this.conn = conn;  
		}
	@Override
	public List listVideoByPage(int page, String sql) throws Exception {
		List l=new ArrayList();
		try{
		//String sql="select * from video order by vtime desc limit ?,?";
		sql=sql+" limit ?,?";
		this.pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*16);  //一页16 个 4*4
		pstmt.setInt(2, 16);
		rs = this.pstmt.executeQuery();
		
		while(rs!=null&&rs.next()){
			HashMap map=new HashMap();
			map.put("vid",rs.getInt(1));
			map.put("username",rs.getString(2));
			map.put("userid",rs.getString(3));
			map.put("kcid",rs.getString(4));
			map.put("vtype",rs.getString(5));
			map.put("vtitle",rs.getString(6));
			map.put("vcontent",rs.getString(7));
			map.put("vtime",rs.getString(8));
			map.put("vlink",rs.getString(9));
			map.put("vpic",rs.getString(10));
			map.put("userhead",rs.getString(11));
			l.add(map);
		}
		rs.close();//关闭ResultSet对象
		this.pstmt.close();//关闭PreparedStatement对象
	}catch(SQLException e){
		e.printStackTrace();
	}
	return l;
	}
	
	@Override
	public int getCountrow(String sql) throws Exception{
		//String sql="select count(*) from video";
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();     
		int countrow = 0;      
		if(rs.next())      
		{       
		    countrow = rs.getInt(1);  
		}
		rs.close();//关闭ResultSet对象
		this.pstmt.close();//关闭PreparedStatement对象
		return countrow;   
	}
	
	@Override
	public List listVideo(String sql) throws Exception {
		List l=new ArrayList();
		try{
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		while(rs!=null&&rs.next()){
			HashMap map=new HashMap();
			map.put("vid",rs.getInt(1));
			map.put("username",rs.getString(2));
			map.put("userid",rs.getString(3));
			map.put("kcid",rs.getString(4));
			map.put("vtype",rs.getString(5));
			map.put("vtitle",rs.getString(6));
			map.put("vcontent",rs.getString(7));
			map.put("vtime",rs.getString(8));
			map.put("vlink",rs.getString(9));
			map.put("vpic",rs.getString(10));
			map.put("userhead",rs.getString(11));
			l.add(map);
		}
		rs.close();//关闭ResultSet对象
		this.pstmt.close();//关闭PreparedStatement对象
	}catch(SQLException e){
		e.printStackTrace();
	}
	return l;
	}

	@Override
	public int saveVideo(Video video) throws Exception{
		String sql = "insert into video(username,userid,kcid,vtype,vtitle,vcontent,vtime,vlink,vpic,userhead) values(?,?,?,?,?,?,sysdate(),?,?,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, video.getUsername());
		pstmt.setString(2, video.getUserid());//
		pstmt.setString(3, video.getKcid());//
		pstmt.setString(4, video.getVtype());
		pstmt.setString(5, video.getVtitle());
		pstmt.setString(6, video.getVcontent());
		pstmt.setString(7, video.getVlink());
		pstmt.setString(8, video.getVpic());
		pstmt.setString(9, video.getUserhead());
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;	
		
	}
	@Override
	public int deleteVideo(int vid) throws Exception{
		String sql = "delete from video where vid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, vid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	//下载用
	@Override
	public Map queryByVid(int downloadID) throws Exception{
		Map map=new HashMap();
		try{
			String sql="select vlink from video where vid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, downloadID);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				
				map.put("vlink",rs.getString(1));
			
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
	}
	

}
