package bean;

import java.sql.Timestamp;

/**
 * 
 * @Description 扔出去的瓶子 实体类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class ThrowBean extends Pojo{
	private long uuid;
	private String content;
	private Timestamp time;
	private int isGet;
	public long getUuid() {
		return uuid;
	}
	public void setUuid(long uuid) {
		this.uuid = uuid;
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
	public int getIsGet() {
		return isGet;
	}
	public void setIsGet(int isGet) {
		this.isGet = isGet;
	}
}
