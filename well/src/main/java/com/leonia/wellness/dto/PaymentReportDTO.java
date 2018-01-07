package com.leonia.wellness.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentReportDTO {

	Integer billno;
	Date billDate;
	String guestName;
	String staffName;
	String serviceName;
	String packageName;
	Integer roomno;
	String paymentmode;
	Integer giftVoucherId;
	BigDecimal discount;
	BigDecimal totalAmount;
	BigDecimal paidAmount;
	Date paymentDate;
	Integer cardno;
	
	public Integer getBillno() {
		return billno;
	}
	public void setBillno(Integer billno) {
		this.billno = billno;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getRoomno() {
		return roomno;
	}
	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public Integer getGiftVoucherId() {
		return giftVoucherId;
	}
	public void setGiftVoucherId(Integer giftVoucherId) {
		this.giftVoucherId = giftVoucherId;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Integer getCardno() {
		return cardno;
	}
	public void setCardno(Integer cardno) {
		this.cardno = cardno;
	}
}
