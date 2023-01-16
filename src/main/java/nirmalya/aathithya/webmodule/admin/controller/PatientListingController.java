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

import nirmalya.aathithya.webmodule.admin.model.DoctorListingModel;
import nirmalya.aathithya.webmodule.admin.model.PatientListingModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class PatientListingController {
	Logger logger = LoggerFactory.getLogger(PatientListingController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-patient" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :adminpatient starts");
 
		
		logger.info("Method : adminpatient ends");
		return "admin/patientlisting";
	}
	
	/*
	 * View patient 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("admin-patient-viewDetails")
	public @ResponseBody List<PatientListingModel> viewPatientDetails(HttpSession session) {

		logger.info("Method :viewPatientDetails starts");

		JsonResponse<List<PatientListingModel>> resp = new JsonResponse<List<PatientListingModel>>();
		String userId = "";
	try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewPatientDetails", JsonResponse.class);
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
		logger.info("Method :viewPatientDetails ends");
		return resp.getBody();
	}
}
	