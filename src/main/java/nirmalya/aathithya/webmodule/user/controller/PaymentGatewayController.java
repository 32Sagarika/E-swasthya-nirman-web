package nirmalya.aathithya.webmodule.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import nirmalya.aathithya.webmodule.util.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import nirmalya.aathithya.webmodule.admin.controller.CouponController;
import nirmalya.aathithya.webmodule.admin.model.AdminLocationModel;
import nirmalya.aathithya.webmodule.admin.model.CouponDetailsModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.PaymentGatewayAPIModel;
import nirmalya.aathithya.webmodule.user.model.PaymentGatewayModel;
import nirmalya.aathithya.webmodule.util.RazorpayKey;

@Controller

@RequestMapping(value = { "user/" })
public class PaymentGatewayController {
	Logger logger = LoggerFactory.getLogger(PaymentGatewayController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "view-order" })

	public String viewOrder(Model model, HttpSession session) {
		logger.info("Method :viewOrder starts");

		logger.info("Method : viewOrder ends");
		return "user/viewOrder";
	}
	//view coupon
		@SuppressWarnings("unchecked")
		@GetMapping("view-order-patient")
		public @ResponseBody List<PaymentGatewayModel> viewPatientDetails(HttpSession session) {

			logger.info("Method :viewPatientDetails starts");

			JsonResponse<List<PaymentGatewayModel>> resp = new JsonResponse<List<PaymentGatewayModel>>();
			String userId = "";
			String dateFormat = "";

			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat = (String) session.getAttribute("DATEFORMAT");

				resp = restTemplate.getForObject(env.getAdminUrl() + "rest-viewPatientDetailss?id=" + userId, JsonResponse.class);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<PaymentGatewayModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<PaymentGatewayModel>>() {
					});

			resp.setBody(lifeHistoryModel);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			//System.out.println("VIEW" + resp.getBody());
			logger.info("Method :viewCouponDetails ends");
			return resp.getBody();
		}
		
		/** Create Order in Razorpay 
		 * @throws JSONException 
		 * @throws NumberFormatException **/	
		@SuppressWarnings("unchecked")
		@PostMapping("create-order-web")
		public @ResponseBody String createOrder(Model model, HttpSession session, @RequestBody Map<String,Object> data) throws NumberFormatException, JSONException{
			logger.info("Method : createOrder starts");
				
			logger.info("Data found successfully......");
				
			String key_id = RazorpayKey.key_id;
			String key_secret = RazorpayKey.key_secret;
				
			String amount = data.get("amnt").toString();
			String orderid = data.get("orderid").toString();
		    String type = "online";
			RazorpayClient razorpayClient = null;
			try {
				razorpayClient = new RazorpayClient(key_id,key_secret);
				logger.info("razorpayClient"+razorpayClient);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				
			JSONObject options = new JSONObject();
			logger.info("options"+options);
			options.put("amount", Double.parseDouble(amount)*100);
			options.put("currency", "INR");
			options.put("receipt", orderid);
			Order order = null;
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				order = razorpayClient.Orders.create(options);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj =  order.toJson();
			
			String userid = (String) session.getAttribute("USER_ID");
			String username = (String) session.getAttribute("USER_NAME");
			String usermob = (String) session.getAttribute("USER_MOBILE");
			String useremail = (String) session.getAttribute("USER_EMAIL");
			
			jsonObj.put("userid", userid);
			jsonObj.put("username", username);
			jsonObj.put("usermob", usermob);
			jsonObj.put("useremail", useremail);
			jsonObj.put("key_id", key_id);
			jsonObj.put("key_secret", key_secret);
			
			logger.info(jsonObj.toString());
				
			/* Save your data to DB */
			JsonResponse<Object> resp = new JsonResponse<Object>();
			DropDownModel dropdownModel = new DropDownModel();
			dropdownModel.setKey(orderid);
			dropdownModel.setCode(amount);
			dropdownModel.setName(type);
			dropdownModel.setImage(userid);
			logger.info( "save-payment-details"+dropdownModel.getCode());
			try {
				resp = restTemplate.postForObject(env.getAdminUrl() + "save-payment-details-web", dropdownModel,
						JsonResponse.class);
			} catch (Exception e) {
				logger.error("payment details not saved");
				e.printStackTrace();
			}
			
			
			logger.info("Method : createOrder ends");
			return !StringUtil.isNull(order) ? order.toString() : jsonObj.toString();
		}
		
		@SuppressWarnings("unchecked")
		@PostMapping("save-payment-status-web")
		public @ResponseBody JsonResponse<Object> savePaymentStatus(@RequestBody  Map<String,Object> map){
			logger.info("Method : savePaymentStatus starts");
			JsonResponse<Object> resp = new JsonResponse<Object>();
			try {
				resp = restTemplate.postForObject(env.getAdminUrl() + "rest-save-payment-status", map, JsonResponse.class);
			} catch (Exception e) {
				logger.error("payment status not updated");
				e.printStackTrace();
			}
			logger.info("Method : savePaymentStatus ends");
			return resp;
		}
		
		@SuppressWarnings("unchecked")
		@PostMapping("create-order-webviews")
		public @ResponseBody String createOrderWeb(Model model, HttpSession session, @RequestParam String orderId,@RequestParam String amounts){
			logger.info("Method : createOrder starts");
				
			logger.info("Data found successfully......");
				
			String key_id = RazorpayKey.key_id;
			String key_secret = RazorpayKey.key_secret;
				
			String amount = amounts;
			String orderid = orderId;
		 
			RazorpayClient razorpayClient = null;
			try {
				razorpayClient = new RazorpayClient(key_id,key_secret);
				logger.info("razorpayClient"+razorpayClient);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				
			JSONObject options = new JSONObject();
			logger.info("options"+options);
			options.put("amount", Double.parseDouble(amount)*100);
			options.put("currency", "INR");
			options.put("receipt", orderid);
			Order order = null;
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				order = razorpayClient.Orders.create(options);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj =  order.toJson();
			
			String userid = (String) session.getAttribute("USER_ID");
			String username = (String) session.getAttribute("USER_NAME");
			String usermob = (String) session.getAttribute("USER_MOBILE");
			String useremail = (String) session.getAttribute("USER_EMAIL");
			
			jsonObj.put("userid", userid);
			jsonObj.put("username", username);
			jsonObj.put("usermob", usermob);
			jsonObj.put("useremail", useremail);
			jsonObj.put("key_id", key_id);
			jsonObj.put("key_secret", key_secret);
			
			logger.info(jsonObj.toString());
			
			
			logger.info("Method : createOrder ends");
			return !StringUtil.isNull(order) ? order.toString() : jsonObj.toString();
		}
		
		  @SuppressWarnings("unchecked")
			@RequestMapping(value = "/create-order-webview", method = RequestMethod.GET)
		    public @ResponseBody String createPaymentOrder(Model model, HttpSession session, @RequestParam String orderId,@RequestParam String amounts){
		    	logger.info("Method : createPaymentOrder starts");
		    	
		    	String key_id = RazorpayKey.key_id;
				String key_secret = RazorpayKey.key_secret;
					
				String amount = amounts;
				String orderid = orderId;
			 
				RazorpayClient razorpayClient = null;
				try {
					razorpayClient = new RazorpayClient(key_id,key_secret);
					logger.info("razorpayClient"+razorpayClient);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					
				JSONObject options = new JSONObject();
				logger.info("options"+options);
				options.put("amount", Double.parseDouble(amount)*100);
				options.put("currency", "INR");
				options.put("receipt", orderid);
				Order order = null;
				String userId = "";
				try {
					userId = (String) session.getAttribute("USER_ID");
					order = razorpayClient.Orders.create(options);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				JSONObject jsonObj =  order.toJson();
				
				String userid = (String) session.getAttribute("USER_ID");
				String username = (String) session.getAttribute("USER_NAME");
				String usermob = (String) session.getAttribute("USER_MOBILE");
				String useremail = (String) session.getAttribute("USER_EMAIL");
				
				jsonObj.put("userid", userid);
				jsonObj.put("username", username);
				jsonObj.put("usermob", usermob);
				jsonObj.put("useremail", useremail);
				jsonObj.put("key_id", key_id);
				jsonObj.put("key_secret", key_secret);
				
				logger.info(jsonObj.toString());
				logger.info("Method : createPaymentOrder ends");
				return jsonObj.toString();
		    }
}
