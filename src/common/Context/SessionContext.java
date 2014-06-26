package common.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

public class SessionContext {
	public static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();
	
	public static void add(HttpSession session,String ip){
		if (session != null&&ip!=null) {
//			String key=DigestUtils.shaHex(session.getId()+ip);
			String key=DigestUtils.shaHex(session.getId());
			sessions.put(key, session);
		}
	}
	
	public static void remove(String sessionId,String ip){
		if (sessionId != null&&ip!=null) {
//			String key=DigestUtils.shaHex(sessionId+ip);
			String key=DigestUtils.shaHex(sessionId);
			sessions.remove(key);
		}
	}
	
	public static HttpSession get(String sessionId,String ip){
		if (sessionId != null&&ip!=null) {
//			String key=DigestUtils.shaHex(sessionId+ip);
			String key=DigestUtils.shaHex(sessionId);
			return sessions.get(key);
		}
		return null;
	}
}
