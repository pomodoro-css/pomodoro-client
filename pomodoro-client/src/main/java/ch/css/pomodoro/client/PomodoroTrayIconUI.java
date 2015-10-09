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
		MenuItem pomodoroVersion = new MenuItem("Pomodoro Version");
		MenuItem beenden = new MenuItem("Beenden");

		// Add components to popup menu
		popup.add(statusAnzeige);
		popup.add(pomodoroVersion);
		popup.addSeparator();
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
				JOptionPane.showMessageDialog(null, "All is well");
			}
		});

		pomodoroVersion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Pomodoro Version: " + VersionInfo.getVersion());
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
