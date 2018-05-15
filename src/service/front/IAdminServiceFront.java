package service.front;

import vo.Admin;

public interface IAdminServiceFront {
	/**
	 * 管理员登录操作
	 * @param admin 包含属于的账号和密码
	 * @return 账号和密码是否正确
	 */
	public boolean login(Admin admin) throws Exception ;
	
	/**
	 * 修改密码
	 * @param aid 管理员id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 密码修改成功返回true，原始密码不正确或修改失败返回false
	 */
	public boolean changePassword(String aid, String oldPassword, 
			String newPassword) throws Exception ;
}
