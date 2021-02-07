package alice.task;

import java.util.List;

public class TaskList implements java.io.Serializable {

	private static final long serialVersionUID = 1;

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

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object.getClass() != this.getClass()) {
			return false;
		}
		TaskList taskList = (TaskList) object;
		if (taskList.count() != this.count()) {
			return false;
		}
		for (int i = 0; i < taskList.count(); i++) {
			if (!taskList.tasks.get(i).equals(this.tasks.get(i))) {
				return false;
			}
		}
		return true;
	}
}
