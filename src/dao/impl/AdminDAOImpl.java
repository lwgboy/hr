package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Admin;
import vo.Role;
import dao.IAdminDAO;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {

	public AdminDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Admin vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findLogin(Admin admin) throws SQLException {
		String sql = "SELECT rid,type,lastdate,flag FROM admin WHERE aid=? AND password=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1,admin.getAid()) ;
		super.pstmt.setString(2,admin.getPassword()) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Role role = new Role() ;
			role.setRid(rs.getInt(1)) ;
			admin.setRole(role) ;
			admin.setType(rs.getInt(2)) ;
			admin.setLastdate(new Date(rs.getDate(3).getTime())) ;
			admin.setFlag(rs.getInt(4)) ;
			return true ;
		} else {
			return false ;
		}
	}

	@Override
	public boolean doUpdateLastdate(String aid, Date lastdate) throws SQLException {
		String sql = "UPDATE admin SET lastdate=? WHERE aid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setTimestamp(1, new java.sql.Timestamp(lastdate.getTime())) ;
		super.pstmt.setString(2, aid) ;
		return pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdatePassword(String aid, String password)
			throws SQLException {
		String sql = "UPDATE admin SET password=? WHERE aid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, password) ;
		super.pstmt.setString(2, aid) ;
		return pstmt.executeUpdate() > 0 ;
	}
}
