package com.leonia.wellness.daoimpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import com.leonia.wellness.idao.IReportDAO;

@Repository
public class ReportDAOImpl implements IReportDAO{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(ReportDAOImpl.class);
	@Override
	public List<Billsettlement> billsettlementList() {
		Session session = sessionFactory.openSession();
		List<Billsettlement> list =new LinkedList<Billsettlement>();
		try{
			
			Criteria criteria = session.createCriteria(Billsettlement.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.setFetchMode("billmaster", FetchMode.EAGER);
			criteria.setFetchMode("paymodemaster", FetchMode.EAGER);
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in billsettlementList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
	}
	@Override
	public List<Billsettlement> getSettlementssList(Date fromDate, Date toDate, String payMode) {
		Session session = sessionFactory.openSession();
		List<Billsettlement> settlementlist = null;
		try {
			Criteria criteria = session.createCriteria(Billsettlement.class,"billsettlement");
			criteria.createAlias("billsettlement.billmaster", "billmaster");
			criteria.createAlias("billsettlement.paymodemaster", "paymodemaster");
			if((fromDate!=null) && (toDate!=null))
			{
				criteria.add(Restrictions.ge("billmaster.billdate",fromDate));
				criteria.add(Restrictions.le("billmaster.billdate",toDate));
			}
			if(payMode!=null && !(payMode.isEmpty())) {
		    	 criteria.add(Restrictions.eq("paymodemaster.paymodeid", Integer.parseInt(payMode)));
		    	
			}
			criteria.setFetchMode("billmaster", FetchMode.EAGER);
			criteria.setFetchMode("paymodemaster", FetchMode.EAGER);
				
			settlementlist = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getSettlementssList Method:", e);
		} finally {
			session.close();
		}
		return settlementlist;
	}
	@Override
	public List<Stockmaster> stockReorderLevelList() {
		Session session = sessionFactory.openSession();
		List<Stockmaster> list =new LinkedList<Stockmaster>();
		try{
			
			Criteria criteria = session.createCriteria(Stockmaster.class,"stock");
			criteria.add(Restrictions.eq("valid", true));
			criteria.createAlias("stock.stocksubgroupmaster", "stocksubgroupmaster");
			criteria.createAlias("stocksubgroupmaster.stockgroupmaster", "stockgroupmaster");
			criteria.setFetchMode("stocksubgroupmaster", FetchMode.EAGER);
			criteria.setFetchMode("stockgroupmaster", FetchMode.EAGER);
			criteria.setFetchMode("dropdowndetails", FetchMode.EAGER);
			
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in stockReorderLevelList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Appointmentservicedetails> paymentList() {
		Session session = sessionFactory.openSession();
		List<Appointmentservicedetails> list =new LinkedList<Appointmentservicedetails>();
		try{
			
			Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria.add(Restrictions.eq("valid", true));
			criteria.createAlias("appointmentservice.appointment", "appointment");
			criteria.createAlias("appointment.guestmaster", "guestmaster");
			criteria.createAlias("appointment.billdetailses", "billdetailses");
			criteria.createAlias("billdetailses.billmaster", "billmaster");
			criteria.setFetchMode("packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("staffmaster", FetchMode.EAGER);
			criteria.setFetchMode("discountmaster", FetchMode.EAGER);
			criteria.setFetchMode("billmaster.billsettlements", FetchMode.EAGER);
			criteria.setFetchMode("roommaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in paymentList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Stockmaster> getStockList(String stockName, String stockType, String stockGroup,String stockSubGroup) {
		Session session = sessionFactory.openSession();
		List<Stockmaster> stockList = null;
		try {
			Criteria criteria = session.createCriteria(Stockmaster.class,"stock");
			criteria.createAlias("stock.dropdowndetails", "dropdown");
			criteria.createAlias("stock.stocksubgroupmaster", "stocksubgroupmaster");
			criteria.createAlias("stocksubgroupmaster.stockgroupmaster", "stockgroupmaster");
			if(stockName!=null && !(stockName.isEmpty())) {
			    	 criteria.add(Restrictions.ilike("stock.stockname",stockName, MatchMode.ANYWHERE));
			    	
			}
			if(stockType!=null && !(stockType.isEmpty())) {
		    	 criteria.add(Restrictions.eq("dropdown.dropdowndetailsid",Integer.parseInt(stockType)));
		    	
			}
			if(stockGroup!=null && !(stockGroup.isEmpty())) {
		    	 criteria.add(Restrictions.eq("stockgroupmaster.stockgroupid",Integer.parseInt(stockGroup)));
		    	
			}
			if(stockSubGroup!=null && !(stockSubGroup.isEmpty())) {
		    	 criteria.add(Restrictions.eq("stocksubgroupmaster.stocksubgroupid",Integer.parseInt(stockSubGroup)));
		    	
			}
			criteria.setFetchMode("stock.dropdowndetails", FetchMode.EAGER);
			criteria.setFetchMode("stock.stocksubgroupmaster", FetchMode.EAGER);
			criteria.setFetchMode("stocksubgroupmaster.stockgroupmaster", FetchMode.EAGER);
				
			stockList = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStockList Method:", e);
		} finally {
			session.close();
		}
		return stockList;
	}
	
	@Override
	public List<Servicestocksmaster> inventoryList() {
		Session session = sessionFactory.openSession();
		List<Servicestocksmaster> list =new LinkedList<Servicestocksmaster>();
		try{
			Criteria criteria = session.createCriteria(Servicestocksmaster.class,"servicestock");
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("stockmaster", FetchMode.EAGER);
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in inventoryList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
	}
	@Override
	public List<Stockmaster> stockWarningLevelList() {
		Session session = sessionFactory.openSession();
		List<Stockmaster> list =new LinkedList<Stockmaster>();
		try{
			
			Criteria criteria = session.createCriteria(Stockmaster.class,"stock");
			criteria.add(Restrictions.eq("valid", true));
			criteria.createAlias("stock.stocksubgroupmaster", "stocksubgroupmaster");
			criteria.createAlias("stocksubgroupmaster.stockgroupmaster", "stockgroupmaster");
			criteria.setFetchMode("stocksubgroupmaster", FetchMode.EAGER);
			criteria.setFetchMode("stockgroupmaster", FetchMode.EAGER);
			criteria.setFetchMode("dropdowndetails", FetchMode.EAGER);
			
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in stockWarningLevelList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Paymodemaster> getPaymodeList() {
		Session session = sessionFactory.openSession();
		List<Paymodemaster> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Paymodemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getPaymodeList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Stockgroupmaster> stockgroupmaster() {
		Session session = sessionFactory.openSession();
		List<Stockgroupmaster> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in Stockgroupmaster Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Stocksubgroupmaster> stocksubgroupmaster() {
		Session session = sessionFactory.openSession();
		List<Stocksubgroupmaster> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Stocksubgroupmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStocksubgroupmaster Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Billdetails> getbilldetails() {
		Session session = sessionFactory.openSession();
		List<Billdetails> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Billdetails.class,"billdetails");
			criteria.createAlias("billdetails.billmaster", "billmaster");
			criteria.createAlias("billmaster.billsettlements", "billsettlements");
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("appointment", FetchMode.EAGER);
			criteria.setFetchMode("billsettlements.paymodemaster", FetchMode.EAGER);
			criteria.setFetchMode("billsettlements.giftvouchermaster", FetchMode.EAGER);
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getbilldetails Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Servicestocksmaster> getSearchInventory(String stockName, String serviceName) {
		Session session = sessionFactory.openSession();
		List<Servicestocksmaster> inventoryList = null;
		try {
			Criteria criteria = session.createCriteria(Servicestocksmaster.class,"servicestock");
			criteria.createAlias("servicestock.stockmaster", "stockmaster");
			criteria.createAlias("servicestock.servicemaster", "servicemaster");
			
			if(stockName!=null && !(stockName.isEmpty())) {
			     criteria.add(Restrictions.eq("stockmaster.stockid",Integer.parseInt(stockName)));
			    
			}
			if(serviceName!=null && !(serviceName.isEmpty())) {
		    	 criteria.add(Restrictions.eq("servicemaster.serviceid", Integer.parseInt(serviceName)));
		    	
			}

			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("stockmaster", FetchMode.EAGER);
				
			inventoryList = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getSearchInventory Method:", e);
		} finally {
			session.close();
		}
		return inventoryList;
	}
	@Override
	public List<Stockmaster> stockmaster() {
		Session session = sessionFactory.openSession();
		List<Stockmaster> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Stockmaster.class,"stock");
			criteria.createAlias("stock.dropdowndetails", "dropdowndetails");
			criteria.add(Restrictions.eq("valid", true));
			criteria.add(Restrictions.eq("dropdowndetails.dropdowndetailsid", 5));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in stockmaster Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Servicemaster> servicemaster() {
		Session session = sessionFactory.openSession();
		List<Servicemaster> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in servicemaster Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Appointmentservicedetails> therapistList() {
		Session session = sessionFactory.openSession();
		List<Appointmentservicedetails> list = null;
		try{
			
			Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria.createAlias("appointmentservice.appointment", "appointment");
			criteria.setFetchMode("packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("staffmaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in therapistList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Appointmentservicedetails> gettherapistReportList(Date date1, Date date2, String serviceName) {
		Session session = sessionFactory.openSession();
		List<Appointmentservicedetails> billDetailslist = null;
		try{
			Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria.createAlias("appointmentservice.servicemaster", "servicemaster");
			criteria.createAlias("appointmentservice.appointment", "appointment");
			if((date1!=null) && (date2!=null))
			{
				criteria.add(Restrictions.ge("appointment.appointmentdate",date1));
				criteria.add(Restrictions.le("appointment.appointmentdate",date2));
			}
			if(serviceName!=null && !(serviceName.isEmpty())) {
		    	 criteria.add(Restrictions.eq("servicemaster.serviceid", Integer.parseInt(serviceName)));
			}
			criteria.setFetchMode("packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("staffmaster", FetchMode.EAGER);
			billDetailslist = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getSettlementssList Method:", e);
		} finally {
			session.close();
		}
		return billDetailslist;
	}
	@Override
	public List<Productsales> productSalesList() {
		Session session = sessionFactory.openSession();
		List<Productsales> list =new LinkedList<Productsales>();
		try{
			
			Criteria criteria = session.createCriteria(Productsales.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.setFetchMode("stockmaster", FetchMode.EAGER);
			criteria.setFetchMode("billmaster", FetchMode.EAGER);
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in productSalesList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Productsales> getProductSalesList(Date date1, Date date2, String stockName) {
		Session session = sessionFactory.openSession();
		List<Productsales> productSaleslist = null;
		try {
			Criteria criteria = session.createCriteria(Productsales.class,"productsale");
			criteria.createAlias("productsale.stockmaster", "stockmaster");
			if((date1!=null) && (date2!=null))
			{
				criteria.add(Restrictions.ge("updateddate",date1));
				criteria.add(Restrictions.le("updateddate",date2));
			}
			if(stockName!=null && !(stockName.isEmpty())) {
		    	 criteria.add(Restrictions.eq("stockmaster.stockid", Integer.parseInt(stockName)));
			}
			//criteria.setFetchMode("stockmaster", FetchMode.EAGER);
			productSaleslist = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getProductSalesList Method:", e);
		} finally {
			session.close();
		}
		return productSaleslist;
	}
}