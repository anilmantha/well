package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.PaymentReportDTO;
import com.leonia.wellness.dto.ReviewResponse;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.entity.Productsales;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Ticketmaster;
import com.leonia.wellness.iservice.IAppointmentsService;
import com.leonia.wellness.iservice.ICustomersService;
import com.leonia.wellness.iservice.IFeedbackReviewService;
import com.leonia.wellness.iservice.IReportService;
/*import com.leonia.wellness.iservice.IFeedbackReviewService;*/
import com.leonia.wellness.iservice.iCustomerFeedbackService;



@Controller
public class ReportController {

	@Autowired
	public IAppointmentsService iAppointmentsService;
	
	@Autowired
	public IReportService iReportService;
	
	@Autowired
	public IFeedbackReviewService iFeedbackreviewservice;
	
	@Autowired
	private iCustomerFeedbackService customerfeedbackservice;
	
	@Autowired
	private ICustomersService iCustomersService;
	
	@RequestMapping(value="/generateReviewReports", method = RequestMethod.POST)
	public String generateReviewReports(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generate") String generatetype){
			
		Integer formId=Integer.parseInt(request.getParameter("formid"));
		String fromDate=request.getParameter("fromdate");
	    String toDate=request.getParameter("todate");
	    List lastlist = new LinkedList();
	    
	    Date date1 = new Date();
		Date date2 = new Date();
		
		  int esum = 0;
			int vsum = 0;
			int gsum = 0;
			int fsum = 0;
			int psum = 0;
			int vpsum = 0;
			int eproduct = 0;
			int vproduct = 0;
			int gproduct = 0;
			int fproduct = 0;
			int pproduct = 0;
			int vpproduct = 0;
			int prosum = 0;
			int totalsum = 0;
			int i;
			String formdes = null;
			
		//Parsing the String type Data into Date type  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = sdf.parse(fromDate);
			date2 = sdf.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
	
	    List<Responsemaster> reslist = iFeedbackreviewservice.getreposnedata();
	    System.out.println(reslist);
	     for(Responsemaster resp:reslist){
	    	 switch(resp.getResponseid()){
	    	 case 1:
	    		 eproduct=resp.getResponsescore();
	    		 break;
	    	 
	     	 case 2:
	     		 vproduct=resp.getResponsescore();
	     		 break;
	     	 
			 case 3:
				 gproduct=resp.getResponsescore();
				 break;
	 		 
			 case 4:
				 fproduct=resp.getResponsescore();
				 break;
			 
			 case 5:
				 pproduct=resp.getResponsescore();
				 break;
			 
			 case 6:
				 vpproduct=resp.getResponsescore();
	    		 break;
	    	 }
	    	 
	     }
	     
	     List<Feedbackdetails> rlist = iFeedbackreviewservice.getReviewDetails(date1,date2,formId);
	 	 List<Feedbackformmaster> formdata = iFeedbackreviewservice.getFormList(date1,date2,formId);
			
		   
			ReviewResponse rdto1 = new ReviewResponse();
			ReviewResponse rdto2 = new ReviewResponse();
			
			Formmaster ffid = iFeedbackreviewservice.getFormRes(formId);
			if(formId == ffid.getFormid()){
				formdes = ffid.getFormdescription();
			}
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			
			map.put("formid",(Integer)formId);
			map.put("formdescp",formdes);
		    map.put("fromdate",fromDate);
		    map.put("todate",toDate);
			map.put("escore",(Integer)eproduct);
			map.put("vscore",(Integer)vproduct);
			map.put("gscore",(Integer)gproduct);
			map.put("fscore",(Integer)fproduct);
			map.put("pscore",(Integer)pproduct);
			map.put("vpscore",(Integer)vpproduct);
		    
			lastlist.add(map);
	     
	    List<Questionmaster> qlist = iFeedbackreviewservice.getQuestionDescription(formId);
	    
		for(Questionmaster qes:qlist){
			int ecount = 0;
			int vcount = 0;
			int gcount = 0;
			int fcount = 0;
			int pcount = 0;
			int vpcount = 0;
			int total = 0;
			
				for(Feedbackdetails resp:rlist){
					
					if(qes.getQuestionid()==resp.getQuestionid())
							{
						switch(resp.getResponsemaster().getResponseid()){
						case 1:
								ecount++;
								break;
						case 2:
								vcount++;
								break;
						case 3:
								gcount++;
								break;
						case 4:
								fcount++;
								break;
						case 5:
								pcount++;
								break;
						case 6:
								vpcount++;
								break;
						default:
							         break;	
		
						}
						
					}
					
				}
			
			Map<String,Object> map1 = new LinkedHashMap<String, Object>();
			
			String x = qes.getQuestiondescription();
			
			map1.put("questiondesc",x);
			
			total = pcount+ecount+vcount+gcount+fcount+vpcount;
				
			map1.put("ecount",ecount);
			map1.put("vcount",vcount);
		    map1.put("gcount",gcount);
		    map1.put("fcount",fcount);
		    map1.put("pcount",pcount);
		    map1.put("vpcount",vpcount);
		    map1.put("total",total);
			
			esum = esum + ecount;
			vsum = vsum + vcount;
			gsum = gsum + gcount;
			fsum = fsum + fcount;
			psum = psum + pcount;
			vpsum = vpsum + vpcount;
			
			lastlist.add(map1);
			
		}
		
		int qescount = qlist.size();
		
		totalsum = esum + vsum + gsum + fsum + psum + vpsum;
		
		int alltotal = totalsum;
		
		int formcount = formdata.size();
				
		int maxscore = (totalsum*eproduct);
		
		eproduct = eproduct * esum;
		vproduct = vproduct * vsum;
		gproduct = gproduct * gsum;
		fproduct = fproduct * fsum;
		pproduct = pproduct * psum;
		vpproduct = vpproduct * vpsum;
		
		prosum = eproduct + vproduct + gproduct + fproduct + pproduct + vpproduct;
		
		i = prosum * 100;
		
		int performindex = 0;
		if(i!=0 && maxscore!=0){
		performindex = i/maxscore;
		}
		
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
	       
		rdto1.setSumECount(esum);
		map2.put("esum",(Integer)rdto1.getSumECount());
        rdto1.setSumVCount(vsum);
        map2.put("vsum",(Integer)rdto1.getSumVCount());
        rdto1.setSumGCount(gsum);
        map2.put("gsum",(Integer)rdto1.getSumGCount());
        rdto1.setSumFCount(fsum);
        map2.put("fsum",(Integer)rdto1.getSumFCount());
        rdto1.setSumPCount(psum);
        map2.put("psum",(Integer)rdto1.getSumPCount());
        rdto1.setSumVPCount(vpsum);
        map2.put("vpsum",(Integer)rdto1.getSumVPCount());
        
        rdto2.setProECount(eproduct);
        map2.put("erese",(Integer)rdto2.getProECount());
	 	rdto2.setProVCount(vproduct);
	 	map2.put("vres",(Integer)rdto2.getProVCount());
        rdto2.setProGCount(gproduct);
        map2.put("gres",(Integer)rdto2.getProGCount());
        rdto2.setProFCount(fproduct);
        map2.put("fres",(Integer)rdto2.getProFCount());
        rdto2.setProPCount(pproduct);
        map2.put("pres",(Integer)rdto2.getProPCount());
        rdto2.setProVPCount(vpproduct);
        map2.put("vpres",(Integer)rdto2.getProVPCount());
        rdto2.setSumtotal(prosum);
        map2.put("totalres",(Integer)rdto2.getSumtotal());
        
        map2.put("totalfilledforms",(Integer)formcount);
        map2.put("attributes",(Integer)qescount);
        map2.put("totalattributes",(Integer)alltotal);
        map2.put("totalscore",(Integer)maxscore);
        map2.put("pertotal",(Integer)performindex);
        
        lastlist.add(map2);
        
		    modelMap.put("reviewlist",lastlist);
		    modelMap.put("generatetype",generatetype);
		    
		    return "ReviewReport";
	        
	    }
	/*@RequestMapping(value="/generatesettlement")
	public String generateSettlementReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generatetype") String generatetype){
		
		List<Map<String,?>> appointmentsList = new LinkedList<Map<String,?>>();
		List<Appointment> appointmentList = iAppointmentsService.getAppointementsList();
		for (Appointment appoinment : appointmentList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("AppoinmentId", appoinment.getAppointmentid());
			map.put("Guestname", appoinment.getGuestmaster().getName());
			map.put("DateOfAppointment", appoinment.getAppointmentdate());
			map.put("DoctorAdvice", appoinment.isDoctoradvice());
			appointmentsList.add(map);
		}
		modelMap.put("appointmentsList",appointmentsList);
		modelMap.put("generatetype",generatetype);
		return "settlementlistreport";
	}
	*/
	@RequestMapping(value="/billsettlement")
	public ModelAndView paymentsettlement(HttpServletRequest req,HttpServletResponse res){
		List<Billsettlement> billSettlementList=iReportService.billSettlementList();
		List<Paymodemaster> paymodeList = iReportService.getPaymodeList();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("settlementlistreport");
		mv.addObject("billSettlementList",billSettlementList);
		mv.addObject("paymodeList",paymodeList);
		return mv;
	}
	
	@RequestMapping(value="/generateSettlementReport")
	public String generateSettlementReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generateType") String generatetype){
		
		List<Map<String,?>> settlementsList = new LinkedList<Map<String,?>>();
		Date date1 = null;
		 Date date2 = null;
	    String fromDate  = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String payMode = request.getParameter("paymode");
		String payMode1 = request.getParameter("paymode1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("fromdate", fromDate);
		map1.put("todate", toDate);
		if(payMode1.equals("Select")){
			payMode1 = null;
		}
		map1.put("searchpaymode", payMode1);
		settlementsList.add(map1);
		List<Billsettlement> settlementList = iReportService.getSettlementssList(date1,date2,payMode);
	    for (Billsettlement billsettlement : settlementList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("billno", billsettlement.getBillmaster().getBillno());
			map.put("amount", billsettlement.getBillmaster().getAmount());
			String date = billsettlement.getBillmaster().getBilldate().toString();
			map.put("billdate", date);
			map.put("paymode", billsettlement.getPaymodemaster().getPaymode());
			map.put("settleamount", billsettlement.getAmount());
			map.put("remarks", billsettlement.getRemarks());
			settlementsList.add(map);
		}
		modelMap.put("settlementsList",settlementsList);
		modelMap.put("generatetype",generatetype);
		return "billsettlementreport";
	}
	
	@RequestMapping(value="/searchsettlement")
	public ModelAndView getSearchSettlements(HttpServletRequest request,HttpServletResponse response)
	{
		 Date date1 = null;
		 Date date2 = null;
	    String fromDate  = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String payMode = request.getParameter("paymodeid");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		List<Billsettlement> settlementList = iReportService.getSettlementssList(date1,date2,payMode);
		List<Paymodemaster> paymodeList = iReportService.getPaymodeList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settlementlistreport");
		modelAndView.addObject("fromdate",fromDate);
		modelAndView.addObject("todate",toDate);
		modelAndView.addObject("payMode",payMode);
		modelAndView.addObject("billSettlementList",settlementList);
		modelAndView.addObject("paymodeList",paymodeList);
	    if(settlementList.size()>0){
	    	return modelAndView;
	    }else{
	    	
	    	String msg="No records are Found";
	    	modelAndView.addObject("msg",msg);
	    	return modelAndView;
	    }
	
	}
	
	@RequestMapping(value = "/generateClientDOBReports", method = RequestMethod.POST)
	public String generateClientDOBReports(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,	@RequestParam("generate") String generatetype){

		String month = request.getParameter("month");
		String fromdate = request.getParameter("fromDate");
		String todate = request.getParameter("toDate");

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + month);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + fromdate);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + todate);
		List guestdetails = new LinkedList();
		List guestDoblist = iCustomersService.getSearchCustomerDOB(month, fromdate, todate);
		
		System.out.println("!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@!!!!!!!!!!!!!!!" + guestDoblist);
		
		Iterator ie = guestDoblist.iterator();

		while (ie.hasNext()) {
			
			Object[] guestmaster2 = (Object[]) ie.next();
			
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			
			int id = (int) guestmaster2[0];
			String name = (String) guestmaster2[1];
			Date dob = (Date) guestmaster2[2];
			BigInteger mobile = (BigInteger) guestmaster2[3];
			String email = (String) guestmaster2[4];
			Date lastvisitdate = (Date) guestmaster2[5];
			int noofvisits = (int) guestmaster2[6];
			

			map.put("CustID",id);
			
			map.put("Name", name);
			
			map.put("DOB",dob);
			
			map.put("Mobile",mobile);
			
			map.put("Email",email);
			
			map.put("LastVisit",lastvisitdate);
			
			map.put("totalVisits",noofvisits);
			
			guestdetails.add(map);
			/*guestdetails.add(name);
			guestdetails.add(dob);
			guestdetails.add(mobile);
			guestdetails.add(email);
			guestdetails.add(lastvisitdate);
			guestdetails.add(noofvisits);*/
			
			
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + id);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + name);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + dob);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + mobile);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + email);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + noofvisits);

		}
		
		modelMap.put("guestdetails",guestdetails);
		modelMap.put("generatetype",generatetype);

		return "ClientDOBReport";
	}
	
	@RequestMapping(value = "/generateClientWDReports", method = RequestMethod.POST)
	public String generateClientWDReport(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,@RequestParam("generate") String generatetype){
		
		String month=request.getParameter("month");
		String fromdate=request.getParameter("fromDate");
		String todate=request.getParameter("toDate");
		List guestDetails=new LinkedList<>();
		List guestWDlist = iCustomersService.getSearchGuestWedding(month, fromdate, todate);
		
		Iterator ie = guestWDlist.iterator();
		while(ie.hasNext()){
			Object[] guestWDlist1 = (Object[])ie.next();
			
			Map<String,Object> map=new  LinkedHashMap<String, Object>();
			
			int id=(int)guestWDlist1[0];
			String name=(String)guestWDlist1[1];
			Date weddingday=(Date)guestWDlist1[2];
			BigInteger mobile = (BigInteger) guestWDlist1[3];
			String email = (String) guestWDlist1[4];
			Date lastvisitdate = (Date)guestWDlist1[5];
			int noofvisits = (int) guestWDlist1[6];
			
			map.put("id", id);
			
			map.put("name", name);
			map.put("weddingday", weddingday);
			map.put("mobile", mobile);
			map.put("email", email);
			map.put("lastvisitdate", lastvisitdate);
			map.put("noofvisits", noofvisits);
			guestDetails.add(map);
			
		}
		modelMap.put("guestdetails",guestDetails);
		modelMap.put("generatetype",generatetype);
	
	
		return "ClientWeedingDate";
	
		}
	
	@RequestMapping(value="/generateTicketReports", method = RequestMethod.POST)
	public String generateticketReports(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generate") String generatetype){
		 
		String fromdate =request.getParameter("fromdate");
   	  
  	    String todate =request.getParameter("todate");
  	  
  	    Date date1 = new Date();
  	    Date date2 = new Date();
 		//Parsing the String type Data into Date type  
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		try {
 			date1 = sdf.parse(fromdate);
			date2 = sdf.parse(todate);
 		} catch (ParseException e) {
 			e.printStackTrace(); 
 		}
 			String departmentId = request.getParameter("dept");
 			String statustype = request.getParameter("status");
 	 		String sourcetype = request.getParameter("srctype");
 	 		List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(date1, date2,departmentId,statustype,sourcetype);
		
		LinkedList reportlist = new LinkedList();
		
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		
		map.put("fromdate",fromdate);
		map.put("todate",todate);
	   
		reportlist.add(map);
		
		for (Ticketmaster tcktlist : tiketslist) {
			Map<String,Object> map1 = new LinkedHashMap<String, Object>();
			map1.put("tcktid", tcktlist.getTicketno());
			map1.put("date", tcktlist.getTicketdate());
			map1.put("Complaints", tcktlist.getComplaint());
			map1.put("Department", tcktlist.getDepartmentmaster().getDepartmentname());
			map1.put("assigned", tcktlist.getAssignedto());
			map1.put("status", tcktlist.getStatusmaster().getStatusdescription());
			map1.put("tckttype", tcktlist.getTickettypemaster().getTickettypedescription());
			reportlist.add(map1);
		}
		modelMap.put("ticketsList",reportlist);
		modelMap.put("generatetype",generatetype);
		
	return "TicketsReport";	
	}
	
	@RequestMapping(value="/stockreorderlevel")
	public ModelAndView stockReorderLevelList(HttpServletRequest req,HttpServletResponse res){
		List<Stockmaster> stockReorderList = new LinkedList<Stockmaster>();
		List<Stockgroupmaster> stockgroupmaster = iReportService.stockgroupmaster();
		List<Stocksubgroupmaster> Stocksubgroupmaster=iReportService.stocksubgroupmaster();
		List<Stockmaster> stockList=iReportService.stockReorderLevelList();
		for (Stockmaster stockmaster : stockList) {
			if((stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getReorderlevel())))<0)
					&& (stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))>0)){
				stockReorderList.add(stockmaster);
			}
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("stockreorderlevellist");
		mv.addObject("stockReorderList",stockReorderList);
		mv.addObject("stockgroupmaster",stockgroupmaster);
		mv.addObject("Stocksubgroupmaster",Stocksubgroupmaster);
		return mv;
	}
	
	@RequestMapping(value="/generateStockReorderLevelReport")
	public String generateStockReorderlevelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generatetype") String generatetype){
		
		List<Map<String,?>> reOrderLevelList = new LinkedList<Map<String,?>>();
		String stockName = request.getParameter("stockname");
		String stockType=request.getParameter("stocktype");
		String stockGroup=request.getParameter("stockgroup");
		String stockSubGroup=request.getParameter("stocksubgroup");
		String stockType1=request.getParameter("type");
		String stockGroup1=request.getParameter("group");
		String stockSubGroup1=request.getParameter("subgroup");
		List<Stockmaster> stockReorderList = new LinkedList<Stockmaster>();
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		if(stockType1.equals("Select"))
		{
			stockType1 = null;
		}
		if(stockGroup1.equals("Select"))
		{
			stockGroup1 = null;
		}
		if(stockSubGroup1.equals("Select"))
		{
			stockSubGroup1 = null;
		}
		map1.put("stockname", stockName);
		map1.put("stocktype", stockType1);
		map1.put("stockgroup", stockGroup1);
		map1.put("stocksubgroup", stockSubGroup1);
		reOrderLevelList.add(map1);
		List<Stockmaster> stockList = iReportService.getStockList(stockName,stockType,stockGroup,stockSubGroup);
		/*List<Stockmaster> stockReorderList=iReportService.stockReorderLevelList();*/
		for (Stockmaster stockmaster : stockList) {
			if((stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getReorderlevel())))<0)
					&& (stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))>0)){
				stockReorderList.add(stockmaster);
			}
		}
		for (Stockmaster stockmaster : stockReorderList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("sno", stockmaster.getSno());
			map.put("stockname1", stockmaster.getStockname());
			map.put("stocktype1", stockmaster.getDropdowndetails().getDescription());
			map.put("currentstock", stockmaster.getAvailable());
			map.put("reorderlevel", stockmaster.getReorderlevel());
			map.put("stockgroup1", stockmaster.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname());
			map.put("stocksubgroup1", stockmaster.getStocksubgroupmaster().getStocksubgroupname());
			reOrderLevelList.add(map);
		}
		modelMap.put("reOrderLevelList",reOrderLevelList);
		modelMap.put("generatetype",generatetype);
		return "stockreorderlevelreport";
	}
	
	@RequestMapping(value="/searchstockreorderlevel")
	public ModelAndView getSearchStock(HttpServletRequest request,HttpServletResponse response)
	{
		List<Stockmaster> stockReorderList = new LinkedList<Stockmaster>();
		String stockName = request.getParameter("stockname");
		String stockType=request.getParameter("stocktype");
		String stockGroup=request.getParameter("stockgroup");
		String stockSubGroup=request.getParameter("stocksubgroup");
		List<Stockmaster> stockList = iReportService.getStockList(stockName,stockType,stockGroup,stockSubGroup);
		List<Stockgroupmaster> stockgroupmaster = iReportService.stockgroupmaster();
		List<Stocksubgroupmaster> Stocksubgroupmaster=iReportService.stocksubgroupmaster();
		for (Stockmaster stockmaster : stockList) {
			if((stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getReorderlevel())))<0)
					&& (stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))>0)){
				stockReorderList.add(stockmaster);
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("stockreorderlevellist");
		modelAndView.addObject("stockName",stockName);
		modelAndView.addObject("stocktypes",stockType);
		modelAndView.addObject("stockGroup",stockGroup);
		modelAndView.addObject("stockSubGroup",stockSubGroup);
		modelAndView.addObject("stockReorderList",stockReorderList);
		modelAndView.addObject("stockgroupmaster",stockgroupmaster);
		modelAndView.addObject("Stocksubgroupmaster",Stocksubgroupmaster);
	    if(stockList.size()>0){
	    	return modelAndView;
	    }else{
	    	
	    	String msg="No records are Found";
	    	modelAndView.addObject("msg",msg);
	    	return modelAndView;
	    }
	}
	
	@RequestMapping(value="/inventory")
	public ModelAndView inventoryList(HttpServletRequest req,HttpServletResponse res){
		List<Servicestocksmaster> stockList=iReportService.inventoryList();
		List<Billdetails> billdetailslist = iReportService.getbilldetails();
		List<Stockmaster> stockmaster = iReportService.stockmaster();
		List<Servicemaster> servicemaster=iReportService.servicemaster();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("inventorylist"); 
		mv.addObject("stockList",stockList);
		mv.addObject("billdetailslist",billdetailslist);
		mv.addObject("stockmaster",stockmaster);
		mv.addObject("servicemaster",servicemaster);
		return mv;
	}
	
	@RequestMapping(value="/generateInventoryReport")
	public String generateInventoryReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generatetype") String generatetype){
		
		List<Map<String,?>> InventoryList = new LinkedList<Map<String,?>>();
		List<Servicestocksmaster> stockList=iReportService.inventoryList();
		List<Billdetails> billdetailslist = iReportService.getbilldetails();
		for (Servicestocksmaster servicestockmaster : stockList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("id", servicestockmaster.getStockmaster().getStockid());
			map.put("stockname", servicestockmaster.getStockmaster().getStockname());
			map.put("servicename", servicestockmaster.getServicemaster().getServicename());
			map.put("quantity", servicestockmaster.getStockmaster().getAvailable());
			String date = servicestockmaster.getUpdateddate().toString();
			map.put("processdate", date);
		for (Billdetails billdetails : billdetailslist) {
			if(billdetails.getServicemaster()!=null && billdetails.getTaxstructureid()==null && billdetails.getServicemaster().getServiceid() == servicestockmaster.getServicemaster().getServiceid())
			{
				map.put("billno", billdetails.getBillmaster().getBillno());			
			}
		}
		InventoryList.add(map);
		}
		modelMap.put("InventoryList",InventoryList);
		modelMap.put("generatetype",generatetype);
		return "inventoryreport";
	}
	
	@RequestMapping(value="/stockwarninglevel")
	public ModelAndView stockWarningLevelList(HttpServletRequest req,HttpServletResponse res){
		List<Stockmaster> stockList=iReportService.stockWarningLevelList();
		List<Stockgroupmaster> stockgroupmaster = iReportService.stockgroupmaster();
		List<Stocksubgroupmaster> Stocksubgroupmaster=iReportService.stocksubgroupmaster();
		List<Stockmaster> stockWarningList = new LinkedList<Stockmaster>();
		for (Stockmaster stockmaster : stockList) {
			if(stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))<0){
				stockWarningList.add(stockmaster);
			}
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("stockwarninglevellist");
		mv.addObject("stockWarningList",stockWarningList);
		mv.addObject("stockgroupmaster",stockgroupmaster);
		mv.addObject("Stocksubgroupmaster",Stocksubgroupmaster);
		return mv;
	}
	
	@RequestMapping(value="/generateStockWarningLevelReport", method=RequestMethod.POST)
	public String generateStockWarninglevelReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generatetype") String generatetype){
		
		List<Map<String,?>> warningLevelList = new LinkedList<Map<String,?>>();
		String stockName = request.getParameter("stockname");
		String stockType=request.getParameter("stocktype");
		String stockGroup=request.getParameter("stockgroup");
		String stockSubGroup=request.getParameter("stocksubgroup");
		String stockType1=request.getParameter("type");
		String stockGroup1=request.getParameter("group");
		String stockSubGroup1=request.getParameter("subgroup");
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		if(stockType1.equals("Select"))
		{
			stockType1 = null;
		}
		if(stockGroup1.equals("Select"))
		{
			stockGroup1 = null;
		}
		if(stockSubGroup1.equals("Select"))
		{
			stockSubGroup1 = null;
		}
		map1.put("stockname", stockName);
		map1.put("stocktype", stockType1);
		map1.put("stockgroup", stockGroup1);
		map1.put("stocksubgroup", stockSubGroup1);
		warningLevelList.add(map1);
		List<Stockmaster> stockList = iReportService.getStockList(stockName,stockType,stockGroup,stockSubGroup);
	    //List<Stockmaster> stockWarningList=iReportService.stockWarningLevelList();
	    List<Stockmaster> stockWarningList = new LinkedList<Stockmaster>();
		for (Stockmaster stockmaster : stockList) {
			if(stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))<0){
				stockWarningList.add(stockmaster);
			}
		}
		for (Stockmaster stockmaster : stockWarningList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("sno", stockmaster.getSno());
			map.put("stockname1", stockmaster.getStockname());
			map.put("stocktype1", stockmaster.getDropdowndetails().getDescription());
			map.put("currentstock", stockmaster.getAvailable());
			map.put("warninglevel", stockmaster.getWarninglevel());
			map.put("stockgroup1", stockmaster.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname());
			map.put("stocksubgroup1", stockmaster.getStocksubgroupmaster().getStocksubgroupname());
			warningLevelList.add(map);
		}
		modelMap.put("warningLevelList",warningLevelList);
		modelMap.put("generatetype",generatetype);
		return "stockwarninglevelreport";
	}
	
	@RequestMapping(value="/searchstockwarninglevel")
	public ModelAndView getSearchStockWarningLevel(HttpServletRequest request,HttpServletResponse response)
	{
		String stockName = request.getParameter("stockname");
		String stockType=request.getParameter("stocktype");
		String stockGroup=request.getParameter("stockgroup");
		String stockSubGroup=request.getParameter("stocksubgroup");
		if(stockType.equals("Select"))
		{
			stockType = null;
		}
		if(stockGroup.equals("Select"))
		{
			stockGroup = null;
		}
		if(stockSubGroup.equals("Select"))
		{
			stockSubGroup = null;
		}
		List<Stockgroupmaster> stockgroupmaster = iReportService.stockgroupmaster();
		List<Stocksubgroupmaster> Stocksubgroupmaster=iReportService.stocksubgroupmaster();
		List<Stockmaster> stockList = iReportService.getStockList(stockName,stockType,stockGroup,stockSubGroup);
		List<Stockmaster> stockWarningList = new LinkedList<Stockmaster>();
		for (Stockmaster stockmaster : stockList) {
			if(stockmaster.getAvailable().compareTo((new BigDecimal(stockmaster.getWarninglevel())))<0){
				stockWarningList.add(stockmaster);
			}
		}ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("stockwarninglevellist");
		modelAndView.addObject("stockname",stockName);
		modelAndView.addObject("stocktypes",stockType);
		modelAndView.addObject("stockGroup",stockGroup);
		modelAndView.addObject("stockSubGroup",stockSubGroup);
		modelAndView.addObject("stockWarningList",stockWarningList);
		modelAndView.addObject("stockgroupmaster",stockgroupmaster);
		modelAndView.addObject("Stocksubgroupmaster",Stocksubgroupmaster);
	    if(stockList.size()>0){
	    	return modelAndView;
	    }else{
	    	
	    	String msg="No records are Found";
	    	modelAndView.addObject("msg",msg);
	    	return modelAndView;
	    }
	}
	

	@RequestMapping(value="/searchInventory")
	public ModelAndView getSearchInventory(HttpServletRequest request,HttpServletResponse response){
		String stockName = request.getParameter("stockname");
		String serviceName=request.getParameter("servicename");
		List<Stockmaster> stockmaster = iReportService.stockmaster();
		List<Servicemaster> servicemaster=iReportService.servicemaster();
		List<Servicestocksmaster> inventoryList = iReportService.getSearchInventory(stockName,serviceName);
		List<Billdetails> billdetailslist = iReportService.getbilldetails();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("inventorylist");
		mv.addObject("stockname",stockName);
		mv.addObject("servicename",serviceName);
		mv.addObject("stockmaster",stockmaster);
		mv.addObject("servicemaster",servicemaster);
		mv.addObject("stockList",inventoryList);
		mv.addObject("billdetailslist",billdetailslist);
		 if(inventoryList.size()>0){
		    	return mv;
		    }else{
		    	
		    	String msg="No records are Found";
		    	mv.addObject("msg",msg);
		    	return mv;
		    }
	}
	
	@RequestMapping(value="/therapistlist")
	public ModelAndView therapistList(HttpServletRequest req,HttpServletResponse res){
		List<Appointmentservicedetails> therapistList=iReportService.therapistList();
		List<Servicemaster> servicemaster=iReportService.servicemaster();
		List<Billdetails> billdetailslist=iReportService.getbilldetails();
		Integer i = 0;
		List<Billdetails> list = new LinkedList<Billdetails>();
		for (Billdetails billdetails : billdetailslist) {
			if(billdetails.getServicemaster()!= null){
				if(i==billdetails.getServicemaster().getServiceid())
				{
					list.add(billdetails);
				}
				i=billdetails.getServicemaster().getServiceid();
			}
		}
		ModelAndView mv=new ModelAndView();
		mv.addObject("servicemaster",servicemaster);
		mv.setViewName("therapisttransactionlist");
		mv.addObject("therapistList",therapistList);
		mv.addObject("billdetailslist",list);
		return mv;
	}
	
	@RequestMapping(value="/searchtherapistreport")
	public ModelAndView getSearchTherapistReport(HttpServletRequest request,HttpServletResponse response)
	{
		 Date date1 = null;
		 Date date2 = null;
	    String fromDate  = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String serviceName = request.getParameter("servicename");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		List<Appointmentservicedetails> therapistReportList = iReportService.gettherapistReportList(date1,date2,serviceName);
		List<Servicemaster> servicemaster=iReportService.servicemaster();
		List<Billdetails> billdetailslist=iReportService.getbilldetails();
		Integer i = 0;
		List<Billdetails> list = new LinkedList<Billdetails>();
		for (Billdetails billdetails : billdetailslist) {
			if(billdetails.getServicemaster()!= null){
				if(i==billdetails.getServicemaster().getServiceid())
				{
					list.add(billdetails);
				}
				i=billdetails.getServicemaster().getServiceid();
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("therapisttransactionlist");
		modelAndView.addObject("fromdate",fromDate);
		modelAndView.addObject("todate",toDate);
		modelAndView.addObject("servicename",serviceName);
		modelAndView.addObject("therapistList",therapistReportList);
		modelAndView.addObject("billdetailslist",list);
		modelAndView.addObject("servicemaster",servicemaster);
	    if(therapistReportList.size()>0){
	    	return modelAndView;
	    }else{
	    	String msg="No records are Found";
	    	modelAndView.addObject("msg",msg);
	    	return modelAndView;
	    }
	}

	
	@RequestMapping(value="/generateTherapistTransactionReport")
	public String generateTherapistTransactionReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generatetype") String generatetype){
		List<Map<String,?>> therapistTransactionList = new LinkedList<Map<String,?>>();
		 Date date1 = null;
		 Date date2 = null;
	    String fromDate  = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String serviceName = request.getParameter("servicename");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("fromdate", date1);
		map1.put("todate", date2);
		map1.put("servicename", serviceName);
		therapistTransactionList.add(map1);
		List<Appointmentservicedetails> therapistReportList = iReportService.gettherapistReportList(date1,date2,serviceName);
		List<Servicemaster> servicemaster=iReportService.servicemaster();
		List<Billdetails> billdetailslist=iReportService.getbilldetails();
		Integer i = 0;
		List<Billdetails> list = new LinkedList<Billdetails>();
		for (Billdetails billdetails : billdetailslist) {
			if(billdetails.getServicemaster()!= null){
				if(i==billdetails.getServicemaster().getServiceid())
				{
					list.add(billdetails);
				}
				i=billdetails.getServicemaster().getServiceid();
			}
		}
		for (Appointmentservicedetails appointmentservice : therapistReportList) {
			for (Billdetails billdetails : list) {
				if((appointmentservice.getServicemaster().getServiceid()==billdetails.getServicemaster().getServiceid() && billdetails.getTaxstructureid()==null)){
					Map<String,Object> map = new LinkedHashMap<String, Object>();
					map.put("appointmentdate", appointmentservice.getAppointment().getAppointmentdate());
					map.put("billno", billdetails.getBillmaster().getBillno());
					map.put("amount", billdetails.getAmount());
					map.put("servicename1", appointmentservice.getServicemaster().getServicename());
					map.put("staffname", appointmentservice.getStaffmaster().getStaffname());
					therapistTransactionList.add(map);
				}
			}
		}
		modelMap.put("therapistTransactionList",therapistTransactionList);
		modelMap.put("generatetype",generatetype);
		return "therapisttransactionreport";
	}
	@RequestMapping(value="/paymentlist")
	public ModelAndView paymentList(HttpServletRequest req,HttpServletResponse res){
		List<Appointmentservicedetails> paymentList=iReportService.paymentList();
		List<Billdetails> billdetailslist=iReportService.getbilldetails();
		Integer i = 0;
		List<PaymentReportDTO> list = new LinkedList<PaymentReportDTO>();
		for (Appointmentservicedetails appointmentservice : paymentList) {
			for (Billdetails billdetails : billdetailslist) {
					if(billdetails.getServicemaster()!= null && billdetails.getPackagemaster()==null && billdetails.getTaxstructureid()==null){
						if(i!=billdetails.getServicemaster().getServiceid())
						{
							PaymentReportDTO paymentReport = new PaymentReportDTO();
							paymentReport.setBillno(billdetails.getBillmaster().getBillno());
							paymentReport.setBillDate(billdetails.getBillmaster().getBilldate());
							paymentReport.setGuestName(appointmentservice.getAppointment().getGuestmaster().getName());
							paymentReport.setStaffName(appointmentservice.getStaffmaster().getStaffname());
							paymentReport.setServiceName(appointmentservice.getServicemaster().getServicename());
							paymentReport.setRoomno(appointmentservice.getRoommaster().getRoomno());
							for (Billsettlement billsettlement : billdetails.getBillmaster().getBillsettlements()) {
								paymentReport.setPaymentmode(billsettlement.getPaymodemaster().getPaymode());
								paymentReport.setGiftVoucherId(billsettlement.getGiftvouchermaster().getGiftvoucherid());
								paymentReport.setPaidAmount(billsettlement.getAmount());
								paymentReport.setPaymentDate(billsettlement.getUpdateddate());
								paymentReport.setCardno(billsettlement.getCreditcardnumber());
							}
							paymentReport.setDiscount(billdetails.getBillmaster().getDiscountamount());
							paymentReport.setTotalAmount(billdetails.getBillmaster().getAmount());
							list.add(paymentReport);
						}
						i=billdetails.getServicemaster().getServiceid();
					}
				}
			}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("paymentlist");
		mv.addObject("paymentreportlist",list);
		return mv;
	}
	
	@RequestMapping(value="/productsales")
	public ModelAndView productSalesList(HttpServletRequest req,HttpServletResponse res){
		List<Productsales> productSalesList=iReportService.productSalesList();
		List<Stockmaster> stockmaster = iReportService.stockmaster();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("productlist"); 
		mv.addObject("productSalesList",productSalesList);
		mv.addObject("stockmaster",stockmaster);
		return mv;
	}
	
	@RequestMapping(value="/searchproductsales")
	public ModelAndView getSearchProductSales(HttpServletRequest request,HttpServletResponse response)
	{
		 Date date1 = null;
		 Date date2 = null;
	    String fromDate  = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String stockName = request.getParameter("stockid");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}
		List<Productsales> productSalesList = iReportService.getProductSalesList(date1,date2,stockName);
		List<Stockmaster> stockmaster = iReportService.stockmaster();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("productlist");
		modelAndView.addObject("fromdate",fromDate);
		modelAndView.addObject("todate",toDate);
		modelAndView.addObject("stockName",stockName);
		modelAndView.addObject("productSalesList",productSalesList);
		modelAndView.addObject("stockmaster",stockmaster);
	    if(productSalesList.size()>0){
	    	return modelAndView;
	    }else{
	    	
	    	String msg="No records are Found";
	    	modelAndView.addObject("msg",msg);
	    	return modelAndView;
	    }
	}
	
	@RequestMapping(value="/generateproductsalesreport")
	public String generatepaymentSalesReport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @RequestParam("generateType") String generatetype){
		List<Map<String,?>> productSalesReportList = new LinkedList<Map<String,?>>();
		Date date1 = null;
		Date date2 = null;
	    String fromDate  = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String stockName = request.getParameter("stockname");
		String stockName1 = request.getParameter("stockname1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((fromDate!=null && !(fromDate.isEmpty())) && (toDate!=null && !(toDate.isEmpty())))
			{
				date1 = sdf.parse(fromDate);
				date2 = sdf.parse(toDate);
			}
		} catch (ParseException e) {
			e.printStackTrace(); 
		}		
		if(stockName1.equals("Select")){
			stockName1 = null;
		}
		Map<String,Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("fromdate", fromDate);
		map1.put("todate", toDate);
		if(stockName.equals("Select")){
			stockName = null;
		}
		map1.put("searchpaymentsales", stockName1);
		productSalesReportList.add(map1);
		List<Productsales> productSalesList = iReportService.getProductSalesList(date1,date2,stockName);
	    for (Productsales productsales : productSalesList) {
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			map.put("productsalesid", productsales.getProductsalesid());
			map.put("stockname1", productsales.getStockmaster().getStockname());
			map.put("quantity", productsales.getQuantity());
			map.put("totalamount", productsales.getTotalamount());
			map.put("billno", productsales.getBillmaster().getBillno());
			map.put("processdate", productsales.getUpdateddate());
			productSalesReportList.add(map);
		}
		modelMap.put("productSalesReportList",productSalesReportList);
		modelMap.put("generatetype",generatetype);
		return "productsalesreport";
		}                                                                         
	/*@RequestMapping(value="/RevenueReportSummary")
	public RevenueReportSummary(HttpServletRequest request,HttpServletReponse response,ModelMap modelmap,@RequestParam("generatetype") String generatetype){
		
	
	
	List<Map<String,?>> revenuesummaryreport=new LinkedList<Map<String,?>>();
	List<ServiceRevenueReport> revenuereports=iBillingservice.getreveneuereportlist();
	
	
	return "revenuesummaryreport";	
	
	}*/
	
	@RequestMapping(value="/guestservicesreport",method=RequestMethod.POST)
	public String guestServicesReports(HttpServletRequest request,HttpServletResponse response,ModelMap modelmap,@RequestParam("generate") String generatetype){
		
		String fromdate=request.getParameter("fromDate");
		String todate=request.getParameter("toDate");
		String[] selectedValues = request.getParameterValues("serviceNames");
		
		List<Appointmentservicedetails> appointmentservicedetails=iCustomersService.getGuestServicesSearch(fromdate,todate,selectedValues);
		System.out.println("report"+appointmentservicedetails.size());
		List guestservicelist=new LinkedList();
	
		
	for (Appointmentservicedetails appintment : appointmentservicedetails) {
		Map<String, Object> map2= new HashMap<String, Object>();
		
		map2.put("guestid",appintment. getAppointment().getGuestmaster().getGuestid());
		map2.put("name", appintment.getAppointment().getGuestmaster().getName());
		map2.put("servicename", appintment.getServicemaster().getServicename());
		map2.put("schappointdate", appintment.getSchappointdate());
		map2.put("email", appintment.getAppointment().getGuestmaster().getEmail());
		
		guestservicelist.add(map2);
	
	}
		modelmap.put("guestservicelist",guestservicelist);
		modelmap.put("generatetype",generatetype );
		return  "clientservicesreport";	
		
	}
	
	
	@RequestMapping(value="/guestemailreport",method=RequestMethod.POST)
	public String guestEmailReports(HttpServletRequest request,HttpServletResponse response,ModelMap modelmap,@RequestParam("generate") String generatetype){
		List guestemaillist=new LinkedList();
		List<Guestmaster> gmlist = iCustomersService.getGuestEmailList();
		
		for (Guestmaster guestmaster: gmlist) {
			Map<String, Object> map= new HashMap<String, Object>();
			
			map.put("guestid",guestmaster.getGuestid());
			map.put("name",guestmaster.getName());
			map.put("mobile",guestmaster.getMobile());
			map.put("email",guestmaster.getEmail());
			
			guestemaillist.add(map);
		}
		System.out.println(guestemaillist);
		modelmap.put("guestemaillist",guestemaillist);
		modelmap.put("generatetype",generatetype );
		return  "clientemailreport";	
		
	}
	
	}
