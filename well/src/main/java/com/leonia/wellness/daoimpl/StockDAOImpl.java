package com.leonia.wellness.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Dropdownmaster;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Stockadjustment;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.idao.IStockDAO;
import com.leonia.wellness.util.Ipaddress;

@Repository
public class StockDAOImpl implements IStockDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger.getLogger(StockDAOImpl.class);
	
	@Override
	public List<Stockgroupmaster> getsubgroupDetails(String groupname) {
		Session session = sessionFactory.openSession();
		List<Stockgroupmaster> stockGroupMasterList=null;
		try {
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			criteria.add(Restrictions.eq("stockgroupname", groupname)).add(Restrictions.eq("valid", true));
			criteria.setFetchMode("stocksubgroupmasters", FetchMode.EAGER);
			stockGroupMasterList = criteria.list();
			} catch (Exception e) {
			logger.error("Exception raised in getsubgroupDetails method:",e);
		}
		finally{session.close();}
		return stockGroupMasterList;
	}
	
		@Override
	public String addStock(Stockmaster stockMaster) {
		Session session = sessionFactory.openSession();
		String message=null;
		try {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.setProjection(Projections.max("stockid"));
			Integer id = (Integer) criteria.uniqueResult();
			if(id==null)
			{id=0;}
			stockMaster.setUnitprice(new BigDecimal(0));
			stockMaster.setAvailable(new BigDecimal(0));
			stockMaster.setStockid(id+1);
			session.save(stockMaster);
			transaction.commit();
			message=stockMaster.getStockname()+" is Successfully Saved!";
		} catch (Exception e) {
			logger.error("Exception raised in addStock method:",e);
			message=stockMaster.getStockname()+" is not Saved!";
		}
		
		finally {session.close();}
		return message;
	}
	
		@SuppressWarnings("deprecation")
		@Override
		public List<StockListDisplayDTO> getStockList() {
			Session session = sessionFactory.openSession();
		    List<StockListDisplayDTO> stockListDisplayList = new ArrayList<>();
			try{
				Criteria criteria = session.createCriteria(Stockmaster.class, "stockmaster");
				criteria.add(Restrictions.eq("valid",true));
				Order order = Order.asc("stockid");
				criteria.addOrder(order);
				List<Stockmaster> stockMasterList = criteria.list();
				Iterator<Stockmaster> stockMasterIterator = stockMasterList.iterator();
				while (stockMasterIterator.hasNext()) {
					Stockmaster stockmaster = stockMasterIterator.next();
					
					Integer stockid = stockmaster.getStockid();
					Integer stocksubgroupid = stockmaster.getStocksubgroupmaster().getStocksubgroupid();
					
					StockListDisplayDTO stockListDisplay = new StockListDisplayDTO();
					stockListDisplay.setStockId(stockid);
					stockListDisplay.setStockName(stockmaster.getStockname());
					stockListDisplay.setWarninglevel(stockmaster.getWarninglevel());
					stockListDisplay.setReorderlevel(stockmaster.getReorderlevel());
					stockListDisplay.setAvailable(stockmaster.getAvailable());
	                stockListDisplay.setStockSubGroupId(stocksubgroupid);
					stockListDisplay.setStockSubGroupName(stockmaster.getStocksubgroupmaster().getStocksubgroupname());
					stockListDisplay.setStockGroupId(stockmaster.getStocksubgroupmaster().getStocksubgroupid());
	                stockListDisplay.setStockGroupMasterName(stockmaster.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname());
					stockListDisplay.setDropdownDetailsId(stockmaster.getDropdowndetails().getDropdowndetailsid());
					stockListDisplay.setDropDownDescription(stockmaster.getDropdowndetails().getDescription());
					stockListDisplay.setRetailprice(stockmaster.getUnitprice());
					

					List<Stockreceipt> stockReceiptList = session.createSQLQuery(
							"select supplierid,manufacturerid,manufacturedate,expirydate,stockreceiptid from Stockreceipt where expirydate =(select Min(expirydate) from Stockreceipt where stockid='"
									+ stockid + "')")
							.list();
					Iterator<Stockreceipt> stockReceiptIterator = stockReceiptList.iterator();
					while (stockReceiptIterator.hasNext()) {
						Object object = stockReceiptIterator.next();
						Object oo[] = (Object[]) object;
						Integer supplierid = (Integer) oo[0];
						Integer manufacturerid = (Integer) oo[1];
						Date manufacturedate = (Date) oo[2];
						Date expirydate = (Date) oo[3];
						//BigDecimal retailprice = (BigDecimal) oo[4];
						//BigDecimal professionalprice = (BigDecimal) oo[5];
						int receiptId =(int) oo[4];
						stockListDisplay.setSupplierId(supplierid);
						stockListDisplay.setManufacturerId(manufacturerid);
						stockListDisplay.setManufacturedate(manufacturedate);
						stockListDisplay.setExpirydate(expirydate);
						//stockListDisplay.setRetailprice(retailprice);
						//stockListDisplay.setProfessionalprice(professionalprice);
						stockListDisplay.setReceiptId(receiptId);

						List<Suppliermaster> supplierNameList = session
								.createQuery(
										"select suppliername from Suppliermaster where supplierid='" + supplierid + "' and valid='true'")
								.list();
						Iterator<Suppliermaster> supplierNameIterator = supplierNameList.iterator();
						while (supplierNameIterator.hasNext()) {
							Object obj = supplierNameIterator.next();
							String suppliername = (String) obj;
							stockListDisplay.setSupplierName(suppliername);
						}
						List<Manufacturermaster> manufacturerNameList = session
								.createQuery("select manufacturername from Manufacturermaster where manufacturerid='"
										+ manufacturerid + "'and valid='true'")
								.list();
						Iterator<Manufacturermaster> manufacturerNameIterator = manufacturerNameList.iterator();
						while (manufacturerNameIterator.hasNext()) {
							Object obj = manufacturerNameIterator.next();
							String manufacturername = (String) obj;
							stockListDisplay.setManufacturerName(manufacturername);

						}
					}
					stockListDisplayList.add(stockListDisplay);
				}
			}catch(Exception e){
				logger.error("Exception raised in stock list"+e);
			}
			finally{
			 session.close();
			}
			return stockListDisplayList;
		}
	
		@Override
		public List<StockListDisplayDTO> getSearchStockList(String name) {
			
			 Session session = sessionFactory.openSession();
		     List<StockListDisplayDTO> stockListDisplayList = new ArrayList<>();
			 try{
				Criteria criteria = session.createCriteria(Stockmaster.class, "stockmaster");
				criteria.add(Restrictions.ilike("stockname", name, MatchMode.ANYWHERE));
				List<Stockmaster> stockMasterList = criteria.list();
				Iterator<Stockmaster> stockMasterIterator = stockMasterList.iterator();
				while (stockMasterIterator.hasNext()) {
					Stockmaster stockmaster = stockMasterIterator.next();
					
					Integer stockid = stockmaster.getStockid();
					Integer stocksubgroupid = stockmaster.getStocksubgroupmaster().getStocksubgroupid();
					
					StockListDisplayDTO stockListDisplay = new StockListDisplayDTO();
					stockListDisplay.setStockId(stockid);
					stockListDisplay.setStockName(stockmaster.getStockname());
					stockListDisplay.setWarninglevel(stockmaster.getWarninglevel());
					stockListDisplay.setReorderlevel(stockmaster.getReorderlevel());
					stockListDisplay.setAvailable(stockmaster.getAvailable());
	                stockListDisplay.setStockSubGroupId(stocksubgroupid);
					stockListDisplay.setStockSubGroupName(stockmaster.getStocksubgroupmaster().getStocksubgroupname());
					stockListDisplay.setStockGroupId(stockmaster.getStocksubgroupmaster().getStocksubgroupid());
	                stockListDisplay.setStockGroupMasterName(stockmaster.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname());
					stockListDisplay.setDropdownDetailsId(stockmaster.getDropdowndetails().getDropdowndetailsid());
					stockListDisplay.setDropDownDescription(stockmaster.getDropdowndetails().getDescription());
					stockListDisplay.setRetailprice(stockmaster.getUnitprice());
					

					List<Stockreceipt> stockReceiptList = session.createSQLQuery(
							"select supplierid,manufacturerid,manufacturedate,expirydate,stockreceiptid from Stockreceipt where expirydate =(select Min(expirydate) from Stockreceipt where stockid='"
									+ stockid + "')")
							.list();
					Iterator<Stockreceipt> stockReceiptIterator = stockReceiptList.iterator();
					while (stockReceiptIterator.hasNext()) {
						Object object = stockReceiptIterator.next();
						Object oo[] = (Object[]) object;
						Integer supplierid = (Integer) oo[0];
						Integer manufacturerid = (Integer) oo[1];
						Date manufacturedate = (Date) oo[2];
						Date expirydate = (Date) oo[3];
						//BigDecimal retailprice = (BigDecimal) oo[4];
						//BigDecimal professionalprice = (BigDecimal) oo[5];
						int receiptId =(int) oo[4];
						stockListDisplay.setSupplierId(supplierid);
						stockListDisplay.setManufacturerId(manufacturerid);
						stockListDisplay.setManufacturedate(manufacturedate);
						stockListDisplay.setExpirydate(expirydate);
						//stockListDisplay.setRetailprice(retailprice);
						//stockListDisplay.setProfessionalprice(professionalprice);
						stockListDisplay.setReceiptId(receiptId);

						List<Suppliermaster> supplierNameList = session
								.createQuery(
										"select suppliername from Suppliermaster where supplierid='" + supplierid + "'")
								.list();
						Iterator<Suppliermaster> supplierNameIterator = supplierNameList.iterator();
						while (supplierNameIterator.hasNext()) {
							Object obj = supplierNameIterator.next();
							String suppliername = (String) obj;
							stockListDisplay.setSupplierName(suppliername);
						}
						List<Manufacturermaster> manufacturerNameList = session
								.createQuery("select manufacturername from Manufacturermaster where manufacturerid='"
										+ manufacturerid + "'")
								.list();
						Iterator<Manufacturermaster> manufacturerNameIterator = manufacturerNameList.iterator();
						while (manufacturerNameIterator.hasNext()) {
							Object obj = manufacturerNameIterator.next();
							String manufacturername = (String) obj;
							stockListDisplay.setManufacturerName(manufacturername);

						}
					}
					stockListDisplayList.add(stockListDisplay);
				}
			 }catch(Exception e){
					logger.error("Exception raised in stock list"+e);
				}
				finally{
				 session.close();
				}
				return stockListDisplayList;
	}
	
	@Override
	public String addstockrefill(Stockreceipt stockrel, Integer stockId, Integer supplierid, Integer manufacturerid) {
		
		Date date = new Date();
		Session session = sessionFactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		String message = null;
		try {
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.add(Restrictions.eq("stockid", stockId));
			Stockmaster stockmaster = (Stockmaster) criteria.uniqueResult();
			Criteria criteria1 = session.createCriteria(Stockmaster.class);
			criteria1.add(Restrictions.eq("stockname", stockmaster.getStockname()));
			List<Stockmaster> stockmaster1 = criteria1.list();
			BigDecimal quantity = stockmaster.getAvailable();
			stockrel.setCurrentstock(quantity.intValueExact()+stockrel.getReceivedstock());
			for (Stockmaster stockmaster2 : stockmaster1) {
				if(quantity==null){
					stockmaster2.setAvailable(new BigDecimal(stockrel.getReceivedstock()));
				}
				else{
					stockmaster2.setAvailable(new BigDecimal(quantity.intValueExact()+stockrel.getReceivedstock()));
				}
				session.update(stockmaster2);
				beginTransaction.commit();
				beginTransaction = session.beginTransaction();
			}
			Suppliermaster supplier = new Suppliermaster();
			supplier.setSupplierid(supplierid);
			stockrel.setTotalstockcost(stockrel.getStockunitprice().multiply(new BigDecimal(stockrel.getReceivedstock())));
			Manufacturermaster manufacture = new Manufacturermaster();
			manufacture.setManufacturerid(manufacturerid);
			stockrel.setManufacturermaster(manufacture);
			stockrel.setSuppliermaster(supplier);
			Criteria criteria3 = session.createCriteria(Stockreceipt.class);
			criteria3.setProjection(Projections.max("stockreceiptid"));
			Integer id = (Integer) criteria3.uniqueResult();
			if(id==null)
			{id=0;}
			stockrel.setStockmaster(stockmaster);
			stockrel.setStockreceiptid(id+1);
			stockrel.setValid(true);
			stockrel.setUpdatedby("Kranthi");
			stockrel.setUpdatedip(Ipaddress.getIpAddress());
			stockrel.setUpdateddate(date);
			session.save(stockrel);
			beginTransaction.commit();
			message=stockrel.getStockmaster().getStockname()+" is Successfully Refilled!";
			
		} catch (Exception e) {
			message=stockrel.getStockmaster().getStockname()+" is not Refilled!";
			logger.error("Exception raised in addstockrefill method:",e);
		}
		session.close();
		return message;
		
	}
	@Override
	public Integer getstockavailability(Integer stockid) {
		BigDecimal availability=null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Stockmaster.class);
			criteria.add(Restrictions.eq("stockid", stockid)).add(Restrictions.eq("valid", true));
			availability=(BigDecimal) criteria.setProjection(Projections.property("available")).uniqueResult();
			} catch (Exception e) {
			logger.error("Exception raised in getstockavailability method:",e);
		}
		finally{session.close();}
		return availability.intValueExact();
		
	}
	@Override
	public List<Suppliermaster> getsupplierlist() {
		List<Suppliermaster> suppliers = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Suppliermaster.class).add(Restrictions.eq("valid", true));
			suppliers = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getsupplierlist method:",e);
		}
	   finally{session.close();}
		return suppliers;
	}
	
	@Override
	public String stockAdjustment(Integer stockId, Integer qua, String remarks, Date date1) {
		Session session = sessionFactory.openSession();
		String msg =null;
		try{
			Transaction transaction = session.beginTransaction();
		BigDecimal currentStock = (BigDecimal) session.createSQLQuery("select available from  Stockmaster where stockid='"+stockId+"'").list().get(0);
		BigDecimal afterStock = currentStock.subtract(new BigDecimal(qua));
		if(afterStock.compareTo(BigDecimal.ZERO) >= 0){
		SQLQuery qryrates = session.createSQLQuery("update Stockmaster set available="+afterStock+" where stockid="+stockId);
		 qryrates.executeUpdate();
		 Stockadjustment stockadj = new Stockadjustment();
		 Integer  stockAdjId = (Integer)session.createSQLQuery("select max(stockadjustid) from Stockadjustment").list().get(0);
		 if(stockAdjId==null){
			 stockadj.setStockadjustid(1);
		 }else{
		 stockadj.setStockadjustid(stockAdjId+1);
		 }
		 stockadj.setExpirydate(date1);
		 stockadj.setQty(qua);
		 stockadj.setQtybefore(currentStock.intValueExact());
		 stockadj.setQtyafter(afterStock.intValueExact());
		 stockadj.setRemarks(remarks);
		 stockadj.setValid(true);
		 stockadj.setUpdatedby("raghava");
		 stockadj.setUpdateddate(new Date());
		 stockadj.setUpdatedip("172.22.1.25");
		 Stockmaster stockmaster = new Stockmaster();
		 stockmaster.setStockid(stockId);
		 stockadj.setStockmaster(stockmaster);
		 session.save(stockadj);
		 transaction.commit();
		 msg="Stock is Adjusted";
		}else{
			msg="You are adjusting more stock than available stock";
		}
		}catch(Exception e){
			logger.error("Exception raised in stock adjustment method"+e);
			msg ="Stock is not Adjusted";
		}
		finally{
		
		 session.close();
		}
		
		return msg;
		
	}

	@Override
	public Integer getStockGroupMasterId(String stockGroupName) {
		Session session = sessionFactory.openSession();
		List<Stockgroupmaster> stockGroupMasterList=null;
		try {
			Criteria criteria = session.createCriteria(Stockgroupmaster.class);
			criteria.add(Restrictions.eq("stockgroupname", stockGroupName)).add(Restrictions.eq("valid", true));
			stockGroupMasterList = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStockGroupMasterId method:",e);
		}
		finally{
			session.close();
		}
		return stockGroupMasterList.get(0).getStockgroupid();
	}

		@Override
	public Integer getStockSubGroupId(String stockSubGroupName) {
		Session session = sessionFactory.openSession();
		List<Stocksubgroupmaster> stockSubGroup = null;
		try {
			Criteria criteria = session.createCriteria(Stocksubgroupmaster.class);
			criteria.add(Restrictions.eq("stocksubgroupname", stockSubGroupName)).add(Restrictions.eq("valid", true));
			stockSubGroup = criteria.list();
		} catch (Exception e) {
			logger.error("Exception raised in getStockSubGroupId Method:",e);
		}
		finally{
			session.close();
		}
		return stockSubGroup.get(0).getStocksubgroupid();
	}

		@Override
		public List<Manufacturermaster> getmanufacturerlist() {
			
			List<Manufacturermaster> manufacturers = null;
			Session session = sessionFactory.openSession();
			try {
				Criteria criteria = session.createCriteria(Manufacturermaster.class).add(Restrictions.eq("valid", true));
				manufacturers = criteria.list();
			} catch (Exception e) {
				logger.error("Exception raised in getsupplierlist method:",e);
			}
		   finally{session.close();}
			return manufacturers;
		}

		@Override
		public StockListDisplayDTO fetchSelectedStock(Integer stockId) {
			Session session = sessionFactory.openSession();
			StockListDisplayDTO stockListDisplay = new StockListDisplayDTO();
			try{
			Criteria criteria = session.createCriteria(Stockmaster.class, "stockmaster");
			criteria.add(Restrictions.eq("stockid", stockId));
			List<Stockmaster> stockMasterList = criteria.list();
			Iterator<Stockmaster> stockMasterIterator = stockMasterList.iterator();
			while (stockMasterIterator.hasNext()) {
				Stockmaster stockmaster = stockMasterIterator.next();
				
				Integer stockid = stockmaster.getStockid();
				Integer stocksubgroupid = stockmaster.getStocksubgroupmaster().getStocksubgroupid();

				stockListDisplay.setStockId(stockid);
				stockListDisplay.setStockName(stockmaster.getStockname());
				stockListDisplay.setWarninglevel(stockmaster.getWarninglevel());
				stockListDisplay.setReorderlevel(stockmaster.getReorderlevel());
				stockListDisplay.setAvailable(stockmaster.getAvailable());
				stockListDisplay.setStockSubGroupId(stocksubgroupid);
				stockListDisplay.setStockSubGroupName(stockmaster.getStocksubgroupmaster().getStocksubgroupname());
				stockListDisplay.setStockGroupId(stockmaster.getStocksubgroupmaster().getStocksubgroupid());
				stockListDisplay.setStockGroupMasterName(stockmaster.getStocksubgroupmaster().getStockgroupmaster().getStockgroupname());
				stockListDisplay.setDropdownDetailsId(stockmaster.getDropdowndetails().getDropdowndetailsid());
				stockListDisplay.setDropDownDescription(stockmaster.getDropdowndetails().getDescription());
				stockListDisplay.setRetailprice(stockmaster.getUnitprice());
				List<Stockreceipt> stockReceiptList = session.createSQLQuery(
						"select supplierid,manufacturerid,manufacturedate,expirydate,stockreceiptid from Stockreceipt where expirydate =(select Min(expirydate) from Stockreceipt where stockid='"
								+ stockid + "')")
						.list();
				Iterator<Stockreceipt> stockReceiptIterator = stockReceiptList.iterator();
				while (stockReceiptIterator.hasNext()) {
					Object object = stockReceiptIterator.next();
					Object oo[] = (Object[]) object;
					Integer supplierid = (Integer) oo[0];
					Integer manufacturerid = (Integer) oo[1];
					Date manufacturedate = (Date) oo[2];
					Date expirydate = (Date) oo[3];
					//BigDecimal retailprice = (BigDecimal) oo[4];
					//BigDecimal professionalprice = (BigDecimal) oo[5];
					int receiptId = (int) oo[4];
					stockListDisplay.setSupplierId(supplierid);
					stockListDisplay.setManufacturerId(manufacturerid);
					stockListDisplay.setManufacturedate(manufacturedate);
					stockListDisplay.setExpirydate(expirydate);
					//stockListDisplay.setRetailprice(retailprice);
					//stockListDisplay.setProfessionalprice(professionalprice);
					stockListDisplay.setReceiptId(receiptId);
					List<Suppliermaster> supplierNameList = session
							.createQuery("select suppliername from Suppliermaster where supplierid='" + supplierid + "'")
							.list();
					Iterator<Suppliermaster> supplierNameIterator = supplierNameList.iterator();
					while (supplierNameIterator.hasNext()) {
						Object obj = supplierNameIterator.next();
						String suppliername = (String) obj;
						stockListDisplay.setSupplierName(suppliername);
					}
					List<Manufacturermaster> manufactureNameList = session.createQuery(
							"select manufacturername from Manufacturermaster where manufacturerid='" + manufacturerid + "'")
							.list();
					Iterator<Manufacturermaster> manufactureNameIterator = manufactureNameList.iterator();
					while (manufactureNameIterator.hasNext()) {
						Object obj = manufactureNameIterator.next();
						String manufacturername = (String) obj;
						stockListDisplay.setManufacturerName(manufacturername);

					}
				}

			}
			}catch(Exception e){
				logger.error("exception raised in fetching stock details"+e);
			}
			finally{
			    session.close();
			}
			return stockListDisplay;
		}

		@Override
		public int updateStockDetails(String stExpireDate, StockListDisplayDTO stockDetails) {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			try{
		    Query qryrates = session.createSQLQuery("update stockmaster set unitprice="+stockDetails.getRetailprice()+",stocktypeid="+stockDetails.getDropdownDetailsId()+",stocksubgroupid="+stockDetails.getStockSubGroupId()+",stockname='"+stockDetails.getStockName()+"' where stockid="+stockDetails.getStockId());
			 qryrates.executeUpdate();
			 Query qryrates1 = session.createSQLQuery("update stockreceipt set supplierid="+stockDetails.getSupplierId()+",manufacturerid="+stockDetails.getManufacturerId()+" where expirydate='"+stExpireDate+"'");
			  qryrates1.executeUpdate();
			}catch(Exception e){
				logger.error("Exception raised in stock update method"+e);
			}
			finally{
			    transaction.commit();
				session.close();
			}
				return 0;
		}

		@Override
		public List<Stockmaster> getStockDetails() {
			Session session = sessionFactory.openSession();
			List<Stockmaster> stockMaster = null;
			try {
				Criteria criteria = session.createCriteria(Stockmaster.class,"stockmaster");
				stockMaster = criteria.list();
				} catch (Exception e) {
				logger.error("Exception raised in getsubgroupDetails method:",e);
			}
			finally{session.close();}
			return stockMaster;
		}
		

		@Override
		public List<Suppliermaster> getSupplierList() {
			Session session = sessionFactory.openSession();
			List<Suppliermaster> list =new LinkedList<Suppliermaster>();
			try{
				
				Criteria criteria = session.createCriteria(Suppliermaster.class);
				criteria.setFetchMode("servicemaster", FetchMode.EAGER);
				list= criteria.list();
			} catch (Exception e) {
				logger.error("Exception raised in getServices Method:", e);
			} finally {
				session.close();
			}
			return list;
			
		}

		@Override
		public String saveManufacturer(Manufacturermaster manufacturer) {
			Session session = sessionFactory.openSession();
			String message=null;
			try{
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			manufacturer.setUpdateddate(date);
			manufacturer.setUpdatedby("Harika");
			manufacturer.setUpdatedip("172.22.1.17");
			manufacturer.setValid(true);
			Criteria criteria = session.createCriteria(Manufacturermaster.class);
			criteria.setProjection(Projections.max("manufacturerid"));
			Integer id = (Integer) criteria.uniqueResult();
			if(id==null){
				id=0;
			}
			manufacturer.setManufacturerid(id+1);
			session.save(manufacturer);
			transaction.commit();
			message=manufacturer.getManufacturername()+" is Successfully saved!";
			}catch(Exception e){
				message=manufacturer.getManufacturername()+" is not saved!";
				logger.error("Exception raised in saveManufacturer Method:",e);
			}
			finally{
			session.close();
			}
			return message;
		}

		@Override
		public List<Manufacturermaster> getManufacturerList() {
		Session session = sessionFactory.openSession();
		List<Manufacturermaster> list = session.createCriteria(Manufacturermaster.class).list();
		try{
		
		}catch(Exception e){
			logger.error("Exception raised in getManufacturerList Method:",e);
		}
		finally{
			session.close();
		}
			return list;
		
		}

		@Override
		public String addSuppliers(Suppliermaster supplier) {
			Session session = sessionFactory.openSession();
			String message=null;
			try{
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			supplier.setUpdateddate(date);
			supplier.setUpdatedby("Harika");
			supplier.setUpdatedip("172.22.1.17");
			supplier.setValid(true);
			Criteria criteria = session.createCriteria(Suppliermaster.class);
			criteria.setProjection(Projections.max("supplierid"));
			Integer id = (Integer) criteria.uniqueResult();
			supplier.setSupplierid(id+1);
			session.save(supplier);
			transaction.commit();
			message=supplier.getSuppliername()+" is Successfully saved!";
			}catch(Exception e){
				message=supplier.getSuppliername()+" is not saved!";
				logger.error("Exception raised in addSuppliers method:",e);
			}
			session.close();
			return message;
		}
		

		@Override
		public void updateSuppliers(Suppliermaster suppliers) {
			Session session = sessionFactory.openSession();
			try{
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			suppliers.setUpdateddate(date);
			suppliers.setUpdatedby("Harika");
			suppliers.setUpdatedip("172.22.1.17");
			suppliers.setValid(true);
			session.update(suppliers);
			transaction.commit();
			}catch(Exception e){
				logger.error("Exception raised in updateSuppliers method:",e);
			}
			session.close();
			
		}

		@Override
		public Suppliermaster fetchSupplierWithId(Integer id) {
			Session session = sessionFactory.openSession();
			Suppliermaster supplierMaster=new Suppliermaster();
			try{
			Criteria criteria = session.createCriteria(Suppliermaster.class);
			criteria.add(Restrictions.eq("supplierid", id));
			supplierMaster = (Suppliermaster) criteria.uniqueResult();
			Date date=new Date();
			}catch(Exception e){
				logger.error("Exception raised in fetchSupplierWithId method:",e);
			}
			session.close();
			return supplierMaster;
		}

		@Override
		public Manufacturermaster fetchManufacturerWithId(Integer id) {
			Session session = sessionFactory.openSession();
			Manufacturermaster manufacturerMaster=new Manufacturermaster();
			try{
			Criteria criteria = session.createCriteria(Manufacturermaster.class);
			criteria.add(Restrictions.eq("manufacturerid", id));
			manufacturerMaster = (Manufacturermaster) criteria.uniqueResult();
			Date date=new Date();
			}catch(Exception e){
				logger.error("Exception raised in fetchManufacturerWithId method:",e);
			}
			session.close();
			return manufacturerMaster;
			
		}

		@Override
		public void updateManufacturer(Manufacturermaster manufacturer) {
			
			Session session = sessionFactory.openSession();
			try{
			Transaction transaction = session.beginTransaction();
			Date date=new Date();
			manufacturer.setUpdateddate(date);
			manufacturer.setUpdatedby("Harika");
			manufacturer.setUpdatedip("172.22.1.17");
			manufacturer.setValid(true);
			session.update(manufacturer);
			transaction.commit();
			}catch(Exception e){
				logger.error("Exception raised in updateManufacturer method:",e);
			}
			session.close();
		}

		@Override
		public void updatestockrate(Integer stockId) {
			
			Session session = sessionFactory.openSession();
			try{
				Transaction transaction = session.beginTransaction();
				Criteria criteria = session.createCriteria(Stockreceipt.class,"stockreceipt");
				criteria.createAlias("stockreceipt.stockmaster", "stockmaster");
				criteria.add(Restrictions.eq("stockmaster.stockid", stockId));
				criteria.setProjection(Projections.sum("stockreceipt.receivedstock"));
				Long quantity = (Long) criteria.uniqueResult();
				Criteria criteria1 = session.createCriteria(Stockreceipt.class,"stockreceipt");
				criteria1.createAlias("stockreceipt.stockmaster", "stockmaster");
				criteria1.add(Restrictions.eq("stockmaster.stockid", stockId));
				criteria1.setProjection(Projections.sum("stockreceipt.totalstockcost"));
				BigDecimal rates = (BigDecimal) criteria1.uniqueResult();
				System.out.println(rates.divide(new BigDecimal(quantity)));
				Stockmaster stockMaster = (Stockmaster) session.createCriteria(Stockmaster.class).add(Restrictions.eq("stockid", stockId)).uniqueResult();
				stockMaster.setUnitprice(rates.divide(new BigDecimal(quantity)));
				session.update(stockMaster);
				transaction.commit();
				
			}catch(Exception e){
				logger.error("Exception raised in updateManufacturer method:",e);
			}
			session.close();
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

}
