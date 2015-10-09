package ch.css.pomodoro.client;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegistrationService {
	private static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	public boolean callRegistrationService(String pNummer, String name, String group) {
		// handle 404 Error
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append("?nr="+pNummer);
		callUrl.append("&name="+name);
		if (group != null) {
			callUrl.append("&group="+group);
		}
		
		logger.info(String.format("URL for Registration Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class);
		
		// TODO response

		return true;

	}

}
