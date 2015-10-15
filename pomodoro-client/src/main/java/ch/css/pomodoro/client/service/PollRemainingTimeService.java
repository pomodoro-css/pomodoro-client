package ch.css.pomodoro.client.service;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ch.css.pomodoro.client.utility.PomodoreSystemUtils;
import ch.css.pomodoro.client.utility.UserInfo;

public class PollRemainingTimeService {
	private static Logger logger = LoggerFactory.getLogger(PollRemainingTimeService.class);

	public int callPollRemainingTimeService() {
		String basisURL = PomodoreSystemUtils.getBasisUrl();
		StringBuilder callUrl = new StringBuilder(basisURL);
		callUrl.append(UserInfo.getPNummer());
		
		logger.info(String.format("URL for Poll Remaining Time Service: %s", callUrl.toString()));

		Client client = Client.create();
		WebResource webResource = client.resource(callUrl.toString());

		String responseSring = webResource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			RegisteredUser user = mapper.readValue(responseSring, new TypeReference<RegisteredUser>(){});
			return user.getRemainingTime();
		} catch (JsonParseException e) {
			logger.error("Json Parse Exception: /n" + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("Json Mapping Exception: /n" + e.getMessage());
		} catch (IOException e) {
			logger.error("I/O Exception: /n" + e.getMessage());
		}
		return 0;
	}


}
