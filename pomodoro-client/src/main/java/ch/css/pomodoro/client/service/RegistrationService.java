package ch.css.pomodoro.client.service;

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

	public boolean callRegistrationService() {
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append("?nr="+UserInfo.getPNummer());
		callUrl.append("&name="+UserInfo.getName());
		if (UserInfo.getGroupName() != null) {
			callUrl.append("&group="+UserInfo.getGroupName());
		}
		
		logger.info(String.format("URL for Registration Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class);
		
		// TODO response
		if (response.getStatus() != 200) {
			return false;
		}
		return true;
	}

}
