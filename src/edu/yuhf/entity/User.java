package edu.yuhf.entity;

public class User {
	private int id;
	private String userno;
	private String username;
	private String password;
	private String realname;
	private String telephone;
	private String idcard;
	private String registertime;
	private int state;
	public User(){}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userno=" + userno + ", username=" + username + ", password=" + password
				+ ", realname=" + realname + ", telephone=" + telephone + ", idcard=" + idcard + ", registertime="
				+ registertime + ", state=" + state + "]";
	}
	public User(String userno, String username, String password, String realname, String telephone,
			String idcard, String registertime, int state) {
		super();
		this.userno = userno;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.telephone = telephone;
		this.idcard = idcard;
		this.registertime = registertime;
		this.state = state;
	}
	public User(int id, String userno, String username, String password, String realname, String telephone,
			String idcard, String registertime, int state) {
		super();
		this.id = id;
		this.userno = userno;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.telephone = telephone;
		this.idcard = idcard;
		this.registertime = registertime;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}

