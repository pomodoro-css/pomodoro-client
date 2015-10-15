package ch.css.pomodoro.client.ui.registration;

import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import ch.css.pomodoro.client.service.RegistrationService;

public class RegistrationHandler {

	public void registerClient() throws UnsupportedEncodingException {
		RegistrationService regService = new RegistrationService();
		int responseCode = regService.callRegistrationService();

		if (responseCode == 500) {
			JOptionPane.showMessageDialog(null, "Your P-Number should have at least 5 characters");
		} else if (responseCode == 304) {
			JOptionPane.showMessageDialog(null, "Your are already registered!");
		} else {
			JOptionPane.showMessageDialog(null, "Your registration was successfull.");
		}
	}

}
