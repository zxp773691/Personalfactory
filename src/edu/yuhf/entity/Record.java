package edu.yuhf.entity;

public class Record {
	public int id;
	public String processtime;
	public double mlweight;
	public double frweight;
	public int mlid;
	public int frid;
	public Record(){}
	public Record(int id, String processtime, double mlweight, double frweight, int mlid, int frid) {
		super();
		this.id = id;
		this.processtime = processtime;
		this.mlweight = mlweight;
		this.frweight = frweight;
		this.mlid = mlid;
		this.frid = frid;
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
	public int getMlid() {
		return mlid;
	}
	public void setMlid(int mlid) {
		this.mlid = mlid;
	}
	public int getFrid() {
		return frid;
	}
	public void setFrid(int frid) {
		this.frid = frid;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", processtime=" + processtime + ", mlweight=" + mlweight + ", frweight=" + frweight
				+ ", mlid=" + mlid + ", frid=" + frid + "]";
	}
	
}
