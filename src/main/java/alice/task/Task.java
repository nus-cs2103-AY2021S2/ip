package alice.task;

import java.time.format.DateTimeFormatter;

public abstract class Task implements Cloneable, java.io.Serializable {

	protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy L dd, E");

	protected final String name;
	protected final boolean isDone;

	public Task(String name) {
		this.name = name;
		this.isDone = false;
	}

	public Task(String name, boolean isDone) {
		this.name = name;
		this.isDone = isDone;
	}

	public String getName() {
		return this.name;
	}

	public abstract Task setDone(boolean done);

	@Override
	public abstract Task clone();

	@Override
	public String toString() {
		return String.format("[%s] %s", this.isDone ? "\u2713" : "\u2718", this.name);
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (this.getClass() != object.getClass()) {
			return false;
		}
		Task task = (Task) object;
		if (!this.name.equals(task.name)) {
			return false;
		}
		return this.isDone == task.isDone;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (isDone ? 1 : 0);
		return result;
	}
}
