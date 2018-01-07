package com.leonia.wellness.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Employeeroster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.idao.IEmployeeRosterDAO;
import com.leonia.wellness.iservice.IEmployeeRosterService;

@Service
public class EmplopyeeRosterServiceimpl implements IEmployeeRosterService {
	@Autowired
	public IEmployeeRosterDAO iEmployeeRosterDao;


	@Override
	public List<Staffmaster> getStaffmaster() {

		return iEmployeeRosterDao.getStaffMaster();
	}

	@Override
	public List<Staffmaster> getSearchStaffName(String staffname) {
	
		return iEmployeeRosterDao.getStaffName(staffname);
	}
	
	@Override
	public Staffmaster fetchSelectedStaff(Integer staffid) {
		
		return iEmployeeRosterDao.fetchSelectedStaff(staffid);
	}

	@Override
	public List<Employeeroster> getRosterList() {
		
		return iEmployeeRosterDao.getRosterList();
	}

	@Override
	public int insetrtRoster(Integer staffId, Integer slemonth, Integer year, String weekDay, Integer numberofdays) {
		// TODO Auto-generated method stub
		return iEmployeeRosterDao.insetrtRoster(staffId, slemonth, year, weekDay, numberofdays);
	}

	@Override
	public String checkmonthyear(Integer staffId, Integer slemonth, Integer year) {
		// TODO Auto-generated method stub
		return iEmployeeRosterDao.checkmonthyear(staffId, slemonth, year);
	}

	@Override
	public List<Integer> getRosterStatus(Integer staffId, Integer slemonth, Integer year) {
		
		return iEmployeeRosterDao.getRosterStatus(staffId, slemonth, year);
		
	}

	@Override
	public String UpdateRoster(Integer staffId, Integer slemonth, Integer year, List<Integer> updateArr, String weekDay) {
	
		return iEmployeeRosterDao.UpdateRoster(staffId, slemonth, year,  updateArr, weekDay);
	}
}
