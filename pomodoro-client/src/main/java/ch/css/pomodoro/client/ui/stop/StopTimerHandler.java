package ch.css.pomodoro.client.ui.stop;

import java.awt.TrayIcon.MessageType;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.StopTimerService;
import ch.css.pomodoro.client.utility.UserInfo;

public class StopTimerHandler {

	private static Logger logger = LoggerFactory.getLogger(StopTimerHandler.class);

	public void stopTimer() throws UnsupportedEncodingException {
		StopTimerService service = new StopTimerService();

		boolean isSuccessful = service.callStopTimerService();
		if (isSuccessful) {
			UserInfo.getTrayIcon().displayMessage("Stop timer", "Your timer has been stopped",
					MessageType.INFO);
			UserInfo.getInstanceOfPollUserStatus().stopUserStatusPolling();
			UserInfo.clearPollUserStatus();
			logger.info("Stop User Status Polling...");

		} else {
			UserInfo.getTrayIcon().displayMessage("Stop timer",
					"Unable to stop yout timer. Please start again.", MessageType.WARNING);
		}
	}

}
