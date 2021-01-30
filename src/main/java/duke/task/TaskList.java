package duke.task;

import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private static List<Task> taskList = new ArrayList<>();

	private List<Task> list;

	public TaskList() {
		this.list = new ArrayList<>();
	}



	public static void addTask(Task t) {
		taskList.add(t);
	}


	public static void done(int i) {
		try {
			Task t = taskList.get(i - 1);
			taskList.get(i - 1).markDone();
			Ui.doneTask(t);
		} catch (IndexOutOfBoundsException e) {
			DukeException.taskErrorException();
		}
	}

	public static String find(String keyword) {
		int index = 1;
		StringBuilder sb = new StringBuilder("");
		for (Task task : taskList) {
			String taskName = task.getTaskName();
			if (taskName.contains(keyword)) {
				String prefix = task.toString().substring(0, 6);
				sb.append(prefix + " " + index + ". " + task.getTaskName() + task.getDate() + "\n");
				index++;
			}
		}
		if (sb.equals("")) {
			sb.append("~~~~~Sorry ah, no match.~~~~~");
		} else {
			sb.insert(0, "Here are the matches for your search: \n");
		}

		return sb.toString();

	}


	public static void listing() {
		System.out.println(Ui.UPPER);
		for (Task task : taskList) {
			if (task == null) break;
			System.out.println(task);
		}
		System.out.println(Ui.LOWER);
	}



	public static void delete(int i) {
		try {
			Task t = taskList.get(i - 1);
			taskList.remove(i - 1);
			Ui.deleteTask(t);
			for (Task task : taskList) {
				if (task.getIndex() > i) {
					task.changeIndex(task.getIndex() - 1);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			DukeException.taskErrorException();
		}
	}

	public static void clearAllTask() {
		taskList.clear();
	}

	public static List<Task> getTaskList() {
		return taskList;
	}

}
