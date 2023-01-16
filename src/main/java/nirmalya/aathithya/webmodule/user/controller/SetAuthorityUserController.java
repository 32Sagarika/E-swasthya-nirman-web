package nirmalya.aathithya.webmodule.user.controller;

import java.util.Arrays;
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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.ProcessMasterModel;
import nirmalya.aathithya.webmodule.user.model.UserSetAuthority;

/**
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = "user")
public class SetAuthorityUserController {

	Logger logger = LoggerFactory.getLogger(SetAuthorityUserController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;
	
	@GetMapping(value = { "manage-authority" })
	public String manageAuthority(Model model, HttpSession session) {
		logger.info("Method : manageAuthority starts");
		
		try {
			DropDownModel[] dd = restClient.getForObject(env.getUserUrl() + "getUserRoleDropDown", DropDownModel[].class);
			List<DropDownModel> roleList = Arrays.asList(dd);
			
			model.addAttribute("roleList", roleList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : manageAuthority ends");
		return "user/manageAuthority";
	}
	
	@SuppressWarnings({ "unchecked" })
	@GetMapping(value = { "manage-authority-get-process-listing" })
	public @ResponseBody List<ProcessMasterModel> getUsersList(Model model, HttpSession session) {
		logger.info("Method : getUsersList starts");
		
		String dateFormat = "";

		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<ProcessMasterModel>> res = new JsonResponse<List<ProcessMasterModel>>();

		try {
			res = restClient.getForObject(env.getUserUrl() + "getProcessMasterList", JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ProcessMasterModel> dataList = mapper.convertValue(res.getBody(),
					new TypeReference<List<ProcessMasterModel>>() {
					});

			for (ProcessMasterModel m : dataList) {
				if (m.gettProcessStatus()) {
					m.setStatus("Active");
				} else {
					m.setStatus("Inactive");
				}

				if (m.gettProcessCreatedBy() == null || m.gettProcessCreatedBy() == "") {
					m.settProcessCreatedBy("SYSTEM");
				}
				
				m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
			}
			res.setBody(dataList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getUsersList ends");
		return res.getBody();
	}
	
	@SuppressWarnings({ "unchecked" })
	@GetMapping(value = { "manage-authority-get-authority-list" })
	public @ResponseBody List<UserSetAuthority> getUserAuthorityList(@RequestParam String id, Model model, HttpSession session) {
		logger.info("Method : getUserAuthorityList starts");
		
		String dateFormat = "";
		
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonResponse<List<UserSetAuthority>> res = new JsonResponse<List<UserSetAuthority>>();
		
		try {
			res = restClient.getForObject(env.getUserUrl() + "getUserAuthorityList?id="+id, JsonResponse.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<UserSetAuthority> dataList = mapper.convertValue(res.getBody(),
					new TypeReference<List<UserSetAuthority>>() {
			});
			
			for (UserSetAuthority m : dataList) {
				if (m.getAuthStatus().contentEquals("true") || m.getAuthStatus().contentEquals("1")) {
					m.setAuthStatus("Active");
				} else {
					m.setAuthStatus("Inactive");
				}
				
				if (m.getCreatedBy() == null || m.getCreatedBy() == "") {
					m.setCreatedBy("SYSTEM");
				}
				
				m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
			}
			res.setBody(dataList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getUserAuthorityList ends");
		return res.getBody();
	}
	
	@SuppressWarnings({ "unchecked" })
	@GetMapping(value = { "manage-authority-get-emp-listing-by-role" })
	public @ResponseBody List<DropDownModel> getEmployeeListByRole(@RequestParam String id, Model model, HttpSession session) {
		logger.info("Method : getEmployeeListByRole starts");
		
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();
		
		try {
			res = restClient.getForObject(env.getUserUrl() + "getEmployeeListByRole?id="+id, JsonResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getEmployeeListByRole ends");
		return res.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-authority-save-data")
	public @ResponseBody JsonResponse<Object> saveProcessMaster(@RequestBody ProcessMasterModel data, HttpSession session) {
		logger.info("Method : saveProcessMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.settProcessCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getUserUrl() + "saveProcessMaster", data, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : saveProcessMaster ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-authority-save-authority-data")
	public @ResponseBody JsonResponse<Object> saveAuthorityMasterData(@RequestBody UserSetAuthority data, HttpSession session) {
		logger.info("Method : saveAuthorityMasterData starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.setCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getUserUrl() + "saveAuthorityMasterData", data, JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : saveAuthorityMasterData ends");
		return resp;
	}
}
