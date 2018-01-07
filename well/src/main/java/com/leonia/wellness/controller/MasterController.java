package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.dto.StaffSkillsData;
import com.leonia.wellness.dto.TaxDetails;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Corporatetypemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Giftvouchermaster;
import com.leonia.wellness.entity.Marketingvouchermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Packageratemaster;
import com.leonia.wellness.entity.Packageservices;
import com.leonia.wellness.entity.Productratemaster;
import com.leonia.wellness.entity.Qualificationmaster;
import com.leonia.wellness.entity.Reasondetails;
import com.leonia.wellness.entity.Reasonmaster;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Servicegroupmaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Serviceratemaster;
import com.leonia.wellness.entity.Serviceroommaster;
import com.leonia.wellness.entity.Servicestaffmaster;
import com.leonia.wellness.entity.Skillmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Staffskill;
import com.leonia.wellness.entity.Statemaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Taxmaster;
import com.leonia.wellness.entity.Taxstructuremaster;
import com.leonia.wellness.iservice.IMasterService;

@Controller
public class MasterController {
	@Autowired
	public IMasterService iMasterService;

	@RequestMapping("/appointmentcancelreason")
	public ModelAndView getAppointmentCancelReasons(ModelMap model) {
		List<Reasonmaster> reasonsList = iMasterService.getAppointmentCancelReasons();
		model.addAttribute("reasonlist", reasonsList);
		return new ModelAndView("appointmentcancelreason");
	}

	@RequestMapping("/listofreasons")
	@ResponseBody
	public List getReasonsList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("reasondetailsid") String reasonchildmasterid) {
		int id = Integer.parseInt(reasonchildmasterid);
		List reasonlist = new LinkedList();
		Set<Reasondetails> reasons = iMasterService.getReasonsList(id);
		for (Reasondetails reasondetails : reasons) {
			if (reasondetails.isValid()) {
				reasonlist.add(reasondetails.getReasondetailsid());
				reasonlist.add(reasondetails.getReasondescription());
			}
		}

		return reasonlist;

	}

	@RequestMapping(value = "/savereason")
	@ResponseBody
	public String saveReason(HttpServletRequest request, HttpServletResponse response) {
		String reason = request.getParameter("description");

		Integer reasonchildmasterId = Integer.parseInt(request.getParameter("reasondetailsid"));

		String message=iMasterService.saveReason(reason, reasonchildmasterId);

		return message;
	}

	@RequestMapping(value = "/updatereason")
	@ResponseBody
	public String reasonUpdate(HttpServletRequest request, HttpServletResponse response) {
		Integer reasonchildmasterId = Integer.parseInt(request.getParameter("reasondetailsid"));
		String reason = request.getParameter("description");
		Integer reasonid = Integer.parseInt(request.getParameter("reasonid"));
		String message=iMasterService.reasonUpdate(reasonchildmasterId, reasonid, reason);
		return message;
	}

	@RequestMapping(value = "/deletereason")
	@ResponseBody
	public String reasonDelete(HttpServletRequest request, HttpServletResponse response) {
		Integer reasonchildmasterId = Integer.parseInt(request.getParameter("reasondetailsid"));
		String reason = request.getParameter("description");
		Integer reasonid = Integer.parseInt(request.getParameter("reasonid"));
		String message=iMasterService.reasonDelete(reasonchildmasterId, reasonid, reason);
		return message;
	}

	@RequestMapping(value = "/openserviceratepage")
	public ModelAndView getServices(ModelMap model) {
		List<Servicemaster> servicesList = iMasterService.getServices();
		List<Taxstructuremaster> taxStructureList = iMasterService.getTaxStructure();
		List<Serviceratemaster> serviceRateList = iMasterService.serviceRateList();	
		model.addAttribute("taxstructurelist", taxStructureList);
		model.addAttribute("serviceslist", servicesList);
		model.addAttribute("serviceRateList", serviceRateList);
		return new ModelAndView("serviceratemaster");
	}

	@RequestMapping(value = "/saveservicerate")
	@ResponseBody
	public String saveServiceRate(HttpServletRequest request, HttpServletResponse response,
			Serviceratemaster servicerate) {
		Date date = new Date();
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		Integer taxStructureId = Integer.parseInt(request.getParameter("taxstructureid"));
		String applicabledate = request.getParameter("applicableDate");
		BigDecimal serviceCost = new BigDecimal(request.getParameter("servicecost"));
		servicerate.setServicecost(serviceCost);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(applicabledate);
			servicerate.setApplicabledate(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message=iMasterService.saveServiceRate(serviceId, taxStructureId, servicerate);
		return message;
	}
	@RequestMapping(value="/searchServiceRate")
	public ModelAndView searchServiceRate(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String serviceName=request.getParameter("serviceName");
		String listmessage = null;
		List<Serviceratemaster> serviceRateList=iMasterService.searchserviceRateList(serviceName);
		if(serviceRateList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+serviceName;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchservicerate", serviceName);
		model.addAttribute("serviceRateList",serviceRateList);
		
		return new ModelAndView("serviceratemaster");
	}

	@RequestMapping(value = "/tax")
	public ModelAndView tax() {
		List<Taxmaster> taxList = iMasterService.taxList();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("taxmaster");
		mv.addObject("taxList",taxList);
		return mv;
	}

	@RequestMapping(value = "/savetax")
	@ResponseBody
	public String saveTax(HttpServletRequest request, HttpServletResponse response,Taxmaster tax) {
		String taxName = request.getParameter("taxname");
		tax.setTaxname(taxName);
		String message=iMasterService.saveTax(tax,taxName);

		return message;
	}
	

	@RequestMapping(value="/editTax",method=RequestMethod.POST)
	public ModelAndView editTax(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer taxId = Integer.parseInt(request.getParameter("taxmasterid"));
		String taxName=request.getParameter("taxname");
		Taxmaster tax=new Taxmaster();
		tax.setTaxmasterid(taxId);;
		tax.setTaxname(taxName);;
		String message = iMasterService.editTax(tax);
		List<Taxmaster> taxList = iMasterService.taxList();
		model.addAttribute("taxList",taxList);
		model.addAttribute("message", message);
		return new ModelAndView("taxmaster");
	}
	@RequestMapping(value="/deleteTax",method=RequestMethod.POST)
	public ModelAndView deleteTax(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer taxId = Integer.parseInt(request.getParameter("taxmasterid"));
		String taxName=request.getParameter("taxname");
		Taxmaster tax=new Taxmaster();
		tax.setTaxmasterid(taxId);;
		tax.setTaxname(taxName);;
		String message = iMasterService.deleteTax(tax);
		List<Taxmaster> taxList = iMasterService.taxList();
		model.addAttribute("taxList",taxList);
		model.addAttribute("message", message);
		return new ModelAndView("taxmaster");
	}
	
	/*@RequestMapping(value="/searchTax")
	public ModelAndView searchTax(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String taxName = request.getParameter("taxname");
		String listmessage = null;
		List<Taxmaster> taxList=iMasterService.getsearchTax(taxName);
		if(taxList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+taxName;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchtax", taxName);
		model.addAttribute("taxList",taxList);
		return new ModelAndView("taxmaster");
	}*/
	
	@RequestMapping(value="/taxstructure")
	public ModelAndView taxStructure(){
		
		List<Taxmaster> taxMasterList = iMasterService.getTaxMasterList();
		List<TaxDetails> taxlist = iMasterService.getTaxStructureList();
		return new ModelAndView("taxstructuremaster","taxMasterList",taxMasterList);
	}
	
	@RequestMapping(value = "/savetaxstructure", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String saveTaxStructure(HttpServletRequest request, HttpServletResponse response,@RequestBody TaxDetails taxDetails) {
		Date date=new Date();
		String message = iMasterService.saveTaxStructure(taxDetails);
		return message;
	}
	
	@RequestMapping(value = "/getTaxStructureList", method = RequestMethod.POST)
	@ResponseBody
	public List getTaxStructureList(HttpServletRequest request, HttpServletResponse response) {
		Date date=new Date();
		List<TaxDetails> taxlist = iMasterService.getTaxStructureList();
		List<String> list = new LinkedList<String>();
		for (TaxDetails taxDetails : taxlist) {
			list.add(""+taxDetails.getTaxStructureId());
			list.add(taxDetails.getTaxName());
			list.add(taxDetails.getTaxStructureDescription());
			list.add(taxDetails.getTaxStructurePercent());
			list.add(taxDetails.getTaxType());
			list.add(taxDetails.getTaxOn());
			list.add(""+taxDetails.getApplicableDate());
		}
		return list;
	}
	
	
	@RequestMapping(value = "/openproductratepage")
	public ModelAndView getProducts(ModelMap model) {
		List<Stockmaster> productList = iMasterService.getProducts();
		List<Taxstructuremaster> taxStructureList = iMasterService.getTaxStructure();
		List<Productratemaster> productRateList = iMasterService.productRateList();
		model.addAttribute("taxstructurelist", taxStructureList);
		model.addAttribute("productlist", productList);
		model.addAttribute("productRateList", productRateList);
		return new ModelAndView("productratemaster");
	}
	
	@RequestMapping(value = "/saveproductrate")
	@ResponseBody
	public String saveProductRate(HttpServletRequest request, HttpServletResponse response,
			Productratemaster productrate) {
		Date date = new Date();
		Integer productId = Integer.parseInt(request.getParameter("stockid"));
		Integer taxStructureId = Integer.parseInt(request.getParameter("taxstructureid"));
		String applicabledate = request.getParameter("applicableDate");
		BigDecimal productcost = new BigDecimal(request.getParameter("productcost"));
		productrate.setProductcost(productcost);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(applicabledate);
			productrate.setApplicabledate(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message=iMasterService.saveProductRate(productId, taxStructureId, productrate);
		return message;
	}
	
	@RequestMapping(value="/servicespage")
	public ModelAndView services(ModelMap model){
		String serviceName=null;
		List<Servicegroupmaster> serviceGroupsList = iMasterService.getServiceGroups(serviceName,1);
		Pagination pagination = iMasterService.serviceCount(serviceName);
		pagination.setCurrentPageNum(1);
		model.addAttribute("paging1", pagination);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Servicemaster> servicesList=iMasterService.getServices();
		model.addAttribute("serviceGroupslist", serviceGroupsList);
		model.addAttribute("genderslist",gendersList);
		model.addAttribute("servicesList",servicesList);
		return new ModelAndView("servicemaster");
	}
	
	@RequestMapping(value="/saveService")
	public ModelAndView saveService(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		//Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		String serarchserviceName=null;
		String serviceName =	request.getParameter("servicename");
		Integer genderid = Integer.parseInt(request.getParameter("genderid"));
		String preparetime = request.getParameter("preparetime");
		String servicetime = request.getParameter("servicetime");
		String waitingtime = request.getParameter("waitingtime");
		String cleaningtime = request.getParameter("cleaningtime");
		String totaltime = request.getParameter("totaltime");
		Integer servicegroupid = Integer.parseInt(request.getParameter("servicegroupid"));
		Servicemaster serviceMaster = new Servicemaster();
		serviceMaster.setServicename(serviceName);
		serviceMaster.setPreparetime(preparetime);
		serviceMaster.setServicetime(servicetime);
		serviceMaster.setWaitingtime(waitingtime);
		serviceMaster.setCleaningtime(cleaningtime);
		serviceMaster.setTotalservicetime(totaltime);
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		dropdowndetails.setDropdowndetailsid(genderid);
		serviceMaster.setDropdowndetails(dropdowndetails);
		Servicegroupmaster servicegroupmaster = new Servicegroupmaster();
		servicegroupmaster.setServicegroupid(servicegroupid);
		serviceMaster.setServicegroupmaster(servicegroupmaster);
		String message = iMasterService.saveService(serviceMaster);
		List<Servicegroupmaster> serviceGroupsList = iMasterService.getServiceGroups(serarchserviceName, 1);
		Pagination pagination = iMasterService.corporateCount(serviceName);
		model.addAttribute("paging1",pagination);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Servicemaster> servicesList=iMasterService.getServices();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupslist", serviceGroupsList);
		model.addAttribute("genderslist",gendersList);
		model.addAttribute("servicesList",servicesList);
		return new ModelAndView("servicemaster");
	}
	
	@RequestMapping(value="/editService")
	public ModelAndView editService(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		String serarchserviceName=null;
		String serviceName =	request.getParameter("servicename");
		Integer genderid = Integer.parseInt(request.getParameter("genderid"));
		String preparetime = request.getParameter("preparetime");
		String servicetime = request.getParameter("servicetime");
		String waitingtime = request.getParameter("waitingtime");
		String cleaningtime = request.getParameter("cleaningtime");
		String totaltime = request.getParameter("totaltime");
		Integer servicegroupid = Integer.parseInt(request.getParameter("servicegroupid"));
		Servicemaster serviceMaster = new Servicemaster();
		serviceMaster.setServiceid(serviceId);
		serviceMaster.setServicename(serviceName);
		serviceMaster.setPreparetime(preparetime);
		serviceMaster.setServicetime(servicetime);
		serviceMaster.setWaitingtime(waitingtime);
		serviceMaster.setCleaningtime(cleaningtime);
		serviceMaster.setTotalservicetime(totaltime);
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		dropdowndetails.setDropdowndetailsid(genderid);
		serviceMaster.setDropdowndetails(dropdowndetails);
		Servicegroupmaster servicegroupmaster = new Servicegroupmaster();
		servicegroupmaster.setServicegroupid(servicegroupid);
		serviceMaster.setServicegroupmaster(servicegroupmaster);
		String message = iMasterService.editService(serviceMaster);
		List<Servicegroupmaster> serviceGroupsList = iMasterService.getServiceGroups(serarchserviceName, 1);
		Pagination pagination = iMasterService.corporateCount(serviceName);
		model.addAttribute("paging1",pagination);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Servicemaster> servicesList=iMasterService.getServices();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupslist", serviceGroupsList);
		model.addAttribute("genderslist",gendersList);
		model.addAttribute("servicesList",servicesList);
		return new ModelAndView("servicemaster");
	}
	
	@RequestMapping(value="/deleteService")
	public ModelAndView deleteService(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		String serarchserviceName=null;
		String serviceName =	request.getParameter("servicename");
		Integer genderid = Integer.parseInt(request.getParameter("genderid"));
		String preparetime = request.getParameter("preparetime");
		String servicetime = request.getParameter("servicetime");
		String waitingtime = request.getParameter("waitingtime");
		String cleaningtime = request.getParameter("cleaningtime");
		String totaltime = request.getParameter("totaltime");
		Integer servicegroupid = Integer.parseInt(request.getParameter("servicegroupid"));
		Servicemaster serviceMaster = new Servicemaster();
		serviceMaster.setServiceid(serviceId);
		serviceMaster.setServicename(serviceName);
		serviceMaster.setPreparetime(preparetime);
		serviceMaster.setServicetime(servicetime);
		serviceMaster.setWaitingtime(waitingtime);
		serviceMaster.setCleaningtime(cleaningtime);
		serviceMaster.setTotalservicetime(totaltime);
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		dropdowndetails.setDropdowndetailsid(genderid);
		serviceMaster.setDropdowndetails(dropdowndetails);
		Servicegroupmaster servicegroupmaster = new Servicegroupmaster();
		servicegroupmaster.setServicegroupid(servicegroupid);
		serviceMaster.setServicegroupmaster(servicegroupmaster);
		String message = iMasterService.deleteService(serviceMaster);
		List<Servicegroupmaster> serviceGroupsList = iMasterService.getServiceGroups(serarchserviceName, 1);
		Pagination pagination = iMasterService.corporateCount(serviceName);
		model.addAttribute("paging1",pagination);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Servicemaster> servicesList=iMasterService.getServices();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupslist", serviceGroupsList);
		model.addAttribute("genderslist",gendersList);
		model.addAttribute("servicesList",servicesList);
		return new ModelAndView("servicemaster");
	}
	
	@RequestMapping(value="/serviceSearchByName")
	public ModelAndView serviceSearchByName(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String serviceName = request.getParameter("serviceName");
		String listmessage = null;
		String recordsStr=request.getParameter("search");
		if(recordsStr.equals("Search")){
			recordsStr="1";
		}
		int records=Integer.parseInt(recordsStr);
		List<Servicegroupmaster> serviceGroupsList = iMasterService.getServiceGroups(serviceName,records);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Servicemaster> servicesList=iMasterService.getServiceByName(serviceName);
		if(serviceGroupsList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+serviceName;
		}
		model.addAttribute("serviceGroupslist", serviceGroupsList);
		model.addAttribute("genderslist",gendersList);
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("searchname",serviceName);
		return new ModelAndView("servicemaster");
	}
	
	@RequestMapping(value="/serviceGroupPage")
	public ModelAndView serviceGroupList(ModelMap model){
		List<Servicegroupmaster> serviceGroupList=iMasterService.getServiceGroupList();
		model.addAttribute("serviceGroupList",serviceGroupList);
		return new ModelAndView("servicegroupmaster");
		
	}
	
	@RequestMapping(value="/saveServiceGroup")
	public ModelAndView saveServiceGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String serviceGroupName =	request.getParameter("servicegroupname");
		String description = request.getParameter("description");
		Servicegroupmaster serviceGroup = new Servicegroupmaster();
		serviceGroup.setServicegroupname(serviceGroupName);
		serviceGroup.setDescription(description);
		String message = iMasterService.saveServiceGroup(serviceGroup);
		List<Servicegroupmaster> serviceGroupList=iMasterService.getServiceGroupList();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupList",serviceGroupList);
		return new ModelAndView("servicegroupmaster");
	}
	
	@RequestMapping(value="/editServiceGroup")
	public ModelAndView editServiceGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceGroupId = Integer.parseInt(request.getParameter("servicegroupid"));
		String serviceGroupName =	request.getParameter("servicegroupname");
		String description = request.getParameter("description");
		Servicegroupmaster serviceGroup = new Servicegroupmaster();
		serviceGroup.setServicegroupid(serviceGroupId);
		serviceGroup.setServicegroupname(serviceGroupName);
		serviceGroup.setDescription(description);
		String message = iMasterService.editServiceGroup(serviceGroup);
		List<Servicegroupmaster> serviceGroupList=iMasterService.getServiceGroupList();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupList",serviceGroupList);
		return new ModelAndView("servicegroupmaster");
	}
	
	@RequestMapping(value="/deleteServiceGroup")
	public ModelAndView deleteServiceGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceGroupId = Integer.parseInt(request.getParameter("servicegroupid"));
		String serviceGroupName =	request.getParameter("servicegroupname");
		String description = request.getParameter("description");
		Servicegroupmaster serviceGroup = new Servicegroupmaster();
		serviceGroup.setServicegroupid(serviceGroupId);
		serviceGroup.setServicegroupname(serviceGroupName);
		serviceGroup.setDescription(description);
		String message = iMasterService.deleteServiceGroup(serviceGroup);
		List<Servicegroupmaster> serviceGroupList=iMasterService.getServiceGroupList();
		model.addAttribute("message", message);
		model.addAttribute("serviceGroupList",serviceGroupList);
		return new ModelAndView("servicegroupmaster");          
	}
	
	@RequestMapping(value="/serviceGroupSearchByName")
	public ModelAndView serviceGroupSearchByName(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String serviceGroupName = request.getParameter("serviceGroupName");
		String listmessage = null;
		List<Servicegroupmaster> servicesGroupList=iMasterService.getServiceGroupByName(serviceGroupName);
		if(servicesGroupList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+serviceGroupName;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchservicegroup", serviceGroupName);
		model.addAttribute("serviceGroupList",servicesGroupList);
		return new ModelAndView("servicegroupmaster");
	}
	
	@RequestMapping(value="/packageservicespage", method=RequestMethod.GET)
	public ModelAndView packageServices(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String message = request.getParameter("message");
		List<Packagemaster> packagesList=iMasterService.getPackages();
		List<Servicemaster> servicesList=iMasterService.getServices();
		List<Packageservices> packageserviceList=iMasterService.getPackageServices();
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("packagesList",packagesList);
		model.addAttribute("packageserviceList",packageserviceList);
		return new ModelAndView("packagemaster");
	}
	

	@RequestMapping(value="/savePackage", method=RequestMethod.POST)
	public ModelAndView savePackage(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String packagename = request.getParameter("packagename");
		String[] serviceids = request.getParameterValues("serviceid");
		String message = null;
		message = iMasterService.savePackage(packagename,serviceids);
		List<Packagemaster> packagesList=iMasterService.getPackages();
		List<Servicemaster> servicesList=iMasterService.getServices();
		List<Packageservices> packageserviceList=iMasterService.getPackageServices();
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("packagesList",packagesList);
		model.addAttribute("packageserviceList",packageserviceList);
		model.addAttribute("message",message);
		return new ModelAndView("packagemaster");
	}
	
	@RequestMapping(value="/editPackage")
	public ModelAndView editPackage(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String packagename = request.getParameter("packagename");
		String[] serviceids = request.getParameterValues("serviceid");
		Integer packageid = Integer.parseInt(request.getParameter("packageid"));
		String message = iMasterService.editPackage(packagename, serviceids, packageid);
		List<Packagemaster> packagesList=iMasterService.getPackages();
		List<Servicemaster> servicesList=iMasterService.getServices();
		List<Packageservices> packageserviceList=iMasterService.getPackageServices();
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("packagesList",packagesList);
		model.addAttribute("packageserviceList",packageserviceList);
		model.addAttribute("message",message);
		return new ModelAndView("packagemaster");
	}
	
	@RequestMapping(value="/deletePackage")
	public ModelAndView deletePackage(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer packageid = Integer.parseInt(request.getParameter("deletePackageId"));
		String message = iMasterService.deletePackage(packageid);
		List<Packagemaster> packagesList=iMasterService.getPackages();
		List<Servicemaster> servicesList=iMasterService.getServices();
		List<Packageservices> packageserviceList=iMasterService.getPackageServices();
		Packagemaster packagedetails = iMasterService.getPackageById(packageid);
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("packagedetails",packagedetails);
		model.addAttribute("packagesList",packagesList);
		model.addAttribute("packageserviceList",packageserviceList);
		model.addAttribute("message",message);
		return new ModelAndView("packagemaster");
	}
	
	@RequestMapping(value="/stockgrouppage")
	public ModelAndView stockGroups(ModelMap model){
		
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroupList();
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stockgroupmaster");
	}
	
	@RequestMapping(value="/saveStockGroup", method=RequestMethod.POST)
	public ModelAndView saveStockGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String stockGroupName=request.getParameter("stockgroupname");
		String description=request.getParameter("description");
		Stockgroupmaster stockGroupMaster = new Stockgroupmaster();
		stockGroupMaster.setStockgroupname(stockGroupName);
		stockGroupMaster.setDescription(description);
		String message = iMasterService.saveStockGroup(stockGroupMaster);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroupList();
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stockgroupmaster");
	}
	
	@RequestMapping(value="/editStockGroup")
	public ModelAndView editStockGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer stockGroupId = Integer.parseInt(request.getParameter("stockgroupid"));
		String stockGroupName =	request.getParameter("stockgroupname");
		String description = request.getParameter("description");
		Stockgroupmaster stockGroup = new Stockgroupmaster();
		stockGroup.setStockgroupid(stockGroupId);;
		stockGroup.setStockgroupname(stockGroupName);
		stockGroup.setDescription(description);
		String message = iMasterService.editStockGroup(stockGroup);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroupList();
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stockgroupmaster");
	}
	
	@RequestMapping(value="/deleteStockGroup")
	public ModelAndView deleteStockGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer stockGroupId = Integer.parseInt(request.getParameter("stockgroupid"));
		String stockGroupName =	request.getParameter("stockgroupname");
		String description = request.getParameter("description");
		Stockgroupmaster stockGroup = new Stockgroupmaster();
		stockGroup.setStockgroupid(stockGroupId);;
		stockGroup.setStockgroupname(stockGroupName);
		stockGroup.setDescription(description);
		String message = iMasterService.deleteStockGroup(stockGroup);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroupList();
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stockgroupmaster");          
	}
	
	@RequestMapping(value="/stockGroupSearchByName")
	public ModelAndView stockGroupSearchByName(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String stockGroupName = request.getParameter("stockgroupname");
		String listmessage = null;
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroupByName(stockGroupName);
		if(stockGroupList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+stockGroupName;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchstockgroup", stockGroupName);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stockgroupmaster");
	}
	
	@RequestMapping(value="/stockpage")
	public ModelAndView getstockGroups(ModelMap model){
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroups();
		List<Stocksubgroupmaster> stockSubGroups=iMasterService.getStockSubGroupList();
		model.addAttribute("stockGroupList", stockGroupList);
		model.addAttribute("stockSubGroups", stockSubGroups);
		return new ModelAndView("stocksubgroupmaster");
	}
	
	@RequestMapping(value="/saveStockSubGroup", method=RequestMethod.POST)
	public ModelAndView saveStockSubGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		//Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		String stockSubGroupName =	request.getParameter("stocksubgroupname");
		String description=request.getParameter("description");
		Integer stockgroupid = Integer.parseInt(request.getParameter("stockgroupid"));
		Stocksubgroupmaster stockSubGroup=new Stocksubgroupmaster();
		stockSubGroup.setStocksubgroupname(stockSubGroupName);
		stockSubGroup.setDescription(description);
		Stockgroupmaster stockGroup=new Stockgroupmaster();
		stockGroup.setStockgroupid(stockgroupid);
		stockSubGroup.setStockgroupmaster(stockGroup);
		String message = iMasterService.saveStockSubGroup(stockSubGroup);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroups();
		List<Stocksubgroupmaster> stockSubGroups=iMasterService.getStockSubGroupList();
		model.addAttribute("stockSubGroups", stockSubGroups);
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stocksubgroupmaster");
	}
	
	@RequestMapping(value="/editStockSubGroup",method=RequestMethod.POST)
	public ModelAndView editStockSubGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer stockSubGroupId = Integer.parseInt(request.getParameter("stocksubgroupid"));
		String stockSubGroupName =	request.getParameter("stocksubgroupname");
		String description=request.getParameter("description");
		Integer stockgroupid = Integer.parseInt(request.getParameter("stockgroupid"));
		Stocksubgroupmaster stockSubGroup=new Stocksubgroupmaster();
		stockSubGroup.setStocksubgroupid(stockSubGroupId);
		stockSubGroup.setStocksubgroupname(stockSubGroupName);
		stockSubGroup.setDescription(description);
		Stockgroupmaster stockGroup=new Stockgroupmaster();
		stockGroup.setStockgroupid(stockgroupid);
		stockSubGroup.setStockgroupmaster(stockGroup);
		String message = iMasterService.editStockSubGroup(stockSubGroup);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroups();
		List<Stocksubgroupmaster> stockSubGroups=iMasterService.getStockSubGroupList();
		model.addAttribute("stockSubGroups", stockSubGroups);
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stocksubgroupmaster");
	}
	
	@RequestMapping(value="/deleteStockSubGroup",method=RequestMethod.POST)
	public ModelAndView deleteStockSubGroup(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer stockSubGroupId = Integer.parseInt(request.getParameter("stocksubgroupid"));
		String stockSubGroupName =	request.getParameter("stocksubgroupname");
		String description=request.getParameter("description");
		Integer stockgroupid = Integer.parseInt(request.getParameter("stockgroupid"));
		Stocksubgroupmaster stockSubGroup=new Stocksubgroupmaster();
		stockSubGroup.setStocksubgroupid(stockSubGroupId);
		stockSubGroup.setStocksubgroupname(stockSubGroupName);
		stockSubGroup.setDescription(description);
		Stockgroupmaster stockGroup=new Stockgroupmaster();
		stockGroup.setStockgroupid(stockgroupid);
		stockSubGroup.setStockgroupmaster(stockGroup);
		String message = iMasterService.deleteStockSubGroup(stockSubGroup);
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroups();
		List<Stocksubgroupmaster> stockSubGroups=iMasterService.getStockSubGroupList();
		model.addAttribute("stockSubGroups", stockSubGroups);
		model.addAttribute("message", message);
		model.addAttribute("stockGroupList",stockGroupList);
		return new ModelAndView("stocksubgroupmaster");
	}
	
	@RequestMapping(value="/stockSubGroupSearchByName")
	public ModelAndView stockSubGroupSearchByName(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String stockSubGroupName = request.getParameter("stocksubgroupname");
		String listmessage = null;
		List<Stocksubgroupmaster> stockSubGroups=iMasterService.getStockSubGroupByName(stockSubGroupName);
		if(stockSubGroups.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+stockSubGroupName;
		}
		List<Stockgroupmaster> stockGroupList=iMasterService.getStockGroups();
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchstocksubgroup", stockSubGroupName);
		model.addAttribute("stockGroupList",stockGroupList);
		model.addAttribute("stockSubGroups",stockSubGroups);
		return new ModelAndView("stocksubgroupmaster");
	}
	
	@RequestMapping(value="/billinginstructionpage")
	public ModelAndView getBillingInstruction(ModelMap model){
		
		List<Billinginstructionmaster> billingInstructionList=iMasterService.getBillingInstructionList();
		model.addAttribute("billingInstructionList",billingInstructionList);
		return new ModelAndView("billinginstructionmaster");
	}
	
	@RequestMapping(value="/saveBillingInstruction", method=RequestMethod.POST)
	public ModelAndView saveBillingInstruction(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String description=request.getParameter("instructiondescription");
		Billinginstructionmaster billingInstruction=new Billinginstructionmaster();
		billingInstruction.setInstructiondescription(description);
		String message = iMasterService.saveBillingInstruction(billingInstruction);
		List<Billinginstructionmaster> billingInstructionList=iMasterService.getBillingInstructionList();
		model.addAttribute("billingInstructionList",billingInstructionList);
		model.addAttribute("message", message);
		return new ModelAndView("billinginstructionmaster");
	}
	
	@RequestMapping(value="/editBillingInstruction",method=RequestMethod.POST)
	public ModelAndView editBillingInstruction(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer billingInstructionId = Integer.parseInt(request.getParameter("billinginstructionid"));
		String description = request.getParameter("instructiondescription");
		Billinginstructionmaster billingInstruction=new Billinginstructionmaster();
		billingInstruction.setInstructiondescription(description);
		billingInstruction.setBillinginstructionid(billingInstructionId);
		String message = iMasterService.editBillingInstruction(billingInstruction);
		List<Billinginstructionmaster> billingInstructionList=iMasterService.getBillingInstructionList();
		model.addAttribute("billingInstructionList",billingInstructionList);
		model.addAttribute("message", message);
		return new ModelAndView("billinginstructionmaster");
	}
	
	@RequestMapping(value="/deleteBillingInstruction",method=RequestMethod.POST)
	public ModelAndView deleteBillingInstruction(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer billingInstructionId = Integer.parseInt(request.getParameter("billinginstructionid"));
		String description = request.getParameter("instructiondescription");
		Billinginstructionmaster billingInstruction=new Billinginstructionmaster();
		billingInstruction.setInstructiondescription(description);
		billingInstruction.setBillinginstructionid(billingInstructionId);
		String message = iMasterService.deleteBillingInstruction(billingInstruction);
		List<Billinginstructionmaster> billingInstructionList=iMasterService.getBillingInstructionList();
		model.addAttribute("billingInstructionList",billingInstructionList);
		model.addAttribute("message", message);
		return new ModelAndView("billinginstructionmaster");
	}
	
	@RequestMapping(value="/getsearchbillinginstruction")
	public ModelAndView searchBillingInstruction(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String description = request.getParameter("instructiondescription");
		String listmessage = null;
		List<Billinginstructionmaster> billingInstructionList=iMasterService.searchBillingInstruction(description);
		if(billingInstructionList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+description;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchbillinginstruction", description);
		model.addAttribute("billingInstructionList",billingInstructionList);
		return new ModelAndView("billinginstructionmaster");
	}
	
	@RequestMapping(value="/businesssourcepage")
	public ModelAndView getBusinessSource(ModelMap model){
		
		List<Businesssourcemaster> businessSourceList=iMasterService.getBusinessSourceList();
		model.addAttribute("businessSourceList",businessSourceList);
		return new ModelAndView("businesssourcemaster");
	}
	
	@RequestMapping(value="/saveBusinessSource", method=RequestMethod.POST)
	public ModelAndView saveBusinessSource(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String description=request.getParameter("sourcedescription");
		Businesssourcemaster businessSource=new Businesssourcemaster();
		businessSource.setSourcedescription(description);
		String message = iMasterService.saveBusinessSource(businessSource);
		List<Businesssourcemaster> businessSourceList=iMasterService.getBusinessSourceList();
		model.addAttribute("businessSourceList",businessSourceList);
		model.addAttribute("message", message);
		return new ModelAndView("businesssourcemaster");
	}
	
	@RequestMapping(value="/editBusinessSource",method=RequestMethod.POST)
	public ModelAndView editBusinessSource(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer businessSourceId = Integer.parseInt(request.getParameter("businesssourceid"));
		String description = request.getParameter("sourcedescription");
		Businesssourcemaster businessSource=new Businesssourcemaster();
		businessSource.setSourcedescription(description);
		businessSource.setBusinesssourceid(businessSourceId);
		String message = iMasterService.editBusinessSource(businessSource);
		List<Businesssourcemaster> businessSourceList=iMasterService.getBusinessSourceList();
		model.addAttribute("businessSourceList",businessSourceList);
		model.addAttribute("message", message);
		return new ModelAndView("businesssourcemaster");
	}
	
	@RequestMapping(value="/deleteBusinessSource",method=RequestMethod.POST)
	public ModelAndView deleteBusinessSource(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer businessSourceId = Integer.parseInt(request.getParameter("businesssourceid"));
		String description = request.getParameter("sourcedescription");
		Businesssourcemaster businessSource=new Businesssourcemaster();
		businessSource.setSourcedescription(description);
		businessSource.setBusinesssourceid(businessSourceId);
		String message = iMasterService.deleteBusinessSource(businessSource);
		List<Businesssourcemaster> businessSourceList=iMasterService.getBusinessSourceList();
		model.addAttribute("businessSourceList",businessSourceList);
		model.addAttribute("message", message);
		return new ModelAndView("businesssourcemaster");
	}
	
	@RequestMapping(value="/searchBusinessSource")
	public ModelAndView searchBusinessSource(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String description = request.getParameter("sourcedescription");
		String listmessage = null;
		List<Businesssourcemaster> businessSourceList=iMasterService.getsearchBusinessSource(description);
		if(businessSourceList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+description;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchbusinesssource", description);
		model.addAttribute("businessSourceList",businessSourceList);
		return new ModelAndView("businesssourcemaster");
	}
	
	@RequestMapping(value="/giftvoucherpage")
	public ModelAndView getGiftVoucher(ModelMap model){
		
		List<Giftvouchermaster> giftVoucherList=iMasterService.getGiftVoucherList();
		model.addAttribute("giftVoucherList",giftVoucherList);
		return new ModelAndView("giftvouchermaster");
	}
	
	@RequestMapping(value="/saveGiftVoucher", method=RequestMethod.POST)
	public ModelAndView saveGiftVoucher(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Date date=new Date();
		BigDecimal giftVoucherAmount=new BigDecimal(request.getParameter("giftvoucheramount"));
		String validFrom = request.getParameter("validfrom");
		String ValidTo = request.getParameter("validto");
		Giftvouchermaster giftVoucher=new Giftvouchermaster();
		giftVoucher.setGiftvoucheramount(giftVoucherAmount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(validFrom);
			giftVoucher.setValidfrom(date);
			date=sdf.parse(ValidTo);
			giftVoucher.setValidto(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message = iMasterService.saveGiftVoucher(giftVoucher);
		List<Giftvouchermaster> giftVoucherList=iMasterService.getGiftVoucherList();
		model.addAttribute("giftVoucherList",giftVoucherList);
		model.addAttribute("message", message);
		return new ModelAndView("giftvouchermaster");
	}
	
	@RequestMapping(value="/creditlistpage")
	public ModelAndView openCreditListPage(ModelMap model){
		List<Corporatemaster> corporateList=iMasterService.getCorporateList();
		List<Creditlistmaster> creditListDetails = iMasterService.getCreditListDetails();
		model.addAttribute("corporateList",corporateList);
		model.addAttribute("creditListDetails",creditListDetails);
		return new ModelAndView("creditlist");
	}
	
	@RequestMapping(value="/saveCreditList")
	public ModelAndView saveCreditList(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Creditlistmaster creditlistmaster = new Creditlistmaster();
		Date date=new Date();
		creditlistmaster.setCreditamount(new BigDecimal(request.getParameter("creditamount")));
		creditlistmaster.setOutstandingamount(new BigDecimal(request.getParameter("creditamount")));
		String isblaclist = request.getParameter("blacklisted");
		if(isblaclist==null)
		{
			creditlistmaster.setBlacklisted(false);
		}
		else{
			creditlistmaster.setBlacklisted(true);
		}
		String reason =request.getParameter("blacklistreason");
		creditlistmaster.setBlacklistreason(reason);
		creditlistmaster.setRemarks(request.getParameter("remarks"));
		Corporatemaster corporatemaster = new Corporatemaster();
		corporatemaster.setCorporateid(Integer.parseInt(request.getParameter("corporateid")));
		creditlistmaster.setCorporatemaster(corporatemaster);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String validFrom = request.getParameter("validfrom");
		String ValidTo = request.getParameter("validto");
		try {
			date = sdf.parse(validFrom);
			creditlistmaster.setValidfrom(date);
			date=sdf.parse(ValidTo);
			creditlistmaster.setValidto(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message = iMasterService.saveCreditList(creditlistmaster);
		List<Corporatemaster> corporateList=iMasterService.getCorporateList();
		List<Creditlistmaster> creditListDetails = iMasterService.getCreditListDetails();
		model.addAttribute("message", message);
		model.addAttribute("corporateList",corporateList);
		model.addAttribute("creditListDetails",creditListDetails);
		return new ModelAndView("creditlist");
	}
	
	@RequestMapping(value="/deleteCreditList")
	public ModelAndView deleteCreditList(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		System.out.println(request.getParameter("deletecorpId"));
		String message = iMasterService.deleteCreditList(Integer.parseInt(request.getParameter("deletecorpId")));
		List<Corporatemaster> corporateList=iMasterService.getCorporateList();
		List<Creditlistmaster> creditListDetails = iMasterService.getCreditListDetails();
		model.addAttribute("message", message);
		model.addAttribute("corporateList",corporateList);
		model.addAttribute("creditListDetails",creditListDetails);
		return new ModelAndView("creditlist");
	}
	
	@RequestMapping(value = "/openserviceroompage")
	public ModelAndView getServiceRoom(ModelMap model) {
		List<Servicemaster> servicesList = iMasterService.getServices();
		List<Roommaster> roomList = iMasterService.getRooms();
		List<Serviceroommaster> serviceRoomMaster=iMasterService.getServiceRoomList();
		model.addAttribute("roomList", roomList);
		model.addAttribute("serviceslist", servicesList);
		model.addAttribute("serviceRoomMaster", serviceRoomMaster);
		return new ModelAndView("serviceroommaster");
	}
	
	@RequestMapping(value="/saveServiceRoom", method=RequestMethod.POST)
	public ModelAndView saveServiceRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		//Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		String description=request.getParameter("description");
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		Integer roomId=Integer.parseInt(request.getParameter("roomid"));
		Serviceroommaster serviceroom=new Serviceroommaster();
		serviceroom.setDescription(description);
		Servicemaster serviceMaster=new Servicemaster();
		serviceMaster.setServiceid(serviceId);
		serviceroom.setServicemaster(serviceMaster);
		Roommaster roomMaster=new Roommaster();
		roomMaster.setRoomid(roomId);
		serviceroom.setRoommaster(roomMaster);
		String message = iMasterService.saveServiceRoom(serviceroom);
		List<Servicemaster> servicesList = iMasterService.getServices();
		List<Roommaster> roomList = iMasterService.getRooms();
		List<Serviceroommaster> serviceRoomMaster=iMasterService.getServiceRoomList();
		model.addAttribute("serviceRoomMaster", serviceRoomMaster);
		model.addAttribute("roomList", roomList);
		model.addAttribute("serviceslist", servicesList);
		model.addAttribute("message", message);
		return new ModelAndView("serviceroommaster");
	}
	
	@RequestMapping(value="/editServiceRoom",method=RequestMethod.POST)
	public ModelAndView editServiceRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceRoomId = Integer.parseInt(request.getParameter("serviceroomid"));
		String description=request.getParameter("description");
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		Integer roomId=Integer.parseInt(request.getParameter("roomid"));
		Serviceroommaster serviceroom=new Serviceroommaster();
		serviceroom.setServiceroomid(serviceRoomId);
		serviceroom.setDescription(description);
		Servicemaster serviceMaster=new Servicemaster();
		serviceMaster.setServiceid(serviceId);
		serviceroom.setServicemaster(serviceMaster);
		Roommaster roomMaster=new Roommaster();
		roomMaster.setRoomid(roomId);
		serviceroom.setRoommaster(roomMaster);
		String message = iMasterService.editServiceRoom(serviceroom);
		List<Servicemaster> servicesList = iMasterService.getServices();
		List<Roommaster> roomList = iMasterService.getRooms();
		List<Serviceroommaster> serviceRoomMaster=iMasterService.getServiceRoomList();
		model.addAttribute("serviceRoomMaster", serviceRoomMaster);
		model.addAttribute("roomList", roomList);
		model.addAttribute("serviceslist", servicesList);
		model.addAttribute("message", message);
		return new ModelAndView("serviceroommaster");
	}
	
	@RequestMapping(value="/deleteServiceRoom",method=RequestMethod.POST)
	public ModelAndView deleteServiceRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer serviceRoomId = Integer.parseInt(request.getParameter("serviceroomid"));
		String description=request.getParameter("description");
		Integer serviceId = Integer.parseInt(request.getParameter("serviceid"));
		Integer roomId=Integer.parseInt(request.getParameter("roomid"));
		Serviceroommaster serviceroom=new Serviceroommaster();
		serviceroom.setServiceroomid(serviceRoomId);
		serviceroom.setDescription(description);
		Servicemaster serviceMaster=new Servicemaster();
		serviceMaster.setServiceid(serviceId);
		serviceroom.setServicemaster(serviceMaster);
		Roommaster roomMaster=new Roommaster();
		roomMaster.setRoomid(roomId);
		serviceroom.setRoommaster(roomMaster);
		String message = iMasterService.deleteServiceRoom(serviceroom);
		List<Servicemaster> servicesList = iMasterService.getServices();
		List<Roommaster> roomList = iMasterService.getRooms();
		List<Serviceroommaster> serviceRoomMaster=iMasterService.getServiceRoomList();
		model.addAttribute("serviceRoomMaster", serviceRoomMaster);
		model.addAttribute("roomList", roomList);
		model.addAttribute("serviceslist", servicesList);
		model.addAttribute("message", message);
		return new ModelAndView("serviceroommaster");
	}
	
	@RequestMapping(value="/searchServiceRoom")
	public ModelAndView searchServiceRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String serviceName = request.getParameter("serviceName");
		String listmessage = null;
		 List<Servicemaster> servicesList = iMasterService.getServices();
		 List<Roommaster> roomList = iMasterService.getRooms();
			List<Serviceroommaster> serviceRoomMaster=iMasterService.getServiceRoomList();
		List<Serviceroommaster> serviceRoomList=iMasterService.getsearchServiceRoom(serviceName);
		if(serviceRoomList.size()==0)
		{
			
			listmessage = "Sorry Records are not found with name: "+serviceName;
		}
		
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchserviceroom", serviceName);
		model.addAttribute("serviceRoomMaster", serviceRoomList);
		model.addAttribute("servicesList", servicesList);
		model.addAttribute("serviceRoomList", serviceRoomList);
		return new ModelAndView("serviceroommaster");
	}
	
	@RequestMapping(value = "/openpackageratepage")
	public ModelAndView getPackages(ModelMap model) {
		List<Packagemaster> packageList = iMasterService.getPackages();
		List<Taxstructuremaster> taxStructureList = iMasterService.getTaxStructure();
		List<Packageratemaster> packageRateList = iMasterService.packageRateList();
		model.addAttribute("taxstructurelist", taxStructureList);
		model.addAttribute("packageList", packageList);
		model.addAttribute("packageRateList", packageRateList);
		return new ModelAndView("packageratemaster");
	}
	
	@RequestMapping(value = "/savepackagerate")
	@ResponseBody
	public String savePackageRate(HttpServletRequest request, HttpServletResponse response,
			Packageratemaster packagerate) {
		Date date = new Date();
		Integer packageId = Integer.parseInt(request.getParameter("packageid"));
		Integer taxStructureId = Integer.parseInt(request.getParameter("taxstructureid"));
		String applicabledate = request.getParameter("applicableDate");
		BigDecimal packageCost = new BigDecimal(request.getParameter("packagecost"));
		packagerate.setPackagecost(packageCost);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(applicabledate);
			packagerate.setApplicabledate(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message=iMasterService.savePackageRate(packageId, taxStructureId, packagerate);
		return message;
	}
	
	@RequestMapping(value="/corporatetypepage")
	public ModelAndView getCorporateType(ModelMap model){
		String corporateName=null;
		List<Corporatetypemaster> corporateTypeList=iMasterService.getCorporateTypeList(corporateName,1);
		Pagination pagination = iMasterService.corporateCount(corporateName);
		pagination.setCurrentPageNum(1);
		model.addAttribute("paging1", pagination);
		model.addAttribute("corporateTypeList",corporateTypeList);
		return new ModelAndView("corporatetypemaster");
	}
	
	@RequestMapping(value="/saveCorportaeType", method=RequestMethod.POST)
	public ModelAndView saveCorporateType(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String corporateName=request.getParameter("corporatetype");
		Corporatetypemaster corporateType=new Corporatetypemaster();
		corporateType.setCorporatetype(corporateName);
		String message = iMasterService.saveCorporateType(corporateType);
		String serarchCorporateType=null;
		List<Corporatetypemaster> corporateTypeList=iMasterService.getCorporateTypeList(serarchCorporateType, 1);
		Pagination pagination = iMasterService.corporateCount(corporateName);
		model.addAttribute("paging1",pagination);
		model.addAttribute("corporateTypeList",corporateTypeList);
		model.addAttribute("message", message);
		return new ModelAndView("corporatetypemaster");
	}
	
	@RequestMapping(value="/editCorportaeType",method=RequestMethod.POST)
	public ModelAndView editCorporateType(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer corporateTypeId = Integer.parseInt(request.getParameter("corporatetypeid"));
		String corporateName=request.getParameter("corporatetype");
		Corporatetypemaster corporateType=new Corporatetypemaster();
		corporateType.setCorporatetypeid(corporateTypeId);
		corporateType.setCorporatetype(corporateName);
		String message = iMasterService.editCorporateType(corporateType);
		List<Corporatetypemaster> corporateTypeList=iMasterService.getCorporateTypeList(corporateName,1);
		Pagination pagination = iMasterService.corporateCount(corporateName);
		pagination.setCurrentPageNum(1);
		model.addAttribute("paging1",pagination);
		model.addAttribute("corporateTypeList",corporateTypeList);
		model.addAttribute("message", message);
		return new ModelAndView("corporatetypemaster");
	}
	
	@RequestMapping(value="/deleteCorportaeType",method=RequestMethod.POST)
	public ModelAndView deleteCorporateType(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer corporateTypeId = Integer.parseInt(request.getParameter("corporatetypeid"));
		String corporateName=request.getParameter("corporatetype");
		Corporatetypemaster corporateType=new Corporatetypemaster();
		corporateType.setCorporatetypeid(corporateTypeId);
		corporateType.setCorporatetype(corporateName);
		String message = iMasterService.deleteCorporateType(corporateType);
		List<Corporatetypemaster> corporateTypeList=iMasterService.getCorporateTypeList(corporateName,1);
		Pagination pagination = iMasterService.corporateCount(corporateName);
		model.addAttribute("paging1",pagination);
		model.addAttribute("corporateTypeList",corporateTypeList);
		model.addAttribute("message", message);
		return new ModelAndView("corporatetypemaster");
	}
	
	@RequestMapping(value="/searchCorporateType")
	public ModelAndView searchCorporateType(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String corporateName=request.getParameter("corporatetype");
		String listmessage = null;
		String recordsStr=request.getParameter("search");
		if(recordsStr.equals("Search")){
			recordsStr="1";
		}
		int records=Integer.parseInt(recordsStr);
		List<Corporatetypemaster> corporateTypeList=iMasterService.getCorporateTypeList(corporateName,records);
		Pagination pagination = iMasterService.corporateCount(corporateName);
		pagination.setCurrentPageNum(records);
		if(corporateTypeList.size()==0)
		{
			listmessage = "Sorry Records are not found with name: "+corporateName;
		}
		model.addAttribute("listmessage", listmessage);
		model.addAttribute("searchcorporatetype", corporateName);
		model.addAttribute("corporateTypeList",corporateTypeList);
		model.addAttribute("paging1", pagination);
		return new ModelAndView("corporatetypemaster");
	}
	
	@RequestMapping(value="/openstafflistpage")
	public ModelAndView openStaffListPage(ModelMap model){
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Citymaster> cityList=iMasterService.getCities();
		List<Statemaster> stateList=iMasterService.getStates();
		List<Countrymaster> countryList=iMasterService.getCountries();
		List<Departmentmaster> departmentList=iMasterService.getDepartments();
		model.addAttribute("gendersList",gendersList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("departmentList",departmentList);
		return new ModelAndView("staffmaster");
		
	}
	
	@RequestMapping(value = "/getStateCountryByCity", method=RequestMethod.POST)
	@ResponseBody
	public List<String> getStateCountryByCity(HttpServletRequest request, HttpServletResponse response,@RequestParam("cityid") Integer cityId) {
		
		List<String> statecountry = iMasterService.getStateCountryByCity(cityId);	
		return statecountry;
	}
	@RequestMapping(value="/saveStaff", method=RequestMethod.POST)
	public ModelAndView saveStaff(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute Staffmaster staffMaster,@RequestParam("staffphotos") CommonsMultipartFile[] staffphoto){
		Date date = new Date();
		Integer genderId=Integer.parseInt(request.getParameter("genderid"));
		String dob=request.getParameter("dateofbirth");
		Integer cityId=Integer.parseInt(request.getParameter("cityid"));
		Integer stateId=Integer.parseInt(request.getParameter("stateid"));
		Integer countryId=Integer.parseInt(request.getParameter("countryid"));
		Integer departmentId=Integer.parseInt(request.getParameter("departmentid"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(dob);
			staffMaster.setDob(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		try{
			if (staffphoto != null && staffphoto.length > 0) {
	            for (CommonsMultipartFile aFile : staffphoto){
	                  
	                   aFile.getOriginalFilename();
	                   staffMaster.setStaffphoto(aFile.getBytes());
	                          
	            }
	        }
	  }
	catch(Exception e){
		e.printStackTrace();
	}
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		dropdowndetails.setDropdowndetailsid(genderId);
		staffMaster.setDropdowndetails(dropdowndetails);
		Citymaster city=new Citymaster();
		city.setCityid(cityId);
		staffMaster.setCitymaster(city);
		Statemaster state=new Statemaster();
		state.setStateid(stateId);
		staffMaster.setStatemaster(state);
		Countrymaster country=new Countrymaster();
		country.setCountryid(countryId);
		staffMaster.setCountrymaster(country);
		Departmentmaster department=new Departmentmaster();
		department.setDepartmentid(departmentId);
		staffMaster.setDepartmentmaster(department);
		String message = iMasterService.saveStaff(staffMaster);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Citymaster> cityList=iMasterService.getCities();
		List<Statemaster> stateList=iMasterService.getStates();
		List<Countrymaster> countryList=iMasterService.getCountries();
		List<Departmentmaster> departmentList=iMasterService.getDepartments();
		model.addAttribute("gendersList",gendersList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("departmentList",departmentList);
		model.addAttribute("message", message);
		return new ModelAndView("staffmaster");
	}
	
	@RequestMapping("/getServicesByPackageId")
	@ResponseBody
	public List getServicesByPackageId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("packageId") Integer packageId) {
		List<String> services = iMasterService.getServicesByPackageId(packageId);
		return services;

	}
	@RequestMapping("/stafflistpage")
	public ModelAndView staffList(){
		List<Staffmaster> staffList=iMasterService.getStaffList();
		return new ModelAndView("stafflist","staffList",staffList);
	}
	@RequestMapping("/openstaffeditpage")
	public ModelAndView openStaffListModifications(HttpServletRequest request,HttpServletResponse response)
	{
		Integer id = Integer.parseInt(request.getParameter("staffid"));
		Staffmaster staff=iMasterService.fetchStaffWithId(id);
		return new ModelAndView("staffmodification","staff",staff);
	}
	@RequestMapping(value="/updatestaff")
	public ModelAndView updateStaff(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute Staffmaster staffMaster,@RequestParam("staffphotos") CommonsMultipartFile[] staffphoto){
		Date date = new Date();
		Integer staffId=Integer.parseInt(request.getParameter("staffid"));
		Integer genderId=Integer.parseInt(request.getParameter("genderid"));
		String dob=request.getParameter("dateofbirth");
		Integer cityId=Integer.parseInt(request.getParameter("cityid"));
		Integer stateId=Integer.parseInt(request.getParameter("stateid"));
		Integer countryId=Integer.parseInt(request.getParameter("countryid"));
		Integer departmentId=Integer.parseInt(request.getParameter("departmentid"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(dob);
			staffMaster.setDob(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		try{
			if (staffphoto != null && staffphoto.length > 0) {
	            for (CommonsMultipartFile aFile : staffphoto){
	                  
	                   aFile.getOriginalFilename();
	                   staffMaster.setStaffphoto(aFile.getBytes());
	                          
	            }
	        }
	  }
	catch(Exception e){
		e.printStackTrace();
	}
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		dropdowndetails.setDropdowndetailsid(genderId);
		staffMaster.setDropdowndetails(dropdowndetails);
		Citymaster city=new Citymaster();
		city.setCityid(cityId);
		staffMaster.setCitymaster(city);
		Statemaster state=new Statemaster();
		state.setStateid(stateId);
		staffMaster.setStatemaster(state);
		Countrymaster country=new Countrymaster();
		country.setCountryid(countryId);
		staffMaster.setCountrymaster(country);
		Departmentmaster department=new Departmentmaster();
		department.setDepartmentid(departmentId);
		staffMaster.setDepartmentmaster(department);
		String message = iMasterService.editStaff(staffMaster);
		List<Dropdowndetails> gendersList=iMasterService.getGenders();
		List<Citymaster> cityList=iMasterService.getCities();
		List<Statemaster> stateList=iMasterService.getStates();
		List<Countrymaster> countryList=iMasterService.getCountries();
		List<Departmentmaster> departmentList=iMasterService.getDepartments();
		model.addAttribute("gendersList",gendersList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("departmentList",departmentList);
		model.addAttribute("message", message);
		return new ModelAndView("staffmodification");
	}

	@RequestMapping(value="/savedepartment", method = RequestMethod.POST)
	
	public String saveDepartment(@ModelAttribute("departmentmaster") Departmentmaster departmentmaster,Map<String,Object> model,HttpServletRequest request){
		
		String pageName=request.getParameter("pageName");
		String save=request.getParameter("search");
		String search=request.getParameter("deptname");
		if(save.equals("Save"))
		{
			iMasterService.saveDepartment(departmentmaster);
			List<Departmentmaster> deptlists = iMasterService.listDepartments(departmentmaster, 1,pageName,search);
     		Pagination pagination = iMasterService.deparmentCount(departmentmaster, pageName,search);
			pagination.setCurrentPageNum(1);
			if (deptlists.size() != 0) {
				request.setAttribute("pageName", "FirstPage");
			} else {
				request.setAttribute("error", "No Records Found");
				request.setAttribute("pageName", "FirstPage");
			}
			model.put("deptlists", deptlists);
			model.put("paging1", pagination);
			return "departmentmaster";
			
		}
		else
		{

			if(save.equals("search")){
				save="1";
			}
			int records=Integer.parseInt(save);
			List<Departmentmaster> deptlists = iMasterService.listDepartments(departmentmaster, records,pageName,search);
			Pagination pagination = iMasterService.deparmentCount(departmentmaster, pageName,search);
			pagination.setCurrentPageNum(records);
     		if(deptlists.size()!=0){
     			request.setAttribute("pageName", "FirstPage");
     		}
     		else{
     			request.setAttribute("error", "No Records Found");
     			request.setAttribute("pageName", "FirstPage");
     		}
     		model.put("deptlists",deptlists);
		 		model.put("paging1", pagination);
		 		 model.put("deptlists", deptlists);
     		return "departmentmaster";
		}
		
      }

	
	@RequestMapping(value="/listdepartments", method = RequestMethod.POST)
	
   public ModelAndView listDepartmentsByName(@ModelAttribute("DepartmentMaster") Departmentmaster departmentmaster,HttpServletRequest request){
		
	String departmentname=request.getParameter("departmentname");
	 	   List<Departmentmaster> deptlists = iMasterService.getDepartment(departmentname);
		  return new ModelAndView("departmentmaster","deptlists",deptlists);
		
    }

	@RequestMapping(value="/editdepartment")
	public ModelAndView editDepartment(@RequestParam("depId") Integer depId){
		Departmentmaster departmentmaster  =iMasterService.editDepartment(depId);
	      
	 return new ModelAndView("updatedepartment","departmentmaster",departmentmaster);
	
	
	}
	
	@RequestMapping(value="/updatedepartment" ,method = RequestMethod.POST)
	public String updateDepartment(@ModelAttribute("departmentmaster") Departmentmaster departmentmaster,String departmentname,HttpServletRequest request){

		 String vaild=request.getParameter("valid");
		 if(vaild=="true"|| vaild.equals("true"))
		 {
			 departmentmaster.setValid(true);
		 }
		 else
		 {
		   departmentmaster.setValid(false);
		 }
		 
		 ModelAndView modelandview=new ModelAndView();
		 iMasterService.updateDepartment(departmentmaster);
	     List<Departmentmaster> deptlists = iMasterService.getDepartment(departmentname);
	     modelandview.addObject("deptlists",deptlists);
		return  "redirect:departmentlist";
    	 }
	

	    
	@RequestMapping(value="/departmentlist" ,method = RequestMethod.GET)
	public String listDepartments(@ModelAttribute("departmentmaster") Departmentmaster departmentmaster,HttpServletRequest request,Map<String,Object> model){
		String pageName = null;
		pageName = request.getParameter("pageName");
		String search=request.getParameter("departmentname");
	    List<Departmentmaster> deptlists = iMasterService.listDepartments(departmentmaster, 1,pageName,search);
		Pagination pagination = iMasterService.deparmentCount(departmentmaster, pageName,search);
		pagination.setCurrentPageNum(1);
		if (deptlists.size() != 0) {
			request.setAttribute("pageName", "FirstPage");
		} else {
			request.setAttribute("error", "No Records Found");
			request.setAttribute("pageName", "FirstPage");
		}
		model.put("deptlists", deptlists);
	model.put("paging1", pagination);
		return "departmentmaster";
	
}
	@RequestMapping(value="/skillmaster", method=RequestMethod.GET)
     public String skillmaster(Map<String,Object>  model,HttpServletRequest request){
		
		String skillname=null;
		
		List<Skillmaster> searchlist=iMasterService.getByName(skillname,1);
		Pagination pagination = iMasterService.skillCount(skillname);
		pagination.setCurrentPageNum(1);
	 	model.put("paging1", pagination);
	    model.put("skillslist",searchlist);
		return "skillmaster";
	}
	
	
	
	
	@RequestMapping(value="/searchSkill" ,method=RequestMethod.GET)
	 public String searchSkill(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
		String listmessage=null;
		  String skillname=	request.getParameter("skill123");
		  
		String recordsStr=request.getParameter("search");
		System.out.println(recordsStr);
			if(recordsStr.equals("Search")){
				recordsStr="1";
			}
			int records=Integer.parseInt(recordsStr);
		    List<Skillmaster> searchlist=iMasterService.getByName(skillname,records);
			Pagination pagination = iMasterService.skillCount(skillname);
			pagination.setCurrentPageNum(records);
		 	if(searchlist.size()==0)
			{
		 	 listmessage = "Sorry Records are not found with name:"+skillname;
			}
		 	model.put("skillname", skillname);
		 	model.put("paging1", pagination);
		    model.put("skillslist",searchlist);
		    model.put("message",listmessage);
			return "skillmaster";
	 }
	

	
	@RequestMapping(value="/addskills", method=RequestMethod.POST)
	public String addSkills(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
		String skillname=request.getParameter("skillname");
		Skillmaster skillmaster = new Skillmaster();
		skillmaster.setSkillname(skillname);
		String message = iMasterService.saveSkills(skillmaster);
		String serarchSkillname=null;
		List<Skillmaster> searchlist=iMasterService.getByName(serarchSkillname,1);
		Pagination pagination = iMasterService.skillCount(serarchSkillname);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		  model.put("message", message);
		model.put("skillslist",searchlist);
		return "skillmaster";
	}


	@RequestMapping(value="/editskills")
	public String editSkills(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
		Integer skillId = Integer.parseInt(request.getParameter("skillid"));
		String skillName=	request.getParameter("skillname");
	
		Skillmaster skillmaster = new Skillmaster();
		skillmaster.setSkillid(skillId);;
		skillmaster.setSkillname(skillName);
	
		String message = iMasterService.editSkills(skillmaster);
		
		List<Skillmaster> searchlist=iMasterService.getByName(skillName,1);
		Pagination pagination = iMasterService.skillCount(skillName);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		model.put("message", message);
		model.put("skillslist",searchlist);
		return "skillmaster";
	}
	@RequestMapping(value="/deleteskills")
	public String deleteSkills(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
		Integer skillId = Integer.parseInt(request.getParameter("skillid"));
		String skillName =	request.getParameter("skillname");
		String serarchSkillname=null;
		Skillmaster skillmaster = new Skillmaster();
		skillmaster.setSkillid(skillId);;
		skillmaster.setSkillname(skillName);
		
		String message = iMasterService.deleteSkills(skillmaster);
	
		List<Skillmaster> searchlist=iMasterService.getByName(serarchSkillname,1);
		Pagination pagination = iMasterService.skillCount(serarchSkillname);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		
		model.put("message", message);
		model.put("skillslist",searchlist);
		return "skillmaster";
	}



	@RequestMapping(value="/Qualification", method=RequestMethod.GET)
    public String qualificationmaster(Map<String,Object>  model,HttpServletRequest request){
		
		String qualification=null;
		List<Qualificationmaster> searchlist=iMasterService.getQualificationByName(qualification, 1);
		Pagination pagination = iMasterService.qualifyCount(qualification);
		pagination.setCurrentPageNum(1);
 		
 		
	 	model.put("paging1", pagination);
	    model.put("qlist",searchlist);
		return "qualificationmaster";
	}
	
	
	@RequestMapping(value="/saveQualification", method=RequestMethod.POST)
	public String saveQualification(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String qual=request.getParameter("qualification");
		String squalification=null;
		Qualificationmaster qualificationmaster = new Qualificationmaster();
		qualificationmaster.setQualification(qual);
		String message = iMasterService.saveQualification(qualificationmaster);
		
		List<Qualificationmaster> searchlist=iMasterService.getQualificationByName(squalification,1);
		
		Pagination pagination = iMasterService.qualifyCount(squalification);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		
		model.put("message", message);
		model.put("qlist",searchlist);
		return "qualificationmaster";    
		
	}

	
	@RequestMapping(value="/editQualification")
	public String editQualifications(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer qualificationId = Integer.parseInt(request.getParameter("qualificationid"));
		String qualification=	request.getParameter("qualification");
		
		Qualificationmaster qualificationmaster = new Qualificationmaster();
		
		qualificationmaster.setQualificationid(qualificationId);
		qualificationmaster.setQualification(qualification);
	
		String message = iMasterService. editQualifications(qualificationmaster);
		
		List<Qualificationmaster> searchlist=iMasterService.getQualificationByName(qualification,1);
		Pagination pagination = iMasterService.qualifyCount(qualification);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		
		model.put("message", message);
		model.put("qlist",searchlist);
		return "qualificationmaster";    
		
		
	}
	@RequestMapping(value="/deleteQualification")
	public String deletekills(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer qualificationId = Integer.parseInt(request.getParameter("qualificationid"));
		String qualification =	request.getParameter("qualification");
		String qualify=null;
		Qualificationmaster qualificationmaster = new Qualificationmaster();
		qualificationmaster.setQualificationid(qualificationId);
		qualificationmaster.setQualification(qualification);
		
		String message = iMasterService.deleteQualification(qualificationmaster);
		List<Qualificationmaster> searchlist=iMasterService.getQualificationByName(qualify,1);
		Pagination pagination = iMasterService.qualifyCount(qualify);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		
		model.put("message", message);
		model.put("qlist",searchlist);
		return "qualificationmaster";    
	}

@RequestMapping(value="/searchQualification" ,method=RequestMethod.GET)
public String searchQualification(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	String listmessage=null;
  String qualification=request.getParameter("qualification1");
  
  String recordsStr=request.getParameter("search");

		if(recordsStr.equals("Search")){
			recordsStr="1";
		}
		int records=Integer.parseInt(recordsStr);
	    List<Qualificationmaster> searchlist=iMasterService.getQualificationByName(qualification, records);
		Pagination pagination = iMasterService.qualifyCount(qualification);
		pagination.setCurrentPageNum(records);
	 	if(searchlist.size()==0)
		{
	 	 listmessage = "Sorry Records are not found with name:"+qualification;
		}
	 	model.put("qualification1", qualification);
	 	model.put("paging1", pagination);
	    model.put("qlist",searchlist);
	    model.put("message",listmessage);
		return "qualificationmaster";
 }
  



@RequestMapping(value="/marketingVoucher", method=RequestMethod.GET)
public String marketVoucherMaster(Map<String,Object> model,HttpServletRequest request){

 String mcompany=null;

List<Marketingvouchermaster> mlist = iMasterService.getMvoucherByName(mcompany, 1);
Pagination pagination = iMasterService.marketCount(mcompany);
pagination.setCurrentPageNum(1);
	model.put("paging1", pagination);
model.put("mlist",mlist);
return "marketingvoucher";
}



@RequestMapping(value="/saveMarketVoucher", method=RequestMethod.GET)
public String saveMarketingVoucher(ModelMap model,HttpServletResponse response,HttpServletRequest request){
 Date date=new Date(); 
  String marketingid =request.getParameter("marketingvoucherid");
String remarks=request.getParameter("remark");
 String Company=request.getParameter("mcompany") ;
 BigDecimal big = new BigDecimal(request.getParameter("voucheramount"));
  Marketingvouchermaster marketvoucher=new Marketingvouchermaster();
	String validfrom=request.getParameter("validfrom");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date1 = new Date();
	try {
		date1 = sdf.parse(validfrom);
		marketvoucher.setValidfrom(date1);
		
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	}
	String validto=request.getParameter("validto");

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	try {
		date = sdf1.parse(validto);
		marketvoucher.setValidto(date);
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	}
	marketvoucher.setRemarks(remarks);
	marketvoucher.setMarketingcompany(Company);
	marketvoucher.setVoucheramount(big);
	 if(marketingid!=""){
    	 Integer marid = Integer.parseInt(marketingid);
    	 marketvoucher.setMarketingvoucherid(marid);
     }
	
     String message=iMasterService.saveMarketingvoucher(marketvoucher);
	String mcompany=null;
	List<Marketingvouchermaster> mlist = iMasterService.getMvoucherByName(mcompany, 1);
	Pagination pagination=iMasterService.marketCount(mcompany);
     model.addAttribute("message", message);
	  model.addAttribute("mlist",mlist);
	  model.addAttribute("paging1",pagination);
	  
	return "marketingvoucher";
}



@RequestMapping(value="/searchMarketVoucher",method=RequestMethod.GET)
public String searchMvoucher(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
	String listmessage=null;
	  String company=request.getParameter("market123");
	  
	String recordsStr=request.getParameter("search");
	
		if(recordsStr.equals("Search")){
			recordsStr="1";
		}
		int records=Integer.parseInt(recordsStr);
	    List<Marketingvouchermaster> searchlist=iMasterService.getMvoucherByName(company, records);
		Pagination pagination = iMasterService.marketCount(company);
		pagination.setCurrentPageNum(records);
	 	if(searchlist.size()==0)
		{
	 	 listmessage = "Sorry Records are not found with name:"+company;
		}
	 
	 	model.put("company", company);
	 	model.put("paging1", pagination);
	    model.put("mlist", searchlist);
	    model.put("message",listmessage);
	    
		return "marketingvoucher";
 }


@RequestMapping(value="/roommaster", method=RequestMethod.GET)
public String roomMaster(Map<String,Object>  model,HttpServletRequest request){
	
	Integer roomnum=null;
	
	List<Roommaster> searchlist=iMasterService.getRoomsByNum(roomnum,1);
	Pagination pagination = iMasterService.roomCount(roomnum);;
	pagination.setCurrentPageNum(1);
	
	
 	model.put("paging1", pagination);
    model.put("roomlist",searchlist);
	return "roommaster";
}

@RequestMapping(value="/addRooms", method=RequestMethod.POST)
public String addRooms(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	String description=request.getParameter("roomname");
	Integer roomno=Integer.parseInt(request.getParameter("roomnum"));
	Roommaster room = new Roommaster();
	room.setDescription(description);
	room.setRoomno(roomno);
	String message = iMasterService.saveRoom(room);
	Integer serarchRoomNum = null;
	List<Roommaster> searchlist=iMasterService.getRoomsByNum(serarchRoomNum,1);
	Pagination pagination = iMasterService.roomCount(serarchRoomNum);
	pagination.setCurrentPageNum(1);

	  model.put("paging1", pagination);
	  model.put("message", message);
	model.put("roomlist",searchlist);
	return  "roommaster";
}
	 

 @RequestMapping(value= "/searchRoom",method=RequestMethod.GET)
 public String searchRoom(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
		 String listmessage=null;	
		String recordsStr=request.getParameter("search1");
	
		String roomNo=request.getParameter("room123");
		Integer roomnumber = null;
		if(roomNo.equals("") || roomNo==null){
			 
		}
		else{
			roomnumber=Integer.parseInt(roomNo);  
		}
		
		
			if(recordsStr.equals("Search")){
				recordsStr="1";
			}
			
			int records=Integer.parseInt(recordsStr);
		
	        List<Roommaster> roomlist=iMasterService.getRoomsByNum(roomnumber, records);
			Pagination pagination = iMasterService.roomCount(roomnumber);
			
			pagination.setCurrentPageNum(records);
			
		 	if(roomlist.size()==0)
			{
		 	 listmessage = "Sorry Records are not found with name:"+roomnumber;
			}
		 	model.put("roomnum", roomnumber);
		 	model.put("paging1", pagination);
		 	model.put("listmessage",listmessage);
		    model.put("roomlist",roomlist);
			return "roommaster";
 }
	  
	@RequestMapping(value="/editRoom")
	public String editRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer roomid = Integer.parseInt(request.getParameter("roomid"));
		Integer roomnum1 = Integer.parseInt(request.getParameter("roomnum"));	
		String Roomdescription=	request.getParameter("roomname");
		Roommaster room = new Roommaster();
		room.setRoomid(roomid);
		room.setRoomno(roomnum1);
		room.setDescription(Roomdescription);
		String message = iMasterService.editRoom(room);
		List<Roommaster> searchlist=iMasterService.getRoomsByNum(roomnum1,1);
		Pagination pagination = iMasterService.roomCount(roomnum1);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		model.put("message", message);
		model.put("roomlist",searchlist);
		return "roommaster";
	}
	@RequestMapping(value="/deleteRoom")
	public String deletRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Integer roomid = Integer.parseInt(request.getParameter("roomid"));
		String description =	request.getParameter("roomname");
	Integer roomno=Integer.parseInt(request.getParameter("roomnum"));
		Roommaster room = new Roommaster();
		room.setRoomid(roomid);
		room.setDescription(description);
		room.setRoomno(roomno);
		String message = iMasterService.deleteRoom(room);
		Integer roomnum1=null;
		List<Roommaster> searchlist=iMasterService.getRoomsByNum(roomnum1,1);
		Pagination pagination = iMasterService.roomCount(roomnum1);
		pagination.setCurrentPageNum(1);
	
		  model.put("paging1", pagination);
		model.put("message", message);
		model.put("roomlist",searchlist);
		return "roommaster";}

	

@RequestMapping(value="/segmentmaster")
 public String segmentmaster(Map<String,Object> model,HttpServletRequest request){
	 String segname=null;
	List<Segmentmaster>seglist=iMasterService.getSegmentByName(segname, 1);
	Pagination pagination = iMasterService.segmentCount(segname);
	pagination.setCurrentPageNum(1);
 	model.put("paging1", pagination);
    model.put("seglist",seglist);
      return "segmentmaster";
	}

@RequestMapping(value="/addSegments")
public String saveSegment(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	String segname=request.getParameter("segname");
	String Description=request.getParameter("segdescription");
Segmentmaster segment=new Segmentmaster();
segment.setSegmentname(segname);
segment.setDescription(Description);
String message=iMasterService.saveSegment(segment);
List<Segmentmaster>seglist=iMasterService.getSegmentByName(segname, 1);
Pagination pagination = iMasterService.segmentCount(segname);
pagination.setCurrentPageNum(1);
model.put("message", message);
model.put("paging1", pagination);
model.put("seglist",seglist);
 return "segmentmaster";
}

@RequestMapping(value="/editSegments")
public String editSegment(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	Integer segmentid = Integer.parseInt(request.getParameter("segid"));
	String segmentname=request.getParameter("segname");
	String Description=request.getParameter("segdescription");
	Segmentmaster segment = new Segmentmaster();
	segment.setSegmentid(segmentid);
	segment.setSegmentname(segmentname);
	segment.setDescription(Description);
	String message = iMasterService.editSegment(segment);
	List<Segmentmaster>seglist=iMasterService.getSegmentByName(segmentname, 1);
	Pagination pagination = iMasterService.segmentCount(segmentname);
	pagination.setCurrentPageNum(1);
	model.put("message", message);
	model.put("paging1", pagination);
   model.put("seglist",seglist);
     return "segmentmaster";
	}

@RequestMapping(value="/deleteSegments")
public String deleteSegment(ModelMap model, HttpServletRequest request, HttpServletResponse response){
	Integer segmentid = Integer.parseInt(request.getParameter("segid"));
	String segname =request.getParameter("segname");
	String Description=request.getParameter("segdescription");

	Segmentmaster segment = new Segmentmaster();
	segment.setSegmentid(segmentid);
	segment.setSegmentname(segname);
	segment.setDescription(Description);
	String message = iMasterService.deleteSegment(segment);	
	List<Segmentmaster>seglist=iMasterService.getSegmentByName(segname, 1);
	Pagination pagination = iMasterService.segmentCount(segname);
	pagination.setCurrentPageNum(1);
	model.put("message", message);
	model.put("paging1", pagination);
   model.put("seglist",seglist);
     return "segmentmaster";
	}
@RequestMapping(value="/searchSegment", method=RequestMethod.POST)
 public String searchSegment(Map<String,Object>  model, HttpServletRequest request, HttpServletResponse response){
	 String listmessage=null;
	  String segname=request.getParameter("searchseg");  
	String recordsStr=request.getParameter("search");
	
		if(recordsStr.equals("Search")){
			recordsStr="1";
		}
		int records=Integer.parseInt(recordsStr);
	
	    List<Segmentmaster> slist=iMasterService.getSegmentByName(segname, records);
		Pagination pagination = iMasterService.segmentCount(segname);
		pagination.setCurrentPageNum(records);
		
	 	if(slist.size()==0)
		{
	 	 listmessage = "Sorry Records are not found with name:"+segname;
		}
	 	model.put("segname", segname);
	 	model.put("paging1", pagination);
	 	model.put("message",listmessage);
	    model.put("seglist",slist);
		return "segmentmaster";
}
  
	

@RequestMapping(value = "/servicestaff")
public String getServiceStaff(Map<String,Object> model) {
	String serviceName=null;
	List<Servicestaffmaster> searchlist=iMasterService.getsearchServiceStaff(serviceName, 1);
	Pagination pagination = iMasterService.serviceStaffCount(serviceName);
	pagination.setCurrentPageNum(1);
 	
 	
	List<Servicemaster> servicesList = iMasterService.getServices();
	List<Staffmaster> staffList = iMasterService.getStaff();

	model.put("paging1", pagination);
 	
	model.put("staffList", staffList);
	model.put("servicesList", servicesList);
	model.put("servicestaff", searchlist);
	return "servicestaff";
}

@RequestMapping(value="/saveServiceStaff")
public String saveServiceStaff(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response){
	
	String serviceName=null;
	String description=request.getParameter("description");
	Integer serviceId = Integer.parseInt(request.getParameter("serviceId"));
	Integer staffid=Integer.parseInt(request.getParameter("staffid"));
	Servicestaffmaster servicestaff=new Servicestaffmaster();
	servicestaff.setDescription(description);
	Servicemaster serviceMaster=new Servicemaster();
	serviceMaster.setServiceid(serviceId);
	servicestaff.setServicemaster(serviceMaster);
	Staffmaster staff=new Staffmaster();
	staff.setStaffid(staffid);
	servicestaff.setStaffmaster(staff);
	String message = iMasterService.saveServiceStaff(servicestaff);
	List<Servicemaster> servicesList = iMasterService.getServices();
	List<Staffmaster> staffList = iMasterService.getStaff();
	List<Servicestaffmaster> searchlist=iMasterService.getsearchServiceStaff(serviceName, 1);
	Pagination pagination = iMasterService.serviceStaffCount(serviceName);
	pagination.setCurrentPageNum(1);
	model.put("paging1", pagination);
	model.put("servicestaff", searchlist);
	model.put("staffList", staffList);
	model.put("servicesList", servicesList);
	model.put("message", message);
	return "servicestaff";
}
@RequestMapping(value="/editServiceStaff")
public String editServiceStaff(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response){
	Integer servicestaffId = Integer.parseInt(request.getParameter("servicestaffid"));
	String serviceName=null;
	String description1=request.getParameter("description");
	Integer serviceId1 = Integer.parseInt(request.getParameter("serviceId"));
	Integer staffId=Integer.parseInt(request.getParameter("staffid"));
	Servicestaffmaster servicestaff=new Servicestaffmaster();
	servicestaff.setServicestaffid(servicestaffId);
	servicestaff.setDescription(description1);
	Servicemaster serviceMaster=new Servicemaster();
	serviceMaster.setServiceid(serviceId1);
	servicestaff.setServicemaster(serviceMaster);
	Staffmaster staff=new Staffmaster();
	staff.setStaffid(staffId);
	servicestaff.setStaffmaster(staff);
	String message = iMasterService.editServiceStaff(servicestaff);
	List<Servicemaster> servicesList = iMasterService.getServices();
	List<Staffmaster> staffList = iMasterService.getStaff();
	List<Servicestaffmaster> searchlist=iMasterService.getsearchServiceStaff(serviceName, 1);
	Pagination pagination = iMasterService.serviceStaffCount(serviceName);
	pagination.setCurrentPageNum(1);
	model.put("paging1", pagination);
	model.put("servicestaff", searchlist);
	model.put("staffList", staffList);
	model.put("servicesList", servicesList);
	model.put("message", message);
	return "servicestaff";
	
}
@RequestMapping(value="/deleteServiceStaff")
public String deleteServiceStaff(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response){
	String serviceName=null;
	Integer servicestaffId = Integer.parseInt(request.getParameter("servicestaffid"));
	String description=request.getParameter("description");
	Integer serviceid = Integer.parseInt(request.getParameter("serviceId"));
	Integer staffId=Integer.parseInt(request.getParameter("staffid"));
	Servicestaffmaster servicestaff=new Servicestaffmaster();
	servicestaff.setServicestaffid(servicestaffId);
	servicestaff.setDescription(description);
	Servicemaster serviceMaster=new Servicemaster();
	serviceMaster.setServiceid(serviceid);
	servicestaff.setServicemaster(serviceMaster);
	Staffmaster staff=new Staffmaster();
	staff.setStaffid(staffId);
	servicestaff.setStaffmaster(staff);
	String message = iMasterService.deleteServiceStaff(servicestaff);
	List<Servicemaster> servicesList = iMasterService.getServices();
	List<Staffmaster> staffList = iMasterService.getStaff();
	List<Servicestaffmaster> searchlist=iMasterService.getsearchServiceStaff(serviceName, 1);
	Pagination pagination = iMasterService.serviceStaffCount(serviceName);
	pagination.setCurrentPageNum(1);
	model.put("paging1", pagination);
	model.put("servicestaff", searchlist);
	model.put("staffList", staffList);
	model.put("servicesList", servicesList);
	model.put("message", message);
	return "servicestaff";
}
@RequestMapping(value="/searchServiceStaff", method=RequestMethod.GET)
 public String searchService(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response){
       String listmessage=null;
	  String serviceName=request.getParameter("serviceName");
	  String recordStr=request.getParameter("search");
	  if(recordStr.equals("Search")){
		  recordStr="1";
	  }
	
	  int records=Integer.parseInt(recordStr);
		List<Servicestaffmaster> searchstaff1=iMasterService.getsearchServiceStaff(serviceName, records);
	    Pagination pagination=iMasterService.serviceStaffCount(serviceName);
	    pagination.setCurrentPageNum(records);
		List<Servicemaster> servicesList = iMasterService.getServices();
	    List<Staffmaster> staffList = iMasterService.getStaff();
	 	if(searchstaff1.size()==0)
		{
	 	 listmessage = "Sorry Records are not found with name:"+serviceName;
		}
	 	model.put("paging1", pagination);
	 	model.put("staffList", staffList);
	 	model.put("servicesList", servicesList);
		model.put("message", listmessage);
		model.put("serviceName", serviceName);
		model.put("servicestaff", searchstaff1);
		return "servicestaff";
 }


@RequestMapping(value="/saveskills", method= RequestMethod.GET)
   public ModelAndView saveskills( )
{ 
	  return new ModelAndView("skillmaster");
}

@RequestMapping(value = "/getSkillId", method = RequestMethod.GET)
@ResponseBody
public Integer getskilldetails(HttpServletRequest request, HttpServletResponse response,
		@RequestParam("skillname") String skillname) {
	Integer skillid = iMasterService.getskillid(skillname);
	
	return skillid;
}


@RequestMapping(value="/staffskills", method= RequestMethod.GET)
   public String staffskills(Skillmaster skillmaster,Model  model,Staffmaster staffmaster)
{ 
	
	List<Skillmaster> skills = iMasterService.getSkills();

	List<Staffmaster> staff = iMasterService.getStaff();

	model.addAttribute("staffs",staff);
    model.addAttribute("skills",skills);
      return "staffskills";
}



@RequestMapping(value="/savestaffskills", method= RequestMethod.POST,consumes="application/json")
@ResponseBody
public String addStaffSkills(@RequestBody List<StaffSkillsData> sfs,HttpServletRequest req, HttpServletResponse res)
{

	iMasterService.saveStaffSkills(sfs);
	return "Successfully Saved";

     }

@SuppressWarnings("unchecked")
@RequestMapping(value="/getSkill", method = RequestMethod.GET)
@ResponseBody
public  List getSkill(HttpServletRequest request, HttpServletResponse response, @RequestParam("staffid") Integer staffid){
	Set<Staffskill> staffskill = iMasterService.getSkill1(staffid);
	List list = new LinkedList();
	 
	Iterator<Staffskill> sfsk = staffskill.iterator();
	Integer skillus = null;
	String skillname = null;
	Integer skillid=null;
	while (sfsk.hasNext()){
		
		Staffskill ss = sfsk.next();
		if(ss.isValid())
		{
		
			skillname=ss.getSkillmaster().getSkillname();
			skillid=ss.getSkillmaster().getSkillid();
			list.add(skillus);
			list.add(skillname);
			list.add(skillid);
		}
		
	}
	return list;
}
@RequestMapping("/membershippage")
public ModelAndView getMembership(ModelMap model) {
	List<Membershipmaster> membershipList = iMasterService.getMembership();
	model.addAttribute("membershipList", membershipList);
	return new ModelAndView("membershipmaster");
}


	@RequestMapping("/membership")	
	public String savemembership(@ModelAttribute Membershipmaster membershipmaster, HttpServletRequest req,ModelMap model){
	{
String datefrom = req.getParameter("validfrom123");
String dateto = req.getParameter("validto123");
String id=req.getParameter("idname");
Date validFrom = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
		validFrom = sdf.parse(datefrom);
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	}
	membershipmaster.setValidfrom(validFrom);
   Date validTo = new Date();
	try {
		validTo = sdf.parse(dateto);
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	}

	membershipmaster.setValidto(validTo);
     String value=iMasterService.membershipSave(membershipmaster,id);
     List<Membershipmaster> membershipList = iMasterService.getMembership();
		model.put("membershipList", membershipList);
     model.put("value", value);
	return "membershipmaster";
	}
	}

	
	@RequestMapping("editmember")
	public ModelAndView edit(@ModelAttribute Membershipmaster membershipmaster, HttpServletRequest req,ModelMap model)
	{
		String memberid=req.getParameter("val");
		List membervalue=iMasterService.editvalue(membershipmaster,memberid);
		   List<Membershipmaster> membershipList = iMasterService.getMembership();
			model.put("membershipList", membershipList);
			model.put("membervalue", membervalue);
		return new ModelAndView("membershipmaster","membervalue",membervalue);
	}
	@RequestMapping("/Search")
	@ResponseBody
	public List listMemberByName(HttpServletRequest request, HttpServletResponse response){
		System.out.println("CALL");
		String membername=request.getParameter("membername");
	 	System.out.println("MEMBER NAME"+membername);
		 List<Membershipmaster> lists = iMasterService.getMemberShipName(membername);
		 Iterator<Membershipmaster> iterator = lists.iterator();
		 while(iterator.hasNext()){
			 Membershipmaster next = iterator.next();
			 System.out.println(next);
		 }
		 System.out.println(lists);
		 List list = new LinkedList<>();
		for (Membershipmaster membershipmaster : lists) {
			list.add(membershipmaster.getMembername());
			/*list.add(membershipmaster.getAddress());
			list.add(membershipmaster.getPhoneno());
			list.add(membershipmaster.getEmail());*/
			list.add(membershipmaster.getAmountpaid());
			list.add(membershipmaster.getValidfrom());
			list.add(membershipmaster.getValidto());
			list.add(membershipmaster.getRemarks());
			list.add(membershipmaster.isBlacklisted());
			list.add(membershipmaster.getBlacklistreason());
			list.add(membershipmaster.getMembershipid());
			//list.add(membershipmaster.getBl());
		}
		 System.out.println(list.size());
	    return list;
		}
	@RequestMapping("/corporatePage")
	public ModelAndView getCorporate(ModelMap model){
		List<Corporatemaster> corporateList =iMasterService.getCorporateList();
		System.out.println("@@@@@@@@"+corporateList);
		model.addAttribute("corporateList",corporateList);
		 List<Corporatetypemaster> corporateTypeList = iMasterService.getListCorporateType();
		 model.addAttribute("corporateTypeList",corporateTypeList);
		 System.out.println("!!!!!!!!!"+corporateTypeList);
		return new ModelAndView("corporatemaster");
		}
	@RequestMapping("/savecorporatemaster")
	public String saveCorporateMaster(@ModelAttribute Corporatemaster corporatemaster,HttpServletRequest req,ModelMap model){
		String corporateid = req.getParameter("corpid");
		String type=req.getParameter("corporateType");
		int value1=Integer.parseInt(type);
		System.out.println(value1);
		Corporatetypemaster master=new Corporatetypemaster();
	     	master.setCorporatetypeid(value1);
		corporatemaster.setCorporatetypemaster(master);
		String value=iMasterService.saveCorporateMaster(corporatemaster,corporateid);
			     List<Corporatemaster> corporateList = iMasterService.getCorporateList();
			     List<Corporatetypemaster> corporateTypeList = iMasterService.getListCorporateType();
			     System.out.println("list:"+corporateTypeList.size());
					model.put("corporateList", corporateList);
			     model.put("value", value);
			     model.put("corporateTypeList",corporateTypeList);
			     return "corporatemaster";
			}
	
	@RequestMapping(value = "/searchcorporatemaster",method = RequestMethod.POST)
	@ResponseBody
	public List getsubgroupDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("corporatename") String corporatename) {
		List<Corporatemaster> serchCorporateName = iMasterService.getSerchCorporateName(corporatename);
		List list=new LinkedList();
		for (Corporatemaster corporatemaster : serchCorporateName) {
			list.add(corporatemaster.getCorporatetypemaster().getCorporatetype());
			list.add(corporatemaster.getCorporatename());
			list.add(corporatemaster.getDescription());
			list.add(corporatemaster.getContactperson1());
			list.add(corporatemaster.getAddress1());
			list.add(corporatemaster.getPhone1());
			list.add(corporatemaster.getFax1());
			list.add(corporatemaster.getEmail1());
			list.add(corporatemaster.getContactperson2());
			list.add(corporatemaster.getAddress2());
			list.add(corporatemaster.getPhone2());
			list.add(corporatemaster.getFax2());
			list.add(corporatemaster.getEmail2());
			list.add(corporatemaster.getContactperson3());
			list.add(corporatemaster.getAddress3());
			list.add(corporatemaster.getPhone3());
			list.add(corporatemaster.getFax3());
			list.add(corporatemaster.getEmail3());
			list.add(corporatemaster.getRemarks());
			list.add(corporatemaster.getBlacklist());
			list.add(corporatemaster.getBlacklistreason());
			list.add(corporatemaster.getCorporateid());
			System.out.println("Hiiiiiiiiiii");
			}
		
			return list;
		
	}

}
	

