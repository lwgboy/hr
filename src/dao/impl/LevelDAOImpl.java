package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Level;
import dao.ILevelDAO;

public class LevelDAOImpl extends AbstractDAOImpl implements ILevelDAO {

	public LevelDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Level vo) throws SQLException {
		String sql = "INSERT INTO level(title,losal,hisal) VALUES(?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle()) ;
		super.pstmt.setDouble(2, vo.getLosal()) ;
		super.pstmt.setDouble(3, vo.getHisal()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle(ids, "level", "lid") ;
	}

	@Override
	public boolean doUpdate(Level vo) throws SQLException {
		String sql = "UPDATE level SET title=?,losal=?,hisal=? WHERE lid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getTitle()) ;
		super.pstmt.setDouble(2, vo.getLosal()) ;
		super.pstmt.setDouble(3, vo.getHisal()) ;
		super.pstmt.setInt(4, vo.getLid()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public Level findById(Integer id) throws SQLException {
		Level level = null ;
		String sql = "SELECT lid,title,losal,hisal FROM level WHERE lid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			level = new Level() ;
			level.setLid(rs.getInt(1)) ;
			level.setTitle(rs.getString(2)) ;
			level.setLosal(rs.getDouble(3)) ;
			level.setHisal(rs.getDouble(4)) ;
		}
		return level ;
	}

	@Override
	public List<Level> findAll() throws SQLException {
		List<Level> all = new ArrayList<Level>() ;
		String sql = "SELECT lid,title,losal,hisal FROM level" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Level level = new Level() ;
			level.setLid(rs.getInt(1)) ;
			level.setTitle(rs.getString(2)) ;
			level.setLosal(rs.getDouble(3)) ;
			level.setHisal(rs.getDouble(4)) ;
			all.add(level) ;
		}
		return all ;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Level findByTitle(String title) throws SQLException {
		Level level = null ;
		String sql = "SELECT lid,title,losal,hisal FROM level WHERE title=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, title) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			level = new Level() ;
			level.setLid(rs.getInt(1)) ;
			level.setTitle(rs.getString(2)) ;
			level.setLosal(rs.getDouble(3)) ;
			level.setHisal(rs.getDouble(4)) ;
		}
		return level ;
	}

	@Override
	public Level findByTitle(String title, Integer lid) throws SQLException {
		Level level = null ;
		String sql = "SELECT lid,title,losal,hisal FROM level WHERE title=? AND lid<>?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, title) ;
		super.pstmt.setInt(2, lid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			level = new Level() ;
			level.setLid(rs.getInt(1)) ;
			level.setTitle(rs.getString(2)) ;
			level.setLosal(rs.getDouble(3)) ;
			level.setHisal(rs.getDouble(4)) ;
		}
		return level ;
	}

}
