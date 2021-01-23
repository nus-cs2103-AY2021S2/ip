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
	protected TaskDeadline clone() {
		return new TaskDeadline(this.name, this.done, this.deadline);
	}

	@Override
	public String toString() {
		return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(Task.formatter));
	}
}
