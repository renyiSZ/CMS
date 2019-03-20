package com.kc.dao;

import java.util.List;
import java.util.Map;

import com.kc.entity.Group;
import com.kc.entity.Homework;


public interface HomeworkDao {
	public int addHomework(Homework hw) throws Exception;
	
	public List getHomework(String sql) throws Exception;
	public int addHWforCorrect(int hwid,String kcid,String studentid,String studentname, String teacherid) throws Exception;
	public List getJoinInfo(String sql) throws Exception;
	public Map queryByhwid(int hwid)throws Exception;
	public Map queryByCorrectid(int cid)throws Exception;
	public int queryHWID(String hwtitle, String kcid, String userid, String hwlink )throws Exception;
	////////////////
	public Map getSingleHW(String sql)throws Exception;//dafenshi yong
	public List getStudentHWs(String sql)throws Exception;//pigai liebiao
	public int correct(int correctid,int result, String comment)throws Exception;
	//////////////
	
	public int getSubmitHWCount(String sql)throws Exception;
	
	
	//学生提交作业更新数据
	public int handinUpdate(Homework hw)throws Exception;
	
	//用于单次作业分析图表
	public List<Homework> ListForSingleHWChart(int hwid) throws Exception;
	public List<Homework> ListForClassHWChart(String kcid, String uid) throws Exception;
	///
	
	
	/////divide group
	public int addGroup(Group g)throws Exception;
	public List getGroupName(String sql)throws Exception;
	public List getGroupInfo(String sql)throws Exception;
	public int updateGroup(String sql)throws Exception;
	public int updateGroupStatus(String hwid)throws Exception;
	
}
