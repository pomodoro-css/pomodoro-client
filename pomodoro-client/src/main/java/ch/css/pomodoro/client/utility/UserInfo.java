package ch.css.pomodoro.client.utility;

public class UserInfo {
	private static UserInfo instance = null;
	
	private static String pNr;
	private static String name;
	private static String gruppe;
	
	protected UserInfo(String pNr, String name, String gruppe) {
		setPNummer(pNr);
		setName(name);
		setGroupName(gruppe);
	}
	
	public static UserInfo getInstance(String pNr, String name, String gruppe) {
		if (instance == null){
			instance = new UserInfo(pNr, name, gruppe);
		}
		return instance;
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


}
