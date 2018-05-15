package servlet;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import util.StringUtil;
import util.ValidateUtil;
import util.servlet.DispatcherServlet;
import vo.Jobs;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/jobs/JobsServletBack/*")
public class JobsServletBack extends DispatcherServlet {
	private final String insertValidate = "jobs.title" ;
	private final String updateValidate = "jobs.title" ;
	
	Jobs jobs = new Jobs() ;
	public Jobs getJobs() {
		return this.jobs ;
	}
	
	public String insertPre() {
		return "back.jobs.insert.page" ;
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().insert(this.jobs)) {
				super.setMsgAndUrl("insert.success.msg", "back.jobs.insert.servlet") ;
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.jobs.insert.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String updatePre() {
		String jid = super.request.getParameter("jid") ;
		if (ValidateUtil.validateRegex(jid, "\\d+")) {
			Jobs vo = null ;
			try {
				vo = ServiceBackFactory.getIJobsServiceBackInstance().updatePre(Integer.parseInt(jid));
			} catch (Exception e) {
				e.printStackTrace() ;
			}
			if (vo != null) {
				super.request.getSession().setAttribute("jobs", vo) ;
			} else {
				return "errors.page" ;
			}
		} else {
			return "errors.page" ;
		}
		return "back.jobs.update.page" ;
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().update(this.jobs)) {
				super.setMsgAndUrl("update.success.msg", "back.jobs.list.servlet") ;
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.jobs.list.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String listPre() {
		try {
			super.request.getSession().setAttribute("allJobs", 
					ServiceBackFactory.getIJobsServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.jobs.list.page" ;
	}
	
	public String delete() {
		Set<Integer> jids = StringUtil.splitInteger(super.request.getParameter("jids")) ;
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().delete(jids)) {
				super.setMsgAndUrl("delete.success.msg", "back.jobs.list.servlet") ;
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.jobs.list.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	@Override
	public String getTitle() {
		return "职位";
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
