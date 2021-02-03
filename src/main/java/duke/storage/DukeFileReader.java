package duke.storage;

import duke.exceptions.EmptyTaskDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads file input from internal storage
 */
public class DukeFileReader {

    private static TaskList readTaskListFromInternalStorage(String filePath) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        FileDirectoryChecker.prepareFile(filePath);
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            String[] taskBreakdown = taskString.split("\\|");
            boolean isTaskDone = taskBreakdown[1].equals("1") ? true : false;
            String taskName = taskBreakdown[2];
            if (taskBreakdown[0].equals("T")) {
                try {
                    Task task = new Todo(taskName);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("D")) {
                try {
                    Task task = new Deadline(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("E")) {
                try {
                    Task task = new Event(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            }
        }
        return taskList;
    }

    /**
     * loads tasks from internal storage
     * @return a task list containing all the tasks from internal storage
     */
    public static TaskList loadTasks(String filePath) {
        try {
            TaskList taskList = readTaskListFromInternalStorage(filePath);
            return taskList;
        } catch (FileNotFoundException e) {
            Ui.printError(e.getMessage());
        }
        return new TaskList();
    }
}
