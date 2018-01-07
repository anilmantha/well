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
 * Guesthistorycomplaints generated by hbm2java
 */
@Entity
@Table(name = "guesthistorycomplaints")
public class Guesthistorycomplaints implements java.io.Serializable {

	private int guesthistorycomplaintid;
	private Appointment appointment;
	private Guestmaster guestmaster;
	private int sno;
	private Date lastvisitdate;
	private String complaints;
	private String remarks;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;

	public Guesthistorycomplaints() {
	}

	public Guesthistorycomplaints(int guesthistorycomplaintid,
			Appointment appointment, Guestmaster guestmaster, int sno,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.guesthistorycomplaintid = guesthistorycomplaintid;
		this.appointment = appointment;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Guesthistorycomplaints(int guesthistorycomplaintid,
			Appointment appointment, Guestmaster guestmaster, int sno,
			Date lastvisitdate, String complaints, String remarks,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.guesthistorycomplaintid = guesthistorycomplaintid;
		this.appointment = appointment;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.lastvisitdate = lastvisitdate;
		this.complaints = complaints;
		this.remarks = remarks;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	@Id
	@Column(name = "guesthistorycomplaintid", unique = true, nullable = false)
	public int getGuesthistorycomplaintid() {
		return this.guesthistorycomplaintid;
	}

	public void setGuesthistorycomplaintid(int guesthistorycomplaintid) {
		this.guesthistorycomplaintid = guesthistorycomplaintid;
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

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lastvisitdate", length = 13)
	public Date getLastvisitdate() {
		return this.lastvisitdate;
	}

	public void setLastvisitdate(Date lastvisitdate) {
		this.lastvisitdate = lastvisitdate;
	}

	@Column(name = "complaints")
	public String getComplaints() {
		return this.complaints;
	}

	public void setComplaints(String complaints) {
		this.complaints = complaints;
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
