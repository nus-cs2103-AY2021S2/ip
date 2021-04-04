package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import duke.handler.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * Storage class that handles the loading of data from data file
 * and updating of task information in data file.
 */
public class Storage {
    private static String storagePath;
    private static final int MARK_INDEX = 2;

    /**
     * Default constructor for Storage.
     * @param storagePath path to the storage file.
     */
    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Loads data from the storage file.
     * @return List of tasks.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        Scanner dataReader = null;
        try {
            File file = new File(storagePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            dataReader = new Scanner(file);
            while (dataReader.hasNextLine()) {
                Task task = Parser.parseFromData(dataReader.nextLine());
                tasks.addTask(task);
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("File did not load");
        } finally {
            dataReader.close();
        }
        return tasks;
    }

    /**
     * Writes new tasks into the storage file.
     * @param task task to be added to the storage file.
     */
    public void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(storagePath, true);
            String toWrite = "";
            switch(task.getType()) {
            case "TODO":
                toWrite = String.format("%c|%c|%s",
                        'T',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription());
                break;
            case "DEADLINE":
                toWrite = String.format("%c|%c|%s|%s",
                        'D',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription(), (
                                (Deadline) task).getBy().toString());
                break;
            case "EVENT":
                toWrite = String.format("%c|%c|%s|%s",
                        'E',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription(), (
                                (Event) task).getAt().toString());
                break;
            default:
                System.out.println("Unknown task requested to add to Storage");
            }
            fw.write(toWrite + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File could not be written to. Could not add task.");
        }
    }

    /**
     * Marks the certain task as done in the storage file.
     * @param task task be marked.
     * @param taskNum task index(from 1) in relation to the number of tasks
     *                in the storage file.
     */
    public void markDoneInStorage(Task task, int taskNum) {
        try {
            Path path = Paths.get(storagePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String line = lines.get(taskNum - 1);
            StringBuilder updatedLine = new StringBuilder(line);
            updatedLine.setCharAt(MARK_INDEX, 'X');
            lines.set(taskNum - 1, updatedLine.toString());
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Fail to mark done in storage file.");
        }
    }

    /**
     * Deletes the nth task of the list of tasks.
     * @param taskNum nth task to be removed from the list of tasks
     *                in the storage file.
     */
    public void delete(int taskNum) {
        try {
            Path path = Paths.get(storagePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.remove(taskNum - 1);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Fail to delete item in storage file.");
        }
    }
}
