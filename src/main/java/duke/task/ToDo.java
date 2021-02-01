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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ToDo)) {
			return false;
		}
		ToDo other = (ToDo) o;
		return this.description.equals(other.description) && this.isDone == other.isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isDone ? 1 : 0);
		return result;
	}
}
