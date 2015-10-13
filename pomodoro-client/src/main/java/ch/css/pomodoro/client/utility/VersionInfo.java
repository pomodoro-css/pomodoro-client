package ch.css.pomodoro.client.utility;

import java.io.IOException;
import java.util.Properties;


public class VersionInfo {
   public static String getVersion() {

    try {
       Properties properties = new Properties();
       properties.load(VersionInfo.class.getClassLoader().getResourceAsStream("version.properties"));
       return properties.getProperty("version");
    } catch (IOException e) {
       return "";
    }

 }
}
