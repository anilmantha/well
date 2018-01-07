package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.iservice.IBillingService;
import com.leonia.wellness.iservice.IManualComplaintService;

@Controller
public class ManualComplaintsController {
	
	@Autowired
	private IManualComplaintService iManualComplaintService;
	
	@Autowired
	public IBillingService iBillingService;
	@RequestMapping("/billsbybilldate")
	public ModelAndView getBillsByBillDatePage(){
		Map guestList = iManualComplaintService.getInServiceGuestDetails();
		Map staffList = iManualComplaintService.getEmployeeDetails();
		List<Departmentmaster> departmentmasterList = iManualComplaintService.departmentDetails();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("billsbybilldate");
		mav.addObject("guestList", guestList);
		mav.addObject("employeeList", staffList);
		mav.addObject("departmentmasterList", departmentmasterList);
		return mav;
	}
	@RequestMapping(value ="/billdates",method = RequestMethod.GET)
	@ResponseBody
	public List getBillsByBillDate(HttpServletRequest req){
		
		String fromdate =req.getParameter("fromdate");
		String todate =req.getParameter("todate");
		String guestname =req.getParameter("guestname");
		Date fromDate = new Date();
		Date toDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fromDate = sdf.parse(fromdate);
			toDate = sdf.parse(todate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		List billMasterList = iManualComplaintService.getBillsByBillDate(fromDate,toDate,guestname);
	    List list = new ArrayList();
		Iterator iterator = billMasterList.iterator();
		while(iterator.hasNext()){
			Object object = iterator.next();
			Object oo[]=(Object[])object;
			Integer billNo =(Integer)oo[0];
			Date dd =(Date)oo[1];
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String stringdate = formatter.format(dd);
			BigDecimal amount =(BigDecimal)oo[2];
			String name=(String)oo[3];
			Integer guestId =(Integer)oo[4];
			list.add(billNo);
			list.add(stringdate);
			list.add(amount);
			list.add(name);
			list.add(guestId);
			System.out.println(billNo+"billNo$$$$$$$$$$");
			System.out.println(stringdate+"date#########");
			System.out.println(amount+"&&&&&&&&&&&&");
			System.out.println(name+"##########");
			System.out.println(guestId+"^^^^^^^^^^");
		}
		
		  return list;
	}

	@RequestMapping("/manualcomplaint")
	public ModelAndView getManualComplaintPage(HttpServletRequest req){
		 Integer billNo =Integer.parseInt(req.getParameter("billno"));
		 Billmaster billMaster =iManualComplaintService.getSelectedBill(billNo);
		 List<Departmentmaster> departmentmasterList = iManualComplaintService.departmentDetails();
		 System.out.println(billMaster.getBillno()+"$$$$$$$$$$$$$$");
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("manualcomplaint");
		 mav.addObject("billMaster", billMaster);
		 mav.addObject("departmentmasterList", departmentmasterList);
		 mav.addObject("msg",null);
		return mav;
	}
	@RequestMapping(value="/savemanualcomplaint",method=RequestMethod.POST)
	public ModelAndView saveManualComplaint(HttpServletRequest req,HttpServletResponse res){
		Integer guestId =Integer.parseInt(req.getParameter("guestId"));
		Integer billNo =Integer.parseInt(req.getParameter("billNo"));
		Integer departmentId =Integer.parseInt(req.getParameter("departmentId"));
		String complaint = req.getParameter("complaint");
		String remarks = req.getParameter("remarks");
		String msg =iManualComplaintService.saveManualComplaint(guestId,billNo,departmentId,complaint,remarks);
		ModelAndView mav = new ModelAndView();
		 mav.setViewName("manualcomplaint");
		 mav.addObject("billMaster", null);
		 mav.addObject("msg",msg);
		 return mav;
	}
	@RequestMapping(value="/savemanualcomplaint123")
	public ModelAndView saveManualComplaint123(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manualcomplaint");
		 return mav;
	}
	@RequestMapping(value="/savemanualcomplaintbyadmin",method=RequestMethod.POST)
	public ModelAndView saveManualComplaintByAdmin(HttpServletRequest req,HttpServletResponse res){
		String emp = req.getParameter("employeeId");
		Integer empId =null;
		if(emp!=null){
			empId = Integer.parseInt(emp);
		}
		Integer departmentId =Integer.parseInt(req.getParameter("department"));
		String complaint = req.getParameter("complaint");
		String remarks = req.getParameter("remarks");
		String msg =iManualComplaintService.saveManualComplaintByAdmin(empId,departmentId,complaint,remarks);
		ModelAndView mav = new ModelAndView();
		 mav.setViewName("billsbybilldate");
		 mav.addObject("billMaster", null);
		 mav.addObject("msg",msg);
		 return mav;
	}
	@RequestMapping(value="/savemanualcomplaintbyinhouseguest",method=RequestMethod.POST)
	public ModelAndView saveManualComplaintByInHouseGuest(HttpServletRequest req,HttpServletResponse res){
		String guest = req.getParameter("inhouseguest1");
		Integer inHouseGuestId =null;
		if(guest!=null){
			inHouseGuestId = Integer.parseInt(guest);
		}
		String service = req.getParameter("serviceId");
		Integer serviceId =null;
		if(service!=null){
			serviceId =Integer.parseInt(service);
		}
		String department =req.getParameter("indepartment");
		Integer departmentId =null;
		if(department!=null){
			departmentId =Integer.parseInt(department);
		}
		String complaint = req.getParameter("incomplaint");
		String remarks = req.getParameter("inremarks");
		String msg =iManualComplaintService.saveManualComplaintByInHouseGuest(inHouseGuestId,serviceId,departmentId,complaint,remarks);
		ModelAndView mav = new ModelAndView();
		 mav.setViewName("billsbybilldate");
		 mav.addObject("billMaster", null);
		 mav.addObject("msg",msg);
		 return mav;
	}
	@RequestMapping("/empdep")
	@ResponseBody
	public List getEmployeeDepIdDesignation(@RequestParam("empId") Integer empId){
		List empDepDes =iManualComplaintService.getEmployeeDepIdDesignation(empId);
		return empDepDes;
	}
	@RequestMapping("/guestservices")
	@ResponseBody
	public List getGuestservices(@RequestParam("guestId") Integer guestId){
		List guestServices =iManualComplaintService.getGuestservices(guestId);
		
		return guestServices;
	}
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
}
