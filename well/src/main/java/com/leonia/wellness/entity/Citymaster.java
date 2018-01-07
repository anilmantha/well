package com.leonia.wellness.entity;

// Generated 2 Jul, 2016 7:32:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Citymaster generated by hbm2java
 */
@Entity
@Table(name = "citymaster")
public class Citymaster implements java.io.Serializable {

	private int cityid;
	private Statemaster statemaster;
	private int sno;
	private String cityname;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Staffmaster> staffmasters = new HashSet<Staffmaster>(0);
	private Set<Guestmaster> guestmasters = new HashSet<Guestmaster>(0);

	public Citymaster() {
	}

	public Citymaster(int cityid, Statemaster statemaster, int sno,
			String cityname, boolean valid, String updatedby, Date updateddate,
			String updatedip) {
		this.cityid = cityid;
		this.statemaster = statemaster;
		this.sno = sno;
		this.cityname = cityname;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Citymaster(int cityid, Statemaster statemaster, int sno,
			String cityname, boolean valid, String updatedby, Date updateddate,
			String updatedip, Set<Staffmaster> staffmasters,
			Set<Guestmaster> guestmasters) {
		this.cityid = cityid;
		this.statemaster = statemaster;
		this.sno = sno;
		this.cityname = cityname;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.staffmasters = staffmasters;
		this.guestmasters = guestmasters;
	}

	@Id
	@Column(name = "cityid", unique = true, nullable = false)
	public int getCityid() {
		return this.cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateid", nullable = false)
	public Statemaster getStatemaster() {
		return this.statemaster;
	}

	public void setStatemaster(Statemaster statemaster) {
		this.statemaster = statemaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "cityname", nullable = false, length = 100)
	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "citymaster")
	public Set<Staffmaster> getStaffmasters() {
		return this.staffmasters;
	}

	public void setStaffmasters(Set<Staffmaster> staffmasters) {
		this.staffmasters = staffmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "citymaster")
	public Set<Guestmaster> getGuestmasters() {
		return this.guestmasters;
	}

	public void setGuestmasters(Set<Guestmaster> guestmasters) {
		this.guestmasters = guestmasters;
	}

}
