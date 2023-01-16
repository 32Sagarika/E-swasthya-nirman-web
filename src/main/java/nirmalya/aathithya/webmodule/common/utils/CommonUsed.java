package nirmalya.aathithya.webmodule.common.utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CommonUsed {

	/** 1. GENERATE QR CODE **/

	@SuppressWarnings("deprecation")
	public static void generateQRCode(String qrcode, String uniqueid, String mobile, String name,
			String fileUploadProfile) {

		try {
			String qrCodeData = "UniqueId : " + uniqueid + "\nMobile : " + mobile + "\nName : " + name; 
			String filePath = fileUploadProfile + qrcode ;
			String charset = "UTF-8";// "ISO-8859-1";

			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, 200, 200, hintMap);
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
					new File(filePath));

			System.out.println("QR Code image created successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 2. SMS GATEWAY 
	 * @throws IOException 
	 * @throws ClientProtocolException **/
	
	public static void sendSMS(String mobile, String message) throws ClientProtocolException, IOException {
		
		String ServerDomainApiEndPoint = "https://www.smsgateway.center/SMSApi/rest/send";

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(ServerDomainApiEndPoint);

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("userId", "ncord"));
		params.add(new BasicNameValuePair("password", "Ayoqpey1"));
		params.add(new BasicNameValuePair("msg", message));
		params.add(new BasicNameValuePair("msgType", "text"));
		params.add(new BasicNameValuePair("sendMethod", "simpleMsg"));
		params.add(new BasicNameValuePair("senderId", "EHSAPP"));
		params.add(new BasicNameValuePair("dltEntityId", "1601100000000002843"));
		params.add(new BasicNameValuePair("duplicateCheck", "true"));
		params.add(new BasicNameValuePair("format", "json"));
		params.add(new BasicNameValuePair("mobile", mobile)); 
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		System.out.println("StatusCode: " + response.getStatusLine().getStatusCode());

		if (entity != null) {
			try (InputStream instream = entity.getContent()) {
				System.out.println(EntityUtils.toString(entity, "utf-8"));
			}
		}
	}
	/** 3. SMS GATEWAY ----Type Return
	 * @throws IOException 
	  @throws ClientProtocolException */
	
	public static int sendSMSS(String mobile, String message) throws ClientProtocolException, IOException {
		
		String ServerDomainApiEndPoint = "https://www.smsgateway.center/SMSApi/rest/send";

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(ServerDomainApiEndPoint);

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("userId", "ncord"));
		params.add(new BasicNameValuePair("password", "Ayoqpey1"));
		params.add(new BasicNameValuePair("msg", message));
		params.add(new BasicNameValuePair("msgType", "text"));
		params.add(new BasicNameValuePair("sendMethod", "simpleMsg"));
		params.add(new BasicNameValuePair("senderId", "EHSAPP"));
		params.add(new BasicNameValuePair("dltEntityId", "1601100000000002843"));
		params.add(new BasicNameValuePair("duplicateCheck", "true"));
		params.add(new BasicNameValuePair("format", "json"));
		params.add(new BasicNameValuePair("mobile", mobile)); 
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
        int smsStatus=response.getStatusLine().getStatusCode();
		//System.out.println("StatusCode: " + response.getStatusLine().getStatusCode());
        System.out.println("smsStatus: " +smsStatus);
		if (entity != null) {
			try (InputStream instream = entity.getContent()) {
				System.out.println(EntityUtils.toString(entity, "utf-8"));
			}
		}
		return smsStatus;
	}
	// Random no of otp
		public static String generateRandom(int length) {
			Random random = new Random();
			char[] digits = new char[length];
			digits[0] = (char) (random.nextInt(9) + '1');
			for (int i = 1; i < length; i++) {
				digits[i] = (char) (random.nextInt(10) + '0');
			}
			return new String(digits);
		}
}
