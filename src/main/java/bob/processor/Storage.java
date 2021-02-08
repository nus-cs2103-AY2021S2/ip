package bob.processor;

import bob.BobException;
import bob.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores the list of tasks, and is responsible for
 * loading and changing the list of tasks in the hard disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the hard disk into Bob.
     * @return An ArrayList containing all the tasks saved in hard disk
     * @throws BobException if the file cannot be found in the hard disk
     */
    public ArrayList<Task> load() throws BobException {
        String currDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currDirectory, "data");

        ArrayList<Task> taskList = new ArrayList<>();
        File tasksFile = new File(filePath);
        try {
            if (!java.nio.file.Files.exists(path)) {
                java.nio.file.Files.createDirectory(path);
            }
            if (!tasksFile.exists()) {
                tasksFile.createNewFile();
            }
        } catch (IOException e) {
            throw new BobException("Unable to create file.", e);
        }

        try {
            Scanner readFile = new Scanner(tasksFile);

            while (readFile.hasNextLine()) {
                String taskName;
                String nextTask = readFile.nextLine();
                boolean done = false;
                if (nextTask.charAt(4) == '1') {
                    assert nextTask.charAt(4) == '1' || nextTask.charAt(4) == '0';
                    done = true;
                }
                if (nextTask.startsWith("T")) {
                    assert nextTask.startsWith("T")
                            || nextTask.startsWith("D")
                            || nextTask.startsWith("E")
                            : "wrong format in hard disk";
                    taskName = nextTask.substring(8);
                    taskList.add(new Todo(taskName, done));
                } else {
                    int index = nextTask.lastIndexOf("|");
                    taskName = nextTask.substring(8, index - 1);
                    String dateTime = nextTask.substring(index + 2);
                    String dateString = dateTime.substring(0, 11);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    String time = dateTime.substring(12);

                    if (nextTask.startsWith("E")) {
                        taskList.add(new Event(taskName, done, date, time));
                    } else if (nextTask.startsWith("D")) {
                        taskList.add(new Deadline(taskName, done, date, time));
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new BobException("No suitable file found.", e);
        }
        return taskList;
    }

    /**
     * Save the Todo tasks in the hard disk
     * @param task new Todo task to be saved
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void appendToDo(Todo task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            fw.write("T | 0 | " + task.getName() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be found.", e);
        }
    }

    /**
     * Save the Event in the hard disk
     * @param task new Event to be saved
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void appendEvent(Event task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            fw.write("E | 0 | " + task.getName() + " | " + task.getDate() + " "
                    + task.getTime() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be found.", e);
        }
    }

    /**
     * Save the new Deadline in the hard disk
     * @param task new Deadline to be saved
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void appendDeadline(Deadline task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            fw.write("D | 0 | " + task.getName() + " | " + task.getDeadline() + " "
                    + task.getTime() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be found.", e);
        }
    }

    /**
     * Update changes in the hard disk
     * @param task The TaskList to be updated
     * @throws BobException if the named file exists but is a directory rather
     * than a regular file, does not exist but cannot be created, or cannot
     * be opened for any other reason
     */
    public void rewrite(TaskList task) throws BobException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task nextTask : task.getTaskList()) {
                String done = nextTask.getDone() ? "1" : "0";
                String type = nextTask.toString().substring(1, 2);
                String name = nextTask.getName();
                String time = "";
                if (type.equals("E")) {
                    int index = nextTask.toString().indexOf("at: ");
                    time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                } else if (type.equals("D")) {
                    int index = nextTask.toString().indexOf("by: ");
                    time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                }
                fw.write(type + " | " + done + " | " + name + " "
                        + time + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("File cannot be opened.", e);
        }
    }
}
