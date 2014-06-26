package protocol.s2c;

import protocol.OP;
import protocol.SPacket;

/**
 * 
 * @Description 服务器发送普通文字数据包
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class S2CSendString extends SPacket{
	private String msg;

	public S2CSendString(long uuid,String msg){
		this.uuid=uuid;
		this.msg=msg;
	}
	
	@Override
	public void handleImp() {
		data.put("msg", msg);
	}

	@Override
	protected void setOp() {
		this.op=OP.SEND_MSG;
	}

	@Override
	protected void sendSuccess() {
	}

	@Override
	protected void sendFail() {
	}


}
