/*package com.leonia.wellness.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.StaffRegister;
import com.leonia.wellness.idao.StaffDAO;

@Repository
public class StaffDAOImpl implements StaffDAO{
	

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<StaffRegister> getstaffname() {
		Session session = sessionFactory.openSession();
		List<StaffRegister> ls = session.createCriteria(StaffRegister.class).list();
		session.close();
		return ls;
		
	}

	
}
*/