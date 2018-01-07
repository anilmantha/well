package com.leonia.wellness.entity;

// Generated 2 Jul, 2016 7:52:45 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * Creditlistmaster generated by hbm2java
 */
@Entity
@Table(name = "creditlistmaster")
public class Creditlistmaster implements java.io.Serializable {

	private int creditlistid;
	private Corporatemaster corporatemaster;
	private int sno;
	private boolean blacklisted;
	private String blacklistreason;
	private BigDecimal creditamount;
	private BigDecimal outstandingamount;
	private Date validfrom;
	private Date validto;
	private String remarks;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Billsettlement> billsettlements = new HashSet<Billsettlement>(0);

	public Creditlistmaster() {
	}

	public Creditlistmaster(int creditlistid, Corporatemaster corporatemaster,
			int sno, boolean blacklisted, BigDecimal creditamount,
			BigDecimal outstandingamount, Date validfrom, Date validto,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.creditlistid = creditlistid;
		this.corporatemaster = corporatemaster;
		this.sno = sno;
		this.blacklisted = blacklisted;
		this.creditamount = creditamount;
		this.outstandingamount = outstandingamount;
		this.validfrom = validfrom;
		this.validto = validto;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Creditlistmaster(int creditlistid, Corporatemaster corporatemaster,
			int sno, boolean blacklisted, String blacklistreason,
			BigDecimal creditamount, BigDecimal outstandingamount,
			Date validfrom, Date validto, String remarks, boolean valid,
			String updatedby, Date updateddate, String updatedip,
			Set<Billsettlement> billsettlements) {
		this.creditlistid = creditlistid;
		this.corporatemaster = corporatemaster;
		this.sno = sno;
		this.blacklisted = blacklisted;
		this.blacklistreason = blacklistreason;
		this.creditamount = creditamount;
		this.outstandingamount = outstandingamount;
		this.validfrom = validfrom;
		this.validto = validto;
		this.remarks = remarks;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.billsettlements = billsettlements;
	}

	@Id
	@Column(name = "creditlistid", unique = true, nullable = false)
	public int getCreditlistid() {
		return this.creditlistid;
	}

	public void setCreditlistid(int creditlistid) {
		this.creditlistid = creditlistid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corporateid", nullable = false)
	public Corporatemaster getCorporatemaster() {
		return this.corporatemaster;
	}

	public void setCorporatemaster(Corporatemaster corporatemaster) {
		this.corporatemaster = corporatemaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "blacklisted", nullable = false)
	public boolean isBlacklisted() {
		return this.blacklisted;
	}

	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	@Column(name = "blacklistreason")
	public String getBlacklistreason() {
		return this.blacklistreason;
	}

	public void setBlacklistreason(String blacklistreason) {
		this.blacklistreason = blacklistreason;
	}

	@Column(name = "creditamount", nullable = false, precision = 15)
	public BigDecimal getCreditamount() {
		return this.creditamount;
	}

	public void setCreditamount(BigDecimal creditamount) {
		this.creditamount = creditamount;
	}

	@Column(name = "outstandingamount", nullable = false, precision = 15)
	public BigDecimal getOutstandingamount() {
		return this.outstandingamount;
	}

	public void setOutstandingamount(BigDecimal outstandingamount) {
		this.outstandingamount = outstandingamount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "validfrom", nullable = false, length = 13)
	public Date getValidfrom() {
		return this.validfrom;
	}

	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "validto", nullable = false, length = 13)
	public Date getValidto() {
		return this.validto;
	}

	public void setValidto(Date validto) {
		this.validto = validto;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creditlistmaster")
	public Set<Billsettlement> getBillsettlements() {
		return this.billsettlements;
	}

	public void setBillsettlements(Set<Billsettlement> billsettlements) {
		this.billsettlements = billsettlements;
	}

}
