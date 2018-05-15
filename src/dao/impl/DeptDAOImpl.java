package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Dept;
import dao.IDeptDAO;

public class DeptDAOImpl extends AbstractDAOImpl implements IDeptDAO {

	public DeptDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "INSERT INTO dept(dname,current) VALUES(?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname()) ;
		super.pstmt.setInt(2, 0) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle(ids, "dept", "did") ;
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException {
		String sql = "UPDATE dept SET dname=? WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname()) ;
		super.pstmt.setInt(2, vo.getDid()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public Dept findById(Integer id) throws SQLException {
		Dept dept = null ;
		String sql = "SELECT did,dname,current FROM dept WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getInt(1)) ;
			dept.setDname(rs.getString(2)) ;
			dept.setCurrent(rs.getInt(3)) ;
		}
		return dept ;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		List<Dept> all = new ArrayList<Dept>() ;
		String sql = "SELECT did,dname,current FROM dept" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Dept dept = new Dept() ;
			dept = new Dept() ;
			dept.setDid(rs.getInt(1)) ;
			dept.setDname(rs.getString(2)) ;
			dept.setCurrent(rs.getInt(3)) ;
			all.add(dept) ;
		}
		return all ;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dept findByDname(String dname) throws SQLException {
		Dept dept = null ;
		String sql = "SELECT did,dname,current FROM dept WHERE dname=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, dname) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getInt(1)) ;
			dept.setDname(rs.getString(2)) ;
			dept.setCurrent(rs.getInt(3)) ;
		}
		return dept ;
	}

	@Override
	public Dept findByDname(String dname, Integer did) throws SQLException {
		Dept dept = null ;
		String sql = "SELECT did,dname,current FROM dept WHERE dname=? AND did<>?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, dname) ;
		super.pstmt.setInt(2, did) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getInt(1)) ;
			dept.setDname(rs.getString(2)) ;
			dept.setCurrent(rs.getInt(3)) ;
		}
		return dept ;
	}

	@Override
	public boolean doUpdateCurrentMinusById(Integer did) 
			throws SQLException {
		String sql = "UPDATE dept SET current=current-1 WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, did) ;
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doUpdateCurrentMinusByIdBatch(Set<Integer> dids)
			throws SQLException {
		if (dids.size()==0) {
			return false ;
		}
		String sql = "UPDATE dept SET current=current-1 WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Integer> iter = dids.iterator() ;
		while (iter.hasNext()) {
			super.pstmt.setInt(1, iter.next()) ;
			super.pstmt.addBatch() ;
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int i=0 ; i<result.length ; i++) {
			if (result[i]==0) {
				return false ;
			}
		}
		return true;
	}

	@Override
	public boolean doUpdateCurrentAddById(Integer did) throws SQLException {
		String sql = "UPDATE dept SET current=current+1 WHERE did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, did) ;
		return super.pstmt.executeUpdate() > 0 ;
	}
	
}
