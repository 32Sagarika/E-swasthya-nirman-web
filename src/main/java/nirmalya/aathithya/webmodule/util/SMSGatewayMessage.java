package nirmalya.aathithya.webmodule.util;

public class SMSGatewayMessage {

	public static String OTP = "Welcome to eHealthSystem. Your OTP is {otp}";
	public static String REGISTRATION = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
			+ "{userid} or {mobile} and password is {password}";
	public static String FORGOT_USERID = "Your Username for eHealthSystem for registered mobile number is {userid}";
	public static String DOCTOR_APPOINTMENT = "Dear User,\r\n"
			+ "\r\n"
			+ "Your Request for Appointment sent successfully! Your Appointment Date and time is {date} "
			+ "{time} with Dr. {doctorname}\r\n"
			+ "\r\n"
			+ "Regards,\r\n"
			+ "eHealthSystem Team";
	public static String EMERGENCY_ALERT = "eHealthSystem Emergency Alert! I need help. My location is {longitude} & {latitude}.";
	public static String TEST_REPORT = "Dear {name},\n" + "\n"
			+ "Your Report has been generated successfully! Download from link {url}\n\n"
			+ "Regards,\r\n" + "eHealthSystem Team";
}
