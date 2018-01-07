package com.leonia.wellness.entity;

// Generated 9 Aug, 2016 6:02:31 PM by Hibernate Tools 4.3.1

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
 * Billmaster generated by hbm2java
 */
@Entity
@Table(name = "billmaster")
public class Billmaster implements java.io.Serializable {

	private int billno;
	private Guestmaster guestmaster;
	private int sno;
	private BigDecimal amount;
	private BigDecimal outstandingamount;
	private boolean settled;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Date billdate;
	private BigDecimal tipamount;
	private BigDecimal discountamount;
	private Set<Feedbackformmaster> feedbackformmasters = new HashSet<Feedbackformmaster>(
			0);
	private Set<Billsettlement> billsettlements = new HashSet<Billsettlement>(0);
	private Set<Ticketmaster> ticketmasters = new HashSet<Ticketmaster>(0);
	private Set<Billdetails> billdetailses = new HashSet<Billdetails>(0);
	private Set<Productsales> productsaleses = new HashSet<Productsales>(0);
	private Set<Discountmaster> discountmasters = new HashSet<Discountmaster>(0);

	public Billmaster() {
	}

	public Billmaster(int billno, Guestmaster guestmaster, int sno,
			BigDecimal amount, BigDecimal outstandingamount, boolean settled,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, Date billdate) {
		this.billno = billno;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.amount = amount;
		this.outstandingamount = outstandingamount;
		this.settled = settled;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.billdate = billdate;
	}

	public Billmaster(int billno, Guestmaster guestmaster, int sno,
			BigDecimal amount, BigDecimal outstandingamount, boolean settled,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, Date billdate, BigDecimal tipamount,
			BigDecimal discountamount,
			Set<Feedbackformmaster> feedbackformmasters,
			Set<Billsettlement> billsettlements,
			Set<Ticketmaster> ticketmasters, Set<Billdetails> billdetailses,
			Set<Productsales> productsaleses,
			Set<Discountmaster> discountmasters) {
		this.billno = billno;
		this.guestmaster = guestmaster;
		this.sno = sno;
		this.amount = amount;
		this.outstandingamount = outstandingamount;
		this.settled = settled;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.billdate = billdate;
		this.tipamount = tipamount;
		this.discountamount = discountamount;
		this.feedbackformmasters = feedbackformmasters;
		this.billsettlements = billsettlements;
		this.ticketmasters = ticketmasters;
		this.billdetailses = billdetailses;
		this.productsaleses = productsaleses;
		this.discountmasters = discountmasters;
	}

	@Id
	@Column(name = "billno", unique = true, nullable = false)
	public int getBillno() {
		return this.billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guestid", nullable = false)
	public Guestmaster getGuestmaster() {
		return this.guestmaster;
	}

	public void setGuestmaster(Guestmaster guestmaster) {
		this.guestmaster = guestmaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "amount", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "outstandingamount", nullable = false, precision = 15)
	public BigDecimal getOutstandingamount() {
		return this.outstandingamount;
	}

	public void setOutstandingamount(BigDecimal outstandingamount) {
		this.outstandingamount = outstandingamount;
	}

	@Column(name = "settled", nullable = false)
	public boolean isSettled() {
		return this.settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "billdate", nullable = false, length = 13)
	public Date getBilldate() {
		return this.billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	@Column(name = "tipamount", precision = 15)
	public BigDecimal getTipamount() {
		return this.tipamount;
	}

	public void setTipamount(BigDecimal tipamount) {
		this.tipamount = tipamount;
	}

	@Column(name = "discountamount", precision = 15)
	public BigDecimal getDiscountamount() {
		return this.discountamount;
	}

	public void setDiscountamount(BigDecimal discountamount) {
		this.discountamount = discountamount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Feedbackformmaster> getFeedbackformmasters() {
		return this.feedbackformmasters;
	}

	public void setFeedbackformmasters(
			Set<Feedbackformmaster> feedbackformmasters) {
		this.feedbackformmasters = feedbackformmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Billsettlement> getBillsettlements() {
		return this.billsettlements;
	}

	public void setBillsettlements(Set<Billsettlement> billsettlements) {
		this.billsettlements = billsettlements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Ticketmaster> getTicketmasters() {
		return this.ticketmasters;
	}

	public void setTicketmasters(Set<Ticketmaster> ticketmasters) {
		this.ticketmasters = ticketmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Billdetails> getBilldetailses() {
		return this.billdetailses;
	}

	public void setBilldetailses(Set<Billdetails> billdetailses) {
		this.billdetailses = billdetailses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Productsales> getProductsaleses() {
		return this.productsaleses;
	}

	public void setProductsaleses(Set<Productsales> productsaleses) {
		this.productsaleses = productsaleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billmaster")
	public Set<Discountmaster> getDiscountmasters() {
		return this.discountmasters;
	}

	public void setDiscountmasters(Set<Discountmaster> discountmasters) {
		this.discountmasters = discountmasters;
	}

}
