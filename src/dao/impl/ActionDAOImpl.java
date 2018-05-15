package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Action;
import vo.Groups;
import dao.IActionDAO;

public class ActionDAOImpl extends AbstractDAOImpl implements IActionDAO {

	public ActionDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Action vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Action vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Action findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAllByGid(Integer gid) throws SQLException {
		List<Action> all = new ArrayList<Action>() ;
		String sql = "SELECT actid,gid,title,url FROM action WHERE gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, gid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Action action = new Action() ;
			action.setActid(rs.getInt(1)) ;
			Groups groups = new Groups() ;
			groups.setGid(rs.getInt(2)) ;
			action.setGroups(groups) ;
			action.setTitle(rs.getString(3)) ;
			action.setUrl(rs.getString(4)) ;
			all.add(action) ;
		}
		return all;
	}

}
