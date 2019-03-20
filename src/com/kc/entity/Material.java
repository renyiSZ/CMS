package com.kc.entity;

public class Material {
		private int mid;  //material id
		private String mtitle;//material title
		private String mlink;//material link
		private String kcid;//class id
		private String userid;//uploader id
		private String mtime;// upload time
		private String mdesc;//material description
		private String mtype;
		
		public String getMtype() {
			return mtype;
		}
		public void setMtype(String mtype) {
			this.mtype = mtype;
		}
		public int getMid(){
			return mid;
		}
		public void setMid(int mid){
			this.mid=mid;
		}
		
		public String getMtitle(){
			return mtitle;
		}
		public void setMtitle(String mtitle){
			this.mtitle=mtitle;
		}
		
		public String getMlink(){
			return mlink;
		}
		public void setMlink(String mlink){
			this.mlink=mlink;
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
		
		public String getMtime(){
			return mtime;
		}
		public void setMtime(String mtime){
			this.mtime=mtime;
		}
		
		public String getMdesc(){
			return mdesc;
		}
		public void setMdesc(String mdesc){
			this.mdesc=mdesc;
		}
		
}
