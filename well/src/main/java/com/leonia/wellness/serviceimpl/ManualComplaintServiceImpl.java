package com.leonia.wellness.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.idao.IManualComplaintDAO;
import com.leonia.wellness.iservice.IManualComplaintService;

@Service
public class ManualComplaintServiceImpl implements IManualComplaintService{

	@Autowired
	private IManualComplaintDAO iManualComplaintDAO;
	@Override
	public List getBillsByBillDate(Date date1, Date date2, String guestname) {
		
		return iManualComplaintDAO.getBillsByBillDate(date1,date2,guestname);
	}
	@Override
	public Billmaster getSelectedBill(Integer billNo) {
		
		return iManualComplaintDAO.getSelectedBill(billNo);
	}
	@Override
	public String saveManualComplaint(Integer guestId, Integer billNo, Integer departmentId, String complaint,
			String remarks) {
		
		return iManualComplaintDAO.saveManualComplaint(guestId,billNo,departmentId,complaint,remarks);
	}
	@Override
	public String saveManualComplaintByAdmin(Integer empId,Integer departmentId, String complaint, String remarks) {
		
		return iManualComplaintDAO.saveManualComplaintByAdmin(empId,departmentId,complaint,remarks);
	}
	@Override
	public Map getInServiceGuestDetails() {
		
		return iManualComplaintDAO.getInServiceGuestDetails();
	}
	@Override
	public String saveManualComplaintByInHouseGuest(Integer inHouseGuestId, Integer serviceId,Integer departmentId, String complaint,
			String remarks) {
		
		return iManualComplaintDAO.saveManualComplaintByInHouseGuest(inHouseGuestId,serviceId,departmentId,complaint,remarks);
	}
	@Override
	public Map getEmployeeDetails() {
		
		return iManualComplaintDAO.getEmployeeDetails();
	}
	@Override
	public List getEmployeeDepIdDesignation(Integer empId) {
		
		return iManualComplaintDAO.getEmployeeDepIdDesignation(empId);
	}
	@Override
	public List getGuestservices(Integer guestId) {
		
		return iManualComplaintDAO.getGuestservices(guestId);
	}
	@Override
	public List<Departmentmaster> departmentDetails() {
		
		return iManualComplaintDAO.departmentDetails();
	}

}
