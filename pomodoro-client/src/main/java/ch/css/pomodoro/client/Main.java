package ch.css.pomodoro.client;

import java.awt.SystemTray;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.ui.PomodoroTrayIconUI;
import ch.css.pomodoro.client.utility.PomodoreSystemUtils;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	private static final String DEFAULT_HOST = "http://localhost:8080";

	{
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.error(String.format("Uncaught exception in Thread %s", t.getName()), e);
			}
		});
	}

	public static void main(String[] args) throws Exception {
		logger.info("Starting Pomodoro Client...");

		String host = determineHost(args);

		checkSystemTrayAvailability();
		setupServicePrequisites(host);
		initUiManager();

		startTrayIconApp();
	}

	private static String determineHost(String[] args) {
		String host = "";
		if (args.length == 1) {
			host = args[0];
		} else {
			host = DEFAULT_HOST;
		}
		logger.info(String.format("Host: %s", host));
		return host;
	}

	private static void setupServicePrequisites(String host) {
		PomodoreSystemUtils.setHost(host);
		PomodoreSystemUtils.setBeginPath("/users/");
	}

	private static void startTrayIconApp() {

		final PomodoroTrayIconUI ui = new PomodoroTrayIconUI();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ui.createAndShowUI();
			}
		});

	}

	private static void initUiManager() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

	private static void checkSystemTrayAvailability() {
		if (!SystemTray.isSupported()) {
			logger.error("No system tray supported");
			System.exit(0);
		} else {
			logger.info("System tray available");
		}
	}

}
