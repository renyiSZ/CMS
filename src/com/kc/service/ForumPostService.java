package com.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.dao.ForumPostDao;
import com.kc.dao.ForumPostDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.ForumPost;
import com.kc.entity.ForumPostReply;
import com.kc.entity.Page;

public class ForumPostService implements ForumPostDao {
	private DBConnection dbconn = null; // 定义数据库连接类
	private ForumPostDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public ForumPostService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new ForumPostDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	//////////////////////////post
	public int addPost(ForumPost post) throws Exception{
		int result = 0;
		try {
			result =this.dao.addPost(post);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int delPost(int postid) throws Exception{
		int result = 0;
		try {
			result = this.dao.delPost(postid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
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
	public List getAllPost(int from, String sql)throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getAllPost(from, sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	public List getPost(String sql)throws Exception{
		
		List list = new ArrayList();
		try {
			list = this.dao.getPost(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	public Map queryByPid(int postID) throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.queryByPid(postID);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map;
	}
	
	public Page pageListPost(int thispage,String sql1, String sql2) throws Exception{
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
		List list=this.dao.getAllPost(thispage,sql2);
		page.setList(list);
		return page;
	}
	public int editpost(int postid, int postclick, String posttype) throws Exception{
		int result = 0;
		try {
			result = this.dao.editpost(postid,postclick, posttype);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public List queryByUserid(String userid) throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.queryByUserid(userid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////reply
	
	public int addReply(ForumPostReply reply) throws Exception{
		int result = 0;
		try {
			result =this.dao.addReply(reply);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int delReply(int postid) throws Exception{
		int result = 0;
		try {
			result = this.dao.delReply(postid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int deleteReply(int replyid) throws Exception{
		int result = 0;
		try {
			result = this.dao.deleteReply(replyid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	public List getAllReply(int postid)throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getAllReply(postid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	public List getMyReply(String userid)throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getMyReply(userid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	public int addRead(int postid, int postclick) throws Exception{
		int result = 0;
		try {
			result = this.dao.addRead( postid, postclick);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public int addReplycount(int postid, int postreply) throws Exception{
		int result = 0;
		try {
			result = this.dao.addReplycount( postid, postreply);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
}
