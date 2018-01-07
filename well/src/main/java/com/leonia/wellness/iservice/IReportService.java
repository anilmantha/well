package com.leonia.wellness.iservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.entity.Productsales;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stocksubgroupmaster;

public interface IReportService {

	List<Billsettlement> billSettlementList();

	List<Billsettlement> getSettlementssList(Date date1, Date date2, String payMode);

	List<Stockmaster> stockReorderLevelList();

	List<Appointmentservicedetails> paymentList();

	List<Stockmaster> getStockList(String stockName, String stockType, String stockGroup, String stockSubGroup);

	List<Servicestocksmaster> inventoryList();

	List<Stockmaster> stockWarningLevelList();

	List<Paymodemaster> getPaymodeList();

	List<Stockgroupmaster> stockgroupmaster();

	List<Stocksubgroupmaster> stocksubgroupmaster();

	List<Billdetails> getbilldetails();

	List<Servicestocksmaster> getSearchInventory(String stockName, String serviceName);

	List<Stockmaster> stockmaster();

	List<Servicemaster> servicemaster();

	List<Appointmentservicedetails> therapistList();

	List<Appointmentservicedetails> gettherapistReportList(Date date1, Date date2, String serviceName);

	List<Productsales> productSalesList();

	List<Productsales> getProductSalesList(Date date1, Date date2, String stockName);
}
