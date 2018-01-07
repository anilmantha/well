/*package com.leonia.wellness.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.dto.AddStockValues;
import com.leonia.wellness.entity.StockDetails;
import com.leonia.wellness.entity.StockGroupMaster;
import com.leonia.wellness.entity.StockSubGroup;
import com.leonia.wellness.idao.IAddNewStockDao;

@Repository
public class AddNewStockimplDao implements IAddNewStockDao {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public List<StockGroupMaster> getsubgroupDetails(String groupname) {
		Session session = sessionfactory.openSession();
		List<StockGroupMaster> stockGroupList = null;
		try {
			Transaction beginTransaction = session.beginTransaction();
			stockGroupList = session.createQuery("from StockGroupMaster s where s.stockGroupMasterName=?").setParameter(0, groupname).list();
			beginTransaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stockGroupList;
	}

	@Override
	public void addStock(AddStockValues asv) {
		StockDetails stockDetails = new StockDetails();
		StockGroupMaster stockGroupMaster = new StockGroupMaster();
		StockSubGroup sg = new StockSubGroup();

		stockDetails.setReOrderLevel(asv.getReOrderlevel());
		stockDetails.setWarningLevel(asv.getWarningLevel());
		stockDetails.setStockName(asv.getStockName());
		stockDetails.setStockSubGroup(sg);

		sg.setStockSubGroupName(asv.getStockSubgroupName());
		sg.setStockGroupMaster(stockGroupMaster);

		stockGroupMaster.setStockGroupMasterName(asv.getStockGroupName());
		Session session = sessionfactory.openSession();
		try {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.save(stockGroupMaster);
			session.save(sg);
			session.save(stockDetails);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			session.close();
		}

	}

}
*/