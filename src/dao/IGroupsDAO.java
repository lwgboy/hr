package dao;

import java.sql.SQLException;
import java.util.List;

import util.dao.IDAO;
import vo.Groups;

public interface IGroupsDAO extends IDAO<Integer, Groups> {
	/**
	 * 根据一个角色查询出该角色拥有的所有权限组
	 * @param rid 角色id
	 * @return 所有权限组信息
	 * @throws SQLException SQL执行异常
	 */
	public List<Groups> findAllByRid(Integer rid) throws SQLException ;
}
