package service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import service.front.ISalaryServiceFront;
import vo.Employee;
import vo.Level;
import vo.Salary;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class SalaryServiceFrontImpl implements ISalaryServiceFront {
	DatabaseConnection dbc = new DatabaseConnection() ;
	
	@Override
	public Map<String, Object> select(int eid) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>() ;
			Employee emp = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid) ;
			emp.setLevel(DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(emp.getLevel().getLid())) ;
			map.put("emp", emp) ;
			map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll()) ;
			return map ;
					
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean insert(Salary salary) throws Exception {
		try {
			// 1、判断工资是否在工资等级限定的范围内
			Level newLevel = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(salary.getNewlid()) ;
			if (salary.getNewsal() < newLevel.getLosal() || 
				salary.getNewsal() > newLevel.getHisal()) {
				return false ;
			}
			
			// 2、修改雇员表中的雇员信息
			if (!DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateLidAndSalById(salary.getNewlid(), salary.getNewsal(), salary.getEmployee().getEid())) {
				return false ;
			}
			
			// 3、在salary表中进行记录
			salary.setCdate(new Date()) ;
			return DAOFactory.getISalaryDAOInstance(this.dbc.getConnection()).doCreate(salary) ;			
			
		} catch (Exception e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

}
