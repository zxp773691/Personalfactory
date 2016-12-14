package edu.yuhf.entity;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int currentPage;
	private int rowPage=3;
	private int totalRow;
	private int totalPage;
	private String keyword;
	private List<T> list=new ArrayList<T>();
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowPage() {
		return rowPage;
	}
	public void setRowPage(int rowPage) {
		this.rowPage = rowPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", rowPage=" + rowPage + ", totalRow=" + totalRow + ", totalPage="
				+ totalPage + ", keyword=" + keyword + ", list=" + list + "]";
	}
}
