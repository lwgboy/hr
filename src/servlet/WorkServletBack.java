package servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import util.BasePath;
import util.servlet.DispatcherServlet;
import vo.Work;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/work/WorkServletBack/*")
public class WorkServletBack extends DispatcherServlet {
	
	Work work = new Work() ;
	public Work getWork() {
		return work;
	}
	
	public String list() {
		super.handleSplit() ;
		try {
			Map<String,Object> map = ServiceBackFactory.getIWorkServiceBackInstance().listSplit(
					super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord()) ;
			super.request.setAttribute("allWorks", map.get("allWorks")) ;
			super.request.setAttribute("allRecorders", map.get("allCount")) ;
			super.request.setAttribute("url", BasePath.getBasePath(super.request) + "/pages/back/admin/work/WorkServletBack/list");
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.work.list.page" ;
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
