package servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.BasePath;
import util.servlet.DispatcherServlet;
import vo.Employee;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/emp/EmployeeServletBack/*")
public class EmployeeServletBack extends DispatcherServlet {
	
	Employee emp = new Employee() ;
	public Employee getEmp() {
		return emp ;
	}
	
	public String listIn() {
		super.handleSplit() ;	// 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getIEmployeeServiceBackInstance()
				.listSplitByStatus(1, super.getCurrentPage(), 
				super.getLineSize(), super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("url", BasePath.getBasePath(super.request) + "/pages/back/admin/emp/EmployeeServletBack/listIn") ;
			super.request.setAttribute("allRecorders", map.get("empCount")) ;
			super.request.setAttribute("allEmps", map.get("allEmps")) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.emp.list.page" ;
	}
	
	public String listOut() {
		super.handleSplit() ;	// 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getIEmployeeServiceBackInstance()
				.listSplitByStatus(0, super.getCurrentPage(), 
				super.getLineSize(), super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("url", BasePath.getBasePath(super.request) + "/pages/back/admin/emp/EmployeeServletBack/listOut") ;
			super.request.setAttribute("allRecorders", map.get("empCount")) ;
			super.request.setAttribute("allEmps", map.get("allEmps")) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.emp.list.page" ;
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
		return "姓名:ename|部门名称:dname|职位名称:job|毕业院校:school|雇员学历:edu|雇员专业:profession" ;
	}

	@Override
	public String getDefaultColumn() {
		return "ename" ;
	}

}
