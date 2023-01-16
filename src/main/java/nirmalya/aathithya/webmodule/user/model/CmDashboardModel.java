package nirmalya.aathithya.webmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CmDashboardModel {
	private Integer supId;
	private String totalRegs;
	private String total_tested;
	private String total_normal;
	private String total_abnrml;
	private String total_highrisk;
	private String todays_regs;
	private String total_smpl_clctd;
	private String total_smpl_prcsd;

	private String ehealthId;
	private String patName;
	private String patMob;
	private String email;
	private String screeningDate;
	private String status;
	private String action;
	private String report;
	private String testId;

	
	private String id;
	private String name;
	private String logo;
	private String image;
	private String distId;
	private String distName;
	private String countryId;
	private String cmName;
	
	
	public Integer getSupId() {
		return supId;
	}

	public void setSupId(Integer supId) {
		this.supId = supId;
	}

	public String getTotalRegs() {
		return totalRegs;
	}

	public void setTotalRegs(String totalRegs) {
		this.totalRegs = totalRegs;
	}
	public String getTotal_tested() {
		return total_tested;
	}

	public void setTotal_tested(String total_tested) {
		this.total_tested = total_tested;
	}

	public String getTotal_normal() {
		return total_normal;
	}

	public void setTotal_normal(String total_normal) {
		this.total_normal = total_normal;
	}

	public String getTotal_abnrml() {
		return total_abnrml;
	}

	public void setTotal_abnrml(String total_abnrml) {
		this.total_abnrml = total_abnrml;
	}

	public String getTotal_highrisk() {
		return total_highrisk;
	}

	public void setTotal_highrisk(String total_highrisk) {
		this.total_highrisk = total_highrisk;
	}

	public String getTodays_regs() {
		return todays_regs;
	}

	public void setTodays_regs(String todays_regs) {
		this.todays_regs = todays_regs;
	}

	public String getTotal_smpl_clctd() {
		return total_smpl_clctd;
	}

	public void setTotal_smpl_clctd(String total_smpl_clctd) {
		this.total_smpl_clctd = total_smpl_clctd;
	}

	public String getTotal_smpl_prcsd() {
		return total_smpl_prcsd;
	}

	public void setTotal_smpl_prcsd(String total_smpl_prcsd) {
		this.total_smpl_prcsd = total_smpl_prcsd;
	}

	public String getEhealthId() {
		return ehealthId;
	}

	public void setEhealthId(String ehealthId) {
		this.ehealthId = ehealthId;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatMob() {
		return patMob;
	}

	public void setPatMob(String patMob) {
		this.patMob = patMob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScreeningDate() {
		return screeningDate;
	}

	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCmName() {
		return cmName;
	}

	public void setCmName(String cmName) {
		this.cmName = cmName;
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