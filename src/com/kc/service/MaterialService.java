package com.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.kc.dao.MaterialDao;
import com.kc.dao.MaterialDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Material;
import com.kc.entity.Page;
import com.kc.entity.Share;


public class MaterialService implements MaterialDao{
	private DBConnection dbconn = null; // 定义数据库连接类
	private MaterialDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public MaterialService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new MaterialDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	public int addMaterial(Material material) throws Exception{
		int result = 0;
		try {
			result =this.dao.addMaterial(material);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int deleteMaterial(int mid) throws Exception{
		int result = 0;
		try {
			result =this.dao.deleteMaterial(mid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int editMaterial(int mid, String mtype, String mdesc, String kcid) throws Exception{
		int result = 0;
		try {
			result = editMaterial(mid,mtype,mdesc,kcid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	/*public List listMaterial() throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.listMaterial();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}*/
	public List queryByClassID(String sql) throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.queryByClassID(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	public Map queryByMid(int downloadID) throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.queryByMid(downloadID);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map;
	}
	
	public int getCountrow(String sql) throws Exception{
		int count = 0;
		try {
			count =this.dao.getCountrow(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return count;
	}
	public List getMaterialByPage(int from, String sql) throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getMaterialByPage(from,sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	public Page pageListMaterial(int thispage, String sql1, String sql2) throws Exception{
		Page page=new Page();
		page.setThispage(thispage);
		int countrow=this.dao.getCountrow(sql1);
		page.setCountrow(countrow);
		//10 rows in one page
		int countpage=countrow/10+(countrow%10==0?0:1);
		page.setCountpage(countpage);
		page.setFirstpage(1);
		page.setRowperpage(10);
		page.setLastpage(countpage);
		page.setPrepage(thispage==1?1:thispage-1);
		page.setNextpage(thispage==countpage?countpage:thispage+1);
		List list=this.dao.getMaterialByPage(thispage,sql2);
		page.setList(list);
		return page;
	}
	@Override
	public List getShareFile(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getShareFile(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public Map queryByShareId(String sql) throws Exception {
		Map map=new HashMap();
		try {
			map = this.dao.queryByShareId(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map;
	}
	@Override
	public int addShareFile(Share s) throws Exception {
		int result = 0;
		try {
			result =this.dao.addShareFile(s);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public int deleteShareFile(int id) throws Exception {
		int result = 0;
		try {
			result =this.dao.deleteShareFile( id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
}
