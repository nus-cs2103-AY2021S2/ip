import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskStorage {

	public static final String FILEPATH = "data/Duke.txt";
	public static final String DIRECTORY = "data";

	public static void writeToFiles(List<Task> taskList) {
		try {
			FileWriter fw = new FileWriter(FILEPATH);
			for (Task t : taskList) {
				fw.write(t.getType() + "@@" + t.isDone()
						+ "@@" + t.getTaskName() + (t.getDate().equals("")
						? "" : ("@@" + t.getDate())) + System.lineSeparator());
			}
			fw.close();
		} catch (IOException err) {
			DukeException.saveToFileError();
		}
	}

	public static void loadFiles() {

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
				return;
			}

			restoreTask(file);

		} catch (IOException e) {
			System.out.println("Testing");
		}

	}

	public static final void restoreTask(File file) throws IOException {
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
		Ui.DONELOADING();

	}
}
