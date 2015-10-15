package ch.css.pomodoro.client.service;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ch.css.pomodoro.client.utility.PomodoreSystemUtils;
import ch.css.pomodoro.client.utility.UserInfo;

public class StartTimerService {
	private static Logger logger = LoggerFactory.getLogger(StartTimerService.class);

	public boolean callStartTimerService() {
		// handle 404 Error
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append(UserInfo.getPNummer());
		callUrl.append("/start");
		
		logger.info(String.format("URL for StartTimer Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON_TYPE)
				.put(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			return false;
		}
		return true;
	}

}
