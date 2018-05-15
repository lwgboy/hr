package dao;

import java.sql.SQLException;

import util.dao.IDAO;
import vo.Level;

public interface ILevelDAO extends IDAO<Integer,Level> {
	/**
	 * 根据级别名称查询级别，用于增加操作时，保证级别名称不重复
	 * @param title 根据的级别名称
	 * @return 查询到的级别名称，如果没有该级别返回null
	 * @throws SQLException SQL执行异常
	 */
	public Level findByTitle(String title) throws SQLException ;
	
	/**
	 * 根据级别名称查询级别，同时排除掉级别编号等于指定编号的级别，用于在更新级别的时候保证名称不重复
	 * @param title 根据的级别名称
	 * @param lid 要排除的级别编号
	 * @return 查询到的级别名称，如果没有该级别返回null
	 * @throws SQLException SQL执行异常
	 */
	public Level findByTitle(String title, Integer lid) throws SQLException ;
}
