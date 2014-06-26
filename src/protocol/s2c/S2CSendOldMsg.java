package protocol.s2c;

import protocol.OP;
import protocol.SPacket;
import bean.MsgBean;
import dao.MsgDao;

/**
 * 
 * @Description 服务器发送数据包（离线消息）
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class S2CSendOldMsg extends SPacket{
	private MsgBean msg;

	public S2CSendOldMsg(long uuid,MsgBean msg){
		this.uuid=uuid;
		this.msg=msg;
	}
	
	@Override
	public void handleImp() {
		data.put("msg", msg.getContent());
	}

	@Override
	protected void setOp() {
		this.op=OP.SEND_MSG;
	}

	@Override
	protected void sendSuccess() {
		// TODO Auto-generated method stub
		msg.setIsRead(1);
		MsgDao.update(msg);
	}

	@Override
	protected void sendFail() {
		// TODO Auto-generated method stub
		
	}


}
