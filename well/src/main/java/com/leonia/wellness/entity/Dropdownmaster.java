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
 * Dropdownmaster generated by hbm2java
 */
@Entity
@Table(name = "dropdownmaster")
public class Dropdownmaster implements java.io.Serializable {

	private int dropdownmasterid;
	private int sno;
	private String description;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Dropdowndetails> dropdowndetailses = new HashSet<Dropdowndetails>(
			0);

	public Dropdownmaster() {
	}

	public Dropdownmaster(int dropdownmasterid, int sno, String description,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.dropdownmasterid = dropdownmasterid;
		this.sno = sno;
		this.description = description;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Dropdownmaster(int dropdownmasterid, int sno, String description,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, Set<Dropdowndetails> dropdowndetailses) {
		this.dropdownmasterid = dropdownmasterid;
		this.sno = sno;
		this.description = description;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.dropdowndetailses = dropdowndetailses;
	}

	@Id
	@Column(name = "dropdownmasterid", unique = true, nullable = false)
	public int getDropdownmasterid() {
		return this.dropdownmasterid;
	}

	public void setDropdownmasterid(int dropdownmasterid) {
		this.dropdownmasterid = dropdownmasterid;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dropdownmaster")
	public Set<Dropdowndetails> getDropdowndetailses() {
		return this.dropdowndetailses;
	}

	public void setDropdowndetailses(Set<Dropdowndetails> dropdowndetailses) {
		this.dropdowndetailses = dropdowndetailses;
	}

}