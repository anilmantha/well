/*package com.leonia.wellness.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.AddStockValues;
import com.leonia.wellness.entity.StockGroupMaster;
import com.leonia.wellness.entity.StockSubGroup;
import com.leonia.wellness.idao.IAddNewStockDao;
import com.leonia.wellness.iservice.IAddNewStockService;

@Service
public class AddNewStockServiceimpl implements IAddNewStockService{

	@Autowired
	private IAddNewStockDao iaddnewstockdao;

	@Override
	public List<StockGroupMaster> getsubgroupDetails(String groupname) {
		return iaddnewstockdao.getsubgroupDetails(groupname);
	}

	@Override
	public void addStock(AddStockValues asv) {
		iaddnewstockdao.addStock(asv);
	}

}
*/