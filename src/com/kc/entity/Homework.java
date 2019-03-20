package com.kc.entity;

public class Homework {
	private int homeworkid;
	private String homeworktitle;
	private String homeworkdesc;
	private String kcid;
	private String userid;//laoshi
	private String username;//laoshi
	private String homeworktime;
	private String homeworklink;
	private String answerlink;
	
	private String correctlink;//student hand in link
	private String studentid;
	private String studentname;
	private int correctid;
	private String teachercorrecttime;//pigai time
	private int status;
	private int result;
	private String handintime;
	private String correcttime;//交作业时间
	private String comment;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAnswerlink() {
		return answerlink;
	}
	public void setAnswerlink(String answerlink) {
		this.answerlink = answerlink;
	}
	public String getTeachercorrecttime() {
		return teachercorrecttime;
	}
	public void setTeachercorrecttime(String teachercorrecttime) {
		this.teachercorrecttime = teachercorrecttime;
	}
	public String getCorrectlink() {
		return correctlink;
	}
	public void setCorrectlink(String correctlink) {
		this.correctlink = correctlink;
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
	public int getCorrectid() {
		return correctid;
	}
	public void setCorrectid(int correctid) {
		this.correctid = correctid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getHandintime() {
		return handintime;
	}
	public void setHandintime(String handintime) {
		this.handintime = handintime;
	}
	public String getCorrecttime() {
		return correcttime;
	}
	public void setCorrecttime(String correcttime) {
		this.correcttime = correcttime;
	}
	
	
	
	public String getHomeworkdesc() {
		return homeworkdesc;
	}
	public void setHomeworkdesc(String homeworkdesc) {
		this.homeworkdesc = homeworkdesc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHomeworkid(){
		return homeworkid;
	}
	public void setHomeworkid(int homeworkid){
		this.homeworkid=homeworkid;
	}
	
	public String getHomeworktitle(){
		return homeworktitle;
	}
	public void setHomeworktitle(String homeworktitle){
		this.homeworktitle=homeworktitle;
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
	
	public String getHomeworktime(){
		return homeworktime;
	}
	public void setHomeworktime(String homeworktime){
		this.homeworktime=homeworktime;
	}
	
	public String getHomeworklink(){
		return homeworklink;
	}
	public void setHomeworklink(String homeworklink){
		this.homeworklink=homeworklink;
	}
}
