package alice.task;

import java.time.format.DateTimeFormatter;

public abstract class Task implements Cloneable, java.io.Serializable {

	protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy L dd, E");

	protected final String name;
	protected final boolean done;

	public Task(String name) {
		this.name = name;
		this.done = false;
	}

	public Task(String name, boolean done) {
		this.name = name;
		this.done = done;
	}

	public String getName() {
		return this.name;
	}

	public boolean getDone() {
		return this.done;
	}

	public abstract Task setDone(boolean done);

	@Override
	public abstract Task clone();

	@Override
	public String toString() {
		return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.name);
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
		return this.done == task.done;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (done ? 1 : 0);
		return result;
	}
}
