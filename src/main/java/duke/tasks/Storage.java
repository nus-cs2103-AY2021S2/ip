package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.ui.Parser;


/**
 * Handles the loading of tasks when the app starts, as well as the saving of tasks when
 * the app terminates.
 */
public class Storage {
    private final String path;

    /**
     * Initializes a storage/loader with a file path.
     *
     * @param filePath Path to text file from which tasks are loaded, and to which tasks are saved.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Loads saved tasks and returns them in a <code>TaskList</code> object. Note that, if a text file
     * is non-existent at the specified path, then no loading is done and an empty <code>TaskList</code>
     * is returned.
     *
     * @return Returns a <code>TaskList</code> object containing the loaded tasks (if any).
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.path);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                Task newTask = createTaskFromSavedString(taskDetails);
                tasks.addTask(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    /**
     * Save the tasks in the input <code>TaskList</code> into a text file at the specified path.
     * If such a text file already exists, it will be overwritten.
     * If such a text file does not exist, one will be created.
     *
     * @param tasks A collection of <code>Task</code> objects to save.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.path);

            for (Task task : tasks.getListOfTasks()) {
                writer.write(convertTaskToSavableString(task));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates a <code>Task</code> object from a <code>String</code> containing a task's details.
     * The <code>String</code> must follow the very specific format of
     * [ Task Type | Status | Task Description | Time if applicable ],
     * for instance [ D | 1 | CS2103 Quiz | 2021-02-12 02:00 ]
     *
     * @param taskDetails A formatted String, containing a task's details, to convert.
     * @return The corresponding <code>Task</code> object.
     */
    private Task createTaskFromSavedString(String taskDetails) {
        String[] taskDetailsArray = taskDetails.split("\\|", 4);
        String taskType = taskDetailsArray[0].trim();
        String done = taskDetailsArray[1].trim();
        String description = taskDetailsArray[2].trim();
        String dateTimeString = taskDetailsArray[3].trim();
        LocalDateTime dateTime = Parser.convertToDateTime(dateTimeString);

        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(description);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(description, dateTime);
        } else {
            newTask = new Event(description, dateTime);
        }

        if (done.equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    /**
     * Converts a <code>Task</code> object into a <code>String</code> containing the task's details.
     * The output <code>String</code> will have the very specific format of
     * [ Task Type | Status | Task Description | Time if applicable ],
     * for instance [ D | 1 | CS2103 Quiz | 2021-02-12 02:00 ]
     *
     * @param task A <code>Task</code> object to convert to a <code>String</code>.
     * @return The corresponding formatted <code>String</code> containing the input task's details.
     */
    private String convertTaskToSavableString(Task task) {
        String dateTimeString = "";
        String taskType;

        if (task instanceof Deadline) {
            taskType = "D";
            dateTimeString = ((Deadline) task).getByDateTimeString();
        } else if (task instanceof Event) {
            taskType = "E";
            dateTimeString = ((Event) task).getAtDateTimeString();
        } else {
            taskType = "T";
        }

        String done = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        return taskType + " | " + done + " | " + description + " | " + dateTimeString + "\n";
    }
}
