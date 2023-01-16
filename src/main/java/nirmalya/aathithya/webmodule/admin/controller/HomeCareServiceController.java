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
import nirmalya.aathithya.webmodule.admin.model.HomeCareServiceModel;
import nirmalya.aathithya.webmodule.admin.model.LabTimeSlotModel;



@Controller

@RequestMapping(value = { "admin/" })
public class HomeCareServiceController {
	Logger logger = LoggerFactory.getLogger(HomeCareServiceController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-homeCareServices" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :homeCareService starts");
 
		
		logger.info("Method : homeCareService ends");
		return "admin/homecareservice";
	}
	
	

	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareServices-addDetails")
	public @ResponseBody JsonResponse<Object> addHomecareserviceDetails(Model model,
			@RequestBody HomeCareServiceModel servicemodel, HttpSession session) {
		logger.info("Method : addHomecareserviceDetails starts");
		//System.out.println("LabTimeSlotModel"+LabTimeSlotModel);
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "addHomecareserviceDetails", servicemodel,
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
		//System.out.println("addLabtimeslotDetails" + resp);
		logger.info("Method : addHomecareserviceDetails starts");
		return resp;
	}
	
	
	/*
	 * View
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("admin-homeCareServices-view")
	public @ResponseBody List<HomeCareServiceModel> viewHomecareserviceDetails(HttpSession session) {

		logger.info("Method :viewHomecareserviceDetails starts");

		JsonResponse<List<HomeCareServiceModel>> resp = new JsonResponse<List<HomeCareServiceModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomecareserviceDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomeCareServiceModel> servicemodel = mapper.convertValue(resp.getBody(), new TypeReference<List<HomeCareServiceModel>>() {
		});

		resp.setBody(servicemodel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("VIEW" + resp.getBody());
		logger.info("Method :viewHomecareserviceDetails ends");
		return resp.getBody();
	}
	
	       //edit 
	
			@SuppressWarnings("unchecked")
			@GetMapping("admin-homeCareServices-editDetails")
			public @ResponseBody JsonResponse<HomeCareServiceModel> editHomeCareServiceDetails(@RequestParam String id,
					HttpSession session) {
				System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
				logger.info("Method : editHomeCareServiceDetails starts");

				JsonResponse<HomeCareServiceModel> jsonResponse = new JsonResponse<HomeCareServiceModel>();

				try {
					jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editHomeCareServiceDetails?id=" + id,
							JsonResponse.class);

				} catch (RestClientException e) {
					e.printStackTrace();
				}

				ObjectMapper mapper = new ObjectMapper();

				HomeCareServiceModel reimModel = mapper.convertValue(jsonResponse.getBody(),
						new TypeReference<HomeCareServiceModel>() {
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

				logger.info("Method : editHomeCareServiceDetails ends");
				System.out.println("editHomeCareServiceDetails"+jsonResponse);
				return jsonResponse;
			}
			
	
	
	//delete 
	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareServices-delete")
	public @ResponseBody JsonResponse<Object> deleteHomecareServiceDetails(@RequestParam String id,
			Model model, HttpSession session) {
		logger.info("Method : deleteHomecareServiceDetails function starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteHomecareServiceDetails?id=" + id  , JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteHomecareServiceDetails function Ends");
		
		System.out.println("RESPPPPPPP"+res);
		return res;
	}
	
	
	
}