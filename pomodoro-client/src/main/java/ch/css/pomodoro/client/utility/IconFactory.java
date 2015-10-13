package ch.css.pomodoro.client.utility;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IconFactory {

   public static final String ICON_APP = "PomodoroTimer.jpeg";

   private static final String ICON_OK = null;
   
   private static final String ICON_NOK = null;

   private static final String ICON_SERVICE_ERROR = null;
   
   private static final String ICON_NOINFO = null;

   private static final int SIZE_SMALL = 11;

   private static final int SIZE_BIG = 16;



   private static Logger logger = LoggerFactory.getLogger(IconFactory.class);

   public static ImageIcon getIconBig(String file) {
      return createImage(file, "", SIZE_BIG);
   }

   public static ImageIcon getIconSmall(String file) {
      return createImage(file, "", SIZE_SMALL);
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
         Image newimg = icon.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
         return new ImageIcon(newimg);
      }
   }

}
