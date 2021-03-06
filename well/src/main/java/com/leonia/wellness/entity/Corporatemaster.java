package com.leonia.wellness.entity;

// Generated 12 Jul, 2016 2:06:04 PM by Hibernate Tools 4.3.1

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
 * Corporatemaster generated by hbm2java
 */
@Entity
@Table(name = "corporatemaster")
public class Corporatemaster implements java.io.Serializable {

	private int corporateid;
	private Corporatetypemaster corporatetypemaster;
	private int sno;
	private String corporatename;
	private String description;
	private String contactperson1;
	private String address1;
	private Long phone1;
	private Long fax1;
	private String email1;
	private String contactperson2;
	private String address2;
	private Long phone2;
	private Long fax2;
	private String email2;
	private String contactperson3;
	private String address3;
	private Long phone3;
	private Long fax3;
	private String email3;
	private String remarks;
	private Boolean blacklist;
	private String blacklistreason;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Appointment> appointments = new HashSet<Appointment>(0);
	private Set<Creditlistmaster> creditlistmasters = new HashSet<Creditlistmaster>(
			0);
	private Set<Guestmaster> guestmasters = new HashSet<Guestmaster>(0);

	public Corporatemaster() {
	}

	public Corporatemaster(int corporateid,
			Corporatetypemaster corporatetypemaster, int sno,
			String corporatename, boolean valid, String updatedby,
			Date updateddate, String updatedip) {
		this.corporateid = corporateid;
		this.corporatetypemaster = corporatetypemaster;
		this.sno = sno;
		this.corporatename = corporatename;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Corporatemaster(int corporateid,
			Corporatetypemaster corporatetypemaster, int sno,
			String corporatename, String description, String contactperson1,
			String address1, Long phone1, Long fax1, String email1,
			String contactperson2, String address2, Long phone2, Long fax2,
			String email2, String contactperson3, String address3, Long phone3,
			Long fax3, String email3, String remarks, Boolean blacklist,
			String blacklistreason, boolean valid, String updatedby,
			Date updateddate, String updatedip, Set<Appointment> appointments,
			Set<Creditlistmaster> creditlistmasters,
			Set<Guestmaster> guestmasters) {
		this.corporateid = corporateid;
		this.corporatetypemaster = corporatetypemaster;
		this.sno = sno;
		this.corporatename = corporatename;
		this.description = description;
		this.contactperson1 = contactperson1;
		this.address1 = address1;
		this.phone1 = phone1;
		this.fax1 = fax1;
		this.email1 = email1;
		this.contactperson2 = contactperson2;
		this.address2 = address2;
		this.phone2 = phone2;
		this.fax2 = fax2;
		this.email2 = email2;
		this.contactperson3 = contactperson3;
		this.address3 = address3;
		this.phone3 = phone3;
		this.fax3 = fax3;
		this.email3 = email3;
		this.remarks = remarks;
		this.blacklist = blacklist;
		this.blacklistreason = blacklistreason;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.appointments = appointments;
		this.creditlistmasters = creditlistmasters;
		this.guestmasters = guestmasters;
	}

	@Id
	@Column(name = "corporateid", unique = true, nullable = false)
	public int getCorporateid() {
		return this.corporateid;
	}

	public void setCorporateid(int corporateid) {
		this.corporateid = corporateid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corporatetypeid", nullable = false)
	public Corporatetypemaster getCorporatetypemaster() {
		return this.corporatetypemaster;
	}

	public void setCorporatetypemaster(Corporatetypemaster corporatetypemaster) {
		this.corporatetypemaster = corporatetypemaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "corporatename", nullable = false, length = 200)
	public String getCorporatename() {
		return this.corporatename;
	}

	public void setCorporatename(String corporatename) {
		this.corporatename = corporatename;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "contactperson1", length = 150)
	public String getContactperson1() {
		return this.contactperson1;
	}

	public void setContactperson1(String contactperson1) {
		this.contactperson1 = contactperson1;
	}

	@Column(name = "address1")
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "phone1")
	public Long getPhone1() {
		return this.phone1;
	}

	public void setPhone1(Long phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "fax1")
	public Long getFax1() {
		return this.fax1;
	}

	public void setFax1(Long fax1) {
		this.fax1 = fax1;
	}

	@Column(name = "email1", length = 150)
	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	@Column(name = "contactperson2", length = 150)
	public String getContactperson2() {
		return this.contactperson2;
	}

	public void setContactperson2(String contactperson2) {
		this.contactperson2 = contactperson2;
	}

	@Column(name = "address2")
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "phone2")
	public Long getPhone2() {
		return this.phone2;
	}

	public void setPhone2(Long phone2) {
		this.phone2 = phone2;
	}

	@Column(name = "fax2")
	public Long getFax2() {
		return this.fax2;
	}

	public void setFax2(Long fax2) {
		this.fax2 = fax2;
	}

	@Column(name = "email2", length = 150)
	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	@Column(name = "contactperson3", length = 150)
	public String getContactperson3() {
		return this.contactperson3;
	}

	public void setContactperson3(String contactperson3) {
		this.contactperson3 = contactperson3;
	}

	@Column(name = "address3")
	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Column(name = "phone3")
	public Long getPhone3() {
		return this.phone3;
	}

	public void setPhone3(Long phone3) {
		this.phone3 = phone3;
	}

	@Column(name = "fax3")
	public Long getFax3() {
		return this.fax3;
	}

	public void setFax3(Long fax3) {
		this.fax3 = fax3;
	}

	@Column(name = "email3", length = 150)
	public String getEmail3() {
		return this.email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "blacklist")
	public Boolean getBlacklist() {
		return this.blacklist;
	}

	public void setBlacklist(Boolean blacklist) {
		this.blacklist = blacklist;
	}

	@Column(name = "blacklistreason")
	public String getBlacklistreason() {
		return this.blacklistreason;
	}

	public void setBlacklistreason(String blacklistreason) {
		this.blacklistreason = blacklistreason;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corporatemaster")
	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corporatemaster")
	public Set<Creditlistmaster> getCreditlistmasters() {
		return this.creditlistmasters;
	}

	public void setCreditlistmasters(Set<Creditlistmaster> creditlistmasters) {
		this.creditlistmasters = creditlistmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corporatemaster")
	public Set<Guestmaster> getGuestmasters() {
		return this.guestmasters;
	}

	public void setGuestmasters(Set<Guestmaster> guestmasters) {
		this.guestmasters = guestmasters;
	}

}
