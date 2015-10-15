package ch.css.pomodoro.client.utility;

import java.awt.TrayIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.ui.background.PollUserStatus;

public class UserInfo {

	private static Logger logger = LoggerFactory.getLogger(UserInfo.class);

	private static String pNr;
	private static String name;
	private static String gruppe;

	private static PollUserStatus pollUser;
	

	public UserInfo(String pNr, String name, String gruppe, TrayIcon tIcon) {
		setPNummer(pNr);
		setName(name);
		setGroupName(gruppe);
	}

	public static boolean exist() {

		boolean exists = pNr != null && name != null;
		logger.info(String.valueOf(exists));
		return exists;
	}

	public static String getPNummer() {
		return pNr;
	}

	public static void setPNummer(String pNummer) {
		pNr = pNummer;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String userName) {
		name = userName;
	}

	public static String getGroupName() {
		return gruppe;
	}

	public static void setGroupName(String gruppeName) {
		gruppe = gruppeName;
	}
	
	public static PollUserStatus getInstanceOfPollUserStatus(){
		if (pollUser == null) {
			pollUser = new PollUserStatus();
		}
		return pollUser;
	}

}
