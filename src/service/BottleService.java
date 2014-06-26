package service;

import java.sql.Timestamp;

import bean.FriendBean;
import bean.ThrowBean;
import dao.FriendDao;
import dao.ThrowDao;

public class BottleService {
	public static boolean throwBottle(long uuid,String content){
		boolean result=false;
		ThrowBean bean=new ThrowBean();
		bean.setContent(content);
		bean.setUuid(uuid);
		bean.setTime(new Timestamp(System.currentTimeMillis()));
		bean.setIsGet(0);
		ThrowBean saved=ThrowDao.save(bean);
		if(saved!=null){
			result=true;
		}
		return result;
	}
	
	public static ThrowBean getBottle(long uuid){
		ThrowBean bean=ThrowDao.loadRandom(uuid);
		if(bean!=null){
			FriendBean fb=FriendDao.load(uuid, bean.getUuid());
			if(fb!=null){
				return null;
			}
		}
		return bean;
	}
	
	public static ThrowBean openBottle(long id){
		ThrowBean bean=ThrowDao.loadById(id);
		return bean;
	}
	
	public static boolean checkFriend(long uuid,long fUuid){
		boolean result=false;
		FriendBean fb=FriendDao.load(uuid, fUuid);
		if(fb!=null){
			FriendBean fb1=FriendDao.load(fUuid, uuid);
			if(fb1==null){
				fb1=new FriendBean();
				fb1.setUuid(fUuid);
				fb1.setfUuid(uuid);
				FriendDao.save(fb1);
			}
			result=true;
		}
		return result;
	}
}
