package ch.css.pomodoro.client.ui.background;

import java.awt.TrayIcon.MessageType;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.PollRemainingTimeService;
import ch.css.pomodoro.client.utility.UserInfo;

public class PollUserStatus extends TimerTask {
	private static Logger logger = LoggerFactory.getLogger(PollUserStatus.class);
	private static int interval = 5000; // 5 seconds
	private Timer timer = null;

	public void startUserStatusPolling() {
		timer = new Timer();
		timer.scheduleAtFixedRate(this, 0, interval);
	}

	public void stopUserStatusPolling() {
		timer.cancel();
	}

	@Override
	public void run() {
		PollRemainingTimeService pollService = new PollRemainingTimeService();
		logger.info(String.format("Polling for User %s ", UserInfo.getPNummer()));
		logger.info(String.format("Remaining Time %d ", pollService.callPollRemainingTimeService()));
		if (pollService.callPollRemainingTimeService() == 0) {
			UserInfo.getTrayIcon().displayMessage("Poll",
					"Remaining Time " + String.valueOf(pollService.callPollRemainingTimeService()),
					MessageType.INFO);
			TimerEndedUI timerEnded = new TimerEndedUI();
			try {
				timerEnded.showTimerEndedUI();
			} catch (UnsupportedEncodingException e) {
				JOptionPane.showMessageDialog(null, "Please don't use unkown symbols/characters.");
			}
			timer.cancel();
		}
	}

}
