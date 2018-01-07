package com.leonia.wellness.iservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Departmentmaster;

public interface IManualComplaintService {

	List getBillsByBillDate(Date date1, Date date2, String guestname);

	Billmaster getSelectedBill(Integer billNo);

	String saveManualComplaint(Integer guestId, Integer billNo, Integer departmentId, String complaint, String remarks);

	String saveManualComplaintByAdmin(Integer empId, Integer departmentId2, String complaint, String remarks);

	Map getInServiceGuestDetails();

	String saveManualComplaintByInHouseGuest(Integer inHouseGuestId, Integer serviceId, Integer departmentId, String complaint,
			String remarks);

	Map getEmployeeDetails();

	List getEmployeeDepIdDesignation(Integer empId);

	List getGuestservices(Integer guestId);

	List<Departmentmaster> departmentDetails();

}
