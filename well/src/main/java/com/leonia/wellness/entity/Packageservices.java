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
 * Packageservices generated by hbm2java
 */
@Entity
@Table(name = "packageservices")
public class Packageservices implements java.io.Serializable {

	private int packageserviceid;
	private Packagemaster packagemaster;
	private Servicemaster servicemaster;
	private int sno;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;

	public Packageservices() {
	}

	public Packageservices(int packageserviceid, Packagemaster packagemaster,
			Servicemaster servicemaster, int sno, boolean valid,
			String updatedby, Date updateddate, String updatedip) {
		this.packageserviceid = packageserviceid;
		this.packagemaster = packagemaster;
		this.servicemaster = servicemaster;
		this.sno = sno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	@Id
	@Column(name = "packageserviceid", unique = true, nullable = false)
	public int getPackageserviceid() {
		return this.packageserviceid;
	}

	public void setPackageserviceid(int packageserviceid) {
		this.packageserviceid = packageserviceid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "packageid", nullable = false)
	public Packagemaster getPackagemaster() {
		return this.packagemaster;
	}

	public void setPackagemaster(Packagemaster packagemaster) {
		this.packagemaster = packagemaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceid", nullable = false)
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
