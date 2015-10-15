package ch.css.pomodoro.client.service;

public class RegisteredUser {

	private String nr;

	private String name;
	private String group;
	private UserState state;
	private int remainingTime;
	private int tomatoTime;

	private String startTime;

	public String getNr() {
		return nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
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

	public Object[] getUserRow() {
		return new Object[] { getState(), getName(), getNr(), getRemainingTime() };
	}

	@Override
	public String toString() {
		return "RegisteredUser [nr=" + nr + ", name=" + name + ", group=" + group + ", state="
				+ state + ", remainingTime=" + remainingTime + ", tomatoTime=" + tomatoTime
				+ ", startTime=" + startTime + "]";
	}
}
