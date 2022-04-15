package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.DueDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage loads existing tasks from the file and saves tasks to the file when required.
 */
public class Storage {
    private String filePath;

    /**
     * Initialises storage with a file path.
     *
     * @param filePath A String indicating the path of the file containing the task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the filepath given during initialisation of Storage
     *
     * @return A task list containing the tasks for the user.
     * @throws FileNotFoundException If the file, given by the filepath during initialisation,
     * does not exist
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String currentTask = fileScanner.nextLine();
            String[] items = currentTask.split(" \\| ");
            boolean done = (items[1].equals("1") ? true : false);
            Task temp = new Todo(items[2]);
            if (items[0].equals("E")) {
                LocalDate date = LocalDate.parse(items[3], formatter);
                temp = new Event(items[2], date);
            } else if (items[0].equals("D")) {
                LocalDate date = LocalDate.parse(items[3], formatter);
                temp = new Deadline(items[2], date);
            }
            if (done) {
                temp = temp.markAsDone();
            }
            tasks.add(temp);
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file indicated by the file path during initialisation.
     *
     * @param taskList List of task to be saved.
     */
    public void save(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getList();
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                String taskToSave = "";
                if (task instanceof Event) {
                    taskToSave += "E";
                } else if (task instanceof Deadline) {
                    taskToSave += "D";
                } else if (task instanceof Todo) {
                    taskToSave += "T";
                }
                taskToSave += " | ";
                if (task.isCompleted()) {
                    taskToSave += "1";
                } else {
                    taskToSave += "0";
                }
                taskToSave += " | ";
                taskToSave += task.getDescription();
                if (task instanceof DueDate) {
                    taskToSave += " | ";
                    taskToSave += ((DueDate) task).getDueDate();
                }
                fw.write(taskToSave + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
