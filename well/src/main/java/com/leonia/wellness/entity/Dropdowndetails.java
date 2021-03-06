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
 * Dropdowndetails generated by hbm2java
 */
@Entity
@Table(name = "dropdowndetails")
public class Dropdowndetails implements java.io.Serializable {

	private int dropdowndetailsid;
	private Dropdownmaster dropdownmaster;
	private int sno;
	private String description;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Stockmaster> stockmasters = new HashSet<Stockmaster>(0);
	private Set<Staffmaster> staffmasters = new HashSet<Staffmaster>(0);
	private Set<Guestmaster> guestmastersForGenderid = new HashSet<Guestmaster>(
			0);
	private Set<Guestmaster> guestmastersForTitleid = new HashSet<Guestmaster>(
			0);
	private Set<Servicemaster> servicemasters = new HashSet<Servicemaster>(0);

	public Dropdowndetails() {
	}

	public Dropdowndetails(int dropdowndetailsid,
			Dropdownmaster dropdownmaster, int sno, String description,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.dropdowndetailsid = dropdowndetailsid;
		this.dropdownmaster = dropdownmaster;
		this.sno = sno;
		this.description = description;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Dropdowndetails(int dropdowndetailsid,
			Dropdownmaster dropdownmaster, int sno, String description,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, Set<Stockmaster> stockmasters,
			Set<Staffmaster> staffmasters,
			Set<Guestmaster> guestmastersForGenderid,
			Set<Guestmaster> guestmastersForTitleid,
			Set<Servicemaster> servicemasters) {
		this.dropdowndetailsid = dropdowndetailsid;
		this.dropdownmaster = dropdownmaster;
		this.sno = sno;
		this.description = description;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.stockmasters = stockmasters;
		this.staffmasters = staffmasters;
		this.guestmastersForGenderid = guestmastersForGenderid;
		this.guestmastersForTitleid = guestmastersForTitleid;
		this.servicemasters = servicemasters;
	}

	@Id
	@Column(name = "dropdowndetailsid", unique = true, nullable = false)
	public int getDropdowndetailsid() {
		return this.dropdowndetailsid;
	}

	public void setDropdowndetailsid(int dropdowndetailsid) {
		this.dropdowndetailsid = dropdowndetailsid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dropdownmasterid", nullable = false)
	public Dropdownmaster getDropdownmaster() {
		return this.dropdownmaster;
	}

	public void setDropdownmaster(Dropdownmaster dropdownmaster) {
		this.dropdownmaster = dropdownmaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "description", nullable = false, length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdowndetails")
	public Set<Stockmaster> getStockmasters() {
		return this.stockmasters;
	}

	public void setStockmasters(Set<Stockmaster> stockmasters) {
		this.stockmasters = stockmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdowndetails")
	public Set<Staffmaster> getStaffmasters() {
		return this.staffmasters;
	}

	public void setStaffmasters(Set<Staffmaster> staffmasters) {
		this.staffmasters = staffmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdowndetailsByGenderid")
	public Set<Guestmaster> getGuestmastersForGenderid() {
		return this.guestmastersForGenderid;
	}

	public void setGuestmastersForGenderid(
			Set<Guestmaster> guestmastersForGenderid) {
		this.guestmastersForGenderid = guestmastersForGenderid;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdowndetailsByTitleid")
	public Set<Guestmaster> getGuestmastersForTitleid() {
		return this.guestmastersForTitleid;
	}

	public void setGuestmastersForTitleid(
			Set<Guestmaster> guestmastersForTitleid) {
		this.guestmastersForTitleid = guestmastersForTitleid;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdowndetails")
	public Set<Servicemaster> getServicemasters() {
		return this.servicemasters;
	}

	public void setServicemasters(Set<Servicemaster> servicemasters) {
		this.servicemasters = servicemasters;
	}

}
