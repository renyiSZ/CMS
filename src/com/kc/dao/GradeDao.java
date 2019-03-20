package com.kc.dao;
import java.util.List;

import com.kc.entity.Grade;
public interface GradeDao {
	
	public List<Grade> getAllGrade(String sql) throws Exception;
	// public List<Grade> getAllByExcel(String file) throws Exception;
	
	public int addGrade(Grade grade)throws Exception;
	public int updateGrade(String sql)throws Exception;
	public boolean isExist(String studentid, String kcid, String gname)throws Exception;
	public List searchTestName(String sql)throws Exception;
}
