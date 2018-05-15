package servlet;

import javax.servlet.annotation.WebServlet;

import util.MD5;
import util.ValidateUtil;
import util.servlet.DispatcherServlet;
import vo.Admin;
import factory.ServiceFrontFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/AdminLogin/*")
public class AdminLoginServlet extends DispatcherServlet {
	private String loginValidate = "admin.aid|admin.password" ;
	private Admin admin = new Admin() ;
	
	public Admin getAdmin() {
		return this.admin ;
	}
	
	public String login() {
		if (super.checkCode()) {
			// 对用户输入的密码进行MD5加密
			this.admin.setPassword(new MD5().getMD5ofStr(this.admin.getPassword())) ;
			this.admin.setIp(super.request.getRemoteAddr()) ;
			try {
				if (ServiceFrontFactory.getIAdminServiceFrontInstance().login(admin)) {
					admin.setIp(super.request.getRemoteAddr()) ;
					if (admin.getType() == 1) {
						super.setMsgAndUrl("admin.login.success.msg", "back.admin.index.page") ;
						super.request.getSession().setAttribute("admin", admin) ;
					} else if (admin.getType() == 0) {
						super.setMsgAndUrl("admin.login.success.msg", "front.admin.index.page") ;
						super.request.getSession().setAttribute("admin", admin) ;
					}
					
				} else {
					super.setMsgAndUrl("admin.login.failure.msg", "login.page") ;
				}
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		} else {
			super.setMsgAndUrl("admin.codeError.msg", "login.page") ;
		}
		return "forward.page" ;
	}
	
	public String logout() {
		super.request.getSession().invalidate() ;
		super.setMsgAndUrl("admin.logout.msg", "login.page") ;
		return "forward.page" ;
	}
	
	public String changePassword() {
		String oldPassword = super.request.getParameter("oldPassword") ;
		String newPassword1 = super.request.getParameter("newPassword1") ;
		String newPassword2 = super.request.getParameter("newPassword2") ;
		if (ValidateUtil.validateEmpty(oldPassword) && 
				ValidateUtil.validateEmpty(newPassword1) && 
				ValidateUtil.validateEmpty(newPassword2)) {
			if (newPassword1.equals(newPassword2)) {
				Admin admin = (Admin) super.request.getSession().getAttribute("admin") ;
				oldPassword = new MD5().getMD5ofStr(oldPassword) ;
				newPassword1 = new MD5().getMD5ofStr(newPassword1) ;
				try {
					if (ServiceFrontFactory.getIAdminServiceFrontInstance().changePassword(admin.getAid(), oldPassword, newPassword1)) {
						super.setMsgAndUrl("changePassword.success.msg", "admin.logout.page") ;
					} else {
						super.setMsgAndUrl("changePassword.oldPassword.wrong.msg", "admin.changePassword.page") ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				super.setMsgAndUrl("validate.string.failture.msg", "admin.changePassword.page") ;
			}
		} else {
			super.setMsgAndUrl("changePassword.same.failure.msg", "admin.changePassword.page") ;
		}
		return "forward.page" ;
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUploadDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}
}
