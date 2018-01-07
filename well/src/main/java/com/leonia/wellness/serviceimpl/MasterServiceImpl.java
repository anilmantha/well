package com.leonia.wellness.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonia.wellness.dto.Pagination;
import com.leonia.wellness.dto.StaffSkillsData;
import com.leonia.wellness.dto.TaxDetails;
import com.leonia.wellness.entity.Billinginstructionmaster;
import com.leonia.wellness.entity.Businesssourcemaster;
import com.leonia.wellness.entity.Citymaster;
import com.leonia.wellness.entity.Corporatemaster;
import com.leonia.wellness.entity.Corporatetypemaster;
import com.leonia.wellness.entity.Countrymaster;
import com.leonia.wellness.entity.Creditlistmaster;
import com.leonia.wellness.entity.Departmentmaster;
import com.leonia.wellness.entity.Dropdowndetails;
import com.leonia.wellness.entity.Giftvouchermaster;
import com.leonia.wellness.entity.Marketingvouchermaster;
import com.leonia.wellness.entity.Membershipmaster;
import com.leonia.wellness.entity.Packagemaster;
import com.leonia.wellness.entity.Packageratemaster;
import com.leonia.wellness.entity.Packageservices;
import com.leonia.wellness.entity.Productratemaster;
import com.leonia.wellness.entity.Qualificationmaster;
import com.leonia.wellness.entity.Reasondetails;
import com.leonia.wellness.entity.Reasonmaster;
import com.leonia.wellness.entity.Roommaster;
import com.leonia.wellness.entity.Segmentmaster;
import com.leonia.wellness.entity.Servicegroupmaster;
import com.leonia.wellness.entity.Servicemaster;
import com.leonia.wellness.entity.Serviceratemaster;
import com.leonia.wellness.entity.Serviceroommaster;
import com.leonia.wellness.entity.Servicestaffmaster;
import com.leonia.wellness.entity.Skillmaster;
import com.leonia.wellness.entity.Staffmaster;
import com.leonia.wellness.entity.Staffskill;
import com.leonia.wellness.entity.Statemaster;
import com.leonia.wellness.entity.Stockgroupmaster;
import com.leonia.wellness.entity.Stockmaster;
import com.leonia.wellness.entity.Stocksubgroupmaster;
import com.leonia.wellness.entity.Taxmaster;
import com.leonia.wellness.entity.Taxstructuremaster;
import com.leonia.wellness.idao.IMasterDAO;
import com.leonia.wellness.iservice.IMasterService;
@Service
public class MasterServiceImpl implements IMasterService {
	@Autowired
	private IMasterDAO iMasterDAO;

	@Override
	public List<Reasonmaster> getAppointmentCancelReasons() {
		return iMasterDAO.getAppointmentCancelReasons();
	}

	@Override
	public Set<Reasondetails> getReasonsList(Integer reasonchildmasterid) {
		return iMasterDAO.getReasonsList(reasonchildmasterid);
	}

	@Override
	public void addReason(Reasondetails reasons) {
		iMasterDAO.addReason(reasons);

	}

	@Override
	public String saveReason(String reasonName, Integer reasonchildmasterId) {
		return iMasterDAO.saveReason(reasonName, reasonchildmasterId);

	}

	@Override
	public String reasonUpdate(Integer reasonchildmasterId, Integer reasonid, String reason) {
		return iMasterDAO.reasonUpdate(reasonchildmasterId, reasonid, reason);

	}

	@Override
	public String reasonDelete(Integer reasonchildmasterId, Integer reasonid, String reason) {
		return iMasterDAO.reasonDelete(reasonchildmasterId, reasonid, reason);
		
	}

	@Override
	public List<Servicemaster> getServices() {
		return iMasterDAO.getServices();
	}

	@Override
	public String saveServiceRate(Integer serviceId,Integer taxStructureId, Serviceratemaster servicerate) {
	return iMasterDAO.saveServiceRate(serviceId, taxStructureId,servicerate);
	
		
	}

	@Override
	public List<Serviceratemaster> serviceRateList() {
		return iMasterDAO.serviceRateList();
	}

	@Override
	public List<Taxstructuremaster> getTaxStructure() {
		return iMasterDAO.getTaxStructure();
	

}
@Override
	public String saveTax(Taxmaster tax,String taxName) {
		return iMasterDAO.saveTax(tax,taxName);
		
	}

	@Override
	public List<Taxmaster> taxList() {
		return iMasterDAO.taxList();
	}

	@Override
	public List<Taxstructuremaster> taxStructureList() {
		return iMasterDAO.taxStructureList();
	}

	@Override
	public List<Taxmaster> getTaxMasterList() {
		return iMasterDAO.getTaxMasterList();
	}

	@Override
	public String saveTaxStructure(TaxDetails taxDetails) {
		return iMasterDAO.saveTaxStructure(taxDetails);
	}

	@Override
	public List<TaxDetails> getTaxStructureList() {
		return iMasterDAO.getTaxStructureList();
	}

	@Override
	public List<Stockmaster> getProducts() {
		return iMasterDAO.getProducts();
	}

	@Override
	public String saveProductRate(Integer productId, Integer taxStructureId, Productratemaster productrate) {
		return iMasterDAO.saveProductRate(productId,taxStructureId,productrate);
		
	}

	@Override
	public List<Productratemaster> productRateList() {
		return iMasterDAO.productRateList();
	}
	@Override
	public List<Servicegroupmaster> getServiceGroups(String serviceName, int records) {
		return iMasterDAO.getServiceGroups(serviceName,records);
	}

	@Override
	public List<Dropdowndetails> getGenders() {
		return iMasterDAO.getGenders();
	}

	@Override
	public String saveService(Servicemaster serviceMaster) {
		return iMasterDAO.saveService(serviceMaster);
	}

	@Override
	public String editService(Servicemaster serviceMaster) {
		return iMasterDAO.editService(serviceMaster);
	}

	@Override
	public String deleteService(Servicemaster serviceMaster) {
		return iMasterDAO.deleteService(serviceMaster);
	}

	@Override
	public List<Servicemaster> getServiceByName(String serviceName) {
		return iMasterDAO.getServiceByName(serviceName);
	}

	@Override
	public List<Servicegroupmaster> getServiceGroupList() {
		return iMasterDAO.getServiceGroupList();
	}

	@Override
	public String saveServiceGroup(Servicegroupmaster serviceGroup) {
		return iMasterDAO.saveServiceGroup(serviceGroup);
	}

	@Override
	public String editServiceGroup(Servicegroupmaster serviceGroup) {
		return iMasterDAO.editServiceGroup(serviceGroup);
	}

	@Override
	public String deleteServiceGroup(Servicegroupmaster serviceGroup) {
		return iMasterDAO.deleteServiceGroup(serviceGroup);
	}

	@Override
	public List<Servicegroupmaster> getServiceGroupByName(String serviceGroupName) {
		return iMasterDAO.getServiceGroupByName(serviceGroupName);
	}
	@Override
	public String savePackage(String packagename, String[] serviceids) {
		
		return iMasterDAO.savePackage(packagename,serviceids);
	}

	@Override
	public List<Packageservices> getPackageServices() {
		return iMasterDAO.getPackageServices();
	}

	@Override
	public String editPackage(String packagename, String[] serviceids, Integer packageid) {
		return iMasterDAO.editPackage(packagename,serviceids,packageid);
	}

	@Override
	public List<Packageservices> getPackageServicebyid(Integer packageid) {
		return iMasterDAO.getPackageServicebyid(packageid);
	}

	@Override
	public String saveStockGroup(Stockgroupmaster stockGroupMaster) {
		return iMasterDAO.saveStockGroup(stockGroupMaster);
	}

	@Override
	public List<Stockgroupmaster> getStockGroupList() {
		return iMasterDAO.getStockGroupList();
	}

	@Override
	public String editStockGroup(Stockgroupmaster stockGroup) {
		return iMasterDAO.editStockGroup(stockGroup);
	}

	@Override
	public String deleteStockGroup(Stockgroupmaster stockGroup) {
		return iMasterDAO.deleteStockGroup(stockGroup);
	}

	@Override
	public List<Stockgroupmaster> getStockGroupByName(String stockGroupName) {
		return iMasterDAO.getStockGroupByName(stockGroupName);
	}

	@Override
	public List<Stockgroupmaster> getStockGroups() {
		return iMasterDAO.getStockGroups();
	}

	@Override
	public String saveStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		return iMasterDAO.saveStockSubGroup(stockSubGroup);
	}

	@Override
	public List<Stocksubgroupmaster> getStockSubGroupList() {
		return iMasterDAO.getStockSubGroupList();
	}

	@Override
	public String editStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		return iMasterDAO.editStockSubGroup(stockSubGroup);
	}

	@Override
	public String deleteStockSubGroup(Stocksubgroupmaster stockSubGroup) {
		return iMasterDAO.deleteStockSubGroup(stockSubGroup);
	}

	@Override
	public List<Stocksubgroupmaster> getStockSubGroupByName(String stockSubGroupName) {
		return iMasterDAO.getStockSubGroupByName(stockSubGroupName);
	}

	@Override
	public List<Billinginstructionmaster> getBillingInstructionList() {
		return iMasterDAO.getBillingInstructionList();
	}

	@Override
	public String saveBillingInstruction(Billinginstructionmaster billingInstruction) {
		return iMasterDAO.saveBillingInstruction(billingInstruction);
	}

	@Override
	public String editBillingInstruction(Billinginstructionmaster billingInstruction) {
		return iMasterDAO.editBillingInstruction(billingInstruction);
	}

	@Override
	public String deleteBillingInstruction(Billinginstructionmaster billingInstruction) {
		return iMasterDAO.deleteBillingInstruction(billingInstruction);
	}

	@Override
	public List<Billinginstructionmaster> searchBillingInstruction(String description) {
		return iMasterDAO.searchBillingInstruction(description);
	}

	@Override
	public List<Businesssourcemaster> getBusinessSourceList() {
		return iMasterDAO.getBusinessSourceList();
	}

	@Override
	public String saveBusinessSource(Businesssourcemaster businessSource) {
		return iMasterDAO.saveBusinessSource(businessSource);
	}

	@Override
	public String editBusinessSource(Businesssourcemaster businessSource) {
		return iMasterDAO.editBusinessSource(businessSource);
	}

	@Override
	public String deleteBusinessSource(Businesssourcemaster businessSource) {
		return iMasterDAO.deleteBusinessSource(businessSource);
	}

	@Override
	public List<Businesssourcemaster> getsearchBusinessSource(String description) {
		return iMasterDAO.getsearchBusinessSource(description);
	}

	@Override
	public List<Giftvouchermaster> getGiftVoucherList() {
		return iMasterDAO.getGiftVoucherList();
	}

	@Override
	public String saveGiftVoucher(Giftvouchermaster giftVoucher) {
		return iMasterDAO.saveGiftVoucher(giftVoucher);
	}

	@Override
	public List<Corporatemaster> getCorporateList() {
		return iMasterDAO.getCorporateList();
	}

	@Override
	public List<Creditlistmaster> getCreditListDetails() {
		return iMasterDAO.getCreditListDetails();
	}

	@Override
	public String saveCreditList(Creditlistmaster creditlistmaster) {
		return iMasterDAO.saveCreditList(creditlistmaster);
	}

	@Override
	public String deleteCreditList(int parseInt) {
		return iMasterDAO.deleteCreditList(parseInt);
	}

	@Override
	public List<Citymaster> getCities() {
	return iMasterDAO.getCities();
	}

	@Override
	public List<Statemaster> getStates() {
		return iMasterDAO.getStates();
	}

	@Override
	public List<Countrymaster> getCountries() {
		return iMasterDAO.getCountries();
	}

	@Override
	public List<Departmentmaster> getDepartments() {
		return iMasterDAO.getDepartments();
	}

	@Override
	public List<String> getStateCountryByCity(Integer cityId) {
		return iMasterDAO.getStateCountryByCity(cityId);
	}

	@Override
	public List<Packagemaster> getPackages() {
		return iMasterDAO.getPackages();
	}



	@Override
	public String saveServiceRoom(Serviceroommaster serviceroom) {
		return iMasterDAO.saveServiceRoom(serviceroom);
	}

	@Override
	public List<Serviceroommaster> getServiceRoomList() {
		return iMasterDAO.getServiceRoomList();
	}

	@Override
	public String editServiceRoom(Serviceroommaster serviceroom) {
		return iMasterDAO.editServiceRoom(serviceroom);
	}

	@Override
	public String deleteServiceRoom(Serviceroommaster serviceroom) {
		return iMasterDAO.deleteServiceRoom(serviceroom);
				}

	@Override
	public List<Serviceroommaster> getsearchServiceRoom(String serviceName) {
		return iMasterDAO.getsearchServiceRoom(serviceName);
	}

	@Override
	public String savePackageRate(Integer packageId, Integer taxStructureId, Packageratemaster packagerate) {
		return iMasterDAO.savePackageRate(packageId,taxStructureId,packagerate);
	}

	@Override
	public List<Packageratemaster> packageRateList() {
		return iMasterDAO.packageRateList();
	}

	@Override
	public List<Corporatetypemaster> getCorporateTypeList(String corporateName, int i) {
		return iMasterDAO.getCorporateTypeList(corporateName,i);
	}

	@Override
	public String saveCorporateType(Corporatetypemaster corporateType) {
		return iMasterDAO.saveCorporateType(corporateType);
	}

	@Override
	public String editCorporateType(Corporatetypemaster corporateType) {
		return iMasterDAO.editCorporateType(corporateType);
	}

	@Override
	public String deleteCorporateType(Corporatetypemaster corporateType) {
		return iMasterDAO.deleteCorporateType(corporateType);
	}

	@Override
	public String deletePackageRate(Integer packageId, Integer taxStructureId, Packageratemaster packagerate) {
		return iMasterDAO.deletePackageRate(packageId,taxStructureId,packagerate);
	}

	@Override
	public Packagemaster getPackageById(Integer packageid) {
		return iMasterDAO.getPackageById(packageid);
	}

	@Override
	public String deletePackage(Integer packageid) {
		return iMasterDAO.deletePackage(packageid);
	}

	@Override
	public String saveStaff(Staffmaster staff) {
		return iMasterDAO.saveStaff(staff);
	}

	@Override
	public List<String> getServicesByPackageId(Integer packageId) {
		return iMasterDAO.getServicesByPackageId(packageId);
	}

	/*@Override
	public List<Corporatetypemaster> getsearchCorporateType(String corporateName, int records) {
		return iMasterDAO.getsearchCorporateType(corporateName,records);
	}*/

	@Override
	public Pagination corporateCount(String corporateName) {
		return iMasterDAO.corporateCount(corporateName);
	}

	@Override
	public List<Serviceratemaster> searchserviceRateList(String serviceName) {
		return iMasterDAO.searchserviceRateList(serviceName);
	}

	@Override
	public List<Taxmaster> searchTaxByName(String taxName) {
		return iMasterDAO.searchTaxByName(taxName);
	}

	@Override
	public String editTax(Taxmaster tax) {
		// TODO Auto-generated method stub
		return iMasterDAO.editTax(tax);
	}

	@Override
	public String deleteTax(Taxmaster tax) {
		return iMasterDAO.deleteTax(tax);
	}

	@Override
	public List<Staffmaster> getStaffList() {
		return iMasterDAO.getStaffList();
	}

	@Override
	public Staffmaster fetchStaffWithId(Integer id) {
		return iMasterDAO.fetchStaffWithId(id);
	}

	@Override
	public String editStaff(Staffmaster staffMaster) {
		return iMasterDAO.editStaff(staffMaster);
	}

	@Override
	public Pagination serviceCount(String serviceName) {
		return iMasterDAO.serviceCount(serviceName);
	}
public void saveDepartment(Departmentmaster departmentmaster) {
		
		iMasterDAO.saveDepartment(departmentmaster);
		
		
	}


	public List<Departmentmaster> getDepartment(String departmentname) {
		
		return iMasterDAO.getDepartment( departmentname);
	}

	
	

	@Override
	public Departmentmaster editDepartment(Integer depId) {

		return iMasterDAO.editDepartment(depId);
	}


	
	@Override
	public void updateDepartment(Departmentmaster departmentmaster) {
		
		iMasterDAO.updateDepartment(departmentmaster);
	}



	@Override
	public List<Departmentmaster> listDepartments(Departmentmaster departmentmaster, int i, String pageName,String serach) {
	
		return iMasterDAO.listDepartments(departmentmaster,i,pageName,serach);
	}

	@Override
	public Pagination deparmentCount(Departmentmaster departmentmaster, String pageName,String serach) {
		
		return iMasterDAO.deparmentCount(departmentmaster,pageName,serach);
	}

	@Override
	public String saveSkills(Skillmaster skillmaster) {
		return iMasterDAO.saveSkills(skillmaster);
	}

	@Override
	public String editSkills(Skillmaster skillmaster) {
		
		return iMasterDAO.editSkills(skillmaster);
	}

	@Override
	public List<Skillmaster> getSkillslist() {
		
		return iMasterDAO.getSkillslist();
	}

	@Override
	public String deleteSkills(Skillmaster skillmaster) {
		
		return iMasterDAO.deleteSkills(skillmaster);
	}
	@Override
	public List<Skillmaster> getByName(String skillname,int records) {
		return iMasterDAO.getByName(skillname,records);
	}
	
	@Override
	public Pagination skillCount(String skillname) {
		
		return iMasterDAO.skillCount(skillname);
	}


	@Override
	public String saveQualification(Qualificationmaster qualificationmaster) {
		return iMasterDAO.saveQualification(qualificationmaster);
}
	public List<Qualificationmaster> getQualifications(){
		
	return iMasterDAO.getQualifications();
}

	@Override
	public String editQualifications(Qualificationmaster qualificationmaster) {
		return iMasterDAO.editQualifications(qualificationmaster);	
	}

	@Override
	public String deleteQualification(Qualificationmaster qualificationmaster) {
		return iMasterDAO.deleteQualification(qualificationmaster);
	}

	@Override
	public List<Qualificationmaster> getQualificationByName(String qualification,int records) {
		return iMasterDAO.getQualificationByName(qualification, records);
	}

	@Override
	public Pagination qualifyCount(String qualification) {
		return iMasterDAO.qualifyCount(qualification);
	}

	@Override
	public List<Marketingvouchermaster> getMarketingVoucher() {
		
		return iMasterDAO.getMarketingVoucher();
		
	}
	
	@Override
	public List<Marketingvouchermaster> getMvoucherByName(String mcompany,int records){
		
		return iMasterDAO.getMvoucherByName(mcompany, records);
	}
	
	@Override
	public Pagination marketCount(String mcompany) {
		
		return iMasterDAO.marketCount(mcompany);
		
	}
	
	
	@Override
	public String saveMarketingvoucher(Marketingvouchermaster marketvoucher) {
	
		return iMasterDAO.saveMarketingvoucher(marketvoucher);
		
	}

	@Override
	public String saveRoom(Roommaster room) {
		return iMasterDAO.saveRoom(room);
	}

	@Override
	public List<Roommaster> getRooms() {
		return iMasterDAO.getRooms();
	}

	@Override
	public String editRoom(Roommaster room) {
		return iMasterDAO.editRoom(room);	
		
	}

	@Override
	public List<Roommaster> getRoomsByNum(Integer roomnum,int records) {
		return iMasterDAO.getRoomsByNum(roomnum, records);
	}

	@Override
	public Pagination roomCount(Integer roomnum) {
		
		return iMasterDAO.roomCount(roomnum);
	}
	
	@Override
	public String deleteRoom(Roommaster room) {
		return iMasterDAO.deleteRoom(room);
	}

	

	@Override
	public String saveSegment(Segmentmaster segment) {
		return iMasterDAO.saveSegment(segment);
	}

	@Override
	public List<Segmentmaster> getSegment() {
		return iMasterDAO.getSegment();
	}

	@Override
	public String editSegment(Segmentmaster segment) {
	return iMasterDAO.editSegment(segment);	
	}

	@Override
	public String deleteSegment(Segmentmaster segment) {
	return iMasterDAO.deleteSegment(segment);
	}

	@Override
	public List<Segmentmaster> getSegmentByName(String segname,int records) {
		return iMasterDAO.getSegmentByName(segname,records);
	}

	@Override
	public Pagination segmentCount(String segname) {
		return iMasterDAO.segmentCount(segname);
	}

	
	@Override
	public String saveServiceStaff(Servicestaffmaster servicestaff) {
		return iMasterDAO.saveServiceStaff(servicestaff);
	}

	@Override
	public List<Servicestaffmaster> getServiceStaffList() {
		return iMasterDAO.getServiceStaffList();
	}

	@Override
	public List<Staffmaster> getStaff() {
		return iMasterDAO.getStaff();
	}

	@Override
	public String editServiceStaff(Servicestaffmaster servicestaff) {
		
		return iMasterDAO.editServiceStaff(servicestaff);
	}

	@Override
	public String deleteServiceStaff(Servicestaffmaster servicestaff) {
		return iMasterDAO.deleteServiceStaff(servicestaff);
	}

	@Override
	public List<Servicestaffmaster> getsearchServiceStaff(String serviceName,int records) {
		return iMasterDAO.getsearchServiceStaff(serviceName,records);
	}

	@Override
	public Pagination serviceStaffCount(String serviceName) {
		return iMasterDAO.serviceStaffCount(serviceName);
	}


	@Override
	public Integer  getskillid(String skillname) {
		
		return iMasterDAO.getskillid(skillname);
		}


	@Override
	public void saveStaffSkills(List<StaffSkillsData> sfs) {
		
		iMasterDAO.saveStaffSkills(sfs);
	}


	@Override
	public Set<Staffskill> getSkill1(Integer staffid) {

		return iMasterDAO.getSkill1(staffid);
	}

	@Override
	public List<Skillmaster> getSkills() {
		return iMasterDAO.getSkills();
	}
	@Override
	public List<Membershipmaster> getMembership() {
		return iMasterDAO.getmembershipList();
	}

	@Override
	public String membershipSave(Membershipmaster membershipmaster,String id) {
	
	return  iMasterDAO.saveMemberShip(membershipmaster,id);
		
		
	}

	@Override
	public List editvalue(Membershipmaster membershipmaster, String memberid) {
		return iMasterDAO.editvalue(membershipmaster,memberid);
	}

	@Override
	public List<Membershipmaster> getMemberShipName(String membername) {
	
		return iMasterDAO.searchByMemberName(membername);
	}

	@Override
	public List<Corporatemaster> getCorporateList1() {
		
		return iMasterDAO.getCorporateList();
	}

	

	@Override
	public List<Corporatemaster> getSerchCorporateName(String corporatename) {
	
		return iMasterDAO.getSearchByName(corporatename);
	}

	@Override
	public String saveCorporateMaster(Corporatemaster corporatemaster, String corporateid) {
	
		return iMasterDAO.saveCorporateMaster(corporatemaster,corporateid);
	}

	@Override
	public List<Corporatetypemaster> getListCorporateType() {
		
		return iMasterDAO.getCorporateTypeList();
	}

}
