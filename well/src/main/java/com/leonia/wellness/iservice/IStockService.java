package com.leonia.wellness.iservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.leonia.wellness.dto.StockListDisplayDTO;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Suppliermaster;

public interface IStockService {
	
	public List<Stockgroupmaster> getsubgroupDetails(String groupname);
	public String addStock(Stockmaster stockMaster);
	public List<StockListDisplayDTO> getStockList();
	public List<StockListDisplayDTO> getSearchStockList(String name);
	public Integer getstockavailability(Integer stockid);
	public List<Suppliermaster> getsupplierlist();
	public Integer getStockGroupMasterId(String stockGroupName);
	public Integer getStockSubGroupId(String stockSubGroupName);
	public StockListDisplayDTO fetchSelectedStock(Integer stockId);
	public int updateStockDetails(String stExpireDate, StockListDisplayDTO stockDetails);
	public String stockAdjustment(Integer stockId, Integer qua, String remarks, Date date1);
	public List<Manufacturermaster> getmanufacturerlist();
	public String addstockrefill(Stockreceipt stockrel, Integer stockId, Integer supplierid, Integer manufacturerid);
	public List<Stockmaster> getStockDetails();
	public String addSuppliers(Suppliermaster supplier);
	public List<Suppliermaster> getSupplierList();
	public String saveManufacturerDetails(Manufacturermaster manufacturer);
	public List<Manufacturermaster> getManufacturerList();
	public void updateSuppliers(Suppliermaster suppliers);
	public Suppliermaster fetchSupplierWithId(Integer id);
	public Manufacturermaster fetchManufacturerWithId(Integer id);
	public void updateManufacturer(Manufacturermaster manufacturer);
	public void updatestockrate(Integer stockId);
	public Map<Integer, String> getSupplier();
	public Map<Integer, String> getManufacturer();
	public Map<Integer, String> getStockType();
}
