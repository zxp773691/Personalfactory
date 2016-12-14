package edu.yuhf.entity;

public class RecordInfo {
	public int id;
	public String processtime;
	public String mlname;
	public String frname;
	public double mlweight;
	public double frweight;
	public RecordInfo(){}
	public RecordInfo(int id, String processtime, String mlname, String frname, double mlweight, double frweight) {
		super();
		this.id = id;
		this.processtime = processtime;
		this.mlname = mlname;
		this.frname = frname;
		this.mlweight = mlweight;
		this.frweight = frweight;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProcesstime() {
		return processtime;
	}
	public void setProcesstime(String processtime) {
		this.processtime = processtime;
	}
	public String getMlname() {
		return mlname;
	}
	public void setMlname(String mlname) {
		this.mlname = mlname;
	}
	public String getFrname() {
		return frname;
	}
	public void setFrname(String frname) {
		this.frname = frname;
	}
	public double getMlweight() {
		return mlweight;
	}
	public void setMlweight(double mlweight) {
		this.mlweight = mlweight;
	}
	public double getFrweight() {
		return frweight;
	}
	public void setFrweight(double frweight) {
		this.frweight = frweight;
	}
	@Override
	public String toString() {
		return "RecordInfo [id=" + id + ", processtime=" + processtime + ", mlname=" + mlname + ", frname=" + frname
				+ ", mlweight=" + mlweight + ", frweight=" + frweight + "]";
	}
	
	
}
