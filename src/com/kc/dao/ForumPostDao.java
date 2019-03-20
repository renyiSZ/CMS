package com.kc.dao;

import java.util.List;
import java.util.Map;

import com.kc.entity.ForumPost;
import com.kc.entity.ForumPostReply;



public interface ForumPostDao {
	//  帖子、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
	
	public int addPost(ForumPost post) throws Exception;
	public int delPost(int postid) throws Exception;
	//fen ye
	public int getCountrow(String sql) throws Exception;
	public List getAllPost(int from,String sql)throws Exception;
	//
	public Map queryByPid(int postID) throws Exception;
	public List getPost(String sql)throws Exception;
	
	public int editpost(int postid, int postclick, String postcontent) throws Exception;
	
	public List queryByUserid(String userid) throws Exception;
	public int addRead(int postid, int postclick) throws Exception;
	public int addReplycount(int postid, int postreply) throws Exception;
	
	//////////////////////////////////////////////////////////////////////////
	//  回帖、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
	
	public int addReply(ForumPostReply reply) throws Exception;
	public int delReply(int postid) throws Exception;
	public int deleteReply(int replyid) throws Exception;
	public List getAllReply(int postid)throws Exception;
	public List getMyReply(String userid)throws Exception;
	//public Map queryByPRid(int postReplyID) throws Exception;
}
