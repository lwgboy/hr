package dao;

import java.sql.SQLException;

import util.dao.IDAO;
import vo.Jobs;

public interface IJobsDAO extends IDAO<Integer,Jobs> {
	/**
	 * 根据职位名称查询职位，用于增加操作时，保证职位名称不重复
	 * @param title 根据的职位名称
	 * @return 查询到的职位名称，如果没有该职位返回null
	 * @throws SQLException SQL执行异常
	 */
	public Jobs findByTitle(String title) throws SQLException ;
	
	/**
	 * 根据职位名称查询职位，同时排除掉职位编号等于指定编号的职位，用于在更新职位的时候保证名称不重复
	 * @param title 根据的职位名称
	 * @param jid 要排除的职位编号
	 * @return 查询到的职位名称，如果没有该职位返回null
	 * @throws SQLException SQL执行异常
	 */
	public Jobs findByTitle(String title, Integer jid) throws SQLException ;
}
