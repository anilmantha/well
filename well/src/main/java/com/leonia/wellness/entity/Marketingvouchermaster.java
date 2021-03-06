package com.leonia.wellness.entity;

// Generated 14 Jul, 2016 5:05:27 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * Marketingvouchermaster generated by hbm2java
 */
@Entity
@Table(name = "marketingvouchermaster")
public class Marketingvouchermaster implements java.io.Serializable {

	private int marketingvoucherid;
	private int sno;
	private BigDecimal voucheramount;
	private String marketingcompany;
	private Date validfrom;
	private Date validto;
	private String remarks;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Billsettlement> billsettlements = new HashSet<Billsettlement>(0);

	public Marketingvouchermaster() {
	}

	public Marketingvouchermaster(int marketingvoucherid, int sno,
			BigDecimal voucheramount, String marketingcompany, Date validfrom,
			Date validto, boolean valid, String updatedby, Date updateddate,
			String updatedip) {
		this.marketingvoucherid = marketingvoucherid;
		this.sno = sno;
		this.voucheramount = voucheramount;
		this.marketingcompany = marketingcompany;
		this.validfrom = validfrom;
		this.validto = validto;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Marketingvouchermaster(int marketingvoucherid, int sno,
			BigDecimal voucheramount, String marketingcompany, Date validfrom,
			Date validto, String remarks, boolean valid, String updatedby,
			Date updateddate, String updatedip,
			Set<Billsettlement> billsettlements) {
		this.marketingvoucherid = marketingvoucherid;
		this.sno = sno;
		this.voucheramount = voucheramount;
		this.marketingcompany = marketingcompany;
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
	@Column(name = "marketingvoucherid", unique = true, nullable = false)
	public int getMarketingvoucherid() {
		return this.marketingvoucherid;
	}

	public void setMarketingvoucherid(int marketingvoucherid) {
		this.marketingvoucherid = marketingvoucherid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "voucheramount", nullable = false, precision = 15)
	public BigDecimal getVoucheramount() {
		return this.voucheramount;
	}

	public void setVoucheramount(BigDecimal voucheramount) {
		this.voucheramount = voucheramount;
	}

	@Column(name = "marketingcompany", nullable = false, length = 200)
	public String getMarketingcompany() {
		return this.marketingcompany;
	}

	public void setMarketingcompany(String marketingcompany) {
		this.marketingcompany = marketingcompany;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marketingvouchermaster")
	public Set<Billsettlement> getBillsettlements() {
		return this.billsettlements;
	}

	public void setBillsettlements(Set<Billsettlement> billsettlements) {
		this.billsettlements = billsettlements;
	}

}
