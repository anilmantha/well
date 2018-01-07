package com.leonia.wellness.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.ReviewResponse;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.idao.IFeedbackReviewDAO;
import com.leonia.wellness.iservice.IFeedbackReviewService;
@Service
public class FeedbackReviewServiceImpl implements IFeedbackReviewService {
	
	 @Autowired
		private IFeedbackReviewDAO feedbackreviewdao;

	@Override
	public List<Questionmaster> getQuestionDescription(Integer fid) {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getQuestionDescription(fid);
	}

	@Override
	public List<Feedbackdetails> getReviewDetails(Date date1, Date date2, Integer fid) {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getReviewDetails(date1,date2,fid);
	}

	@Override
	public List<Formmaster> getFormdetails() {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getFormdetails();
	}

	@Override
	public Formmaster getFormData(int formid) {
		
		return feedbackreviewdao.getFormData(formid);
	}

	@Override
	public List<Responsemaster> getreposnedata() {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getreposnedata();
	}

	@Override
	public Formmaster getFormRes(Integer formid) {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getFormRes(formid);
	}

	@Override
	public String generateReviewReports(List<ReviewResponse> responseCountList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedbackformmaster> getFormList(Date date1, Date date2, Integer fid) {
		// TODO Auto-generated method stub
		return feedbackreviewdao.getFormList(date1,date2,fid);
	}
}
