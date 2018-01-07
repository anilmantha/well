package com.leonia.wellness.idao;

import java.util.List;

import com.leonia.wellness.entity.Employeeroster;
import com.leonia.wellness.entity.Staffmaster;

public interface IEmployeeRosterDAO {

	List<Staffmaster> getStaffMaster();
	List<Staffmaster> getStaffName(String staffname);
	List<Employeeroster> getRosterList();
	Staffmaster fetchSelectedStaff(Integer staffid);
	int insetrtRoster(Integer staffId, Integer slemonth, Integer year, String weekDay, Integer numberofdays);
	String checkmonthyear(Integer staffId, Integer slemonth, Integer year);
	List<Integer> getRosterStatus(Integer staffId, Integer slemonth, Integer year);
	String UpdateRoster(Integer staffId, Integer slemonth, Integer year, List<Integer> updateArr, String weekDay);

}