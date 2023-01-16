package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorAddressModel {

	
	
	private String addslNo;
	private String docCity;
	private String docAddress;
	private String docLongitude;
	private String docLatitude;
	
	public DoctorAddressModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getAddslNo() {
		return addslNo;
	}


	public void setAddslNo(String addslNo) {
		this.addslNo = addslNo;
	}


	public String getDocCity() {
		return docCity;
	}


	public void setDocCity(String docCity) {
		this.docCity = docCity;
	}


	public String getDocAddress() {
		return docAddress;
	}


	public void setDocAddress(String docAddress) {
		this.docAddress = docAddress;
	}


	public String getDocLongitude() {
		return docLongitude;
	}


	public void setDocLongitude(String docLongitude) {
		this.docLongitude = docLongitude;
	}


	public String getDocLatitude() {
		return docLatitude;
	}


	public void setDocLatitude(String docLatitude) {
		this.docLatitude = docLatitude;
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
