package protocol.c2s;

import java.sql.Timestamp;

import protocol.CPacket;
import protocol.s2c.S2CSendMsg;
import service.BottleService;
import utils.JsonUtils;
import anno.Packet;
import bean.MsgBean;

/**
 * 
 * @Description 服务器接收聊天信息，并转发
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
@Packet("0x01")
public class C2SSendMsg extends CPacket{
	private MsgBean msg;
	@Override
	protected void handleImp() {
		// TODO Auto-generated method stub
		msg=new MsgBean();
		msg.setContent(JsonUtils.getString("content", requestData));
		msg.setIsRead(0);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		msg.setToUuid(JsonUtils.getLong("to", requestData));
		msg.setUuid(uuid);
		if(BottleService.checkFriend(uuid,msg.getToUuid())){
			handleMsg();
		}
		else{
			msg.setContent("你和对方没有瓶子关系");
			msg.setToUuid(uuid);
			new S2CSendMsg(new long[]{uuid},msg).send();
		}
	}
	
	private void handleMsg(){
		new S2CSendMsg(new long[]{msg.getToUuid()},msg).send();
	}
	

}
