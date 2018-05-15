package service.back.impl;

import java.util.HashMap;
import java.util.Map;

import service.back.IWorkServiceBack;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class WorkServiceBackImpl implements IWorkServiceBack {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public Map<String,Object> listSplit(int currentPage, int lineSize, 
			String column, String keyWord) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("allWorks", DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord)) ;
			map.put("allCount", DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).getCount(column, keyWord)) ;
			return map ;
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
