package com.leonia.wellness.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Dropdownmaster;
//import com.leonia.wellness.entity.Customers;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Statemaster;
//import com.leonia.wellness.entity.RegisterUser;
import com.leonia.wellness.idao.ICustomersDAO;

@Repository
public class CustomersDAOImpl implements ICustomersDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(CustomersDAOImpl.class);
	@Override
	public void saveCustomerRegistration(Guestmaster guestMaster) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Integer  guestid = (Integer)session.createSQLQuery("select max(guestid) from guestmaster").list().get(0);
		if(guestid==null)
		{
			guestid=0;
		}
		guestMaster.setGuestid(guestid+1);
		guestMaster.setValid(true);
		guestMaster.setUpdatedby("raghava");
		guestMaster.setUpdateddate(new Date());
		guestMaster.setUpdatedip("172.22.1.25");
		session.save(guestMaster);
		transaction.commit();
		session.close();
	}

	public List<Guestmaster> getAppointements() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Guestmaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("guestid"));
		criteria.setFetchMode("dropdowndetailsByGenderid", FetchMode.EAGER);
		List<Guestmaster> ls = criteria.list();
		//List<Guestmaster> ls = session.createCriteria(Guestmaster.class).list();
		session.close();
		return ls;
	}

	@Override
	public List getGuestSearch(String guests) {
		
		Session sessionObj = sessionFactory.openSession();
		sessionObj.beginTransaction();
		Criteria crit = sessionObj.createCriteria(Guestmaster.class);
		if(guests!=null && !(guests.isEmpty()))
		{
			crit.add(Restrictions.ilike("name",guests,MatchMode.ANYWHERE));
		}
		crit.add(Restrictions.eq("valid", true));
		crit.addOrder(Order.asc("guestid"));
		crit.setFetchMode("dropdowndetailsByGenderid", FetchMode.EAGER);
		
		List list = crit.list();
	    sessionObj.close();
		return list;
		
	}

	@Override
	public void saveGuestDetails(Guestmaster guestmaster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> getSegment() {
		Session session = sessionFactory.openSession();
		List<Segmentmaster> list = null;
		Map<Integer, String> segmap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Segmentmaster.class).list();
			
			for (Segmentmaster segment : list) {
				segmap.put(segment.getSegmentid(), segment.getSegmentname());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return segmap;
	}

	@Override
	public Map<Integer, String> getCorporate() {
		Session session = sessionFactory.openSession();
		List<Corporatemaster> list = null;
		Map<Integer, String> corporatemap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Corporatemaster.class).list();
			
			for (Corporatemaster corporate : list) {
				corporatemap.put(corporate.getCorporateid(), corporate.getCorporatename());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return corporatemap;
	}

	@Override
	public Map<Integer, String> getBusinessSource() {
		Session session = sessionFactory.openSession();
		List<Businesssourcemaster> list = null;
		Map<Integer, String> businessmap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Businesssourcemaster.class).list();
			
			for (Businesssourcemaster business : list) {
				businessmap.put(business.getBusinesssourceid(), business.getSourcedescription());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return businessmap;
	}

	@Override
	public Map<Integer, String> getBillingInformation() {
		Session session = sessionFactory.openSession();
		List<Billinginstructionmaster> list = null;
		Map<Integer, String> billimap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Billinginstructionmaster.class).list();
			
			for (Billinginstructionmaster billing : list) {
				billimap.put(billing.getBillinginstructionid(), billing.getInstructiondescription());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return billimap;
	}

	@Override
	public Map<Integer, String> getCity() {
		Session session = sessionFactory.openSession();
		List<Citymaster> list = null;
		Map<Integer, String> citymap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Citymaster.class).list();
			
			for (Citymaster city : list) {
				citymap.put(city.getCityid(), city.getCityname());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return citymap;
	}

	@Override
	public Map<Integer, String> getState() {
		Session session = sessionFactory.openSession();
		List<Statemaster> list = null;
		Map<Integer, String> statemap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Statemaster.class).list();
			
			for (Statemaster state : list) {
				statemap.put(state.getStateid(), state.getStatename());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return statemap;
	}

	@Override
	public Map<Integer, String> getCountry() {
		Session session = sessionFactory.openSession();
		List<Countrymaster> list = null;
		Map<Integer, String> countrymap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Countrymaster.class).list();
			
			for (Countrymaster country : list) {
				countrymap.put(country.getCountryid(), country.getCountryname());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return countrymap;
	}

	@Override
	public Map<Integer, String> getTitle() {
		Session session = sessionFactory.openSession();
		Map<Integer, String> titlemap = new HashMap<>();
		try{
			Dropdownmaster dropdownmaster = (Dropdownmaster) session.get(Dropdownmaster.class,2);
			Set<Dropdowndetails> dropdowndetailsSet = dropdownmaster.getDropdowndetailses();
			for (Dropdowndetails dropdowndetails : dropdowndetailsSet) {
				titlemap.put(dropdowndetails.getDropdowndetailsid(), dropdowndetails.getDescription());
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in getGender method:", e);
		}
		
		finally {
			session.close();
		}
		return titlemap;
	}
	
	@Override
	public List<Guestmaster> getSearchCustomerDOB(String month, String fromdate, String todate) {
		Session session=sessionFactory.openSession();
		String query="SELECT guestid,name ,dob,mobile,email,lastvisitdate,noofvisits FROM guestmaster WHERE EXTRACT(MONTH FROM dob)='"+month+"' AND EXTRACT(Day FROM dob)>='"+fromdate+"' And EXTRACT(Day FROM dob) <='"+ todate+"'  order by  dob";
		List<Guestmaster> gmlist = session.createSQLQuery(query).list();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+gmlist.size()+"@@@@@@@@@@@@@");
		return gmlist;
		
		}

		@Override
		public List getSearchCustomerWedding(String month, String fromdate, String todate) {
		Session session=sessionFactory.openSession();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@123");
		String query="SELECT guestid,name ,weddingday,mobile,email,lastvisitdate,noofvisits FROM guestmaster WHERE EXTRACT(MONTH FROM weddingday)='"+month+"' AND EXTRACT(Day FROM weddingday)>='"+fromdate+"' And EXTRACT(Day FROM weddingday) <='"+ todate+"' order by weddingday";
		
		

		List<Guestmaster> grmwdlist=session.createSQLQuery(query).list();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+grmwdlist.size()+"@@@@@@@@@@@@@");
		return grmwdlist;
	}
		
		@Override
		public List<Appointmentservicedetails> getGuestServicesSearch(String fromdate, String todate, String[] serviceName) {
			
			Session session=sessionFactory.openSession();
			List<Appointmentservicedetails> appointmentServiceList = null;
			try{
				Criteria criteria = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
				criteria.createAlias("appointmentservice.appointment", "appointment");
				criteria.createAlias("appointmentservice.servicemaster", "service");
				criteria.createAlias("appointment.guestmaster", "guestmaster");
				if(fromdate!=null && !(fromdate.isEmpty()))
				{
					Date fromDate = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					fromDate = sdf.parse(fromdate);
					criteria.add(Restrictions.ge("schappointdate", fromDate));
				}
				
				if(todate!=null && !(todate.isEmpty()))
				{
					Date toDate = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					toDate = sdf.parse(todate);
					criteria.add(Restrictions.le("schappointdate", toDate));
				}
				
				if(serviceName!=null)
				{
					Disjunction disjunction = Restrictions.disjunction();
					for (String serviceid : serviceName) {
						
						Integer serviceId = Integer.parseInt(serviceid); 
						disjunction.add(Restrictions.eq("service.serviceid", serviceId));
					}
					criteria.add(disjunction);
				}
				criteria.add(Restrictions.eq("valid", true));
				appointmentServiceList = criteria.list();
			}
			catch(Exception e) {
				logger.error("Exception raised in getGuestServicesSearch method:", e);
			}
			
			finally {
				
				session.close();
			}
			return appointmentServiceList;
		}

		@Override
		public List getGuestEmailList() {
			Session session = sessionFactory.openSession();
		List<Guestmaster> list = new ArrayList<>();
		
		try {
			
			
			Criteria criteria = session.createCriteria(Guestmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			Order order = Order.asc("guestid");
			criteria.addOrder(order);
			list = criteria.list();
		
			
		} catch (Exception e) {
			logger.error("Exception raised in getGuestEmailList() method:", e);
		}

		finally {
			session.close();
		}
		return list;
			
		}	
		
	}