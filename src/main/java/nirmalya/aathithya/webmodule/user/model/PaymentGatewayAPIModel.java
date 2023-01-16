package nirmalya.aathithya.webmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentGatewayAPIModel {

	private String order_id;
	private String payment_id;
	private String amount;
	private String status;
	private String createdOn;
	private String userid;
	private String pay_mode;
	private String join_url;
	private String start_url;
	private String showcode;
	
	private String userId;
	private String userName;
	private String phno;
	private String email;
	private String paymentdate;
	private String paymenttime;
	private String partnerName;
	private String deviceToken;
	private String consulttype;
	private String doctorToken;
	private String doctorName;
	private String doctorMob;
	public PaymentGatewayAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public PaymentGatewayAPIModel(Object order_id, Object payment_id, Object amount, Object status, Object createdOn,
			Object userid, Object pay_mode, Object join_url,Object start_url,Object showcode,Object consulttype) {
		super();
		this.order_id = (String)order_id;
		this.payment_id = (String)payment_id;
		this.amount = (String)amount;
		this.status = (String)status;
		this.createdOn = (String)createdOn;
		this.userid = (String)userid;
		this.pay_mode =(String) pay_mode;
		this.join_url = (String)join_url;
		this.start_url = (String)start_url;
		this.showcode = (String)showcode;
		this.consulttype = (String)consulttype;
		
	}
	
	public PaymentGatewayAPIModel(Object userId, Object userName, Object phno, Object email,Object paymenttime
			,Object status,Object deviceToken,Object paymentdate,Object doctorName,Object doctorMob) {
		super();
		this.userId = (String)userId;
		this.userName = (String)userName;
		this.phno = (String)phno;
		this.email = (String)email;
		this.paymenttime = (String)paymenttime;
		this.status = (String)status;
		this.deviceToken = (String)deviceToken;
		this.paymentdate = (String)paymentdate;
		this.doctorName = (String)doctorName;
		this.doctorMob = (String)doctorMob;
	}
	public PaymentGatewayAPIModel(Object doctorToken, Object phno) {
		super();
		this.doctorToken = (String)doctorToken;
		this.phno = (String)phno;
	}
	public PaymentGatewayAPIModel(Object order_id, Object paymentdate, Object paymenttime, Object userName
			, Object email,Object partnerName,Object deviceToken,Object phno,Object consulttype) {
		super();
		this.order_id = (String)order_id;
		this.paymentdate = (String)paymentdate;
		this.paymenttime = (String)paymenttime;
		this.userName = (String)userName;
		this.email = (String)email;
		this.partnerName = (String)partnerName;
		this.deviceToken = (String)deviceToken;
		this.phno = (String)phno;
		this.consulttype = (String)consulttype;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}
	

	public String getJoin_url() {
		return join_url;
	}

	public void setJoin_url(String join_url) {
		this.join_url = join_url;
	}
	

	public String getStart_url() {
		return start_url;
	}


	public void setStart_url(String start_url) {
		this.start_url = start_url;
	}
	


	public String getShowcode() {
		return showcode;
	}


	public void setShowcode(String showcode) {
		this.showcode = showcode;
	}
	


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhno() {
		return phno;
	}


	public void setPhno(String phno) {
		this.phno = phno;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	


	public String getPaymentdate() {
		return paymentdate;
	}


	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}


	public String getPaymenttime() {
		return paymenttime;
	}


	public void setPaymenttime(String paymenttime) {
		this.paymenttime = paymenttime;
	}
	


	public String getPartnerName() {
		return partnerName;
	}


	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	


	public String getDeviceToken() {
		return deviceToken;
	}


	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	


	public String getConsulttype() {
		return consulttype;
	}


	public void setConsulttype(String consulttype) {
		this.consulttype = consulttype;
	}


	public String getDoctorToken() {
		return doctorToken;
	}


	public void setDoctorToken(String doctorToken) {
		this.doctorToken = doctorToken;
	}
	


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getDoctorMob() {
		return doctorMob;
	}


	public void setDoctorMob(String doctorMob) {
		this.doctorMob = doctorMob;
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