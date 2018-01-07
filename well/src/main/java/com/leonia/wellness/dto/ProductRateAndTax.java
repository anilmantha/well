package com.leonia.wellness.dto;

import java.math.BigDecimal;
import java.util.List;

import com.leonia.wellness.entity.Taxstructuredetails;

public class ProductRateAndTax {
	
	private BigDecimal productCost;
	private List<Taxstructuredetails> taxAmount;
	private String productName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductCost() {
		return productCost;
	}
	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}
	public List<Taxstructuredetails> getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(List<Taxstructuredetails> taxAmount) {
		this.taxAmount = taxAmount;
	}
}
