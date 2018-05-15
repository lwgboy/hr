package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import util.dao.IDAO;
import vo.Employee;

public interface IEmployeeDAO extends IDAO<Integer, Employee> {
	/**
	 * 设置雇员状态变更操作，如果设置为离职，则日期设置为当前日期
	 * @param status
	 * @param ids
	 * @param outdate
	 * @return 更新成功返回true
	 * @throws SQLException
	 */
	public boolean doUpdateStatus(Integer status, Set<Integer> ids, 
			Date outdate) throws SQLException ;
	
	/**
	 * 根据雇员状态进行分页数据显示
	 * @param status 雇员状态（在职status=1,离职status=0）
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> findAllSplitByStatus(Integer status, 
			Integer currentPage, Integer lineSize, String column, 
			String keyWord) throws SQLException ;
	
	/**
	 * 根据雇员状态取得雇员信息量
	 * @param status 雇员状态
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @throws SQLException
	 */
	public Integer getCountByStatus(Integer status, String column, 
			String keyWord) throws SQLException ;
	
	/**
	 * 修改雇员部门和职位信息
	 * @param did
	 * @param jid
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateDidAndJidById(Integer did, Integer jid, 
			Integer id) throws SQLException ;
	
	/**
	 * 修改雇员工资等级和工资信息
	 * @param lid
	 * @param sal
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateLidAndSalById(Integer lid, Double sal, 
			Integer id) throws SQLException ;
	
	/**
	 * 更新字段dname和job
	 * @param dname
	 * @param job
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateDnameAndJobById(String dname, String job, 
			Integer id) throws SQLException ;
	
}
