package com.leonia.wellness.entity;

// Generated 8 Aug, 2016 12:23:20 PM by Hibernate Tools 4.3.1

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
 * Responsemaster generated by hbm2java
 */
@Entity
@Table(name = "responsemaster")
public class Responsemaster implements java.io.Serializable {

	private int responseid;
	private int sno;
	private String responsedescription;
	private int responsescore;
	private int responseorder;
	private boolean confirmationrequired;
	private boolean ticketgenerated;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Ticketmaster> ticketmasters = new HashSet<Ticketmaster>(0);
	private Set<Feedbackdetails> feedbackdetailses = new HashSet<Feedbackdetails>(
			0);

	public Responsemaster() {
	}

	public Responsemaster(int responseid, int sno, String responsedescription,
			int responsescore, int responseorder, boolean confirmationrequired,
			boolean ticketgenerated, boolean valid, String updatedby,
			Date updateddate, String updatedip) {
		this.responseid = responseid;
		this.sno = sno;
		this.responsedescription = responsedescription;
		this.responsescore = responsescore;
		this.responseorder = responseorder;
		this.confirmationrequired = confirmationrequired;
		this.ticketgenerated = ticketgenerated;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Responsemaster(int responseid, int sno, String responsedescription,
			int responsescore, int responseorder, boolean confirmationrequired,
			boolean ticketgenerated, boolean valid, String updatedby,
			Date updateddate, String updatedip,
			Set<Ticketmaster> ticketmasters,
			Set<Feedbackdetails> feedbackdetailses) {
		this.responseid = responseid;
		this.sno = sno;
		this.responsedescription = responsedescription;
		this.responsescore = responsescore;
		this.responseorder = responseorder;
		this.confirmationrequired = confirmationrequired;
		this.ticketgenerated = ticketgenerated;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.ticketmasters = ticketmasters;
		this.feedbackdetailses = feedbackdetailses;
	}

	@Id
	@Column(name = "responseid", unique = true, nullable = false)
	public int getResponseid() {
		return this.responseid;
	}

	public void setResponseid(int responseid) {
		this.responseid = responseid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "responsedescription", nullable = false, length = 150)
	public String getResponsedescription() {
		return this.responsedescription;
	}

	public void setResponsedescription(String responsedescription) {
		this.responsedescription = responsedescription;
	}

	@Column(name = "responsescore", nullable = false)
	public int getResponsescore() {
		return this.responsescore;
	}

	public void setResponsescore(int responsescore) {
		this.responsescore = responsescore;
	}

	@Column(name = "responseorder", nullable = false)
	public int getResponseorder() {
		return this.responseorder;
	}

	public void setResponseorder(int responseorder) {
		this.responseorder = responseorder;
	}

	@Column(name = "confirmationrequired", nullable = false)
	public boolean isConfirmationrequired() {
		return this.confirmationrequired;
	}

	public void setConfirmationrequired(boolean confirmationrequired) {
		this.confirmationrequired = confirmationrequired;
	}

	@Column(name = "ticketgenerated", nullable = false)
	public boolean isTicketgenerated() {
		return this.ticketgenerated;
	}

	public void setTicketgenerated(boolean ticketgenerated) {
		this.ticketgenerated = ticketgenerated;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsemaster")
	public Set<Ticketmaster> getTicketmasters() {
		return this.ticketmasters;
	}

	public void setTicketmasters(Set<Ticketmaster> ticketmasters) {
		this.ticketmasters = ticketmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsemaster")
	public Set<Feedbackdetails> getFeedbackdetailses() {
		return this.feedbackdetailses;
	}

	public void setFeedbackdetailses(Set<Feedbackdetails> feedbackdetailses) {
		this.feedbackdetailses = feedbackdetailses;
	}

}