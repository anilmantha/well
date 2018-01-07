package com.leonia.wellness.daoimpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.ServiceStockQuantity;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Servicestocksmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.idao.IServicesDAO;
import com.leonia.wellness.util.Ipaddress;

@Repository
public class ServicesDAOImpl implements IServicesDAO{

	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(ServicesDAOImpl.class);
	
	@Override
	public Integer getstockid(String stockname) {
		
		Session session = sessionFactory.openSession();
		List<Integer> stockid=null;
		try {
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.add(Restrictions.eq("stockname", stockname));
			criteria.setProjection(Projections.property("stockid"));
			stockid = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroupMasterId method:",e);
		}
		finally{
			session.close();
		}
		return stockid.get(0);
	}

	
	/*@Override
	public List<Services> getServices() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		
		List<Services> ls = session.createQuery("from Services").list();
		System.out.println("ls===="+ls.size());
		session.close();
		return ls;
		
	}


	@Override
	public List<Services> getServicesDetails(String service) {
Session session = sessionFactory.openSession();
		System.out.println(service);
		List<Services> lss = session.createQuery("select  preparationTime,serviceTime,waitingTime,cleaningTime, serviceCost from Services  where serviceName=?").setParameter(0, service).list();
		Iterator ie = lss.iterator();
		List le = new ArrayList();
	while(ie.hasNext())
		{
			Object[] val = (Object[])ie.next();
			String prepTime = (String) val[0];
			String srvTime = (String) val[1];
			String waitTime = (String) val[2];
			String cleanTime = (String) val[3];
			Double serviceCost = (Double) val[4];
			le.add(prepTime);
			le.add(srvTime);
			le.add(waitTime);
			le.add(cleanTime);
			le.add(serviceCost);
		}
		session.close();
		return le;
		
		
	}*/
	
	@Override
	public void saveservicestock(List<ServiceStockQuantity> objectArray) {
		
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		boolean valid=true;
		Date date = new Date();
		
		Iterator<ServiceStockQuantity> ie = objectArray.iterator();
		
		Integer stockId = null;
		Integer serviceId = null;
		Integer stockQuantity = null;
		String stockName = null;
		try {
			while(ie.hasNext()){
			Stockmaster stockmaster = new Stockmaster();
			Servicemaster servicemaster = new Servicemaster();
			Servicestocksmaster servicestockmaster = new Servicestocksmaster();
			ServiceStockQuantity se = ie.next();
			Criteria criteria = session.createCriteria(Servicestocksmaster.class);
			criteria.setProjection(Projections.max("servicestockid"));
			Integer id = (Integer) criteria.uniqueResult();
			if(id==null)
				id=0;
			stockId = se.getStockId();
			serviceId = se.getServiceId();
			stockQuantity = se.getStockQuantity();
			stockName = se.getStockName();
			System.out.println(stockId);
			if(stockId==null)
			{
				Criteria criteria2 = session.createCriteria(Stockmaster.class);
				criteria2.add(Restrictions.eq("stockname", stockName));
				criteria2.setProjection(Projections.property("stockid"));
				stockId = (Integer) criteria2.list().get(0);
			}
			
			stockmaster.setStockid(stockId);
			servicestockmaster.setStockmaster(stockmaster);
			servicestockmaster.setStockusage(new BigDecimal(stockQuantity));
			servicemaster.setServiceid(serviceId);
			servicestockmaster.setServicemaster(servicemaster);
			servicestockmaster.setServicestockid(id+1);
			servicestockmaster.setValid(valid);
			servicestockmaster.setUpdatedby("Kranthi");
			servicestockmaster.setUpdateddate(date);
			servicestockmaster.setUpdatedip(Ipaddress.getIpAddress());
			
			session.save(servicestockmaster);
			
			
	}
		} catch (Exception e) {
			logger.error("Exception raised in saveservicestock method:",e);
		}
		finally{
			beginTransaction.commit();
			session.close();
			}
	}


	@Override
	public Set<Servicestocksmaster> getStockUsage(Integer serviceId) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicemaster.class);
		criteria.add(Restrictions.eq("serviceid", serviceId));
		criteria.setFetchMode("servicestocksmasters", FetchMode.EAGER);
		Servicemaster service = (Servicemaster) criteria.uniqueResult();
		Set<Servicestocksmaster> servicestocksmasters = service.getServicestocksmasters();
		return servicestocksmasters;
	}


	@Override
	public void updateservicestock(List<ServiceStockQuantity> objectArray) {
		
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		
		Iterator<ServiceStockQuantity> ie = objectArray.iterator();
		Integer stockId = null;
		Integer serviceId = null;
		Integer stockQuantity = null;
		String stockName = null;
		try {
			while(ie.hasNext()){
			Stockmaster stockmaster = new Stockmaster();
			Servicemaster servicemaster = new Servicemaster();
			Servicestocksmaster servicestockmaster = new Servicestocksmaster();
			ServiceStockQuantity se = ie.next();
			serviceId = se.getServiceId();
			stockQuantity = se.getStockQuantity();
			stockName = se.getStockName();
			stockId = se.getStockId();
			Criteria createCriteria = session.createCriteria(Servicemaster.class);
			createCriteria.add(Restrictions.eq("serviceid", serviceId));
			createCriteria.setFetchMode("servicestocksmasters", FetchMode.EAGER);
			servicemaster= (Servicemaster) createCriteria.uniqueResult();
			Set<Servicestocksmaster> servicestocksmasters2 = servicemaster.getServicestocksmasters();
			for (Servicestocksmaster servicestocksmaster : servicestocksmasters2) {
				int stockid = servicestocksmaster.getStockmaster().getStockid();
				if(stockid==stockId)
				{
					servicestocksmaster.setStockusage(new BigDecimal(stockQuantity));
					session.update(servicestocksmaster);
				}
			}			
			
	}
		} catch (Exception e) {
			logger.error("Exception raised in saveservicestock method:",e);
		}
		finally{
			beginTransaction.commit();
			session.close();
			}
		
	}


	@Override
	public void deleteservicestock(List<ServiceStockQuantity> objectArray) {
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		
		Iterator<ServiceStockQuantity> ie = objectArray.iterator();
		Integer stockId = null;
		Integer serviceId = null;
		Integer stockQuantity = null;
		String stockName = null;
		try {
			while(ie.hasNext()){
			Stockmaster stockmaster = new Stockmaster();
			Servicemaster servicemaster = new Servicemaster();
			Servicestocksmaster servicestockmaster = new Servicestocksmaster();
			ServiceStockQuantity se = ie.next();
			serviceId = se.getServiceId();
			stockQuantity = se.getStockQuantity();
			stockName = se.getStockName();
			stockId = se.getStockId();
			Criteria createCriteria = session.createCriteria(Servicemaster.class);
			createCriteria.add(Restrictions.eq("serviceid", serviceId));
			createCriteria.setFetchMode("servicestocksmasters", FetchMode.EAGER);
			servicemaster= (Servicemaster) createCriteria.uniqueResult();
			Set<Servicestocksmaster> servicestocksmasters2 = servicemaster.getServicestocksmasters();
			for (Servicestocksmaster servicestocksmaster : servicestocksmasters2) {
				int stockid = servicestocksmaster.getStockmaster().getStockid();
				if(stockid==stockId)
				{
					servicestocksmaster.setValid(false);
					session.update(servicestocksmaster);
				}
			}			
			
	}
		} catch (Exception e) {
			logger.error("Exception raised in saveservicestock method:",e);
		}
		finally{
			beginTransaction.commit();
			session.close();
			}
	}


	@Override
	public List<Servicemaster> getServicesUnAddedStock() {
		
		Session session = sessionFactory.openSession();
		List<Servicemaster> serviceslist = new LinkedList<Servicemaster>();
		try {
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			List<Servicemaster> list = criteria.list();
			for (Servicemaster servicemaster : list) {
				if(servicemaster.getServicestocksmasters().size()==0)
				{
					serviceslist.add(servicemaster);
				}
			}
		} catch (Exception e) {
			logger.error("Exception raised in getServicesUnAddedStock method:",e);
		}
		finally{
			session.close();
			}
		return serviceslist;
	}


	@Override
	public List<Servicemaster> getServicesToBeEdited() {
		Session session = sessionFactory.openSession();
		List<Servicemaster> serviceslist = new LinkedList<Servicemaster>();
		try {
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			List<Servicemaster> list = criteria.list();
			for (Servicemaster servicemaster : list) {
				if(servicemaster.getServicestocksmasters().size()!=0)
				{
					serviceslist.add(servicemaster);
				}
			}
		} catch (Exception e) {
			logger.error("Exception raised in getServicesUnAddedStock method:",e);
		}
		finally{
			session.close();
			}
		return serviceslist;
	}
}