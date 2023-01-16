package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminLocationModel {

	private String locationId;
	private String locationName;
	private String cityName;
	private String cityId;
	private String pinCode;
	private String locationFor;
	
	
	
	public String getLocationFor() {
		return locationFor;
	}


	public void setLocationFor(String locationFor) {
		this.locationFor = locationFor;
	}


	public String getCityId() {
		return cityId;
	}


	public void setCityId(String cityId) {
		this.cityId = cityId;
	}


	public String getLocationId() {
		return locationId;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
