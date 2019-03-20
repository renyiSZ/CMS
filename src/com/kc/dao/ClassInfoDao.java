package com.kc.dao;

import java.util.List;

import com.kc.entity.ClassInfo;

public interface ClassInfoDao {
	public int addClass(ClassInfo classinfo)throws Exception;
	public List getClass(String sql) throws Exception;
	public int deleteClass(String classid) throws Exception;
	public int deleteClasses(String[] classid) throws Exception;
	public List getJoinKCandUsers(String sql)throws Exception;
	public int editClass(String classid, String teachingplan)throws Exception;
}
