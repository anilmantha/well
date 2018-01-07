package com.leonia.wellness.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.StockListDisplayDTO;
import com.leonia.wellness.entity.Manufacturermaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stockreceipt;
import com.leonia.wellness.entity.Suppliermaster;
import com.leonia.wellness.idao.IStockDAO;
import com.leonia.wellness.iservice.IStockService;

@Service
public class StockServiceImpl implements IStockService {
	
	@Autowired
	private IStockDAO iStockDAO;
		

	
	@Override
	public List<Stockgroupmaster> getsubgroupDetails(String groupname) {
		return iStockDAO.getsubgroupDetails(groupname);
	}
	
	@Override
	public String addStock(Stockmaster stockMaster) {
		return iStockDAO.addStock(stockMaster);
	}
	
	@Override
	public List<StockListDisplayDTO> getStockList() {
		return iStockDAO.getStockList();
	}
	
	@Override
	public List<StockListDisplayDTO> getSearchStockList(String name) {
		return iStockDAO.getSearchStockList(name);
	}
	
	@Override
	public String addstockrefill(Stockreceipt stockrel, Integer stockId, Integer supplierid, Integer manufacturerid) {
		return iStockDAO.addstockrefill(stockrel, stockId, supplierid, manufacturerid);
	}
	
	@Override
	public Integer getstockavailability(Integer stockid) {
		return iStockDAO.getstockavailability(stockid);
	}
	
	@Override
	public List<Suppliermaster> getsupplierlist() {
		return iStockDAO.getsupplierlist();
	}
	
	@Override
	public String stockAdjustment(Integer stockId, Integer qua, String remarks, Date date1) {
		return iStockDAO.stockAdjustment(stockId,qua,remarks,date1);
	}

	@Override
	public Integer getStockGroupMasterId(String stockGroupName) {
		return iStockDAO.getStockGroupMasterId(stockGroupName);
	}

	@Override
	public Integer getStockSubGroupId(String stockSubGroupName) {
		return iStockDAO.getStockSubGroupId(stockSubGroupName);
	}
	
	@Override
	public StockListDisplayDTO fetchSelectedStock(Integer stockId) {
		
		return iStockDAO.fetchSelectedStock(stockId);
	}
	
	@Override
	public int updateStockDetails(String stExpireDate, StockListDisplayDTO stockDetails) {
		return iStockDAO.updateStockDetails(stExpireDate,stockDetails);
	}
	
	@Override
	public List<Manufacturermaster> getmanufacturerlist() {
		return iStockDAO.getmanufacturerlist();
	}

	@Override
	public List<Stockmaster> getStockDetails() {
		return iStockDAO.getStockDetails();
	}
	
	@Override
	public String addSuppliers(Suppliermaster supplier) {
		return iStockDAO.addSuppliers(supplier);
		
	}

	@Override
	public List<Suppliermaster> getSupplierList() {
		return iStockDAO.getSupplierList();
	}

	@Override
	public String saveManufacturerDetails(Manufacturermaster manufacturer) {
		return iStockDAO.saveManufacturer(manufacturer);
		
	}

	@Override
	public List<Manufacturermaster> getManufacturerList() {
		return iStockDAO.getManufacturerList();
	}

	@Override
	public void updateSuppliers(Suppliermaster suppliers) {
		iStockDAO.updateSuppliers(suppliers);
		
	}

	@Override
	public Suppliermaster fetchSupplierWithId(Integer id) {
		return iStockDAO.fetchSupplierWithId(id);
	}

	@Override
	public Manufacturermaster fetchManufacturerWithId(Integer id) {
		return iStockDAO.fetchManufacturerWithId(id);
	}

	@Override
	public void updateManufacturer(Manufacturermaster manufacturer) {
		iStockDAO.updateManufacturer(manufacturer);
		
	}

	@Override
	public void updatestockrate(Integer stockId) {
		iStockDAO.updatestockrate(stockId);
	}

	@Override
	public Map<Integer, String> getSupplier() {
		
		return iStockDAO.getSupplier();
	}

	@Override
	public Map<Integer, String> getManufacturer() {
		
		return iStockDAO.getManufacturer();
	}

	@Override
	public Map<Integer, String> getStockType() {
		
		return iStockDAO.getStockType();
	}
}
