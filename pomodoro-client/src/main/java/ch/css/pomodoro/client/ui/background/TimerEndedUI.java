package ch.css.pomodoro.client.ui.background;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.ui.start.StartTimerHandler;
import ch.css.pomodoro.client.ui.stop.StopTimerHandler;
import ch.css.pomodoro.client.utility.UserInfo;

public class TimerEndedUI {

	private static Logger logger = LoggerFactory.getLogger(TimerEndedUI.class);

	public TimerEndedUI() {
		logger.info("Timer ended UI!");
	}

	public void showTimerEndedUI() {
		int result = JOptionPane.showConfirmDialog((Component) null, "Your tomato time has expired, congratulations! Another Tomato Time for you?",
		        "alert", JOptionPane.YES_NO_OPTION);
		if (result == 0){
			logger.info("User wants more tomatos: " + String.valueOf(result));
			UserInfo.clearPollUserStatus();
			StartTimerHandler newStart = new StartTimerHandler();
			newStart.startTimer();
		} else {
			StopTimerHandler stopTimer = new StopTimerHandler();
			stopTimer.stopTimer();
			logger.info("User has enough tomatos: " + String.valueOf(result));
		}
		
	}


}
