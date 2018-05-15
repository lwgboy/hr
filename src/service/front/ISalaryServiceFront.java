package service.front;

import java.util.Map;

import vo.Salary;

public interface ISalaryServiceFront {
	/**
	 * 根据雇员编号查询雇员
	 * @param eid
	 * @return 
	 * <li>key="emp" value=IEmployeeDAO.findById()</li>
	 * <li>key="allLevels" value=IDeptDAO.findAll()</li>
	 */
	public Map<String,Object> select(int eid) throws Exception ;
	
	/**
	 * 执行以下业务：
	 * <li>判断工资是否在工资等级限定的范围内</li>
	 * <li>修改雇员表中的雇员信息</li>
	 * <li>在salary表中进行记录</li>
	 * @param salary
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Salary salary) throws Exception ;
}
