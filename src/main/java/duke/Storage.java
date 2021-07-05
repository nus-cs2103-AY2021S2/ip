package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {
    private File file;

    /**
     * Creates necessary directories to the file path if not present
     *
     * @param filePath
     * @return TaskList
     * @throws DukeException
     */
    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    /**
     * Returns a TaskList created from the data given in file
     *
     * @return TaskList
     * @throws DukeException
     */
    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();

            while (sc.hasNextLine()) {
                String[] taskData = sc.nextLine().split(" \\| ");
                switch (taskData[0]) {
                case "T":
                    taskList.add(new Todo(taskData[2], taskData[1].equals("1")));
                    break;
                case "D":
                    taskList.add(new Deadline(taskData[2], taskData[1].equals("1"), taskData[3]));
                    break;
                case "E":
                    taskList.add(new Event(taskData[2], taskData[1].equals("1"), taskData[3]));
                    break;
                default:
                }
            }
            sc.close();
            return taskList;

        } catch (IOException e) {
            throw new DukeException("Unable to load data file.");
        }
    }

    /**
     * Writes data to file
     *
     * @param text
     * @throws DukeException
     */
    public void write(String text) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write task list to file.");
        }
    }
}
