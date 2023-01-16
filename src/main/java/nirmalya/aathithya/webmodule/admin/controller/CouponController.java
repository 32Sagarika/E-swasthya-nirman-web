package nirmalya.aathithya.webmodule.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.admin.model.CityLanguageWebModel;
import nirmalya.aathithya.webmodule.admin.model.CouponDetailsModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller

@RequestMapping(value = { "admin/" })
public class CouponController {
	Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-coupon" })

	public String adminbnciti(Model model, HttpSession session) {
		logger.info("Method :city starts");

		logger.info("Method : city ends");
		return "admin/coupon";
	}
	
	
	
	//Add coupon
	@SuppressWarnings("unchecked")
	@PostMapping("admin-coupon-addDetails")
	public @ResponseBody JsonResponse<Object> addCoupon(Model model, @RequestBody CouponDetailsModel adminCouponModel,
			HttpSession session) {
		logger.info("Method : addCoupon starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addCoupon", adminCouponModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		// resp.setBody(body);
		System.out.println("#####" + resp);
		logger.info("Method : addCoupon ends");
		return resp;
	}

	
	//view coupon
	@SuppressWarnings("unchecked")
	@GetMapping("admin-coupon-viewDetails")
	public @ResponseBody List<CouponDetailsModel> viewCouponDetails(HttpSession session) {

		logger.info("Method :viewCouponDetails starts");

		JsonResponse<List<CouponDetailsModel>> resp = new JsonResponse<List<CouponDetailsModel>>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

			resp = restTemplate.getForObject(env.getAdminUrl() + "restViewCoupon", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<CouponDetailsModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CouponDetailsModel>>() {
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
	
	//edit coupon
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-coupon-editDetails")
	public @ResponseBody JsonResponse<CouponDetailsModel> EditCouponDetails(@RequestParam String id,
			HttpSession session) {
		//System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
		logger.info("Method : EditCouponDetails starts");

		JsonResponse<CouponDetailsModel> jsonResponse = new JsonResponse<CouponDetailsModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "EditCouponDetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		CouponDetailsModel couponDetailModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<CouponDetailsModel>() {
		});

		jsonResponse.setBody(couponDetailModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : EditCouponDetails ends");
		System.out.println("######" + jsonResponse);
		return jsonResponse;
	}
	
	
	//delete coupon
	@SuppressWarnings("unchecked")
	@PostMapping("admin-coupon-delete")
	public @ResponseBody JsonResponse<Object> deleteCouponDetails(@RequestParam String id, Model model,
			HttpSession session) {
		logger.info("Method : deleteCouponDetails function starts");
		System.out.println("RESPPPPPPP" + id);
		JsonResponse<Object> res = new JsonResponse<Object>();

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteAdminCouponDetails?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteCouponDetails function Ends");

		System.out.println("RESPPPPPPP" + res);
		return res;
	}
}