/*package com.leonia.wellness.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leonia.wellness.entity.Rooms;
import com.leonia.wellness.idao.RoomsDAO;

@Repository
public class RoomsDAOImpl implements RoomsDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Rooms> getRooms() {
		Session session = sessionFactory.openSession();
		List<Rooms> ls = session.createCriteria(Rooms.class).list();
		session.close();
		return ls;
	}
	
	
	
}
*/