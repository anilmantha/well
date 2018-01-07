package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.ServiceStockQuantity;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.idao.IServicesDAO;
import com.leonia.wellness.iservice.IServicesService;

@Service
public class ServicesServiceImpl implements  IServicesService {

	
	@Autowired
	private IServicesDAO iServicesDao;

	@Override
	public Integer getstockid(String stockname) {
		return iServicesDao.getstockid(stockname);
	}

	@Override
	public void saveservicestock(List<ServiceStockQuantity> objectArray){
		iServicesDao.saveservicestock(objectArray);
		
	}

	@Override
	public Set<Servicestocksmaster> getStockUsage(Integer serviceId) {
		return iServicesDao.getStockUsage(serviceId);
	}

	@Override
	public void updateservicestock(List<ServiceStockQuantity> objectArray) {
		
		iServicesDao.updateservicestock(objectArray);
	}

	@Override
	public void deleteservicestock(List<ServiceStockQuantity> objectArray) {
		iServicesDao.deleteservicestock(objectArray);
		
	}

	@Override
	public List<Servicemaster> getServicesUnAddedStock() {
		return iServicesDao.getServicesUnAddedStock();
	}

	@Override
	public List<Servicemaster> getServicesToBeEdited() {
		return iServicesDao.getServicesToBeEdited();
	}


	
	/*@Override
	public List<Services> getServices() {
		// TODO Auto-generated method stub
		return servicesDao.getServices();
	}



	@Override
	public List<Services> getServicesDetails(String service) {
		// TODO Auto-generated method stub
		return servicesDao.getServicesDetails(service);
	}*/

}
