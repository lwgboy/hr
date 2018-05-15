package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Salary;
import dao.ISalaryDAO;

public class SalaryDAOImpl extends AbstractDAOImpl implements ISalaryDAO {

	public SalaryDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Salary vo) throws SQLException {
		String sql = "INSERT INTO salary(oldlid,newlid,eid,aid,cdate,"
					+ "oldsal,newsal,reason,note) VALUES(?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getOldlid()) ;
		super.pstmt.setInt(2, vo.getNewlid()) ;
		super.pstmt.setInt(3, vo.getEmployee().getEid()) ;
		super.pstmt.setString(4, vo.getAdmin().getAid()) ;
		super.pstmt.setTimestamp(5, new java.sql.Timestamp(vo.getCdate().getTime())) ;
		super.pstmt.setDouble(6, vo.getOldsal()) ;
		super.pstmt.setDouble(7, vo.getNewsal()) ;
		super.pstmt.setString(8, vo.getReason()) ;
		super.pstmt.setString(9, vo.getNote()) ;
		return super.pstmt.executeUpdate() > 0 ;
		
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Salary vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Salary findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salary> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		return super.countHandle("salary", column, keyWord) ;
	}

	@Override
	public List<Salary> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Salary> all = new ArrayList<Salary>() ;
		String sql = "SELECT sid,oldlid,newlid,eid,aid,cdate,oldsal,newsal,"
					+ "reason,note FROM salary WHERE " + column + " LIKE ? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%" + keyWord + "%") ;
		super.pstmt.setInt(2, (currentPage-1)*lineSize) ;
		super.pstmt.setInt(3, lineSize) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Salary salary = new Salary() ;
			salary.setSid(rs.getInt(1)) ;
			salary.setOldlid(rs.getInt(2)) ;
			salary.setNewlid(rs.getInt(3)) ;
			salary.getEmployee().setEid(rs.getInt(4)) ;
			salary.getAdmin().setAid(rs.getString(5)) ;
			salary.setCdate(rs.getDate(6)) ;
			salary.setOldsal(rs.getDouble(7)) ;
			salary.setNewsal(rs.getDouble(8)) ;
			salary.setReason(rs.getString(9)) ;
			salary.setNote(rs.getString(10)) ;
			all.add(salary) ;
		}
		return all ;
	}

}
