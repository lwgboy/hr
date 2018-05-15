package servlet;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import util.StringUtil;
import util.ValidateUtil;
import util.servlet.DispatcherServlet;
import vo.Dept;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/dept/DeptServletBack/*")
public class DeptServletBack extends DispatcherServlet {
	private final String insertValidate = "dept.dname" ;
	
	private Dept dept = new Dept() ;
	public Dept getDept() {
		return this.dept ;
	}
	
	public String insertPre() {
		return "back.dept.insert.page" ;
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIDeptServiceBackInstance().insert(this.dept)) {
				super.setMsgAndUrl("insert.success.msg", "back.dept.insert.servlet") ;
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.dept.insert.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String update() {
		boolean flag = true ;
		Enumeration<String> enu = super.request.getParameterNames() ;
		while (enu.hasMoreElements()) {
			String did = enu.nextElement() ;
			String dname = super.request.getParameter(did) ;
			if (ValidateUtil.validateRegex(did, "\\d+") && 
					ValidateUtil.validateEmpty(dname)) {
				Dept vo = new Dept() ;
				vo.setDid(Integer.parseInt(did)) ;
				vo.setDname(dname) ;
				try {
					flag = flag && ServiceBackFactory.getIDeptServiceBackInstance().update(vo) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			} else {
				flag = false ;
			}
		}
		if (flag) {
			super.setMsgAndUrl("update.success.msg", "back.dept.list.servlet") ;
		} else {
			super.setMsgAndUrl("update.failure.msg", "back.dept.list.servlet") ;
		}
		return "forward.page" ;
	}
	
	public String listPre() {
		try {
			super.request.setAttribute("allDepts", ServiceBackFactory.getIDeptServiceBackInstance().list()) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.dept.list.page" ;
	}
	
	public String delete() {
		Set<Integer> dids = StringUtil.splitInteger(super.request.getParameter("dids")) ;
		try {
			if (ServiceBackFactory.getIDeptServiceBackInstance().delete(dids)) {
				super.setMsgAndUrl("delete.success.msg", "back.dept.list.servlet") ;
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.dept.list.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	@Override
	public String getTitle() {
		return "部门" ;
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
