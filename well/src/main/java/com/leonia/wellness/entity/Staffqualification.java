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
 * Staffqualification generated by hbm2java
 */
@Entity
@Table(name = "staffqualification")
public class Staffqualification implements java.io.Serializable {

	private int staffqualificationid;
	private Qualificationmaster qualificationmaster;
	private Staffmaster staffmaster;
	private int sno;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;

	public Staffqualification() {
	}

	public Staffqualification(int staffqualificationid,
			Qualificationmaster qualificationmaster, Staffmaster staffmaster,
			int sno, boolean valid, String updatedby, Date updateddate,
			String updatedip) {
		this.staffqualificationid = staffqualificationid;
		this.qualificationmaster = qualificationmaster;
		this.staffmaster = staffmaster;
		this.sno = sno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	@Id
	@Column(name = "staffqualificationid", unique = true, nullable = false)
	public int getStaffqualificationid() {
		return this.staffqualificationid;
	}

	public void setStaffqualificationid(int staffqualificationid) {
		this.staffqualificationid = staffqualificationid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qualificationid", nullable = false)
	public Qualificationmaster getQualificationmaster() {
		return this.qualificationmaster;
	}

	public void setQualificationmaster(Qualificationmaster qualificationmaster) {
		this.qualificationmaster = qualificationmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffid", nullable = false)
	public Staffmaster getStaffmaster() {
		return this.staffmaster;
	}

	public void setStaffmaster(Staffmaster staffmaster) {
		this.staffmaster = staffmaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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
