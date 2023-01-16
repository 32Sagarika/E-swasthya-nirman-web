package nirmalya.aathithya.webmodule.user.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.Activity;
import nirmalya.aathithya.webmodule.user.model.ActivityAvlFunctionModel;
import nirmalya.aathithya.webmodule.user.model.Function;
import nirmalya.aathithya.webmodule.user.model.Module;
import nirmalya.aathithya.webmodule.user.model.User;
import nirmalya.aathithya.webmodule.user.model.UserRolesAndModuleIdModel;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
public class AccessController {

	Logger logger = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRolesAndModuleIdModel userModel;

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("welcome")
	public String welcome(Model model, HttpSession session) {
		logger.info("Method : welcome starts");

		logger.info("Method : welcome ends");
		return "welcome";
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("hrms-dashboard")
	public String hrmsDashboard(Model model, HttpSession session) {
		logger.info("Method : hrmsDashboard starts");

		logger.info("Method : hrmsDashboard ends");
		return "employee/hrms-dashboard";
	}

	@GetMapping("/hrms-index")
	public String hrmsIndex(Model model, HttpSession session) {
		logger.info("Method : hrmsIndex starts");

		logger.info("Method : hrmsIndex ends");
		return "hrms-index";
	}
	

	@GetMapping("/cureEazy-index")
	public String cureEazyIndex(Model model, HttpSession session) {
		logger.info("Method : cureEazy starts");

		logger.info("Method : cureEazy ends");
		return "cureEazy-index";
	}

	/**
	 * Function for home page
	 *
	 */
	

	/**
	 * Function to show register user form
	 *
	 */
	@GetMapping("register")
	public String addUser(Model model, HttpSession session) {
		logger.info("Method : register starts");

		User user = new User();

		User form = (User) session.getAttribute("suser");

		String message = (String) session.getAttribute("message");
		if (message != null && message != "") {
			model.addAttribute("message", message);
		}

		session.setAttribute("message", "");

		if (form != null) {
			form.setUserPassword(null);
			model.addAttribute("user", form);
			session.setAttribute("suser", null);
		} else {
			model.addAttribute("user", user);
		}

		logger.info("Method : register ends");
		return "register";
	}

	/**
	 * Function show login form
	 *
	 */

	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		logger.info("Method : login starts");

		String message = (String) session.getAttribute("loginMessage");

		if (message != null && message != "") {
			model.addAttribute("loginMessage", message);
			session.setAttribute("loginMessage", null);
			
		}
		System.out.println("loginMessage"+model.addAttribute("loginMessage", message));
		logger.info("Method : login ends");
		return "cureEazy_login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		logger.info("Method : / starts");

		logger.info("Method : / ends");
		return "cureEazy_login";
	}
	/**
	 * Function show login form
	 *
	 */

	@GetMapping("/term-and-condition")
	public String termAndCondition(Model model, HttpSession session) {
		logger.info("Method : termAndCondition starts");
 

		logger.info("Method : termAndCondition ends");
		return "term-and-condition.html";
	}
	/**
	 * Function for Forgot Password page
	 *
	 */
	@GetMapping("/forgot-password")
	public String forgotePasword(Model model) {
		logger.info("Method : forgotePasword starts");

		logger.info("Method : forgotePasword ends");
		return "hrmsForgotPwd";
	}

	/**
	 * Function for get otp
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/get-otp")
	public @ResponseBody JsonResponse<Object> getOtp(HttpSession session, @RequestBody DropDownModel dropDownModel) {
		logger.info("Method : getOtp starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			resp = restTemplate.postForObject(env.getApiUrl() + "forgot-password-get-otp", dropDownModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			DropDownModel seat = mapper.convertValue(resp.getBody(), new TypeReference<DropDownModel>() {
			});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getCode() == "" && resp.getCode() == null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");

		}
		logger.info("Method : getOtp ends");

		return resp;
	}

	/**
	 * Function for save new password
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/save-new-password")
	public @ResponseBody JsonResponse<Object> saveNewPassword(HttpSession session,
			@RequestBody DropDownModel dropDownModel) {
		logger.info("Method : saveNewPassword starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {

			resp = restTemplate.postForObject(env.getApiUrl() + "change-password", dropDownModel, JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			DropDownModel seat = mapper.convertValue(resp.getBody(), new TypeReference<DropDownModel>() {
			});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getCode() == "" && resp.getCode() == null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");

		}
		logger.info("Method : saveNewPassword ends");

		return resp;
	}

	/**
	 * Function show index page after login
	 *
	 */

	@GetMapping("access-denied")
	public String accessDenied(Model model, HttpSession session) {
		logger.info("Method : access-denied starts");

		logger.info("Method : access-denied ends");
		return "accessDenied";
	}

	/**
	 * Function to logout user
	 *
	 */
	@GetMapping("logout")
	public String logout(Model model, HttpSession session) {
		logger.info("Method : access-denied Starts");

		session.invalidate();

		logger.info("Method : access-denied ends");
		return "redirect:";
	}

	/**
	 * Function to post register user form
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("addUser")
	public String addUserForm(@ModelAttribute User user, Model model, HttpSession session) {
		logger.info("Method POST : addUser starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			String enc = user.getUserPassword();
			if (enc != null && enc != "") {
				enc = passwordEncoder.encode(enc);
				user.setUserPassword(enc);
			}

			jsonResponse = restTemplate.postForObject(env.getUserUrl() + "registerUser", user, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != "") {
			session.setAttribute("message", jsonResponse.getMessage());
			session.setAttribute("suser", user);
			return "redirect:register";
		}

		logger.info("Method POST : addUser ends");
		return "redirect:login";
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	/*
	 * @GetMapping("/index") public String index(Model model, HttpSession session) {
	 * logger.info("Method : index starts");
	 * 
	 * String dashboard = (String) session.getAttribute("DASHBOARD");
	 * 
	 * String userType=(String) session.getAttribute("USER_ROLETYPE");
	 * 
	 * //if(userType=="Doctor") { // return "hrms-index"; //}
	 * 
	 * System.out.println("usertype====="+userType);
	 * 
	 * logger.info("Method : index  extend-index ends"); return "hrms-index"; }
	 */
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		logger.info("Method : index starts");

		String dashboard = (String) session.getAttribute("DASHBOARD");
		String usertype = (String) session.getAttribute("USER_ROLETYPE");
		System.out.println("usertype===>>>" + usertype);

		if (usertype.contains("Doctor")) {
			// return "eComShoukeen/customer-dashboard";
			return "cureEazy-index";
		} else if (usertype.contains("Dealer")) {
			return "cureEazy-index";
		}
		/*
		 * else if(usertype.contains("Dealer Partner")) { return
		 * "eComShoukeen/dealer-partner-dashboard"; return
		 * "eComShoukeen/delivery-partner-dashboard";
		 * 
		 * }
		 */
		/*
		 * else if(usertype.contains("Delivery Boy")) { return
		 * "redirect: shoukeen/delivery-boy-dashboard"; }
		 */else {
			logger.info("Method : index  extend-index ends");
			return "cureEazy-index";
		}

	}

//	@SuppressWarnings("unchecked")
//	@PostMapping(value = { "index-sales-report" })
//	public @ResponseBody JsonResponse<MapModel1> getSalesReportGraph(Model model) {
//		logger.info("Method : getSalesReportGraph starts");
//		JsonResponse<MapModel1> res = new JsonResponse<MapModel1>();
//
//		try {
//			res = restTemplate.getForObject(env.getRestaurantUrl() + "dbSalesReport", JsonResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (res.getMessage() != null) {
//			res.setCode(res.getMessage());
//			res.setMessage("Unsuccess");
//		} else {
//			res.setMessage("success");
//		}
//		logger.info("Method : getSalesReportGraph ends");
//
//		return res;
//	}
//
//	@SuppressWarnings("unchecked")
//	@PostMapping(value = { "index-order-report" })
//	public @ResponseBody JsonResponse<MapModel1> getOrderReportGraph(Model model) {
//		logger.info("Method : getOrderReportGraph starts");
//		JsonResponse<MapModel1> res = new JsonResponse<MapModel1>();
//
//		try {
//			res = restTemplate.getForObject(env.getRestaurantUrl() + "dbOrderReport", JsonResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (res.getMessage() != null) {
//			res.setCode(res.getMessage());
//			res.setMessage("Unsuccess");
//		} else {
//			res.setMessage("success");
//		}
//		logger.info("Method : getOrderReportGraph ends");
//
//		return res;
//	}

	// for order status page

//	@GetMapping("/order-status")
//	public String dashboard(Model model) {
//		logger.info("Method : /dashboard starts");
//
//		try {
//			OrderStatusModel[] order = restTemplate.getForObject(env.getRestaurantUrl() + "getOrderStatus",
//					OrderStatusModel[].class);
//			List<OrderStatusModel> orderList1 = Arrays.asList(order);
//
//			model.addAttribute("orderList1", orderList1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			OrderStatusModel[] order2 = restTemplate.getForObject(env.getRestaurantUrl() + "getOrderStatusReady",
//					OrderStatusModel[].class);
//			List<OrderStatusModel> orderList2 = Arrays.asList(order2);
//
//			model.addAttribute("orderList2", orderList2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			OrderStatusModel[] discount = restTemplate.getForObject(env.getRestaurantUrl() + "getDiscountDetails",
//					OrderStatusModel[].class);
//			List<OrderStatusModel> discountList = Arrays.asList(discount);
//
//			for (int i = 0; i < discountList.size(); i++) {
//				if (i % 4 == 0) {
//					discountList.get(i).setStatus("bg1.jpg");
//					discountList.get(i).setDiscountImage("offer.png");
//				} else if (i % 4 == 1) {
//					discountList.get(i).setStatus("bg2.jpg");
//					discountList.get(i).setDiscountImage("offer2.png");
//				} else if (i % 4 == 2) {
//					discountList.get(i).setStatus("bg3.jpg");
//					discountList.get(i).setDiscountImage("offer3.png");
//				} else {
//					discountList.get(i).setStatus("bg4.jpg");
//					discountList.get(i).setDiscountImage("offer4.png");
//				}
//			}
//
//			model.addAttribute("discountList", discountList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		logger.info("Method : /dashboard ends");
//		return "dashboard";
//		// return "app_index";
//	}

	/**
	 * Web Controller for view all assigned kitchen to restaurant
	 *
	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping("/restaurant/kitchen-staff-order-details")
//	public String getKitchenStaffOrderDetails(@RequestParam String id, Model model) {
//
//		logger.info("Method : getKitchenStaffOrderDetails starts");
//
//		DataTableRequest tableRequest = new DataTableRequest(); 
//		 
//		try {
//			// String UserId = (String) session.getAttribute("USER_ID");
//
//			tableRequest.setParam1(id);
//			// tableRequest.setUserId(UserId);
//
//			JsonResponse<List<KitchenStaffFoodOrderListModel>> jsonResponse = new JsonResponse<List<KitchenStaffFoodOrderListModel>>();
//
//			jsonResponse = restTemplate.postForObject(env.getKitchenUrl() + "getFoodListForView", tableRequest,
//					JsonResponse.class);
//
//			ObjectMapper mapper = new ObjectMapper();
//
//			List<KitchenStaffFoodOrderListModel> assignTS = mapper.convertValue(jsonResponse.getBody(),
//					new TypeReference<List<KitchenStaffFoodOrderListModel>>() {
//					});
//
//			String s = "";
//
//			for (KitchenStaffFoodOrderListModel m : assignTS) {
//
//				byte[] pId = Base64.getEncoder().encode(m.getFoodOrderId().getBytes());
//
//				/*
//				 * m.
//				 * setFoodOrderId("<a data-toggle='modal' title='View' data-target='#myModal1' href='javascript:void(0)' onclick='viewInModel(\""
//				 * + new String(pId) + "\")'>"+m.getFoodOrderId()+"</a>");
//				 */
//
//				s = s + "<a href='javascript:void(0)'" + " onclick='printPDF(\"" + new String(pId)
//						+ "\")' ><i class=\"fa fa-download\" title=\"PDF\" style=\"color:#d00c08;font-size:24px;\"></i></a>&nbsp;&nbsp;";
//
//				if (m.getFoodPrepareStatus() == 1) {
//					s = s + "<a href='javascript:void(0)'" + " onclick='changePrepareStatus(\"" + new String(pId)
//							+ "\")' ><i class=\"fa fa-cutlery\" title=\"Receive\" style=\"color:#e30f0f;font-size:24px;\"></i></a>&nbsp;&nbsp;";
//				} else {
//					if (m.getKitchenStatus() == 1) {
//
//						s = s + "<a href='javascript:void(0)'" + " onclick='changeKitchenStatus(\"" + new String(pId)
//								+ "\")' ><i class=\"fa fa-times-circle\" title=\"In Progress\" style=\"color:#e30f0f;font-size:24px;\"></i></a>&nbsp;&nbsp;";
//
//					} else if (m.getKitchenStatus() == 2) {
//
//						s = s + "<a href='javascript:void(0)'"
//								+ "' onclick='' ><i class=\"fa fa-check-circle\" title=\"Ready To Delivered\" style=\"color:#090;cursor: context-menu;font-size:24px;\"></i></a>&nbsp;&nbsp;";
//					}
//				}
//
//				m.setAction(s);
//				s = "";
//
//			}
//			model.addAttribute("orderData", assignTS);
//			model.addAttribute("storeId", id);
//			JsonResponse<Object> res = new JsonResponse<Object>();
//
//			try {
//				res = restTemplate.getForObject(env.getKitchenUrl() + "getOrderSummary?id=" + id, JsonResponse.class);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			ObjectMapper mapper1 = new ObjectMapper();
//
//			List<KitchenItemDetailsModel> itemSummery = mapper1.convertValue(res.getBody(),
//					new TypeReference<List<KitchenItemDetailsModel>>() {
//					});
//			model.addAttribute("itemSummery", itemSummery);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		logger.info("Method : getKitchenStaffOrderDetails ends");
//		return "kitchen/gocool-get-kitchen-order-status";
//	}

	/**
	 * Web Controller - Get details For Modal
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/restaurant/kitchen-staff-order-details-modal" })
	public @ResponseBody JsonResponse<Object> modalQuotation(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : summaryModal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getKitchenUrl() + "getOrderSummary?id=" + index, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : summaryModal ends");
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list/{id}")
	public String getMenuDetails(Model model, HttpSession session, @PathVariable String id) {
		logger.info("Method : index starts");

		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {
			
			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(id))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
						}

					}
				}
				
				for(Function f: funDetails) {
					logger.info("function list size: "+f.getName());	
				}
				
				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", id);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : index  extend-index ends");

		System.out.println("Act URL=====" + activityUrl);

		// return "extend-index2";
		return "redirect:" + activityUrl;

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-activity-list" })
	public @ResponseBody JsonResponse<List<Activity>> getActivityList(@RequestParam String funId,
			@RequestParam String moduleId, HttpSession session) {
		logger.info("Method : getActivityList starts");

		JsonResponse<List<Activity>> res = new JsonResponse<List<Activity>>();
		List<Activity> activityList = new ArrayList<Activity>();

		try {
			List<Module> module = new ArrayList<Module>();
			try {

				module = (List<Module>) session.getAttribute("MENU");
				if (module != null && module.size() > 0) {
					List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
							.collect(Collectors.toList());
					if (fList != null) {
						List<Function> funDetails = (List<Function>) fList.get(0).getModule().stream()
								.filter(a -> a.getFunctionId().equals(funId)).collect(Collectors.toList());
						activityList = funDetails.get(0).getFunction();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			res.setBody(activityList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : getActivityList ends");
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list-resp")
	public @ResponseBody JsonResponse<List<Function>> getFunctionDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : index starts");

		JsonResponse<List<Function>> res = new JsonResponse<List<Function>>();
		List<Activity> activityList = new ArrayList<Activity>();

		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {

			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
							a.setDefaultUrlId(a.getFunction().get(0).getActivityId());
						}

					}
				}

				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", moduleId);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.setBody(funDetails);
		logger.info("Method : index  extend-index ends");

		// return "extend-index2";
		return res;

	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-breadcrumb-data")
	public @ResponseBody JsonResponse<Object> getBreadcrumbData(Model model, HttpSession session,
			@RequestParam("moduleId") String moduleId, @RequestParam("fun") String funId,
			@RequestParam("activity") String actId) {
		logger.info("Method : index getBreadcrumbData starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			// REST - MenuController
			res = restTemplate.getForObject(
					env.getUserUrl() + "get-breadcrumb-data?modId=" + moduleId + "&funId=" + funId + "&actId=" + actId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : index  getBreadcrumbData ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-module-list" })
	public @ResponseBody JsonResponse<List<Module>> getModuleDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : getModuleDetails starts");

		JsonResponse<List<Module>> res = new JsonResponse<List<Module>>();

		List<Module> module = (List<Module>) session.getAttribute("MENU");

		res.setBody(module);
		logger.info("Method : getModuleDetails ends");

		return res;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/index-get-avl-function-list")
	public @ResponseBody JsonResponse<ActivityAvlFunctionModel> getAvlFunctionByActivityRole(
			@RequestBody List<DropDownModel> data, HttpSession session) {
		logger.info("Method : updateModule starts");

		JsonResponse<ActivityAvlFunctionModel> resp = new JsonResponse<ActivityAvlFunctionModel>();

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "getAvlFunctionByActivityRole", data,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : getAvlFunctionByActivityRole starts");
		return resp;
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("error")
	public String error(Model model, HttpSession session) {
		logger.info("Method : error starts");

		logger.info("Method : error ends");
		return "error";
	}

	/******************************************************************************************/

	/*
	 * @Autowired AdvanceManagementController advanceManagementController;
	 */

	// @SuppressWarnings("unchecked")
	@GetMapping("/master/advance-disburshment-mobile")
	public String advDisburshment(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {
		logger.info("Method : advDisburshment starts");
		System.out.println("#####" + userId);
		if ("code".equals(code)) {
			System.out.println("userId==" + userId + "code==" + code + "role===" + userRole);
			// String userRole = "rol001, rol002, rol003";

			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			try {
				DropDownModel[] requisition = restTemplate.getForObject(
						env.getMasterUrl() + "get-advance-policy-list?userId=" + userId, DropDownModel[].class);
				System.out.println("$$$$$" + env.getMasterUrl() + "get-advance-policy-list?userId=" + userId);
				List<DropDownModel> policyList = Arrays.asList(requisition);
				model.addAttribute("policyList", policyList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// advanceManagementController.viewAdvanceApply(session, userId,userRole);
			model.addAttribute("userId", userId);
			model.addAttribute("userRole", userRole);
			logger.info("Method : advDisburshment ends");
			return "master/advanceManagement";
		} else {
			logger.info("Method : advDisburshment ends");
			return "accessDenied";

		}
	}

	// Summary

	@GetMapping("/employee/view-manage-employee-mobile")
	public String employeeMobile(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : employeeMobile starts");
		if ("code".equals(code)) {
			session.setAttribute("USER_ID", userId);

			String UID = "";
			try {
				UID = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Gender = restTemplate.getForObject(env.getEmployeeUrl() + "getgenderList1",
						DropDownModel[].class);
				List<DropDownModel> genderTypeList = Arrays.asList(Gender);

				model.addAttribute("genderTypeList", genderTypeList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			try {
				DropDownModel[] Nationality = restTemplate.getForObject(env.getEmployeeUrl() + "getnationalityList1",
						DropDownModel[].class);
				List<DropDownModel> nationalityList = Arrays.asList(Nationality);

				model.addAttribute("nationalityList", nationalityList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] BloodGroup = restTemplate.getForObject(env.getEmployeeUrl() + "getbloodgroupList1",
						DropDownModel[].class);
				List<DropDownModel> bloodgroupList = Arrays.asList(BloodGroup);

				model.addAttribute("bloodgroupList", bloodgroupList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] MaritalStatus = restTemplate
						.getForObject(env.getEmployeeUrl() + "getmaritalstatusList1", DropDownModel[].class);
				List<DropDownModel> maritalstatusList = Arrays.asList(MaritalStatus);

				model.addAttribute("maritalstatusList", maritalstatusList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Country = restTemplate.getForObject(env.getEmployeeUrl() + "getCountryList",
						DropDownModel[].class);
				List<DropDownModel> counntryList = Arrays.asList(Country);

				model.addAttribute("counntryList", counntryList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {

				DropDownModel[] addressType = restTemplate.getForObject(env.getRecruitment() + "addressTypeList",
						DropDownModel[].class);
				List<DropDownModel> addressTypeList = Arrays.asList(addressType);
				model.addAttribute("addressTypeList", addressTypeList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] JobType = restTemplate.getForObject(env.getEmployeeUrl() + "getJobType1",
						DropDownModel[].class);
				List<DropDownModel> jobtypeList = Arrays.asList(JobType);

				model.addAttribute("jobtypeList", jobtypeList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Department = restTemplate.getForObject(env.getEmployeeUrl() + "getDepartmentList1",
						DropDownModel[].class);
				List<DropDownModel> DepartmentList = Arrays.asList(Department);

				model.addAttribute("DepartmentList", DepartmentList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] TimesheetType = restTemplate.getForObject(env.getEmployeeUrl() + "getTimesheetType1",
						DropDownModel[].class);
				List<DropDownModel> TimesheetList = Arrays.asList(TimesheetType);

				model.addAttribute("TimesheetList", TimesheetList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {

				DropDownModel[] band = restTemplate.getForObject(env.getRecruitment() + "bandList",
						DropDownModel[].class);
				List<DropDownModel> bandList = Arrays.asList(band);
				model.addAttribute("bandList", bandList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] employmentType = restTemplate.getForObject(env.getEmployeeUrl() + "getemploymentType1",
						DropDownModel[].class);
				List<DropDownModel> employmentstatusList = Arrays.asList(employmentType);

				model.addAttribute("employmentstatusList", employmentstatusList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {

				DropDownModel[] manager = restTemplate.getForObject(env.getEmployeeUrl() + "EmployeeList",
						DropDownModel[].class);
				List<DropDownModel> managerList = Arrays.asList(manager);
				model.addAttribute("EmployeeList", managerList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Benefits = restTemplate.getForObject(env.getEmployeeUrl() + "getBenefits",
						DropDownModel[].class);
				List<DropDownModel> benefitsList = Arrays.asList(Benefits);

				model.addAttribute("benefitsList", benefitsList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] dependentType = restTemplate.getForObject(env.getEmployeeUrl() + "dependentTypeList",
						DropDownModel[].class);
				List<DropDownModel> dependentTypeList = Arrays.asList(dependentType);

				model.addAttribute("dependentTypeList", dependentTypeList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] relationship = restTemplate.getForObject(env.getEmployeeUrl() + "relationshipList",
						DropDownModel[].class);
				List<DropDownModel> relationshipList = Arrays.asList(relationship);

				model.addAttribute("relationshipList", relationshipList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "getBankNameList",
						DropDownModel[].class);
				List<DropDownModel> BankNameList = Arrays.asList(Bank);

				model.addAttribute("BankNameList", BankNameList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "insuranceCompanyList",
						DropDownModel[].class);
				List<DropDownModel> insuranceCompanyList = Arrays.asList(Bank);

				model.addAttribute("insuranceCompanyList", insuranceCompanyList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "documentTypeList",
						DropDownModel[].class);
				List<DropDownModel> documentTypeList = Arrays.asList(Bank);

				model.addAttribute("documentTypeList", documentTypeList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			try {
				DropDownModel[] dropDown = restTemplate.getForObject(env.getMasterUrl() + "rest-getDesignationDropDown",
						DropDownModel[].class);
				List<DropDownModel> Designation = Arrays.asList(dropDown);
				model.addAttribute("Designation", Designation);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			System.out.println("UID====" + UID);
			logger.info("Method : employeeMobile ends");

			return "employee/view-manage-employee";
		} else {
			logger.info("Method : employeeMobile ends");
			return "accessDenied";
		}
	}

	@GetMapping("/master/organization-category-mobile")
	public String employeeOrgnization(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : employeeOrgnization starts");
		if ("code".equals(code)) {

			try {
				DropDownModel org = restTemplate.getForObject(env.getMasterUrl() + "getOrganizationName",
						DropDownModel.class);
				model.addAttribute("orgName", org.getName());
				model.addAttribute("orgId", org.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// drop down for employee list
			try {
				DropDownModel[] emplist = restTemplate.getForObject(env.getMasterUrl() + "getEmployeeLists",
						DropDownModel[].class);
				List<DropDownModel> emplists = Arrays.asList(emplist);
				model.addAttribute("emplists", emplists);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			return "master/organizationCategory";
		} else {
			logger.info("Method : employeeOrgnization ends");
			return "accessDenied";
		}
	}

	@GetMapping("master/leave-apply-mobile")
	public String leaveApplyMob(Model model, @RequestParam String userId, String userRole, String userName, String code,
			HttpSession session) {

		logger.info("Method : leaveApplyMob starts");
		if ("code".equals(code)) {
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}

			try {
				DropDownModel[] leaveType = restTemplate.getForObject(env.getMasterUrl() + "getleavelists",
						DropDownModel[].class);
				List<DropDownModel> leavelist = Arrays.asList(leaveType);
				model.addAttribute("leavelist", leavelist);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			model.addAttribute("userId", userId);
			model.addAttribute("userRole", userRole);
			model.addAttribute("userName", userName);

			return "master/leave-apply";
		} else {
			logger.info("Method : leaveApplyMob ends");
			return "accessDenied";
		}
	}

	@GetMapping("employee/reimbursement-mobile")
	public String reimbursementMob(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : reimbursementMob starts");
		if ("code".equals(code)) {
			
			try {
				DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-reimbursement-type",
						DropDownModel[].class);
				List<DropDownModel> reimType = Arrays.asList(dropDownModel);
				model.addAttribute("reimType", reimType);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-policy-type",
						DropDownModel[].class);
				List<DropDownModel> policyType = Arrays.asList(dropDownModel);
				model.addAttribute("policyType", policyType);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] gender = restTemplate.getForObject(env.getEmployeeUrl() + "getRequisitionList",
						DropDownModel[].class);
				List<DropDownModel> getRequisitionList = Arrays.asList(gender);
				model.addAttribute("getRequisitionList", getRequisitionList);

			} catch (RestClientException e) {
				e.printStackTrace();

			}
			// Payment mode list
			try {
				DropDownModel[] paymentMode = restTemplate.getForObject(env.getEmployeeUrl() + "getPaymentMode",
						DropDownModel[].class);
				List<DropDownModel> PayModeLists = Arrays.asList(paymentMode);

				model.addAttribute("PayModeLists", PayModeLists);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			// Get bank Name Details
			try {
				DropDownModel[] bankNames = restTemplate.getForObject(env.getEmployeeUrl() + "getBankNamesPay",
						DropDownModel[].class);
				List<DropDownModel> bankNameLists = Arrays.asList(bankNames);
				model.addAttribute("bankNameLists", bankNameLists);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			
			
			model.addAttribute("userId", userId);
			
			model.addAttribute("userRole", userRole);

			return "employee/reimbursement";
		} else {
			logger.info("Method : reimbursementMob ends");
			return "accessDenied";
		}
	}
	

	@GetMapping("employee/reimbursement-other-mobile")
	public String reimbursementOtherMob(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : reimbursementOtherMob starts");
		if ("code".equals(code)) {

			try {
				DropDownModel[] reimType = restTemplate.getForObject(env.getEmployeeUrl() + "getTypeLists",
						DropDownModel[].class);
				List<DropDownModel> reimbTypeLists = Arrays.asList(reimType);
				model.addAttribute("reimbTypeLists", reimbTypeLists);
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			model.addAttribute("userId", userId);
			model.addAttribute("userRole", userRole);
		

			return "employee/reimbursement-other";
		} else {
			logger.info("Method : reimbursementOtherMob ends");
			return "accessDenied";
		}
	}
	
	@GetMapping("employee/travel-requisition-mobile")
	public String travelReqMob(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : travelReqMob starts");
		if ("code".equals(code)) {
			
			
			
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			
			
			model.addAttribute("userId", userId);
			
			model.addAttribute("userRole", userRole);

			return "employee/travelRequisition";
		} else {
			logger.info("Method : travelReqMob ends");
			return "accessDenied";
		}
	}
	
	@GetMapping("employee/travel-claim-mobile")
	public String travelClaimMob(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : travelClaimMob starts");
		if ("code".equals(code)) {
			
			
			
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			
			
			model.addAttribute("userId", userId);
			
			model.addAttribute("userRole", userRole);

			return "employee/travelClaim";
		} else {
			logger.info("Method : travelClaimMob ends");
			return "accessDenied";
		}
	}
	
	@GetMapping("master/view-payslip-mobile")
	public String payslipMob(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : payslipMob starts");
		if ("code".equals(code)) {
			
			try {
				
				
				DropDownModel[] month = restTemplate.getForObject(env.getMasterUrl() + "getMonthLists",
						DropDownModel[].class);
				List<DropDownModel> monthLists = Arrays.asList(month);
				model.addAttribute("monthLists", monthLists);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			String isHr = "";
			
			try {
				userRole = (String) session.getAttribute("USER_ROLES_STRING");

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		

			userId = (String) session.getAttribute("USER_ID");
			// drop down for employee list
			try {
				DropDownModel[] emp = restTemplate.getForObject(
						env.getMasterUrl() + "getEmployeeListsSlip?userId=" + userId + "&isHr=" + isHr,
						DropDownModel[].class);
				List<DropDownModel> employeeLists = Arrays.asList(emp);
				model.addAttribute("employeeLists", employeeLists);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			try {
				DropDownModel[] dropDownModel = restTemplate.getForObject(env.getMasterUrl() + "rest-getBandDropDown",
						DropDownModel[].class);
				List<DropDownModel> bandType = Arrays.asList(dropDownModel);
				model.addAttribute("bandType", bandType);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			
			model.addAttribute("userId", userId);
			
			model.addAttribute("userRole", userRole);

			return "master/viewPayslip";
		} else {
			logger.info("Method : payslipMob ends");
			return "accessDenied";
		}
	}

	@GetMapping("master/view-tax-mobile")
	public String taxMobile(Model model, @RequestParam String userId, String userRole, String code,
			HttpSession session) {

		logger.info("Method : taxMobile starts");
		if ("code".equals(code)) {
			
			try {
				DropDownModel[] month = restTemplate.getForObject(env.getMasterUrl() + "getMonthLists",
						DropDownModel[].class);
				List<DropDownModel> monthLists = Arrays.asList(month);
				model.addAttribute("monthLists", monthLists);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] dropDownModel = restTemplate.getForObject(env.getMasterUrl() + "rest-getBandDropDown",
						DropDownModel[].class);
				List<DropDownModel> bandType = Arrays.asList(dropDownModel);
				model.addAttribute("bandType", bandType);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			String splitData[] = userRole.split("r");
			String[] removedNull = Arrays.stream(splitData).filter(value -> value != "" && value.length() > 0)
					.toArray(size -> new String[size]);
			for (String part : removedNull) {
				String data = "r" + part;

				if (data.contentEquals("rol001") || data.contentEquals("rol003")) {
					model.addAttribute("hrRole", data);
				}
			}
			
			
			model.addAttribute("userId", userId);
			
			model.addAttribute("userRole", userRole);

			return "master/viewTax";
		} else {
			logger.info("Method : taxMobile ends");
			return "accessDenied";
		}
	}
	
}
