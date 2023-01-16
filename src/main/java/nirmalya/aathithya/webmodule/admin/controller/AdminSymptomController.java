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

import nirmalya.aathithya.webmodule.admin.model.AdminSymptomModel;
import nirmalya.aathithya.webmodule.admin.model.AdminSymptomModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class AdminSymptomController {
	Logger logger = LoggerFactory.getLogger(AdminSymptomController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-symptoms" })

	public String adminSymptom(Model model, HttpSession session) {
		logger.info("Method :Symptomss starts");
 
		try {
			DropDownModel[] specialist = restTemplate.getForObject(env.getAdminUrl() + "getspecialityList",
					DropDownModel[].class);
			List<DropDownModel> specialityList = Arrays.asList(specialist);

			model.addAttribute("Specialist", specialityList);
			System.out.println("Specialist"+specialityList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : Symptomss ends");
		return "admin/symptomDetails";
	}
	
	
	@PostMapping("admin-symptoms-image-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("employeePFile", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}
	
	private String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = nowTime + "." + ext;
				}

			}
			
			Path path = Paths.get(env.getDocumentUpload() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}
        logger.info("Path"+path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
		
	}
	
	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-symptoms-addDetails")
	public @ResponseBody JsonResponse<Object> addSymptomDetails(Model model,
			@RequestBody AdminSymptomModel adminModel, HttpSession session) {
		logger.info("Method : addSymptomDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
      try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
      
  	MultipartFile inputFile = (MultipartFile) session.getAttribute("employeePFile");
	byte[] bytes;
	String imageName = null;

	if (inputFile != null) {
		try {
			bytes = inputFile.getBytes();
			String[] fileType = inputFile.getContentType().split("/");
			imageName = saveAllImage(bytes, fileType[1]);
			// System.out.println(imageName);

			adminModel.setSymptomImg(imageName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    //  adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addSymptomDetails", adminModel,
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
		System.out.println("addSymptomDetails" + resp);
		logger.info("Method : addSymptomDetails starts");
		return resp;
	}

	
	
	/*
	 * View 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-symptoms-viewDetails")
	public @ResponseBody List<AdminSymptomModel> viewSymptomDetails(HttpSession session) {

		logger.info("Method :viewSymptomDetails starts");

		JsonResponse<List<AdminSymptomModel>> resp = new JsonResponse<List<AdminSymptomModel>>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

			resp = restTemplate.getForObject(env.getAdminUrl() + "viewSymptomDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AdminSymptomModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AdminSymptomModel>>() {
				});
		
		
		
		resp.setBody(lifeHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewSymptomDetails ends");
		return resp.getBody();
	}
	@SuppressWarnings("unchecked")
	@GetMapping("admin-symptoms-editDetails")
	public @ResponseBody JsonResponse<AdminSymptomModel> editSymptomDetails(@RequestParam String id,
			HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
		logger.info("Method : editSymptomDetails starts");

		JsonResponse<AdminSymptomModel> jsonResponse = new JsonResponse<AdminSymptomModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editSymptomDetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		AdminSymptomModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<AdminSymptomModel>() {
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

		logger.info("Method : editSymptomDetails ends");
		System.out.println("editSymptomDetails" + jsonResponse);
		return jsonResponse;
	}

	// delete

		@SuppressWarnings("unchecked")
		@PostMapping("admin-symptoms-delete")
		public @ResponseBody JsonResponse<Object> deleteAdminSymptoms(@RequestParam String id, Model model,
				HttpSession session) {
			logger.info("Method : deleteAdminSymptoms function starts");

			JsonResponse<Object> res = new JsonResponse<Object>();

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteAdminSymptoms?id=" + id, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = res.getMessage();
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			logger.info("Method : deleteAdminSymptoms function Ends");

			System.out.println("RESPPPPPPP" + res);
			return res;
		}
}