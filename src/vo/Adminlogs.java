package vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Adminlogs implements Serializable {
	private Integer alid ;
	private Admin admin ;
	private Date logindate ;
	private String ip ;
	
	public Adminlogs() {
		super();
	}

	public Integer getAlid() {
		return alid;
	}

	public void setAlid(Integer alid) {
		this.alid = alid;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
