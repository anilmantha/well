package com.leonia.wellness.iservice;

import java.util.List;

import com.leonia.wellness.entity.Login;

public interface ILoginService {
	
	List<Login> loginCheck(Login login);

}
