package com.leonia.wellness.iservice;

import java.util.Date;
import java.util.List;

import com.leonia.wellness.dto.ReviewResponse;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;

public interface IFeedbackReviewService {

	public List<Questionmaster> getQuestionDescription(Integer fid);

	public String generateReviewReports(List<ReviewResponse> responseCountList);

	public List<Feedbackdetails> getReviewDetails(Date date1, Date date2, Integer fid);

	public List<Formmaster> getFormdetails();

	public Formmaster getFormData(int formid);

	public List<Responsemaster> getreposnedata();

	public Formmaster getFormRes(Integer formid);

	public List<Feedbackformmaster> getFormList(Date date1, Date date2, Integer fid);

}
