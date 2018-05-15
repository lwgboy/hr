package vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Groups implements Serializable {
	private Integer gid ;
	private String title ;
	private String note ;
	private List<Role> roles ;		// 一个权限组被多个角色所拥有
	private List<Action> action ;	// 一个权限组拥有多个权限
	
	public Groups() {
		super();
	}

	public Integer getGid() {
		return gid;
	}
	
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Action> getAction() {
		return action;
	}

	public void setAction(List<Action> action) {
		this.action = action;
	}
	
}
