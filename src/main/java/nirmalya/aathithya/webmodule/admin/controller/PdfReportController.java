package nirmalya.aathithya.webmodule.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.pagination.DataTableRequest;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;

@Controller

@RequestMapping(value = { "admin/" })
public class PdfReportController {
	Logger logger = LoggerFactory.getLogger(PdfReportController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;


	
	@GetMapping("view-patient-documentation-report")
	public void dischargereportPdfDownload(HttpSession session,Model model, HttpServletResponse response, 
			@RequestParam("id") String encodedParam1) {
		logger.info("Method : dischargereportPdfDownload starts");
		
		byte[] encodeByte1 = Base64.getDecoder().decode(encodedParam1.getBytes());

		String param1 = (new String(encodeByte1));
		DropDownModel gatPatientdetails = new DropDownModel();
		try {
			DropDownModel title = restTemplate.getForObject(env.getAdminUrl()
					+ "rest-getPatientDetails?id=" + param1,
					DropDownModel.class);
			
			gatPatientdetails = title;
			model.addAttribute("gatPatientdetails", gatPatientdetails);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	

		Map<String, Object> data = new HashMap<String, Object>();
	
		data.put("gatPatientdetails", gatPatientdetails);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=PatientDocumentation.pdf");
		File file;
		byte[] fileData = null;
		try {
			file = pdfGeneratorUtil.createPdf("admin/patient-report", data);
			file.createNewFile();
			System.out.println("Generatefile"+file);
			InputStream in = new FileInputStream(file);
			fileData = IOUtils.toByteArray(in);
			response.setContentLength(fileData.length);
			response.getOutputStream().write(fileData);
			response.getOutputStream().flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		System.out.println("rdmodeldocMerge====");

		logger.info("Method : dischargereportPdfDownload ends");
	}
	@GetMapping(value = { "view-patient-document-details" })
	public String getatientDocument(HttpServletResponse response, Model model, HttpSession session,
			@RequestParam("id") String encodedParam1) {

		logger.info("Method : generatePatientDocumentation starts");
		logger.info("id"+encodedParam1);
		
		

		logger.info("Method : generatePatientDocumentation ends");
		return "admin/patientDocument";
	}
}
