package nirmalya.aathithya.webmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PMDashboardModel {
	
	private String totalPatient;
	private String admitHoospital;
	private String homeTreatment;
	private String totalDctr;
	private String highRiskPatient;
	private String mediumRiskPatient;
	private String smallRiskPatient;
	private String death;
	
	private String male;
	private String female;

	
	public PMDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}






	public String getTotalPatient() {
		return totalPatient;
	}






	public void setTotalPatient(String totalPatient) {
		this.totalPatient = totalPatient;
	}






	public String getAdmitHoospital() {
		return admitHoospital;
	}






	public void setAdmitHoospital(String admitHoospital) {
		this.admitHoospital = admitHoospital;
	}






	public String getHomeTreatment() {
		return homeTreatment;
	}






	public void setHomeTreatment(String homeTreatment) {
		this.homeTreatment = homeTreatment;
	}






	public String getTotalDctr() {
		return totalDctr;
	}






	public void setTotalDctr(String totalDctr) {
		this.totalDctr = totalDctr;
	}






	public String getHighRiskPatient() {
		return highRiskPatient;
	}






	public void setHighRiskPatient(String highRiskPatient) {
		this.highRiskPatient = highRiskPatient;
	}






	public String getMediumRiskPatient() {
		return mediumRiskPatient;
	}






	public void setMediumRiskPatient(String mediumRiskPatient) {
		this.mediumRiskPatient = mediumRiskPatient;
	}






	public String getSmallRiskPatient() {
		return smallRiskPatient;
	}






	public void setSmallRiskPatient(String smallRiskPatient) {
		this.smallRiskPatient = smallRiskPatient;
	}






	public String getDeath() {
		return death;
	}






	public void setDeath(String death) {
		this.death = death;
	}






	public String getMale() {
		return male;
	}






	public void setMale(String male) {
		this.male = male;
	}






	public String getFemale() {
		return female;
	}






	public void setFemale(String female) {
		this.female = female;
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
