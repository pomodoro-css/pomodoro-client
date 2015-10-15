package ch.css.pomodoro.client.ui.start;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.StartTimerService;
import ch.css.pomodoro.client.utility.UserInfo;

public class StartTimerHandler {
	private static Logger logger = LoggerFactory.getLogger(StartTimerHandler.class);
	private TrayIcon trayIcon;

	public StartTimerHandler(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	public void startTimer() {
		StartTimerService startTimerService = new StartTimerService();
		boolean successful = startTimerService.callStartTimerService();
		if (successful) {
			trayIcon.displayMessage("Start", "Starting timer with 25 Minutes.", MessageType.INFO);
			UserInfo.getInstanceOfPollUserStatus().startUserStatusPolling(trayIcon);
			logger.info("Start User Status Polling...");
		} else {
			trayIcon.displayMessage("Start", "Starting timer failed!", MessageType.ERROR);
		}
	}

}
