/*package com.leonia.wellness.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Reasonschildmaster;
import com.leonia.wellness.entity.Reasonsmaster;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.iservice.IReasonsMasterService;
import com.leonia.wellness.iservice.IStockService;

@Controller
public class ReasonsMasterController {
	@Autowired
	public IReasonsMasterService iReasonsMasterService;
	@RequestMapping("/appointmentcancelreason")
	public ModelAndView getAppointmentCancelReasons(ModelMap model){
		List<Reasonsmaster> reasonsList=iReasonsMasterService.getAppointmentCancelReasons();
		model.addAttribute("reasonlist", reasonsList);
		return new ModelAndView("appointmentcancelreason");
	}
	@RequestMapping("/listofreasons")
	@ResponseBody
	public List getReasonsList(HttpServletRequest request,HttpServletResponse response,@RequestParam("reasonchildmasterId") String reasonchildmasterid){
		int id = Integer.parseInt(reasonchildmasterid);
		List reasonlist = new LinkedList();
		Set<Reasonschildmaster> reasons=iReasonsMasterService.getReasonsList(id);
		for (Reasonschildmaster reasonschildmaster : reasons) {
			if(reasonschildmaster.isValid())
			{
				reasonlist.add(reasonschildmaster.getReasonschildmasterid());
				reasonlist.add(reasonschildmaster.getDescription());
			}
		}
		
		return reasonlist;
		
	}
	@RequestMapping(value="/savereason")
	@ResponseBody
	public String saveReason(HttpServletRequest request,HttpServletResponse response){
		String reason=request.getParameter("description");
	
		Integer reasonchildmasterId=Integer.parseInt(request.getParameter("reasonchildmasterId"));
		System.out.println(reason);
		iReasonsMasterService.saveReason(reason,reasonchildmasterId);
		
		return "appointmentcancelreason";
	}
	@RequestMapping(value="/updatereason")
	@ResponseBody
	public String reasonUpdate(HttpServletRequest request,HttpServletResponse response){
		Integer reasonchildmasterId=Integer.parseInt(request.getParameter("reasonchildmasterId"));
		String reason=request.getParameter("description");
		Integer reasonid=Integer.parseInt(request.getParameter("reasonid"));
		iReasonsMasterService.reasonUpdate(reasonchildmasterId,reasonid,reason);
		return "appointmentcancelreason";
	}
	@RequestMapping(value="/deletereason")
	@ResponseBody
	public String reasonDelete(HttpServletRequest request,HttpServletResponse response){
		Integer reasonchildmasterId=Integer.parseInt(request.getParameter("reasonchildmasterId"));
		String reason=request.getParameter("description");
		Integer reasonid=Integer.parseInt(request.getParameter("reasonid"));
		iReasonsMasterService.reasonDelete(reasonchildmasterId,reasonid,reason);
		return "appointmentcancelreason";
	}
	
		
}
*/