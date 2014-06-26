package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import bean.FriendBean;
import bean.UserBean;

import common.Context.UserContext;

import dao.FriendDao;
import dao.MsgDao;
import dao.UserDao;

/**
 * 
 * @Description 用户服务类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class UserService {
	public static boolean login(HttpSession session,String userName,String password){
		boolean result=false;
		UserBean user=UserDao.getUser(userName, password);
		if(user!=null){
			session.setAttribute("uuid", user.getId());
			UserContext.add(user);
			result=true;
		}
		return result;
	}
	
	public static List<Map<String,Object>> getFriendList(long uuid){
		List<Map<String,Object>> flist=new ArrayList<Map<String,Object>>();
		List<FriendBean> fbs=FriendDao.loadById(uuid);
		if(fbs!=null)
		for(FriendBean fb:fbs){
			String lastContent=MsgDao.lastMsg(fb.getUuid(),fb.getfUuid());
			UserBean friend=UserDao.getUserById(fb.getfUuid());
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("uuid", fb.getfUuid());
			map.put("content", lastContent);
			map.put("name", friend.getUname());
			flist.add(map);
		}
		return flist;
	}
}
