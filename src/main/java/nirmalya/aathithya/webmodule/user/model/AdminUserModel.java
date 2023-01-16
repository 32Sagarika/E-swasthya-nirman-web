package nirmalya.aathithya.webmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminUserModel {
	private String orgName;
	private String orgRegdNo;
	private String orgAddress; 
	private String orgTypeName;
	private String orgCountryName;
	private String orgStateName;
	private String orgDistName;
	private String orgCityName;
	private String hospitalId;
	private String orgAttachment;
	private String status;
	public AdminUserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgRegdNo() {
		return orgRegdNo;
	}
	public void setOrgRegdNo(String orgRegdNo) {
		this.orgRegdNo = orgRegdNo;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgTypeName() {
		return orgTypeName;
	}
	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	public String getOrgCountryName() {
		return orgCountryName;
	}
	public void setOrgCountryName(String orgCountryName) {
		this.orgCountryName = orgCountryName;
	}
	public String getOrgStateName() {
		return orgStateName;
	}
	public void setOrgStateName(String orgStateName) {
		this.orgStateName = orgStateName;
	}
	public String getOrgDistName() {
		return orgDistName;
	}
	public void setOrgDistName(String orgDistName) {
		this.orgDistName = orgDistName;
	}
	public String getOrgCityName() {
		return orgCityName;
	}
	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}
	public String getOrgAttachment() {
		return orgAttachment;
	}
	public void setOrgAttachment(String orgAttachment) {
		this.orgAttachment = orgAttachment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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
