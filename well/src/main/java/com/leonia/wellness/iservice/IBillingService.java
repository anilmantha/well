package com.leonia.wellness.iservice;

import java.math.BigDecimal;
import java.util.List;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Paymodemaster;
public interface IBillingService {
     public Guestmaster getGusetName(Integer guestid);
     public String saveBillPayment(List<Billsettlement> billSettlementList, String tipamount, String discountamount);
     //public String saveBillPayment(List<Billsettlement> billSettlementList, String tipamount);
     public List<Billmaster> generatedBillDetails();
	public Billmaster getSelectedBill(Integer billNo);
	public BigDecimal creditMoneyAval(int parseInt);
	public BigDecimal membershipMoneyAval(int parseInt);
	public BigDecimal giftVoucherMoneyAval(int parseInt);
	public List marketingVoucherMoneyAval(int parseInt);
	public List<Billmaster> getSearchBillsByBillNo(String parseInt);
	public List generatepdfreceipt(Integer billno, Integer guestid);
	public String updateBillSettlementDiscount(String discountreason, String discountpercent, String discountbyamount,
			Integer billid);
	public List<Paymodemaster> paymodeDetails();
	public List<Creditlistmaster> corporateDetails();
	public List<Membershipmaster> memberDetails();
	public List<Cardtypemaster> getCardTypes();
	
}
