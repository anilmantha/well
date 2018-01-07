
package com.leonia.wellness.iservice;
import java.util.Date;
import java.util.List;

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


public interface iCustomerFeedbackService{
	
    public List<Responsemaster> getResponseDetails();
    public List<Questionmaster> getQuestionInfo(Integer formId);
    public Integer questionCount(Integer formid);
    public void saveResponse(Feedbackdetails feedbackdetails);
    public void generateTicket(Ticketmaster ticketmaster);
    public List<Departmentmaster> getDepartmentNames();
	public Ticketmaster openTicket(int ticketno);
	public List<Statusmaster> getStatus();
	public Feedbackformmaster getFeedbackFormDetailsById(Integer feedbackFormId);
	public List<Tickettypemaster> getTicketType();
	public void saveTicketClosing(Ticketmaster ticketmaster);
	public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentId, String statustype,String sourcetype, Integer records);
	public List<Ticketmaster> ticketCount();
	public Pagination ticketsCount(String pageName,Date date1, Date date2, String departmentId, String statustype,String sourcetype);
	public Ticketmaster getTicketDetails(Integer ticketno);
	public List<Ticketmaster> getOpenlist();
	public List<Ticketmaster> getCloselist();
	public List<Departmentmaster> getDeptData();
	public List<Ticketmaster> getOpentCount(int i);
	public List<Ticketmaster> openCount();
	public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentId, String statustype,String sourcetype);
	public Feedbackformmaster feedbackNotGivenList(Date date,Integer billno);
	public List<Billmaster> billSettlementList(Date date1);
	public Integer saveFeedbackFormOfManual(Integer formid, Integer guestId, Integer billno, Integer sbuid);
	public Feedbackformmaster getFeedbackFormMasterByBillno(Integer billno);
	public void updatefeedbackformmaster(Integer feedbackformId);

	
}
