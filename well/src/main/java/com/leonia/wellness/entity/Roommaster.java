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
 * Roommaster generated by hbm2java
 */
@Entity
@Table(name = "roommaster")
public class Roommaster implements java.io.Serializable {

	private int roomid;
	private int sno;
	private int roomno;
	private String description;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Appointmentservicedetails> appointmentservicedetailses = new HashSet<Appointmentservicedetails>(
			0);
	private Set<Serviceroommaster> serviceroommasters = new HashSet<Serviceroommaster>(
			0);

	public Roommaster() {
	}

	public Roommaster(int roomid, int sno, int roomno, boolean valid,
			String updatedby, Date updateddate, String updatedip) {
		this.roomid = roomid;
		this.sno = sno;
		this.roomno = roomno;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Roommaster(int roomid, int sno, int roomno, String description,
			boolean valid, String updatedby, Date updateddate,
			String updatedip,
			Set<Appointmentservicedetails> appointmentservicedetailses,
			Set<Serviceroommaster> serviceroommasters) {
		this.roomid = roomid;
		this.sno = sno;
		this.roomno = roomno;
		this.description = description;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.appointmentservicedetailses = appointmentservicedetailses;
		this.serviceroommasters = serviceroommasters;
	}

	@Id
	@Column(name = "roomid", unique = true, nullable = false)
	public int getRoomid() {
		return this.roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "roomno", nullable = false)
	public int getRoomno() {
		return this.roomno;
	}

	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

	@Column(name = "description")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roommaster")
	public Set<Appointmentservicedetails> getAppointmentservicedetailses() {
		return this.appointmentservicedetailses;
	}

	public void setAppointmentservicedetailses(
			Set<Appointmentservicedetails> appointmentservicedetailses) {
		this.appointmentservicedetailses = appointmentservicedetailses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roommaster")
	public Set<Serviceroommaster> getServiceroommasters() {
		return this.serviceroommasters;
	}

	public void setServiceroommasters(Set<Serviceroommaster> serviceroommasters) {
		this.serviceroommasters = serviceroommasters;
	}

}
