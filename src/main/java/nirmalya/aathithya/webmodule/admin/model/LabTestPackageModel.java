package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabTestPackageModel {

	private String labId;
	private String name;
	private String labtestName;
	private String imgCategory;
	private String description;
	private String price;
	private String discountprice;
	public LabTestPackageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getLabId() {
		return labId;
	}


	public void setLabId(String labId) {
		this.labId = labId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLabtestName() {
		return labtestName;
	}


	public void setLabtestName(String labtestName) {
		this.labtestName = labtestName;
	}


	public String getImgCategory() {
		return imgCategory;
	}


	public void setImgCategory(String imgCategory) {
		this.imgCategory = imgCategory;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
