package com.leonia.wellness.daoimpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.dto.StaffSkillsData;
import com.leonia.wellness.dto.TaxDetails;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Corporatetypemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Giftvouchermaster;
import com.leonia.wellness.entity.Marketingvouchermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Packageratemaster;
import com.leonia.wellness.entity.Packageservices;
import com.leonia.wellness.entity.Productratemaster;
import com.leonia.wellness.entity.Qualificationmaster;
import com.leonia.wellness.entity.Reasondetails;
import com.leonia.wellness.entity.Reasonmaster;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Servicegroupmaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Serviceratemaster;
import com.leonia.wellness.entity.Serviceroommaster;
import com.leonia.wellness.entity.Servicestaffmaster;
import com.leonia.wellness.entity.Skillmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Staffskill;
import com.leonia.wellness.entity.Statemaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Taxmaster;
import com.leonia.wellness.entity.Taxstructuredetails;
import com.leonia.wellness.entity.Taxstructuremaster;
import com.leonia.wellness.idao.IMasterDAO;
import com.leonia.wellness.util.Ipaddress;
@Repository
public class MasterDAOImpl implements IMasterDAO{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(MasterDAOImpl.class);

	@Override
	public List<Reasonmaster> getAppointmentCancelReasons() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Reasonmaster.class);
		List<Reasonmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getAppointmentReasons Method:", e);
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public Set<Reasondetails> getReasonsList(Integer reasonchildmasterid) {

		Session session = sessionFactory.openSession();
		List<Reasonmaster> reasons = new LinkedList<Reasonmaster>() {
		};
		Criteria criteria = session.createCriteria(Reasonmaster.class);
		criteria.add(Restrictions.eq("reasonid", reasonchildmasterid));
		Reasonmaster uniqueResult = (Reasonmaster) criteria.uniqueResult();

		return uniqueResult.getReasondetailses();
	}

	@Override
	public void addReason(Reasondetails reasons) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			reasons.setUpdateddate(date);
			reasons.setUpdatedby("Harika");
			reasons.setUpdatedip("172.22.1.17");
			reasons.setValid(true);
			Criteria criteria = session.createCriteria(Reasondetails.class);
			criteria.setProjection(Projections.max("reasondetailsid"));
			Integer id = (Integer) criteria.uniqueResult();
			reasons.setReasondetailsid(id + 1);
			session.save(reasons);
			transaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in addReason Method:", e);
		} finally {
			session.close();
		}

	}

	@Override
	public String saveReason(String reasonName, Integer reasonchildmasterId) {
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasondetails.class);
			criteria.setProjection(Projections.max("reasondetailsid"));
			Integer id = (Integer) criteria.uniqueResult();
			if(id==null){
				id=0;
			}
			Date date = new Date();
			Reasonmaster reason = new Reasonmaster();
			Reasondetails reasonList = new Reasondetails();
			reason.setReasonid(reasonchildmasterId);
			reasonList.setReasonmaster(reason);
			reasonList.setReasondescription(reasonName);
			reasonList.setUpdatedby("Harika");
			reasonList.setUpdateddate(date);
			reasonList.setUpdatedip("172.22.1.17");
			reasonList.setValid(true);
			reasonList.setReasondetailsid(id + 1);
			session.save(reasonList);
			transaction.commit();
			message = "Reason of "+reasonList.getReasondescription()+" are Successfully saved!";
		} catch (Exception e) {
			logger.error("Exception raised in saveReason Method:", e);
		} finally {
			session.close();
		}
		return message;

	}

	@Override
	public String reasonUpdate(Integer reasonchildmasterId, Integer reasonid, String reason) {
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasondetails.class);
			Reasondetails reasonList = (Reasondetails) criteria.add(Restrictions.eq("reasondetailsid", reasonid)).uniqueResult();
			reasonList.setReasondescription(reason);
			session.update(reasonList);
			transaction.commit();
			message = "Reason of "+reasonList.getReasondescription()+" are Successfully updated!";
		} catch (Exception e) {
			logger.error("Exception raised in reasonupdate Method:", e);
		} finally {
			session.close();
		}
		return message;

	}

	@Override
	public String reasonDelete(Integer reasonchildmasterId, Integer reasonid, String reason) {
		
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasondetails.class);
			Reasondetails reasonList = (Reasondetails) criteria.add(Restrictions.eq("reasondetailsid", reasonid)).uniqueResult();
			reasonList.setValid(false);
			session.update(reasonList);
			transaction.commit();
			message = "Reason of "+reasonList.getReasondescription()+" are Successfully deleted!";
		} catch (Exception e) {
			logger.error("Exception raised in reasonDelete Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Servicemaster> getServices() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicemaster.class,"services");
		criteria.createAlias("services.dropdowndetails", "dropdowndetails");
		criteria.createAlias("services.servicegroupmaster", "servicegroupmaster");
		criteria.add(Restrictions.eq("valid", true));
		criteria.setFetchMode("dropdowndetails.dropdowndetails", FetchMode.EAGER);
		criteria.setFetchMode("servicegroupmaster.servicegroupmaster", FetchMode.EAGER);
		List<Servicemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServices Method:", e);
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public String saveServiceRate(Integer serviceId, Integer taxStructureId,Serviceratemaster servicerate) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.eq("serviceid",serviceId));
			Criteria criteria1 = session.createCriteria(Taxstructuremaster.class);
			criteria1.add(Restrictions.eq("sno",taxStructureId));
			Taxstructuremaster uniqueResult = (Taxstructuremaster) criteria1.uniqueResult();
			Servicemaster servicemaster=(Servicemaster) criteria.uniqueResult();
			servicerate.setTaxstructuremaster(uniqueResult);
			servicerate.setTaxstructureid(uniqueResult.getTaxstructureid());
			servicerate.setServicemaster(servicemaster);
			Criteria criteria2 = session.createCriteria(Serviceratemaster.class);
			criteria2.setProjection(Projections.max("servicerateid"));
			Integer id = (Integer) criteria2.uniqueResult();
			if(id==null){
				id=0;
			}
			servicerate.setServicerateid(id+1);
			servicerate.setUpdatedby("Harika");
			servicerate.setUpdateddate(date);
			servicerate.setUpdatedip("172.22.1.17");
			servicerate.setValid(true);
			session.save(servicerate);
			transaction.commit();
			message = "servicerate of "+servicerate.getServicecost()+" are Successfully saved!";
			}catch(Exception e){
				message = "servicerate of "+servicerate.getServicecost()+" are not saved!";
				logger.error("Exception raised in saveServiceRate Method:",e);
			}
			finally{
			session.close();
			}
		return message;
		
	}
	@Override
	public List<Serviceratemaster> serviceRateList() {
		Session session = sessionFactory.openSession();
		List<Serviceratemaster> list = new LinkedList<Serviceratemaster>();
		try{
			Date date = new Date();
			Criteria criteria = session.createCriteria(Serviceratemaster.class,"servicerate");
			criteria.createAlias("servicerate.taxstructuremaster", "taxstructuremaster");
			criteria.createAlias("servicerate.servicemaster", "servicemaster");
			criteria.add(Restrictions.eq("valid", true));
			criteria.setProjection(Projections.distinct(Projections.property("servicemaster.serviceid")));
			List<Integer> uniqueResult = criteria.list();
			for (Integer integer : uniqueResult) {
				Criteria ServiceCriterian = session.createCriteria(Serviceratemaster.class,"serviceratemaster");
				ServiceCriterian.createAlias("serviceratemaster.taxstructuremaster", "taxstructuremaster");
				ServiceCriterian.createAlias("serviceratemaster.servicemaster", "servicemaster");
				ServiceCriterian.add(Restrictions.eq("servicemaster.serviceid", integer));
				ServiceCriterian.add(Restrictions.eq("valid", true));
				ServiceCriterian.add(Restrictions.le("applicabledate", date));
				ServiceCriterian.setFetchMode("serviceratemaster.taxstructuremaster", FetchMode.EAGER);
				ServiceCriterian.setFetchMode("serviceratemaster.servicemaster", FetchMode.EAGER);
				Serviceratemaster serviceratemaster = (Serviceratemaster) ServiceCriterian.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
				if(serviceratemaster!=null)
				{
					list.add(serviceratemaster);
				}
				Criteria ServiceCriterian1 = session.createCriteria(Serviceratemaster.class,"serviceratemaster");
				ServiceCriterian1.createAlias("serviceratemaster.servicemaster", "servicemaster");
				ServiceCriterian1.createAlias("serviceratemaster.taxstructuremaster", "taxstructuremaster");
				ServiceCriterian1.add(Restrictions.eq("servicemaster.serviceid", integer));
				ServiceCriterian1.add(Restrictions.eq("valid", true));
				ServiceCriterian1.add(Restrictions.gt("applicabledate", date));
				ServiceCriterian1.setFetchMode("serviceratemaster.taxstructuremaster", FetchMode.EAGER);
				ServiceCriterian1.setFetchMode("serviceratemaster.servicemaster", FetchMode.EAGER);
				@SuppressWarnings("unchecked")
				List<Serviceratemaster> serviceratemaster1 = ServiceCriterian1.list();
				if(serviceratemaster1!=null)
				{
					for (Serviceratemaster serviceratemaster2 : serviceratemaster1) {
						list.add(serviceratemaster2);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("Exception raised in serviceRateList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
		}
	@Override
	public List<Taxstructuremaster> getTaxStructure() {
		Session session = sessionFactory.openSession();
		List<Taxstructuremaster> list = new LinkedList();
		try{
			Date date = new Date();
		Criteria criteria = session.createCriteria(Taxstructuremaster.class,"taxstructuremaster");
		criteria.createAlias("taxstructuremaster.taxstructuredetailses", "taxstructuredetails");
		criteria.add(Restrictions.eq("valid", true));
		ProjectionList proList = Projections.projectionList();
        proList.add(Projections.distinct(Projections.property("taxstructureid")));
        proList.add(Projections.property("taxstructuredetails.taxstructuredetailses"));
		criteria.setProjection(Projections.distinct(Projections.property("taxstructureid")));
		List<Integer> uniqueResult = criteria.list();
		for (Integer i : uniqueResult) {
			Criteria taxcriterain = session.createCriteria(Taxstructuremaster.class);
			taxcriterain.add(Restrictions.eq("taxstructureid", i));
			taxcriterain.add(Restrictions.eq("valid", true));
			taxcriterain.add(Restrictions.le("applicabledate", date));
			Taxstructuremaster uniqueResult2 = (Taxstructuremaster) taxcriterain.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
			if(uniqueResult2!=null)
			{
				list.add(uniqueResult2);
			}
			Criteria taxcriteraingreat = session.createCriteria(Taxstructuremaster.class);
			taxcriteraingreat.add(Restrictions.eq("taxstructureid", i));
			taxcriteraingreat.add(Restrictions.eq("valid", true));
			taxcriteraingreat.add(Restrictions.gt("applicabledate", date));
			List<Taxstructuremaster> result = taxcriteraingreat.list();
			if(result!=null)
			{
			for (Taxstructuremaster taxstructuremaster : result) {
				list.add(taxstructuremaster);
				}
			}
		}
		} catch (Exception e) {
			logger.error("Exception raised in getTaxStructure Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveTax(Taxmaster tax,String taxName) {
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			Taxmaster taxmaster=new Taxmaster();
			Criteria criteria = session.createCriteria(Taxmaster.class);
			criteria.setProjection(Projections.max("taxmasterid"));
			Integer id = (Integer) criteria.uniqueResult();
			if(id==null){
				id=0;
			}
			taxmaster.setTaxmasterid(id+1);
			taxmaster.setTaxname(taxName);
			taxmaster.setUpdatedby("Harika");
			taxmaster.setUpdateddate(date);
			taxmaster.setUpdatedip("172.22.1.17");
			taxmaster.setValid(true);
			session.save(taxmaster);
			transaction.commit();
			message = "taxes of "+taxmaster.getTaxname()+" are Successfully saved!";
			}catch(Exception e){
				message = "taxes of "+tax.getTaxname()+" are not saved!";
				logger.error("Exception raised in saveTax Method:",e);
			}
			finally{
			session.close();
			}
		return message;
	}

	@Override
	public List<Taxmaster> taxList() {
		Session session = sessionFactory.openSession();
		List<Taxmaster> list =new LinkedList<Taxmaster>();
		try{
			
			Criteria criteria = session.createCriteria(Taxmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in taxList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
		}

	@Override
	public List<Taxstructuremaster> taxStructureList() {
		Session session = sessionFactory.openSession();
		List<Taxstructuremaster> list =new LinkedList<Taxstructuremaster>();
		try{
			
			Criteria criteria = session.createCriteria(Taxstructuremaster.class);
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in taxStructureList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
		}

	@Override
	public List<Taxmaster> getTaxMasterList() {
		Session session = sessionFactory.openSession();
		List<Taxmaster> list =new LinkedList<Taxmaster>();
		try{
			
			Criteria criteria = session.createCriteria(Taxmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getTaxMasterList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveTaxStructure(TaxDetails taxDetails) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		String message = null;
		try{
			Taxstructuremaster taxstructuremaster = new Taxstructuremaster();
			Criteria criteria2 = session.createCriteria(Taxstructuremaster.class);
			criteria2.setProjection(Projections.max("sno"));
			Integer taxstructuresno = (Integer) criteria2.uniqueResult();
			if(taxstructuresno==null){
				taxstructuresno=0;
			}
			taxstructuremaster.setTaxstructureid(taxDetails.getTaxStructureId());
			taxstructuremaster.setTaxstructuredescription(taxDetails.getTaxStructureDescription());
			taxstructuremaster.setApplicabledate(taxDetails.getApplicableDate());
			taxstructuremaster.setValid(true);
			taxstructuremaster.setSno(++taxstructuresno);
			taxstructuremaster.setUpdatedby("Harika");
			taxstructuremaster.setUpdateddate(date);
			taxstructuremaster.setUpdatedip(Ipaddress.getIpAddress());
			session.save(taxstructuremaster);
			StringTokenizer str = new StringTokenizer(taxDetails.getTaxStructureDescription(),",");
			StringTokenizer taxpercent = new StringTokenizer(taxDetails.getTaxStructurePercent(),",");
			Taxmaster taxmaster = new Taxmaster();
			taxmaster.setTaxmasterid(taxDetails.getTaxId());
			Criteria criteria1 = session.createCriteria(Taxstructuredetails.class);
			criteria1.setProjection(Projections.max("sno"));
			Integer sno = (Integer) criteria1.uniqueResult();
			if(sno==null){
				sno=0;
			}
			Integer id = 0;
			while(str.hasMoreTokens() && taxpercent.hasMoreTokens()){
				Taxstructuredetails taxstructuredetails = new Taxstructuredetails();
				taxstructuredetails.setTaxstructuremaster(taxstructuremaster);
				taxstructuredetails.setTaxstructureid(taxDetails.getTaxStructureId());
				taxstructuredetails.setTaxmaster(taxmaster);
				taxstructuredetails.setPercent(new BigDecimal(taxpercent.nextToken()));
				taxstructuredetails.setTaxdescription(str.nextToken());
				taxstructuredetails.setTaxon(taxDetails.getTaxOn());
				taxstructuredetails.setTaxtype(taxDetails.getTaxType());
				taxstructuredetails.setTaxdetailsid(++id);
				taxstructuredetails.setValid(true);
				taxstructuredetails.setUpdatedby("Harika");
				taxstructuredetails.setUpdateddate(date);
				taxstructuredetails.setUpdatedip(Ipaddress.getIpAddress());
				taxstructuredetails.setSno(++sno);
				session.save(taxstructuredetails);
			}
			transaction.commit();
			message = "taxes of "+taxDetails.getTaxStructureDescription()+" are Successfully saved!";
		} catch (Exception e) {
			message = "taxes of "+taxDetails.getTaxStructureDescription()+" are not saved!";
			logger.error("Exception raised in saveTaxStructure Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<TaxDetails> getTaxStructureList() {
		Session session = sessionFactory.openSession();
		List<TaxDetails> list = new LinkedList<TaxDetails>();
		Date date = new Date();
		try{
			Criteria criteria = session.createCriteria(Taxstructuremaster.class,"taxstructuremaster");
			criteria.createAlias("taxstructuremaster.taxstructuredetailses", "taxstructuredetails");
			criteria.add(Restrictions.eq("valid", true));
			ProjectionList proList = Projections.projectionList();
	        proList.add(Projections.distinct(Projections.property("taxstructureid")));
	        proList.add(Projections.property("taxstructuredetails.taxstructuredetailses"));
			criteria.setProjection(Projections.distinct(Projections.property("taxstructureid")));
			List<Integer> uniqueResult = criteria.list();
			for (Integer i : uniqueResult) {
				Criteria taxcriterain = session.createCriteria(Taxstructuremaster.class);
				taxcriterain.add(Restrictions.eq("taxstructureid", i));
				taxcriterain.add(Restrictions.eq("valid", true));
				taxcriterain.add(Restrictions.le("applicabledate", date));
				Taxstructuremaster uniqueResult2 = (Taxstructuremaster) taxcriterain.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
				TaxDetails taxDetails = new TaxDetails();
				String percent = "";
				Integer count = 1;
				if(uniqueResult2!=null)
					{
						for (Taxstructuredetails taxstructuredetails : uniqueResult2.getTaxstructuredetailses()) {
							if(taxstructuredetails.getTaxstructuremaster().getSno()==taxstructuredetails.getTaxstructuremaster().getSno()){
								taxDetails.setApplicableDate(taxstructuredetails.getTaxstructuremaster().getApplicabledate());
								taxDetails.setTaxStructureDescription(taxstructuredetails.getTaxstructuremaster().getTaxstructuredescription());
								taxDetails.setTaxOn(taxstructuredetails.getTaxon());
								taxDetails.setTaxType(taxstructuredetails.getTaxtype());
								taxDetails.setTaxId(taxstructuredetails.getTaxmaster().getTaxmasterid());
								taxDetails.setTaxName(taxstructuredetails.getTaxmaster().getTaxname());
								taxDetails.setTaxStructureId(taxstructuredetails.getTaxstructureid());
								percent = percent+(taxstructuredetails.getPercent().setScale(2, BigDecimal.ROUND_HALF_EVEN));
								if(count<uniqueResult2.getTaxstructuredetailses().size())
									{
										percent = percent+",";
										count++;
									}
							}
						taxDetails.setTaxStructurePercent(percent);
						list.add(taxDetails);
					}
				}
				Criteria taxcriteraingreat = session.createCriteria(Taxstructuremaster.class);
				taxcriteraingreat.add(Restrictions.eq("taxstructureid", i));
				taxcriteraingreat.add(Restrictions.eq("valid", true));
				taxcriteraingreat.add(Restrictions.gt("applicabledate", date));
				List<Taxstructuremaster> result = taxcriteraingreat.list();
				if(result!=null)
				{
					for (Taxstructuremaster taxstructuremaster : result) {
						TaxDetails taxDetail = new TaxDetails();
						taxDetail.setApplicableDate(taxstructuremaster.getApplicabledate());
						taxDetail.setTaxStructureDescription(taxstructuremaster.getTaxstructuredescription());
						taxDetail.setTaxStructureId(taxstructuremaster.getTaxstructureid());
						Criteria createCriteria = session.createCriteria(Taxstructuredetails.class,"taxStructure");
						createCriteria.createAlias("taxStructure.taxstructuremaster","taxStructuremaster");
						createCriteria.createAlias("taxStructure.taxmaster","taxmaster");
						createCriteria.add(Restrictions.eq("taxStructuremaster.sno", taxstructuremaster.getSno()));
						List<Taxstructuredetails> taxstructuredetails = createCriteria.list();
						String percentage = "";
						Integer counts = 0;
						for (Taxstructuredetails taxstructuredetails2 : taxstructuredetails) {
							taxDetail.setTaxId(taxstructuredetails2.getTaxmaster().getTaxmasterid());
							taxDetail.setTaxName(taxstructuredetails2.getTaxmaster().getTaxname());
							taxDetail.setTaxOn(taxstructuredetails2.getTaxon());
							taxDetail.setTaxType(taxstructuredetails2.getTaxtype());
							percentage = percentage+(taxstructuredetails2.getPercent().setScale(2, BigDecimal.ROUND_HALF_EVEN));
							if(count<taxstructuredetails.size())
								{
								percentage = percentage+",";
								counts++;
								}
							else{
								
							}
							}
						taxDetail.setTaxStructurePercent(percentage);
						list.add(taxDetail);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception raised in taxStructureList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Stockmaster> getProducts() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Stockmaster.class,"stock");
		criteria.createAlias("stock.dropdowndetails","dropdowndetails");
		criteria.add(Restrictions.eq("dropdowndetails.dropdowndetailsid",5));
		List<Stockmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getProducts Method:", e);
		} finally {
			session.close();
		}
		return list;

		
	}

	@Override
	public String saveProductRate(Integer productId, Integer taxStructureId, Productratemaster productrate) {
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.add(Restrictions.eq("stockid",productId));
			Criteria criteria1 = session.createCriteria(Taxstructuremaster.class);
			criteria1.add(Restrictions.eq("sno",taxStructureId));
			Taxstructuremaster uniqueResult = (Taxstructuremaster) criteria1.uniqueResult();
			Stockmaster stockmaster=(Stockmaster) criteria.uniqueResult();
			productrate.setStockmaster(stockmaster);
			productrate.setTaxstructuremaster(uniqueResult);
			Criteria criteria2 = session.createCriteria(Productratemaster.class);
			criteria2.setProjection(Projections.max("productrateid"));
			Integer id = (Integer) criteria2.uniqueResult();
			if(id==null){
				id=0;
			}
			productrate.setProductrateid(id+1);
			productrate.setUpdatedby("Harika");
			productrate.setUpdateddate(date);
			productrate.setUpdatedip("172.22.1.17");
			productrate.setValid(true);
			session.save(productrate);
			transaction.commit();
			message = "productrate of "+productrate.getProductcost()+" are Successfully saved!";
			}catch(Exception e){
				message = "productrate of "+productrate.getProductcost()+" are not saved!";
				logger.error("Exception raised in saveProductRate Method:",e);
			}
			finally{
			session.close();
			}
		return message;
		
	}

	@Override
	public List<Productratemaster> productRateList() {
		Session session = sessionFactory.openSession();
		List<Productratemaster> list =new LinkedList<Productratemaster>();
		try{
			Date date = new Date();
			Criteria criteria = session.createCriteria(Productratemaster.class,"productrate");
			criteria.createAlias("productrate.taxstructuremaster", "taxstructuremaster");
			criteria.createAlias("productrate.stockmaster", "stockmaster");
			criteria.add(Restrictions.eq("valid", true));
			criteria.setProjection(Projections.distinct(Projections.property("stockmaster.stockid")));
			List<Integer> uniqueResult = criteria.list();
			for (Integer integer : uniqueResult) {
				Criteria ProductCriterian = session.createCriteria(Productratemaster.class,"productratemaster");
				ProductCriterian.createAlias("productratemaster.stockmaster", "stockmaster");
				ProductCriterian.createAlias("productratemaster.taxstructuremaster", "taxstructuremaster");
				ProductCriterian.add(Restrictions.eq("stockmaster.stockid", integer));
				ProductCriterian.add(Restrictions.eq("valid", true));
				ProductCriterian.add(Restrictions.le("applicabledate", date));
				ProductCriterian.setFetchMode("productratemaster.taxstructuremaster", FetchMode.EAGER);
				ProductCriterian.setFetchMode("productratemaster.stockmaster", FetchMode.EAGER);
				Productratemaster productratemaster = (Productratemaster) ProductCriterian.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
				if(productratemaster!=null)
				{
					list.add(productratemaster);
				}
				Criteria ProductCriterian1 = session.createCriteria(Productratemaster.class,"productratemaster");
				ProductCriterian1.createAlias("productratemaster.stockmaster", "stockmaster");
				ProductCriterian1.createAlias("productratemaster.taxstructuremaster", "taxstructuremaster");
				ProductCriterian1.add(Restrictions.eq("stockmaster.stockid", integer));
				ProductCriterian1.add(Restrictions.eq("valid", true));
				ProductCriterian1.add(Restrictions.gt("applicabledate", date));
				ProductCriterian1.setFetchMode("productratemaster.taxstructuremaster", FetchMode.EAGER);
				ProductCriterian1.setFetchMode("productratemaster.stockmaster", FetchMode.EAGER);
				List<Productratemaster> productratemaster1 = ProductCriterian1.list();
				if(ProductCriterian1!=null)
				{
					for (Productratemaster productratemaster2 : productratemaster1) {
						list.add(productratemaster2);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("Exception raised in productRateList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public List<Servicegroupmaster> getServiceGroups(String serviceName, int records) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicegroupmaster.class);
		List<Servicegroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServiceGroups Method:", e);
		} finally {
			session.close();
		}
		return list;
		
	}

	@Override
	public List<Dropdowndetails> getGenders() {
		Session session = sessionFactory.openSession();
		Criteria createCriteria = session.createCriteria(Dropdowndetails.class,"dropdown");
		createCriteria.createAlias("dropdown.dropdownmaster","dropdownmaster");
		createCriteria.add(Restrictions.eq("dropdownmaster.dropdownmasterid", 1));
		List<Dropdowndetails> dropdowndetails = createCriteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getGenders Method:", e);
		} finally {
			session.close();
		}
		return dropdowndetails;
	}

	@Override
	public String saveService(Servicemaster serviceMaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceMaster.setValid(true);
			serviceMaster.setUpdatedby("Harika");
			serviceMaster.setUpdatedip(Ipaddress.getIpAddress());
			serviceMaster.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.setProjection(Projections.max("serviceid"));
			Integer serviceId = (Integer) criteria.uniqueResult();
			if(serviceId==null)
			{serviceId=0;}
			serviceMaster.setServiceid(++serviceId);
			session.save(serviceMaster);
			transaction.commit();
			message = serviceMaster.getServicename()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Service is not saved due to Exception!";
			logger.error("Exception raised in saveService Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String editService(Servicemaster serviceMaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceMaster.setValid(true);
			serviceMaster.setUpdatedby("Harika");
			serviceMaster.setUpdatedip(Ipaddress.getIpAddress());
			serviceMaster.setUpdateddate(date);
			session.update(serviceMaster);
			transaction.commit();
			message = serviceMaster.getServicename()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Service is not Edited due to Exception!";
			logger.error("Exception raised in editService Method:", e);
		} finally {
			session.close();
		}
		return message;
	}
	
	@Override
	public String deleteService(Servicemaster serviceMaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceMaster.setValid(false);
			serviceMaster.setUpdatedby("Harika");
			serviceMaster.setUpdatedip(Ipaddress.getIpAddress());
			serviceMaster.setUpdateddate(date);
			session.update(serviceMaster);
			transaction.commit();
			message = serviceMaster.getServicename()+" is Deleted";
		} catch (Exception e) {
			message = "Service is not Deleted due to Exception!";
			logger.error("Exception raised in deleteService Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Servicemaster> getServiceByName(String serviceName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicemaster.class);
		criteria.add(Restrictions.ilike("servicename", serviceName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("serviceid"));
		criteria.setFetchMode("dropdowndetails", FetchMode.EAGER);
		criteria.setFetchMode("servicegroupmaster", FetchMode.EAGER);
		List<Servicemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServiceByName Method:", e);
		} finally {
			session.close();
		}
		return list;

		}

	@Override
	public List<Servicegroupmaster> getServiceGroupList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicegroupmaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("servicegroupid"));
		List<Servicegroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServiceGroupList Method:", e);
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public String saveServiceGroup(Servicegroupmaster serviceGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceGroup.setValid(true);
			serviceGroup.setUpdatedby("Harika");
			serviceGroup.setUpdatedip(Ipaddress.getIpAddress());
			serviceGroup.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Servicegroupmaster.class);
			criteria.setProjection(Projections.max("servicegroupid"));
			Integer serviceGroupId = (Integer) criteria.uniqueResult();
			if(serviceGroupId==null)
			{serviceGroupId=0;}
			serviceGroup.setServicegroupid(++serviceGroupId);
			session.save(serviceGroup);
			transaction.commit();
			message = serviceGroup.getServicegroupname()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Service is not saved due to Exception!";
			logger.error("Exception raised in saveServiceGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String editServiceGroup(Servicegroupmaster serviceGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceGroup.setValid(true);
			serviceGroup.setUpdatedby("Harika");
			serviceGroup.setUpdatedip(Ipaddress.getIpAddress());
			serviceGroup.setUpdateddate(date);
			session.update(serviceGroup);
			transaction.commit();
			message = serviceGroup.getServicegroupname()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Service is not Edited due to Exception!";
			logger.error("Exception raised in editServiceGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteServiceGroup(Servicegroupmaster serviceGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceGroup.setValid(false);
			serviceGroup.setUpdatedby("Harika");
			serviceGroup.setUpdatedip(Ipaddress.getIpAddress());
			serviceGroup.setUpdateddate(date);
			session.update(serviceGroup);
			transaction.commit();
			message = serviceGroup.getServicegroupname()+" is Deleted";
		} catch (Exception e) {
			message = "Service is not Deleted due to Exception!";
			logger.error("Exception raised in deleteService Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Servicegroupmaster> getServiceGroupByName(String serviceGroupName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Servicegroupmaster.class);
		criteria.add(Restrictions.ilike("servicegroupname", serviceGroupName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("servicegroupid"));
		List<Servicegroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServiceGroupByName Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	@Override
	public String savePackage(String packagename, String[] serviceids) {
		String message = null;
		Session session = sessionFactory.openSession();
		Date date = new Date();
		try {
			Transaction beginTransaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Packagemaster.class);
			criteria.setProjection(Projections.max("packageid"));
			Integer packageid = (Integer) criteria.uniqueResult();
			if(packageid==null)
			{
				packageid = 0;
			}
			Criteria criteria1 = session.createCriteria(Packageservices.class);
			criteria1.setProjection(Projections.max("packageserviceid"));
			Integer packageserviceid = (Integer) criteria1.uniqueResult();
			if(packageserviceid==null)
			{
				packageserviceid = 0;
			}
			Packagemaster packagemaster = new Packagemaster();
			packagemaster.setPackageid(++packageid);
			packagemaster.setPackagename(packagename);
			packagemaster.setUpdatedby("Harika");
			packagemaster.setUpdateddate(date);
			packagemaster.setUpdatedip(Ipaddress.getIpAddress());
			packagemaster.setValid(true);
			session.save(packagemaster);
			for (String id : serviceids) {
				Integer serviceid = Integer.parseInt(id);
				Servicemaster servicemaster = new Servicemaster();
				servicemaster.setServiceid(serviceid);
				Packageservices packageservices = new Packageservices();
				packageservices.setPackagemaster(packagemaster);
				packageservices.setServicemaster(servicemaster);
				packageservices.setUpdatedby("Harika");
				packageservices.setUpdateddate(date);
				packageservices.setUpdatedip(Ipaddress.getIpAddress());
				packageservices.setValid(true);
				packageservices.setPackageserviceid(++packageserviceid);
				session.save(packageservices);
			}
			beginTransaction.commit();
			message = "Package Created and services are added to it!";
		} catch (Exception e) {
			message = "Package is not created!";
			logger.error("Exception raised in savePackage Method:", e);
		} finally {
			session.close();
		}

		return message;
	}

	@Override
	public List<Packageservices> getPackageServices() {
		Session session = sessionFactory.openSession();
		List<Packageservices> list = null;
		try {
			Criteria criteria = session.createCriteria(Packageservices.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.setFetchMode("servicemaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getPackageServices Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String editPackage(String packagename, String[] serviceids, Integer packageid) {
		String message = null;
		Session session = sessionFactory.openSession();
		Date date = new Date();
		try {
			Transaction beginTransaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Packagemaster.class);
			Packagemaster packagemaster = (Packagemaster) criteria.add(Restrictions.eq("packageid", packageid)).uniqueResult();
			packagemaster.setPackagename(packagename);
			session.update(packagemaster);
			for (Packageservices packageservice : packagemaster.getPackageserviceses()) {
				packageservice.setValid(false);
				session.update(packageservice);
				}
			for (String id : serviceids) {
				Integer serviceid = Integer.parseInt(id);
				Servicemaster servicemaster = new Servicemaster();
				servicemaster.setServiceid(serviceid);
				Packageservices packageservices = new Packageservices();
				packageservices.setPackagemaster(packagemaster);
				packageservices.setServicemaster(servicemaster);
				packageservices.setUpdatedby("Harika");
				packageservices.setUpdateddate(date);
				packageservices.setUpdatedip(Ipaddress.getIpAddress());
				packageservices.setValid(true);
				Criteria createCriteria = session.createCriteria(Packageservices.class);
				createCriteria.setProjection(Projections.max("packageserviceid"));
				Integer packageserviceid = (Integer) createCriteria.uniqueResult();
				if(packageserviceid==null)
				{
					packageserviceid = 0;
				}
				packageservices.setPackageserviceid(++packageserviceid);
				session.save(packageservices);
			}
			beginTransaction.commit();
			message = "Package and it's services are edited to it!";
		} catch (Exception e) {
			message = "Package is not edited due to exception!";
			logger.error("Exception raised in editPackage Method:", e);
		} finally {
			session.close();
		}

		return message;
	}

	@Override
	public List<Packageservices> getPackageServicebyid(Integer packageid) {
		Session session = sessionFactory.openSession();
		List<Packageservices> list = null;
		try {
			Criteria criteria = session.createCriteria(Packageservices.class,"packages");
			criteria.createAlias("packages.packagemaster", "packagemaster");
			criteria.createAlias("packages.servicemaster", "servicemaster");
			criteria.add(Restrictions.eq("valid", true));
			criteria.add(Restrictions.eq("packagemaster.packageid", packageid));
			criteria.addOrder(Order.asc("packageserviceid"));
			criteria.setFetchMode("packages.packagemaster", FetchMode.EAGER);
			criteria.setFetchMode("packages.servicemaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getPackageServicebyid Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveStockGroup(Stockgroupmaster stockGroupMaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockGroupMaster.setValid(true);
			stockGroupMaster.setUpdatedby("Harika");
			stockGroupMaster.setUpdatedip(Ipaddress.getIpAddress());
			stockGroupMaster.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			criteria.setProjection(Projections.max("stockgroupid"));
			Integer stockGroupId = (Integer) criteria.uniqueResult();
			if(stockGroupId==null)
			{stockGroupId=0;}
			stockGroupMaster.setStockgroupid(++stockGroupId);
			session.save(stockGroupMaster);
			transaction.commit();
			message = stockGroupMaster.getStockgroupname()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Service is not saved due to Exception!";
			logger.error("Exception raised in saveStockGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}
	
	@Override
	public List<Stockgroupmaster> getStockGroupList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Stockgroupmaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("stockgroupid"));
		List<Stockgroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getStockGroupList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String editStockGroup(Stockgroupmaster stockGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockGroup.setValid(true);
			stockGroup.setUpdatedby("Harika");
			stockGroup.setUpdatedip(Ipaddress.getIpAddress());
			stockGroup.setUpdateddate(date);
			session.update(stockGroup);
			transaction.commit();
			message = stockGroup.getStockgroupname()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Stock is not Edited due to Exception!";
			logger.error("Exception raised in editStockGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteStockGroup(Stockgroupmaster stockGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockGroup.setValid(false);
			stockGroup.setUpdatedby("Harika");
			stockGroup.setUpdatedip(Ipaddress.getIpAddress());
			stockGroup.setUpdateddate(date);
			session.update(stockGroup);
			transaction.commit();
			message = stockGroup.getStockgroupname()+" is Deleted";
		} catch (Exception e) {
			message = "Stock is not Deleted due to Exception!";
			logger.error("Exception raised in deleteStockGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Stockgroupmaster> getStockGroupByName(String stockGroupName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Stockgroupmaster.class);
		criteria.add(Restrictions.ilike("stockgroupname", stockGroupName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("stockgroupid"));
		List<Stockgroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getStockGroupByName Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Stockgroupmaster> getStockGroups() {
		Session session = sessionFactory.openSession();
		List<Stockgroupmaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.addOrder(Order.asc("stockgroupid"));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroups Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockSubGroup.setValid(true);
			stockSubGroup.setUpdatedby("Harika");
			stockSubGroup.setUpdatedip(Ipaddress.getIpAddress());
			stockSubGroup.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Stocksubgroupmaster.class);
			criteria.setProjection(Projections.max("stocksubgroupid"));
			Integer stockSubGroupId = (Integer) criteria.uniqueResult();
			if(stockSubGroupId==null)
			{stockSubGroupId=0;}
			stockSubGroup.setStocksubgroupid(++stockSubGroupId);
			session.save(stockSubGroup);
			transaction.commit();
			message = stockSubGroup.getStocksubgroupname()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Stock is not saved due to Exception!";
			logger.error("Exception raised in saveStockSubGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Stocksubgroupmaster> getStockSubGroupList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Stocksubgroupmaster.class,"stocksubgroup");
		criteria.createAlias("stocksubgroup.stockgroupmaster", "stockgroupmaster");
		criteria.setFetchMode("stockgroupmaster.stockgroupmaster", FetchMode.EAGER);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("stocksubgroupid"));
		List<Stocksubgroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getStockSubGroupList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String editStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockSubGroup.setValid(true);
			stockSubGroup.setUpdatedby("Harika");
			stockSubGroup.setUpdatedip(Ipaddress.getIpAddress());
			stockSubGroup.setUpdateddate(date);
			session.update(stockSubGroup);
			transaction.commit();
			message = stockSubGroup.getStocksubgroupname()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Service is not Edited due to Exception!";
			logger.error("Exception raised in editStockSubGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			stockSubGroup.setValid(false);
			stockSubGroup.setUpdatedby("Harika");
			stockSubGroup.setUpdatedip(Ipaddress.getIpAddress());
			stockSubGroup.setUpdateddate(date);
			session.update(stockSubGroup);
			transaction.commit();
			message = stockSubGroup.getStocksubgroupname()+" is Deleted";
		} catch (Exception e) {
			message = "Stock is not Deleted due to Exception!";
			logger.error("Exception raised in deleteStockSubGroup Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Stocksubgroupmaster> getStockSubGroupByName(String stockSubGroupName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Stocksubgroupmaster.class);
		criteria.add(Restrictions.ilike("stocksubgroupname", stockSubGroupName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("stocksubgroupid"));
		criteria.setFetchMode("stockgroupmaster", FetchMode.EAGER);
		List<Stocksubgroupmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getStockSubGroupByName Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Billinginstructionmaster> getBillingInstructionList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Billinginstructionmaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("billinginstructionid"));
		List<Billinginstructionmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getBillingInstructionList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveBillingInstruction(Billinginstructionmaster billingInstruction) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			billingInstruction.setValid(true);
			billingInstruction.setUpdatedby("Harika");
			billingInstruction.setUpdatedip(Ipaddress.getIpAddress());
			billingInstruction.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Billinginstructionmaster.class);
			criteria.setProjection(Projections.max("billinginstructionid"));
			Integer billingInstructionId = (Integer) criteria.uniqueResult();
			if(billingInstructionId==null)
			{billingInstructionId=0;}
			billingInstruction.setBillinginstructionid(++billingInstructionId);
			session.save(billingInstruction);
			transaction.commit();
			message = billingInstruction.getInstructiondescription()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Billinginstruction is not saved due to Exception!";
			logger.error("Exception raised in saveBillingInstruction Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String editBillingInstruction(Billinginstructionmaster billingInstruction) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			billingInstruction.setValid(true);
			billingInstruction.setUpdatedby("Harika");
			billingInstruction.setUpdatedip(Ipaddress.getIpAddress());
			billingInstruction.setUpdateddate(date);
			session.update(billingInstruction);
			transaction.commit();
			message = billingInstruction.getInstructiondescription()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Billinginstruction is not Edited due to Exception!";
			logger.error("Exception raised in editBillingInstruction Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteBillingInstruction(Billinginstructionmaster billingInstruction) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			billingInstruction.setValid(false);
			billingInstruction.setUpdatedby("Harika");
			billingInstruction.setUpdatedip(Ipaddress.getIpAddress());
			billingInstruction.setUpdateddate(date);
			session.update(billingInstruction);
			transaction.commit();
			message = billingInstruction.getInstructiondescription()+" is Deleted";
		} catch (Exception e) {
			message = "BillingInstruction is not Deleted due to Exception!";
			logger.error("Exception raised in deleteBillingInstruction Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Billinginstructionmaster> searchBillingInstruction(String description) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Billinginstructionmaster.class);
		criteria.add(Restrictions.ilike("instructiondescription", description, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("billinginstructionid"));
		List<Billinginstructionmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in searchBillingInstruction Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Businesssourcemaster> getBusinessSourceList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Businesssourcemaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("businesssourceid"));
		List<Businesssourcemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getBusinessSourceList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveBusinessSource(Businesssourcemaster businessSource) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			businessSource.setValid(true);
			businessSource.setUpdatedby("Harika");
			businessSource.setUpdatedip(Ipaddress.getIpAddress());
			businessSource.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Businesssourcemaster.class);
			criteria.setProjection(Projections.max("businesssourceid"));
			Integer businessSourceId = (Integer) criteria.uniqueResult();
			if(businessSourceId==null)
			{businessSourceId=0;}
			businessSource.setBusinesssourceid(++businessSourceId);
			session.save(businessSource);
			transaction.commit();
			message = businessSource.getSourcedescription()+" is successfully saved.:";
		} catch (Exception e) {
			message = "BusinessSource is not saved due to Exception!";
			logger.error("Exception raised in saveBusinessSource Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String editBusinessSource(Businesssourcemaster businessSource) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			businessSource.setValid(true);
			businessSource.setUpdatedby("Harika");
			businessSource.setUpdatedip(Ipaddress.getIpAddress());
			businessSource.setUpdateddate(date);
			session.update(businessSource);
			transaction.commit();
			message = businessSource.getSourcedescription()+" is successfully Edited:";
		} catch (Exception e) {
			message = "BusinessSource is not Edited due to Exception!";
			logger.error("Exception raised in editBusinessSource Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteBusinessSource(Businesssourcemaster businessSource) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			businessSource.setValid(false);
			businessSource.setUpdatedby("Harika");
			businessSource.setUpdatedip(Ipaddress.getIpAddress());
			businessSource.setUpdateddate(date);
			session.update(businessSource);
			transaction.commit();
			message = businessSource.getSourcedescription()+" is Deleted";
		} catch (Exception e) {
			message = "BusinessSource is not Deleted due to Exception!";
			logger.error("Exception raised in deleteBusinessSource Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Businesssourcemaster> getsearchBusinessSource(String description) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Businesssourcemaster.class);
		criteria.add(Restrictions.ilike("sourcedescription", description, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("businesssourceid"));
		List<Businesssourcemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getsearchBusinessSource Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Giftvouchermaster> getGiftVoucherList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Giftvouchermaster.class);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("giftvoucherid"));
		List<Giftvouchermaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getGiftVoucherList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveGiftVoucher(Giftvouchermaster giftVoucher) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			giftVoucher.setValid(true);
			giftVoucher.setUpdatedby("Harika");
			giftVoucher.setUpdatedip(Ipaddress.getIpAddress());
			giftVoucher.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Giftvouchermaster.class);
			criteria.setProjection(Projections.max("giftvoucherid"));
			Integer giftVoucherId = (Integer) criteria.uniqueResult();
			if(giftVoucherId==null)
			{giftVoucherId=0;}
			giftVoucher.setGiftvoucherid(++giftVoucherId);
			session.save(giftVoucher);
			transaction.commit();
			message = giftVoucher.getGiftvoucheramount()+" is successfully saved.:";
		} catch (Exception e) {
			message = "GiftAmount is not saved due to Exception!";
			logger.error("Exception raised in saveBusinessSource Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Corporatemaster> getCorporateList() {
		
		Session session = sessionFactory.openSession();
		List<Corporatemaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Corporatemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.setFetchMode("corporatetypemaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getCorporateList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Creditlistmaster> getCreditListDetails() {
		Session session = sessionFactory.openSession();
		List<Creditlistmaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Creditlistmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			criteria.setFetchMode("corporatemaster", FetchMode.EAGER);
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getCreditListDetails Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String saveCreditList(Creditlistmaster creditlistmaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			creditlistmaster.setValid(true);
			creditlistmaster.setUpdatedby("Harika");
			creditlistmaster.setUpdatedip(Ipaddress.getIpAddress());
			creditlistmaster.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Creditlistmaster.class);
			criteria.setProjection(Projections.max("creditlistid"));
			Integer creditlistid = (Integer) criteria.uniqueResult();
			if(creditlistid==null)
			{creditlistid=0;}
			creditlistmaster.setCreditlistid(++creditlistid);
			session.save(creditlistmaster);
			transaction.commit();
			message = "CreditList is successfully saved!";
		} catch (Exception e) {
			message = "CreditList is not saved due to Exception!";
			logger.error("Exception raised in saveCreditList Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteCreditList(int parseInt) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			Criteria criteria = session.createCriteria(Creditlistmaster.class);
			criteria.add(Restrictions.eq("creditlistid", parseInt));
			Creditlistmaster creditlistmaster = (Creditlistmaster) criteria.uniqueResult();
			creditlistmaster.setValid(false);
			creditlistmaster.setUpdateddate(date);
			session.update(creditlistmaster);
			transaction.commit();
			message = "CreditList is successfully deleted!";
		} catch (Exception e) {
			message = "CreditList is not deleted due to Exception!";
			logger.error("Exception raised in deleteCreditList Method:", e);
		} finally {
			session.close();
		}
		return message;
	}


	@Override
	public List<Citymaster> getCities() {
		Session session = sessionFactory.openSession();
		List<Citymaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Citymaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getCities Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Statemaster> getStates() {
		Session session = sessionFactory.openSession();
		List<Statemaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Statemaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStates Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Countrymaster> getCountries() {
		Session session = sessionFactory.openSession();
		List<Countrymaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Countrymaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getCountries Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Departmentmaster> getDepartments() {
		Session session = sessionFactory.openSession();
		List<Departmentmaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Departmentmaster.class);
			criteria.add(Restrictions.eq("valid", true));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getDepartments Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<String> getStateCountryByCity(Integer cityId) {
		Session session = sessionFactory.openSession();
		List<String> list = new LinkedList<String>();
		 try {
			Criteria criteria = session.createCriteria(Citymaster.class,"city");
			criteria.createAlias("city.statemaster", "state");
			criteria.createAlias("state.countrymaster", "country");
			criteria.add(Restrictions.eq("city.valid", true));
			criteria.add(Restrictions.eq("city.cityid", cityId));
			criteria.setFetchMode("city.statemaster", FetchMode.EAGER);
			criteria.setFetchMode("state.countrymaster", FetchMode.EAGER);
			Citymaster citymaster = (Citymaster) criteria.uniqueResult();
			list.add(citymaster.getStatemaster().getStatename());
			list.add(citymaster.getStatemaster().getCountrymaster().getCountryname());
		} catch (Exception e) {
			logger.error("Exception raised in getStateCountryByCity Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Packagemaster> getPackages() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Packagemaster.class,"packages");
		criteria.add(Restrictions.eq("packages.valid", true));
		List<Packagemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getPackages Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	

	@Override
	public String saveServiceRoom(Serviceroommaster serviceroom) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceroom.setValid(true);
			serviceroom.setUpdatedby("Harika");
			serviceroom.setUpdatedip(Ipaddress.getIpAddress());
			serviceroom.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Serviceroommaster.class);
			criteria.setProjection(Projections.max("serviceroomid"));
			Integer serviceRoomId = (Integer) criteria.uniqueResult();
			if(serviceRoomId==null)
			{serviceRoomId=0;}
			serviceroom.setServiceroomid(++serviceRoomId);
			session.save(serviceroom);
			transaction.commit();
			message = "Service Room is successfully saved.:";
		} catch (Exception e) {
			message = "Service Room is not saved due to Exception!";
			logger.error("Exception raised in saveServiceRoom Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Serviceroommaster> getServiceRoomList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Serviceroommaster.class,"serviceroom");
		criteria.createAlias("serviceroom.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("serviceroom.roommaster", "roommaster");
		criteria.setFetchMode("roommaster.roommaster", FetchMode.EAGER);
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("serviceroomid"));
		List<Serviceroommaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServiceRoomList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String editServiceRoom(Serviceroommaster serviceroom) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceroom.setValid(true);
			serviceroom.setUpdatedby("Harika");
			serviceroom.setUpdatedip(Ipaddress.getIpAddress());
			serviceroom.setUpdateddate(date);
			session.update(serviceroom);
			transaction.commit();
			message = "Service Room is successfully Edited:";
		} catch (Exception e) {
			message = "Service Room is not Edited due to Exception!";
			logger.error("Exception raised in editServiceRoom Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteServiceRoom(Serviceroommaster serviceroom) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			serviceroom.setValid(false);
			serviceroom.setUpdatedby("Harika");
			serviceroom.setUpdatedip(Ipaddress.getIpAddress());
			serviceroom.setUpdateddate(date);
			session.update(serviceroom);
			transaction.commit();
			message = "Service Room is Deleted";
		} catch (Exception e) {
			message = "Service Room is not Deleted due to Exception!";
			logger.error("Exception raised in deleteServiceRoom Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Serviceroommaster> getsearchServiceRoom(String serviceName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Serviceroommaster.class,"serviceroom");
		criteria.createAlias("serviceroom.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("serviceroom.roommaster", "roommaster");
		criteria.setFetchMode("roommaster.roommaster", FetchMode.EAGER);

		criteria.add(Restrictions.ilike("servicemaster.servicename", serviceName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		List<Serviceroommaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getsearchServiceRoom Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String savePackageRate(Integer packageId, Integer taxStructureId, Packageratemaster packagerate) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			Criteria criteria = session.createCriteria(Packagemaster.class);
			criteria.add(Restrictions.eq("packageid",packageId));
			Criteria criteria1 = session.createCriteria(Taxstructuremaster.class);
			criteria1.add(Restrictions.eq("sno",taxStructureId));
			Taxstructuremaster uniqueResult = (Taxstructuremaster) criteria1.uniqueResult();
			Packagemaster packagemaster=(Packagemaster) criteria.uniqueResult();
			packagerate.setTaxstructuremaster(uniqueResult);
			packagerate.setTaxstructureid(taxStructureId);
			packagerate.setPackagemaster(packagemaster);
			Criteria criteria2 = session.createCriteria(Packageratemaster.class);
			criteria2.setProjection(Projections.max("packagerateid"));
			Integer id = (Integer) criteria2.uniqueResult();
			if(id==null){
				id=0;
			}
			packagerate.setPackagerateid(id+1);
			packagerate.setUpdatedby("Harika");
			packagerate.setUpdateddate(date);
			packagerate.setUpdatedip("172.22.1.17");
			packagerate.setValid(true);
			session.save(packagerate);
			transaction.commit();
			message = "servicerate of "+packagerate.getPackagecost()+" are Successfully saved!";
			}catch(Exception e){
				message = "packagerate of "+packagerate.getPackagecost()+" are not saved!";
				logger.error("Exception raised in savePackageRate Method:",e);
			}
			finally{
			session.close();
			}
		return message;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public List<Packageratemaster> packageRateList() {
		Session session = sessionFactory.openSession();
		List<Packageratemaster> list =new LinkedList<Packageratemaster>();
		try{
			Date date = new Date();
			Criteria criteria = session.createCriteria(Packageratemaster.class,"packagerate");
			criteria.createAlias("packagerate.taxstructuremaster", "taxstructuremaster");
			criteria.createAlias("packagerate.packagemaster", "packagemaster");
			criteria.add(Restrictions.eq("valid", true));
			criteria.setProjection(Projections.distinct(Projections.property("packagemaster.packageid")));
			List<Integer> uniqueResult = criteria.list();
			for (Integer integer : uniqueResult) {
				Criteria packageCriterian = session.createCriteria(Packageratemaster.class,"packagerate");
				packageCriterian.createAlias("packagerate.taxstructuremaster", "taxstructuremaster");
				packageCriterian.createAlias("packagerate.packagemaster", "packagemaster");
				packageCriterian.add(Restrictions.eq("packagemaster.packageid", integer));
				packageCriterian.add(Restrictions.eq("valid", true));
				packageCriterian.add(Restrictions.le("applicabledate", date));
				packageCriterian.setFetchMode("packagerate.taxstructuremaster", FetchMode.EAGER);
				packageCriterian.setFetchMode("packagerate.packagemaster", FetchMode.EAGER);
				Packageratemaster packageratemaster = (Packageratemaster) packageCriterian.addOrder(Order.desc("applicabledate")).setMaxResults(1).uniqueResult();
				if(packageratemaster!=null)
				{
					list.add(packageratemaster);
				}
				Criteria packageCriterian1 = session.createCriteria(Packageratemaster.class,"packagerate");
				packageCriterian1.createAlias("packagerate.taxstructuremaster", "taxstructuremaster");
				packageCriterian1.createAlias("packagerate.packagemaster", "packagemaster");
				packageCriterian1.add(Restrictions.eq("packagemaster.packageid", integer));
				packageCriterian1.add(Restrictions.eq("valid", true));
				packageCriterian1.add(Restrictions.gt("applicabledate", date));
				packageCriterian1.setFetchMode("packagerate.taxstructuremaster", FetchMode.EAGER);
				packageCriterian1.setFetchMode("packagerate.packagemaster", FetchMode.EAGER);
				List<Packageratemaster> packageratemaster1 = packageCriterian1.list();
				if(packageratemaster1!=null)
				{
					for (Packageratemaster packageratemaster2 : packageratemaster1) {
						list.add(packageratemaster2);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("Exception raised in packageRateList Method:", e);
		} finally {
			session.close();
		}
		return list;
			
	}

	@Override
	public List<Corporatetypemaster> getCorporateTypeList(String corporateName,int records) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		int recordsPerPage=records*5-5;
		if(corporateName==null){
		Criteria criteria = session.createCriteria(Corporatetypemaster.class);
		criteria.add(Restrictions.ilike("corporatetype", corporateName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("corporatetypeid"));
		List<Corporatetypemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getCorporateTypeList Method:", e);
		} finally {
			tx.commit();
		
			session.close();
		}
		return list;
	}
		else{
			
			Criteria criteria = session.createCriteria(Corporatetypemaster.class);
			criteria.add(Restrictions.ilike("corporatetype", corporateName, MatchMode.ANYWHERE));
			
			criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);;
			criteria.addOrder(Order.asc("corporatetypeid"));
			List<Corporatetypemaster> list = criteria.list();
			
			try {

			} catch (Exception e) {
				logger.error("Exception raised in  getCorporateTypeList Method:", e);
			} finally {
				tx.commit();
				session.close();
			}
			return list;
		}
		
	}

	@Override
	public String saveCorporateType(Corporatetypemaster corporateType) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			corporateType.setValid(true);
			corporateType.setUpdatedby("Harika");
			corporateType.setUpdatedip(Ipaddress.getIpAddress());
			corporateType.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Corporatetypemaster.class);
			criteria.setProjection(Projections.max("corporatetypeid"));
			Integer corporateTypeId = (Integer) criteria.uniqueResult();
			if(corporateTypeId==null)
			{corporateTypeId=0;}
			corporateType.setCorporatetypeid(++corporateTypeId);
			session.save(corporateType);
			transaction.commit();
			message = corporateType.getCorporatetype()+" is successfully saved.:";
		} catch (Exception e) {
			message = "Corporate Type is not saved due to Exception!";
			logger.error("Exception raised in saveCorporateType Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String editCorporateType(Corporatetypemaster corporateType) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			corporateType.setValid(true);
			corporateType.setUpdatedby("Harika");
			corporateType.setUpdatedip(Ipaddress.getIpAddress());
			corporateType.setUpdateddate(date);
			session.update(corporateType);
			transaction.commit();
			message = corporateType.getCorporatetype()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Corporate Type is not Edited due to Exception!";
			logger.error("Exception raised in editCorporateType Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteCorporateType(Corporatetypemaster corporateType) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			corporateType.setValid(false);
			corporateType.setUpdatedby("Harika");
			corporateType.setUpdatedip(Ipaddress.getIpAddress());
			corporateType.setUpdateddate(date);
			session.update(corporateType);
			transaction.commit();
			message = corporateType.getCorporatetype()+" is Deleted";
		} catch (Exception e) {
			message = "Corporate Type is not Deleted due to Exception!";
			logger.error("Exception raised in deleteCorporateType Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deletePackageRate(Integer packageId, Integer taxStructureId, Packageratemaster packagerate) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			Criteria criteria = session.createCriteria(Packagemaster.class);
			criteria.add(Restrictions.eq("packageid",packageId));
			Criteria criteria1 = session.createCriteria(Taxstructuremaster.class);
			criteria1.add(Restrictions.eq("sno",taxStructureId));
			Taxstructuremaster uniqueResult = (Taxstructuremaster) criteria1.uniqueResult();
			Packagemaster packagemaster=(Packagemaster) criteria.uniqueResult();
			packagerate.setTaxstructuremaster(uniqueResult);
			packagerate.setTaxstructureid(uniqueResult.getTaxstructureid());
			packagerate.setPackagemaster(packagemaster);
			Criteria criteria2 = session.createCriteria(Packageratemaster.class);
			criteria2.setProjection(Projections.max("packagerateid"));
			Integer id = (Integer) criteria2.uniqueResult();
			if(id==null){
				id=0;
			}
			packagerate.setPackagerateid(id+1);
			packagerate.setUpdatedby("Harika");
			packagerate.setUpdateddate(date);
			packagerate.setUpdatedip("172.22.1.17");
			packagerate.setValid(true);
			session.update(packagerate);
			transaction.commit();
			message = "servicerate of "+packagerate.getPackagecost()+" are Successfully Deleted!";
			}catch(Exception e){
				message = "packagerate of "+packagerate.getPackagecost()+" are not Deleted!";
				logger.error("Exception raised in deletePackageRate Method:",e);
			}
			finally{
			session.close();
			}
		return message;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Packagemaster getPackageById(Integer packageid) {
		Session session = sessionFactory.openSession();
		Packagemaster list = null;
		try {
			Criteria criteria = session.createCriteria(Packagemaster.class,"package");
			criteria.createAlias("package.packageserviceses", "packageservice");
			criteria.add(Restrictions.eq("package.packageid", packageid));
			criteria.add(Restrictions.eq("package.valid", true));
			criteria.setFetchMode("package.packageserviceses", FetchMode.EAGER);
			criteria.setFetchMode("packageservice.servicemaster", FetchMode.EAGER);
			list = (Packagemaster) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception raised in getPackageServices Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String deletePackage(Integer packageid) {
		Session session = sessionFactory.openSession();
		Packagemaster packageMaster = null;
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			System.out.println("PackageID:"+packageid);
			Criteria criteria = session.createCriteria(Packagemaster.class,"package");
			criteria.add(Restrictions.eq("package.packageid", packageid));
			criteria.add(Restrictions.eq("package.valid", true));
			criteria.setFetchMode("package.packageserviceses", FetchMode.EAGER);
			packageMaster = (Packagemaster) criteria.uniqueResult();
			packageMaster.setValid(false);
			packageMaster.setUpdatedby("Harika");
			packageMaster.setUpdatedip(Ipaddress.getIpAddress());
			packageMaster.setUpdateddate(new Date());
			session.update(packageMaster);
			for (Packageservices packageservice : packageMaster.getPackageserviceses()) {
				packageservice.setValid(false);
				packageservice.setUpdatedby("Harika");
				packageservice.setUpdateddate(new Date());
				packageservice.setUpdatedip(Ipaddress.getIpAddress());
				session.update(packageservice);
			}
			transaction.commit();
			message = "Successfully deleted of Package with PackageId = "+packageid+"!";
		} catch (Exception e) {
			message = "Package with PackageId = "+packageid+" is not deleted!";
			logger.error("Exception raised in getPackageServices Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String saveStaff(Staffmaster staff) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			staff.setValid(true);
			staff.setUpdatedby("Harika");
			staff.setUpdatedip(Ipaddress.getIpAddress());
			staff.setUpdateddate(date);
			Criteria criteria = session.createCriteria(Staffmaster.class);
			criteria.setProjection(Projections.max("staffid"));
			Integer staffId = (Integer) criteria.uniqueResult();
			if(staffId==null)
			{staffId=0;}
			staff.setStaffid(++staffId);
			session.save(staff);
			transaction.commit();
			message = "Staff is successfully saved.:";
		} catch (Exception e) {
			message = "Staff is not saved due to Exception!";
			logger.error("Exception raised in saveStaff Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<String> getServicesByPackageId(Integer packageId) {
		Session session = sessionFactory.openSession();
		List<Packageservices> list = null;
		List<String> servicesList = new LinkedList<String>();
		try {
			Criteria criteria = session.createCriteria(Packageservices.class,"packageservice");
			criteria.createAlias("packageservice.packagemaster", "packages");
			criteria.createAlias("packageservice.servicemaster", "service");
			criteria.add(Restrictions.eq("packages.packageid", packageId));
			criteria.add(Restrictions.eq("packages.valid", true));
			criteria.add(Restrictions.eq("packageservice.valid", true));
			criteria.setFetchMode("service.servicemaster", FetchMode.EAGER);
			list = criteria.list();
			for (Packageservices packageservices : list) {
				servicesList.add(packageservices.getServicemaster().getServicename());
			}
		} catch (Exception e) {
			logger.error("Exception raised in getPackageServices Method:", e);
		} finally {
			session.close();
		}
		return servicesList;
	}

	@Override
	public Pagination corporateCount(String corporateName) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(corporateName==null){
			Criteria criteria = session.createCriteria(Corporatetypemaster.class);
			criteria.add(Restrictions.eq("valid", true));
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
			Criteria criteria = session.createCriteria(Corporatetypemaster.class);
			criteria.add(Restrictions.ilike("corporatetype",corporateName,MatchMode.ANYWHERE));
			//
			criteria.add(Restrictions.eq("valid", true));
			Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
			int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
			Pagination pagination=Pagination.getInstance();
			pagination.setTotalRecs(totalRecs);
			pagination.setNoOfPages(noOfPages);
			try {

			} catch (Exception e) {
				logger.error("Exception raised in  corporateCount Method:", e);
			} finally {
				tx.commit();
				session.close();
			}
			return pagination;
		}
	}

	@Override
	public List<Serviceratemaster> searchserviceRateList(String serviceName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Serviceratemaster.class,"servicerate");
		criteria.createAlias("servicerate.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("servicerate.taxstructuremaster", "taxstructuremaster");
		criteria.setFetchMode("taxstructuremaster.taxstructuremaster", FetchMode.EAGER);

		criteria.add(Restrictions.ilike("servicemaster.servicename", serviceName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		List<Serviceratemaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in searchserviceRateList Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Taxmaster> searchTaxByName(String taxName) {
		Session session = sessionFactory.openSession();
		List<Taxmaster> list = null;
		try {
			Criteria criteria = session.createCriteria(Taxmaster.class);
			criteria.add(Restrictions.ilike("taxname", taxName, MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("valid", true));
			criteria.addOrder(Order.asc("taxmasterid"));
			list = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in searchTaxByName Method:", e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String editTax(Taxmaster tax) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			tax.setValid(true);
			tax.setUpdatedby("Harika");
			tax.setUpdatedip(Ipaddress.getIpAddress());
			tax.setUpdateddate(date);
			session.update(tax);
			transaction.commit();
			message =" tax is successfully Edited:";
		} catch (Exception e) {
			message = "tax is not Edited due to Exception!";
			logger.error("Exception raised in editTax Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public String deleteTax(Taxmaster tax) {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			tax.setValid(false);
			tax.setUpdatedby("Harika");
			tax.setUpdatedip(Ipaddress.getIpAddress());
			tax.setUpdateddate(date);
			session.update(tax);
			transaction.commit();
			message =" tax is successfully Deleted:";
		} catch (Exception e) {
			message = "tax is not deleted due to Exception!";
			logger.error("Exception raised in deleteTax Method:", e);
		} finally {
			session.close();
		}
		return message;
	}

	@Override
	public List<Staffmaster> getStaffList() {
		Session session = sessionFactory.openSession();
		List<Staffmaster> staffList = null;
		try{
			Criteria criteria = session.createCriteria(Staffmaster.class,"staff");
			criteria.add(Restrictions.eq("staff.valid", true));
			criteria.createAlias("staff.citymaster", "citymaster");
			criteria.createAlias("citymaster.statemaster", "statemaster");
			criteria.createAlias("statemaster.countrymaster", "countrymaster");
			criteria.setFetchMode("staff.citymaster", FetchMode.EAGER);
			criteria.setFetchMode("statemaster.countrymaster", FetchMode.EAGER);
			criteria.setFetchMode("staff.dropdowndetails", FetchMode.EAGER);
			criteria.setFetchMode("citymaster.statemaster", FetchMode.EAGER);
			staffList = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in getStaffList Method:",e);
		}
		finally{
			session.close();
		}
			return staffList;
		
		}

	@Override
	public Staffmaster fetchStaffWithId(Integer id) {
		Session session = sessionFactory.openSession();
		Staffmaster staffMaster=new Staffmaster();
		try{
		Criteria criteria = session.createCriteria(Staffmaster.class);
		criteria.add(Restrictions.eq("supplierid", id));
		staffMaster = (Staffmaster) criteria.uniqueResult();
		Date date=new Date();
		}catch(Exception e){
			logger.error("Exception raised in fetchStaffWithId method:",e);
		}
		session.close();
		return staffMaster;
	}

	@Override
	public String editStaff(Staffmaster staffMaster) {
		Session session = sessionFactory.openSession();
		String message = null;
		try{
		Transaction transaction = session.beginTransaction();
		Date date=new Date();
		staffMaster.setUpdateddate(date);
		staffMaster.setUpdatedby("Harika");
		staffMaster.setUpdatedip("172.22.1.17");
		staffMaster.setValid(true);
		session.update(staffMaster);
		transaction.commit();
		message =" staff is successfully updated:";
		}catch(Exception e){
			message = "staff is not deleted due to Exception!";
			logger.error("Exception raised in editStaff method:",e);
		}
		session.close();
	return message;
	}

	@Override
	public Pagination serviceCount(String serviceName) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(serviceName==null){
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.eq("valid", true));
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
			Criteria criteria = session.createCriteria(Servicemaster.class);
			criteria.add(Restrictions.ilike("servicename",serviceName,MatchMode.ANYWHERE));
			//
			criteria.add(Restrictions.eq("valid", true));
			Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
			int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
			Pagination pagination=Pagination.getInstance();
			pagination.setTotalRecs(totalRecs);
			pagination.setNoOfPages(noOfPages);
			try {

			} catch (Exception e) {
				logger.error("Exception raised in  serviceCount Method:", e);
			} finally {
				tx.commit();
				session.close();
			}
			return pagination;
		}
	}
	

	
	

	

	@Override
	public List<Taxmaster> getsearchTax(String taxName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Taxmaster.class);
		criteria.add(Restrictions.ilike("taxname", taxName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		criteria.addOrder(Order.asc("taxmasterid"));
		List<Taxmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getsearchTax Method:", e);
		} finally {
			session.close();
		}
		return list;
	}
	

	public void saveDepartment(Departmentmaster departmentmaster){
	Session session = sessionFactory.openSession();
	try{
		Transaction transaction = session.beginTransaction();
	Criteria criteria = session.createCriteria(Departmentmaster.class);
	criteria.setProjection(Projections.max("departmentid"));
	Integer id = (Integer) criteria.uniqueResult();
	if(id==null){
		departmentmaster.setDepartmentid(1);
	}else{
		departmentmaster.setDepartmentid(id+1);
	}
	
	departmentmaster.setUpdatedby("anil");
	Date date = new Date();
	departmentmaster.setUpdateddate(date);
	departmentmaster.setUpdatedip("172.22.1.17");
	session.save(departmentmaster);
	transaction.commit();
	}
	 catch (Exception e) {
		logger.error("Exception raised in addReason Method:", e);
	} finally {
		session.close();
	}
	}
	
		
	

public List<Departmentmaster> getDepartment(String departmentname) {
	Session session=sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	Criteria cr= session.createCriteria(Departmentmaster.class);
	cr.add(Restrictions.ilike("departmentname",departmentname, MatchMode.ANYWHERE));
	cr.add(Restrictions.eq("valid",true));
	cr.addOrder(Order.asc("departmentid"));
	List<Departmentmaster> results = cr.list();
	
	return results;
	
}



public Departmentmaster editDepartment(Integer depId) {
	Session session=sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	 Departmentmaster departmentmaster = ( Departmentmaster) session.load( Departmentmaster.class,  depId);
	
	 return departmentmaster;
}

public void updateDepartment(Departmentmaster departmentmaster) {
	
	Session session=sessionFactory.openSession();
	try{
		
	
	System.out.println(departmentmaster.isValid());
	Transaction transaction = session.beginTransaction();
	int departmentid = departmentmaster.getDepartmentid();
	Departmentmaster departmentmaster123 = (Departmentmaster)session.load(Departmentmaster.class,departmentid);
	departmentmaster123.setDepartmentid(departmentmaster.getDepartmentid());
	departmentmaster123.setDepartmentname(departmentmaster.getDepartmentname());
	departmentmaster123.setDescription(departmentmaster.getDescription());
	departmentmaster123.setValid(departmentmaster.isValid());
    session.update(departmentmaster123);
	transaction.commit();
	}catch(Exception e) {
		logger.error("Exception raised in addReason Method:", e);
	} finally {
		session.close();
	}
	
}

@Override
public List<Departmentmaster> listDepartments(Departmentmaster departmentmaster, int records, String pageName,String search) {
	Session session=sessionFactory.openSession();
	if(pageName == null)
	{
		Transaction tx=session.beginTransaction();
		int recordsPerPage=records*5-5;
		List<Departmentmaster> agentList = sessionFactory.openSession().createSQLQuery("select departmentid,departmentname,description,valid,updatedby,updateddate,updatedip from  departmentmaster ORDER BY departmentid ASC" ).setFirstResult(recordsPerPage).setMaxResults(5).list();
		tx.commit();
		session.close();
		return agentList;
	}
	else
	{
		Transaction tx=session.beginTransaction();
		int recordsPerPage=records*5-5;
		List<Departmentmaster> agentList = sessionFactory.openSession().createSQLQuery("select departmentid,departmentname,description,valid,updatedby,updateddate,updatedip from departmentmaster where departmentname ilike '%"+search+"%' ORDER BY departmentid ASC").setFirstResult(recordsPerPage).setMaxResults(5).list();
		tx.commit();
		session.close();
		return agentList;
	}
		}

@Override
public Pagination deparmentCount(Departmentmaster departmentmaster, String pageName,String search) {

	Session session=sessionFactory.openSession();
	if(pageName == null){
	Transaction tx=session.beginTransaction();
	Criteria crit=session.createCriteria(Departmentmaster.class);
	Long totalRecs= (Long)crit.setProjection(Projections.rowCount()).uniqueResult();
	int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
	Pagination pagination=Pagination.getInstance();
	pagination.setTotalRecs(totalRecs);
	pagination.setNoOfPages(noOfPages);
	tx.commit();
	session.close();
	return pagination;
	}
	else{
	Transaction tx1=session.beginTransaction();
	@SuppressWarnings("unchecked")
	List<Departmentmaster> list = sessionFactory.openSession().createSQLQuery("select departmentid,departmentname from departmentmaster where (departmentname ilike '%"+search+"%') ").list();
    int  totalRecs=list.size();
	int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
	Pagination pagination=Pagination.getInstance();
	pagination.setTotalRecs(totalRecs);
	pagination.setNoOfPages(noOfPages);
	tx1.commit();
	session.close();
    return pagination;
	}
}

@Override
public String saveSkills(Skillmaster Skillmaster) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		Skillmaster.setValid(true);
		Skillmaster.setUpdatedby("santhosh");
		Skillmaster.setUpdatedip("172.22.1.17");
		Skillmaster.setUpdateddate(date);
		Criteria criteria = session.createCriteria(Skillmaster.class);
		criteria.setProjection(Projections.max("skillid"));
		Integer skillid = (Integer) criteria.uniqueResult();
		if(skillid==null)
		{skillid=0;}
		Skillmaster.setSkillid(++skillid);
		session.save(Skillmaster);
		transaction.commit();
		message = Skillmaster.getSkillname()+" is successfully saved.:";
	} catch (Exception e) {
		message = "Service is not saved due to Exception!";
		logger.error("Exception raised in saveServiceGroup Method:", e);
	} finally {
		session.close();
	}
	return message;
}


@Override
public List<Skillmaster> getSkillslist() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Skillmaster.class);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("skillid"));
	List<Skillmaster> skillslist = criteria.list();

	try {

	} catch (Exception e) {
		logger.error("Exception raised in getTaxS Method:", e);
	} finally {
		session.close();
	}
	return skillslist;

}

@Override
public String editSkills(Skillmaster skillmaster) {
	 {
		Session session = sessionFactory.openSession();
		String message = null;
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			skillmaster.setValid(true);
			skillmaster.setUpdatedby("santhosh");
			skillmaster.setUpdatedip("172.22.1.17");
			skillmaster.setUpdateddate(date);
			session.update(skillmaster);
			transaction.commit();
			message = skillmaster.getSkillname()+" is successfully Edited:";
		} catch (Exception e) {
			message = "Skill is not Edited due to Exception!";
			logger.error("Exception raised in editSkills Method:", e);
		} finally {
			session.close();
		}
		return message;
	 }
	}
	

@Override
public String deleteSkills(Skillmaster skillmaster) {
			Session session = sessionFactory.openSession();
			String message = null;
			try {
				Transaction transaction = session.beginTransaction();
				Date date = new Date();
				skillmaster.setValid(false);
				skillmaster.setUpdatedby("santhosh");
				skillmaster.setUpdatedip("172.22.1.17");
				skillmaster.setUpdateddate(date);
				session.update(skillmaster);
				transaction.commit();
				message = skillmaster.getSkillname()+" is Deleted";
			} catch (Exception e) {
				message = "Skill is not Deleted due to Exception!";
				logger.error("Exception raised in deleteSkill Method:", e);
			} finally {
				session.close();
			}
			return message;
}
@Override
public List<Skillmaster> getByName(String skillname,int records) {
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(skillname==null){
		Criteria criteria = session.createCriteria(Skillmaster.class);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("skillid"));
		List<Skillmaster> sklist = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return sklist;
	}
	
	else{
		
		Criteria criteria = session.createCriteria(Skillmaster.class);
		criteria.add(Restrictions.ilike("skillname",skillname,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("skillid"));
		List<Skillmaster> sklist = criteria.list();
		
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return sklist;
	}
	
	
	
}


@Override
public Pagination skillCount(String skillname) {
	
	
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(skillname==null){
		Criteria criteria = session.createCriteria(Skillmaster.class);
		criteria.add(Restrictions.eq("valid", true));
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
		Criteria criteria = session.createCriteria(Skillmaster.class);
		criteria.add(Restrictions.ilike("skillname",skillname,MatchMode.ANYWHERE));
		//
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillcount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
}





@Override
public String saveQualification(Qualificationmaster qualificationmaster) {
	Session session = sessionFactory.openSession();
	String message;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		qualificationmaster.setValid(true);
		qualificationmaster.setUpdatedby("santhosh");
		qualificationmaster.setUpdatedip("172.22.1.17");
		qualificationmaster.setUpdateddate(date);
		Criteria criteria = session.createCriteria(Qualificationmaster.class);
		criteria.setProjection(Projections.max("qualificationid"));
		Integer qualificationid = (Integer) criteria.uniqueResult();
		if(qualificationid==null)
		{qualificationid=0;}
		qualificationmaster.setQualificationid(++qualificationid);
		session.save(qualificationmaster);
		transaction.commit();
		message = qualificationmaster.getQualification()+" is successfully saved.:";
	} catch (Exception e) {
		message = "Qualification is not saved due to Exception!";
		logger.error("Exception raised in saveServiceGroup Method:", e);
	} finally {
		session.close();
	}
	return message;
}
public List<Qualificationmaster> getQualifications(){
	
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Qualificationmaster.class);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("qualificationid"));
	List<Qualificationmaster> qualification = criteria.list();

	try {

	} catch (Exception e) {
		logger.error("Exception raised in getTaxS Method:", e);
	} finally {
		session.close();
	}
	return qualification;

}

@Override
public String editQualifications(Qualificationmaster qualificationmaster) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		qualificationmaster.setValid(true);
		qualificationmaster.setUpdatedby("santhosh");
		qualificationmaster.setUpdatedip("172.22.1.17");
		qualificationmaster.setUpdateddate(date);
		session.update(qualificationmaster);
		transaction.commit();
		message = qualificationmaster.getQualification()+" is successfully Edited:";
	} catch (Exception e) {
		message = "qualificationmaster is not Edited due to Exception!";
		logger.error("Exception raised in editSkills Method:", e);
	} finally {
		session.close();
	}
	return message;
}


@Override
public String deleteQualification(Qualificationmaster qualificationmaster) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		qualificationmaster.setValid(false);
		qualificationmaster.setUpdatedby("santhosh");
		qualificationmaster.setUpdatedip("172.22.1.17");
		qualificationmaster.setUpdateddate(date);
		session.update(qualificationmaster);
		transaction.commit();
		message = qualificationmaster.getQualification()+" is Deleted";
	} catch (Exception e) {
		message = "Skill is not Deleted due to Exception!";
		logger.error("Exception raised in deleteQualification Method:", e);
	} finally {
		session.close();
	}
	return message;
}


@Override
public List<Qualificationmaster> getQualificationByName(String qualification, int records) {
	
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(qualification==null){
		
		Criteria criteria = session.createCriteria(Qualificationmaster.class);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("qualificationid"));
		List<Qualificationmaster> qlist = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getQualificationByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return qlist;
	}
	
	else{
		
		Criteria criteria = session.createCriteria(Qualificationmaster.class);
		criteria.add(Restrictions.ilike("qualification",qualification,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);;
		criteria.addOrder(Order.asc("qualificationid"));
		List<Qualificationmaster> qlist = criteria.list();
		
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return qlist;
	}
	
}

@Override
public Pagination qualifyCount(String qualification) {
	
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(qualification==null){
		Criteria criteria = session.createCriteria(Qualificationmaster.class);
		criteria.add(Restrictions.eq("valid", true));
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
		Criteria criteria = session.createCriteria(Qualificationmaster.class);
		criteria.add(Restrictions.ilike("qualification",qualification,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  qualifyCount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
}


@Override
public String saveMarketingvoucher(Marketingvouchermaster marketvoucher) {
	Session session = sessionFactory.openSession();
	String message;
	try {
		Transaction transaction = session.beginTransaction();
	
		Date date = new Date();
		marketvoucher.setValid(true);
		marketvoucher.setUpdatedby("santhosh");
		marketvoucher.setUpdatedip("172.22.1.17");
		marketvoucher.setUpdateddate(date);
		Integer markid = marketvoucher.getMarketingvoucherid();
		
		   Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
		   criteria.setProjection(Projections.max("marketingvoucherid"));
		   Integer marketingvoucherid = (Integer) criteria.uniqueResult();
		   if(marketingvoucherid==null)
		          {
			   marketingvoucherid=0;
			   }
		   marketvoucher.setMarketingvoucherid(++marketingvoucherid);
		   session.save(marketvoucher);	
		transaction.commit();
		message = marketvoucher.getVoucheramount()+" is successfully saved.:";
	} catch (Exception e) {
		message = "Marketingvouchermaster is not saved due to Exception!";
		logger.error("Exception raised in saveMarketingvoucher Method:", e);
	} finally {
		session.close();
	}
	return message;
}

@Override
public List<Marketingvouchermaster> getMarketingVoucher() {
	
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("marketingvoucherid"));
	List<Marketingvouchermaster> voucherlist = criteria.list();

	try {

	} catch (Exception e) {
		logger.error("Exception raised in getMarketingVoucher Method:", e);
	} finally {
		session.close();
	}

	return voucherlist;
}

@Override
public Pagination marketCount(String mcompany) {

	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(mcompany==null){
		Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
		criteria.add(Restrictions.eq("valid", true));
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
		Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
		criteria.add(Restrictions.ilike("marketingcompany",mcompany,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in MarketingCount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
}




@Override
public List<Marketingvouchermaster>  getMvoucherByName(String mcompany,int records)
{
	
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(mcompany==null){
		Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("marketingvoucherid"));
		List<Marketingvouchermaster> mlist = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getMvoucherByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return mlist;
	}
	
	else{
		
		Criteria criteria = session.createCriteria(Marketingvouchermaster.class);
		criteria.add(Restrictions.ilike("marketingcompany",mcompany,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("marketingvoucherid"));
		List<Marketingvouchermaster> mlist = criteria.list();
		
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getMvoucherByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return mlist;
	}
	
	
}
@Override
public String saveRoom(Roommaster room) {
	Session session = sessionFactory.openSession();
	String message;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		room.setValid(true);
		room.setUpdatedby("santhosh");
		room.setUpdatedip("172.22.1.17");
		room.setUpdateddate(date);
		Criteria criteria = session.createCriteria(Roommaster.class);
		criteria.setProjection(Projections.max("roomid"));
		Integer roomid = (Integer) criteria.uniqueResult();
		if(roomid==null)
		{
			roomid=0;
			
		}
		room.setRoomid(++roomid);
		session.save(room);
		transaction.commit();
		message = room.getDescription()+" is successfully saved.:";
	} catch (Exception e) {
		message = "Room master is not saved due to Exception!";
		logger.error("Exception raised in saveRoommaster Method:", e);
	} finally {
		session.close();
	}
	return message;

}

@Override
public List<Roommaster> getRooms() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Roommaster.class);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("roomid"));
	@SuppressWarnings("unchecked")
	List<Roommaster> roomlist = criteria.list();

	try {

	} catch (Exception e) {
		logger.error("Exception raised in getRooms Method:", e);
	} finally {
		session.close();
	}
	return roomlist;
}

@Override
public String editRoom(Roommaster room) {
	
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		room.setValid(true);
		room.setUpdatedby("santhosh");
		room.setUpdatedip("172.22.1.17");
		room.setUpdateddate(date);
		session.update(room);
		transaction.commit();
		message = room.getRoomno()+" is successfully Edited:";
	} catch (Exception e) {
		message = "Roommaster is not Edited due to Exception!";
		logger.error("Exception raised in editRoom Method:", e);
	} finally {
		session.close();
	}
	return message;
 }

@Override
public List<Roommaster> getRoomsByNum(Integer roomnum,int records) {
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(roomnum==null){
		Criteria criteria = session.createCriteria(Roommaster.class);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("roomid"));
		@SuppressWarnings("unchecked")
		List<Roommaster> rlist = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getRoomsByNum Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return rlist;
	}
	
	else{
		
		Criteria criteria = session.createCriteria(Roommaster.class);
		criteria.add(Restrictions.eq("roomno",roomnum));
		
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("roomid"));
		@SuppressWarnings("unchecked")
		List<Roommaster> rlist = criteria.list();
		
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return rlist;
	}
	
	
	
}




@Override
public Pagination roomCount(Integer roomnum) {
	
	
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(roomnum==null){
		Criteria criteria = session.createCriteria(Roommaster.class);
		criteria.add(Restrictions.eq("valid", true));
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
		Criteria criteria = session.createCriteria(Roommaster.class);
		criteria.add(Restrictions.eq("roomno",roomnum));
		//
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in roomCount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
}



@Override
public String deleteRoom(Roommaster room) {
	
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		room.setValid(false);
		room.setUpdatedby("santhosh");
		room.setUpdatedip("172.22.1.17");
		room.setUpdateddate(date);
		session.update(room);
		transaction.commit();
		message = room.getDescription()+" is Deleted";
	} catch (Exception e) {
		message = "Room is not Deleted due to Exception!";
		logger.error("Exception raised in deleteRoom Method:", e);
	} finally {
		session.close();
	}
	return message;
}

@Override
public String saveSegment(Segmentmaster segment) {
	Session session = sessionFactory.openSession();
	String message;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		segment.setValid(true);
		segment.setUpdatedby("santhosh");
		segment.setUpdatedip("172.22.1.17");
		segment.setUpdateddate(date);
		Criteria criteria = session.createCriteria(Segmentmaster.class);
		criteria.setProjection(Projections.max("segmentid"));
		Integer segmentid = (Integer) criteria.uniqueResult();
		if(segmentid==null)
		{
			segmentid=0;
			
		}
		segment.setSegmentid(++segmentid);;
		session.save(segment);
		transaction.commit();
		message = segment.getSegmentname()+" is successfully saved.:";
	} catch (Exception e) {
		message = "Segment master is not saved due to Exception!";
		logger.error("Exception raised in saveSegment master Method:", e);
	} finally {
		session.close();
	}
	return message;

}

@Override
public List<Segmentmaster> getSegment() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Segmentmaster.class);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("segmentid"));
	@SuppressWarnings("unchecked")
	List<Segmentmaster> seglist = criteria.list();

	try {

	} catch (Exception e) {
		logger.error("Exception raised in getSegment Method:", e);
	} finally {
		session.close();
	}
	return seglist;
}

@Override
public String editSegment(Segmentmaster segment) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		segment.setValid(true);
		segment.setUpdatedby("santhosh");
		segment.setUpdatedip("172.22.1.17");
		segment.setUpdateddate(date);
		session.update(segment);
		transaction.commit();
		message = segment.getSegmentname()+" is successfully Edited:";
	} catch (Exception e) {
		message = "Segmentmaster is not Edited due to Exception!";
		logger.error("Exception raised in editSegment Method:", e);
	} finally {
		session.close();
	}
	return message;
 }

@Override
public String deleteSegment(Segmentmaster segment) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		segment.setValid(false);
		segment.setUpdatedby("santhosh");
		segment.setUpdatedip("172.22.1.17");
		segment.setUpdateddate(date);
		session.update(segment);
		transaction.commit();
		message = segment.getSegmentname()+" is Deleted";
	} catch (Exception e) {
		message = "segment is not Deleted due to Exception!";
		logger.error("Exception raised in deletesegment Method:", e);
	} finally {
		session.close();
	}
	return message;
}


@Override
public List<Segmentmaster> getSegmentByName(String segname,int records) {
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(segname==null){
		Criteria criteria = session.createCriteria(Segmentmaster.class);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("segmentid"));
		@SuppressWarnings("unchecked")
		List<Segmentmaster> sklist = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSegmentsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return sklist;
	}
	
	else{
		
		Criteria criteria = session.createCriteria(Segmentmaster.class);
		criteria.add(Restrictions.ilike("segmentname",segname,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);;
		criteria.addOrder(Order.asc("segmentid"));
		@SuppressWarnings("unchecked")
		List<Segmentmaster> sslist = criteria.list();
		
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  getSkillsByName Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return sslist;
	}
	
	
	
}


@Override
public Pagination segmentCount(String segname) {
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(segname==null){
		Criteria criteria = session.createCriteria(Segmentmaster.class);
		criteria.add(Restrictions.eq("valid", true));
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
		Criteria criteria = session.createCriteria(Segmentmaster.class);
		criteria.add(Restrictions.ilike("segmentname",segname,MatchMode.ANYWHERE));
		
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  get segmentCount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
	
}

@Override
public String saveServiceStaff(Servicestaffmaster servicestaff) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		servicestaff.setValid(true);
		servicestaff.setUpdatedby("Santhosh");
		servicestaff.setUpdatedip(Ipaddress.getIpAddress());
		servicestaff.setUpdateddate(date);
		Criteria criteria = session.createCriteria(Servicestaffmaster.class);
		criteria.setProjection(Projections.max("servicestaffid"));
		Integer servicestaffid = (Integer) criteria.uniqueResult();
		if(servicestaffid==null)
		{servicestaffid=0;}
		servicestaff.setServicestaffid(++servicestaffid);
		session.save(servicestaff);
		transaction.commit();
		message = "Service Staff is successfully saved.:";
	} catch (Exception e) {
		message = "Service Staff is not saved due to Exception!";
		logger.error("Exception raised in saveServiceStaff Method:", e);
	} finally {
		session.close();
	}
	return message;
}

@Override
public List<Servicestaffmaster> getServiceStaffList() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Servicestaffmaster.class,"servicestaff");
	criteria.createAlias("servicestaff.servicemaster", "servicemaster");
	criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
	criteria.createAlias("servicestaff.staffmaster", "staffmaster");
	criteria.setFetchMode("staffmaster.staffmaster", FetchMode.EAGER);
	criteria.add(Restrictions.eq("valid", true));
	criteria.addOrder(Order.asc("servicestaffid"));
	List<Servicestaffmaster> list = criteria.list();
	try {

	} catch (Exception e) {
		logger.error("Exception raised in getServicestaffList Method:", e);
	} finally {
		session.close();
	}
	return list;
}

@Override
public String editServiceStaff(Servicestaffmaster servicestaff) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		servicestaff.setValid(true);
		servicestaff.setUpdatedby("santhosh");
		servicestaff.setUpdatedip(Ipaddress.getIpAddress());
		servicestaff.setUpdateddate(date);
		session.update(servicestaff);
		transaction.commit();
		message = "Service Staff is successfully Edited:";
	} catch (Exception e) {
		message = "Service is not Edited due to Exception!";
		logger.error("Exception raised in editServiceStaff Method:", e);
	} finally {
		session.close();
	}
	return message;
}

@Override
public String deleteServiceStaff(Servicestaffmaster servicestaff) {
	Session session = sessionFactory.openSession();
	String message = null;
	try {
		Transaction transaction = session.beginTransaction();
		Date date = new Date();
		servicestaff.setValid(false);
		servicestaff.setUpdatedby("Santhosh");
		servicestaff.setUpdatedip(Ipaddress.getIpAddress());
		servicestaff.setUpdateddate(date);
		session.update(servicestaff);
		transaction.commit();
		message =  "Service Staff is successfully Deleted";
	} catch (Exception e) {
		message = "Service is not Deleted due to Exception!";
		logger.error("Exception raised in deleteServiceStaff Method:", e);
	} finally {
		session.close();
	}
	return message;
}

@Override
public List<Servicestaffmaster> getsearchServiceStaff(String serviceName, int records) {
	Session session = sessionFactory.openSession();
	Transaction trx=session.beginTransaction();
	int recordsPerPage=records*5-5;
	if(serviceName==null){
		Criteria criteria = session.createCriteria(Servicestaffmaster.class,"servicestaff");
		criteria.createAlias("servicestaff.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("servicestaff.staffmaster", "staffmaster");
		criteria.setFetchMode("staffmaster.staffmaster", FetchMode.EAGER);
		criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
		criteria.addOrder(Order.asc("servicestaffid"));
		List<Servicestaffmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getServicestaffList Method:", e);
		} finally {
			trx.commit();
			session.close();
		}
		return list;
	}
	
	else{
	
	Criteria criteria = session.createCriteria(Servicestaffmaster.class,"servicestaff");
	criteria.createAlias("servicestaff.servicemaster", "servicemaster");
	criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
	criteria.createAlias("servicestaff.staffmaster", "staffmaster");
	criteria.setFetchMode("staffmaster.staffmaster", FetchMode.EAGER);
	
	criteria.add(Restrictions.ilike("servicemaster.servicename", serviceName, MatchMode.ANYWHERE));
	criteria.add(Restrictions.eq("valid", true)).setFirstResult(recordsPerPage).setMaxResults(5);
	List<Servicestaffmaster> list = criteria.list();
	try {

	} catch (Exception e) {
		logger.error("Exception raised in getsearchServiceStaff Method:", e);
	} finally {
		trx.commit();
		session.close();
	}
	return list;
}
}
@Override
public List<Staffmaster> getStaff() {
	
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Staffmaster.class);
	criteria.add(Restrictions.eq("valid", true));
	List<Staffmaster> slist = criteria.list();
	try {

	} catch (Exception e) {
		logger.error("Exception raised in getStaff Method:", e);
	} finally {
		session.close();
	}
	return slist;
}



@Override
public Pagination serviceStaffCount(String serviceName) {
	Session session = sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	if(serviceName==null){
	
		Criteria criteria = session.createCriteria(Servicestaffmaster.class,"servicestaff");
		criteria.createAlias("servicestaff.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("servicestaff.staffmaster", "staffmaster");
		criteria.setFetchMode("staffmaster.staffmaster", FetchMode.EAGER);
		criteria.add(Restrictions.eq("valid", true));
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

		Criteria criteria = session.createCriteria(Servicestaffmaster.class,"servicestaff");
		criteria.createAlias("servicestaff.servicemaster", "servicemaster");
		criteria.setFetchMode("servicemaster.servicemaster", FetchMode.EAGER);
		criteria.createAlias("servicestaff.staffmaster", "staffmaster");
		criteria.setFetchMode("staffmaster.staffmaster", FetchMode.EAGER);
		
		criteria.add(Restrictions.ilike("servicemaster.servicename", serviceName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("valid", true));
		Long totalRecs= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		int noOfPages = (int) Math.ceil(totalRecs * 1.0 / 5);
		Pagination pagination=Pagination.getInstance();
		pagination.setTotalRecs(totalRecs);
		pagination.setNoOfPages(noOfPages);
		try {

		} catch (Exception e) {
			logger.error("Exception raised in  serviceStaffCount Method:", e);
		} finally {
			tx.commit();
			session.close();
		}
		return pagination;
	}
}
	@Override
	public Integer getskillid(String skillname) {
		
		Session session = sessionFactory.openSession();
		List<Integer> skillid=null;
			Criteria criteria = session.createCriteria(Skillmaster.class);
			criteria.add(Restrictions.eq("skillname", skillname));
			criteria.setProjection(Projections.property("skillid"));
			skillid = criteria.list();
		      return skillid.get(0);
    }
	@Override
	public void saveStaffSkills(List<StaffSkillsData> sfs) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean valid=true;
		Date date = new Date();
		
		java.util.Iterator<StaffSkillsData> sk = sfs.iterator();
		
		Integer skillid = null;
		Integer staffid = null;
		String skillname = null;
		try{
			Criteria criteria = session.createCriteria(Staffskill.class);
			criteria.setProjection(Projections.max("staffskillid"));
			Integer id = (Integer) criteria.uniqueResult();
			while(sk.hasNext())
			{
			Skillmaster skillmaster = new Skillmaster();
			Staffmaster staffmaster = new Staffmaster();
			Staffskill staffskill = new Staffskill();
			StaffSkillsData se = sk.next();
			
			if(id==null)
				id=0;
			staffid = se.getStaffid();
			skillname = se.getSkillname();
			skillid = se.getSkillid();
			System.out.println(skillid);		
			skillmaster.setSkillid(skillid);
			staffskill.setSkillmaster(skillmaster);
			
			staffmaster.setStaffid(staffid);
			staffskill.setStaffmaster(staffmaster);
			staffskill.setStaffskillid(++id);
			staffskill.setValid(true);
			staffskill.setUpdatedby("Santhosh");
			staffskill.setUpdateddate(date);
			staffskill.setUpdatedip("172.22.1.23");
			List list = session.createSQLQuery("select * from staffskill where staffid="+staffid+" and skillid="+skillid).list();
			Integer condition =list.size();
			if(condition==0){
			session.save(staffskill);
			}
			} 
		}  catch (Exception e) {
				
				logger.error("Exception raised in savestaffskill method:",e);
			}
			finally{
			
			transaction.commit();
			session.close();
			}
	}

	

	@Override
	public Set<Staffskill> getSkill1(Integer staffId) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Staffmaster.class);
		criteria.add(Restrictions.eq("staffid", staffId));
		criteria.setFetchMode("staffskills", FetchMode.EAGER);
		Staffmaster staff = (Staffmaster) criteria.uniqueResult();
		Set<Staffskill> staffskills = staff.getStaffskills();
		return staffskills;
	}

	@Override
	public List<Skillmaster> getSkills() {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cr= session.createCriteria(Skillmaster.class);
		cr.add(Restrictions.eq("valid",true));
		cr.addOrder(Order.asc("skillid"));
		List<Skillmaster> skills = cr.list();
		return skills;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<Membershipmaster> getmembershipList(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Membershipmaster.class);
		List<Membershipmaster> list = criteria.list();
		try {      

		} catch (Exception e) {
			logger.error("Exception raised in getServices Method:", e);
		} finally {
			session.close();
		}
		return list;
		}

	@Override
	public String saveMemberShip(Membershipmaster membershipmaster,String idname) {

		String saved="sucess";
		Session session1=sessionFactory.openSession();
	    Transaction transaction=session1.beginTransaction();
	    System.out.println(idname+"@@@@@@@@@@@@@@");
	     if(idname == null||idname==""){
	    	 try{
	 			Criteria criteria = session1.createCriteria(Membershipmaster.class);
	 			criteria.setProjection(Projections.max("membershipid"));
	 			Integer id = (Integer)criteria.uniqueResult();
	 			System.out.println(id);
	 			if(id==null){
	 				membershipmaster.setMembershipid(1);
	 			}
	 			else{
	 				membershipmaster.setMembershipid(id+1);
	 			}
	 			//membershipmaster.setBlacklisted(blacklisted);
	 			BigDecimal amountpaid = membershipmaster.getAmountpaid();
	 			membershipmaster.setOutstandingamount(amountpaid);
	 			membershipmaster.setValid(true);
	 			membershipmaster.setUpdatedby("anil");
	 			Date date = new Date();
	 			membershipmaster.setUpdateddate(date);
	 			membershipmaster.setUpdatedip("172.22.1.27");
	 			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	 		session1.save(membershipmaster);
	 		transaction.commit();
	 		}catch(Exception e){
	 			logger.error("Exception raised in membership save method"+e);
	 		}
	 		finally{
	 			
	 			session1.close();
	 		}
	    	 return saved;
	     }
	     else
	     {
	    	 Criteria criteria = session1.createCriteria(Membershipmaster.class);
	 			criteria.setProjection(Projections.max("membershipid"));
	 			Integer id = (Integer)criteria.uniqueResult();
	 			System.out.println(id);
	 			if(id==null){
	 				membershipmaster.setMembershipid(1);
	 			}
	 			else {
	 				membershipmaster.setMembershipid(id+1);
	 			}
	 			membershipmaster.setBlacklisted(false);
	 			BigDecimal amountpaid = membershipmaster.getAmountpaid();
	 			membershipmaster.setOutstandingamount(amountpaid);
	    	 int id1=Integer.parseInt(idname);
	    	 membershipmaster.setMembershipid(id1);
	    	 membershipmaster.setValid(true);
				membershipmaster.setUpdatedby("anil");
				Date date = new Date();
				membershipmaster.setUpdateddate(date);
				membershipmaster.setUpdatedip("172.22.1.27");
			session1.update(membershipmaster);
			transaction.commit();
			session1.close();
		return saved;
	     }
	}



	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Membershipmaster> searchByMemberName(String membername) {
		System.out.println("NAME"+membername);
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr= session.createCriteria(Membershipmaster.class);
		cr.add(Restrictions.ilike("membername",membername,MatchMode.ANYWHERE));
		List<Membershipmaster> results = cr.list();
		tx.commit();
		session.close();
		return results;
	}

	@Override
	public List<Corporatemaster> getCorporateList1(){
		System.out.println("!!!!!!!ii am DAO!");
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Corporatemaster.class);
		criteria.setFetchMode("corporatetypemaster", FetchMode.EAGER);
		List<Corporatemaster> list = criteria.list();
		System.out.println("anil:"+list.size());
		try {      

		} catch (Exception e) {
			logger.error("Exception raised in getServices Method:", e);
		} finally {
			session.close();
		}
		return list;
		}

	

	@Override
	public List<Corporatemaster> getSearchByName(String corporatename){
		System.out.println("NAME"+corporatename);
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Corporatemaster.class);
		criteria.add(Restrictions.ilike("corporatename",corporatename,MatchMode.ANYWHERE));
		criteria.setFetchMode("corporatetypemaster", FetchMode.EAGER);
		List<Corporatemaster> corporatesearchList = criteria.list();
		return corporatesearchList;
	}

	@Override
	public String saveCorporateMaster(Corporatemaster corporatemaster, String corporateid){
		String saved="success";
	   	 Session session1=sessionFactory.openSession();
		    Transaction transaction=session1.beginTransaction();
		  
		    try{
		     if(corporateid == null||corporateid==""){
		    	 Criteria criteria = session1.createCriteria(Corporatemaster.class);
		 			criteria.setProjection(Projections.max("corporateid"));
		 			Integer id = (Integer)criteria.uniqueResult();
		 			System.out.println(id+"^^^^^^^^^^^^^^^^");
		 			if(id==null){
		 				corporatemaster.setCorporateid(1);
		 			}
		 			else{
		 				corporatemaster.setCorporateid(id+1);
		 			}
		 			corporatemaster.setBlacklist(false);
		 		
		 		
		    	 corporatemaster.setValid(true);
		    	 corporatemaster.setUpdatedby("anil");
					Date date = new Date();
					corporatemaster.setUpdateddate(date);
					corporatemaster.setUpdatedip("172.22.1.27");
				session1.save(corporatemaster);
		 			
		     }
	 		 else{
	 			Integer cid = Integer.parseInt(corporateid);
	 			corporatemaster.setCorporateid(cid);
	 			System.out.println("PPPPPPPPP"+corporateid);
	 			corporatemaster.setValid(true);
	 			corporatemaster.setUpdatedby("anil");
	 			Date date = new Date();
	 			corporatemaster.setUpdateddate(date);
	 			corporatemaster.setUpdatedip("172.22.1.27");
	 			
	 		session1.update(corporatemaster);
	 		 }
		     transaction.commit();
		     }catch(Exception e){
		 			logger.error("Exception raised in corporate save method"+e);
		 		}
		 		finally{
		 		session1.close();
		 		}
		    	 return saved;
			}

	@Override
	public List<Corporatetypemaster> getCorporateTypeList(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Corporatetypemaster.class);
		List<Corporatetypemaster> list = criteria.list();
	
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getCorporateTypeList Method:", e);
		} finally {
			session.close();
		}
		return list;
	
		
	}

	
	@Override
	public List editvalue(Membershipmaster membershipmaster, String memberid) {
	
		Session session=sessionFactory.openSession();
		List memberList = session.createQuery(" FROM Membershipmaster where membershipid='"+memberid+"' and valid='true' ").list();
    session.close();
		return memberList;
	}
	
}


	
	
