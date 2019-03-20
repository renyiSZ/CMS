package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.dao.UserDao;
import com.kc.entity.Users;

public class UserDaoImpl implements UserDao{
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;

	// 通过构造方法取得数据库连接
	public UserDaoImpl(Connection conn) { 
		this.conn = conn;  
	}
	
	@Override
	public int addUser(Users user) throws Exception{
		String sql = "insert into users(uid,ujob,uname,upass,uemail,usex,uhead,uphone,ubirthday) values(?,?,?,?,?,?,?,?,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, user.getUid());//设定用户用户名
		pstmt.setString(2, user.getJob());//设定用户密码
		pstmt.setString(3, user.getUname());//设定用户Email
		pstmt.setString(4, user.getPasswd());
		pstmt.setString(5, user.getEmail());
		pstmt.setInt(6, user.getUsex());
		pstmt.setString(7, user.getUhead());
		pstmt.setString(8, user.getUphone());
		pstmt.setString(9, user.getUbirthday());
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	@Override
	public int deleteUser(String uid) throws Exception{ //删除指定用户
		String sql = "delete from users where uid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, uid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	
	@Override
	public int deleteUsers(String[] uid) throws Exception{
		String sql = "delete from users where ";
		for(int i=0;i<uid.length;i++){
			if(i==0){
				sql=sql+"uid='"+uid[i]+"'";
			}
			else{
				sql=sql+" or uid='"+uid[i]+"'";
			}
		}
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;	
	}
	
	@Override
	public List getUsers(String sql) throws Exception{
		List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("uid",rs.getString(1));
				map.put("ujob",rs.getString(2));
				map.put("uname",rs.getString(3));
				map.put("upass",rs.getString(4));
				map.put("uemail",rs.getString(5));
				map.put("usex",rs.getInt(6));
				map.put("uhead",rs.getString(7));
				map.put("uphone",rs.getString(8));
				map.put("ubirthday",rs.getString(9));
				map.put("representative",rs.getString(10));
				map.put("assistant",rs.getString(11));
				l.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
	}
	/*public Users queryByEmail(String email) throws Exception{
		String sql = "select uid,uname,passwd,lastlogin from users where email=?";
		Users user = new Users();
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();//执行数据库操作
		if (rs.next()) {
			user.setUid(rs.getInt(1));//设定用户id
			user.setUname(rs.getString(2));//设定用户用户名
			user.setPasswd(rs.getString(3));//设定用户密码
			user.setEmail(email);//设定用户Email
			user.setLastLogin(rs.getDate(4));//设定用户登录时间
		}
		rs.close();//关闭ResultSet对象
		pstmt.close();//关闭PreparedStatement对象
		return user;
	}*/
	
	 @Override
	public Users queryByID(String uid) throws Exception{
		String sql = "select ujob,uname,upass,uemail,usex, uhead,uphone,ubirthday,representative,assistant from users where uid=?";
		Users user = new Users();
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1,uid);
		rs = pstmt.executeQuery();//执行数据库操作
		if (rs.next()) {
			user.setJob(rs.getString(1));//设定用户名
			user.setUname(rs.getString(2));
			user.setPasswd(rs.getString(3));//设定用户密码
			user.setUid(uid);//设定用户用户名
			user.setEmail(rs.getString(4));//设定用户Email
			user.setUsex(rs.getInt(5));
			user.setUhead(rs.getString(6));
			user.setUphone(rs.getString(7));
			user.setUbirthday(rs.getString(8));
			user.setRepresentative(rs.getString(9));
			user.setAssistant(rs.getString(10));
			System.out.println("daoimpl  representative:"+rs.getString(9));
			//user.setLastLogin(rs.getDate(4));//设定用户登录时间
		}
		rs.close();//关闭ResultSet对象
		pstmt.close();//关闭PreparedStatement对象
		return user;
	}

	@Override
	public int editInfo(String sql) throws Exception{
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}

	//更新指定用户信息
/*	public int editInfo(int uid, String uname, String email) throws Exception{
		System.out.println(uid+"---"+uname+"---"+email);
		String sql="update users set uname=?,email=? where uid=?";
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, uname);
		pstmt.setString(2, email);
		pstmt.setInt(3, uid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	//修改用户密码
	public int editPasswd(int uid, String passwd) throws Exception{
		String sql="update users set passwd=? where uid=?";
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, passwd);
		pstmt.setInt(2, uid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}*/
    
    //////////////////////////////////////////////////////////
    @Override
    public Map searchClass(String userid)throws Exception{
    	Map map=new HashMap();
		try{
			String sql="select kc1,kc2,kc3,kc4,kc5,kc6,kc7,kc8,kc9,kc10 from class where userid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				map.put("userid",userid);
				map.put("kc1",rs.getString(1));
				map.put("kc2",rs.getString(2));
				map.put("kc3",rs.getString(3));
				map.put("kc4",rs.getString(4));
				map.put("kc5",rs.getString(5));
				map.put("kc6",rs.getString(6));
				map.put("kc7",rs.getString(7));
				map.put("kc8",rs.getString(8));
				map.put("kc9",rs.getString(9));
				map.put("kc10",rs.getString(10));
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
    }
    @Override
    public List searchStudent(String sql)throws Exception{
    	List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("studentid",rs.getString(1));
				map.put("studentname",rs.getString(2));
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
    public List searchTableClassUsers(String sql)throws Exception{
    	List l = new ArrayList();
		try{
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Map map=new HashMap();
				map.put("uid",rs.getString(1));
				//map.put("ujob",rs.getString(2));
				map.put("uname",rs.getString(3));
				//map.put("upass",rs.getString(4));
				map.put("uemail",rs.getString(5));
				map.put("usex",rs.getInt(6));
				map.put("uhead",rs.getString(7));
				map.put("uphone",rs.getString(8));
				//map.put("ubirthday",rs.getString(9));
				map.put("kc1",rs.getString(11));
				map.put("kc2",rs.getString(12));
				map.put("kc3",rs.getString(13));
				map.put("kc4",rs.getString(14));
				map.put("kc5",rs.getString(15));
				map.put("kc6",rs.getString(16));
				map.put("kc7",rs.getString(17));
				map.put("kc8",rs.getString(18));
				map.put("kc9",rs.getString(19));
				map.put("kc10",rs.getString(20));
				
				
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

