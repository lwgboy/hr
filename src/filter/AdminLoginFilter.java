package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/pages/back/*", "/pages/front/*", "/pages/admin_change_passsword.jsp"})
public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest requ, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requ ;
		HttpSession session = request.getSession() ;
		if (session.getAttribute("admin") != null) {
			filterChain.doFilter(requ, resp);
		} else {
			request.setAttribute("msg", "您还未登录！") ;
			request.setAttribute("url", "/login.jsp") ;
			request.getRequestDispatcher("/pages/forward.jsp").forward(requ, resp) ; ;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
