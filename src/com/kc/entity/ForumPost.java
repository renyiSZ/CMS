package com.kc.entity;

import java.util.List;

public class ForumPost {
	private int postid; // 
	private String userid; //po主
	private String username;
	private String posttitle; // 
	private String postcontent; // 
	private String posttime; // 
	private String posthead; //头像
	private int postclick;//点击次数
	private int postreply;//回复次数
	private String posttype; //帖子类型
	
	List<ForumPostReply> postReplies;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<ForumPostReply> getPostReplies() {
		return postReplies;
	}
	public void setPostReplies(List<ForumPostReply> postReplies) {
		this.postReplies = postReplies;
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
	public String getPosttitle() {
		return posttitle;
	}
	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	public String getPosttime() {
		return posttime;
	}
	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	public String getPosthead() {
		return posthead;
	}
	public void setPosthead(String posthead) {
		this.posthead = posthead;
	}
	/*public String getPostpic() {
		return postpic;
	}
	public void setPostpic(String postpic) {
		this.postpic = postpic;
	}*/
	public int getPostclick() {
		return postclick;
	}
	public void setPostclick(int postclick) {
		this.postclick = postclick;
	}
	public int getPostreply() {
		return postreply;
	}
	public void setPostreply(int postreply) {
		this.postreply = postreply;
	}
	public String getPosttype() {
		return posttype;
	}
	public void setPosttype(String posttype) {
		this.posttype = posttype;
	}
	
	
	
}
