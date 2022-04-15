package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
/**
 * A class represents a Storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage.
     * @param path The file path specified by the Duke bot.
     */
    public Storage(String path) {
        filePath = path;
    }

    private void createFileNotExists(File file) throws IOException {
        file.createNewFile();
    }

    /**
     * Returns an ArrayList of tasks that recorded in the storage to the current task list.
     * @return An ArrayList of tasks.
     * @throws DukeException If error occurs during the process.
     */
    public ArrayList<Task> syncFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File(filePath);
            if (!f.exists()) {
                createFileNotExists(f);
            }
            assert(f.exists());
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] keyWords = line.split(" \\| ");
                String taskType = keyWords[0].trim();
                String taskName;
                String taskStatus;
                int taskPriority;
                Task thisTask;

                if (taskType.equals("T")) {
                    taskName = keyWords[3];
                    taskPriority = Integer.parseInt(keyWords[2].trim());
                    thisTask = new Todo(taskName);
                } else if (taskType.equals("D")) {
                    taskName = keyWords[3];
                    LocalDateTime cutOffDate = DateTimeParser.parseDateTime(keyWords[4]);
                    taskPriority = Integer.parseInt(keyWords[2].trim());
                    thisTask = new Deadline(taskName, cutOffDate);
                } else {
                    taskName = keyWords[3];
                    LocalDateTime startDate = DateTimeParser.parseDateTime(keyWords[4]);
                    taskPriority = Integer.parseInt(keyWords[2].trim());
                    thisTask = new Event(taskName, startDate);
                }
                taskStatus = keyWords[1].trim();
                if (taskStatus.equals("1")) {
                    thisTask.complete();
                }
                thisTask.setPriority(taskPriority);
                tasks.add(thisTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Updates the tasks recorded in the storage.
     * @param tasks Tasks to be recorded.
     */
    public void updateInFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            tasks.forEach(t -> {
                sb.append(t.toFileString());
                sb.append("\n");
            });
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Has no targeted file in: " + e.getMessage());
        }
    }
}
