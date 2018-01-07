package com.leonia.wellness.entity;
// Generated Aug 20, 2016 2:52:14 PM by Hibernate Tools 4.3.1.Final

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
 * Guesthistoryservices generated by hbm2java
 */
@Entity
@Table(name = "guesthistoryservices")
public class Guesthistoryservices implements java.io.Serializable {

	private int guesthistoryserviceid;
	private Appointment appointment;
	private Guestmaster guestmaster;
	private Packagemaster packagemaster;
	private Servicemaster servicemaster;
	private int sno;
	private BigDecimal revenue;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Date visitdate;
	private Date remainderdate;

	public Guesthistoryservices() {
	}

	public Guesthistoryservices(int guesthistoryserviceid, Appointment appointment, Guestmaster guestmaster, int sno,
			BigDecimal revenue, boolean valid, String updatedby, Date updateddate, String updatedip, Date visitdate) {
		this.guesthistoryserviceid = guesthistoryserviceid;
		this.appointment = appointment;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.revenue = revenue;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.visitdate = visitdate;
	}

	public Guesthistoryservices(int guesthistoryserviceid, Appointment appointment, Guestmaster guestmaster,
			Packagemaster packagemaster, Servicemaster servicemaster, int sno, BigDecimal revenue, boolean valid,
			String updatedby, Date updateddate, String updatedip, Date visitdate, Date remainderdate) {
		this.guesthistoryserviceid = guesthistoryserviceid;
		this.appointment = appointment;
		this.guestmaster = guestmaster;
		this.packagemaster = packagemaster;
		this.servicemaster = servicemaster;
		this.sno = sno;
		this.revenue = revenue;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.visitdate = visitdate;
		this.remainderdate = remainderdate;
	}

	@Id

	@Column(name = "guesthistoryserviceid", unique = true, nullable = false)
	public int getGuesthistoryserviceid() {
		return this.guesthistoryserviceid;
	}

	public void setGuesthistoryserviceid(int guesthistoryserviceid) {
		this.guesthistoryserviceid = guesthistoryserviceid;
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
	@JoinColumn(name = "guestid", nullable = false)
	public Guestmaster getGuestmaster() {
		return this.guestmaster;
	}

	public void setGuestmaster(Guestmaster guestmaster) {
		this.guestmaster = guestmaster;
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

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "visitdate", nullable = false, length = 13)
	public Date getVisitdate() {
		return this.visitdate;
	}

	public void setVisitdate(Date visitdate) {
		this.visitdate = visitdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "remainderdate", length = 13)
	public Date getRemainderdate() {
		return this.remainderdate;
	}

	public void setRemainderdate(Date remainderdate) {
		this.remainderdate = remainderdate;
	}

}
