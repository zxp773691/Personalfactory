package edu.yuhf.entity;

public class Customer {
	public int id;
	public String customerno;
	public String name;
	public String address;
	public String telephone;
	public String idcard;
	public String registertime;
	public String remark;
	public int state;
	public Customer(){}
	
	public Customer(int id, String customerno, String name, String address, String telephone, String idcard,
			String registertime, String remark, int state) {
		super();
		this.id = id;
		this.customerno = customerno;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.idcard = idcard;
		this.registertime = registertime;
		this.remark = remark;
		this.state = state;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerno=" + customerno + ", name=" + name + ", address=" + address
				+ ", telephone=" + telephone + ", idcard=" + idcard + ", registertime=" + registertime + ", remark="
				+ remark + "]";
	}
	
}
