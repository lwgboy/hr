package service.front;

import java.util.Map;
import java.util.Set;

import vo.Employee;

public interface IEmployeeServiceFront {
	/**
	 * 执行雇员增加前的数据准备操作，查询全部的部门、职位、级别
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> insertPre() throws Exception ;
	
	/**
	 * 雇员增加操作，需要执行如下功能：<br>
	 * <li>1、根据雇员部门编号查询出部门名称</li>
	 * <li>2、根据雇员职位编号查询出职位名称</li>
	 * <li>3、根据雇员工资等级查询出等级范围以确认雇员工资是否合理</li>
	 * <li>4、判断雇员状态是在职还是离职，如果是离职则需要设置离职时间</li>
	 * <li>5、判断雇员编号是否存在</li>
	 * <li>6、进行雇员数据的保存</li>
	 * <li>7、对应部门人数加1</li>
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Employee emp) throws Exception ;
	
	/**
	 * 执行雇员更新前的数据准备操作，查询雇员信息、全部的部门、职位、级别
	 * @param eid 要更新的雇员编号
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> updatePre(int eid) throws Exception ;
	
	/**
	 * 雇员增加操作，需要执行如下功能：<br>
	 * <li>1、根据雇员部门编号查询出部门名称</li>
	 * <li>2、根据雇员职位编号查询出职位名称</li>
	 * <li>3、根据雇员工资等级查询出等级范围以确认雇员工资是否合理</li>
	 * <li>4、判断雇员状态是在职还是离职，如果是离职则需要设置离职时间</li>
	 * <li>5、判断雇员数据的更新</li>
	 * <li>6、对应的两个部门人数改变</li>
	 * <li>7、在work表中进行部门和职位变更的记录</li>
	 * <li>8、工资变更记录</li>
	 * @param emp
	 * @param oldEmp 修改前的雇员信息，其中oldEmp.getAdmin()返回对其进行修改的管理员id
	 * @return
	 * @throws Exception
	 */
	public boolean update(Employee emp, Employee oldEmp) throws Exception ;
	
	/**
	 * 批量设置雇员状态为离职，离职日期设置为当前日期
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean updateOut(Set<Integer> ids) throws Exception ;
	
	/**
	 * 分页列出所有雇员信息，同时在页面上可以修改
	 * @return 
	 * @throws Exception
	 */
	public Map<String,Object> listSplit(int currentPage,
			int lineSize,String column,String keyWord) throws Exception ;
	
	/**
	 * 根据雇员的在职与离职状态进行列出
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> listSplitByStatus(int status, int currentPage,
			int lineSize,String column,String keyWord) throws Exception ;
}
