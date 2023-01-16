package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

import java.util.List;
public class CouponDetailsModel {

	private String couponId;
	private String couponName;
	private String discountPrice;
	private String fromdate;
	private String todate;
	private String percentage;
	private String patientId;
	private List<DropDownModel> couponList;
	
	public  CouponDetailsModel() {
		super();
	}
	
	
	public String getCouponId() {
		return couponId;
	}


	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}


	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public String getDiscountPrice() {
		return discountPrice;
	}


	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}


	public String getFromdate() {
		return fromdate;
	}


	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}


	public String getTodate() {
		return todate;
	}


	public void setTodate(String todate) {
		this.todate = todate;
	}


	public String getPercentage() {
		return percentage;
	}


	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public List<DropDownModel> getCouponList() {
		return couponList;
	}


	public void setCouponList(List<DropDownModel> couponList) {
		this.couponList = couponList;
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
