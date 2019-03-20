package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.ClassInfo;

public class ClassInfoDaoImpl implements ClassInfoDao {
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	// 通过构造方法取得数据库连接
		public ClassInfoDaoImpl(Connection conn) { 
			this.conn = conn;  
		}
	@Override
	public int addClass(ClassInfo classinfo) throws Exception {
		String sql = "insert into kcdesc(kcid,kcname,kctype,teacherid,credit,semester,teachername) values(?,?,?,?,?,?,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1,classinfo.getKcid() );
		pstmt.setString(2, classinfo.getKcname());//
		pstmt.setString(3, classinfo.getKctype());//
		pstmt.setString(4, classinfo.getTeacherid());
		pstmt.setInt(5, classinfo.getCredit());
		pstmt.setString(6, classinfo.getSemester() );
		pstmt.setString(7, classinfo.getTeachername() );
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	@Override
	public List getClass(String sql) throws Exception {
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("kcid",rs.getString(1));
				map.put("kcname",rs.getString(2));
				map.put("kctype",rs.getString(3));
				map.put("kcdesc",rs.getString(4));
				map.put("teacherid",rs.getString(5));
				map.put("credit",rs.getInt(6));
				map.put("semester",rs.getString(7));
				map.put("teachingplan",rs.getString(8));
				map.put("teachername",rs.getString(9));
				
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
	public int deleteClass(String classid) throws Exception {
		String sql = "delete from kcdesc where kcid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, classid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	@Override
	public int deleteClasses(String[] classid) throws Exception {
		String sql = "delete from kcdesc where ";
		for(int i=0;i<classid.length;i++){
			if(i==0){
				sql=sql+"kcid='"+classid[i]+"'";
			}
			else{
				sql=sql+" or kcid='"+classid[i]+"'";
			}
		}
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	@Override
	public List getJoinKCandUsers(String sql)throws Exception{
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("kcid",rs.getString(1));
				map.put("kcname",rs.getString(2));
				map.put("kctype",rs.getString(3));
				map.put("kcdesc",rs.getString(4));
				map.put("teacherid",rs.getString(5));
				map.put("credit",rs.getInt(6));
				map.put("semester",rs.getString(7));
				map.put("teachingplan",rs.getString(8));
				map.put("teachername",rs.getString(12));
				map.put("email", rs.getString(14));
				map.put("sex", rs.getString(15));
				map.put("head", rs.getString(16));
				map.put("phone", rs.getString(17));
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
	public int editClass(String classid, String teachingplan) throws Exception {
		int result = 0;
		String sql="update kcdesc set teachingplan=? where kcid=?";
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, teachingplan);
		pstmt.setString(2, classid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
		
	}

}
