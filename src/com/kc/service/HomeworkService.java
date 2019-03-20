package com.kc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

import com.kc.dao.HomeworkDao;
import com.kc.dao.HomeworkDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Grade;
import com.kc.entity.Group;
import com.kc.entity.Homework;

public class HomeworkService implements HomeworkDao {
	private DBConnection dbconn = null; // 定义数据库连接类
	private HomeworkDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public HomeworkService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new HomeworkDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	@Override
	public int addHomework(Homework hw) throws Exception {
		int result = 0;
		try {
			result =this.dao.addHomework(hw);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public List getHomework(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getHomework(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	
	@Override
	public int addHWforCorrect(int hwid,String kcid,String studentid,String studentname,String teacherid) throws Exception {
		int result = 0;
		try {
			result =this.dao.addHWforCorrect(hwid, kcid, studentid,studentname,teacherid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public Map getSingleHW(String sql)throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.getSingleHW(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map; 
	}
	@Override
	public List getStudentHWs(String sql)throws Exception{
		List list = new ArrayList();
		try {
			list = this.dao.getStudentHWs(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public List getJoinInfo(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getJoinInfo(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public Map queryByhwid(int hwid)throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.queryByhwid( hwid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map; 
	}
	@Override
	public Map queryByCorrectid(int cid)throws Exception{
		Map map=new HashMap();
		try {
			map = this.dao.queryByCorrectid( cid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return map; 
	}
	@Override	
	public int correct(int correctid,int result, String comment)throws Exception{
		int r = 0;
		try {
			r =this.dao.correct( correctid, result, comment);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return r;
	}
	@Override
	public int queryHWID(String hwtitle, String kcid, String userid, String hwlink )throws Exception{
		int r = 0;
		try {
			r =this.dao.queryHWID(hwtitle, kcid,userid, hwlink );
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return r;
	}
	@Override
	public int handinUpdate(Homework hw)throws Exception{
		int r = 0;
		try {
			r =this.dao.handinUpdate(hw);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return r;
	}
	@Override
	public List<Homework> ListForSingleHWChart(int hwid) throws Exception{
		List<Homework> list = new ArrayList<Homework>();
		try {
			list = this.dao.ListForSingleHWChart( hwid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public List<Homework> ListForClassHWChart(String kcid, String uid) throws Exception{
		List<Homework> list = new ArrayList<Homework>();
		try {
			list = this.dao.ListForClassHWChart(kcid, uid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public int getSubmitHWCount(String sql)throws Exception{
		int count = 0;
		try {
			count =this.dao.getSubmitHWCount(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return count;
	}
	@Override
	public int addGroup(Group g) throws Exception {
		int result = 0;
		try {
			result =this.dao.addGroup(g);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	@Override
	public List getGroupName(String hwid) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getGroupName(hwid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public List getGroupInfo(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.getGroupInfo(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
	@Override
	public int updateGroup(String sql) throws Exception {
		int result = 0;
		try {
			result =this.dao.updateGroup(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	
	public static List<Group> getAllByExcel(String file, String kcid, String hwid){
		List<Group> list=new ArrayList<Group>();
		int hwidid=Integer.parseInt(hwid);
		try {
			Workbook rwb=Workbook.getWorkbook(new File(file));
			Sheet rs=rwb.getSheet(0);
			int clos=rs.getColumns();
			int rows=rs.getRows();
			
			System.out.println(clos+" rows:"+rows);
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					
					String number=rs.getCell(j++, i).getContents();
					String id=rs.getCell(j++, i).getContents();
					String name=rs.getCell(j++, i).getContents();
					String gname=rs.getCell(j++, i).getContents();
					
					Group g=new Group();
					g.setStudentid(id);
					g.setStudentname(name);
					g.setGroupname(gname);
					g.setKcid(kcid);
					g.setHwid(hwidid);
					
					list.add(g);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	@Override
	public int updateGroupStatus(String sql) throws Exception {
		int result = 0;
		try {
			result =this.dao.updateGroupStatus(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}
	

}
