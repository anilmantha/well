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
 * Statemaster generated by hbm2java
 */
@Entity
@Table(name = "statemaster")
public class Statemaster implements java.io.Serializable {

	private int stateid;
	private Countrymaster countrymaster;
	private int sno;
	private String statename;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Citymaster> citymasters = new HashSet<Citymaster>(0);
	private Set<Staffmaster> staffmasters = new HashSet<Staffmaster>(0);
	private Set<Guestmaster> guestmasters = new HashSet<Guestmaster>(0);

	public Statemaster() {
	}

	public Statemaster(int stateid, Countrymaster countrymaster, int sno,
			String statename, boolean valid, String updatedby,
			Date updateddate, String updatedip) {
		this.stateid = stateid;
		this.countrymaster = countrymaster;
		this.sno = sno;
		this.statename = statename;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Statemaster(int stateid, Countrymaster countrymaster, int sno,
			String statename, boolean valid, String updatedby,
			Date updateddate, String updatedip, Set<Citymaster> citymasters,
			Set<Staffmaster> staffmasters, Set<Guestmaster> guestmasters) {
		this.stateid = stateid;
		this.countrymaster = countrymaster;
		this.sno = sno;
		this.statename = statename;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.citymasters = citymasters;
		this.staffmasters = staffmasters;
		this.guestmasters = guestmasters;
	}

	@Id
	@Column(name = "stateid", unique = true, nullable = false)
	public int getStateid() {
		return this.stateid;
	}

	public void setStateid(int stateid) {
		this.stateid = stateid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryid", nullable = false)
	public Countrymaster getCountrymaster() {
		return this.countrymaster;
	}

	public void setCountrymaster(Countrymaster countrymaster) {
		this.countrymaster = countrymaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "statename", nullable = false, length = 100)
	public String getStatename() {
		return this.statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statemaster")
	public Set<Citymaster> getCitymasters() {
		return this.citymasters;
	}

	public void setCitymasters(Set<Citymaster> citymasters) {
		this.citymasters = citymasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statemaster")
	public Set<Staffmaster> getStaffmasters() {
		return this.staffmasters;
	}

	public void setStaffmasters(Set<Staffmaster> staffmasters) {
		this.staffmasters = staffmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statemaster")
	public Set<Guestmaster> getGuestmasters() {
		return this.guestmasters;
	}

	public void setGuestmasters(Set<Guestmaster> guestmasters) {
		this.guestmasters = guestmasters;
	}

}
