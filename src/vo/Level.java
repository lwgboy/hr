package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Level implements Serializable {
	private Integer lid ;
	private String title ;
	private double losal ;
	private double hisal ;
	
	public Level() {
		super();
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLosal() {
		return losal;
	}

	public void setLosal(double losal) {
		this.losal = losal;
	}

	public double getHisal() {
		return hisal;
	}

	public void setHisal(double hisal) {
		this.hisal = hisal;
	}
	
}
