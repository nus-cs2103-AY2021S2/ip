package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

public class TaskList {
	private final List<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(List<Task> tasks) {
		this.tasks = new ArrayList<>();
		this.tasks.addAll(tasks);
	}

	public List<String> encode() {
		return tasks.stream()
                        .map(task -> task.encode())
                        .filter(str -> str != null).collect(Collectors.toList());

	}

	public int size() {
		return tasks.size();
	}

	public void markAsDone(int index) { // TODO: Consider making this immutable class in the future
		tasks.get(index).markAsDone();
	}

	public String getTaskDescription(int index) {
		return tasks.get(index).toString();
	}

	public void list() {
        int i = 0;
        for (final Task t : tasks) {
            System.out.printf("\t%d. %s\n", ++i, t);
        }
	}

	public Task delete(int index) {
		return tasks.remove(index);
	}

	public boolean add(Task task) {
		return tasks.add(task);
	}
}
