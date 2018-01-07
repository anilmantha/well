package com.leonia.wellness.controller;



import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leonia.wellness.entity.Guestmaster;
import com.leonia.wellness.entity.Login;
import com.leonia.wellness.iservice.ICustomersService;
import com.leonia.wellness.iservice.ILoginService;

@Controller
public class LoginController {

	@Autowired
	public ILoginService loginService;
	/*@Autowired
	public Data data;*/
	@Autowired
	private ICustomersService iCustomersService;
	

	//This controller displays home.jsp 
	@RequestMapping(value="/home",method=RequestMethod.POST)
	public String displaylogin(@RequestParam("username") String user,@RequestParam("password") String pass)
	{
		        Login login = new Login();
		        login.setUsername(user);
		        login.setPassword(pass);
   
		System.out.println("user"+user);
		List<Login> list=loginService.loginCheck(login);
		if(list.size()!=0)
		{
			Login myusername=list.get(0);
			  String uname=myusername.getUsername();
			  String passed=myusername.getPassword();
			 
			 if(uname.equals(user) && passed.equals(pass))
			 {
				 List<Guestmaster> ls=iCustomersService.getAppointements();

				/* return new ModelAndView("home","records",ls);*/
				 return "redirect:/appointmentslist";
				  
			 }
			 return null;
		}
	 else{
		 /*return new ModelAndView("login","loginmessage","Username or Password are invalid");*/
		 return "redirect:/";
	 }
		
	}
	
	@RequestMapping(value="/dashboard")
	public ModelAndView  dashboard()
	{
		 List<Guestmaster> ls=iCustomersService.getAppointements();
		 
		  return new ModelAndView("home","records",ls);
	}
	
	@RequestMapping(value="/")
	public String  loginPage()
	{
		 
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String  logout(HttpSession session)
	{
		 session.invalidate();
		 return "redirect:/";
	}
}
