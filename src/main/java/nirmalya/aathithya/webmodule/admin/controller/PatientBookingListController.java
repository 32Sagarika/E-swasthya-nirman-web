package nirmalya.aathithya.webmodule.admin.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.admin.model.PatientListingModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class PatientBookingListController {
	Logger logger = LoggerFactory.getLogger(PatientBookingListController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-patientBooking" })

	public String adminbni(Model model, HttpSession session) {
		logger.info("Method :adminpatient starts");
 
		
		logger.info("Method : adminpatient ends");
		return "admin/patientBookingList";
	}
	
	/*
	 * View patient booking list
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-patientBooking-viewDetails")
	public @ResponseBody List<PatientListingModel> viewPatientBookingDetails(HttpSession session) {

		logger.info("Method :viewPatientBookingDetails starts");

		JsonResponse<List<PatientListingModel>> resp = new JsonResponse<List<PatientListingModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewPatientBookingDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PatientListingModel>  patientListingModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<PatientListingModel>>() {
				});
		
		resp.setBody(patientListingModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
			System.out.println("VIEW"+resp.getBody());
		logger.info("Method :viewPatientBookingDetails ends");
		return resp.getBody();
	}
}
	