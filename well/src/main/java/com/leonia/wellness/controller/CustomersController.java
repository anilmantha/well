package com.leonia.wellness.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Statemaster;
import com.leonia.wellness.iservice.ICustomersService;

@Controller
public class CustomersController {
	
	
	@Autowired
	public ICustomersService iCustomersService;
	
	@RequestMapping(value = "/customersave",method = RequestMethod.POST)
	public ModelAndView saveCustomerRegistration(@ModelAttribute Guestmaster guestMaster,BindingResult result,HttpServletRequest request){
		if(request.getParameter("segmentmaster")!= ""){
		Segmentmaster segment = new Segmentmaster();
		segment.setSegmentid(Integer.parseInt(request.getParameter("segmentmaster")));
		guestMaster.setSegmentmaster(segment);
		}
		if(request.getParameter("corporate")!=""){
		    Corporatemaster corporateMaster = new Corporatemaster();
		    corporateMaster.setCorporateid(Integer.parseInt(request.getParameter("corporate")));
		    guestMaster.setCorporatemaster(corporateMaster);
		}
		if(request.getParameter("businessSource")!=""){
		Businesssourcemaster businesssourcemaster = new Businesssourcemaster();
		businesssourcemaster.setBusinesssourceid(Integer.parseInt(request.getParameter("businessSource")));
		guestMaster.setBusinesssourcemaster(businesssourcemaster);
		}
		if(request.getParameter("billingInstruction")!=""){
		Billinginstructionmaster billinginstructionmaster = new Billinginstructionmaster();
		billinginstructionmaster.setBillinginstructionid(Integer.parseInt(request.getParameter("billingInstruction")));
		guestMaster.setBillinginstructionmaster(billinginstructionmaster);
		}
		if(request.getParameter("city")!=""){
		Citymaster citymaster = new Citymaster();
		citymaster.setCityid(Integer.parseInt(request.getParameter("city")));
		guestMaster.setCitymaster(citymaster);
		}
		if(request.getParameter("state")!=""){
		Statemaster statemaster = new Statemaster();
		statemaster.setStateid(Integer.parseInt(request.getParameter("state")));
		guestMaster.setStatemaster(statemaster);
		}
		if(request.getParameter("country")!=""){
		Countrymaster countrymaster = new Countrymaster();
		countrymaster.setCountryid(Integer.parseInt(request.getParameter("country")));
		guestMaster.setCountrymaster(countrymaster);
		}
		
		Dropdowndetails titleddd = new Dropdowndetails();
		titleddd.setDropdowndetailsid(Integer.parseInt(request.getParameter("title")));
		guestMaster.setDropdowndetailsByTitleid(titleddd);
		
		
		Dropdowndetails genderddd = new Dropdowndetails();
		genderddd.setDropdowndetailsid(Integer.parseInt(request.getParameter("gender")));
		guestMaster.setDropdowndetailsByGenderid(genderddd);
		
		
		String date=request.getParameter("dob");
		
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date1 = sdf.parse(date);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		
		guestMaster.setDob(date1);
		
		String name = guestMaster.getName();
		iCustomersService.saveCustomerDetails(guestMaster);
		return new ModelAndView("booking-selection","name",name);
	}
	@RequestMapping(value = "/onlyregistration",method = RequestMethod.POST)
	public ModelAndView onlyRegistration(@ModelAttribute Guestmaster guestMaster,BindingResult result,HttpServletRequest request){
		if(request.getParameter("segmentmaster")!= ""){
		Segmentmaster segment = new Segmentmaster();
		segment.setSegmentid(Integer.parseInt(request.getParameter("segmentmaster")));
		guestMaster.setSegmentmaster(segment);
		}
		if(request.getParameter("corporate")!=""){
		    Corporatemaster corporateMaster = new Corporatemaster();
		    corporateMaster.setCorporateid(Integer.parseInt(request.getParameter("corporate")));
		    guestMaster.setCorporatemaster(corporateMaster);
		}
		if(request.getParameter("businessSource")!=""){
		Businesssourcemaster businesssourcemaster = new Businesssourcemaster();
		businesssourcemaster.setBusinesssourceid(Integer.parseInt(request.getParameter("businessSource")));
		guestMaster.setBusinesssourcemaster(businesssourcemaster);
		}
		if(request.getParameter("billingInstruction")!=""){
		Billinginstructionmaster billinginstructionmaster = new Billinginstructionmaster();
		billinginstructionmaster.setBillinginstructionid(Integer.parseInt(request.getParameter("billingInstruction")));
		guestMaster.setBillinginstructionmaster(billinginstructionmaster);
		}
		if(request.getParameter("city")!=""){
		Citymaster citymaster = new Citymaster();
		citymaster.setCityid(Integer.parseInt(request.getParameter("city")));
		guestMaster.setCitymaster(citymaster);
		}
		if(request.getParameter("state")!=""){
		Statemaster statemaster = new Statemaster();
		statemaster.setStateid(Integer.parseInt(request.getParameter("state")));
		guestMaster.setStatemaster(statemaster);
		}
		if(request.getParameter("country")!=""){
		Countrymaster countrymaster = new Countrymaster();
		countrymaster.setCountryid(Integer.parseInt(request.getParameter("country")));
		guestMaster.setCountrymaster(countrymaster);
		}
		
		Dropdowndetails titleddd = new Dropdowndetails();
		titleddd.setDropdowndetailsid(Integer.parseInt(request.getParameter("title")));
		guestMaster.setDropdowndetailsByTitleid(titleddd);
		
		
		Dropdowndetails genderddd = new Dropdowndetails();
		genderddd.setDropdowndetailsid(Integer.parseInt(request.getParameter("gender")));
		guestMaster.setDropdowndetailsByGenderid(genderddd);
		
		
		String date=request.getParameter("dob");
		
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date1 = sdf.parse(date);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		
		guestMaster.setDob(date1);
		
		
		iCustomersService.saveCustomerDetails(guestMaster);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registration");
		mav.addObject("msg", "Registration is completed");
		return mav;
	}
	@RequestMapping("/registration")
	public ModelAndView registrationPage(){
		Map<Integer,String> segment = iCustomersService.getSegment();
		Map<Integer,String> corporate = iCustomersService.getCorporate();
		Map<Integer,String> businessSource = iCustomersService.getBusinessSource();
		Map<Integer,String> billingInformation = iCustomersService.getBillingInformation();
		Map<Integer,String> city = iCustomersService.getCity();
		Map<Integer,String> state = iCustomersService.getState();
		Map<Integer,String> country = iCustomersService.getCountry();
		Map<Integer,String> title = iCustomersService.getTitle();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registration");
		mav.addObject("segment", segment);
		mav.addObject("corporate", corporate);
		mav.addObject("businessSource", businessSource);
		mav.addObject("billingInformation", billingInformation);
		mav.addObject("city", city);
		mav.addObject("state", state);
		mav.addObject("country", country);
		mav.addObject("title", title);
		return mav;
	}
	
	@RequestMapping(value="customerlist",method=RequestMethod.POST)
	public ModelAndView getCustomerSearch(HttpServletRequest request,@RequestParam("firstname") String name, HttpServletResponse response){
		List list=iCustomersService.getGuestSearch(name);
		ModelAndView mv = new ModelAndView();
		
		if(list.size()==0)
		{
			mv.addObject("msg","There are no guests with the searched element: "+name);
		}
		mv.setViewName("home");
		mv.addObject("records",list);
		mv.addObject("searchedName",name);
		return	mv;
	}
	
	
	@RequestMapping(value="/DOBSearchPage",method=RequestMethod.GET)
	public String geuestDOBSearch(){
		
		return "DOBSearch";
		
	}
	
	@RequestMapping(value="/guestdobsearch",method=RequestMethod.GET)
	@ResponseBody
	public List getCustomerDOBSearch(Guestmaster guestmaster,HttpServletRequest request) {
		
		String month =request.getParameter("month");
		String fromdate =request.getParameter("fromDate");
 	   	String todate =request.getParameter("toDate");

 	   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+month);
 	   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+fromdate);
 	   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+todate);
 	 List guestdetails = new LinkedList();
		List guestDoblist=iCustomersService.getSearchCustomerDOB(month,fromdate,todate);
		System.out.println("!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!!!!!!!!!!!!!!!!!!"+guestDoblist);
		Iterator ie = guestDoblist.iterator();
		
		while (ie.hasNext()) {
			Object[] guestmaster2 = (Object[]) ie.next();
			
			int id = (int) guestmaster2[0];
			String name = (String) guestmaster2[1];
			 Date dob = (Date) guestmaster2[2];
			 BigInteger mobile = (BigInteger) guestmaster2[3];
			String  email=(String) guestmaster2[4];
			 Date  lastvisitdate = (Date) guestmaster2[5];
			int noofvisits = (int) guestmaster2[6];
			guestdetails.add(id);
			guestdetails.add(name);
			guestdetails.add(dob);
			guestdetails.add(mobile);
			guestdetails.add(email);
			guestdetails.add(lastvisitdate);
			guestdetails.add(noofvisits);
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+id);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+name);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+dob);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+mobile);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+email);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+noofvisits);
			
		}

		return guestdetails;
		
		}
	@RequestMapping(value="/guestweddingsearchPage",method=RequestMethod.GET)
	public String guestWeddingDateSearch(){
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		return "weddingSearch";
	}
	
	
	
	@RequestMapping(value="/WeddingDateSearch",method=RequestMethod.GET)
	@ResponseBody
	public List getCustomerWeddingList(HttpServletRequest request,HttpServletResponse response)
	
	{
		System.out.println("data");
		String month=request.getParameter("month");
		String fromdate=request.getParameter("fromDate");
		String todate=request.getParameter("toDate");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+month);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+fromdate);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!"+todate);
		
		List guestweddingdetails=new LinkedList<>();
		List guestweddingdetails1=iCustomersService.getSearchGuestWedding(month,fromdate,todate);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+guestweddingdetails1);
		Iterator it=guestweddingdetails1.iterator();
		while(it.hasNext()){
			Object[] weddingList =(Object[])it.next();
			int id=(int)weddingList[0];
			String name=(String)weddingList[1];
			Date weddingday=(Date)weddingList[2];
			BigInteger mobile=(BigInteger)weddingList[3];
			String email=(String)weddingList[4];
			Date lastvisits=(Date)weddingList[5];
			int noofvisits=(int)weddingList[6];
			System.out.println(id);
			System.out.println(name);
			System.out.println(weddingday);
			System.out.println(mobile);
			System.out.println(email);
			System.out.println(lastvisits);
			System.out.println(noofvisits);
			
			guestweddingdetails.add(id);
			guestweddingdetails.add(name);
			guestweddingdetails.add(weddingday);
			guestweddingdetails.add(mobile);
			guestweddingdetails.add(email);
			guestweddingdetails.add(lastvisits);
			guestweddingdetails.add(noofvisits);
			
			}
		
		return guestweddingdetails;
		}
	
	
	@RequestMapping(value="/guestServicePage",method=RequestMethod.GET)
	public ModelAndView guestPackagesPage(){
		return new ModelAndView("guestServiceSearch","servicenames",null);
		}
	

	
	@RequestMapping(value="/guestServicesSearch",method=RequestMethod.POST)
	public ModelAndView getGuestServicesSearch(HttpServletRequest request,HttpServletResponse response,ModelMap modelmap,@RequestParam("generatetype") String generatetype){
		ModelAndView mv = new ModelAndView();
		String fromdate=request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		String[] servicenames = request.getParameterValues("serviceNames");
		List<Appointmentservicedetails> appointmentservicedetails=iCustomersService.getGuestServicesSearch(fromdate,todate,servicenames);
		if(generatetype.equals("select")){
			mv.setViewName("guestServiceSearch");
			if(appointmentservicedetails.size()>0){
				mv.addObject("appointmentservicelist", appointmentservicedetails);
			}
			else
			{
				mv.addObject("message","records not found");
			}
			mv.addObject("fromdate", fromdate);
			mv.addObject("todate", todate);
			mv.addObject("servicenames", servicenames);
			return 	mv;
			}
			else{
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
					mv.setViewName("clientservicesreport");
					mv.addObject("guestservicelist",guestservicelist);
					mv.addObject("generatetype",generatetype );
					return  mv;	
			}
	}
	@RequestMapping(value="/guestEmailPage",method=RequestMethod.GET)
	public ModelAndView gusetEmailList(){
	
		List gmlist=iCustomersService.getGuestEmailList();
		System.out.println("!!!!!!!!!!!!!!!!");
		//return "guestemailist";
		return new ModelAndView("guestemailist","guemali",gmlist);
		}
	
	

	}
