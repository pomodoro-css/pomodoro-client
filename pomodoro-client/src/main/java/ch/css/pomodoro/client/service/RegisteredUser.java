package ch.css.pomodoro.client.service;

import ch.css.pomodoro.client.utility.IconFactory;

public class RegisteredUser {

	private String nr;
	private String name;
	private Group group;
	private UserState state;
	private int remainingTime;
	private int tomatoTime;
	private String startTime;
	private String taskName;

	public String getNr() {
		return nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public boolean hasStartTime() {
		return startTime != null;
	}

	public void setTomatoTime(int time) {
		this.tomatoTime = time;
	}

	public int getTomatoTime() {
		return tomatoTime;
	}

	public String getRemainingTimeInMinAndSec() {
		String min = String.valueOf(getRemainingTime() / 60);
		String sec = String.valueOf(getRemainingTime() % 60);
		return min + " min " + sec + " sec";
	}

	public Object[] getUserRow() {
		return new Object[] { IconFactory.getStateIcon(getState().name()), getName(), getNr(),
				getRemainingTimeInMinAndSec() };
	}

	@Override
	public String toString() {
		return "RegisteredUser [nr=" + nr + ", name=" + name + ", group=" + group + ", state="
				+ state + ", remainingTime=" + remainingTime + ", tomatoTime=" + tomatoTime
				+ ", startTime=" + startTime + "]";
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
