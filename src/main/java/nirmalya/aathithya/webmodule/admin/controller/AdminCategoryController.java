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

import nirmalya.aathithya.webmodule.admin.model.AdminBannerModel;
import nirmalya.aathithya.webmodule.admin.model.AdminCategoryModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class AdminCategoryController {
	Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-category" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :Category starts");
 
		
		logger.info("Method : category ends");
		return "admin/categoryDetails";
	}
	
	
	@PostMapping("admin-category-image-upload-file")
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
	
	@SuppressWarnings("unused")
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
	@PostMapping("admin-category-addDetails")
	public @ResponseBody JsonResponse<Object> addCategoryDetails(Model model,
			@RequestBody AdminCategoryModel adminModel, HttpSession session) {
		logger.info("Method : addCategoryDetails starts");
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

			adminModel.setImgCategory(imageName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    //  adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addCategoryDetails", adminModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		/*
		 * if (resp.getCode().equals("success")) { resp.setMessage("Success");
		 * session.removeAttribute("employeePFile"); } else {
		 * 
		 * }
		 */
		
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
       // resp.setBody(body);
		System.out.println("addCategoryDetails" + resp);
		logger.info("Method : addCategoryDetails starts");
		return resp;
	}

	
	
	/*
	 * View patient immunization
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-category-viewDetails")
	public @ResponseBody List<AdminCategoryModel> viewCategoryDetails(HttpSession session) {

		logger.info("Method :viewCategoryDetails starts");

		JsonResponse<List<AdminCategoryModel>> resp = new JsonResponse<List<AdminCategoryModel>>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

			resp = restTemplate.getForObject(env.getAdminUrl() + "viewCategoryDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AdminCategoryModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AdminCategoryModel>>() {
				});
	
		
		resp.setBody(lifeHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewCategoryDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-category-editDetails")
	public @ResponseBody JsonResponse<AdminCategoryModel> editCategoryDetails(@RequestParam String id,
			HttpSession session) {
		System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
		logger.info("Method : editCategoryDetails starts");

		JsonResponse<AdminCategoryModel> jsonResponse = new JsonResponse<AdminCategoryModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editCategoryDetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		AdminCategoryModel reimModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<AdminCategoryModel>() {
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

		logger.info("Method : editCategoryDetails ends");
		System.out.println("editCategoryDetails"+jsonResponse);
		return jsonResponse;
	}
	
	//delete 
	
		@SuppressWarnings("unchecked")
		@PostMapping("admin-category-delete")
		public @ResponseBody JsonResponse<Object> deleteAdminCategory(@RequestParam String id,
				Model model, HttpSession session) {
			logger.info("Method : deleteAdminCategory function starts");

			JsonResponse<Object> res = new JsonResponse<Object>();

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteAdminCategory?id=" + id  , JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = res.getMessage();
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			logger.info("Method : deleteAdminCategory function Ends");
			
			System.out.println("RESPPPPPPP"+res);
			return res;
		}
}