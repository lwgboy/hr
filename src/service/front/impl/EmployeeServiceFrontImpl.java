package service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import service.front.IEmployeeServiceFront;
import vo.Employee;
import vo.Level;
import vo.Salary;
import vo.Work;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class EmployeeServiceFrontImpl implements IEmployeeServiceFront {
	private DatabaseConnection dbc = new DatabaseConnection() ;

	@Override
	public Map<String, Object> insertPre() throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("allJobs", DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll()) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean insert(Employee emp) throws Exception {
		try {
			// 1、判断雇员编号是否存在
			if (DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(emp.getEid()) != null) {
				return false ;
			}
			
			// 2、根据雇员工资等级查询出等级范围以确认雇员工资是否合理
			Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(emp.getLevel().getLid()) ;
			if (level.getLosal() > emp.getSal() || 
				level.getHisal() < emp.getSal()) {
				return false ;
			}
			
			// 3、根据雇员部门编号查询出部门名称
			emp.setDname(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(emp.getDept().getDid()).getDname()) ;
			
			// 4、根据雇员职位编号查询出职位名称
			emp.setJob(DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(emp.getJobs().getJid()).getTitle()) ;
			
			// 5、判断雇员状态是在职还是离职，如果是离职则需要设置离职时间
			if (emp.getStatus()==0) {
				emp.setOutdate(new Date()) ;
			} else {
				// 6、对应部门人数加1
				if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentAddById(emp.getDept().getDid())) {
					return false ;
				}
			}
			
			// 7、进行雇员数据的保存
			return DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doCreate(emp) ;
			
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public Map<String, Object> updatePre(int eid) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("allJobs", DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("emp", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid)) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean update(Employee emp, Employee oldEmp) throws Exception {
		try {
			// 1、根据雇员工资等级查询出等级范围以确认雇员工资是否合理
			Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(emp.getLevel().getLid()) ;
			if (level.getLosal() > emp.getSal() || 
				level.getHisal() < emp.getSal()) {
				return false ;
			}
			
			// 2、根据雇员部门编号查询出部门名称
			String dname = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(emp.getDept().getDid()).getDname() ;
			String oldDname = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(oldEmp.getDept().getDid()).getDname() ;
			emp.setDname(dname) ;
			
			// 3、根据雇员职位编号查询出职位名称
			String job = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(emp.getJobs().getJid()).getTitle() ;
			String oldjob = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(oldEmp.getJobs().getJid()).getTitle() ;
			emp.setJob(job) ;
			
			// 4、判断雇员状态是在职还是离职，如果是离职则需要设置离职时间
			if (emp.getStatus()==0) {
				emp.setOutdate(new Date()) ;
				// 5、修改相应部门人数
				if (oldEmp.getStatus()==1) {
					if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentMinusById(oldEmp.getDept().getDid())) {
						return false ;
					}
				}
			} else {
				// 5、修改相应部门人数
				if (emp.getDept().getDid() != oldEmp.getDept().getDid()) {
					if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentMinusById(oldEmp.getDept().getDid())) {
						return false ;
					}
					if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentAddById(emp.getDept().getDid())) {
						return false ;
					}
				}
			}
			
			// 6、部门和职位变更记录
			if (!(emp.getDept().getDid().equals(oldEmp.getDept().getDid())) || 
					!(emp.getJobs().getJid().equals(oldEmp.getJobs().getJid()))) {
				Work work = new Work() ;
				work.setEmployee(emp) ;
				work.setAdmin(oldEmp.getAdmin()) ;
				work.setOlddid(oldEmp.getDept().getDid()) ;
				work.setNewdid(emp.getDept().getDid()) ;
				work.setOldjid(oldEmp.getJobs().getJid()) ;
				work.setNewjid(emp.getJobs().getJid()) ;
				work.setCdate(new Date()) ;
				work.setOlddname(oldDname) ;
				work.setNewdname(dname) ;
				work.setOldjob(oldjob) ;
				work.setNewjob(job) ;
				if (!DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).doCreate(work)) {
					return false ;
				}
			}
			
			// 7、工资变更记录
			if (!(emp.getSal().equals(oldEmp.getSal()))) {
				Salary salary = new Salary() ;
				salary.setEmployee(emp) ;
				salary.setAdmin(oldEmp.getAdmin()) ;
				salary.setOldlid(oldEmp.getLevel().getLid()) ;
				salary.setNewlid(emp.getLevel().getLid()) ;
				salary.setCdate(new Date()) ;
				salary.setOldsal(oldEmp.getSal()) ;
				salary.setNewsal(emp.getSal()) ;
				if (!DAOFactory.getISalaryDAOInstance(this.dbc.getConnection()).doCreate(salary)) {
					return false ;
				}
			}
			
			// 8、进行雇员数据的保存
			return DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdate(emp) ;

		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean updateOut(Set<Integer> ids) throws Exception {
		try {
			if(!DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateStatus(0, ids, new Date())) {
				return false ;
			}
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentMinusByIdBatch(ids) ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public Map<String, Object> listSplit(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String ,Object> map = new HashMap<String,Object>() ;
			map.put("allEmps", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord)) ;
			map.put("empCount", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getCount(column, keyWord)) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public Map<String, Object> listSplitByStatus(int status, int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String ,Object> map = new HashMap<String,Object>() ;
			map.put("allEmps", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(status, currentPage, lineSize, column, keyWord)) ;
			map.put("empCount", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getCountByStatus(status, column, keyWord)) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
