package com.leonia.wellness.dto;

public class AddStockValues {
	private String stockName;
	
	private Integer stockGroupMasterId;
	
	private Integer stockSubGroupId;
	
	private Integer reOrderlevel;
	
	private Integer warningLevel;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getStockGroupMasterId() {
		return stockGroupMasterId;
	}

	public void setStockGroupMasterId(Integer stockGroupMasterId) {
		this.stockGroupMasterId = stockGroupMasterId;
	}

	public Integer getStockSubGroupId() {
		return stockSubGroupId;
	}

	public void setStockSubGroupId(Integer stockSubGroupId) {
		this.stockSubGroupId = stockSubGroupId;
	}

	public Integer getReOrderlevel() {
		return reOrderlevel;
	}

	public void setReOrderlevel(Integer reOrderlevel) {
		this.reOrderlevel = reOrderlevel;
	}

	public Integer getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(Integer warningLevel) {
		this.warningLevel = warningLevel;
	}

	
}
