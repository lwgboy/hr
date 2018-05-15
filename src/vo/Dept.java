package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Integer did ;
	private String dname ;
	private Integer current ;
	
	public Dept() {
		super();
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}
	
}
