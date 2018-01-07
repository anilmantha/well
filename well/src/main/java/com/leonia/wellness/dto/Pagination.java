package com.leonia.wellness.dto;

public class Pagination {
	private long totalRecs;
	private int noOfPages;
	private int currentPageNum;
	public long getTotalRecs() {
		return totalRecs;
	}
	public void setTotalRecs(long totalRecs) {
		this.totalRecs = totalRecs;
	}
	public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	private Pagination(){}
	private static final Pagination pagination=new Pagination();
	public static Pagination getInstance()
	{
		return pagination;
	}
}
