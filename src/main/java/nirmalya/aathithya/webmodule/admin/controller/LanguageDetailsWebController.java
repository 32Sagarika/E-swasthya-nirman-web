package nirmalya.aathithya.webmodule.admin.controller;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.admin.model.CityLanguageWebModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller

@RequestMapping(value = { "admin/" })
public class LanguageDetailsWebController {
	Logger logger = LoggerFactory.getLogger(LanguageDetailsWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-languages" })

	public String adminbnlang(Model model, HttpSession session) {
		logger.info("Method :lang starts");

		logger.info("Method : lang ends");
		return "admin/languageDetails";
	}

	
	/*
	 * ADD FOR
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("admin-languages-addDetails")
	public @ResponseBody JsonResponse<Object> addLanguageDetails(Model model, @RequestBody CityLanguageWebModel adminModel,
			HttpSession session) {
		logger.info("Method : addLanguageDetails starts");
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "addLanguageDetails", adminModel, JsonResponse.class);
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
		System.out.println("addLanguageDetails" + resp);
		logger.info("Method : addLanguageDetails starts");
		return resp;
	}

	/*
	 * View
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-languages-viewDetails")
	public @ResponseBody List<CityLanguageWebModel> viewLanguageDetails(HttpSession session) {

		logger.info("Method :viewLanguageDetails starts");

		JsonResponse<List<CityLanguageWebModel>> resp = new JsonResponse<List<CityLanguageWebModel>>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

			resp = restTemplate.getForObject(env.getAdminUrl() + "viewLanguageDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<CityLanguageWebModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CityLanguageWebModel>>() {
				});

		resp.setBody(lifeHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("VIEW" + resp.getBody());
		logger.info("Method :viewLanguageDetails ends");
		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("admin-languages-editDetails")
	public @ResponseBody JsonResponse<CityLanguageWebModel> editLanguageDetails(@RequestParam String id,
			HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
		logger.info("Method : editLanguageDetails starts");

		JsonResponse<CityLanguageWebModel> jsonResponse = new JsonResponse<CityLanguageWebModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLanguageDetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		CityLanguageWebModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<CityLanguageWebModel>() {
		});

		jsonResponse.setBody(reimModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : editLanguageDetails ends");
		System.out.println("editLanguageDetails" + jsonResponse);
		return jsonResponse;
	}

	// delete

	@SuppressWarnings("unchecked")
	@PostMapping("admin-languages-delete")
	public @ResponseBody JsonResponse<Object> deleteLanguageDetails(@RequestParam String id, Model model,
			HttpSession session) {
		logger.info("Method : deleteLanguageDetails function starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteLanguageDetails?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteLanguageDetails function Ends");

		System.out.println("RESPPPPPPP" + res);
		return res;
	}
	
	


}