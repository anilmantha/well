package com.leonia.wellness.idao;

import java.util.List;
import java.util.Set;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.dto.DataToGenerateBill;
import com.leonia.wellness.dto.ProductRateAndTax;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Stockmaster;


public interface IAppointmentsDAO {

	public  int saveAppointment(Appointment appointment);
	public Appointment getAppointment(int id);
	public List getServiceStocks(int serviceId);
	public boolean getAppointmentRoomCondition(String roomId, String startTime, String endTime, String arrivalDate);
	public boolean getAppointmentStaffCondition(String staffId, String startTime, String endTime, String arrivalDate);
	public List getstockDetailInfo(int serviceId);
	public int makeAppointment(List<Appointment> objArry, Integer custid);
	public List<AppointmentService> getAppointmentServiceInfo(Integer appointmentId);
	public void startService(Integer appointmentId, Integer serviceId);
	public void stopService(Integer appointmentId, Integer serviceId);
	public Set<Appointment> getAppointmentsCompletedList();
	public Set<Appointmentservicedetails> getAppointmentServicecompletedList();
	public List<Stockmaster> getStockOfRetailType();
	public ProductRateAndTax getProductRateAndTax(Integer productId);
	public String generateBillDetails(List<DataToGenerateBill> dataArray);
	public List<Billdetails> getServicerate(Integer serviceId, Integer appointmentId);

	public Integer getRetailStockAvailability(Integer stockId);

	

	public List getAppointementsList();

	public List getSearchAppointementsList(String appointmentId,String guestName,String appointmentDate,String doctorAdvice);
	public Set<Appointmentservicedetails> getAppointmentPackageServicecompletedList();
	public List<Billdetails> getPackageRate(Integer packageId, Integer appointmentId);
	List printbillreceipt(Integer billno, Integer guestid);
		public List getAllServiceDetails(String idArr, String nameArr, String srvOrPkg, String appointTime);
		public int bookAppointment(Appointment appointment, String appointTime);
		public String subtractTimes(String startTime, String endTime);
		public String addTimes(String startTime, String endTime);
		public List getAppointmentGuestDetails(int guestId);
		public List<Servicemaster> getServices();
		public List getPackages();
		public String savePreBillDiscount(String discountreason, String discountpercent, String discountamount);

}
