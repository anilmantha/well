package com.leonia.wellness.dto;

import java.math.BigDecimal;
import java.util.List;

import com.leonia.wellness.entity.Taxstructuredetails;

public class ServiceTaxRates {
	
	private BigDecimal serviceCost;
	private List<Taxstructuredetails> taxAmount;
	
	public BigDecimal getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(BigDecimal serviceCost) {
		this.serviceCost = serviceCost;
	}
	public List<Taxstructuredetails> getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(List<Taxstructuredetails> taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	
	
}
