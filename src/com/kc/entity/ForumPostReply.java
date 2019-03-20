package com.kc.entity;

public class ForumPostReply {
	private int postreplyid; // postreplyid,postid,userid,replyuserid,replyusername,replycontent,replytime,replyhead
	private int postid; // 
	private String userid; //被回复的 
	private String replyuserid; // 回复者
	private String replyusername; // 回复者名字
	private String replycontent; // 
	private String replytime; // 
	private String replyhead; //
	
	
	public String getReplyusername() {
		return replyusername;
	}
	public void setReplyusername(String replyusername) {
		this.replyusername = replyusername;
	}
	public int getPostreplyid() {
		return postreplyid;
	}
	public void setPostreplyid(int postreplyid) {
		this.postreplyid = postreplyid;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getReplyuserid() {
		return replyuserid;
	}
	public void setReplyuserid(String replyuserid) {
		this.replyuserid = replyuserid;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public String getReplyhead() {
		return replyhead;
	}
	public void setReplyhead(String replyhead) {
		this.replyhead = replyhead;
	}
	
	
}
