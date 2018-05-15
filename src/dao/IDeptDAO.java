package dao;

import java.sql.SQLException;
import java.util.Set;

import util.dao.IDAO;
import vo.Dept;

public interface IDeptDAO extends IDAO<Integer, Dept> {
	/**
	 * 根据部门名称查询部门，用于增加操作时，保证部门名称不重复
	 * @param dname 根据的部门名称
	 * @return 查询到的部门名称，如果没有该部门返回null
	 * @throws SQLException SQL执行异常
	 */
	public Dept findByDname(String dname) throws SQLException ;
	
	/**
	 * 根据部门名称查询部门，同时排除掉部门编号等于指定编号的部门，用于在更新部门的时候保证名称不重复
	 * @param dname 根据的部门名称
	 * @param did 要排除的部门编号
	 * @return 查询到的部门名称，如果没有该部门返回null
	 * @throws SQLException SQL执行异常
	 */
	public Dept findByDname(String dname, Integer did) throws SQLException ;
	
	/**
	 * 根据部门Id将字段current的值减小1，用于雇员离职操作、雇员转部门操作
	 * @param did 部门Id
	 * @param minusCount
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateCurrentMinusById(Integer did) throws SQLException ;
	
	/**
	 * 根据部门Id将字段current的值减小1，批量操作，用于雇员批量离职操作
	 * @param dids
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateCurrentMinusByIdBatch(Set<Integer> dids) throws SQLException ;
	
	/**
	 * 根据部门Id将字段current的值减小1，用于雇员增加、雇员转部门操作
	 * @param did
	 * @return
	 * @throws SQLExcption
	 */
	public boolean doUpdateCurrentAddById(Integer did) throws SQLException ;
}
