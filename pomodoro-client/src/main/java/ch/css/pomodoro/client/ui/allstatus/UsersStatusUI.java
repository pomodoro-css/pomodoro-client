package ch.css.pomodoro.client.ui.allstatus;

import java.util.List;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.RegisteredUser;
import ch.css.pomodoro.client.service.StatusRegisteredUsersService;

public class UsersStatusUI extends JFrame {

	private static final long serialVersionUID = -3510538483492789960L;
	private static Logger logger = LoggerFactory.getLogger(UsersStatusUI.class);

	public UsersStatusUI() {
		logger.info("Starting Status UI");
	}

	public void showAllUserStatusUI() {
		this.setTitle("Status");

		StatusRegisteredUsersService service = new StatusRegisteredUsersService();
		List<RegisteredUser> mylist = service.callStatusRegisteredUsers();
		logger.info(mylist.toString());

	}

}
