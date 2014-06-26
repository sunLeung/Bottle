package protocol.s2c;

import java.util.List;
import java.util.Map;

import protocol.OP;
import protocol.SPacket;

/**
 * 
 * @Description 服务器发送数据包（聊天列表功能）
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class S2CFriendList extends SPacket{
	private List<Map<String,Object>> flist;
	public S2CFriendList(long uuid,List<Map<String,Object>> flist){
		this.uuid=uuid;
		this.flist=flist;
	}
	
	@Override
	public void handleImp() {
		data.put("flist", flist);
	}

	@Override
	protected void setOp() {
		this.op=OP.SEND_FLIST;
	}

	@Override
	protected void sendSuccess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendFail() {
		// TODO Auto-generated method stub
		
	}

}
