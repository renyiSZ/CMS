package com.kc.dao;

import java.util.List;

import com.kc.entity.Ask;

public interface AskDao {
	
	public int AddAsk(Ask ask)throws Exception;
	
	public int AddAnswer(Ask ask)throws Exception;
	
	public int updateAsk(int askid) throws Exception;
	
	public List ListAsk(String sql)throws Exception;
	
	public List ListJoinQA(String sql)throws Exception;
}
