package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

import common.Context.UserContext;

/**
 * 
 * @Description 验证用户是否登陆过滤器
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		req.setCharacterEncoding("utf-8");
		res.setHeader("content-type", "text/html;charset=UTF-8");
		String uri = req.getRequestURI();
		if (uri.endsWith("login.jsp") || uri.endsWith("/login")
				|| uri.startsWith("/fonts/") || uri.endsWith(".css")
				|| uri.endsWith(".ico") || uri.endsWith(".js")
				|| uri.endsWith("test.jsp")) {
			arg2.doFilter(req, res);
			return;
		}

		HttpSession sess = req.getSession();
		Integer uuid = (Integer) sess.getAttribute("uuid");
		if (uuid == null) {
			res.sendRedirect("/login.jsp");
		} else {
			UserBean user = UserContext.get(uuid);
			if (user == null) {
				res.sendRedirect("/login.jsp");
			} else {
				arg2.doFilter(req, res);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
