
package com.leonia.wellness.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.iservice.IBillingService;

@Controller
public class BillingController {
	
	@Autowired
	public IBillingService iBillingService;
	@RequestMapping("/billsettle")
	@ResponseBody
	public ModelAndView billSettlementPage(@RequestParam("billNo") Integer billNo){
		Billmaster billMaster = iBillingService.getSelectedBill(billNo);
		List<Paymodemaster> paymodeDetails = iBillingService.paymodeDetails();
		List<Creditlistmaster> creditMasterList = iBillingService.corporateDetails();
		List<Membershipmaster> membershipList = iBillingService.memberDetails();
		List<Cardtypemaster> cardTypeMasterList = iBillingService.getCardTypes();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("billingsettlement");
		mav.addObject("billMaster", billMaster);
		mav.addObject("paymodeDetails", paymodeDetails);
		mav.addObject("creditMasterList", creditMasterList);
		mav.addObject("membershipList", membershipList);
		mav.addObject("cardtypelist", cardTypeMasterList);
		return mav;
	}
	@RequestMapping("/paymentsettlement")
	public ModelAndView paymentSettlementListPage(HttpServletRequest req,HttpServletResponse res){
		List<Billmaster> billDetailsList = iBillingService.generatedBillDetails();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("paymentsettlementlist");
		mav.addObject("billmasterlist", billDetailsList);
		return mav;
	}
	
	@RequestMapping(value="/savebillsettlement", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String saveBillPayment(@RequestBody List<Billsettlement> billSettlementList,HttpServletRequest request)
	{
		String tipamount = request.getParameter("tipamount");	
		String discountamount = request.getParameter("discountamount");
		System.out.println(discountamount+"*************");
		String msg =iBillingService.saveBillPayment(billSettlementList,tipamount,discountamount);
		return msg;
	    
	}
	@RequestMapping("/creditlistmoneyavailability")
	@ResponseBody
	public BigDecimal creditMoneyAval(@RequestParam("creditListId") String creditListId){
		BigDecimal outsatndingmoney =iBillingService.creditMoneyAval(Integer.parseInt(creditListId));
		if (outsatndingmoney == null)
			return new BigDecimal(0);
		
		return outsatndingmoney;
	}
	@RequestMapping("/memebershipmoneyavailability")
	@ResponseBody
	public BigDecimal membershipMoneyAval(@RequestParam("memebershipId") String memebershipId){
		BigDecimal outsatndingmoney =iBillingService.membershipMoneyAval(Integer.parseInt(memebershipId));
		if (outsatndingmoney == null)
			return new BigDecimal(0);
		
		return outsatndingmoney;
	}
	@RequestMapping("/giftvouchermoneyavailability")
	@ResponseBody
	public BigDecimal giftVoucherMoneyAval(@RequestParam("giftVoucherId") String giftVoucherId){
		BigDecimal giftVocherAmount =iBillingService.giftVoucherMoneyAval(Integer.parseInt(giftVoucherId));
		if (giftVocherAmount == null)
			return new BigDecimal(0);
		
		return giftVocherAmount;
	}
	@RequestMapping("/marketingvouchermoneyavailability")
	@ResponseBody
	public List marketingVoucherMoneyAval(@RequestParam("marketingvoucherid") String marketingvoucherid){
		List marketingVocherList =iBillingService.marketingVoucherMoneyAval(Integer.parseInt(marketingvoucherid));
		
		
		return marketingVocherList;
	}
	@RequestMapping("/searchbybillno")
	public ModelAndView getSearchBillsByBillNo(HttpServletRequest req,HttpServletResponse res){
		List<Billmaster> billDetailsList = iBillingService.getSearchBillsByBillNo(req.getParameter("billno"));
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("paymentsettlementlist");
		mav.addObject("billmasterlist", billDetailsList);
		return mav;
	}
	
	@RequestMapping(value="/generatepdfreceipt", method = RequestMethod.GET)
	 public String  generatepdfreceipt(@RequestParam ("billno") Integer billno,@RequestParam ("guestid") Integer guestid,HttpServletRequest request,Map<String, Object> model,ModelMap modelMap){
	    
		 List viewExcelreport1 = iBillingService.generatepdfreceipt(billno,guestid);
	    	
	    List viewExcelreport=(List<Object>) viewExcelreport1.get(0);
	    	
	    	List viewExcelreport2=(List<Map<String,String>>) viewExcelreport1.get(1);
	    	
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
	    	return "PDFPaymentReceipt";
		}
	@RequestMapping(value="/billsettlementdiscount")
	@ResponseBody
	public Integer billSettlementDiscount(HttpServletRequest req,HttpServletResponse res){
		System.out.println("#############");
		String discountreason =req.getParameter("discountreason");
		String discounttype = req.getParameter("discounttype");
		String discountamount = req.getParameter("discountamount");
		Integer billid = Integer.parseInt(req.getParameter("billid"));
		String msg =iBillingService.updateBillSettlementDiscount(discountreason,discounttype,discountamount,billid);
		return billid;
		
	}
}