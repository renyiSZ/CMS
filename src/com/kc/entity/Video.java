package com.kc.entity;

public class Video {
	private String vid;  //id
	private String vtitle;//title
	private String vcontent;//content
	private String vlink;//link
	private String kcid;//class id
	private String userid;//uploader id
	private String username;
	private String vtime;// upload time
    private String vtype;
    private String vpic;
    private String userhead;
	
    
	public String getUserhead() {
		return userhead;
	}
	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getVpic() {
		return vpic;
	}
	public void setVpic(String vpic) {
		this.vpic = vpic;
	}
	public String getVid(){
		return vid;
	}
	public void setVid(String vid){
		this.vid=vid;
	}
	
	public String getVtitle(){
		return vtitle;
	}
	public void setVtitle(String vtitle){
		this.vtitle=vtitle;
	}
	
	public String getVcontent(){
		return vcontent;
	}
	public void setVcontent(String vcontent){
		this.vcontent=vcontent;
	}
	
	public String getVlink(){
		return vlink;
	}
	public void setVlink(String vlink){
		this.vlink=vlink;
	}
	
	public String getKcid(){
		return kcid;
	}
	public void setKcid(String kcid){
		this.kcid=kcid;
	}
	
	public String getUserid(){
		return userid;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	
	public String getVtime(){
		return vtime;
	}
	public void setVtime(String vtime){
		this.vtime=vtime;
	}
	
}
