package com.leonia.wellness.idao;


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

public interface ICustomerFeedbackDao {
	
public List<Responsemaster> getResponseDetails();
public List<Questionmaster> getQuestionInfo(Integer formId);
public void saveResponse(Feedbackdetails feedbackdetails);
public void generateTicket(Ticketmaster ticketmaster);
public Integer questionCount(Integer formid);
public List<Departmentmaster> getDepartmentNames();
public Ticketmaster openTicket(int ticketno);
public List<Statusmaster> getStatus();
public Feedbackformmaster getFeedbackFormDetailsById(Integer feedbackFormId);
public List<Tickettypemaster> getTicketType();
public Ticketmaster getTicketDetails(Integer ticketno);
public void saveTicketClosing(Ticketmaster ticketmaster);
public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype, String sourcetype, Integer records);
public List<Ticketmaster> ticketCount();
public Pagination ticketsCount(String pageName, Date date1, Date date2, String departmentId, String statustype, String sourcetype);
public List<Ticketmaster> openCount();
public List<Ticketmaster> getOpenlist();
public List<Ticketmaster> getCloselist();
public List<Departmentmaster> getDeptData();
public List<Ticketmaster> getOpentCount(int i);
public List<Ticketmaster> ticketsList(Date date1, Date date2, String departmentid, String statustype,String sourcetype);
public Feedbackformmaster feedbackNotGivenList(Date date,Integer feedbackformid);
public List<Billmaster> billSettlementList(Date billno);
public Integer saveFeedbackFormOfManual(Integer formid, Integer guestId, Integer billno, Integer sbuid);
public Feedbackformmaster getFeedbackFormMasterByBillno(Integer billno);
public Object updatefeedbackformmaster(Integer feedbackformId);


}



