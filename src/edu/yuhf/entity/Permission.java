package edu.yuhf.entity;

public class Permission {
	public int id;
	public String name;
	public String url;
	public String remark;
	public boolean checked;
	public int type;
	public Permission(){}
	
	public Permission(int id, String name, String url, String remark, int type) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.remark = remark;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", url=" + url + ", remark=" + remark + ", checked="
				+ checked + ", type=" + type + "]";
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
