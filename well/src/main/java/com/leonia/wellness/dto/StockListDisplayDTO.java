package com.leonia.wellness.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class StockListDisplayDTO {
	
	private Integer stockId;
	private Integer stockSubGroupId;
	private Integer stockGroupId;
	private Integer stockReceiptId;
	private Integer manufacturerId;
	private Integer supplierId;
	private Integer dropdownDetailsId;
	private String stockName;
	private int reorderlevel;
	private int warninglevel;
	private BigDecimal available;
	private BigDecimal retailprice;
	private BigDecimal professionalprice;
	@Temporal(TemporalType.DATE)
	private Date manufacturedate;
	@Temporal(TemporalType.DATE)
	private Date expirydate;
	private String stockSubGroupName;
	private String stockGroupMasterName;
	private String dropDownDescription;
	private String manufacturerName;
	private String supplierName;
	private Integer receiptId;
	
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDropDownDescription() {
		return dropDownDescription;
	}
	public void setDropDownDescription(String dropDownDescription) {
		this.dropDownDescription = dropDownDescription;
	}
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	public Integer getStockSubGroupId() {
		return stockSubGroupId;
	}
	public void setStockSubGroupId(Integer stockSubGroupId) {
		this.stockSubGroupId = stockSubGroupId;
	}
	public Integer getStockGroupId() {
		return stockGroupId;
	}
	public void setStockGroupId(Integer stockGroupId) {
		this.stockGroupId = stockGroupId;
	}
	public Integer getStockReceiptId() {
		return stockReceiptId;
	}
	public void setStockReceiptId(Integer stockReceiptId) {
		this.stockReceiptId = stockReceiptId;
	}
	public Integer getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getDropdownDetailsId() {
		return dropdownDetailsId;
	}
	public void setDropdownDetailsId(Integer dropdownDetailsId) {
		this.dropdownDetailsId = dropdownDetailsId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getReorderlevel() {
		return reorderlevel;
	}
	public void setReorderlevel(int reorderlevel) {
		this.reorderlevel = reorderlevel;
	}
	public int getWarninglevel() {
		return warninglevel;
	}
	public void setWarninglevel(int warninglevel) {
		this.warninglevel = warninglevel;
	}
	public BigDecimal getAvailable() {
		return available;
	}
	public void setAvailable(BigDecimal bigDecimal) {
		this.available = bigDecimal;
	}
	public BigDecimal getRetailprice() {
		return retailprice;
	}
	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}
	public BigDecimal getProfessionalprice() {
		return professionalprice;
	}
	public void setProfessionalprice(BigDecimal professionalprice) {
		this.professionalprice = professionalprice;
	}
	public Date getManufacturedate() {
		return manufacturedate;
	}
	public void setManufacturedate(Date manufacturedate) {
		this.manufacturedate = manufacturedate;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	public String getStockSubGroupName() {
		return stockSubGroupName;
	}
	public void setStockSubGroupName(String stockSubGroupName) {
		this.stockSubGroupName = stockSubGroupName;
	}
	public String getStockGroupMasterName() {
		return stockGroupMasterName;
	}
	public void setStockGroupMasterName(String stockGroupMasterName) {
		this.stockGroupMasterName = stockGroupMasterName;
	}
	
	
	
	

}
