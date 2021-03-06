package com.leonia.wellness.entity;

// Generated 10 Aug, 2016 10:41:43 AM by Hibernate Tools 4.3.1

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
 * Guesthistoryrevenue generated by hbm2java
 */
@Entity
@Table(name = "guesthistoryrevenue")
public class Guesthistoryrevenue implements java.io.Serializable {

	private int guesthistoryrevenueid;
	private Guestmaster guestmaster;
	private int sno;
	private Date visitdate;
	private BigDecimal revenue;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;

	public Guesthistoryrevenue() {
	}

	public Guesthistoryrevenue(int guesthistoryrevenueid,
			Guestmaster guestmaster, int sno, Date visitdate,
			BigDecimal revenue, boolean valid, String updatedby,
			Date updateddate, String updatedip) {
		this.guesthistoryrevenueid = guesthistoryrevenueid;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.visitdate = visitdate;
		this.revenue = revenue;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	@Id
	@Column(name = "guesthistoryrevenueid", unique = true, nullable = false)
	public int getGuesthistoryrevenueid() {
		return this.guesthistoryrevenueid;
	}

	public void setGuesthistoryrevenueid(int guesthistoryrevenueid) {
		this.guesthistoryrevenueid = guesthistoryrevenueid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guestid", nullable = false)
	public Guestmaster getGuestmaster() {
		return this.guestmaster;
	}

	public void setGuestmaster(Guestmaster guestmaster) {
		this.guestmaster = guestmaster;
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
	@Column(name = "visitdate", nullable = false, length = 13)
	public Date getVisitdate() {
		return this.visitdate;
	}

	public void setVisitdate(Date visitdate) {
		this.visitdate = visitdate;
	}

	@Column(name = "revenue", nullable = false, precision = 15)
	public BigDecimal getRevenue() {
		return this.revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
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
