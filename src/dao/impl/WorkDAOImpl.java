package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Work;
import dao.IWorkDAO;

public class WorkDAOImpl extends AbstractDAOImpl implements IWorkDAO {

	public WorkDAOImpl(Connection conn) {
		super(conn) ;
	}

	@Override
	public boolean doCreate(Work vo) throws SQLException {
		String sql = "INSERT INTO work(eid,aid,olddid,newdid,oldjid,newjid,"
					+ "cdate,olddname,oldjob,newdname,newjob,reason,note) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getEmployee().getEid()) ;
		super.pstmt.setString(2, vo.getAdmin().getAid()) ;
		super.pstmt.setInt(3, vo.getOlddid()) ;
		super.pstmt.setInt(4, vo.getNewdid()) ;
		super.pstmt.setInt(5, vo.getOldjid()) ;
		super.pstmt.setInt(6, vo.getNewjid()) ;
		super.pstmt.setTimestamp(7, new java.sql.Timestamp(vo.getCdate().getTime())) ;
		super.pstmt.setString(8, vo.getOlddname()) ;
		super.pstmt.setString(9, vo.getOldjob()) ;
		super.pstmt.setString(10, vo.getNewdname()) ;
		super.pstmt.setString(11, vo.getNewjob()) ;
		super.pstmt.setString(12, vo.getReason()) ;
		super.pstmt.setString(13, vo.getNote()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Work vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Work findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Work> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		return super.countHandle("work", column, keyWord) ;
	}

	@Override
	public List<Work> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Work> all = new ArrayList<Work>() ;
		String sql = "SELECT wid,eid,aid,olddid,newdid,oldjid,newjid,cdate,"
					+ "olddname,oldjob,newdname,newjob,reason,note FROM work "
					+ "WHERE " + column + " LIKE ? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1,"%" + keyWord + "%") ;
		super.pstmt.setInt(2, (currentPage-1)*lineSize) ;
		super.pstmt.setInt(3, lineSize) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Work work = new Work() ;
			work.setWid(rs.getInt(1)) ;
			work.getEmployee().setEid(rs.getInt(2)) ;
			work.getAdmin().setAid(rs.getString(3)) ;
			work.setOlddid(rs.getInt(4)) ;
			work.setNewdid(rs.getInt(5)) ;
			work.setOldjid(rs.getInt(6)) ;
			work.setNewjid(rs.getInt(7)) ;
			work.setCdate(rs.getDate(8)) ;
			work.setOlddname(rs.getString(9)) ;
			work.setOldjob(rs.getString(10)) ;
			work.setNewdname(rs.getString(11)) ;
			work.setNewjob(rs.getString(12)) ;
			work.setReason(rs.getString(13)) ;
			work.setNote(rs.getString(14)) ;
			all.add(work) ;
		}
		return all ;
	}

}
