package com.leonia.wellness.idao;

import java.util.List;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.entity.Appointmentstockconsumption;

public interface IDoctorAdviceDAO {
	
	List<AppointmentService> doctrAdviceList();
	
	//List doctrAdvice(String appId);
	List clientInfo(String appId);
	List fetchStockNames();
	List getStockDetails(String stockname);
	int deleteStock(String id);
	//int addStock(Integer appid, Integer srvid, Integer stkid, Double stockreq);

	int addStock(List<Appointmentstockconsumption> objArry);

	List getServiceName(Integer srvid);

	List getSearchAppointements(String name);

	List doctrAdvice(String appId, String srvId);
	List<AppointmentService> searchdoctrAdviceList(String guestname);
}
