package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import protocol.s2c.S2CSendMsg;
import dao.FriendDao;
import dao.ThrowDao;

import service.BottleService;
import utils.JsonUtils;
import utils.StringUtils;
import bean.FriendBean;
import bean.MsgBean;
import bean.ThrowBean;

/**
 * 
 * @Description 瓶子 控制器
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class BottleAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if("throwBottle".equals(op)){
			throwBottle(req,resp);
		}else if("getBottle".equals(op)){
			getBottle(req, resp);
		}else if("openBottle".equals(op)){
			openBottle(req,resp);
		}
	}
	
	private void throwBottle(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String content=req.getParameter("content");
		//code:0失败1成功2格式错误
		Map<String,Integer> json=new HashMap<String,Integer>();
		HttpSession session=req.getSession();
		Integer uuid=(Integer)session.getAttribute("uuid");
		if(content==null||"".equals(content)){
			json.put("code", 2);
		}else{
			if(BottleService.throwBottle(uuid,content))
				json.put("code", 1);
			else
				json.put("code", 0);
		}
		PrintWriter out=resp.getWriter();
		out.write(JsonUtils.encode2Str(json));
	}
	
	private void getBottle(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Map<String,Object> json=new HashMap<String,Object>();
		HttpSession session=req.getSession();
		Integer uuid=(Integer)session.getAttribute("uuid");
		ThrowBean bean=BottleService.getBottle(uuid);
		if(bean!=null){
			json.put("code", 1);
			json.put("id", bean.getId());
			json.put("content", bean.getContent());
		}else{
			json.put("code", 0);
		}
		PrintWriter out=resp.getWriter();
		out.write(JsonUtils.encode2Str(json));
	}
	
	private void openBottle(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String id=req.getParameter("id");
		HttpSession session=req.getSession();
		Integer uuid=(Integer)session.getAttribute("uuid");
		Map<String,Object> json=new HashMap<String,Object>();
		if(id!=null&&!"".equals(id)&&StringUtils.isNumeric(id)){
			long bid=Long.valueOf(id);
			ThrowBean bean=BottleService.openBottle(bid);
			if(bean!=null){
				bean.setIsGet(1);
				FriendBean fb=new FriendBean();
				fb.setfUuid(bean.getUuid());
				fb.setUuid(uuid);
				
				MsgBean msg=new MsgBean();
				msg.setContent(bean.getContent());
				msg.setTime(new Timestamp(System.currentTimeMillis()));
				msg.setToUuid(uuid);
				msg.setUuid(bean.getUuid());
				
				ThrowDao.update(bean);
				FriendDao.save(fb);
				new S2CSendMsg(new long[]{uuid},msg).send();
				json.put("code", 1);
				json.put("uuid", bean.getUuid());
			}else{
				json.put("code", 0);
			}
		}else{
			json.put("code", 0);
		}
		PrintWriter out=resp.getWriter();
		out.write(JsonUtils.encode2Str(json));
	}
}
