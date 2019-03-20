package com.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;







import java.util.Map;

import com.kc.dao.VideoDao;
import com.kc.dao.VideoDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Page;
import com.kc.entity.Video;

public class VideoService implements VideoDao{
	private DBConnection dbconn = null; // 定义数据库连接类
	private VideoDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public VideoService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new VideoDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	@Override
	public List listVideoByPage(int page,String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.listVideoByPage(page,sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public List listVideo(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.listVideo(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public int saveVideo(Video video) throws Exception {
		int result = 0;
		try {
			result =this.dao.saveVideo(video);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public int getCountrow(String sql) throws Exception {
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
	
	public Page pageListVideo(int thispage,String sql1, String sql2) throws Exception{
		Page page=new Page();
		page.setThispage(thispage);
		int countrow=this.dao.getCountrow(sql1);
		page.setCountrow(countrow);
		//16 rows in one page
		int countpage=countrow/16+(countrow%16==0?0:1);
		page.setCountpage(countpage);
		page.setFirstpage(1);
		page.setRowperpage(16);
		page.setLastpage(countpage);
		page.setPrepage(thispage==1?1:thispage-1);
		page.setNextpage(thispage==countpage?countpage:thispage+1);
		List list=this.dao.listVideoByPage(thispage,sql2);
		page.setList(list);
		return page;
	}
	
	public Map queryByVid(int downloadID) throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.queryByVid(downloadID);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map;
	}
	
	public int deleteVideo(int vid) throws Exception{
		int result = 0;
		try {
			result =this.dao.deleteVideo(vid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
}
		 
	       