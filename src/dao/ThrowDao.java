package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.ThrowBean;

public class ThrowDao {
	private static Log log=LogFactory.getLog(ThrowDao.class);
	
	public static ThrowBean save(ThrowBean bean){
		try {
			int id=dbUtils.insert(bean);
			if(id!=-1){
				bean.setId(id);
			}
			return bean;
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static ThrowBean loadRandom(long uuid){
		ThrowBean bean=null;
		try {
			bean=dbUtils.read(ThrowBean.class, "where isGet=? and uuid!=? ORDER BY RAND() LIMIT 1", 0,uuid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static ThrowBean loadById(long id){
		ThrowBean bean=null;
		try {
			bean=dbUtils.read(ThrowBean.class,id);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static boolean update(ThrowBean bean){
		boolean result=false;
		try {
			int ret=dbUtils.update(bean);
			if(ret==1)
				result=true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
