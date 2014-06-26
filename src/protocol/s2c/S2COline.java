package protocol.s2c;

import java.util.List;

import protocol.OP;
import protocol.SPacket;
import service.UserService;
import bean.MsgBean;
import dao.MsgDao;

/**
 * 
 * @Description 服务器发送数据包（上线功能）
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class S2COline extends SPacket{
	private String msg;
	public S2COline(long uuid,String msg){
		this.uuid=uuid;
		this.msg=msg;
	}
	
	@Override
	public void handleImp() {
		data.put("msg", msg);
	}

	@Override
	protected void setOp() {
		this.op=OP.ONLINE;
	}

	@Override
	protected void sendSuccess() {
		// TODO Auto-generated method stub
		List<MsgBean> list=MsgDao.loadNotRead(uuid);
		if(list!=null){
			for(MsgBean msg:list){
				new S2CSendOldMsg(uuid, msg).send();
			}
		}
		new S2CFriendList(uuid, UserService.getFriendList(uuid)).send();
	}

	@Override
	protected void sendFail() {
		// TODO Auto-generated method stub
		
	}

}
