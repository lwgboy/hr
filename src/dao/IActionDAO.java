package dao;

import java.sql.SQLException;
import java.util.List;

import util.dao.IDAO;
import vo.Action;

public interface IActionDAO extends IDAO<Integer,Action> {
	/**
	 * 根据权限组编号查询权限组对应的权限
	 * @param gid 权限组编号
	 * @return 权限组对应的权限
	 * @throws SQLException SQL执行异常
	 */
	public List<Action> findAllByGid(Integer gid) throws SQLException ;
}
