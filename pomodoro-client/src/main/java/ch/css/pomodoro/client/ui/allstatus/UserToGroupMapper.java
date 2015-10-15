package ch.css.pomodoro.client.ui.allstatus;

import java.util.List;

import org.jvnet.hk2.component.MultiMap;

import ch.css.pomodoro.client.service.RegisteredUser;

public class UserToGroupMapper {

	private MultiMap<String, RegisteredUser> userGroupMap = new MultiMap<String, RegisteredUser>();

	public MultiMap<String,RegisteredUser> mapToGroup(List<RegisteredUser> regUsers) {

		for (RegisteredUser registeredUser : regUsers) {
			userGroupMap.add(registeredUser.getGroup(), registeredUser);

		}
		return userGroupMap;
	}

}
