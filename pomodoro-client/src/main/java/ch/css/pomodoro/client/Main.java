package ch.css.pomodoro.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	private final String myString = "mine";

	private static final String DEFAULT_HOST = "http://lnx54064.css.ch:12345";

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

		String host = "";
		if (args.length == 1) {
			host = args[0];
		} else {
			host = DEFAULT_HOST;
		}

		logger.info(String.format("Host: %s", host));

		checkSystemTrayAvailability();

		// TrayIcon appIcon = new TrayIcon(image);

		// SystemTray.getSystemTray().add(trayIcon);

		Main main = new Main();
		main.start(host);
	}

	private void start(String host) {
		initUiManager();
		startTrayIconUI();
	}

	private void startTrayIconUI() {
		// TODO Auto-generated method stub

	}

	private void initUiManager() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
