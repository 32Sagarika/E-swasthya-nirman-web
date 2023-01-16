package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabTestModel {
	private String labId;
	private String code;
	private String name;
	private String methodology;
	private String sampletypevolume;
	private String processon;
	private String tat;
	private String frequently;
	private String featured;
	private String price;
	private String discountprice;
	private String description;
	private String b2b;
	private String yoursaving;
	private String yoursavingspercentage;
	public LabTestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLabId() {
		return labId;
	}
	public void setLabId(String labId) {
		this.labId = labId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethodology() {
		return methodology;
	}
	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}
	public String getSampletypevolume() {
		return sampletypevolume;
	}
	public void setSampletypevolume(String sampletypevolume) {
		this.sampletypevolume = sampletypevolume;
	}
	public String getProcesson() {
		return processon;
	}
	public void setProcesson(String processon) {
		this.processon = processon;
	}
	public String getTat() {
		return tat;
	}
	public void setTat(String tat) {
		this.tat = tat;
	}
	public String getFrequently() {
		return frequently;
	}
	public void setFrequently(String frequently) {
		this.frequently = frequently;
	}
	public String getFeatured() {
		return featured;
	}
	public void setFeatured(String featured) {
		this.featured = featured;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(String discountprice) {
		this.discountprice = discountprice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getB2b() {
		return b2b;
	}
	public void setB2b(String b2b) {
		this.b2b = b2b;
	}
	public String getYoursaving() {
		return yoursaving;
	}
	public void setYoursaving(String yoursaving) {
		this.yoursaving = yoursaving;
	}
	public String getYoursavingspercentage() {
		return yoursavingspercentage;
	}
	public void setYoursavingspercentage(String yoursavingspercentage) {
		this.yoursavingspercentage = yoursavingspercentage;
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
