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

	import nirmalya.aathithya.webmodule.admin.model.AdminLocationModel;
	import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
	import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
	import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
	import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

	@Controller

	@RequestMapping(value = { "admin/" })
	public class AdminLocationController {
		Logger logger = LoggerFactory.getLogger(AdminLocationController.class);

		@Autowired
		RestTemplate restTemplate;

		@Autowired
		EnvironmentVaribles env;

		@GetMapping(value = { "admin-location" })

		public String adminbn(Model model, HttpSession session) {
			logger.info("Method :location starts");

			logger.info("Method : location ends");
			return "admin/adminLocation";
		}

		
		/*
		 * ADD FOR
		 */

		@SuppressWarnings("unchecked")
		@PostMapping("admin-location-addDetails")
		public @ResponseBody JsonResponse<Object> addLocationDetails(Model model, @RequestBody AdminLocationModel adminModel,
				HttpSession session) {
			logger.info("Method : addLocationDetails starts");
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
				resp = restTemplate.postForObject(env.getAdminUrl() + "addLocationDetails", adminModel, JsonResponse.class);
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
			System.out.println("addLocationDetails" + resp);
			logger.info("Method : addLocationDetails starts");
			return resp;
		}

		/*
		 * View
		 */

		@SuppressWarnings("unchecked")
		@GetMapping("admin-location-viewDetails")
		public @ResponseBody List<AdminLocationModel> viewLocationDetails(HttpSession session) {

			logger.info("Method :viewLocationDetails starts");

			JsonResponse<List<AdminLocationModel>> resp = new JsonResponse<List<AdminLocationModel>>();
			String userId = "";
			String dateFormat = "";

			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat = (String) session.getAttribute("DATEFORMAT");

				resp = restTemplate.getForObject(env.getAdminUrl() + "viewLocationDetails", JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<AdminLocationModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<AdminLocationModel>>() {
					});

			resp.setBody(lifeHistoryModel);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			System.out.println("VIEW" + resp.getBody());
			logger.info("Method :viewLocationDetails ends");
			return resp.getBody();
		}

		@SuppressWarnings("unchecked")
		@GetMapping("admin-location-editDetails")
		public @ResponseBody JsonResponse<AdminLocationModel> editLocationDetails(@RequestParam String id,
				HttpSession session) {
			System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
			logger.info("Method : editLocationDetails starts");

			JsonResponse<AdminLocationModel> jsonResponse = new JsonResponse<AdminLocationModel>();

			try {
				jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLocationDetails?id=" + id,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			ObjectMapper mapper = new ObjectMapper();

			AdminLocationModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<AdminLocationModel>() {
			});

			jsonResponse.setBody(reimModel);
			if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
				jsonResponse.setCode(jsonResponse.getMessage());
				jsonResponse.setMessage("Unsuccess");

			} else {
				jsonResponse.setMessage("Success");
			}

			logger.info("Method : editLocationDetails ends");
			System.out.println("editLocationDetails" + jsonResponse);
			return jsonResponse;
		}

		// delete

		@SuppressWarnings("unchecked")
		@PostMapping("admin-location-delete")
		public @ResponseBody JsonResponse<Object> LocationDetails(@RequestParam String id, Model model,
				HttpSession session) {
			logger.info("Method : LocationDetails function starts");

			JsonResponse<Object> res = new JsonResponse<Object>();

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "rest-LocationDetails?id=" + id, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = res.getMessage();
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			logger.info("Method : LocationDetails function Ends");

			System.out.println("RESPPPPPPP" + res);
			return res;
		}
		
		/*
		 *  autoSearch
		 */
		
		@SuppressWarnings("unchecked")
		@PostMapping(value = { "admin-location-autosearch-city" })
		public @ResponseBody JsonResponse<AdminLocationModel> getAutoSearchList(Model model,
				@RequestBody String searchValue, BindingResult result) {
			logger.info("Method : getNoticeAutoSearchList starts");
			JsonResponse<AdminLocationModel> res = new JsonResponse<AdminLocationModel>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "getAutoSearchList?id=" + searchValue,
						JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() != null) {

				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}
			logger.info("Method : getAutoSearchList ends");
			return res;
		}


}
