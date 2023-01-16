package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomecareServicePartnerModel {

	
	private String homeCarePartnerId;
	private String homeCarePartnerName;
	private String homeCarePartnerMobile;
	private String homeCarePartnerLoc;
	
	
	
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
