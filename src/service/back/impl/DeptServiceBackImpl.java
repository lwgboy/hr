package service.back.impl;

import java.util.List;
import java.util.Set;

import service.back.IDeptServiceBack;
import vo.Dept;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class DeptServiceBackImpl implements IDeptServiceBack {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public boolean insert(Dept dept) throws Exception {
		try {
			if (DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDname(dept.getDname()) == null) {
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doCreate(dept) ;
			} else {
				return false ;
			}
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean delete(Set<Integer> dids) throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doRemoveBatch(dids) ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean update(Dept dept) throws Exception {
		try {
			if (DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDname(dept.getDname(),dept.getDid()) == null) {
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdate(dept) ;
			} else {
				return false ;
			}
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public List<Dept> list() throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll() ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}
}
