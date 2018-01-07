package com.leonia.wellness.dto;

import java.util.Date;

public class AppointmentService {

	private Integer appointmentId;
	private Integer guestId;
	private Integer staffId;
	private Integer serviceId;
	private String serviceName;
	private String guestName;
	private String staffName;
	private String serviceStatus;
	private Date dateOfAppontment;
	private String startTime;
	private String endTime;
	private String appointmentStatus;
	private boolean doctoradvice;
	
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Integer getGuestId() {
		return guestId;
	}
	public void setGuestId(Integer guestId) {
		this.guestId = guestId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public Date getDateOfAppontment() {
		return dateOfAppontment;
	}
	public void setDateOfAppontment(Date dateOfAppontment) {
		this.dateOfAppontment = dateOfAppontment;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public boolean isDoctoradvice() {
		return doctoradvice;
	}
	public void setDoctoradvice(boolean doctoradvice) {
		this.doctoradvice = doctoradvice;
	}
}
