package ch.css.pomodoro.client.ui.start;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import ch.css.pomodoro.client.service.StartTimerService;

public class StartTimerHandler {

	private TrayIcon trayIcon;

	public StartTimerHandler(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	public void startTimer() {
		StartTimerService startTimerService = new StartTimerService();
		boolean successful = startTimerService.callStartTimerService();
		if (successful) {
			trayIcon.displayMessage("Start", "Starting timer with 25 Minutes.", MessageType.INFO);
		} else {
			trayIcon.displayMessage("Start", "Starting timer failed!", MessageType.ERROR);
		}
	}

}
