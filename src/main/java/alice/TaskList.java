package alice;

import java.util.List;

public class TaskList implements java.io.Serializable {
	private final List<Task> tasks;

	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int count() {
		return tasks.size();
	}
}
