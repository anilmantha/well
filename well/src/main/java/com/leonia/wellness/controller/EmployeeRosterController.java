package com.leonia.wellness.controller;

import java.util.LinkedList;
import java.util.List;

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

import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.iservice.IEmployeeRosterService;

	@Controller
	public class EmployeeRosterController {
		
	@Autowired
	public IEmployeeRosterService iEmployeeService;

	@RequestMapping(value = "/stafflist")
	public ModelAndView openRosterPage(HttpServletRequest request, HttpServletResponse response) {
		List<Staffmaster> staffmasterlist = iEmployeeService.getStaffmaster();

		return new ModelAndView("employeeRoster", "staffmasterlist", staffmasterlist);
	}
	
	@RequestMapping(value = "/searchstaffname123", method = RequestMethod.GET)
	@ResponseBody
	public List searchStaffName(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("staffname") String staffname) {
		System.out.println("###############");
		
		List<Staffmaster> staffNamelist = iEmployeeService.getSearchStaffName(staffname);
		
		System.out.println("ContrllerstaffName:" + staffNamelist.size());
		
		List list = new LinkedList<Staffmaster>();
		
		for (Staffmaster stafflist : staffNamelist) {
			list.add(stafflist.getStaffid());
			list.add(stafflist.getStaffname());
			list.add(stafflist.getDepartmentmaster().getDepartmentname());
			System.out.println("search controller");
		}
		
		System.out.println("@@@@@@@" + list);

		return list;
		
		}
	
	@RequestMapping("/fetchselectedstaff")
	 @ResponseBody
	 public ModelAndView fetchSelectedStaff(@RequestParam("staffid") Integer staffid){
		 Staffmaster staffname = iEmployeeService.fetchSelectedStaff(staffid);
		 System.out.println(staffname.getStaffid()+"staffid%%%%%%");
		 System.out.println(staffname.getStaffname()+"staffname^^^^^^");
		 System.out.println(staffname.getDepartmentmaster().getDepartmentname()+"depatname***");
		 ModelAndView mav = new ModelAndView();
		mav.setViewName("createroster");
		mav.addObject("staffname1",staffname);
		 return mav;
	 }
	
	@RequestMapping("/addRooster")
	@ResponseBody
	public List<Integer> addRooster(HttpServletRequest request){
		
		Integer numberofdays = null;
		
		Integer staffId=Integer.parseInt(request.getParameter("staffId"));
		
		Integer slemonth=Integer.parseInt(request.getParameter("slemonth"));
		
		Integer year=Integer.parseInt(request.getParameter("year"));
		
		String weekDay=request.getParameter("weekDay");
		
		if(slemonth == 4 || slemonth == 6 || slemonth == 9 || slemonth == 11)
			
		{
			
			numberofdays = 30;
		}
		
		else {
			
			if(slemonth == 2)
				
			{
				if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
					
				{
					numberofdays = 29;
				}
				
				else{
					
					numberofdays = 28;
				}
			}
			
			else{
				
				numberofdays = 31;
			
			}
			
		}
		
		String response=request.getParameter("response");
		
		if(response == null || response.equals("")){
			
		int a=iEmployeeService.insetrtRoster(staffId,slemonth,year,weekDay,numberofdays);
		
		}
		
		List<Integer> resultMap=iEmployeeService.getRosterStatus(staffId,slemonth,year);
		
		System.out.println(resultMap);
		
		return resultMap;
	 }
	
	@RequestMapping("/checkmonthyear")
	@ResponseBody
	public String checkmonthyear(HttpServletRequest request){
		
		Integer staffId=Integer.parseInt(request.getParameter("staffId"));
		Integer slemonth=Integer.parseInt(request.getParameter("slemonth"));
		Integer year=Integer.parseInt(request.getParameter("year"));
		
		String a=iEmployeeService.checkmonthyear(staffId,slemonth,year);
		
		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIII"+a);
		
		return a;
	 }
	
	@RequestMapping(value="/UpdateRoster", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String UpdateRoster(HttpServletRequest request,@RequestBody List<Integer> updateArr){
		
	System.out.println("!!!!!!!!!!!$$$$$$$$"+updateArr);
		
		
		Integer staffId=Integer.parseInt(request.getParameter("staffId"));
		
		Integer slemonth=Integer.parseInt(request.getParameter("slemonth"));
		
		Integer year=Integer.parseInt(request.getParameter("year"));
		
		String weekDay=request.getParameter("weekDay");
		
		for (Integer integer : updateArr){
			
			System.out.println("Roster :::::  "+integer);
			
			}
		
		String a=iEmployeeService.UpdateRoster(staffId,slemonth,year,updateArr,weekDay);
		
		return a;
	 }
	
	}
