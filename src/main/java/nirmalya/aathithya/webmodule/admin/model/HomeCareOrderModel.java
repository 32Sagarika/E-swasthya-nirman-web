package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeCareOrderModel {
	private String orderId;
	private String name;
	private String mobile;
	private String location;
	private String city;
	private String requestedService;
	private String timeSlot;
	private String status;
	private String remark;
	private String request;
	private String assign;
	private String accept;
	private String reject;
	private String documentation;
	private String serviceClosed;
	private String serviceCompleted;
	private String homeCareServiceId;
	private String homeCareServiceType;

	private String homeCarePartnerId;
	private String homeCarePartnerName;
	private String homeCarePartnerMobile;
	
	private String docstatus;
	private String description;
	private String servicePrice;
	
	private String serviceDays;
	private String startDate;
	private String endDate;
	private String serviceDocumentUpload;
	private String entryTime;

	private String rejectReason;
	
	
	
	public String getEntryTime() {
		return entryTime;
	}


	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}


	public String getRejectReason() {
		return rejectReason;
	}


	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}


	public String getHomeCareServiceType() {
		return homeCareServiceType;
	}


	public void setHomeCareServiceType(String homeCareServiceType) {
		this.homeCareServiceType = homeCareServiceType;
	}


	public String getDocstatus() {
		return docstatus;
	}


	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getServicePrice() {
		return servicePrice;
	}


	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}


	public String getServiceDays() {
		return serviceDays;
	}


	public void setServiceDays(String serviceDays) {
		this.serviceDays = serviceDays;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public List<HomecareServicePartnerModel> getPartnerList() {
		return partnerList;
	}


	public void setPartnerList(List<HomecareServicePartnerModel> partnerList) {
		this.partnerList = partnerList;
	}

	private String homeCarePartnerLoc;
	
	private List<HomecareServicePartnerModel> partnerList;
	
	
	public String getHomeCarePartnerId() {
		return homeCarePartnerId;
	}


	public void setHomeCarePartnerId(String homeCarePartnerId) {
		this.homeCarePartnerId = homeCarePartnerId;
	}


	public String getHomeCarePartnerName() {
		return homeCarePartnerName;
	}


	public void setHomeCarePartnerName(String homeCarePartnerName) {
		this.homeCarePartnerName = homeCarePartnerName;
	}


	public String getHomeCarePartnerMobile() {
		return homeCarePartnerMobile;
	}


	public void setHomeCarePartnerMobile(String homeCarePartnerMobile) {
		this.homeCarePartnerMobile = homeCarePartnerMobile;
	}


	public String getHomeCarePartnerLoc() {
		return homeCarePartnerLoc;
	}


	public void setHomeCarePartnerLoc(String homeCarePartnerLoc) {
		this.homeCarePartnerLoc = homeCarePartnerLoc;
	}


	
	
	public String getServiceDocumentUpload() {
		return serviceDocumentUpload;
	}


	public void setServiceDocumentUpload(String serviceDocumentUpload) {
		this.serviceDocumentUpload = serviceDocumentUpload;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getHomeCareServiceId() {
		return homeCareServiceId;
	}


	public void setHomeCareServiceId(String homeCareServiceId) {
		this.homeCareServiceId = homeCareServiceId;
	}


	public String getRequest() {
		return request;
	}


	public String getServiceCompleted() {
		return serviceCompleted;
	}


	public void setServiceCompleted(String serviceCompleted) {
		this.serviceCompleted = serviceCompleted;
	}


	public void setRequest(String request) {
		this.request = request;
	}


	public String getAssign() {
		return assign;
	}


	public void setAssign(String assign) {
		this.assign = assign;
	}


	public String getAccept() {
		return accept;
	}


	public void setAccept(String accept) {
		this.accept = accept;
	}


	public String getReject() {
		return reject;
	}


	public void setReject(String reject) {
		this.reject = reject;
	}


	public String getDocumentation() {
		return documentation;
	}


	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}


	public String getServiceClosed() {
		return serviceClosed;
	}


	public void setServiceClosed(String serviceClosed) {
		this.serviceClosed = serviceClosed;
	}


	public HomeCareOrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRequestedService() {
		return requestedService;
	}
	public void setRequestedService(String requestedService) {
		this.requestedService = requestedService;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}


}
