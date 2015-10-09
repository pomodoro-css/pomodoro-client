package ch.css.pomodoro.client;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PomodoroTrayIconUI {

	private static Logger logger = LoggerFactory.getLogger(PomodoroTrayIconUI.class);

	private TrayIcon trayIcon;

	public void createAndShowUI() {
		final PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(IconFactory.createAppIconBig().getImage());

		final SystemTray tray = SystemTray.getSystemTray();

		MenuItem statusAnzeige = new MenuItem("Status anzeigen");
		MenuItem register = new MenuItem("Register");
		MenuItem startTimer = new MenuItem("Start Timer");
		MenuItem stopTimer = new MenuItem("Stop Timer");
		MenuItem about = new MenuItem("About Pomodoro");
		MenuItem beenden = new MenuItem("Beenden");

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

		trayIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This dialog box is run from System Tray");
			}
		});

		statusAnzeige.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder msg = new StringBuilder();
				msg.append("Manuel Mueller is busy Scrum Mastering!");
				msg.append("\n");
				msg.append("Marco Busy is relaxing as usual!");
				msg.append("\n");
				msg.append("Adi Wey is busy testing.");
				msg.append("\n");
				msg.append("Sascha Waser is busy with AngularJs.");
				msg.append("\n");
				msg.append("Rahul Rao is busy with Leads!");
				JOptionPane.showMessageDialog(null, msg.toString());
			}
		});

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

		startTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "I am busy now!");
			}
		});

		stopTimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "I am free now!");
			}
		});

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

		beenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				logger.info("Closing down Pomodoro Timer");
				System.exit(0);
			}
		});
	}

	public void setIcon(boolean isOk) {
		trayIcon.setImage(IconFactory.createAppIconBig().getImage());
	}

	public void showMessage(String title, String text, MessageType type) {
		trayIcon.displayMessage(title, text, type);
	}

}
