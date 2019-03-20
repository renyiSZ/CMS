package com.kc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kc.entity.ForumPost;
import com.kc.entity.ForumPostReply;


public class ForumPostDaoImpl implements ForumPostDao{
	private Connection conn = null; // 数据库连接对象
	private PreparedStatement pstmt = null; // 数据库操作对象
	ResultSet rs = null;
	
	public ForumPostDaoImpl(Connection conn) { 
		this.conn = conn;  
	}
	//帖子部分
	////////////////////////////////////////////////////////////////
	@Override
	public int addPost(ForumPost post) throws Exception{
		String sql = "insert into post(userid,username,posttitle,postcontent,posttime,posthead,postclick,postreply,posttype) values(?,?,?,?,sysdate(),?,0,0,?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setString(1, post.getUserid());
		pstmt.setString(2, post.getUsername());
		pstmt.setString(3, post.getPosttitle());//
		pstmt.setString(4, post.getPostcontent());//
		pstmt.setString(5, post.getPosthead());
		pstmt.setString(6,post.getPosttype());
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;	
		
	}
	
	@Override
	public int delPost(int postid) throws Exception{
		String sql = "delete from post where postid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, postid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	
	//得出所有post记录数
	@Override
	public int getCountrow(String sql) throws Exception{
		//String sql="select count(*) from post";
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
	//public int delPost(int postid) throws Exception;
	
	//分页的list
	@Override
	public List getAllPost(int from, String sql)throws Exception{
		List l = new ArrayList();
		try{
			//String sql="select * from post order by posttime desc limit ?,?";
			sql=sql+" limit ?,?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, (from-1)*10);  //from-1 *10变成记录数， from是页数
			pstmt.setInt(2, 10);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("postid",rs.getInt(1));
				map.put("userid",rs.getString(2));
				map.put("username",rs.getString(3));
				map.put("posttitle",rs.getString(4));
				map.put("postcontent",rs.getString(5));
				map.put("posttime",rs.getString(6));
				map.put("posthead",rs.getString(7));
				map.put("postclick",rs.getString(8));
				map.put("postreply",rs.getString(9));
				map.put("posttype",rs.getString(10));
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
	public Map queryByPid(int postID) throws Exception{
		Map map=new HashMap();
		try{
			String sql="select userid,username,posttitle,postcontent,posttime,posthead,postclick,postreply,posttype from post where postid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, postID);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				map.put("postid",postID);
				map.put("userid",rs.getString(1));
				map.put("username",rs.getString(2));
				map.put("posttitle",rs.getString(3));
				map.put("postcontent",rs.getString(4));
				map.put("posttime",rs.getString(5));
				map.put("posthead",rs.getString(6));
				map.put("postclick",rs.getString(7));
				map.put("postreply",rs.getString(8));
				map.put("posttype",rs.getString(9));
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return map;
	}
	@Override
	// 增加click 以及管理员修改分类
	public int editpost(int postid, int postclick, String posttype) throws Exception{
		String sql="update post set postclick=?,posttype=? where postid=?";
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, postclick);
		pstmt.setString(2, posttype);
		pstmt.setInt(3, postid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	
	@Override
	// 查个人发帖
	public List queryByUserid(String userid) throws Exception{
		List l = new ArrayList();
		try{
			String sql="select * from post where userid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("postid",rs.getInt(1));
				map.put("userid",rs.getString(2));
				map.put("username",rs.getString(3));
				map.put("posttitle",rs.getString(4));
				map.put("postcontent",rs.getString(5));
				map.put("posttime",rs.getString(6));
				map.put("posthead",rs.getString(7));
				map.put("postclick",rs.getString(8));
				map.put("postreply",rs.getString(9));
				map.put("posttype",rs.getString(10));
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
	public List getPost(String sql)throws Exception{
		List l = new ArrayList();
		try{
			
			this.pstmt = this.conn.prepareStatement(sql);
			
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("postid",rs.getInt(1));
				map.put("userid",rs.getString(2));
				map.put("username",rs.getString(3));
				map.put("posttitle",rs.getString(4));
				map.put("postcontent",rs.getString(5));
				map.put("posttime",rs.getString(6));
				map.put("posthead",rs.getString(7));
				map.put("postclick",rs.getString(8));
				map.put("postreply",rs.getString(9));
				map.put("posttype",rs.getString(10));
				l.add(map);
			}
			rs.close();//关闭ResultSet对象
			this.pstmt.close();//关闭PreparedStatement对象
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
	}
	
	
	/////////////////////////////////////////////////
	//回帖
	/////////////////////////////////////////////////////
	@Override
	public int addReply(ForumPostReply reply) throws Exception{
		String sql = "insert into reply(postid,userid,replyuserid,replyusername,replycontent,replytime,replyhead) values(?,?,?,?,?,sysdate(),?)";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1,reply.getPostid());
		pstmt.setString(2, reply.getUserid());//
		pstmt.setString(3, reply.getReplyuserid());//
		pstmt.setString(4,reply.getReplyusername());
		pstmt.setString(5, reply.getReplycontent());
		
		pstmt.setString(6, reply.getReplyhead());//回复次数和浏览次数先设为0
		
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;	
	}
	@Override
	//帖子删除后，评论一并删除
	public int delReply(int postid) throws Exception{
		String sql = "delete from reply where postid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, postid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	@Override
	public int deleteReply(int replyid) throws Exception{
		String sql = "delete from reply where postreplyid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
		pstmt.setInt(1, replyid);
		result = pstmt.executeUpdate();//执行数据库操作
		pstmt.close();//关闭PreparedStatement对象
		return result;
	}
	@Override
	public List getAllReply(int postid)throws Exception{
		List l = new ArrayList();
		try{
			String sql="select postreplyid,userid,replyuserid, replyusername,replycontent, replytime,replyhead from reply where postid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1,postid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("postreplyid",rs.getInt(1));//userid,replyuserid,replyusername,replycontent,replytime,replyhead
				map.put("postid",postid);
				map.put("userid",rs.getString(2));
				map.put("replyuserid",rs.getString(3));
				map.put("replyusername",rs.getString(4));
				map.put("replycontent",rs.getString(5));
				map.put("replytime",rs.getString(6));
				map.put("replyhead",rs.getString(7));
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
	public List getMyReply(String userid)throws Exception{
		List l = new ArrayList();
		try{
			String sql="select * from reply where replyuserid=?";
			this.pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = this.pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				HashMap map=new HashMap();
				map.put("postreplyid",rs.getInt(1));//userid,replyuserid,replyusername,replycontent,replytime,replyhead
				map.put("postid",rs.getInt(2));
				map.put("userid",rs.getString(3));
				map.put("replyuserid",rs.getString(4));
				map.put("replyusername",rs.getString(5));
				map.put("replycontent",rs.getString(6));
				map.put("replytime",rs.getString(7));
				map.put("replyhead",rs.getString(8));
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
	public int addRead(int postid, int postclick) throws Exception{
		String sql="";
		if (postclick==1){
		sql="update post set postclick=postclick+1 where postid=?";}
		else if(postclick==-1){
			sql="update post set postclick=postclick-1 where postid=?";}
		int result;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
	
		pstmt.setInt(1, postid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
	@Override
	public int addReplycount(int postid, int postreply) throws Exception{
		String sql="";
		if (postreply==1){
		sql="update post set postreply=postreply+1 where postid=?";}
		else if(postreply==-1){
			sql="update post set postreply=postreply-1 where postid=?";}
		
		int result=0;
		pstmt=this.conn.prepareStatement(sql);//获取PreparedStatement对象
		
		pstmt.setInt(1, postid);
		result=pstmt.executeUpdate();//执行数据库操作
		pstmt.close();
		return result;
	}
}
