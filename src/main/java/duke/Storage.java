package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    /**
     * The path of the storage file where the TaskList is stored.
     */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the appropriate tags onto the task given.
     *
     * @param task The task to update tags.
     * @param tags The tags to be put onto the task.
     */
    public void updateTags(Task task, String tags) {
        if (tags == null || tags == "") {
            return;
        }
        String[] split = tags.split("\\s+");
        for (String s : split) {
            task.setTag(s);
        }
    }

    /**
     * Loads a TaskList from storage, if it exists.
     * Else creates a new storage file.
     *
     * @return the TaskList stored on the hard disk.
     */
    public TaskList load() {
        File taskData = new File(filePath);
        TaskList leest = new TaskList();
        try {
            Scanner s = new Scanner(taskData);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" \\| ");
                switch (line[0]) {
                case "T":
                    leest.add(loadTask(line));
                    break;
                case "D":
                    leest.add(loadDeadline(line));
                    break;
                case "E":
                    leest.add(loadEvent(line));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            try {
                taskData.createNewFile();
            } catch (IOException f) {
                try {
                    Path path = Paths.get("test/");
                    Files.createDirectories(path);
                    taskData.createNewFile();
                } catch (IOException g) {
                    System.err.println("Failed to create directory uwu");
                }
            }
        }
        return leest;
    }

    /**
     * Loads a task from storage.
     *
     * @param line The line representing a task stored in the Duke.txt file.
     * @return The line as a Task.
     */
    private Task loadTask(String[] line) {
        Task task = new Task(line[2]);
        if (line[1].equals("1")) {
            task.setDone();
        }
        if (line.length > 3) {
            updateTags(task, line[3]);
        }
        return task;
    }

    /**
     * Loads a deadline from storage.
     *
     * @param line The line representing a deadline stored in the Duke.txt file.
     * @return The line as a Deadline.
     */
    private Deadline loadDeadline(String[] line) {
        Deadline deadline = new Deadline(line[2], line[3]);
        if (line[1].equals("1")) {
            deadline.setDone();
        }
        if (line.length > 4) {
            updateTags(deadline, line[4]);
        }
        return deadline;
    }

    /**
     * Loads a event from storage.
     *
     * @param line The line representing an event stored in the Duke.txt file.
     * @return The line as an Event.
     */
    private Event loadEvent(String[] line) {
        Event event = new Event(line[2], line[3]);
        if (line[1].equals("1")) {
            event.setDone();
        }
        if (line.length > 4) {
            updateTags(event, line[4]);
        }
        return event;
    }

    /**
     * Writes the task to a file, erasing any previous data.
     *
     * @param filePath  The storage file to be written to.
     * @param textToAdd The data to be written.
     * @throws IOException If the file does not exist.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Appends the task to a file.
     *
     * @param filePath  The storage file to be written to.
     * @param textToAdd The data to be written.
     * @throws IOException If the file does not exist.
     */
    private void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Saves the given TaskList to a file.
     *
     * @param l The TaskList to be saved.
     */
    public void saveAsFile(TaskList l) {
        boolean isFirst = true;
        if (l.isEmpty()) {
            try {
                writeToFile(filePath, " ");
            } catch (IOException e) {
                System.out.println("Something went wrong uwu: " + e.getMessage());
            }
        }
        for (Task t : l.getList()) {
            try {
                if (isFirst) {
                    writeToFile(filePath, t.saveToData());
                    isFirst = false;
                } else {
                    appendToFile(filePath, t.saveToData());
                }
            } catch (IOException e) {
                System.out.println("Something went wrong uwu: " + e.getMessage());
            }
        }
    }

}
