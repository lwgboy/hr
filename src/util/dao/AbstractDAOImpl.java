package util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AbstractDAOImpl {
	protected Connection conn ;
	protected PreparedStatement pstmt ;
	
	public AbstractDAOImpl(Connection conn) {
		this.conn = conn ;
	}
	
	/**
	 * 抽象化DAO层中的批量删除操作
	 * @param ids 要删除的记录ID
	 * @param tabName 被操作的表名
	 * @param idName 被操作表的主键名（ID）
	 * @return 所有数据删除成功返回true，否则返回false
	 * @throws SQLException SQL执行异常
	 */
	public boolean removeHandle(Set<?> ids, String tabName, String idName) throws SQLException {
		if (ids.size()==0) {
			return false ;
		}
		StringBuffer sql = new StringBuffer() ;
		sql.append("DELETE FROM ").append(tabName).append(" WHERE ").append(idName).append(" IN (") ;
		Iterator<?> iter = ids.iterator() ;
		while (iter.hasNext()) {
			sql.append(iter.next().toString()).append(",") ;
		}
		sql.delete(sql.length()-1, sql.length()).append(")") ;
		this.pstmt = this.conn.prepareStatement(sql.toString()) ;
		return this.pstmt.executeUpdate()==ids.size() ;
	}
		
	/**
	 * 抽象化DAO层分页操作中的查询所有符合要求的记录数的操作
	 * @param tabName 被操作的表名
	 * @param column 列名
	 * @param keyWord 关键字
	 * @return 符合要求的记录数
	 * @throws SQLException SQL执行异常
	 */
	public Integer countHandle(String tabName, String column, String keyWord) throws SQLException {
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT COUNT(*) FROM ").append(tabName).append(" WHERE ")
			.append(column).append(" LIKE ?") ;
		this.pstmt = this.conn.prepareStatement(sql.toString()) ;
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0 ;
	}
	
	
	public Set<String> photoHandle(String table, String column, 
			String photoColumn, Set<?> ids) throws Exception {
		Set<String> all = new HashSet<String>() ;
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT ").append(photoColumn).append(" FROM ")
			.append(table).append(" WHERE ").append(column)
			.append(" IN (") ;
		Iterator<?> iter = ids.iterator() ;
		while (iter.hasNext()) {
			sql.append(iter.next()).append(",") ;
		}
		sql.delete(sql.length()-1, sql.length()).append(")") ;
		sql.append(" AND ").append(photoColumn).append(" <>'nophoto.jpg'") ;
		PreparedStatement pstmt = this.conn.prepareStatement(sql.toString()) ;
		ResultSet rs = pstmt.executeQuery() ;
		while (rs.next()) {
			all.add(rs.getString(1)) ;
		}
		return all ;
	}

}
