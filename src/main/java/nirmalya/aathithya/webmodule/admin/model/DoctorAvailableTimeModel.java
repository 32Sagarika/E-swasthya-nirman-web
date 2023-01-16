package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorAvailableTimeModel {

	
	private String avlSlNo;
	private String openingTime;
	private String closingTime;
	private String fromTime;
	private String toTime;
	private String slotBooking;
	private String offDays;
		
		
	public DoctorAvailableTimeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getAvlSlNo() {
		return avlSlNo;
	}


	public void setAvlSlNo(String avlSlNo) {
		this.avlSlNo = avlSlNo;
	}


	public String getOpeningTime() {
		return openingTime;
	}


	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}


	public String getClosingTime() {
		return closingTime;
	}


	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}


	public String getFromTime() {
		return fromTime;
	}


	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}


	public String getToTime() {
		return toTime;
	}


	public void setToTime(String toTime) {
		this.toTime = toTime;
	}


	public String getSlotBooking() {
		return slotBooking;
	}


	public void setSlotBooking(String slotBooking) {
		this.slotBooking = slotBooking;
	}


	public String getOffDays() {
		return offDays;
	}


	public void setOffDays(String offDays) {
		this.offDays = offDays;
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
