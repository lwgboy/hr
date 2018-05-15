package servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.servlet.DispatcherServlet;
import vo.Admin;
import vo.Work;
import factory.ServiceFrontFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/front/work/WorkServletFront/*")
public class WorkServletFront extends DispatcherServlet {
	private final String selectValidate = "work.employee.eid" ;
	private final String insertValidate = "work.employee.eid|work.olddid|work.newdid|work.oldjid|work.newjid|work.olddname|work.oldjob" ;
	
	Work work = new Work() ;
	public Work getWork() {
		return work;
	}
	
	public String selectPre() {
		return "front.work.step1.page" ;
	}
	
	public String select() {
		Map<String,Object> map = null ;
		try {
			map =  ServiceFrontFactory.getIWorkServiceFrontInstance().select(this.work.getEmployee().getEid()) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		if (map == null) {
			super.setMsgAndUrl("front.work.select.failure", "front.work.step1.servlet") ;
			return "forward.page" ;
		}
		super.request.setAttribute("emp", map.get("emp")) ;
		super.request.setAttribute("allDepts", map.get("allDepts")) ;
		super.request.setAttribute("allJobs", map.get("allJobs")) ;
		return "front.work.step2.page" ;
	}
	
	public String insert() {
		this.work.setAdmin((Admin) super.request.getSession().getAttribute("admin")) ;
		try {
			if (ServiceFrontFactory.getIWorkServiceFrontInstance().insert(this.work)) {
				super.setMsgAndUrl("front.work.insert.success", "front.work.step1.servlet") ;
			} else {
				super.setMsgAndUrl("front.work.insert.failure", "front.work.step1.servlet") ;
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
