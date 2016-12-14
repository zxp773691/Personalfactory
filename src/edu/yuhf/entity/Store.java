package edu.yuhf.entity;

public class Store {
	private int id;
	private double weight;
	private int materialid;
	public Store(){}
	public Store(int id, double weight, int materialid) {
		super();
		this.id = id;
		this.weight = weight;
		this.materialid = materialid;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", weight=" + weight + ", materialid=" + materialid + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getMaterialid() {
		return materialid;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	
}
