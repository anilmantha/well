package com.leonia.wellness.entity;
// Generated Aug 20, 2016 4:44:54 PM by Hibernate Tools 4.3.1.Final

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
 * Guestmaster generated by hbm2java
 */
@Entity
@Table(name = "guestmaster")
public class Guestmaster implements java.io.Serializable {

	private int guestid;
	private Billinginstructionmaster billinginstructionmaster;
	private Businesssourcemaster businesssourcemaster;
	private Citymaster citymaster;
	private Corporatemaster corporatemaster;
	private Countrymaster countrymaster;
	private Dropdowndetails dropdowndetailsByGenderid;
	private Dropdowndetails dropdowndetailsByTitleid;
	private Segmentmaster segmentmaster;
	private Statemaster statemaster;
	private int sno;
	private String name;
	private String email;
	private Date dob;
	private Long mobile;
	private String address;
	private Integer pincode;
	private String nationality;
	private Integer age;
	private String occupation;
	private String dndfrom;
	private String dndto;
	private String catogery;
	private Integer noofvisits;
	private BigDecimal revenue;
	private Date lastvisitdate;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Date weddingday;
	private Set<Guesthistoryrevenue> guesthistoryrevenues = new HashSet<Guesthistoryrevenue>(0);
	private Set<Ticketmaster> ticketmasters = new HashSet<Ticketmaster>(0);
	private Set<Guesthistoryservices> guesthistoryserviceses = new HashSet<Guesthistoryservices>(0);
	private Set<Guesthistorycomplaints> guesthistorycomplaintses = new HashSet<Guesthistorycomplaints>(0);
	private Set<Feedbackformmaster> feedbackformmasters = new HashSet<Feedbackformmaster>(0);
	private Set<Membershipmaster> membershipmasters = new HashSet<Membershipmaster>(0);
	private Set<Billmaster> billmasters = new HashSet<Billmaster>(0);
	private Set<Billsettlement> billsettlements = new HashSet<Billsettlement>(0);
	private Set<Appointment> appointments = new HashSet<Appointment>(0);

	public Guestmaster() {
	}

	public Guestmaster(int guestid, Dropdowndetails dropdowndetailsByGenderid, Dropdowndetails dropdowndetailsByTitleid,
			int sno, String name, boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.guestid = guestid;
		this.dropdowndetailsByGenderid = dropdowndetailsByGenderid;
		this.dropdowndetailsByTitleid = dropdowndetailsByTitleid;
		this.sno = sno;
		this.name = name;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Guestmaster(int guestid, Billinginstructionmaster billinginstructionmaster,
			Businesssourcemaster businesssourcemaster, Citymaster citymaster, Corporatemaster corporatemaster,
			Countrymaster countrymaster, Dropdowndetails dropdowndetailsByGenderid,
			Dropdowndetails dropdowndetailsByTitleid, Segmentmaster segmentmaster, Statemaster statemaster, int sno,
			String name, String email, Date dob, Long mobile, String address, Integer pincode, String nationality,
			Integer age, String occupation, String dndfrom, String dndto, String catogery, Integer noofvisits,
			BigDecimal revenue, Date lastvisitdate, boolean valid, String updatedby, Date updateddate, String updatedip,
			Date weddingday, Set<Guesthistoryrevenue> guesthistoryrevenues, Set<Ticketmaster> ticketmasters,
			Set<Guesthistoryservices> guesthistoryserviceses, Set<Guesthistorycomplaints> guesthistorycomplaintses,
			Set<Feedbackformmaster> feedbackformmasters, Set<Membershipmaster> membershipmasters,
			Set<Billmaster> billmasters, Set<Billsettlement> billsettlements, Set<Appointment> appointments) {
		this.guestid = guestid;
		this.billinginstructionmaster = billinginstructionmaster;
		this.businesssourcemaster = businesssourcemaster;
		this.citymaster = citymaster;
		this.corporatemaster = corporatemaster;
		this.countrymaster = countrymaster;
		this.dropdowndetailsByGenderid = dropdowndetailsByGenderid;
		this.dropdowndetailsByTitleid = dropdowndetailsByTitleid;
		this.segmentmaster = segmentmaster;
		this.statemaster = statemaster;
		this.sno = sno;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.mobile = mobile;
		this.address = address;
		this.pincode = pincode;
		this.nationality = nationality;
		this.age = age;
		this.occupation = occupation;
		this.dndfrom = dndfrom;
		this.dndto = dndto;
		this.catogery = catogery;
		this.noofvisits = noofvisits;
		this.revenue = revenue;
		this.lastvisitdate = lastvisitdate;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.weddingday = weddingday;
		this.guesthistoryrevenues = guesthistoryrevenues;
		this.ticketmasters = ticketmasters;
		this.guesthistoryserviceses = guesthistoryserviceses;
		this.guesthistorycomplaintses = guesthistorycomplaintses;
		this.feedbackformmasters = feedbackformmasters;
		this.membershipmasters = membershipmasters;
		this.billmasters = billmasters;
		this.billsettlements = billsettlements;
		this.appointments = appointments;
	}

	@Id

	@Column(name = "guestid", unique = true, nullable = false)
	public int getGuestid() {
		return this.guestid;
	}

	public void setGuestid(int guestid) {
		this.guestid = guestid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billinginstructionid")
	public Billinginstructionmaster getBillinginstructionmaster() {
		return this.billinginstructionmaster;
	}

	public void setBillinginstructionmaster(Billinginstructionmaster billinginstructionmaster) {
		this.billinginstructionmaster = billinginstructionmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "businesssourceid")
	public Businesssourcemaster getBusinesssourcemaster() {
		return this.businesssourcemaster;
	}

	public void setBusinesssourcemaster(Businesssourcemaster businesssourcemaster) {
		this.businesssourcemaster = businesssourcemaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cityid")
	public Citymaster getCitymaster() {
		return this.citymaster;
	}

	public void setCitymaster(Citymaster citymaster) {
		this.citymaster = citymaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corporateid")
	public Corporatemaster getCorporatemaster() {
		return this.corporatemaster;
	}

	public void setCorporatemaster(Corporatemaster corporatemaster) {
		this.corporatemaster = corporatemaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryid")
	public Countrymaster getCountrymaster() {
		return this.countrymaster;
	}

	public void setCountrymaster(Countrymaster countrymaster) {
		this.countrymaster = countrymaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genderid", nullable = false)
	public Dropdowndetails getDropdowndetailsByGenderid() {
		return this.dropdowndetailsByGenderid;
	}

	public void setDropdowndetailsByGenderid(Dropdowndetails dropdowndetailsByGenderid) {
		this.dropdowndetailsByGenderid = dropdowndetailsByGenderid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "titleid", nullable = false)
	public Dropdowndetails getDropdowndetailsByTitleid() {
		return this.dropdowndetailsByTitleid;
	}

	public void setDropdowndetailsByTitleid(Dropdowndetails dropdowndetailsByTitleid) {
		this.dropdowndetailsByTitleid = dropdowndetailsByTitleid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "segmentid")
	public Segmentmaster getSegmentmaster() {
		return this.segmentmaster;
	}

	public void setSegmentmaster(Segmentmaster segmentmaster) {
		this.segmentmaster = segmentmaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateid")
	public Statemaster getStatemaster() {
		return this.statemaster;
	}

	public void setStatemaster(Statemaster statemaster) {
		this.statemaster = statemaster;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "sno", insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "name", nullable = false, length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 13)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "mobile")
	public Long getMobile() {
		return this.mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "pincode")
	public Integer getPincode() {
		return this.pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Column(name = "nationality", length = 100)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "occupation", length = 100)
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "dndfrom", length = 100)
	public String getDndfrom() {
		return this.dndfrom;
	}

	public void setDndfrom(String dndfrom) {
		this.dndfrom = dndfrom;
	}

	@Column(name = "dndto", length = 100)
	public String getDndto() {
		return this.dndto;
	}

	public void setDndto(String dndto) {
		this.dndto = dndto;
	}

	@Column(name = "catogery", length = 200)
	public String getCatogery() {
		return this.catogery;
	}

	public void setCatogery(String catogery) {
		this.catogery = catogery;
	}

	@Column(name = "noofvisits")
	public Integer getNoofvisits() {
		return this.noofvisits;
	}

	public void setNoofvisits(Integer noofvisits) {
		this.noofvisits = noofvisits;
	}

	@Column(name = "revenue", precision = 15)
	public BigDecimal getRevenue() {
		return this.revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lastvisitdate", length = 13)
	public Date getLastvisitdate() {
		return this.lastvisitdate;
	}

	public void setLastvisitdate(Date lastvisitdate) {
		this.lastvisitdate = lastvisitdate;
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
	@Column(name = "weddingday", length = 13)
	public Date getWeddingday() {
		return this.weddingday;
	}

	public void setWeddingday(Date weddingday) {
		this.weddingday = weddingday;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Guesthistoryrevenue> getGuesthistoryrevenues() {
		return this.guesthistoryrevenues;
	}

	public void setGuesthistoryrevenues(Set<Guesthistoryrevenue> guesthistoryrevenues) {
		this.guesthistoryrevenues = guesthistoryrevenues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Ticketmaster> getTicketmasters() {
		return this.ticketmasters;
	}

	public void setTicketmasters(Set<Ticketmaster> ticketmasters) {
		this.ticketmasters = ticketmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Guesthistoryservices> getGuesthistoryserviceses() {
		return this.guesthistoryserviceses;
	}

	public void setGuesthistoryserviceses(Set<Guesthistoryservices> guesthistoryserviceses) {
		this.guesthistoryserviceses = guesthistoryserviceses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Guesthistorycomplaints> getGuesthistorycomplaintses() {
		return this.guesthistorycomplaintses;
	}

	public void setGuesthistorycomplaintses(Set<Guesthistorycomplaints> guesthistorycomplaintses) {
		this.guesthistorycomplaintses = guesthistorycomplaintses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Feedbackformmaster> getFeedbackformmasters() {
		return this.feedbackformmasters;
	}

	public void setFeedbackformmasters(Set<Feedbackformmaster> feedbackformmasters) {
		this.feedbackformmasters = feedbackformmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Membershipmaster> getMembershipmasters() {
		return this.membershipmasters;
	}

	public void setMembershipmasters(Set<Membershipmaster> membershipmasters) {
		this.membershipmasters = membershipmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Billmaster> getBillmasters() {
		return this.billmasters;
	}

	public void setBillmasters(Set<Billmaster> billmasters) {
		this.billmasters = billmasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Billsettlement> getBillsettlements() {
		return this.billsettlements;
	}

	public void setBillsettlements(Set<Billsettlement> billsettlements) {
		this.billsettlements = billsettlements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guestmaster")
	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

}
