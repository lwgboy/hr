package servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.BasePath;
import util.servlet.DispatcherServlet;
import vo.Salary;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/salary/SalaryServletBack/*")
public class SalaryServletBack extends DispatcherServlet {
	
	Salary salary = new Salary() ;
	public Salary getSalary() {
		return salary;
	}
	
	public String list() {
		super.handleSplit() ;
		try {
			Map<String,Object> map = ServiceBackFactory.getISalaryServiceBackInstance().listSplit(
					super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("allSalarys", map.get("allSalarys")) ;
			super.request.setAttribute("allRecorders", map.get("allCount")) ;
			super.request.setAttribute("url", BasePath.getBasePath(super.request) + "/pages/back/admin/salary/SalaryServletBack/list");
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.salary.list.page" ;
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
		return "雇员编号:eid|管理员编号:aid|" ;
	}

	@Override
	public String getDefaultColumn() {
		return "eid";
	}

}
