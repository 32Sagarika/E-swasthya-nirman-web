package nirmalya.aathithya.webmodule.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DropDownModel {
	private String key;

	private String name;
	private String code;

	private String image;
	private String language;
	private String pass;
	List<Object> diseaseDetails = new ArrayList<Object>();
	
	public DropDownModel(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	public DropDownModel() {
		super(); 
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Object> getDiseaseDetails() {
		return diseaseDetails;
	}

	public void setDiseaseDetails(List<Object> diseaseDetails) {
		this.diseaseDetails = diseaseDetails;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
