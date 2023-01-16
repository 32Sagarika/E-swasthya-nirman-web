package nirmalya.aathithya.webmodule.dropdown;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Service
public class DropdownService {

	Logger logger = LoggerFactory.getLogger(DropdownService.class);
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> fetchCountryList() {
		logger.info("Fetch country list for listing");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		try {
			logger.info(env.getDropdownUrl() + "get-country-lists");
			resp = restTemplate.getForObject(env.getDropdownUrl() + "get-country-lists", 
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			countryList = mapper.convertValue(resp.getBody(),
					new TypeReference<DropDownModel>() {
					});
		} catch (RestClientException e) {
			logger.error("Unable to fetch country list: " + e.getMessage());
		}

		return countryList;
	}
}
