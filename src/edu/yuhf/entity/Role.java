package edu.yuhf.entity;

public class Role {
	public int id;
	public String name;
	public String remark;
	public boolean checked;
	public Role(){}
	public Role(int id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
