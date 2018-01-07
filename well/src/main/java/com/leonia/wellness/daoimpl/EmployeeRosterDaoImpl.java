package com.leonia.wellness.daoimpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.StockListDisplayDTO;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Employeeroster;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Skillmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.idao.IEmployeeRosterDAO;
import com.leonia.wellness.entity.Employeeroster;
@Repository
public class EmployeeRosterDaoImpl implements IEmployeeRosterDAO{
@Autowired
private SessionFactory sessionFactory;
private static final Logger logger = Logger.getLogger(EmployeeRosterDaoImpl.class);
	@Override
	public List<Staffmaster> getStaffMaster(){
	Session session = sessionFactory.openSession();
		System.out.println("!!!!!!!ii am DAO!");
		
		Criteria criteria = session.createCriteria(Staffmaster.class);
		criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
		criteria.add(Restrictions.eq("valid",true));
		Order order=Order.asc("staffid");
		criteria.addOrder(order);
		List<Staffmaster> list = criteria.list();
		System.out.println("staff:"+list.size());
		session.close();
		return list;
		}
		@Override
	public List<Staffmaster> getStaffName(String staffname){
	System.out.println("StaffName"+staffname);
	Session session = sessionFactory.openSession();
	Transaction tansaction=session.beginTransaction();
	Criteria criteria = session.createCriteria(Staffmaster.class);
	criteria.add(Restrictions.ilike("staffname", staffname,MatchMode.ANYWHERE));
	criteria.setFetchMode("departmentmaster",FetchMode.EAGER);
	List<Staffmaster> list = criteria.list();
	System.out.println("staff2323523523523523:"+list.size());

	return list;
}


		@Override
		public Staffmaster fetchSelectedStaff(Integer staffid) {
	
		Session session = sessionFactory.openSession();
		Staffmaster staffmaster=null;
		try{
			Criteria criteria = session.createCriteria(Staffmaster.class);
			criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
			criteria.add(Restrictions.eq("staffid", staffid));
			 staffmaster = (Staffmaster)criteria.uniqueResult();
		}
		catch(Exception e){
			logger.error("exception raised in fetching stock details"+e);
		}
		
		finally{
			
		    session.close();
		}
		return staffmaster;
	}

	@Override
	public List<Employeeroster> getRosterList(){
		Session session = sessionFactory.openSession();
		List<Employeeroster> list=null;
		try{
			Criteria criteria = session.createCriteria(Employeeroster.class);

			criteria.setFetchMode("departmentmaster", FetchMode.EAGER);
			criteria.setFetchMode("staffmaster", FetchMode.EAGER);
			list=criteria.list();

		}
		catch(Exception e){
			logger.error("Exception raised in employee rosterDAOImpl save method"+e);
		}

		finally{
			session.close();
		}
		return list;

		}
	@Override
	public int insetrtRoster(Integer staffId, Integer slemonth, Integer year, String weekDay, Integer numberofdays)
	
	{
		
	Session session = sessionFactory.openSession();
	
	
	
	Transaction txt=session.beginTransaction();
	
	String Query="from Employeeroster where rosterid=?";
	
	Query queryObj = session.createQuery(Query);
	Employeeroster emproster=new Employeeroster();
	String monYear=slemonth+"/"+year;
	
		try{
			
		int id1=0;
		Criteria criteria=session.createCriteria(Employeeroster.class);
		criteria.setProjection(Projections.max("rosterid"));
		Integer id=(Integer)criteria.uniqueResult();
		
		System.out.println(id+"----------------------------"+slemonth);
		
		if(id==null){
			
			id1=1; 
			
			emproster.setRosterid(1);
			
			}
			else{
			id1=id+1; 
			emproster.setRosterid(id+1);
			}
		Staffmaster staffmaster =new Staffmaster();
		
		staffmaster.setStaffid(staffId);
		Employeeroster emp1 = null;
		
		if(slemonth == 4 || slemonth == 6 || slemonth == 9 || slemonth == 11 )
		{
			emp1 =new Employeeroster(id1,staffmaster,monYear,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,null,true,"leo",new Date(),"172.12.12.12");
		}
		else{
			if(slemonth == 2)
			{
				if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
				{
					emp1 =new Employeeroster(id1,staffmaster,monYear,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,null,null,true,"leo",new Date(),"172.12.12.12");
				}
				else{
					emp1 =new Employeeroster(id1,staffmaster,monYear,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,null,null,null,true,"leo",new Date(),"172.12.12.12");
				}
			}
			else{
				emp1 =new Employeeroster(id1,staffmaster,monYear,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,true,"leo",new Date(),"172.12.12.12");
			}
		}
		
		session.save(emp1);
		Calendar mycal = new GregorianCalendar(year, slemonth, 1);
		 int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		 		 StringBuffer strQury=new StringBuffer();
				 strQury.append("Update Employeeroster set ");
				 int j=0;
				 for(int i=1;i<=numberofdays;i++){
			 	 String dateString = String.format("%d-%d-%d", year, slemonth, i);
			 	 Date date;
				try {
					date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
				 	String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
				 	if(weekDay.equals(dayOfWeek)){
				 		
				 		String day="day"+i;
				 		
				 		if(j==0){
				 			strQury.append(day+"=2");
				 		}
				 		else{
				 		strQury.append(","+day+"=2");
				 		}
				 		j++;
				 	}
				 	else{
				 		
				 	}
				 	
				} 
				catch (ParseException e) {
					e.printStackTrace();
				}
	      }
	 
		   strQury.append(" where staffid="+staffId+" AND monthandyear='"+monYear+"'");
		 	System.out.println(strQury);
		 	String strr=strQury.toString();
		 	int rosterId=emp1.getRosterid();
			if(rosterId !=0){
				Query qryrates = session.createQuery(strr);
				 int ratesres = qryrates.executeUpdate();
			}
		 	
		}
	catch(Exception e){
		e.printStackTrace();
		txt.rollback();
	}
	 
	 
	// System.out.println);
	txt.commit();
	session.close();
	return 0;
	}

	@Override
	public String checkmonthyear(Integer staffId, Integer slemonth, Integer year){
	Session session = sessionFactory.openSession();
	Transaction txt=session.beginTransaction();
	String monYear=slemonth+"/"+year;
	String status = null;
	
	String myhql = "from Employeeroster where monthandyear ='"+monYear+"' and staffid="+staffId;
	List montyearlist = session.createQuery(myhql).list();
	System.out.println(montyearlist.size());
	if(montyearlist.size() == 0){
		
		return status;
	}
	else{
		status="RosterAvial";
		return status;
	}
	/*Iterator monthyearie = montyearlist.iterator();
	while (monthyearie.hasNext()) {
		Object monyar = (Object) monthyearie.next();
		myear = (String)monyar;
	} */
	
	}

	@Override
	public List<Integer> getRosterStatus(Integer staffId, Integer slemonth, Integer year) {
		
		// TODO Auto-generated method stub
		String monYear=slemonth+"/"+year;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String query="from Employeeroster where monthandyear ='"+monYear+"' and staffid="+staffId;
		List<Employeeroster> montyearlist = session.createQuery(query).list();
		Iterator<Employeeroster> ie=montyearlist.iterator();
		List<Integer> statusList=new ArrayList<Integer>();
		
		while(ie.hasNext()){
			Employeeroster eroster=ie.next();
			statusList.add(eroster.getDay1());
			statusList.add(eroster.getDay2());
			statusList.add(eroster.getDay3());
			statusList.add(eroster.getDay4());
			statusList.add(eroster.getDay5());
			statusList.add(eroster.getDay6());
			statusList.add(eroster.getDay7());
			statusList.add(eroster.getDay8());
			statusList.add(eroster.getDay9());
			statusList.add(eroster.getDay10());
			statusList.add(eroster.getDay11());
			statusList.add(eroster.getDay12());
			statusList.add(eroster.getDay13());
			statusList.add(eroster.getDay14());
			statusList.add(eroster.getDay15());
			statusList.add(eroster.getDay16());
			statusList.add(eroster.getDay17());
			statusList.add(eroster.getDay18());
			statusList.add(eroster.getDay19());
			statusList.add(eroster.getDay20());
			statusList.add(eroster.getDay21());
			statusList.add(eroster.getDay22());
			statusList.add(eroster.getDay23());
			statusList.add(eroster.getDay24());
			statusList.add(eroster.getDay25());
			statusList.add(eroster.getDay26());
			statusList.add(eroster.getDay27());
			statusList.add(eroster.getDay28());
			statusList.add(eroster.getDay29());
			statusList.add(eroster.getDay30());
			statusList.add(eroster.getDay31());
		}
		
		tx.commit();
		session.close();
		return statusList;
	}

	@Override
	public String UpdateRoster(Integer staffId, Integer slemonth, Integer year, List<Integer> updateArr, String weekDay) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Iterator<Integer> ie=updateArr.iterator();
		StringBuffer query=new StringBuffer();
		int j=1;
		String monYear=slemonth+"/"+year;
		
		query.append("Update Employeeroster set ");
		
		while(ie.hasNext()){
			
			Calendar mycal = new GregorianCalendar(year, slemonth, 1);
			
			 int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			 
					  String dateString = String.format("%d-%d-%d", year, slemonth, j);
					  
				 	 Date date;
				 	 
					try {
						
						date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
						
					 	String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
					 	
					 	if(weekDay.equals(dayOfWeek)){
					 		
					 		String day="day"+j;
					 		
					 		if(j==1){
					 			int temp = ie.next();
					 			if(temp!=1)
					 			{
					 				query.append(day+"=2");
					 			}
					 			else
					 			{
					 				query.append(day+"="+temp);
					 			}
					 			
					 		}
					 		
					 		else{
					 			int temp = ie.next();
					 			if(temp!=1)
					 			{
					 				query.append(","+day+"=2");
					 			}
					 			else
					 			{
					 				query.append(","+day+"="+temp);
					 			}
					 			
					 			
					 		}
					 		
					 	}
					 	
					 	else{
					 		
					 		if(j==1){
					 			
								String day="day"+j;
								
								query.append(day+"="+ie.next());
								
							}
					 		
							else{
								
								String day="day"+j;
								
								query.append(","+day+"="+ie.next());
								
								}
					 	}
					 	
					 	j++;
					 	
					} 
					
					catch (ParseException e) {
						
						e.printStackTrace();
					}
		      
			
		}
		
		query.append(" where staffid="+staffId+" AND monthandyear='"+monYear+"'");
		
		System.out.println(query);
		
		Query qryrates = session.createQuery(query.toString());
		
		int ratesres = qryrates.executeUpdate();
		
		tx.commit();
		
		session.close();
		
		return "sucess";
	}
}
