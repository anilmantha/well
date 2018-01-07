package com.leonia.wellness.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Dropdownmaster;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.entity.Reasondetails;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Statemaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.idao.ILoadOnStartupDAO;

@Repository
public class LoadOnStartupDAOImpl implements ILoadOnStartupDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger.getLogger(LoadOnStartupDAOImpl.class);
	
	@Override
	public List<String> getStockGroup() {
		Session session = sessionFactory.openSession();
		List<String> stockGroupnames=null;
		try {
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			stockGroupnames = criteria.setProjection(Projections.property("stockgroupname")).list();
			} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:",e);
		}
		finally{session.close();}
		return stockGroupnames;
	}

	@Override
	public Set<Dropdowndetails> getstockType() {
		Session session = sessionFactory.openSession();
		Set<Dropdowndetails> dropdowndetailses=null;
		try{
			  Criteria criteria = session.createCriteria(Dropdownmaster.class);
			 criteria.add(Restrictions.eq("dropdownmasterid", 3));
			 criteria.setFetchMode("dropdowndetailses", FetchMode.EAGER);
			List<Dropdownmaster> dropdownmaster = criteria.list();
			dropdowndetailses = dropdownmaster.get(0).getDropdowndetailses();
		}
		catch(Exception e) {
			logger.error("Exception raised in getStockType method:", e);
		}
		
		finally {
			session.close();
		}
		
		
		return dropdowndetailses;
	}

	@Override
	public List<Roommaster> getRooms() {
		Session session = sessionFactory.openSession();
		List<Roommaster> roomList = session.createQuery("select roomid,description from Roommaster").list();
		Iterator ie  = roomList.iterator();
		while(ie.hasNext()){
			Object[] as = (Object[]) ie.next();
			int rmid = (Integer) as[0];
			String rmname = (String) as[1];
			
		}
		
		
		
		return roomList;
	}

	@Override
	public List<Servicemaster> getServices() {
		List<Servicemaster> services = null;
		Session session = sessionFactory.openSession();
		try {
			services = session.createCriteria(Servicemaster.class).list();
		} catch (Exception e) {
			logger.error("Exception Raised in getStaffNames method:", e);
		}
		finally {session.close();}
		return services;
	}

	@Override
	public List<String> getStockDetails() {
		List<String> stockNames = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Stockmaster.class,"stockmaster");
			criteria.createAlias("stockmaster.dropdowndetails", "dropdowndetails");
			criteria.add(Restrictions.eq("dropdowndetails.dropdowndetailsid", 6));
			stockNames = criteria.setProjection(Projections.property("stockname")).list();
		} catch (Exception e) {
			logger.error("Exception Raised in getStaffNames method:", e);
		}
		finally {session.close();}
		return stockNames;
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
	public Map<Integer, String> getGender() {
		Session session = sessionFactory.openSession();
		Map<Integer, String> gendermap = new HashMap<>();
		try{
			Dropdownmaster dropdownmaster = (Dropdownmaster) session.get(Dropdownmaster.class,1);
			Set<Dropdowndetails> dropdowndetailsSet = dropdownmaster.getDropdowndetailses();
			for (Dropdowndetails dropdowndetails : dropdowndetailsSet) {
				gendermap.put(dropdowndetails.getDropdowndetailsid(), dropdowndetails.getDescription());
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in getGender method:", e);
		}
		
		finally {
			session.close();
		}
		return gendermap;
	}

	@Override
	public Map<Integer, String> getSupplier() {
		Session session = sessionFactory.openSession();
		List<Suppliermaster> list = null;
		Map<Integer, String> suppliermap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Suppliermaster.class).list();
			
			for (Suppliermaster supplier : list) {
				suppliermap.put(supplier.getSupplierid(), supplier.getSuppliername());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return suppliermap;
	}

	@Override
	public Map<Integer, String> getManufacturer() {
		Session session = sessionFactory.openSession();
		List<Manufacturermaster> list = null;
		Map<Integer, String> manufacturemap = new HashMap<>();
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(Manufacturermaster.class).list();
			
			for (Manufacturermaster manufacturer : list) {
				manufacturemap.put(manufacturer.getManufacturerid(), manufacturer.getManufacturername());
			}
			beginTransaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:", e);
		}

		finally {
			session.close();
		}
		return manufacturemap;
	}

	@Override
	public Map<Integer, String> getStockType() {
		Session session = sessionFactory.openSession();
		Map<Integer, String> stockTypemap = new HashMap<>();
		try{
			Dropdownmaster dropdownmaster = (Dropdownmaster) session.get(Dropdownmaster.class,3);
			Set<Dropdowndetails> dropdowndetailsSet = dropdownmaster.getDropdowndetailses();
			for (Dropdowndetails dropdowndetails : dropdowndetailsSet) {
				stockTypemap.put(dropdowndetails.getDropdowndetailsid(), dropdowndetails.getDescription());
			}
		}
		catch(Exception e) {
			logger.error("Exception raised in getGender method:", e);
		}
		
		finally {
			session.close();
		}
		return stockTypemap;
	}
	
	@Override
	public List<Staffmaster> getStaffNames() {
		Session session = sessionFactory.openSession();
		List<Staffmaster> staffList = session.createQuery("select staffid,staffname from Staffmaster").list();
		session.close();
		return staffList;
	}

	@Override
	public List<Paymodemaster> paymodeDetails() {
		Session session = sessionFactory.openSession();
	    List<Paymodemaster> paymodeList =null;
	    try{
	         Criteria criteria = session.createCriteria(Paymodemaster.class);
	         criteria.add(Restrictions.eq("valid", true));
	         paymodeList = criteria.list();
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return paymodeList;
	}

	@Override
	public List<Creditlistmaster> corporateDetails() {
		Session session = sessionFactory.openSession();
	    List<Creditlistmaster> creditMasterList =null;
	    try{
	         /*Criteria criteria = session.createCriteria(Corporatemaster.class,"corporatemaster");
	          criteria.createAlias("corporatemaster.creditlistmaster", "creditlistmaster");
	         criteria.setFetchMode("corporatemaster", FetchMode.EAGER);
	         criteria.add(Restrictions.eq("valid", true));
	         //criteria.add(Restrictions.eq("corporatemaster.valid", true));
	         creditMasterList = criteria.list();*/
	         Criteria criteria = session.createCriteria(Creditlistmaster.class);
	         criteria.setFetchMode("corporatemaster", FetchMode.EAGER);
	         criteria.add(Restrictions.eq("valid", true));
	         creditMasterList = criteria.list();
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return creditMasterList;
	}

	@Override
	public List<Membershipmaster> memberDetails() {
		
		Session session = sessionFactory.openSession();
	    List<Membershipmaster> membershipmasterList =null;
	    try{
	         Criteria criteria = session.createCriteria(Membershipmaster.class);
	         criteria.add(Restrictions.eq("valid", true));
	         membershipmasterList = criteria.list();
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return membershipmasterList;
	}

	@Override
	public List<Departmentmaster> departmentDetails() {
		Session session = sessionFactory.openSession();
	    List<Departmentmaster> departmentMasterList =null;
	    try{
	         Criteria criteria = session.createCriteria(Departmentmaster.class);
	         criteria.add(Restrictions.eq("valid", true));
	         departmentMasterList = criteria.list();
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return departmentMasterList;
	}

	@Override
	public List<Cardtypemaster> getCardTypes() {
		Session session = sessionFactory.openSession();
	    List<Cardtypemaster> cardTypeMasterList =new ArrayList<>();
	    try{
	         Criteria criteria = session.createCriteria(Cardtypemaster.class);
	         criteria.add(Restrictions.eq("valid", true));
	         cardTypeMasterList = criteria.list();
	         
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return cardTypeMasterList;
	}

	@Override
	public List<Appointment> getGuestList() {
		Session session = sessionFactory.openSession();
	    List<Appointment> guestList =null;
	    try{
	         Criteria criteria = session.createCriteria(Appointment.class,"appointment");
	         criteria.createAlias("appointment.guestmaster", "guestmaster");
	         criteria.createAlias("appointment.statusmaster", "statusmaster");
	         criteria.add(Restrictions.eq("statusmaster.statusid", 2));
	         criteria.add(Restrictions.eq("valid", true));
	          ProjectionList projectionsList = Projections.projectionList();
	          projectionsList.add(Projections.property("guestmaster.guestid"));
	          projectionsList.add(Projections.property("guestmaster.name"));
	          criteria.setProjection(projectionsList);	
	         guestList = criteria.list();
	   }catch(Exception e){
	    	logger.error("Exception raised in getGuestList method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return guestList;
	}

	@Override
	public Map<Integer, String> getMonth() {
		Session session = sessionFactory.openSession();
		Map<Integer, String> monthmap = new TreeMap<>();
		try{
			
			Dropdownmaster dropdownmaster = (Dropdownmaster)session.get(Dropdownmaster.class, 4);
			Set<Dropdowndetails> dropdowndetailsSet = dropdownmaster.getDropdowndetailses();
			for (Dropdowndetails dropdowndetails : dropdowndetailsSet) {
				monthmap.put(dropdowndetails.getDropdowndetailsid(), dropdowndetails.getDescription());
		       
			}
			}
		catch(Exception e) {
			logger.error("Exception raised in getGender method:", e);
		}
		
		finally {
			session.close();
		}
		return monthmap;
	}

	@Override
	public List<Reasondetails> getReasonsList() {
		Session session = sessionFactory.openSession();
		List<Reasondetails> reasonsList=null;
		try {
			Criteria criteria = session.createCriteria(Reasondetails.class,"reasondetails");
			criteria.createAlias("reasondetails.reasonmaster", "reasonmaster");
			criteria.add(Restrictions.eq("reasonmaster.reasonid", 1));
			criteria.add(Restrictions.eq("reasonmaster.valid", true));
			reasonsList = criteria.list();
			} catch (Exception e) {
			logger.error("Exception raised in getStockGroup method:",e);
		}
		finally{session.close();}
		return reasonsList;
	}

}
