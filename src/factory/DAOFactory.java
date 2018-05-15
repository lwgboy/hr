package factory;

import java.sql.Connection;

import dao.IActionDAO;
import dao.IAdminDAO;
import dao.IAdminlogsDAO;
import dao.IDeptDAO;
import dao.IEmployeeDAO;
import dao.IGroupsDAO;
import dao.IJobsDAO;
import dao.ILevelDAO;
import dao.ISalaryDAO;
import dao.IWorkDAO;
import dao.impl.ActionDAOImpl;
import dao.impl.AdminDAOImpl;
import dao.impl.AdminlogsDAOImpl;
import dao.impl.DeptDAOImpl;
import dao.impl.EmployeeDAOImpl;
import dao.impl.GroupsDAOImpl;
import dao.impl.JobsDAOImpl;
import dao.impl.LevelDAOImpl;
import dao.impl.SalaryDAOImpl;
import dao.impl.WorkDAOImpl;

public class DAOFactory {
	public static IAdminDAO getIAdminDAOInstance(Connection conn) {
		return new AdminDAOImpl(conn) ;
	}
	public static IAdminlogsDAO getIAdminlogsDAOInstance(Connection conn) {
		return new AdminlogsDAOImpl(conn) ;
	}
	public static IGroupsDAO getIGroupsDAOInstance(Connection conn) {
		return new GroupsDAOImpl(conn) ;
	}
	public static IActionDAO getIActionDAOInstance(Connection conn) {
		return new ActionDAOImpl(conn) ;
	}
	public static IDeptDAO getIDeptDAOInstance(Connection conn) {
		return new DeptDAOImpl(conn) ;
	}
	public static IJobsDAO getIJobsDAOInstance(Connection conn) {
		return new JobsDAOImpl(conn) ;
	}
	public static ILevelDAO getILevelDAOInstance(Connection conn) {
		return new LevelDAOImpl(conn) ;
	}
	public static IEmployeeDAO getIEmployeeDAOInstance(Connection conn) {
		return new EmployeeDAOImpl(conn) ;
	}
	public static IWorkDAO getIWorkDAOInstance(Connection conn) {
		return new WorkDAOImpl(conn) ;
	}
	public static ISalaryDAO getISalaryDAOInstance(Connection conn) {
		return new SalaryDAOImpl(conn) ;
	}
}
