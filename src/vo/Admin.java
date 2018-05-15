package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Admin implements Serializable {
	private String aid ;
	private Role role ;
	private String password ;
	private Integer type ;
	private Date lastdate ;
	private Integer flag ;
	private List<Adminlogs> logs ;	// 一个管理员有多个日志
	private String ip ;	// 给Adminlogs用
	
	public Admin() {
		super();
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Adminlogs> getLogs() {
		return logs;
	}

	public void setLogs(List<Adminlogs> logs) {
		this.logs = logs;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
