package ch.css.pomodoro.client;

import java.awt.AWTException;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class PomodoroTrayIconUI {

	private TrayIcon trayIcon;

	public PomodoroTrayIconUI(MouseListener mouseListener, ActionListener actionListener) {

<<<<<<< HEAD
		final PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(IconFactory.createAppIconBig().getImage());
=======
      final PopupMenu popup = new PopupMenu();
      //trayIcon = new TrayIcon();
>>>>>>> branch 'master' of https://github.com/pomodoro-css/pomodoro-client.git

		final SystemTray tray = SystemTray.getSystemTray();

<<<<<<< HEAD
		// Add components to popup menu
		popup.add("Status anzeigen");
		//popup.add("Hilfe öffnen (Pomodoro " + VersionInfo.getVersion() + ")");
		popup.add("Konfiguration");
		popup.addSeparator();
		popup.add("Beenden");
=======
      // Add components to popup menu
      popup.add("Status anzeigen");
      popup.add("Hilfe oeffnen (SWE Gadget " + VersionInfo.getVersion() + ")");
      popup.add("Konfiguration");
      popup.addSeparator();
      popup.add("Beenden");
>>>>>>> branch 'master' of https://github.com/pomodoro-css/pomodoro-client.git

		popup.addActionListener(actionListener);

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

		trayIcon.addMouseListener(mouseListener);
	}

<<<<<<< HEAD
	public void setIcon(boolean isOk) {
		trayIcon.setImage(IconFactory.createAppIconBig().getImage());
	}
=======
   public void setIcon(boolean isOk) {
//      if (isOk) {
//         trayIcon.setImage(IconFactory.createOkIconBig().getImage());
//      } else {
//         trayIcon.setImage(IconFactory.createNokIconBig().getImage());
//      }
   }
>>>>>>> branch 'master' of https://github.com/pomodoro-css/pomodoro-client.git

	public void showMessage(String title, String text, MessageType type) {
		trayIcon.displayMessage(title, text, type);
	}

}
