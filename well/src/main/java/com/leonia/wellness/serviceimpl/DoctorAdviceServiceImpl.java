package com.leonia.wellness.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.idao.IDoctorAdviceDAO;
import com.leonia.wellness.iservice.IDoctorAdviceService;

@Service
public class DoctorAdviceServiceImpl implements IDoctorAdviceService {
	
	@Autowired
	private IDoctorAdviceDAO doctorAdviceDAO;

	@Override
	public List<AppointmentService> doctrAdviceList() {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.doctrAdviceList();
	}

	@Override
	public List doctrAdvice(String appId,String srvId) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.doctrAdvice(appId,srvId);
	}

	@Override
	public List clientInfo(String appId) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.clientInfo(appId);
	}

	@Override
	public List fetchStockNames() {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.fetchStockNames();
	}

	@Override
	public List getStockDetails(String stockname) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.getStockDetails(stockname);
	}

	@Override
	public int deleteStock(String id) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.deleteStock(id);
	}

	/*@Override
	public int addStock(Integer appid, Integer srvid, Integer stkid, Double stockreq) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.addStock(appid,srvid,stkid,stockreq);
	}*/

	@Override
	public int addStock(List<Appointmentstockconsumption> objArry) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.addStock(objArry);
	}

	@Override
	public List getServiceName(Integer srvid) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.getServiceName(srvid);
	}

	@Override
	public List getSearchAppointements(String name) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.getSearchAppointements(name);
	}

	@Override
	public List<AppointmentService> searchdoctrAdviceList(String guestname) {
		// TODO Auto-generated method stub
		return doctorAdviceDAO.searchdoctrAdviceList(guestname);
	}

}
