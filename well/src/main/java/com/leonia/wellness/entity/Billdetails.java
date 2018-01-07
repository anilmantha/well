package com.leonia.wellness.entity;
// Generated Aug 17, 2016 6:02:23 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Billdetails generated by hbm2java
 */
@Entity
@Table(name = "billdetails")
public class Billdetails implements java.io.Serializable {

	private int sno;
	private Appointment appointment;
	private Billmaster billmaster;
	private Discountmaster discountmaster;
	private Packagemaster packagemaster;
	private Servicemaster servicemaster;
	private Stockmaster stockmaster;
	private Taxstructuremaster taxstructuremaster;
	private Integer taxstructureid;
	private Integer taxdetailsid;
	private BigDecimal amount;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Integer productquantity;

	public Billdetails() {
	}

	public Billdetails(int sno, Appointment appointment, BigDecimal amount, String updatedby, Date updateddate,
			String updatedip) {
		this.sno = sno;
		this.appointment = appointment;
		this.amount = amount;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Billdetails(int sno, Appointment appointment, Billmaster billmaster, Discountmaster discountmaster,
			Packagemaster packagemaster, Servicemaster servicemaster, Stockmaster stockmaster,
			Taxstructuremaster taxstructuremaster, Integer taxstructureid, Integer taxdetailsid, BigDecimal amount,
			String updatedby, Date updateddate, String updatedip, Integer productquantity) {
		this.sno = sno;
		this.appointment = appointment;
		this.billmaster = billmaster;
		this.discountmaster = discountmaster;
		this.packagemaster = packagemaster;
		this.servicemaster = servicemaster;
		this.stockmaster = stockmaster;
		this.taxstructuremaster = taxstructuremaster;
		this.taxstructureid = taxstructureid;
		this.taxdetailsid = taxdetailsid;
		this.amount = amount;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.productquantity = productquantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno", unique = true, insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointmentid", nullable = false)
	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billno")
	public Billmaster getBillmaster() {
		return this.billmaster;
	}

	public void setBillmaster(Billmaster billmaster) {
		this.billmaster = billmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discountid")
	public Discountmaster getDiscountmaster() {
		return this.discountmaster;
	}

	public void setDiscountmaster(Discountmaster discountmaster) {
		this.discountmaster = discountmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "packageid")
	public Packagemaster getPackagemaster() {
		return this.packagemaster;
	}

	public void setPackagemaster(Packagemaster packagemaster) {
		this.packagemaster = packagemaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceid")
	public Servicemaster getServicemaster() {
		return this.servicemaster;
	}

	public void setServicemaster(Servicemaster servicemaster) {
		this.servicemaster = servicemaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	public Stockmaster getStockmaster() {
		return this.stockmaster;
	}

	public void setStockmaster(Stockmaster stockmaster) {
		this.stockmaster = stockmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxstructurecode")
	public Taxstructuremaster getTaxstructuremaster() {
		return this.taxstructuremaster;
	}

	public void setTaxstructuremaster(Taxstructuremaster taxstructuremaster) {
		this.taxstructuremaster = taxstructuremaster;
	}

	@Column(name = "taxstructureid")
	public Integer getTaxstructureid() {
		return this.taxstructureid;
	}

	public void setTaxstructureid(Integer taxstructureid) {
		this.taxstructureid = taxstructureid;
	}

	@Column(name = "taxdetailsid")
	public Integer getTaxdetailsid() {
		return this.taxdetailsid;
	}

	public void setTaxdetailsid(Integer taxdetailsid) {
		this.taxdetailsid = taxdetailsid;
	}

	@Column(name = "amount", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "updatedby", nullable = false, length = 150)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateddate", nullable = false, length = 29)
	public Date getUpdateddate() {
		return this.updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	@Column(name = "updatedip", nullable = false, length = 150)
	public String getUpdatedip() {
		return this.updatedip;
	}

	public void setUpdatedip(String updatedip) {
		this.updatedip = updatedip;
	}

	@Column(name = "productquantity")
	public Integer getProductquantity() {
		return this.productquantity;
	}

	public void setProductquantity(Integer productquantity) {
		this.productquantity = productquantity;
	}

}
