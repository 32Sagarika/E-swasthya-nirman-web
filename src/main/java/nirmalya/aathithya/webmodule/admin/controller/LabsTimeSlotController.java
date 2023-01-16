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
import nirmalya.aathithya.webmodule.admin.model.LabModel;
import nirmalya.aathithya.webmodule.admin.model.LabTestModel;
import nirmalya.aathithya.webmodule.admin.model.LabTimeSlotModel;



@Controller

@RequestMapping(value = { "admin/" })
public class LabsTimeSlotController {
	Logger logger = LoggerFactory.getLogger(LabsTimeSlotController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "lab-timeslot" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :lab starts");
 
		
		logger.info("Method : lab ends");
		return "admin/labtimeslot";
	}
	
	
	/*
	 * ADD FOR
	 */

	
	@SuppressWarnings("unchecked")
	@PostMapping("lab-timeslot-addDetails")
	public @ResponseBody JsonResponse<Object> addLabtimeslotDetails(Model model,
			@RequestBody LabTimeSlotModel labmodel, HttpSession session) {
		logger.info("Method : addLabtimeslotDetails starts");
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
			resp = restTemplate.postForObject(env.getAdminUrl() + "addLabtimeslotDetails", labmodel,
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
		logger.info("Method : addLabtimeslotDetails starts");
		return resp;
	}
	
	
	/*
	 * View patient
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("lab-timeslot-view")
	public @ResponseBody List<LabTimeSlotModel> viewLabTimeslotDetails(HttpSession session) {

		logger.info("Method :viewLabTimeslotDetails starts");

		JsonResponse<List<LabTimeSlotModel>> resp = new JsonResponse<List<LabTimeSlotModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewLabTimeslotDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabTimeSlotModel> labModel = mapper.convertValue(resp.getBody(), new TypeReference<List<LabTimeSlotModel>>() {
		});

		resp.setBody(labModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		//System.out.println("VIEW" + resp.getBody());
		logger.info("Method :viewLabTimeslotDetails ends");
		return resp.getBody();
	}

	//edit 
	
		@SuppressWarnings("unchecked")
		@GetMapping("lab-timeslot-editDetails")
		public @ResponseBody JsonResponse<LabTimeSlotModel> editLabtimeslotDetails(@RequestParam String id,
				HttpSession session) {
			System.out.println("edrtgy/uugfde5wsrdty/ugtfrertyg/u"+id);
			logger.info("Method : editLabtimeslotDetails starts");

			JsonResponse<LabTimeSlotModel> jsonResponse = new JsonResponse<LabTimeSlotModel>();

			try {
				jsonResponse = restTemplate.getForObject(env.getAdminUrl() + "editLabtimeslotDetails?id=" + id,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			ObjectMapper mapper = new ObjectMapper();

			LabTimeSlotModel reimModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<LabTimeSlotModel>() {
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

			logger.info("Method : editLabtimeslotDetails ends");
			System.out.println("editLabtimeslotDetails"+jsonResponse);
			return jsonResponse;
		}
		

	//delete 
	
			@SuppressWarnings("unchecked")
			@PostMapping("lab-timeslot-delete")
			public @ResponseBody JsonResponse<Object> deleteLabTimeslotDetails(@RequestParam String id,
					Model model, HttpSession session) {
				logger.info("Method : deleteLabTimeslotDetails function starts");

				JsonResponse<Object> res = new JsonResponse<Object>();

				JsonResponse<Object> resp = new JsonResponse<Object>();

				try {
					res = restTemplate.getForObject(env.getAdminUrl() + "rest-deleteLabTimeslotDetails?id=" + id  , JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				String message = res.getMessage();
				if (message != null && message != "") {

				} else {
					res.setMessage("Success");
				}
				logger.info("Method : deleteLabTimeslotDetails function Ends");
				
				System.out.println("RESPPPPPPP"+res);
				return res;
			}

	
}
	