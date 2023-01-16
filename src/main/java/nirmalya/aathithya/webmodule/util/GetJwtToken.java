package nirmalya.aathithya.webmodule.util;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GetJwtToken {

	@Autowired
	RestTemplate restTemplate;

	public String toekn() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("clientId", "SBX_000035");
		map.put("clientSecret", "2f59bf0b-f396-4f2a-b639-0ef8572c8618");
		JSONObject ob = new JSONObject(map);
		System.out.println(ob.toString());
		String authToken = null;
		try {
			String url = "https://dev.abdm.gov.in/gateway/v0.5/sessions";
			HttpHeaders requestHeaders = new HttpHeaders();

			requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

			HttpEntity<String> httpEntity = new HttpEntity<String>(ob.toString(), requestHeaders);
			ResponseEntity<String> st = restTemplate.postForEntity(url, httpEntity, String.class);

			JSONObject obres=null;
			try {
				obres = new JSONObject(st.getBody());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				authToken = obres.getString("accessToken");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return authToken;
	}
}
