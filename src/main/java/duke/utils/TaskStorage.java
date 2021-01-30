package duke.utils;

import duke.exceptions.DukeException;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadlines;
import duke.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class handle the saving and loading of the taskList to/from a specific FilePath.
 */
public class TaskStorage {

	public static final String FILEPATH = "data/Duke.txt";
	public static final String DIRECTORY = "data";

	/**
	 * This method write the content of a taskList to data/Duke.txt.
	 *
	 * @return A string display of a successful save.
	 */
	public static String writeToFiles() {
		List<Task> taskList = Task.getTaskList();
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
		return Ui.SUCCESSFULSAVE;
	}

	/**
	 * This method load the file from data/Duke.txt and display in a list format when the program starts.
	 *
	 * @return the String display of a successful load.
	 */
	public static String loadFiles() {
		String output = Ui.LOADFILE;
		System.out.println(output);
		try {
			File directory = new File(DIRECTORY);
			if (!directory.exists()) {
				System.out.println(Ui.NODIRECTORY);
				directory.mkdir();
				Ui.SLEEP();
			}

			File file = new File(FILEPATH);
			if (!file.exists()) {
				System.out.println(Ui.NOFILE);
				file.createNewFile();
				Ui.SLEEP();
			}

			if (file.length() == 0) {
				Ui.SLEEP();
				System.out.println(Ui.EMPTYFILE);
				return Ui.EMPTYFILE;
			}

			restoreTask(file);
			Ui.DONELOADING();
		} catch (IOException e) {
			System.out.println("IO error!: " + e.getMessage());
		}

		return Ui.SUCESSFULLOAD;
	}

	private static final void restoreTask(File file) throws IOException {
		Scanner sc = new Scanner(file);

		while (sc.hasNext()) {
			String[] line = sc.nextLine().split("@@");
			if (line.length == 3) {
				Todo t = new Todo(line[2], line[1]);

			} else {
				int type = Integer.parseInt(line[0]);
				String done = line[1];
				String taskName = line[2];
				String date = line[3];
				Task t = type == 2 ? new Deadlines(taskName, date, done) : new Event(taskName, date, done);

			}
		}
		sc.close();
	}
}
