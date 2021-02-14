package soonwee.duke;

import java.time.LocalDateTime;

public class TimedTask extends Task {
	protected LocalDateTime dateTime;

	/**
	 * Instantiates the task with a time.
	 *
	 * @param description description of the task
	 * @param dateTime date time of the task
	 */
	public TimedTask(String description, LocalDateTime dateTime) {
		super(description);
		this.dateTime = dateTime;
	}

	@Override
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
}
