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
 * Qualificationmaster generated by hbm2java
 */
@Entity
@Table(name = "qualificationmaster")
public class Qualificationmaster implements java.io.Serializable {

	private int qualificationid;
	private int sno;
	private String qualification;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Staffqualification> staffqualifications = new HashSet<Staffqualification>(
			0);

	public Qualificationmaster() {
	}

	public Qualificationmaster(int qualificationid, int sno, boolean valid,
			String updatedby, Date updateddate, String updatedip) {
		this.qualificationid = qualificationid;
		this.sno = sno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Qualificationmaster(int qualificationid, int sno,
			String qualification, boolean valid, String updatedby,
			Date updateddate, String updatedip,
			Set<Staffqualification> staffqualifications) {
		this.qualificationid = qualificationid;
		this.sno = sno;
		this.qualification = qualification;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.staffqualifications = staffqualifications;
	}

	@Id
	@Column(name = "qualificationid", unique = true, nullable = false)
	public int getQualificationid() {
		return this.qualificationid;
	}

	public void setQualificationid(int qualificationid) {
		this.qualificationid = qualificationid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "qualification", length = 200)
	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "qualificationmaster")
	public Set<Staffqualification> getStaffqualifications() {
		return this.staffqualifications;
	}

	public void setStaffqualifications(
			Set<Staffqualification> staffqualifications) {
		this.staffqualifications = staffqualifications;
	}

}
