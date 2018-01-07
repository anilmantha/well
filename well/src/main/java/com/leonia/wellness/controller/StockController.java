package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.dto.StockListDisplayDTO;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Stockadjustment;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.iservice.ILoadOnStartupService;
/*import com.leonia.wellness.entity.Manufacturer;
import com.leonia.wellness.entity.StockAdjustment;
import com.leonia.wellness.entity.StockDetails;
import com.leonia.wellness.entity.StockGroupMaster;
import com.leonia.wellness.entity.StockReceipt;
import com.leonia.wellness.entity.StockSubGroup;
import com.leonia.wellness.entity.Suppliers;*/
import com.leonia.wellness.iservice.IStockService;

@Controller
public class StockController {

	@Autowired
	public IStockService iStockService;

	@Autowired
	private ILoadOnStartupService iLoadOnStartupService;
	
	@RequestMapping("/addnewstockpage")
	public ModelAndView getstockgroup() {
		return new ModelAndView("addnewstock");
	}

	@RequestMapping(value = "/stocksubgroupname", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getsubgroupDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stockGroupMasterName") String groupname) {
		Set<String> stockname = new HashSet<String>();
		List<Stockgroupmaster> stockgroupaster = iStockService.getsubgroupDetails(groupname);
		for (Stockgroupmaster stockGroupMaster : stockgroupaster) {
			for (Stocksubgroupmaster stock : stockgroupaster.get(0).getStocksubgroupmasters()) {
				stockname.add(stock.getStocksubgroupname());
			}
		}
		return stockname;
	}

	@RequestMapping(value = "/savenewstock")
	public ModelAndView addStock(HttpServletRequest request, HttpServletResponse response) {
		/* AddStockValues addStockValues = new AddStockValues(); */
		boolean valid = true;
		Date date = new Date();
		Stockmaster stockMaster = new Stockmaster();
		Dropdowndetails dropdowndetails = new Dropdowndetails();
		Stocksubgroupmaster stockSubgroupMaster = new Stocksubgroupmaster();
		stockMaster.setStockname(request.getParameter("stockName"));
		int r = Integer.parseInt(request.getParameter("reOrderlevel"));
		int w = Integer.parseInt(request.getParameter("warningLevel"));
		stockSubgroupMaster.setStocksubgroupid(Integer.parseInt(request.getParameter("StockSubGroupId")));
		stockMaster.setStocksubgroupmaster(stockSubgroupMaster);
		dropdowndetails.setDropdowndetailsid(Integer.parseInt(request.getParameter("stocktype")));
		stockMaster.setStockid(11);
		stockMaster.setReorderlevel(r);
		stockMaster.setWarninglevel(w);
		stockMaster.setValid(valid);
		stockMaster.setDropdowndetails(dropdowndetails);
		stockMaster.setUpdatedby("kranthi");
		stockMaster.setUpdateddate(date);
		stockMaster.setUpdatedip("172.22.1.21");
		String message = iStockService.addStock(stockMaster);
		return new ModelAndView("addnewstock","message",message);
	}
	
	@RequestMapping("/stocklist")
	@ResponseBody
	public ModelAndView getStockList(){
		List<StockListDisplayDTO> stockList = iStockService.getStockList();
		/*String msg =null;
		if(stockList.size()>0){
			return new ModelAndView("stock-lists","stockList",stockList);
		}else{
			msg="No records are Found";
			return new ModelAndView("stock-lists","msg",msg);
		}*/
		String msg =null;
		if(stockList.size()>0){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("stock-lists");
			mav.addObject("stockList", stockList);
			mav.addObject("msg", msg);
			return mav;
		}else{
			msg="No records are Found";
			ModelAndView mav = new ModelAndView();
			mav.setViewName("stock-lists");
			mav.addObject("stockList", stockList);
			mav.addObject("msg", msg);
			return mav;
		}
        
	}
	
	@RequestMapping("/stocksearch")
	public ModelAndView getSearchStockList(HttpServletRequest request,HttpServletResponse response){
		List<StockListDisplayDTO> stockList = iStockService.getSearchStockList(request.getParameter("stockName"));
		//System.out.println();
	    //return new ModelAndView("stock-lists","stockList",stockList);
	    String msg =null;
		if(stockList.size()>0){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("stock-lists");
			mav.addObject("stockList", stockList);
			mav.addObject("msg", msg);
			return mav;
		}else{
			msg="No records are Found";
			ModelAndView mav = new ModelAndView();
			mav.setViewName("stock-lists");
			mav.addObject("stockList", stockList);
			mav.addObject("msg", msg);
			return mav;
		}
	}
	
	@RequestMapping("/newstockreceipt")
	public ModelAndView stockReceipt() {

		List<Stockmaster> stockdetails = iStockService.getStockDetails();
		return new ModelAndView("stockreceipt", "receiptstockdetails", stockdetails);
	}

	@RequestMapping(value = "/stockavailability", method = RequestMethod.GET)
	@ResponseBody
	public Integer getstockavailability(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stockId") String stockid) {
		Integer availability = iStockService.getstockavailability(Integer.parseInt(stockid));
		
		if (availability == null)
			return 0;
		return availability;
	}

	@RequestMapping(value = "/getsupplierlist", method = RequestMethod.GET)
	@ResponseBody
	public List getsupplierlist(HttpServletRequest request, HttpServletResponse response) {
		List<Suppliermaster> supplierlist = iStockService.getsupplierlist();
		List list =new LinkedList();
		
		for (Suppliermaster supplier : supplierlist) {
			list.add(supplier.getSupplierid());
			list.add(supplier.getSuppliername());
		}
		return list;
	}

	
	@RequestMapping("/stockrefill")
	public ModelAndView stockRefill(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Stockreceipt stockrel, BindingResult result) {

		Integer stockId = Integer.parseInt(request.getParameter("stockId"));
		String receiveddate = request.getParameter("receiveddate");
		stockrel.setStockunitprice(new BigDecimal(request.getParameter("stockunitprice")));
		String expirydate = request.getParameter("expirydate");
		String manufacturedate = request.getParameter("manufacturedate");
		Integer supplierid = Integer.parseInt(request.getParameter("suppliermaster"));
		Integer Manufacturerid = Integer.parseInt(request.getParameter("manufacturermaster"));
		Date date3 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date3 = sdf.parse(receiveddate);
			stockrel.setReceiveddate(date3);
			date3 = sdf.parse(expirydate);
			stockrel.setExpirydate(date3);
			date3 = sdf.parse(manufacturedate);
			stockrel.setManufacturedate(date3);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String message = iStockService.addstockrefill(stockrel, stockId, supplierid, Manufacturerid);
		iStockService.updatestockrate(stockId);
		List<Stockmaster> stockdetails = iStockService.getStockDetails();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stockreceipt");
		mv.addObject("receiptstockdetails", stockdetails);
		mv.addObject("message",message);
		return mv;
	}
	
	@RequestMapping("/stockadjustmentpage")
	@ResponseBody
	public ModelAndView stockAdjustmentPage(){
		List<StockListDisplayDTO> stockList = iStockService.getStockList();
		return new ModelAndView("stock-adjustment","stockList",stockList);
	}
	
	@RequestMapping(value="/stockadjustment",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView stockAdjustment(@ModelAttribute Stockadjustment stockAdjustment, Stockmaster stockDetails,HttpServletRequest request,    BindingResult result) throws java.text.ParseException{
		String stock =request.getParameter("stockName");
		Integer stockId =null;
		if(stock!=""||stock!=null){
		stockId =Integer.parseInt(stock);
		}
		String quantity =request.getParameter("quantityAdjusted");
		Integer qua =null;
		if(stock!=""||stock!=null){
			qua =Integer.parseInt(quantity);
		}
		String remarks = request.getParameter("remarks");
		String expireDate=request.getParameter("expireDate");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = sdf.parse(expireDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String msg = iStockService.stockAdjustment(stockId,qua,remarks,date1);
		List<StockListDisplayDTO> stockList = iStockService.getStockList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock-adjustment");
		mav.addObject("stockList", stockList);
		mav.addObject("msg", msg);
		//return new ModelAndView("stock-adjustment","stockList",stockList);
		return mav;
     }
	
	@RequestMapping("/stockgroupmasterid")
	@ResponseBody
	public Integer getStockGroupMasterId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stockGroupMasterName") String stockGroupName) {
		Integer stockGroupMasterId = iStockService.getStockGroupMasterId(stockGroupName);
		return stockGroupMasterId;
	}

	@RequestMapping("/getStockSubGroupId")
	@ResponseBody
	public Integer getStockSubGroupId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stockSubGroupName") String stockSubGroupName) {
		Integer stockSubGroupId = iStockService.getStockSubGroupId(stockSubGroupName);
		return stockSubGroupId;
	}
	@RequestMapping("/fetchselectedstock")
	 @ResponseBody
	 public ModelAndView fetchSelectedStock(@RequestParam("stockId") Integer stockId){
		  StockListDisplayDTO stockDetails = iStockService.fetchSelectedStock(stockId);
		  Map<Integer,String> supplier = iStockService.getSupplier();
			Map<Integer,String> manufacturer = iStockService.getManufacturer();
			Map<Integer,String> stockType = iStockService.getStockType();
		 ModelAndView mav = new ModelAndView();
		mav.setViewName("stockdetailsmodifications");
		mav.addObject("stockDetails", stockDetails);
		mav.addObject("supplier", supplier);
		mav.addObject("manufacturer", manufacturer);
		mav.addObject("stockType", stockType);
		 return mav;
	 }
	@RequestMapping("/fetchselectedstock123")
	 @ResponseBody
	 public ModelAndView fetchSelectedStock123(){
		  
		 ModelAndView mav = new ModelAndView();
		mav.setViewName("stockdetailsmodifications");
		
		 return mav;
	 }
	@RequestMapping(value="/updateStock")
	 public ModelAndView updateStock(@ModelAttribute StockListDisplayDTO stockDetails,BindingResult result,HttpServletRequest request,HttpServletResponse response){
        String StExpireDate=request.getParameter("expirydate");
		 iStockService.updateStockDetails(StExpireDate,stockDetails);
        List<StockListDisplayDTO> stockList = iStockService.getStockList();
		 String msg = "Stock is updated";
        return new ModelAndView("stockdetailsmodifications","msg",msg);
       }

	@RequestMapping(value = "/getmanufacturerlist", method = RequestMethod.GET)
	@ResponseBody
	public List getmanufacturerlist(HttpServletRequest request, HttpServletResponse response) {
		List<Manufacturermaster> manufacturelist = iStockService.getmanufacturerlist();
		List list =new LinkedList();
		
		for (Manufacturermaster manufacturer : manufacturelist) {
			list.add(manufacturer.getManufacturerid());
			list.add(manufacturer.getManufacturername());
		}
		return list;
	}
		
	@RequestMapping("/getStocknames")
	@ResponseBody
	public List getStocknames(HttpServletRequest request, HttpServletResponse response) {
		List<String> stocklist = iLoadOnStartupService.getStockDetails();
		return stocklist;
	}
	
	@RequestMapping(value="/savenewsupplier")
	public ModelAndView saveSupplier(@ModelAttribute  Suppliermaster suppliers,BindingResult result,HttpServletRequest request){
		if(suppliers.getWholesale()==null){
			suppliers.setWholesale(false);
		}
		String message=iStockService.addSuppliers(suppliers);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("supplier");
		mv.addObject("message",message);
		return mv;
	}
	@RequestMapping("/supplier")
	public String supplier(){
		 return "supplier";
	}
	@RequestMapping("/supplierlistpage")
	public ModelAndView supplierList(){
		/*List<Suppliermaster> supplierList = iStockService.getSupplierList();
	
		return new ModelAndView("supplierlist","supplierList",supplierList);*/
		List<Suppliermaster>	supplierList = iStockService.getSupplierList();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("supplierlist");
		mv.addObject("supplierList", supplierList);
	 return mv;
	}
	@RequestMapping("/opensuppliereditpage")
	public ModelAndView openSupplierListModifications(HttpServletRequest request,HttpServletResponse response)
	{
		Integer id = Integer.parseInt(request.getParameter("supplierid"));
		Suppliermaster supplier=iStockService.fetchSupplierWithId(id);
		return new ModelAndView("suppliersmodifications","supplier",supplier);
	}
	@RequestMapping(value="/updatesupplier")
	public String updateSupplier(@ModelAttribute  Suppliermaster suppliers,BindingResult result,HttpServletRequest request){
		if(suppliers.getWholesale()==null){
			suppliers.setWholesale(false);
		}
		iStockService.updateSuppliers(suppliers);
		
		  return "redirect:/supplierlistpage";
	}

	@RequestMapping("/manufacturer")
	public String manufacturer(){
		 return "manufacturer";
	}

	@RequestMapping("/saveManufacturer")
	public ModelAndView saveManufacturer(@ModelAttribute  Manufacturermaster manufacturer,BindingResult result,HttpServletRequest request){
		Date date=new Date();
		manufacturer.setUpdatedby("Harika");
		manufacturer.setUpdateddate(date);
		manufacturer.setUpdatedip("172.22.1.28");
		String message=iStockService.saveManufacturerDetails(manufacturer);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("manufacturer");
		mv.addObject("message",message);
		 return mv;
	}
	@RequestMapping("/manufacturerlistpage")
	public ModelAndView manufacturerList(){
		List<Manufacturermaster> manufacturerList=iStockService.getManufacturerList();
		return new ModelAndView("manufacturerlist","manufacturerList",manufacturerList);
	}
	@RequestMapping("/openmanufacturereditpage")
	public ModelAndView openManufacturerListModifications(HttpServletRequest request,HttpServletResponse response)
	{
		Integer id = Integer.parseInt(request.getParameter("manufacturerid"));
		Manufacturermaster manufacturer=iStockService.fetchManufacturerWithId(id);
		return new ModelAndView("manufacturermodifications","manufacturer",manufacturer);
	}
	@RequestMapping(value="/updatemanufacturer")
	public String updateManufacturer(@ModelAttribute  Manufacturermaster manufacturer,BindingResult result,HttpServletRequest request){
		iStockService.updateManufacturer(manufacturer);
		
		  return "redirect:/manufacturerlistpage";
	}
}