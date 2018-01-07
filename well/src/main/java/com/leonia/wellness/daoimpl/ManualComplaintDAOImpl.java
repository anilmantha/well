package com.leonia.wellness.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Appointmentservicedetails;
import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Guesthistorycomplaints;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Prioritymaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Statusmaster;
import com.leonia.wellness.entity.Ticketmaster;
import com.leonia.wellness.entity.Tickettypemaster;
import com.leonia.wellness.idao.IManualComplaintDAO;
@Repository
public class ManualComplaintDAOImpl implements IManualComplaintDAO{

	
	private static final Logger logger =Logger.getLogger(ManualComplaintDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List getBillsByBillDate(Date fromDate, Date toDate, String guestName) {
		Session session = sessionFactory.openSession();
		List billMasterList = new ArrayList<>();
		try{
		Criteria criteria = session.createCriteria(Billmaster.class,"billmaster");
		criteria.createAlias("billmaster.guestmaster", "guestmaster");
		Order asc = Order.asc("billno");
		criteria.addOrder(asc);
	    criteria.add(Restrictions.eq("valid",true));
	    criteria.add(Restrictions.eq("settled", true));
	    criteria.add(Restrictions.le("billdate", toDate));
	    criteria.add(Restrictions.ge("billdate", fromDate));
	     if(guestName!=null){
		   criteria.add(Restrictions.ilike("guestmaster.name", guestName, MatchMode.ANYWHERE));
	     }
	     ProjectionList projectionList = Projections.projectionList();
	    projectionList.add(Projections.property("billno"));
	    projectionList.add(Projections.property("billdate"));
	    projectionList.add(Projections.property("amount"));
	    projectionList.add(Projections.property("guestmaster.name"));
	    projectionList.add(Projections.property("guestmaster.guestid"));
	    criteria.setProjection(projectionList);		
	    billMasterList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in getBillsByBillDate"+e);
		}
		finally{
		session.close();
		}
		return billMasterList;
	}
	@Override
	public Billmaster getSelectedBill(Integer billNo) {
		Session session = sessionFactory.openSession();
		Billmaster billMaster = null;
		try{
		    Criteria criteria = session.createCriteria(Billmaster.class,"billmaster");
		    criteria.add(Restrictions.eq("valid",true));
		    criteria.add(Restrictions.eq("settled",true));
		    criteria.add(Restrictions.eq("billno",billNo));
		    criteria.setFetchMode("guestmaster", FetchMode.EAGER);
		    billMaster = (Billmaster)criteria.uniqueResult();
		}catch(Exception e){
			logger.error("Exception raised in generatedBillDetails method:",e);
		}
		finally{
			session.close();
		}
		return billMaster;
	}
	@Override
	public String saveManualComplaint(Integer guestId, Integer billNo, Integer departmentId, String complaint,
			String remarks) {
		Session session = sessionFactory.openSession();
		String msg=null;
		
		try{
		Transaction transaction = session.beginTransaction();
		Date date =new Date();
		 List list =session.createSQLQuery("select appointmentid from billdetails where billno="+billNo+" group by appointmentid").list();
	     Iterator iterator = list.iterator();
	     List<Integer> appointmentId = new ArrayList<>();//list for storing appointmentid's
	      while(iterator.hasNext()){
	    	 Object next = iterator.next();
	    	 Integer id= (Integer)next;
	    	 appointmentId.add(id);
	    }
	     
	      //*getting single appointmentid and appointmentdate who are paying money that guest details 
	      
	     
	     /* getting single appointmentid and appointmentdate who are paying money that guest details */
	      Guestmaster gm = new Guestmaster();
	     Integer appointmentIdSingle=null;
	     Date appointmentDate =null;
	     for(int i=0;i<appointmentId.size();i++){
	         List list2 = session.createSQLQuery("select appointmentid,appointmentdate from appointment where appointmentid="+appointmentId.get(i)+" and guestid="+guestId).list();
	         Iterator iterator2 = list2.iterator();
	         while(iterator2.hasNext()){
	        	 Object next = iterator2.next();
	        	 Object[] oo = (Object[])next;
	        	 appointmentIdSingle = (Integer)oo[0];
	        	 appointmentDate = (Date)oo[1];
	         }
	     }
	     Guesthistorycomplaints ghc = new Guesthistorycomplaints();
	     ghc.setComplaints(complaint);
	     ghc.setRemarks(remarks);
	     ghc.setLastvisitdate(date);
	     Appointment ament = new Appointment();
	     ament.setAppointmentid(appointmentIdSingle);
	     ghc.setAppointment(ament);
	    
	     gm.setGuestid(guestId);
	     ghc.setGuestmaster(gm);
	     ghc.setUpdatedby("raghava");
	     ghc.setValid(true);
	     ghc.setUpdatedip("172.22.1.27");
	     ghc.setUpdateddate(date);
         Criteria criteria = session.createCriteria(Guesthistorycomplaints.class);
		 criteria.setProjection(Projections.max("guesthistorycomplaintid"));
		    Integer id = (Integer) criteria.uniqueResult();
		    if(id==null){
			   ghc.setGuesthistorycomplaintid(1);
		    }else{
			   ghc.setGuesthistorycomplaintid(id+1);
		    }
		   session.save(ghc);
		   
		   
		   Ticketmaster tg = new Ticketmaster();
		   Departmentmaster dm = new Departmentmaster();
		   dm.setDepartmentid(departmentId);
		   tg.setDepartmentmaster(dm);
		  
		   tg.setGuestmaster(gm);
		   tg.setValid(true);
		   tg.setUpdatedby("raghava");
		   tg.setUpdateddate(date);
		   tg.setUpdatedip("172.22.1.27");
		   Tickettypemaster ttm = new Tickettypemaster();
		   ttm.setTickettypeid(2);
		   tg.setTickettypemaster(ttm);
		   Billmaster bm =new Billmaster();
		   bm.setBillno(billNo);
		   tg.setBillmaster(bm);
		   tg.setComplaint(complaint);
		   Statusmaster sm =new Statusmaster();
		   sm.setStatusid(6);
		   tg.setStatusmaster(sm);
		   tg.setRemarks(remarks);
		   tg.setTicketdate(date);
		   Prioritymaster pm =new Prioritymaster();
		   pm.setPriorityid(1);
		   tg.setPrioritymaster(pm);
		   
		 
		   
		   Criteria createCriteria = session.createCriteria(Ticketmaster.class);
	        createCriteria.setProjection(Projections.max("ticketno"));
	        Integer guestHisServId = (Integer)createCriteria.uniqueResult();
	        if(guestHisServId==null){
	        	tg.setTicketno(1);
	        }else{
	        	tg.setTicketno(guestHisServId+1);
	        }
		   
		   
		   
		   session.save(tg);
		   transaction.commit();
		  msg="Complaint is successfully saved";
		}catch(Exception e){
			logger.error("Exception raised in saveManualComplaint method"+e);
			msg="Complaint is not saved";
		}
		finally{
			session.close();
		}
	     
		return msg;
	}
	@Override
	public String saveManualComplaintByAdmin(Integer empId,Integer departmentId, String complaint, String remarks) {
		Session session = sessionFactory.openSession();
		String msg=null;
		try{
		Transaction transaction = session.beginTransaction();
		Date date  =new Date();
		Ticketmaster tg = new Ticketmaster();
		   Departmentmaster dm = new Departmentmaster();
		   dm.setDepartmentid(departmentId);
		   tg.setDepartmentmaster(dm);
		   tg.setValid(true);
		   tg.setUpdatedby("raghava");
		   tg.setUpdateddate(date);
		   tg.setUpdatedip("172.22.1.27");
		  Tickettypemaster ttm =new Tickettypemaster();
		  ttm.setTickettypeid(3);
		  tg.setTickettypemaster(ttm);
		  Staffmaster sm = new Staffmaster();
		  sm.setStaffid(empId);
		  tg.setStaffmaster(sm);
		  tg.setComplaint(complaint);
		  Statusmaster ssm = new Statusmaster();
		  ssm.setStatusid(6);
		  tg.setStatusmaster(ssm);
		  tg.setRemarks(remarks);
		  tg.setTicketdate(date);
		  Prioritymaster pm =new Prioritymaster();
		  pm.setPriorityid(1);
		  tg.setPrioritymaster(pm);
		 
		
		   Criteria createCriteria = session.createCriteria(Ticketmaster.class);
	        createCriteria.setProjection(Projections.max("ticketno"));
	        Integer id = (Integer)createCriteria.uniqueResult();
	        if(id==null){
	        	tg.setTicketno(1);
	        }else{
	        	tg.setTicketno(id+1);
	        }
		   
		   
		   session.save(tg);
		   transaction.commit();
		   msg="Complaint is successfully saved";
		}catch(Exception e){
			logger.error("Exception raised in saveManualComplaint method"+e);
			msg="Complaint is not saved";
		}
		finally{
			session.close();
		}
	     
		return msg;
	}
	@Override
	public Map getInServiceGuestDetails() {
		Session session = sessionFactory.openSession();
	    Map<Integer,String> map =new HashMap<>();
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
	         List guestList = criteria.list();
	         Iterator iterator = guestList.iterator();
	         while(iterator.hasNext()){
	        	 Object next = iterator.next();
	        	 Object []objArray =(Object[])next;
	        	 Integer guestId =(Integer)objArray[0];
	        	 String guestName =(String)objArray[1];
	        	 map.put(guestId,guestName);
	         }
	   }catch(Exception e){
	    	logger.error("Exception raised in getGuestList method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return map;
	}
	@Override
	public String saveManualComplaintByInHouseGuest(Integer inHouseGuestId, Integer serviceId,Integer departmentId, String complaint,
			String remarks) {
		Session session = sessionFactory.openSession();
		String msg=null;
		try{
		Transaction transaction = session.beginTransaction();
		Date date =new Date();
		Guestmaster gm = new Guestmaster();
		   gm.setGuestid(inHouseGuestId);
		   List<Integer> appointmentIdList = new ArrayList<>();
		   List<Date> appointmentDateList = new ArrayList<>();
	     //Integer appointmentIdSingle=null;
	     //Date appointmentDate =null;
	     List list2 = session.createSQLQuery("select appointmentid,appointmentdate from appointment where guestid="+inHouseGuestId+" and statusid=2").list();
	     Iterator iterator2 = list2.iterator();
	     while(iterator2.hasNext()){
	        	 Object next = iterator2.next();
	        	 Object[] oo = (Object[])next;
	        	 Integer appointmentId = (Integer)oo[0];
	        	 Date appointmentDate = (Date)oo[1];
	        	 appointmentIdList.add(appointmentId);
	        	 appointmentDateList.add(appointmentDate);
	         }
	     Integer appointmentIdSingle =null;
	     Date appointmentdDate=null;
	     for(int i=0;i<appointmentIdList.size();i++){
	     List list = session.createSQLQuery("select appointmentid,actstartdate from appointmentservicedetails where appointmentid="+appointmentIdList.get(i)+" and serviceid="+serviceId).list();
	     Iterator iterator = list.iterator();
	     while(iterator.hasNext()){
	        	 Object next = iterator.next();
	        	 Object[] oo = (Object[])next;
	        	 appointmentIdSingle = (Integer)oo[0];
	        	  appointmentdDate = (Date)oo[1];
	        	 
	         }
	     }
	     System.out.println("^^&&**$$$$$$$$$$$$$3"+appointmentdDate);
	     Guesthistorycomplaints ghc = new Guesthistorycomplaints();
	     ghc.setComplaints(complaint);
	     ghc.setRemarks(remarks);
	     ghc.setLastvisitdate(date);
	     Appointment ament = new Appointment();
	     ament.setAppointmentid(appointmentIdSingle);
	     ghc.setAppointment(ament);
	    
	   
	     ghc.setGuestmaster(gm);
	     ghc.setUpdatedby("raghava");
	     ghc.setValid(true);
	     ghc.setUpdatedip("172.22.1.27");
	     ghc.setUpdateddate(date);
         Criteria criteria = session.createCriteria(Guesthistorycomplaints.class);
		 criteria.setProjection(Projections.max("guesthistorycomplaintid"));
		    Integer id = (Integer) criteria.uniqueResult();
		    if(id==null){
			   ghc.setGuesthistorycomplaintid(1);
		    }else{
			   ghc.setGuesthistorycomplaintid(id+1);
		    }
		   session.save(ghc);
		   
		   
		   Ticketmaster tg = new Ticketmaster();
		   
		   tg.setGuestmaster(gm);
		   Departmentmaster dm = new Departmentmaster();
		   dm.setDepartmentid(departmentId);
		   tg.setDepartmentmaster(dm);
		  
		   tg.setGuestmaster(gm);
		   tg.setValid(true);
		   tg.setUpdatedby("raghava");
		   tg.setUpdateddate(date);
		   tg.setUpdatedip("172.22.1.27");
		   Tickettypemaster ttm = new Tickettypemaster();
		   ttm.setTickettypeid(4);
		   tg.setTickettypemaster(ttm);
		  Servicemaster sermas = new Servicemaster();
		  sermas.setServiceid(serviceId);
		   tg.setComplaint(complaint);
		   Statusmaster sm =new Statusmaster();
		   sm.setStatusid(6);
		   tg.setStatusmaster(sm);
		   tg.setRemarks(remarks);
		   tg.setTicketdate(date);
		   Prioritymaster pm =new Prioritymaster();
		   pm.setPriorityid(1);
		   tg.setPrioritymaster(pm);
		   
		 
		   
		   Criteria createCriteria = session.createCriteria(Ticketmaster.class);
	        createCriteria.setProjection(Projections.max("ticketno"));
	        Integer guestHisServId = (Integer)createCriteria.uniqueResult();
	        if(guestHisServId==null){
	        	tg.setTicketno(1);
	        }else{
	        	tg.setTicketno(guestHisServId+1);
	        }
		   
		   
		   
		   session.save(tg);
		   transaction.commit();
		  msg="Complaint is successfully saved";
		}catch(Exception e){
			logger.error("Exception raised in saveManualComplaint method"+e);
			msg="Complaint is not saved";
		}
		finally{
			session.close();
		}
	     
		return msg;
		
	}
	@Override
	public Map getEmployeeDetails() {
		Session session = sessionFactory.openSession();
	     Map<Integer,String> employeeMap = new HashMap<>();  
	    try{
	         Criteria criteria = session.createCriteria(Staffmaster.class);
	         criteria.add(Restrictions.eq("valid", true));
	         ProjectionList projectionsList = Projections.projectionList();
	          projectionsList.add(Projections.property("staffid"));
	          projectionsList.add(Projections.property("staffname"));
	          criteria.setProjection(projectionsList);	
	          List staffList = criteria.list();
	         Iterator iterator = staffList.iterator();
	         while(iterator.hasNext()){
	        	 Object next = iterator.next();
	        	 Object []objArray =(Object[])next;
	        	 Integer staffId =(Integer)objArray[0];
	        	 String staffName =(String)objArray[1];
	        	 employeeMap.put(staffId,staffName);
	         }
	   }catch(Exception e){
	    	logger.error("Exception raised in paymodemaster method:",e);
	    }
	    finally{
	    	 session.close();	
	    }
	   return employeeMap;
	}
	@Override
	public List getEmployeeDepIdDesignation(Integer empId) {
		Session session = sessionFactory.openSession();
		List depDeslist = new ArrayList<>();
		try{
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Staffmaster.class,"staffmaster");
			criteria.createAlias("departmentmaster", "dm");
			criteria.add(Restrictions.eq("staffid", empId));
			ProjectionList projectionList =Projections.projectionList();
			projectionList.add(Projections.property("dm.departmentname"));
			projectionList.add(Projections.property("desgination"));
			criteria.setProjection(projectionList);
			List list = criteria.list();
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				Object next = iterator.next();
				Object objArray[] =(Object[])next;
				String depName =(String)objArray[0];
				String des =(String)objArray[1];
				depDeslist.add(depName);
				depDeslist.add(des);
				
			}
			transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in getEmployeeDepIdDesignation"+e);
		}
		finally{
			session.close();
		}
		return depDeslist;
	}
	@Override
	public List getGuestservices(Integer guestId) {
		Session session = sessionFactory.openSession();
		List serList = new ArrayList<>();
		try{
			Transaction transaction = session.beginTransaction();
			
			
			
			Criteria criteria = session.createCriteria(Appointment.class,"appointment");
			criteria.createAlias("appointment.guestmaster", "guestmaster");
			criteria.add(Restrictions.eq("guestmaster.guestid", guestId));
			criteria.createAlias("appointment.statusmaster", "statusmaster");
			criteria.add(Restrictions.eq("statusmaster.statusid", 2));
			criteria.setProjection(Projections.property("appointmentid"));
			Integer  appId = (Integer)criteria.uniqueResult();
			Criteria criteria2 = session.createCriteria(Appointmentservicedetails.class,"appointmentservice");
			criteria2.createAlias("appointmentservice.statusmaster", "statusmaster");
			criteria2.createAlias("appointmentservice.servicemaster", "servicemaster");
			criteria2.createAlias("appointmentservice.appointment", "appointment");
			criteria2.add(Restrictions.eq("appointment.appointmentid", appId));
			Criterion status1 = Restrictions.eq("statusmaster.statusid", 3);
			Criterion status2 = Restrictions.eq("statusmaster.statusid", 2);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(status1);
			disjunction.add(status2);
			criteria2.add(disjunction);
			ProjectionList proList = Projections.projectionList();
			proList.add(Projections.property("servicemaster.serviceid"));
			proList.add(Projections.property("servicemaster.servicename"));
			criteria2.setProjection(proList);
			List list = criteria2.list();
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				Object next = iterator.next();
				Object []objArray =(Object[])next;
				Integer serId =(Integer)objArray[0];
				String serName =(String)objArray[1];
				serList.add(serId);
				serList.add(serName);
				
			}
		}catch(Exception e){
			logger.error("Exception raised in getGuestservices"+e);
		}
		finally{
			session.close();
		}
		return serList;
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

}
