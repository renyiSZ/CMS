package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import com.kc.dao.ClassInfoDao;
import com.kc.dao.ClassInfoDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.ClassInfo;

public class ClassInfoService implements ClassInfoDao{
	private DBConnection dbconn = null; // 定义数据库连接类
	private ClassInfoDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public ClassInfoService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new ClassInfoDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	
	@Override
	public int addClass(ClassInfo classinfo) throws Exception {
		int result = 0;
		try {
			result =this.dao.addClass(classinfo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public List getClass(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getClass(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public int deleteClass(String classid) throws Exception {
		int result = 0;
		try {
			result = this.dao.deleteClass(classid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public int deleteClasses(String[] classid) throws Exception {
		int result = 0;
		try {
			result = this.dao.deleteClasses(classid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public List getJoinKCandUsers(String sql)throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getJoinKCandUsers(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public int editClass(String classid, String teachingplan) throws Exception {
		int result = 0;
		try {
			result = this.dao.editClass(classid, teachingplan);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	
	}

}
