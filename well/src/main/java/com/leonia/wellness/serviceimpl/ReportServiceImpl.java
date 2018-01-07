package com.leonia.wellness.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.leonia.wellness.idao.IMasterDAO;
import com.leonia.wellness.idao.IReportDAO;
import com.leonia.wellness.iservice.IReportService;

@Service
public class ReportServiceImpl implements IReportService{
	@Autowired
	private IReportDAO iReportDAO;

	@Override
	public List<Billsettlement> billSettlementList() {
		return iReportDAO.billsettlementList();
	}

	@Override
	public List<Billsettlement> getSettlementssList(Date fromDate, Date toDate, String payMode) {
		return iReportDAO.getSettlementssList(fromDate,toDate,payMode);
	}

	@Override
	public List<Stockmaster> stockReorderLevelList() {
		return iReportDAO.stockReorderLevelList();
	}

	@Override
	public List<Appointmentservicedetails> paymentList() {
		return iReportDAO.paymentList();
	}

	@Override
	public List<Stockmaster> getStockList(String stockName, String stockType, String stockGroup, String stockSubGroup) {
		return iReportDAO.getStockList(stockName,stockType,stockGroup,stockSubGroup);
	}

	@Override
	public List<Servicestocksmaster> inventoryList() {
		return iReportDAO.inventoryList();
	}
	@Override
	public List<Stockmaster> stockWarningLevelList() {
		return iReportDAO.stockWarningLevelList();
	}

	@Override
	public List<Paymodemaster> getPaymodeList() {
		return iReportDAO.getPaymodeList();
	}

	@Override
	public List<Stockgroupmaster> stockgroupmaster() {
		return iReportDAO.stockgroupmaster();
	}

	@Override
	public List<Stocksubgroupmaster> stocksubgroupmaster() {
		return iReportDAO.stocksubgroupmaster();
	}

	@Override
	public List<Billdetails> getbilldetails() {
		return iReportDAO.getbilldetails();
	}

	@Override
	public List<Servicestocksmaster> getSearchInventory(String stockName, String serviceName) {
		return iReportDAO.getSearchInventory(stockName,serviceName);
	}

	@Override
	public List<Stockmaster> stockmaster() {
		return iReportDAO.stockmaster();
	}

	@Override
	public List<Servicemaster> servicemaster() {
		return iReportDAO.servicemaster();
	}

	@Override
	public List<Appointmentservicedetails> therapistList() {
		return iReportDAO.therapistList();
	}

	@Override
	public List<Appointmentservicedetails> gettherapistReportList(Date date1, Date date2, String serviceName) {
		return iReportDAO.gettherapistReportList(date1,date2,serviceName);
	}

	@Override
	public List<Productsales> productSalesList() {
		return iReportDAO.productSalesList();
	}

	@Override
	public List<Productsales> getProductSalesList(Date date1, Date date2, String stockName) {
		return iReportDAO.getProductSalesList(date1,date2,stockName);
	}

}
