package com.leonia.wellness.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.entity.Billmaster;
import com.leonia.wellness.entity.Billsettlement;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Prioritymaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.entity.Sbumaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Statusmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Ticketmaster;
import com.leonia.wellness.entity.Tickettypemaster;
import com.leonia.wellness.iservice.iCustomerFeedbackService;



@Controller
public class CustomerFeedbackController {

	@Autowired
	private iCustomerFeedbackService customerfeedbackservice;
	
    @RequestMapping(value = "/customerfeedback")
	public ModelAndView getGuestdetails(HttpServletRequest request, HttpServletResponse response ) {
		
    	String feedbackformid = request.getParameter("feedbackformid");
    	String guestId1 = request.getParameter("guestid");
    	String billno1 = request.getParameter("billno");
    	String sbuid1 = request.getParameter("sbuid");
    	Integer guestId = 0;
    	Integer billno = 0;
    	Integer sbuid = 0;
    	//String formid = request.getParameter("formid");
    	Integer feedbackFormId = 0;
    	if(feedbackformid!=null && !(feedbackformid.isEmpty()))
    	{
    		/*feedbackFormId = Integer.parseInt(feedbackformid);*/
    		feedbackFormId = 1;
    		
    	}
    	else{
    		if(guestId1!=null || !(guestId1.isEmpty())){
        		guestId = Integer.parseInt(guestId1);
        	}
    		if(billno1!=null || !(billno1.isEmpty())){
    			billno = Integer.parseInt(billno1);
    		}
    		if(sbuid1!=null || !(sbuid1.isEmpty())){
    			sbuid = Integer.parseInt(sbuid1);
    		}
    		feedbackFormId = customerfeedbackservice.saveFeedbackFormOfManual(1001,guestId,billno,sbuid);
    		 
    	}
    	 Feedbackformmaster feedbackformMaster = customerfeedbackservice.getFeedbackFormMasterByBillno(billno);
		 Feedbackformmaster feedbackFormMaster = customerfeedbackservice.getFeedbackFormDetailsById(feedbackFormId);
		 List<Questionmaster> questiondetails=customerfeedbackservice.getQuestionInfo(feedbackFormMaster.getFormmaster().getFormid());
		 List<Responsemaster> responsedetails=customerfeedbackservice.getResponseDetails();
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("formvalue", feedbackFormMaster.getFormmaster());
         mv.addObject("feedbackformid", feedbackFormMaster.getFeedbackformid());
         mv.addObject("feedbackform", feedbackFormMaster);
		 mv.addObject("guestdetails", feedbackFormMaster.getGuestmaster());
		 mv.addObject("responsedetails", responsedetails);
		 mv.addObject("questiondetails",questiondetails);
		 mv.setViewName("CustomerFeedbackForm");
    	 return mv;
		}
	//saving response
	 	@RequestMapping(value = "/saveResponse")
	 	public  ModelAndView  saveResponse(HttpServletRequest request, HttpServletResponse response ){
	 	Feedbackdetails feedbackDetails=new  Feedbackdetails();
	 	Guestmaster guestmaster=new Guestmaster();
		Departmentmaster departmentmaster=new Departmentmaster();
		Feedbackformmaster feedbackformmaster=new Feedbackformmaster();
		Formmaster formmaster=new Formmaster();
		Responsemaster responsemaster=new Responsemaster();
		Integer guestId=Integer.parseInt(request.getParameter("guestid"));
        Integer  formId=Integer.parseInt(request.getParameter("formid"));
        Integer  feedbackformId=Integer.parseInt(request.getParameter("feedbackformid"));
        
        Integer  sbuId=Integer.parseInt(request.getParameter("Sbuid"));
        Integer responseScore = null;
        Integer questionCount = customerfeedbackservice.questionCount(formId);
        formmaster.setFormid(formId);
    	guestmaster.setGuestid(guestId);
    	feedbackformmaster.setGuestmaster(guestmaster);
    	feedbackformmaster.setFeedbackformid(feedbackformId);
    	feedbackformmaster.setFormmaster(formmaster);
        Sbumaster sbumaster=new Sbumaster();
        sbumaster.setSbuid(sbuId);
		feedbackformmaster.setSbumaster(sbumaster);
		    for(int i=1;i<=questionCount;i++){
		    	
		    	Integer responseinfo =Integer.parseInt(request.getParameter("ques"+i));
	            responsemaster.setResponseid(responseinfo);
	            
	            switch (responseinfo) {
				case 5:
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					break;
				case 4:
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					break;
				case 3:
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					break;
				case 2:
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					break;
				case 1:
					
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					
					break;
				case 0:
					responseScore=Integer.parseInt(request.getParameter(""+responseinfo));
					break;
				default:
					break;
				}
	            
	            feedbackDetails.setFeedbackformmaster(feedbackformmaster);
	            feedbackDetails.setResponsemaster(responsemaster);
	            feedbackDetails.setQuestionid(i);
	            customerfeedbackservice.saveResponse(feedbackDetails);
		        //Ticket generation 
		        if(responseinfo==4 || responseinfo==5){
		        	    Integer  departmentid=Integer.parseInt(request.getParameter("dept"+i));
			    	    departmentmaster.setDepartmentid((departmentid));
		        	    Ticketmaster ticketmaster=new Ticketmaster();
		        	    responsemaster.setResponseid(responseinfo);
		                ticketmaster.setResponsemaster(responsemaster);
		                ticketmaster.setQuestionid(i);
		                 List<Questionmaster> questionInfo = customerfeedbackservice.getQuestionInfo(formId);
		                for (Questionmaster questionmaster : questionInfo) {
							if(questionmaster.getQuestionid()==i){
								ticketmaster.setComplaint(questionmaster.getQuestiondescription());
								
							}
						}
		                Integer billNo=Integer.parseInt(request.getParameter("billno"));
		                ticketmaster.setFeedbackformmaster(feedbackformmaster);
		                ticketmaster.setDepartmentmaster(departmentmaster);
		                ticketmaster.setAssignedto(departmentid);
		                ticketmaster.setRemarks("None");
		                ticketmaster.setValid(true);
		                ticketmaster.setResponsemaster(responsemaster);
		                ticketmaster.setGuestmaster(guestmaster);
		                ticketmaster.setFormmaster(formmaster);
		                Statusmaster statusMaster = new Statusmaster();
		                statusMaster.setStatusid(6);
		                ticketmaster.setStatusmaster(statusMaster);
		                ticketmaster.setSbumaster(sbumaster);
		                /*Servicemaster servicemaster = new Servicemaster();
		                servicemaster.setServiceid(1);
		                ticketmaster.setServicemaster(servicemaster);*/
		                Prioritymaster prioritymaster=new Prioritymaster();
		                prioritymaster.setPriorityid(2);
						ticketmaster.setPrioritymaster(prioritymaster);
						Billmaster billmaster=new Billmaster();
						billmaster.setBillno(billNo);
						ticketmaster.setBillmaster(billmaster);
						ticketmaster.setTicketdate(new Date());
						Tickettypemaster tickettypemaster=new Tickettypemaster();
		                tickettypemaster.setTickettypeid(1);
						ticketmaster.setTickettypemaster(tickettypemaster);
						Staffmaster staffMaster=new Staffmaster();
						staffMaster.setStaffid(2);
						ticketmaster.setStaffmaster(staffMaster);
		            	customerfeedbackservice.generateTicket(ticketmaster);
		        }
		           
		        }
		    	customerfeedbackservice.updatefeedbackformmaster(feedbackformId);
		    	ModelAndView mv=new  ModelAndView();
		        mv.setViewName("Feedback");
			    return mv;
		    }
	 	
	 	
	 	
	 		@RequestMapping("/displayTickets")
	 		public ModelAndView displayTickets(){
	 			String pageName=null;
	 			List<Ticketmaster> ticketCount=customerfeedbackservice.ticketCount();
	 			List<Ticketmaster> openTickets=customerfeedbackservice.openCount();
	 			Integer ticketListCount=ticketCount.size();
	 			List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
	 			List<Statusmaster> statusname = customerfeedbackservice.getStatus();
	 			List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
	 			
	 			List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(null, null,null,null,null,1);
	 			Pagination pagination = customerfeedbackservice.ticketsCount(pageName,null, null,null,null,null);
	 			pagination.setCurrentPageNum(1);
	 			
	 			ModelAndView mv = new ModelAndView();
	 			
	 			mv.addObject("pageName", "pageName");
	 			mv.addObject("paging1", pagination);
	 			mv.addObject("departmentnames",departmentnames);
	 			mv.addObject("statusname",statusname);
	 			mv.addObject("tickettype",tickettype);
	 			mv.addObject("ticketListCount",ticketListCount);
	 			mv.addObject("openTickets",openTickets.size());
	 			mv.addObject("ticketslist",tiketslist);
	 			mv.setViewName("Tickets");
	 			return mv;
	 		}
	    
	     
	 		@RequestMapping(value = "/searchticketsList")
	       public  ModelAndView ticketsGenerateList(HttpServletRequest request, HttpServletResponse response )
	       {   
	 			String fromdate =request.getParameter("fromdate");
		    	String todate =request.getParameter("todate");
	    	    String pageName=request.getParameter("pageName");
	    	    Date date1 = null;
	    	    Date date2 = null;
	   		//Parsing the String type Data into Date type  
	   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   		try {
	   			if(fromdate!=null && !(fromdate.isEmpty()) && todate!=null && !(todate.isEmpty())){
	   			date1 = sdf.parse(fromdate);
				date2 = sdf.parse(todate);
	   			}
	   		} catch (ParseException e) {
	   			e.printStackTrace(); 
	   		}
	   			String departmentId = request.getParameter("deptMode");
	   			String statustype = request.getParameter("statusMode"); 
	   	 		String sourcetype = request.getParameter("ticketMode");
	   	 	    String recordsStr=request.getParameter("search");
	   	 	    if(recordsStr.equals("search")){
				recordsStr="1";
	   	 	    }
			    int records=Integer.parseInt(recordsStr);
	   	 		List<Ticketmaster> ticketCount=customerfeedbackservice.ticketCount();
	   	 		List<Ticketmaster> openTickets=customerfeedbackservice.openCount();
	   	 		
	   	 	List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
	 		List<Statusmaster> statusname = customerfeedbackservice.getStatus();
	 		List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
 			Pagination pagination = customerfeedbackservice.ticketsCount(pageName,date1, date2,departmentId,statustype,sourcetype);
	 		pagination.setCurrentPageNum(records);

	 		
	   	 	List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(date1, date2,departmentId,statustype,sourcetype,records);
			ModelAndView modelview = new ModelAndView();
	    	modelview.addObject("fromdate" ,fromdate);
	    	modelview.addObject("todate", todate);
	    	    if(departmentId.equals("Select")){
	    	    	modelview.addObject("departmentid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("departmentid",   Long.parseLong(departmentId));
	    	    }
	    	    if(statustype.equals("Select")){
	    	    	modelview.addObject("statusid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("statusid",   Long.parseLong(statustype));
	    	    }
	    	    if(sourcetype.equals("Select")){
	    	    	modelview.addObject("tickettypeid",   0);
	    	    }
	    	    else{
	    	    	 modelview.addObject("tickettypeid",Long.parseLong(sourcetype) );
	    	    }
	    	    modelview.addObject("ticketslist",tiketslist);
	    	    modelview.addObject("departmentnames",departmentnames);
	    	    modelview.addObject("statusname",statusname);
	    	    modelview.addObject("tickettype",tickettype);
	  		 	modelview.addObject("ticketListCount",ticketCount.size());
	    	    modelview.addObject("openTickets",openTickets.size());
	    	   	modelview.setViewName("Tickets");
	    	   	modelview.addObject("paging1", pagination);
	  		 	return  modelview ;
	    	  }
	     //Open Ticket
	       @RequestMapping(value = "/openticketnopage")
	       public ModelAndView sendData(HttpServletRequest request,HttpServletResponse response){
	    	   
	    	   Integer ticketno =  Integer.parseInt(request.getParameter("ticketno"));
	    	   Ticketmaster  ticketdetails = customerfeedbackservice.getTicketDetails(ticketno);
	    	   
	    	   List<Statusmaster> statusname = customerfeedbackservice.getStatus();
	    	   ModelAndView mv = new ModelAndView();
	    	   mv.setViewName("openticketnopage");
	    	   mv.addObject("statusname", statusname);
	    	   mv.addObject("ticketdetails", ticketdetails);
	    	   
	    	   return mv;
	       }
	       //update ticket status
	      @RequestMapping(value = "/updateTicketStatus")
	       public ModelAndView saveTicketClosing(HttpServletRequest request,HttpServletResponse response){
	    	Ticketmaster ticketmaster=new Ticketmaster();
	    	Integer ticketNo=Integer.parseInt(request.getParameter("ticketNo"));
	    	Integer statusId=Integer.parseInt(request.getParameter("ticketStatus"));
	    	String remarks=request.getParameter("remarks");
	    	ticketmaster.setRemarks(remarks);
	    	ticketmaster.setTicketno(ticketNo);
	    	Statusmaster statusmaster=new Statusmaster();
	    	statusmaster.setStatusid(statusId);
			ticketmaster.setStatusmaster(statusmaster);
			customerfeedbackservice.saveTicketClosing(ticketmaster);
			String pageName=null;
 			List<Ticketmaster> ticketCount=customerfeedbackservice.ticketCount();
 			List<Ticketmaster> openTickets=customerfeedbackservice.openCount();
 			Integer ticketListCount=ticketCount.size();
 			List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
 			List<Statusmaster> statusname = customerfeedbackservice.getStatus();
 			List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
 			
 			List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(null, null,null,null,null,1);
 			Pagination pagination = customerfeedbackservice.ticketsCount(pageName,null, null,null,null,null);
 			pagination.setCurrentPageNum(1);
 			ModelAndView mv = new ModelAndView();
 			mv.addObject("pageName", "pageName");
 			mv.addObject("paging1", pagination);
 			mv.addObject("departmentnames",departmentnames);
 			mv.addObject("statusname",statusname);
 			mv.addObject("tickettype",tickettype);
 			mv.addObject("ticketListCount",ticketListCount);
 			mv.addObject("openTickets",openTickets.size());
 			mv.addObject("ticketslist",tiketslist);
			mv.setViewName("Tickets");
		    return mv;
}
	      
	       @RequestMapping("/displayDash")
	 		public ModelAndView displayBoard(){
	    	   
	    	List lastlist = new LinkedList();   
	    	List<Ticketmaster> ticketopen = customerfeedbackservice.getOpenlist();
	    	List<Ticketmaster> ticketclose = customerfeedbackservice.getCloselist();
	    	List<Departmentmaster> deptmaster = customerfeedbackservice.getDeptData();
	    	for (Departmentmaster departmentmaster : deptmaster) {
	    		List<Ticketmaster> opencount = customerfeedbackservice.getOpentCount(departmentmaster.getDepartmentid());
	    	    lastlist.add(opencount.size());
			}
	    	  
	    	ModelAndView mv = new ModelAndView();
	    	mv.addObject("ticketopen", ticketopen.size());
	 		mv.addObject("ticketclose", ticketclose.size());
	 		
	 		mv.addObject("Deptcount", lastlist);
	 		mv.addObject("Deptmaster", deptmaster);
			mv.setViewName("TicketDashboard");
			return mv;
	 		}
	       
	       @RequestMapping("/displayListTickets")
	 		public ModelAndView displayListTickets(){
	 		List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
	 		List<Statusmaster> statusname = customerfeedbackservice.getStatus();
	 		List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
			ModelAndView mv = new ModelAndView();
			mv.addObject("departmentnames",departmentnames);
			mv.addObject("statusname",statusname);
			mv.addObject("tickettype",tickettype);
			mv.setViewName("TicketReviewReport");
			return mv;
	 		}
	     
	       @RequestMapping(value = "/searchticketsreportList")
	       public  ModelAndView searchTicketsReportList(HttpServletRequest request, HttpServletResponse response )
	       {   
	    	   String fromdate =request.getParameter("fromdate");
		    	  
	    	   String todate =request.getParameter("todate");
	    	   String pageName=request.getParameter("pageName");
	    	   Date date1 = null;
	    	   Date date2 = null;
	   		//Parsing the String type Data into Date type  
	   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   		try {
	   			if(fromdate!=null && !(fromdate.isEmpty()) && todate!=null && !(todate.isEmpty())){
	   			date1 = sdf.parse(fromdate);
				date2 = sdf.parse(todate);
	   			}
	   		} catch (ParseException e) {
	   			e.printStackTrace(); 
	   		}
	   			String departmentId = request.getParameter("deptMode");
	   			String statustype = request.getParameter("statusMode"); 
	   	 		String sourcetype = request.getParameter("ticketMode");
	   	 	    String recordsStr=request.getParameter("search");
	   	 	    if(recordsStr.equals("search")){
				recordsStr="1";
	   	 	    }
			    int records=Integer.parseInt(recordsStr);
	   	 		List<Ticketmaster> ticketCount=customerfeedbackservice.ticketCount();
	   	 		List<Ticketmaster> openTickets=customerfeedbackservice.openCount();
	   	 	
	   	 	
	   	 	List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
	 		List<Statusmaster> statusname = customerfeedbackservice.getStatus();
	 		List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
 			Pagination pagination = customerfeedbackservice.ticketsCount(pageName,date1, date2,departmentId,statustype,sourcetype);
	 		pagination.setCurrentPageNum(records);
	   	 	List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(date1, date2,departmentId,statustype,sourcetype,records);
			ModelAndView modelview = new ModelAndView();
	    	modelview.addObject("fromdate" ,fromdate);
	    	modelview.addObject("todate", todate);
	    	    if(departmentId.equals("Select")){
	    	    	modelview.addObject("departmentid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("departmentid",   Long.parseLong(departmentId));
	    	    }
	    	    if(statustype.equals("Select")){
	    	    	modelview.addObject("statusid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("statusid",   Long.parseLong(statustype));
	    	    }
	    	    if(sourcetype.equals("Select")){
	    	    	modelview.addObject("tickettypeid",   0);
	    	    }
	    	    else{
	    	    	 modelview.addObject("tickettypeid",Long.parseLong(sourcetype) );
	    	    }
	    	    modelview.addObject("totaltickets",ticketCount.size());
	    	    modelview.addObject("opentickets",openTickets.size());
	    	    modelview.addObject("ticketslist",tiketslist);
	    	    modelview.addObject("departmentnames",departmentnames);
	    	    modelview.addObject("statusname",statusname);
	    	    modelview.addObject("tickettype",tickettype);
	    	   	modelview.setViewName("TicketReviewReport");
	    	   	return  modelview ;
	    	  }
	       
	       @RequestMapping(value = "/ticketsGenerateList")
	       public  ModelAndView ticketsReportGenerateList(HttpServletRequest request, HttpServletResponse response )
	       {   
				
	    	   String fromdate =request.getParameter("fromdate");
	    	  
	    	   String todate =request.getParameter("todate");
	    	  
	    	   Date date1 = new Date();
	    	   Date date2 = new Date();
	   		//Parsing the String type Data into Date type  
	   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   		try {
	   			date1 = sdf.parse(fromdate);
				date2 = sdf.parse(todate);
	   		} catch (ParseException e) {
	   			e.printStackTrace(); 
	   		}
	   			String departmentId = request.getParameter("deptMode");
	   			String statustype = request.getParameter("statusMode"); 
	   	 		String sourcetype = request.getParameter("ticketMode");
	   	 		
				List<Ticketmaster> tiketslist = customerfeedbackservice.ticketsList(date1, date2,departmentId,statustype,sourcetype);
				List< Departmentmaster> departmentnames = customerfeedbackservice.getDepartmentNames();
		 		List<Statusmaster> statusname = customerfeedbackservice.getStatus();
		 		List<Tickettypemaster> tickettype = customerfeedbackservice.getTicketType();
	   	 		ModelAndView modelview = new ModelAndView();
	    	    modelview.addObject("fromdate" ,fromdate);
	    	    modelview.addObject("todate", todate);
	    	    if(departmentId.equals("Select")){
	    	    	modelview.addObject("departmentid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("departmentid",   Long.parseLong(departmentId));
	    	    }
	    	    if(statustype.equals("Select")){
	    	    	modelview.addObject("statusid",   0);
	    	    }
	    	    else{
	    	    	modelview.addObject("statusid",   Long.parseLong(statustype));
	    	    }
	    	    if(sourcetype.equals("Select")){
	    	    	modelview.addObject("tickettypeid",   0);
	    	    }
	    	    else{
	    	    	 modelview.addObject("tickettypeid",Long.parseLong(sourcetype) );
	    	    }
	    	    
	    	    modelview.addObject("ticketslist",tiketslist);
	    	    modelview.addObject("departmentnames",departmentnames);
	    	    modelview.addObject("statusname",statusname);
	    	    modelview.addObject("tickettype",tickettype);
	    	   	modelview.setViewName("TicketReviewReport");
	  		 	return  modelview ;
	    	   
	       }
	       
	       @RequestMapping(value="/billsSearchByDate")
	       public ModelAndView feedbackNotGivenList(HttpServletRequest request, HttpServletResponse response) {
	    	   String date =request.getParameter("fromdate");
	    	   Date date1 = null;
	    	   List<Billmaster> billsettledlist = new LinkedList();
	    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   		try {
		   			if(date!=null && !(date.isEmpty())){
		   			date1 = sdf.parse(date);
					}
		   		} catch (ParseException e) {
		   			e.printStackTrace(); 
		   		}
		   		List<Billmaster> billSettlementList=customerfeedbackservice.billSettlementList(date1);
		   		for (Billmaster billmaster : billSettlementList) {
		   			Feedbackformmaster feedbackNotGivenList = customerfeedbackservice.feedbackNotGivenList(date1, billmaster.getBillno());
		   			if(feedbackNotGivenList==null){
		   				billsettledlist.add(billmaster);
		   				
		   			}
				}
		   		Integer formid = 1001;
		   		ModelAndView mv = new ModelAndView();
		   		mv.setViewName("Feedbackformlistbydate");
		   		mv.addObject("billsettledlist",billsettledlist);
		   		mv.addObject("formid",formid);
	    	   return mv;
			
		}
	       
         @RequestMapping(value="/openBillsPage")
	       public String openBillsPageOfFeedback(){
	    	   return "Feedbackformlistbydate";
	       }

}	  
	    
