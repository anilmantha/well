package com.leonia.wellness.idao;

import java.util.List;
import java.util.Set;

import com.leonia.wellness.dto.ServiceStockQuantity;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;

public interface IServicesDAO {

	Integer getstockid(String stockname);

	void saveservicestock(List<ServiceStockQuantity> objectArray);

	Set<Servicestocksmaster> getStockUsage(Integer serviceId);

	void updateservicestock(List<ServiceStockQuantity> objectArray);

	void deleteservicestock(List<ServiceStockQuantity> objectArray);

	List<Servicemaster> getServicesUnAddedStock();

	List<Servicemaster> getServicesToBeEdited();
	
	/*public List<Services> getServices();

	public List<Services> getServicesDetails(String service);*/
}
