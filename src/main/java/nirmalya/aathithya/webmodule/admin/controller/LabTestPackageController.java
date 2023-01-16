package nirmalya.aathithya.webmodule.admin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import nirmalya.aathithya.webmodule.admin.model.AdminCategoryModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.LabTestPackageModel;
import nirmalya.aathithya.webmodule.admin.model.LabTimeSlotModel;



@Controller

@RequestMapping(value = { "admin/" })
public class LabTestPackageController {
	Logger logger = LoggerFactory.getLogger(LabTestPackageController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "lab-labtestpackage" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :labtestpackage starts");
 
		
		logger.info("Method : labtestpackage ends");
		return "admin/labtestpackage";
	}
	
	
	//upload document
	
	@PostMapping("lab-labtestpackage-image-upload-file")
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
	
	//lab-labtestpackage-add 
	

	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("lab-labtestpackage-add")
	public @ResponseBody JsonResponse<Object> addlabtestpackageDetails(Model model,
			@RequestBody LabTestPackageModel adminModel, HttpSession session) {
		logger.info("Method : addlabtestpackageDetails starts");
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
			System.out.println(imageName);

			adminModel.setImgCategory(imageName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    //  adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addlabtestpackageDetails", adminModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		  if (resp.getCode().equals("success")) {
		  resp.setMessage("Success");
		  session.removeAttribute("employeePFile"); 
		  }
		
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
         //resp.setBody(body);
		System.out.println("addlabtestpackageDetails" + resp);
		logger.info("Method : addlabtestpackageDetails starts");
		return resp;
	}
	
	
	
	/*
	 * View patient immunization
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("lab-labtestpackage-view")
	public @ResponseBody List<LabTestPackageModel> viewLabTestPackageDetails(HttpSession session) {

		logger.info("Method :viewLabTestPackageDetails starts");

		JsonResponse<List<LabTestPackageModel>> resp = new JsonResponse<List<LabTestPackageModel>>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

			resp = restTemplate.getForObject(env.getAdminUrl() + "viewLabTestPackageDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabTestPackageModel> lifeHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabTestPackageModel>>() {
				});
	
		
		resp.setBody(lifeHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			//System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewLabTestPackageDetails ends");
		return resp.getBody();
	}
	
	
	//edit 
	
			@SuppressWarnings("unchecked")
			@GetMapping("lab-labtestpackage-editDetails")
			public @ResponseBody JsonResponse<LabTestPackageModel> editLabtestpackageDetails(@RequestParam String id,
					HttpSession session) {
				System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
				logger.info("Method : editLabtestpackageDetails starts");

				JsonResponse<LabTestPackageModel> jsonResponse = new JsonResponse<LabTestPackageModel>();

				try {
					jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLabtestpackageDetails?id=" + id,
							JsonResponse.class);

				} catch (RestClientException e) {
					e.printStackTrace();
				}

				ObjectMapper mapper = new ObjectMapper();

				LabTestPackageModel reimModel = mapper.convertValue(jsonResponse.getBody(),
						new TypeReference<LabTestPackageModel>() {
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

				logger.info("Method : editLabtestpackageDetails ends");
				System.out.println("editLabtestpackageDetails"+jsonResponse);
				return jsonResponse;
			}
			


	

	//delete 
	
			@SuppressWarnings("unchecked")
			@PostMapping("lab-labtestpackage-delete")
			public @ResponseBody JsonResponse<Object> deleteLabTestpackageDetails(@RequestParam String id,
					Model model, HttpSession session) {
				logger.info("Method : deleteLabTestpackageDetails function starts");

				JsonResponse<Object> res = new JsonResponse<Object>();

				JsonResponse<Object> resp = new JsonResponse<Object>();

				try {
					res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteLabTestpackageDetails?id=" + id  , JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				String message = res.getMessage();
				if (message != null && message != "") {

				} else {
					res.setMessage("Success");
				}
				logger.info("Method : deleteLabTestpackageDetails function Ends");
				
				//System.out.println("RESPPPPPPP"+res);
				return res;
			}

	
	
}