package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Appointmentservicedetails;
//import com.leonia.wellness.entity.Customers;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.idao.ICustomersDAO;
//import com.leonia.wellness.entity.RegisterUser;
import com.leonia.wellness.iservice.ICustomersService;

@Service
public class CustomersServiceImpl implements ICustomersService{

	@Autowired
	private ICustomersDAO iCustomersDAO;

	@Override
	public void saveCustomerDetails(Guestmaster guestMaster) {
		iCustomersDAO.saveCustomerRegistration(guestMaster);
	}
	
	@Override
	public List<Guestmaster> getAppointements() {
		return iCustomersDAO.getAppointements();
	}
	

	@Override
	public void saveGuestDetails(Guestmaster guestmaster) {
			
	}

	@Override
	public List getGuestSearch(String name) {
		return iCustomersDAO.getGuestSearch(name);
	}

	@Override
	public Map<Integer, String> getSegment() {
		
		return iCustomersDAO.getSegment();
	}

	@Override
	public Map<Integer, String> getCorporate() {
		
		return iCustomersDAO.getCorporate();
	}

	@Override
	public Map<Integer, String> getBusinessSource() {
		
		return iCustomersDAO.getBusinessSource();
	}

	@Override
	public Map<Integer, String> getBillingInformation() {
		
		return iCustomersDAO.getBillingInformation();
	}

	@Override
	public Map<Integer, String> getCity() {
		
		return iCustomersDAO.getCity();
	}

	@Override
	public Map<Integer, String> getState() {
		
		return iCustomersDAO.getState();
	}

	@Override
	public Map<Integer, String> getCountry() {
		
		return iCustomersDAO.getCountry();
	}

	@Override
	public Map<Integer, String> getTitle() {
		
		return iCustomersDAO.getTitle();
	}
	
	
	
	@Override
	public List<Guestmaster> getSearchCustomerDOB(String month, String fromdate, String todate) {
		// TODO Auto-generated method stub
		return iCustomersDAO.getSearchCustomerDOB(month,fromdate,todate);
	}

	@Override
	public List<Guestmaster> getSearchGuestWedding(String month, String fromdate, String todate) {
		
		return iCustomersDAO.getSearchCustomerWedding(month, fromdate, todate);
	}
	
	@Override
	public List<Appointmentservicedetails> getGuestServicesSearch(String fromdate, String todate, String[] servicename) {
		return iCustomersDAO.getGuestServicesSearch(fromdate, todate, servicename);
	}

	@Override
	public List getGuestEmailList() {
	
		return iCustomersDAO.getGuestEmailList();
	}

}
