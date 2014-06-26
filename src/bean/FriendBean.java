package bean;

/**
 * 
 * @Description 用户关系 实体类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class FriendBean extends Pojo{
	private long uuid;
	private long fUuid;
	public long getUuid() {
		return uuid;
	}
	public void setUuid(long uuid) {
		this.uuid = uuid;
	}
	public long getfUuid() {
		return fUuid;
	}
	public void setfUuid(long fUuid) {
		this.fUuid = fUuid;
	}
}
