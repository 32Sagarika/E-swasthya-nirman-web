package nirmalya.aathithya.webmodule.admin.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.user.model.ModulesAccessModel;

public class DoctorListingModel {
	private String profilepic;
	private String docName;
	private String docRegNo;
	private String docId;
	private String docClinic;
	private String topDoctor;
	private String docSpeciality;
	private String docEmail;
	private String docMobile;
	private String docAge;
	private String docGender;
	private String docQualification;
	private String docAbout;
	private String docPassword;
	private String docPYear;
	private String docLang;
	private String docImg;
	
	
	private String addslNo;
	private String docCity;
	private String docAddress;
	private String docLongitude;
	private String docLatitude;
	
	private String conSlNo;
	private String docAudio;
	private String docVideo;
	private String docPhy;
		
	private String avlSlNo;
	private String openingTime;
	private String closingTime;
	private String fromTime;
	private String toTime;
	private String slotBooking;
	private String offDays;
	
	private String accSlNo;
	private String accountName;
	private String accountNo;
	private String ifscCode;
	
	private String leaveFrom;
	private String leaveTo;
	
	private List<DoctorAddressModel> address;
	private List<DoctorConsultationFeeModel> consult;
	private List<DoctorAvailableTimeModel> avail;
	private List<DoctorBankAccountModel> bank;
	//private ArrayList<DropDownModel> timeSlotList;
	
	//private List<DropDownModel> timeSlotList= new ArrayList<DropDownModel>();
	private  ArrayList timeSlotList; 
	
	private String first;
	private String last;
	
	public DoctorListingModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getLeaveFrom() {
		return leaveFrom;
	}




	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}




	public String getLeaveTo() {
		return leaveTo;
	}




	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}




	public String getAddslNo() {
		return addslNo;
	}




	public void setAddslNo(String addslNo) {
		this.addslNo = addslNo;
	}




	public String getDocCity() {
		return docCity;
	}




	public void setDocCity(String docCity) {
		this.docCity = docCity;
	}




	public String getDocAddress() {
		return docAddress;
	}




	public void setDocAddress(String docAddress) {
		this.docAddress = docAddress;
	}




	public String getDocLongitude() {
		return docLongitude;
	}




	public void setDocLongitude(String docLongitude) {
		this.docLongitude = docLongitude;
	}




	public String getDocLatitude() {
		return docLatitude;
	}




	public void setDocLatitude(String docLatitude) {
		this.docLatitude = docLatitude;
	}




	public String getConSlNo() {
		return conSlNo;
	}




	public void setConSlNo(String conSlNo) {
		this.conSlNo = conSlNo;
	}




	public String getDocAudio() {
		return docAudio;
	}




	public void setDocAudio(String docAudio) {
		this.docAudio = docAudio;
	}




	public String getDocVideo() {
		return docVideo;
	}




	public void setDocVideo(String docVideo) {
		this.docVideo = docVideo;
	}




	public String getDocPhy() {
		return docPhy;
	}




	public void setDocPhy(String docPhy) {
		this.docPhy = docPhy;
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




	public String getAccSlNo() {
		return accSlNo;
	}




	public void setAccSlNo(String accSlNo) {
		this.accSlNo = accSlNo;
	}




	public String getAccountName() {
		return accountName;
	}




	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}




	public String getAccountNo() {
		return accountNo;
	}




	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}




	public String getIfscCode() {
		return ifscCode;
	}




	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}




	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocRegNo() {
		return docRegNo;
	}

	public void setDocRegNo(String docRegNo) {
		this.docRegNo = docRegNo;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocClinic() {
		return docClinic;
	}

	public void setDocClinic(String docClinic) {
		this.docClinic = docClinic;
	}

	public String getTopDoctor() {
		return topDoctor;
	}

	public void setTopDoctor(String topDoctor) {
		this.topDoctor = topDoctor;
	}

	public String getDocSpeciality() {
		return docSpeciality;
	}

	public void setDocSpeciality(String docSpeciality) {
		this.docSpeciality = docSpeciality;
	}

	public String getDocEmail() {
		return docEmail;
	}

	public void setDocEmail(String docEmail) {
		this.docEmail = docEmail;
	}

	public String getDocMobile() {
		return docMobile;
	}

	public void setDocMobile(String docMobile) {
		this.docMobile = docMobile;
	}

	public String getDocAge() {
		return docAge;
	}

	public void setDocAge(String docAge) {
		this.docAge = docAge;
	}

	public String getDocGender() {
		return docGender;
	}

	public void setDocGender(String docGender) {
		this.docGender = docGender;
	}

	public String getDocQualification() {
		return docQualification;
	}

	public void setDocQualification(String docQualification) {
		this.docQualification = docQualification;
	}

	public String getDocAbout() {
		return docAbout;
	}

	public void setDocAbout(String docAbout) {
		this.docAbout = docAbout;
	}

	public String getDocPassword() {
		return docPassword;
	}

	public void setDocPassword(String docPassword) {
		this.docPassword = docPassword;
	}

	public String getDocPYear() {
		return docPYear;
	}

	public void setDocPYear(String docPYear) {
		this.docPYear = docPYear;
	}

	public String getDocLang() {
		return docLang;
	}

	public void setDocLang(String docLang) {
		this.docLang = docLang;
	}

	public String getDocImg() {
		return docImg;
	}

	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}

	
	public List<DoctorAddressModel> getAddress() {
		return address;
	}

	public void setAddress(List<DoctorAddressModel> address) {
		this.address = address;
	}

	public List<DoctorConsultationFeeModel> getConsult() {
		return consult;
	}

	public void setConsult(List<DoctorConsultationFeeModel> consult) {
		this.consult = consult;
	}

	public List<DoctorAvailableTimeModel> getAvail() {
		return avail;
	}

	public void setAvail(List<DoctorAvailableTimeModel> avail) {
		this.avail = avail;
	}

	public List<DoctorBankAccountModel> getBank() {
		return bank;
	}

	public void setBank(List<DoctorBankAccountModel> bank) {
		this.bank = bank;
	}
	

	


	










	public ArrayList getTimeSlotList() {
		return timeSlotList;
	}




	public void setTimeSlotList(ArrayList timeSlotList) {
		this.timeSlotList = timeSlotList;
	}




	public String getFirst() {
		return first;
	}




	public void setFirst(String first) {
		this.first = first;
	}




	public String getLast() {
		return last;
	}




	public void setLast(String last) {
		this.last = last;
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
