package ch.css.pomodoro.client.ui.background;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.PollRemainingTimeService;
import ch.css.pomodoro.client.utility.UserInfo;

public class PollUserStatus extends TimerTask {
	private static Logger logger = LoggerFactory.getLogger(PollUserStatus.class);
	private static int interval = 5000; // 5 seconds
	private Timer timer;
	private TrayIcon trayIcon;
	
	public void startUserStatusPolling(TrayIcon tIcon) {
		TimerTask pollRemainingTime = this;
		timer = new Timer();
		timer.scheduleAtFixedRate(pollRemainingTime, 0, interval);
		trayIcon = tIcon;
		
	}

	public void stopUserStatusPoling() {
		timer.cancel();
	}

	@Override
	public void run() {
		PollRemainingTimeService pollService = new PollRemainingTimeService();
		pollService.callPollRemainingTimeService();
		logger.info(String.format("Polling for User %s ", UserInfo.getPNummer()));
		logger.info(String.format("Remaining Time %d ", pollService.callPollRemainingTimeService()));
		if (pollService.callPollRemainingTimeService() == 0){
			trayIcon.displayMessage("Poll", "Remaining Time " + String.valueOf(pollService.callPollRemainingTimeService()), MessageType.INFO);
			timer.cancel();
		}
	}

}
