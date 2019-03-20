package com.kc.dao;
import java.util.List;
import java.util.Map;







import com.kc.entity.Material;
import com.kc.entity.Page;
import com.kc.entity.Share;


public interface MaterialDao {
			//添加资料
			public int addMaterial(Material material) throws Exception;
			public int deleteMaterial(int mid) throws Exception;
		    //list 资料
			//public List listMaterial() throws Exception;
			public int editMaterial(int mid, String mtype, String mdesc, String kcid) throws Exception;
			//通过ID查询     供下载使用
			public Map queryByMid(int downloadID) throws Exception;
			//修改资料
			//public int editMaterial(int mid,String kcid,String mdesc) throws Exception;
			
			//根据classid查询资料//老师按上传者查找
			public List queryByClassID(String sql) throws Exception;
			
			//根据教师id查询资料
			//public Material queryByTeacherID(String tid) throws Exception;
			
			//分页查询
			public List getMaterialByPage(int from, String sql) throws Exception;
			//查询数据库共有多少条记录
			public int getCountrow(String sql)throws Exception;
			
			
			
			//public Page pageListMaterial(int thispage) throws Exception;
			
			
			/////////////////////////////////////////////////////////////////////////////
			//////////////////share notes group share files部分
			public List getShareFile(String sql)throws Exception;
			public Map queryByShareId(String sql)throws Exception;
			public int addShareFile(Share s)throws Exception;
			public int deleteShareFile(int id) throws Exception;
			
			
}
