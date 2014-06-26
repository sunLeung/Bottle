package protocol.s2c;

import protocol.OP;
import protocol.SPacket;
import bean.MsgBean;
import dao.MsgDao;

/**
 * 
 * @Description 服务器发送数据包（聊天功能）
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class S2CSendMsg extends SPacket{
	private MsgBean msg;
	private long[] uuids=null;

	public S2CSendMsg(long[] uuids,MsgBean msg){
		this.uuids=uuids;
		this.msg=msg;
	}
	
	public void send(){
		for(long uuid:uuids){
			setUuid(uuid);
			super.send();
		}
	}
	
	@Override
	public void handleImp() {
		data.put("uuid", msg.getUuid());
		data.put("to", msg.getToUuid());
		data.put("content", msg.getContent());
	}

	@Override
	protected void setOp() {
		this.op=OP.SEND_MSG;
	}

	@Override
	protected void sendSuccess() {
		// TODO Auto-generated method stub
		msg.setToUuid(uuid);
		msg.setIsRead(1);
		MsgDao.save(msg);
	}

	@Override
	protected void sendFail() {
		// TODO Auto-generated method stub
		msg.setToUuid(uuid);
		msg.setIsRead(0);
		MsgDao.save(msg);
	}


}
