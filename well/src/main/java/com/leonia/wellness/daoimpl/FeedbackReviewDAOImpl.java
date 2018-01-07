package com.leonia.wellness.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.idao.IFeedbackReviewDAO;
@Repository
public class FeedbackReviewDAOImpl implements IFeedbackReviewDAO {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private SessionFactory sessionFactory;
     private static final Logger logger = Logger.getLogger(FeedbackReviewDAOImpl.class);

	@Override
	public List<Questionmaster> getQuestionDescription(Integer fid) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		List<Questionmaster> list = null;
			
		try{
			criteria = session.createCriteria(Questionmaster.class,"ques");
			criteria.createAlias("ques.formmaster", "formmaster");
			criteria.add(Restrictions.eq("formmaster.formid", fid));
			list = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		return list;
	}

	@Override
	public List<Feedbackdetails> getReviewDetails(Date date1, Date date2, Integer fid) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		Feedbackdetails cfr = null;
		try{
			criteria = session.createCriteria(Feedbackdetails.class,"cfr");
			criteria.createAlias("cfr.feedbackformmaster", "feedbackformmaster");
			criteria.add(Restrictions.ge("feedbackformmaster.formreceiveddate",date1));
			criteria.add(Restrictions.le("feedbackformmaster.formreceiveddate",date2));
			criteria.add(Restrictions.eq("feedbackformmaster.formmaster.formid",fid));
			List<Feedbackdetails> list = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		
		return criteria.list();
	}

	@Override
	public List<Formmaster> getFormdetails() {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		List flist = new ArrayList();
		
		try{
			criteria = session.createCriteria(Formmaster.class);
			
			flist = criteria.list();
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		
		return flist;
	}

	@Override
	public Formmaster getFormData(int formid) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		Formmaster formno = null;
		
		try{
			criteria = session.createCriteria(Formmaster.class);
			criteria.add(Restrictions.eq("formid",formid));
			formno = (Formmaster) criteria.uniqueResult();	
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		
		return formno;
	}

	@Override
	public List<Responsemaster> getreposnedata() {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		List<Responsemaster> list = null;
		
		try{
			criteria = session.createCriteria(Responsemaster.class);
			list = criteria.list();
			}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		
		return list;
	}

	@Override
	public Formmaster getFormRes(Integer formid) {
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		Formmaster formno = null;
		
		try{
			criteria = session.createCriteria(Formmaster.class);
			criteria.add(Restrictions.eq("formid",formid));
			formno = (Formmaster) criteria.uniqueResult();
				
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		return formno;
	}

	@Override
	public List<Feedbackformmaster> getFormList(Date date1, Date date2, Integer fid) {
		
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		List<Feedbackformmaster> formlist = null;
		
		try{
			criteria = session.createCriteria(Feedbackformmaster.class,"feedbackform");
			criteria.createAlias("feedbackform.formmaster", "formmaster");
			criteria.add(Restrictions.eq("formmaster.formid", fid));
			criteria.add(Restrictions.ge("formreceiveddate",date1));
			criteria.add(Restrictions.le("formreceiveddate",date2));
			formlist = criteria.list();
			System.out.println(formlist.size());
		}catch(Exception e){
			logger.error("Exception raised in Feedback List"+e);
		}
		
		return formlist;
	}
	
}
