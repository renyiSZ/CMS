package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import com.kc.entity.Grade;
import com.kc.entity.Homework;

public class GradeDaoImpl implements GradeDao {
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	// 通过构造方法取得数据库连接
		public GradeDaoImpl(Connection conn) { 
			this.conn = conn;  
		}
	@Override
	public List<Grade> getAllGrade(String sql) throws Exception {
		ArrayList<Grade> list = new ArrayList<Grade>();
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Grade g = new Grade();
				g.setGid(rs.getInt(1));
				g.setKcid(rs.getString(2));
				g.setGname(rs.getString(3));
				g.setStudentid(rs.getString(4));
				g.setStudentname(rs.getString(5));
				g.setTeachername(rs.getString(6));
				g.setTeacherid(rs.getString(7));
				g.setGtime(rs.getString(8));
				g.setGrade(rs.getFloat(9));
				list.add(g);
			}
		return list;
	}

	@Override
	public int addGrade(Grade grade) throws Exception {
		String sql = "insert into grade(kcid,gname,studentid,studentname,teachername,teacherid,gtime,grade) values(?,?,?,?,?,?,sysdate(),?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, grade.getKcid());
		pstmt.setString(2,grade.getGname());//
		pstmt.setString(3, grade.getStudentid());//
		pstmt.setString(4,grade.getStudentname());
		pstmt.setString(5, grade.getTeachername());
		pstmt.setString(6,grade.getTeacherid());
		pstmt.setFloat(7,grade.getGrade());
		
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	@Override
	public int updateGrade(String sql)throws Exception {
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	@Override
	public boolean isExist(String studentid, String kcid, String gname)
			throws Exception {
		String sql="select * from grade where studentid='"+studentid+"' and kcid='"+kcid+"' and gname='"+gname+"'";
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
		if (rs.next()) {
				return true;
		}
		
		return false;
		
	}
	@Override
	public List searchTestName(String sql) throws Exception {
		List list = new ArrayList();
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
			while (rs.next()) {
				HashMap m = new HashMap();
				m.put("kcid",rs.getString(1));
				m.put("gname",rs.getString(2));
				list.add(m);
			}
			for  ( int  i = 0 ; i  <  list.size()-1 ; i++){     
				   for  ( int  j=list.size()-1 ; j>i; j--) {     
				       if  (list.get(j).equals(list.get(i)))  {     
				             list.remove(j);     
				        }      
				   }      
		    }      
			//System.out.println("GradeDaoImpl search test:"+list);
		return list;
	}

}
