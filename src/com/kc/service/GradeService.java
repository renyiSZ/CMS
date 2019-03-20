package com.kc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.kc.dao.GradeDao;
import com.kc.dao.GradeDaoImpl;
import com.kc.db.DBConnection;
import com.kc.entity.Grade;
import com.kc.entity.Homework;

public class GradeService implements GradeDao{
	private DBConnection dbconn = null; // 定义数据库连接类
	private GradeDao dao = null; // 声明DAO对象
	// 在构造方法中实例化数据库连接，同时实例化dao对象
	public GradeService() throws Exception { 
		this.dbconn = new DBConnection();
		this.dao = new  GradeDaoImpl(this.dbconn.getConnection());// 实例化GoodDao的实现类
	}
	
	@Override
	public List<Grade> getAllGrade(String sql) throws Exception {
		List<Grade> list = new ArrayList<Grade>();
		try {
			list = this.dao. getAllGrade(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}

	@Override
	public int addGrade(Grade grade) throws Exception {
		int result = 0;
		try {
			result =this.dao.addGrade(grade);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}


	public static List<Grade> getAllByExcel(String file, String kcid, String teacherid, String teachername){
		List<Grade> list=new ArrayList<Grade>();
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
					String grade=rs.getCell(j++, i).getContents();
					
					float mark=Float.parseFloat(grade);
					Grade g=new Grade();
					g.setGrade(mark);
					g.setStudentid(id);
					g.setStudentname(name);
					g.setGname(gname);
					g.setKcid(kcid);
					g.setTeacherid(teacherid);
					g.setTeachername(teachername);
					
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
	public int updateGrade(String sql)throws Exception {
		int result = 0;
		try {
			result =this.dao.updateGrade(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return result;
	}

	@Override
	public boolean isExist(String studentid, String kcid, String gname)
			throws Exception {
		boolean flag=false;
		try {
			flag =this.dao.isExist(studentid, kcid,  gname);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return flag;
	}

	@Override
	public List searchTestName(String sql) throws Exception {
		List list = new ArrayList();
		try {
			list = this.dao.searchTestName(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbconn.close();
		}
		return list;
	}
}
