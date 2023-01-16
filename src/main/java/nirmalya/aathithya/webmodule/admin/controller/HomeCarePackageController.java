package nirmalya.aathithya.webmodule.admin.controller;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.admin.model.HomecarepackageModel;
import nirmalya.aathithya.webmodule.admin.model.LabTimeSlotModel;




@Controller

@RequestMapping(value = { "admin/" })
public class HomeCarePackageController {
	Logger logger = LoggerFactory.getLogger(HomeCarePackageController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-homeCareServicePartner" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :homecarepackage starts");
 
		
		logger.info("Method : homecarepackage ends");
		return "admin/homecareServicePartner";
	}
	
	/*
	 *  autoSearch
	 */
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping(value = { "homecare-homecareservice-autosearch-serviceName" })
	 * public @ResponseBody JsonResponse<HomecarepackageModel>
	 * getAutoSearchList(Model model,
	 * 
	 * @RequestBody String searchValue, BindingResult result) {
	 * logger.info("Method : getNoticeAutoSearchList starts");
	 * JsonResponse<HomecarepackageModel> res = new
	 * JsonResponse<HomecarepackageModel>();
	 * 
	 * try { res = restTemplate.getForObject(env.getAdminUrl() +
	 * "getAutoSearchListData?id=" + searchValue, JsonResponse.class); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * if (res.getMessage() != null) {
	 * 
	 * res.setCode(res.getMessage()); res.setMessage("Unsuccess"); } else {
	 * res.setMessage("success"); } logger.info("Method : getAutoSearchList ends");
	 * return res; }
	 */
	
	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("admin-homeCareServicePartner-addDetails")
	public @ResponseBody JsonResponse<Object> addHomecarepackageDetails(Model model,
			@RequestBody HomecarepackageModel servicemodel, HttpSession session) {
		logger.info("Method : addLabtimeslotDetails starts");
		//System.out.println("LabTimeSlotModel"+LabTimeSlotModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		//String dateFormat = "";
      try {
			userId = (String) session.getAttribute("USER_ID");
			//dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
           //adminModel.setAdminId(userId);

		try {
			resp = restTemplate.postForObject(env.getAdminUrl() + "addhomeCareServicePartner", servicemodel,
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
		logger.info("Method : addHomecarepackageDetails starts");
		return resp;
	}
	
	/*
	 * View homecares package
	 */ 

	@SuppressWarnings("unchecked")

	@GetMapping("admin-homeCareServicePartner-view")
	public @ResponseBody List<HomecarepackageModel> viewHomeCarePackageDetails(HttpSession session) {

		logger.info("Method :viewHomeCarePackageDetails starts");

		JsonResponse<List<HomecarepackageModel>> resp = new JsonResponse<List<HomecarepackageModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewHomeCareServicePartner", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HomecarepackageModel> serviceModel = mapper.convertValue(resp.getBody(), new TypeReference<List<HomecarepackageModel>>() {
		});

		resp.setBody(serviceModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		//System.out.println("VIEW" + resp.getBody());
		logger.info("Method :viewHomeCarePackageDetails ends");
		return resp.getBody();
	}
	
	
	   //edit 
	
				@SuppressWarnings("unchecked")
				@GetMapping("admin-homeCareServicePartner-editDetails")
				public @ResponseBody JsonResponse<HomecarepackageModel> editHomeCarePartnerDetails(@RequestParam String id,
						HttpSession session) {
					System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
					logger.info("Method : editHomeCarePartnerDetails starts");

					JsonResponse<HomecarepackageModel> jsonResponse = new JsonResponse<HomecarepackageModel>();

					try {
						jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editHomeCarePartnerDetails?id=" + id,
								JsonResponse.class);

					} catch (RestClientException e) {
						e.printStackTrace();
					}

					ObjectMapper mapper = new ObjectMapper();

					HomecarepackageModel reimModel = mapper.convertValue(jsonResponse.getBody(),
							new TypeReference<HomecarepackageModel>() {
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

					logger.info("Method : editHomeCarePartnerDetails ends");
					System.out.println("editHomeCarePartnerDetails"+jsonResponse);
					return jsonResponse;
				}
				
		
		
	
	
	//delete 
	
		@SuppressWarnings("unchecked")
		@PostMapping("admin-homeCareServicePartner-delete")
		public @ResponseBody JsonResponse<Object> deletehomeCareServicePartner(@RequestParam String id,
				Model model, HttpSession session) {
			logger.info("Method : deleteHomecarePackageDetails function starts");

			JsonResponse<Object> res = new JsonResponse<Object>();

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				res = restTemplate.getForObject(env.getAdminUrl() + "rest-deletehomeCareServicePartner?id=" + id  , JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = res.getMessage();
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			logger.info("Method : deletehomeCareServicePartner function Ends");
			
			//System.out.println("RESPPPPPPP"+res);
			return res;
		}
	
}
