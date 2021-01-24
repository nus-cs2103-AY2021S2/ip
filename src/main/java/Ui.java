import alice.Alice;
import alice.command.CommandBye;
import alice.command.Parser;
import alice.task.TaskList;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is responsible for "gluing" components together and invoke/
 * perform any I/O operation.
 */

public class Ui {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String PROMPT = ">";

	private static String getDataPath() {
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

	private static boolean saveTasks(TaskList taskList) {
		byte[] data = Storage.serialize(taskList);
		return Storage.saveBytes(getDataPath(), data);
	}

	/**
	 * Entry point of the program.
	 *
	 * @param args Not implemented.
	 */
	public static void main(String[] args) {
		Alice agent = new Alice(loadTasks(), false);
		System.out.println(agent.getCurrentMessage());
		while (!agent.getDone()) {
			System.out.print(PROMPT);
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
