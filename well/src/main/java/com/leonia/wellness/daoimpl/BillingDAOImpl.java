package com.leonia.wellness.daoimpl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonia.wellness.entity.Appointment;
import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Cardtypemaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Discountmaster;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Giftvouchermaster;
import com.leonia.wellness.entity.Guesthistoryrevenue;
import com.leonia.wellness.entity.Guesthistoryservices;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Marketingvouchermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Paymodemaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.idao.IBillingDAO;
import com.leonia.wellness.util.Ipaddress;

@Repository
public class BillingDAOImpl implements IBillingDAO{
    @Autowired
	public SessionFactory sessionFactory;
    @Autowired
    private MailSender mailSender; 
    private static final Logger logger = Logger.getLogger(BillingDAOImpl.class);
	@Override
	public Guestmaster getGusetName(Integer guestid) {
		Session session = sessionFactory.openSession();
		Guestmaster guestMaster = null;
		try{
			guestMaster =(Guestmaster)session.get(Guestmaster.class, guestid);
		}catch(Exception e){
			logger.error("Exception raised in getGusetName method:",e);
		}
		finally{
			session.close();
		}
		return guestMaster;
	}
	@Override
	public String saveBillPayment(List<Billsettlement> billSettlementList,String tipamount,String discountamount) {
		Session session = sessionFactory.openSession();
		String msg =null;
		try{
		    Transaction transaction = session.beginTransaction();
		    Iterator iebill = billSettlementList.iterator();
		    Integer billNo=null;
		    Integer guestId = null;
		    Date date = new Date();
		    while(iebill.hasNext()){
			    Billsettlement billsettlement = (Billsettlement) iebill.next();
			    billNo = billsettlement.getBillmaster().getBillno();
			    guestId = billsettlement.getGuestmaster().getGuestid();
			    Criteria criteria = session.createCriteria(Billsettlement.class);
			    criteria.setProjection(Projections.max("sno"));
			    Integer id = (Integer) criteria.uniqueResult();
			    if(id==null){
				   billsettlement.setSno(1);
			    }else{
				   billsettlement.setSno(id+1);
			    }
			    billsettlement.setValid(true);
			    billsettlement.setUpdatedby("raghava");
			    billsettlement.setUpdateddate(date);
			    billsettlement.setUpdatedip("172.22.1.27");
			    session.save(billsettlement);
			    session.clear();session.flush();
			    /*----------------Updating Creditlist table begin----------*/
			      int creditlistid = billsettlement.getCreditlistmaster().getCreditlistid();
			      if(creditlistid!=0){
			    	BigDecimal amount = billsettlement.getAmount();
			    	Criteria creditlistcriteria = session.createCriteria(Creditlistmaster.class);
			    	creditlistcriteria.add(Restrictions.eq("creditlistid", creditlistid));
			    	creditlistcriteria.setProjection(Projections.property("outstandingamount"));
			    	BigDecimal outstandingamount = (BigDecimal)creditlistcriteria.uniqueResult();
			    	BigDecimal remaingBalance = outstandingamount.subtract(amount);
			    	SQLQuery query = session.createSQLQuery("update creditlistmaster set outstandingamount="+remaingBalance+" where creditlistid = "+creditlistid);
			    	query.executeUpdate();
			     }
			    /*----------------Updating Creditlist table end----------*/
			    
			    
			    /*----------------Updating Memebership table begin----------*/
			    int membershipid = billsettlement.getMembershipmaster().getMembershipid();
			    if(membershipid!=0){
			    	BigDecimal amount = billsettlement.getAmount();
			    	Criteria creditlistcriteria = session.createCriteria(Membershipmaster.class);
			    	creditlistcriteria.add(Restrictions.eq("membershipid", membershipid));
			    	creditlistcriteria.setProjection(Projections.property("outstandingamount"));
			    	BigDecimal outstandingamount = (BigDecimal)creditlistcriteria.uniqueResult();
			    	BigDecimal remaingBalance = outstandingamount.subtract(amount);
			    	SQLQuery query = session.createSQLQuery("update membershipmaster set outstandingamount="+remaingBalance+" where membershipid = "+membershipid);
			    	query.executeUpdate();
			    }
			    /*----------------Updating Memebership table end----------*/
			    
			    /*----------------Updating GiftVoucher table begin----------*/
			    int giftvoucherid = billsettlement.getGiftvouchermaster().getGiftvoucherid();
			     if(giftvoucherid!=0){
			    	SQLQuery query = session.createSQLQuery("update giftvouchermaster set valid=false where giftvoucherid = "+giftvoucherid);
			    	query.executeUpdate();
			    }
			     /*----------------Updating GiftVoucher table end----------*/
			     
			     /*----------------Updating MarketingVoucher table begin----------*/
			     int marketingvoucherid = billsettlement.getMarketingvouchermaster().getMarketingvoucherid();
			     if(marketingvoucherid!=0){
			    	SQLQuery query = session.createSQLQuery("update Marketingvouchermaster set valid=false where marketingvoucherid = "+marketingvoucherid);
			    	query.executeUpdate();
			    }
			     /*----------------Updating MarketingVoucher table end----------*/
			}
		   
		    
		   
		    Billmaster billMaster = (Billmaster)session.load(Billmaster.class,billNo);
		    BigDecimal totalAmount = billMaster.getAmount();
		    //Set<Appointment> appointments = billMaster.getGuestmaster().getAppointments();
		    if(tipamount!=""){
		    	Integer tipAmount =Integer.parseInt(tipamount);
		    	billMaster.setTipamount(new BigDecimal(tipAmount));
		    }
		    if(discountamount!=""){
		    	System.out.println(discountamount+"discountamount");
		    	Integer discountAmount =Integer.parseInt(discountamount);
		    	billMaster.setDiscountamount(new BigDecimal(discountAmount));
		    }
		    billMaster.setSettled(true);
		    billMaster.setOutstandingamount(new BigDecimal(0));
		    
		    List allappoiIdList = session.createSQLQuery("select Distinct(appointmentid) from billdetails where billno="+billNo).list();
		    List guestappList = new ArrayList<>();
		   /* Set allappoiIdset = new HashSet(allappoiIdList);
		    Iterator iterator2 = allappoiIdset.iterator();
		    while(iterator2.hasNext()){
		    	Object next = iterator2.next();
		    	Integer appid =(Integer)next;
		    	guestappList = session.createSQLQuery("select appointmentid from appointment where appointmentid="+appid+" and guestid="+guestId).list();
		    }*/
		    for(int i =0;i<allappoiIdList.size();i++){
		    	guestappList = session.createSQLQuery("select appointmentid from appointment where appointmentid="+allappoiIdList.get(i)+" and guestid="+guestId).list();
		    	
		    }
		   
		    List<Integer> appointmentId = new ArrayList<>();//list for storing appointmentid's
		    List<Integer> serviceIdList = new ArrayList<>();//list for storing serviceid's
		    List<BigDecimal> serviceAmountList = new ArrayList<>();//list for storing serviceamount(including tax)
		    List<Integer> remainderDaysList = new ArrayList<>();
		    for(int i=0;i<guestappList.size();i++){
		        List list =session.createSQLQuery("select bd.appointmentid,bd.serviceid,sum(bd.amount),sm.remainderdays from billdetails as bd,servicemaster as sm where bd.serviceid=sm.serviceid and bd.serviceid is not null and billno="+billNo+" and appointmentid="+guestappList.get(i)+" group by bd.appointmentid,bd.serviceid,sm.remainderdays").list();
		        Iterator iterator = list.iterator();
		        while(iterator.hasNext()){
		    	 Object next = iterator.next();
		    	 Object oo[] = (Object[])next;
		    	 Integer id= (Integer)oo[0];
		    	 Integer serviceId =(Integer)oo[1];
		    	 BigDecimal serviceAmount =(BigDecimal)oo[2];
		    	 Integer remainderDay =(Integer)oo[3];
		    	 appointmentId.add(id);
		    	 serviceIdList.add(serviceId);
		    	 serviceAmountList.add(serviceAmount);
		    	 remainderDaysList.add(remainderDay);
		      }
		    }
		    List<Integer> appointmentIdPack = new ArrayList<>();//list for storing appointmentid's
		    List<Integer> serviceIdListPack = new ArrayList<>();//list for storing serviceid's
		    List<BigDecimal> serviceAmountListPack = new ArrayList<>();//list for storing serviceamount(including tax)
		    for(int i=0;i<guestappList.size();i++){
		     List list3 = session.createSQLQuery("select appointmentid,packageid,sum(amount) from billdetails where packageid is not null and billno="+billNo+" and appointmentid="+guestappList.get(i)+"group by appointmentid,packageid").list();
		     Iterator iterator3 = list3.iterator();
		     while(iterator3.hasNext()){
		    	 Object next = iterator3.next();
		    	 Object oo[] = (Object[])next;
		    	 Integer appPacId= (Integer)oo[0];
		    	 Integer serviceIdPack =(Integer)oo[1];
		    	 BigDecimal serviceAmountPack =(BigDecimal)oo[2];
		    	 appointmentIdPack.add(appPacId);
		    	 serviceIdListPack.add(serviceIdPack);
		    	 serviceAmountListPack.add(serviceAmountPack);
		     }
		    }
		   
		     
		     Guestmaster gm = new Guestmaster();
		     gm.setGuestid(guestId);
		    /*-----------Inserting record into GuestHistoryService table Begin---------------*/
			   for(int i=0;(i<appointmentId.size()&&i<serviceIdList.size()&&i<serviceAmountList.size()&&i<remainderDaysList.size());i++){
			        Guesthistoryservices ghs = new Guesthistoryservices();
			        Calendar c = Calendar.getInstance();    
			        c.setTime(date);
			        c.add(Calendar.DATE, remainderDaysList.get(i));
			        Date remainderDate = c.getTime();
			        ghs.setRemainderdate(remainderDate);
			        ghs.setValid(true);
			        ghs.setUpdatedby("raghava");
			        ghs.setUpdateddate(date);
			        ghs.setUpdatedip("172.22.1.27");
			        ghs.setGuestmaster(gm);
			        ghs.setVisitdate(date);
			        Criteria createCriteria = session.createCriteria(Guesthistoryservices.class);
			        createCriteria.setProjection(Projections.max("guesthistoryserviceid"));
			        Integer guestHisServId = (Integer)createCriteria.uniqueResult();
			        if(guestHisServId==null){
			        	ghs.setGuesthistoryserviceid(1);
			        }else{
			        	ghs.setGuesthistoryserviceid(guestHisServId+1);
			        }
			        ghs.setRevenue(serviceAmountList.get(i));
			        Servicemaster sm = new Servicemaster();
			        sm.setServiceid(serviceIdList.get(i));
			        ghs.setServicemaster(sm);
			        Appointment appment = new Appointment();
			        appment.setAppointmentid(appointmentId.get(i));
			        ghs.setAppointment(appment);
			        session.save(ghs);
			   }
			   /*-----------Inserting record into GuestHistoryService table end---------------*/
			   /*-----------Inserting record into GuestHistoryService based on package table Begin---------------*/
			   for(int i=0;(i<appointmentIdPack.size()&&i<serviceIdListPack.size()&&i<serviceAmountListPack.size());i++){
			        Guesthistoryservices ghs = new Guesthistoryservices();
			        ghs.setValid(true);
			        ghs.setUpdatedby("raghava");
			        ghs.setUpdateddate(date);
			        ghs.setUpdatedip("172.22.1.27");
			        ghs.setVisitdate(date);
			        ghs.setGuestmaster(gm);
			        Criteria createCriteria = session.createCriteria(Guesthistoryservices.class);
			        createCriteria.setProjection(Projections.max("guesthistoryserviceid"));
			        Integer guestHisServId = (Integer)createCriteria.uniqueResult();
			        if(guestHisServId==null){
			        	ghs.setGuesthistoryserviceid(1);
			        }else{
			        	ghs.setGuesthistoryserviceid(guestHisServId+1);
			        }
			        ghs.setRevenue(serviceAmountListPack.get(i));
			        Packagemaster objPackageMaster = new Packagemaster();
			        objPackageMaster.setPackageid(serviceIdListPack.get(i));
			        ghs.setPackagemaster(objPackageMaster);
			        Appointment appment = new Appointment();
			        appment.setAppointmentid(appointmentIdPack.get(i));
			        ghs.setAppointment(appment);
			        session.save(ghs);
			   }
			   /*-----------Inserting record into GuestHistoryService based on package table end---------------*/
			  
			    BigDecimal billAmu = (BigDecimal)session.createSQLQuery("select amount from billmaster where billno="+billNo).list().get(0);
			   /*-----------Inserting record into GuestHistoryRevenue table Begin---------------*/
			    
			     Guesthistoryrevenue ghr = new Guesthistoryrevenue();
			     ghr.setRevenue(billAmu);
			     ghr.setVisitdate(date);
			     ghr.setGuestmaster(gm);
			     ghr.setUpdatedby("raghava");
			     ghr.setValid(true);
	             ghr.setUpdatedip("172.22.1.27");
	             ghr.setUpdateddate(date);
	             Criteria criteria = session.createCriteria(Guesthistoryrevenue.class);
				    criteria.setProjection(Projections.max("guesthistoryrevenueid"));
				    Integer id = (Integer) criteria.uniqueResult();
				    if(id==null){
					   ghr.setGuesthistoryrevenueid(1);
				    }else{
					   ghr.setGuesthistoryrevenueid(id+1);
				    }
				   session.save(ghr);
			    
			   /*-----------Inserting record into GuestHistoryRevenue table end---------------*/
			     
			     
			   /*-----------updating record into GuestMaster table start---------------*/
			   Object object = session.createSQLQuery("select noofvisits,revenue,email from guestmaster where guestid="+guestId).list().get(0);
			   Object[] objArray =(Object[])object;
			   
			   Object numVisitObj =objArray[0];
			   Integer numVisits =0;
			   if(numVisitObj==null){
				   numVisits=0;
			   }else{
				   numVisits=(Integer)objArray[0];
			   }
			  
			   Object amountObj =objArray[1];
			   BigDecimal rev = new BigDecimal(0);
		       if(amountObj==null){
				   rev=new BigDecimal(0);
			   }else{
				   rev=(BigDecimal)objArray[1];
			   }
		       String email =(String)objArray[2];
			   Integer noOfVisits = numVisits+1;
			   BigDecimal sum =rev.multiply(new BigDecimal(numVisits)).add(billAmu);
			   BigDecimal revenue = sum.divide(new BigDecimal(noOfVisits),0, RoundingMode.CEILING);
			 
			   SQLQuery createSQLQuery = session.createSQLQuery("update guestmaster set noofvisits="+noOfVisits+", revenue="+revenue+", lastvisitdate='"+date+"' where guestid="+guestId);
			   createSQLQuery.executeUpdate();
			   /*-----------updating record into GuestMaster table end---------------*/
		     transaction.commit();
		     
		      msg="Successfully Bill Paid ";
		      /*if(email!=""){
		     /* if(email!=""){
		      Transaction transaction2 = session.beginTransaction();
		      SimpleMailMessage smm=new SimpleMailMessage();
		 	   smm.setFrom("raghava.wellness@gmail.com");
		 	  String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
		    		  + "Please click on the following link, or paste into your browser to complete the reset password process.\n\n"
		    		   + ServletUriComponentsBuilder.fromCurrentContextPath().path("/customerfeedback").toUriString()
		    		  + "\n\n Note:- If you did not request this, please ignore this email and your password will remain unchanged.This link Expires after reset password process completes (OR ELSE) Expires in one hour:";
		 	   
		 	   smm.setTo(email);
		 	   smm.setSubject("Feedback Form");
		 	   smm.setText(text);
		 	   mailSender.send(smm);
		 	   Feedbackformmaster feedbackformmaster = new Feedbackformmaster();
		 	   feedbackformmaster.setValid(true);
		 	   feedbackformmaster.setUpdatedby("raghava");
		 	   feedbackformmaster.setUpdateddate(date);
		 	   feedbackformmaster.setUpdatedip(Ipaddress.getIpAddress());
		 	   Billmaster billmasterObj = new Billmaster();
		 	   billmasterObj.setBillno(billNo);
		 	   feedbackformmaster.setBillmaster(billmasterObj);
		 	  
		 	   feedbackformmaster.setGuestmaster(gm);
		 	   Departmentmaster departmentmaster = new Departmentmaster();
		 	   departmentmaster.setDepartmentid(1);
		 	   feedbackformmaster.setDepartmentmaster(departmentmaster);
		 	   Formmaster formmaster = new Formmaster();
		 	   formmaster.setFormid(1001);
		 	   feedbackformmaster.setFormmaster(formmaster);
		 	   feedbackformmaster.setFormsentdate(date);
		 	   Criteria criteria2 = session.createCriteria(Feedbackformmaster.class);
		 	   criteria2.setProjection(Projections.max("feedbackformid"));
		 	   Integer feedformid = (Integer)criteria2.uniqueResult();
		 	   if(feedformid==null){
		 		   feedbackformmaster.setFeedbackformid(1);
		 	   }else{
		 		   feedbackformmaster.setFeedbackformid(feedformid+1);
		 	   }
		 	   
		 	   session.save(feedbackformmaster);
		 	   transaction2.commit();
		      }*/
		}catch(Exception e){
			logger.error("Exception raised in saveBillPayment method:",e);
			msg ="Bill not paid";
		}
		finally{
			session.close();
		}
		return msg;
	}
	@Override
	public List<Billmaster> generatedBillDetails(){
		Session session = sessionFactory.openSession();
		List<Billmaster> billMasterList = new ArrayList<>();
		try{
		    Criteria criteria = session.createCriteria(Billmaster.class,"billmaster");
		    criteria.add(Restrictions.eq("valid",true));
		    criteria.add(Restrictions.eq("settled",false));
		    criteria.setFetchMode("guestmaster", FetchMode.EAGER);
		    Order order = Order.asc("billno");
			criteria.addOrder(order);
		    billMasterList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in generatedBillDetails method:",e);
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
		    criteria.add(Restrictions.eq("settled",false));
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
	public BigDecimal creditMoneyAval(int creditlistid) {
		Session session = sessionFactory.openSession();
		BigDecimal outstandingamount =null;
		try{
			Transaction transaction = session.beginTransaction();
			Criteria creditlistcriteria = session.createCriteria(Creditlistmaster.class);
	    	creditlistcriteria.add(Restrictions.eq("creditlistid", creditlistid));
	    	creditlistcriteria.setProjection(Projections.property("outstandingamount"));
	    	outstandingamount = (BigDecimal)creditlistcriteria.uniqueResult();
	    	transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in creditMoneyAval method:",e);
		}
		finally{
			session.close();
		}
		return outstandingamount;
	}
	@Override
	public BigDecimal membershipMoneyAval(int membershipid) {
		Session session = sessionFactory.openSession();
		BigDecimal outstandingamount =null;
		try{
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Membershipmaster.class);
	    	criteria.add(Restrictions.eq("membershipid", membershipid));
	    	criteria.setProjection(Projections.property("outstandingamount"));
	    	outstandingamount = (BigDecimal)criteria.uniqueResult();
	    	transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in membershipMoneyAval method:",e);
		}
		finally{
			session.close();
		}
		return outstandingamount;
	}
	@Override
	public BigDecimal giftVoucherMoneyAval(int giftVoucherId) {
		Session session = sessionFactory.openSession();
		BigDecimal giftVoucherAmount =null;
		try{
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Giftvouchermaster.class);
	    	criteria.add(Restrictions.eq("giftvoucherid", giftVoucherId));
	    	Date date = new Date();
	    	criteria.add(Restrictions.ge("validto", date));
	    	criteria.add(Restrictions.le("validfrom", date));
	    	criteria.add(Restrictions.eq("valid", true));
	    	criteria.setProjection(Projections.property("giftvoucheramount"));
	    	giftVoucherAmount = (BigDecimal)criteria.uniqueResult();
	    	transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in membershipMoneyAval method:",e);
		}
		finally{
			session.close();
		}
		return giftVoucherAmount;
		
	}
	@Override
	public List marketingVoucherMoneyAval(int marketingvoucherid) {
		Session session = sessionFactory.openSession();
		List marketingVoucherList = new ArrayList();
			Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
	    	criteria.add(Restrictions.eq("marketingvoucherid", marketingvoucherid));
	    	Date date = new Date();
	    	criteria.add(Restrictions.ge("validto", date));
	    	criteria.add(Restrictions.le("validfrom", date));
	    	criteria.add(Restrictions.eq("valid", true));
	    	criteria.setProjection(Projections.projectionList()
	    	.add(Projections.property("voucheramount"))
	    	.add(Projections.property("marketingcompany")));
	    	Object object = criteria.list().get(0);
	    	Object objArr[] = (Object[])object;
	    	BigDecimal vocheramount =(BigDecimal)objArr[0];
	    	String marketingcompany =(String)objArr[1];
	    	System.out.println("!!@!@!@!@!@!@!@!@@@!@!@"+vocheramount);
	    	System.out.println("!!@!@!@!@!@!@!@!marketingcompany@@@!@!@"+marketingcompany);
	    	marketingVoucherList.add(vocheramount);
	    	marketingVoucherList.add(marketingcompany);
			session.close();
		return marketingVoucherList;
	}
	@Override
	public List<Billmaster> getSearchBillsByBillNo(String billNo) {
		Session session = sessionFactory.openSession();
		List<Billmaster> billMasterList = new ArrayList<>();
		try{
		    Criteria criteria = session.createCriteria(Billmaster.class,"billmaster");
		    criteria.add(Restrictions.eq("valid",true));
		    criteria.add(Restrictions.eq("settled",false));
		    if(billNo!=""){
		    	int billNum =Integer.parseInt(billNo);
		    criteria.add(Restrictions.eq("billno", billNum));
		    }
		    criteria.setFetchMode("guestmaster", FetchMode.EAGER);
		    Order order = Order.asc("billno");
			criteria.addOrder(order);
		    billMasterList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in generatedBillDetails method:",e);
		}
		finally{
			session.close();
		}
		return billMasterList;
	}
	
	@Override
	public List generatepdfreceipt(Integer billno, Integer guestid) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ArrayList<Object> listPrintBill = new ArrayList<Object>();
		List<Map<String,String>> ListServices=new ArrayList<Map<String,String>>();
		Integer payid = 0;
		String payname = null;
		BigDecimal totamount = new BigDecimal(0);
		BigDecimal amount = new BigDecimal(0);
		BigDecimal outstandamount = new BigDecimal(0);
		String guestname = null;
		Integer titleid = 0;
		String title = null;
		Map<String,String> emap1 = new LinkedHashMap<String, String>();
		Map<String,String> lmap1 = new LinkedHashMap<String, String>();
		
		
		Date newDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
		String formatedDate = sdf.format(newDate);
		
		
		
		
		Map<String,String> billmap = new LinkedHashMap<String, String>();
		billmap.put("pname", "Receipt No");
		billmap.put("pqty", "");
		billmap.put("prate", billno.toString());
		ListServices.add(billmap);
		
		
		Map<String,String> datemap = new LinkedHashMap<String, String>();
		datemap.put("pname", "Receipt Date");
		datemap.put("pqty", "");
		datemap.put("prate", formatedDate);
		ListServices.add(datemap);
		
		
		String guestQuery = "select titleid,name from guestmaster where guestid='"+guestid+"'";
		List guestlist = session.createSQLQuery(guestQuery).list();
		Iterator guestiterator = guestlist.iterator();
		while (guestiterator.hasNext()) {
			Map<String,String> pmap = new LinkedHashMap<String, String>();
			Object[] object = (Object[]) guestiterator.next();
			titleid = (Integer)object[0];
			guestname = (String)object[1];
			
			String titleQuery = "select description from dropdowndetails where dropdowndetailsid = '"+titleid+"'";
			List titlelist = session.createSQLQuery(titleQuery).list();
			Iterator titleiterator = titlelist.iterator();
			while (titleiterator.hasNext()) {
				Object tit = (Object) titleiterator.next();
				title = (String)tit;
			
			pmap.put("pname", "Guest Name");
			pmap.put("pqty", "");
			pmap.put("prate",title+" "+guestname);
			ListServices.add(pmap);
		}
		
		
		emap1.put("pname", "");
		emap1.put("pqty", "");
		emap1.put("prate", "");
		ListServices.add(emap1);
		
		}
		
		String totamountQuery = "select amount from billmaster where guestid='"+guestid+"' and billno='"+billno+"' and settled = 'false'";
		List totamountlist = session.createSQLQuery(totamountQuery).list();
		Iterator totamountiterator = totamountlist.iterator();
		while (totamountiterator.hasNext()) {
			Map<String,String> amtmap = new LinkedHashMap<String, String>();
			Object totamtobj = (Object) totamountiterator.next();
			totamount = (BigDecimal)totamtobj;
			
			amtmap.put("pname", "Total Amount Due");
			amtmap.put("pqty", "");
			amtmap.put("prate", totamount.toString());
			ListServices.add(amtmap);
		}
		
		
		
		
		String paymodeQuery = "select paymodeid,paymode from paymodemaster";
		
		List paymodeList = session.createSQLQuery(paymodeQuery).list();
		
		Iterator paymodeiterator = paymodeList.iterator();
		while (paymodeiterator.hasNext()) {
			
			Map<String,String> map=new LinkedHashMap<String,String>();
			Object[] pay = (Object[]) paymodeiterator.next();
			
			payid = (Integer)pay[0];
			payname = (String)pay[1];
			
			String amountQuery = "select sum(amount) from billsettlement where paymodeid='"+payid+"' and  billno='"+billno+"'";
			List amountlist = session.createSQLQuery(amountQuery).list();
			Iterator amountiterator = amountlist.iterator();
			while (amountiterator.hasNext()) {
				Object amt = (Object) amountiterator.next();
				amount = (BigDecimal) amt;
			}
			if(amount != null){
				
				map.put("pname", "Payed By "+ " " +payname);
				map.put("pqty", "");
				map.put("prate", amount.toString());
				ListServices.add(map);
			}
			
		}
		
		
		
		emap1.put("pname", "");
		emap1.put("pqty", "");
		emap1.put("prate", "------------");
		ListServices.add(emap1);
		
		
		
		
		String outstandamountQuery = "select amount from billmaster where guestid='"+guestid+"' and billno='"+billno+"' ";
		List outstandamountlist = session.createSQLQuery(outstandamountQuery).list();
		Iterator outstandamountiterator = outstandamountlist.iterator();
		while (outstandamountiterator.hasNext()) {
			Map<String,String> amtmap = new LinkedHashMap<String, String>();
			Object outstandamtobj = (Object) outstandamountiterator.next();
			outstandamount = (BigDecimal)outstandamtobj;
			
			amtmap.put("pname", "Total Amount Paid");
			amtmap.put("pqty", "");
			amtmap.put("prate", outstandamount.toString());
			ListServices.add(amtmap);
		}
		
		
		lmap1.put("pname", "");
		lmap1.put("pqty", "");
		lmap1.put("prate", "------------");
		ListServices.add(lmap1);
		
		
		
		List<Object> finalList=new ArrayList<Object>();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ListServices);
		 finalList.add(listPrintBill);
		finalList.add(ListServices);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+finalList);
		return finalList;
	}
	@Override
	public String updateBillSettlementDiscount(String discountreason, String discounttype, String discountamount,
			Integer billid) {
		Session session = sessionFactory.openSession();
		/*Integer percentage =Integer.parseInt(discountpercent);
		Date date = new Date();
		try{
			Transaction transaction = session.beginTransaction();
			Object object = session.createSQLQuery("select amount,outstandingamount,discountamount from billmaster where billno="+billid).list().get(0);
			Object []objArray = (Object[])object;
			BigDecimal amount =(BigDecimal)objArray[0];
			BigDecimal outamount =(BigDecimal)objArray[1];
			Object aaa =objArray[2];
			BigDecimal disAmount =new BigDecimal(0);
			if(aaa!=null){
				disAmount=(BigDecimal)aaa;
			}else{
				disAmount =new BigDecimal(0);
			}
			System.out.println(amount+"amount");
			System.out.println(outamount+"outamount");
			System.out.println(disAmount+"disAmount");
			BigDecimal discountamount =new BigDecimal(0);
			BigDecimal outstandAmount =new BigDecimal(0);
			BigDecimal actdisamount =new BigDecimal(0);
			System.out.println("discountbyamount"+discountbyamount+"discountbyamount");
			if(discountbyamount!=""){
				discountamount = new BigDecimal(discountbyamount);
			    outstandAmount = outamount.subtract(discountamount);
			    actdisamount =disAmount.add(discountamount);
			}else if(discountpercent!=""){
				System.out.println(discountpercent+"discountpercent");
				BigDecimal per =new BigDecimal(discountpercent);
				BigDecimal percentamount =(amount.divide(new BigDecimal(100))).multiply(per);
				System.out.println(percentamount+"percentamount");
				outstandAmount = outamount.subtract(percentamount);
				outstandAmount = outstandAmount.setScale(0, RoundingMode.CEILING);
				actdisamount =disAmount.add(percentamount);
			}
			
			 
			System.out.println("outstandAmount="+outstandAmount+"actdisamount="+actdisamount+"actdisamount="+actdisamount);
			SQLQuery query = session.createSQLQuery("update billmaster set outstandingamount="+outstandAmount+", discountamount="+actdisamount+" where billno="+billid);
			query.executeUpdate();*/
		Date date = new Date();
		   try{
			   Transaction transaction = session.beginTransaction();
			   Object object = session.createSQLQuery("select amount,outstandingamount,discountamount from billmaster where billno="+billid).list().get(0);
			   Object []objArray = (Object[])object;
			   BigDecimal amount =(BigDecimal)objArray[0];
			   BigDecimal outamount =(BigDecimal)objArray[1];
			   Object aaa =objArray[2];
			   BigDecimal disAmount =new BigDecimal(0);
			   if(aaa!=null){
				  disAmount=(BigDecimal)aaa;
			   }else{
				  disAmount =new BigDecimal(0);
			  }
			   
			   BigDecimal discountamount1 = new BigDecimal(discountamount);
			    BigDecimal outstandAmount = outamount.subtract(discountamount1);
			    BigDecimal actdisamount =disAmount.add(discountamount1);
			    SQLQuery query = session.createSQLQuery("update billmaster set outstandingamount="+outstandAmount+", discountamount="+actdisamount+" where billno="+billid);
				query.executeUpdate();
			Discountmaster discountmaster = new Discountmaster();
			Criteria criteria = session.createCriteria(Discountmaster.class);
			criteria.setProjection(Projections.max("discountid"));
			Integer discountid = (Integer)criteria.uniqueResult();
			if(discountid==null){
				discountmaster.setDiscountid(1);
			}else{
			discountmaster.setDiscountid(discountid+1);
			}
			discountmaster.setValid(true);
			discountmaster.setUpdatedby("raghava");
			discountmaster.setUpdatedip("172.22.1.27");
			discountmaster.setUpdateddate(date);
			discountmaster.setDiscountreason(discountreason);
			Billmaster billmaster = new Billmaster();
			billmaster.setBillno(billid);
			discountmaster.setBillmaster(billmaster);
			discountmaster.setDiscount(discountamount1);
			discountmaster.setDiscounttype(discounttype);
			session.save(discountmaster);
			transaction.commit();
		}catch(Exception e){
			logger.error("Exception raised in updateBillSettlementDiscount"+e);
		}
		finally{
			session.close();
		}
		return null;
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
}
