package servlet;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import util.StringUtil;
import util.servlet.DispatcherServlet;
import vo.Level;
import factory.ServiceBackFactory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/level/LevelServletBack/*")
public class LevelServletBack extends DispatcherServlet {
	private final String insertValidate = "level.title|level.hisal|level.hisal" ;
	private final String updateValidate = "level.lid|level.title|level.hisal|level.hisal" ;
	
	Level level = new Level() ;
	public Level getLevel() {
		return this.level ;
	}
	
	public String insertPre() {
		return "back.level.insert.page" ;
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().insert(this.level)) {
				super.setMsgAndUrl("insert.success.msg", "back.level.insert.servlet") ;
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.level.insert.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().update(this.level)) {
				super.setMsgAndUrl("update.success.msg", "back.level.list.servlet") ;
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.level.list.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	public String listPre() {
		try {
			super.request.getSession().setAttribute("allLevels", 
					ServiceBackFactory.getILevelServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "back.level.list.page" ;
	}
	
	public String delete() {
		Set<Integer> lids = StringUtil.splitInteger(super.request.getParameter("lids")) ;
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().delete(lids)) {
				super.setMsgAndUrl("delete.success.msg", "back.level.list.servlet") ;
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.level.list.servlet") ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return "forward.page" ;
	}
	
	@Override
	public String getTitle() {
		return "级别";
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
