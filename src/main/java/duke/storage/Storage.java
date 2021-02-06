package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Command;

/**
 * Manages storage information for the program.
 */
public class Storage {
    /**
     * File path of save file
     */
    private final String filePath;

    /**
     * Creates new instance of Storage.
     *
     * @param filePath File path of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a list of tasks from the save file.
     *
     * @return List of tasks.
     * @throws IOException   If file could not be read.
     * @throws DukeException If save file is corrupted.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dir = new File("data");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File f = new File(filePath);
        f.createNewFile();
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim();

            if (!input.equals("")) {
                String[] tokens = input.split("\\|");
                Command taskType = Command.valueOf(tokens[0]);
                boolean isTimeFieldPresent = tokens.length >= 5;
                Task task;
                String date;
                String time;
                switch (taskType) {
                case EVENT:
                    date = tokens[3];
                    time = isTimeFieldPresent ? " " + tokens[4] : "";
                    task = new Event(tokens[2], date + time);
                    break;
                case DEADLINE:
                    date = tokens[3];
                    time = isTimeFieldPresent ? " " + tokens[4] : "";
                    task = new Deadline(tokens[2], date + time);
                    break;
                case TODO:
                    task = new Todo(tokens[2]);
                    break;
                default:
                    throw new DukeException("Sorry something when wrong loading your safe file :(");
                }

                if (tokens[1].equals("true")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves a list of tasks to the save file.
     *
     * @param tasks List of tasks to be saved.
     * @throws DukeException If unable to save to file.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            File f = new File(filePath);

            f.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();

            for (Task task : tasks) {
                sb.append(task.serialise()).append('\n');
            }

            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong trying to save your data :(");
        }
    }
}
