/*package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.entity.Reasonschildmaster;
import com.leonia.wellness.entity.Reasonsmaster;
import com.leonia.wellness.idao.IReasonsMasterDAO;
import com.leonia.wellness.idao.IStockDAO;
import com.leonia.wellness.iservice.IReasonsMasterService;

@Service
public class ReasonsMasterServiceImpl implements IReasonsMasterService {
	@Autowired
	private IReasonsMasterDAO iReasonsMasterDAO;

	@Override
	public List<Reasonsmaster> getAppointmentCancelReasons() {
		return iReasonsMasterDAO.getAppointmentCancelReasons();
	}

	@Override
	public Set<Reasonschildmaster> getReasonsList(Integer reasonchildmasterid) {
		return iReasonsMasterDAO.getReasonsList(reasonchildmasterid);
	}

	@Override
	public void addReason(Reasonschildmaster reasons) {
		iReasonsMasterDAO.addReason(reasons);

	}

	@Override
	public void saveReason(String reasonName, Integer reasonchildmasterId) {
		iReasonsMasterDAO.saveReason(reasonName, reasonchildmasterId);

	}

	@Override
	public void reasonUpdate(Integer reasonchildmasterId, Integer reasonid, String reason) {
		iReasonsMasterDAO.reasonUpdate(reasonchildmasterId, reasonid, reason);

	}

	@Override
	public void reasonDelete(Integer reasonchildmasterId, Integer reasonid, String reason) {
		iReasonsMasterDAO.reasonDelete(reasonchildmasterId, reasonid, reason);
		
	}

}
*/