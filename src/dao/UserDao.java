package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.UserBean;

/**
 * 
 * @Description User数据库操作
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class UserDao {
	private static Log log=LogFactory.getLog(UserDao.class);
	
	public static int save(UserBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public static UserBean getUser(String userName,String password){
		UserBean user=null;
		try {
			user=dbUtils.read(UserBean.class, "where uname=? and password=?", userName,password);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	public static UserBean getUserById(long uuid){
		UserBean user=null;
		try {
			user=dbUtils.read(UserBean.class, uuid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	
	public static void main(String[] args) {
		UserBean user=new UserBean();
		user.setAge(24);
		user.setPassword("test");
		user.setUname("a");
		user.setSex(0);
		
		System.out.println(save(user));
		
//		UserBean user=getUser(null, null);
//		System.out.println(user);
	}
}
