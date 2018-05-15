package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Jobs;
import dao.IJobsDAO;

public class JobsDAOImpl extends AbstractDAOImpl implements IJobsDAO {

	public JobsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Jobs vo) throws SQLException {
		String sql = "INSERT INTO jobs(title,note) VALUES(?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle()) ;
		super.pstmt.setString(2, vo.getNote()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle(ids, "jobs", "jid") ;
	}

	@Override
	public boolean doUpdate(Jobs vo) throws SQLException {
		String sql = "UPDATE jobs SET title=?,note=? WHERE jid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle()) ;
		super.pstmt.setString(2, vo.getNote()) ;
		super.pstmt.setInt(3, vo.getJid()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public Jobs findById(Integer id) throws SQLException {
		Jobs jobs = null ;
		String sql = "SELECT jid,title,note FROM jobs WHERE jid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			jobs = new Jobs() ;
			jobs.setJid(rs.getInt(1)) ;
			jobs.setTitle(rs.getString(2)) ;
			jobs.setNote(rs.getString(3)) ;
		}
		return jobs ;
	}

	@Override
	public List<Jobs> findAll() throws SQLException {
		List<Jobs> all = new ArrayList<Jobs>() ;
		String sql = "SELECT jid,title,note FROM jobs" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Jobs jobs = new Jobs() ;
			jobs.setJid(rs.getInt(1)) ;
			jobs.setTitle(rs.getString(2)) ;
			jobs.setNote(rs.getString(3)) ;
			all.add(jobs) ;
		}
		return all ;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jobs> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jobs findByTitle(String title) throws SQLException {
		Jobs jobs = null ;
		String sql = "SELECT jid,title,note FROM jobs WHERE title=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, title) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			jobs = new Jobs() ;
			jobs.setJid(rs.getInt(1)) ;
			jobs.setTitle(rs.getString(2)) ;
			jobs.setNote(rs.getString(3)) ;
		}
		return jobs ;
	}

	@Override
	public Jobs findByTitle(String title, Integer jid) throws SQLException {
		Jobs jobs = null ;
		String sql = "SELECT jid,title,note FROM jobs WHERE title=? AND jid<>?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, title) ;
		super.pstmt.setInt(2, jid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			jobs = new Jobs() ;
			jobs.setJid(rs.getInt(1)) ;
			jobs.setTitle(rs.getString(2)) ;
			jobs.setNote(rs.getString(3)) ;
		}
		return jobs ;
	}

}
