package ch.css.pomodoro.client.ui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.utility.IconFactory;
import ch.css.pomodoro.client.utility.VersionInfo;

public class PomodoroTrayIconUI {

	private static Logger logger = LoggerFactory.getLogger(PomodoroTrayIconUI.class);

	private TrayIcon trayIcon;

	public void createAndShowUI() {
		final PopupMenu popup = new PopupMenu();
		final SystemTray tray = SystemTray.getSystemTray();

		MenuItem statusAnzeige = createStatusAnzeigeMenuItem();
		MenuItem register = createRegistrationMenuItem();
		MenuItem startTimer = createStartTimerMenuItem();
		MenuItem stopTimer = createStopTimerMenuItem();
		MenuItem about = createAboutMenuItem();
		MenuItem beenden = createBeendenMenuItem(tray);

		trayIcon = new TrayIcon(IconFactory.createAppIconBig().getImage());
		registerActionListenerOnTrayIcon();

		// Add components to popup menu
		popup.add(statusAnzeige);
		popup.add(register);
		popup.add(startTimer);
		popup.add(stopTimer);
		popup.addSeparator();
		popup.add(about);
		popup.add(beenden);
		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

	}

	private void registerActionListenerOnTrayIcon() {
		trayIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This dialog box is run from System Tray");
			}
		});
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
		MenuItem stopTimer = new MenuItem("Stop Timer");
		stopTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "I am free now!");
			}
		});
		return stopTimer;
	}

	private MenuItem createStartTimerMenuItem() {
		MenuItem startTimer = new MenuItem("Start Timer");
		startTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "I am busy now!");
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
						ui.registerClient();
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
				StringBuilder msg = new StringBuilder();
				msg.append("Manuel Mueller is busy Scrum Mastering!");
				msg.append("\n");
				msg.append("Marco Birrer is relaxing as usual!");
				msg.append("\n");
				msg.append("Adi Wey is busy testing.");
				msg.append("\n");
				msg.append("Sascha Waser is busy with AngularJs.");
				msg.append("\n");
				msg.append("Rahul Rao is busy with Leads!");
				JOptionPane.showMessageDialog(null, msg.toString());
			}
		});
		return statusAnzeige;
	}

}
