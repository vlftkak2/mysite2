package kr.ac.sungkyul.mysite.vo;

public class MapVo {
	
	private Long no;
	private String name;
	private double localx;
	private double localy;
	private String percent;
	private Long regionno;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLocalx() {
		return localx;
	}
	public void setLocalx(double localx) {
		this.localx = localx;
	}
	public double getLocaly() {
		return localy;
	}
	public void setLocaly(double localy) {
		this.localy = localy;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public Long getRegionno() {
		return regionno;
	}
	public void setRegionno(Long regionno) {
		this.regionno = regionno;
	}
	@Override
	public String toString() {
		return "MapVo [no=" + no + ", name=" + name + ", localx=" + localx + ", localy=" + localy + ", percent="
				+ percent + ", regionno=" + regionno + "]";
	}
	
	


	
	
	}
	
	
	
	


