package ch.css.pomodoro.client.ui.exit;

import java.io.UnsupportedEncodingException;

import ch.css.pomodoro.client.service.DeregistrationService;

public class DeregistrationHandler {

	public void deregisterClient() throws UnsupportedEncodingException {
		DeregistrationService service = new DeregistrationService();
		service.callDeregistrationService();
	}

}
