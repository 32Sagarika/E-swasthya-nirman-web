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

import nirmalya.aathithya.webmodule.admin.model.LabtestOrderModel;
import nirmalya.aathithya.webmodule.admin.model.CityLanguageWebModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorListingModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.LabtestOrderModel;



@Controller

@RequestMapping(value = { "admin/" })
public class LabTestOrderController {
	Logger logger = LoggerFactory.getLogger(LabTestOrderController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "lab-labtestorders" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :labtestorders starts");

		logger.info("Method : labtestorders ends");
		return "admin/labtestorder";
	}
	
	/*
	 * View patient 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("lab-labtestorders-viewDetails")
	public @ResponseBody List<LabtestOrderModel> viewlabtestordersDetails(HttpSession session) {

		logger.info("Method :viewlabtestordersDetails starts");

		JsonResponse<List<LabtestOrderModel>> resp = new JsonResponse<List<LabtestOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewlabtestordersDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabtestOrderModel>  labtestOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabtestOrderModel>>() {
				});
		
		resp.setBody(labtestOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewlabtestordersDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("lab-labtestorders-EditStatusDetails")
	public @ResponseBody JsonResponse<Object> EditStatusDetails(Model model, @RequestBody LabtestOrderModel adminModel,
			HttpSession session) {
		logger.info("Method : EditStatusDetails starts");
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "editStatusDetails", adminModel, JsonResponse.class);
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
		System.out.println("EditStatusDetails" + resp);
		logger.info("Method : EditStatusDetails starts");
		return resp;
	}
	

	@SuppressWarnings("unchecked")
	@GetMapping("lab-labtestorders-viewDetailsById")
	public @ResponseBody JsonResponse<LabtestOrderModel> ViewLabTestOrderDetails(@RequestParam String id,
			HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
		logger.info("Method : ViewLabTestOrderDetails starts");

		JsonResponse<LabtestOrderModel> jsonResponse = new JsonResponse<LabtestOrderModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "viewLabTestOrderDetailsById?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		LabtestOrderModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<LabtestOrderModel>() {
		});

		jsonResponse.setBody(reimModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : ViewLabTestOrderDetails ends");
		System.out.println("ViewLabTestOrderDetails" + jsonResponse);
		return jsonResponse;
	}
}
