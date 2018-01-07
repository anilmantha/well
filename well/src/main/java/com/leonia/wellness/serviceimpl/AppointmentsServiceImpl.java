package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.dto.DataToGenerateBill;
import com.leonia.wellness.dto.ProductRateAndTax;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.idao.IAppointmentsDAO;
import com.leonia.wellness.iservice.IAppointmentsService;
@Service
public class AppointmentsServiceImpl  implements IAppointmentsService{

     @Autowired
    private IAppointmentsDAO iAppointmentsDAO;
	@Override
	public int saveAppointment(Appointment appointment) {
		return iAppointmentsDAO.saveAppointment(appointment);
	}

	@Override
	public Appointment getAppointment(int id) {
		return iAppointmentsDAO.getAppointment(id);
	}
	
	@Override
	public List getServiceStocks(int serviceId) {
		return iAppointmentsDAO.getServiceStocks(serviceId);
	}

	@Override
	public boolean getAppointmentRoomCondition(String roomId, String startTime, String endTime, String arrivalDate) {
		return iAppointmentsDAO.getAppointmentRoomCondition(roomId, startTime, endTime, arrivalDate);
	}
	
	@Override
	public boolean getAppointmentStaffCondition(String staffId, String startTime, String endTime, String arrivalDate) {
		return iAppointmentsDAO.getAppointmentStaffCondition(staffId, startTime, endTime, arrivalDate);
	}

	@Override
	public List getstockDetailInfo(int serviceId) {
		// TODO Auto-generated method stub
		return iAppointmentsDAO.getstockDetailInfo(serviceId);
	}

	@Override
	public int makeAppointment(List<Appointment> objArry, Integer custid) {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public List getAppointementsList() {
		
		return iAppointmentsDAO.getAppointementsList();
	}

	@Override
	public List getSearchAppointementsList(String appointmentId,String guestName,String appointmentDate,String doctorAdvice) {
		
		return iAppointmentsDAO.getSearchAppointementsList(appointmentId,guestName,appointmentDate,doctorAdvice);
	}
	@Override
	public List<AppointmentService> getAppointmentServiceInfo(Integer appointmentId) {
		return iAppointmentsDAO.getAppointmentServiceInfo(appointmentId);
	}

	@Override
	public void startService(Integer appointmentId, Integer serviceId) {
		iAppointmentsDAO.startService(appointmentId,serviceId);
		return;
	}

	@Override
	public void stopService(Integer appointmentId, Integer serviceId) {
		iAppointmentsDAO.stopService(appointmentId,serviceId);
		return;
	}

	@Override
	public Set<Appointment> getAppointmentsCompletedList() {
		return iAppointmentsDAO.getAppointmentsCompletedList();
	}

	@Override
	public Set<Appointmentservicedetails> getAppointmentServicecompletedList() {
		return iAppointmentsDAO.getAppointmentServicecompletedList();
		
	}

	@Override
	public List<Stockmaster> getStockOfRetailType() {
		return iAppointmentsDAO.getStockOfRetailType();
	}

	@Override
	public ProductRateAndTax getProductRateAndTax(Integer productId) {
		return iAppointmentsDAO.getProductRateAndTax(productId);
	}

	@Override
	public String generateBillDetails(List<DataToGenerateBill> dataArray) {
		return iAppointmentsDAO.generateBillDetails(dataArray);
	}

	@Override
	public List<Billdetails> getServicerate(Integer serviceId, Integer appointmentId) {
		return iAppointmentsDAO.getServicerate(serviceId,appointmentId);
	}

	@Override
	public Integer getRetailStockAvailability(Integer stockId) {
		return iAppointmentsDAO.getRetailStockAvailability(stockId);
	}

	@Override
	public Set<Appointmentservicedetails> getAppointmentPackageServicecompletedList() {
		return iAppointmentsDAO.getAppointmentPackageServicecompletedList();
	}

	@Override
	public List<Billdetails> getPackageRate(Integer packageId, Integer appointmentId) {
		return iAppointmentsDAO.getPackageRate(packageId,appointmentId);
	}
	
	@Override
	public List printbillreceipt(Integer billno,Integer guestid) {
		return iAppointmentsDAO.printbillreceipt(billno,guestid);
	}
	
		@Override
		public List getAllServiceDetails(String idArr, String nameArr, String srvOrPkg, String appointTime) {
			// TODO Auto-generated method stub
			return iAppointmentsDAO.getAllServiceDetails(idArr, nameArr, srvOrPkg, appointTime);
		}

		@Override
		public int bookAppointment(Appointment appointment, String appointTime) {
			return iAppointmentsDAO.bookAppointment(appointment, appointTime);
		}
		
		@Override
		public String addTimes(String startTime, String endTime) {
			return iAppointmentsDAO.addTimes(startTime, endTime);
		}
		
		@Override
		public String subtractTimes(String startTime, String endTime) {
			return iAppointmentsDAO.subtractTimes(startTime, endTime);
		}
		

		@Override
		public List<Servicemaster> getServices() {
			// TODO Auto-generated method stub
			return iAppointmentsDAO.getServices();
		}

		@Override
		public List getPackages() {
			// TODO Auto-generated method stub
			return iAppointmentsDAO.getPackages();
		}

		@Override
		public String savePreBillDiscount(String discountreason, String discountpercent, String discountamount) {
			return iAppointmentsDAO.savePreBillDiscount(discountreason, discountpercent, discountamount);
		}
		@Override
		public List getAppointmentGuestDetails(int guestId) {
			return iAppointmentsDAO.getAppointmentGuestDetails(guestId);
		}

}
