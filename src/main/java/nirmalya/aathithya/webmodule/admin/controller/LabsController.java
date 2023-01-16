package nirmalya.aathithya.webmodule.admin.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.admin.model.AdminCategoryModel;
import nirmalya.aathithya.webmodule.admin.model.PatientListingModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.LabModel;


@Controller

@RequestMapping(value = { "admin/" })
public class LabsController {
	Logger logger = LoggerFactory.getLogger(LabsController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "lab-labs" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :lab starts");
 
		
		logger.info("Method : lab ends");
		return "admin/labs";
	}
	
	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("lab-labs-addDetails")
	public @ResponseBody JsonResponse<Object> addLabDetails(Model model,
			@RequestBody LabModel adminModel, HttpSession session) {
		logger.info("Method : addLabDetails starts");
		System.out.println("LabModel"+model);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
      try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
           //adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addLabDetails", adminModel,
					JsonResponse.class);
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
		System.out.println("addLabDetails" + resp);
		logger.info("Method : addLabDetails starts");
		return resp;
	}
	
	/*
	 * View patient 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("lab-labs-viewDetails")
	public @ResponseBody List<LabModel> viewLabDetails(HttpSession session) {

		logger.info("Method :viewLabDetails starts");

		JsonResponse<List<LabModel>> resp = new JsonResponse<List<LabModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewLabDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabModel>  labModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabModel>>() {});
		
		resp.setBody(labModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewLabDetails ends");
		return resp.getBody();
	}
	
	//edit 
	
	@SuppressWarnings("unchecked")
	@GetMapping("lab-labs-editDetails")
	public @ResponseBody JsonResponse<LabModel> editLabDetails(@RequestParam String id,
			HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
		logger.info("Method : editLabDetails starts");

		JsonResponse<LabModel> jsonResponse = new JsonResponse<LabModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLabDetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		LabModel reimModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<LabModel>() {
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

		logger.info("Method : editLabDetails ends");
		System.out.println("editLabDetails"+jsonResponse);
		return jsonResponse;
	}
	
	
	//delete 
	
			@SuppressWarnings("unchecked")
			@PostMapping("lab-labs-delete")
			public @ResponseBody JsonResponse<Object> deleteLabDetails(@RequestParam String id,
					Model model, HttpSession session) {
				logger.info("Method : deleteLabDetails function starts");

				JsonResponse<Object> res = new JsonResponse<Object>();

				JsonResponse<Object> resp = new JsonResponse<Object>();

				try {
					res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteLabDetails?id=" + id  , JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				String message = res.getMessage();
				if (message != null && message != "") {

				} else {
					res.setMessage("Success");
				}
				logger.info("Method : deleteLabDetails function Ends");
				
				System.out.println("RESPPPPPPP"+res);
				return res;
			}

}