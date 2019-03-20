package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.Group;
import com.kc.entity.Homework;

public class HomeworkDaoImpl implements HomeworkDao {
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	// 通过构造方法取得数据库连接
		public HomeworkDaoImpl(Connection conn) { 
			this.conn = conn;  
		}
		
	@Override
	public int addHomework(Homework hw) throws Exception {
		String sql = "insert into homework(kcid,teacherid,teachername,hwtitle,hwdesc,hwlink,hwtime,answerlink) values(?,?,?,?,?,?,sysdate(),?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, hw.getKcid());
		pstmt.setString(2, hw.getUserid());//
		pstmt.setString(3, hw.getUsername());//
		pstmt.setString(4, hw.getHomeworktitle());
		pstmt.setString(5, hw.getHomeworkdesc());
		pstmt.setString(6, hw.getHomeworklink());
		pstmt.setString(7, hw.getAnswerlink());
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public List getHomework(String sql) throws Exception {
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("hwid",rs.getString(1));
				map.put("kcid",rs.getString(2));
				map.put("teacherid",rs.getString(3));
				map.put("teachername",rs.getString(4));
				map.put("hwtitle",rs.getString(5));
				map.put("hwdesc",rs.getString(6));
				map.put("hwlink",rs.getString(7));
				map.put("hwtime",rs.getString(8));
				map.put("answerlink",rs.getString(9));
				l.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addHWforCorrect(int hwid,String kcid,String studentid,String studentname,String teacherid) throws Exception {
		String sql = "insert into hwforcorrect(hwid,kcid,studentid,studentname,correct,teacherid) values(?,?,?,?,9,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, hwid);//
		pstmt.setString(2, kcid);//
		pstmt.setString(3, studentid);
		pstmt.setString(4, studentname);
		pstmt.setString(5, teacherid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	@Override
	public Map getSingleHW(String sql)throws Exception{
		Map map=new HashMap();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				map.put("correctid",rs.getInt(1));
				map.put("hwid",rs.getInt(2));
				map.put("kcid",rs.getString(3));
				map.put("studentid",rs.getString(4));
				map.put("studentname",rs.getString(5));
				map.put("handintime",rs.getString(6));
				map.put("handinlink",rs.getString(7));
				map.put("status",rs.getInt(8));
				map.put("result",rs.getInt(9));
				map.put("teacherid",rs.getString(10));
				map.put("correcttime",rs.getString(11));
				map.put("comment",rs.getString(12));
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public List getStudentHWs(String sql)throws Exception{
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("correctid",rs.getInt(1));
				map.put("hwid",rs.getInt(2));
				map.put("kcid",rs.getString(3));
				map.put("studentid",rs.getString(4));
				map.put("studentname",rs.getString(5));
				map.put("handintime",rs.getString(6));
				map.put("handinlink",rs.getString(7));
				map.put("status",rs.getInt(8));
				map.put("result",rs.getInt(9));
				map.put("teacherid",rs.getString(10));
				map.put("correcttime",rs.getString(11));
				map.put("comment",rs.getString(12));
	
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
	public List getJoinInfo(String sql) throws Exception {
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("hwid",rs.getString(1));
				map.put("kcid",rs.getString(2));
				map.put("teacherid",rs.getString(3));
				map.put("teachername",rs.getString(4));
				map.put("hwtitle",rs.getString(5));
				map.put("hwdesc",rs.getString(6));
				map.put("hwlink",rs.getString(7));
				map.put("hwtime",rs.getString(8));
				map.put("answerlink",rs.getString(9));
				///////////////////////////
				map.put("correctid",rs.getInt(10));
				map.put("studentid",rs.getString(13));
				map.put("studentname",rs.getString(14));
				map.put("handintime",rs.getString(15));
				map.put("handinlink",rs.getString(16));
				map.put("status",rs.getInt(17));
				map.put("result",rs.getInt(18));
				map.put("correcttime",rs.getString(20));
				map.put("comment",rs.getString(21));
	
				//////////////////////////////
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
	public Map queryByhwid(int hwid)throws Exception{
		Map map=new HashMap();
		try{
			String sql="select hwlink from homework where hwid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, hwid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){		
				map.put("hwlink",rs.getString(1));
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public Map queryByCorrectid(int cid)throws Exception{
		Map map=new HashMap();
		try{
			String sql="select link from hwforcorrect where correctid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){		
				map.put("correctlink",rs.getString(1));
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
	}
	@Override	
	public int correct(int correctid,int result, String comment)throws Exception{
		String sql="update hwforcorrect set result='"+result+"',comment='"+comment+"',correct=1,correcttime=sysdate() where correctid='"+correctid+"'";
		int r=0;
		this.pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		
		r=this.pstmt.executeUpdate(sql);//执行数据库操作
		this.pstmt.close();//关闭PreparedStatement对象
		return r;		
	}
	@Override
	public int queryHWID(String hwtitle, String kcid, String userid, String hwlink )throws Exception{
		int hwid=0;
		try{
		String sql="select hwid from homework where hwtitle=? and kcid=? and teacherid=? and hwlink=?";
		this.pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, hwtitle);
		pstmt.setString(2, kcid);
		pstmt.setString(3, userid);
		pstmt.setString(4, hwlink);
		rs = this.pstmt.executeQuery();
		while(rs!=null&&rs.next()){		
		     hwid=rs.getInt(1);
		}
		rs.close();//关闭ResultSet对象
		this.pstmt.close();//关闭PreparedStatement对象
	}catch(SQLException e){
		e.printStackTrace();
	}
		return hwid;	
	}
	@Override
	public int handinUpdate(Homework hw)throws Exception{
		String sql="update hwforcorrect set studentname=?,link=?,correct=0,time=sysdate() where hwid=? and studentid=?";
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, hw.getStudentname());
		pstmt.setString(2, hw.getCorrectlink());
		pstmt.setInt(3, hw.getHomeworkid());
		pstmt.setString(4, hw.getStudentid());
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	
	@Override
	public List<Homework> ListForSingleHWChart(int hwid) throws Exception{
		ArrayList<Homework> list = new ArrayList<Homework>();
		String sql="select studentid,result from hwforcorrect where correct=1 and hwid='"+hwid+"' order by result desc";
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Homework hw = new Homework();
				hw.setStudentid(rs.getString(1));
				hw.setResult(rs.getInt(2));
				list.add(hw);
			}
		
		return list;
	}
	@Override
	public List<Homework> ListForClassHWChart(String kcid, String uid) throws Exception{
		ArrayList<Homework> list = new ArrayList<Homework>();
		String sql="select hwtitle,result from homework join hwforcorrect on homework.hwid=hwforcorrect.hwid where correct=1 and studentid='"+uid+"' and hwforcorrect.kcid='"+kcid+"'";
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Homework hw = new Homework();
				hw.setHomeworktitle(rs.getString(1));
				hw.setResult(rs.getInt(2));
				list.add(hw);
			}
		
		return list;
	}
	
	@Override
	public int getSubmitHWCount(String sql)throws Exception{
		int countrow = 0;    
		this.pstmt = this.conn.prepareStatement(sql);
		rs = this.pstmt.executeQuery();     
		  
		if(rs.next())      
		{       
		    countrow = rs.getInt(1);  
		}
		rs.close();//关闭ResultSet对象
		this.pstmt.close();//关闭PreparedStatement对象
		return countrow;
	}
	
//###############################################################################//
	
	@Override
	public int addGroup(Group g) throws Exception {
		String sql="insert into studentgroup (studentid,studentname,kcid,groupname,hwid) values (?,?,?,?,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, g.getStudentid());
		pstmt.setString(2, g.getStudentname());//
		pstmt.setString(3, g.getKcid());//
		pstmt.setString(4, g.getGroupname());//
		pstmt.setInt(5, g.getHwid());
		
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public List getGroupName(String sql) throws Exception {
		List list = new ArrayList();
		
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("groupname",rs.getString(1));
				list.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
			
			for  ( int  i = 0 ; i  <  list.size()-1 ; i++){     
				   for  ( int  j=list.size()-1 ; j>i; j--) {     
				       if  (list.get(j).equals(list.get(i)))  {     
				             list.remove(j);     
				        }      
				   }      
		    }    
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List getGroupInfo(String sql) throws Exception {
		List list = new ArrayList();
		
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("gid",rs.getInt(1));
				map.put("studentid",rs.getString(2));
				map.put("studentname",rs.getString(3));
				map.put("kcid",rs.getString(4));
				map.put("groupname",rs.getString(5));
				map.put("hwid",rs.getInt(6));
				list.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象  
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateGroup(String sql) throws Exception {
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}

	@Override
	public int updateGroupStatus(String hwid) throws Exception {
		String sql="update homework set answerlink='divided' where hwid='"+hwid+"'";
		int result=0;
		this.pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		result=pstmt.executeUpdate();//执行数据库操作
		this.pstmt.close();
		return result;
	}
}
