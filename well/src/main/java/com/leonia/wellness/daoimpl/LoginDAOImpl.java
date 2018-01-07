package com.leonia.wellness.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Login;
import com.leonia.wellness.idao.ILoginDAO;

@Service
public class LoginDAOImpl implements ILoginDAO  {

		Login login =new Login();
		
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public List<Login> loginCheck(Login login) {
			
			String QUERY = "from Login u where u.username='" + login.getUsername() + "' and u.password='" + login.getPassword() + "'";
			System.out.println(QUERY);
			@SuppressWarnings("unchecked")
			List<Login> loginList = sessionFactory.openSession().createQuery(QUERY).list();
	  
				return loginList ;

		}
		
		
		


		

		
}
