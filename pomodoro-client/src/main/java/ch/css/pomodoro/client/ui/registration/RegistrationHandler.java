package ch.css.pomodoro.client.ui.registration;

import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import ch.css.pomodoro.client.service.RegistrationService;

public class RegistrationHandler {

	public void registerClient() throws UnsupportedEncodingException {
		RegistrationService regService = new RegistrationService();
		boolean successful = regService.callRegistrationService();
		if (successful) {
			JOptionPane.showMessageDialog(null, "You have been registered.");
		} else {
			JOptionPane.showMessageDialog(null,
					"Sorry, we couldn't register you. Please try again later.");
		}
	}

}
