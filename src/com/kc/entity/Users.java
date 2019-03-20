package com.kc.entity;

public class Users {
	private String uid;//用户id
	private String uname;//用户姓名
	private String passwd;//用户密码
    private String job;//用户班级号
	private String email;//用户的Email
	private int usex;
	private String uhead;
	private String uphone;
	private String ubirthday;
	private String representative;
	private String assistant;
	
//	private int phoneNumber;//用户的 手机号
	
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getAssistant() {
		return assistant;
	}
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUbirthday() {
		return ubirthday;
	}
	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}
	public int getUsex() {
		return usex;
	}
	public void setUsex(int usex) {
		this.usex = usex;
	}
	public String getUhead() {
		return uhead;
	}
	public void setUhead(String uhead) {
		this.uhead = uhead;
	}
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}*/

}

