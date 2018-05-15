package vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Role implements Serializable {
	private Integer rid ;
	private String title ;
	private String note ;
	private List<Admin> admins ;	// 一个角色有多个管理用拥有
	private List<Groups> groups ;	// 一个角色包含多个权限最

	public Role() {
		super();
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
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

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<Groups> getGroups() {
		return groups;
	}

	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	
	
}
