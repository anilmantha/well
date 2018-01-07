package com.leonia.wellness.iservice;

import java.util.List;

import com.leonia.wellness.entity.Employeeroster;
import com.leonia.wellness.entity.Staffmaster;

public interface IEmployeeRosterService {



public List<Staffmaster> getStaffmaster();

public List<Staffmaster> getSearchStaffName(String staffname);



public Staffmaster fetchSelectedStaff(Integer staffid);



public List<Employeeroster> getRosterList();

public int insetrtRoster(Integer staffId, Integer slemonth, Integer year, String weekDay, Integer numberofdays);

public String checkmonthyear(Integer staffId, Integer slemonth, Integer year);

public List<Integer> getRosterStatus(Integer staffId, Integer slemonth, Integer year);

public String UpdateRoster(Integer staffId, Integer slemonth, Integer year, List<Integer> updateArr, String weekDay);



}
