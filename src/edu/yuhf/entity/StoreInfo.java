package edu.yuhf.entity;

public class StoreInfo {
	private int id;
	private String name;
	private double weight;
	private int type;
	public StoreInfo(){}
	public StoreInfo(int id, String name, double weight, int type) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.type = type;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "StoreInfo [id=" + id + ", name=" + name + ", weight=" + weight + ", type=" + type + "]";
	}
	
}
