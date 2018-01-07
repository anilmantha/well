package com.leonia.wellness.dto;

import java.util.Date;

public class TaxDetails {

	private Integer taxStructureId;
	private Integer taxId;
	private String taxStructureDescription;
	private String taxStructurePercent;
	private String taxType;
	private String taxOn;
	private String taxName;
	private Date applicableDate;
	
	public Integer getTaxStructureId() {
		return taxStructureId;
	}
	public void setTaxStructureId(Integer taxStructureId) {
		this.taxStructureId = taxStructureId;
	}
	public Integer getTaxId() {
		return taxId;
	}
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}
	public String getTaxStructureDescription() {
		return taxStructureDescription;
	}
	public void setTaxStructureDescription(String taxStructureDescription) {
		this.taxStructureDescription = taxStructureDescription;
	}
	public String getTaxStructurePercent() {
		return taxStructurePercent;
	}
	public void setTaxStructurePercent(String taxStructurePercent) {
		this.taxStructurePercent = taxStructurePercent;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getTaxOn() {
		return taxOn;
	}
	public void setTaxOn(String taxOn) {
		this.taxOn = taxOn;
	}
	public Date getApplicableDate() {
		return applicableDate;
	}
	public void setApplicableDate(Date applicableDate) {
		this.applicableDate = applicableDate;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
}
