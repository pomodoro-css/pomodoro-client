package ch.css.pomodoro.client.ui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.ui.allstatus.UsersStatusUI;
import ch.css.pomodoro.client.ui.registration.RegistrationUI;
import ch.css.pomodoro.client.ui.start.StartTimerHandler;
import ch.css.pomodoro.client.ui.stop.StopTimerHandler;
import ch.css.pomodoro.client.utility.IconFactory;
import ch.css.pomodoro.client.utility.UserInfo;
import ch.css.pomodoro.client.utility.VersionInfo;

public class PomodoroTrayIconUI {

	private static Logger logger = LoggerFactory.getLogger(PomodoroTrayIconUI.class);

	private TrayIcon trayIcon;
	private MenuItem startTimer;
	private MenuItem stopTimer;

	public void createAndShowUI() {
		final PopupMenu popup = new PopupMenu();
		final SystemTray tray = SystemTray.getSystemTray();

		MenuItem statusAnzeige = createStatusAnzeigeMenuItem();
		MenuItem register = createRegistrationMenuItem();
		startTimer = createStartTimerMenuItem();
		stopTimer = createStopTimerMenuItem();
		MenuItem about = createAboutMenuItem();
		MenuItem beenden = createBeendenMenuItem(tray);

		// Add components to popup menu
		popup.add(statusAnzeige);
		popup.add(register);
		popup.add(startTimer);
		popup.add(stopTimer);
		popup.addSeparator();
		popup.add(about);
		popup.add(beenden);

		trayIcon = new TrayIcon(IconFactory.createAppIconTray().getImage());
		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
			UserInfo.setTrayIcon(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

	}

	private MenuItem createBeendenMenuItem(final SystemTray tray) {
		MenuItem beenden = new MenuItem("Beenden");
		beenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				logger.info("Closing down Pomodoro Timer");
				System.exit(0);
			}
		});
		return beenden;
	}

	private MenuItem createAboutMenuItem() {
		MenuItem about = new MenuItem("About Pomodoro");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder msg = new StringBuilder();
				msg.append("Pomodoro Version: " + VersionInfo.getVersion());
				msg.append("\n");
				msg.append("Contributors: Manuel Mueller, Adrian Wey, Marco Birrer, Sascha Waser, Rahul Rao");
				JOptionPane.showMessageDialog(null, msg.toString());
			}
		});
		return about;
	}

	private MenuItem createStopTimerMenuItem() {
		stopTimer = new MenuItem("Stop Timer");
		stopTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!UserInfo.exist()) {
					JOptionPane.showMessageDialog(null, "Please register first");

				} else {
					final StopTimerHandler stopHandler = new StopTimerHandler();

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								stopHandler.stopTimer();
							} catch (UnsupportedEncodingException e) {
								JOptionPane.showMessageDialog(null,
										"Please don't use unkown symbols/characters.");
							}
						}
					});
				}
			}
		});

		return stopTimer;
	}

	private MenuItem createStartTimerMenuItem() {
		startTimer = new MenuItem("Start Timer");
		startTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!UserInfo.exist()) {
					JOptionPane.showMessageDialog(null, "Please register first");
				} else {
					final StartTimerHandler startTimer = new StartTimerHandler();

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								startTimer.startTimer();
							} catch (UnsupportedEncodingException e) {
								JOptionPane.showMessageDialog(null,
										"Please don't use unkown symbols/characters.");
							}
						}
					});
				}

			}
		});

		return startTimer;
	}

	private MenuItem createRegistrationMenuItem() {
		MenuItem register = new MenuItem("Register");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final RegistrationUI ui = new RegistrationUI();

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						ui.showRegistrationUI();
					}
				});
			}
		});

		return register;
	}

	private MenuItem createStatusAnzeigeMenuItem() {
		MenuItem statusAnzeige = new MenuItem("Status anzeigen");
		statusAnzeige.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				final UsersStatusUI ui = new UsersStatusUI();
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						ui.showAllUserStatusUI();
					}
				});

				// StringBuilder msg = new StringBuilder();
				// msg.append("Manuel Mueller is busy mastering scrum!");
				// msg.append("\n");
				// msg.append("Marco Birrer is busy relaxing as usual!");
				// msg.append("\n");
				// msg.append("Adi Wey is busy creating strange tests!");
				// msg.append("\n");
				// msg.append("Sascha Waser is busy building a house!");
				// msg.append("\n");
				// msg.append("Rahul Rao is busy deleting code!");
				// JOptionPane.showMessageDialog(null, msg.toString());
			}
		});
		return statusAnzeige;
	}

}
