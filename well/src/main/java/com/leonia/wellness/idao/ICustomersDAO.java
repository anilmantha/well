package com.leonia.wellness.idao;

import java.util.List;
import java.util.Map;

import com.leonia.wellness.entity.Appointmentservicedetails;
//import com.leonia.wellness.entity.Customers;
import com.leonia.wellness.entity.Guestmaster;
//import com.leonia.wellness.entity.RegisterUser;

public interface ICustomersDAO {
	
	public void saveCustomerRegistration(Guestmaster guestMaster);
	public List<Guestmaster> getAppointements();
	public void saveGuestDetails(Guestmaster guestmaster);
	public List getGuestSearch(String name);
	public Map<Integer, String> getSegment();
	public Map<Integer, String> getCorporate();
	public Map<Integer, String> getBusinessSource();
	public Map<Integer, String> getBillingInformation();
	public Map<Integer, String> getCity();
	public Map<Integer, String> getState();
	public Map<Integer, String> getCountry();
	public Map<Integer, String> getTitle();
	public List<Guestmaster> getSearchCustomerDOB(String month, String fromdate, String todate);
	public List getSearchCustomerWedding(String month, String fromdate, String todate);
	public List<Appointmentservicedetails> getGuestServicesSearch(String fromdate, String todate, String[] servicename);
	public List getGuestEmailList();
}
