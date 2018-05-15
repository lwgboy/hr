package service.back.impl;

import java.util.HashMap;
import java.util.Map;

import service.back.IEmployeeServiceBack;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class EmployeeServiceBackImpl implements IEmployeeServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection() ;

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
