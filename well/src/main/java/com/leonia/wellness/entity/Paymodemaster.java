package com.leonia.wellness.entity;

// Generated 2 Jul, 2016 7:52:45 PM by Hibernate Tools 4.3.1

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
 * Paymodemaster generated by hbm2java
 */
@Entity
@Table(name = "paymodemaster")
public class Paymodemaster implements java.io.Serializable {

	private int paymodeid;
	private int sno;
	private String paymode;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Billsettlement> billsettlements = new HashSet<Billsettlement>(0);

	public Paymodemaster() {
	}

	public Paymodemaster(int paymodeid, int sno, String paymode, boolean valid,
			String updatedby, Date updateddate, String updatedip) {
		this.paymodeid = paymodeid;
		this.sno = sno;
		this.paymode = paymode;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Paymodemaster(int paymodeid, int sno, String paymode, boolean valid,
			String updatedby, Date updateddate, String updatedip,
			Set<Billsettlement> billsettlements) {
		this.paymodeid = paymodeid;
		this.sno = sno;
		this.paymode = paymode;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.billsettlements = billsettlements;
	}

	@Id
	@Column(name = "paymodeid", unique = true, nullable = false)
	public int getPaymodeid() {
		return this.paymodeid;
	}

	public void setPaymodeid(int paymodeid) {
		this.paymodeid = paymodeid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "paymode", nullable = false, length = 200)
	public String getPaymode() {
		return this.paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymodemaster")
	public Set<Billsettlement> getBillsettlements() {
		return this.billsettlements;
	}

	public void setBillsettlements(Set<Billsettlement> billsettlements) {
		this.billsettlements = billsettlements;
	}

}
