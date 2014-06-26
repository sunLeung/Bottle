package common.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;

/**
 * 
 * @Description Channel上下文
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class ChannelContext {
	private static Map<Long, Channel> channels = new ConcurrentHashMap<Long, Channel>();

	public static void putChannel(long uuid,Channel channel){
		channels.put(uuid, channel);
	}
	
	public static Channel getChannel(long uuid){
		return channels.get(uuid);
	}
	
	public static void removeChannel(long uuid){
		channels.remove(uuid);
	}
	
}
