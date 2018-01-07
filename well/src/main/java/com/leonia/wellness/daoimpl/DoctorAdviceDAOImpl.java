package com.leonia.wellness.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.idao.IDoctorAdviceDAO;

@Repository
public class DoctorAdviceDAOImpl implements IDoctorAdviceDAO{
	private static final Logger logger = Logger.getLogger(DoctorAdviceDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<AppointmentService> doctrAdviceList() {
		Session session = sessionFactory.openSession();
		List<AppointmentService> appointmentList = new ArrayList<AppointmentService>();
		try{
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria.createAlias("appointmentservice.appointment", "appointment");
			criteria.add(Restrictions.eq("appointmentservice.doctoradvice",true));
			criteria.setFetchMode("appointmentservice.packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("appointmentservice.servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("appointmentservice.staffmaster", FetchMode.EAGER);
			criteria.setFetchMode("appointment.guestmaster", FetchMode.EAGER);
			appointmentList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in getAppointementsList method:", e);
		}
		session.close();
		return appointmentList;
	}
		
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppointmentService> searchdoctrAdviceList(String guests) {
		Session session = sessionFactory.openSession();
		List<AppointmentService> appointmentList = new ArrayList<AppointmentService>();
		try{
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria.createAlias("appointmentservice.appointment", "appointment");
			criteria.createAlias("appointment.guestmaster", "guestmaster");
			criteria.add(Restrictions.eq("appointmentservice.doctoradvice",true));
			criteria.add(Restrictions.ilike("guestmaster.name",guests,MatchMode.ANYWHERE));
			
			criteria.setFetchMode("appointmentservice.packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("appointmentservice.servicemaster", FetchMode.EAGER);
			criteria.setFetchMode("appointmentservice.staffmaster", FetchMode.EAGER);
			appointmentList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in getAppointementsList method:", e);
		}
		session.close();
		return appointmentList;
	}
		
	/*	List<Appointment> customerList = session.createQuery("from Appointment where doctoradvice = 't' ").list();
		session.close();
		return customerList;

	}*/

	@SuppressWarnings("rawtypes")
	@Override
	public List clientInfo(String appId) {
		
		Session session = sessionFactory.openSession();
		List  regUserId = session.createSQLQuery("select guestid from appointment where appointmentid ='"+appId+"'").list();
        Iterator ie = regUserId.iterator();
		
		Integer userId = null ;
		
		while(ie.hasNext()){
			
			userId = (Integer) ie.next();
		}
		
		List  UserInfo = session.createSQLQuery("select name from guestmaster where guestid ='"+userId+"'").list();
		session.close();
		return UserInfo;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List doctrAdvice(String appId,String srvId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		List finalList = new ArrayList();
		/*List  serviceId = session.createSQLQuery("select Distinct (serviceid) from appointmentstockconsumption where appointmentid = '"+appId+"'").list();
		
		Iterator ie = serviceId.iterator();
		
		Integer serId = null ;
		
		while(ie.hasNext()){
			
			serId = (Integer) ie.next();
			
		}
*/		
		List  serviceName = session.createSQLQuery("select servicename from servicemaster where serviceid = '"+srvId+"'").list();	

		Iterator IeSerName = serviceName.iterator();
		
		String serName = null;
		
       while(IeSerName.hasNext()){
			
    	   serName = (String) IeSerName.next();
		}
		
		
		List  servicestockList = session.createSQLQuery("select serviceid,stockid,stockusage,sno,appointmentstockid from appointmentstockconsumption where serviceid = '"+srvId+"' and appointmentid ='"+appId+"'").list();	

		Iterator Iestock = servicestockList.iterator();
		
		Integer servId = null;
	 
		Integer stockId = null;
		
		BigDecimal stockQuantity = new BigDecimal(0);
		
		Integer appointmentstockid = null;
		
		int sno=0;
		
		while(Iestock.hasNext()){
			List subList = new ArrayList<>();
			Object ServiceStocks[]=(Object[])Iestock.next();
			servId = (Integer) ServiceStocks[0];
			stockId = (Integer) ServiceStocks[1];
			stockQuantity = (BigDecimal)ServiceStocks[2];
			sno=(Integer)ServiceStocks[3];
			appointmentstockid = (Integer)ServiceStocks[4];
			
			
			List  stockDetailsList = session.createSQLQuery("select stockname,reorderlevel,warninglevel,available,stockid from stockmaster where stockid = '"+stockId+"'").list();	
			
			List  retailPriceList = session.createSQLQuery("select stockunitprice,stockid from stockreceipt where stockid = '"+stockId+"'").list();
			
			Iterator Iestockdet = stockDetailsList.iterator();
			Iterator Iestkrcptdet = retailPriceList.iterator();
			
		    String stockName = null;
		    BigDecimal retailPrice = null;
		   	Integer reOrderLevel = null;
			Integer warningLevel = null;
            BigDecimal	 stockAvailability = new BigDecimal(0);
			Integer stockid = null;
			
			while(Iestkrcptdet.hasNext()){
				
				Object stockreceipt[]=(Object[])Iestkrcptdet.next();
				
				retailPrice = (BigDecimal) stockreceipt[0];
			}
					
			
			while(Iestockdet.hasNext()){
				
				Object StockDetails[]=(Object[])Iestockdet.next();
				stockName = (String) StockDetails[0];
				reOrderLevel= (Integer)StockDetails[1];
				warningLevel= (Integer)StockDetails[2];
				stockAvailability = (BigDecimal)StockDetails[3];
				stockid= (Integer)StockDetails[4];
				
			}
			subList.add(stockName);
			subList.add(stockAvailability);
			subList.add(reOrderLevel);
			subList.add(warningLevel);
			subList.add(stockQuantity);
			subList.add(retailPrice);
			subList.add(serName);
			subList.add(stockid);
			subList.add(sno);
			subList.add(appointmentstockid);
			
			finalList.add(subList);
			
		}
		
		
		session.close();
		return finalList;
	}

	@Override
	public List fetchStockNames() {

		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
	    List list = session.createSQLQuery("select stockname from stockmaster").list();
	    beginTransaction.commit();
		session.close();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getStockDetails(String stockname) {
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		List subList = new ArrayList<>();
	    List stockDetailInfo = session.createQuery("select stockid,stockname,reorderlevel,warninglevel,available from Stockmaster where stockname = '"+stockname+"' ").list();
	    
		
	    
	    Iterator Iestockdet = stockDetailInfo.iterator();
	    Integer stockId = null;
	    String stockName = null;
	   	Integer reOrderLevel = null;
		Integer warningLevel = null;
		BigDecimal stockAvailability = new BigDecimal(0);
		BigDecimal professionalprice = new BigDecimal(0);
		
		while(Iestockdet.hasNext()){
			Object StockDetails[]=(Object[])Iestockdet.next();
			
			stockId = (Integer) StockDetails[0];
			stockName = (String) StockDetails[1];
			reOrderLevel= (Integer)StockDetails[2];
			warningLevel= (Integer)StockDetails[3];
			stockAvailability = (BigDecimal)StockDetails[4];
			
		}
		List  professionalpriceList = session.createSQLQuery("select stockunitprice,stockid from stockreceipt where stockid = '"+stockId+"'").list();
		Iterator Iestkrcptdet = professionalpriceList.iterator();
		
		while(Iestkrcptdet.hasNext()){
			
			Object stockreceipt[]=(Object[])Iestkrcptdet.next();
			
			professionalprice = (BigDecimal) stockreceipt[0];
		}
		
		subList.add(stockName);
		subList.add(reOrderLevel);
		subList.add(warningLevel);
		subList.add(stockAvailability);
		subList.add(professionalprice);
		subList.add(stockId);
		
				
		beginTransaction.commit();
		session.close();
		return subList;
	}

	@Override
	public int deleteStock(String id) {
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		
		/* List stockDetailInfo = session.createQuery("select stockid,stockname from Stockmaster where stockname = '"+id+"' ").list();
		
		 
		 Iterator Iestockdet = stockDetailInfo.iterator();
		 Integer stockId = null;
		 
		 while(Iestockdet.hasNext()){
				Object StockDetails[]=(Object[])Iestockdet.next();
				
				stockId = (Integer) StockDetails[0];
		 } */
		 
		Query query = session.createSQLQuery("DELETE FROM appointmentstockconsumption  where appointmentstockid = '"+id+"' ");
		
		int result = query.executeUpdate();
		
		if (result > 0) {
			
		    System.out.println("Expensive products was removed");
		}
		beginTransaction.commit();
		session.close();
		return 1;
	}

	/*@Override
	public int addStock(Integer appid, Integer srvid, Integer stkid, Double stockreq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
	
		Transaction beginTransaction = session.beginTransaction();
		AppointmentStockConsumption appStkCon = new AppointmentStockConsumption();
	
		
		appStkCon.setAppointmentId(appid);
		appStkCon.setServiceId(srvid);
		appStkCon.setStockId(stkid);
		appStkCon.setStockQuantity(stockreq);
		
		session.save(appStkCon);
		beginTransaction.commit();
		return 1;
	}*/

	@Override
	public int addStock(List<Appointmentstockconsumption> objArry) {
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		Iterator<Appointmentstockconsumption> iearry = objArry.iterator();
		
		int appointmentstockid = (int) session.createSQLQuery("select Max(appointmentstockid) from  appointmentstockconsumption").uniqueResult();
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+appointmentstockid);
		
		while(iearry.hasNext())
		{
			
			Appointmentstockconsumption appcon = iearry.next();
			int serino = appcon.getAppointmentstockid();
			BigDecimal pofpr = null;
			
			
			if(serino == 0){
				
				int appid = appcon.getAppointment().getAppointmentid();
				int serid = appcon.getServicemaster().getServiceid();
				int stkid = appcon.getStockmaster().getStockid();
				int pkgid = appcon.getPackagemaster().getPackageid();
				BigDecimal stkvalue = appcon.getStockvalue();
				appcon.setAppointmentstockid(appointmentstockid+1);
				appcon.getAppointment().setAppointmentid(appid);
				if(pkgid == 0)
					appcon.setPackagemaster(null);
				else
					appcon.getPackagemaster().setPackageid(pkgid);
				appcon.getServicemaster().setServiceid(serid);
				appcon.getStockmaster().setStockid(stkid);
				appcon.setStockvalue(stkvalue);
				appcon.setUpdatedby("pavan");
				appcon.setUpdateddate(new Date());
				appcon.setUpdatedip("192.22.1.12");
				session.save(appcon);
			}
			else {
				if(appcon.getPackagemaster().getPackageid() == 0)
					appcon.setPackagemaster(null);
				else
					appcon.getPackagemaster().setPackageid(appcon.getPackagemaster().getPackageid());
				appcon.setUpdatedby("pavan");
				appcon.setUpdateddate(new Date());
				appcon.setUpdatedip("192.22.1.12");
				session.update(appcon);
			}
		}
		
		
		
		beginTransaction.commit();
		session.close();
		return 0;
	}

	@Override
	public List getServiceName(Integer srvid) {
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
	    List ServiceList = session.createSQLQuery("select servicename from servicemaster where serviceid = '"+srvid+"' ").list();
		session.close();
		return ServiceList;
	}
	
	
	
	
	@Override
	public List<Set> getSearchAppointements(String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Guestmaster.class);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+name);
		
	//	criteria.createAlias("appointment.guestmaster", "guestmaster");
		if(name!="") {
			criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		}
		criteria.setFetchMode("appointments",FetchMode.EAGER);
		List<Guestmaster> list = criteria.list();
	   List<Set> mainlist = new ArrayList<>();
	 
       for (Guestmaster guestmaster : list) {
    	   Appointment app = new Appointment();
    	   Set<Appointment> appointments = guestmaster.getAppointments();
    	   mainlist.add(appointments);
	    }
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+mainlist);
        transaction.commit();
		session.close();
		return mainlist;
	}
	
	
	
	
	
	
	
	
	

	
	
	
}