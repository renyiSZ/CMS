package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.Material;
import com.kc.entity.Page;
import com.kc.entity.Share;

public class MaterialDaoImpl implements MaterialDao{
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	// 通过构造方法取得数据库连接
		public MaterialDaoImpl(Connection conn) { 
			this.conn = conn;  
		}
		
		@Override
		public int addMaterial(Material material) throws Exception{
			String sql = "insert into material(mtitle,kcid,userid,mdesc,mlink,mtime,mtype) values(?,?,?,?,?,sysdate(),?)";
			int result = 0;
			pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
			pstmt.setString(1, material.getMtitle());
			pstmt.setString(2, material.getKcid());//
			pstmt.setString(3, material.getUserid());//
			pstmt.setString(4, material.getMdesc());
			pstmt.setString(5, material.getMlink());
			pstmt.setString(6, material.getMtype());
			result = pstmt.executeUpdate();//执行数据库操作
			pstmt.close();
			return result;	
		}
		@Override
		public int deleteMaterial(int mid) throws Exception{
			String sql = "delete from material where mid=?";
			int result = 0;
			pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
			pstmt.setInt(1, mid);
			result = pstmt.executeUpdate();//执行数据库操作
			pstmt.close();//关闭PreparedStatement对象
			return result;
		}
		@Override
		public int editMaterial(int mid, String mtype, String mdesc, String kcid) throws Exception{
			String sql="update material set kcid='"+kcid+"',mdesc='"+mdesc+"', mtype='"+mtype+"' where mid='"+mid+"'";
			int result=0;
			this.pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
			result=pstmt.executeUpdate();//执行数据库操作
			this.pstmt.close();
			return result;
		}
		/*@Override
		public List listMaterial() throws Exception{
			List l = new ArrayList();
			try{
				String sql="select * from material";
				this.pstmt = this.conn.prepareStatement(sql);
				rs = this.pstmt.executeQuery();
				while(rs!=null&&rs.next()){
					HashMap map=new HashMap();
					map.put("mid",rs.getInt(1));
					map.put("mtitle",rs.getString(2));
					map.put("kcid",rs.getString(3));
					map.put("userid",rs.getString(4));
					map.put("mdesc",rs.getString(5));
					map.put("mlink",rs.getString(6));
					map.put("mtime",rs.getString(7));
					l.add(map);
				}
				rs.close();//关闭ResultSet对象
				this.pstmt.close();//关闭PreparedStatement对象
			}catch(SQLException e){
				e.printStackTrace();
			}
			return l;
		}*/
		
		//根据class获取material
		public List queryByClassID(String sql) throws Exception{
			List l = new ArrayList();
			try{
				
				this.pstmt = this.conn.prepareStatement(sql);
				rs = this.pstmt.executeQuery();
				while(rs!=null&&rs.next()){
					Map map=new HashMap();
					
					map.put("mid",rs.getString(1));
					map.put("mtitle",rs.getString(2));
					map.put("kcid",rs.getString(3));
					map.put("userid",rs.getString(4));
					map.put("mdesc",rs.getString(5));
					map.put("mlink",rs.getString(6));
					map.put("mtime",rs.getString(7));
					map.put("mtype",rs.getString(8));
					l.add(map);
				}
				rs.close();//关闭ResultSet对象
				this.pstmt.close();//关闭PreparedStatement对象
			}catch(SQLException e){
				e.printStackTrace();
			}
			return l;
		}
		//下载用
		@Override
		public Map queryByMid(int downloadID) throws Exception{
			Map map=new HashMap();
			try{
				String sql="select mtitle,kcid,userid,mdesc,mlink,mtime,mtype from material where mid=?";
				this.pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, downloadID);
				rs = this.pstmt.executeQuery();
				while(rs!=null&&rs.next()){
					map.put("mid",downloadID);
					map.put("mtitle",rs.getString(1));
					map.put("kcid",rs.getString(2));
					map.put("userid",rs.getString(3));
					map.put("mdesc",rs.getString(4));
					map.put("mlink",rs.getString(5));
					map.put("mtime",rs.getString(6));
					map.put("mtype",rs.getString(7));
				}
				rs.close();//关闭ResultSet对象
				this.pstmt.close();//关闭PreparedStatement对象
			}catch(SQLException e){
				e.printStackTrace();
			}
			return map;
		}
		
		//得出总material表中记录条数
		@Override
		public int getCountrow(String sql) throws Exception{
			//String sql="select count(*) from material";
			this.pstmt = this.conn.prepareStatement(sql);
			rs = this.pstmt.executeQuery();     
			int countrow = 0;      
			if(rs.next())      
			{       
			    countrow = rs.getInt(1);  
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
			return countrow;   

		}
		
		//分页的list
		@Override
		public List getMaterialByPage(int from,String sql) throws Exception{
			List l=new ArrayList();
			try{
			//String sql="select * from material order by mtime desc limit ?,?";
			sql=sql+" limit ?,?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, (from-1)*10);  //from-1 *10变成记录数， from是页数
			pstmt.setInt(2, 10);
			rs = this.pstmt.executeQuery();
			
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("mid",rs.getInt(1));
				map.put("mtitle",rs.getString(2));
				map.put("kcid",rs.getString(3));
				map.put("userid",rs.getString(4));
				map.put("mdesc",rs.getString(5));
				map.put("mlink",rs.getString(6));
				map.put("mtime",rs.getString(7));
				map.put("mtype",rs.getString(8));
				l.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
		}

		///##################################################################/////////////////
		
		
		@Override
		public List getShareFile(String sql) throws Exception {
			List l = new ArrayList();
			try{				
				this.pstmt = this.conn.prepareStatement(sql);
				rs = this.pstmt.executeQuery();
				while(rs!=null&&rs.next()){
					Map map=new HashMap();
					map.put("shareid",rs.getString(1));
					map.put("uploaderid",rs.getString(2));
					map.put("uploadername",rs.getString(3));
					map.put("sharelink",rs.getString(4));
					map.put("sharetime",rs.getString(5));
					map.put("kcid",rs.getString(6));
					map.put("group",rs.getString(7));					
					l.add(map);
				}
				rs.close();//关闭ResultSet对象
				this.pstmt.close();//关闭PreparedStatement对象
			}catch(SQLException e){
				e.printStackTrace();
			}
			return l;
		}

		@Override
		public Map queryByShareId(String sql) throws Exception {
			Map map=new HashMap();
			try{				
				this.pstmt = this.conn.prepareStatement(sql);				
				rs = this.pstmt.executeQuery();
				while(rs!=null&&rs.next()){
					map.put("shareid",rs.getString(1));
					map.put("uploaderid",rs.getString(2));
					map.put("uploadername",rs.getString(3));
					map.put("sharelink",rs.getString(4));
					map.put("sharetime",rs.getString(5));
					map.put("kcid",rs.getString(6));
					map.put("group",rs.getString(7));	
				}
				rs.close();//关闭ResultSet对象
				this.pstmt.close();//关闭PreparedStatement对象
			}catch(SQLException e){
				e.printStackTrace();
			}
			return map;
		}

		@Override
		public int addShareFile(Share s) throws Exception {
			String sql = "insert into share(uploaderid,uploadername,sharelink,sharetime,classid,groupname) values(?,?,?,sysdate(),?,?)";
			int result = 0;
			pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
			pstmt.setString(1, s.getUploaderid());
			pstmt.setString(2, s.getUploadername());//
			pstmt.setString(3, s.getSharelink());//
			pstmt.setString(4, s.getKcid());
			pstmt.setString(5, s.getGroup());
			
			result = pstmt.executeUpdate();//执行数据库操作
			pstmt.close();
			return result;
		}

		@Override
		public int deleteShareFile(int id) throws Exception {
			String sql = "delete from share where shareid=?";
			int result = 0;
			pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();//执行数据库操作
			pstmt.close();//关闭PreparedStatement对象
			return result;
		}
		
		
}
