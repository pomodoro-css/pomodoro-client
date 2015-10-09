package ch.css.pomodoro.client;

public class PomodoreSystemUtils {
	
	private static String host;
	
	private PomodoreSystemUtils(){
		host = "stub";
	}
	
	public static void setHost(String server){
		host = server;
	}

	public static String getHost() {
		return host;
	}



}
