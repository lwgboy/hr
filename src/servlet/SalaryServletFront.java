package servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.servlet.DispatcherServlet;
import vo.Admin;
import vo.Salary;
import factory.ServiceFrontFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/front/salary/SalaryServletFront/*")
public class SalaryServletFront extends DispatcherServlet {
	private final String selectValidate = "salary.employee.eid" ;
	private final String insertValidate = "salary.employee.eid|salary.oldlid|salary.newlid|salary.oldsal|salary.newsal" ;
	
	Salary salary = new Salary() ;
	public Salary getSalary() {
		return salary;
	}
	
	public String selectPre() {
		return "front.salary.step1.page" ;
	}
	
	public String select() {
		Map<String,Object> map = null ;
		try {
			map =  ServiceFrontFactory.getISalaryServiceFrontInstance().select(this.salary.getEmployee().getEid()) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		if (map == null) {
			super.setMsgAndUrl("front.salary.select.failure", "front.salary.step1.servlet") ;
			return "forward.page" ;
		}
		super.request.setAttribute("emp", map.get("emp")) ;
		super.request.setAttribute("allLevels", map.get("allLevels")) ;
		return "front.salary.step2.page" ;
	}
	
	public String insert() {
		this.salary.setAdmin((Admin) super.request.getSession().getAttribute("admin")) ;
		try {
			if (ServiceFrontFactory.getISalaryServiceFrontInstance().insert(this.salary)) {
				super.setMsgAndUrl("front.salary.insert.success", "front.salary.step1.servlet") ;
			} else {
				super.setMsgAndUrl("front.salary.insert.failure", "front.salary.step1.servlet") ;
			}
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
