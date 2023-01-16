package nirmalya.aathithya.webmodule.common.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Nirmalya Labs
 *
 */
public class EnvironmentVaribles {

	@Value("${service.url.ticket}")
	private String ticketUrl;

	@Value("${service.url.recruitment}")
	private String recruitment;

	@Value("${service.url.planning}")
	private String planningUrl;

	@Value("${service.url.user}")
	private String userUrl;

	@Value("${service.url.restaurant}")
	private String restaurantUrl;

	@Value("${service.url.property}")
	private String propertyUrl;

	@Value("${service.url.maintenance}")
	private String maintenanceUrl;

	@Value("${service.url.gatepass}")
	private String gatepassUrl;

	@Value("${service.url.inventory}")
	private String inventoryUrl;

	@Value("${service.url.housekeeping}")
	private String housekeepingUrl;

	@Value("${service.url.frontdesk}")
	private String frontdeskUrl;

	@Value("${service.url.master}")
	private String masterUrl;

	@Value("${service.url.laundry}")
	private String laundryUrl;

	@Value("${service.url.gym}")
	private String gymUrl;

	@Value("${service.url.roomservice}")
	private String roomserviceUrl;

	@Value("${service.url.otherservices}")
	private String otherservicesUrl;

	@Value("${service.url.kitchen}")
	private String kitchenUrl;

	@Value("${service.url.asset}")
	private String assetUrl;

	@Value("${service.url.member}")
	private String memberUrl;

	@Value("${service.url.spa}")
	private String spaUrl;

	@Value("${service.url.beautyparlour}")
	private String beautyparlourUrl;

	@Value("${service.url.parking}")
	private String parkingUrl;

	@Value("${service.url.saloon}")
	private String saloonUrl;

	@Value("${service.url.sales}")
	private String salesUrl;

	@Value("${service.url.account}")
	private String accountUrl;

	@Value("${service.url.reimbursement}")
	private String reimbursementUrl;

	@Value("${service.url.employee}")
	private String employeeUrl;

	@Value("${service.url.nightclub}")
	private String nightclubUrl;

	@Value("${service.url.document}")
	private String documentUrl;

	@Value("${service.url.leave}")
	private String leaveUrl;

	@Value("${service.url.attendance}")
	private String attendanceUrl;

	@Value("${service.url.fileUpload-laundry}")
	private String fileUploadLaundry;

	@Value("${service.url.fileUpload-restaurant}")
	private String fileUploadRestaurant;

	@Value("${service.url.fileUpload-hotel}")
	private String fileUploadHotel;

	@Value("${service.url.fileUpload-property}")
	private String fileUploadProperty;

	@Value("${service.url.fileUpload-sales}")
	private String fileUploadSales;

	@Value("${service.url.fileUpload-inventory}")
	private String fileUploadInventory;

	@Value("${service.url.fileUpload-beautyparlour}")
	private String fileUploadBParlour;

	@Value("${service.url.fileUpload-master}")
	private String fileUploadMaster;

	@Value("${service.url.baseURL}")
	private String baseURL;

	@Value("${service.url.hrms}")
	private String hrmsUrl;

	@Value("${service.url.fileUpload-uploadEmployee}")
	private String fileUploadEmployee;

	@Value("${service.url.fileUpload-reimbursement}")
	private String fileUploadReimbursementUrl;

	@Value("${service.url.fileUpload-document}")
	private String fileUploadDocumenttUrl;

	@Value("${service.url.fileUpload-store}")
	private String fileUploadStoreUrl;

	@Value("${service.url.qualitycontrol}")
	private String qualitycontrol;

	@Value("${service.url.production}")
	private String production;

	@Value("${service.url.challan}")
	private String challan;

	@Value("${service.url.gatePass}")
	private String gatePass;

	@Value("${service.url.fileUpload-uploadItem}")
	private String fileUploadItem;

	@Value("${service.url.barcodes}")
	private String barcodesUpload;

	@Value("${service.url.fileUpload-procurement}")
	private String fileUploadProcurement;

	@Value("${service.url.projects}")
	private String projects;

	@Value("${service.url.customer}")
	private String customerUrl;
	
	@Value("${service.url.notice}")
	private String notice;
	
	@Value("${service.url.pipeline}")
	private String pipeline;
	
	@Value("${service.url.hospitality}")
	private String hospitalityUrl;
	
	
	@Value("${service.url.audit}")
	private String auditUrl;
	
	@Value("${service.url.register}")
	private String registerUrl;
	
	@Value("${service.url.lab}")
	private String labUrl;
	
	@Value("${service.url.doctor}")
	private String doctorUrl;


	@Value("${service.url.dmdashboard}")
	private String dmdashboardUrl;
	
	
	@Value("${service.url.userqrcode}")
	private String userqrcodeUrl;
	
	@Value("${service.url.reception}")
	private String receptionUrl;
	
	@Value("${service.url.chemist}")
	private String chemistUrl;
	
	@Value("${service.url.ambulance}")
	private String ambulanceUrl;
	
	@Value("${service.url.pathologist}")
	private String pathologistUrl;
	
	@Value("${service.url.vle}")
	private String vleUrl;
	
	@Value("${service.url.vleadmin}")
	private String vleadminUrl;
	
	@Value("${service.url.bloodbank}")
	private String bloodbankUrl;
	
	@Value("${service.url.pm}")
	private String pmUrl;
	
	@Value("${service.url.api}")
	private String apiUrl;

	@Value("${service.url.dropdown}")
	private String dropdownUrl;
	
	@Value("${service.url.documentUpload}")
	private String documentUpload;
	
	@Value("${service.url.organisationUpload}")
	private String organisationUpload;
	
	@Value("${service.url.insuranceUpload}")
	private String insuranceUpload;
	
	@Value("${service.url.bpo}")
	private String bpoUrl;
	
	@Value("${service.url.syndicate}")
	private String syndicateUrl;
	
	
	@Value("${service.url.syndicateupload}")
	private String syndicateuploadUrl;
	
	@Value("${service.url.testReport}")
	private String testReportUrl;

	@Value("${service.url.ecg}")
	private String ecgUrl;
	@Value("${service.url.mergeTestReport}")
	private String mergeTestReportUrl;

	@Value("${service.url.adminuser}")
	private String adminuserUrl;
	
	@Value("${service.url.newsAndMediaUpload}")
	private String newsAndMediaUpload;
	
	@Value("${service.url.adminorganization}")
	private String adminorganizationUrl;
	
	@Value("${service.url.adminCalender}")
	private String adminCalender;
	
	@Value("${service.url.spiro}")
	private String spiroUrl; 
	
	@Value("${service.url.wrizto}")
	private String wriztoUrl;


	@Value("${service.url.ehsadmin}")
	private String ehsadminUrl;
	
	
	public String getWriztoUrl() {
		return wriztoUrl;
	}

	

	@Value("${service.url.admin}")
	private String adminUrl;
	
	
	public String getAdminUrl() {
		return adminUrl;
	}

	public String getAdminorganizationUrl() {
		return adminorganizationUrl;
	}
	
	public String getNewsAndMediaUpload() {
		return newsAndMediaUpload;
	}

	public String getInsuranceUpload() {
		return insuranceUpload;
	}
	
	public String getOrganisationUpload() {
		return organisationUpload;
	}
	public String getDocumentUpload() {
		return documentUpload;
	}
	public String getDmdashboardUrl() {
		return dmdashboardUrl;
	}

	public void setDmdashboardUrl(String dmdashboardUrl) {
		this.dmdashboardUrl = dmdashboardUrl;
	}

	public String getPipeline() {
		return pipeline;
	}

	public String getTicketUrl() {
		return ticketUrl;
	}
	
	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}

	public String getProjects() {
		return projects;
	}

	public String getBarcodesUpload() {
		return barcodesUpload;
	}

	public String getFileUploadStoreUrl() {
		return fileUploadStoreUrl;
	}

	public String getFileUploadDocumenttUrl() {
		return fileUploadDocumenttUrl;
	}

	public EnvironmentVaribles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserUrl() {
		return userUrl;
	}

	public String getRestaurantUrl() {
		return restaurantUrl;
	}

	public String getPropertyUrl() {
		return propertyUrl;
	}

	public String getMaintenanceUrl() {
		return maintenanceUrl;
	}

	public String getGatepassUrl() {
		return gatepassUrl;
	}

	public String getFileUploadRestaurant() {
		return fileUploadRestaurant;
	}

	public String getInventoryUrl() {
		return inventoryUrl;
	}

	public String getHousekeepingUrl() {
		return housekeepingUrl;
	}

	public String getFrontdeskUrl() {
		return frontdeskUrl;
	}

	public String getLaundryUrl() {
		return laundryUrl;
	}

	public String getGymUrl() {
		return gymUrl;
	}

	public String getRoomserviceUrl() {
		return roomserviceUrl;
	}

	public String getOtherservicesUrl() {
		return otherservicesUrl;
	}

	public String getKitchenUrl() {
		return kitchenUrl;
	}

	public String getAssetUrl() {
		return assetUrl;
	}

	public String getMemberUrl() {
		return memberUrl;
	}

	public String getSpaUrl() {
		return spaUrl;
	}

	public String getBeautyparlourUrl() {
		return beautyparlourUrl;
	}

	public String getParkingUrl() {
		return parkingUrl;
	}

	public String getSaloonUrl() {
		return saloonUrl;
	}

	public String getSalesUrl() {
		return salesUrl;
	}

	public String getAccountUrl() {
		return accountUrl;
	}

	public String getReimbursementUrl() {
		return reimbursementUrl;
	}

	public String getLeaveUrl() {
		return leaveUrl;
	}

	public String getAttendanceUrl() {
		return attendanceUrl;
	}

	public String getFileUploadLaundry() {
		return fileUploadLaundry;
	}

	public String getfileUploadRestaurant() {
		return fileUploadRestaurant;
	}

	public String getFileUploadHotel() {
		return fileUploadHotel;
	}

	public String getHospitalityUrl() {
		return hospitalityUrl;
	}

	public String getFileUploadProperty() {
		return fileUploadProperty;
	}

	public String getMasterUrl() {
		return masterUrl;
	}

	public String getFileUploadMaster() {
		return fileUploadMaster;
	}

	public String getFileUploadSales() {
		return fileUploadSales;
	}

	public String getFileUploadInventory() {
		return fileUploadInventory;
	}

	public String getFileUploadBParlour() {
		return fileUploadBParlour;
	}

	public String getBaseUrlPath() {
		return baseURL;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getHrmsUrl() {
		return hrmsUrl;
	}

	public String getFileUploadEmployee() {
		return fileUploadEmployee;
	}

	public String getFileUploadItem() {
		return fileUploadItem;
	}

	public String getFileUploadReimbursementUrl() {
		return fileUploadReimbursementUrl;
	}

	public String getEmployeeUrl() {
		// TODO Auto-generated method stub
		return employeeUrl;
	}

	public String getQualitycontrol() {
		return qualitycontrol;
	}

	public String getProduction() {
		return production;
	}

	public String getNightclubUrl() {
		return nightclubUrl;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public String getChallan() {
		return challan;
	}

	public String getFileUploadGatePassUrl() {
		return gatePass;
	}

	public String getPlanningUrl() {
		return planningUrl;
	}

	public String getGatePass() {
		return gatePass;
	}

	public String getRecruitment() {
		return recruitment;
	}

	public String getFileUploadProcurement() {
		return fileUploadProcurement;
	}
	
	public String getNotice() {
		return notice;
	}
	public String getAuditUrl() {
		return auditUrl;
	}
	public String getRegisterUrl() {
		return registerUrl;
	}

	public String getLabUrl() {
		return labUrl;
	}
	public String getDoctorUrl() {
		return doctorUrl;
	}

	public String getUserqrcodeUrl() {
		return userqrcodeUrl;
	}

	public String getReceptionUrl() {
		return receptionUrl;
	}

	public void setReceptionUrl(String receptionUrl) {
		this.receptionUrl = receptionUrl;
	}

	public String getChemistUrl() {
		return chemistUrl;
	}

	public void setChemistUrl(String chemistUrl) {
		this.chemistUrl = chemistUrl;
	}

	public String getAmbulanceUrl() {
		return ambulanceUrl;
	}

	public void setAmbulanceUrl(String ambulanceUrl) {
		this.ambulanceUrl = ambulanceUrl;
	}

	public String getBloodbankUrl() {
		return bloodbankUrl;
	}

	public void setBloodbankUrl(String bloodbankUrl) {
		this.bloodbankUrl = bloodbankUrl;
	}

	
	public String getPmUrl() {
		return pmUrl;
	}

	public void setPmUrl(String pmUrl) {
		this.pmUrl = pmUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}
	public String getDropdownUrl() {
		return dropdownUrl;
	}

	public String getBpoUrl() {
		return bpoUrl;
	}

	public String getSyndicateUrl() {
		return syndicateUrl;
	}

	public String getSyndicateuploadUrl() {
		return syndicateuploadUrl;
	}

	public String getTestReportUrl() {
		return testReportUrl;
	}

	public String getMergeTestReportUrl() {
		return mergeTestReportUrl;
	}

	public String getEcgUrl() {
		return ecgUrl;
	}

	public String getAdminuserUrl() {
		return adminuserUrl;
	}
	public String getAdminCalender() {
		return adminCalender;
	}

	public String getPathologistUrl() {
		return pathologistUrl;
	}

	public String getSpiroUrl() {
		return spiroUrl;
	}

	public String getVleUrl() {
		return vleUrl;
	}

	public String getVleadminUrl() {
		return vleadminUrl;
	}

	public String getEhsadminUrl() {
		return ehsadminUrl;
	}

	
	
}

