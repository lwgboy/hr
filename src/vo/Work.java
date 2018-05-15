package vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Work implements Serializable {
	private Integer wid ;
	private Employee employee = new Employee() ; 
	private Admin admin = new Admin() ;
	private Integer olddid ;
	private Integer newdid ;
	private Integer oldjid ;
	private Integer newjid ;
	private Date cdate ;
	private String olddname ;
	private String oldjob ;
	private String newdname ;
	private String newjob ;
	private String reason ;
	private String note ;
	
	public Work() {
		super() ;
	}

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getOlddid() {
		return olddid;
	}

	public void setOlddid(Integer olddid) {
		this.olddid = olddid;
	}

	public Integer getNewdid() {
		return newdid;
	}

	public void setNewdid(Integer newdid) {
		this.newdid = newdid;
	}
	
	public Integer getOldjid() {
		return oldjid;
	}

	public void setOldjid(Integer oldjid) {
		this.oldjid = oldjid;
	}

	public Integer getNewjid() {
		return newjid;
	}

	public void setNewjid(Integer newjid) {
		this.newjid = newjid;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getOlddname() {
		return olddname;
	}

	public void setOlddname(String olddname) {
		this.olddname = olddname;
	}

	public String getOldjob() {
		return oldjob;
	}

	public void setOldjob(String oldjob) {
		this.oldjob = oldjob;
	}

	public String getNewdname() {
		return newdname;
	}

	public void setNewdname(String newdname) {
		this.newdname = newdname;
	}

	public String getNewjob() {
		return newjob;
	}

	public void setNewjob(String newjob) {
		this.newjob = newjob;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
