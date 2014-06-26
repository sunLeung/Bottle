package protocol;

import org.codehaus.jackson.JsonNode;

/**
 * 
 * @Description 客户端-->服务器 数据包
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public abstract class CPacket implements Cloneable {
	protected long uuid;
	protected JsonNode requestData;
	
	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	public JsonNode getRequestData() {
		return requestData;
	}

	public void setRequestData(JsonNode requestData) {
		this.requestData = requestData;
	}

	/**
	 * 运行协议实现方法
	 */
	protected abstract void handleImp();
	
	public void handle(){
		handleImp();
	}

	public CPacket clone() {
		try {
			return (CPacket) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
