package com.leonia.wellness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.iservice.IDoctorAdviceService;

@Controller
public class DoctorAdviceController {
	
	@Autowired
	public IDoctorAdviceService idoctorService;
	
	
	
	@RequestMapping(value="/doctoradvicelist")
	public String doctrAdviceList(@ModelAttribute Appointment appointment, Map<String, Object> model,ModelMap modelMap,HttpServletRequest request)
	
	{
		List<AppointmentService> customerList=idoctorService.doctrAdviceList();
   		model.put("customerList", customerList);
   		System.out.println("#!@!@#!#!@!@!#!#!#!@!#!@!#!@!#"+customerList);
		return "doctoradvicelist";
	}
	
	
	
	
	@RequestMapping(value="searchdoctrAdviceList",method=RequestMethod.POST)
	public ModelAndView searchdoctrAdviceList(@ModelAttribute Appointment appointment, Map<String, Object> model,ModelMap modelMap,HttpServletRequest request)
	
	{
		
		String guestname = request.getParameter("firstname");
		List<AppointmentService> customerList=idoctorService.searchdoctrAdviceList(guestname);
		
		
        ModelAndView mv = new ModelAndView();
		if(customerList.size()==0)
		{
			mv.addObject("msg","There are no guests with the searched element: "+guestname);
		}
		
		mv.setViewName("doctoradvicelist");
		mv.addObject("customerList",customerList);
		mv.addObject("searchedName",guestname);
		return	mv;
		
		
   		/*model.put("customerList", customerList);
   		System.out.println("#!@!@#!#!@!@!#!#!#!@!#!@!#!@!#"+customerList);
		return "doctoradvicelist";*/
	}
	
	
	
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value="/doctoradvice")
	public String doctrAdvice(@ModelAttribute Appointment appointment, Map<String, Object> model,ModelMap modelMap,HttpServletRequest request)
	
	{
		 String appId = request.getParameter("appId");
		 String srvId = request.getParameter("srvId");
		 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+srvId);
		 List<List> mainList = new ArrayList();
		 List clientInfo = idoctorService.clientInfo(appId);
		 List servicestockList =  idoctorService.doctrAdvice(appId,srvId);
		 mainList.add(clientInfo);
		 mainList.add(servicestockList);
  		 model.put("mainList", mainList);
		 return "doctoradvice";
		
	}
	
	@RequestMapping("/getServiceName")
	@ResponseBody
	public List getServiceName(Map<String, Object> model,ModelMap modelMap,HttpServletRequest request, @RequestParam("srvid") Integer srvid){
		List ServiceList = idoctorService.getServiceName(srvid);
		return ServiceList;
	}
	
	
	@RequestMapping("/fetchStockNames")
	@ResponseBody
	public List fetchStockNames(){
		List stockdetails = idoctorService.fetchStockNames();
		
		return stockdetails;
	}
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/stockDetailInfo", method=RequestMethod.GET)
	@ResponseBody
	public List getStockDetails(Map<String, Object> model,ModelMap modelMap,HttpServletRequest request, @RequestParam("stockName") String stockname)
	{
		List stockDeatilsList = idoctorService.getStockDetails(stockname);
		System.out.println(stockDeatilsList);
		 model.put("stockDeatilsList", stockDeatilsList);
		return stockDeatilsList;
     }
	
	
	
	@RequestMapping(value="/deleteStock", method=RequestMethod.GET)
	@ResponseBody
	public Integer deleteStock(Map<String, Object> model,ModelMap modelMap,HttpServletRequest request, @RequestParam("id") String id)
	{
		int stockDeatilsList = idoctorService.deleteStock(id);
		System.out.println(stockDeatilsList);
		 model.put("stockDeatilsList", stockDeatilsList);
		return stockDeatilsList;
     }

	
	
	
	
	
	
	/*
	@RequestMapping(value="/addStock", method=RequestMethod.GET)
	@ResponseBody
	public Integer addStock(Map<String, Object> model,ModelMap modelMap,HttpServletRequest request)
	{
		//, @RequestParam("appid") Integer appid, @RequestParam("srvid") Integer srvid, @RequestParam("stkid") Integer stkid, @RequestParam("stockreq") Double stockreq
		
		String api = request.getParameter("appid");
		String srpi = request.getParameter("srvid");
		String skpi = request.getParameter("stkid");
		String str = request.getParameter("stockreq");
		
		int appid = Integer.parseInt(api);
		int srvid = Integer.parseInt(srpi);
		int stkid = Integer.parseInt(skpi);
		double stockreq = Double.parseDouble(str);
		
		System.out.println("appid"+appid);
		System.out.println("srvid"+srvid);
		System.out.println("stkid"+stkid);
		System.out.println("stockreq"+stockreq);
		
		int stockDeatilsList = idoctorService.addStock(appid,srvid,stkid,stockreq);
		System.out.println(stockDeatilsList);
		 model.put("stockDeatilsList", stockDeatilsList);
		return 1;
     }*/
	
	@RequestMapping(value="/addStock", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public int savegroupRegistrationCheckin(@RequestBody List<Appointmentstockconsumption> ObjArry,HttpServletRequest request)
	{
		
		System.out.println(ObjArry.size());
		int stockDeatilsList = idoctorService.addStock(ObjArry);
		return 0;
	    
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/searchAppointment", method=RequestMethod.GET)
	@ResponseBody
	public List getSearchAppointements(HttpServletRequest request, HttpServletResponse response, @RequestParam("guestName") String name)
	{
		System.out.println("######################################################################################################################");
		List appointmentDeatilsList = idoctorService.getSearchAppointements(name);
		return appointmentDeatilsList;
     }
	
	
	
}
