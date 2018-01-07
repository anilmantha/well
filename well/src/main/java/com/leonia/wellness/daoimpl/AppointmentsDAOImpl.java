package com.leonia.wellness.daoimpl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.persistence.OrderBy;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.tree.OrderByClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.AppointmentService;
import com.leonia.wellness.dto.DataToGenerateBill;
import com.leonia.wellness.dto.MyDto;
import com.leonia.wellness.dto.ProductRateAndTax;
import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Appointmentstockconsumption;
import com.leonia.wellness.entity.Billdetails;
import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Discountmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Packageratemaster;
import com.leonia.wellness.entity.Packageservices;
import com.leonia.wellness.entity.Productratemaster;
import com.leonia.wellness.entity.Productsales;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Serviceratemaster;
import com.leonia.wellness.entity.Serviceroommaster;
import com.leonia.wellness.entity.Servicestaffmaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Statusmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Taxmasteraa;
import com.leonia.wellness.entity.Taxstructuredetails;
import com.leonia.wellness.entity.Taxstructuremaster;
import com.leonia.wellness.idao.IAppointmentsDAO;
import com.leonia.wellness.util.Ipaddress;

@Repository
public class AppointmentsDAOImpl implements IAppointmentsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger.getLogger(AppointmentsDAOImpl.class);
	
	public  void setAppointment(Appointment appointment){
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.save(appointment);
		tx.commit();
		session.close();
		
	}

	@Override
	public int saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		appointment.setValid(true);
		int id=(Integer)session.save(appointment);
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public Appointment getAppointment(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		
		return (Appointment)session.get(Appointment.class, id);
	}
	
	@Override
	public List getServiceStocks(int serviceId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List serviceStocksList = new ArrayList();
		List stocks = session.createQuery("select stockId, stockQuantity from ServiceStocks where serviceId = '"+serviceId+"' order by stockId").list();
		Iterator ie = stocks.iterator();
		while(ie.hasNext())
		{
			List stocksSubList = new ArrayList();
			Object[] values = (Object[]) ie.next();
			String stockId = (String) values[0];
			String stockQuantity = (String) values[1];
			
			List stockList = session.createQuery("select stockName, stockAvailability, retailPrice, warningLevel, reOrderLevel from StockDetails where stockId = "+Integer.parseInt(stockId)+" ").list();
			Iterator ie1 = stockList.iterator();
			while(ie1.hasNext())
			{
				Object[] vals = (Object[]) ie1.next();
				String stockName = (String) vals[0];
				int stockAvailability = (int) vals[1];
				double cost = (double) vals[2];
				int warningLevel = (int) vals[3];
				int reorderLevel = (int) vals[4];
				double finalCost = Integer.parseInt(stockQuantity) * cost;
				stocksSubList.add(stockId);
				stocksSubList.add(stockName);
				stocksSubList.add(stockQuantity);
				stocksSubList.add(finalCost);
				stocksSubList.add(stockAvailability);
				stocksSubList.add(warningLevel);
				stocksSubList.add(reorderLevel);
				stocksSubList.add(stockAvailability - Integer.parseInt(stockQuantity));
			}
			
			serviceStocksList.add(stocksSubList);
		}
		tx.commit();
		session.close();
		return serviceStocksList;
	}


	@Override
	public boolean getAppointmentRoomCondition(String roomId, String startTime, String endTime, String arrivalDate) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean result = false;
		List apList = session.createSQLQuery("select roomId from Appointmentservicedetails where roomId = " + roomId + " and valid = 't' and schappointdate = '" + arrivalDate + "' and (('" + startTime + "' >= schappointtime and '" + startTime + "' < schdeparttime) or ('" + endTime + "' >= schappointtime and '" + endTime + "' < schdeparttime))").list();
		if (apList.size() == 0) {
			result = true;
		}
		tx.commit();
		session.close();
		return result;
	}

	@Override
	public boolean getAppointmentStaffCondition(String staffId, String startTime, String endTime, String arrivalDate) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean result = false;
		List apList = session.createSQLQuery("select staffId from Appointmentservicedetails where staffId = " + staffId + " and valid = 't' and schappointdate = '" + arrivalDate + "' and (('" +startTime + "' >= schappointtime and '" + startTime + "' < schdeparttime) or ('" + endTime + "' >= schappointtime and '" + endTime+ "' < schdeparttime))").list();
		if (apList.size() == 0) {
			result = true;
		}
		tx.commit();
		session.close();
		return result;
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Override
	public List getstockDetailInfo(int serviceId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List serviceStocksList = new ArrayList();
		List stocks = session.createQuery("select stockId, stockQuantity from ServiceStocks where serviceId = '"+serviceId+"' order by stockId").list();
		Iterator ie = stocks.iterator();
		String stockId = null;
		String stockQuantity = null;
		while(ie.hasNext())
		{
			List stocksSubList = new ArrayList();
			Object[] values = (Object[]) ie.next();
			stockId = (String) values[0];
		    stockQuantity = (String) values[1];
		}
		List stocksList = new ArrayList();
		stocksList.add(stockId);
		stocksList.add(stockQuantity);
		
		return stocksList;
	}

	@Override
	public int makeAppointment(List<Appointment> objArry,Integer custid) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Iterator<Appointment> ie = objArry.iterator();
		Integer servid = null;
		int appid = 0;
		Guestmaster regUserId=new Guestmaster();
		System.out.println("************************************"+custid);;
		while(ie.hasNext())
		{
			/*Appointment app =  ie.next();
			
			servid = app.getServicemaster().getServiceid();
			regUserId.setGuestid(custid);
			app.setGuestmaster(regUserId);
			session.save(app);
			appid = app.getAppointmentid();
			*/
		}
		
		
		List stocks = session.createQuery("select stockId, stockQuantity from ServiceStocks where serviceId = '"+servid+"' order by stockId").list();
		
		Iterator iestock = stocks.iterator();
		String stockId = null;
		String stockQuantity = null;
		while(iestock.hasNext())
		{
			List stocksSubList = new ArrayList();
			Object[] values = (Object[]) iestock.next();
			stockId = (String) values[0];
		    stockQuantity = (String) values[1];
		    
		    Appointmentstockconsumption asc = new Appointmentstockconsumption();
		    
		    asc.getAppointment().setAppointmentid(appid);
		    asc.getServicemaster().setServiceid(servid);
		    asc.getStockmaster().setStockid(Integer.parseInt(stockId));
		    asc.setStockusage(new BigDecimal(stockQuantity));
		    
		    session.save(asc);
		    
		    
		}
		
		tx.commit();
		
		
		
		return 1;
	}
	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List getAppointementsList() {
		
		
		
		
		
		Session session = sessionFactory.openSession();
/*		Taxmasteraa ta=new Taxmasteraa();
		ta.setTaxmasterid(1);
		ta.setTaxname("divya");
		Transaction beginTransaction = session.beginTransaction();
		session.save(ta);
		beginTransaction.commit();*/
		
		List appointmentList = new ArrayList<>();
		try{
		Transaction transaction = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select this_.appointmentid as y0_, guestmaste1_.name as y1_, this_.appointmentdate as y2_, min(appointmen3_.schappointtime) as y3_, "
+"statusmast2_.statusdescription as y4_ from appointment this_ inner join appointmentservicedetails appointmen3_ "
+"on this_.appointmentid=appointmen3_.appointmentid inner join guestmaster guestmaste1_ on this_.guestid=guestmaste1_.guestid "
+"inner join statusmaster statusmast2_ on this_.statusid=statusmast2_.statusid where this_.valid=true and "
+"(statusmast2_.statusid=1 or statusmast2_.statusid=2) and this_.appointmentid=appointmen3_.appointmentid "
+"group by this_.appointmentid, guestmaste1_.name, this_.appointmentdate, statusmast2_.statusdescription order by this_.appointmentdate asc,min(appointmen3_.schappointtime) asc");
		/*Criteria criteria = session.createCriteria(Appointment.class,"appointment");
		criteria.createAlias("appointment.guestmaster","guestmaster");
		criteria.createAlias("appointment.statusmaster","statusmaster");
		criteria.createAlias("appointment.appointmentservicedetailses","appointmentservicedetailses");
		criteria.add(Restrictions.eq("valid",true));
		//Order order = Order.asc("appointment.appointmentdate");
		
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("statusmaster.statusid", 1))
				.add(Restrictions.eq("statusmaster.statusid", 2))
				);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("appointment.appointmentid"))
				.add(Projections.groupProperty("guestmaster.name"))
				.add(Projections.groupProperty("appointment.appointmentdate"))
				.add(Projections.groupProperty("statusmaster.statusdescription"))
				.add(Projections.min("appointmentservicedetailses.schappointtime"))
					
           );
		criteria.add(Restrictions.eqProperty("appointment.appointmentid", "appointmentservicedetailses.appointment"));
		criteria.addOrder(Order.asc("appointment.appointmentdate")).addOrder(Order.asc("appointmentservicedetailses.schappointtime"));
		//criteria.setProjection(Projections.min("appointmentservicedetailses.schappointtime"));
		//criteria.addOrder(Order.asc("appointmentservicedetailses.schappointtime"));
*/		appointmentList = query.list();
		
		/*
		Criterion status1 = Restrictions.eq("statusmaster.statusid", 1);
		Criterion status2 = Restrictions.eq("statusmaster.statusid", 2);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(status1);
		disjunction.add(status2);
		criteria.add(disjunction);
		Order order = Order.asc("appointmentid");
		criteria.addOrder(order);
		appointmentList = criteria.list();*/
		
		
		
		/*Criteria criteria2 = session.createCriteria(Appointmentservicedetails.class,"asd");
		criteria2.createAlias("asd.appointment", "appointment");
		criteria2.add(Restrictions.eq("valid", true));
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("appointment.appointmentid"));
		projectionList.add(Projections.min("schappointtime"));
		criteria2.setProjection(projectionList);
		List list = criteria2.list();*/
		
		transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in getAppointementsList method:", e);
		}
		session.close();
		return appointmentList;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List getSearchAppointementsList(String appointmentId,String guestName,String appointmentDate,String doctorAdvice) {
		Session session = sessionFactory.openSession();
		List appointmentList = new ArrayList<>();
		try{
		Transaction transaction = session.beginTransaction();
		
		String query =" select this_.appointmentid as y0_, guestmaste1_.name as y1_, this_.appointmentdate as y2_, min(appointmen3_.schappointtime) as y3_, statusmast2_.statusdescription as y4_ "
				+ "from appointment this_ inner join appointmentservicedetails appointmen3_ on this_.appointmentid=appointmen3_.appointmentid "
				+ "inner join guestmaster guestmaste1_ on this_.guestid=guestmaste1_.guestid "
				+ "inner join statusmaster statusmast2_ on this_.statusid=statusmast2_.statusid "
				+ "where this_.valid=true and (statusmast2_.statusid=1 or statusmast2_.statusid=2) and this_.appointmentid=appointmen3_.appointmentid ";
	

		if(appointmentId!="") {
		     String appIdQuery=" and this_.appointmentid="+appointmentId;
		     query=query.concat(appIdQuery);
		}
		if(guestName!="") {
			 String guestQuery=" and guestmaste1_.name ilike '%"+guestName+"%'";
			 query =query.concat(guestQuery);
		}
		if(appointmentDate!=""){
			Date date1 = new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date1 = sdf.parse(appointmentDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
		  String guestQuery=" and this_.appointmentdate='"+date1+"'";
		  query = query.concat(guestQuery);
		}
		String groupQuery=" group by this_.appointmentid, guestmaste1_.name, this_.appointmentdate, statusmast2_.statusdescription order by this_.appointmentdate asc,min(appointmen3_.schappointtime) asc";
		query=query.concat(groupQuery);
		SQLQuery sqlQuery = session.createSQLQuery(query);
		/*Criteria criteria = session.createCriteria(Appointment.class,"appointment");
		criteria.createAlias("appointment.guestmaster","guestmaster");
		criteria.createAlias("appointment.statusmaster","statusmaster");
		criteria.createAlias("appointment.appointmentservicedetailses","appointmentservicedetailses");
		criteria.add(Restrictions.eq("valid",true));
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("statusmaster.statusid", 1))
				.add(Restrictions.eq("statusmaster.statusid", 2))
				);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("appointment.appointmentid"))
				.add(Projections.groupProperty("guestmaster.name"))
				.add(Projections.groupProperty("appointment.appointmentdate"))
				.add(Projections.min("appointmentservicedetailses.schappointtime"))
				.add(Projections.groupProperty("statusmaster.statusdescription"))
					
           );
		criteria.add(Restrictions.eqProperty("appointment.appointmentid", "appointmentservicedetailses.appointment"));
		criteria.addOrder(Order.asc("appointment.appointmentdate"));
		if(appointmentId!="") {
		     criteria.add(Restrictions.eq("appointment.appointmentid", Integer.parseInt(appointmentId)));
		}
        if(guestName!="") {
			criteria.add(Restrictions.ilike("guestmaster.name", guestName, MatchMode.ANYWHERE));
		}*/
		/*if(doctorAdvice!="") {
			  String yes="yes";
			  String no="no";
			   if(yes.equalsIgnoreCase(doctorAdvice)){
				  criteria.add(Restrictions.eq("appointment.doctoradvice", true));
			  }else if(no.equalsIgnoreCase(doctorAdvice)){
				  criteria.add(Restrictions.eq("appointment.doctoradvice", false));
			  }
		}*/
		/*if(appointmentDate!=""){
			Date date1 = new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date1 = sdf.parse(appointmentDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			criteria.add(Expression.eq("appointment.appointmentdate", date1));
		}*/
	    appointmentList = sqlQuery.list();
		transaction.commit();
		}catch(Exception e){
			
			
			logger.error("Exception raised in getSearchAppointementsList method:", e);
		}
		finally{
		session.close();
		}
		return appointmentList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<AppointmentService> getAppointmentServiceInfo(Integer appointmentId) {
		
		Session session = sessionFactory.openSession();
		List<AppointmentService> listofservices = new LinkedList<AppointmentService>();
		
		try{
			 Criteria criteria = session.createCriteria(Appointment.class);
			 criteria.add(Restrictions.eq("appointmentid", appointmentId));
			 criteria.setFetchMode("appointmentservicemaster", FetchMode.EAGER);
			 Appointment uniqueResult = (Appointment) criteria.uniqueResult();
			 AppointmentService appointmentService = new AppointmentService();
			 appointmentService.setAppointmentId(uniqueResult.getAppointmentid());
			 appointmentService.setAppointmentStatus(uniqueResult.getStatusmaster().getStatusdescription());
			 appointmentService.setGuestId(uniqueResult.getGuestmaster().getGuestid());
			 appointmentService.setGuestName(uniqueResult.getGuestmaster().getName());
			 appointmentService.setDateOfAppontment(uniqueResult.getAppointmentdate());
			 listofservices.add(appointmentService);
			 for (Appointmentservicedetails asmaster : uniqueResult.getAppointmentservicedetailses()) {
				 AppointmentService appointmentService1 = new AppointmentService();
				 appointmentService1.setServiceId(asmaster.getServicemaster().getServiceid());
				 appointmentService1.setServiceName(asmaster.getServicemaster().getServicename());
				 appointmentService1.setStaffId(asmaster.getStaffmaster().getStaffid());
				 appointmentService1.setStaffName(asmaster.getStaffmaster().getStaffname());
				 appointmentService1.setStartTime(asmaster.getSchappointtime());
				 appointmentService1.setEndTime(asmaster.getSchdeparttime());
				 appointmentService1.setServiceStatus(asmaster.getStatusmaster().getStatusdescription());
				 listofservices.add(appointmentService1);
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in getAppointmentServiceInfo method:", e);
		}
		
		finally {
			session.close();
		}
		return listofservices;
	}

	@Override
	public void startService(Integer appointmentId, Integer serviceId) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		DateFormat time = new SimpleDateFormat("hh:mm:ss");
        try{
			Criteria criteria = session.createCriteria(Appointment.class);
			criteria.add(Restrictions.eq("appointmentid", appointmentId));
			Appointment Appointment = (Appointment) criteria.uniqueResult();
			List<Appointmentservicedetails> appointmentservicemaster = Appointment.getAppointmentservicedetailses();
			for (Appointmentservicedetails appointmentServiceMaster : appointmentservicemaster) {
				if(appointmentServiceMaster.getServicemaster().getServiceid()==serviceId && appointmentServiceMaster.getStatusmaster().getStatusid()==1){
					Statusmaster status = new Statusmaster();
					status.setStatusid(2);
					appointmentServiceMaster.setStatusmaster(status);
					appointmentServiceMaster.setActstarttime(time.format(date));
					Date date1 = new Date();
					appointmentServiceMaster.setActstartdate(date1);
					session.update(appointmentServiceMaster);
					Statusmaster status1 = new Statusmaster();
					status1.setStatusid(2);
					Appointment.setStatusmaster(status1);
					session.update(Appointment);
					}
			}
			transaction.commit();
		}
		catch(Exception e) {
			logger.error("Exception raised in startService method:", e);
		}
		
		finally {
			session.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void stopService(Integer appointmentId, Integer serviceId) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Integer temp;
			Integer packageId = null;
			Criteria criteriasno = session.createCriteria(Billdetails.class);
			criteriasno.setProjection(Projections.max("sno"));
			Integer sno = (Integer) criteriasno.uniqueResult();
			if(sno==null)
			{sno=0;}
			temp = sno;
			Criteria criteria1 = session.createCriteria(Appointment.class);
			criteria1.add(Restrictions.eq("appointmentid", appointmentId));
			Appointment Appointment = (Appointment) criteria1.uniqueResult();
			List<Appointmentservicedetails> appointmentservicemaster = Appointment.getAppointmentservicedetailses();
			for (Appointmentservicedetails appointmentServiceMaster : appointmentservicemaster) {
				if(appointmentServiceMaster.getServicemaster().getServiceid()==serviceId && appointmentServiceMaster.getStatusmaster().getStatusid()==2){
					if(appointmentServiceMaster.getPackagemaster()!=null)
					{
						packageId = appointmentServiceMaster.getPackagemaster().getPackageid();
					}
					else
					{
						packageId = null;	
					}
					Statusmaster status = new Statusmaster();
					status.setStatusid(3);
					appointmentServiceMaster.setStatusmaster(status);
					Date date = new Date();
					DateFormat time = new SimpleDateFormat("hh:mm:ss");
			        appointmentServiceMaster.setActdeparttime(time.format(date));
			        appointmentServiceMaster.setActdepartdate(date);
					session.update(appointmentServiceMaster);
				}
			}
			if(packageId==null)
			{
			Date date = new Date();
			Criteria criteria = session.createCriteria(Serviceratemaster.class,"servicerates");
			criteria.createAlias("servicerates.servicemaster", "servicemaster");
			criteria.add(Restrictions.eq("servicemaster.serviceid",serviceId));
			criteria.add(Restrictions.le("applicabledate", date));
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			Serviceratemaster list = (Serviceratemaster) criteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
			Billdetails billData = new Billdetails();
			Appointment appointment = new Appointment();
			appointment.setAppointmentid(appointmentId);
			billData.setAppointment(appointment);
			Servicemaster serviceMaster = new Servicemaster();
			/*Billmaster billmaster = new Billmaster();
			billmaster.setBillno(0);
			billData.setBillmaster(billmaster);*/
			billData.setUpdatedby("Kranthi");
			billData.setUpdateddate(date);
			billData.setUpdatedip(Ipaddress.getIpAddress());
			billData.setAmount((list.getServicecost()));
			serviceMaster.setServiceid(serviceId);
			billData.setServicemaster(serviceMaster);
			
			billData.setSno(++temp);
			session.save(billData);
			Criteria taxriteria = session.createCriteria(Taxstructuremaster.class);
			taxriteria.add(Restrictions.le("applicabledate", date));
			taxriteria.add(Restrictions.eq("taxstructureid", list.getTaxstructureid()));
			Taxstructuremaster list2 = (Taxstructuremaster) taxriteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
			Criteria taxcriteria = session.createCriteria(Taxstructuredetails.class,"taxdetails");
			taxcriteria.createAlias("taxdetails.taxstructuremaster", "taxstructuremaster");
			taxcriteria.add(Restrictions.eq("taxstructuremaster.sno", list2.getSno()));
			List<Taxstructuredetails> taxstructuredetaillist = taxcriteria.add(Restrictions.eq("taxstructureid", list2.getTaxstructureid())).list();
			billData.setTaxstructuremaster(list2);
			for (Taxstructuredetails taxstructureDetails : taxstructuredetaillist) {
				Billdetails billData1 = new Billdetails();
				appointment.setAppointmentid(appointmentId);
				billData1.setAppointment(appointment);
				billData1.setUpdatedby("Kranthi");
				billData1.setUpdateddate(date);
				billData1.setUpdatedip(Ipaddress.getIpAddress());
				billData1.setTaxstructureid(list.getTaxstructureid());
				billData1.setServicemaster(serviceMaster);
				/*Billmaster billmaster1 = new Billmaster();
				billmaster1.setBillno(0);
				billData1.setBillmaster(billmaster1);*/
				billData1.setTaxstructuremaster(list2);
				BigDecimal multiply = new BigDecimal(0);
				BigDecimal percent = taxstructureDetails.getPercent();
				if(list.getServicecost()!=null)
				{
					multiply = percent.multiply(list.getServicecost());
				}
				BigDecimal percentage = new BigDecimal(100);
				billData1.setAmount((multiply.divide(percentage)));
				billData1.setTaxdetailsid(taxstructureDetails.getTaxdetailsid());
				billData1.setSno(++temp);
				session.save(billData1);
			}
			}
			Integer count=0;
			for (Appointmentservicedetails appointmentServiceMaster : appointmentservicemaster) {
				if(appointmentServiceMaster.getStatusmaster().getStatusid()==3)
				{
					count++;
				}
			}
			if(count==appointmentservicemaster.size())
			{
				Statusmaster status = new Statusmaster();
				status.setStatusid(3);
				Appointment.setStatusmaster(status);
				session.update(Appointment);
				Criteria createCriteria = session.createCriteria(Appointment.class,"appointment");
				createCriteria.createAlias("appointment.appointmentservicedetailses", "appointmentservice");
				createCriteria.createAlias("appointmentservice.packagemaster", "packagemaster");
				createCriteria.add(Restrictions.eq("appointment.appointmentid", appointmentId));
				createCriteria.add(Restrictions.isNotNull("packagemaster.packageid"));
				createCriteria.setProjection(Projections.distinct(Projections.property("packagemaster.packageid")));
				List<Integer> list = createCriteria.list();
				System.out.println(list);
				for (Integer packageid : list) {
					Date date1 = new Date();
					Criteria packagecriteria = session.createCriteria(Packageratemaster.class,"packagerates");
					packagecriteria.createAlias("packagerates.packagemaster", "packagemaster");
					packagecriteria.add(Restrictions.eq("packagemaster.packageid",packageid));
					packagecriteria.add(Restrictions.le("applicabledate", date1));
					packagecriteria.setFetchMode("packagemaster", FetchMode.EAGER);
					Packageratemaster packagerates = (Packageratemaster) packagecriteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
					Billdetails packageBillData = new Billdetails();
					Appointment appointments = new Appointment();
					appointments.setAppointmentid(appointmentId);
					packageBillData.setAppointment(appointments);
					Packagemaster packagemaster = new Packagemaster();
					/*Billmaster billmaster1 = new Billmaster();
					billmaster1.setBillno(0);
					packageBillData.setBillmaster(billmaster1);*/
					packageBillData.setUpdatedby("Kranthi");
					packageBillData.setUpdateddate(date1);
					packageBillData.setUpdatedip(Ipaddress.getIpAddress());
					packageBillData.setAmount(packagerates.getPackagecost());
					packagemaster.setPackageid(packageid);
					packageBillData.setPackagemaster(packagemaster);
					
					packageBillData.setSno(++temp);
					session.save(packageBillData);
					Criteria packagetaxcriteria = session.createCriteria(Taxstructuremaster.class);
					packagetaxcriteria.add(Restrictions.le("applicabledate", date1));
					packagetaxcriteria.add(Restrictions.eq("taxstructureid", packagerates.getTaxstructureid()));
					Taxstructuremaster packagelist2 = (Taxstructuremaster) packagetaxcriteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
					Criteria packagetaxcriteria1 = session.createCriteria(Taxstructuredetails.class,"taxdetails");
					packagetaxcriteria1.createAlias("taxdetails.taxstructuremaster", "taxstructuremaster");
					packagetaxcriteria1.add(Restrictions.eq("taxstructuremaster.sno", packagelist2.getSno()));
					List<Taxstructuredetails> packagetaxstructuredetaillist = packagetaxcriteria1.add(Restrictions.eq("taxstructureid", packagelist2.getTaxstructureid())).list();
					packageBillData.setTaxstructuremaster(packagelist2);
					for (Taxstructuredetails taxstructureDetails : packagetaxstructuredetaillist) {
						Billdetails billData1 = new Billdetails();
						appointments.setAppointmentid(appointmentId);
						billData1.setAppointment(appointments);
						billData1.setUpdatedby("Kranthi");
						billData1.setUpdateddate(date1);
						billData1.setUpdatedip(Ipaddress.getIpAddress());
						billData1.setTaxstructureid(packagerates.getTaxstructureid());
						billData1.setPackagemaster(packagemaster);
						/*Billmaster billmaster2 = new Billmaster();
						billmaster2.setBillno(0);
						billData1.setBillmaster(billmaster2);*/
						billData1.setTaxstructuremaster(packagelist2);
						BigDecimal multiply = new BigDecimal(0);
						BigDecimal percent = taxstructureDetails.getPercent();
						if(packagerates.getPackagecost()!=null)
						{
							multiply = percent.multiply(packagerates.getPackagecost());
						}
						BigDecimal percentage = new BigDecimal(100);
						billData1.setAmount(multiply.divide(percentage));
						billData1.setTaxdetailsid(taxstructureDetails.getTaxdetailsid());
						billData1.setSno(++temp);
						session.save(billData1);
					}
				}
			}
			transaction.commit();
			stockConsumption(appointmentId,serviceId);
		}
		catch(Exception e) {
			logger.error("Exception raised in stopService method:", e);
		}
		
		finally {
			session.close();
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Set<Appointment> getAppointmentsCompletedList() {
		Session session = sessionFactory.openSession();
		Set<Appointment> appointmentslist = new LinkedHashSet<Appointment>();
		try{
			Criteria criteria = session.createCriteria(Statusmaster.class);
			criteria.add(Restrictions.eq("statusid", 3));
			criteria.setProjection(Projections.property("statusdescription"));
			String status = (String) criteria.uniqueResult();
			Criteria criteria1 = session.createCriteria(Appointment.class,"Appointment");
			criteria1.setFetchMode("guestmaster", FetchMode.EAGER);
			criteria1.setFetchMode("appointmentservicemaster", FetchMode.EAGER);
			@SuppressWarnings("unchecked")
			List<Appointment> list = criteria1.list();
			for (Appointment Appointment : list) {
				if(Appointment.getStatusmaster().getStatusdescription().equals(status)){
					appointmentslist.add(Appointment);
				}
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in stopService method:", e);
		}
		
		finally {
			session.close();
		}
		return appointmentslist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Appointmentservicedetails> getAppointmentServicecompletedList() {
		Session session = sessionFactory.openSession();
		Set<Appointmentservicedetails> appointmentservicelist = new LinkedHashSet<Appointmentservicedetails>();
		try{
			Criteria criteria = session.createCriteria(Statusmaster.class);
			criteria.add(Restrictions.eq("statusid", 3));
			criteria.setProjection(Projections.property("statusdescription"));
			String status = (String) criteria.uniqueResult();			
			Criteria appointmentCriteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			appointmentCriteria.createAlias("appointmentservice.appointment", "appointment");
			appointmentCriteria.createAlias("appointmentservice.servicemaster", "servicemaster");
			appointmentCriteria.createAlias("appointmentservice.statusmaster", "statusmaster");
			appointmentCriteria.createAlias("appointment.guestmaster", "guestmaster");
			appointmentCriteria.add(Restrictions.eq("appointmentservice.valid", true));
			appointmentCriteria.add(Restrictions.eq("statusmaster.statusdescription", status));
			List<Appointmentservicedetails> appointmentList = appointmentCriteria.list();
			for (Appointmentservicedetails appointmentservicedetails : appointmentList) {
				appointmentservicelist.add(appointmentservicedetails);
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in stopService method:", e);
		}
		
		finally {
			session.close();
		}
		return appointmentservicelist;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Stockmaster> getStockOfRetailType() {
		
		Session session = sessionFactory.openSession();
		List<Stockmaster> stockMaster = new LinkedList<Stockmaster>();
		try{
			Dropdowndetails uniqueResult = (Dropdowndetails) session.createCriteria(Dropdowndetails.class).add(Restrictions.eq("description","Retail")).uniqueResult();
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.setFetchMode("dropdowndetails", FetchMode.EAGER);
			List<Stockmaster> list = criteria.list();
			for (Stockmaster stockmaster2 : list) {
				if(stockmaster2.getDropdowndetails().getDescription()==uniqueResult.getDescription())
				{
					stockMaster.add(stockmaster2);
				}
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in getServiceandTax method:", e);
		}
		
		finally {
			session.close();
		}
		return stockMaster;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ProductRateAndTax getProductRateAndTax(Integer productId) {
		Session session = sessionFactory.openSession();
		ProductRateAndTax productRates = new ProductRateAndTax();
		try{
			Date date = new Date();
			Criteria criteria = session.createCriteria(Productratemaster.class,"productrates");
			criteria.createAlias("productrates.stockmaster", "stockmaster");
			criteria.add(Restrictions.eq("stockmaster.stockid",productId));
			criteria.add(Restrictions.le("applicabledate", date));
			criteria.setFetchMode("stockmaster", FetchMode.EAGER);
			Productratemaster list = (Productratemaster) criteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
			Criteria taxriteria = session.createCriteria(Taxstructuremaster.class);
			taxriteria.add(Restrictions.le("applicabledate", date));
			taxriteria.add(Restrictions.eq("taxstructureid", list.getTaxstructureid()));
			Taxstructuremaster list2 = (Taxstructuremaster) taxriteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
			Criteria taxcriteria = session.createCriteria(Taxstructuredetails.class,"taxdetails");
			taxcriteria.createAlias("taxdetails.taxstructuremaster", "taxstructuremaster");
			taxcriteria.add(Restrictions.eq("taxstructuremaster.sno", list2.getSno()));
			List<Taxstructuredetails> taxstructuredetaillist = taxcriteria.add(Restrictions.eq("taxstructureid", list2.getTaxstructureid())).list();
			productRates.setTaxAmount(taxstructuredetaillist);
			productRates.setProductCost(list.getProductcost());
			productRates.setProductName(list.getStockmaster().getStockname());
		}
		catch(Exception e) {
			logger.error("Exception raised in getServiceandTax method:", e);
		}
		
		finally {
			session.close();
		}
		return productRates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String generateBillDetails(List<DataToGenerateBill> dataArray) {
		Integer temp = 0;
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		Date date = new Date();
		/*Billmaster billmaster = new Billmaster();
		billmaster.setBillno(0);*/
		Criteria criteriasno = session.createCriteria(Billdetails.class);
		criteriasno.setProjection(Projections.max("sno"));
		Integer sno = (Integer) criteriasno.uniqueResult();
		if(sno==null)
		{sno=0;}
		temp = sno;
		BigDecimal totalamount = new BigDecimal(0);
		String message = null;
		try{
			Criteria criteria = session.createCriteria(Productsales.class);
			criteria.setProjection(Projections.max("productsalesid"));
			Integer pid = (Integer) criteria.uniqueResult();
			if(pid==null)
			{pid=0;}
			Criteria criterias = session.createCriteria(Productsales.class);
			criterias.setProjection(Projections.max("sno"));
			Integer psno = (Integer) criterias.uniqueResult();
			if(psno==null)
			{psno=0;}
			
			for (DataToGenerateBill dataToGenerateBill : dataArray) {
				if(dataToGenerateBill.getProductrate()!=null){
					Billdetails billData = new Billdetails();
					Appointment appointment = new Appointment();
					appointment.setAppointmentid(dataToGenerateBill.getAppointmentId());
					billData.setAppointment(appointment);
					Stockmaster stockMaster = new Stockmaster();
					billData.setUpdatedby("Kranthi");
					/*billData.setBillmaster(billmaster);*/
					billData.setUpdateddate(date);
					billData.setUpdatedip(Ipaddress.getIpAddress());
					billData.setAmount(dataToGenerateBill.getProductrate().multiply(dataToGenerateBill.getQuantity()));
					stockMaster.setStockid(dataToGenerateBill.getProductId());
					billData.setStockmaster(stockMaster);
					billData.setProductquantity(dataToGenerateBill.getQuantity().intValueExact());
					billData.setSno(++temp);
					session.save(billData);
					Productsales productsales = new Productsales();
					/*productsales.setBillmaster(billmaster);*/
					productsales.setQuantity(dataToGenerateBill.getQuantity().intValueExact());
					productsales.setStockmaster(stockMaster);
					productsales.setUnitprice(dataToGenerateBill.getProductrate());
					productsales.setTotalamount(dataToGenerateBill.getProductrate().multiply(dataToGenerateBill.getQuantity()));
					productsales.setValid(true);
					productsales.setUpdatedby("kranthi");
					productsales.setUpdateddate(date);
					productsales.setUpdatedip(Ipaddress.getIpAddress());
					productsales.setProductsalesid(++pid);
					productsales.setSno(++psno);
					session.save(productsales);
					totalamount = totalamount.add(dataToGenerateBill.getTotal());
				}
					if(dataToGenerateBill.getProductId()!=null)
					{
						Criteria criteriapid = session.createCriteria(Productratemaster.class,"productratemaster");
						criteriapid.createAlias("productratemaster.stockmaster", "stockmaster");
						criteriapid.add(Restrictions.eq("stockmaster.stockid",dataToGenerateBill.getProductId()));
						Productratemaster list = (Productratemaster) criteriapid.addOrder(Order.asc("applicabledate")).setMaxResults(1).uniqueResult();
						Criteria taxriteria = session.createCriteria(Taxstructuremaster.class);
						taxriteria.add(Restrictions.le("applicabledate", date));
						taxriteria.add(Restrictions.eq("taxstructureid", list.getTaxstructureid()));
						Taxstructuremaster list2 = (Taxstructuremaster) taxriteria.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
						Criteria taxcriteria = session.createCriteria(Taxstructuredetails.class,"taxdetails");
						taxcriteria.createAlias("taxdetails.taxstructuremaster", "taxstructuremaster");
						taxcriteria.add(Restrictions.eq("taxstructuremaster.sno", list2.getSno()));
						List<Taxstructuredetails> uniqueResult = taxcriteria.add(Restrictions.eq("taxstructureid", list2.getTaxstructureid())).list();
						for (Taxstructuredetails taxstructureDetails : uniqueResult) {
							Billdetails billData = new Billdetails();
							Appointment appointment = new Appointment();
							appointment.setAppointmentid(dataToGenerateBill.getAppointmentId());
							billData.setAppointment(appointment);
							/*billData.setBillmaster(billmaster);*/
							Stockmaster stockMaster = new Stockmaster();
							billData.setUpdatedby("Kranthi");
							billData.setUpdateddate(date);
							billData.setUpdatedip(Ipaddress.getIpAddress());
							stockMaster.setStockid(dataToGenerateBill.getProductId());
							billData.setStockmaster(stockMaster);
							billData.setTaxstructureid(list.getTaxstructureid());

							BigDecimal multiply = new BigDecimal(0);
							BigDecimal percent = taxstructureDetails.getPercent();
							if(dataToGenerateBill.getProductrate()!=null)
							{
								multiply = percent.multiply(dataToGenerateBill.getProductrate().multiply(dataToGenerateBill.getQuantity()));
							}
							BigDecimal percentage = new BigDecimal(100);
							billData.setAmount(multiply.divide(percentage));
							billData.setTaxdetailsid(taxstructureDetails.getTaxdetailsid());
							billData.setSno(++temp);
							session.save(billData);
						}
						totalamount = totalamount.add(dataToGenerateBill.getTotal());
					}
					else
					{
						if(dataToGenerateBill.getAppointmentId()!=null && dataToGenerateBill.getServiceId()==null && dataToGenerateBill.getPackageId()==null && dataToGenerateBill.getProductId()==null)
						{
							Billdetails billData = new Billdetails();
							Appointment appointment = new Appointment();
							appointment.setAppointmentid(dataToGenerateBill.getAppointmentId());
							billData.setAppointment(appointment);
							Criteria discountCriteria = session.createCriteria(Discountmaster.class,"discount");
							discountCriteria.add(Restrictions.isNull("billmaster"));
							/*discountCriteria.createAlias("discount.billmaster", "billmaster");
							discountCriteria.add(Restrictions.eq("billmaster.billno", 0));*/
							Discountmaster discountMaster = (Discountmaster) discountCriteria.uniqueResult();
							billData.setDiscountmaster(discountMaster);
							billData.setUpdatedby("Kranthi");
							//billData.setBillmaster(billmaster);
							billData.setUpdateddate(date);
							billData.setUpdatedip(Ipaddress.getIpAddress());
							BigDecimal discountamount = discountMaster.getDiscount();
							BigDecimal per = new BigDecimal(100);
							if((discountMaster.getDiscounttype()).equals("Percent"))
							{
								discountamount = discountamount.multiply(totalamount);
								discountamount = discountamount.divide(per);
								billData.setAmount(discountamount);
							}
							else{
								billData.setAmount(discountMaster.getDiscount());
							}
							billData.setSno(++temp);
							session.save(billData);
							totalamount = totalamount.subtract(discountamount);
						}
						else{
							totalamount = totalamount.add(dataToGenerateBill.getTotal());
						}
					}
					
				} 
			beginTransaction.commit();
			message = generateBillnoWithBillDetails(totalamount,dataArray);
		}
		catch(Exception e) {
			logger.error("Exception raised in generateBillDetails method:", e);
			message = "Bill generation failured";
		}
		
		finally {
			
			session.close();
		}
		return message;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Billdetails> getServicerate(Integer serviceId, Integer appointmentId) {
		Session session = sessionFactory.openSession();
		List<Billdetails> list = new LinkedList<Billdetails>();
		try{
			Criteria criteria = session.createCriteria(Billdetails.class,"billdata");
			criteria.createAlias("billdata.appointment", "appointment");
			criteria.createAlias("billdata.servicemaster", "servicemaster");
			criteria.add(Restrictions.eq("appointment.appointmentid", appointmentId));
			criteria.add(Restrictions.eq("servicemaster.serviceid", serviceId));
			list = criteria.list();
		}
		catch(Exception e) {
			logger.error("Exception raised in getServicerate method:", e);
		}
		
		finally {
			session.close();
		}
		return list;
	}

	
	
@SuppressWarnings("unchecked")
public void stockConsumption(Integer appointmentId, Integer serviceId) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
		
			Criteria criteria = session.createCriteria(Appointmentstockconsumption.class,"appointmentstock");
			criteria.createAlias("appointmentstock.stockmaster", "stockmaster");
			criteria.createAlias("appointmentstock.appointment", "appointment");
			criteria.add(Restrictions.eq("appointment.appointmentid", appointmentId));
			List<Appointmentstockconsumption> list = criteria.list();
			for (Appointmentstockconsumption appointmentstockconsumption : list) {
				Stockmaster stockmaster = appointmentstockconsumption.getStockmaster();
				stockmaster.setAvailable(stockmaster.getAvailable().subtract(appointmentstockconsumption.getStockusage()));
				BigDecimal stockusage = appointmentstockconsumption.getStockusage();
				while(stockusage.compareTo( BigDecimal.ZERO) > 0 ){
					Stockreceipt stockreceipt = new Stockreceipt();
					Criteria createCriteria = session.createCriteria(Stockreceipt.class,"stockreceipt");
					createCriteria.createAlias("stockreceipt.stockmaster", "stockmaster");
					createCriteria.add(Restrictions.eq("stockreceipt.valid", true));
					createCriteria.add(Restrictions.eq("stockmaster.stockid", stockmaster.getStockid()));
					stockreceipt = (Stockreceipt) createCriteria.addOrder(Order.asc("expirydate")).addOrder(Order.desc("receiveddate")).setMaxResults(1).uniqueResult();
					Integer currentstock = stockreceipt.getCurrentstock();
					if(stockreceipt!=null)
					{
					if(currentstock==0){
						stockreceipt.setValid(false);
						session.update(stockreceipt);
					}
					else if(currentstock<appointmentstockconsumption.getStockusage().intValueExact()){
						stockreceipt.setCurrentstock((appointmentstockconsumption.getStockusage().subtract(new BigDecimal(currentstock))).intValueExact());
						stockusage = stockusage.subtract(new BigDecimal(currentstock));
						session.update(stockreceipt);
					}
					else{
						stockreceipt.setCurrentstock(currentstock-(appointmentstockconsumption.getStockusage().intValueExact()));
						stockusage = new BigDecimal(0);
						session.update(stockreceipt);
					}
				}
				session.update(stockmaster);
				}
			}
			transaction.commit();
		}
		catch(Exception e) {
			logger.error("Exception raised in stockConsumption method:", e);
		}
		
		finally {
			session.close();
		}
	}
	
@SuppressWarnings({ "unchecked", "deprecation" })
private String generateBillnoWithBillDetails(BigDecimal totalamount, List<DataToGenerateBill> dataArray) {
		
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	Date date = new Date();
	String message = null;
	try{
		Billmaster billmaster2 = new Billmaster();
		Guestmaster guestmaster = new Guestmaster();
		guestmaster.setGuestid(dataArray.get(0).getGuestId());
		billmaster2.setGuestmaster(guestmaster);
		billmaster2.setSettled(false);
		billmaster2.setValid(true);
		billmaster2.setUpdatedby("Kranthi");
		billmaster2.setUpdateddate(date);
		billmaster2.setBilldate(date);
		billmaster2.setUpdatedip(Ipaddress.getIpAddress());
		Criteria createCriteria = session.createCriteria(Billmaster.class);
		Integer billmastersno = (Integer) createCriteria.setProjection(Projections.max("sno")).uniqueResult();
		if(billmastersno==null)
		{billmastersno=0;}
		Criteria create = session.createCriteria(Billmaster.class);
		Integer billmasterbillno = (Integer) create.setProjection(Projections.max("billno")).uniqueResult();
		if(billmasterbillno==null)
		{billmasterbillno=0;}
		totalamount = totalamount.setScale(0, RoundingMode.CEILING);
		billmaster2.setSno(++billmastersno);
		billmaster2.setBillno(++billmasterbillno);
		billmaster2.setAmount(totalamount);
		billmaster2.setOutstandingamount(totalamount);
		session.save(billmaster2);
		Criteria billcriteria = session.createCriteria(Billdetails.class,"bill");
		//billcriteria.createAlias("bill.billmaster", "billmaster");
		billcriteria.add(Restrictions.isNull("billmaster"));
		//billcriteria.add(Restrictions.eq("billmaster.billno", 0));
		billcriteria.setFetchMode("bill.stockmaster", FetchMode.EAGER);
		List<Billdetails> list = billcriteria.list();
		for (Billdetails billdetails : list) {
			for (DataToGenerateBill dataToGenerateBill1 : dataArray)
			{
				if(billdetails.getAppointment().getAppointmentid()==dataToGenerateBill1.getAppointmentId())
				{
					if(billdetails.getServicemaster()!=null && dataToGenerateBill1.getServiceId()!=null)
					{
						if(billdetails.getServicemaster().getServiceid()==dataToGenerateBill1.getServiceId())
						{
							billdetails.setBillmaster(billmaster2);
							session.update(billdetails);
						}
					}
					if(billdetails.getStockmaster()!=null && dataToGenerateBill1.getProductId()!=null)
					{
						if(billdetails.getStockmaster().getStockid()==dataToGenerateBill1.getProductId())
						{
							billdetails.setBillmaster(billmaster2);
							session.update(billdetails);
						}
					}
					else{
						billdetails.setBillmaster(billmaster2);
						session.update(billdetails);
					}
				}
			}
			}
			Criteria productsalescriteria = session.createCriteria(Productsales.class,"sales");
			//productsalescriteria.createAlias("sales.billmaster", "billmaster");
			billcriteria.add(Restrictions.isNull("billmaster"));
			//productsalescriteria.add(Restrictions.eq("billmaster.billno", 0));
			List<Productsales> saleslist = productsalescriteria.list();
			for (Productsales productsales : saleslist) {
				productsales.setBillmaster(billmaster2);
				Stockmaster stockmaster = productsales.getStockmaster();
				Criteria stockcriteria = session.createCriteria(Stockmaster.class);
				stockcriteria.add(Restrictions.eq("stockid", stockmaster.getStockid()));
				List<Stockmaster> list2 = stockcriteria.list();
				for (Stockmaster stockmaster2 : list2) {
					BigDecimal available = stockmaster2.getAvailable();
					available = available.subtract(new BigDecimal(productsales.getQuantity()));
					stockmaster2.setAvailable(available);
					session.update(stockmaster2);
				}
				session.update(productsales);
				
			}
			for (Billdetails billdetails : list) {
				for (DataToGenerateBill dataToGenerateBill1 : dataArray)
				{
					if(billdetails.getAppointment().getAppointmentid()==dataToGenerateBill1.getAppointmentId())
					{
						Appointment appointment = billdetails.getAppointment();
						Statusmaster statusmaster = new Statusmaster();
						statusmaster.setStatusid(5);
						appointment.setStatusmaster(statusmaster);
						session.update(appointment);
						List<Appointmentservicedetails> appointmentservicedetailses = billdetails.getAppointment().getAppointmentservicedetailses();
						for (Appointmentservicedetails appointmentservicedetails : appointmentservicedetailses) {
							appointmentservicedetails.setStatusmaster(statusmaster);
							session.update(appointmentservicedetails);
						}
					}
				}
			}
			Criteria discountcriteria = session.createCriteria(Discountmaster.class);
			//discountcriteria.createAlias("discount.billmaster", "bill");
			discountcriteria.add(Restrictions.isNull("billmaster"));
			//discountcriteria.add(Restrictions.eq("bill.billno", 0));
			List<Discountmaster> discountlist = discountcriteria.list();
			for (Discountmaster discountmaster : discountlist) {
				discountmaster.setBillmaster(billmaster2);
				discountmaster.setUpdatedby("Kranthi");
				discountmaster.setUpdateddate(date);
				discountmaster.setUpdatedip(Ipaddress.getIpAddress());
				session.update(discountmaster);
			}
			transaction.commit();
			message = billmasterbillno.toString();
		}
	catch(Exception e) {
		message = "Bill generation failured";
		logger.error("Exception raised in stockConsumption method:", e);
	}
	
	finally {
		session.close();
	}
	return message;
	}

@Override
public Integer getRetailStockAvailability(Integer stockId) {
	BigDecimal availability=null;
	Session session = sessionFactory.openSession();
	try {
		Criteria criteria = session.createCriteria(Stockmaster.class);
		criteria.add(Restrictions.eq("stockid", stockId)).add(Restrictions.eq("valid", true));
		availability=(BigDecimal) criteria.setProjection(Projections.property("available")).uniqueResult();
		} catch (Exception e) {
		logger.error("Exception raised in getRetailStockAvailability method:",e);
	}
	finally{session.close();}
	return availability.intValueExact();
}

@SuppressWarnings("unchecked")
@Override
public Set<Appointmentservicedetails> getAppointmentPackageServicecompletedList() {
	Session session = sessionFactory.openSession();
	Set<Appointmentservicedetails> appointmentservicelist = new LinkedHashSet<Appointmentservicedetails>();
	try{
		Criteria criteria = session.createCriteria(Statusmaster.class);
		criteria.add(Restrictions.eq("statusid", 3));
		criteria.setProjection(Projections.property("statusdescription"));
		String status = (String) criteria.uniqueResult();			
		Criteria appointmentCriteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
		appointmentCriteria.createAlias("appointmentservice.appointment", "appointment");
		appointmentCriteria.createAlias("appointmentservice.servicemaster", "servicemaster");
		appointmentCriteria.createAlias("appointmentservice.statusmaster", "statusmaster");
		appointmentCriteria.createAlias("appointmentservice.packagemaster", "packagemaster");
		appointmentCriteria.createAlias("appointment.guestmaster", "guestmaster");
		appointmentCriteria.add(Restrictions.eq("appointmentservice.valid", true));
		appointmentCriteria.add(Restrictions.eq("statusmaster.statusdescription", status));
		List<Appointmentservicedetails> appointmentList = appointmentCriteria.list();
		for (Appointmentservicedetails appointmentservicedetails : appointmentList) {
			appointmentservicelist.add(appointmentservicedetails);
		}
	}
	catch(Exception e) {
		logger.error("Exception raised in getAppointmentPackageServicecompletedList method:", e);
	}
	
	finally {
		session.close();
	}
	return appointmentservicelist;
}

@SuppressWarnings("unchecked")
@Override
public List<Billdetails> getPackageRate(Integer packageId, Integer appointmentId) {
	Session session = sessionFactory.openSession();
	List<Billdetails> list = new LinkedList<Billdetails>();
	try{
		Criteria criteria = session.createCriteria(Billdetails.class,"billdata");
		criteria.createAlias("billdata.appointment", "appointment");
		criteria.createAlias("billdata.packagemaster", "packagemaster");
		criteria.add(Restrictions.eq("appointment.appointmentid", appointmentId));
		criteria.add(Restrictions.eq("packagemaster.packageid", packageId));
		list = criteria.list();
	}
	catch(Exception e) {
		logger.error("Exception raised in getPackageRate method:", e);
	}
	
	finally {
		session.close();
	}
	return list;
}

@Override
public List printbillreceipt(Integer billno,Integer guestid) {

	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	ArrayList<Object> listPrintBill = new ArrayList<Object>();
	ArrayList<Object> headeDetails = new ArrayList<Object>();
	ArrayList<Object> headeDetails1 = new ArrayList<Object>();

	headeDetails1.add("tax");
	headeDetails1.add("Value");
	
	String billQuery = "select amount,appointmentid,serviceid,productid,packageid from billdetails where billno='"+billno+"' and taxdetailsid is null";
	List billList = session.createSQLQuery(billQuery).list();
	Iterator iebill = billList.iterator();
	Integer appid=0;
	String guestname = null;
	Integer servid=0;
	BigDecimal servrate = null;
	String servname=null;
	String pkgname=null;
	Integer prodid =0;
	Integer pkgid = 0;
	Integer packid = 0;
	
	String prodname = "n/a";
	BigDecimal prodrate = null;
	Integer prodqty=0;
	
	Integer taxid = null;
	Integer prodtaxid = null;
	Integer taxstrucid = 0;
	Integer taxdetid = 0;
	
	
	String taxdescription = null;
	Integer discountid = 0;
	BigDecimal discamt= new BigDecimal(0); 
	BigDecimal percent= new BigDecimal(0); 
	BigDecimal grandtotal= new BigDecimal(0); 
	
	List<Map<String,String>> ListServices=new ArrayList<Map<String,String>>();
	
	Map<String,String> billmap = new LinkedHashMap<String, String>();
	billmap.put("pname", "Invoice No");
	billmap.put("pqty", "");
	billmap.put("prate", billno.toString());
	ListServices.add(billmap);
	
	
	Date newDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
	String formatedDate = sdf.format(newDate);
	
	Map<String,String> datemap = new LinkedHashMap<String, String>();
	datemap.put("pname", "Invoice Date");
	datemap.put("pqty", "");
	datemap.put("prate", formatedDate);
	ListServices.add(datemap);
	
	
	String guestQuery = "select name from guestmaster where guestid='"+guestid+"'";
	List guestlist = session.createSQLQuery(guestQuery).list();
	Iterator guestiterator = guestlist.iterator();
	while (guestiterator.hasNext()) {
		Map<String,String> pmap = new LinkedHashMap<String, String>();
		Object object = (Object) guestiterator.next();
		guestname = (String)object;
		
		pmap.put("pname", "Guest Name");
		pmap.put("pqty", "");
		pmap.put("prate", guestname);
		ListServices.add(pmap);
	}
	
/*	Map<String,String> emap1 = new LinkedHashMap<String, String>();
	emap1.put("pname", "");
	emap1.put("pqty", "");
	emap1.put("prate", "");
	ListServices.add(emap1);*/
	
	
	
	
	
	
	
	
	
	
	
	
	Map<String,String> map1 = new LinkedHashMap<String, String>();
	
	while (iebill.hasNext()) {
		Object[] billdetails = (Object[]) iebill.next();
		BigDecimal amt = (BigDecimal) billdetails[0];
		appid  = (Integer)billdetails[1] ;
		servid = (Integer) billdetails[2];
		prodid = (Integer) billdetails[3];
		pkgid  = (Integer) billdetails[4];
		

		
		
		
		
		
		
/*	Map<String,String> pkgmap = new LinkedHashMap<String, String>();
		String pkgQuery = "select packagename from packagemaster where packageid='"+pkgid+"'";
		List pkgList = session.createSQLQuery(pkgQuery).list();
		Iterator pkgiterator = pkgList.iterator();
		while (pkgiterator.hasNext()) {
			Object object = (Object) pkgiterator.next();
			pkgname = (String)object;
			
		}
		
		pkgmap.put("pname", "Package Name");
		pkgmap.put("pqty", "");
		pkgmap.put("prate", pkgname);
*/		
		
		
		
		if(servid != null){
			Map<String,String> map = new LinkedHashMap<String, String>();
			String StrServRate=null;
			String servQuery = "select servicename from servicemaster where serviceid='"+servid+"'";
			List servlist = session.createSQLQuery(servQuery).list();
			Iterator iterator = servlist.iterator();
			while (iterator.hasNext()) {
				Object object = (Object) iterator.next();
				servname = (String)object;
			}
			String servrateQuery = "select servicecost from serviceratemaster where serviceid='"+servid+"' and applicabledate <='"+new Date()+"' ORDER by applicabledate DESC";
			int i=0;
			List servratelist = session.createSQLQuery(servrateQuery).list();
			Iterator servrateiterator = servratelist.iterator();
			while (servrateiterator.hasNext()) {
				Object object = (Object) servrateiterator.next();
				
				if(i==0){
				servrate = (BigDecimal)object;
				StrServRate=servrate.toString();
				i++;
				}
				else{      
					i++;
				}
				
			}
			map.put("pname", servname);
			map.put("pqty", "");
			map.put("prate", StrServRate);
			
			ListServices.add(map);
			System.out.println("!!!!!!!!!!!!!!!!mapmapmapma2222222222222222222p!!!!!!!!!!!!!!!"+map);
			
		}
		
	}		
	
	
	/*emap1.put("pname", "");
	emap1.put("pqty", "");
	emap1.put("prate", "");
	ListServices.add(emap1);*/
	
	String pkgidQuery = "select Distinct(packageid) from billdetails where billno = '"+billno+"' and packageid is not null";
	List pkgidList = session.createSQLQuery(pkgidQuery).list();
	Iterator pkgiditerator = pkgidList.iterator();
	while (pkgiditerator.hasNext()) {
		Map<String,String> pkgmap = new LinkedHashMap<String, String>();
		Object pkgidobject = (Object) pkgiditerator.next();
		packid = (Integer)pkgidobject;
		
		String pkgQuery = "select packagename from packagemaster where packageid='"+packid+"'";
		List pkgList = session.createSQLQuery(pkgQuery).list();
		Iterator pkgiterator = pkgList.iterator();
		while (pkgiterator.hasNext()) {
			Object object = (Object) pkgiterator.next();
			pkgname = (String)object;
			
		}
		
		pkgmap.put("pname", "Package Name");
		pkgmap.put("pqty", "");
		pkgmap.put("prate", pkgname);
		ListServices.add(pkgmap);
	}
	    
	
	String productQuery = "select productid from billdetails where billno='"+billno+"' and taxdetailsid is null and productid is not null";
	List productlist = session.createSQLQuery(productQuery).list();
	Iterator productiterator = productlist.iterator();
	while (productiterator.hasNext()) {
		Object prod = (Object) productiterator.next();
		prodid =(Integer) prod;
		
		Map<String,String> pmap = new LinkedHashMap<String, String>();
		
		String prodQuery = "select stockname from stockmaster where stockid='" + prodid + "'";
		List prodlist = session.createSQLQuery(prodQuery).list();
		Iterator proditerator = prodlist.iterator();
		while (proditerator.hasNext()) {
			Object stockname = (Object) proditerator.next();
			prodname = (String) stockname;
		}

		String productQtyQuery = "select productquantity from billdetails where billno='"+billno+"' and productid ='"+prodid+"' and taxdetailsid is null";
		List prodQtylist = session.createSQLQuery(productQtyQuery).list();
		Iterator prodQtyiterator = prodQtylist.iterator();
		while (prodQtyiterator.hasNext()) {
			Object stockqty = (Object) prodQtyiterator.next();
			prodqty = (Integer) stockqty;
		}
		
		String prodrateQuery = "select productcost from productratemaster where productid='" + prodid + "' and applicabledate <='"+new Date()+"' ORDER by applicabledate DESC";
		
		int i=0;
		List prodratelist = session.createSQLQuery(prodrateQuery).list();
		Iterator prodrateiterator = prodratelist.iterator();
		while (prodrateiterator.hasNext()) {
			Object stockcost = (Object) prodrateiterator.next();
			if(i==0){
				prodrate = (BigDecimal) stockcost;
				i++;
			}
			else{
				i++;
			}
			
		}
		prodrate = prodrate.multiply(new BigDecimal(prodqty));
		pmap.put("pname", prodname + "   " +"(" + prodqty.toString() + ")");
		pmap.put("pqty", "" );
		pmap.put("prate", prodrate.toString());
		
		System.out.println("************************************************************"+pmap);
		
		ListServices.add(pmap);
		
	}
	

	/*
	String discountQuery = "select discountid,amount from billdetails where billno='"+billno+"' and packageid is null and serviceid is null and productid is null and taxstructureid is null and taxdetailsid is null ";
	List discountlist = session.createSQLQuery(discountQuery).list();
	Iterator discountiterator = discountlist.iterator();
	Map<String,String> discountmap=new LinkedHashMap<String,String>();

	while (discountiterator.hasNext()) {
		Object[] discobject = (Object[]) discountiterator.next();
		discountid = (Integer) discobject[0];
		discamt = (BigDecimal) discobject[1];
		
	}
>>>>>>> stash
	
<<<<<<< HEAD
	String discountQuery = "select discountid,amount from billdetails where billno='"+billno+"' and packageid is null and serviceid is null and productid is null and taxstructureid is null and taxdetailsid is null ";
	List discountlist = session.createSQLQuery(discountQuery).list();
	Iterator discountiterator = discountlist.iterator();
	Map<String,String> discountmap=new LinkedHashMap<String,String>();

	while (discountiterator.hasNext()) {
		Object[] discobject = (Object[]) discountiterator.next();
		discountid = (Integer) discobject[0];
		discamt = (BigDecimal) discobject[1];
		
	}
	
	
	discountmap.put("pname", "Discount Amount");
	discountmap.put("pqty", "");
	discountmap.put("prate", "-"+discamt.toString());
	ListServices.add(discountmap);
	

	
	discountmap.put("pname", "Discount Amount");
	discountmap.put("pqty", "");
	discountmap.put("prate", discamt.toString());
	ListServices.add(discountmap);
	*/

	
	
	String taxQuery = "select Distinct (taxstructurecode) from billdetails where billno='"+billno+"' and (packageid is not null or  serviceid is not null) and (taxstructureid is not null and taxdetailsid is not null)";
	List taxlist = session.createSQLQuery(taxQuery).list();
	Iterator taxiterator = taxlist.iterator();
	while (taxiterator.hasNext()) {
		Object tid = (Object) taxiterator.next();
		taxid = (Integer) tid;
		String taxstructureQuery = "select taxstructureid,taxdetailsid,taxdescription from taxstructuredetails where taxstructurecode='"+taxid+"'";
		List taxstructurelist = session.createSQLQuery(taxstructureQuery).list();
		Iterator taxstructureiterator = taxstructurelist.iterator();
		while (taxstructureiterator.hasNext()) {
			Map<String,String> map=new LinkedHashMap<String,String>();
			Object[] taxsts = (Object[]) taxstructureiterator.next();
			taxstrucid = (Integer)taxsts[0];
			taxdetid = (Integer)taxsts[1];
			taxdescription = (String)taxsts[2];
			
			
			
			String taxsumQuery = "select sum(amount) from billdetails where taxdetailsid='"+taxdetid+"' and  billno='"+billno+"' and (packageid is not null or  serviceid is not null) and (taxstructureid is not null and taxdetailsid is not null)";
			List taxsumlist = session.createSQLQuery(taxsumQuery).list();
			Iterator taxsumiterator = taxsumlist.iterator();
			while (taxsumiterator.hasNext()) {
				Object taxsum = (Object) taxsumiterator.next();
				percent = (BigDecimal) taxsum;
			}
			
			map.put("pname", taxdescription);
			map.put("pqty", "");
			map.put("prate", percent.toString());
			ListServices.add(map);
		}
	
	}
	
		
		
		
		
	/*String taxQuery = "select Distinct (taxdetailsid) from billdetails where billno='"+billno+"' and (packageid is not null or  serviceid is not null) and (taxstructureid is not null and taxdetailsid is not null)";
	List taxlist = session.createSQLQuery(taxQuery).list();
	Iterator taxiterator = taxlist.iterator();
	while (taxiterator.hasNext()) {
		Object tid = (Object) taxiterator.next();
		taxid = (Integer) tid;
		String taxstructureQuery = "select taxdescription from taxstructuredetails where taxdetailsid='"+taxid+"'";
		List taxstructurelist = session.createSQLQuery(taxstructureQuery).list();
		Iterator taxstructureiterator = taxstructurelist.iterator();
		while (taxstructureiterator.hasNext()) {
			Map<String,String> map=new LinkedHashMap<String,String>();
			Object taxsts = (Object) taxstructureiterator.next();
			taxdescription = (String)taxsts;
			
			String taxsumQuery = "select sum(amount) from billdetails where taxdetailsid='"+taxid+"' and  billno='"+billno+"' and (packageid is not null or  serviceid is not null) and (taxstructureid is not null and taxdetailsid is not null)";
			List taxsumlist = session.createSQLQuery(taxsumQuery).list();
			Iterator taxsumiterator = taxsumlist.iterator();
			while (taxsumiterator.hasNext()) {
				Object taxsum = (Object) taxsumiterator.next();
				percent = (BigDecimal) taxsum;
			}
			
			map.put("pname", taxdescription);
			map.put("pqty", "");
			map.put("prate", percent.toString());
			ListServices.add(map);
		}
	
	}	*/
	
	
	/*emap1.put("pname", "");
	emap1.put("pqty", "");
	emap1.put("prate", "");
	ListServices.add(emap1);*/
	
	String grandTotalQuery = "select sum(amount) from billdetails where billno='"+billno+"' and discountid IS Null" ;
	List grandTotallist = session.createSQLQuery(grandTotalQuery).list();
	Map<String,String> map=new LinkedHashMap<String,String>();
	Iterator grandTotaliterator = grandTotallist.iterator();
	while (grandTotaliterator.hasNext()) {
		Object grandTota = (Object) grandTotaliterator.next();
		grandtotal = (BigDecimal) grandTota;
	}
	
	String discountTotalQuery = "select COALESCE(sum( amount), 0) from billdetails where billno='"+billno+"' and discountid IS Not Null" ;
	List discountTotallist = session.createSQLQuery(discountTotalQuery).list();
	Iterator discountTotaliterator = discountTotallist.iterator();
	while (discountTotaliterator.hasNext()) {
		Object grandTota = (Object) discountTotaliterator.next();
		grandtotal = grandtotal.subtract((BigDecimal) grandTota);
	}
	map.put("pname", "GRAND TOTAL");
	map.put("pqty", "");
	map.put("prate", grandtotal.toString());
	ListServices.add(map);	
		
 List<Object> finalList=new ArrayList<Object>();
 finalList.add(listPrintBill);
 finalList.add(ListServices);
 return finalList;
}


@Override
public List getAllServiceDetails(String idArr, String nameArr, String srvOrPkg, String appointTime) {
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	StringTokenizer st = new StringTokenizer(idArr);
	List idList = new ArrayList();

	while (st.hasMoreTokens()) {
		idList.add(Integer.parseInt(st.nextToken(",")));
	}

	Criteria criteria = null;

	if (srvOrPkg.equals("service")) {
		criteria = session.createCriteria(Servicemaster.class, "servicemaster");
		criteria.setFetchMode("servicemaster", FetchMode.JOIN);
		criteria.add(Restrictions.in("serviceid", idList));
		criteria.addOrder(Order.asc("serviceid"));

	} else if (srvOrPkg.equals("package")) {
		criteria = session.createCriteria(Packagemaster.class, "packagemaster");
		criteria.setFetchMode("packagemaster", FetchMode.JOIN);
		criteria.add(Restrictions.in("packageid", idList));
		criteria.addOrder(Order.asc("packageid"));
	} else {
	}

	List resList = criteria.list();
	List mainList = new ArrayList();
	Iterator ie = resList.iterator();

	while (ie.hasNext()) {
		if (srvOrPkg.equals("service")) {
			Servicemaster srv = (Servicemaster) ie.next();
			Map<String, List> mmap = abcFunction(srv, null, "", appointTime);
			Set mapSet = (Set) mmap.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) mapIterator.next();
				String keyValue = (String) mapEntry.getKey();
				List value = (List) mapEntry.getValue();
				appointTime = keyValue;
				mainList.add(value);
			}
		} else if (srvOrPkg.equals("package")) {
			Packagemaster pkgMaster = (Packagemaster) ie.next();
			Iterator iee = pkgMaster.getPackageserviceses().iterator();
			while (iee.hasNext()) {
				Packageservices pksss = (Packageservices) iee.next();
				Servicemaster srv = pksss.getServicemaster();
				Map<String, List> mmap = abcFunction(srv, pkgMaster.getPackageid(), pkgMaster.getPackagename(),
						appointTime);
				Set mapSet = (Set) mmap.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while (mapIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) mapIterator.next();
					String keyValue = (String) mapEntry.getKey();
					List value = (List) mapEntry.getValue();
					appointTime = keyValue;
					mainList.add(value);
				}
			}
		} else {
		}
	}
	tx.commit();
	session.close();
	return mainList;
}

public Map<String, List> abcFunction(Servicemaster srv, Integer pkgId, String pkgName, String appointTime) {
	Map<String, List> returnMap = new HashMap();
	List serviceList = new ArrayList();
	if (pkgId == null)
		serviceList.add("");
	else
		serviceList.add(pkgId);
	serviceList.add(pkgName);
	serviceList.add(srv.getServiceid());
	serviceList.add(srv.getServicename());
	List roomsList = new ArrayList();
	Iterator ie1 = srv.getServiceroommasters().iterator();
	while (ie1.hasNext()) {
		Serviceroommaster sr = (Serviceroommaster) ie1.next();
		Roommaster rm = new Roommaster();
		rm.setRoomid(sr.getRoommaster().getRoomid());
		rm.setRoomno(sr.getRoommaster().getRoomno());
		roomsList.add(rm);
	}
	ie1.remove();
	serviceList.add(roomsList);

	List staffsList = new ArrayList();
	ie1 = srv.getServicestaffmasters().iterator();
	while (ie1.hasNext()) {
		Servicestaffmaster srvStaff = (Servicestaffmaster) ie1.next();
		Staffmaster sm = new Staffmaster();
		sm.setStaffid(srvStaff.getStaffmaster().getStaffid());
		sm.setStaffname(srvStaff.getStaffmaster().getStaffname());
		staffsList.add(sm);
	}
	ie1.remove();

	serviceList.add(staffsList);
	serviceList.add(appointTime);
	String departTime = null;
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	try {
		Date timeObj = timeFormat.parse(appointTime);
		Date timeObject = timeFormat.parse(srv.getTotalservicetime());
		long sumOfTimes = timeObj.getTime() + timeObject.getTime();
		departTime = timeFormat.format(new Date(sumOfTimes));
		//departTime = timeFormat.format(new Date(sumOfTimes + 1000*60));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	serviceList.add(srv.getPreparetime());
	serviceList.add(srv.getServicetime());
	serviceList.add(srv.getWaitingtime());
	serviceList.add(srv.getCleaningtime());
	serviceList.add(departTime);

	
	
	Criteria criteria = sessionFactory.openSession().createCriteria(Serviceratemaster.class, "servicerates");
	criteria.createAlias("servicerates.servicemaster", "servicemaster");
	criteria.add(Restrictions.eq("servicemaster.serviceid", srv.getServiceid()));
	criteria.add(Restrictions.le("applicabledate", new Date()));
	// criteria.setProjection(Projections.property("servicecost"));
	criteria.addOrder(Order.desc("updateddate"));
	criteria.setMaxResults(1);
	Serviceratemaster serviceratemaster = (Serviceratemaster) criteria.uniqueResult();
	Double serviceRate = serviceratemaster.getServicecost().doubleValue();

	criteria = sessionFactory.openSession().createCriteria(Taxstructuredetails.class, "taxDetails");
	criteria.createAlias("taxDetails.taxstructuremaster", "taxmaster");
	criteria.add(Restrictions.eq("taxmaster.sno", serviceratemaster.getTaxstructuremaster().getSno()));

	List<Taxstructuredetails> taxdetailsList = criteria.list();
	double tax = 0.0;
	Iterator ie = taxdetailsList.iterator();
	while (ie.hasNext()) {
		Taxstructuredetails taxstructuredetails = (Taxstructuredetails) ie.next();
		double taxPercent = taxstructuredetails.getPercent().doubleValue();
		tax += (serviceRate * taxPercent) / 100;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	String serviceCost = String.format("%.2f", serviceRate);
	String taxAmount = String.format("%.2f", tax);
	serviceList.add(serviceCost);
	serviceList.add(taxAmount);
	ie1 = srv.getServicestocksmasters().iterator();
	List serviceStockList = new ArrayList();
	while (ie1.hasNext()) {
		List stockList = new ArrayList();
		Servicestocksmaster srvStkMaster = (Servicestocksmaster) ie1.next();
		stockList.add(srvStkMaster.getStockmaster().getStockid());
		stockList.add(srvStkMaster.getStockmaster().getStockname());
		stockList.add(srvStkMaster.getStockmaster().getWarninglevel());
		stockList.add(srvStkMaster.getStockmaster().getReorderlevel());
		stockList.add(srvStkMaster.getStockusage());
		stockList.add(srvStkMaster.getStockmaster().getUnitprice());
		stockList.add(srvStkMaster.getStockmaster().getAvailable());
		stockList.add(srvStkMaster.getStockmaster().getAvailable().doubleValue() - srvStkMaster.getStockusage().doubleValue());
		serviceStockList.add(stockList);
	}
	serviceList.add(serviceStockList);
	returnMap.put(departTime, serviceList);
	return returnMap;
}

@Override
public int bookAppointment(Appointment appointment, String schAppointTime) {
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	int result = 0;
	try {
		Integer appointmentId = (Integer) session.createQuery("select max(appointmentid) from Appointment").uniqueResult();
		if (appointmentId == null)
			appointmentId = 0;
		appointment.setAppointmentid(appointmentId + 1);
		appointment.setUpdateddate(new Date());
		appointment.setUpdatedby("Maneendra");
		appointment.setUpdatedip("23.23.23.23");
		appointment.setValid(true);
		Statusmaster statusmaster = new Statusmaster();
		statusmaster.setStatusid(1);
		appointment.setStatusmaster(statusmaster);
		
		session.save(appointment);

		// schAppointTime += ":00";

		Iterator ie = appointment.getAppointmentservicedetailses().iterator();
		while (ie.hasNext()) {
			Appointmentservicedetails appointmentservicedetails = (Appointmentservicedetails) ie.next();

			int serviceId = appointmentservicedetails.getServicemaster().getServiceid();
			
			String totalServiceTime = session.createQuery("select totalservicetime from Servicemaster where serviceid = " + serviceId).uniqueResult().toString();

			appointmentservicedetails.setSchappointtime(schAppointTime);
			appointmentservicedetails.setSchappointdate(appointment.getAppointmentdate());
			
			String schDepartTime = null;

			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			try {
				Date timeObj = timeFormat.parse(schAppointTime);
				Date timeObject = timeFormat.parse(totalServiceTime);
				long sumOfTimes = timeObj.getTime() + timeObject.getTime();
				schDepartTime = timeFormat.format(new Date(sumOfTimes));

				schAppointTime = schDepartTime;

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// return date5;

			appointmentservicedetails.setSchdeparttime(schDepartTime);
			appointmentservicedetails.setSchdepartdate(appointment.getAppointmentdate());
			appointmentservicedetails.setAppointment(appointment);
			
			appointmentservicedetails.setStatusmaster(statusmaster);
			
			if (appointmentservicedetails.getPackagemaster().getPackageid() == 0)
				appointmentservicedetails.setPackagemaster(null);

			appointmentservicedetails.setUpdateddate(new Date());
			appointmentservicedetails.setUpdatedby("Maneendra");
			appointmentservicedetails.setUpdatedip("23.23.23.23");
			appointmentservicedetails.setValid(true);
			session.save(appointmentservicedetails);

			Criteria criteria = session.createCriteria(Servicestocksmaster.class, "servicestockmaster");
			criteria.createAlias("servicestockmaster.servicemaster", "servicemaster");
			criteria.add(Restrictions.eq("servicemaster.serviceid", serviceId));

			List resList = criteria.list();

			Iterator ie1 = resList.iterator();

			while (ie1.hasNext()) {
				Appointmentstockconsumption appointmentstockconsumption = new Appointmentstockconsumption();
				Servicestocksmaster servicestocksmaster = (Servicestocksmaster) ie1.next();

				appointmentstockconsumption.setAppointment(appointment);

				Integer stkConsumptionId = (Integer) session.createQuery("select max(appointmentstockid) from Appointmentstockconsumption").uniqueResult();
				if (stkConsumptionId == null)
					stkConsumptionId = 0;
				appointmentstockconsumption.setAppointmentstockid(stkConsumptionId + 1);

				appointmentstockconsumption.setPackagemaster(appointmentservicedetails.getPackagemaster());

				appointmentstockconsumption.setServicemaster(appointmentservicedetails.getServicemaster());
				appointmentstockconsumption.setStockmaster(servicestocksmaster.getStockmaster());
				appointmentstockconsumption.setStockusage(servicestocksmaster.getStockusage());

				BigDecimal unitPrice = servicestocksmaster.getStockmaster().getUnitprice();
				BigDecimal stkUsage = servicestocksmaster.getStockusage();
				Double price = stkUsage.doubleValue() * unitPrice.doubleValue();

				appointmentstockconsumption.setStockvalue(new BigDecimal(price));
				appointmentstockconsumption.setUpdateddate(new Date());
				appointmentstockconsumption.setUpdatedby("Maneendra");
				appointmentstockconsumption.setUpdatedip("23.23.23.23");
				appointmentstockconsumption.setValid(true);
				session.save(appointmentstockconsumption);
			}

		}
		
		result = appointment.getAppointmentid();
	} catch (Exception e) {
		result = 0;
		if (tx != null)
			tx.rollback();
		e.printStackTrace();
	}
	tx.commit();
	session.close();
	return result;
}

	@Override
	public String subtractTimes(String startTime, String endTime) {
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
     	timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
     	
     	String resultTime = null;
     	
		try {
			Date startTimeObj = timeFormat.parse(startTime);
			Date endTimeObj = timeFormat.parse(endTime);
			long res = endTimeObj.getTime() - startTimeObj.getTime();
			resultTime = timeFormat.format(new Date(res));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resultTime;
	}
	
	@Override
	public String addTimes(String startTime, String endTime) {
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
     	timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
     	
     	String resultTime = null;
     	
		try {
			Date startTimeObj = timeFormat.parse(startTime);
			Date endTimeObj = timeFormat.parse(endTime);
			long res = endTimeObj.getTime() + startTimeObj.getTime();
			resultTime = timeFormat.format(new Date(res));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resultTime;
	}


	@Override
	public List<Servicemaster> getServices() {
		Session session = sessionFactory.openSession();
		Transaction txTransaction = session.beginTransaction();
		List<Servicemaster> servlist = session.createSQLQuery("select serviceid, servicename from servicemaster order by serviceid").list();
		txTransaction.commit();
		session.close();
		return servlist;
	}

	@Override
	public List getPackages() {
		
		List list=new LinkedList();
		Session session = sessionFactory.openSession();
		List packagesList = session.createSQLQuery("select packageid, packagename from packagemaster order by packageid").list();
		Iterator itr = packagesList.iterator();
		MyDto dto=new MyDto();
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   Integer id = (Integer) obj[0]; //SERVICE assumed as int
		   String name = (String) obj[1];
		   
		   dto.setId(id);
		   dto.setName(name);
		  
		}	
		
		 list.add(dto);
		
		session.close();
		return list;
	}

	@Override
	public String savePreBillDiscount(String discountreason, String discountpercent, String discountamount) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		try{
			Discountmaster discountMaster = new Discountmaster();
			discountMaster.setDiscountreason(discountreason);
			discountMaster.setValid(true);
			discountMaster.setUpdatedby("Kranthi");
			discountMaster.setUpdateddate(new Date());
			discountMaster.setUpdatedip(Ipaddress.getIpAddress());
			if(!(discountamount.isEmpty()))
			{
				BigDecimal amount = new BigDecimal(discountamount);
				discountMaster.setDiscount(amount);
				discountMaster.setDiscounttype("Amount");
			}
			if(!(discountpercent.isEmpty()))
			{
				BigDecimal amount = new BigDecimal(discountpercent);
				discountMaster.setDiscount(amount);
				discountMaster.setDiscounttype("Percent");
			}
			Criteria criteriasno = session.createCriteria(Discountmaster.class);
			criteriasno.setProjection(Projections.max("discountid"));
			Integer discountid = (Integer) criteriasno.uniqueResult();
			if(discountid==null)
			{discountid=0;}
			discountMaster.setDiscountid(++discountid);
			session.save(discountMaster);
			transaction.commit();
		}
		catch(Exception e) {
			logger.error("Exception raised in savePreBillDiscount method:", e);
		}
		
		finally {
			session.close();
		}
		return null;
	}
	
	@Override
	public List getAppointmentGuestDetails(int guestId)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List resList = new ArrayList();
		List guestDetailsList = session.createQuery("select noofvisits, revenue, lastvisitdate from Guestmaster where guestid = "+guestId).list();
		Iterator ie = guestDetailsList.iterator();
		while(ie.hasNext())
		{
			Object[] values = (Object[]) ie.next();
			Integer visits = (Integer) values[0];
			BigDecimal revenue = (BigDecimal) values[1];
			Date visitDate = (Date) values[2];
			String lastVisitDate = "";
			if(visitDate != null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				lastVisitDate = sdf.format(visitDate);
			}
			resList.add(visits);
			resList.add(revenue);
			resList.add(lastVisitDate);
			List guestComplaintsList = session.createQuery("select complaints from Guesthistorycomplaints where guestid = "+guestId+" and lastvisitdate = '"+visitDate+"'").list();
			
			System.out.println("#####################"+guestComplaintsList.size());
			
			if(guestComplaintsList.size() == 0){
				
				
				resList.add(0);
				
			}
			
			else{
				String complaints = "<table>";
				for(int i = 0; i < guestComplaintsList.size(); i++)
					complaints += "<tr><td>"+(i+1)+". "+guestComplaintsList.get(i)+"</td></tr>";
				complaints += "</table>";
				resList.add(complaints);
			}
		}
		tx.commit();
		session.close();
		return resList;
	}
}