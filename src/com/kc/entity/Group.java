package com.kc.entity;

public class Group {
	private int gid;
	private String studentid;
	private String studentname;
	private String kcid;
	private String groupname;
	private int hwid;
	
	
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getHwid() {
		return hwid;
	}
	public void setHwid(int hwid) {
		this.hwid = hwid;
	}
	
}
