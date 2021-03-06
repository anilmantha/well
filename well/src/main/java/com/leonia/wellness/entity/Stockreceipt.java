package com.leonia.wellness.entity;

// Generated 20 Jul, 2016 3:39:07 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Stockreceipt generated by hbm2java
 */
@Entity
@Table(name = "stockreceipt")
public class Stockreceipt implements java.io.Serializable {

	private int stockreceiptid;
	private Manufacturermaster manufacturermaster;
	private Stockmaster stockmaster;
	private Suppliermaster suppliermaster;
	private int sno;
	private Date manufacturedate;
	private Date expirydate;
	private int receivedstock;
	private Date receiveddate;
	private BigDecimal stockunitprice;
	private int currentstock;
	private String remarks;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private BigDecimal totalstockcost;

	public Stockreceipt() {
	}

	public Stockreceipt(int stockreceiptid,
			Manufacturermaster manufacturermaster, Stockmaster stockmaster,
			Suppliermaster suppliermaster, int sno, Date manufacturedate,
			Date expirydate, int receivedstock, Date receiveddate,
			BigDecimal stockunitprice, int currentstock, boolean valid,
			String updatedby, Date updateddate, String updatedip,
			BigDecimal totalstockcost) {
		this.stockreceiptid = stockreceiptid;
		this.manufacturermaster = manufacturermaster;
		this.stockmaster = stockmaster;
		this.suppliermaster = suppliermaster;
		this.sno = sno;
		this.manufacturedate = manufacturedate;
		this.expirydate = expirydate;
		this.receivedstock = receivedstock;
		this.receiveddate = receiveddate;
		this.stockunitprice = stockunitprice;
		this.currentstock = currentstock;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.totalstockcost = totalstockcost;
	}

	public Stockreceipt(int stockreceiptid,
			Manufacturermaster manufacturermaster, Stockmaster stockmaster,
			Suppliermaster suppliermaster, int sno, Date manufacturedate,
			Date expirydate, int receivedstock, Date receiveddate,
			BigDecimal stockunitprice, int currentstock, String remarks,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, BigDecimal totalstockcost) {
		this.stockreceiptid = stockreceiptid;
		this.manufacturermaster = manufacturermaster;
		this.stockmaster = stockmaster;
		this.suppliermaster = suppliermaster;
		this.sno = sno;
		this.manufacturedate = manufacturedate;
		this.expirydate = expirydate;
		this.receivedstock = receivedstock;
		this.receiveddate = receiveddate;
		this.stockunitprice = stockunitprice;
		this.currentstock = currentstock;
		this.remarks = remarks;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.totalstockcost = totalstockcost;
	}

	@Id
	@Column(name = "stockreceiptid", unique = true, nullable = false)
	public int getStockreceiptid() {
		return this.stockreceiptid;
	}

	public void setStockreceiptid(int stockreceiptid) {
		this.stockreceiptid = stockreceiptid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturerid", nullable = false)
	public Manufacturermaster getManufacturermaster() {
		return this.manufacturermaster;
	}

	public void setManufacturermaster(Manufacturermaster manufacturermaster) {
		this.manufacturermaster = manufacturermaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stockid", nullable = false)
	public Stockmaster getStockmaster() {
		return this.stockmaster;
	}

	public void setStockmaster(Stockmaster stockmaster) {
		this.stockmaster = stockmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplierid", nullable = false)
	public Suppliermaster getSuppliermaster() {
		return this.suppliermaster;
	}

	public void setSuppliermaster(Suppliermaster suppliermaster) {
		this.suppliermaster = suppliermaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "manufacturedate", nullable = false, length = 13)
	public Date getManufacturedate() {
		return this.manufacturedate;
	}

	public void setManufacturedate(Date manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate", nullable = false, length = 13)
	public Date getExpirydate() {
		return this.expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	@Column(name = "receivedstock", nullable = false)
	public int getReceivedstock() {
		return this.receivedstock;
	}

	public void setReceivedstock(int receivedstock) {
		this.receivedstock = receivedstock;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "receiveddate", nullable = false, length = 13)
	public Date getReceiveddate() {
		return this.receiveddate;
	}

	public void setReceiveddate(Date receiveddate) {
		this.receiveddate = receiveddate;
	}

	@Column(name = "stockunitprice", nullable = false, precision = 15)
	public BigDecimal getStockunitprice() {
		return this.stockunitprice;
	}

	public void setStockunitprice(BigDecimal stockunitprice) {
		this.stockunitprice = stockunitprice;
	}

	@Column(name = "currentstock", nullable = false)
	public int getCurrentstock() {
		return this.currentstock;
	}

	public void setCurrentstock(int currentstock) {
		this.currentstock = currentstock;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "valid", nullable = false)
	public boolean isValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
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

	@Column(name = "totalstockcost", nullable = false, precision = 15)
	public BigDecimal getTotalstockcost() {
		return this.totalstockcost;
	}

	public void setTotalstockcost(BigDecimal totalstockcost) {
		this.totalstockcost = totalstockcost;
	}

}
