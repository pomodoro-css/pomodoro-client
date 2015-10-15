package ch.css.pomodoro.client.ui.stop;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.StopTimerService;
import ch.css.pomodoro.client.utility.UserInfo;

public class StopTimerHandler {

	private static Logger logger = LoggerFactory.getLogger(StopTimerHandler.class);
	private TrayIcon trayIcon;

	public StopTimerHandler(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	public void stopTimer() {
		StopTimerService service = new StopTimerService();

		boolean isSuccessful = service.callStopTimerService();
		if (isSuccessful) {
			trayIcon.displayMessage("Stop timer", "Your timer has been stopped", MessageType.INFO);
			UserInfo.getInstanceOfPollUserStatus().stopUserStatusPoling();
			logger.info("Stop User Status Polling...");
			
		} else {
			trayIcon.displayMessage("Stop timer", "Unable to stop yout timer. Please start again.",
					MessageType.WARNING);
		}
	}

}
