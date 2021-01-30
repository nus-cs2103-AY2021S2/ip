import alice.Alice;
import alice.command.CommandBye;
import alice.command.Parser;
import alice.task.Task;
import alice.task.TaskDeadline;
import alice.task.TaskList;
import alice.task.TaskTodo;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

	private static final Scanner scanner = new Scanner(System.in);

	public static String getDataPath() {
		if (Storage.OS.contains("win")) {
			return String.join(File.separator, System.getenv("USERPROFILE"),
					".alice_save", "data", "save_data");
		} else {
			return String.join(File.separator, "~", ".alice_save", "data", "save_data");
		}
	}

	private static TaskList loadTasks() {
		byte[] data = Storage.loadBytes(getDataPath());
		if (data == null) {
			return new TaskList(new ArrayList<>());
		} else {
			return Storage.deserialize(data, TaskList.class);
		}
	}

	public static boolean saveTasks(TaskList taskList) {
		byte[] data = Storage.serialize(taskList);
		return Storage.saveBytes(getDataPath(), data);
	}

	public static void main(String[] args) {

		List<Task> tl1 = new ArrayList<Task>();
		List<Task> tl2 = new ArrayList<Task>();
		tl1.add(new TaskTodo("a", false));
		tl1.add(new TaskDeadline("b", false, LocalDate.of(2020, 1, 8)));
		tl1.add(new TaskTodo("c", true));
		tl2.add(new TaskTodo("a", false));
		tl2.add(new TaskDeadline("b", false, LocalDate.of(2020, 1, 8)));
		tl2.add(new TaskTodo("c", true));
		System.out.println(new TaskList(tl1).equals(new TaskList(tl2)));

		Alice agent = new Alice(loadTasks(), false);

		System.out.println(agent.getCurrentMessage());
		while (!agent.getDone()) {
			System.out.print(Alice.getPrompt());
			try {
				agent = Parser.parse(scanner.nextLine().trim()).execute(agent);
				if (agent.hasDelta() && !saveTasks(agent.getData())) {
					System.out.println("Error saving tasks to " + getDataPath());
				}
			} catch (NoSuchElementException noSuchElementException) {
				agent = new CommandBye().execute(agent);
			}
			System.out.println(agent.getCurrentMessage());
		}
	}
}
