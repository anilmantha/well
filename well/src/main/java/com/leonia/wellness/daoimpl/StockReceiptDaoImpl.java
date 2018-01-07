/*package com.leonia.wellness.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.StockDetails;
import com.leonia.wellness.entity.StockReceipt;
import com.leonia.wellness.idao.IStockReceiptDao;

@Repository
public class StockReceiptDaoImpl implements IStockReceiptDao {

	private static final Logger logger = Logger.getLogger(StockReceiptDaoImpl.class);
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public List<StockDetails> getStockDetails() {

		Session session = sessionfactory.openSession();
		List<StockDetails> list = null;
		try {
			Transaction beginTransaction = session.beginTransaction();
			list = session.createCriteria(StockDetails.class).list();
			beginTransaction.commit();
		} catch (HibernateException e) {
			logger.error("Exception raised in StockReceiptDaoImpl:", e);
		}

		finally {
			session.close();
		}
		return list;
	}

	@Override
	public void addstockrefill(StockReceipt stockrel, int stockid) {

		Session session = sessionfactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		StockDetails sd = (StockDetails) session.get(StockDetails.class, stockid);
		Integer quantity = sd.getStockAvailability();

		if (quantity != null) {
			sd.setStockAvailability(quantity + stockrel.getQuantityReceived());
		} else {
			sd.setStockAvailability(stockrel.getQuantityReceived());
		}

		sd.setExpireDate(stockrel.getExpiryDate());
		sd.setRetailPrice(stockrel.getUnitRate());
		sd.setSupplierName(stockrel.getSupplier());
		session.update(sd);
		stockrel.setStockDetails(sd);
		session.save(stockrel);
		beginTransaction.commit();
		session.close();
		return;

	}

	@Override
	public Integer getstockavailability(String stockname) {
		Integer availability = null;
		Session session = sessionfactory.openSession();
		Transaction beginTransaction = session.beginTransaction();
		List<StockDetails> list = session.createQuery("from StockDetails s where s.stockName=?")
				.setParameter(0, stockname).list();
		for (StockDetails stockDetails : list) {
			availability = stockDetails.getStockAvailability();
		}
		beginTransaction.commit();
		session.close();
		return availability;

	}

	@Override
	public Set<String> getsupplierlist() {
		Set<String> suppliers = new HashSet<String>();
		Session session = sessionfactory.openSession();
		try {
			Transaction beginTransaction = session.beginTransaction();
			List<StockDetails> list = session.createCriteria(StockDetails.class).list();
			for (StockDetails stockDetails : list) {
				suppliers.add(stockDetails.getSupplierName());
			}
			suppliers.remove(null);
			beginTransaction.commit();
		} catch (HibernateException e) {
			logger.error("Exception raised in StockReceiptDaoImpl:", e);
		}

		finally {
			session.close();
		}
		return suppliers;

	}

}
*/