package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminCategoryModel {

	private String adminId;
	private String categoryId;
	private String categoryTitle;
	private String status;
	private String imgCategory;
	
	
	
	
	public String getAdminId() {
		return adminId;
	}




	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}




	public String getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}




	public String getCategoryTitle() {
		return categoryTitle;
	}




	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getImgCategory() {
		return imgCategory;
	}




	public void setImgCategory(String imgCategory) {
		this.imgCategory = imgCategory;
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
