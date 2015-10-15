package ch.css.pomodoro.client.service;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.utility.PomodoreSystemUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class StopTimerService {

	private static Logger logger = LoggerFactory.getLogger(StopTimerService.class);

	public boolean callStopTimerService(String pNummer) {
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append("/" + pNummer);
		callUrl.append("/stop");

		logger.info(String.format("URL for StopTimer Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class);

		if (response.getStatus() != 200) {
			return false;
		}
		return true;
	}

}
