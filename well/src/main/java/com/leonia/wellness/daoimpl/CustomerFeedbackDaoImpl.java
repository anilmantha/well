package com.leonia.wellness.daoimpl;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.entity.Sbumaster;
import com.leonia.wellness.entity.Statusmaster;
import com.leonia.wellness.entity.Ticketmaster;
import com.leonia.wellness.entity.Tickettypemaster;
import com.leonia.wellness.idao.ICustomerFeedbackDao;
import com.leonia.wellness.util.Ipaddress;
import com.sun.swing.internal.plaf.basic.resources.basic;

import sun.print.resources.serviceui;


@Repository
public class CustomerFeedbackDaoImpl implements ICustomerFeedbackDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(CustomerFeedbackDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Responsemaster> getResponseDetails() {

		List<Responsemaster> responselist = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Responsemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.addOrder(Order.asc("responseorder"));
			responselist = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in  getResponseDetails Method:", e);
		} finally {
			session.close();
		}
		return responselist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questionmaster> getQuestionInfo(Integer formId) {
		
			Session session = sessionFactory.openSession();

			List<Questionmaster> questionlist = null;

			try {
				Criteria criteria = session.createCriteria(Questionmaster.class,"question");
				criteria.createAlias("question.formmaster", "form");
				criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
				criteria.add(Restrictions.eq("form.formid", formId));
				criteria.add(Restrictions.eq("question.valid", true));
				criteria.addOrder(Order.asc("questionid"));
				questionlist = criteria.list();
			} catch (Exception e) {
				logger.error("Exception raised in getQuestionInfo Method:", e);
			} finally {
				session.close();
			}
			return questionlist;
		}

	
	@Override
	public void generateTicket(Ticketmaster ticketMaster) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			Criteria criteria1 = session.createCriteria(Ticketmaster.class);
			    criteria1.setProjection(Projections.max("ticketno"));
				Integer id = (Integer) criteria1.uniqueResult();
				if(id==null)
				{id=0;}
				ticketMaster.setTicketno(id+1);
				ticketMaster.setUpdatedby("prabha");
				ticketMaster.setUpdateddate(date);
				ticketMaster.setUpdatedip(Ipaddress.getIpAddress());
				session.save(ticketMaster);
			    transaction.commit();
              	} catch (Exception e) {
			logger.error("Exception raised in saveresponse method:", e);

		}
		finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer questionCount(Integer formid) {
		Session session = sessionFactory.openSession();
		List<Questionmaster> questionlist = null;

	        Integer  questioncount =null;
		try {

			Criteria criteria = session.createCriteria(Questionmaster.class,"question");
			criteria.createAlias("question.formmaster","formmaster");
			
			
			criteria.add(Restrictions.eq("formmaster.formid", formid));
			 questionlist = criteria.list();
			 questioncount =  questionlist.size();

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		
		return  questioncount ;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Departmentmaster> getDepartmentNames() {
		Session session = sessionFactory.openSession();
		 List<Departmentmaster> departmentlist=null;
		try {

			Criteria criteria = session.createCriteria(Departmentmaster.class);
			departmentlist=criteria.list();
			

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		
		return departmentlist;
		
	}
   @Override
	public Ticketmaster openTicket(int ticketno) {
		Session session = sessionFactory.openSession();
		Ticketmaster ticketNo=null;
		
		try {
			Criteria criteria = session.createCriteria(Ticketmaster.class);
			criteria.add(Restrictions.eq("ticketno",ticketno));
			ticketNo=(Ticketmaster) criteria.uniqueResult();
			
		} catch (Exception e) {
			logger.error("Exception raised in getQuestionInfo Method:", e);
		} finally {
			session.close();
		}
		return ticketNo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype,String sourcetype,Integer records) {
		
		Session session = sessionFactory.openSession();
		int recordsPerPage=records*5-5;
		List<Ticketmaster> ticketlist = null;
		try {
			Criteria criteria = session.createCriteria(Ticketmaster.class,"ticketmaster").setFirstResult(recordsPerPage).setMaxResults(5);
			criteria.addOrder(Order.asc("sno"));
			criteria.createAlias("ticketmaster.departmentmaster", "departmentmaster");
			criteria.createAlias("ticketmaster.statusmaster", "statusmaster");
			criteria.createAlias("ticketmaster.tickettypemaster", "tickettypemaster");
			
			if(date1!=null) {
				criteria.add(Restrictions.ge("ticketmaster.ticketdate",date1)).setFirstResult(recordsPerPage).setMaxResults(5);
			}
			
			if(date2!=null) {
				criteria.add(Restrictions.le("ticketmaster.ticketdate",date2)).setFirstResult(recordsPerPage).setMaxResults(5);
			}
			
			if(departmentid!=null && !(departmentid.equals("Select"))) {
			     criteria.add(Restrictions.eq("departmentmaster.departmentid",Integer.parseInt(departmentid)));
			    
			}
			if(statustype!=null && !(statustype.equals("Select"))) {
		    	 criteria.add(Restrictions.eq("statusmaster.statusid", Integer.parseInt(statustype)));
		    	
			}
			 if(sourcetype!=null && !(sourcetype.equals("Select"))) {
		    	 criteria.add(Restrictions.eq("tickettypemaster.tickettypeid", Integer.parseInt(sourcetype)));
		    	
			}
			criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
			criteria.setFetchMode("tickettypemaster", FetchMode.EAGER);
			criteria.setFetchMode("statusmaster", FetchMode.EAGER);
			criteria.setFetchMode("responsemaster", FetchMode.EAGER);
				
			ticketlist = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in TicketList Method:", e);
		} finally {
			session.close();
		}
		return ticketlist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Statusmaster> getStatus() {
		Session session = sessionFactory.openSession();
		 List<Statusmaster> statuslist=null;
		try {

			Criteria criteria = session.createCriteria(Statusmaster.class);
			criteria.add(Restrictions.ge("statusid", 6));
			criteria.add(Restrictions.le("statusid", 7));
			
			statuslist=criteria.list();
			} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		
		return statuslist;
		

	}


	@Override
	public Feedbackformmaster getFeedbackFormDetailsById(Integer feedbackFormId) {
		Session session = sessionFactory.openSession();
		 Feedbackformmaster feedbackFormMaster=null;
		try {

			Criteria createCriteria = session.createCriteria(Feedbackformmaster.class);
			createCriteria.add(Restrictions.eq("feedbackformid", feedbackFormId));
			createCriteria.setFetchMode("formmaster", FetchMode.EAGER);
			createCriteria.setFetchMode("guestmaster", FetchMode.EAGER);
			feedbackFormMaster = (Feedbackformmaster) createCriteria.uniqueResult();
			

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		return feedbackFormMaster;
	}
	

	@Override
	public void saveResponse(Feedbackdetails feedbackdetails) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			    feedbackdetails.setValid(true);
				feedbackdetails.setUpdatedby("prabha");
				feedbackdetails.setUpdateddate(date);
				feedbackdetails.setUpdatedip(Ipaddress.getIpAddress());
			
			    session.save(feedbackdetails);
			    transaction.commit();
              	} catch (Exception e) {
			logger.error("Exception raised in saveresponse method:", e);

		}
		finally {
			session.close();
		}
		return;
     }

	@Override
	public List<Tickettypemaster> getTicketType() {
		Session session = sessionFactory.openSession();
		 List<Tickettypemaster> tickettypelist=null;
		try {

			Criteria criteria = session.createCriteria(Tickettypemaster.class);
			tickettypelist=criteria.list();
		

		} catch (Exception e) {
			logger.error("Exception raised in get tickettype method" + e);

		} finally {
			session.close();
		}
		
		return tickettypelist;
	}

	@Override
	public Ticketmaster getTicketDetails(Integer ticketno) {
		Session session = sessionFactory.openSession();
		Ticketmaster ticketNo=null;
		
		try {
			Criteria criteria = session.createCriteria(Ticketmaster.class);
			criteria.addOrder(Order.asc("sno"));
			criteria.add(Restrictions.eq("ticketno",ticketno));
			criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
			criteria.setFetchMode("prioritymaster", FetchMode.EAGER);
			criteria.setFetchMode("statusmaster", FetchMode.EAGER);
			criteria.setFetchMode("staffmaster", FetchMode.EAGER);
			criteria.setFetchMode("tickettypemaster", FetchMode.EAGER);
			criteria.setFetchMode("responsemaster", FetchMode.EAGER);
			ticketNo=(Ticketmaster) criteria.uniqueResult();
			
		} catch (Exception e) {
			logger.error("Exception raised in getQuestionInfo Method:", e);
		} finally {
			session.close();
		}
		return ticketNo;
	}

	@Override
	public void saveTicketClosing(Ticketmaster ticketmaster) {
		Session session = sessionFactory.openSession();
		try {
			Date date = new Date();
			Transaction transaction = session.beginTransaction();
			Criteria createCriteria = session.createCriteria(Ticketmaster.class);
			createCriteria.add(Restrictions.eq("ticketno", ticketmaster.getTicketno()));
			Ticketmaster ticket = (Ticketmaster) createCriteria.uniqueResult();
			ticket.setStatusmaster(ticketmaster.getStatusmaster());
			ticket.setRemarks(ticketmaster.getRemarks());
			ticket.setValid(false);
			//ticket.setCloseddate(date);
			ticket.setUpdateddate(date);
			ticket.setUpdatedby("Surya");
			ticket.setUpdatedip(Ipaddress.getIpAddress());
			session.update(ticket);
		    transaction.commit();
	              	} catch (Exception e) {
				logger.error("Exception raised in save ticket closing method:", e);

			}
			finally {
				session.close();
			}
			}

	@Override
	public List<Ticketmaster> ticketCount() {
		Session session = sessionFactory.openSession();
		List<Ticketmaster> ticketlist = null; 
       
		try {
			Criteria criteria = session.createCriteria(Ticketmaster.class);
			
			ticketlist = criteria.list();
	         
            
		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		
		return  ticketlist ;
	}

	@Override
	public List<Ticketmaster> getOpenlist() {
		Session session = sessionFactory.openSession();
		List<Ticketmaster> ticketmaster = null;
		try {

			Criteria criteria = session.createCriteria(Ticketmaster.class,"tm");
			criteria.createAlias("tm.statusmaster", "sm");
			criteria.add(Restrictions.eq("sm.statusid",6));
			ticketmaster = criteria.list();

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
			
		return ticketmaster;
	}

	@Override
	public List<Ticketmaster> getCloselist() {
		Session session = sessionFactory.openSession();
		List<Ticketmaster> ticketmaster = null;
		try {

			Criteria criteria = session.createCriteria(Ticketmaster.class,"tm");
			criteria.createAlias("tm.statusmaster", "sm");
			criteria.add(Restrictions.eq("sm.statusid",7));
			ticketmaster = criteria.list();

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
			
		return ticketmaster;
	}

	

	@Override
	public List<Departmentmaster> getDeptData() {
		Session session = sessionFactory.openSession();
		List<Departmentmaster> deptmaster = null;
		try {

			Criteria criteria = session.createCriteria(Departmentmaster.class);
			deptmaster=criteria.list();
			
			
		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
			
		return deptmaster;
	}

	@Override
	public List<Ticketmaster> getOpentCount(int i) {
		Session session = sessionFactory.openSession();
		List<Ticketmaster> tcktmaster = null;
		try {

			Criteria criteria = session.createCriteria(Ticketmaster.class,"ticketmaster");
			criteria.createAlias("ticketmaster.departmentmaster", "departmentmaster");
			criteria.createAlias("ticketmaster.statusmaster", "statusmaster");
			criteria.add(Restrictions.eq("departmentmaster.departmentid",i));
			criteria.add(Restrictions.eq("statusmaster.statusid",6));
			tcktmaster = criteria.list();
			
			
		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		return tcktmaster;
	}

	@Override
	public List<Ticketmaster> openCount() {
		Session session = sessionFactory.openSession();
		List<Ticketmaster> ticketmaster = null;
		try {

			Criteria criteria = session.createCriteria(Ticketmaster.class,"tm");
			criteria.createAlias("tm.statusmaster", "sm");
			criteria.add(Restrictions.eq("sm.statusid",6));
			ticketmaster = criteria.list();

		} catch (Exception e) {
			logger.error("Exception raised in " + e);

		} finally {
			session.close();
		}
		return ticketmaster;
	}
	@Override
	public Pagination ticketsCount(String pageName, Date date1, Date date2, String departmentId, String statustype,
			String sourcetype) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		
		if(pageName == null){
			Criteria criteria = session.createCriteria(Ticketmaster.class);
			//criteria.add(Restrictions.eq("valid", true));
			Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
			int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
			Pagination pagination=Pagination.getInstance();
			pagination.setTotalRecs(totalRecs);
			pagination.setNoOfPages(noOfPages);
			tx.commit();
			session.close();
			return pagination;
		}
		else{
			Criteria criteria = session.createCriteria(Ticketmaster.class,"ticketmaster");
			criteria.createAlias("ticketmaster.departmentmaster", "departmentmaster");
			criteria.createAlias("ticketmaster.statusmaster", "statusmaster");
			criteria.createAlias("ticketmaster.tickettypemaster", "tickettypemaster");
			
			if(date1!=null) {
				criteria.add(Restrictions.ge("ticketmaster.ticketdate",date1));
			}
			
			if(date2!=null) {
				criteria.add(Restrictions.le("ticketmaster.ticketdate",date2));
			}
			
			if(departmentId!=null && !(departmentId.equals("Select"))) {
			     criteria.add(Restrictions.eq("departmentmaster.departmentid",Integer.parseInt(departmentId)));
			    
			}
			if(statustype!=null && !(statustype.equals("Select"))) {
		    	 criteria.add(Restrictions.eq("statusmaster.statusid", Integer.parseInt(statustype)));
		    	
			}
			 if(sourcetype!=null && !(sourcetype.equals("Select"))) {
		    	 criteria.add(Restrictions.eq("tickettypemaster.tickettypeid", Integer.parseInt(sourcetype)));
		    	
			}
			criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
			criteria.setFetchMode("tickettypemaster", FetchMode.EAGER);
			criteria.setFetchMode("statusmaster", FetchMode.EAGER);
			criteria.setFetchMode("responsemaster", FetchMode.EAGER);
			
			Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
			int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
			Pagination pagination=Pagination.getInstance();
			pagination.setTotalRecs(totalRecs);
			pagination.setNoOfPages(noOfPages);
			tx.commit();
			session.close();
			return pagination;
			
		}
	}
		@Override
		public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype,String sourcetype) {
			
			Session session = sessionFactory.openSession();
			List<Ticketmaster> ticketlist = null;
			try {
				Criteria criteria = session.createCriteria(Ticketmaster.class,"ticketmaster");
				criteria.createAlias("ticketmaster.departmentmaster", "departmentmaster");
				criteria.createAlias("ticketmaster.statusmaster", "statusmaster");
				criteria.createAlias("ticketmaster.tickettypemaster", "tickettypemaster");
				
				if(date1!=null) {
					criteria.add(Restrictions.ge("ticketmaster.ticketdate",date1));
				}
				
				if(date2!=null) {
					criteria.add(Restrictions.le("ticketmaster.ticketdate",date2));
				}
				
				if(departmentid!=null && !(departmentid.equals("Select"))) {
					 
				     criteria.add(Restrictions.eq("departmentmaster.departmentid",Integer.parseInt(departmentid)));
				   
				    
				}
				if(statustype!=null && !(statustype.equals("Select"))) {
			    	 criteria.add(Restrictions.eq("statusmaster.statusid", Integer.parseInt(statustype)));
			    	
				}
				 if(sourcetype!=null && !(sourcetype.equals("Select"))) {
			    	 criteria.add(Restrictions.eq("tickettypemaster.tickettypeid", Integer.parseInt(sourcetype)));
			    	
				}
				criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
				criteria.setFetchMode("tickettypemaster", FetchMode.EAGER);
				criteria.setFetchMode("statusmaster", FetchMode.EAGER);
				criteria.setFetchMode("responsemaster", FetchMode.EAGER);
					
				ticketlist = criteria.list();
			} catch (Exception e) {
				logger.error("Exception raised in TicketList Method:", e);
			} finally {
				session.close();
			}
			return ticketlist;

		}

		@Override
		public Feedbackformmaster feedbackNotGivenList(Date date,Integer billno) {
			Session session = sessionFactory.openSession();
			Feedbackformmaster feedbacklist = null;
			try {
				Criteria criteria = session.createCriteria(Feedbackformmaster.class,"feedbackformmaster");
				criteria.createAlias("feedbackformmaster.billmaster", "billmaster");
				criteria.add(Restrictions.eq("billmaster.billno",billno));
				feedbacklist=(Feedbackformmaster) criteria.uniqueResult();				
			}
			finally {
				session.close();
			}
			
			return feedbacklist;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Billmaster> billSettlementList(Date date) {
			Session session = sessionFactory.openSession();
			 List<Billmaster> billSettledlist=null;
			 try {
					Criteria criteria = session.createCriteria(Billmaster.class);
					criteria.add(Restrictions.eq("billdate",date));
					criteria.setFetchMode("guestmaster", FetchMode.EAGER);
					billSettledlist = criteria.list();
			 }
			 finally {
					session.close();
				}
			return billSettledlist;
		}

		@Override
		public Integer saveFeedbackFormOfManual(Integer formid, Integer guestId, Integer billno, Integer sbuid) {
			
			Session session = sessionFactory.openSession();
			Transaction beginTransaction = session.beginTransaction();
			Integer feedbackformid = null;
			try {
				Date date = new Date();
				Feedbackformmaster feedbackformmaster = new Feedbackformmaster();
				Guestmaster guestmaster = new Guestmaster();
				guestmaster.setGuestid(guestId);
				Sbumaster sbumaster = new Sbumaster();
				sbumaster.setSbuid(sbuid);
				Formmaster formmaster = new Formmaster();
				formmaster.setFormid(formid);
				Billmaster billmaster = new Billmaster();
				billmaster.setBillno(billno);
				feedbackformmaster.setGuestmaster(guestmaster);
				feedbackformmaster.setBillmaster(billmaster);
				feedbackformmaster.setSbumaster(sbumaster);
				feedbackformmaster.setFormmaster(formmaster);
				feedbackformmaster.setFormsentdate(date);
				feedbackformmaster.setUpdateddate(date);
				feedbackformmaster.setUpdatedip(Ipaddress.getIpAddress());
				feedbackformmaster.setUpdatedby("Prabha");
				feedbackformmaster.setValid(true);
				feedbackformmaster.setAutomatedfeedback(false);
				Criteria criteria1 = session.createCriteria(Feedbackformmaster.class);
			    criteria1.setProjection(Projections.max("feedbackformid"));
				Integer id = (Integer) criteria1.uniqueResult();
				if(id==null)
				{id=0;}
				else
				{
					id++;
				}
				feedbackformmaster.setFeedbackformid(id);
				session.save(feedbackformmaster);
				beginTransaction.commit();
				feedbackformid = id;
			} catch (Exception e) {
				feedbackformid=null;
				logger.error("Exception raised in saveFeedbackFormOfManual" + e);

			} finally {
				session.close();
			}
			return feedbackformid;
		}

		@Override
		public Feedbackformmaster getFeedbackFormMasterByBillno(Integer billno) {
			Session session = sessionFactory.openSession();
			 Feedbackformmaster feedbackformMaster=null;
			try {

				Criteria createCriteria = session.createCriteria(Feedbackformmaster.class);
				
				createCriteria.setFetchMode("billmaster", FetchMode.EAGER);
				feedbackformMaster = (Feedbackformmaster) createCriteria.uniqueResult();
				

			} catch (Exception e) {
				logger.error("Exception raised in " + e);

			} finally {
				session.close();
			}
			return feedbackformMaster;
		}

		@Override
		public Object updatefeedbackformmaster(Integer feedbackformId) {
			Session session = sessionFactory.openSession();
			Transaction beginTransaction = session.beginTransaction();
			try {
				Criteria criteria = session.createCriteria(Feedbackformmaster.class);
				criteria.add(Restrictions.eq("feedbackformid", feedbackformId));
				criteria.add(Restrictions.eq("valid", true));
				Feedbackformmaster feedbackformmaster = (Feedbackformmaster) criteria.uniqueResult();
				feedbackformmaster.setValid(false);
				feedbackformmaster.setUpdateddate(new Date());
				feedbackformmaster.setFormreceiveddate(new Date());
				feedbackformmaster.setUpdatedby("Prabha");
				feedbackformmaster.setFeedbackreceived(true);
				feedbackformmaster.setUpdatedip(Ipaddress.getIpAddress());
				session.update(feedbackformmaster);
				beginTransaction.commit();
			} catch (Exception e) {
				logger.error("Exception raised in " + e);

			} finally {
				session.close();
			}
			return null;
		}
			
		}