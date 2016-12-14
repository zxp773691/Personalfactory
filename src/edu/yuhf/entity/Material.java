package edu.yuhf.entity;

public class Material {
	private int id;
	private String name;
	private double rate;
	private int type;
	private int state;
	private double price;
	
	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", rate=" + rate + ", type=" + type + ", state=" + state
				+ ", price=" + price + "]";
	}
	public  Material(){}
	public Material(int id, String name, double rate, int type, int state, double price) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.type = type;
		this.state = state;
		this.price = price;
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
