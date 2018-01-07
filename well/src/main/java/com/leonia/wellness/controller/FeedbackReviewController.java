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

import com.leonia.wellness.dto.ReviewResponse;
import com.leonia.wellness.entity.Feedbackdetails;
import com.leonia.wellness.entity.Feedbackformmaster;
import com.leonia.wellness.entity.Formmaster;
import com.leonia.wellness.entity.Questionmaster;
import com.leonia.wellness.entity.Responsemaster;
import com.leonia.wellness.iservice.IFeedbackReviewService;

@Controller
public class FeedbackReviewController {
	
	@Autowired
	public IFeedbackReviewService iFeedbackreviewservice;
	
	//Displaying Page to the jsp form "Feedbackreviewform"
	@RequestMapping("/openFeedbackreviewform")
	public ModelAndView openFeedbackreviewPage(){
		List<Formmaster> fdetails = iFeedbackreviewservice.getFormdetails();
		ModelAndView mv = new ModelAndView();
		mv.addObject("FormModeList",fdetails);
		mv.setViewName("Feedbackreviewform");
		return mv;
	}
	
	//Getting the data from the Jsp Page
	@RequestMapping("/searchbydate")
	public ModelAndView getReviewDetails(HttpServletRequest req,HttpServletResponse res){
		String fromdate = req.getParameter("fromdate");
		String todate = req.getParameter("todate");
		Integer fid = Integer.parseInt(req.getParameter("formMode"));
		System.out.println("Fid**************************************"+fid);
		System.out.println("todate**************************************"+todate);
		System.out.println("fromdate**************************************"+fromdate);
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
		int esum = 0;
		int vsum = 0;
		int gsum = 0;
		int fsum = 0;
		int psum = 0;
		int vpsum = 0;
		int eproduct = 0;
		int vproduct = 0;
		int gproduct = 0;
		int fproduct = 0;
		int pproduct = 0;
		int vpproduct = 0;
		int prosum = 0;
		int totalsum = 0;
		int i=0;
		String formdes = null;
	
	    List<Responsemaster> reslist = iFeedbackreviewservice.getreposnedata();
	     for(Responsemaster resp:reslist){
	    	 switch(resp.getResponseid()){
	    	 case 1:
	    		 eproduct=resp.getResponsescore();
	    		
	    		 break;
	    	 
	     	 case 2:
	     		 vproduct=resp.getResponsescore();
	     		
	     		 break;
	     	 
			 case 3:
				 gproduct=resp.getResponsescore();
				
				 break;
	 		 
			 case 4:
				 fproduct=resp.getResponsescore();
				 
				 break;
			 
			 case 5:
				 pproduct=resp.getResponsescore();
				
				 break;
			 
			 case 6:
				 vpproduct=resp.getResponsescore();
				
	    		 break;
	    	 }
	    	 
	     }
	    //Sending and Getting the Data from FeedbackReviewDAOImpl
		List <Feedbackdetails> rlist = iFeedbackreviewservice.getReviewDetails(date1,date2,fid);
		List<Feedbackformmaster> formdata = iFeedbackreviewservice.getFormList(date1,date2,fid);
		List<Questionmaster> qlist = iFeedbackreviewservice.getQuestionDescription(fid);
		List questionrelatedcount = new LinkedList();
		List sumrelatedcount = new LinkedList();
		List prorelatedcount = new LinkedList();
	
		ReviewResponse rdto1 = new ReviewResponse();
		ReviewResponse rdto2 = new ReviewResponse();
		
		List<Formmaster> fdetails = iFeedbackreviewservice.getFormdetails();
		Formmaster ffid = iFeedbackreviewservice.getFormRes(fid);
		if(fid == ffid.getFormid()){
			formdes = ffid.getFormdescription();
		}
		for(Questionmaster qes:qlist){
			int ecount = 0;
			int vcount = 0;
			int gcount = 0;
			int fcount = 0;
			int pcount = 0;
			int vpcount = 0;
			
				for(Feedbackdetails resp:rlist){
					
					if(qes.getQuestionid()==resp.getQuestionid())
							{
						switch(resp.getResponsemaster().getResponseid()){
						case 1:
								ecount++;
								break;
						case 2:
								vcount++;
								break;
						case 3:
								gcount++;
								break;
						case 4:
								fcount++;
								break;
						case 5:
								pcount++;
								break;
						case 6:
								vpcount++;
								break;
						default:
							         break;	
		
						}
						
					}
					
				}
			ReviewResponse rdto = new ReviewResponse();
			
			rdto.setQuestionid(qes.getQuestionid());
			rdto.setECount(ecount);
			esum = esum + ecount;
			System.out.println(esum+"esum**********");
			rdto.setVCount(vcount);
			vsum = vsum + vcount;
			System.out.println(vsum+"vsum_____________");
			rdto.setGCount(gcount);
			gsum = gsum + gcount;
			System.out.println(gsum+"gsum==============");
			rdto.setFCount(fcount);
			fsum = fsum + fcount;
			System.out.println(fsum+"fsum############");
			rdto.setPCount(pcount);
			psum = psum + pcount;
			System.out.println(psum+"psum@@@@@@@@@@@@@@@@@@");
			rdto.setVPCount(vpcount);
			vpsum = vpsum + vpcount;
			System.out.println(vpsum+"vpsum%%%%%%%%%%%%%%%%");
			rdto.setTotal(pcount+ecount+vcount+gcount+fcount+vpcount);
			questionrelatedcount.add(rdto);
			
		}
		
		rdto1.setSumECount(esum);
		rdto1.setSumVCount(vsum);
		rdto1.setSumGCount(gsum);
		rdto1.setSumFCount(fsum);
		rdto1.setSumPCount(psum);
		rdto1.setSumVPCount(vpsum);
		sumrelatedcount.add(rdto1);
		int qescount = qlist.size();
		totalsum = esum + vsum + gsum + fsum + psum + vpsum;
		int alltotal = totalsum;
		
		int formcount = formdata.size();
		
		
		int maxscore = (totalsum*eproduct);
		
		eproduct = eproduct * esum;
		vproduct = vproduct * vsum;
		gproduct = gproduct * gsum;
		fproduct = fproduct * fsum;
		pproduct = pproduct * psum;
		vpproduct = vpproduct * vpsum;
		
		prosum = eproduct + vproduct + gproduct + fproduct + pproduct + vpproduct;
		
		i = prosum * 100;
		int performindex = 0;
		if(i!=0 && maxscore!=0){
		performindex = i/maxscore;
		}
	/*	performindex = Double.parseDouble(new DecimalFormat("#.00").format());*/
		System.out.println(performindex);
		
		rdto2.setProECount(eproduct);
		rdto2.setProVCount(vproduct);
		rdto2.setProGCount(gproduct);
		rdto2.setProFCount(fproduct);
		rdto2.setProPCount(pproduct);
		rdto2.setProVPCount(vpproduct);
		rdto2.setSumtotal(prosum);
		
		prorelatedcount.add(rdto2);
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Feedbackreviewform");
		mv.addObject("fromdate", fromdate);
		mv.addObject("formid", fid);
		mv.addObject("todate", todate);
		mv.addObject("qlist",qlist);
		mv.addObject("FormModeList",fdetails);
		mv.addObject("rcount",questionrelatedcount);
		mv.addObject("sumlist", sumrelatedcount);
		mv.addObject("prolist", prorelatedcount);
		mv.addObject("attribute", alltotal);
		mv.addObject("qescount", qescount);
		mv.addObject("formcount", formcount);
		mv.addObject("maxscore", maxscore);
		mv.addObject("performindex", performindex);
		mv.addObject("fdescription", formdes);
		mv.addObject("excellentscore", eproduct);
		
		
		return mv;
	}
	
}
