package com.leonia.wellness.idao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.entity.Reasondetails;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Servicemaster;

public interface ILoadOnStartupDAO {
	List<String> getStockGroup();

	Set<Dropdowndetails> getstockType();

	List<Roommaster> getRooms();

	List<String> getStockDetails();
	
	List<Servicemaster> getServices();
	
	Map<Integer, String> getSegment();

	Map<Integer, String> getCorporate();

	Map<Integer, String> getBusinessSource();

	Map<Integer, String> getBillingInformation();

	Map<Integer, String> getState();

	Map<Integer, String> getCity();

	Map<Integer, String> getCountry();

	public Map<Integer, String> getTitle();

	Map<Integer, String> getGender();

	Map<Integer, String> getSupplier();

	Map<Integer, String> getManufacturer();

	Map<Integer, String> getStockType();
	
	List getStaffNames();

	List<Paymodemaster> paymodeDetails();

	List<Creditlistmaster> corporateDetails();

	List<Membershipmaster> memberDetails();

	List<Departmentmaster> departmentDetails();

	List<Cardtypemaster> getCardTypes();

	List<Appointment> getGuestList();
	Map<Integer, String> getMonth();

	List<Reasondetails> getReasonsList();
	

	
	
	
	

}
