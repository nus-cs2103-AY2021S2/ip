package duke.task;

public class ToDo extends Task {
	public ToDo(String description) {
		super(false, description);
	}

	@Override
	public String toString() {
		return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
	}

	@Override
	public String encode() {
		return String.format("T | %s | %s", this.isDone ? "1" : "0", this.description);
	}
}
