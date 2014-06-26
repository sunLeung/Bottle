package common.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import bean.UserBean;

/**
 * 
 * @Description 用户上下文
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class UserContext {
	private static Map<Integer, UserBean> userContext = new ConcurrentHashMap<Integer, UserBean>();
	
	public static boolean add(UserBean user){
		if(user!=null&&user.getId()!=0){
			userContext.put(user.getId(), user);
			return true;
		}
		return false;
	}
	
	public static UserBean get(int uuid){
		return userContext.get(uuid);
	}
	
	public static void remove(int uuid){
		userContext.remove(uuid);
	}
}
