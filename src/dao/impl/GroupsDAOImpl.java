package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Groups;
import dao.IGroupsDAO;

public class GroupsDAOImpl extends AbstractDAOImpl implements IGroupsDAO {

	public GroupsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Groups vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Groups vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groups findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllByRid(Integer rid) throws SQLException {
		List<Groups> all = new ArrayList<Groups>() ;
		String sql = "	SELECT gid,title,note "
					+ " FROM groups "
					+ " WHERE gid IN (SELECT gid FROM role_groups WHERE rid=?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1,rid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next())  {
			Groups groups = new Groups() ;
			groups.setGid(rs.getInt(1)) ;
			groups.setTitle(rs.getString(2)) ;
			groups.setNote(rs.getString(3)) ;
			all.add(groups) ;
		}
		return all;
	}

}
