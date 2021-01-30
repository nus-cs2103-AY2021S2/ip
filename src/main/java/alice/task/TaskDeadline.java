package alice.task;

import java.time.LocalDate;

public class TaskDeadline extends Task {

	private final LocalDate deadline;

	public TaskDeadline(String name, LocalDate deadline) {
		super(name);
		this.deadline = deadline;
	}

	public TaskDeadline(String name, boolean done, LocalDate deadline) {
		super(name, done);
		this.deadline = deadline;
	}

	public TaskDeadline setDone(boolean done) {
		return new TaskDeadline(this.name, done, this.deadline);
	}

	@Override
	public TaskDeadline clone() {
		return new TaskDeadline(this.name, this.done, this.deadline);
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(Task.formatter));
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TaskDeadline)) {
			return false;
		}
		if (!super.equals(object)) {
			return false;
		}
		TaskDeadline taskDeadline = (TaskDeadline) object;
		return taskDeadline.deadline.equals(this.deadline);
	}
}
