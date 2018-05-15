package service.front;

import java.util.Map;

import vo.Work;

public interface IWorkServiceFront {
	/**
	 * 根据雇员编号查询雇员
	 * @param eid
	 * @return 
	 * <li>key="emp" value=IEmployeeDAO.findById()</li>
	 * <li>key="allDepts" value=IDeptDAO.findAll()</li>
	 * <li>key="allJobs" value=IDeptDAO.findAll()</li>
	 */
	public Map<String,Object> select(int eid) throws Exception ;
	
	/**
	 * 执行以下业务：
	 * <li>修改雇员表中的雇员信息</li>
	 * <li>修改部门表中的人数信息</li>
	 * <li>在work表中进行记录</li>
	 * @param work
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Work work) throws Exception ;
}
