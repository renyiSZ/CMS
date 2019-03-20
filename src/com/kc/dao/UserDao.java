package com.kc.dao;



import java.util.List;
import java.util.Map;

import com.kc.entity.Users;

public interface UserDao {
	   //添加用户
		public int addUser(Users user) throws Exception;
		//根据用户id，删除用户
		public int deleteUser(String uid) throws Exception;
		public int deleteUsers(String[] uid) throws Exception;
		public List getUsers(String sql) throws Exception;
		//修改用户信息
		//public int editInfo(int uid,String uname,String email) throws Exception;
		
		//修改用户密码
		//public int editPasswd(int uid,String passwd) throws Exception;
		
		
		public int editInfo(String sql) throws Exception;
		
		//根据用户id查询用户
		public Users queryByID(String uid) throws Exception;
	
		//根据用户Email查询用户
		//public Users queryByEmail(String email) throws Exception;
		
		////////////////////////////////////////////////
		//class table相关
		public Map searchClass(String userid)throws Exception;
		public List searchStudent(String sql)throws Exception;
		 
		//老师空间中学生信息用
		public List searchTableClassUsers(String sql)throws Exception;
}