package com.leonia.wellness.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Login;
import com.leonia.wellness.idao.ILoginDAO;
import com.leonia.wellness.iservice.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	
	@Autowired
	private ILoginDAO loginDAO;

	@Override
	public List<Login> loginCheck(Login login) {
		// TODO Auto-generated method stub
		
		return loginDAO.loginCheck(login);
	}

	

	
}
