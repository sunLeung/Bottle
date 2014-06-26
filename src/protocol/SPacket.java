package protocol;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import utils.JsonUtils;

import common.Context.ChannelContext;

/**
 * 
 * @Description 服务器-->客户端 数据包
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class SPacket {

	protected long uuid = -1;
	protected int op;
	protected Map data = new HashMap();

	/**
	 * 发送数据后是否关闭连接
	 */
	private boolean isSendClose = false;

	/**
	 * 具体实现
	 */
	public abstract void handleImp();

	protected abstract void setOp();

	protected abstract void sendSuccess();

	protected abstract void sendFail();

	/**
	 * 发送数据包
	 */
	public void send() {
		this.handleImp();
		this.setOp();
		data.put("op", op);
		Channel channel = ChannelContext.getChannel(uuid);
		if (channel != null && channel.isConnected()) {
			channel.write(new TextWebSocketFrame(JsonUtils.jsonFromObject(data)));
			if (isSendClose) {
				channel.close();
				ChannelContext.removeChannel(uuid);
			}
			sendSuccess();
		} else {
			sendFail();
			System.out.println("uuid:" + uuid + "信息发送失败");
		}
	}

	public boolean isSendClose() {
		return isSendClose;
	}

	public void setSendClose(boolean isSendClose) {
		this.isSendClose = isSendClose;
	}

	public void setUuid(long uuid){
		this.uuid=uuid;
	}
}
