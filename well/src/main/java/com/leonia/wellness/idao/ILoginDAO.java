package com.leonia.wellness.idao;

import java.util.List;

import com.leonia.wellness.entity.Login;

public interface ILoginDAO {
	
	List<Login> loginCheck(Login login);

}
