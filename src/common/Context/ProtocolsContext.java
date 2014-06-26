package common.Context;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import protocol.CPacket;
import protocol.s2c.S2COline;
import utils.AnnoUtils;
import utils.JsonUtils;
import anno.Packet;

/**
 * 
 * @Description 协议上下文
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class ProtocolsContext {
	public static Map<Integer, CPacket> CPackets = new ConcurrentHashMap<Integer, CPacket>();

	/**
	 * 协议解析器
	 */
	public static void parseCPackets() {
		try {
			Set<Class<?>> set = AnnoUtils.getClasses("protocol.c2s");
			for (Class<?> clz : set) {
				Packet handler = clz.getAnnotation(Packet.class);
				if (handler != null) {
					String o = handler.value();
					int op = 0;
					if (o.indexOf("+") != -1) {
						String[] s = o.split("\\+");
						String a = s[0];
						String b = s[1];
						if (a.trim().startsWith("0x"))
							a = a.substring(2);
						if (b.trim().startsWith("0x"))
							b = b.substring(2);
						op = (Integer.parseInt(a.trim(), 16))
								+ (Integer.parseInt(b.trim(), 16));
					} else if (o.startsWith("0x")) {
						op = Integer.parseInt(o.substring(2));
					} else
						op = Integer.parseInt(o);
					if (CPackets.get(op) != null)
						System.out.println("[error] same op (" + o + ":"
								+ clz.getName() + " & "
								+ CPackets.get(op).getClass().getName()
								+ ")");
					CPackets.put(op, (CPacket) clz.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 协议分发器
	 * @param session
	 * @param is
	 */
	public static void handlePacket(ChannelHandlerContext ctx, String requestJSON) {
		try {
			JsonNode json=JsonUtils.decode(requestJSON);
			if(json == null)
				return;
			int op=JsonUtils.getInt("op", json);
			long uuid=JsonUtils.getLong("uuid", json);
			if(op == 0){
				onlineProtocol(ctx,uuid,JsonUtils.getString("key", json));
				return;
			}else if(op == -1){
				return;
			}
			
			CPacket cpacket=ProtocolsContext.CPackets.get(op);
			if(cpacket!=null){
				CPacket clone=cpacket.clone();
				clone.setUuid(uuid);
				clone.setRequestData(json);
				clone.handle();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static void onlineProtocol(ChannelHandlerContext ctx, long uuid,String sessionId){
		InetAddress ip=((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getAddress();
		HttpSession session=SessionContext.get(sessionId, ip.getHostAddress());
		if(session!=null){
			Integer sessionUuid=(Integer)session.getAttribute("uuid");
			if(sessionUuid!=null&&uuid==sessionUuid){
				ChannelContext.putChannel(uuid, ctx.getChannel());
				new S2COline(uuid, "登陆成功").send();
				return;
			}
		}
		Map<String,String> error=new HashMap<String, String>();
		error.put("op", "17");
		error.put("msg", "非法连接");
		ctx.getChannel().write(new TextWebSocketFrame(JsonUtils.encode2Str(error)));
	}
}
