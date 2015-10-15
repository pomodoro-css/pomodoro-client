package ch.css.pomodoro.client.ui.stop;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import ch.css.pomodoro.client.service.StopTimerService;
import ch.css.pomodoro.client.utility.UserInfo;

public class StopTimerHandler {

	private TrayIcon trayIcon;

	public StopTimerHandler(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	public void stopTimer() {
		StopTimerService service = new StopTimerService();

		boolean isSuccessful = service.callStopTimerService(UserInfo.getPNummer());
		if (isSuccessful) {
			trayIcon.displayMessage("Stop timer", "Your timer has been stopped", MessageType.INFO);
		} else {
			trayIcon.displayMessage("Stop timer", "Unable to stop yout timer. Please start again.",
					MessageType.WARNING);
		}
	}

}
