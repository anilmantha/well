package com.leonia.wellness.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

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
import com.leonia.wellness.iservice.ILoadOnStartupService;


				/* This class is for Load values at application startup*/
@Controller
public class LoadOnStartupController implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ILoadOnStartupService iLoadOnStartupService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		/* statements to hit database and get values*/
		
		List<String> stockgroupnames = iLoadOnStartupService.getStockGroup();
		Set<Dropdowndetails> stocktype = iLoadOnStartupService.getstockType();
		List<Roommaster> roomsList = iLoadOnStartupService.getRooms();
		List<Servicemaster> serviceList=iLoadOnStartupService.getServices();
		List<String> stockList=iLoadOnStartupService.getStockDetails();
		Map<Integer,String> segment = iLoadOnStartupService.getSegment();
		Map<Integer,String> corporate = iLoadOnStartupService.getCorporate();
		Map<Integer,String> businessSource = iLoadOnStartupService.getBusinessSource();
		Map<Integer,String> billingInformation = iLoadOnStartupService.getBillingInformation();
		Map<Integer,String> city = iLoadOnStartupService.getCity();
		Map<Integer,String> state = iLoadOnStartupService.getState();
		Map<Integer,String> country = iLoadOnStartupService.getCountry();
		Map<Integer,String> title = iLoadOnStartupService.getTitle();
		Map<Integer,String> gender = iLoadOnStartupService.getGender();
		Map<Integer,String> supplier = iLoadOnStartupService.getSupplier();
		Map<Integer,String> manufacturer = iLoadOnStartupService.getManufacturer();
		Map<Integer,String> stockType = iLoadOnStartupService.getStockType();
		List<Staffmaster> staffList=iLoadOnStartupService.getStaffNames();
		List<Paymodemaster> paymodeDetails = iLoadOnStartupService.paymodeDetails();
		List<Creditlistmaster> creditMasterList = iLoadOnStartupService.corporateDetails();
		List<Membershipmaster> membershipList = iLoadOnStartupService.memberDetails();
		List<Departmentmaster> departmentmasterList = iLoadOnStartupService.departmentDetails();
		List<Cardtypemaster> cardTypeMasterList = iLoadOnStartupService.getCardTypes();
		
		List<Appointment> guestlist = iLoadOnStartupService.getGuestList();
		Map<Integer,String> month = iLoadOnStartupService.getMonth();
		List<Reasondetails> reasonList = iLoadOnStartupService.getReasonsList();
		
		/* objects to be used in jsp for getting display this values */
		
		servletContext.setAttribute("stockgroupnames", stockgroupnames);
		servletContext.setAttribute("gender", gender);
		servletContext.setAttribute("stocktype", stocktype);
		servletContext.setAttribute("roomsList", roomsList);
		servletContext.setAttribute("serviceList", serviceList);
		servletContext.setAttribute("stockList", stockList);
		servletContext.setAttribute("segment", segment);
		servletContext.setAttribute("corporate", corporate);
		servletContext.setAttribute("businessSource", businessSource);
		servletContext.setAttribute("billingInformation", billingInformation);
		servletContext.setAttribute("city", city);
		servletContext.setAttribute("state", state);
		servletContext.setAttribute("country", country);
		servletContext.setAttribute("title", title);
		servletContext.setAttribute("gender", gender);
		servletContext.setAttribute("supplier", supplier);
		servletContext.setAttribute("manufacturer", manufacturer);
		servletContext.setAttribute("stockType", stockType);
		servletContext.setAttribute("staffList", staffList);
		servletContext.setAttribute("paymodeDetails", paymodeDetails);
		servletContext.setAttribute("creditMasterList", creditMasterList);
		servletContext.setAttribute("membershipList", membershipList);
		servletContext.setAttribute("departmentmasterList", departmentmasterList);
		servletContext.setAttribute("cardtypelist", cardTypeMasterList);
		servletContext.setAttribute("reasonList", reasonList);
		//servletContext.setAttribute("guestlist", guestlist);

		servletContext.setAttribute("month", month);

	
		

	}

}
