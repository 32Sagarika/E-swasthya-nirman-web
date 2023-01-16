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
import nirmalya.aathithya.webmodule.admin.model.DoctorAddressModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorAvailableTimeModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorBankAccountModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorConsultationFeeModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorListingModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class DoctorListingController {
	Logger logger = LoggerFactory.getLogger(DoctorListingController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-doctor" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :admindoctor starts");
 
		try {
			DropDownModel[] specialist = restTemplate.getForObject(env.getAdminUrl() + "getspecialityList",
					DropDownModel[].class);
			List<DropDownModel> specialityList = Arrays.asList(specialist);

			model.addAttribute("Specialist", specialityList);
			System.out.println("Specialist"+specialityList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] specialist = restTemplate.getForObject(env.getAdminUrl() + "getGenderList",
					DropDownModel[].class);
			List<DropDownModel> getGenderList = Arrays.asList(specialist);

			model.addAttribute("getGenderList", getGenderList);
			System.out.println("getGenderList"+getGenderList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : admindoctor ends");
		return "admin/doctorlisting";
	}
	
	/*
	 * View
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-doctor-viewDetails")
	public @ResponseBody List<DoctorListingModel> viewDoctorDetails(HttpSession session) {

		logger.info("Method :viewDoctorDetails starts");

		JsonResponse<List<DoctorListingModel>> resp = new JsonResponse<List<DoctorListingModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewDoctorDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<DoctorListingModel>  doctorListingModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<DoctorListingModel>>() {
				});
		
		resp.setBody(doctorListingModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewDoctorDetails ends");
		return resp.getBody();
	}
	
	//method to save data 
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping(value = "/admin-doctor-addDocDetails") public @ResponseBody
	 * JsonResponse<Object> addDocDetails(@RequestBody DoctorListingModel
	 * doctorListingModel, Model model, HttpSession session) {
	 * logger.info("Method :addDocDetails starts"); JsonResponse<Object>
	 * jsonResponse = new JsonResponse<Object>(); String userId = ""; String
	 * dateFormat = "";
	 * 
	 * try { userId = (String) session.getAttribute("USER_ID"); dateFormat =(String)
	 * session.getAttribute("DATEFORMAT");
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * List<DoctorAddressModel> address = doctorListingModel.getAddress();
	 * List<DoctorConsultationFeeModel> consult = doctorListingModel.getConsult();
	 * List<DoctorAvailableTimeModel> avail = doctorListingModel.getAvail();
	 * List<DoctorBankAccountModel> bank = doctorListingModel.getBank();
	 * 
	 * 
	 * // doctorListingModel.setEventCreatedBy(userId);
	 * 
	 * try { jsonResponse = restTemplate.postForObject(env.getAdminUrl() +
	 * "rest-addDocDetails", doctorListingModel, JsonResponse.class); } catch
	 * (RestClientException e) { e.printStackTrace(); } String message =
	 * jsonResponse.getMessage(); if (message != null && message != "") {
	 * 
	 * } else { jsonResponse.setMessage("Success"); }
	 * logger.info("Method : addDocDetails ends"); return jsonResponse; }
	 */
	
	
	@PostMapping("admin-doctor-image-upload-file")
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
			logger.info("Path" + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;

	}
		@SuppressWarnings("unchecked")

		@PostMapping("admin-doctor-addDocDetails")
		public @ResponseBody JsonResponse<Object> addDocDetails(@RequestBody DoctorListingModel doctorListingModel,
				HttpSession session) {
			logger.info("Method : addDocDetails starts"+doctorListingModel);

			JsonResponse<Object> resp = new JsonResponse<Object>();

			String userId = "";
			String dateFormat = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat =(String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (doctorListingModel.getDocPYear() != null && doctorListingModel.getDocPYear() != "") {
				doctorListingModel.setDocPYear(DateFormatter.inputDateFormat(doctorListingModel.getDocPYear(), dateFormat));
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

					doctorListingModel.setDocImg(imageName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			//doctorListingModel.setVleid(userId);
			
			try {

				resp = restTemplate.postForObject(env.getAdminUrl() + "rest-addDocDetails", doctorListingModel, JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<DoctorListingModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<DoctorListingModel>>() {
					});

			resp.setBody(seat);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}

			logger.info("Method : addDocDetails ends"+resp);
			return resp;
		}
		
		@SuppressWarnings("unchecked")
		@GetMapping("admin-doctor-editDetails")
		public @ResponseBody JsonResponse<DoctorListingModel> editDocDetails(@RequestParam String id,
				HttpSession session) {
			System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u" + id);
			logger.info("Method : editDocDetails starts");

			JsonResponse<DoctorListingModel> jsonResponse = new JsonResponse<DoctorListingModel>();

			try {
				jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editDocDetails?id=" + id,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			String userId = "";
			String dateFormat = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat =(String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			DoctorListingModel reimModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<DoctorListingModel>() {
			});

			 if (reimModel.getDocPYear() != null && reimModel.getDocPYear() != "") {
			 reimModel.setDocPYear(DateFormatter.dateFormat(reimModel.getDocPYear(), dateFormat));
			 }
			 

			jsonResponse.setBody(reimModel);
			if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
				jsonResponse.setCode(jsonResponse.getMessage());
				jsonResponse.setMessage("Unsuccess");

			} else {
				jsonResponse.setMessage("Success");
			}

			logger.info("Method : editDocDetails ends");
			System.out.println("editDocDetails" + jsonResponse);
			return jsonResponse;
		}

		// delete

		@SuppressWarnings("unchecked")
		@PostMapping("admin-doctor-delete")
		public @ResponseBody JsonResponse<Object> deleteDocDetails(@RequestParam String id, Model model,
				HttpSession session) {
			logger.info("Method : deleteDocDetails function starts");

			JsonResponse<Object> res = new JsonResponse<Object>();

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteDocDetails?id=" + id, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = res.getMessage();
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			logger.info("Method : deleteDocDetails function Ends");

			System.out.println("RESPPPPPPP" + res);
			return res;
		}

}