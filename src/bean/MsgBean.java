package bean;

import java.sql.Timestamp;

/**
 * 
 * @Description 消息实体类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class MsgBean extends Pojo{
	private long uuid;
	private long toUuid;
	private String content;
	private Timestamp time;
	private int isRead;
	public long getUuid() {
		return uuid;
	}
	public void setUuid(long uuid) {
		this.uuid = uuid;
	}
	public long getToUuid() {
		return toUuid;
	}
	public void setToUuid(long toUuid) {
		this.toUuid = toUuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

}
