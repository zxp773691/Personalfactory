package edu.yuhf.entity;

public class Voucher {
	public int id;
	public String voucherno;
	public int type;
	public String occurtime;
	public double weight;
	public double totalprice;
	public int materialid;
	public int customerid;
	public int bususerid;
	public int storeuserid;
	public Voucher(){}
	public Voucher(int id, String voucherno, int type, String occurtime, double weight, double totalprice,
			int materialid, int customerid, int bususerid, int storeuserid) {
		super();
		this.id = id;
		this.voucherno = voucherno;
		this.type = type;
		this.occurtime = occurtime;
		this.weight = weight;
		this.totalprice = totalprice;
		this.materialid = materialid;
		this.customerid = customerid;
		this.bususerid = bususerid;
		this.storeuserid = storeuserid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVoucherno() {
		return voucherno;
	}
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public int getMaterialid() {
		return materialid;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getBususerid() {
		return bususerid;
	}
	public void setBususerid(int bususerid) {
		this.bususerid = bususerid;
	}
	public int getStoreuserid() {
		return storeuserid;
	}
	public void setStoreuserid(int storeuserid) {
		this.storeuserid = storeuserid;
	}
	@Override
	public String toString() {
		return "Voucher [id=" + id + ", voucherno=" + voucherno + ", type=" + type + ", occurtime=" + occurtime
				+ ", weight=" + weight + ", totalprice=" + totalprice + ", materialid=" + materialid + ", customerid="
				+ customerid + ", bususerid=" + bususerid + ", storeuserid=" + storeuserid + "]";
	}
	
}
