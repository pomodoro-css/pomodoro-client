package ch.css.pomodoro.client.ui;

import javax.swing.JOptionPane;

import ch.css.pomodoro.client.service.RegistrationService;

public class RegistrationHandler {

	private String pNr;
	private String name;
	private String gruppe;

	public RegistrationHandler(String pNr, String name, String gruppe) {
		this.pNr = pNr;
		this.name = name;
		this.gruppe = gruppe;
	}

	public void registerClient() {
		RegistrationService regService = new RegistrationService();
		boolean successful = regService.callRegistrationService(pNr, name, gruppe);
		if (successful) {
			JOptionPane.showMessageDialog(null, "You have been registered.");
		} else {
			JOptionPane.showMessageDialog(null,
					"Sorry, we couldn't register you. Please try again later.");
		}
	}

}
