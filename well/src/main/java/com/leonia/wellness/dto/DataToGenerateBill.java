package com.leonia.wellness.dto;

import java.math.BigDecimal;

public class DataToGenerateBill {
	private Integer appointmentId;
	private Integer packageId;
	private Integer serviceId;
	private Integer productId;
	private BigDecimal quantity;
	private String productName;
	private String guestName;
	private BigDecimal productrate;
	private BigDecimal servicerate;
	private Integer guestId;
	private BigDecimal tax;
	private BigDecimal total;
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductrate() {
		return productrate;
	}
	public void setProductrate(BigDecimal productrate) {
		this.productrate = productrate;
	}
	public BigDecimal getServicerate() {
		return servicerate;
	}
	public void setServicerate(BigDecimal servicerate) {
		this.servicerate = servicerate;
	}
	public Integer getGuestId() {
		return guestId;
	}
	public void setGuestId(Integer guestId) {
		this.guestId = guestId;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		packageId = packageId;
	}
}
