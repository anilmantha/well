/*package com.leonia.wellness.daoimpl;

import java.util.Date;
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
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Reasonschildmaster;
import com.leonia.wellness.entity.Reasonsmaster;
import com.leonia.wellness.idao.IReasonsMasterDAO;

@Repository
public class ReasonsMasterDAOImpl implements IReasonsMasterDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(ReasonsMasterDAOImpl.class);

	@Override
	public List<Reasonsmaster> getAppointmentCancelReasons() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Reasonsmaster.class);
		List<Reasonsmaster> list = criteria.list();
		try {

		} catch (Exception e) {
			logger.error("Exception raised in getAppointmentReasons Method:", e);
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public Set<Reasonschildmaster> getReasonsList(Integer reasonchildmasterid) {

		Session session = sessionFactory.openSession();
		List<Reasonsmaster> reasons = new LinkedList<Reasonsmaster>() {
		};
		Criteria criteria = session.createCriteria(Reasonsmaster.class);
		criteria.add(Restrictions.eq("reasonsmasterid", reasonchildmasterid));
		Reasonsmaster uniqueResult = (Reasonsmaster) criteria.uniqueResult();

		return uniqueResult.getReasons();
	}

	@Override
	public void addReason(Reasonschildmaster reasons) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Date date = new Date();
			reasons.setUpdateddate(date);
			reasons.setUpdatedby("Harika");
			reasons.setUpdatedip("172.22.1.17");
			reasons.setValid(true);
			Criteria criteria = session.createCriteria(Reasonschildmaster.class);
			criteria.setProjection(Projections.max("reasonschildmasterid"));
			Integer id = (Integer) criteria.uniqueResult();
			reasons.setReasonschildmasterid(id + 1);
			session.save(reasons);
			transaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in addReason Method:", e);
		} finally {
			session.close();
		}

	}

	@Override
	public void saveReason(String reasonName, Integer reasonchildmasterId) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasonschildmaster.class);
			criteria.setProjection(Projections.max("reasonschildmasterid"));
			Integer id = (Integer) criteria.uniqueResult();
			Date date = new Date();
			Reasonsmaster reason = new Reasonsmaster();
			Reasonschildmaster reasonList = new Reasonschildmaster();
			reason.setReasonsmasterid(reasonchildmasterId);
			reasonList.setReasonsmaster(reason);
			reasonList.setDescription(reasonName);
			System.out.println(reasonName);
			reasonList.setUpdatedby("Harika");
			reasonList.setUpdateddate(date);
			reasonList.setUpdatedip("172.22.1.17");
			reasonList.setValid(true);
			reasonList.setReasonschildmasterid(id + 1);
			session.save(reasonList);
			transaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in saveReason Method:", e);
		} finally {
			session.close();
		}

	}

	@Override
	public void reasonUpdate(Integer reasonchildmasterId, Integer reasonid, String reason) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasonschildmaster.class);
			Reasonschildmaster reasonList = (Reasonschildmaster) criteria.add(Restrictions.eq("reasonschildmasterid", reasonid)).uniqueResult();
			reasonList.setDescription(reason);
			session.update(reasonList);
			transaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in reasonupdate Method:", e);
		} finally {
			session.close();
		}

	}

	@Override
	public void reasonDelete(Integer reasonchildmasterId, Integer reasonid, String reason) {
		
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Reasonschildmaster.class);
			Reasonschildmaster reasonList = (Reasonschildmaster) criteria.add(Restrictions.eq("reasonschildmasterid", reasonid)).uniqueResult();
			reasonList.setValid(false);
			session.update(reasonList);
			transaction.commit();
		} catch (Exception e) {
			logger.error("Exception raised in reasonDelete Method:", e);
		} finally {
			session.close();
		}
	}

}
*/