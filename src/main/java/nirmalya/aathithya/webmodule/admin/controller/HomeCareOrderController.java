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

import nirmalya.aathithya.webmodule.admin.model.AdminDashboardModel;
import nirmalya.aathithya.webmodule.admin.model.DoctorListingModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.HomeCareOrderModel;
import nirmalya.aathithya.webmodule.admin.model.LabtestOrderModel;

@Controller

@RequestMapping(value = { "admin/" })
public class HomeCareOrderController {
	Logger logger = LoggerFactory.getLogger(HomeCareOrderController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-homeCareOrders" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :homecareservices starts");
 
		
		try {
			DropDownModel[] specialist = restTemplate.getForObject(env.getAdminUrl() + "getHomeCareServiceList",
					DropDownModel[].class);
			List<DropDownModel> homeCareServiceList = Arrays.asList(specialist);

			model.addAttribute("HomeCareServiceList", homeCareServiceList);
			System.out.println("HomeCareServiceList"+homeCareServiceList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : homecareservices ends");
		return "admin/homecareorder";
	}
	
	
	//dashboard count controller
	
	
		@SuppressWarnings("unchecked")
		@GetMapping(value = { "admin-homeCareOrders-CountList" })
		public @ResponseBody JsonResponse<List<HomeCareOrderModel>> gethomeCareOrdersCountDetails(Model model, HttpSession session) {

			logger.info("Method : gethomeCareOrdersCountDetails starts");
		
			JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				resp = restTemplate.getForObject(env.getAdminUrl() + "gethomeCareOrdersCountDetails", JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<HomeCareOrderModel>  adminDashboardModel= mapper.convertValue(resp.getBody(),
					new TypeReference<List<HomeCareOrderModel>>() {
					});
			
			resp.setBody(adminDashboardModel);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			System.out.println("VIEW"+resp.getBody());
			logger.info("Method :gethomeCareOrdersCountDetails ends");
			return resp;
		}
		
	
	/*
	 * View Home Care Order
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-viewDetails")
	public @ResponseBody List<HomeCareOrderModel> viewHomeCareOrderDetails(HttpSession session,@RequestParam String id) {

		logger.info("Method :viewHomeCareOrderDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomeCareOrderDetails?id=" + id, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewHomeCareOrderDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-assignviewDetails")
	public @ResponseBody List<HomeCareOrderModel> viewHomeCareOrderassignDetails(HttpSession session) {

		logger.info("Method :viewHomeCareOrderassignDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomeCareOrderassignDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewHomeCareOrderassignDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-acceptviewDetails")
	public @ResponseBody List<HomeCareOrderModel> viewHomeCareOrderAcceptDetails(HttpSession session) {

		logger.info("Method :viewHomeCareOrderAcceptDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomeCareOrderAcceptDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewHomeCareOrderAcceptDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-viewHomeServicePartnerDetails")
	public @ResponseBody List<HomeCareOrderModel> viewHomeServicePartnerDetails(HttpSession session) {

		logger.info("Method :viewHomeServicePartnerDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomeServicePartnerDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewHomeServicePartnerDetails ends");
		return resp.getBody();
	}
	
	@PostMapping("admin-homeCareOrders-uploadDocument")
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
	@PostMapping("admin-homeCareOrders-AcceptServicePartner")
	public @ResponseBody JsonResponse<Object> AcceptServicePartner(Model model, @RequestBody HomeCareOrderModel adminModel,
			HttpSession session) {
		logger.info("Method : AcceptServicePartner starts");
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

				adminModel.setServiceDocumentUpload(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "rest-AcceptServicePartner", adminModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("AcceptServicePartner" + resp);
		logger.info("Method : AcceptServicePartner starts");
		return resp;
	} 
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-RejectviewDetails")
	public @ResponseBody List<HomeCareOrderModel> viewRejectviewDetails(HttpSession session) {

		logger.info("Method :viewRejectviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "RejectviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewRejectviewDetails ends");
		return resp.getBody();
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-DocumentationviewDetails")
	public @ResponseBody List<HomeCareOrderModel> viewDocumentationviewDetails(HttpSession session) {

		logger.info("Method :viewDocumentationviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "DocumentationviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewDocumentationviewDetails ends");
		return resp.getBody();
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareOrders-AssignServicePartner")
	public @ResponseBody JsonResponse<Object> AssignServicePartner(HttpSession session,
			@RequestBody HomeCareOrderModel adminModel) {
		logger.info("Method : AssignServicePartner starts"+adminModel);
	JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "rest-AssignServicePartner", adminModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			List<HomeCareOrderModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<HomeCareOrderModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");

		}
		logger.info("Method : AssignServicePartner ends"+resp);

		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-InProgressviewDetails")
	public @ResponseBody List<HomeCareOrderModel> InProgressviewDetails(HttpSession session) {

		logger.info("Method :InProgressviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "InProgressviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :InProgressviewDetails ends");
		return resp.getBody();
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-FeedBackviewDetails")
	public @ResponseBody List<HomeCareOrderModel> FeedBackviewDetails(HttpSession session) {

		logger.info("Method :FeedBackviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "FeedBackviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :FeedBackviewDetails ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareOrders-ServiceCompleted")
	public @ResponseBody JsonResponse<Object> updateServiceCompleted(Model model, @RequestBody HomeCareOrderModel adminModel,
			HttpSession session) {
		logger.info("Method : updateServiceCompleted starts");
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "rest-updateServiceCompleted", adminModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("updateServiceCompleted" + resp);
		logger.info("Method : updateServiceCompleted starts");
		return resp;
	} 
	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareOrders-ServiceClosed")
	public @ResponseBody JsonResponse<Object> updateServiceClosed(Model model, @RequestBody HomeCareOrderModel adminModel,
			HttpSession session) {
		logger.info("Method : updateServiceClosed starts");
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "rest-updateServiceClosed", adminModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("updateServiceClosed" + resp);
		logger.info("Method : updateServiceClosed starts");
		return resp;
	} 
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-ViewServiceCompletedDetails")
	public @ResponseBody List<HomeCareOrderModel> serviceCompletedviewDetails(HttpSession session) {

		logger.info("Method :serviceCompletedviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "serviceCompletedviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :serviceCompletedviewDetails ends");
		return resp.getBody();
	}
	@SuppressWarnings("unchecked")
	@GetMapping("admin-homeCareOrders-serviceClosedviewDetails")
	public @ResponseBody List<HomeCareOrderModel> serviceClosedviewDetails(HttpSession session) {

		logger.info("Method :serviceClosedviewDetails starts");

		JsonResponse<List<HomeCareOrderModel>> resp = new JsonResponse<List<HomeCareOrderModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "serviceClosedviewDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareOrderModel>  homeCareOrderModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<HomeCareOrderModel>>() {
				});
		
		resp.setBody(homeCareOrderModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :serviceClosedviewDetails ends");
		return resp.getBody();
	}
	
	
}