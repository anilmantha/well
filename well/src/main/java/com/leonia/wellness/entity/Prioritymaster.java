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
 * Prioritymaster generated by hbm2java
 */
@Entity
@Table(name = "prioritymaster")
public class Prioritymaster implements java.io.Serializable {

	private int priorityid;
	private int sno;
	private String prioritydescription;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Ticketmaster> ticketmasters = new HashSet<Ticketmaster>(0);

	public Prioritymaster() {
	}

	public Prioritymaster(int priorityid, int sno, String prioritydescription,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.priorityid = priorityid;
		this.sno = sno;
		this.prioritydescription = prioritydescription;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Prioritymaster(int priorityid, int sno, String prioritydescription,
			boolean valid, String updatedby, Date updateddate,
			String updatedip, Set<Ticketmaster> ticketmasters) {
		this.priorityid = priorityid;
		this.sno = sno;
		this.prioritydescription = prioritydescription;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.ticketmasters = ticketmasters;
	}

	@Id
	@Column(name = "priorityid", unique = true, nullable = false)
	public int getPriorityid() {
		return this.priorityid;
	}

	public void setPriorityid(int priorityid) {
		this.priorityid = priorityid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "prioritydescription", nullable = false, length = 150)
	public String getPrioritydescription() {
		return this.prioritydescription;
	}

	public void setPrioritydescription(String prioritydescription) {
		this.prioritydescription = prioritydescription;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prioritymaster")
	public Set<Ticketmaster> getTicketmasters() {
		return this.ticketmasters;
	}

	public void setTicketmasters(Set<Ticketmaster> ticketmasters) {
		this.ticketmasters = ticketmasters;
	}

}