package com.kc.factory;

import com.kc.dao.AskDao;
import com.kc.dao.ClassInfoDao;
import com.kc.dao.ForumPostDao;
import com.kc.dao.GradeDao;
import com.kc.dao.HomeworkDao;
import com.kc.dao.MaterialDao;
import com.kc.dao.UserDao;
import com.kc.dao.VideoDao;
import com.kc.service.AskService;
import com.kc.service.ClassInfoService;
import com.kc.service.ForumPostService;
import com.kc.service.GradeService;
import com.kc.service.HomeworkService;
import com.kc.service.MaterialService;
import com.kc.service.UsersService;
import com.kc.service.VideoService;

public class DAOFactory {
	//取得用户业务操作类
		public static UserDao getUserDAOInstance()throws Exception {   
	        return new UsersService();    
	    }
		
		public static MaterialDao getMaterialDAOInstance()throws Exception {   
	        return new MaterialService();    
	    }
		public static ForumPostDao getForumPostDAOInstance()throws Exception {   
	        return new ForumPostService();    
	    }
		public static VideoDao getVideoDAOInstance()throws Exception {   
	        return new VideoService();    
	    }
		public static ClassInfoDao getClassInfoDAOInstance()throws Exception {   
	        return new ClassInfoService();    
	    }
		public static HomeworkDao getHomeworkDAOInstance()throws Exception {   
	        return new HomeworkService();    
	    }
		public static GradeDao getGradeDAOInstance()throws Exception {   
	        return new GradeService();    
	    }
		public static AskDao getAskDAOInstance()throws Exception {   
	        return new AskService();    
	    }
}
