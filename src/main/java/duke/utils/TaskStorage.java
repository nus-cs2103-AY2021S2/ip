package duke.utils;

import duke.exceptions.DukeException;
import duke.task.*;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class TaskStorage {

	public static final String FILEPATH = "data/Duke.txt";
	public static final String DIRECTORY = "data";


	public static String writeToFiles() {
		List<Task> taskList = TaskList.getTaskList();
		try {
			FileWriter fw = new FileWriter(FILEPATH);
			for (Task t : taskList) {
				String temp = t.getType() + "@@" + t.getDoneStatus()
						+ "@@" + t.getTaskName() + (t.getDate().equals("")
						? "" : ("@@" + t.getDate())) + System.lineSeparator();
				fw.write(temp);
			}
			fw.close();
		} catch (IOException err) {
			DukeException.saveToFileError();
		}
		return Ui.SUCCESSFUL_SAVE;
	}


	public static String loadFiles() {
		String output = Ui.LOAD_FILE;
		System.out.println(output);
		try {
			File directory = new File(DIRECTORY);
			if (!directory.exists()) {
				System.out.println(Ui.NO_DIRECTORY);
				directory.mkdir();
				Ui.SLEEP();
			}

			File file = new File(FILEPATH);
			if (!file.exists()) {
				System.out.println(Ui.NO_FILE);
				file.createNewFile();
				Ui.SLEEP();
			}

			if (file.length() == 0) {
				Ui.SLEEP();
				System.out.println(Ui.EMPTY_FILE);
				return Ui.EMPTY_FILE;
			}

			restoreTask(file);
			Ui.DONELOADING();
		} catch (IOException e) {
			System.out.println("IO error!: " + e.getMessage());
		}

		return Ui.SUCESSFUL_LOAD;
	}

	private static void restoreTask(File file) throws IOException {
		Scanner sc = new Scanner(file);

		while (sc.hasNext()) {
			String[] line = sc.nextLine().split("@@");
			if (line.length == 3) {
				Todo t = new Todo(line[2], line[1]);
				TaskList.addTask(t);

			} else {
				int type = Integer.parseInt(line[0]);
				String done = line[1];
				String taskName = line[2];
				String date = line[3];
				Task t = type == 2 ? new Deadlines(taskName, date, done) : new Event(taskName, date, done);
				TaskList.addTask(t);

			}
		}
		sc.close();
	}
}
