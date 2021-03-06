package com.leonia.wellness.entity;

// Generated 2 Jul, 2016 7:32:27 PM by Hibernate Tools 4.3.1

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
 * Stockadjustment generated by hbm2java
 */
@Entity
@Table(name = "stockadjustment")
public class Stockadjustment implements java.io.Serializable {

	private int stockadjustid;
	private Stockmaster stockmaster;
	private int sno;
	private int qtybefore;
	private int qty;
	private int qtyafter;
	private Date expirydate;
	private String remarks;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;

	public Stockadjustment() {
	}

	public Stockadjustment(int stockadjustid, Stockmaster stockmaster, int sno,
			int qtybefore, int qty, int qtyafter, boolean valid,
			String updatedby, Date updateddate, String updatedip) {
		this.stockadjustid = stockadjustid;
		this.stockmaster = stockmaster;
		this.sno = sno;
		this.qtybefore = qtybefore;
		this.qty = qty;
		this.qtyafter = qtyafter;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Stockadjustment(int stockadjustid, Stockmaster stockmaster, int sno,
			int qtybefore, int qty, int qtyafter, Date expirydate,
			String remarks, boolean valid, String updatedby, Date updateddate,
			String updatedip) {
		this.stockadjustid = stockadjustid;
		this.stockmaster = stockmaster;
		this.sno = sno;
		this.qtybefore = qtybefore;
		this.qty = qty;
		this.qtyafter = qtyafter;
		this.expirydate = expirydate;
		this.remarks = remarks;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	@Id
	@Column(name = "stockadjustid", unique = true, nullable = false)
	public int getStockadjustid() {
		return this.stockadjustid;
	}

	public void setStockadjustid(int stockadjustid) {
		this.stockadjustid = stockadjustid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stockid", nullable = false)
	public Stockmaster getStockmaster() {
		return this.stockmaster;
	}

	public void setStockmaster(Stockmaster stockmaster) {
		this.stockmaster = stockmaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "qtybefore", nullable = false)
	public int getQtybefore() {
		return this.qtybefore;
	}

	public void setQtybefore(int qtybefore) {
		this.qtybefore = qtybefore;
	}

	@Column(name = "qty", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Column(name = "qtyafter", nullable = false)
	public int getQtyafter() {
		return this.qtyafter;
	}

	public void setQtyafter(int qtyafter) {
		this.qtyafter = qtyafter;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate", length = 13)
	public Date getExpirydate() {
		return this.expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
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

}
