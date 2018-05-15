package vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Salary implements Serializable {
	private Integer sid ;
	private Integer oldlid ;
	private Integer newlid ;
	private Employee employee = new Employee() ;
	private Admin admin = new Admin() ;
	private Date cdate ;
	private Double oldsal ;
	private Double newsal ;
	private String reason ;
	private String note ;
	
	public Salary() {
		super();
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getOldlid() {
		return oldlid;
	}

	public void setOldlid(Integer oldlid) {
		this.oldlid = oldlid;
	}

	public Integer getNewlid() {
		return newlid;
	}

	public void setNewlid(Integer newlid) {
		this.newlid = newlid;
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

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Double getOldsal() {
		return oldsal;
	}

	public void setOldsal(Double oldsal) {
		this.oldsal = oldsal;
	}

	public Double getNewsal() {
		return newsal;
	}

	public void setNewsal(Double newsal) {
		this.newsal = newsal;
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
