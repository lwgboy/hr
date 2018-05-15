package service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import service.front.IWorkServiceFront;
import vo.Work;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class WorkServiceFrontImpl implements IWorkServiceFront {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public Map<String,Object> select(int eid) throws Exception  {
		try {
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("emp", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid)) ;
			map.put("allDepts",DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll()) ;
			map.put("allJobs",DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll()) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean insert(Work work) throws Exception {
		try {
			// 1、修改雇员表中的信息
			if (!DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateDidAndJidById(work.getNewdid(), work.getNewjid(), work.getEmployee().getEid())) {
				return false ;
			}
			String newdname = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(work.getNewdid()).getDname() ;
			String newjob = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(work.getNewjid()).getTitle() ;
			if (!DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateDnameAndJobById(newdname, newjob, work.getEmployee().getEid())) {
				return false ;
			}
			
			// 2、修改部门人数信息
			if (work.getNewdid()!=work.getOlddid()) {
				if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentMinusById(work.getOlddid())) {
					return false ;
				}
				if (!DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateCurrentAddById(work.getNewdid())) {
					return false ;
				}
			}
			
			// 3、在work表中进行记录
			work.setNewdname(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(work.getNewdid()).getDname()) ;
			work.setNewjob(DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(work.getNewjid()).getTitle()) ;
			work.setCdate(new Date()) ;
			return DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).doCreate(work) ;
			
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
