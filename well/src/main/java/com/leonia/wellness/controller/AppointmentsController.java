package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.dto.DataToGenerateBill;
import com.leonia.wellness.dto.ProductRateAndTax;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Taxstructuredetails;
import com.leonia.wellness.iservice.IAppointmentsService;


@RestController
@RequestMapping(value = "/appointment")
public class AppointmentsController {
	
	@Autowired
	public IAppointmentsService iAppointmentsService;
	
	@RequestMapping(value="/appointment")
	public ModelAndView getAppointements(@RequestParam("name") String name)
	{
		return new ModelAndView("booking-selection","name",name);
	}
	
	@RequestMapping(value="/makeAppointment", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public int makeAppointment(@RequestBody List<Appointment> ObjArry,HttpServletRequest request,@RequestParam("custid") Integer custid)
	{
		int stockDeatilsList = iAppointmentsService.makeAppointment(ObjArry,custid);
		return 1;
	    
	}

	@RequestMapping(value="/makeAppointment",method=RequestMethod.POST)
	public ModelAndView display(Appointment appoin, @RequestParam("id") int id,HttpServletRequest req) 
	{
		String appoint=req.getParameter("newappoint");
		if("apb".equals(appoint)){
		appoin.setGuestmaster(new Guestmaster());
		appoin.getGuestmaster().setGuestid(id);
		int idd= iAppointmentsService.saveAppointment(appoin);
		Appointment app= iAppointmentsService.getAppointment(idd);
		return new ModelAndView("appointment","bookedappointment",app);
		}
		
		else
		{
			appoin.setGuestmaster(new Guestmaster());
			appoin.getGuestmaster().setGuestid(id);
			//appoin.setDoctoradvice(true);
			int idd= iAppointmentsService.saveAppointment(appoin);
			Appointment app= iAppointmentsService.getAppointment(idd);
			//System.out.println("sid---"+ appoin.getArrivaltime()+" "+appoin.getServiceId());
			return new ModelAndView("appointment","bookedappointment",app);
		}
		
	}

	@RequestMapping("/serviceStocksPage")
	public String getServiceStocks(@RequestParam("service") int serviceId, Map<String,Object> model)
	{
		System.out.println("!@@@@@@@@@@@@!!!!!!!!!!!!!@@@@@@@@@@@@"+serviceId);
		List<?> serviceStockList = iAppointmentsService.getServiceStocks(serviceId);
		
		model.put("serviceStockList", serviceStockList);
		return "serviceStocksPage";
	}
	
	
	@RequestMapping(value="/appointmentConditions")
	@ResponseBody
	public int getAppointmentConditions(@RequestParam("r") String 

roomId, @RequestParam("st") String startTime, @RequestParam("end") 

String endTime, @RequestParam("arv") String arrivalDate, @RequestParam

("staff") String staffId)
	{
		boolean roomResult = iAppointmentsService.getAppointmentRoomCondition(roomId, startTime, endTime, arrivalDate);
boolean staffResult = iAppointmentsService.getAppointmentStaffCondition(staffId, startTime, endTime, arrivalDate);
		int result = 0;
		if(!roomResult)
			result = 1;
		else if(!staffResult)
			result = 2;
		return result;
	}
	
	@RequestMapping("/stockDetailInfo")
	public List<?> getstockDetailInfo(@RequestParam("stockName") int serviceId, Map<String,Object> model)
	{
		System.out.println("!@@@@@@@@@@@@!!!!!!!!!!!!!@@@@@@@@@@@@"+serviceId);
		List<?> StockList = iAppointmentsService.getstockDetailInfo(serviceId);
		System.out.println("******************************************************"+StockList);
		return StockList;
	}
	@RequestMapping(value="/appointmentslist")
	public ModelAndView getAppointementsList()
	{
		List appointmentList = iAppointmentsService.getAppointementsList();
		if(appointmentList.size()>0){
			 return new ModelAndView("appointmentslist","appointmentList",appointmentList);
		}else{
		   String msg="No records are Found";
		   return new ModelAndView("appointmentslist","msg",msg);
		}
	}

	@RequestMapping(value="/appointmentsearch",method=RequestMethod.POST)
	public ModelAndView getSearchAppointementsList(HttpServletRequest request,HttpServletResponse response)
	{
	    String appointmentId = request.getParameter("appointmentsid");
		String guestName = request.getParameter("name");
		String appointmentDate = request.getParameter("appointmentdate");
		String doctorAdvice = request.getParameter("doctoradvice");
	    List appointmentList = iAppointmentsService.getSearchAppointementsList(appointmentId,guestName,appointmentDate,doctorAdvice);
	    if(appointmentList.size()>0){
	    	return new ModelAndView("appointmentslist","appointmentList",appointmentList);
	    }else{
	    	
	    	String msg="No records are Found";
	    	return new ModelAndView("appointmentslist","msg",msg);
	    }
	    
	    
	    }
		
	@RequestMapping(value="/appointmentservice")
	public ModelAndView openAppointementserviceslistPage(@RequestParam("appointmentId") Integer appointmentId)
	{
		List<AppointmentService> appointmentserviceslist = iAppointmentsService.getAppointmentServiceInfo(appointmentId);
		return new ModelAndView("appointmentservice","appointmentserviceslist",appointmentserviceslist);
	}
	
	@RequestMapping(value="/startService", method = RequestMethod.POST)
	@ResponseBody
	public String startService(HttpServletRequest request, HttpServletResponse response){
		
		Integer appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		Integer serviceId = Integer.parseInt(request.getParameter("serviceId"));
		iAppointmentsService.startService(appointmentId,serviceId);
		return "Successfully Service Started";
	}
	
	@RequestMapping(value="/stopService", method = RequestMethod.POST)
	@ResponseBody
	public String stopService(HttpServletRequest request, HttpServletResponse response){
		
		Integer appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		Integer serviceId = Integer.parseInt(request.getParameter("serviceId"));
		iAppointmentsService.stopService(appointmentId,serviceId);
		return "Successfully Service Completed";
	}
	
	@RequestMapping(value="/appointmentscompletedlistpage")
	public ModelAndView openAppointementsCompletedListPage(HttpServletRequest request, HttpServletResponse response)
	{
		Integer id = 0;
		Set<Appointment> appointmentscompletedlist = iAppointmentsService.getAppointmentsCompletedList();
		Set<Appointmentservicedetails> servicelist = iAppointmentsService.getAppointmentServicecompletedList();
		Set<Appointmentservicedetails> packageList = new LinkedHashSet<Appointmentservicedetails>();
		Set<Appointmentservicedetails> packageService = iAppointmentsService.getAppointmentPackageServicecompletedList();
		for (Appointmentservicedetails appointmentservicedetails : packageService) {
			if(appointmentservicedetails.getPackagemaster()!=null && id!=appointmentservicedetails.getAppointment().getAppointmentid())
			{
				packageList.add(appointmentservicedetails);
				id = appointmentservicedetails.getAppointment().getAppointmentid();
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("appointmentscompletedlist", appointmentscompletedlist);
		mv.addObject("serviceList", servicelist);
		mv.addObject("packageList", packageList);
		mv.setViewName("appointmentscompletedlist");
		return mv;
	}
	
	@RequestMapping(value="/getServicerate", method = RequestMethod.POST)
	@ResponseBody
	public List<BigDecimal> getServicerate(HttpServletRequest request, HttpServletResponse response){
		Integer packageId = Integer.parseInt(request.getParameter("packageId"));
		Integer serviceId = Integer.parseInt(request.getParameter("serviceId"));
		Integer appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		List<Billdetails> serviceRate = null;
		if(packageId==0)
		{
			serviceRate = iAppointmentsService.getServicerate(serviceId,appointmentId);
		}
		else
		{
			serviceRate = iAppointmentsService.getPackageRate(packageId,appointmentId);
		}
		BigDecimal serviceCost = new BigDecimal(0);
		BigDecimal tax = new BigDecimal(0);
		BigDecimal totalAmount = new BigDecimal(0);
		List<BigDecimal> amountlist = new LinkedList<BigDecimal>();
		for (Billdetails billdata : serviceRate) {
			if((billdata.getTaxstructureid())!=null){
				tax = tax.add(billdata.getAmount());
			}
			else{
				serviceCost = billdata.getAmount();
			}
		}
		totalAmount = serviceCost.add(tax);
		amountlist.add(serviceCost);
		amountlist.add(tax);
		amountlist.add(totalAmount);
		return amountlist;
	}
	
	@RequestMapping(value="/getStockofRetail", method = RequestMethod.POST)
	@ResponseBody
	public List<Comparable> getStockOfRetailType(HttpServletRequest request, HttpServletResponse response){
		List<Comparable> list = new LinkedList<Comparable>();
		List<Stockmaster> stockMaster = iAppointmentsService.getStockOfRetailType();
		for (Stockmaster stockmaster2 : stockMaster) {
			list.add(stockmaster2.getStockid());
			list.add(stockmaster2.getStockname());
		}
		return list;
	}
	
	@RequestMapping(value="/getproductrate", method = RequestMethod.POST)
	@ResponseBody
	public List<Comparable> getProductRateAndTax(HttpServletRequest request, HttpServletResponse response, @RequestParam("productid") Integer productId){
		System.out.println(request.getParameter("quantity"));
		BigDecimal quantity = new BigDecimal(request.getParameter("quantity"));
		ProductRateAndTax rates = iAppointmentsService.getProductRateAndTax(productId);
		List<Comparable> list = new LinkedList<Comparable>();
		List<Taxstructuredetails> tax = rates.getTaxAmount();
		BigDecimal productcost = rates.getProductCost();
		BigDecimal producttotal = new BigDecimal(0);
		BigDecimal percent = new BigDecimal(100);
		BigDecimal total = new BigDecimal(0);
		BigDecimal total2 = new BigDecimal(0);
		producttotal = productcost.multiply(quantity);
		for (Taxstructuredetails taxstructuredetails : tax) {
			total = taxstructuredetails.getPercent().divide(percent);
			total=total.multiply(producttotal);
			total2 = total.add(total2);
			
		}
		list.add(productcost);
		list.add(total2);
		list.add(total2.add(producttotal));
		list.add(rates.getProductName());
		return list;
	}
	
	@RequestMapping(value="/generateBillDetails", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String generateBillDetails(@RequestBody List<DataToGenerateBill> DataArray){
		String message = iAppointmentsService.generateBillDetails(DataArray);
		return message;
	}

	@RequestMapping(value="/getRetailStockAvailability", method = RequestMethod.POST)
	@ResponseBody
	public Integer getRetailStockAvailability(HttpServletRequest request, HttpServletResponse response){
		Integer stockId = Integer.parseInt(request.getParameter("stockid"));
		Integer stockAvailability = iAppointmentsService.getRetailStockAvailability(stockId);
		return stockAvailability;
	}
	
	@RequestMapping(value="/printbillreceipt", method = RequestMethod.GET)
	public String  printbillreceipt(@RequestParam ("billno") Integer billno,@RequestParam ("guestid") Integer guestid,HttpServletRequest request,Map<String, Object> model,ModelMap modelMap){
    	
    	List<?> viewExcelreport1 = iAppointmentsService.printbillreceipt(billno,guestid);
    	
    	List<?> viewExcelreport=(List<Object>) viewExcelreport1.get(0);
    	
    	List<?> viewExcelreport2=(List<Map<String,String>>) viewExcelreport1.get(1);
    	
    	System.out.println(viewExcelreport.size() +"--------------------------------------------------------------------");
    	System.out.println(viewExcelreport2.size() +"--------------------------------------------------------------------");
    	List<Map<?,?>> listProducts=new ArrayList<Map<?,?>>();
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("tax","value1");
		map1.put("Value","value2");
		map1.put("name", "venkatesh");
		map1.put("id", "28667");
		map1.put("loc", "hyderabd");
		listProducts.add(map1);        
		modelMap.put("listProducts",listProducts);
		modelMap.put("listProducts1",viewExcelreport2);
		
		return "PDFBillReceipt";
	}
	
	 @RequestMapping("/allServiceDetails")
	    @ResponseBody
	    public List<List<List>> getAllServiceDetails(@RequestParam("idArr") String idArr, @RequestParam("nameArr") String nameArr, @RequestParam("srvOrPkg") String srvOrPkg, @RequestParam("time") String appointTime)
	    {
	    	List result = iAppointmentsService.getAllServiceDetails(idArr, nameArr, srvOrPkg, appointTime);
	    	return result;
	    }
	    
	 @RequestMapping(value="/bookAppointment", method = RequestMethod.POST, consumes="application/json")
		@ResponseBody
		public int bookAppointment(@RequestBody Appointment appointment, HttpServletRequest request, @RequestParam("time") String appointTime)
		{
	    	int result = iAppointmentsService.bookAppointment(appointment, appointTime);
	    	return result;
		}
	    
	    @RequestMapping(value="/addTimes")
		@ResponseBody
		public String addTimes(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime)
		{
	    	String resultTime = iAppointmentsService.addTimes(startTime, endTime);
	    	return resultTime;
		}
	    
	    @RequestMapping(value="/subtractTimes")
		@ResponseBody
		public String subtractTimes(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime)
		{
	    	String resultTime = iAppointmentsService.subtractTimes(startTime, endTime);
	    	return resultTime;
		}
	    
	    @RequestMapping(value="/appointmentGuestDetails")
		@ResponseBody
		public List getAppointmentGuestDetails(@RequestParam("gid") Integer guestId)
		{
	    	return iAppointmentsService.getAppointmentGuestDetails(guestId);
		}
	    @RequestMapping(value="/getServices")
		@ResponseBody
		public List<Servicemaster> getServices()
		{
			List<Servicemaster> serviceList= iAppointmentsService.getServices();
			return serviceList;

		}
		
		@RequestMapping(value="/getPackages")
		@ResponseBody
		public List<Packagemaster> getPackages()
		{
			List<Packagemaster> packagesList= iAppointmentsService.getPackages();
			return packagesList;

		}

		@RequestMapping(value="/savePreBillDiscount", method = RequestMethod.POST)
		@ResponseBody
		public String generateBillDetails(HttpServletRequest request, HttpServletResponse response){
			String discountreason = request.getParameter("discountreason");
			String discountpercent = request.getParameter("discountpercent");
			String discountamount = request.getParameter("discountamount");
			String message = iAppointmentsService.savePreBillDiscount(discountreason, discountpercent, discountamount);
			return message;
		}
		
		@RequestMapping(value = "/segment")
		public String openMyPage(){
			
			System.out.println("webservices working@@@@@@@@@@@@@@@@@@@@@");
			
			return "hi";
		}
		
		
		@RequestMapping(value="/getMyPackages",produces = "application/json", method = RequestMethod.GET)
		@ResponseBody
		public List getMyPackages()
		{
			List packagesList= iAppointmentsService.getPackages();
			
			return packagesList;

		}
		
		
		
		
		
}
