package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.idao.ILoadOnStartupDAO;
import com.leonia.wellness.iservice.ILoadOnStartupService;

@Service
public class LoadOnStartupServiceImpl implements ILoadOnStartupService {

	@Autowired
	private ILoadOnStartupDAO iloadOnStartupDao;

	@Override
	public List<String> getStockGroup() {
		return iloadOnStartupDao.getStockGroup();
	}

	@Override
	public Set<Dropdowndetails> getstockType() {
		return iloadOnStartupDao.getstockType();
	}

	@Override
	public List<Roommaster> getRooms() {
		return iloadOnStartupDao.getRooms();
	}

	@Override
	public List<Servicemaster> getServices() {
		return iloadOnStartupDao.getServices();
	}

	@Override
	public List<String> getStockDetails() {
		return iloadOnStartupDao.getStockDetails();
	}
	
	@Override
	public List<Staffmaster> getStaffNames() {
		return iloadOnStartupDao.getStaffNames();
	}
	
	@Override
	public Map<Integer, String> getSegment() {
		
		return iloadOnStartupDao.getSegment();
	}

	@Override
	public Map<Integer, String> getCorporate() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getCorporate();
	}


	@Override
	public Map<Integer, String> getBusinessSource() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getBusinessSource();
	}


	@Override
	public Map<Integer, String> getBillingInformation() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getBillingInformation();
	}


	@Override
	public Map<Integer, String> getState() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getState();
	}


	@Override
	public Map<Integer, String> getCity() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getCity();
	}


	@Override
	public Map<Integer, String> getCountry() {
		// TODO Auto-generated method stub
		return iloadOnStartupDao.getCountry();
	}
	@Override
	public Map<Integer, String> getTitle() {
		
		return iloadOnStartupDao.getTitle();
	}


	@Override
	public Map<Integer, String> getGender() {
	
		return iloadOnStartupDao.getGender();
	}


	@Override
	public Map<Integer, String> getSupplier() {
		
		return iloadOnStartupDao.getSupplier();
	}


	@Override
	public Map<Integer, String> getManufacturer() {
		
		return iloadOnStartupDao.getManufacturer();
	}


	@Override
	public Map<Integer, String> getStockType() {
		
		return iloadOnStartupDao.getStockType();
	}

	@Override
	public List<Paymodemaster> paymodeDetails() {
		
		return iloadOnStartupDao.paymodeDetails();
	}

	@Override
	public List<Creditlistmaster> corporateDetails() {
		
		return iloadOnStartupDao.corporateDetails();
	}

	@Override
	public List<Membershipmaster> memberDetails() {
		
		return iloadOnStartupDao.memberDetails();
	}

	@Override
	public List<Departmentmaster> departmentDetails() {
		
		return iloadOnStartupDao.departmentDetails();
	}

	@Override
	public List<Cardtypemaster> getCardTypes() {
		
		return iloadOnStartupDao.getCardTypes();
	}

	@Override
	public List<Appointment> getGuestList() {
		return iloadOnStartupDao.getGuestList();
	}


	@Override
	public Map<Integer, String> getMonth() {
		
		return iloadOnStartupDao.getMonth();
	}

	@Override
	public List<Reasondetails> getReasonsList() {
		return iloadOnStartupDao.getReasonsList();
	}

}
