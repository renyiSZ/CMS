package com.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.dao.UserDao;
import com.kc.dao.UserDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Users;

public class UsersService implements UserDao {
	private DBConnection dbconn = null; // 定义数据库连接类
	private UserDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public UsersService() throws Exception { 
		dbconn = new DBConnection();
		dao = new UserDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	public int addUser(Users user) throws Exception {
		int result = 0;
		try {
			result = this.dao.addUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	public int deleteUser(String uid) throws Exception {
		int result = 0;
		try {
			result = this.dao.deleteUser(uid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int deleteUsers(String[] uid) throws Exception{
		int result = 0;
		try {
			result = this.dao.deleteUsers(uid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int editInfo(String sql) throws Exception{
		int result = 0;
		try {
			result = this.dao.editInfo( sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	public List getUsers(String sql) throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getUsers(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
		
	}
	
	/*public int editInfo(int uid, String uname, String email) throws Exception {
		int result = 0;
		try {
			result = this.dao.editInfo(uid, uname, email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	public int editPasswd(int uid, String passwd) throws Exception {
		int result = 0;
		try {
			result = this.dao.editPasswd(uid, passwd);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	public Users queryByEmail(String email) throws Exception {
		Users user = new Users();
		try {
			user = this.dao.queryByEmail(email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return user;
	}*/
    
	@Override
	public Users queryByID(String uid) throws Exception {
		Users user = new Users();
		try {
			user = dao.queryByID(uid);
		} catch (Exception e) {
			throw e;
		} finally {
			dbconn.close();
		}
		return user;
	}
	/////////////////////////////////
	 @Override
	 public Map searchClass(String userid)throws Exception{
		 Map map=new HashMap();
			try {
				map = this.dao.searchClass(userid);
			} catch (Exception e) {
				throw e;
			} finally {
				this.dbconn.close();
			}
			return map;
	 }
	 @Override
	    public List searchStudent(String sql)throws Exception{
		 List list = new ArrayList();
			try {
				list = this.dao.searchStudent(sql);
			} catch (Exception e) {
				throw e;
			} finally {
				this.dbconn.close();
			}
			return list;
	 }
	 @Override
	  public List searchTableClassUsers(String sql)throws Exception{
		 List list = new ArrayList();
			try {
				list = this.dao.searchTableClassUsers(sql);
			} catch (Exception e) {
				throw e;
			} finally {
				this.dbconn.close();
			}
			return list;
	 }
}

