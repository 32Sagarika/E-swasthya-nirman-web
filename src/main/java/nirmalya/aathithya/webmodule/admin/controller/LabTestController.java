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

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.LabModel;
import nirmalya.aathithya.webmodule.admin.model.LabTestModel;

@Controller

@RequestMapping(value = { "admin/" })
public class LabTestController {
	Logger logger = LoggerFactory.getLogger(LabTestController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "lab-test" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :lab starts");

		logger.info("Method : lab ends");
		return "admin/labtest";
	}

	/*
	 * ADD FOR
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("lab-test-addDetails")
	public @ResponseBody JsonResponse<Object> addLabTestDetails(Model model, @RequestBody LabTestModel adminModel,
			HttpSession session) {
		logger.info("Method : addLabTestDetails starts");
		System.out.println("LabTestModel" + model);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addLabTestDetails", adminModel, JsonResponse.class);
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
		System.out.println("addLabTestDetails" + resp);
		logger.info("Method : addLabTestDetails starts");
		return resp;
	}

	/*
	 * View patient
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("lab-test-viewDetails")
	public @ResponseBody List<LabTestModel> viewslabTestsDetails(HttpSession session) {

		logger.info("Method :viewslabTestsDetails starts");

		JsonResponse<List<LabTestModel>> resp = new JsonResponse<List<LabTestModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewslabTestsDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabTestModel> labModel = mapper.convertValue(resp.getBody(), new TypeReference<List<LabTestModel>>() {
		});

		resp.setBody(labModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("VIEW" + resp.getBody());
		logger.info("Method :viewslabTestsDetails ends");
		return resp.getBody();
	}

	// edit

	@SuppressWarnings("unchecked")

	@GetMapping("lab-test-neweditDetails")
	public @ResponseBody JsonResponse<LabTestModel> editLabTestDetails(@RequestParam String id, HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
		logger.info("Method : editLabTestDetails starts");

		JsonResponse<LabTestModel> jsonResponse = new JsonResponse<LabTestModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLabTestDetails?id=" + id, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		LabTestModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<LabTestModel>() {
		});

		/*
		 * if (reimModel.getDate() != null && reimModel.getDate() != "") {
		 * reimModel.setDate(DateFormatter.dateFormat(reimModel.getDate(), dateFormat));
		 * }
		 */
		jsonResponse.setBody(reimModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : editLabTestDetails ends");
		System.out.println("editLabTestDetails" + jsonResponse);
		return jsonResponse;
	}
	
	
	//delete 
	
	@SuppressWarnings("unchecked")
	@PostMapping("lab-test-delete")
	public @ResponseBody JsonResponse<Object> deleteLabTestDetails(@RequestParam String id,
			Model model, HttpSession session) {
		logger.info("Method : deleteLabTestDetails function starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteLabTestDetails?id=" + id  , JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteLabTestDetails function Ends");
		
		System.out.println("RESPPPPPPP"+res);
		return res;
	}

}