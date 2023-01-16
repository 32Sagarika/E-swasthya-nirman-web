package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientListingModel {
	
	private String name;
	private String email;
	private String mobile;
	private String totalBooking;
	
	private String patName;
	private String docName;
	private String bookDate;
	private String bookType;
	private String timeSlot;
	private String speciality;
	private String status;
	 
	 
	public PatientListingModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getPatName() {
		return patName;
	}




	public void setPatName(String patName) {
		this.patName = patName;
	}




	public String getDocName() {
		return docName;
	}




	public void setDocName(String docName) {
		this.docName = docName;
	}




	public String getBookDate() {
		return bookDate;
	}




	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}




	public String getBookType() {
		return bookType;
	}




	public void setBookType(String bookType) {
		this.bookType = bookType;
	}




	public String getTimeSlot() {
		return timeSlot;
	}




	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}




	public String getSpeciality() {
		return speciality;
	}




	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTotalBooking() {
		return totalBooking;
	}
	public void setTotalBooking(String totalBooking) {
		this.totalBooking = totalBooking;
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
