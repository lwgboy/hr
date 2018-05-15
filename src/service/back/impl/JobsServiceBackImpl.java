package service.back.impl;

import java.util.List;
import java.util.Set;

import service.back.IJobsServiceBack;
import vo.Jobs;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class JobsServiceBackImpl implements IJobsServiceBack {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public boolean insert(Jobs jobs) throws Exception {
		try {
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitle(jobs.getTitle()) == null) {
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doCreate(jobs) ;
			} else {
				return false ;
			}
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean delete(Set<Integer> jids) throws Exception {
		try {
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doRemoveBatch(jids) ;
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}
	
	@Override
	public Jobs updatePre(int jid) throws Exception {
		try {
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(jid) ;
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean update(Jobs jobs) throws Exception {
		try {
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitle(jobs.getTitle(),jobs.getJid()) == null) {
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doUpdate(jobs) ;
			} else {
				return false ;
			}
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public List<Jobs> list() throws Exception {
		try {
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll() ;
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
