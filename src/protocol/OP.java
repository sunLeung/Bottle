package protocol;

/**
 * 
 * @Description 协议码
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class OP {
	/**服务器下发协议起始位*/
	private static final Integer SERVER_BASE=0x10;
	/**服务器下发上线信息给客户端*/
	public static final Integer ONLINE=SERVER_BASE+0x01;
	/**服务器下发聊天信息给客户端*/
	public static final Integer SEND_MSG=SERVER_BASE+0x02;
	/**服务器下发好友列表给客户端*/
	public static final Integer SEND_FLIST=SERVER_BASE+0x03;
	
}
