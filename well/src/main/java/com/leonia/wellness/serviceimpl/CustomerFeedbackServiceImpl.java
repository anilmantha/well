package com.leonia.wellness.serviceimpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.entity.Statusmaster;
import com.leonia.wellness.entity.Ticketmaster;
import com.leonia.wellness.entity.Tickettypemaster;
import com.leonia.wellness.idao.ICustomerFeedbackDao;
import com.leonia.wellness.iservice.iCustomerFeedbackService;

@Service
public class CustomerFeedbackServiceImpl implements iCustomerFeedbackService{
	
	 @Autowired
	private ICustomerFeedbackDao customerfeedbackdao;

	

	@Override
	public List<Responsemaster> getResponseDetails() {
		return customerfeedbackdao.getResponseDetails();
	}

	@Override
	public List<Questionmaster> getQuestionInfo(Integer formId) {
		return customerfeedbackdao.getQuestionInfo(formId);
	}
   
	@Override
	public Feedbackformmaster getFeedbackFormDetailsById(Integer feedbackFormId) {
		return customerfeedbackdao.getFeedbackFormDetailsById(feedbackFormId);
	}

	@Override
	public Integer questionCount(Integer formid) {
		return customerfeedbackdao.questionCount(formid);
	}

	@Override
	public void saveResponse(Feedbackdetails feedbackdetails) {
		customerfeedbackdao.saveResponse(feedbackdetails);
		
	}

	@Override
	public void generateTicket(Ticketmaster ticketmaster) {
		customerfeedbackdao.generateTicket(ticketmaster);
		
	}

	@Override
	public List<Departmentmaster> getDepartmentNames() {
		
		return customerfeedbackdao.getDepartmentNames();
	}

	@Override
	public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype,String sourcetype,Integer records) {
		
		return customerfeedbackdao.ticketsList(date1, date2,departmentid,statustype,sourcetype,records);
	}

	@Override
	public Ticketmaster openTicket(int ticketno) {
		
		return customerfeedbackdao.openTicket(ticketno);
	}

	@Override
	public List<Statusmaster> getStatus() {
		
		return customerfeedbackdao.getStatus();
	}

	@Override
	public List<Tickettypemaster> getTicketType() {
	
		return customerfeedbackdao.getTicketType();
	}

	@Override
	public Ticketmaster getTicketDetails(Integer ticketno) {
		
		return customerfeedbackdao.getTicketDetails(ticketno);
	}

	@Override
	public void saveTicketClosing(Ticketmaster ticketmaster) {
		customerfeedbackdao.saveTicketClosing(ticketmaster);
		
	}

	@Override
	public List<Ticketmaster> ticketCount() {
		return customerfeedbackdao.ticketCount();

	}

	@Override
	public List<Ticketmaster> getOpenlist() {
		return customerfeedbackdao.getOpenlist();
	}

	@Override
	public List<Ticketmaster> getCloselist() {
		return customerfeedbackdao.getCloselist();
	}

	/*@Override
	public List<Ticketmaster> getDuelist() {
		return customerfeedbackdao.getDuelist();
	}*/

	@Override
	public List<Departmentmaster> getDeptData() {
		return customerfeedbackdao.getDeptData();
	}

	@Override
	public List<Ticketmaster> getOpentCount(int i) {
		return customerfeedbackdao.getOpentCount(i);
	}

	@Override
	public List<Ticketmaster> openCount() {
		return customerfeedbackdao.openCount();
	}

	@Override
	public Pagination ticketsCount(String pageName, Date date1, Date date2, String departmentId, String statustype,
			String sourcetype) {
		
		return customerfeedbackdao.ticketsCount(pageName,date1,date2,departmentId,statustype,sourcetype);
	}

	@Override
	public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype,String sourcetype) {
		
		return customerfeedbackdao.ticketsList(date1, date2,departmentid,statustype,sourcetype);
	}

	@Override
	public Feedbackformmaster feedbackNotGivenList(Date date,Integer billno) {
		
		return customerfeedbackdao.feedbackNotGivenList(date,billno);
	}

	@Override
	public List<Billmaster> billSettlementList(Date billno) {
		return customerfeedbackdao.billSettlementList(billno);
	
	}

	@Override
	public Integer saveFeedbackFormOfManual(Integer formid, Integer guestId, Integer billno, Integer sbuid) {
		return customerfeedbackdao.saveFeedbackFormOfManual(formid,guestId,billno,sbuid);
	}

	@Override
	public Feedbackformmaster getFeedbackFormMasterByBillno(Integer billno) {
		
		return customerfeedbackdao.getFeedbackFormMasterByBillno(billno);
	}

	@Override
	public void updatefeedbackformmaster(Integer feedbackformId) {
		customerfeedbackdao.updatefeedbackformmaster(feedbackformId);
	}
	
	
	}

	

	


	

	



	
