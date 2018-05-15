package service.front.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import service.front.IAdminServiceFront;
import vo.Admin;
import vo.Adminlogs;
import vo.Groups;
import dbc.DatabaseConnection;
import factory.DAOFactory;

public class AdminServiceFrontImpl implements IAdminServiceFront{
	DatabaseConnection dbc = new DatabaseConnection() ;

	@Override
	public boolean login(Admin admin) throws Exception {
		try {
			// 1、进行用户的登录验证操作
			boolean flag = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin) ;
			if (flag) {
				Date lastdate = new Date() ;
				// 2、保存登录日志信息
				Adminlogs log = new Adminlogs() ;
				log.setAdmin(admin) ;
				log.setLogindate(lastdate) ;
				log.setIp(admin.getIp()) ;
				if (flag = DAOFactory.getIAdminlogsDAOInstance(this.dbc.getConnection()).doCreate(log)) {
					// 3、修改最后一次登录时间
					if (flag = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(admin.getAid(), lastdate)) {
						// 4、判断当前管理员的类型
						if (admin.getType() == 1) {		// 后台管理员
							// 5、取出管理员所属角色所对应的权限组
							List<Groups> allGroups = DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAllByRid(admin.getRole().getRid()) ;
							// 6、根据权限组查询所有的权限信息
							Iterator<Groups> iter = allGroups.iterator() ;
							while (iter.hasNext()) {
								Groups groups = iter.next() ;
								groups.setAction(DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).findAllByGid(groups.getGid())) ;
							}
							// 7、将所有权限组的 信息保存在角色中
							admin.getRole().setGroups(allGroups) ;
						}
					}
				}
			}
			return flag ;
		} catch (SQLException e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}

	@Override
	public boolean changePassword(String aid, String oldPassword, 
			String newPassword) throws Exception {
		try {
			Admin admin = new Admin() ;
			admin.setAid(aid) ;
			admin.setPassword(oldPassword) ;
			if (DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin)) {
				return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdatePassword(aid, newPassword) ;
			} else {
				return false ;
			}
		} catch (SQLException e) {
			throw e ;
		} finally {
			this.dbc.close() ;
		}
	}
}
