package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.FriendBean;

/**
 * 
 * @Description 用户关系 数据库操作
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class FriendDao {
	private static Log log=LogFactory.getLog(FriendDao.class);
	
	public static int save(FriendBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public static FriendBean load(long uuid,long fUuid){
		FriendBean bean=null;
		try {
			bean=dbUtils.read(FriendBean.class,"where uuid=? and fUuid=?",uuid,fUuid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static List<FriendBean> loadById(long uuid){
		List<FriendBean> beans=null;
		try {
			beans=dbUtils.query(FriendBean.class,"where uuid=?",uuid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return beans;
	}
}
