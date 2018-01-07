package com.leonia.wellness.entity;

// Generated 8 Aug, 2016 12:44:55 PM by Hibernate Tools 4.3.1

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
 * Cardtypemaster generated by hbm2java
 */
@Entity
@Table(name = "cardtypemaster")
public class Cardtypemaster implements java.io.Serializable {

	private int cardtypeid;
	private int sno;
	private String cardtypedescription;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Billsettlement> billsettlementsForDebitcardtype = new HashSet<Billsettlement>(
			0);
	private Set<Billsettlement> billsettlementsForCreditcardtype = new HashSet<Billsettlement>(
			0);

	public Cardtypemaster() {
	}

	public Cardtypemaster(int cardtypeid, int sno, String cardtypedescription,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.cardtypeid = cardtypeid;
		this.sno = sno;
		this.cardtypedescription = cardtypedescription;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Cardtypemaster(int cardtypeid, int sno, String cardtypedescription,
			boolean valid, String updatedby, Date updateddate,
			String updatedip,
			Set<Billsettlement> billsettlementsForDebitcardtype,
			Set<Billsettlement> billsettlementsForCreditcardtype) {
		this.cardtypeid = cardtypeid;
		this.sno = sno;
		this.cardtypedescription = cardtypedescription;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.billsettlementsForDebitcardtype = billsettlementsForDebitcardtype;
		this.billsettlementsForCreditcardtype = billsettlementsForCreditcardtype;
	}

	@Id
	@Column(name = "cardtypeid", unique = true, nullable = false)
	public int getCardtypeid() {
		return this.cardtypeid;
	}

	public void setCardtypeid(int cardtypeid) {
		this.cardtypeid = cardtypeid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "cardtypedescription", nullable = false, length = 150)
	public String getCardtypedescription() {
		return this.cardtypedescription;
	}

	public void setCardtypedescription(String cardtypedescription) {
		this.cardtypedescription = cardtypedescription;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cardtypemasterByDebitcardtype")
	public Set<Billsettlement> getBillsettlementsForDebitcardtype() {
		return this.billsettlementsForDebitcardtype;
	}

	public void setBillsettlementsForDebitcardtype(
			Set<Billsettlement> billsettlementsForDebitcardtype) {
		this.billsettlementsForDebitcardtype = billsettlementsForDebitcardtype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cardtypemasterByCreditcardtype")
	public Set<Billsettlement> getBillsettlementsForCreditcardtype() {
		return this.billsettlementsForCreditcardtype;
	}

	public void setBillsettlementsForCreditcardtype(
			Set<Billsettlement> billsettlementsForCreditcardtype) {
		this.billsettlementsForCreditcardtype = billsettlementsForCreditcardtype;
	}

}