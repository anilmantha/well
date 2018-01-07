package com.leonia.wellness.entity;
// Generated Aug 17, 2016 4:12:57 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Taxstructuremaster generated by hbm2java
 */
@Entity
@Table(name = "taxstructuremaster")
public class Taxstructuremaster implements java.io.Serializable {

	private int sno;
	private int taxstructureid;
	private String taxstructuredescription;
	private Date applicabledate;
	private boolean valid;
	private String updatedby;
	private Date updateddate;
	private String updatedip;
	private Set<Taxstructuredetails> taxstructuredetailses = new HashSet<Taxstructuredetails>(0);
	private Set<Packageratemaster> packageratemasters = new HashSet<Packageratemaster>(0);
	private Set<Serviceratemaster> serviceratemasters = new HashSet<Serviceratemaster>(0);
	private Set<Productratemaster> productratemasters = new HashSet<Productratemaster>(0);
	private Set<Billdetails> billdetailses = new HashSet<Billdetails>(0);

	public Taxstructuremaster() {
	}

	public Taxstructuremaster(int sno, int taxstructureid, String taxstructuredescription, Date applicabledate,
			boolean valid, String updatedby, Date updateddate, String updatedip) {
		this.sno = sno;
		this.taxstructureid = taxstructureid;
		this.taxstructuredescription = taxstructuredescription;
		this.applicabledate = applicabledate;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
	}

	public Taxstructuremaster(int sno, int taxstructureid, String taxstructuredescription, Date applicabledate,
			boolean valid, String updatedby, Date updateddate, String updatedip,
			Set<Taxstructuredetails> taxstructuredetailses, Set<Packageratemaster> packageratemasters,
			Set<Serviceratemaster> serviceratemasters, Set<Productratemaster> productratemasters,
			Set<Billdetails> billdetailses) {
		this.sno = sno;
		this.taxstructureid = taxstructureid;
		this.taxstructuredescription = taxstructuredescription;
		this.applicabledate = applicabledate;
		this.valid = valid;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
		this.updatedip = updatedip;
		this.taxstructuredetailses = taxstructuredetailses;
		this.packageratemasters = packageratemasters;
		this.serviceratemasters = serviceratemasters;
		this.productratemasters = productratemasters;
		this.billdetailses = billdetailses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno", unique = true, insertable = false, nullable = false)
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Column(name = "taxstructureid", nullable = false)
	public int getTaxstructureid() {
		return this.taxstructureid;
	}

	public void setTaxstructureid(int taxstructureid) {
		this.taxstructureid = taxstructureid;
	}

	@Column(name = "taxstructuredescription", nullable = false)
	public String getTaxstructuredescription() {
		return this.taxstructuredescription;
	}

	public void setTaxstructuredescription(String taxstructuredescription) {
		this.taxstructuredescription = taxstructuredescription;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "applicabledate", nullable = false, length = 13)
	public Date getApplicabledate() {
		return this.applicabledate;
	}

	public void setApplicabledate(Date applicabledate) {
		this.applicabledate = applicabledate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxstructuremaster")
	public Set<Taxstructuredetails> getTaxstructuredetailses() {
		return this.taxstructuredetailses;
	}

	public void setTaxstructuredetailses(Set<Taxstructuredetails> taxstructuredetailses) {
		this.taxstructuredetailses = taxstructuredetailses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxstructuremaster")
	public Set<Packageratemaster> getPackageratemasters() {
		return this.packageratemasters;
	}

	public void setPackageratemasters(Set<Packageratemaster> packageratemasters) {
		this.packageratemasters = packageratemasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxstructuremaster")
	public Set<Serviceratemaster> getServiceratemasters() {
		return this.serviceratemasters;
	}

	public void setServiceratemasters(Set<Serviceratemaster> serviceratemasters) {
		this.serviceratemasters = serviceratemasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxstructuremaster")
	public Set<Productratemaster> getProductratemasters() {
		return this.productratemasters;
	}

	public void setProductratemasters(Set<Productratemaster> productratemasters) {
		this.productratemasters = productratemasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxstructuremaster")
	public Set<Billdetails> getBilldetailses() {
		return this.billdetailses;
	}

	public void setBilldetailses(Set<Billdetails> billdetailses) {
		this.billdetailses = billdetailses;
	}

}
