package com.kc.entity;

public class Ask {
	private int askid;
	private int answerid;
	
	private String studentid;
	private String studentname;
	
	private String teacherid;
	private String teachername;
	
	private String asktoid;
	private String answertoid;
	
	private int status;//0未回答 1已回答
	private String asktime;
	private String answertime;
	
	private String askcontent;
	private String answercontent;
	
	
	
	
	public String getAskcontent() {
		return askcontent;
	}

	public void setAskcontent(String askcontent) {
		this.askcontent = askcontent;
	}

	public String getAnswercontent() {
		return answercontent;
	}

	public void setAnswercontent(String annswercontent) {
		this.answercontent = annswercontent;
	}

	public String getAsktime() {
		return asktime;
	}

	public void setAsktime(String asktime) {
		this.asktime = asktime;
	}

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public int getAskid() {
		return askid;
	}

	public void setAskid(int askid) {
		this.askid = askid;
	}

	public int getAnswerid() {
		return answerid;
	}

	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getAsktoid() {
		return asktoid;
	}

	public void setAsktoid(String asktoid) {
		this.asktoid = asktoid;
	}

	public String getAnswertoid() {
		return answertoid;
	}

	public void setAnswertoid(String answertoid) {
		this.answertoid = answertoid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
