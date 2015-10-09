package ch.css.pomodoro.client;

public class PomodoreSystemUtils {

	private static String host;
	private static String beginPath;

	public static void setHost(String server) {
		host = server;
	}

	public static String getHost() {
		return host;
	}

	public static String getBeginPath() {
		return beginPath;
	}

	public static void setBeginPath(String path) {
		beginPath = path;
	}

	public static String getBasisUrl() {
		return host + beginPath;
	}

}
