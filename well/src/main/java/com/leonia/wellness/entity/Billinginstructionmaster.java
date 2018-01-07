package com.leonia.wellness.entity;

// Generated 2 Jul, 2016 7:32:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Billinginstructionmaster generated by hbm2java
 */
@Entity
@Table(name = "billinginstructionmaster")
public class Billinginstructionmaster implements java.io.Serializable {

	private int billinginstructionid;
	private int sno;
	private String instructiondescription;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Guestmaster> guestmasters = new HashSet<Guestmaster>(0);
	private Set<Appointment> appointments = new HashSet<Appointment>(0);

	public Billinginstructionmaster() {
	}

	public Billinginstructionmaster(int billinginstructionid, int sno,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.billinginstructionid = billinginstructionid;
		this.sno = sno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Billinginstructionmaster(int billinginstructionid, int sno,
			String instructiondescription, boolean valid, String updatedby,
			Date updateddate, String updatedip, Set<Guestmaster> guestmasters,
			Set<Appointment> appointments) {
		this.billinginstructionid = billinginstructionid;
		this.sno = sno;
		this.instructiondescription = instructiondescription;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.guestmasters = guestmasters;
		this.appointments = appointments;
	}

	@Id
	@Column(name = "billinginstructionid", unique = true, nullable = false)
	public int getBillinginstructionid() {
		return this.billinginstructionid;
	}

	public void setBillinginstructionid(int billinginstructionid) {
		this.billinginstructionid = billinginstructionid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "instructiondescription", length = 200)
	public String getInstructiondescription() {
		return this.instructiondescription;
	}

	public void setInstructiondescription(String instructiondescription) {
		this.instructiondescription = instructiondescription;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billinginstructionmaster")
	public Set<Guestmaster> getGuestmasters() {
		return this.guestmasters;
	}

	public void setGuestmasters(Set<Guestmaster> guestmasters) {
		this.guestmasters = guestmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billinginstructionmaster")
	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

}