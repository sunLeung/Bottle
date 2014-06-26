package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.MsgBean;

/**
 * 
 * @Description Msg数据库操作
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class MsgDao {
	private static Log log=LogFactory.getLog(MsgDao.class);
	
	public static MsgBean save(MsgBean bean){
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
	
	public static List<MsgBean> loadNotRead(long uuid){
		List<MsgBean> list=null;
		try {
			list=dbUtils.query(MsgBean.class, "where toUuid=? and isRead=?", uuid,0);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean update(MsgBean msg){
		boolean result=false;
		try {
			int ret=dbUtils.update(msg);
			if(ret==1)
				result=true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static String lastMsg(long uuid,long toUuid){
		String msg="";
		try {
			MsgBean msg1=dbUtils.read(MsgBean.class, "where uuid=? and toUuid=? ORDER BY time DESC LIMIT 1", uuid,toUuid);
			MsgBean msg2=dbUtils.read(MsgBean.class, "where uuid=? and toUuid=? ORDER BY time DESC LIMIT 1", toUuid,uuid);
			if(msg1.getTime().compareTo(msg2.getTime())>0){
				msg=msg1.getContent();
			}else{
				msg=msg2.getContent();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
}
