package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import util.dao.AbstractDAOImpl;
import vo.Employee;
import dao.IEmployeeDAO;

public class EmployeeDAOImpl extends AbstractDAOImpl implements IEmployeeDAO {

	public EmployeeDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Employee vo) throws SQLException {
		String sql = "INSERT INTO employee(eid,aid,did,lid,jid,ename,"
					+ "birthday,sex,idcard,dname,job,school,profession,"
					+ "grad,photo,indate,outdate,status,sal,note,edu) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getEid()) ;
		super.pstmt.setString(2, vo.getAdmin().getAid()) ;
		super.pstmt.setInt(3, vo.getDept().getDid()) ;
		super.pstmt.setInt(4, vo.getLevel().getLid()) ;
		super.pstmt.setInt(5, vo.getJobs().getJid()) ;
		super.pstmt.setString(6, vo.getEname()) ;
		super.pstmt.setDate(7, new java.sql.Date(vo.getBirthday().getTime())) ;
		super.pstmt.setString(8, vo.getSex()) ;
		super.pstmt.setString(9, vo.getIdcard()) ;
		super.pstmt.setString(10, vo.getDname()) ;
		super.pstmt.setString(11, vo.getJob()) ;
		super.pstmt.setString(12, vo.getSchool()) ;
		super.pstmt.setString(13, vo.getProfession()) ;
		super.pstmt.setDate(14, new java.sql.Date(vo.getGrad().getTime())) ;
		super.pstmt.setString(15,vo.getPhoto()) ;
		super.pstmt.setTimestamp(16, new java.sql.Timestamp(vo.getIndate().getTime())) ;
		if (vo.getOutdate() == null) {
			super.pstmt.setNull(17, Types.NULL) ;
		} else {
			super.pstmt.setTimestamp(17, new java.sql.Timestamp(vo.getOutdate().getTime())) ;
		}
		super.pstmt.setInt(18, vo.getStatus()) ;
		super.pstmt.setDouble(19, vo.getSal()) ;
		super.pstmt.setString(20, vo.getNote()) ;
		super.pstmt.setString(21, vo.getEdu()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Employee vo) throws SQLException {
		String sql = "UPDATE employee SET did=?,lid=?,jid=?,ename=?,birthday=?,sex=?,"
					+ "idcard=?,dname=?,job=?,school=?,profession=?,grad=?,photo=?,"
					+ "status=?,sal=?,note=?,edu=? WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getDept().getDid()) ;
		super.pstmt.setInt(2, vo.getLevel().getLid()) ;
		super.pstmt.setInt(3, vo.getJobs().getJid()) ;
		super.pstmt.setString(4, vo.getEname()) ;
		super.pstmt.setDate(5, new java.sql.Date(vo.getBirthday().getTime())) ;
		super.pstmt.setString(6, vo.getSex()) ;
		super.pstmt.setString(7, vo.getIdcard()) ;
		super.pstmt.setString(8, vo.getDname()) ;
		super.pstmt.setString(9, vo.getJob()) ;
		super.pstmt.setString(10, vo.getSchool()) ;
		super.pstmt.setString(11, vo.getProfession()) ;
		super.pstmt.setDate(12, new java.sql.Date(vo.getGrad().getTime())) ;
		super.pstmt.setString(13,vo.getPhoto()) ;
		super.pstmt.setInt(14, vo.getStatus()) ;
		super.pstmt.setDouble(15, vo.getSal()) ;
		super.pstmt.setString(16, vo.getNote()) ;
		super.pstmt.setString(17, vo.getEdu()) ;
		super.pstmt.setInt(18, vo.getEid()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public Employee findById(Integer id) throws SQLException {
		Employee emp = null ;
		String sql = "SELECT eid,aid,did,lid,jid,ename,birthday,"
					+ "sex,idcard,dname,job,school,profession,"
					+ "grad,photo,indate,outdate,status,sal,note,edu "
					+ "FROM employee WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			emp = new Employee() ;
			emp.setEid(rs.getInt(1)) ;
			emp.getAdmin().setAid(rs.getString(2)) ;
			emp.getDept().setDid(rs.getInt(3)) ;
			emp.getLevel().setLid(rs.getInt(4)) ;
			emp.getJobs().setJid(rs.getInt(5)) ;
			emp.setEname(rs.getString(6)) ;
			emp.setBirthday(rs.getDate(7)) ;
			emp.setSex(rs.getString(8)) ;
			emp.setIdcard(rs.getString(9)) ;
			emp.setDname(rs.getString(10)) ;
			emp.setJob(rs.getString(11)) ;
			emp.setSchool(rs.getString(12)) ;
			emp.setProfession(rs.getString(13)) ;
			emp.setGrad(rs.getDate(14)) ;
			emp.setPhoto(rs.getString(15)) ;
			emp.setIndate(rs.getDate(16)) ;
			emp.setOutdate(rs.getDate(17)) ;
			emp.setStatus(rs.getInt(18)) ;
			emp.setSal(rs.getDouble(19)) ;
			emp.setNote(rs.getString(20)) ;
			emp.setEdu(rs.getString(21)) ;
		}
		return emp ;
	}

	@Override
	public List<Employee> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(String column, String keyWord) throws SQLException {
		return super.countHandle("employee", column, keyWord) ;
	}

	@Override
	public List<Employee> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Employee> all = new ArrayList<Employee>() ;
		String sql = "SELECT eid,aid,did,lid,jid,ename,birthday,sex,"
					+ "idcard,dname,job,school,profession,grad,photo,"
					+ "indate,outdate,status,sal,note,edu FROM employee "
					+ "WHERE " + column + " LIKE ? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%" + keyWord + "%") ;
		super.pstmt.setInt(2, (currentPage-1)*lineSize) ;
		super.pstmt.setInt(3, lineSize) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Employee emp = new Employee() ;
			emp.setEid(rs.getInt(1)) ;
			emp.getAdmin().setAid(rs.getString(2)) ;
			emp.getDept().setDid(rs.getInt(3)) ;
			emp.getLevel().setLid(rs.getInt(4)) ;
			emp.getJobs().setJid(rs.getInt(5)) ;
			emp.setEname(rs.getString(6)) ;
			emp.setBirthday(rs.getDate(7)) ;
			emp.setSex(rs.getString(8)) ;
			emp.setIdcard(rs.getString(9)) ;
			emp.setDname(rs.getString(10)) ;
			emp.setJob(rs.getString(11)) ;
			emp.setSchool(rs.getString(12)) ;
			emp.setProfession(rs.getString(13)) ;
			emp.setGrad(rs.getDate(14)) ;
			emp.setPhoto(rs.getString(15)) ;
			emp.setIndate(rs.getDate(16)) ;
			emp.setOutdate(rs.getDate(17)) ;
			emp.setStatus(rs.getInt(18)) ;
			emp.setSal(rs.getDouble(19)) ;
			emp.setNote(rs.getString(20)) ;
			emp.setEdu(rs.getString(21)) ;
			all.add(emp) ;
		}
		return all;
	}

	@Override
	public boolean doUpdateStatus(Integer status, Set<Integer> ids, 
			Date outdate) throws SQLException {
		if (ids.size() == 0) {
			return false ;
		}
		String sql = "UPDATE employee SET status=?,outdate=? WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Integer> iter = ids.iterator() ;
		while (iter.hasNext()) {
			super.pstmt.setInt(1, status) ;
			super.pstmt.setDate(2, new java.sql.Date(outdate.getTime())) ;
			super.pstmt.setInt(3, iter.next()) ;
			super.pstmt.addBatch() ;
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int i=0 ; i<result.length ; i++) {
			if (result[i] == 0) {
				return false ;
			}
		}
		return true ;
	}

	@Override
	public List<Employee> findAllSplitByStatus(Integer status,
			Integer currentPage, Integer lineSize, String column, 
			String keyWord)	throws SQLException {
		List<Employee> all = new ArrayList<Employee>() ;
		String sql = "SELECT eid,aid,did,lid,jid,ename,birthday,sex,"
					+ "idcard,dname,job,school,profession,grad,photo,"
					+ "indate,outdate,status,sal,note,edu FROM employee "
					+ "WHERE " + column + " LIKE ? AND status=? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%" + keyWord + "%") ;
		super.pstmt.setInt(2, status) ;
		super.pstmt.setInt(3, (currentPage-1)*lineSize) ;
		super.pstmt.setInt(4, lineSize) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Employee emp = new Employee() ;
			emp.setEid(rs.getInt(1)) ;
			emp.getAdmin().setAid(rs.getString(2)) ;
			emp.getDept().setDid(rs.getInt(3)) ;
			emp.getLevel().setLid(rs.getInt(4)) ;
			emp.getJobs().setJid(rs.getInt(5)) ;
			emp.setEname(rs.getString(6)) ;
			emp.setBirthday(rs.getDate(7)) ;
			emp.setSex(rs.getString(8)) ;
			emp.setIdcard(rs.getString(9)) ;
			emp.setDname(rs.getString(10)) ;
			emp.setJob(rs.getString(11)) ;
			emp.setSchool(rs.getString(12)) ;
			emp.setProfession(rs.getString(13)) ;
			emp.setGrad(rs.getDate(14)) ;
			emp.setPhoto(rs.getString(15)) ;
			emp.setIndate(rs.getDate(16)) ;
			emp.setOutdate(rs.getDate(17)) ;
			emp.setStatus(rs.getInt(18)) ;
			emp.setSal(rs.getDouble(19)) ;
			emp.setNote(rs.getString(20)) ;
			emp.setEdu(rs.getString(21)) ;
			all.add(emp) ;
		}
		return all;
	}

	@Override
	public Integer getCountByStatus(Integer status, String column, 
			String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM employee "
					+ "WHERE " + column + " LIKE ? AND status=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%" + keyWord + "%") ;
		super.pstmt.setInt(2, status) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		} else {
			return 0 ;
		}
	}

	@Override
	public boolean doUpdateDidAndJidById(Integer did, Integer jid, Integer id)
			throws SQLException {
		String sql = "UPDATE employee SET did=?,jid=? WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, did) ;
		super.pstmt.setInt(2, jid) ;
		super.pstmt.setInt(3, id) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdateLidAndSalById(Integer lid, Double sal, Integer id)
			throws SQLException {
		String sql = "UPDATE employee SET lid=?,sal=? WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, lid) ;
		super.pstmt.setDouble(2, sal) ;
		super.pstmt.setInt(3, id) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdateDnameAndJobById(String dname, String job, Integer id)
			throws SQLException {
		String sql = "UPDATE employee SET dname=?,job=? WHERE eid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, dname) ;
		super.pstmt.setString(2, job) ;
		super.pstmt.setInt(3, id) ;
		return super.pstmt.executeUpdate() > 0 ;
	}
}
