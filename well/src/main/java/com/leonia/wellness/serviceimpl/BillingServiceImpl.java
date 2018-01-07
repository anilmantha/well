package com.leonia.wellness.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.idao.IBillingDAO;
import com.leonia.wellness.iservice.IBillingService;

@Service
public class BillingServiceImpl implements IBillingService{
    @Autowired
	public IBillingDAO iBillingDAO;
	@Override
	public Guestmaster getGusetName(Integer guestid) {
		return iBillingDAO.getGusetName(guestid);
	}
	@Override
	public String saveBillPayment(List<Billsettlement> billSettlementList,String tipamount,String discountamount) {
		return iBillingDAO.saveBillPayment(billSettlementList,tipamount,discountamount);
	}
	@Override
	public List<Billmaster> generatedBillDetails() {
		return iBillingDAO.generatedBillDetails();
	}
	@Override
	public Billmaster getSelectedBill(Integer billNo) {
		
		return iBillingDAO.getSelectedBill(billNo);
	}
	@Override
	public BigDecimal creditMoneyAval(int parseInt) {
		
		return iBillingDAO.creditMoneyAval(parseInt);
	}
	@Override
	public BigDecimal membershipMoneyAval(int parseInt) {
		
		return iBillingDAO.membershipMoneyAval(parseInt);
	}
	@Override
	public BigDecimal giftVoucherMoneyAval(int parseInt) {
		
		return iBillingDAO.giftVoucherMoneyAval(parseInt);
	}
	@Override
	public List marketingVoucherMoneyAval(int parseInt) {
		
		return iBillingDAO.marketingVoucherMoneyAval(parseInt);
	}
	@Override
	public List<Billmaster> getSearchBillsByBillNo(String parseInt) {
		
		return iBillingDAO.getSearchBillsByBillNo(parseInt);
	}
	
	@Override
	public List generatepdfreceipt(Integer billno, Integer guestid) {
		return iBillingDAO.generatepdfreceipt(billno,guestid);
	}
	@Override
	public String updateBillSettlementDiscount(String discountreason, String discountpercent, String discountbyamount,
			Integer billid) {
		
		return iBillingDAO.updateBillSettlementDiscount(discountreason,discountpercent,discountbyamount,billid);
	}
	@Override
	public List<Paymodemaster> paymodeDetails() {
	
		return iBillingDAO.paymodeDetails();
	}
	@Override
	public List<Creditlistmaster> corporateDetails() {
		
		return iBillingDAO.corporateDetails();
	}
	@Override
	public List<Membershipmaster> memberDetails() {
		
		return iBillingDAO.memberDetails();
	}
	@Override
	public List<Cardtypemaster> getCardTypes() {
		
		return iBillingDAO.getCardTypes();
	}
	

}
