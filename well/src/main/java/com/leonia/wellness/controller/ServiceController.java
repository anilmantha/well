package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.ServiceStockQuantity;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.iservice.IServicesService;
import com.leonia.wellness.iservice.IStockService;

@Controller
public class ServiceController {
	
	@Autowired
	private IServicesService iServicesService;
	
	@Autowired
	private IStockService iStockService;
	
	@RequestMapping("/serviceStockNewPage")
	public ModelAndView openServiceStockPage(){
		
		List<Stockmaster> stockDetails = iStockService.getStockDetails();
		List<Servicemaster> serviceslist = iServicesService.getServicesUnAddedStock();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("servicestocknewpage");
		mv.addObject("listofservices",serviceslist);
		mv.addObject("stockList",stockDetails);
		return mv;
	}
	
	@RequestMapping(value = "/getStockId", method = RequestMethod.GET)
	@ResponseBody
	public Integer getsubgroupDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stockname") String stockname) {
		Integer stockid = iServicesService.getstockid(stockname);
		
		return stockid;
	}
	
	@RequestMapping(value="/saveservicestock", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String saveServiceStock(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ServiceStockQuantity> ObjectArray){
		iServicesService.saveservicestock(ObjectArray);
		return "Successfully Saved";
	}
	
	@RequestMapping("/serviceStockList")
	public ModelAndView openServiceStockListPage(){
		List<Servicemaster> serviceslist = iServicesService.getServicesToBeEdited();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("servicestocklist");
		mv.addObject("listofexistedstockservices",serviceslist);
		return mv;
	}
	
	@RequestMapping(value="/getStockUsage", method = RequestMethod.GET)
	@ResponseBody
	public  List getStockUsage(HttpServletRequest request, HttpServletResponse response, @RequestParam("serviceId") Integer serviceId){
		Set<Servicestocksmaster> serviceStockmaster = iServicesService.getStockUsage(serviceId);
		List list = new LinkedList();
		Iterator<Servicestocksmaster> iestock = serviceStockmaster.iterator();
		BigDecimal stockus = null;
		String stockname = null;
		Integer stockid=null;
		while (iestock.hasNext()){
			
			Servicestocksmaster ssm = iestock.next();
			if(ssm.isValid())
			{
				stockus=ssm.getStockusage();
				stockname=ssm.getStockmaster().getStockname();
				stockid=ssm.getStockmaster().getStockid();
				list.add(stockus);
				list.add(stockname);
				list.add(stockid);
			}
			
		}
		return list;
	}
	
	@RequestMapping(value="/updateservicestock", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String updateServiceStock(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ServiceStockQuantity> ObjectArray){
		iServicesService.updateservicestock(ObjectArray);
		return "Successfully updated";
	}
	
	@RequestMapping(value="/deleteservicestock", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String deleteServiceStock(HttpServletRequest request, HttpServletResponse response, @RequestBody List<ServiceStockQuantity> ObjectArray){
		iServicesService.deleteservicestock(ObjectArray);
		return "Successfully deleted";
	}
}
