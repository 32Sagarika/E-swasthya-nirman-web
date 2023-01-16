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

import nirmalya.aathithya.webmodule.admin.model.AdminDashboardModel;
import nirmalya.aathithya.webmodule.admin.model.PatientListingModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;


@Controller

@RequestMapping(value = { "admin/" })
public class AdminDashboardController {
	Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "admin-dashboard" })

	public String adminbn(Model model, HttpSession session) {
		logger.info("Method :admindashboard starts");
 
		
		logger.info("Method : admindashboard ends");
		return "admin/admindashboard";
	}
	
	//dashboard count controller
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "admin-dashboard-count-details" })
	public @ResponseBody JsonResponse<List<AdminDashboardModel>> getAdminCountDetails(Model model, HttpSession session) {

		logger.info("Method : getAdminCountDetails starts");
	
		JsonResponse<List<AdminDashboardModel>> resp = new JsonResponse<List<AdminDashboardModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getAdminUrl() + "viewDashboardDetails", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AdminDashboardModel>  adminDashboardModel= mapper.convertValue(resp.getBody(),
				new TypeReference<List<AdminDashboardModel>>() {
				});
		
		resp.setBody(adminDashboardModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("VIEW"+resp.getBody());
		logger.info("Method :getAdminCountDetails ends");
		return resp;
	}
	
}