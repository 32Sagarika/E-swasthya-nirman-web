package nirmalya.aathithya.webmodule.admin.model;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminSymptomModel {
	private String symptomId;
	private String symptomName;
	private String speciality;
	private String specialityId;
	private String symptomImg;
	private String symptomDesc;
	
	
	

	public String getSpecialityId() {
		return specialityId;
	}




	public void setSpecialityId(String specialityId) {
		this.specialityId = specialityId;
	}




	public String getSymptomId() {
		return symptomId;
	}




	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}




	public String getSymptomName() {
		return symptomName;
	}




	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}




	public String getSpeciality() {
		return speciality;
	}




	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}




	public String getSymptomImg() {
		return symptomImg;
	}




	public void setSymptomImg(String symptomImg) {
		this.symptomImg = symptomImg;
	}




	public String getSymptomDesc() {
		return symptomDesc;
	}




	public void setSymptomDesc(String symptomDesc) {
		this.symptomDesc = symptomDesc;
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
	
	