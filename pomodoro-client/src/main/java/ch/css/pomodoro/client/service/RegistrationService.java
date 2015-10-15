package ch.css.pomodoro.client.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.utility.PomodoreSystemUtils;
import ch.css.pomodoro.client.utility.UserInfo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegistrationService {
	private static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	public int callRegistrationService() throws UnsupportedEncodingException {
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append("?nr=" + URLEncoder.encode(UserInfo.getPNummer(), "UTF-8"));
		callUrl.append("&name=" + URLEncoder.encode(UserInfo.getName(), "UTF-8"));
		callUrl.append("&group=" + URLEncoder.encode(UserInfo.getGroupName(), "UTF-8"));

		logger.info(String.format("URL for Registration Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);

		return response.getStatus();
	}
}
