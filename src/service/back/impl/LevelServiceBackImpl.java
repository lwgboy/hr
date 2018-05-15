package service.back.impl;

import java.util.List;
import java.util.Set;

import service.back.ILevelServiceBack;
import vo.Level;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class LevelServiceBackImpl implements ILevelServiceBack {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public boolean insert(Level level) throws Exception {
		try {
			if (level.getHisal() < level.getLosal()) {
				return false ;
			}
			if (DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitle(level.getTitle()) == null) {
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doCreate(level) ;
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
	public boolean delete(Set<Integer> lids) throws Exception {
		try {
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doRemoveBatch(lids) ;
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}
	
	@Override
	public boolean update(Level level) throws Exception {
		try {
			if (level.getHisal() < level.getLosal()) {
				return false ;
			}
			if (DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitle(level.getTitle(),level.getLid()) == null) {
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doUpdate(level) ;
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
	public List<Level> list() throws Exception {
		try {
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll() ;
		} catch(Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
