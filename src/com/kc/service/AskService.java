package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import com.kc.dao.AskDao;
import com.kc.dao.AskDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Ask;

public class AskService implements AskDao {
	private DBConnection dbconn = null; // 定义数据库连接类
	private AskDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public AskService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new AskDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	
	@Override
	public int AddAsk(Ask ask) throws Exception {
		int result = 0;
		try {
			result =this.dao.AddAsk(ask);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public int AddAnswer(Ask ask) throws Exception {
		int result = 0;
		try {
			result =this.dao.AddAnswer(ask);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public int updateAsk(int askid) throws Exception {
		int result = 0;
		try {
			result = updateAsk(askid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public List ListAsk(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.ListAsk(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public List ListJoinQA(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.ListJoinQA(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

}
