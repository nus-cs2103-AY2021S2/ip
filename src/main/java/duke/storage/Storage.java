package duke.storage;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

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
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] keyWords = line.split(" \\| ");
                String taskType = keyWords[0].strip();
                String taskName;
                String taskStatus;
                Task thisTask;

                if (taskType.equals("T")) {
                    taskName = keyWords[2];
                    thisTask = new Todo(taskName);
                } else if (taskType.equals("D")) {
                    taskName = keyWords[2];
                    LocalDateTime cutOffDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Deadline(taskName, cutOffDate);
                } else {
                    taskName = keyWords[2];
                    LocalDateTime startDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Event(taskName, startDate);
                }
                taskStatus = keyWords[1].strip();
                if (taskStatus.equals("1")) {
                    thisTask.complete();
                }
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
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Has no targeted file in: " + e.getMessage());
        }
    }


}
