package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.Ask;

public class AskDaoImpl implements AskDao {
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	public AskDaoImpl(Connection conn) { 
		this.conn = conn;  
	}
	
	@Override
	public int AddAsk(Ask ask) throws Exception {
		String sql = "insert into ask(askerid, askername,toid,status,asktime,askcontent) values(?,?,?,?,sysdate(),?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, ask.getStudentid());
		pstmt.setString(2, ask.getStudentname());
		pstmt.setString(3, ask.getAsktoid());//
		pstmt.setInt(4, ask.getStatus());//
		pstmt.setString(5, ask.getAskcontent());//
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public int AddAnswer(Ask ask) throws Exception {
		String sql = "insert into answer(askid, teacherid,teachername,toid,answertime,answercontent) values(?,?,?,?,sysdate(),?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, ask.getAskid());
		pstmt.setString(2, ask.getTeacherid());
		pstmt.setString(3, ask.getTeachername());//
		pstmt.setString(4, ask.getAnswertoid());//
		pstmt.setString(5, ask.getAnswercontent());//
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public int updateAsk(int askid) throws Exception {
		String sql="update ask set status=? where askid=?";
		System.out.println("updateAsk sql:"+sql);
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, 1);
		pstmt.setInt(2, askid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public List ListAsk(String sql) throws Exception {
		List l = new ArrayList();
		try{
			
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("askid",rs.getInt(1));
				map.put("studentid",rs.getString(2));
				map.put("studentname",rs.getString(3));
				map.put("asktoid",rs.getString(4));
				map.put("status",rs.getInt(5));
				map.put("asktime",rs.getString(6));
				map.put("askcontent",rs.getString(7));
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
	public List ListJoinQA(String sql) throws Exception {
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("askid",rs.getInt(1));
				map.put("studentid",rs.getString(2));
				map.put("studentname",rs.getString(3));
				map.put("asktoid",rs.getString(4));
				map.put("status",rs.getInt(5));
				map.put("asktime",rs.getString(6));
				map.put("askcontent",rs.getString(7));
				
				map.put("answerid",rs.getInt(8));
				//
				map.put("teacherid",rs.getString(10));
				map.put("teachername",rs.getString(11));
				map.put("answertoid",rs.getString(12));
				map.put("answertime",rs.getString(13));
				map.put("answercontent",rs.getString(14));
				
				l.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
	}

}
