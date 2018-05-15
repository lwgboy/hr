package dao;

import java.sql.SQLException;
import java.util.Date;

import util.dao.IDAO;
import vo.Admin;

public interface IAdminDAO extends IDAO<String,Admin> {
	/**
	 * 用于验证登录操作，同时在形参中返回一些会使用到的数据
	 * @param admin 包含传入的账号和密码，需要向其中填入其他的一些内容
	 * @return 登录成功返回true，否则返回false
	 * @throws SQLException SQL执行异常
	 */
	public boolean findLogin(Admin admin) throws SQLException ;
	
	/**
	 * 修改最后一次登录时间
	 * @param aid 要修改的管理员id
	 * @param lastdate 修改到的登录日期
	 * @return sql执行成功返回true
	 * @throws SQLException SQL执行异常
	 */
	public boolean doUpdateLastdate(String aid, Date lastdate) throws SQLException ;

	/**
	 * 修改管理员登录密码
	 * @param aid 要修改的管理员id
	 * @param password 修改到的密码
	 * @return sql执行成功返回true
	 * @throws SQLException SQL执行异常
	 */
	public boolean doUpdatePassword(String aid, String password) throws SQLException ;
}
