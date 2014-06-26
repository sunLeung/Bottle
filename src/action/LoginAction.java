package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import utils.JsonUtils;

import common.Context.SessionContext;
import common.Context.UserContext;

/**
 * 
 * @Description 登陆 登录控制器
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class LoginAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=req.getParameter("op");
		if("login".equals(op)){
			login(req,resp);
		}else if("logout".equals(op)){
			logout(req, resp);
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		PrintWriter out=resp.getWriter();
		HttpSession session=req.getSession();
		Map<String,Integer> json=new HashMap<String,Integer>();
		if(UserService.login(session,userName, password)){
			SessionContext.add(session, req.getRemoteAddr());
			Cookie cookie=new Cookie("key", session.getId());
			resp.addCookie(cookie);
			json.put("code", 1);
			out.write(JsonUtils.jsonFromObject(json));
		}else{
			json.put("code", 0);
			out.write(JsonUtils.jsonFromObject(json));
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session=req.getSession();
		Integer uuid=(Integer)session.getAttribute("uuid");
		session.removeAttribute("uuid");
		if(uuid!=null){
			UserContext.remove(uuid);
		}
		SessionContext.remove(session.getId(), req.getRemoteAddr());
		resp.sendRedirect("/login.jsp");
	}
}
