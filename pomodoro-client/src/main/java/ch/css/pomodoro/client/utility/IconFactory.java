package ch.css.pomodoro.client.utility;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.UserState;

public class IconFactory {

	public static final String ICON_APP = "tomato_red.png";

	public static final String ICON_ONLINE = "tomato_green.png";

	public static final String ICON_BUSY = "tomato_red.png";

	private static final String ICON_OK = null;

	private static final String ICON_NOK = null;

	private static final String ICON_SERVICE_ERROR = null;

	private static final String ICON_NOINFO = null;

	private static final int SIZE_SMALL = 11;

	private static final int SIZE_TRAY = 16;
	
	private static final int SIZE_BIG = 24;
	

	private static Logger logger = LoggerFactory.getLogger(IconFactory.class);

	public static ImageIcon getIconBig(String file) {
		return createImage(file, "", SIZE_BIG);
	}

	public static ImageIcon getIconSmall(String file) {
		return createImage(file, "", SIZE_SMALL);
	}

	public static ImageIcon getIconTray(String file) {
		return createImage(file, "", SIZE_TRAY);
	}

	public static ImageIcon getStateIcon(String state) {
		return createImage(determineStateIconFile(state), "", SIZE_BIG);
	}

	private static String determineStateIconFile(String state) {
		if (UserState.BUSY.name().equals(state)) {
			return ICON_BUSY;
		}
		return ICON_ONLINE;
	}

	public static ImageIcon createAppIconTray() {
		return createImage(ICON_APP, "PomodoroTimer", SIZE_TRAY);
	}

	public static ImageIcon createAppIconBig() {
		return createImage(ICON_APP, "PomodoroTimer", SIZE_BIG);
	}

	public static ImageIcon createAppIconSmall() {
		return createImage(ICON_APP, "PomodoroTimer", SIZE_SMALL);
	}

	private static ImageIcon createImage(final String path, final String description, int size) {
		final URL imageURL = IconFactory.class.getClassLoader().getResource(path);
		if (imageURL == null) {
			logger.error("Resource not found: " + path);
			return null;
		} else {
			ImageIcon icon = new ImageIcon(imageURL, description);
			Image newimg = icon.getImage().getScaledInstance(size, size,
					java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(newimg);
		}
	}

}
