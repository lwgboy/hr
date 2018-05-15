package servlet;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import util.BasePath;
import util.StringUtil;
import util.servlet.DispatcherServlet;
import vo.Admin;
import vo.Employee;
import factory.ServiceFrontFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/front/emp/EmployeeServletFront/*")
public class EmployeeServletFront extends DispatcherServlet {
	private final String insertValidate = "emp.eid|emp.dept.did|emp.level.lid|emp.jobs.jid|emp.ename|emp.birthday|emp.sex|emp.idcard|emp.school|emp.profession|emp.grad|emp.status|emp.sal|emp.edu" ;
	private final String updatePreValidate = "emp.eid" ;
	private final String updateValidate = "emp.dept.did|emp.level.lid|emp.jobs.jid|emp.ename|emp.birthday|emp.sex|emp.idcard|emp.school|emp.profession|emp.grad|emp.status|emp.sal|emp.edu" ; ;
	
	Employee emp = new Employee() ;
	public Employee getEmp() {
		return emp ;
	}
	
	public String insertPre() {
		Map<String, Object> map = null ;
		try {
			map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().insertPre();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		super.request.setAttribute("allDepts", map.get("allDepts")) ;
		super.request.setAttribute("allLevels", map.get("allLevels")) ;
		super.request.setAttribute("allJobs", map.get("allJobs")) ;
		return "front.emp.insert.page" ;
	}
	
	public String insert() {
		this.emp.setAdmin((Admin) super.request.getSession().getAttribute("admin")) ;
		if (super.isUpload()) {
			this.emp.setPhoto(super.createSingleFileName()) ;
		} else {
			this.emp.setPhoto("nophoto.jpg") ;
		}
		this.emp.setIndate(new Date()) ;
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().insert(this.emp)) {
				if (super.isUpload()) {
					super.upload(this.emp.getPhoto()) ;
				}
				super.setMsgAndUrl("insert.success.msg", "front.emp.insert.servlet") ;
			} else {
				super.setMsgAndUrl("insert.failure.msg", "front.emp.insert.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String list() {
		super.handleSplit() ;	// 处理分页
		try {
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance()
				.listSplit(super.getCurrentPage(), super.getLineSize(), 
				super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("url", BasePath.getBasePath(super.request) + "/pages/front/emp/EmployeeServletFront/list") ;
			super.request.setAttribute("allRecorders", map.get("empCount")) ;
			super.request.setAttribute("allEmps", map.get("allEmps")) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "front.emp.list.page" ;
	}
	
	public String listStatus() {
		super.handleSplit() ;	// 处理分页
		try {
			int status = Integer.parseInt(super.request.getParameter("status")) ;
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance()
				.listSplitByStatus(status, super.getCurrentPage(), 
				super.getLineSize(), super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("url", "/pages/front/emp/EmpServletFront/listStatus?status=" + status) ;
			super.request.setAttribute("allRecorders", map.get("empCount")) ;
			super.request.setAttribute("allEmps", map.get("allEmps")) ;
			super.request.setAttribute("paramName", "status") ;
			super.request.setAttribute("paramValue", status) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "front.emp.list.page" ;
	}
	
	public String updatePre() {
		Map<String, Object> map = null ;
		String referer = super.request.getHeader("referer") ;
		String backUrl = "/pages/front/emp/EmployeeServletFront/" 
						+ referer.substring(referer.lastIndexOf("/") + 1) ;
		try {
			map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().updatePre(this.emp.getEid()) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		super.request.setAttribute("allDepts", map.get("allDepts")) ;
		super.request.setAttribute("allLevels", map.get("allLevels")) ;
		super.request.setAttribute("allJobs", map.get("allJobs")) ;
		super.request.getSession().setAttribute("emp", map.get("emp"));
		super.request.setAttribute("backUrl", backUrl) ;
		return "front.emp.update.page" ;
	}
	
	public String update() {
		Employee oldEmp = (Employee) super.request.getSession().getAttribute("emp") ;
		if (super.isUpload()) {
			if ("nophoto.jpg".equals(this.emp.getPhoto())) {
				this.emp.setPhoto(super.createSingleFileName()) ;
			}
		} else {
			emp.setPhoto(oldEmp.getPhoto()) ;
		}
		
		oldEmp.setAdmin((Admin) super.request.getSession().getAttribute("admin")) ;
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().update(this.emp, oldEmp)) {
				if (super.isUpload()) {
					super.upload(this.emp.getPhoto()) ;
				}
				super.setMsgAndUrl("update.success.msg", "front.emp.list.servlet") ;
			} else {
				super.setMsgAndUrl("update.failure.msg", "front.emp.list.servlet") ;
			}
			super.request.setAttribute("url", super.getSmart().getRequest().getParameter("backUrl")) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String updateStatus() {
		String eids = super.request.getParameter("eids") ;
		Set<Integer> set = StringUtil.splitInteger(eids) ;
		String referer = super.request.getHeader("referer") ;
		String backUrl = "/pages/front/emp/EmployeeServletFront/" 
						+ referer.substring(referer.lastIndexOf("/") + 1) ;
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateOut(set)) {
				super.setMsgAndUrl("front.emp.out.success", "front.emp.list.servlet") ;
			} else {
				super.setMsgAndUrl("front.emp.out.failure", "front.emp.list.servlet") ;
			}
			super.request.setAttribute("url", backUrl) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	@Override
	public String getTitle() {
		return "雇员" ;
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/emp/" ;
	}

	@Override
	public String getColumnData() {
		return "姓名:ename|部门名称:dname|职位名称:job|毕业院校:school|雇员学历:edu|雇员专业:profession" ;
	}

	@Override
	public String getDefaultColumn() {
		return "ename" ;
	}

}
