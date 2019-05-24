package com.cafe24.mysite.vo;

public class Paging {
	private double total;
	private int perPage = 3;
	private int maxPage;
	private int block;
	private int currentPage = 1;
	@Override
	public String toString() {
		return "Paging [total=" + total + ", perPage=" + perPage + ", maxPage=" + maxPage + ", block=" + block
				+ ", currentPage=" + currentPage + "]";
	}
	public double getTotal() {
		return total;
	}
	public int getPerPage() {
		return perPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public int getBlock() {
		return block;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
